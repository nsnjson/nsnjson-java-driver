package com.github.nsnjson.encoding.style;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.nsnjson.Driver;
import com.github.nsnjson.style.AbstractArrayStyleTest;
import org.junit.Assert;

public class ArrayStyleEncodingTest extends AbstractArrayStyleTest {

    @Override
    protected void processTest(JsonNode data, JsonNode presentation) {
        Assert.assertEquals(presentation, assertAndGetPresentation(Driver.encodeWithArrayStyle(data)));
    }

}