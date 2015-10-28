package com.github.nsnjson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
        assertConsistency(getArray());
    }

    @Test
    public void shouldBeConsistencyWhenGivenObjectIsEmpty() {
        assertConsistency(getEmptyObject());
    }

    @Test
    public void shouldBeConsistencyWhenGivenObject() {
        assertConsistency(getObject());
    }

    private static void assertConsistency(JsonNode value) {
        JsonNode restoredValue = Driver.decode(Driver.encode(value));

        assertEquals(value, restoredValue);
    }

    private static void assertEquals(JsonNode value1, JsonNode value2) {
        Assert.assertEquals(value1.toString(), value2.toString());
    }

}