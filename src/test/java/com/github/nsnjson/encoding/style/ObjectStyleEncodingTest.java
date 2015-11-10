package com.github.nsnjson.encoding.style;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.nsnjson.Driver;
import com.github.nsnjson.style.AbstractObjectStyleTest;
import org.junit.Assert;

public class ObjectStyleEncodingTest extends AbstractObjectStyleTest {

    @Override
    protected void processTest(JsonNode data, JsonNode presentation) {
        Assert.assertEquals(presentation, assertAndGetPresentation(Driver.encodeWithObjectStyle(data)));
    }

}