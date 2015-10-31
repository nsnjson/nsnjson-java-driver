package com.github.nsnjson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;
import org.junit.Assert;

public class DriverTest extends AbstractFormatTest {

    @Override
    protected void processTestNull(NullNode value, JsonNode presentation) {
        shouldBeConsistencyWhenGivenNull(value, presentation);
    }

    @Override
    protected void processTestNumberInt(NumericNode value, JsonNode presentation) {
        shouldBeConsistencyWhenGivenNumber(value, presentation);
    }

    @Override
    protected void processTestNumberLong(NumericNode value, JsonNode presentation) {
        shouldBeConsistencyWhenGivenNumber(value, presentation);
    }

    @Override
    protected void processTestNumberDouble(NumericNode value, JsonNode presentation) {
        shouldBeConsistencyWhenGivenNumber(value, presentation);
    }

    @Override
    protected void processTestString(TextNode value, JsonNode presentation) {
        shouldBeConsistencyWhenGivenString(value, presentation);
    }

    @Override
    protected void processTestBoolean(BooleanNode value, JsonNode presentation) {
        shouldBeConsistencyWhenGivenBoolean(value, presentation);
    }

    @Override
    protected void processTestArray(ArrayNode array, JsonNode presentation) {
        shouldBeConsistencyWhenGivenArray(array, presentation);
    }

    @Override
    protected void processTestObject(ObjectNode object, JsonNode presentation) {
        shouldBeConsistencyWhenGivenObject(object, presentation);
    }

    private static void shouldBeConsistencyWhenGivenNull(NullNode value, JsonNode presentation) {
        assertConsistency(value, presentation);
    }

    private static void shouldBeConsistencyWhenGivenNumber(NumericNode value, JsonNode presentation) {
        assertConsistency(value, presentation);
    }

    private static void shouldBeConsistencyWhenGivenString(TextNode value, JsonNode presentation) {
        assertConsistency(value, presentation);
    }

    private static void shouldBeConsistencyWhenGivenBoolean(BooleanNode value, JsonNode presentation) {
        assertConsistency(value, presentation);
    }

    private static void shouldBeConsistencyWhenGivenArray(ArrayNode array, JsonNode presentation) {
        assertConsistency(array, presentation);
    }

    private static void shouldBeConsistencyWhenGivenObject(ObjectNode object, JsonNode presentation) {
        assertConsistency(object, presentation);
    }

    private static void assertConsistency(JsonNode value, JsonNode presentation) {
        assertConsistencyByEncoding(value);

        assertConsistencyByDecoding(presentation);
    }

    private static void assertConsistencyByEncoding(JsonNode value) {
        JsonNode presentation = assertAndGetPresentation(Driver.encode(value));

        Assert.assertEquals(value, assertAndGetValue(Driver.decode(presentation)));
    }

    private static void assertConsistencyByDecoding(JsonNode presentation) {
        JsonNode value = assertAndGetValue(Driver.decode(presentation));

        Assert.assertEquals(presentation, assertAndGetPresentation(Driver.encode(value)));
    }

}