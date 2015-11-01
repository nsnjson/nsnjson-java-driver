package com.github.nsnjson.encoding;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;
import com.github.nsnjson.AbstractCustomFormatTest;
import org.junit.Assert;

public class CustomEncoderTest extends AbstractCustomFormatTest {

    @Override
    protected void processTestNull(NullNode value, JsonNode presentation) {
        assertCustomEncoding(value, presentation);
    }

    @Override
    protected void processTestNumber(NumericNode value, JsonNode presentation) {
        assertCustomEncoding(value, presentation);
    }

    @Override
    protected void processTestString(TextNode value, JsonNode presentation) {
        assertCustomEncoding(value, presentation);
    }

    @Override
    protected void processTestBoolean(BooleanNode value, JsonNode presentation) {
        assertCustomEncoding(value, presentation);
    }

    @Override
    protected void processTestArray(ArrayNode array, JsonNode presentation) {
        assertCustomEncoding(array, presentation);
    }

    @Override
    protected void processTestObject(ObjectNode object, JsonNode presentation) {
        assertCustomEncoding(object, presentation);
    }

    private static void assertCustomEncoding(JsonNode json, JsonNode presentation) {
        Assert.assertEquals(presentation, assertAndGetPresentation(Encoder.encode(json, new CustomEncoding())));
    }

}