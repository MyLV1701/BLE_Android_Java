package com.fasterxml.jackson.databind.deser.std;

import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.impl.NullsAsEmptyProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsFailProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class StdDeserializer<T> extends JsonDeserializer<T> implements Serializable {
    protected final Class<?> _valueClass;
    protected final JavaType _valueType;
    protected static final int F_MASK_INT_COERCIONS = DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.getMask() | DeserializationFeature.USE_LONG_FOR_INTS.getMask();
    protected static final int F_MASK_ACCEPT_ARRAYS = DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS.getMask() | DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT.getMask();

    /* JADX INFO: Access modifiers changed from: protected */
    public StdDeserializer(Class<?> cls) {
        this._valueClass = cls;
        this._valueType = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final boolean _neitherNull(Object obj, Object obj2) {
        return (obj == null || obj2 == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final double parseDouble(String str) {
        if ("2.2250738585072012e-308".equals(str)) {
            return Double.MIN_NORMAL;
        }
        return Double.parseDouble(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean _byteOverflow(int i) {
        return i < -128 || i > 255;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object _coerceEmptyString(DeserializationContext deserializationContext, boolean z) {
        Enum<?> r4;
        boolean z2;
        if (deserializationContext.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
            if (z && deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
                r4 = DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;
                z2 = false;
            } else {
                return getNullValue(deserializationContext);
            }
        } else {
            r4 = MapperFeature.ALLOW_COERCION_OF_SCALARS;
            z2 = true;
        }
        _reportFailedNullCoerce(deserializationContext, z2, r4, "empty String (\"\")");
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object _coerceIntegral(JsonParser jsonParser, DeserializationContext deserializationContext) {
        int deserializationFeatures = deserializationContext.getDeserializationFeatures();
        if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(deserializationFeatures)) {
            return jsonParser.getBigIntegerValue();
        }
        if (DeserializationFeature.USE_LONG_FOR_INTS.enabledIn(deserializationFeatures)) {
            return Long.valueOf(jsonParser.getLongValue());
        }
        return jsonParser.getBigIntegerValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object _coerceNullToken(DeserializationContext deserializationContext, boolean z) {
        if (z) {
            _verifyNullForPrimitive(deserializationContext);
        }
        return getNullValue(deserializationContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object _coerceTextualNull(DeserializationContext deserializationContext, boolean z) {
        Enum<?> r4;
        boolean z2;
        if (deserializationContext.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
            if (z && deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
                r4 = DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;
                z2 = false;
            } else {
                return getNullValue(deserializationContext);
            }
        } else {
            r4 = MapperFeature.ALLOW_COERCION_OF_SCALARS;
            z2 = true;
        }
        _reportFailedNullCoerce(deserializationContext, z2, r4, "String \"null\"");
        throw null;
    }

    protected String _coercedTypeDesc() {
        boolean z;
        String nameOf;
        JavaType valueType = getValueType();
        if (valueType != null && !valueType.isPrimitive()) {
            z = valueType.isContainerType() || valueType.isReferenceType();
            nameOf = "'" + valueType.toString() + "'";
        } else {
            Class<?> handledType = handledType();
            z = handledType.isArray() || Collection.class.isAssignableFrom(handledType) || Map.class.isAssignableFrom(handledType);
            nameOf = ClassUtil.nameOf(handledType);
        }
        if (z) {
            return "as content of type " + nameOf;
        }
        return "for type " + nameOf;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T _deserializeFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (deserializationContext.hasSomeOfFeatures(F_MASK_ACCEPT_ARRAYS)) {
            if (jsonParser.nextToken() == JsonToken.END_ARRAY && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)) {
                return getNullValue(deserializationContext);
            }
            if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                T deserialize = deserialize(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return deserialize;
                }
                handleMissingEndArrayForSingle(jsonParser, deserializationContext);
                throw null;
            }
        } else {
            jsonParser.getCurrentToken();
        }
        return (T) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser.getCurrentToken(), jsonParser, (String) null, new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T _deserializeFromEmpty(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_ARRAY) {
            if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)) {
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return null;
                }
                return (T) deserializationContext.handleUnexpectedToken(handledType(), jsonParser);
            }
        } else if (currentToken == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().trim().isEmpty()) {
            return null;
        }
        return (T) deserializationContext.handleUnexpectedToken(handledType(), jsonParser);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _failDoubleToIntCoercion(JsonParser jsonParser, DeserializationContext deserializationContext, String str) {
        deserializationContext.reportInputMismatch(handledType(), "Cannot coerce a floating-point value ('%s') into %s (enable `DeserializationFeature.ACCEPT_FLOAT_AS_INT` to allow)", jsonParser.getValueAsString(), str);
        throw null;
    }

    protected final NullValueProvider _findNullProvider(DeserializationContext deserializationContext, BeanProperty beanProperty, Nulls nulls, JsonDeserializer<?> jsonDeserializer) {
        if (nulls == Nulls.FAIL) {
            if (beanProperty == null) {
                return NullsFailProvider.constructForRootValue(deserializationContext.constructType(jsonDeserializer.handledType()));
            }
            return NullsFailProvider.constructForProperty(beanProperty);
        }
        if (nulls != Nulls.AS_EMPTY) {
            if (nulls == Nulls.SKIP) {
                return NullsConstantProvider.skipper();
            }
            return null;
        }
        if (jsonDeserializer == null) {
            return null;
        }
        if ((jsonDeserializer instanceof BeanDeserializerBase) && !((BeanDeserializerBase) jsonDeserializer).getValueInstantiator().canCreateUsingDefault()) {
            JavaType type = beanProperty.getType();
            deserializationContext.reportBadDefinition(type, String.format("Cannot create empty instance of %s, no default Creator", type));
            throw null;
        }
        AccessPattern emptyAccessPattern = jsonDeserializer.getEmptyAccessPattern();
        if (emptyAccessPattern == AccessPattern.ALWAYS_NULL) {
            return NullsConstantProvider.nuller();
        }
        if (emptyAccessPattern == AccessPattern.CONSTANT) {
            return NullsConstantProvider.forValue(jsonDeserializer.getEmptyValue(deserializationContext));
        }
        return new NullsAsEmptyProvider(jsonDeserializer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean _hasTextualNull(String str) {
        return "null".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean _intOverflow(long j) {
        return j < -2147483648L || j > 2147483647L;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean _isEmptyOrTextualNull(String str) {
        return str.isEmpty() || "null".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean _isIntNumber(String str) {
        int length = str.length();
        if (length <= 0) {
            return false;
        }
        char charAt = str.charAt(0);
        for (int i = (charAt == '-' || charAt == '+') ? 1 : 0; i < length; i++) {
            char charAt2 = str.charAt(i);
            if (charAt2 > '9' || charAt2 < '0') {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean _isNaN(String str) {
        return "NaN".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean _isNegInf(String str) {
        return "-Infinity".equals(str) || "-INF".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean _isPosInf(String str) {
        return "Infinity".equals(str) || "INF".equals(str);
    }

    protected Number _nonNullNumber(Number number) {
        if (number == null) {
            return 0;
        }
        return number;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean _parseBooleanFromInt(JsonParser jsonParser, DeserializationContext deserializationContext) {
        _verifyNumberForScalarCoercion(deserializationContext, jsonParser);
        return !"0".equals(jsonParser.getText());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean _parseBooleanPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_TRUE) {
            return true;
        }
        if (currentToken == JsonToken.VALUE_FALSE) {
            return false;
        }
        if (currentToken == JsonToken.VALUE_NULL) {
            _verifyNullForPrimitive(deserializationContext);
            return false;
        }
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            return _parseBooleanFromInt(jsonParser, deserializationContext);
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if ("true".equals(trim) || "True".equals(trim)) {
                return true;
            }
            if ("false".equals(trim) || "False".equals(trim)) {
                return false;
            }
            if (_isEmptyOrTextualNull(trim)) {
                _verifyNullForPrimitiveCoercion(deserializationContext, trim);
                return false;
            }
            return Boolean.TRUE.equals((Boolean) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "only \"true\" or \"false\" recognized", new Object[0]));
        }
        if (currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            jsonParser.nextToken();
            boolean _parseBooleanPrimitive = _parseBooleanPrimitive(jsonParser, deserializationContext);
            _verifyEndArrayForSingle(jsonParser, deserializationContext);
            return _parseBooleanPrimitive;
        }
        return ((Boolean) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser)).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final byte _parseBytePrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
        return _byteOverflow(_parseIntPrimitive) ? _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(this._valueClass, String.valueOf(_parseIntPrimitive), "overflow, value cannot be represented as 8-bit value", new Object[0])).byteValue() : (byte) _parseIntPrimitive;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Date _parseDate(JsonParser jsonParser, DeserializationContext deserializationContext) {
        long longValue;
        int currentTokenId = jsonParser.getCurrentTokenId();
        if (currentTokenId == 3) {
            return _parseDateFromArray(jsonParser, deserializationContext);
        }
        if (currentTokenId == 11) {
            return (Date) getNullValue(deserializationContext);
        }
        if (currentTokenId == 6) {
            return _parseDate(jsonParser.getText().trim(), deserializationContext);
        }
        if (currentTokenId != 7) {
            return (Date) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
        }
        try {
            longValue = jsonParser.getLongValue();
        } catch (JsonParseException | InputCoercionException unused) {
            longValue = ((Number) deserializationContext.handleWeirdNumberValue(this._valueClass, jsonParser.getNumberValue(), "not a valid 64-bit long for creating `java.util.Date`", new Object[0])).longValue();
        }
        return new Date(longValue);
    }

    protected Date _parseDateFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken;
        if (deserializationContext.hasSomeOfFeatures(F_MASK_ACCEPT_ARRAYS)) {
            currentToken = jsonParser.nextToken();
            if (currentToken == JsonToken.END_ARRAY && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)) {
                return (Date) getNullValue(deserializationContext);
            }
            if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                Date _parseDate = _parseDate(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseDate;
            }
        } else {
            currentToken = jsonParser.getCurrentToken();
        }
        return (Date) deserializationContext.handleUnexpectedToken(this._valueClass, currentToken, jsonParser, (String) null, new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final double _parseDoublePrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_FLOAT)) {
            return jsonParser.getDoubleValue();
        }
        int currentTokenId = jsonParser.getCurrentTokenId();
        if (currentTokenId != 3) {
            if (currentTokenId == 11) {
                _verifyNullForPrimitive(deserializationContext);
                return 0.0d;
            }
            if (currentTokenId == 6) {
                String trim = jsonParser.getText().trim();
                if (_isEmptyOrTextualNull(trim)) {
                    _verifyNullForPrimitiveCoercion(deserializationContext, trim);
                    return 0.0d;
                }
                return _parseDoublePrimitive(deserializationContext, trim);
            }
            if (currentTokenId == 7) {
                return jsonParser.getDoubleValue();
            }
        } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            jsonParser.nextToken();
            double _parseDoublePrimitive = _parseDoublePrimitive(jsonParser, deserializationContext);
            _verifyEndArrayForSingle(jsonParser, deserializationContext);
            return _parseDoublePrimitive;
        }
        return ((Number) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser)).doubleValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float _parseFloatPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_FLOAT)) {
            return jsonParser.getFloatValue();
        }
        int currentTokenId = jsonParser.getCurrentTokenId();
        if (currentTokenId != 3) {
            if (currentTokenId == 11) {
                _verifyNullForPrimitive(deserializationContext);
                return 0.0f;
            }
            if (currentTokenId == 6) {
                String trim = jsonParser.getText().trim();
                if (_isEmptyOrTextualNull(trim)) {
                    _verifyNullForPrimitiveCoercion(deserializationContext, trim);
                    return 0.0f;
                }
                return _parseFloatPrimitive(deserializationContext, trim);
            }
            if (currentTokenId == 7) {
                return jsonParser.getFloatValue();
            }
        } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            jsonParser.nextToken();
            float _parseFloatPrimitive = _parseFloatPrimitive(jsonParser, deserializationContext);
            _verifyEndArrayForSingle(jsonParser, deserializationContext);
            return _parseFloatPrimitive;
        }
        return ((Number) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser)).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int _parseIntPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
            return jsonParser.getIntValue();
        }
        int currentTokenId = jsonParser.getCurrentTokenId();
        if (currentTokenId != 3) {
            if (currentTokenId == 6) {
                String trim = jsonParser.getText().trim();
                if (_isEmptyOrTextualNull(trim)) {
                    _verifyNullForPrimitiveCoercion(deserializationContext, trim);
                    return 0;
                }
                return _parseIntPrimitive(deserializationContext, trim);
            }
            if (currentTokenId == 8) {
                if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
                    return jsonParser.getValueAsInt();
                }
                _failDoubleToIntCoercion(jsonParser, deserializationContext, "int");
                throw null;
            }
            if (currentTokenId == 11) {
                _verifyNullForPrimitive(deserializationContext);
                return 0;
            }
        } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            jsonParser.nextToken();
            int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
            _verifyEndArrayForSingle(jsonParser, deserializationContext);
            return _parseIntPrimitive;
        }
        return ((Number) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser)).intValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long _parseLongPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
            return jsonParser.getLongValue();
        }
        int currentTokenId = jsonParser.getCurrentTokenId();
        if (currentTokenId != 3) {
            if (currentTokenId == 6) {
                String trim = jsonParser.getText().trim();
                if (_isEmptyOrTextualNull(trim)) {
                    _verifyNullForPrimitiveCoercion(deserializationContext, trim);
                    return 0L;
                }
                return _parseLongPrimitive(deserializationContext, trim);
            }
            if (currentTokenId == 8) {
                if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
                    return jsonParser.getValueAsLong();
                }
                _failDoubleToIntCoercion(jsonParser, deserializationContext, "long");
                throw null;
            }
            if (currentTokenId == 11) {
                _verifyNullForPrimitive(deserializationContext);
                return 0L;
            }
        } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            jsonParser.nextToken();
            long _parseLongPrimitive = _parseLongPrimitive(jsonParser, deserializationContext);
            _verifyEndArrayForSingle(jsonParser, deserializationContext);
            return _parseLongPrimitive;
        }
        return ((Number) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser)).longValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final short _parseShortPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
        return _shortOverflow(_parseIntPrimitive) ? _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(this._valueClass, String.valueOf(_parseIntPrimitive), "overflow, value cannot be represented as 16-bit value", new Object[0])).shortValue() : (short) _parseIntPrimitive;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String _parseString(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
            return jsonParser.getText();
        }
        String valueAsString = jsonParser.getValueAsString();
        return valueAsString != null ? valueAsString : (String) deserializationContext.handleUnexpectedToken(String.class, jsonParser);
    }

    protected void _reportFailedNullCoerce(DeserializationContext deserializationContext, boolean z, Enum<?> r5, String str) {
        deserializationContext.reportInputMismatch(this, "Cannot coerce %s to Null value %s (%s `%s.%s` to allow)", str, _coercedTypeDesc(), z ? "enable" : "disable", r5.getClass().getSimpleName(), r5.name());
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean _shortOverflow(int i) {
        return i < -32768 || i > 32767;
    }

    protected void _verifyEndArrayForSingle(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            return;
        }
        handleMissingEndArrayForSingle(jsonParser, deserializationContext);
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _verifyNullForPrimitive(DeserializationContext deserializationContext) {
        if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            deserializationContext.reportInputMismatch(this, "Cannot coerce `null` %s (disable `DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES` to allow)", _coercedTypeDesc());
            throw null;
        }
    }

    protected final void _verifyNullForPrimitiveCoercion(DeserializationContext deserializationContext, String str) {
        Enum<?> r3;
        boolean z;
        if (!deserializationContext.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
            r3 = MapperFeature.ALLOW_COERCION_OF_SCALARS;
            z = true;
        } else {
            if (!deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
                return;
            }
            r3 = DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;
            z = false;
        }
        _reportFailedNullCoerce(deserializationContext, z, r3, str.isEmpty() ? "empty String (\"\")" : String.format("String \"%s\"", str));
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _verifyNullForScalarCoercion(DeserializationContext deserializationContext, String str) {
        if (deserializationContext.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
            return;
        }
        _reportFailedNullCoerce(deserializationContext, true, MapperFeature.ALLOW_COERCION_OF_SCALARS, str.isEmpty() ? "empty String (\"\")" : String.format("String \"%s\"", str));
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _verifyNumberForScalarCoercion(DeserializationContext deserializationContext, JsonParser jsonParser) {
        MapperFeature mapperFeature = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (deserializationContext.isEnabled(mapperFeature)) {
            return;
        }
        deserializationContext.reportInputMismatch(this, "Cannot coerce Number (%s) %s (enable `%s.%s` to allow)", jsonParser.getText(), _coercedTypeDesc(), mapperFeature.getClass().getSimpleName(), mapperFeature.name());
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _verifyStringForScalarCoercion(DeserializationContext deserializationContext, String str) {
        MapperFeature mapperFeature = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (deserializationContext.isEnabled(mapperFeature)) {
            return;
        }
        deserializationContext.reportInputMismatch(this, "Cannot coerce String \"%s\" %s (enable `%s.%s` to allow)", str, _coercedTypeDesc(), mapperFeature.getClass().getSimpleName(), mapperFeature.name());
        throw null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public NullValueProvider findContentNullProvider(DeserializationContext deserializationContext, BeanProperty beanProperty, JsonDeserializer<?> jsonDeserializer) {
        Nulls findContentNullStyle = findContentNullStyle(deserializationContext, beanProperty);
        if (findContentNullStyle == Nulls.SKIP) {
            return NullsConstantProvider.skipper();
        }
        NullValueProvider _findNullProvider = _findNullProvider(deserializationContext, beanProperty, findContentNullStyle, jsonDeserializer);
        return _findNullProvider != null ? _findNullProvider : jsonDeserializer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Nulls findContentNullStyle(DeserializationContext deserializationContext, BeanProperty beanProperty) {
        if (beanProperty != null) {
            return beanProperty.getMetadata().getContentNulls();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonDeserializer<?> findConvertingContentDeserializer(DeserializationContext deserializationContext, BeanProperty beanProperty, JsonDeserializer<?> jsonDeserializer) {
        AnnotatedMember member;
        Object findDeserializationContentConverter;
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (!_neitherNull(annotationIntrospector, beanProperty) || (member = beanProperty.getMember()) == null || (findDeserializationContentConverter = annotationIntrospector.findDeserializationContentConverter(member)) == null) {
            return jsonDeserializer;
        }
        Converter<Object, Object> converterInstance = deserializationContext.converterInstance(beanProperty.getMember(), findDeserializationContentConverter);
        JavaType inputType = converterInstance.getInputType(deserializationContext.getTypeFactory());
        if (jsonDeserializer == null) {
            jsonDeserializer = deserializationContext.findContextualValueDeserializer(inputType, beanProperty);
        }
        return new StdDelegatingDeserializer(converterInstance, inputType, jsonDeserializer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonDeserializer<Object> findDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanProperty beanProperty) {
        return deserializationContext.findContextualValueDeserializer(javaType, beanProperty);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Boolean findFormatFeature(DeserializationContext deserializationContext, BeanProperty beanProperty, Class<?> cls, JsonFormat.Feature feature) {
        JsonFormat.Value findFormatOverrides = findFormatOverrides(deserializationContext, beanProperty, cls);
        if (findFormatOverrides != null) {
            return findFormatOverrides.getFeature(feature);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonFormat.Value findFormatOverrides(DeserializationContext deserializationContext, BeanProperty beanProperty, Class<?> cls) {
        if (beanProperty != null) {
            return beanProperty.findPropertyFormat(deserializationContext.getConfig(), cls);
        }
        return deserializationContext.getDefaultPropertyFormat(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final NullValueProvider findValueNullProvider(DeserializationContext deserializationContext, SettableBeanProperty settableBeanProperty, PropertyMetadata propertyMetadata) {
        if (settableBeanProperty != null) {
            return _findNullProvider(deserializationContext, settableBeanProperty, propertyMetadata.getValueNulls(), settableBeanProperty.getValueDeserializer());
        }
        return null;
    }

    public JavaType getValueType() {
        return this._valueType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleMissingEndArrayForSingle(JsonParser jsonParser, DeserializationContext deserializationContext) {
        deserializationContext.reportWrongTokenException(this, JsonToken.END_ARRAY, "Attempted to unwrap '%s' value from an array (with `DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS`) but it contains more than one value", handledType().getName());
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleUnknownProperty(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) {
        if (obj == null) {
            obj = handledType();
        }
        if (deserializationContext.handleUnknownProperty(jsonParser, this, obj, str)) {
            return;
        }
        jsonParser.skipChildren();
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Class<?> handledType() {
        return this._valueClass;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDefaultDeserializer(JsonDeserializer<?> jsonDeserializer) {
        return ClassUtil.isJacksonStdImpl(jsonDeserializer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDefaultKeyDeserializer(KeyDeserializer keyDeserializer) {
        return ClassUtil.isJacksonStdImpl(keyDeserializer);
    }

    public JavaType getValueType(DeserializationContext deserializationContext) {
        JavaType javaType = this._valueType;
        return javaType != null ? javaType : deserializationContext.constructType(this._valueClass);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StdDeserializer(JavaType javaType) {
        this._valueClass = javaType == null ? Object.class : javaType.getRawClass();
        this._valueType = javaType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StdDeserializer(StdDeserializer<?> stdDeserializer) {
        this._valueClass = stdDeserializer._valueClass;
        this._valueType = stdDeserializer._valueType;
    }

    protected Date _parseDate(String str, DeserializationContext deserializationContext) {
        try {
            if (_isEmptyOrTextualNull(str)) {
                return (Date) getNullValue(deserializationContext);
            }
            return deserializationContext.parseDate(str);
        } catch (IllegalArgumentException e2) {
            return (Date) deserializationContext.handleWeirdStringValue(this._valueClass, str, "not a valid representation (error: %s)", ClassUtil.exceptionMessage(e2));
        }
    }

    protected final double _parseDoublePrimitive(DeserializationContext deserializationContext, String str) {
        char charAt = str.charAt(0);
        if (charAt != '-') {
            if (charAt != 'I') {
                if (charAt == 'N' && _isNaN(str)) {
                    return Double.NaN;
                }
            } else if (_isPosInf(str)) {
                return Double.POSITIVE_INFINITY;
            }
        } else if (_isNegInf(str)) {
            return Double.NEGATIVE_INFINITY;
        }
        try {
            return parseDouble(str);
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(this._valueClass, str, "not a valid double value (as String to convert)", new Object[0])).doubleValue();
        }
    }

    protected final float _parseFloatPrimitive(DeserializationContext deserializationContext, String str) {
        char charAt = str.charAt(0);
        if (charAt != '-') {
            if (charAt != 'I') {
                if (charAt == 'N' && _isNaN(str)) {
                    return Float.NaN;
                }
            } else if (_isPosInf(str)) {
                return Float.POSITIVE_INFINITY;
            }
        } else if (_isNegInf(str)) {
            return Float.NEGATIVE_INFINITY;
        }
        try {
            return Float.parseFloat(str);
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(this._valueClass, str, "not a valid float value", new Object[0])).floatValue();
        }
    }

    protected final int _parseIntPrimitive(DeserializationContext deserializationContext, String str) {
        try {
            if (str.length() > 9) {
                long parseLong = Long.parseLong(str);
                return _intOverflow(parseLong) ? _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(this._valueClass, str, "Overflow: numeric value (%s) out of range of int (%d -%d)", str, Integer.valueOf(RecyclerView.UNDEFINED_DURATION), Integer.valueOf(Preference.DEFAULT_ORDER))).intValue() : (int) parseLong;
            }
            return NumberInput.parseInt(str);
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(this._valueClass, str, "not a valid int value", new Object[0])).intValue();
        }
    }

    protected final long _parseLongPrimitive(DeserializationContext deserializationContext, String str) {
        try {
            return NumberInput.parseLong(str);
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(this._valueClass, str, "not a valid long value", new Object[0])).longValue();
        }
    }
}