package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.util.TokenBuffer;

@JacksonStdImpl
/* loaded from: classes.dex */
public class TokenBufferDeserializer extends StdScalarDeserializer<TokenBuffer> {
    public TokenBufferDeserializer() {
        super((Class<?>) TokenBuffer.class);
    }

    protected TokenBuffer createBufferInstance(JsonParser jsonParser) {
        return new TokenBuffer(jsonParser);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public TokenBuffer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        TokenBuffer createBufferInstance = createBufferInstance(jsonParser);
        createBufferInstance.deserialize(jsonParser, deserializationContext);
        return createBufferInstance;
    }
}
