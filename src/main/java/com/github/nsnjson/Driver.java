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
     * Encodes JSON to NSNJSON presentation.
     * @see Encoder#encode(JsonNode)
     * @param data any JSON data
     * @return NSNJSON presentation of JSON
     */
    public static Optional<JsonNode> encode(JsonNode data) {
        return Encoder.encode(data);
    }

    /**
     * Encodes JSON to NSNJSON presentation by custom encoding.
     * @see Encoder#encode(JsonNode, Encoding)
     * @param data any JSON data
     * @return NSNJSON presentation of JSON
     */
    public static Optional<JsonNode> encode(JsonNode data, Encoding encoding) {
        return Encoder.encode(data, encoding);
    }

    /**
     * Decodes JSON from specified NSNJSON presentation.
     * @see Decoder#decode(JsonNode)
     * @param presentation NSNJSON presentation of JSON
     * @return JSON
     */
    public static Optional<JsonNode> decode(JsonNode presentation) {
        return Decoder.decode(presentation);
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

}