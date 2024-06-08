package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/* loaded from: classes.dex */
public class FailingDeserializer extends StdDeserializer<Object> {
    protected final String _message;

    public FailingDeserializer(String str) {
        super((Class<?>) Object.class);
        this._message = str;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        deserializationContext.reportInputMismatch(this, this._message, new Object[0]);
        throw null;
    }
}
