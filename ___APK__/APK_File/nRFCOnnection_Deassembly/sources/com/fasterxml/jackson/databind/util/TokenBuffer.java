package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.TreeMap;

/* loaded from: classes.dex */
public class TokenBuffer extends JsonGenerator {
    protected static final int DEFAULT_GENERATOR_FEATURES = JsonGenerator.Feature.collectDefaults();
    protected int _appendAt;
    protected Segment _first;
    protected boolean _forceBigDecimal;
    protected int _generatorFeatures;
    protected boolean _hasNativeId;
    protected boolean _hasNativeObjectIds;
    protected boolean _hasNativeTypeIds;
    protected Segment _last;
    protected boolean _mayHaveNativeIds;
    protected ObjectCodec _objectCodec;
    protected Object _objectId;
    protected JsonStreamContext _parentContext;
    protected Object _typeId;
    protected JsonWriteContext _writeContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.fasterxml.jackson.databind.util.TokenBuffer$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType = new int[JsonParser.NumberType.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonToken;

        static {
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[JsonParser.NumberType.INT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[JsonParser.NumberType.BIG_INTEGER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[JsonParser.NumberType.BIG_DECIMAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[JsonParser.NumberType.FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[JsonParser.NumberType.LONG.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $SwitchMap$com$fasterxml$jackson$core$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_ARRAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 9;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 10;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 11;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 12;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static final class Parser extends ParserMinimalBase {
        protected transient ByteArrayBuilder _byteBuilder;
        protected boolean _closed;
        protected ObjectCodec _codec;
        protected final boolean _hasNativeObjectIds;
        protected final boolean _hasNativeTypeIds;
        protected JsonLocation _location;
        protected TokenBufferReadContext _parsingContext;
        protected Segment _segment;
        protected int _segmentPtr;

        public Parser(Segment segment, ObjectCodec objectCodec, boolean z, boolean z2, JsonStreamContext jsonStreamContext) {
            super(0);
            this._location = null;
            this._segment = segment;
            this._segmentPtr = -1;
            this._codec = objectCodec;
            this._parsingContext = TokenBufferReadContext.createRootContext(jsonStreamContext);
            this._hasNativeTypeIds = z;
            this._hasNativeObjectIds = z2;
        }

        private final boolean _smallerThanInt(Number number) {
            return (number instanceof Short) || (number instanceof Byte);
        }

        private final boolean _smallerThanLong(Number number) {
            return (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
        }

        protected final void _checkIsNumber() {
            JsonToken jsonToken = this._currToken;
            if (jsonToken == null || !jsonToken.isNumeric()) {
                throw _constructError("Current token (" + this._currToken + ") not numeric, cannot use numeric value accessors");
            }
        }

        protected int _convertNumberToInt(Number number) {
            if (number instanceof Long) {
                long longValue = number.longValue();
                int i = (int) longValue;
                if (i == longValue) {
                    return i;
                }
                reportOverflowInt();
                throw null;
            }
            if (number instanceof BigInteger) {
                BigInteger bigInteger = (BigInteger) number;
                if (ParserMinimalBase.BI_MIN_INT.compareTo(bigInteger) > 0 || ParserMinimalBase.BI_MAX_INT.compareTo(bigInteger) < 0) {
                    reportOverflowInt();
                    throw null;
                }
            } else if (!(number instanceof Double) && !(number instanceof Float)) {
                if (number instanceof BigDecimal) {
                    BigDecimal bigDecimal = (BigDecimal) number;
                    if (ParserMinimalBase.BD_MIN_INT.compareTo(bigDecimal) > 0 || ParserMinimalBase.BD_MAX_INT.compareTo(bigDecimal) < 0) {
                        reportOverflowInt();
                        throw null;
                    }
                } else {
                    _throwInternal();
                    throw null;
                }
            } else {
                double doubleValue = number.doubleValue();
                if (doubleValue >= -2.147483648E9d && doubleValue <= 2.147483647E9d) {
                    return (int) doubleValue;
                }
                reportOverflowInt();
                throw null;
            }
            return number.intValue();
        }

        protected long _convertNumberToLong(Number number) {
            if (number instanceof BigInteger) {
                BigInteger bigInteger = (BigInteger) number;
                if (ParserMinimalBase.BI_MIN_LONG.compareTo(bigInteger) > 0 || ParserMinimalBase.BI_MAX_LONG.compareTo(bigInteger) < 0) {
                    reportOverflowLong();
                    throw null;
                }
            } else if (!(number instanceof Double) && !(number instanceof Float)) {
                if (number instanceof BigDecimal) {
                    BigDecimal bigDecimal = (BigDecimal) number;
                    if (ParserMinimalBase.BD_MIN_LONG.compareTo(bigDecimal) > 0 || ParserMinimalBase.BD_MAX_LONG.compareTo(bigDecimal) < 0) {
                        reportOverflowLong();
                        throw null;
                    }
                } else {
                    _throwInternal();
                    throw null;
                }
            } else {
                double doubleValue = number.doubleValue();
                if (doubleValue >= -9.223372036854776E18d && doubleValue <= 9.223372036854776E18d) {
                    return (long) doubleValue;
                }
                reportOverflowLong();
                throw null;
            }
            return number.longValue();
        }

        protected final Object _currentObject() {
            return this._segment.get(this._segmentPtr);
        }

        @Override // com.fasterxml.jackson.core.base.ParserMinimalBase
        protected void _handleEOF() {
            _throwInternal();
            throw null;
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public boolean canReadObjectId() {
            return this._hasNativeObjectIds;
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public boolean canReadTypeId() {
            return this._hasNativeTypeIds;
        }

        @Override // com.fasterxml.jackson.core.JsonParser, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this._closed) {
                return;
            }
            this._closed = true;
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public BigInteger getBigIntegerValue() {
            Number numberValue = getNumberValue();
            if (numberValue instanceof BigInteger) {
                return (BigInteger) numberValue;
            }
            if (getNumberType() == JsonParser.NumberType.BIG_DECIMAL) {
                return ((BigDecimal) numberValue).toBigInteger();
            }
            return BigInteger.valueOf(numberValue.longValue());
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public byte[] getBinaryValue(Base64Variant base64Variant) {
            if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object _currentObject = _currentObject();
                if (_currentObject instanceof byte[]) {
                    return (byte[]) _currentObject;
                }
            }
            if (this._currToken == JsonToken.VALUE_STRING) {
                String text = getText();
                if (text == null) {
                    return null;
                }
                ByteArrayBuilder byteArrayBuilder = this._byteBuilder;
                if (byteArrayBuilder == null) {
                    byteArrayBuilder = new ByteArrayBuilder(100);
                    this._byteBuilder = byteArrayBuilder;
                } else {
                    byteArrayBuilder.reset();
                }
                _decodeBase64(text, byteArrayBuilder, base64Variant);
                return byteArrayBuilder.toByteArray();
            }
            throw _constructError("Current token (" + this._currToken + ") not VALUE_STRING (or VALUE_EMBEDDED_OBJECT with byte[]), cannot access as binary");
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public ObjectCodec getCodec() {
            return this._codec;
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public JsonLocation getCurrentLocation() {
            JsonLocation jsonLocation = this._location;
            return jsonLocation == null ? JsonLocation.NA : jsonLocation;
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public String getCurrentName() {
            JsonToken jsonToken = this._currToken;
            if (jsonToken != JsonToken.START_OBJECT && jsonToken != JsonToken.START_ARRAY) {
                return this._parsingContext.getCurrentName();
            }
            return this._parsingContext.getParent().getCurrentName();
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public BigDecimal getDecimalValue() {
            Number numberValue = getNumberValue();
            if (numberValue instanceof BigDecimal) {
                return (BigDecimal) numberValue;
            }
            int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[getNumberType().ordinal()];
            if (i != 1) {
                if (i == 2) {
                    return new BigDecimal((BigInteger) numberValue);
                }
                if (i != 5) {
                    return BigDecimal.valueOf(numberValue.doubleValue());
                }
            }
            return BigDecimal.valueOf(numberValue.longValue());
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public double getDoubleValue() {
            return getNumberValue().doubleValue();
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public Object getEmbeddedObject() {
            if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                return _currentObject();
            }
            return null;
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public float getFloatValue() {
            return getNumberValue().floatValue();
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public int getIntValue() {
            Number numberValue = this._currToken == JsonToken.VALUE_NUMBER_INT ? (Number) _currentObject() : getNumberValue();
            if (!(numberValue instanceof Integer) && !_smallerThanInt(numberValue)) {
                return _convertNumberToInt(numberValue);
            }
            return numberValue.intValue();
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public long getLongValue() {
            Number numberValue = this._currToken == JsonToken.VALUE_NUMBER_INT ? (Number) _currentObject() : getNumberValue();
            if (!(numberValue instanceof Long) && !_smallerThanLong(numberValue)) {
                return _convertNumberToLong(numberValue);
            }
            return numberValue.longValue();
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public JsonParser.NumberType getNumberType() {
            Number numberValue = getNumberValue();
            if (numberValue instanceof Integer) {
                return JsonParser.NumberType.INT;
            }
            if (numberValue instanceof Long) {
                return JsonParser.NumberType.LONG;
            }
            if (numberValue instanceof Double) {
                return JsonParser.NumberType.DOUBLE;
            }
            if (numberValue instanceof BigDecimal) {
                return JsonParser.NumberType.BIG_DECIMAL;
            }
            if (numberValue instanceof BigInteger) {
                return JsonParser.NumberType.BIG_INTEGER;
            }
            if (numberValue instanceof Float) {
                return JsonParser.NumberType.FLOAT;
            }
            if (numberValue instanceof Short) {
                return JsonParser.NumberType.INT;
            }
            return null;
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public final Number getNumberValue() {
            _checkIsNumber();
            Object _currentObject = _currentObject();
            if (_currentObject instanceof Number) {
                return (Number) _currentObject;
            }
            if (_currentObject instanceof String) {
                String str = (String) _currentObject;
                if (str.indexOf(46) >= 0) {
                    return Double.valueOf(Double.parseDouble(str));
                }
                return Long.valueOf(Long.parseLong(str));
            }
            if (_currentObject == null) {
                return null;
            }
            throw new IllegalStateException("Internal error: entry should be a Number, but is of type " + _currentObject.getClass().getName());
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public Object getObjectId() {
            return this._segment.findObjectId(this._segmentPtr);
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public JsonStreamContext getParsingContext() {
            return this._parsingContext;
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public String getText() {
            JsonToken jsonToken = this._currToken;
            if (jsonToken == JsonToken.VALUE_STRING || jsonToken == JsonToken.FIELD_NAME) {
                Object _currentObject = _currentObject();
                if (_currentObject instanceof String) {
                    return (String) _currentObject;
                }
                return ClassUtil.nullOrToString(_currentObject);
            }
            if (jsonToken == null) {
                return null;
            }
            int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[jsonToken.ordinal()];
            if (i != 7 && i != 8) {
                return this._currToken.asString();
            }
            return ClassUtil.nullOrToString(_currentObject());
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public char[] getTextCharacters() {
            String text = getText();
            if (text == null) {
                return null;
            }
            return text.toCharArray();
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public int getTextLength() {
            String text = getText();
            if (text == null) {
                return 0;
            }
            return text.length();
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public int getTextOffset() {
            return 0;
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public JsonLocation getTokenLocation() {
            return getCurrentLocation();
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public Object getTypeId() {
            return this._segment.findTypeId(this._segmentPtr);
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public boolean hasTextCharacters() {
            return false;
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public boolean isNaN() {
            if (this._currToken != JsonToken.VALUE_NUMBER_FLOAT) {
                return false;
            }
            Object _currentObject = _currentObject();
            if (_currentObject instanceof Double) {
                Double d2 = (Double) _currentObject;
                return d2.isNaN() || d2.isInfinite();
            }
            if (!(_currentObject instanceof Float)) {
                return false;
            }
            Float f2 = (Float) _currentObject;
            return f2.isNaN() || f2.isInfinite();
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public String nextFieldName() {
            Segment segment;
            if (this._closed || (segment = this._segment) == null) {
                return null;
            }
            int i = this._segmentPtr + 1;
            if (i < 16) {
                JsonToken type = segment.type(i);
                JsonToken jsonToken = JsonToken.FIELD_NAME;
                if (type == jsonToken) {
                    this._segmentPtr = i;
                    this._currToken = jsonToken;
                    Object obj = this._segment.get(i);
                    String obj2 = obj instanceof String ? (String) obj : obj.toString();
                    this._parsingContext.setCurrentName(obj2);
                    return obj2;
                }
            }
            if (nextToken() == JsonToken.FIELD_NAME) {
                return getCurrentName();
            }
            return null;
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public JsonToken nextToken() {
            Segment segment;
            if (this._closed || (segment = this._segment) == null) {
                return null;
            }
            int i = this._segmentPtr + 1;
            this._segmentPtr = i;
            if (i >= 16) {
                this._segmentPtr = 0;
                this._segment = segment.next();
                if (this._segment == null) {
                    return null;
                }
            }
            this._currToken = this._segment.type(this._segmentPtr);
            JsonToken jsonToken = this._currToken;
            if (jsonToken == JsonToken.FIELD_NAME) {
                Object _currentObject = _currentObject();
                this._parsingContext.setCurrentName(_currentObject instanceof String ? (String) _currentObject : _currentObject.toString());
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext();
            } else if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext();
            } else if (jsonToken == JsonToken.END_OBJECT || jsonToken == JsonToken.END_ARRAY) {
                this._parsingContext = this._parsingContext.parentOrCopy();
            }
            return this._currToken;
        }

        @Override // com.fasterxml.jackson.core.JsonParser
        public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) {
            byte[] binaryValue = getBinaryValue(base64Variant);
            if (binaryValue == null) {
                return 0;
            }
            outputStream.write(binaryValue, 0, binaryValue.length);
            return binaryValue.length;
        }

        public void setLocation(JsonLocation jsonLocation) {
            this._location = jsonLocation;
        }
    }

    public TokenBuffer(ObjectCodec objectCodec, boolean z) {
        this._hasNativeId = false;
        this._objectCodec = objectCodec;
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURES;
        this._writeContext = JsonWriteContext.createRootContext(null);
        Segment segment = new Segment();
        this._last = segment;
        this._first = segment;
        this._appendAt = 0;
        this._hasNativeTypeIds = z;
        this._hasNativeObjectIds = z;
        this._mayHaveNativeIds = this._hasNativeTypeIds | this._hasNativeObjectIds;
    }

    private final void _appendNativeIds(StringBuilder sb) {
        Object findObjectId = this._last.findObjectId(this._appendAt - 1);
        if (findObjectId != null) {
            sb.append("[objectId=");
            sb.append(String.valueOf(findObjectId));
            sb.append(']');
        }
        Object findTypeId = this._last.findTypeId(this._appendAt - 1);
        if (findTypeId != null) {
            sb.append("[typeId=");
            sb.append(String.valueOf(findTypeId));
            sb.append(']');
        }
    }

    private final void _checkNativeIds(JsonParser jsonParser) {
        Object typeId = jsonParser.getTypeId();
        this._typeId = typeId;
        if (typeId != null) {
            this._hasNativeId = true;
        }
        Object objectId = jsonParser.getObjectId();
        this._objectId = objectId;
        if (objectId != null) {
            this._hasNativeId = true;
        }
    }

    private void _copyBufferValue(JsonParser jsonParser, JsonToken jsonToken) {
        if (this._mayHaveNativeIds) {
            _checkNativeIds(jsonParser);
        }
        switch (AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[jsonToken.ordinal()]) {
            case 6:
                if (jsonParser.hasTextCharacters()) {
                    writeString(jsonParser.getTextCharacters(), jsonParser.getTextOffset(), jsonParser.getTextLength());
                    return;
                } else {
                    writeString(jsonParser.getText());
                    return;
                }
            case 7:
                int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[jsonParser.getNumberType().ordinal()];
                if (i == 1) {
                    writeNumber(jsonParser.getIntValue());
                    return;
                } else if (i != 2) {
                    writeNumber(jsonParser.getLongValue());
                    return;
                } else {
                    writeNumber(jsonParser.getBigIntegerValue());
                    return;
                }
            case 8:
                if (this._forceBigDecimal) {
                    writeNumber(jsonParser.getDecimalValue());
                    return;
                }
                int i2 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[jsonParser.getNumberType().ordinal()];
                if (i2 == 3) {
                    writeNumber(jsonParser.getDecimalValue());
                    return;
                } else if (i2 != 4) {
                    writeNumber(jsonParser.getDoubleValue());
                    return;
                } else {
                    writeNumber(jsonParser.getFloatValue());
                    return;
                }
            case 9:
                writeBoolean(true);
                return;
            case 10:
                writeBoolean(false);
                return;
            case 11:
                writeNull();
                return;
            case 12:
                writeObject(jsonParser.getEmbeddedObject());
                return;
            default:
                throw new RuntimeException("Internal error: unexpected token: " + jsonToken);
        }
    }

    public static TokenBuffer asCopyOfValue(JsonParser jsonParser) {
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser);
        tokenBuffer.copyCurrentStructure(jsonParser);
        return tokenBuffer;
    }

    protected final void _appendEndMarker(JsonToken jsonToken) {
        Segment append = this._last.append(this._appendAt, jsonToken);
        if (append == null) {
            this._appendAt++;
        } else {
            this._last = append;
            this._appendAt = 1;
        }
    }

    protected final void _appendFieldName(Object obj) {
        Segment append;
        if (this._hasNativeId) {
            append = this._last.append(this._appendAt, JsonToken.FIELD_NAME, obj, this._objectId, this._typeId);
        } else {
            append = this._last.append(this._appendAt, JsonToken.FIELD_NAME, obj);
        }
        if (append == null) {
            this._appendAt++;
        } else {
            this._last = append;
            this._appendAt = 1;
        }
    }

    protected final void _appendStartMarker(JsonToken jsonToken) {
        Segment append;
        if (this._hasNativeId) {
            append = this._last.append(this._appendAt, jsonToken, this._objectId, this._typeId);
        } else {
            append = this._last.append(this._appendAt, jsonToken);
        }
        if (append == null) {
            this._appendAt++;
        } else {
            this._last = append;
            this._appendAt = 1;
        }
    }

    protected final void _appendValue(JsonToken jsonToken) {
        Segment append;
        this._writeContext.writeValue();
        if (this._hasNativeId) {
            append = this._last.append(this._appendAt, jsonToken, this._objectId, this._typeId);
        } else {
            append = this._last.append(this._appendAt, jsonToken);
        }
        if (append == null) {
            this._appendAt++;
        } else {
            this._last = append;
            this._appendAt = 1;
        }
    }

    protected void _copyBufferContents(JsonParser jsonParser) {
        int i = 1;
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == null) {
                return;
            }
            int i2 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[nextToken.ordinal()];
            if (i2 == 1) {
                if (this._mayHaveNativeIds) {
                    _checkNativeIds(jsonParser);
                }
                writeStartObject();
            } else if (i2 == 2) {
                writeEndObject();
                i--;
                if (i == 0) {
                    return;
                }
            } else if (i2 == 3) {
                if (this._mayHaveNativeIds) {
                    _checkNativeIds(jsonParser);
                }
                writeStartArray();
            } else if (i2 == 4) {
                writeEndArray();
                i--;
                if (i == 0) {
                    return;
                }
            } else if (i2 != 5) {
                _copyBufferValue(jsonParser, nextToken);
            } else {
                if (this._mayHaveNativeIds) {
                    _checkNativeIds(jsonParser);
                }
                writeFieldName(jsonParser.getCurrentName());
            }
            i++;
        }
    }

    protected void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    public TokenBuffer append(TokenBuffer tokenBuffer) {
        if (!this._hasNativeTypeIds) {
            this._hasNativeTypeIds = tokenBuffer.canWriteTypeId();
        }
        if (!this._hasNativeObjectIds) {
            this._hasNativeObjectIds = tokenBuffer.canWriteObjectId();
        }
        this._mayHaveNativeIds = this._hasNativeTypeIds | this._hasNativeObjectIds;
        JsonParser asParser = tokenBuffer.asParser();
        while (asParser.nextToken() != null) {
            copyCurrentStructure(asParser);
        }
        return this;
    }

    public JsonParser asParser() {
        return asParser(this._objectCodec);
    }

    public JsonParser asParserOnFirstToken() {
        JsonParser asParser = asParser(this._objectCodec);
        asParser.nextToken();
        return asParser;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public boolean canWriteBinaryNatively() {
        return true;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public boolean canWriteObjectId() {
        return this._hasNativeObjectIds;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public boolean canWriteTypeId() {
        return this._hasNativeTypeIds;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public void copyCurrentStructure(JsonParser jsonParser) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.FIELD_NAME) {
            if (this._mayHaveNativeIds) {
                _checkNativeIds(jsonParser);
            }
            writeFieldName(jsonParser.getCurrentName());
            currentToken = jsonParser.nextToken();
        } else if (currentToken == null) {
            throw new IllegalStateException("No token available from argument `JsonParser`");
        }
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[currentToken.ordinal()];
        if (i == 1) {
            if (this._mayHaveNativeIds) {
                _checkNativeIds(jsonParser);
            }
            writeStartObject();
            _copyBufferContents(jsonParser);
            return;
        }
        if (i == 2) {
            writeEndObject();
            return;
        }
        if (i != 3) {
            if (i != 4) {
                _copyBufferValue(jsonParser, currentToken);
                return;
            } else {
                writeEndArray();
                return;
            }
        }
        if (this._mayHaveNativeIds) {
            _checkNativeIds(jsonParser);
        }
        writeStartArray();
        _copyBufferContents(jsonParser);
    }

    public TokenBuffer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken nextToken;
        if (jsonParser.getCurrentTokenId() != JsonToken.FIELD_NAME.id()) {
            copyCurrentStructure(jsonParser);
            return this;
        }
        writeStartObject();
        do {
            copyCurrentStructure(jsonParser);
            nextToken = jsonParser.nextToken();
        } while (nextToken == JsonToken.FIELD_NAME);
        JsonToken jsonToken = JsonToken.END_OBJECT;
        if (nextToken == jsonToken) {
            writeEndObject();
            return this;
        }
        deserializationContext.reportWrongTokenException(TokenBuffer.class, jsonToken, "Expected END_OBJECT after copying contents of a JsonParser into TokenBuffer, got " + nextToken, new Object[0]);
        throw null;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator disable(JsonGenerator.Feature feature) {
        this._generatorFeatures = (feature.getMask() ^ (-1)) & this._generatorFeatures;
        return this;
    }

    public JsonToken firstToken() {
        return this._first.type(0);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator, java.io.Flushable
    public void flush() {
    }

    public TokenBuffer forceUseOfBigDecimal(boolean z) {
        this._forceBigDecimal = z;
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public int getFeatureMask() {
        return this._generatorFeatures;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public boolean isEnabled(JsonGenerator.Feature feature) {
        return (feature.getMask() & this._generatorFeatures) != 0;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator overrideStdFeatures(int i, int i2) {
        this._generatorFeatures = (i & i2) | (getFeatureMask() & (i2 ^ (-1)));
        return this;
    }

    public void serialize(JsonGenerator jsonGenerator) {
        Segment segment = this._first;
        boolean z = this._mayHaveNativeIds;
        boolean z2 = z && segment.hasIds();
        int i = -1;
        while (true) {
            i++;
            if (i >= 16) {
                segment = segment.next();
                if (segment == null) {
                    return;
                }
                z2 = z && segment.hasIds();
                i = 0;
            }
            JsonToken type = segment.type(i);
            if (type == null) {
                return;
            }
            if (z2) {
                Object findObjectId = segment.findObjectId(i);
                if (findObjectId != null) {
                    jsonGenerator.writeObjectId(findObjectId);
                }
                Object findTypeId = segment.findTypeId(i);
                if (findTypeId != null) {
                    jsonGenerator.writeTypeId(findTypeId);
                }
            }
            switch (AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[type.ordinal()]) {
                case 1:
                    jsonGenerator.writeStartObject();
                    break;
                case 2:
                    jsonGenerator.writeEndObject();
                    break;
                case 3:
                    jsonGenerator.writeStartArray();
                    break;
                case 4:
                    jsonGenerator.writeEndArray();
                    break;
                case 5:
                    Object obj = segment.get(i);
                    if (obj instanceof SerializableString) {
                        jsonGenerator.writeFieldName((SerializableString) obj);
                        break;
                    } else {
                        jsonGenerator.writeFieldName((String) obj);
                        break;
                    }
                case 6:
                    Object obj2 = segment.get(i);
                    if (obj2 instanceof SerializableString) {
                        jsonGenerator.writeString((SerializableString) obj2);
                        break;
                    } else {
                        jsonGenerator.writeString((String) obj2);
                        break;
                    }
                case 7:
                    Object obj3 = segment.get(i);
                    if (obj3 instanceof Integer) {
                        jsonGenerator.writeNumber(((Integer) obj3).intValue());
                        break;
                    } else if (obj3 instanceof BigInteger) {
                        jsonGenerator.writeNumber((BigInteger) obj3);
                        break;
                    } else if (obj3 instanceof Long) {
                        jsonGenerator.writeNumber(((Long) obj3).longValue());
                        break;
                    } else if (obj3 instanceof Short) {
                        jsonGenerator.writeNumber(((Short) obj3).shortValue());
                        break;
                    } else {
                        jsonGenerator.writeNumber(((Number) obj3).intValue());
                        break;
                    }
                case 8:
                    Object obj4 = segment.get(i);
                    if (obj4 instanceof Double) {
                        jsonGenerator.writeNumber(((Double) obj4).doubleValue());
                        break;
                    } else if (obj4 instanceof BigDecimal) {
                        jsonGenerator.writeNumber((BigDecimal) obj4);
                        break;
                    } else if (obj4 instanceof Float) {
                        jsonGenerator.writeNumber(((Float) obj4).floatValue());
                        break;
                    } else if (obj4 == null) {
                        jsonGenerator.writeNull();
                        break;
                    } else if (obj4 instanceof String) {
                        jsonGenerator.writeNumber((String) obj4);
                        break;
                    } else {
                        throw new JsonGenerationException(String.format("Unrecognized value type for VALUE_NUMBER_FLOAT: %s, cannot serialize", obj4.getClass().getName()), jsonGenerator);
                    }
                case 9:
                    jsonGenerator.writeBoolean(true);
                    break;
                case 10:
                    jsonGenerator.writeBoolean(false);
                    break;
                case 11:
                    jsonGenerator.writeNull();
                    break;
                case 12:
                    Object obj5 = segment.get(i);
                    if (obj5 instanceof RawValue) {
                        ((RawValue) obj5).serialize(jsonGenerator);
                        break;
                    } else if (obj5 instanceof JsonSerializable) {
                        jsonGenerator.writeObject(obj5);
                        break;
                    } else {
                        jsonGenerator.writeEmbeddedObject(obj5);
                        break;
                    }
                default:
                    throw new RuntimeException("Internal error: should never end up through this code path");
            }
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    @Deprecated
    public JsonGenerator setFeatureMask(int i) {
        this._generatorFeatures = i;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[TokenBuffer: ");
        JsonParser asParser = asParser();
        int i = 0;
        boolean z = this._hasNativeTypeIds || this._hasNativeObjectIds;
        while (true) {
            try {
                JsonToken nextToken = asParser.nextToken();
                if (nextToken == null) {
                    break;
                }
                if (z) {
                    _appendNativeIds(sb);
                }
                if (i < 100) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(nextToken.toString());
                    if (nextToken == JsonToken.FIELD_NAME) {
                        sb.append('(');
                        sb.append(asParser.getCurrentName());
                        sb.append(')');
                    }
                }
                i++;
            } catch (IOException e2) {
                throw new IllegalStateException(e2);
            }
        }
        if (i >= 100) {
            sb.append(" ... (truncated ");
            sb.append(i - 100);
            sb.append(" entries)");
        }
        sb.append(']');
        return sb.toString();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        writeObject(bArr2);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBoolean(boolean z) {
        _appendValue(z ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeEmbeddedObject(Object obj) {
        _appendValue(JsonToken.VALUE_EMBEDDED_OBJECT, obj);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeEndArray() {
        _appendEndMarker(JsonToken.END_ARRAY);
        JsonWriteContext parent = this._writeContext.getParent();
        if (parent != null) {
            this._writeContext = parent;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeEndObject() {
        _appendEndMarker(JsonToken.END_OBJECT);
        JsonWriteContext parent = this._writeContext.getParent();
        if (parent != null) {
            this._writeContext = parent;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeFieldName(String str) {
        this._writeContext.writeFieldName(str);
        _appendFieldName(str);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNull() {
        _appendValue(JsonToken.VALUE_NULL);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(short s) {
        _appendValue(JsonToken.VALUE_NUMBER_INT, Short.valueOf(s));
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeObject(Object obj) {
        if (obj == null) {
            writeNull();
            return;
        }
        if (obj.getClass() != byte[].class && !(obj instanceof RawValue)) {
            ObjectCodec objectCodec = this._objectCodec;
            if (objectCodec == null) {
                _appendValue(JsonToken.VALUE_EMBEDDED_OBJECT, obj);
                return;
            } else {
                objectCodec.writeValue(this, obj);
                return;
            }
        }
        _appendValue(JsonToken.VALUE_EMBEDDED_OBJECT, obj);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeObjectId(Object obj) {
        this._objectId = obj;
        this._hasNativeId = true;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str) {
        _reportUnsupportedOperation();
        throw null;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(String str) {
        _appendValue(JsonToken.VALUE_EMBEDDED_OBJECT, new RawValue(str));
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartArray() {
        this._writeContext.writeValue();
        _appendStartMarker(JsonToken.START_ARRAY);
        this._writeContext = this._writeContext.createChildArrayContext();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartObject() {
        this._writeContext.writeValue();
        _appendStartMarker(JsonToken.START_OBJECT);
        this._writeContext = this._writeContext.createChildObjectContext();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(String str) {
        if (str == null) {
            writeNull();
        } else {
            _appendValue(JsonToken.VALUE_STRING, str);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeTypeId(Object obj) {
        this._typeId = obj;
        this._hasNativeId = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static final class Segment {
        private static final JsonToken[] TOKEN_TYPES_BY_INDEX = new JsonToken[16];
        protected TreeMap<Integer, Object> _nativeIds;
        protected Segment _next;
        protected long _tokenTypes;
        protected final Object[] _tokens = new Object[16];

        static {
            JsonToken[] values = JsonToken.values();
            System.arraycopy(values, 1, TOKEN_TYPES_BY_INDEX, 1, Math.min(15, values.length - 1));
        }

        private final int _objectIdIndex(int i) {
            return i + i + 1;
        }

        private final int _typeIdIndex(int i) {
            return i + i;
        }

        private final void assignNativeIds(int i, Object obj, Object obj2) {
            if (this._nativeIds == null) {
                this._nativeIds = new TreeMap<>();
            }
            if (obj != null) {
                this._nativeIds.put(Integer.valueOf(_objectIdIndex(i)), obj);
            }
            if (obj2 != null) {
                this._nativeIds.put(Integer.valueOf(_typeIdIndex(i)), obj2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Object findObjectId(int i) {
            TreeMap<Integer, Object> treeMap = this._nativeIds;
            if (treeMap == null) {
                return null;
            }
            return treeMap.get(Integer.valueOf(_objectIdIndex(i)));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Object findTypeId(int i) {
            TreeMap<Integer, Object> treeMap = this._nativeIds;
            if (treeMap == null) {
                return null;
            }
            return treeMap.get(Integer.valueOf(_typeIdIndex(i)));
        }

        private void set(int i, JsonToken jsonToken) {
            long ordinal = jsonToken.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            this._tokenTypes |= ordinal;
        }

        public Segment append(int i, JsonToken jsonToken) {
            if (i < 16) {
                set(i, jsonToken);
                return null;
            }
            this._next = new Segment();
            this._next.set(0, jsonToken);
            return this._next;
        }

        public Object get(int i) {
            return this._tokens[i];
        }

        public boolean hasIds() {
            return this._nativeIds != null;
        }

        public Segment next() {
            return this._next;
        }

        public JsonToken type(int i) {
            long j = this._tokenTypes;
            if (i > 0) {
                j >>= i << 2;
            }
            return TOKEN_TYPES_BY_INDEX[((int) j) & 15];
        }

        private void set(int i, JsonToken jsonToken, Object obj, Object obj2) {
            long ordinal = jsonToken.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            this._tokenTypes = ordinal | this._tokenTypes;
            assignNativeIds(i, obj, obj2);
        }

        public Segment append(int i, JsonToken jsonToken, Object obj, Object obj2) {
            if (i < 16) {
                set(i, jsonToken, obj, obj2);
                return null;
            }
            this._next = new Segment();
            this._next.set(0, jsonToken, obj, obj2);
            return this._next;
        }

        private void set(int i, JsonToken jsonToken, Object obj) {
            this._tokens[i] = obj;
            long ordinal = jsonToken.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            this._tokenTypes = ordinal | this._tokenTypes;
        }

        private void set(int i, JsonToken jsonToken, Object obj, Object obj2, Object obj3) {
            this._tokens[i] = obj;
            long ordinal = jsonToken.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            this._tokenTypes = ordinal | this._tokenTypes;
            assignNativeIds(i, obj2, obj3);
        }

        public Segment append(int i, JsonToken jsonToken, Object obj) {
            if (i < 16) {
                set(i, jsonToken, obj);
                return null;
            }
            this._next = new Segment();
            this._next.set(0, jsonToken, obj);
            return this._next;
        }

        public Segment append(int i, JsonToken jsonToken, Object obj, Object obj2, Object obj3) {
            if (i < 16) {
                set(i, jsonToken, obj, obj2, obj3);
                return null;
            }
            this._next = new Segment();
            this._next.set(0, jsonToken, obj, obj2, obj3);
            return this._next;
        }
    }

    public JsonParser asParser(ObjectCodec objectCodec) {
        return new Parser(this._first, objectCodec, this._hasNativeTypeIds, this._hasNativeObjectIds, this._parentContext);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final JsonWriteContext getOutputContext() {
        return this._writeContext;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(int i) {
        _appendValue(JsonToken.VALUE_NUMBER_INT, Integer.valueOf(i));
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(SerializableString serializableString) {
        _reportUnsupportedOperation();
        throw null;
    }

    public JsonParser asParser(JsonParser jsonParser) {
        Parser parser = new Parser(this._first, jsonParser.getCodec(), this._hasNativeTypeIds, this._hasNativeObjectIds, this._parentContext);
        parser.setLocation(jsonParser.getTokenLocation());
        return parser;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(SerializableString serializableString) {
        this._writeContext.writeFieldName(serializableString.getValue());
        _appendFieldName(serializableString);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(long j) {
        _appendValue(JsonToken.VALUE_NUMBER_INT, Long.valueOf(j));
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(char[] cArr, int i, int i2) {
        _reportUnsupportedOperation();
        throw null;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(char[] cArr, int i, int i2) {
        writeString(new String(cArr, i, i2));
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(double d2) {
        _appendValue(JsonToken.VALUE_NUMBER_FLOAT, Double.valueOf(d2));
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(char c2) {
        _reportUnsupportedOperation();
        throw null;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartArray(int i) {
        this._writeContext.writeValue();
        _appendStartMarker(JsonToken.START_ARRAY);
        this._writeContext = this._writeContext.createChildArrayContext();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeStartObject(Object obj) {
        this._writeContext.writeValue();
        _appendStartMarker(JsonToken.START_OBJECT);
        JsonWriteContext createChildObjectContext = this._writeContext.createChildObjectContext();
        this._writeContext = createChildObjectContext;
        if (obj != null) {
            createChildObjectContext.setCurrentValue(obj);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(SerializableString serializableString) {
        if (serializableString == null) {
            writeNull();
        } else {
            _appendValue(JsonToken.VALUE_STRING, serializableString);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(float f2) {
        _appendValue(JsonToken.VALUE_NUMBER_FLOAT, Float.valueOf(f2));
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            writeNull();
        } else {
            _appendValue(JsonToken.VALUE_NUMBER_FLOAT, bigDecimal);
        }
    }

    protected final void _appendValue(JsonToken jsonToken, Object obj) {
        Segment append;
        this._writeContext.writeValue();
        if (this._hasNativeId) {
            append = this._last.append(this._appendAt, jsonToken, obj, this._objectId, this._typeId);
        } else {
            append = this._last.append(this._appendAt, jsonToken, obj);
        }
        if (append == null) {
            this._appendAt++;
        } else {
            this._last = append;
            this._appendAt = 1;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigInteger bigInteger) {
        if (bigInteger == null) {
            writeNull();
        } else {
            _appendValue(JsonToken.VALUE_NUMBER_INT, bigInteger);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(String str) {
        _appendValue(JsonToken.VALUE_NUMBER_FLOAT, str);
    }

    public TokenBuffer(JsonParser jsonParser) {
        this(jsonParser, (DeserializationContext) null);
    }

    public TokenBuffer(JsonParser jsonParser, DeserializationContext deserializationContext) {
        this._hasNativeId = false;
        this._objectCodec = jsonParser.getCodec();
        this._parentContext = jsonParser.getParsingContext();
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURES;
        this._writeContext = JsonWriteContext.createRootContext(null);
        Segment segment = new Segment();
        this._last = segment;
        this._first = segment;
        this._appendAt = 0;
        this._hasNativeTypeIds = jsonParser.canReadTypeId();
        this._hasNativeObjectIds = jsonParser.canReadObjectId();
        this._mayHaveNativeIds = this._hasNativeTypeIds | this._hasNativeObjectIds;
        this._forceBigDecimal = deserializationContext != null ? deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS) : false;
    }
}
