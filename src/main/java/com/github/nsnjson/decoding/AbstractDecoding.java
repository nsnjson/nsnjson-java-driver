package com.github.nsnjson.decoding;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import java.util.Optional;

public abstract class AbstractDecoding implements Decoding {

    @Override
    public Optional<JsonNode> decode(JsonNode presentation) {
        Optional<JsonNodeType> typeOption = getType(presentation);

        if (typeOption.isPresent()) {
            JsonNodeType type = typeOption.get();

            switch (type) {
                case NULL:    return decodeNull();
                case NUMBER:  return decodeNumber(presentation);
                case STRING:  return decodeString(presentation);
                case BOOLEAN: return decodeBoolean(presentation);
                case ARRAY:   return decodeArray(presentation);
                case OBJECT:  return decodeObject(presentation);
            }
        }

        return Optional.empty();
    }

}