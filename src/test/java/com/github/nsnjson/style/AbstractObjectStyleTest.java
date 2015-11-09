package com.github.nsnjson.style;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.nsnjson.AbstractTest;
import com.github.nsnjson.asset.data.AssetsProvider;

public abstract class AbstractObjectStyleTest extends AbstractTest {

    @Override
    protected JsonNode getNullPresentation() {
        return AssetsProvider.Style.Object.getNullPresentation();
    }

    @Override
    protected JsonNode getIntPresentation() {
        return AssetsProvider.Style.Object.getIntPresentation();
    }

    @Override
    protected JsonNode getLongPresentation() {
        return AssetsProvider.Style.Object.getLongPresentation();
    }

    @Override
    protected JsonNode getDoublePresentation() {
        return AssetsProvider.Style.Object.getDoublePresentation();
    }

    @Override
    protected JsonNode getEmptyStringPresentation() {
        return AssetsProvider.Style.Object.getEmptyStringPresentation();
    }

    @Override
    protected JsonNode getStringPresentation() {
        return AssetsProvider.Style.Object.getStringPresentation();
    }

    @Override
    protected JsonNode getTruePresentation() {
        return AssetsProvider.Style.Object.getTruePresentation();
    }

    @Override
    protected JsonNode getFalsePresentation() {
        return AssetsProvider.Style.Object.getFalsePresentation();
    }

    @Override
    protected JsonNode getEmptyArrayPresentation() {
        return AssetsProvider.Style.Object.getEmptyArrayPresentation();
    }

    @Override
    protected JsonNode getArrayPresentation() {
        return AssetsProvider.Style.Object.getArrayPresentation();
    }

    @Override
    protected JsonNode getEmptyObjectPresentation() {
        return AssetsProvider.Style.Object.getEmptyObjectPresentation();
    }

    @Override
    protected JsonNode getObjectPresentation() {
        return AssetsProvider.Style.Object.getObjectPresentation();
    }

}