package com.github.nsnjson.encoding;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import com.github.nsnjson.AbstractFormatTest;
import org.junit.*;

import static com.github.nsnjson.format.Format.*;

public class EncoderTest extends AbstractFormatTest {

    @Test
    public void processTestNull() {
        shouldEncodeWhenGivenNull(getNull(), getNullPresentation());
    }

    @Test
    public void processTestNumberInt() {
        NumericNode value = getNumberInt();

        shouldEncodeWhenGivenNumber(value, getNumberIntPresentation(value));
    }

    @Test
    public void processTestNumberLong() {
        NumericNode value = getNumberLong();

        shouldEncodeWhenGivenNumber(value, getNumberLongPresentation(value));
    }

    @Test
    public void processTestNumberDouble() {
        NumericNode value = getNumberDouble();

        shouldEncodeWhenGivenNumber(value, getNumberDoublePresentation(value));
    }

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

    private void shouldEncodeWhenGivenNull(NullNode value, ObjectNode presentation) {
        assertEncoding(value, presentation);
    }

    private void shouldEncodeWhenGivenNumber(NumericNode value, ObjectNode presentation) {
        assertEncoding(value, presentation);
    }

    public void shouldEncodeWhenGivenString(TextNode value, ObjectNode presentation) {
        assertEncoding(value, presentation);
    }

    private void shouldEncodeWhenGivenBoolean(BooleanNode value, ObjectNode presentation) {
        assertEncoding(value, presentation);
    }

    private void shouldEncodeWhenGivenArray(ArrayNode array, ObjectNode presentation) {
        assertEncoding(array, presentation);
    }

    private void shouldEncodeWhenGivenObject(ObjectNode object, ObjectNode presentation) {
        assertEncoding(object, presentation);
    }

    private static void assertEncoding(JsonNode value, ObjectNode presentation) {
        Assert.assertEquals(presentation, Encoder.encode(value));
    }

}