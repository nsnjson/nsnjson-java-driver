package com.github.nsnjson.decoding;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import java.util.Optional;

public interface Decoding {

    Optional<JsonNodeType> getType(JsonNode presentation);

    Optional<JsonNode> decodeNull();

    Optional<JsonNode> decodeNumber(JsonNode presentation);

    Optional<JsonNode> decodeString(JsonNode presentation);

    Optional<JsonNode> decodeBoolean(JsonNode presentation);

    Optional<JsonNode> decodeArray(JsonNode presentation);

    Optional<JsonNode> decodeObject(JsonNode presentation);

    Optional<JsonNode> decode(JsonNode presentation);

}