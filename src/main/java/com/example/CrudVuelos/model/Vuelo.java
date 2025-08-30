package com.example.CrudVuelos.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vuelo {
    private Integer id;
    @NotBlank
    private String nombreVuelo;
    @NotBlank
    private String empresa;
    @NotBlank
    private String lugarSalida;
    @NotBlank
    private String lugarLlegada;
    @NotNull
    private LocalDate fechaSalida;
    @NotNull
    private LocalDate fechaLlegada;
}
