package com.github.nsnjson.decoding;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;
import com.github.nsnjson.AbstractFormatTest;
import org.junit.*;

public class DecoderTest extends AbstractFormatTest {

    @Test
    public void processTestArray() {
        ArrayNode array = getArray();

        shouldDecodeWhenGivenArray(array, getArrayPresentation(array));
    }

    @Test
    public void processTestEmptyObject() {
        ObjectNode object = getEmptyObject();

        shouldDecodeWhenGivenObject(object, getObjectPresentation(object));
    }

    @Test
    public void processTestObject() {
        ObjectNode object = getObject();

        shouldDecodeWhenGivenObject(object, getObjectPresentation(object));
    }

    @Override
    protected void processTestNull(NullNode value, ObjectNode presentation) {
        shouldDecodeWhenGivenNull(value, presentation);
    }

    @Override
    protected void processTestNumberInt(NumericNode value, ObjectNode presentation) {
        shouldDecodeWhenGivenNumber(value, presentation);
    }

    @Override
    protected void processTestNumberLong(NumericNode value, ObjectNode presentation) {
        shouldDecodeWhenGivenNumber(value, presentation);
    }

    @Override
    protected void processTestNumberDouble(NumericNode value, ObjectNode presentation) {
        shouldDecodeWhenGivenNumber(value, presentation);
    }

    @Override
    protected void processTestString(TextNode value, ObjectNode presentation) {
        shouldDecodeWhenGivenString(value, presentation);
    }

    @Override
    protected void processTestBoolean(BooleanNode value, ObjectNode presentation) {
        shouldDecodeWhenGivenBoolean(value, presentation);
    }

    @Override
    protected void processTestArray(ArrayNode array, ObjectNode presentation) {
        shouldDecodeWhenGivenArray(array, presentation);
    }

    private static void shouldDecodeWhenGivenNull(NullNode value, ObjectNode presentation) {
        assertDecoding(value, presentation);
    }

    private static void shouldDecodeWhenGivenNumber(NumericNode value, ObjectNode presentation) {
        assertDecoding(value, presentation);
    }

    private static void shouldDecodeWhenGivenString(TextNode value, ObjectNode presentation) {
        assertDecoding(value, presentation);
    }

    private static void shouldDecodeWhenGivenBoolean(BooleanNode value, ObjectNode presentation) {
        assertDecoding(value, presentation);
    }

    private static void shouldDecodeWhenGivenArray(ArrayNode array, ObjectNode presentation) {
        assertDecoding(array, presentation);
    }

    private static void shouldDecodeWhenGivenObject(ObjectNode object, ObjectNode presentation) {
        assertDecoding(object, presentation);
    }

    private static void assertDecoding(JsonNode value, ObjectNode presentation) {
        Assert.assertEquals(value, Decoder.decode(presentation));
    }

}