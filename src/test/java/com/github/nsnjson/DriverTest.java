package com.github.nsnjson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;
import org.junit.*;

public class DriverTest extends AbstractFormatTest {

    @Test
    public void processTestObject() {
        ObjectNode object = getObject();

        shouldBeConsistencyWhenGivenObject(object, getObjectPresentation(object));
    }

    @Override
    protected void processTestNull(NullNode value, ObjectNode presentation) {
        shouldBeConsistencyWhenGivenNull(value, presentation);
    }

    @Override
    protected void processTestNumberInt(NumericNode value, ObjectNode presentation) {
        shouldBeConsistencyWhenGivenNumber(value, presentation);
    }

    @Override
    protected void processTestNumberLong(NumericNode value, ObjectNode presentation) {
        shouldBeConsistencyWhenGivenNumber(value, presentation);
    }

    @Override
    protected void processTestNumberDouble(NumericNode value, ObjectNode presentation) {
        shouldBeConsistencyWhenGivenNumber(value, presentation);
    }

    @Override
    protected void processTestString(TextNode value, ObjectNode presentation) {
        shouldBeConsistencyWhenGivenString(value, presentation);
    }

    @Override
    protected void processTestBoolean(BooleanNode value, ObjectNode presentation) {
        shouldBeConsistencyWhenGivenBoolean(value, presentation);
    }

    @Override
    protected void processTestArray(ArrayNode array, ObjectNode presentation) {
        shouldBeConsistencyWhenGivenArray(array, presentation);
    }

    @Override
    protected void processTestObject(ObjectNode object, ObjectNode presentation) {
        shouldBeConsistencyWhenGivenObject(object, presentation);
    }

    private static void shouldBeConsistencyWhenGivenNull(NullNode value, ObjectNode presentation) {
        assertConsistency(value, presentation);
    }

    private static void shouldBeConsistencyWhenGivenNumber(NumericNode value, ObjectNode presentation) {
        assertConsistency(value, presentation);
    }

    private static void shouldBeConsistencyWhenGivenString(TextNode value, ObjectNode presentation) {
        assertConsistency(value, presentation);
    }

    private static void shouldBeConsistencyWhenGivenBoolean(BooleanNode value, ObjectNode presentation) {
        assertConsistency(value, presentation);
    }

    private static void shouldBeConsistencyWhenGivenArray(ArrayNode array, ObjectNode presentation) {
        assertConsistency(array, presentation);
    }

    private static void shouldBeConsistencyWhenGivenObject(ObjectNode object, ObjectNode presentation) {
        assertConsistency(object, presentation);
    }

    private static void assertConsistency(JsonNode value, ObjectNode presentation) {
        assertConsistencyByEncoding(value);

        assertConsistencyByDecoding(presentation);
    }

    private static void assertConsistencyByEncoding(JsonNode value) {
        Assert.assertEquals(value, Driver.decode(Driver.encode(value)));
    }

    private static void assertConsistencyByDecoding(ObjectNode presentation) {
        Assert.assertEquals(presentation, Driver.encode(Driver.decode(presentation)));
    }

}