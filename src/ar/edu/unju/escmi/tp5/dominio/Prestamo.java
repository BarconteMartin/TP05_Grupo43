package ar.edu.unju.escmi.tp5.dominio;

import java.time.LocalDate;

import ar.edu.unju.escmi.tp5.collections.CollectionPrestamo;

public class Prestamo {
    private int id;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private Libro libro;
    private Usuario usuario;

    
    // Constructor original
    public Prestamo(int id, LocalDate fechaPrestamo, Libro libro, Usuario usuario) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
        this.libro = libro;
        this.usuario = usuario;
        this.fechaDevolucion = null;
        this.libro.setEstado(false);
    }
    
    public Prestamo(LocalDate fechaPrestamo, Libro libro, Usuario usuario) {
        this.fechaPrestamo = fechaPrestamo;
        this.libro = libro;
        this.usuario = usuario;
        this.fechaDevolucion = null;
        this.libro.setEstado(false);
        this.id = generarId(); 
    }

    private int generarId() {
        return CollectionPrestamo.prestamos.size() + 1;
    }

    public Libro getLibro() {
        return libro;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void registrarDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
        this.libro.setEstado(true);
    }

    public void mostrarDatos() {
        System.out.println("Préstamo: " + id + ", Fecha de préstamo: " + fechaPrestamo + ", Fecha de devolución: " + (fechaDevolucion != null ? fechaDevolucion : "No devuelto"));
        libro.mostrarDatos();
        usuario.mostrarDatos();
    }
}
