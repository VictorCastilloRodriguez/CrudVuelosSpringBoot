package com.example.CrudVuelos.controller;

import com.example.CrudVuelos.dto.VueloDto;
import com.example.CrudVuelos.model.Vuelo;
import com.example.CrudVuelos.service.VueloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vuelos")
public class VueloController {
    // Inyeccion de service
    private final VueloService service;

    @Autowired
    public VueloController(VueloService service) {
        this.service = service;
    }

    //Devuelve una lista de vuelos filtrados por empresa, lugar de llegada, fecha de salida y orden
    @GetMapping
    public ResponseEntity<List<VueloDto>> getVuelos(
            @RequestParam(required = false) String empresa,
            @RequestParam(required = false) String lugarLlegada,
            @RequestParam(required = false) LocalDate fechaSalida,
            @RequestParam(required = false) String ordenarPor
    ) {
        return ResponseEntity.ok(service.getAll(empresa, lugarLlegada, fechaSalida, ordenarPor));
    }

    //Buscar vuelo por Id
    @GetMapping("/{id}")
    public ResponseEntity<Vuelo> getVueloById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    //Crear vuelo
    @PostMapping
    public ResponseEntity<VueloDto> crearVuelo(@RequestBody @Valid Vuelo vuelo) {
        return ResponseEntity.ok(service.crearVuelo(vuelo));
    }

    // Actualizar vuelo
    @PutMapping("/{id}")
    public ResponseEntity<VueloDto> actualizarVuelo(@PathVariable int id, @RequestBody @Valid Vuelo vueloActualizado) {
        return ResponseEntity.ok(service.actualizarVuelo(id, vueloActualizado));
    }
// Elimina vuelo
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarVuelo(@PathVariable int id) {
        service.eliminarVuelo(id);
        return ResponseEntity.ok("Vuelo eliminado correctamente");
    }
}


