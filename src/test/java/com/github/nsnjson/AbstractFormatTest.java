package com.github.nsnjson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

import java.util.*;

import static com.github.nsnjson.format.Format.*;

public class AbstractFormatTest {

    protected static NullNode getNull() {
        return NullNode.getInstance();
    }

    protected static BooleanNode getBooleanTrue() {
        return BooleanNode.getTrue();
    }

    protected static BooleanNode getBooleanFalse() {
        return BooleanNode.getFalse();
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

    protected static ObjectNode getNullPresentation() {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NULL);

        return presentation;
    }

    protected static ObjectNode getNumberIntPresentation(NumericNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asInt());

        return presentation;
    }

    protected static ObjectNode getNumberLongPresentation(NumericNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asLong());

        return presentation;
    }

    protected static ObjectNode getNumberDoublePresentation(NumericNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asDouble());

        return presentation;
    }

    protected static ObjectNode getStringPresentation(TextNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_STRING);
        presentation.put(FIELD_VALUE, value.asText());

        return presentation;
    }

    protected static ObjectNode getBooleanPresentation(BooleanNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_BOOLEAN);
        presentation.put(FIELD_VALUE, value.asBoolean() ? BOOLEAN_TRUE : BOOLEAN_FALSE);

        return presentation;
    }

    protected static ObjectNode getArrayPresentation(ArrayNode array) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentationOfArrayItems = objectMapper.createArrayNode();

        for (int i = 0; i < array.size(); i++) {
            JsonNode value = array.get(i);

            if (value instanceof NullNode) {
                presentationOfArrayItems.add(getNullPresentation());
            }
            else if (value instanceof NumericNode) {
                NumericNode numericValue = (NumericNode) value;

                if (numericValue.isInt()) {
                    presentationOfArrayItems.add(getNumberIntPresentation(numericValue));
                }
                else if (numericValue.isLong()) {
                    presentationOfArrayItems.add(getNumberLongPresentation(numericValue));
                }
                else if (numericValue.isDouble()) {
                    presentationOfArrayItems.add(getNumberDoublePresentation(numericValue));
                }
            }
            else if (value instanceof TextNode) {
                TextNode stringValue = (TextNode) value;

                presentationOfArrayItems.add(getStringPresentation(stringValue));
            }
            else if (value instanceof BooleanNode) {
                BooleanNode booleanValue = (BooleanNode) value;

                presentationOfArrayItems.add(getBooleanPresentation(booleanValue));
            }
        }

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_ARRAY);
        presentation.set(FIELD_VALUE, presentationOfArrayItems);

        return presentation;
    }

    protected static ObjectNode getEmptyObjectPresentation() {
        return getObjectPresentation(getEmptyObject());
    }

    protected static ObjectNode getObjectPresentation(ObjectNode object) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentationOfObjectFields = objectMapper.createArrayNode();

        for (Iterator<String> namesIterator = object.fieldNames(); namesIterator.hasNext();) {
            String name = namesIterator.next();

            JsonNode value = object.get(name);

            if (value instanceof NullNode) {
                presentationOfObjectFields.add(getFieldPresentation(name, getNullPresentation()));
            }
            else if (value instanceof NumericNode) {
                NumericNode numericValue = (NumericNode) value;

                if (numericValue.isInt()) {
                    presentationOfObjectFields.add(getFieldPresentation(name, getNumberIntPresentation(numericValue)));
                }
                else if (numericValue.isLong()) {
                    presentationOfObjectFields.add(getFieldPresentation(name, getNumberLongPresentation(numericValue)));
                }
                else if (numericValue.isDouble()) {
                    presentationOfObjectFields.add(getFieldPresentation(name, getNumberDoublePresentation(numericValue)));
                }
            }
            else if (value instanceof TextNode) {
                TextNode stringValue = (TextNode) value;

                presentationOfObjectFields.add(getFieldPresentation(name, getStringPresentation(stringValue)));
            }
            else if (value instanceof BooleanNode) {
                BooleanNode booleanValue = (BooleanNode) value;

                presentationOfObjectFields.add(getFieldPresentation(name, getBooleanPresentation(booleanValue)));
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