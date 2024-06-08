package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.util.AbstractMap;
import java.util.Map;

@JacksonStdImpl
/* loaded from: classes.dex */
public class MapEntryDeserializer extends ContainerDeserializerBase<Map.Entry<Object, Object>> implements ContextualDeserializer {
    protected final KeyDeserializer _keyDeserializer;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;

    public MapEntryDeserializer(JavaType javaType, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        super(javaType);
        if (javaType.containedTypeCount() == 2) {
            this._keyDeserializer = keyDeserializer;
            this._valueDeserializer = jsonDeserializer;
            this._valueTypeDeserializer = typeDeserializer;
        } else {
            throw new IllegalArgumentException("Missing generic type information for " + javaType);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) {
        KeyDeserializer keyDeserializer;
        JsonDeserializer<?> handleSecondaryContextualization;
        KeyDeserializer keyDeserializer2 = this._keyDeserializer;
        if (keyDeserializer2 == 0) {
            keyDeserializer = deserializationContext.findKeyDeserializer(this._containerType.containedType(0), beanProperty);
        } else {
            boolean z = keyDeserializer2 instanceof ContextualKeyDeserializer;
            keyDeserializer = keyDeserializer2;
            if (z) {
                keyDeserializer = ((ContextualKeyDeserializer) keyDeserializer2).createContextual(deserializationContext, beanProperty);
            }
        }
        JsonDeserializer<?> findConvertingContentDeserializer = findConvertingContentDeserializer(deserializationContext, beanProperty, this._valueDeserializer);
        JavaType containedType = this._containerType.containedType(1);
        if (findConvertingContentDeserializer == null) {
            handleSecondaryContextualization = deserializationContext.findContextualValueDeserializer(containedType, beanProperty);
        } else {
            handleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(findConvertingContentDeserializer, beanProperty, containedType);
        }
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (typeDeserializer != null) {
            typeDeserializer = typeDeserializer.forProperty(beanProperty);
        }
        return withResolved(keyDeserializer, typeDeserializer, handleSecondaryContextualization);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    protected MapEntryDeserializer withResolved(KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        return (this._keyDeserializer == keyDeserializer && this._valueDeserializer == jsonDeserializer && this._valueTypeDeserializer == typeDeserializer) ? this : new MapEntryDeserializer(this, keyDeserializer, jsonDeserializer, typeDeserializer);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public /* bridge */ /* synthetic */ Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        deserialize(jsonParser, deserializationContext, (Map.Entry<Object, Object>) obj);
        throw null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Map.Entry<Object, Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        Object deserializeWithType;
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.START_OBJECT && currentToken != JsonToken.FIELD_NAME && currentToken != JsonToken.END_OBJECT) {
            return _deserializeFromEmpty(jsonParser, deserializationContext);
        }
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        if (currentToken != JsonToken.FIELD_NAME) {
            if (currentToken != JsonToken.END_OBJECT) {
                return (Map.Entry) deserializationContext.handleUnexpectedToken(handledType(), jsonParser);
            }
            deserializationContext.reportInputMismatch(this, "Cannot deserialize a Map.Entry out of empty JSON Object", new Object[0]);
            throw null;
        }
        KeyDeserializer keyDeserializer = this._keyDeserializer;
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        String currentName = jsonParser.getCurrentName();
        Object deserializeKey = keyDeserializer.deserializeKey(currentName, deserializationContext);
        try {
            if (jsonParser.nextToken() == JsonToken.VALUE_NULL) {
                deserializeWithType = jsonDeserializer.getNullValue(deserializationContext);
            } else if (typeDeserializer == null) {
                deserializeWithType = jsonDeserializer.deserialize(jsonParser, deserializationContext);
            } else {
                deserializeWithType = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
            }
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken != JsonToken.END_OBJECT) {
                if (nextToken == JsonToken.FIELD_NAME) {
                    deserializationContext.reportInputMismatch(this, "Problem binding JSON into Map.Entry: more than one entry in JSON (second field: '%s')", jsonParser.getCurrentName());
                    throw null;
                }
                deserializationContext.reportInputMismatch(this, "Problem binding JSON into Map.Entry: unexpected content after JSON Object entry: " + nextToken, new Object[0]);
                throw null;
            }
            return new AbstractMap.SimpleEntry(deserializeKey, deserializeWithType);
        } catch (Exception e2) {
            wrapAndThrow(e2, Map.Entry.class, currentName);
            throw null;
        }
    }

    protected MapEntryDeserializer(MapEntryDeserializer mapEntryDeserializer, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        super(mapEntryDeserializer);
        this._keyDeserializer = keyDeserializer;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
    }

    public Map.Entry<Object, Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Map.Entry<Object, Object> entry) {
        throw new IllegalStateException("Cannot update Map.Entry values");
    }
}
