package com.autotechsolutions.modelo;

import java.math.BigDecimal;
import java.util.Objects;

public class Repuesto {
    private final String codigo;
    private final String nombre;
    private int cantidad;
    private final BigDecimal precioUnitario;
    private final int stockMinimo;

    public Repuesto(String codigo, String nombre, int cantidad, BigDecimal precioUnitario, int stockMinimo) {
        this.codigo = obligatorio(codigo, "El código");
        this.nombre = obligatorio(nombre, "El nombre");
        if (cantidad < 0 || stockMinimo < 0) throw new IllegalArgumentException("Las cantidades no pueden ser negativas.");
        this.precioUnitario = Objects.requireNonNull(precioUnitario, "El precio es obligatorio.");
        if (precioUnitario.signum() <= 0) throw new IllegalArgumentException("El precio debe ser positivo.");
        this.cantidad = cantidad;
        this.stockMinimo = stockMinimo;
    }

    private static String obligatorio(String valor, String campo) {
        if (valor == null || valor.isBlank()) throw new IllegalArgumentException(campo + " es obligatorio.");
        return valor.trim();
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public int getStockMinimo() { return stockMinimo; }
    public boolean necesitaReposicion() { return cantidad <= stockMinimo; }

    public void ingresar(int unidades) {
        if (unidades <= 0) throw new IllegalArgumentException("La entrada debe ser mayor que cero.");
        cantidad = Math.addExact(cantidad, unidades);
    }

    public void descontar(int unidades) {
        if (unidades <= 0) throw new IllegalArgumentException("El descuento debe ser mayor que cero.");
        if (unidades > cantidad) throw new IllegalArgumentException("No hay existencias suficientes.");
        cantidad -= unidades;
    }
}
