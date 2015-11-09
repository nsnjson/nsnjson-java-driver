package com.github.nsnjson.decoding;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

public abstract class AbstractDecoding implements Decoding {

    @Override
    public Optional<JsonNode> decode(JsonNode presentation) {
        return getType(presentation).flatMap(type -> {
            switch (type) {
                case NULL:    return decodeNull();
                case NUMBER:  return decodeNumber(presentation);
                case STRING:  return decodeString(presentation);
                case BOOLEAN: return decodeBoolean(presentation);
                case ARRAY:   return decodeArray(presentation);
                case OBJECT:  return decodeObject(presentation);
                default:
                    return Optional.empty();
            }
        });
    }

}