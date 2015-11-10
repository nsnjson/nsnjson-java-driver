package com.github.nsnjson.encoding;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

public interface DynamicEncoding extends Encoding {

    default Optional<JsonNode> encodeDynamic(Object data) {
        if (data instanceof JsonNode) {
            encode((JsonNode) data);
        }

        return Optional.empty();
    }

}