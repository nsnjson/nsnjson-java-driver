package com.github.nsnjson.encoding;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

public class Encoder {

    private static final Encoding DEFAULT_ENCODING = new DefaultEncoding();

    public static Optional<JsonNode> encode(JsonNode json) {
        return DEFAULT_ENCODING.encode(json);
    }

    public static Optional<JsonNode> encode(JsonNode json, Encoding encoding) {
        return Optional.ofNullable(encoding).orElse(DEFAULT_ENCODING).encode(json);
    }

}