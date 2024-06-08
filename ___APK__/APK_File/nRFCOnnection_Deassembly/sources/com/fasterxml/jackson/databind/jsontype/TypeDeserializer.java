package com.fasterxml.jackson.databind.jsontype;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;

/* loaded from: classes.dex */
public abstract class TypeDeserializer {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.fasterxml.jackson.databind.jsontype.TypeDeserializer$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static Object deserializeIfNatural(JsonParser jsonParser, DeserializationContext deserializationContext, JavaType javaType) {
        return deserializeIfNatural(jsonParser, deserializationContext, javaType.getRawClass());
    }

    public abstract Object deserializeTypedFromAny(JsonParser jsonParser, DeserializationContext deserializationContext);

    public abstract Object deserializeTypedFromArray(JsonParser jsonParser, DeserializationContext deserializationContext);

    public abstract Object deserializeTypedFromObject(JsonParser jsonParser, DeserializationContext deserializationContext);

    public abstract Object deserializeTypedFromScalar(JsonParser jsonParser, DeserializationContext deserializationContext);

    public abstract TypeDeserializer forProperty(BeanProperty beanProperty);

    public abstract Class<?> getDefaultImpl();

    public abstract String getPropertyName();

    public abstract TypeIdResolver getTypeIdResolver();

    public abstract JsonTypeInfo.As getTypeInclusion();

    public static Object deserializeIfNatural(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == null) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[currentToken.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i == 5 && cls.isAssignableFrom(Boolean.class)) {
                            return Boolean.FALSE;
                        }
                    } else if (cls.isAssignableFrom(Boolean.class)) {
                        return Boolean.TRUE;
                    }
                } else if (cls.isAssignableFrom(Double.class)) {
                    return Double.valueOf(jsonParser.getDoubleValue());
                }
            } else if (cls.isAssignableFrom(Integer.class)) {
                return Integer.valueOf(jsonParser.getIntValue());
            }
        } else if (cls.isAssignableFrom(String.class)) {
            return jsonParser.getText();
        }
        return null;
    }
}
