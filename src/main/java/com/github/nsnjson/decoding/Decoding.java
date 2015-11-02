package com.github.nsnjson.decoding;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import java.util.Optional;

/**
 * Base interface of decoding process.
 */
public interface Decoding {

    /**
     * Detects type of JSON which is presented in presentation.
     * @param presentation NSNJSON presentation
     * @return JSON type
     */
    Optional<JsonNodeType> getType(JsonNode presentation);

    /**
     * Decodes JSON null value.
     * @return JSON null value
     */
    Optional<JsonNode> decodeNull();

    /**
     * Decodes JSON number value from specified NSNJSON presentation.
     * @param presentation NSNJSON presentation of JSON number value
     * @return JSON number value
     */
    Optional<JsonNode> decodeNumber(JsonNode presentation);

    /**
     * Decodes JSON string value from specified NSNJSON presentation.
     * @param presentation NSNJSON presentation of JSON string value
     * @return JSON string value
     */
    Optional<JsonNode> decodeString(JsonNode presentation);

    /**
     * Decodes JSON boolean value from specified NSNJSON presentation.
     * @param presentation NSNJSON presentation of JSON boolean value
     * @return JSON boolean value
     */
    Optional<JsonNode> decodeBoolean(JsonNode presentation);

    /**
     * Decodes JSON array from specified NSNJSON presentation.
     * @param presentation NSNJSON presentation of JSON array
     * @return JSON array
     */
    Optional<JsonNode> decodeArray(JsonNode presentation);

    /**
     * Decodes JSON object from specified NSNJSON presentation.
     * @param presentation NSNJSON presentation of JSON object
     * @return JSON object
     */
    Optional<JsonNode> decodeObject(JsonNode presentation);

    /**
     * Decodes JSON from specified NSNJSON presentation.
     * @param presentation NSNJSON presentation of JSON
     * @return JSON
     */
    Optional<JsonNode> decode(JsonNode presentation);

}