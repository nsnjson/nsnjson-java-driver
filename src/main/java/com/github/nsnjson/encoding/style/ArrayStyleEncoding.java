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

            encode(item).ifPresent(presentation::add);
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

            encode(value).ifPresent(valuePresentation -> {
                ArrayNode fieldPresentation = objectMapper.createArrayNode();
                fieldPresentation.add(name);
                fieldPresentation.add(valuePresentation);

                presentation.add(fieldPresentation);
            });
        });

        return Optional.of(presentation);
    }

}