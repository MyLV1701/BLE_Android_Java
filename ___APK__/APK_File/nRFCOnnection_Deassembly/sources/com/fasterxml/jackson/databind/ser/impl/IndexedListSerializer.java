package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import java.util.List;

@JacksonStdImpl
/* loaded from: classes.dex */
public final class IndexedListSerializer extends AsArraySerializerBase<List<?>> {
    public IndexedListSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        super((Class<?>) List.class, javaType, z, typeSerializer, jsonSerializer);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new IndexedListSerializer(this, this._property, typeSerializer, this._elementSerializer, this._unwrapSingle);
    }

    public void serializeContentsUsing(List<?> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) {
        int size = list.size();
        if (size == 0) {
            return;
        }
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        for (int i = 0; i < size; i++) {
            Object obj = list.get(i);
            if (obj == null) {
                try {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } catch (Exception e2) {
                    wrapAndThrow(serializerProvider, e2, list, i);
                    throw null;
                }
            } else if (typeSerializer == null) {
                jsonSerializer.serialize(obj, jsonGenerator, serializerProvider);
            } else {
                jsonSerializer.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
            }
        }
    }

    public void serializeTypedContents(List<?> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        JsonSerializer<Object> _findAndAddDynamic;
        int size = list.size();
        if (size == 0) {
            return;
        }
        int i = 0;
        try {
            TypeSerializer typeSerializer = this._valueTypeSerializer;
            PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
            while (i < size) {
                Object obj = list.get(i);
                if (obj == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    Class<?> cls = obj.getClass();
                    JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                    if (serializerFor == null) {
                        if (this._elementType.hasGenericTypes()) {
                            _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, serializerProvider.constructSpecializedType(this._elementType, cls), serializerProvider);
                        } else {
                            _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                        }
                        serializerFor = _findAndAddDynamic;
                        propertySerializerMap = this._dynamicSerializers;
                    }
                    serializerFor.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
                }
                i++;
            }
        } catch (Exception e2) {
            wrapAndThrow(serializerProvider, e2, list, i);
            throw null;
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    /* renamed from: withResolved, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ AsArraySerializerBase<List<?>> withResolved2(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer jsonSerializer, Boolean bool) {
        return withResolved(beanProperty, typeSerializer, (JsonSerializer<?>) jsonSerializer, bool);
    }

    public IndexedListSerializer(IndexedListSerializer indexedListSerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, Boolean bool) {
        super(indexedListSerializer, beanProperty, typeSerializer, jsonSerializer, bool);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public boolean isEmpty(SerializerProvider serializerProvider, List<?> list) {
        return list.isEmpty();
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(List<?> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        int size = list.size();
        if (size == 1 && ((this._unwrapSingle == null && serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) || this._unwrapSingle == Boolean.TRUE)) {
            serializeContents(list, jsonGenerator, serializerProvider);
            return;
        }
        jsonGenerator.writeStartArray(size);
        serializeContents(list, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndArray();
    }

    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public void serializeContents(List<?> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        JsonSerializer<Object> _findAndAddDynamic;
        JsonSerializer<Object> jsonSerializer = this._elementSerializer;
        if (jsonSerializer != null) {
            serializeContentsUsing(list, jsonGenerator, serializerProvider, jsonSerializer);
            return;
        }
        if (this._valueTypeSerializer != null) {
            serializeTypedContents(list, jsonGenerator, serializerProvider);
            return;
        }
        int size = list.size();
        if (size == 0) {
            return;
        }
        int i = 0;
        try {
            PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
            while (i < size) {
                Object obj = list.get(i);
                if (obj == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    Class<?> cls = obj.getClass();
                    JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                    if (serializerFor == null) {
                        if (this._elementType.hasGenericTypes()) {
                            _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, serializerProvider.constructSpecializedType(this._elementType, cls), serializerProvider);
                        } else {
                            _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                        }
                        serializerFor = _findAndAddDynamic;
                        propertySerializerMap = this._dynamicSerializers;
                    }
                    serializerFor.serialize(obj, jsonGenerator, serializerProvider);
                }
                i++;
            }
        } catch (Exception e2) {
            wrapAndThrow(serializerProvider, e2, list, i);
            throw null;
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public AsArraySerializerBase<List<?>> withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, Boolean bool) {
        return new IndexedListSerializer(this, beanProperty, typeSerializer, jsonSerializer, bool);
    }
}