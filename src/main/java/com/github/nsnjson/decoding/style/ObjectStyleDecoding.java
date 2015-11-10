package com.github.nsnjson.decoding.style;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import com.github.nsnjson.decoding.AbstractDecoding;

import java.util.Optional;

import static com.fasterxml.jackson.databind.node.JsonNodeType.*;
import static com.github.nsnjson.format.Format.*;

public class ObjectStyleDecoding extends AbstractDecoding {

    @Override
    public Optional<JsonNodeType> getType(JsonNode presentation) {
        if (!presentation.has(FIELD_TYPE)) {
            return Optional.of(NULL);
        }

        switch (presentation.get(FIELD_TYPE).asInt()) {
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
        return Optional.of(presentation.get(FIELD_VALUE));
    }

    @Override
    public Optional<JsonNode> decodeString(JsonNode presentation) {
        return Optional.of(presentation.get(FIELD_VALUE));
    }

    @Override
    public Optional<JsonNode> decodeBoolean(JsonNode presentation) {
        switch (presentation.get(FIELD_VALUE).asInt()) {
            case BOOLEAN_TRUE:  return Optional.of(BooleanNode.getTrue());
            case BOOLEAN_FALSE: return Optional.of(BooleanNode.getFalse());
        }

        return Optional.empty();
    }

    @Override
    public Optional<JsonNode> decodeArray(JsonNode presentation) {
        ArrayNode array = new ObjectMapper().createArrayNode();

        ArrayNode itemsPresentation = (ArrayNode) presentation.get(FIELD_VALUE);

        for (int i = 0; i < itemsPresentation.size(); i++) {
            JsonNode itemPresentation = itemsPresentation.get(i);

            decode(itemPresentation).ifPresent(array::add);
        }

        return Optional.of(array);
    }

    @Override
    public Optional<JsonNode> decodeObject(JsonNode presentation) {
        ObjectNode object = new ObjectMapper().createObjectNode();

        ArrayNode fieldsPresentation = (ArrayNode) presentation.get(FIELD_VALUE);

        for (int i = 0; i < fieldsPresentation.size(); i++) {
            JsonNode fieldPresentation = fieldsPresentation.get(i);

            String name = fieldPresentation.get(FIELD_NAME).asText();

            decode(fieldPresentation).ifPresent(value -> object.set(name, value));
        }

        return Optional.of(object);
    }

}