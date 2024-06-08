package com.fasterxml.jackson.core;

/* loaded from: classes.dex */
public abstract class ObjectCodec extends TreeCodec implements Versioned {
    public abstract void writeValue(JsonGenerator jsonGenerator, Object obj);
}
