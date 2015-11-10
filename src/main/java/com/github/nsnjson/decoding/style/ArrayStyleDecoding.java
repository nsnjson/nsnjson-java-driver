package com.github.nsnjson.decoding.style;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import com.github.nsnjson.decoding.AbstractDecoding;

import java.util.Optional;

import static com.fasterxml.jackson.databind.node.JsonNodeType.*;
import static com.github.nsnjson.format.Format.*;

public class ArrayStyleDecoding extends AbstractDecoding {

    @Override
    public Optional<JsonNodeType> getType(JsonNode presentation) {
        if (presentation.size() == 0) {
            return Optional.of(NULL);
        }

        switch (presentation.get(INDEX_TYPE).asInt()) {
            case TYPE_MARKER_NUMBER:
                return Optional.of(NUMBER);
            case TYPE_MARKER_STRING:
                return Optional.of(STRING);
            case TYPE_MARKER_BOOLEAN:
                return Optional.of(BOOLEAN);
            case TYPE_MARKER_ARRAY:
                return Optional.of(ARRAY);
            case TYPE_MARKER_OBJECT:
                return Optional.of(OBJECT);
        }

        return Optional.empty();

    }

    @Override
    public Optional<JsonNode> decodeNull() {
        return Optional.of(NullNode.getInstance());
    }

    @Override
    public Optional<JsonNode> decodeNumber(JsonNode presentation) {
        return Optional.of(presentation.get(INDEX_VALUE));
    }

    @Override
    public Optional<JsonNode> decodeString(JsonNode presentation) {
        return Optional.of(presentation.get(INDEX_VALUE));
    }

    @Override
    public Optional<JsonNode> decodeBoolean(JsonNode presentation) {
        switch (presentation.get(INDEX_VALUE).asInt()) {
            case BOOLEAN_TRUE:  return Optional.of(BooleanNode.getTrue());
            case BOOLEAN_FALSE: return Optional.of(BooleanNode.getFalse());
        }

        return Optional.empty();
    }

    @Override
    public Optional<JsonNode> decodeArray(JsonNode presentation) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode array = objectMapper.createArrayNode();

        for (int i = INDEX_VALUE; i < presentation.size(); i++) {
            decode(presentation.get(i)).ifPresent(array::add);
        }

        return Optional.of(array);
    }

    @Override
    public Optional<JsonNode> decodeObject(JsonNode presentation) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode object = objectMapper.createObjectNode();

        for (int i = INDEX_VALUE; i < presentation.size(); i++) {
            JsonNode fieldPresentation = presentation.get(i);

            String name = fieldPresentation.get(INDEX_NAME).asText();

            decode(fieldPresentation.get(INDEX_VALUE)).ifPresent(value -> object.set(name, value));
        }

        return Optional.of(object);
    }

}