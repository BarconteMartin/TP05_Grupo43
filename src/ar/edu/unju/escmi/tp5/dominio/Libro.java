package ar.edu.unju.escmi.tp5.dominio;

public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private String isbn;
    private boolean estado;

    public Libro(int id, String titulo, String autor, String isbn, boolean estado) {
        this.setId(id);
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }
    
    public void setDisponible(boolean disponible) {
        this.estado = disponible;
    }
    public boolean isDisponible() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void mostrarDatos() {
        String disponibilidad = (estado) ? "Disponible" : "No disponible";
        System.out.println("Libro: " + titulo + ", Autor: " + autor + ", ISBN: " + isbn + ", Estado: " + disponibilidad);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
