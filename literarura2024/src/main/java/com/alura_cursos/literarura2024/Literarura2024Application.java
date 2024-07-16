package com.alura_cursos.literarura2024;

import com.alura_cursos.literarura2024.modelos.DatosLibroRecord;
import com.alura_cursos.literarura2024.servicios.GutenbergAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static com.alura_cursos.literarura2024.logica.MenuPrincipal.menuPrincipale;

@SpringBootApplication
public class Literarura2024Application {

	public static void main(String[] args) {

		menuPrincipale();

//		SpringApplication.run(Literarura2024Application.class, args);
//		Scanner scanner = new Scanner(System.in);
//		System.out.print("Ingrese el nombre del libro que desea buscar: ");
//		String busqueda = scanner.nextLine();
//
//		DatosLibroRecord[] libros = GutenbergAPI.obtenerDatosLibros(busqueda);
//
//		for (DatosLibroRecord libro : libros) {
//			System.out.println("Título: " + libro.titulo());
//			System.out.println("Autores: " + String.join(", ", libro.autores()));
//			System.out.println("Descargas: " + libro.descargas());
//			System.out.println();
//		}
	}

}
