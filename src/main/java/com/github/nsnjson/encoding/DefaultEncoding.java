package com.github.nsnjson.encoding;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

import java.util.Optional;

import static com.github.nsnjson.format.Format.*;

public class DefaultEncoding implements Encoding {

    @Override
    public Optional<JsonNode> encodeNull() {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NULL);

        return Optional.of(presentation);
    }

    @Override
    public Optional<JsonNode> encodeNumber(NumericNode value) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.set(FIELD_VALUE, value);

        return Optional.of(presentation);
    }

    @Override
    public Optional<JsonNode> encodeString(TextNode value) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_STRING);
        presentation.set(FIELD_VALUE, value);

        return Optional.of(presentation);
    }

    @Override
    public Optional<JsonNode> encodeBoolean(BooleanNode value) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_BOOLEAN);
        presentation.put(FIELD_VALUE, value.asBoolean() ? BOOLEAN_TRUE : BOOLEAN_FALSE);

        return Optional.of(presentation);
    }

    @Override
    public Optional<JsonNode> encodeArray(ArrayNode array) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode itemsPresentation = objectMapper.createArrayNode();

        for (int i = 0; i < array.size(); i++) {
            JsonNode item = array.get(i);

            Optional<JsonNode> itemPresentationOption = encode(item);

            if (itemPresentationOption.isPresent()) {
                JsonNode itemPresentation = itemPresentationOption.get();

                itemsPresentation.add(itemPresentation);
            }
        }

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_ARRAY);
        presentation.set(FIELD_VALUE, itemsPresentation);

        return Optional.of(presentation);
    }

    @Override
    public Optional<JsonNode> encodeObject(ObjectNode object) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode fieldsPresentation = objectMapper.createArrayNode();

        object.fieldNames().forEachRemaining(name -> {
            JsonNode value = object.get(name);

            Optional<JsonNode> valuePresentationOption = encode(value);

            if (valuePresentationOption.isPresent()) {
                JsonNode valuePresentation = valuePresentationOption.get();

                ObjectNode fieldPresentation = objectMapper.createObjectNode();
                fieldPresentation.put(FIELD_NAME, name);

                if (valuePresentation.has(FIELD_TYPE)) {
                    fieldPresentation.set(FIELD_TYPE, valuePresentation.get(FIELD_TYPE));
                }

                if (valuePresentation.has(FIELD_VALUE)) {
                    fieldPresentation.set(FIELD_VALUE, valuePresentation.get(FIELD_VALUE));
                }

                fieldsPresentation.add(fieldPresentation);
            }
        });

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_OBJECT);
        presentation.set(FIELD_VALUE, fieldsPresentation);

        return Optional.of(presentation);
    }

    @Override
    public Optional<JsonNode> encode(JsonNode json) {
        switch (json.getNodeType()) {
            case NULL:
                return encodeNull();
            case NUMBER:
                return encodeNumber((NumericNode) json);
            case STRING:
                return encodeString((TextNode) json);
            case BOOLEAN:
                return encodeBoolean((BooleanNode) json);
            case ARRAY:
                return encodeArray((ArrayNode) json);
            case OBJECT:
                return encodeObject((ObjectNode) json);
            default:
                return Optional.empty();
        }
    }
}