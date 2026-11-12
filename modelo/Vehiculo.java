package com.autotechsolutions.modelo;

import java.time.Year;

public class Vehiculo {
    private final String placa;
    private final String marca;
    private final String modelo;
    private final int anio;
    private final String color;
    private final Cliente propietario;

    public Vehiculo(String placa, String marca, String modelo, int anio, String color, Cliente propietario) {
        this.placa = obligatorio(placa, "La placa").toUpperCase();
        this.marca = obligatorio(marca, "La marca");
        this.modelo = obligatorio(modelo, "El modelo");
        if (anio < 1886 || anio > Year.now().getValue() + 1)
            throw new IllegalArgumentException("El año del vehículo no es válido.");
        this.anio = anio;
        this.color = obligatorio(color, "El color");
        this.propietario = java.util.Objects.requireNonNull(propietario, "Debe seleccionar un cliente existente.");
    }

    private static String obligatorio(String valor, String campo) {
        if (valor == null || valor.isBlank()) throw new IllegalArgumentException(campo + " es obligatorio.");
        return valor.trim();
    }

    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAnio() { return anio; }
    public String getColor() { return color; }
    public Cliente getPropietario() { return propietario; }

    @Override
    public String toString() { return placa + " - " + marca + " " + modelo; }
}
