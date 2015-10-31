package com.github.nsnjson.encoding;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import com.github.nsnjson.AbstractFormatTest;
import org.junit.*;

import static com.github.nsnjson.format.Format.*;

public class EncoderTest extends AbstractFormatTest {

    @Test
    public void processTestNull() {
        shouldEncodeWhenGivenNull(getNull(), getNullPresentation());
    }

    @Test
    public void processTestNumberInt() {
        NumericNode value = getNumberInt();

        shouldEncodeWhenGivenNumber(value, getNumberIntPresentation(value));
    }

    @Test
    public void shouldEncodeWhenGivenNumberIsLong() {
        JsonNode value = getNumberLong();

        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asLong());

        assertEncoding(value, presentation);
    }

    @Test
    public void shouldEncodeWhenGivenNumberIsDouble() {
        JsonNode value = getNumberDouble();

        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.put(FIELD_VALUE, value.asDouble());

        assertEncoding(value, presentation);
    }

    @Test
    public void shouldEncodeWhenGivenStringIsEmpty() {
        JsonNode value = getEmptyString();

        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_STRING);
        presentation.put(FIELD_VALUE, value.asText());

        assertEncoding(value, presentation);
    }

    @Test
    public void shouldEncodeWhenGivenString() {
        JsonNode value = getString();

        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_STRING);
        presentation.put(FIELD_VALUE, value.asText());

        assertEncoding(value, presentation);
    }

    @Test
    public void shouldEncodeWhenGivenBooleanIsTrue() {
        JsonNode value = getBooleanTrue();

        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_BOOLEAN);
        presentation.put(FIELD_VALUE, BOOLEAN_TRUE);

        assertEncoding(value, presentation);
    }

    @Test
    public void shouldEncodeWhenGivenBooleanIsFalse() {
        JsonNode value = getBooleanFalse();

        ObjectNode presentation = new ObjectMapper().createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_BOOLEAN);
        presentation.put(FIELD_VALUE, BOOLEAN_FALSE);

        assertEncoding(value, presentation);
    }

    @Test
    public void shouldEncodeWhenGivenArrayIsEmpty() {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_ARRAY);
        presentation.set(FIELD_VALUE, objectMapper.createArrayNode());

        assertEncoding(getEmptyArray(), presentation);
    }

    @Test
    public void shouldEncodeWhenGivenArray() {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode numberInt = getNumberInt();

        JsonNode numberLong = getNumberLong();

        JsonNode numberDouble = getNumberDouble();

        JsonNode string = getString();

        JsonNode booleanTrue = getBooleanTrue();

        JsonNode booleanFalse = getBooleanFalse();

        ArrayNode array = objectMapper.createArrayNode();
        array.add(getNull());
        array.add(numberInt);
        array.add(numberLong);
        array.add(numberDouble);
        array.add(string);
        array.add(booleanTrue);
        array.add(booleanFalse);

        ObjectNode presentationOfNull = objectMapper.createObjectNode();
        presentationOfNull.put(FIELD_TYPE, TYPE_MARKER_NULL);

        ObjectNode presentationOfNumberInt = objectMapper.createObjectNode();
        presentationOfNumberInt.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentationOfNumberInt.put(FIELD_VALUE, numberInt.asInt());

        ObjectNode presentationOfNumberLong = objectMapper.createObjectNode();
        presentationOfNumberLong.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentationOfNumberLong.put(FIELD_VALUE, numberLong.asLong());

        ObjectNode presentationOfNumberDouble = objectMapper.createObjectNode();
        presentationOfNumberDouble.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentationOfNumberDouble.put(FIELD_VALUE, numberDouble.asDouble());

        ObjectNode presentationOfString = objectMapper.createObjectNode();
        presentationOfString.put(FIELD_TYPE, TYPE_MARKER_STRING);
        presentationOfString.put(FIELD_VALUE, string.asText());

        ObjectNode presentationOfBooleanTrue = objectMapper.createObjectNode();
        presentationOfBooleanTrue.put(FIELD_TYPE, TYPE_MARKER_BOOLEAN);
        presentationOfBooleanTrue.put(FIELD_VALUE, BOOLEAN_TRUE);

        ObjectNode presentationOfBooleanFalse = objectMapper.createObjectNode();
        presentationOfBooleanFalse.put(FIELD_TYPE, TYPE_MARKER_BOOLEAN);
        presentationOfBooleanFalse.put(FIELD_VALUE, BOOLEAN_FALSE);

        ArrayNode presentationOfArrayItems = objectMapper.createArrayNode();
        presentationOfArrayItems.add(presentationOfNull);
        presentationOfArrayItems.add(presentationOfNumberInt);
        presentationOfArrayItems.add(presentationOfNumberLong);
        presentationOfArrayItems.add(presentationOfNumberDouble);
        presentationOfArrayItems.add(presentationOfString);
        presentationOfArrayItems.add(presentationOfBooleanTrue);
        presentationOfArrayItems.add(presentationOfBooleanFalse);

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_ARRAY);
        presentation.set(FIELD_VALUE, presentationOfArrayItems);

        assertEncoding(array, presentation);
    }

    @Test
    public void shouldEncodeWhenGivenObjectIsEmpty() {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_OBJECT);
        presentation.set(FIELD_VALUE, objectMapper.createArrayNode());

        assertEncoding(getEmptyObject(), presentation);
    }

    @Test
    public void shouldEncodeWhenGivenObject() {
        ObjectMapper objectMapper = new ObjectMapper();

        String nullField = "null_field";

        String numberIntField = "int_field";

        String numberLongField = "long_field";

        String numberDoubleField = "double_field";

        String stringField = "string_field";

        String booleanTrueField = "true_field";

        String booleanFalseField = "false_field";

        JsonNode numberInt = getNumberInt();

        JsonNode numberLong = getNumberLong();

        JsonNode numberDouble = getNumberDouble();

        JsonNode string = getString();

        JsonNode booleanTrue = getBooleanTrue();

        JsonNode booleanFalse = getBooleanFalse();

        ObjectNode object = objectMapper.createObjectNode();
        object.set(nullField, getNull());
        object.set(numberIntField, numberInt);
        object.set(numberLongField, numberLong);
        object.set(numberDoubleField, numberDouble);
        object.set(stringField, string);
        object.set(booleanTrueField, booleanTrue);
        object.set(booleanFalseField, booleanFalse);

        ObjectNode presentationOfNullField = objectMapper.createObjectNode();
        presentationOfNullField.put(FIELD_NAME, nullField);
        presentationOfNullField.put(FIELD_TYPE, TYPE_MARKER_NULL);

        ObjectNode presentationOfNumberIntField = objectMapper.createObjectNode();
        presentationOfNumberIntField.put(FIELD_NAME, numberIntField);
        presentationOfNumberIntField.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentationOfNumberIntField.put(FIELD_VALUE, numberInt.asInt());

        ObjectNode presentationOfNumberLongField = objectMapper.createObjectNode();
        presentationOfNumberLongField.put(FIELD_NAME, numberLongField);
        presentationOfNumberLongField.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentationOfNumberLongField.put(FIELD_VALUE, numberLong.asLong());

        ObjectNode presentationOfNumberDoubleField = objectMapper.createObjectNode();
        presentationOfNumberDoubleField.put(FIELD_NAME, numberDoubleField);
        presentationOfNumberDoubleField.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentationOfNumberDoubleField.put(FIELD_VALUE, numberDouble.asDouble());

        ObjectNode presentationOfStringField = objectMapper.createObjectNode();
        presentationOfStringField.put(FIELD_NAME, stringField);
        presentationOfStringField.put(FIELD_TYPE, TYPE_MARKER_STRING);
        presentationOfStringField.put(FIELD_VALUE, string.asText());

        ObjectNode presentationOfBooleanTrueField = objectMapper.createObjectNode();
        presentationOfBooleanTrueField.put(FIELD_NAME, booleanTrueField);
        presentationOfBooleanTrueField.put(FIELD_TYPE, TYPE_MARKER_BOOLEAN);
        presentationOfBooleanTrueField.put(FIELD_VALUE, BOOLEAN_TRUE);

        ObjectNode presentationOfBooleanFalseField = objectMapper.createObjectNode();
        presentationOfBooleanFalseField.put(FIELD_NAME, booleanFalseField);
        presentationOfBooleanFalseField.put(FIELD_TYPE, TYPE_MARKER_BOOLEAN);
        presentationOfBooleanFalseField.put(FIELD_VALUE, BOOLEAN_FALSE);

        ArrayNode presentationOfArrayItems = objectMapper.createArrayNode();
        presentationOfArrayItems.add(presentationOfNullField);
        presentationOfArrayItems.add(presentationOfNumberIntField);
        presentationOfArrayItems.add(presentationOfNumberLongField);
        presentationOfArrayItems.add(presentationOfNumberDoubleField);
        presentationOfArrayItems.add(presentationOfStringField);
        presentationOfArrayItems.add(presentationOfBooleanTrueField);
        presentationOfArrayItems.add(presentationOfBooleanFalseField);

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_OBJECT);
        presentation.set(FIELD_VALUE, presentationOfArrayItems);

        assertEncoding(object, presentation);
    }

    private void shouldEncodeWhenGivenNull(NullNode value, ObjectNode presentation) {
        assertEncoding(value, presentation);
    }

    private void shouldEncodeWhenGivenNumber(NumericNode value, ObjectNode presentation) {
        assertEncoding(value, presentation);
    }

    private static void assertEncoding(JsonNode value, ObjectNode presentation) {
        Assert.assertEquals(presentation, Encoder.encode(value));
    }

}