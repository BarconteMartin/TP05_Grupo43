package ar.edu.unju.escmi.tp5.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class FechaUtil {
    
    public static LocalDate obtenerFechaDesdeString(String fechaStr) {
        try {
            return LocalDate.parse(fechaStr);
        } catch (DateTimeParseException e) {
            return null; 
        }
    }
    public static LocalDate obtenerFechaActual() {
        return LocalDate.now();
    }
    
}
