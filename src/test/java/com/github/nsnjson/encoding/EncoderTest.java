package com.github.nsnjson.encoding;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;
import com.github.nsnjson.AbstractFormatTest;
import org.junit.Assert;

public class EncoderTest extends AbstractFormatTest {

    @Override
    protected void processTestNull(NullNode value, JsonNode presentation) {
        shouldEncodeWhenGivenNull(value, presentation);
    }

    @Override
    protected void processTestNumber(NumericNode value, JsonNode presentation) {
        shouldEncodeWhenGivenNumber(value, presentation);
    }

    @Override
    protected void processTestString(TextNode value, JsonNode presentation) {
        shouldEncodeWhenGivenString(value, presentation);
    }

    @Override
    protected void processTestBoolean(BooleanNode value, JsonNode presentation) {
        shouldEncodeWhenGivenBoolean(value, presentation);
    }

    @Override
    protected void processTestArray(ArrayNode array, JsonNode presentation) {
        shouldEncodeWhenGivenArray(array, presentation);
    }

    @Override
    protected void processTestObject(ObjectNode object, JsonNode presentation) {
        shouldEncodeWhenGivenObject(object, presentation);
    }

    private static void shouldEncodeWhenGivenNull(NullNode value, JsonNode presentation) {
        assertEncoding(value, presentation);
    }

    private static void shouldEncodeWhenGivenNumber(NumericNode value, JsonNode presentation) {
        assertEncoding(value, presentation);
    }

    private static void shouldEncodeWhenGivenString(TextNode value, JsonNode presentation) {
        assertEncoding(value, presentation);
    }

    private static void shouldEncodeWhenGivenBoolean(BooleanNode value, JsonNode presentation) {
        assertEncoding(value, presentation);
    }

    private static void shouldEncodeWhenGivenArray(ArrayNode array, JsonNode presentation) {
        assertEncoding(array, presentation);
    }

    private static void shouldEncodeWhenGivenObject(ObjectNode object, JsonNode presentation) {
        assertEncoding(object, presentation);
    }

    private static void assertEncoding(JsonNode data, JsonNode presentation) {
        Assert.assertEquals(presentation, assertAndGetPresentation(Encoder.encode(data)));
    }

}