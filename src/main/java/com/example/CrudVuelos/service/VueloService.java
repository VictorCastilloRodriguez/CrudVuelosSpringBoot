package com.example.CrudVuelos.service;

import com.example.CrudVuelos.dto.VueloDto;
import com.example.CrudVuelos.model.Vuelo;
import com.example.CrudVuelos.repository.VueloRepository;
import com.example.CrudVuelos.utils.Fechas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VueloService {

    private final VueloRepository repository;

    @Autowired
    public VueloService(VueloRepository repository) {
        this.repository = repository;
    }

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
                .map(v -> new VueloDto(
                        v.getId(),
                        v.getNombreVuelo(),
                        v.getEmpresa(),
                        v.getLugarSalida(),
                        v.getLugarLlegada(),
                        v.getFechaSalida(),
                        v.getFechaLlegada(),
                        (int) Fechas.calcularDuracionEnDias(v.getFechaSalida(), v.getFechaLlegada())
                ))
                .collect(Collectors.toList());
    }

    public Vuelo getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vuelo con ID " + id + " no encontrado"));
    }

    public Vuelo crearVuelo(Vuelo vuelo) {
        return repository.save(vuelo);
    }

    public Vuelo actualizarVuelo(int id, Vuelo vueloActualizado) {
        Vuelo existente = getById(id);
        vueloActualizado.setId(id);
        return repository.save(vueloActualizado);
    }

    public void eliminarVuelo(int id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Vuelo con ID " + id + " no existe");
        }
        repository.deleteById(id);
    }
}

