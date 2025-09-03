package com.example.CrudVuelos.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Fechas {

    // Calcular tiempo de vuelo en dias
    public static int calcularDuracionEnDias(LocalDate salida, LocalDate llegada) {
        if (llegada.isBefore(salida)) {
            throw new IllegalArgumentException("La fecha de llegada no puede ser anterior a la de salida");
        }
        return (int) ChronoUnit.DAYS.between(salida, llegada);
    }
}



