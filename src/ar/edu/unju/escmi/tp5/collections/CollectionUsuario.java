package ar.edu.unju.escmi.tp5.collections;

import java.util.ArrayList;
import java.util.List;
import ar.edu.unju.escmi.tp5.dominio.Usuario;

public class CollectionUsuario {
    public static List<Usuario> usuarios = new ArrayList<>();

    public static void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static Usuario buscarUsuarioPorEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return usuario;
            }
        }
        return null;
    }
    
    public static Usuario buscarUsuarioPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) { 
                return usuario;
            }
        }
        return null;
    }

    public static List<Usuario> listarUsuarios() {
        return usuarios;
    }
}
