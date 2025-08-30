package com.example.CrudVuelos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VueloDto {
    private Integer id;
    private String nombre;
    private String compañia;
    private String lSalida;
    private String lLlegada;
    private LocalDate fSalida;
    private LocalDate fLlegada;
    private Integer duracionDias;
}
