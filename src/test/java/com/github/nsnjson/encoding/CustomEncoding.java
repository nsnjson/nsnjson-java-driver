package com.github.nsnjson.encoding;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

import java.util.Optional;

import static com.github.nsnjson.format.CustomFormat.*;

public class CustomEncoding extends AbstractEncoding {

    @Override
    public Optional<JsonNode> encodeNull() {
        ArrayNode presentation = new ObjectMapper().createArrayNode();
        presentation.add(TYPE_NAME_NULL);

        return Optional.of(presentation);
    }

    @Override
    public Optional<JsonNode> encodeNumber(NumericNode value) {
        ArrayNode presentation = new ObjectMapper().createArrayNode();
        presentation.add(TYPE_NAME_NUMBER);
        presentation.add(value);

        return Optional.of(presentation);
    }

    @Override
    public Optional<JsonNode> encodeString(TextNode value) {
        ArrayNode presentation = new ObjectMapper().createArrayNode();
        presentation.add(TYPE_NAME_STRING);
        presentation.add(value);

        return Optional.of(presentation);
    }

    @Override
    public Optional<JsonNode> encodeBoolean(BooleanNode value) {
        ArrayNode presentation = new ObjectMapper().createArrayNode();
        presentation.add(TYPE_NAME_BOOLEAN);
        presentation.add(value);

        return Optional.of(presentation);
    }

    @Override
    public Optional<JsonNode> encodeArray(ArrayNode array) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentation = objectMapper.createArrayNode();
        presentation.add(TYPE_NAME_ARRAY);

        for (JsonNode value : array) {
            Optional<JsonNode> itemPresentationOption = encode(value);

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
        presentation.add(TYPE_NAME_OBJECT);

        object.fieldNames().forEachRemaining(name -> {
            JsonNode value = object.get(name);

            Optional<JsonNode> valuePresentationOption = encode(value);

            if (valuePresentationOption.isPresent()) {
                JsonNode valuePresentation = valuePresentationOption.get();

                ArrayNode fieldPresentation = objectMapper.createArrayNode();
                fieldPresentation.add(name);
                fieldPresentation.add(valuePresentation);

                presentation.add(fieldPresentation);
            }
        });

        return Optional.of(presentation);
    }

}