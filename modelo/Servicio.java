package com.autotechsolutions.modelo;

import java.math.BigDecimal;
import java.util.Objects;

public class Servicio {
    private final String codigo;
    private final String descripcion;
    private final BigDecimal precioBase;
    private final int tiempoEstimadoMinutos;

    public Servicio(String codigo, String descripcion, BigDecimal precioBase, int tiempoEstimadoMinutos) {
        this.codigo = obligatorio(codigo, "El código");
        this.descripcion = obligatorio(descripcion, "La descripción");
        this.precioBase = Objects.requireNonNull(precioBase, "El precio es obligatorio.");
        if (precioBase.signum() <= 0) throw new IllegalArgumentException("El precio debe ser positivo.");
        if (tiempoEstimadoMinutos <= 0) throw new IllegalArgumentException("El tiempo estimado debe ser positivo.");
        this.tiempoEstimadoMinutos = tiempoEstimadoMinutos;
    }

    private static String obligatorio(String valor, String campo) {
        if (valor == null || valor.isBlank()) throw new IllegalArgumentException(campo + " es obligatorio.");
        return valor.trim();
    }

    public String getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public BigDecimal getPrecioBase() { return precioBase; }
    public int getTiempoEstimadoMinutos() { return tiempoEstimadoMinutos; }
}
