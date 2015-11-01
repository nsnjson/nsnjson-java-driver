package com.github.nsnjson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import org.junit.*;

import java.util.Optional;

import static com.github.nsnjson.format.Format.*;

public abstract class AbstractFormatTest extends AbstractTest {

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

    protected abstract void processTestNull(NullNode value, JsonNode presentation);

    protected abstract void processTestNumberInt(NumericNode value, JsonNode presentation);

    protected abstract void processTestNumberLong(NumericNode value, JsonNode presentation);

    protected abstract void processTestNumberDouble(NumericNode value, JsonNode presentation);

    protected abstract void processTestString(TextNode value, JsonNode presentation);

    protected abstract void processTestBoolean(BooleanNode value, JsonNode presentation);

    protected abstract void processTestArray(ArrayNode array, JsonNode presentation);

    protected abstract void processTestObject(ObjectNode object, JsonNode presentation);

    private static JsonNode getNullPresentation() {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NULL);

        return presentation;
    }

    private static JsonNode getNumberIntPresentation(NumericNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asInt());

        return presentation;
    }

    private static JsonNode getNumberLongPresentation(NumericNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asLong());

        return presentation;
    }

    private static JsonNode getNumberDoublePresentation(NumericNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asDouble());

        return presentation;
    }

    private static JsonNode getStringPresentation(TextNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_STRING);
        presentation.put(FIELD_VALUE, value.asText());

        return presentation;
    }

    private static JsonNode getBooleanPresentation(BooleanNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_BOOLEAN);
        presentation.put(FIELD_VALUE, value.asBoolean() ? BOOLEAN_TRUE : BOOLEAN_FALSE);

        return presentation;
    }

    private static JsonNode getArrayPresentation(ArrayNode array) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentationOfArrayItems = objectMapper.createArrayNode();

        for (JsonNode value : array) {
            Optional<JsonNode> itemPresentationOption = Optional.empty();

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

    private static JsonNode getObjectPresentation(ObjectNode object) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentationOfObjectFields = objectMapper.createArrayNode();

        object.fieldNames().forEachRemaining(name -> {
            JsonNode value = object.get(name);

            Optional<JsonNode> fieldPresentationOption = Optional.empty();

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
        });

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_OBJECT);
        presentation.set(FIELD_VALUE, presentationOfObjectFields);

        return presentation;
    }

    private static JsonNode getFieldPresentation(String name, JsonNode valuePresentation) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_NAME, name);

        valuePresentation.fieldNames().forEachRemaining((valueProperty) -> {
            presentation.set(valueProperty, valuePresentation.get(valueProperty));
        });

        return presentation;
    }

}