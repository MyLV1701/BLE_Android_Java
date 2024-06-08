package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

/* loaded from: classes.dex */
public abstract class StdScalarSerializer<T> extends StdSerializer<T> {
    /* JADX INFO: Access modifiers changed from: protected */
    public StdScalarSerializer(Class<T> cls) {
        super(cls);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        WritableTypeId writeTypePrefix = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(t, JsonToken.VALUE_STRING));
        serialize(t, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, writeTypePrefix);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StdScalarSerializer(Class<?> cls, boolean z) {
        super(cls);
    }
}
