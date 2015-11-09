package com.github.nsnjson.encoding;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;
import com.github.nsnjson.AbstractTest;
import org.junit.Assert;

import java.util.Optional;
import java.util.function.Function;

public abstract class AbstractEncodingTest extends AbstractTest {

    @Override
    protected void processTestNull(NullNode value, JsonNode presentation) {
        assertEncoding(value, presentation);
    }

    @Override
    protected void processTestNumber(NumericNode value, JsonNode presentation) {
        assertEncoding(value, presentation);
    }

    @Override
    protected void processTestString(TextNode value, JsonNode presentation) {
        assertEncoding(value, presentation);
    }

    @Override
    protected void processTestBoolean(BooleanNode value, JsonNode presentation) {
        assertEncoding(value, presentation);
    }

    @Override
    protected void processTestArray(ArrayNode array, JsonNode presentation) {
        assertEncoding(array, presentation);
    }

    @Override
    protected void processTestObject(ObjectNode object, JsonNode presentation) {
        assertEncoding(object, presentation);
    }

    protected void assertEncoding(JsonNode data, JsonNode presentation) {
        Assert.assertEquals(presentation, assertAndGetPresentation(getEncodeFunction().apply(data)));
    }

    protected abstract Function<JsonNode, Optional<JsonNode>> getEncodeFunction();

}