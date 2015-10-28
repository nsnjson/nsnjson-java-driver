package com.github.nsnjson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import org.junit.*;

public class DriverTest extends AbstractFormatTest {

    @Test
    public void shouldBeConsistencyWhenGivenNull() {
        assertConsistency(getNull());
    }

    @Test
    public void shouldBeConsistencyWhenGivenNumberIsInt() {
        assertConsistency(getNumberInt());
    }

    @Test
    public void shouldBeConsistencyWhenGivenNumberIsLong() {
        assertConsistency(getNumberLong());
    }

    @Test
    public void shouldBeConsistencyWhenGivenNumberIsDouble() {
        assertConsistency(getNumberDouble());
    }

    @Test
    public void shouldBeConsistencyWhenGivenStringIsEmpty() {
        assertConsistency(getEmptyString());
    }

    @Test
    public void shouldBeConsistencyWhenGivenString() {
        assertConsistency(getString());
    }

    @Test
    public void shouldBeConsistencyWhenGivenBooleanIsTrue() {
        assertConsistency(getBooleanTrue());
    }

    @Test
    public void shouldBeConsistencyWhenGivenBooleanIsFalse() {
        assertConsistency(getBooleanFalse());
    }

    @Test
    public void shouldBeConsistencyWhenGivenArrayIsEmpty() {
        assertConsistency(getEmptyArray());
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
    public void shouldBeConsistencyWhenGivenObjectIsEmpty() {
        assertConsistency(getEmptyObject());
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

}