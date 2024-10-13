package ar.edu.unju.escmi.tp5.dominio;

public class Alumno extends Usuario {
    private String curso;
    private String numeroLibreta;

    
    public Alumno(int id, String nombre, String apellido, String email, String curso, String numeroLibreta) {
        super(id, nombre, apellido, email);
        this.curso = curso;
        this.numeroLibreta = numeroLibreta;
    }

    
    @Override
    public void mostrarDatos() {
        System.out.println("Alumno: " + this.getNombre() + " " + this.getApellido() + ", Curso: " + curso + ", N° Libreta: " + numeroLibreta);
    }
}
