package com.github.nsnjson.encoding;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;

import java.util.Optional;

/**
 * Base interface of encoding process.
 */
public interface Encoding {

    /**
     * Encodes JSON null value to NSNJSON presentation.
     * @return NSNJSON presentation of JSON null value
     */
    Optional<JsonNode> encodeNull();

    /**
     * Encodes JSON number value to NSNJSON presentation.
     * @param value JSON number
     * @return NSNJSON presentation of JSON number value
     */
    Optional<JsonNode> encodeNumber(NumericNode value);

    /**
     * Encodes JSON string value to NSNJSON presentation.
     * @param value JSON string
     * @return NSNJSON presentation of JSON string value
     */
    Optional<JsonNode> encodeString(TextNode value);

    /**
     * Encodes JSON boolean value to NSNJSON presentation.
     * @param value JSON boolean
     * @return NSNJSON presentation of JSON boolean value
     */
    Optional<JsonNode> encodeBoolean(BooleanNode value);

    /**
     * Encodes JSON array to NSNJSON presentation.
     * @param array JSON array
     * @return NSNJSON presentation of JSON array
     */
    Optional<JsonNode> encodeArray(ArrayNode array);

    /**
     * Encodes JSON object to NSNJSON presentation.
     * @param object JSON object
     * @return NSNJSON presentation of JSON object
     */
    Optional<JsonNode> encodeObject(ObjectNode object);

    /**
     * Encodes JSON to NSNJSON presentation.
     * @param data any JSON data
     * @return NSNJSON presentation of JSON
     */
    Optional<JsonNode> encode(JsonNode data);

}