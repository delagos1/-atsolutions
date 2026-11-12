package com.autotechsolutions.repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;

/**
 * Repositorio en memoria. Mantiene un listado, un índice por clave y snapshots
 * para deshacer operaciones manteniendo sincronizadas ambas estructuras.
 */
public class RepositorioGenerico<T> {
    private final Function<T, String> extractorClave;
    private final ArrayList<T> elementos = new ArrayList<>();
    private final HashMap<String, T> indice = new HashMap<>();
    private final Stack<List<T>> historial = new Stack<>();

    public RepositorioGenerico(Function<T, String> extractorClave) {
        this.extractorClave = java.util.Objects.requireNonNull(extractorClave);
    }

    public void agregar(T elemento) {
        String clave = clave(elemento);
        if (indice.containsKey(clave)) throw new IllegalArgumentException("Ya existe un registro con código: " + clave);
        guardarSnapshot();
        elementos.add(elemento);
        indice.put(clave, elemento);
    }

    public T buscar(String clave) { return indice.get(normalizar(clave)); }

    public boolean eliminar(String clave) {
        String normalizada = normalizar(clave);
        T existente = indice.get(normalizada);
        if (existente == null) return false;
        guardarSnapshot();
        indice.remove(normalizada);
        elementos.remove(existente);
        return true;
    }

    public List<T> listar() { return List.copyOf(elementos); }
    public int cantidad() { return elementos.size(); }
    public boolean deshacer() {
        if (historial.empty()) return false;
        List<T> anterior = historial.pop();
        elementos.clear();
        elementos.addAll(anterior);
        reconstruirIndice();
        return true;
    }

    private void guardarSnapshot() { historial.push(new ArrayList<>(elementos)); }
    private void reconstruirIndice() {
        indice.clear();
        for (T elemento : elementos) indice.put(clave(elemento), elemento);
    }
    private String clave(T elemento) { return normalizar(extractorClave.apply(elemento)); }
    private String normalizar(String clave) {
        if (clave == null || clave.isBlank()) throw new IllegalArgumentException("La clave es obligatoria.");
        return clave.trim().toUpperCase();
    }
}
