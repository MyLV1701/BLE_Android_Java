package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;

/* loaded from: classes.dex */
public class StdDelegatingDeserializer<T> extends StdDeserializer<T> implements ContextualDeserializer, ResolvableDeserializer {
    protected final Converter<Object, T> _converter;
    protected final JsonDeserializer<Object> _delegateDeserializer;
    protected final JavaType _delegateType;

    public StdDelegatingDeserializer(Converter<?, T> converter) {
        super((Class<?>) Object.class);
        this._converter = converter;
        this._delegateType = null;
        this._delegateDeserializer = null;
    }

    protected Object _handleIncompatibleUpdateValue(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        throw new UnsupportedOperationException(String.format("Cannot update object of type %s (using deserializer for type %s)" + obj.getClass().getName(), this._delegateType));
    }

    protected T convertValue(Object obj) {
        return this._converter.convert(obj);
    }

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) {
        JsonDeserializer<?> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            JsonDeserializer<?> handleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(jsonDeserializer, beanProperty, this._delegateType);
            return handleSecondaryContextualization != this._delegateDeserializer ? withDelegate(this._converter, this._delegateType, handleSecondaryContextualization) : this;
        }
        JavaType inputType = this._converter.getInputType(deserializationContext.getTypeFactory());
        return withDelegate(this._converter, inputType, deserializationContext.findContextualValueDeserializer(inputType, beanProperty));
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        Object deserialize = this._delegateDeserializer.deserialize(jsonParser, deserializationContext);
        if (deserialize == null) {
            return null;
        }
        return convertValue(deserialize);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        Object deserialize = this._delegateDeserializer.deserialize(jsonParser, deserializationContext);
        if (deserialize == null) {
            return null;
        }
        return convertValue(deserialize);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Class<?> handledType() {
        return this._delegateDeserializer.handledType();
    }

    @Override // com.fasterxml.jackson.databind.deser.ResolvableDeserializer
    public void resolve(DeserializationContext deserializationContext) {
        NullValueProvider nullValueProvider = this._delegateDeserializer;
        if (nullValueProvider == null || !(nullValueProvider instanceof ResolvableDeserializer)) {
            return;
        }
        ((ResolvableDeserializer) nullValueProvider).resolve(deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return this._delegateDeserializer.supportsUpdate(deserializationConfig);
    }

    protected StdDelegatingDeserializer<T> withDelegate(Converter<Object, T> converter, JavaType javaType, JsonDeserializer<?> jsonDeserializer) {
        ClassUtil.verifyMustOverride(StdDelegatingDeserializer.class, this, "withDelegate");
        return new StdDelegatingDeserializer<>(converter, javaType, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        if (this._delegateType.getRawClass().isAssignableFrom(obj.getClass())) {
            return (T) this._delegateDeserializer.deserialize(jsonParser, deserializationContext, obj);
        }
        _handleIncompatibleUpdateValue(jsonParser, deserializationContext, obj);
        throw null;
    }

    public StdDelegatingDeserializer(Converter<Object, T> converter, JavaType javaType, JsonDeserializer<?> jsonDeserializer) {
        super(javaType);
        this._converter = converter;
        this._delegateType = javaType;
        this._delegateDeserializer = jsonDeserializer;
    }
}
