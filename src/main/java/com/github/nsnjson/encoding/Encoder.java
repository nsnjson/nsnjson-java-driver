package com.github.nsnjson.encoding;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.nsnjson.encoding.style.*;

import java.util.Optional;

public class Encoder {

    private static final Encoding DEFAULT_ENCODING = new DefaultEncoding();

    private static final Encoding ARRAY_STYLE_ENCODING = new ArrayStyleEncoding();

    private static final Encoding OBJECT_STYLE_ENCODING = new ObjectStyleEncoding();

    /**
     * Encodes JSON to NSNJSON presentation by custom encoding.
     * @param data any JSON data
     * @return NSNJSON presentation of JSON
     */
    public static Optional<JsonNode> encode(JsonNode data, Encoding encoding) {
        return Optional.ofNullable(encoding).orElse(DEFAULT_ENCODING).encode(data);
    }

    public static Optional<JsonNode> encodeWithArrayStyle(JsonNode data) {
        return ARRAY_STYLE_ENCODING.encode(data);
    }

    public static Optional<JsonNode> encodeWithObjectStyle(JsonNode data) {
        return OBJECT_STYLE_ENCODING.encode(data);
    }

}