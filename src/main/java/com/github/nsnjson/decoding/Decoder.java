package com.github.nsnjson.decoding;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.nsnjson.decoding.style.*;

import java.util.Optional;

public class Decoder {

    private static final Decoding ARRAY_STYLE_DECODING = new ArrayStyleDecoding();

    private static final Decoding OBJECT_STYLE_DECODING = new ObjectStyleDecoding();

    /**
     * Decodes JSON from specified NSNJSON presentation by custom decoding.
     * @param presentation NSNJSON presentation of JSON
     * @param decoding custom decoding
     * @return JSON
     */
    public static Optional<JsonNode> decode(JsonNode presentation, Decoding decoding) {
        return decoding.decode(presentation);
    }

    public static Optional<JsonNode> decodeWithArrayStyle(JsonNode presentation) {
        return ARRAY_STYLE_DECODING.decode(presentation);
    }

    public static Optional<JsonNode> decodeWithObjectStyle(JsonNode presentation) {
        return OBJECT_STYLE_DECODING.decode(presentation);
    }

}