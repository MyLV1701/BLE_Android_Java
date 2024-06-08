package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.math.BigDecimal;
import java.math.BigInteger;

@JacksonStdImpl
/* loaded from: classes.dex */
public class NumberSerializer extends StdScalarSerializer<Number> implements ContextualSerializer {
    public static final NumberSerializer instance = new NumberSerializer(Number.class);

    /* renamed from: com.fasterxml.jackson.databind.ser.std.NumberSerializer$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape = new int[JsonFormat.Shape.values().length];

        static {
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape[JsonFormat.Shape.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class BigDecimalAsStringSerializer extends ToStringSerializerBase {
        static final BigDecimalAsStringSerializer BD_INSTANCE = new BigDecimalAsStringSerializer();

        public BigDecimalAsStringSerializer() {
            super(BigDecimal.class);
        }

        protected boolean _verifyBigDecimalRange(JsonGenerator jsonGenerator, BigDecimal bigDecimal) {
            int scale = bigDecimal.scale();
            return scale >= -9999 && scale <= 9999;
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ToStringSerializerBase, com.fasterxml.jackson.databind.JsonSerializer
        public boolean isEmpty(SerializerProvider serializerProvider, Object obj) {
            valueToString(obj);
            throw null;
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ToStringSerializerBase, com.fasterxml.jackson.databind.JsonSerializer
        public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            String obj2;
            if (jsonGenerator.isEnabled(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)) {
                BigDecimal bigDecimal = (BigDecimal) obj;
                if (_verifyBigDecimalRange(jsonGenerator, bigDecimal)) {
                    obj2 = bigDecimal.toPlainString();
                } else {
                    serializerProvider.reportMappingProblem(String.format("Attempt to write plain `java.math.BigDecimal` (see JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN) with illegal scale (%d): needs to be between [-%d, %d]", Integer.valueOf(bigDecimal.scale()), 9999, 9999), new Object[0]);
                    throw null;
                }
            } else {
                obj2 = obj.toString();
            }
            jsonGenerator.writeString(obj2);
        }

        @Override // com.fasterxml.jackson.databind.ser.std.ToStringSerializerBase
        public String valueToString(Object obj) {
            throw new IllegalStateException();
        }
    }

    public NumberSerializer(Class<? extends Number> cls) {
        super(cls, false);
    }

    public static JsonSerializer<?> bigDecimalAsStringSerializer() {
        return BigDecimalAsStringSerializer.BD_INSTANCE;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContextualSerializer
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
        JsonFormat.Value findFormatOverrides = findFormatOverrides(serializerProvider, beanProperty, handledType());
        if (findFormatOverrides == null || AnonymousClass1.$SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape[findFormatOverrides.getShape().ordinal()] != 1) {
            return this;
        }
        if (handledType() == BigDecimal.class) {
            return bigDecimalAsStringSerializer();
        }
        return ToStringSerializer.instance;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(Number number, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (number instanceof BigDecimal) {
            jsonGenerator.writeNumber((BigDecimal) number);
            return;
        }
        if (number instanceof BigInteger) {
            jsonGenerator.writeNumber((BigInteger) number);
            return;
        }
        if (number instanceof Long) {
            jsonGenerator.writeNumber(number.longValue());
            return;
        }
        if (number instanceof Double) {
            jsonGenerator.writeNumber(number.doubleValue());
            return;
        }
        if (number instanceof Float) {
            jsonGenerator.writeNumber(number.floatValue());
        } else if (!(number instanceof Integer) && !(number instanceof Byte) && !(number instanceof Short)) {
            jsonGenerator.writeNumber(number.toString());
        } else {
            jsonGenerator.writeNumber(number.intValue());
        }
    }
}
