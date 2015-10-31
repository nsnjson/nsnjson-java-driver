package com.github.nsnjson.encoding;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;
import com.github.nsnjson.AbstractFormatTest;
import org.junit.*;

public class EncoderTest extends AbstractFormatTest {

    @Test
    public void processTestEmptyString() {
        TextNode value = getEmptyString();

        shouldEncodeWhenGivenString(value, getStringPresentation(value));
    }

    @Test
    public void processTestString() {
        TextNode value = getString();

        shouldEncodeWhenGivenString(value, getStringPresentation(value));
    }

    @Test
    public void processTestBooleanTrue() {
        BooleanNode value = getBooleanTrue();

        shouldEncodeWhenGivenBoolean(value, getBooleanPresentation(value));
    }

    @Test
    public void processTestBooleanFalse() {
        BooleanNode value = getBooleanFalse();

        shouldEncodeWhenGivenBoolean(value, getBooleanPresentation(value));
    }

    @Test
    public void processTestEmptyArray() {
        ArrayNode array = getEmptyArray();

        shouldEncodeWhenGivenArray(array, getArrayPresentation(array));
    }

    @Test
    public void processTestArray() {
        ArrayNode array = getArray();

        shouldEncodeWhenGivenArray(array, getArrayPresentation(array));
    }

    @Test
    public void processTestEmptyObject() {
        ObjectNode object = getEmptyObject();

        shouldEncodeWhenGivenObject(object, getObjectPresentation(object));
    }

    @Test
    public void processTestObject() {
        ObjectNode object = getObject();

        shouldEncodeWhenGivenObject(object, getObjectPresentation(object));
    }

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
        Assert.assertEquals(presentation, Encoder.encode(value));
    }

}