package com.github.nsnjson.encoding.style;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import com.github.nsnjson.encoding.AbstractEncoding;

import java.util.Optional;

import static com.github.nsnjson.format.Format.*;

public class ArrayStyleEncoding extends AbstractEncoding {

    @Override
    public Optional<JsonNode> encodeNull() {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentation = objectMapper.createArrayNode();

        return Optional.of(presentation);
    }

    @Override
    public Optional<JsonNode> encodeNumber(NumericNode value) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentation = objectMapper.createArrayNode();
        presentation.add(TYPE_MARKER_NUMBER);
        presentation.add(value);

        return Optional.of(presentation);
    }

    @Override
    public Optional<JsonNode> encodeString(TextNode value) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentation = objectMapper.createArrayNode();
        presentation.add(TYPE_MARKER_STRING);
        presentation.add(value);

        return Optional.of(presentation);
    }

    @Override
    public Optional<JsonNode> encodeBoolean(BooleanNode value) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentation = objectMapper.createArrayNode();
        presentation.add(TYPE_MARKER_BOOLEAN);
        presentation.add(value.asBoolean() ? BOOLEAN_TRUE : BOOLEAN_FALSE);

        return Optional.of(presentation);
    }

    @Override
    public Optional<JsonNode> encodeArray(ArrayNode array) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentation = objectMapper.createArrayNode();
        presentation.add(TYPE_MARKER_ARRAY);

        for (int i = 0; i < array.size(); i++) {
            JsonNode item = array.get(i);

            Optional<JsonNode> itemPresentationOption = encode(item);

            if (itemPresentationOption.isPresent()) {
                JsonNode itemPresentation = itemPresentationOption.get();

                presentation.add(itemPresentation);
            }
        }

        return Optional.of(presentation);
    }

    @Override
    public Optional<JsonNode> encodeObject(ObjectNode object) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentation = objectMapper.createArrayNode();
        presentation.add(TYPE_MARKER_OBJECT);

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

                presentation.add(fieldPresentation);
            }
        });

        return Optional.of(presentation);
    }

}