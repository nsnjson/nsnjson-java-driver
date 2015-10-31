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
    public void processTestNumberLong() {
        NumericNode value = getNumberLong();

        shouldEncodeWhenGivenNumber(value, getNumberLongPresentation(value));
    }

    @Test
    public void processTestNumberDouble() {
        NumericNode value = getNumberDouble();

        shouldEncodeWhenGivenNumber(value, getNumberDoublePresentation(value));
    }

    @Test
    public void processTestEmptyString() {
        TextNode value = getEmptyString();

        shouldEncodeWhenGivenString(value, getStringPresentation(value));
    }

    @Test
    public void processTestString() {
        TextNode value = getString();

        shouldEncodeWhenGivenString(value, getStringPresentation(value));
    }

    @Test
    public void processTestBooleanTrue() {
        BooleanNode value = getBooleanTrue();

        shouldEncodeWhenGivenBoolean(value, getBooleanPresentation(value));
    }

    @Test
    public void processTestBooleanFalse() {
        BooleanNode value = getBooleanFalse();

        shouldEncodeWhenGivenBoolean(value, getBooleanPresentation(value));
    }

    @Test
    public void processTestEmptyArray() {
        shouldEncodeWhenGivenArray(getEmptyArray(), getEmptyArrayPresentation());
    }

    @Test
    public void processTestArray() {
        ArrayNode array = getArray();

        shouldEncodeWhenGivenArray(array, getArrayPresentation(array));
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

    public void shouldEncodeWhenGivenString(TextNode value, ObjectNode presentation) {
        assertEncoding(value, presentation);
    }

    private void shouldEncodeWhenGivenBoolean(BooleanNode value, ObjectNode presentation) {
        assertEncoding(value, presentation);
    }

    private void shouldEncodeWhenGivenArray(ArrayNode array, ObjectNode presentation) {
        assertEncoding(array, presentation);
    }

    private static void assertEncoding(JsonNode value, ObjectNode presentation) {
        Assert.assertEquals(presentation, Encoder.encode(value));
    }

}