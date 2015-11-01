package com.github.nsnjson.encoding;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

public class Encoder {

    private static final Encoding DEFAULT_ENCODING = new DefaultEncoding();

    public static Optional<JsonNode> encode(JsonNode json) {
        return DEFAULT_ENCODING.encode(json);
    }

}