package com.github.nsnjson.consistency;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.nsnjson.style.AbstractArrayStyleTest;
import org.junit.Assert;

import static com.github.nsnjson.Driver.*;

public class ArrayStyleConsistencyTest extends AbstractArrayStyleTest {

    @Override
    protected void processTest(JsonNode data, JsonNode presentation) {
        JsonNode actualPresentation = assertAndGetPresentation(encodeWithArrayStyle(data));

        Assert.assertEquals(presentation, actualPresentation);

        JsonNode actualData = assertAndGetData(decodeWithArrayStyle(actualPresentation));

        Assert.assertEquals(data, actualData);
    }

}