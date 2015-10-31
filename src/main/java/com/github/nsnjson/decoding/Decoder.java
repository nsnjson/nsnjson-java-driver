package com.github.nsnjson.decoding;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

import java.util.Optional;

import static com.github.nsnjson.format.Format.*;

public class Decoder {

    public static Optional<JsonNode> decode(ObjectNode presentation) {
        switch (type(presentation)) {
            case TYPE_MARKER_NULL:
                return decodeNull();
            case TYPE_MARKER_NUMBER:
                return decodeNumber(presentation);
            case TYPE_MARKER_STRING:
                return decodeString(presentation);
            case TYPE_MARKER_BOOLEAN:
                return decodeBoolean(presentation);
            case TYPE_MARKER_ARRAY:
                return decodeArray(presentation);
            case TYPE_MARKER_OBJECT:
                return decodeObject(presentation);
        }

        return Optional.empty();
    }

    private static int type(ObjectNode presentation) {
        return presentation.get(FIELD_TYPE).asInt();
    }

    private static Optional<JsonNode> decodeNull() {
        return Optional.of(NullNode.getInstance());
    }

    private static Optional<JsonNode> decodeBoolean(ObjectNode presentation) {
        if (presentation.has(FIELD_VALUE)) {
            JsonNode valueNode = presentation.get(FIELD_VALUE);

            if (valueNode.isInt()) {
                switch (valueNode.asInt()) {
                    case BOOLEAN_TRUE:
                        return Optional.of(BooleanNode.getTrue());
                    case BOOLEAN_FALSE:
                        return Optional.of(BooleanNode.getFalse());
                }
            }
        }

        return Optional.empty();
    }

    private static Optional<JsonNode> decodeNumber(ObjectNode presentation) {
        if (presentation.has(FIELD_VALUE)) {
            JsonNode valueNode = presentation.get(FIELD_VALUE);

            if (valueNode.isInt()) {
                return Optional.of(IntNode.valueOf(valueNode.asInt()));
            }
            else if (valueNode.isLong()) {
                return Optional.of(LongNode.valueOf(valueNode.asLong()));
            }
            else if (valueNode.isDouble()) {
                return Optional.of(DoubleNode.valueOf(valueNode.asDouble()));
            }
        }

        return Optional.empty();
    }

    private static Optional<JsonNode> decodeString(ObjectNode presentation) {
        if (presentation.has(FIELD_VALUE)) {
            JsonNode value = presentation.get(FIELD_VALUE);

            if (value.isTextual()) {
                return Optional.of(TextNode.valueOf(value.asText()));
            }
         }

        return Optional.empty();
    }


    private static Optional<JsonNode> decodeArray(ObjectNode presentation) {
        if (presentation.has(FIELD_VALUE)) {
            JsonNode valueNode = presentation.get(FIELD_VALUE);

            if (valueNode.isArray()) {
                ArrayNode array = new ObjectMapper().createArrayNode();

                ArrayNode presentationOfArrayItems = (ArrayNode) valueNode;

                for (int i = 0; i < presentationOfArrayItems.size(); i++) {
                    JsonNode itemPresentation = presentationOfArrayItems.get(i);

                    if (itemPresentation.isObject()) {
                        Optional<JsonNode> itemOption = decode((ObjectNode) itemPresentation);

                        if (itemOption.isPresent()) {
                            JsonNode item = itemOption.get();

                            array.add(item);
                        }
                    }
                }

                return Optional.of(array);
            }
        }

        return Optional.empty();
    }

    private static Optional<JsonNode> decodeObject(ObjectNode presentation) {
        if (presentation.has(FIELD_VALUE)) {
            JsonNode valueNode = presentation.get(FIELD_VALUE);

            if (valueNode.isArray()) {
                ObjectNode object = new ObjectMapper().createObjectNode();

                ArrayNode presentationOfObjectFields = (ArrayNode) valueNode;

                for (int i = 0; i < presentationOfObjectFields.size(); i++) {
                    JsonNode fieldPresentation = presentationOfObjectFields.get(i);

                    if (fieldPresentation.isObject()) {
                        if (fieldPresentation.has(FIELD_NAME)) {
                            JsonNode nameNode = fieldPresentation.get(FIELD_NAME);

                            if (nameNode.isTextual()) {
                                String name = nameNode.asText();

                                Optional<JsonNode> valueOption = decode((ObjectNode) fieldPresentation);

                                if (valueOption.isPresent()) {
                                    JsonNode value = valueOption.get();

                                    object.set(name, value);
                                }
                            }
                        }
                    }
                }

                return Optional.of(object);
            }
        }

        return Optional.empty();
    }

}