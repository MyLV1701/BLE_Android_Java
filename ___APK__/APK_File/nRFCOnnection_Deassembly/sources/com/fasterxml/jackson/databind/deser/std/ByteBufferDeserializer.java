package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.util.ByteBufferBackedOutputStream;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class ByteBufferDeserializer extends StdScalarDeserializer<ByteBuffer> {
    /* JADX INFO: Access modifiers changed from: protected */
    public ByteBufferDeserializer() {
        super((Class<?>) ByteBuffer.class);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public /* bridge */ /* synthetic */ Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        ByteBuffer byteBuffer = (ByteBuffer) obj;
        deserialize(jsonParser, deserializationContext, byteBuffer);
        return byteBuffer;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public ByteBuffer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return ByteBuffer.wrap(jsonParser.getBinaryValue());
    }

    public ByteBuffer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, ByteBuffer byteBuffer) {
        ByteBufferBackedOutputStream byteBufferBackedOutputStream = new ByteBufferBackedOutputStream(byteBuffer);
        jsonParser.readBinaryValue(deserializationContext.getBase64Variant(), byteBufferBackedOutputStream);
        byteBufferBackedOutputStream.close();
        return byteBuffer;
    }
}
