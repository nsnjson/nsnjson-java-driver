package com.github.nsnjson.encoding;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;
import com.github.nsnjson.AbstractFormatTest;
import org.junit.Assert;

public class EncoderTest extends AbstractFormatTest {

    @Override
    protected void processTestNull(NullNode value, ObjectNode presentation) {
        shouldEncodeWhenGivenNull(value, presentation);
    }

    @Override
    protected void processTestNumberInt(NumericNode value, ObjectNode presentation) {
        shouldEncodeWhenGivenNumber(value, presentation);
    }

    @Override
    protected void processTestNumberLong(NumericNode value, ObjectNode presentation) {
        shouldEncodeWhenGivenNumber(value, presentation);
    }

    @Override
    protected void processTestNumberDouble(NumericNode value, ObjectNode presentation) {
        shouldEncodeWhenGivenNumber(value, presentation);
    }

    @Override
    protected void processTestString(TextNode value, ObjectNode presentation) {
        shouldEncodeWhenGivenString(value, presentation);
    }

    @Override
    protected void processTestBoolean(BooleanNode value, ObjectNode presentation) {
        shouldEncodeWhenGivenBoolean(value, presentation);
    }

    @Override
    protected void processTestArray(ArrayNode array, ObjectNode presentation) {
        shouldEncodeWhenGivenArray(array, presentation);
    }

    @Override
    protected void processTestObject(ObjectNode object, ObjectNode presentation) {
        shouldEncodeWhenGivenObject(object, presentation);
    }

    private static void shouldEncodeWhenGivenNull(NullNode value, ObjectNode presentation) {
        assertEncoding(value, presentation);
    }

    private static void shouldEncodeWhenGivenNumber(NumericNode value, ObjectNode presentation) {
        assertEncoding(value, presentation);
    }

    private static void shouldEncodeWhenGivenString(TextNode value, ObjectNode presentation) {
        assertEncoding(value, presentation);
    }

    private static void shouldEncodeWhenGivenBoolean(BooleanNode value, ObjectNode presentation) {
        assertEncoding(value, presentation);
    }

    private static void shouldEncodeWhenGivenArray(ArrayNode array, ObjectNode presentation) {
        assertEncoding(array, presentation);
    }

    private static void shouldEncodeWhenGivenObject(ObjectNode object, ObjectNode presentation) {
        assertEncoding(object, presentation);
    }

    private static void assertEncoding(JsonNode value, ObjectNode presentation) {
        Assert.assertEquals(presentation, assertAndGetPresentation(Encoder.encode(value)));
    }

}