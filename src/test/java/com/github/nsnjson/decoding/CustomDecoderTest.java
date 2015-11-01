package com.github.nsnjson.decoding;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;
import com.github.nsnjson.AbstractCustomFormatTest;
import org.junit.Assert;

public class CustomDecoderTest extends AbstractCustomFormatTest {

    @Override
    protected void processTestNull(NullNode value, JsonNode presentation) {
        assertCustomDecoding(value, presentation);
    }

    @Override
    protected void processTestNumber(NumericNode value, JsonNode presentation) {
        assertCustomDecoding(value, presentation);
    }

    @Override
    protected void processTestString(TextNode value, JsonNode presentation) {
        assertCustomDecoding(value, presentation);
    }

    @Override
    protected void processTestBoolean(BooleanNode value, JsonNode presentation) {
        assertCustomDecoding(value, presentation);
    }

    @Override
    protected void processTestArray(ArrayNode array, JsonNode presentation) {
        assertCustomDecoding(array, presentation);
    }

    @Override
    protected void processTestObject(ObjectNode object, JsonNode presentation) {
        assertCustomDecoding(object, presentation);
    }

    private static void assertCustomDecoding(JsonNode json, JsonNode presentation) {
        Assert.assertEquals(json, assertAndGetPresentation(Decoder.decode(presentation, new CustomDecoding())));
    }

}