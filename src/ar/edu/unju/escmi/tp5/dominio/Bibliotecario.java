package ar.edu.unju.escmi.tp5.dominio;

public class Bibliotecario extends Usuario {
    private String legajo;

    public Bibliotecario(int id, String nombre, String apellido, String email, String legajo) {
        super(id, nombre, apellido, email);
        this.legajo = legajo;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Bibliotecario: " + this.getNombre() + " " + this.getApellido() + ", Legajo: " + legajo);
    }
}
