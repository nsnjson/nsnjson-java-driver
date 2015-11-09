package com.github.nsnjson.decoding;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

import java.util.Optional;

import static com.fasterxml.jackson.databind.node.JsonNodeType.*;
import static com.github.nsnjson.format.CustomFormat.*;

public class CustomDecoding extends AbstractDecoding {

    @Override
    public Optional<JsonNodeType> getType(JsonNode presentation) {
        switch (presentation.get(INDEX_TYPE).asText()) {
            case TYPE_NAME_NULL:
                return Optional.of(NULL);
            case TYPE_NAME_NUMBER:
                return Optional.of(NUMBER);
            case TYPE_NAME_STRING:
                return Optional.of(STRING);
            case TYPE_NAME_BOOLEAN:
                return Optional.of(BOOLEAN);
            case TYPE_NAME_ARRAY:
                return Optional.of(ARRAY);
            case TYPE_NAME_OBJECT:
                return Optional.of(OBJECT);
        }

        return Optional.empty();
    }

    @Override
    public Optional<JsonNode> decodeNull() {
        return Optional.of(NullNode.getInstance());
    }

    @Override
    public Optional<JsonNode> decodeNumber(JsonNode presentation) {
        return Optional.of(presentation.get(INDEX_VALUE));
    }

    @Override
    public Optional<JsonNode> decodeString(JsonNode presentation) {
        return Optional.of(presentation.get(INDEX_VALUE));
    }

    @Override
    public Optional<JsonNode> decodeBoolean(JsonNode presentation) {
        return Optional.of(presentation.get(INDEX_VALUE));
    }

    @Override
    public Optional<JsonNode> decodeArray(JsonNode presentation) {
        ArrayNode array = new ObjectMapper().createArrayNode();

        for (int i = INDEX_VALUE; i < presentation.size(); i++) {
            JsonNode itemPresentation = presentation.get(i);

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

        for (int i = INDEX_VALUE; i < presentation.size(); i++) {
            JsonNode fieldPresentation = presentation.get(i);

            JsonNode namePresentation = fieldPresentation.get(INDEX_NAME);

            String name = namePresentation.asText();

            Optional<JsonNode> valueOption = decode(fieldPresentation.get(INDEX_VALUE));

            if (valueOption.isPresent()) {
                JsonNode value = valueOption.get();

                object.set(name, value);
            }
        }

        return Optional.of(object);
    }

}