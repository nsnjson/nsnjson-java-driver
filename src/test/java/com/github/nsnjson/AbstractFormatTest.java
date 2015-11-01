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

    @Override
    protected JsonNode getNullPresentation() {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NULL);

        return presentation;
    }

    @Override
    protected JsonNode getNumberIntPresentation(NumericNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asInt());

        return presentation;
    }

    @Override
    protected JsonNode getNumberLongPresentation(NumericNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asLong());

        return presentation;
    }

    @Override
    protected JsonNode getNumberDoublePresentation(NumericNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asDouble());

        return presentation;
    }

    @Override
    protected JsonNode getStringPresentation(TextNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_STRING);
        presentation.put(FIELD_VALUE, value.asText());

        return presentation;
    }

    @Override
    protected JsonNode getBooleanPresentation(BooleanNode value) {
        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_BOOLEAN);
        presentation.put(FIELD_VALUE, value.asBoolean() ? BOOLEAN_TRUE : BOOLEAN_FALSE);

        return presentation;
    }

    @Override
    protected JsonNode getArrayPresentation(ArrayNode array) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode itemsPresentation = objectMapper.createArrayNode();

        for (JsonNode value : array) {
            Optional<JsonNode> itemPresentationOption = getPresentation(value);

            if (itemPresentationOption.isPresent()) {
                itemsPresentation.add(itemPresentationOption.get());
            }
        }

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_ARRAY);
        presentation.set(FIELD_VALUE, itemsPresentation);

        return presentation;
    }

    @Override
    protected JsonNode getObjectPresentation(ObjectNode object) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode fieldsPresentation = objectMapper.createArrayNode();

        object.fieldNames().forEachRemaining(name -> {
            JsonNode value = object.get(name);

            Optional<JsonNode> valuePresentationOption = getPresentation(value);

            if (valuePresentationOption.isPresent()) {
                JsonNode valuePresentation = valuePresentationOption.get();

                ObjectNode fieldPresentation = objectMapper.createObjectNode();
                fieldPresentation.put(FIELD_NAME, name);

                if (valuePresentation.has(FIELD_TYPE)) {
                    fieldPresentation.set(FIELD_TYPE, valuePresentation.get(FIELD_TYPE));
                }

                if (valuePresentation.has(FIELD_VALUE)) {
                    fieldPresentation.set(FIELD_VALUE, valuePresentation.get(FIELD_VALUE));
                }

                fieldsPresentation.add(fieldPresentation);
            }
        });

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_OBJECT);
        presentation.set(FIELD_VALUE, fieldsPresentation);

        return presentation;
    }

}