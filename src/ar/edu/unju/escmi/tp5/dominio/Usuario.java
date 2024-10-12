package ar.edu.unju.escmi.tp5.dominio;

public abstract class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String email;

    public Usuario(int id, String nombre, String apellido, String email) {
        this.setId(id);
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public abstract void mostrarDatos();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
