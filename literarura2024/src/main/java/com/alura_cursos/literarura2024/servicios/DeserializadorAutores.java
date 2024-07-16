package com.alura_cursos.literarura2024.servicios;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeserializadorAutores extends StdDeserializer<List<String>> {
    public DeserializadorAutores() {
        this(null);
    }

    protected DeserializadorAutores(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        List<String> authors = new ArrayList<>();

        if (node.isArray()) {
            for (JsonNode authorNode : node) {
                if (authorNode.isObject()) {
                    authors.add(authorNode.get("name").asText());
                } else {
                    authors.add(authorNode.asText());
                }
            }
        } else if (node.isObject()) {
            authors.add(node.get("name").asText());
        }

        return authors;
    }
}

