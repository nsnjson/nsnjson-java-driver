package com.github.nsnjson.decoding;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

import static com.github.nsnjson.format.Format.*;

public class Decoder {

    public static JsonNode decode(ObjectNode presentation) {
        String presentationType = type(presentation);

        if (TYPE_MARKER_NULL.equals(presentationType)) {
            return decodeNull();
        }
        else if (TYPE_MARKER_BOOLEAN.equals(presentationType)) {
            return decodeBoolean(presentation);
        }
        else if (TYPE_MARKER_NUMBER.equals(presentationType)) {
            return decodeNumber(presentation);
        }
        else if (TYPE_MARKER_STRING.equals(presentationType)) {
            return decodeString(presentation);
        }
        else if (TYPE_MARKER_ARRAY.equals(presentationType)) {
            return decodeArray(presentation);
        }
        else if (TYPE_MARKER_OBJECT.equals(presentationType)) {
            return decodeObject(presentation);
        }

        return null;
    }

    private static String type(ObjectNode presentation) {
        return presentation.get(FIELD_TYPE).asText();
    }

    private static NullNode decodeNull() {
        return NullNode.getInstance();
    }

    private static BooleanNode decodeBoolean(ObjectNode presentation) {
        if (presentation.has(FIELD_VALUE)) {
            JsonNode valueNode = presentation.get(FIELD_VALUE);

            if (valueNode.isInt()) {
                switch (valueNode.asInt()) {
                    case BOOLEAN_TRUE:
                        return BooleanNode.getTrue();
                    case BOOLEAN_FALSE:
                        return BooleanNode.getFalse();
                }
            }
        }

        return null;
    }

    private static NumericNode decodeNumber(ObjectNode presentation) {
        if (presentation.has(FIELD_VALUE)) {
            JsonNode valueNode = presentation.get(FIELD_VALUE);

            if (valueNode.isInt()) {
                return new IntNode(valueNode.asInt());
            }
            else if (valueNode.isLong()) {
                return new LongNode(valueNode.asLong());
            }
            else if (valueNode.isDouble()) {
                return new DoubleNode(valueNode.asDouble());
            }
        }

        return null;
    }

    private static TextNode decodeString(ObjectNode presentation) {
        if (presentation.has(FIELD_VALUE)) {
            JsonNode value = presentation.get(FIELD_VALUE);

            if (value.isTextual()) {
                return (TextNode) value;
            }
         }

        return null;
    }


    private static ArrayNode decodeArray(ObjectNode presentation) {
        if (presentation.has(FIELD_VALUE)) {
            JsonNode valueNode = presentation.get(FIELD_VALUE);

            if (valueNode.isArray()) {
                ArrayNode array = new ObjectMapper().createArrayNode();

                ArrayNode encodedItems = (ArrayNode) valueNode;

                for (int i = 0; i < encodedItems.size(); i++) {
                    JsonNode encodedItem = encodedItems.get(i);

                    if (encodedItem.isObject()) {
                        JsonNode item = decode((ObjectNode) encodedItem);

                        if (item != null) {
                            array.add(item);
                        }
                    }
                }

                return array;
            }
        }

        return null;
    }

    private static ObjectNode decodeObject(ObjectNode presentation) {
        if (presentation.has(FIELD_VALUE)) {
            JsonNode valueNode = presentation.get(FIELD_VALUE);

            if (valueNode.isArray()) {
                ObjectNode object = new ObjectMapper().createObjectNode();

                ArrayNode encodedFields = (ArrayNode) valueNode;

                for (int i = 0; i < encodedFields.size(); i++) {
                    JsonNode encodedField = encodedFields.get(i);

                    if (encodedField.isObject() && encodedField.has(FIELD_NAME)) {
                        JsonNode nameNode = encodedField.get(FIELD_NAME);

                        if (nameNode.isTextual()) {
                            String name = nameNode.asText();

                            JsonNode value = decode((ObjectNode) encodedField);

                            if (value != null) {
                                object.set(name, value);
                            }
                        }
                    }
                }

                return object;
            }
        }

        return null;
    }

}