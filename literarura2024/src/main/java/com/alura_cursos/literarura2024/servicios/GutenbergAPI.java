package com.alura_cursos.literarura2024.servicios;

import com.alura_cursos.literarura2024.modelos.DatosLibroRecord;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

public class GutenbergAPI {
    private static final String API_URL = "https://gutendex.com/books/";

    public static DatosLibroRecord[] obtenerDatosLibros(String busqueda) {
        try {
            // Realizar la solicitud a la API
            URL url = new URL(API_URL + "?search=" + busqueda.replace(" ", "+"));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Leer la respuesta de la API
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(connection.getInputStream());

            // Acceder al apartado "results"
            JsonNode resultsNode = rootNode.get("results");
            Iterator<JsonNode> iterator = resultsNode.iterator();

            // Iterar sobre los resultados y mapear los datos
            DatosLibroRecord[] libros = new DatosLibroRecord[resultsNode.size()];
            int i = 0;
            while (iterator.hasNext()) {
                JsonNode bookNode = iterator.next();
                DatosLibroRecord bookData = objectMapper.treeToValue(bookNode, DatosLibroRecord.class);
                libros[i++] = bookData;
            }

            return libros;
        } catch (IOException e) {
            e.printStackTrace();
            return new DatosLibroRecord[0];
        }
    }
}
