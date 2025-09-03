package com.example.CrudVuelos.service;

import com.example.CrudVuelos.dto.VueloDto;
import com.example.CrudVuelos.model.Vuelo;
import com.example.CrudVuelos.repository.VueloRepository;
import com.example.CrudVuelos.utils.Fechas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VueloService {

    // Inyeccion de repositorio
    private final VueloRepository repository;

    @Autowired
    public VueloService(VueloRepository repository) {
        this.repository = repository;
    }

    // filtros por empresa,lugar de llegada, fecha de salida
    public List<VueloDto> getAll(String empresa, String lugarLlegada, LocalDate fechaSalida, String ordenarPor) {
        return repository.findAll().stream()
                .filter(v -> empresa == null || v.getEmpresa().equalsIgnoreCase(empresa))
                .filter(v -> lugarLlegada == null || v.getLugarLlegada().equalsIgnoreCase(lugarLlegada))
                .filter(v -> fechaSalida == null || v.getFechaSalida().equals(fechaSalida))
                .sorted((v1, v2) -> {
                    if ("empresa".equalsIgnoreCase(ordenarPor)) {
                        return v1.getEmpresa().compareToIgnoreCase(v2.getEmpresa());
                    } else if ("lugarLlegada".equalsIgnoreCase(ordenarPor)) {
                        return v1.getLugarLlegada().compareToIgnoreCase(v2.getLugarLlegada());
                    } else if ("nombre".equalsIgnoreCase(ordenarPor)) {
                        return v1.getNombreVuelo().compareToIgnoreCase(v2.getNombreVuelo());
                    } else if ("id".equalsIgnoreCase(ordenarPor)) {
                        return Integer.compare(v1.getId(), v2.getId());
                    } else {
                        return v1.getFechaSalida().compareTo(v2.getFechaSalida());
                    }
                })
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    // Valida las fechas del vuelo, guardar vuelo en repositorio y devolucion como dto
    public VueloDto crearVuelo(Vuelo vuelo) {
        validarFechas(vuelo);
        Vuelo creado = repository.save(vuelo);
        return convertirADto(creado);
    }

    // Actualizar vuelo
    public VueloDto actualizarVuelo(int id, Vuelo vueloActualizado) {
        getById(id);
        vueloActualizado.setId(id);
        validarFechas(vueloActualizado);
        Vuelo actualizado = repository.save(vueloActualizado);
        return convertirADto(actualizado);
    }

    // Buscar vuelo por Id
    public Vuelo getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vuelo con ID " + id + " no encontrado"));
    }

    // Eliminar vuelo
    public void eliminarVuelo(int id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Vuelo con ID " + id + " no existe");
        }
        repository.deleteById(id);
    }

    // Convertir Vuelo a VueloDto con calculo de duracion de vuelo en dias
    private VueloDto convertirADto(Vuelo v) {
        return new VueloDto(
                v.getId(),
                v.getNombreVuelo(),
                v.getEmpresa(),
                v.getLugarSalida(),
                v.getLugarLlegada(),
                v.getFechaSalida(),
                v.getFechaLlegada(),
                Fechas.calcularDuracionEnDias(v.getFechaSalida(), v.getFechaLlegada())
        );
    }

    // Verifica que la fecha de llegada no sea anterior a la de salida.
    private void validarFechas(Vuelo vuelo) {
        if (vuelo.getFechaLlegada().isBefore(vuelo.getFechaSalida())) {
            throw new IllegalArgumentException("La fecha de llegada no puede ser anterior a la de salida");
        }
    }
}


