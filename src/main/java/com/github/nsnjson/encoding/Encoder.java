package com.github.nsnjson.encoding;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

import java.util.Iterator;

import static com.github.nsnjson.format.Format.*;

public class Encoder {

    public static ObjectNode encode(JsonNode value) {
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

        return null;
    }

    private static ObjectNode encodeNull() {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode valueInfo = objectMapper.createObjectNode();
        valueInfo.put(FIELD_TYPE, TYPE_MARKER_NULL);

        return valueInfo;
    }

    private static ObjectNode encodeBoolean(BooleanNode value) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode valueInfo = objectMapper.createObjectNode();
        valueInfo.put(FIELD_TYPE, TYPE_MARKER_BOOLEAN);
        valueInfo.put(FIELD_VALUE, value.booleanValue() ? BOOLEAN_TRUE : BOOLEAN_FALSE);

        return valueInfo;
    }

    private static ObjectNode encodeNumber(NumericNode value) {
        if (value.isInt()) {
            return encodeNumberInt(value);
        }
        else if (value.isLong()) {
            return encodeNumberLong(value);
        }
        else if (value.isDouble()) {
            return encodeNumberDouble(value);
        }

        return null;
    }

    private static ObjectNode encodeNumberInt(NumericNode value) {
        ObjectNode valueInfo = new ObjectMapper().createObjectNode();
        valueInfo.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        valueInfo.put(FIELD_VALUE, value.asInt());

        return valueInfo;
    }

    private static ObjectNode encodeNumberLong(NumericNode value) {
        ObjectNode valueInfo = new ObjectMapper().createObjectNode();
        valueInfo.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        valueInfo.put(FIELD_VALUE, value.asLong());

        return valueInfo;
    }

    private static ObjectNode encodeNumberDouble(NumericNode value) {
        ObjectNode valueInfo = new ObjectMapper().createObjectNode();
        valueInfo.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        valueInfo.put(FIELD_VALUE, value.asDouble());

        return valueInfo;
    }

    private static ObjectNode encodeString(TextNode value) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode valueInfo = objectMapper.createObjectNode();
        valueInfo.put(FIELD_TYPE, TYPE_MARKER_STRING);
        valueInfo.put(FIELD_VALUE, value.asText());

        return valueInfo;
    }

    private static ObjectNode encodeArray(ArrayNode array) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode encodedItems = objectMapper.createArrayNode();

        for (JsonNode item: array) {
            ObjectNode encodedItem = encode(item);

            if (encodedItem != null) {
                encodedItems.add(encodedItem);
            }
        }

        ObjectNode arrayInfo = objectMapper.createObjectNode();
        arrayInfo.put(FIELD_TYPE, TYPE_MARKER_ARRAY);
        arrayInfo.set(FIELD_VALUE, encodedItems);

        return arrayInfo;
    }

    private static ObjectNode encodeObject(ObjectNode object) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode encodedFields = objectMapper.createArrayNode();

        for (Iterator<String> namesIterator = object.fieldNames(); namesIterator.hasNext();) {
            String name = namesIterator.next();

            JsonNode value = object.get(name);

            ObjectNode encodedValue = encode(value);

            if (encodedValue != null) {
                ObjectNode encodedField = objectMapper.createObjectNode();
                encodedField.put(FIELD_NAME, name);
                encodedField.set(FIELD_TYPE, encodedValue.get(FIELD_TYPE));

                if (encodedValue.has(FIELD_VALUE)) {
                    encodedField.set(FIELD_VALUE, encodedValue.get(FIELD_VALUE));
                }

                encodedFields.add(encodedField);
            }
        }

        ObjectNode objectInfo = objectMapper.createObjectNode();
        objectInfo.put(FIELD_TYPE, TYPE_MARKER_OBJECT);
        objectInfo.set(FIELD_VALUE, encodedFields);

        return objectInfo;
    }

}