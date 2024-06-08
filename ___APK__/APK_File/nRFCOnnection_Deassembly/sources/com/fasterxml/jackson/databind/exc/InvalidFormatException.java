package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonParser;

/* loaded from: classes.dex */
public class InvalidFormatException extends MismatchedInputException {
    public InvalidFormatException(JsonParser jsonParser, String str, Object obj, Class<?> cls) {
        super(jsonParser, str, cls);
    }

    public static InvalidFormatException from(JsonParser jsonParser, String str, Object obj, Class<?> cls) {
        return new InvalidFormatException(jsonParser, str, obj, cls);
    }
}
