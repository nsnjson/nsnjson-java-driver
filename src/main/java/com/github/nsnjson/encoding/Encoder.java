package com.github.nsnjson.encoding;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

import java.util.*;

import static com.github.nsnjson.format.Format.*;

public class Encoder {

    public static Optional<ObjectNode> encode(JsonNode value) {
        switch (value.getNodeType()) {
            case NULL:
                return encodeNull();
            case BOOLEAN:
                return encodeBoolean((BooleanNode) value);
            case NUMBER:
                return encodeNumber((NumericNode) value);
            case STRING:
                return encodeString((TextNode) value);
            case ARRAY:
                return encodeArray((ArrayNode) value);
            case OBJECT:
                return encodeObject((ObjectNode) value);
        }

        return Optional.empty();
    }

    private static Optional<ObjectNode> encodeNull() {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NULL);

        return Optional.of(presentation);
    }

    private static Optional<ObjectNode> encodeBoolean(BooleanNode value) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_BOOLEAN);
        presentation.put(FIELD_VALUE, value.booleanValue() ? BOOLEAN_TRUE : BOOLEAN_FALSE);

        return Optional.of(presentation);
    }

    private static Optional<ObjectNode> encodeNumber(NumericNode value) {
        if (value.isInt()) {
            return encodeNumberInt(value);
        }
        else if (value.isLong()) {
            return encodeNumberLong(value);
        }
        else if (value.isDouble()) {
            return encodeNumberDouble(value);
        }

        return Optional.empty();
    }

    private static Optional<ObjectNode> encodeNumberInt(NumericNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asInt());

        return Optional.of(presentation);
    }

    private static Optional<ObjectNode> encodeNumberLong(NumericNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asLong());

        return Optional.of(presentation);
    }

    private static Optional<ObjectNode> encodeNumberDouble(NumericNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asDouble());

        return Optional.of(presentation);
    }

    private static Optional<ObjectNode> encodeString(TextNode value) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_STRING);
        presentation.put(FIELD_VALUE, value.asText());

        return Optional.of(presentation);
    }

    private static Optional<ObjectNode> encodeArray(ArrayNode array) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentationOfArrayItems = objectMapper.createArrayNode();

        for (JsonNode item: array) {
            Optional<ObjectNode> itemPresentationOption = encode(item);

            if (itemPresentationOption.isPresent()) {
                ObjectNode encodedItem = itemPresentationOption.get();

                presentationOfArrayItems.add(encodedItem);
            }
        }

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_ARRAY);
        presentation.set(FIELD_VALUE, presentationOfArrayItems);

        return Optional.of(presentation);
    }

    private static Optional<ObjectNode> encodeObject(ObjectNode object) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentationOfObjectFields = objectMapper.createArrayNode();

        for (Iterator<String> namesIterator = object.fieldNames(); namesIterator.hasNext();) {
            String name = namesIterator.next();

            JsonNode value = object.get(name);

            Optional<ObjectNode> valuePresentationOption = encode(value);

            if (valuePresentationOption.isPresent()) {
                ObjectNode valuePresentation = valuePresentationOption.get();

                ObjectNode fieldPresentation = objectMapper.createObjectNode();
                fieldPresentation.put(FIELD_NAME, name);
                fieldPresentation.set(FIELD_TYPE, valuePresentation.get(FIELD_TYPE));

                if (valuePresentation.has(FIELD_VALUE)) {
                    fieldPresentation.set(FIELD_VALUE, valuePresentation.get(FIELD_VALUE));
                }

                presentationOfObjectFields.add(fieldPresentation);
            }
        }

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_OBJECT);
        presentation.set(FIELD_VALUE, presentationOfObjectFields);

        return Optional.of(presentation);
    }

}