package com.github.nsnjson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import org.junit.Assert;

import java.util.*;

public abstract class AbstractTest {

    protected Optional<JsonNode> getPresentation(JsonNode json) {
        if (json instanceof NullNode) {
            return Optional.of(getNullPresentation());
        }

        if (json instanceof NumericNode) {
            NumericNode value = (NumericNode) json;

            if (value.isInt()) {
                return Optional.of(getNumberIntPresentation(value));
            }

            if (value.isLong()) {
                return Optional.of(getNumberLongPresentation(value));
            }

            if (value.isDouble()) {
                return Optional.of(getNumberDoublePresentation(value));
            }
        }

        if (json instanceof TextNode) {
            TextNode value = (TextNode) json;

            return Optional.of(getStringPresentation(value));
        }

        if (json instanceof BooleanNode) {
            BooleanNode value = (BooleanNode) json;

            return Optional.of(getBooleanPresentation(value));
        }

        if (json instanceof ArrayNode) {
            ArrayNode array = (ArrayNode) json;

            return Optional.of(getArrayPresentation(array));
        }

        if (json instanceof ObjectNode) {
            ObjectNode object = (ObjectNode) json;

            return Optional.of(getObjectPresentation(object));
        }

        return Optional.empty();
    }

    protected abstract JsonNode getNullPresentation();

    protected abstract JsonNode getNumberIntPresentation(NumericNode value);

    protected abstract JsonNode getNumberLongPresentation(NumericNode value);

    protected abstract JsonNode getNumberDoublePresentation(NumericNode value);

    protected abstract JsonNode getStringPresentation(TextNode value);

    protected abstract JsonNode getBooleanPresentation(BooleanNode value);

    protected abstract JsonNode getArrayPresentation(ArrayNode array);

    protected abstract JsonNode getObjectPresentation(ObjectNode object);

    protected static NullNode getNull() {
        return NullNode.getInstance();
    }

    protected static NumericNode getNumberInt() {
        return new IntNode(new Random().nextInt());
    }

    protected static NumericNode getNumberLong() {
        return new LongNode(new Random().nextLong());
    }

    protected static NumericNode getNumberDouble() {
        return new DoubleNode(new Random().nextDouble());
    }

    protected static TextNode getEmptyString() {
        return new TextNode("");
    }

    protected static TextNode getString() {
        return new TextNode(UUID.randomUUID().toString().replaceAll("-", ""));
    }

    protected static BooleanNode getBooleanTrue() {
        return BooleanNode.getTrue();
    }

    protected static BooleanNode getBooleanFalse() {
        return BooleanNode.getFalse();
    }

    protected static ArrayNode getEmptyArray() {
        return new ObjectMapper().createArrayNode();
    }

    protected static ArrayNode getArray() {
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

    protected static ObjectNode getEmptyObject() {
        return new ObjectMapper().createObjectNode();
    }

    protected static ObjectNode getObject() {
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

    protected static JsonNode assertAndGetValue(Optional<JsonNode> valueOption) {
        Assert.assertTrue(valueOption.isPresent());

        return valueOption.get();
    }

    protected static JsonNode assertAndGetPresentation(Optional<JsonNode> presentationOption) {
        Assert.assertTrue(presentationOption.isPresent());

        return presentationOption.get();
    }

}