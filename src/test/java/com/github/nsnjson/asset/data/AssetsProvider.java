package com.github.nsnjson.asset.data;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

public class AssetsProvider {

    public static JsonNode getAsset(String name) {
        try {
            return new ObjectMapper().readTree(AssetsProvider.class.getResource(name));
        } catch (Exception e) {
            throw new RuntimeException("Can't load asset \"" + name + "\"", e);
        }
    }

    public static NullNode getNull() {
        return (NullNode) getAsset("/data/null.json");
    }

    public static NumericNode getInt() {
        return (NumericNode) getAsset("/data/number.int.json");
    }

    public static NumericNode getLong() {
        return (NumericNode) getAsset("/data/number.long.json");
    }

    public static NumericNode getDouble() {
        return (NumericNode) getAsset("/data/number.double.json");
    }

    public static TextNode getEmptyString() {
        return (TextNode) getAsset("/data/string.empty.json");
    }

    public static TextNode getString() {
        return (TextNode) getAsset("/data/string.json");
    }

    public static BooleanNode getTrue() {
        return (BooleanNode) getAsset("/data/boolean.true.json");
    }

    public static BooleanNode getFalse() {
        return (BooleanNode) getAsset("/data/boolean.false.json");
    }

    public static ArrayNode getEmptyArray() {
        return (ArrayNode) getAsset("/data/array.empty.json");
    }

    public static ArrayNode getArray() {
        return (ArrayNode) getAsset("/data/array.json");
    }

    public static ObjectNode getEmptyObject() {
        return (ObjectNode) getAsset("/data/object.empty.json");
    }

    public static ObjectNode getObject() {
        return (ObjectNode) getAsset("/data/object.json");
    }

    public static class Style {

        public static class Array {

            public static JsonNode getNullPresentation() {
                return getAsset("/presentation/style/array/null.json");
            }

            public static JsonNode getIntPresentation() {
                return getAsset("/presentation/style/array/number.int.json");
            }

            public static JsonNode getLongPresentation() {
                return getAsset("/presentation/style/array/number.long.json");
            }

            public static JsonNode getDoublePresentation() {
                return getAsset("/presentation/style/array/number.double.json");
            }

            public static JsonNode getEmptyStringPresentation() {
                return getAsset("/presentation/style/array/string.empty.json");
            }

            public static JsonNode getStringPresentation() {
                return getAsset("/presentation/style/array/string.json");
            }

            public static JsonNode getTruePresentation() {
                return getAsset("/presentation/style/array/boolean.true.json");
            }

            public static JsonNode getFalsePresentation() {
                return getAsset("/presentation/style/array/boolean.false.json");
            }

            public static JsonNode getEmptyArrayPresentation() {
                return getAsset("/presentation/style/array/array.empty.json");
            }

            public static JsonNode getArrayPresentation() {
                return getAsset("/presentation/style/array/array.json");
            }

            public static JsonNode getEmptyObjectPresentation() {
                return getAsset("/presentation/style/array/object.empty.json");
            }

            public static JsonNode getObjectPresentation() {
                return getAsset("/presentation/style/array/object.json");
            }

        }

        public static class Object {

            public static JsonNode getNullPresentation() {
                return getAsset("/presentation/style/object/null.json");
            }

            public static JsonNode getIntPresentation() {
                return getAsset("/presentation/style/object/number.int.json");
            }

            public static JsonNode getLongPresentation() {
                return getAsset("/presentation/style/object/number.long.json");
            }

            public static JsonNode getDoublePresentation() {
                return getAsset("/presentation/style/object/number.double.json");
            }

            public static JsonNode getEmptyStringPresentation() {
                return getAsset("/presentation/style/object/string.empty.json");
            }

            public static JsonNode getStringPresentation() {
                return getAsset("/presentation/style/object/string.json");
            }

            public static JsonNode getTruePresentation() {
                return getAsset("/presentation/style/object/boolean.true.json");
            }

            public static JsonNode getFalsePresentation() {
                return getAsset("/presentation/style/object/boolean.false.json");
            }

            public static JsonNode getEmptyArrayPresentation() {
                return getAsset("/presentation/style/object/array.empty.json");
            }

            public static JsonNode getArrayPresentation() {
                return getAsset("/presentation/style/object/array.json");
            }

            public static JsonNode getEmptyObjectPresentation() {
                return getAsset("/presentation/style/object/object.empty.json");
            }

            public static JsonNode getObjectPresentation() {
                return getAsset("/presentation/style/object/object.json");
            }

        }

    }

}