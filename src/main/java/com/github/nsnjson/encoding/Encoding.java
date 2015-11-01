package com.github.nsnjson.encoding;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;

import java.util.Optional;

public interface Encoding {

    Optional<JsonNode> encodeNull();

    Optional<JsonNode> encodeNumber(NumericNode value);

    Optional<JsonNode> encodeString(TextNode value);

    Optional<JsonNode> encodeBoolean(BooleanNode value);

    Optional<JsonNode> encodeArray(ArrayNode array);

    Optional<JsonNode> encodeObject(ObjectNode object);

    Optional<JsonNode> encode(JsonNode json);

}