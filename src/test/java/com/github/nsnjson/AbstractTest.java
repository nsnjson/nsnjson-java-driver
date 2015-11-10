package com.github.nsnjson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;
import com.github.nsnjson.asset.data.AssetsProvider;
import org.junit.*;

import java.util.Optional;

public abstract class AbstractTest {

    @Test
    public void testNull() {
        processTest(getNull(), getNullPresentation());
    }

    @Test
    public void testNumberInt() {
        processTest(getNumberInt(), getIntPresentation());
    }

    @Test
    public void testNumberLong() {
        processTest(getNumberLong(), getLongPresentation());
    }

    @Test
    public void testNumberDouble() {
        processTest(getNumberDouble(), getDoublePresentation());
    }

    @Test
    public void testEmptyString() {
        processTest(getEmptyString(), getEmptyStringPresentation());
    }

    @Test
    public void testString() {
        processTest(getString(), getStringPresentation());
    }

    @Test
    public void testBooleanTrue() {
        processTest(getBooleanTrue(), getTruePresentation());
    }

    @Test
    public void testBooleanFalse() {
        processTest(getBooleanFalse(), getFalsePresentation());
    }

    @Test
    public void testEmptyArray() {
        processTest(getEmptyArray(), getEmptyArrayPresentation());
    }

    @Test
    public void testArray() {
        processTest(getArray(), getArrayPresentation());
    }

    @Test
    public void testEmptyObject() {
        processTest(getEmptyObject(), getEmptyObjectPresentation());
    }

    @Test
    public void testObject() {
        processTest(getObject(), getObjectPresentation());
    }

    protected abstract void processTest(JsonNode data, JsonNode presentation);

    protected abstract JsonNode getNullPresentation();

    protected abstract JsonNode getIntPresentation();

    protected abstract JsonNode getLongPresentation();

    protected abstract JsonNode getDoublePresentation();

    protected abstract JsonNode getEmptyStringPresentation();

    protected abstract JsonNode getStringPresentation();

    protected abstract JsonNode getTruePresentation();

    protected abstract JsonNode getFalsePresentation();

    protected abstract JsonNode getEmptyArrayPresentation();

    protected abstract JsonNode getArrayPresentation();

    protected abstract JsonNode getEmptyObjectPresentation();

    protected abstract JsonNode getObjectPresentation();

    protected static JsonNode assertAndGetData(Optional<JsonNode> valueOption) {
        Assert.assertTrue(valueOption.isPresent());

        return valueOption.get();
    }

    protected static JsonNode assertAndGetPresentation(Optional<JsonNode> presentationOption) {
        Assert.assertTrue(presentationOption.isPresent());

        return presentationOption.get();
    }

    private static NullNode getNull() {
        return AssetsProvider.getNull();
    }

    private static NumericNode getNumberInt() {
        return AssetsProvider.getInt();
    }

    private static NumericNode getNumberLong() {
        return AssetsProvider.getLong();
    }

    private static NumericNode getNumberDouble() {
        return AssetsProvider.getDouble();
    }

    private static TextNode getEmptyString() {
        return AssetsProvider.getEmptyString();
    }

    private static TextNode getString() {
        return AssetsProvider.getString();
    }

    private static BooleanNode getBooleanTrue() {
        return AssetsProvider.getTrue();
    }

    private static BooleanNode getBooleanFalse() {
        return AssetsProvider.getFalse();
    }

    private static ArrayNode getEmptyArray() {
        return AssetsProvider.getEmptyArray();
    }

    private static ArrayNode getArray() {
        return AssetsProvider.getArray();
    }

    private static ObjectNode getEmptyObject() {
        return AssetsProvider.getEmptyObject();
    }

    private static ObjectNode getObject() {
        return AssetsProvider.getObject();
    }

}