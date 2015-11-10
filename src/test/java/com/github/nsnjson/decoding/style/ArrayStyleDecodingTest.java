package com.github.nsnjson.decoding.style;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.nsnjson.Driver;
import com.github.nsnjson.style.AbstractArrayStyleTest;
import org.junit.Assert;

public class ArrayStyleDecodingTest extends AbstractArrayStyleTest {

    @Override
    protected void processTest(JsonNode data, JsonNode presentation) {
        Assert.assertEquals(data, assertAndGetData(Driver.decodeWithArrayStyle(presentation)));
    }

}