package com.github.nsnjson;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.nsnjson.decoding.*;
import com.github.nsnjson.encoding.*;

import java.util.Optional;

/**
 * NSNJSON Driver.
 */
public class Driver {

    /**
     * Encodes JSON to NSNJSON presentation by custom encoding.
     * @see Encoder#encode(JsonNode, Encoding)
     * @param data any JSON data
     * @return NSNJSON presentation of JSON
     */
    public static Optional<JsonNode> encode(JsonNode data, Encoding encoding) {
        return Encoder.encode(data, encoding);
    }

    public static Optional<JsonNode> encodeWithArrayStyle(JsonNode data) {
        return Encoder.encodeWithArrayStyle(data);
    }

    public static Optional<JsonNode> encodeWithObjectStyle(JsonNode data) {
        return Encoder.encodeWithObjectStyle(data);
    }

    /**
     * Decodes JSON from specified NSNJSON presentation by custom decoding.
     * @see Decoder#decode(JsonNode, Decoding)
     * @param presentation NSNJSON presentation of JSON
     * @param decoding custom decoding
     * @return JSON
     */
    public static Optional<JsonNode> decode(JsonNode presentation, Decoding decoding) {
        return Decoder.decode(presentation, decoding);
    }

    public static Optional<JsonNode> decodeWithArrayStyle(JsonNode presentation) {
        return Decoder.decodeWithArrayStyle(presentation);
    }

    public static Optional<JsonNode> decodeWithObjectStyle(JsonNode presentation) {
        return Decoder.decodeWithObjectStyle(presentation);
    }

}