package ar.edu.unju.escmi.tp5.collections;

import java.util.ArrayList;
import java.util.List;
import ar.edu.unju.escmi.tp5.dominio.Libro;

public class CollectionLibro {
    public static List<Libro> libros = new ArrayList<>();

    public static void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public static Libro buscarLibroPorTitulo(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }

    public static List<Libro> listarLibros() {
        return libros;
    }
}