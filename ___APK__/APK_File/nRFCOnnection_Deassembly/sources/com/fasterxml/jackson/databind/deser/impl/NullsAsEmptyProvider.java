package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import java.io.Serializable;

/* loaded from: classes.dex */
public class NullsAsEmptyProvider implements NullValueProvider, Serializable {
    protected final JsonDeserializer<?> _deserializer;

    public NullsAsEmptyProvider(JsonDeserializer<?> jsonDeserializer) {
        this._deserializer = jsonDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.deser.NullValueProvider
    public Object getNullValue(DeserializationContext deserializationContext) {
        return this._deserializer.getEmptyValue(deserializationContext);
    }
}
