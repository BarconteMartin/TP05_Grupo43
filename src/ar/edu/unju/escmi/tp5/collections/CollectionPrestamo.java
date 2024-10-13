package ar.edu.unju.escmi.tp5.collections;

import java.util.ArrayList;
import java.util.List;
import ar.edu.unju.escmi.tp5.dominio.Prestamo;

public class CollectionPrestamo {
    public static List<Prestamo> prestamos = new ArrayList<>();
    private static int idCounter = 1;

    public static int generarId() {
        return idCounter++;
    }
    
    
    public static void agregarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    public static List<Prestamo> listarPrestamos() {
        return prestamos;
    }
}
