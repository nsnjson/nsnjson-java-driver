package com.github.nsnjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;

import java.util.*;

public abstract class AbstractTest {

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

}