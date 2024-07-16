package com.alura_cursos.literarura2024.logica;

import com.alura_cursos.literarura2024.modelos.DatosLibroRecord;
import com.alura_cursos.literarura2024.servicios.GutenbergAPI;

import java.util.*;

public class MenuPrincipal {
    private static final Scanner scanner = new Scanner(System.in);
    private static List<DatosLibroRecord> librosSeleccionados = new ArrayList<>();

    public static void menuPrincipale(){
        int opcion;
        do {
            System.out.println("\n\n******************************************* ");
            System.out.println("SELECCIONE UNA OPCIÓN DEL MENÚ: ");
            System.out.println("\n1. Buscar libro por título");
            System.out.println("2. Mostrar libros buscados");
            System.out.println("3. Mostrar autores registrados");
            System.out.println("4. Mostrar autores vivos según el año");
            System.out.println("5. Mostrar libros por idioma");
            System.out.println("\n6. Salir");
            System.out.println("******************************************* ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostrarAutoresVivos();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 6:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    //System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
            }
        } while (opcion != 6);
    }

    private static void buscarLibroPorTitulo() {
        boolean buscarOtroLibro = true;
        while(buscarOtroLibro){
            System.out.print("Ingrese el nombre del libro que desea buscar: ");
            String busqueda = scanner.nextLine();
            DatosLibroRecord[] libros = GutenbergAPI.obtenerDatosLibros(busqueda);

            if (libros.length == 0) {
                System.out.println("Lo siento, no se encontraron coincidencias para su búsqueda.");
                esperarEnter();
            } else {
                imprimirLibros(libros);
                seleccionarLibro(libros);
            }
            if (!preguntarSeguirBuscando()){
                buscarOtroLibro = false;
            }
        }
    }

    private static void imprimirLibros(DatosLibroRecord[] libros) {
        int id = 1;
        for (DatosLibroRecord libro : libros) {
            System.out.println(id + ". Título: " + libro.titulo());
            System.out.println("   Autores: " + String.join(", ", libro.autores()));
            System.out.println("   Descargas: " + libro.descargas());
            System.out.println();
            id++;
        }
        //esperarEnter();
    }

    private static void seleccionarLibro(DatosLibroRecord[] libros) {
        int opcionSeleccionada;
        boolean opcionValida = false;
        while (!opcionValida) {
            System.out.println("Por favor, seleccione el número del libro que desea ver:");
            if (scanner.hasNextInt()) {
                opcionSeleccionada = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del teclado
                if (opcionSeleccionada >= 1 && opcionSeleccionada <= libros.length) {
                    opcionValida = true;
                    DatosLibroRecord libroSeleccionado = libros[opcionSeleccionada - 1];
                    System.out.println("\n\nTítulo: " + libroSeleccionado.titulo());
                    System.out.println("Autores: " + String.join(", ", libroSeleccionado.autores()));
                    //System.out.println("Descargas: " + libroSeleccionado.descargas());
                    System.out.println("Idiomas: " + String.join(", ", libroSeleccionado.idiomas()));
                    //esperarEnter();
                    librosSeleccionados.add(libroSeleccionado);
                } else {
                    System.out.println("Opción inválida. Por favor, intente de nuevo.");
                }
            } else {
                System.out.println("Opción inválida. Por favor, intente de nuevo.\n\n");
                scanner.nextLine(); // Limpiar el buffer del teclado
            }
        }
    }

    private static boolean preguntarSeguirBuscando() {
        System.out.println("\n\n¿Desea buscar otro libro? (s/n)");
        String respuesta = scanner.nextLine();

        return !respuesta.equalsIgnoreCase("n");

//        if (respuesta.equalsIgnoreCase("n")) {
//            System.out.println("Volviendo al menú principal...");
//        }
    }

    private static void esperarEnter() {
        System.out.println("Presiona Enter para continuar...");
        scanner.nextLine();
    }



    private static void mostrarLibrosBuscados() {
        if (librosSeleccionados.isEmpty()) {
            System.out.println("No se han seleccionado libros aún.");
        } else {
            System.out.println("\n\nLibros seleccionados:");
            for (DatosLibroRecord libro : librosSeleccionados) {
                System.out.println("\n++++++++++++++++++++++++");
                System.out.println("Título: " + libro.titulo());
                System.out.println("Autores: " + String.join(", ", libro.autores()));
                System.out.println("Descargas: " + libro.descargas());
                System.out.println("Idiomas: " + String.join(", ", libro.idiomas()));
                System.out.println("++++++++++++++++++++++++");
            }
        }
        //esperarEnter();
    }

    public static void mostrarAutoresRegistrados() {
        // Crear un conjunto para almacenar los autores únicos
        Set<String> autoresUnicos = new HashSet<>();

        // Recorrer la lista de libros y agregar los autores al conjunto
        for (DatosLibroRecord libro : librosSeleccionados) {
            autoresUnicos.addAll(libro.autores());
        }

        // Imprimir la lista de autores únicos
        System.out.println("Autores registrados:");
        for (String autor : autoresUnicos) {
            System.out.println("- " + autor);
        }
    }

    private static void mostrarAutoresVivos() {
        int anioBusqueda;

        // Pedir al usuario que ingrese el año a buscar
        System.out.print("Ingrese el año que desea buscar: ");
        anioBusqueda = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        // Crear un conjunto para almacenar los autores vivos
        Set<String> autoresVivos = new HashSet<>();

        // Recorrer la lista de libros seleccionados
        for (DatosLibroRecord libro : librosSeleccionados) {
            // Recorrer la lista de autores del libro
            for (String autor : libro.autores()) {
                // Verificar si el autor vivió en el año buscado
                if (estaVivoEnAnio(autor, anioBusqueda)) {
                    autoresVivos.add(autor);
                }
            }
        }

        // Imprimir la lista de autores vivos
        System.out.println("Autores vivos en el año " + anioBusqueda + ":");
        for (String autor : autoresVivos) {
            System.out.println("- " + autor);
        }
    }

    private static boolean estaVivoEnAnio(String autor, int anio) {
        // Aquí debes implementar la lógica para determinar si un autor estuvo vivo en un año determinado
        // Puedes utilizar la información de la API o alguna otra fuente de datos sobre los autores
        // Por ejemplo, podrías tener una estructura de datos que almacene los años de nacimiento y muerte de cada autor

        // Por ahora, vamos a suponer que todos los autores están vivos en el año buscado
        return true;
    }



    private static void mostrarLibrosPorIdioma() {
        // Implementa la lógica para mostrar los libros por idioma
        System.out.println("Opción 5: Mostrar libros por idioma");
    }
}
