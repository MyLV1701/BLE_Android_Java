package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class ObjectReader extends ObjectCodec implements Versioned, Serializable {
    protected final DeserializationConfig _config;
    protected final DefaultDeserializationContext _context;
    protected final InjectableValues _injectableValues;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;

    /* JADX INFO: Access modifiers changed from: protected */
    public ObjectReader(ObjectMapper objectMapper, DeserializationConfig deserializationConfig, JavaType javaType, Object obj, FormatSchema formatSchema, InjectableValues injectableValues) {
        this._config = deserializationConfig;
        this._context = objectMapper._deserializationContext;
        this._rootDeserializers = objectMapper._rootDeserializers;
        JsonFactory jsonFactory = objectMapper._jsonFactory;
        this._injectableValues = injectableValues;
        deserializationConfig.useRootWrapping();
        _prefetchRootDeserializer(javaType);
    }

    protected JsonDeserializer<Object> _prefetchRootDeserializer(JavaType javaType) {
        if (javaType == null || !this._config.isEnabled(DeserializationFeature.EAGER_DESERIALIZER_FETCH)) {
            return null;
        }
        JsonDeserializer<Object> jsonDeserializer = this._rootDeserializers.get(javaType);
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        try {
            JsonDeserializer<Object> findRootValueDeserializer = createDeserializationContext(null).findRootValueDeserializer(javaType);
            if (findRootValueDeserializer != null) {
                try {
                    this._rootDeserializers.put(javaType, findRootValueDeserializer);
                } catch (JsonProcessingException unused) {
                    return findRootValueDeserializer;
                }
            }
            return findRootValueDeserializer;
        } catch (JsonProcessingException unused2) {
            return jsonDeserializer;
        }
    }

    protected DefaultDeserializationContext createDeserializationContext(JsonParser jsonParser) {
        return this._context.createInstance(this._config, jsonParser, this._injectableValues);
    }

    @Override // com.fasterxml.jackson.core.ObjectCodec
    public void writeValue(JsonGenerator jsonGenerator, Object obj) {
        throw new UnsupportedOperationException("Not implemented for ObjectReader");
    }
}
