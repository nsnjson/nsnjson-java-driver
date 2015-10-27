package com.github.nsnjson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import org.junit.*;

import java.util.*;

public class DriverTest {

    @Test
    public void shouldBeConsistencyWhenGivenNull() {
        assertConsistency(getNull());
    }

    @Test
    public void shouldBeConsistencyWhenGivenBooleanTrue() {
        assertConsistency(getBooleanTrue());
    }

    @Test
    public void shouldBeConsistencyWhenGivenBooleanFalse() {
        assertConsistency(getBooleanFalse());
    }

    @Test
    public void shouldBeConsistencyWhenGivenNumberInt() {
        assertConsistency(getNumberInt());
    }

    @Test
    public void shouldBeConsistencyWhenGivenNumberLong() {
        assertConsistency(getNumberLong());
    }

    @Test
    public void shouldBeConsistencyWhenGivenNumberDouble() {
        assertConsistency(getNumberDouble());
    }

    @Test
    public void shouldBeConsistencyWhenGivenStringWhichIsEmpty() {
        assertConsistency(getEmptyString());
    }

    @Test
    public void shouldBeConsistencyWhenGivenString() {
        assertConsistency(getString());
    }

    @Test
    public void shouldBeConsistencyWhenGivenArrayWhichIsEmpty() {
        ArrayNode array = new ObjectMapper().createArrayNode();

        assertConsistency(array);
    }

    @Test
    public void shouldBeConsistencyWhenGivenArray() {
        ArrayNode array = new ObjectMapper().createArrayNode();
        array.add(getNull());
        array.add(getBooleanTrue());
        array.add(getBooleanFalse());
        array.add(getNumberInt());
        array.add(getNumberLong());
        array.add(getNumberDouble());
        array.add(getString());

        assertConsistency(array);
    }

    @Test
    public void shouldBeConsistencyWhenGivenObjectWhichIsEmpty() {
        ObjectNode object = new ObjectMapper().createObjectNode();

        assertConsistency(object);
    }

    @Test
    public void shouldBeConsistencyWhenGivenObject() {
        ObjectNode object = new ObjectMapper().createObjectNode();
        object.set("null_field", getNull());
        object.set("true_field", getBooleanTrue());
        object.set("false_field", getBooleanFalse());
        object.set("int_field", getNumberInt());
        object.set("long_field", getNumberLong());
        object.set("double_field", getNumberDouble());
        object.set("string_field", getString());

        assertConsistency(object);
    }

    private static void assertConsistency(JsonNode value) {
        JsonNode restoredValue = Driver.decode(Driver.encode(value));

        assertEquals(value, restoredValue);
    }

    private static void assertEquals(JsonNode value1, JsonNode value2) {
        Assert.assertEquals(value1.toString(), value2.toString());
    }

    private static NullNode getNull() {
        return NullNode.getInstance();
    }

    private static BooleanNode getBooleanTrue() {
        return BooleanNode.getTrue();
    }

    private static BooleanNode getBooleanFalse() {
        return BooleanNode.getFalse();
    }

    private static NumericNode getNumberInt() {
        return new IntNode(new Random().nextInt());
    }

    private static NumericNode getNumberLong() {
        return new LongNode(new Random().nextLong());
    }

    private static NumericNode getNumberDouble() {
        return new DoubleNode(new Random().nextDouble());
    }

    private static TextNode getEmptyString() {
        return new TextNode("");
    }

    private static TextNode getString() {
        return new TextNode(UUID.randomUUID().toString().replaceAll("-", ""));
    }

}