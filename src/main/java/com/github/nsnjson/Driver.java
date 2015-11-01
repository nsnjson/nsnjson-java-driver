package com.github.nsnjson;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.nsnjson.decoding.*;
import com.github.nsnjson.encoding.*;

import java.util.Optional;

public class Driver {

    public static Optional<JsonNode> encode(JsonNode value) {
        return Encoder.encode(value);
    }

    public static Optional<JsonNode> encode(JsonNode value, Encoding encoding) {
        return Encoder.encode(value, encoding);
    }

    public static Optional<JsonNode> decode(JsonNode presentation) {
        return Decoder.decode(presentation);
    }

    public static Optional<JsonNode> decode(JsonNode presentation, Decoding decoding) {
        return Decoder.decode(presentation, decoding);
    }
}