package com.autotechsolutions.modelo;

import java.util.Objects;

public class Cliente {
    private final String codigo;
    private final String nombreCompleto;
    private final String identidad;
    private final String telefono;
    private final String direccion;

    public Cliente(String codigo, String nombreCompleto, String identidad, String telefono, String direccion) {
        this.codigo = obligatorio(codigo, "El código");
        this.nombreCompleto = obligatorio(nombreCompleto, "El nombre completo");
        this.identidad = obligatorio(identidad, "La identidad");
        this.telefono = obligatorio(telefono, "El teléfono");
        this.direccion = obligatorio(direccion, "La dirección");
    }

    private static String obligatorio(String valor, String campo) {
        if (valor == null || valor.isBlank()) throw new IllegalArgumentException(campo + " es obligatorio.");
        return valor.trim();
    }

    public String getCodigo() { return codigo; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getIdentidad() { return identidad; }
    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cliente otro)) return false;
        return codigo.equalsIgnoreCase(otro.codigo);
    }

    @Override
    public int hashCode() { return Objects.hash(codigo.toLowerCase()); }

    @Override
    public String toString() { return codigo + " - " + nombreCompleto; }
}
