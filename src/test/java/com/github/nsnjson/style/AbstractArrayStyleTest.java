package com.github.nsnjson.style;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.nsnjson.AbstractTest;
import com.github.nsnjson.asset.data.AssetsProvider;

public abstract class AbstractArrayStyleTest extends AbstractTest {

    @Override
    protected JsonNode getNullPresentation() {
        return AssetsProvider.Style.Array.getNullPresentation();
    }

    @Override
    protected JsonNode getIntPresentation() {
        return AssetsProvider.Style.Array.getIntPresentation();
    }

    @Override
    protected JsonNode getLongPresentation() {
        return AssetsProvider.Style.Array.getLongPresentation();
    }

    @Override
    protected JsonNode getDoublePresentation() {
        return AssetsProvider.Style.Array.getDoublePresentation();
    }

    @Override
    protected JsonNode getEmptyStringPresentation() {
        return AssetsProvider.Style.Array.getEmptyStringPresentation();
    }

    @Override
    protected JsonNode getStringPresentation() {
        return AssetsProvider.Style.Array.getStringPresentation();
    }

    @Override
    protected JsonNode getTruePresentation() {
        return AssetsProvider.Style.Array.getTruePresentation();
    }

    @Override
    protected JsonNode getFalsePresentation() {
        return AssetsProvider.Style.Array.getFalsePresentation();
    }

    @Override
    protected JsonNode getEmptyArrayPresentation() {
        return AssetsProvider.Style.Array.getEmptyArrayPresentation();
    }

    @Override
    protected JsonNode getArrayPresentation() {
        return AssetsProvider.Style.Array.getArrayPresentation();
    }

    @Override
    protected JsonNode getEmptyObjectPresentation() {
        return AssetsProvider.Style.Array.getEmptyObjectPresentation();
    }

    @Override
    protected JsonNode getObjectPresentation() {
        return AssetsProvider.Style.Array.getObjectPresentation();
    }

}