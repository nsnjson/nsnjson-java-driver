package com.github.nsnjson.consistency;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.nsnjson.style.AbstractObjectStyleTest;
import org.junit.Assert;

import static com.github.nsnjson.Driver.*;

public class ObjectStyleConsistencyTest extends AbstractObjectStyleTest {

    @Override
    protected void processTest(JsonNode data, JsonNode presentation) {
        JsonNode actualPresentation = assertAndGetPresentation(encodeWithObjectStyle(data));

        Assert.assertEquals(presentation, actualPresentation);

        JsonNode actualData = assertAndGetData(decodeWithObjectStyle(actualPresentation));

        Assert.assertEquals(data, actualData);
    }

}