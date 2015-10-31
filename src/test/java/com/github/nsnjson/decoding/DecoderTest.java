package com.github.nsnjson.decoding;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;
import com.github.nsnjson.AbstractFormatTest;
import org.junit.*;

public class DecoderTest extends AbstractFormatTest {

    @Test
    public void processTestNumberLong() {
        NumericNode value = getNumberLong();

        shouldDecodeWhenGivenNumber(value, getNumberLongPresentation(value));
    }

    @Test
    public void processTestNumberDouble() {
        NumericNode value = getNumberDouble();

        shouldDecodeWhenGivenNumber(value, getNumberDoublePresentation(value));
    }

    @Test
    public void processTestEmptyString() {
        TextNode value = getEmptyString();

        shouldDecodeWhenGivenString(value, getStringPresentation(value));
    }

    @Test
    public void processTestString() {
        TextNode value = getString();

        shouldDecodeWhenGivenString(value, getStringPresentation(value));
    }

    @Test
    public void processTestBooleanTrue() {
        BooleanNode value = getBooleanTrue();

        shouldDecodeWhenGivenBoolean(value, getBooleanPresentation(value));
    }

    @Test
    public void processTestBooleanFalse() {
        BooleanNode value = getBooleanFalse();

        shouldDecodeWhenGivenBoolean(value, getBooleanPresentation(value));
    }

    @Test
    public void processTestEmptyArray() {
        ArrayNode array = getEmptyArray();

        shouldDecodeWhenGivenArray(array, getArrayPresentation(array));
    }

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
        shouldDecodeWhenGivenNull(getNull(), getNullPresentation());
    }

    @Override
    protected void processTestNumberInt(NumericNode value, ObjectNode presentation) {
        shouldDecodeWhenGivenNumber(value, presentation);
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