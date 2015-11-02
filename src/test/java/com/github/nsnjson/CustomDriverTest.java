package com.github.nsnjson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;
import com.github.nsnjson.decoding.CustomDecoding;
import com.github.nsnjson.encoding.CustomEncoding;
import org.junit.Assert;

import java.util.Optional;

public class CustomDriverTest extends AbstractCustomFormatTest {

    @Override
    protected void processTestNull(NullNode value, JsonNode presentation) {
        shouldBeConsistencyWhenGivenNull(value, presentation);
    }

    @Override
    protected void processTestNumber(NumericNode value, JsonNode presentation) {
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

    private static void assertConsistency(JsonNode data, JsonNode presentation) {
        assertConsistencyByCustomEncoding(data);

        assertConsistencyByCustomDecoding(presentation);
    }

    private static void assertConsistencyByCustomEncoding(JsonNode data) {
        JsonNode presentation = assertAndGetPresentation(encode(data));

        Assert.assertEquals(data, assertAndGetValue(decode(presentation)));
    }

    private static void assertConsistencyByCustomDecoding(JsonNode presentation) {
        JsonNode data = assertAndGetValue(decode(presentation));

        Assert.assertEquals(presentation, assertAndGetPresentation(encode(data)));
    }

    private static Optional<JsonNode> encode(JsonNode data) {
        return Driver.encode(data, new CustomEncoding());
    }

    private static Optional<JsonNode> decode(JsonNode presentation) {
        return Driver.decode(presentation, new CustomDecoding());
    }

}