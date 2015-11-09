package com.github.nsnjson.encoding.style;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import com.github.nsnjson.encoding.*;
import org.junit.Assert;

import java.util.Optional;

import static com.github.nsnjson.format.Format.*;

public class ArrayStyleEncodingTest extends AbstractEncodingTest {

    @Override
    protected JsonNode getNullPresentation() {
        return new ObjectMapper().createArrayNode();
    }

    @Override
    protected JsonNode getNumberPresentation(NumericNode value) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentation = objectMapper.createArrayNode();
        presentation.add(TYPE_MARKER_NUMBER);
        presentation.add(value);

        return presentation;
    }

    @Override
    protected JsonNode getStringPresentation(TextNode value) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentation = objectMapper.createArrayNode();
        presentation.add(TYPE_MARKER_STRING);
        presentation.add(value);

        return presentation;
    }

    @Override
    protected JsonNode getBooleanPresentation(BooleanNode value) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentation = objectMapper.createArrayNode();
        presentation.add(TYPE_MARKER_BOOLEAN);
        presentation.add(value.asBoolean() ? BOOLEAN_TRUE : BOOLEAN_FALSE);

        return presentation;
    }

    @Override
    protected JsonNode getArrayPresentation(ArrayNode array) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentation = objectMapper.createArrayNode();
        presentation.add(TYPE_MARKER_ARRAY);

        for (int i = 0; i < array.size(); i++) {
            JsonNode item = array.get(i);

            Optional<JsonNode> itemPresentationOption = getPresentation(item);

            if (itemPresentationOption.isPresent()) {
                JsonNode itemPresentation = itemPresentationOption.get();

                presentation.add(itemPresentation);
            }
        }

        return presentation;
    }

    @Override
    protected JsonNode getObjectPresentation(ObjectNode object) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentation = objectMapper.createArrayNode();
        presentation.add(TYPE_MARKER_OBJECT);

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

                presentation.add(fieldPresentation);
            }
        });

        return presentation;
    }

    @Override
    protected void assertEncoding(JsonNode data, JsonNode presentation) {
        Assert.assertEquals(presentation, assertAndGetPresentation(Encoder.encodeWithArrayStyle(data)));
    }

}