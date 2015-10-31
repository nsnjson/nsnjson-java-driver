package com.github.nsnjson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.nsnjson.decoding.Decoder;
import com.github.nsnjson.encoding.Encoder;

import java.util.Optional;

public class Driver {

    public static Optional<ObjectNode> encode(JsonNode value) {
        return Encoder.encode(value);
    }

    public static Optional<JsonNode> decode(ObjectNode presentation) {
        return Decoder.decode(presentation);
    }

}