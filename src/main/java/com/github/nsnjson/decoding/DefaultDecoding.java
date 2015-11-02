package com.github.nsnjson.decoding;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

import java.util.Optional;

import static com.fasterxml.jackson.databind.node.JsonNodeType.*;
import static com.github.nsnjson.format.Format.*;

public class DefaultDecoding implements Decoding {

    @Override
    public Optional<JsonNodeType> getType(JsonNode presentation) {
        switch (presentation.get(FIELD_TYPE).asInt()) {
            case TYPE_MARKER_NULL:    return Optional.of(NULL);
            case TYPE_MARKER_NUMBER:  return Optional.of(NUMBER);
            case TYPE_MARKER_STRING:  return Optional.of(STRING);
            case TYPE_MARKER_BOOLEAN: return Optional.of(BOOLEAN);
            case TYPE_MARKER_ARRAY:   return Optional.of(ARRAY);
            case TYPE_MARKER_OBJECT:  return Optional.of(OBJECT);
        }

        return Optional.empty();
    }

    @Override
    public Optional<JsonNode> decodeNull() {
        return Optional.of(NullNode.getInstance());
    }

    @Override
    public Optional<JsonNode> decodeNumber(JsonNode presentation) {
        JsonNode valueNode = presentation.get(FIELD_VALUE);

        NumericNode value = (NumericNode) valueNode;

        return Optional.of(value);
    }

    @Override
    public Optional<JsonNode> decodeString(JsonNode presentation) {
        JsonNode valueNode = presentation.get(FIELD_VALUE);

        TextNode value = (TextNode) valueNode;

        return Optional.of(value);
    }

    @Override
    public Optional<JsonNode> decodeBoolean(JsonNode presentation) {
        JsonNode valueNode = presentation.get(FIELD_VALUE);

        NumericNode value = (NumericNode) valueNode;

        switch (value.asInt()) {
            case BOOLEAN_TRUE:
                return Optional.of(BooleanNode.getTrue());
            case BOOLEAN_FALSE:
                return Optional.of(BooleanNode.getFalse());
            default:
                return Optional.empty();
        }
    }

    @Override
    public Optional<JsonNode> decodeArray(JsonNode presentation) {
        ArrayNode array = new ObjectMapper().createArrayNode();

        JsonNode valueNode = presentation.get(FIELD_VALUE);

        ArrayNode itemsPresentation = (ArrayNode) valueNode;

        for (int i = 0; i < itemsPresentation.size(); i++) {
            JsonNode itemPresentation = itemsPresentation.get(i);

            Optional<JsonNode> itemOption = decode(itemPresentation);

            if (itemOption.isPresent()) {
                JsonNode item = itemOption.get();

                array.add(item);
            }
        }

        return Optional.of(array);
    }

    @Override
    public Optional<JsonNode> decodeObject(JsonNode presentation) {
        ObjectNode object = new ObjectMapper().createObjectNode();

        JsonNode valueNode = presentation.get(FIELD_VALUE);

        ArrayNode fieldsPresentation = (ArrayNode) valueNode;

        for (int i = 0; i < fieldsPresentation.size(); i++) {
            JsonNode fieldPresentation = fieldsPresentation.get(i);

            if (fieldPresentation.has(FIELD_NAME)) {
                JsonNode nameNode = fieldPresentation.get(FIELD_NAME);

                String name = nameNode.asText();

                Optional<JsonNode> valueOption = decode(fieldPresentation);

                if (valueOption.isPresent()) {
                    JsonNode value = valueOption.get();

                    object.set(name, value);
                }
            }
        }

        return Optional.of(object);
    }

    @Override
    public Optional<JsonNode> decode(JsonNode presentation) {
        Optional<JsonNodeType> typeOption = getType(presentation);

        if (typeOption.isPresent()) {
            JsonNodeType type = typeOption.get();

            switch (type) {
                case NULL:    return decodeNull();
                case NUMBER:  return decodeNumber(presentation);
                case STRING:  return decodeString(presentation);
                case BOOLEAN: return decodeBoolean(presentation);
                case ARRAY:   return decodeArray(presentation);
                case OBJECT:  return decodeObject(presentation);
            }
        }

        return Optional.empty();
    }

}