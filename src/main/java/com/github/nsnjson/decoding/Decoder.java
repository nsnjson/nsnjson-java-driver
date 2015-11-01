package com.github.nsnjson.decoding;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

public class Decoder {

    private static final Decoding DEFAULT_DECODING = new DefaultDecoding();

    public static Optional<JsonNode> decode(JsonNode presentation) {
        return DEFAULT_DECODING.decode(presentation);
    }

    public static Optional<JsonNode> decode(JsonNode json, Decoding decoding) {
        return Optional.ofNullable(decoding).orElse(DEFAULT_DECODING).decode(json);
    }

}