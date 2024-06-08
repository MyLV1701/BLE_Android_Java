package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumValues;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/* loaded from: classes.dex */
public abstract class StdKeySerializers {
    protected static final JsonSerializer<Object> DEFAULT_STRING_SERIALIZER;

    /* loaded from: classes.dex */
    public static class Default extends StdSerializer<Object> {
        protected final int _typeId;

        public Default(int i, Class<?> cls) {
            super(cls, false);
            this._typeId = i;
        }

        @Override // com.fasterxml.jackson.databind.JsonSerializer
        public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            String name;
            switch (this._typeId) {
                case 1:
                    serializerProvider.defaultSerializeDateKey((Date) obj, jsonGenerator);
                    return;
                case 2:
                    serializerProvider.defaultSerializeDateKey(((Calendar) obj).getTimeInMillis(), jsonGenerator);
                    return;
                case 3:
                    jsonGenerator.writeFieldName(((Class) obj).getName());
                    return;
                case 4:
                    if (serializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {
                        name = obj.toString();
                    } else {
                        Enum r3 = (Enum) obj;
                        if (serializerProvider.isEnabled(SerializationFeature.WRITE_ENUM_KEYS_USING_INDEX)) {
                            name = String.valueOf(r3.ordinal());
                        } else {
                            name = r3.name();
                        }
                    }
                    jsonGenerator.writeFieldName(name);
                    return;
                case 5:
                case 6:
                    jsonGenerator.writeFieldId(((Number) obj).longValue());
                    return;
                case 7:
                    jsonGenerator.writeFieldName(serializerProvider.getConfig().getBase64Variant().encode((byte[]) obj));
                    return;
                default:
                    jsonGenerator.writeFieldName(obj.toString());
                    return;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class Dynamic extends StdSerializer<Object> {
        protected transient PropertySerializerMap _dynamicSerializers;

        public Dynamic() {
            super(String.class, false);
            this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
        }

        protected JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) {
            if (cls == Object.class) {
                Default r4 = new Default(8, cls);
                this._dynamicSerializers = propertySerializerMap.newWith(cls, r4);
                return r4;
            }
            PropertySerializerMap.SerializerAndMapResult findAndAddKeySerializer = propertySerializerMap.findAndAddKeySerializer(cls, serializerProvider, null);
            PropertySerializerMap propertySerializerMap2 = findAndAddKeySerializer.map;
            if (propertySerializerMap != propertySerializerMap2) {
                this._dynamicSerializers = propertySerializerMap2;
            }
            return findAndAddKeySerializer.serializer;
        }

        @Override // com.fasterxml.jackson.databind.JsonSerializer
        public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            Class<?> cls = obj.getClass();
            PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
            JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
            if (serializerFor == null) {
                serializerFor = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
            }
            serializerFor.serialize(obj, jsonGenerator, serializerProvider);
        }
    }

    /* loaded from: classes.dex */
    public static class EnumKeySerializer extends StdSerializer<Object> {
        protected final EnumValues _values;

        protected EnumKeySerializer(Class<?> cls, EnumValues enumValues) {
            super(cls, false);
            this._values = enumValues;
        }

        public static EnumKeySerializer construct(Class<?> cls, EnumValues enumValues) {
            return new EnumKeySerializer(cls, enumValues);
        }

        @Override // com.fasterxml.jackson.databind.JsonSerializer
        public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            if (serializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {
                jsonGenerator.writeFieldName(obj.toString());
                return;
            }
            Enum<?> r2 = (Enum) obj;
            if (serializerProvider.isEnabled(SerializationFeature.WRITE_ENUM_KEYS_USING_INDEX)) {
                jsonGenerator.writeFieldName(String.valueOf(r2.ordinal()));
            } else {
                jsonGenerator.writeFieldName(this._values.serializedValueFor(r2));
            }
        }
    }

    /* loaded from: classes.dex */
    public static class StringKeySerializer extends StdSerializer<Object> {
        public StringKeySerializer() {
            super(String.class, false);
        }

        @Override // com.fasterxml.jackson.databind.JsonSerializer
        public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeFieldName((String) obj);
        }
    }

    static {
        new StdKeySerializer();
        DEFAULT_STRING_SERIALIZER = new StringKeySerializer();
    }

    public static JsonSerializer<Object> getFallbackKeySerializer(SerializationConfig serializationConfig, Class<?> cls) {
        if (cls != null) {
            if (cls == Enum.class) {
                return new Dynamic();
            }
            if (cls.isEnum()) {
                return EnumKeySerializer.construct(cls, EnumValues.constructFromName(serializationConfig, cls));
            }
        }
        return new Default(8, cls);
    }

    public static JsonSerializer<Object> getStdKeySerializer(SerializationConfig serializationConfig, Class<?> cls, boolean z) {
        if (cls != null && cls != Object.class) {
            if (cls == String.class) {
                return DEFAULT_STRING_SERIALIZER;
            }
            if (cls.isPrimitive()) {
                cls = ClassUtil.wrapperType(cls);
            }
            if (cls == Integer.class) {
                return new Default(5, cls);
            }
            if (cls == Long.class) {
                return new Default(6, cls);
            }
            if (!cls.isPrimitive() && !Number.class.isAssignableFrom(cls)) {
                if (cls == Class.class) {
                    return new Default(3, cls);
                }
                if (Date.class.isAssignableFrom(cls)) {
                    return new Default(1, cls);
                }
                if (Calendar.class.isAssignableFrom(cls)) {
                    return new Default(2, cls);
                }
                if (cls == UUID.class) {
                    return new Default(8, cls);
                }
                if (cls == byte[].class) {
                    return new Default(7, cls);
                }
                if (z) {
                    return new Default(8, cls);
                }
                return null;
            }
            return new Default(8, cls);
        }
        return new Dynamic();
    }
}
