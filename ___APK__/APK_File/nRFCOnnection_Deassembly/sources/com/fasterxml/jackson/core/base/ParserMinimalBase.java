package com.fasterxml.jackson.core.base;

import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes.dex */
public abstract class ParserMinimalBase extends JsonParser {
    protected JsonToken _currToken;
    protected static final byte[] NO_BYTES = new byte[0];
    protected static final int[] NO_INTS = new int[0];
    protected static final BigInteger BI_MIN_INT = BigInteger.valueOf(-2147483648L);
    protected static final BigInteger BI_MAX_INT = BigInteger.valueOf(2147483647L);
    protected static final BigInteger BI_MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
    protected static final BigInteger BI_MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
    protected static final BigDecimal BD_MIN_LONG = new BigDecimal(BI_MIN_LONG);
    protected static final BigDecimal BD_MAX_LONG = new BigDecimal(BI_MAX_LONG);
    protected static final BigDecimal BD_MIN_INT = new BigDecimal(BI_MIN_INT);
    protected static final BigDecimal BD_MAX_INT = new BigDecimal(BI_MAX_INT);

    /* JADX INFO: Access modifiers changed from: protected */
    public ParserMinimalBase(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final String _getCharDesc(int i) {
        char c2 = (char) i;
        if (Character.isISOControl(c2)) {
            return "(CTRL-CHAR, code " + i + ")";
        }
        if (i > 255) {
            return "'" + c2 + "' (code " + i + " / 0x" + Integer.toHexString(i) + ")";
        }
        return "'" + c2 + "' (code " + i + ")";
    }

    protected final JsonParseException _constructError(String str, Throwable th) {
        return new JsonParseException(this, str, th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _decodeBase64(String str, ByteArrayBuilder byteArrayBuilder, Base64Variant base64Variant) {
        try {
            base64Variant.decode(str, byteArrayBuilder);
        } catch (IllegalArgumentException e2) {
            _reportError(e2.getMessage());
            throw null;
        }
    }

    protected abstract void _handleEOF();

    protected boolean _hasTextualNull(String str) {
        return "null".equals(str);
    }

    protected String _longIntegerDesc(String str) {
        int length = str.length();
        if (length < 1000) {
            return str;
        }
        if (str.startsWith("-")) {
            length--;
        }
        return String.format("[Integer with %d digits]", Integer.valueOf(length));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String _longNumberDesc(String str) {
        int length = str.length();
        if (length < 1000) {
            return str;
        }
        if (str.startsWith("-")) {
            length--;
        }
        return String.format("[number with %d characters]", Integer.valueOf(length));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _reportError(String str) {
        throw _constructError(str);
    }

    protected void _reportInputCoercion(String str, JsonToken jsonToken, Class<?> cls) {
        throw new InputCoercionException(this, str, jsonToken, cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportInvalidEOF() {
        _reportInvalidEOF(" in " + this._currToken, this._currToken);
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportInvalidEOFInValue(JsonToken jsonToken) {
        String str;
        if (jsonToken != JsonToken.VALUE_STRING) {
            str = (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) ? " in a Number value" : " in a value";
        } else {
            str = " in a String value";
        }
        _reportInvalidEOF(str, jsonToken);
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportMissingRootWS(int i) {
        _reportUnexpectedChar(i, "Expected space separating root-level values");
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportUnexpectedChar(int i, String str) {
        if (i >= 0) {
            String format = String.format("Unexpected character (%s)", _getCharDesc(i));
            if (str != null) {
                format = format + ": " + str;
            }
            _reportError(format);
            throw null;
        }
        _reportInvalidEOF();
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _throwInternal() {
        VersionUtil.throwInternal();
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _throwInvalidSpace(int i) {
        _reportError("Illegal character (" + _getCharDesc((char) i) + "): only regular white space (\\r, \\n, \\t) is allowed between tokens");
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _wrapError(String str, Throwable th) {
        throw _constructError(str, th);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void clearCurrentToken() {
        if (this._currToken != null) {
            this._currToken = null;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken currentToken() {
        return this._currToken;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken getCurrentToken() {
        return this._currToken;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getCurrentTokenId() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return 0;
        }
        return jsonToken.id();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_NUMBER_INT && jsonToken != JsonToken.VALUE_NUMBER_FLOAT) {
            return getValueAsInt(0);
        }
        return getIntValue();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_NUMBER_INT && jsonToken != JsonToken.VALUE_NUMBER_FLOAT) {
            return getValueAsLong(0L);
        }
        return getLongValue();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String getValueAsString() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            return getText();
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            return getCurrentName();
        }
        return getValueAsString(null);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean hasCurrentToken() {
        return this._currToken != null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean hasToken(JsonToken jsonToken) {
        return this._currToken == jsonToken;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean hasTokenId(int i) {
        JsonToken jsonToken = this._currToken;
        return jsonToken == null ? i == 0 : jsonToken.id() == i;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartArrayToken() {
        return this._currToken == JsonToken.START_ARRAY;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartObjectToken() {
        return this._currToken == JsonToken.START_OBJECT;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken nextValue() {
        JsonToken nextToken = nextToken();
        return nextToken == JsonToken.FIELD_NAME ? nextToken() : nextToken;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportInvalidNumber(String str) {
        _reportError("Invalid numeric value: " + str);
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportOverflowInt() {
        reportOverflowInt(getText());
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportOverflowLong() {
        reportOverflowLong(getText());
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportUnexpectedNumberChar(int i, String str) {
        String format = String.format("Unexpected character (%s) in numeric value", _getCharDesc(i));
        if (str != null) {
            format = format + ": " + str;
        }
        _reportError(format);
        throw null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonParser skipChildren() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.START_OBJECT && jsonToken != JsonToken.START_ARRAY) {
            return this;
        }
        int i = 1;
        while (true) {
            JsonToken nextToken = nextToken();
            if (nextToken == null) {
                _handleEOF();
                return this;
            }
            if (nextToken.isStructStart()) {
                i++;
            } else if (nextToken.isStructEnd()) {
                i--;
                if (i == 0) {
                    return this;
                }
            } else if (nextToken == JsonToken.NOT_AVAILABLE) {
                _reportError("Not enough content available for `skipChildren()`: non-blocking parser? (%s)", getClass().getName());
                throw null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _reportError(String str, Object obj) {
        throw _constructError(String.format(str, obj));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _reportInvalidEOF(String str, JsonToken jsonToken) {
        throw new JsonEOFException(this, jsonToken, "Unexpected end-of-input" + str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportOverflowInt(String str) {
        reportOverflowInt(str, JsonToken.VALUE_NUMBER_INT);
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportOverflowLong(String str) {
        reportOverflowLong(str, JsonToken.VALUE_NUMBER_INT);
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _reportError(String str, Object obj, Object obj2) {
        throw _constructError(String.format(str, obj, obj2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportOverflowInt(String str, JsonToken jsonToken) {
        _reportInputCoercion(String.format("Numeric value (%s) out of range of int (%d - %s)", _longIntegerDesc(str), Integer.valueOf(RecyclerView.UNDEFINED_DURATION), Integer.valueOf(Preference.DEFAULT_ORDER)), jsonToken, Integer.TYPE);
        throw null;
    }

    protected void reportOverflowLong(String str, JsonToken jsonToken) {
        _reportInputCoercion(String.format("Numeric value (%s) out of range of long (%d - %s)", _longIntegerDesc(str), Long.MIN_VALUE, Long.MAX_VALUE), jsonToken, Long.TYPE);
        throw null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt(int i) {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return getIntValue();
        }
        if (jsonToken == null) {
            return i;
        }
        int id = jsonToken.id();
        if (id != 6) {
            switch (id) {
                case 9:
                    return 1;
                case 10:
                case 11:
                    return 0;
                case 12:
                    Object embeddedObject = getEmbeddedObject();
                    return embeddedObject instanceof Number ? ((Number) embeddedObject).intValue() : i;
                default:
                    return i;
            }
        }
        String text = getText();
        if (_hasTextualNull(text)) {
            return 0;
        }
        return NumberInput.parseAsInt(text, i);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong(long j) {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return getLongValue();
        }
        if (jsonToken == null) {
            return j;
        }
        int id = jsonToken.id();
        if (id != 6) {
            switch (id) {
                case 9:
                    return 1L;
                case 10:
                case 11:
                    return 0L;
                case 12:
                    Object embeddedObject = getEmbeddedObject();
                    return embeddedObject instanceof Number ? ((Number) embeddedObject).longValue() : j;
                default:
                    return j;
            }
        }
        String text = getText();
        if (_hasTextualNull(text)) {
            return 0L;
        }
        return NumberInput.parseAsLong(text, j);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String getValueAsString(String str) {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            return getText();
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            return getCurrentName();
        }
        return (jsonToken == null || jsonToken == JsonToken.VALUE_NULL || !jsonToken.isScalarValue()) ? str : getText();
    }
}
