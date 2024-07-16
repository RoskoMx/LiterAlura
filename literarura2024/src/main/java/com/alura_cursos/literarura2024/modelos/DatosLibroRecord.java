package com.alura_cursos.literarura2024.modelos;

import com.alura_cursos.literarura2024.servicios.DeserializadorAutores;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibroRecord(
        @JsonAlias("id") int ID,
        @JsonAlias("title") String titulo,
        @JsonDeserialize(using = DeserializadorAutores.class) @JsonAlias("authors") List<String> autores,
        //@JsonAlias("subjects") String generos,
        @JsonAlias("languages") String[] idiomas,
        @JsonAlias("download_count") int descargas
) {}

