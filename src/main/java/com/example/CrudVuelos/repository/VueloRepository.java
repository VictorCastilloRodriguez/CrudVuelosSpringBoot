package com.example.CrudVuelos.repository;

import com.example.CrudVuelos.model.Vuelo;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class VueloRepository {

    // simulador de BD
    private final Map<Integer, Vuelo> vuelos = new HashMap<>();

    // Generar Id
    private final AtomicLong idGenerator = new AtomicLong();

    // Precarga de lista de vuelos
    @PostConstruct
    public void preLista() {
        vuelos.put(1, new Vuelo(1, "H251-J", "Emirates", "Barcelona", "Hong Kong", LocalDate.of(2025, 7, 19), LocalDate.of(2025, 7, 20)));
        vuelos.put(2, new Vuelo(2, "G672-Q", "RayanAir", "Barcelona", "Madrid", LocalDate.of(2025, 8, 11), LocalDate.of(2025, 8, 11)));
        vuelos.put(3, new Vuelo(3, "R816-P", "Iberia", "Barcelona", "Canarias", LocalDate.of(2025, 10, 8), LocalDate.of(2025, 10, 8)));
        vuelos.put(4, new Vuelo(4, "Y564-G", "American Airlines", "Girona", "Dublin", LocalDate.of(2025, 11, 25), LocalDate.of(2025, 11, 25)));
        vuelos.put(5, new Vuelo(5, "Q795-T", "Emirates", "Canarias", "Tokyo", LocalDate.of(2025, 11, 16), LocalDate.of(2025, 11, 17)));
        vuelos.put(6, new Vuelo(6, "Z456-E", "Vueling", "Canarias", "Maldivas", LocalDate.of(2025, 5, 19), LocalDate.of(2025, 5, 20)));
        vuelos.put(7, new Vuelo(7, "L159-C", "Iberia", "Canarias", "Tokyo", LocalDate.of(2025, 5, 14), LocalDate.of(2025, 5, 15)));
        vuelos.put(8, new Vuelo(8, "C618-K", "American Airlines", "Barcelona", "New York", LocalDate.of(2025, 12, 29), LocalDate.of(2025, 12, 30)));
        vuelos.put(9, new Vuelo(9, "K498-J", "RayanAir", "Girona", "Moscou", LocalDate.of(2025, 12, 14), LocalDate.of(2025, 12, 15)));
        vuelos.put(10, new Vuelo(10, "L623-I", "American Airlines", "Barcelona", "New York", LocalDate.of(2025, 12, 6), LocalDate.of(2025, 12, 7)));
    }

    // Devolver vuelos como lista
    public List<Vuelo> findAll() {
        return new ArrayList<>(vuelos.values());
    }

    // Buscar vuelo por Id
    public Optional<Vuelo> findById(int id) {
        return Optional.ofNullable(vuelos.get(id));
    }

    // Guardar vuelo
    public Vuelo save(Vuelo vuelo) {
        if (vuelo.getId() == null) {
            vuelo.setId((int) idGenerator.incrementAndGet());
        }
        vuelos.put(vuelo.getId(), vuelo);
        return vuelo;
    }

    // Verificar si existe el vuelo con Id
    public boolean existsById(int id) {
        return vuelos.containsKey(id);
    }

    // Eliminar vuelo por Id
    public void deleteById(int id) {
        vuelos.remove(id);
    }
}

