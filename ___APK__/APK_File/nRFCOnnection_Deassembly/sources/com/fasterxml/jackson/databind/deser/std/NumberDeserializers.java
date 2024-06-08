package com.fasterxml.jackson.databind.deser.std;

import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;

/* loaded from: classes.dex */
public class NumberDeserializers {
    private static final HashSet<String> _classNames = new HashSet<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.fasterxml.jackson.databind.deser.std.NumberDeserializers$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType = new int[JsonParser.NumberType.values().length];

        static {
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[JsonParser.NumberType.INT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[JsonParser.NumberType.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[JsonParser.NumberType.BIG_INTEGER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public static class BigDecimalDeserializer extends StdScalarDeserializer<BigDecimal> {
        public static final BigDecimalDeserializer instance = new BigDecimalDeserializer();

        public BigDecimalDeserializer() {
            super((Class<?>) BigDecimal.class);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Object getEmptyValue(DeserializationContext deserializationContext) {
            return BigDecimal.ZERO;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int currentTokenId = jsonParser.getCurrentTokenId();
            if (currentTokenId == 3) {
                return _deserializeFromArray(jsonParser, deserializationContext);
            }
            if (currentTokenId != 6) {
                if (currentTokenId != 7 && currentTokenId != 8) {
                    return (BigDecimal) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
                }
                return jsonParser.getDecimalValue();
            }
            String trim = jsonParser.getText().trim();
            if (_isEmptyOrTextualNull(trim)) {
                _verifyNullForScalarCoercion(deserializationContext, trim);
                return getNullValue(deserializationContext);
            }
            _verifyStringForScalarCoercion(deserializationContext, trim);
            try {
                return new BigDecimal(trim);
            } catch (IllegalArgumentException unused) {
                return (BigDecimal) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "not a valid representation", new Object[0]);
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public static class BigIntegerDeserializer extends StdScalarDeserializer<BigInteger> {
        public static final BigIntegerDeserializer instance = new BigIntegerDeserializer();

        public BigIntegerDeserializer() {
            super((Class<?>) BigInteger.class);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Object getEmptyValue(DeserializationContext deserializationContext) {
            return BigInteger.ZERO;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public BigInteger deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int currentTokenId = jsonParser.getCurrentTokenId();
            if (currentTokenId == 3) {
                return _deserializeFromArray(jsonParser, deserializationContext);
            }
            if (currentTokenId != 6) {
                if (currentTokenId == 7) {
                    int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[jsonParser.getNumberType().ordinal()];
                    if (i == 1 || i == 2 || i == 3) {
                        return jsonParser.getBigIntegerValue();
                    }
                } else if (currentTokenId == 8) {
                    if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
                        return jsonParser.getDecimalValue().toBigInteger();
                    }
                    _failDoubleToIntCoercion(jsonParser, deserializationContext, "java.math.BigInteger");
                    throw null;
                }
                return (BigInteger) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
            }
            String trim = jsonParser.getText().trim();
            if (_isEmptyOrTextualNull(trim)) {
                _verifyNullForScalarCoercion(deserializationContext, trim);
                return getNullValue(deserializationContext);
            }
            _verifyStringForScalarCoercion(deserializationContext, trim);
            try {
                return new BigInteger(trim);
            } catch (IllegalArgumentException unused) {
                return (BigInteger) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "not a valid representation", new Object[0]);
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public static final class BooleanDeserializer extends PrimitiveOrWrapperDeserializer<Boolean> {
        static final BooleanDeserializer primitiveInstance = new BooleanDeserializer(Boolean.TYPE, Boolean.FALSE);
        static final BooleanDeserializer wrapperInstance = new BooleanDeserializer(Boolean.class, null);

        public BooleanDeserializer(Class<Boolean> cls, Boolean bool) {
            super(cls, bool, Boolean.FALSE);
        }

        protected final Boolean _parseBoolean(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NULL) {
                return (Boolean) _coerceNullToken(deserializationContext, this._primitive);
            }
            if (currentToken == JsonToken.START_ARRAY) {
                return _deserializeFromArray(jsonParser, deserializationContext);
            }
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                return Boolean.valueOf(_parseBooleanFromInt(jsonParser, deserializationContext));
            }
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if (!"true".equals(trim) && !"True".equals(trim)) {
                    if (!"false".equals(trim) && !"False".equals(trim)) {
                        if (trim.length() == 0) {
                            return (Boolean) _coerceEmptyString(deserializationContext, this._primitive);
                        }
                        if (_hasTextualNull(trim)) {
                            return (Boolean) _coerceTextualNull(deserializationContext, this._primitive);
                        }
                        return (Boolean) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "only \"true\" or \"false\" recognized", new Object[0]);
                    }
                    _verifyStringForScalarCoercion(deserializationContext, trim);
                    return Boolean.FALSE;
                }
                _verifyStringForScalarCoercion(deserializationContext, trim);
                return Boolean.TRUE;
            }
            if (currentToken == JsonToken.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (currentToken == JsonToken.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            return (Boolean) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (currentToken == JsonToken.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            return _parseBoolean(jsonParser, deserializationContext);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
        public Boolean deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (currentToken == JsonToken.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            return _parseBoolean(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public static class ByteDeserializer extends PrimitiveOrWrapperDeserializer<Byte> {
        static final ByteDeserializer primitiveInstance = new ByteDeserializer(Byte.TYPE, (byte) 0);
        static final ByteDeserializer wrapperInstance = new ByteDeserializer(Byte.class, null);

        public ByteDeserializer(Class<Byte> cls, Byte b2) {
            super(cls, b2, (byte) 0);
        }

        protected Byte _parseByte(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if (_hasTextualNull(trim)) {
                    return (Byte) _coerceTextualNull(deserializationContext, this._primitive);
                }
                if (trim.length() == 0) {
                    return (Byte) _coerceEmptyString(deserializationContext, this._primitive);
                }
                _verifyStringForScalarCoercion(deserializationContext, trim);
                try {
                    int parseInt = NumberInput.parseInt(trim);
                    if (_byteOverflow(parseInt)) {
                        return (Byte) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "overflow, value cannot be represented as 8-bit value", new Object[0]);
                    }
                    return Byte.valueOf((byte) parseInt);
                } catch (IllegalArgumentException unused) {
                    return (Byte) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "not a valid Byte value", new Object[0]);
                }
            }
            if (currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
                    return Byte.valueOf(jsonParser.getByteValue());
                }
                _failDoubleToIntCoercion(jsonParser, deserializationContext, "Byte");
                throw null;
            }
            if (currentToken == JsonToken.VALUE_NULL) {
                return (Byte) _coerceNullToken(deserializationContext, this._primitive);
            }
            if (currentToken == JsonToken.START_ARRAY) {
                return _deserializeFromArray(jsonParser, deserializationContext);
            }
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                return Byte.valueOf(jsonParser.getByteValue());
            }
            return (Byte) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Byte deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
                return Byte.valueOf(jsonParser.getByteValue());
            }
            return _parseByte(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public static class CharacterDeserializer extends PrimitiveOrWrapperDeserializer<Character> {
        static final CharacterDeserializer primitiveInstance = new CharacterDeserializer(Character.TYPE, 0);
        static final CharacterDeserializer wrapperInstance = new CharacterDeserializer(Character.class, null);

        public CharacterDeserializer(Class<Character> cls, Character ch) {
            super(cls, ch, (char) 0);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Character deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int currentTokenId = jsonParser.getCurrentTokenId();
            if (currentTokenId == 3) {
                return _deserializeFromArray(jsonParser, deserializationContext);
            }
            if (currentTokenId != 11) {
                if (currentTokenId == 6) {
                    String text = jsonParser.getText();
                    if (text.length() == 1) {
                        return Character.valueOf(text.charAt(0));
                    }
                    if (text.length() == 0) {
                        return (Character) _coerceEmptyString(deserializationContext, this._primitive);
                    }
                } else if (currentTokenId == 7) {
                    _verifyNumberForScalarCoercion(deserializationContext, jsonParser);
                    int intValue = jsonParser.getIntValue();
                    if (intValue >= 0 && intValue <= 65535) {
                        return Character.valueOf((char) intValue);
                    }
                }
                return (Character) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
            }
            return (Character) _coerceNullToken(deserializationContext, this._primitive);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public static class DoubleDeserializer extends PrimitiveOrWrapperDeserializer<Double> {
        static final DoubleDeserializer primitiveInstance = new DoubleDeserializer(Double.TYPE, Double.valueOf(0.0d));
        static final DoubleDeserializer wrapperInstance = new DoubleDeserializer(Double.class, null);

        public DoubleDeserializer(Class<Double> cls, Double d2) {
            super(cls, d2, Double.valueOf(0.0d));
        }

        protected final Double _parseDouble(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken != JsonToken.VALUE_NUMBER_INT && currentToken != JsonToken.VALUE_NUMBER_FLOAT) {
                if (currentToken == JsonToken.VALUE_STRING) {
                    String trim = jsonParser.getText().trim();
                    if (trim.length() == 0) {
                        return (Double) _coerceEmptyString(deserializationContext, this._primitive);
                    }
                    if (_hasTextualNull(trim)) {
                        return (Double) _coerceTextualNull(deserializationContext, this._primitive);
                    }
                    char charAt = trim.charAt(0);
                    if (charAt != '-') {
                        if (charAt != 'I') {
                            if (charAt == 'N' && _isNaN(trim)) {
                                return Double.valueOf(Double.NaN);
                            }
                        } else if (_isPosInf(trim)) {
                            return Double.valueOf(Double.POSITIVE_INFINITY);
                        }
                    } else if (_isNegInf(trim)) {
                        return Double.valueOf(Double.NEGATIVE_INFINITY);
                    }
                    _verifyStringForScalarCoercion(deserializationContext, trim);
                    try {
                        return Double.valueOf(StdDeserializer.parseDouble(trim));
                    } catch (IllegalArgumentException unused) {
                        return (Double) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "not a valid Double value", new Object[0]);
                    }
                }
                if (currentToken == JsonToken.VALUE_NULL) {
                    return (Double) _coerceNullToken(deserializationContext, this._primitive);
                }
                if (currentToken == JsonToken.START_ARRAY) {
                    return _deserializeFromArray(jsonParser, deserializationContext);
                }
                return (Double) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
            }
            return Double.valueOf(jsonParser.getDoubleValue());
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseDouble(jsonParser, deserializationContext);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
        public Double deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            return _parseDouble(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public static class FloatDeserializer extends PrimitiveOrWrapperDeserializer<Float> {
        static final FloatDeserializer primitiveInstance = new FloatDeserializer(Float.TYPE, Float.valueOf(0.0f));
        static final FloatDeserializer wrapperInstance = new FloatDeserializer(Float.class, null);

        public FloatDeserializer(Class<Float> cls, Float f2) {
            super(cls, f2, Float.valueOf(0.0f));
        }

        protected final Float _parseFloat(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken != JsonToken.VALUE_NUMBER_FLOAT && currentToken != JsonToken.VALUE_NUMBER_INT) {
                if (currentToken == JsonToken.VALUE_STRING) {
                    String trim = jsonParser.getText().trim();
                    if (trim.length() == 0) {
                        return (Float) _coerceEmptyString(deserializationContext, this._primitive);
                    }
                    if (_hasTextualNull(trim)) {
                        return (Float) _coerceTextualNull(deserializationContext, this._primitive);
                    }
                    char charAt = trim.charAt(0);
                    if (charAt != '-') {
                        if (charAt != 'I') {
                            if (charAt == 'N' && _isNaN(trim)) {
                                return Float.valueOf(Float.NaN);
                            }
                        } else if (_isPosInf(trim)) {
                            return Float.valueOf(Float.POSITIVE_INFINITY);
                        }
                    } else if (_isNegInf(trim)) {
                        return Float.valueOf(Float.NEGATIVE_INFINITY);
                    }
                    _verifyStringForScalarCoercion(deserializationContext, trim);
                    try {
                        return Float.valueOf(Float.parseFloat(trim));
                    } catch (IllegalArgumentException unused) {
                        return (Float) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "not a valid Float value", new Object[0]);
                    }
                }
                if (currentToken == JsonToken.VALUE_NULL) {
                    return (Float) _coerceNullToken(deserializationContext, this._primitive);
                }
                if (currentToken == JsonToken.START_ARRAY) {
                    return _deserializeFromArray(jsonParser, deserializationContext);
                }
                return (Float) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
            }
            return Float.valueOf(jsonParser.getFloatValue());
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Float deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseFloat(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public static final class IntegerDeserializer extends PrimitiveOrWrapperDeserializer<Integer> {
        static final IntegerDeserializer primitiveInstance = new IntegerDeserializer(Integer.TYPE, 0);
        static final IntegerDeserializer wrapperInstance = new IntegerDeserializer(Integer.class, null);

        public IntegerDeserializer(Class<Integer> cls, Integer num) {
            super(cls, num, 0);
        }

        protected final Integer _parseInteger(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int currentTokenId = jsonParser.getCurrentTokenId();
            if (currentTokenId == 3) {
                return _deserializeFromArray(jsonParser, deserializationContext);
            }
            if (currentTokenId == 11) {
                return (Integer) _coerceNullToken(deserializationContext, this._primitive);
            }
            if (currentTokenId != 6) {
                if (currentTokenId == 7) {
                    return Integer.valueOf(jsonParser.getIntValue());
                }
                if (currentTokenId != 8) {
                    return (Integer) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
                }
                if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
                    return Integer.valueOf(jsonParser.getValueAsInt());
                }
                _failDoubleToIntCoercion(jsonParser, deserializationContext, "Integer");
                throw null;
            }
            String trim = jsonParser.getText().trim();
            int length = trim.length();
            if (length == 0) {
                return (Integer) _coerceEmptyString(deserializationContext, this._primitive);
            }
            if (_hasTextualNull(trim)) {
                return (Integer) _coerceTextualNull(deserializationContext, this._primitive);
            }
            _verifyStringForScalarCoercion(deserializationContext, trim);
            try {
                if (length > 9) {
                    long parseLong = Long.parseLong(trim);
                    if (_intOverflow(parseLong)) {
                        return (Integer) deserializationContext.handleWeirdStringValue(this._valueClass, trim, String.format("Overflow: numeric value (%s) out of range of Integer (%d - %d)", trim, Integer.valueOf(RecyclerView.UNDEFINED_DURATION), Integer.valueOf(Preference.DEFAULT_ORDER)), new Object[0]);
                    }
                    return Integer.valueOf((int) parseLong);
                }
                return Integer.valueOf(NumberInput.parseInt(trim));
            } catch (IllegalArgumentException unused) {
                return (Integer) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "not a valid Integer value", new Object[0]);
            }
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public boolean isCachable() {
            return true;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
                return Integer.valueOf(jsonParser.getIntValue());
            }
            return _parseInteger(jsonParser, deserializationContext);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
        public Integer deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
                return Integer.valueOf(jsonParser.getIntValue());
            }
            return _parseInteger(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public static final class LongDeserializer extends PrimitiveOrWrapperDeserializer<Long> {
        static final LongDeserializer primitiveInstance = new LongDeserializer(Long.TYPE, 0L);
        static final LongDeserializer wrapperInstance = new LongDeserializer(Long.class, null);

        public LongDeserializer(Class<Long> cls, Long l) {
            super(cls, l, 0L);
        }

        protected final Long _parseLong(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int currentTokenId = jsonParser.getCurrentTokenId();
            if (currentTokenId == 3) {
                return _deserializeFromArray(jsonParser, deserializationContext);
            }
            if (currentTokenId == 11) {
                return (Long) _coerceNullToken(deserializationContext, this._primitive);
            }
            if (currentTokenId != 6) {
                if (currentTokenId == 7) {
                    return Long.valueOf(jsonParser.getLongValue());
                }
                if (currentTokenId != 8) {
                    return (Long) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
                }
                if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
                    return Long.valueOf(jsonParser.getValueAsLong());
                }
                _failDoubleToIntCoercion(jsonParser, deserializationContext, "Long");
                throw null;
            }
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return (Long) _coerceEmptyString(deserializationContext, this._primitive);
            }
            if (_hasTextualNull(trim)) {
                return (Long) _coerceTextualNull(deserializationContext, this._primitive);
            }
            _verifyStringForScalarCoercion(deserializationContext, trim);
            try {
                return Long.valueOf(NumberInput.parseLong(trim));
            } catch (IllegalArgumentException unused) {
                return (Long) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "not a valid Long value", new Object[0]);
            }
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public boolean isCachable() {
            return true;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
                return Long.valueOf(jsonParser.getLongValue());
            }
            return _parseLong(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public static class NumberDeserializer extends StdScalarDeserializer<Object> {
        public static final NumberDeserializer instance = new NumberDeserializer();

        public NumberDeserializer() {
            super((Class<?>) Number.class);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int currentTokenId = jsonParser.getCurrentTokenId();
            if (currentTokenId == 3) {
                return _deserializeFromArray(jsonParser, deserializationContext);
            }
            if (currentTokenId != 6) {
                if (currentTokenId == 7) {
                    if (deserializationContext.hasSomeOfFeatures(StdDeserializer.F_MASK_INT_COERCIONS)) {
                        return _coerceIntegral(jsonParser, deserializationContext);
                    }
                    return jsonParser.getNumberValue();
                }
                if (currentTokenId != 8) {
                    return deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
                }
                if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS) && !jsonParser.isNaN()) {
                    return jsonParser.getDecimalValue();
                }
                return jsonParser.getNumberValue();
            }
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return getNullValue(deserializationContext);
            }
            if (_hasTextualNull(trim)) {
                return getNullValue(deserializationContext);
            }
            if (_isPosInf(trim)) {
                return Double.valueOf(Double.POSITIVE_INFINITY);
            }
            if (_isNegInf(trim)) {
                return Double.valueOf(Double.NEGATIVE_INFINITY);
            }
            if (_isNaN(trim)) {
                return Double.valueOf(Double.NaN);
            }
            _verifyStringForScalarCoercion(deserializationContext, trim);
            try {
                if (!_isIntNumber(trim)) {
                    if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return new BigDecimal(trim);
                    }
                    return Double.valueOf(trim);
                }
                if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                    return new BigInteger(trim);
                }
                long parseLong = Long.parseLong(trim);
                if (!deserializationContext.isEnabled(DeserializationFeature.USE_LONG_FOR_INTS) && parseLong <= 2147483647L && parseLong >= -2147483648L) {
                    return Integer.valueOf((int) parseLong);
                }
                return Long.valueOf(parseLong);
            } catch (IllegalArgumentException unused) {
                return deserializationContext.handleWeirdStringValue(this._valueClass, trim, "not a valid number", new Object[0]);
            }
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
        public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            int currentTokenId = jsonParser.getCurrentTokenId();
            if (currentTokenId != 6 && currentTokenId != 7 && currentTokenId != 8) {
                return typeDeserializer.deserializeTypedFromScalar(jsonParser, deserializationContext);
            }
            return deserialize(jsonParser, deserializationContext);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static abstract class PrimitiveOrWrapperDeserializer<T> extends StdScalarDeserializer<T> {
        protected final T _emptyValue;
        protected final T _nullValue;
        protected final boolean _primitive;

        protected PrimitiveOrWrapperDeserializer(Class<T> cls, T t, T t2) {
            super((Class<?>) cls);
            this._nullValue = t;
            this._emptyValue = t2;
            this._primitive = cls.isPrimitive();
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Object getEmptyValue(DeserializationContext deserializationContext) {
            return this._emptyValue;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.NullValueProvider
        public final T getNullValue(DeserializationContext deserializationContext) {
            if (this._primitive && deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
                deserializationContext.reportInputMismatch(this, "Cannot map `null` into type %s (set DeserializationConfig.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)", handledType().toString());
                throw null;
            }
            return this._nullValue;
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public static class ShortDeserializer extends PrimitiveOrWrapperDeserializer<Short> {
        static final ShortDeserializer primitiveInstance = new ShortDeserializer(Short.TYPE, 0);
        static final ShortDeserializer wrapperInstance = new ShortDeserializer(Short.class, null);

        public ShortDeserializer(Class<Short> cls, Short sh) {
            super(cls, sh, (short) 0);
        }

        protected Short _parseShort(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                return Short.valueOf(jsonParser.getShortValue());
            }
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if (trim.length() == 0) {
                    return (Short) _coerceEmptyString(deserializationContext, this._primitive);
                }
                if (_hasTextualNull(trim)) {
                    return (Short) _coerceTextualNull(deserializationContext, this._primitive);
                }
                _verifyStringForScalarCoercion(deserializationContext, trim);
                try {
                    int parseInt = NumberInput.parseInt(trim);
                    if (_shortOverflow(parseInt)) {
                        return (Short) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "overflow, value cannot be represented as 16-bit value", new Object[0]);
                    }
                    return Short.valueOf((short) parseInt);
                } catch (IllegalArgumentException unused) {
                    return (Short) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "not a valid Short value", new Object[0]);
                }
            }
            if (currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
                    return Short.valueOf(jsonParser.getShortValue());
                }
                _failDoubleToIntCoercion(jsonParser, deserializationContext, "Short");
                throw null;
            }
            if (currentToken == JsonToken.VALUE_NULL) {
                return (Short) _coerceNullToken(deserializationContext, this._primitive);
            }
            if (currentToken == JsonToken.START_ARRAY) {
                return _deserializeFromArray(jsonParser, deserializationContext);
            }
            return (Short) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Short deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseShort(jsonParser, deserializationContext);
        }
    }

    static {
        for (Class cls : new Class[]{Boolean.class, Byte.class, Short.class, Character.class, Integer.class, Long.class, Float.class, Double.class, Number.class, BigDecimal.class, BigInteger.class}) {
            _classNames.add(cls.getName());
        }
    }

    public static JsonDeserializer<?> find(Class<?> cls, String str) {
        if (cls.isPrimitive()) {
            if (cls == Integer.TYPE) {
                return IntegerDeserializer.primitiveInstance;
            }
            if (cls == Boolean.TYPE) {
                return BooleanDeserializer.primitiveInstance;
            }
            if (cls == Long.TYPE) {
                return LongDeserializer.primitiveInstance;
            }
            if (cls == Double.TYPE) {
                return DoubleDeserializer.primitiveInstance;
            }
            if (cls == Character.TYPE) {
                return CharacterDeserializer.primitiveInstance;
            }
            if (cls == Byte.TYPE) {
                return ByteDeserializer.primitiveInstance;
            }
            if (cls == Short.TYPE) {
                return ShortDeserializer.primitiveInstance;
            }
            if (cls == Float.TYPE) {
                return FloatDeserializer.primitiveInstance;
            }
        } else {
            if (!_classNames.contains(str)) {
                return null;
            }
            if (cls == Integer.class) {
                return IntegerDeserializer.wrapperInstance;
            }
            if (cls == Boolean.class) {
                return BooleanDeserializer.wrapperInstance;
            }
            if (cls == Long.class) {
                return LongDeserializer.wrapperInstance;
            }
            if (cls == Double.class) {
                return DoubleDeserializer.wrapperInstance;
            }
            if (cls == Character.class) {
                return CharacterDeserializer.wrapperInstance;
            }
            if (cls == Byte.class) {
                return ByteDeserializer.wrapperInstance;
            }
            if (cls == Short.class) {
                return ShortDeserializer.wrapperInstance;
            }
            if (cls == Float.class) {
                return FloatDeserializer.wrapperInstance;
            }
            if (cls == Number.class) {
                return NumberDeserializer.instance;
            }
            if (cls == BigDecimal.class) {
                return BigDecimalDeserializer.instance;
            }
            if (cls == BigInteger.class) {
                return BigIntegerDeserializer.instance;
            }
        }
        throw new IllegalArgumentException("Internal error: can't find deserializer for " + cls.getName());
    }
}
