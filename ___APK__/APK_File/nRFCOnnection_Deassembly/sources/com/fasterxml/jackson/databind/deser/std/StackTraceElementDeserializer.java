package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import no.nordicsemi.android.mcp.database.FormatColumns;

/* loaded from: classes.dex */
public class StackTraceElementDeserializer extends StdScalarDeserializer<StackTraceElement> {
    public StackTraceElementDeserializer() {
        super((Class<?>) StackTraceElement.class);
    }

    protected StackTraceElement constructValue(DeserializationContext deserializationContext, String str, String str2, String str3, int i, String str4, String str5, String str6) {
        return new StackTraceElement(str, str2, str3, i);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public StackTraceElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        int _parseIntPrimitive;
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.START_OBJECT) {
            if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                StackTraceElement deserialize = deserialize(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return deserialize;
                }
                handleMissingEndArrayForSingle(jsonParser, deserializationContext);
                throw null;
            }
            return (StackTraceElement) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
        }
        String str = "";
        String str2 = str;
        String str3 = str2;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        int i = -1;
        while (true) {
            JsonToken nextValue = jsonParser.nextValue();
            if (nextValue != JsonToken.END_OBJECT) {
                String currentName = jsonParser.getCurrentName();
                if ("className".equals(currentName)) {
                    str = jsonParser.getText();
                } else if ("classLoaderName".equals(currentName)) {
                    str6 = jsonParser.getText();
                } else if ("fileName".equals(currentName)) {
                    str3 = jsonParser.getText();
                } else if ("lineNumber".equals(currentName)) {
                    if (nextValue.isNumeric()) {
                        _parseIntPrimitive = jsonParser.getIntValue();
                    } else {
                        _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
                    }
                    i = _parseIntPrimitive;
                } else if ("methodName".equals(currentName)) {
                    str2 = jsonParser.getText();
                } else if (!"nativeMethod".equals(currentName)) {
                    if ("moduleName".equals(currentName)) {
                        str4 = jsonParser.getText();
                    } else if ("moduleVersion".equals(currentName)) {
                        str5 = jsonParser.getText();
                    } else if (!"declaringClass".equals(currentName) && !FormatColumns.FORMAT.equals(currentName)) {
                        handleUnknownProperty(jsonParser, deserializationContext, this._valueClass, currentName);
                    }
                }
                jsonParser.skipChildren();
            } else {
                return constructValue(deserializationContext, str, str2, str3, i, str4, str5, str6);
            }
        }
    }
}
