package com.github.nsnjson.decoding;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

public class Decoder {

    private static final Decoding DEFAULT_DECODING = new DefaultDecoding();

    /**
     * Decodes JSON from specified NSNJSON presentation by custom decoding.
     * @param presentation NSNJSON presentation of JSON
     * @param decoding custom decoding
     * @return JSON
     */
    public static Optional<JsonNode> decode(JsonNode presentation, Decoding decoding) {
        return decoding.decode(presentation);
    }

}