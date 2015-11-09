package com.github.nsnjson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

import static com.github.nsnjson.format.CustomFormat.*;

public abstract class AbstractCustomFormatTest extends AbstractTest {

    @Override
    protected JsonNode getNullPresentation() {
        ArrayNode presentation = new ObjectMapper().createArrayNode();
        presentation.add(TYPE_NAME_NULL);

        return presentation;
    }

    @Override
    protected JsonNode getNumberPresentation(NumericNode value) {
        ArrayNode presentation = new ObjectMapper().createArrayNode();
        presentation.add(TYPE_NAME_NUMBER);
        presentation.add(value);

        return presentation;
    }

    @Override
    protected JsonNode getStringPresentation(TextNode value) {
        ArrayNode presentation = new ObjectMapper().createArrayNode();
        presentation.add(TYPE_NAME_STRING);
        presentation.add(value);

        return presentation;
    }

    @Override
    protected JsonNode getBooleanPresentation(BooleanNode value) {
        ArrayNode presentation = new ObjectMapper().createArrayNode();
        presentation.add(TYPE_NAME_BOOLEAN);
        presentation.add(value);

        return presentation;
    }

    @Override
    protected JsonNode getArrayPresentation(ArrayNode array) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentation = objectMapper.createArrayNode();
        presentation.add(TYPE_NAME_ARRAY);

        for (JsonNode value : array) {
            getPresentation(value).ifPresent(presentation::add);
        }

        return presentation;
    }

    @Override
    protected JsonNode getObjectPresentation(ObjectNode object) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode presentation = objectMapper.createArrayNode();
        presentation.add(TYPE_NAME_OBJECT);

        object.fieldNames().forEachRemaining(name -> {
            JsonNode value = object.get(name);

            getPresentation(value).ifPresent(valuePresentation -> {
                ArrayNode fieldPresentation = objectMapper.createArrayNode();
                fieldPresentation.add(name);
                fieldPresentation.add(valuePresentation);

                presentation.add(fieldPresentation);
            });
        });

        return presentation;
    }

}