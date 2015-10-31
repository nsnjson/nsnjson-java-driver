package com.github.nsnjson.encoding;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

import java.util.*;
import java.util.function.Function;

import static com.fasterxml.jackson.databind.node.JsonNodeType.*;
import static com.github.nsnjson.format.Format.*;

public class Encoder {

    private static final Map<JsonNodeType, Function<JsonNode, Optional<JsonNode>>> resolvers = new HashMap<>();
    static {
        resolvers.put(NULL, (json) -> encodeNull());
        resolvers.put(NUMBER, (json) -> encodeNumber((NumericNode) json));
        resolvers.put(STRING, (json) -> encodeString((TextNode) json));
        resolvers.put(BOOLEAN, (json) -> encodeBoolean((BooleanNode) json));
        resolvers.put(ARRAY, (json) -> encodeArray((ArrayNode) json));
        resolvers.put(OBJECT, (json) -> encodeObject((ObjectNode) json));
    }

    public static Optional<JsonNode> encode(JsonNode value) {
        return Optional.ofNullable(resolvers.get(value.getNodeType())).flatMap(resolver -> resolver.apply(value));
    }

    private static Optional<JsonNode> encodeNull() {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NULL);

        return Optional.of(presentation);
    }

    private static Optional<JsonNode> encodeBoolean(BooleanNode value) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_BOOLEAN);
        presentation.put(FIELD_VALUE, value.booleanValue() ? BOOLEAN_TRUE : BOOLEAN_FALSE);

        return Optional.of(presentation);
    }

    private static Optional<JsonNode> encodeNumber(NumericNode value) {
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

    private static Optional<JsonNode> encodeNumberInt(NumericNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asInt());

        return Optional.of(presentation);
    }

    private static Optional<JsonNode> encodeNumberLong(NumericNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asLong());

        return Optional.of(presentation);
    }

    private static Optional<JsonNode> encodeNumberDouble(NumericNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asDouble());

        return Optional.of(presentation);
    }

    private static Optional<JsonNode> encodeString(TextNode value) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_STRING);
        presentation.put(FIELD_VALUE, value.asText());

        return Optional.of(presentation);
    }

    private static Optional<JsonNode> encodeArray(ArrayNode array) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentationOfArrayItems = objectMapper.createArrayNode();

        for (JsonNode item: array) {
            Optional<JsonNode> itemPresentationOption = encode(item);

            if (itemPresentationOption.isPresent()) {
                JsonNode encodedItem = itemPresentationOption.get();

                presentationOfArrayItems.add(encodedItem);
            }
        }

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_ARRAY);
        presentation.set(FIELD_VALUE, presentationOfArrayItems);

        return Optional.of(presentation);
    }

    private static Optional<JsonNode> encodeObject(ObjectNode object) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentationOfObjectFields = objectMapper.createArrayNode();

        for (Iterator<String> namesIterator = object.fieldNames(); namesIterator.hasNext();) {
            String name = namesIterator.next();

            JsonNode value = object.get(name);

            Optional<JsonNode> valuePresentationOption = encode(value);

            if (valuePresentationOption.isPresent()) {
                JsonNode valuePresentation = valuePresentationOption.get();

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