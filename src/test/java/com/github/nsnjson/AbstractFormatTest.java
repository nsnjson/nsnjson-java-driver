package com.github.nsnjson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import org.junit.*;

import java.util.*;

import static com.github.nsnjson.format.Format.*;

public abstract class AbstractFormatTest {

    @Test
    public void testNull() {
        processTestNull(getNull(), getNullPresentation());
    }

    @Test
    public void testNumberInt() {
        NumericNode value = getNumberInt();

        processTestNumberInt(value, getNumberIntPresentation(value));
    }

    @Test
    public void testNumberLong() {
        NumericNode value = getNumberLong();

        processTestNumberLong(value, getNumberLongPresentation(value));
    }

    @Test
    public void testNumberDouble() {
        NumericNode value = getNumberDouble();

        processTestNumberDouble(value, getNumberDoublePresentation(value));
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

    protected abstract void processTestNull(NullNode value, ObjectNode presentation);

    protected abstract void processTestNumberInt(NumericNode value, ObjectNode presentation);

    protected abstract void processTestNumberLong(NumericNode value, ObjectNode presentation);

    protected abstract void processTestNumberDouble(NumericNode value, ObjectNode presentation);

    protected abstract void processTestString(TextNode value, ObjectNode presentation);

    protected abstract void processTestBoolean(BooleanNode value, ObjectNode presentation);

    protected abstract void processTestArray(ArrayNode array, ObjectNode presentation);

    protected abstract void processTestObject(ObjectNode object, ObjectNode presentation);

    protected static ObjectNode assertAndGetPresentation(Optional<ObjectNode> presentationOption) {
        Assert.assertTrue(presentationOption.isPresent());

        return presentationOption.get();
    }

    protected static JsonNode assertAndGetValue(Optional<JsonNode> valueOption) {
        Assert.assertTrue(valueOption.isPresent());

        return valueOption.get();
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

    private static ObjectNode getNullPresentation() {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NULL);

        return presentation;
    }

    private static ObjectNode getNumberIntPresentation(NumericNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asInt());

        return presentation;
    }

    private static ObjectNode getNumberLongPresentation(NumericNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asLong());

        return presentation;
    }

    private static ObjectNode getNumberDoublePresentation(NumericNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asDouble());

        return presentation;
    }

    private static ObjectNode getStringPresentation(TextNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_STRING);
        presentation.put(FIELD_VALUE, value.asText());

        return presentation;
    }

    private static ObjectNode getBooleanPresentation(BooleanNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_BOOLEAN);
        presentation.put(FIELD_VALUE, value.asBoolean() ? BOOLEAN_TRUE : BOOLEAN_FALSE);

        return presentation;
    }

    private static ObjectNode getArrayPresentation(ArrayNode array) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentationOfArrayItems = objectMapper.createArrayNode();

        for (int i = 0; i < array.size(); i++) {
            JsonNode value = array.get(i);

            Optional<ObjectNode> itemPresentationOption = Optional.empty();

            if (value instanceof NullNode) {
                itemPresentationOption = Optional.of(getNullPresentation());
            }
            else if (value instanceof NumericNode) {
                NumericNode numericValue = (NumericNode) value;

                if (numericValue.isInt()) {
                    itemPresentationOption = Optional.of(getNumberIntPresentation(numericValue));
                }
                else if (numericValue.isLong()) {
                    itemPresentationOption = Optional.of(getNumberLongPresentation(numericValue));
                }
                else if (numericValue.isDouble()) {
                    itemPresentationOption = Optional.of(getNumberDoublePresentation(numericValue));
                }
            }
            else if (value instanceof TextNode) {
                TextNode stringValue = (TextNode) value;

                itemPresentationOption = Optional.of(getStringPresentation(stringValue));
            }
            else if (value instanceof BooleanNode) {
                BooleanNode booleanValue = (BooleanNode) value;

                itemPresentationOption = Optional.of(getBooleanPresentation(booleanValue));
            }

            if (itemPresentationOption.isPresent()) {
                presentationOfArrayItems.add(itemPresentationOption.get());
            }
        }

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_ARRAY);
        presentation.set(FIELD_VALUE, presentationOfArrayItems);

        return presentation;
    }

    private static ObjectNode getObjectPresentation(ObjectNode object) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentationOfObjectFields = objectMapper.createArrayNode();

        for (Iterator<String> namesIterator = object.fieldNames(); namesIterator.hasNext();) {
            String name = namesIterator.next();

            JsonNode value = object.get(name);

            Optional<ObjectNode> fieldPresentationOption = Optional.empty();

            if (value instanceof NullNode) {
                fieldPresentationOption = Optional.of(getFieldPresentation(name, getNullPresentation()));
            }
            else if (value instanceof NumericNode) {
                NumericNode numericValue = (NumericNode) value;

                if (numericValue.isInt()) {
                    fieldPresentationOption = Optional.of(getFieldPresentation(name, getNumberIntPresentation(numericValue)));
                }
                else if (numericValue.isLong()) {
                    fieldPresentationOption = Optional.of(getFieldPresentation(name, getNumberLongPresentation(numericValue)));
                }
                else if (numericValue.isDouble()) {
                    fieldPresentationOption = Optional.of(getFieldPresentation(name, getNumberDoublePresentation(numericValue)));
                }
            }
            else if (value instanceof TextNode) {
                TextNode stringValue = (TextNode) value;

                fieldPresentationOption = Optional.of(getFieldPresentation(name, getStringPresentation(stringValue)));
            }
            else if (value instanceof BooleanNode) {
                BooleanNode booleanValue = (BooleanNode) value;

                fieldPresentationOption = Optional.of(getFieldPresentation(name, getBooleanPresentation(booleanValue)));
            }

            if (fieldPresentationOption.isPresent()) {
                presentationOfObjectFields.add(fieldPresentationOption.get());
            }
        }

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_OBJECT);
        presentation.set(FIELD_VALUE, presentationOfObjectFields);

        return presentation;
    }

    private static ObjectNode getFieldPresentation(String name, ObjectNode valuePresentation) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_NAME, name);

        if (valuePresentation != null) {
            presentation.set(FIELD_TYPE, valuePresentation.get(FIELD_TYPE));

            if (valuePresentation.has(FIELD_VALUE)) {
                presentation.set(FIELD_VALUE, valuePresentation.get(FIELD_VALUE));
            }
        }

        return presentation;
    }

}