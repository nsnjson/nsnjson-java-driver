package com.github.nsnjson.encoding;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;

import java.util.Optional;

public abstract class AbstractEncoding implements Encoding {

    @Override
    public Optional<JsonNode> encode(JsonNode data) {
        switch (data.getNodeType()) {
            case NULL:    return encodeNull();
            case NUMBER:  return encodeNumber((NumericNode) data);
            case STRING:  return encodeString((TextNode) data);
            case BOOLEAN: return encodeBoolean((BooleanNode) data);
            case ARRAY:   return encodeArray((ArrayNode) data);
            case OBJECT:  return encodeObject((ObjectNode) data);
        }

        return Optional.empty();
    }

}