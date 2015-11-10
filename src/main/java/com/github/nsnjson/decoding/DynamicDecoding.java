package com.github.nsnjson.decoding;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

public interface DynamicDecoding extends Decoding {

    default Optional<Object> decodeDynamic(JsonNode presentation) {
        return decode(presentation).flatMap(Optional::of);
    }

}