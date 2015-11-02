package com.github.nsnjson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import org.junit.*;

import java.util.*;

public abstract class AbstractTest {

    @Test
    public void testNull() {
        processTestNull(getNull(), getNullPresentation());
    }

    @Test
    public void testNumberInt() {
        NumericNode value = getNumberInt();

        processTestNumber(value, getNumberPresentation(value));
    }

    @Test
    public void testNumberLong() {
        NumericNode value = getNumberLong();

        processTestNumber(value, getNumberPresentation(value));
    }

    @Test
    public void testNumberDouble() {
        NumericNode value = getNumberDouble();

        processTestNumber(value, getNumberPresentation(value));
    }

    @Test
    public void testEmptyString() {
        TextNode value = getEmptyString();

        processTestString(value, getStringPresentation(value));
    }

    @Test
    public void testString() {
        TextNode value = getString();

        processTestString(value, getStringPresentation(value));
    }

    @Test
    public void testBooleanTrue() {
        BooleanNode value = getBooleanTrue();

        processTestBoolean(value, getBooleanPresentation(value));
    }

    @Test
    public void testBooleanFalse() {
        BooleanNode value = getBooleanFalse();

        processTestBoolean(value, getBooleanPresentation(value));
    }

    @Test
    public void testEmptyArray() {
        ArrayNode array = getEmptyArray();

        processTestArray(array, getArrayPresentation(array));
    }

    @Test
    public void testArray() {
        ArrayNode array = getArray();

        processTestArray(array, getArrayPresentation(array));
    }

    @Test
    public void testEmptyObject() {
        ObjectNode object = getEmptyObject();

        processTestObject(object, getObjectPresentation(object));
    }

    @Test
    public void testObject() {
        ObjectNode object = getObject();

        processTestObject(object, getObjectPresentation(object));
    }

    protected abstract void processTestNull(NullNode value, JsonNode presentation);

    protected abstract void processTestNumber(NumericNode value, JsonNode presentation);

    protected abstract void processTestString(TextNode value, JsonNode presentation);

    protected abstract void processTestBoolean(BooleanNode value, JsonNode presentation);

    protected abstract void processTestArray(ArrayNode array, JsonNode presentation);

    protected abstract void processTestObject(ObjectNode object, JsonNode presentation);

    protected Optional<JsonNode> getPresentation(JsonNode data) {
        if (data instanceof NullNode) {
            return Optional.of(getNullPresentation());
        }

        if (data instanceof NumericNode) {
            NumericNode value = (NumericNode) data;

            return Optional.of(getNumberPresentation(value));
        }

        if (data instanceof TextNode) {
            TextNode value = (TextNode) data;

            return Optional.of(getStringPresentation(value));
        }

        if (data instanceof BooleanNode) {
            BooleanNode value = (BooleanNode) data;

            return Optional.of(getBooleanPresentation(value));
        }

        if (data instanceof ArrayNode) {
            ArrayNode array = (ArrayNode) data;

            return Optional.of(getArrayPresentation(array));
        }

        if (data instanceof ObjectNode) {
            ObjectNode object = (ObjectNode) data;

            return Optional.of(getObjectPresentation(object));
        }

        return Optional.empty();
    }

    protected abstract JsonNode getNullPresentation();

    protected abstract JsonNode getNumberPresentation(NumericNode value);

    protected abstract JsonNode getStringPresentation(TextNode value);

    protected abstract JsonNode getBooleanPresentation(BooleanNode value);

    protected abstract JsonNode getArrayPresentation(ArrayNode array);

    protected abstract JsonNode getObjectPresentation(ObjectNode object);

    protected static JsonNode assertAndGetValue(Optional<JsonNode> valueOption) {
        Assert.assertTrue(valueOption.isPresent());

        return valueOption.get();
    }

    protected static JsonNode assertAndGetPresentation(Optional<JsonNode> presentationOption) {
        Assert.assertTrue(presentationOption.isPresent());

        return presentationOption.get();
    }

    private static NullNode getNull() {
        return NullNode.getInstance();
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

    private static BooleanNode getBooleanTrue() {
        return BooleanNode.getTrue();
    }

    private static BooleanNode getBooleanFalse() {
        return BooleanNode.getFalse();
    }

    private static ArrayNode getEmptyArray() {
        return new ObjectMapper().createArrayNode();
    }

    private static ArrayNode getArray() {
        ArrayNode array = getEmptyArray();
        array.add(getNull());
        array.add(getBooleanTrue());
        array.add(getBooleanFalse());
        array.add(getNumberInt());
        array.add(getNumberLong());
        array.add(getNumberDouble());
        array.add(getString());

        return array;
    }

    private static ObjectNode getEmptyObject() {
        return new ObjectMapper().createObjectNode();
    }

    private static ObjectNode getObject() {
        ObjectNode object = new ObjectMapper().createObjectNode();
        object.set("null_field", getNull());
        object.set("int_field", getNumberInt());
        object.set("long_field", getNumberLong());
        object.set("double_field", getNumberDouble());
        object.set("true_field", getBooleanTrue());
        object.set("false_field", getBooleanFalse());
        object.set("string_field", getString());

        return object;
    }

}