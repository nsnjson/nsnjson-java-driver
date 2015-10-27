package com.github.nsnjson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.nsnjson.decoding.Decoder;
import com.github.nsnjson.encoding.Encoder;

public class Driver {

    public static ObjectNode encode(JsonNode value) {
        return Encoder.encode(value);
    }

    public static JsonNode decode(ObjectNode presentation) {
        return Decoder.decode(presentation);
    }

}