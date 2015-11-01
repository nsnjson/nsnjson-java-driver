package com.github.nsnjson.decoding;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;
import com.github.nsnjson.AbstractFormatTest;
import org.junit.Assert;

public class DecoderTest extends AbstractFormatTest {

    @Override
    protected void processTestNull(NullNode value, JsonNode presentation) {
        shouldDecodeWhenGivenNull(value, presentation);
    }

    @Override
    protected void processTestNumber(NumericNode value, JsonNode presentation) {
        shouldDecodeWhenGivenNumber(value, presentation);
    }

    @Override
    protected void processTestString(TextNode value, JsonNode presentation) {
        shouldDecodeWhenGivenString(value, presentation);
    }

    @Override
    protected void processTestBoolean(BooleanNode value, JsonNode presentation) {
        shouldDecodeWhenGivenBoolean(value, presentation);
    }

    @Override
    protected void processTestArray(ArrayNode array, JsonNode presentation) {
        shouldDecodeWhenGivenArray(array, presentation);
    }

    @Override
    protected void processTestObject(ObjectNode object, JsonNode presentation) {
        shouldDecodeWhenGivenObject(object, presentation);
    }

    private static void shouldDecodeWhenGivenNull(NullNode value, JsonNode presentation) {
        assertDecoding(value, presentation);
    }

    private static void shouldDecodeWhenGivenNumber(NumericNode value, JsonNode presentation) {
        assertDecoding(value, presentation);
    }

    private static void shouldDecodeWhenGivenString(TextNode value, JsonNode presentation) {
        assertDecoding(value, presentation);
    }

    private static void shouldDecodeWhenGivenBoolean(BooleanNode value, JsonNode presentation) {
        assertDecoding(value, presentation);
    }

    private static void shouldDecodeWhenGivenArray(ArrayNode array, JsonNode presentation) {
        assertDecoding(array, presentation);
    }

    private static void shouldDecodeWhenGivenObject(ObjectNode object, JsonNode presentation) {
        assertDecoding(object, presentation);
    }

    private static void assertDecoding(JsonNode json, JsonNode presentation) {
        Assert.assertEquals(json, assertAndGetValue(Decoder.decode(presentation)));
    }

}