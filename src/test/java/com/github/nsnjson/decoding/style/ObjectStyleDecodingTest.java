package com.github.nsnjson.decoding.style;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.nsnjson.Driver;
import com.github.nsnjson.style.AbstractObjectStyleTest;
import org.junit.Assert;

public class ObjectStyleDecodingTest extends AbstractObjectStyleTest {

    @Override
    protected void processTest(JsonNode data, JsonNode presentation) {
        Assert.assertEquals(data, assertAndGetData(Driver.decodeWithObjectStyle(presentation)));
    }

}