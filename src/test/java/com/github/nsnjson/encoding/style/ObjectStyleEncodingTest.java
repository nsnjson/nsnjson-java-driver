package com.github.nsnjson.encoding.style;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import com.github.nsnjson.encoding.*;

import java.util.Optional;
import java.util.function.Function;

import static com.github.nsnjson.format.Format.*;

public class ObjectStyleEncodingTest extends AbstractEncodingTest {

    @Override
    protected JsonNode getNullPresentation() {
        return new ObjectMapper().createObjectNode();
    }

    @Override
    protected JsonNode getNumberPresentation(NumericNode value) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_NUMBER);
        presentation.set(FIELD_VALUE, value);

        return presentation;
    }

    @Override
    protected JsonNode getStringPresentation(TextNode value) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_STRING);
        presentation.set(FIELD_VALUE, value);

        return presentation;
    }

    @Override
    protected JsonNode getBooleanPresentation(BooleanNode value) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode presentation = objectMapper.createObjectNode();
        presentation.put(FIELD_TYPE, TYPE_MARKER_BOOLEAN);
        presentation.put(FIELD_VALUE, value.asBoolean() ? BOOLEAN_TRUE : BOOLEAN_FALSE);

        return presentation;
    }

    @Override
    protected JsonNode getArrayPresentation(ArrayNode array) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode itemsPresentation = objectMapper.createArrayNode();

        for (int i = 0; i < array.size(); i++) {
            JsonNode item = array.get(i);

            Optional<JsonNode> itemPresentationOption = getPresentation(item);

            if (itemPresentationOption.isPresent()) {
                JsonNode itemPresentation = itemPresentationOption.get();

                itemsPresentation.add(itemPresentation);
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

    @Override
    protected Function<JsonNode, Optional<JsonNode>> getEncodeFunction() {
        return Encoder::encodeWithObjectStyle;
    }

}