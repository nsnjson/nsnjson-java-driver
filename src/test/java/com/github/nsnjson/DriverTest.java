package com.github.nsnjson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import org.junit.*;

import static com.github.nsnjson.format.Format.*;

public class DriverTest extends AbstractFormatTest {

    @Test
    public void processTestNull() {
        shouldBeConsistencyWhenGivenNull(getNull(), getNullPresentation());
    }

    @Test
    public void processTestNumberInt() {
        NumericNode value = getNumberInt();

        shouldBeConsistencyWhenGivenNumber(value, getNumberIntPresentation(value));
    }

    @Test
    public void processTestNumberLong() {
        NumericNode value = getNumberLong();

        shouldBeConsistencyWhenGivenNumber(value, getNumberLongPresentation(value));
    }

    @Test
    public void processTestNumberDouble() {
        NumericNode value = getNumberDouble();

        shouldBeConsistencyWhenGivenNumber(value, getNumberDoublePresentation(value));
    }

    @Test
    public void processTestEmptyString() {
        TextNode value = getEmptyString();

        shouldBeConsistencyWhenGivenString(value, getStringPresentation(value));
    }

    @Test
    public void processTestString() {
        TextNode value = getString();

        shouldBeConsistencyWhenGivenString(value, getStringPresentation(value));
    }

    @Test
    public void processTestBooleanTrue() {
        BooleanNode value = getBooleanTrue();

        shouldBeConsistencyWhenGivenBoolean(value, getBooleanPresentation(value));
    }

    @Test
    public void processTestBooleanFalse() {
        BooleanNode value = getBooleanFalse();

        shouldBeConsistencyWhenGivenBoolean(value, getBooleanPresentation(value));
    }

    @Test
    public void processTestEmptyArray() {
        shouldBeConsistencyWhenGivenArray(getEmptyArray(), getEmptyArrayPresentation());
    }

    @Test
    public void processTestArray() {
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

        shouldBeConsistencyWhenGivenArray(array, presentation);
    }

    @Test
    public void processTestEmptyObject() {
        shouldBeConsistencyWhenGivenObject(getEmptyObject(), getEmptyObjectPresentation());
    }

    @Test
    public void processObject() {
        ObjectNode object = getObject();

        shouldBeConsistencyWhenGivenObject(object, getObjectPresentation(object));
    }

    private void shouldBeConsistencyWhenGivenNull(NullNode value, ObjectNode presentation) {
        assertConsistency(value, presentation);
    }

    private void shouldBeConsistencyWhenGivenNumber(NumericNode value, ObjectNode presentation) {
        assertConsistency(value, presentation);
    }

    private void shouldBeConsistencyWhenGivenString(TextNode value, ObjectNode presentation) {
        assertConsistency(value, presentation);
    }

    private void shouldBeConsistencyWhenGivenBoolean(BooleanNode value, ObjectNode presentation) {
        assertConsistency(value, presentation);
    }

    private void shouldBeConsistencyWhenGivenArray(ArrayNode array, ObjectNode presentation) {
        assertConsistency(array, presentation);
    }

    private  void shouldBeConsistencyWhenGivenObject(ObjectNode object, ObjectNode presentation) {
        assertConsistency(object, presentation);
    }

    private static void assertConsistency(JsonNode value, ObjectNode presentation) {
        assertConsistencyByEncoding(value);

        assertConsistencyByDecoding(presentation);
    }

    private static void assertConsistencyByEncoding(JsonNode value) {
        Assert.assertEquals(value, Driver.decode(Driver.encode(value)));
    }

    private static void assertConsistencyByDecoding(ObjectNode presentation) {
        Assert.assertEquals(presentation, Driver.encode(Driver.decode(presentation)));
    }

}