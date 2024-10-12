package ar.edu.unju.escmi.tp5.main;

import ar.edu.unju.escmi.tp5.collections.*;
import ar.edu.unju.escmi.tp5.dominio.*;
import ar.edu.unju.escmi.tp5.exceptions.*;
import ar.edu.unju.escmi.tp5.utils.FechaUtil;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("1 - Registrar libro");
            System.out.println("2 - Registrar usuario");
            System.out.println("3 - Préstamo de libro");
            System.out.println("4 - Devolución de libro");
            System.out.println("5 - Listar libros");
            System.out.println("6 - Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    registrarLibro(sc);
                    break;
                case 2:
                    registrarUsuario(sc);
                    break;
                case 3:
                    registrarPrestamo(sc);
                    break;
                case 4:
                    devolverLibro(sc);
                    break;
                case 5:
                    listarLibros();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 6);
    }

    private static void registrarLibro(Scanner sc) {
        System.out.print("Ingrese el ID del libro: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese el título: ");
        String titulo = sc.nextLine();
        System.out.print("Ingrese el autor: ");
        String autor = sc.nextLine();
        System.out.print("Ingrese el ISBN: ");
        String isbn = sc.nextLine();

        Libro libro = new Libro(id, titulo, autor, isbn, true);
        CollectionLibro.agregarLibro(libro);
        System.out.println("Libro registrado.");
    }

    private static void registrarUsuario(Scanner sc) {
        System.out.print("Ingrese el tipo de usuario (1 - Alumno, 2 - Bibliotecario): ");
        int tipoUsuario = sc.nextInt();
        sc.nextLine(); 

        System.out.print("Ingrese el ID del usuario: ");
        int idUsuario = sc.nextInt();
        sc.nextLine();

        Usuario usuarioExistente = CollectionUsuario.buscarUsuarioPorId(idUsuario);
        if (usuarioExistente != null) {
            System.out.println("Error: El ID de usuario ya está en uso. Intente con un ID diferente.");
            return; 
        }

        System.out.print("Ingrese el nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese el apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Ingrese el email: ");
        String email = sc.nextLine();

        if (tipoUsuario == 1) {
            // Registro de Alumno
            System.out.print("Ingrese el curso: ");
            String curso = sc.nextLine();

            System.out.print("Ingrese el número de libreta: ");
            String libreta = sc.nextLine();

            Alumno alumno = new Alumno(idUsuario, nombre, apellido, email, curso, libreta);
            CollectionUsuario.agregarUsuario(alumno);
            System.out.println("Alumno registrado correctamente.");
        } else if (tipoUsuario == 2) {
            // Registro de Bibliotecario
            System.out.print("Ingrese el legajo: ");
            String legajo = sc.nextLine();

            Bibliotecario bibliotecario = new Bibliotecario(idUsuario, nombre, apellido, email, legajo);
            CollectionUsuario.agregarUsuario(bibliotecario);
            System.out.println("Bibliotecario registrado correctamente.");
        } else {
            System.out.println("Tipo de usuario no válido.");
        }
    }


    private static void registrarPrestamo(Scanner sc) {
        System.out.print("Ingrese el ID del usuario: ");
        int userId = sc.nextInt();
        sc.nextLine();

        try {
            Usuario usuario = CollectionUsuario.buscarUsuarioPorId(userId);
            if (usuario == null) {
                throw new UsuarioNoRegistradoException("El usuario con ID " + userId + " no está registrado.");
            }

            System.out.print("Ingrese el título del libro: ");
            String titulo = sc.nextLine();
            
            Libro libro = CollectionLibro.buscarLibroPorTitulo(titulo);
            if (libro == null) {
                throw new LibroNoEncontradoException("El libro con el título \"" + titulo + "\" no fue encontrado.");
            }

            if (!libro.isDisponible()) {
                throw new LibroNoDisponibleException("El libro no está disponible.");
            }

            Prestamo prestamo = new Prestamo(CollectionPrestamo.generarId(), LocalDate.now(), libro, usuario);
            CollectionPrestamo.agregarPrestamo(prestamo);
            
            libro.setDisponible(false);
            System.out.println("Préstamo registrado correctamente.");

        } catch (UsuarioNoRegistradoException | LibroNoEncontradoException | LibroNoDisponibleException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }


    private static void devolverLibro(Scanner sc) {
        System.out.print("Ingrese el título del libro: ");
        String titulo = sc.nextLine();
        Libro libro = CollectionLibro.buscarLibroPorTitulo(titulo);

        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }

        if (libro.isDisponible()) {
            System.out.println("El libro ya está disponible.");
            return;
        }

        System.out.print("Ingrese la fecha de devolución (YYYY-MM-DD): ");
        String fechaDevolucionStr = sc.nextLine();
        LocalDate fechaDevolucion = FechaUtil.obtenerFechaDesdeString(fechaDevolucionStr);

        if (fechaDevolucion == null) {
            System.out.println("Formato de fecha inválido. Asegúrese de usar el formato YYYY-MM-DD.");
            return;
        }

        for (Prestamo prestamo : CollectionPrestamo.prestamos) {
            if (prestamo.getLibro().equals(libro) && prestamo.getFechaDevolucion() == null) {
                prestamo.registrarDevolucion(fechaDevolucion);
                System.out.println("Libro devuelto.");
                return;
            }
        }

        System.out.println("No se encontró un préstamo activo para este libro.");
    }


    private static void listarLibros() {
        for (Libro libro : CollectionLibro.listarLibros()) {
            libro.mostrarDatos();
        }
    }
}
