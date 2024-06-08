package com.fasterxml.jackson.dataformat.cbor;

import androidx.preference.Preference;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;
import no.nordicsemi.android.mcp.ble.parser.AppearanceLibrary;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public class CBORParser extends ParserMinimalBase {
    protected byte[] _binaryValue;
    protected boolean _bufferRecyclable;
    protected ByteArrayBuilder _byteArrayBuilder;
    private int _chunkEnd;
    private int _chunkLeft;
    protected boolean _closed;
    protected long _currInputProcessed;
    protected byte[] _inputBuffer;
    protected int _inputEnd;
    protected int _inputPtr;
    protected InputStream _inputStream;
    protected final IOContext _ioContext;
    protected boolean _nameCopied;
    protected char[] _nameCopyBuffer;
    protected int _numTypesValid;
    protected BigDecimal _numberBigDecimal;
    protected BigInteger _numberBigInt;
    protected double _numberDouble;
    protected float _numberFloat;
    protected int _numberInt;
    protected long _numberLong;
    protected ObjectCodec _objectCodec;
    protected CBORReadContext _parsingContext;
    protected int _quad1;
    protected int _quad2;
    protected int _quad3;
    protected int[] _quadBuffer;
    protected final ByteQuadsCanonicalizer _symbols;
    protected int _tagValue;
    protected final TextBuffer _textBuffer;
    protected boolean _tokenIncomplete;
    protected long _tokenInputTotal;
    protected int _typeByte;
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final int[] UTF8_UNIT_CODES = CBORConstants.sUtf8UnitLengths;
    private static final double MATH_POW_2_10 = Math.pow(2.0d, 10.0d);
    private static final double MATH_POW_2_NEG14 = Math.pow(2.0d, -14.0d);
    static final BigInteger BI_MIN_INT = BigInteger.valueOf(-2147483648L);
    static final BigInteger BI_MAX_INT = BigInteger.valueOf(2147483647L);
    static final BigInteger BI_MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
    static final BigInteger BI_MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
    static final BigDecimal BD_MIN_LONG = new BigDecimal(BI_MIN_LONG);
    static final BigDecimal BD_MAX_LONG = new BigDecimal(BI_MAX_LONG);
    static final BigDecimal BD_MIN_INT = new BigDecimal(BI_MIN_INT);
    static final BigDecimal BD_MAX_INT = new BigDecimal(BI_MAX_INT);
    private static final BigInteger BIT_63 = BigInteger.ONE.shiftLeft(63);

    /* loaded from: classes.dex */
    public enum Feature implements FormatFeature {
        ;

        final boolean _defaultState;
        final int _mask;

        public static int collectDefaults() {
            int i = 0;
            for (Feature feature : values()) {
                if (feature.enabledByDefault()) {
                    i |= feature.getMask();
                }
            }
            return i;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public int getMask() {
            return this._mask;
        }
    }

    public CBORParser(IOContext iOContext, int i, int i2, ObjectCodec objectCodec, ByteQuadsCanonicalizer byteQuadsCanonicalizer, InputStream inputStream, byte[] bArr, int i3, int i4, boolean z) {
        super(i);
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._currInputProcessed = 0L;
        this._tokenInputTotal = 0L;
        this._nameCopyBuffer = null;
        this._nameCopied = false;
        this._byteArrayBuilder = null;
        this._tagValue = -1;
        this._tokenIncomplete = false;
        this._quadBuffer = ParserMinimalBase.NO_INTS;
        this._numTypesValid = 0;
        this._ioContext = iOContext;
        this._objectCodec = objectCodec;
        this._symbols = byteQuadsCanonicalizer;
        this._inputStream = inputStream;
        this._inputBuffer = bArr;
        this._inputPtr = i3;
        this._inputEnd = i4;
        this._bufferRecyclable = z;
        this._textBuffer = iOContext.constructTextBuffer();
        this._parsingContext = CBORReadContext.createRootContext(JsonParser.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(i) ? DupDetector.rootDetector(this) : null);
    }

    private final String _addDecodedToSymbols(int i, String str) {
        if (i < 5) {
            return this._symbols.addName(str, this._quad1);
        }
        if (i < 9) {
            return this._symbols.addName(str, this._quad1, this._quad2);
        }
        if (i < 13) {
            return this._symbols.addName(str, this._quad1, this._quad2, this._quad3);
        }
        return this._symbols.addName(str, this._quadBuffer, (i + 3) >> 2);
    }

    private final BigInteger _bigNegative(long j) {
        return _bigPositive(j).negate().subtract(BigInteger.ONE);
    }

    private final BigInteger _bigPositive(long j) {
        return BigInteger.valueOf((j << 1) >>> 1).or(BIT_63);
    }

    private final int _decode16Bits() {
        int i = this._inputPtr;
        int i2 = i + 1;
        if (i2 >= this._inputEnd) {
            return _slow16();
        }
        byte[] bArr = this._inputBuffer;
        int i3 = ((bArr[i] & FlagsParser.UNKNOWN_FLAGS) << 8) + (bArr[i2] & FlagsParser.UNKNOWN_FLAGS);
        this._inputPtr = i + 2;
        return i3;
    }

    private final int _decode32Bits() {
        int i = this._inputPtr;
        if (i + 3 >= this._inputEnd) {
            return _slow32();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i] << 24) + ((bArr[i2] & FlagsParser.UNKNOWN_FLAGS) << 16);
        int i5 = i3 + 1;
        int i6 = i4 + ((bArr[i3] & FlagsParser.UNKNOWN_FLAGS) << 8) + (bArr[i5] & FlagsParser.UNKNOWN_FLAGS);
        this._inputPtr = i5 + 1;
        return i6;
    }

    private final long _decode64Bits() {
        int i = this._inputPtr;
        if (i + 7 >= this._inputEnd) {
            return _slow64();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i] << 24) + ((bArr[i2] & FlagsParser.UNKNOWN_FLAGS) << 16);
        int i5 = i3 + 1;
        int i6 = i4 + ((bArr[i3] & FlagsParser.UNKNOWN_FLAGS) << 8);
        int i7 = i5 + 1;
        int i8 = i6 + (bArr[i5] & FlagsParser.UNKNOWN_FLAGS);
        int i9 = i7 + 1;
        int i10 = i9 + 1;
        int i11 = (bArr[i7] << 24) + ((bArr[i9] & FlagsParser.UNKNOWN_FLAGS) << 16);
        int i12 = i10 + 1;
        int i13 = i11 + ((bArr[i10] & FlagsParser.UNKNOWN_FLAGS) << 8) + (bArr[i12] & FlagsParser.UNKNOWN_FLAGS);
        this._inputPtr = i12 + 1;
        return _long(i8, i13);
    }

    private final int _decode8Bits() {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        return bArr[i] & FlagsParser.UNKNOWN_FLAGS;
    }

    private int _decodeChunkLength(int i) {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int i3 = bArr[i2] & FlagsParser.UNKNOWN_FLAGS;
        if (i3 == 255) {
            return -1;
        }
        int i4 = i3 >> 5;
        if (i4 == i) {
            int _decodeExplicitLength = _decodeExplicitLength(i3 & 31);
            if (_decodeExplicitLength >= 0) {
                return _decodeExplicitLength;
            }
            throw _constructError("Illegal chunked-length indicator within chunked-length value (type " + i + ")");
        }
        throw _constructError("Mismatched chunk in chunked content: expected " + i + " but encountered " + i4 + " (byte 0x" + Integer.toHexString(i3) + ")");
    }

    private final String _decodeChunkedName() {
        _finishChunkedText();
        return this._textBuffer.contentsAsString();
    }

    private final int _decodeChunkedUTF8_3(int i) {
        int i2 = i & 15;
        int _nextChunkedByte = _nextChunkedByte();
        if ((_nextChunkedByte & AppearanceLibrary.APPEARANCE_GENERIC_WATCH) == 128) {
            int i3 = (i2 << 6) | (_nextChunkedByte & 63);
            int _nextChunkedByte2 = _nextChunkedByte();
            if ((_nextChunkedByte2 & AppearanceLibrary.APPEARANCE_GENERIC_WATCH) == 128) {
                return (i3 << 6) | (_nextChunkedByte2 & 63);
            }
            _reportInvalidOther(_nextChunkedByte2 & 255, this._inputPtr);
            throw null;
        }
        _reportInvalidOther(_nextChunkedByte & 255, this._inputPtr);
        throw null;
    }

    private final int _decodeChunkedUTF8_4(int i) {
        int _nextChunkedByte = _nextChunkedByte();
        if ((_nextChunkedByte & AppearanceLibrary.APPEARANCE_GENERIC_WATCH) == 128) {
            int i2 = ((i & 7) << 6) | (_nextChunkedByte & 63);
            int _nextChunkedByte2 = _nextChunkedByte();
            if ((_nextChunkedByte2 & AppearanceLibrary.APPEARANCE_GENERIC_WATCH) == 128) {
                int i3 = (i2 << 6) | (_nextChunkedByte2 & 63);
                int _nextChunkedByte3 = _nextChunkedByte();
                if ((_nextChunkedByte3 & AppearanceLibrary.APPEARANCE_GENERIC_WATCH) == 128) {
                    return ((i3 << 6) | (_nextChunkedByte3 & 63)) - 65536;
                }
                _reportInvalidOther(_nextChunkedByte3 & 255, this._inputPtr);
                throw null;
            }
            _reportInvalidOther(_nextChunkedByte2 & 255, this._inputPtr);
            throw null;
        }
        _reportInvalidOther(_nextChunkedByte & 255, this._inputPtr);
        throw null;
    }

    private final int _decodeExplicitLength(int i) {
        if (i == 31) {
            return -1;
        }
        if (i <= 23) {
            return i;
        }
        int i2 = i - 24;
        if (i2 == 0) {
            return _decode8Bits();
        }
        if (i2 == 1) {
            return _decode16Bits();
        }
        if (i2 == 2) {
            return _decode32Bits();
        }
        if (i2 == 3) {
            long _decode64Bits = _decode64Bits();
            if (_decode64Bits >= 0 && _decode64Bits <= 2147483647L) {
                return (int) _decode64Bits;
            }
            throw _constructError("Illegal length for " + getCurrentToken() + ": " + _decode64Bits);
        }
        throw _constructError("Invalid length for " + getCurrentToken() + ": 0x" + Integer.toHexString(i));
    }

    private float _decodeHalfSizeFloat() {
        int _decode16Bits = _decode16Bits() & 65535;
        boolean z = (_decode16Bits >> 15) != 0;
        int i = (_decode16Bits >> 10) & 31;
        int i2 = _decode16Bits & 1023;
        if (i == 0) {
            double d2 = MATH_POW_2_NEG14;
            double d3 = i2;
            double d4 = MATH_POW_2_10;
            Double.isNaN(d3);
            float f2 = (float) (d2 * (d3 / d4));
            return z ? -f2 : f2;
        }
        if (i == 31) {
            if (i2 != 0) {
                return Float.NaN;
            }
            return z ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
        }
        double pow = Math.pow(2.0d, i - 15);
        double d5 = i2;
        double d6 = MATH_POW_2_10;
        Double.isNaN(d5);
        float f3 = (float) (pow * ((d5 / d6) + 1.0d));
        return z ? -f3 : f3;
    }

    private final String _decodeLongerName(int i) {
        if (this._inputEnd - this._inputPtr < i) {
            if (i >= this._inputBuffer.length) {
                _finishLongText(i);
                return this._textBuffer.contentsAsString();
            }
            _loadToHaveAtLeast(i);
        }
        String _findDecodedFromSymbols = _findDecodedFromSymbols(i);
        if (_findDecodedFromSymbols != null) {
            this._inputPtr += i;
            return _findDecodedFromSymbols;
        }
        return _addDecodedToSymbols(i, _decodeShortName(i));
    }

    private final String _decodeShortName(int i) {
        int i2;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        if (emptyAndGetCurrentSegment.length < i) {
            emptyAndGetCurrentSegment = this._textBuffer.expandCurrentSegment(i);
        }
        int i3 = this._inputPtr;
        this._inputPtr = i3 + i;
        int[] iArr = UTF8_UNIT_CODES;
        byte[] bArr = this._inputBuffer;
        int i4 = i + i3;
        int i5 = 0;
        while (true) {
            int i6 = bArr[i3] & FlagsParser.UNKNOWN_FLAGS;
            if (iArr[i6] != 0) {
                while (i3 < i4) {
                    int i7 = i3 + 1;
                    int i8 = bArr[i3] & FlagsParser.UNKNOWN_FLAGS;
                    int i9 = iArr[i8];
                    if (i9 != 0) {
                        if (i9 == 1) {
                            i2 = i7 + 1;
                            i8 = ((i8 & 31) << 6) | (bArr[i7] & 63);
                        } else if (i9 == 2) {
                            int i10 = i7 + 1;
                            int i11 = ((i8 & 15) << 12) | ((bArr[i7] & 63) << 6);
                            i7 = i10 + 1;
                            i8 = i11 | (bArr[i10] & 63);
                        } else if (i9 == 3) {
                            int i12 = i7 + 1;
                            int i13 = ((i8 & 7) << 18) | ((bArr[i7] & 63) << 12);
                            int i14 = i12 + 1;
                            int i15 = i13 | ((bArr[i12] & 63) << 6);
                            i2 = i14 + 1;
                            int i16 = (i15 | (bArr[i14] & 63)) - 65536;
                            emptyAndGetCurrentSegment[i5] = (char) (55296 | (i16 >> 10));
                            i8 = (i16 & 1023) | 56320;
                            i5++;
                        } else {
                            _reportError("Invalid byte " + Integer.toHexString(i8) + " in Object name");
                            throw null;
                        }
                        i7 = i2;
                    }
                    emptyAndGetCurrentSegment[i5] = (char) i8;
                    i3 = i7;
                    i5++;
                }
                return this._textBuffer.setCurrentAndReturn(i5);
            }
            int i17 = i5 + 1;
            emptyAndGetCurrentSegment[i5] = (char) i6;
            i3++;
            if (i3 == i4) {
                return this._textBuffer.setCurrentAndReturn(i17);
            }
            i5 = i17;
        }
    }

    private final int _decodeTag(int i) {
        if (i <= 23) {
            return i;
        }
        int i2 = i - 24;
        if (i2 == 0) {
            return _decode8Bits();
        }
        if (i2 == 1) {
            return _decode16Bits();
        }
        if (i2 == 2) {
            return _decode32Bits();
        }
        if (i2 == 3) {
            long _decode64Bits = _decode64Bits();
            if (_decode64Bits >= -2147483648L && _decode64Bits <= 2147483647L) {
                return (int) _decode64Bits;
            }
            _reportError("Illegal Tag value: " + _decode64Bits);
            throw null;
        }
        throw _constructError("Invalid low bits for Tag token: 0x" + Integer.toHexString(i));
    }

    private final int _decodeUTF8_3(int i) {
        int i2 = i & 15;
        int _nextByte = _nextByte();
        if ((_nextByte & AppearanceLibrary.APPEARANCE_GENERIC_WATCH) == 128) {
            int i3 = (i2 << 6) | (_nextByte & 63);
            int _nextByte2 = _nextByte();
            if ((_nextByte2 & AppearanceLibrary.APPEARANCE_GENERIC_WATCH) == 128) {
                return (i3 << 6) | (_nextByte2 & 63);
            }
            _reportInvalidOther(_nextByte2 & 255, this._inputPtr);
            throw null;
        }
        _reportInvalidOther(_nextByte & 255, this._inputPtr);
        throw null;
    }

    private final int _decodeUTF8_4(int i) {
        int _nextByte = _nextByte();
        if ((_nextByte & AppearanceLibrary.APPEARANCE_GENERIC_WATCH) == 128) {
            int i2 = ((i & 7) << 6) | (_nextByte & 63);
            int _nextByte2 = _nextByte();
            if ((_nextByte2 & AppearanceLibrary.APPEARANCE_GENERIC_WATCH) == 128) {
                int i3 = (i2 << 6) | (_nextByte2 & 63);
                int _nextByte3 = _nextByte();
                if ((_nextByte3 & AppearanceLibrary.APPEARANCE_GENERIC_WATCH) == 128) {
                    return ((i3 << 6) | (_nextByte3 & 63)) - 65536;
                }
                _reportInvalidOther(_nextByte3 & 255, this._inputPtr);
                throw null;
            }
            _reportInvalidOther(_nextByte2 & 255, this._inputPtr);
            throw null;
        }
        _reportInvalidOther(_nextByte & 255, this._inputPtr);
        throw null;
    }

    private final String _findDecodedFromSymbols(int i) {
        if (this._inputEnd - this._inputPtr < i) {
            _loadToHaveAtLeast(i);
        }
        if (i < 5) {
            int i2 = this._inputPtr;
            byte[] bArr = this._inputBuffer;
            int i3 = bArr[i2] & FlagsParser.UNKNOWN_FLAGS;
            if (i > 1) {
                int i4 = i2 + 1;
                i3 = (i3 << 8) + (bArr[i4] & FlagsParser.UNKNOWN_FLAGS);
                if (i > 2) {
                    int i5 = i4 + 1;
                    i3 = (bArr[i5] & FlagsParser.UNKNOWN_FLAGS) + (i3 << 8);
                    if (i > 3) {
                        i3 = (i3 << 8) + (bArr[i5 + 1] & FlagsParser.UNKNOWN_FLAGS);
                    }
                }
            }
            this._quad1 = i3;
            return this._symbols.findName(i3);
        }
        byte[] bArr2 = this._inputBuffer;
        int i6 = this._inputPtr;
        int i7 = i6 + 1;
        int i8 = i7 + 1;
        int i9 = (((bArr2[i6] & FlagsParser.UNKNOWN_FLAGS) << 8) | (bArr2[i7] & FlagsParser.UNKNOWN_FLAGS)) << 8;
        int i10 = i8 + 1;
        int i11 = (i9 | (bArr2[i8] & FlagsParser.UNKNOWN_FLAGS)) << 8;
        int i12 = i10 + 1;
        int i13 = i11 | (bArr2[i10] & FlagsParser.UNKNOWN_FLAGS);
        if (i < 9) {
            int i14 = i12 + 1;
            int i15 = bArr2[i12] & FlagsParser.UNKNOWN_FLAGS;
            int i16 = i - 5;
            if (i16 > 0) {
                int i17 = i15 << 8;
                int i18 = i14 + 1;
                int i19 = i17 + (bArr2[i14] & FlagsParser.UNKNOWN_FLAGS);
                if (i16 > 1) {
                    int i20 = i18 + 1;
                    i15 = (i19 << 8) + (bArr2[i18] & FlagsParser.UNKNOWN_FLAGS);
                    if (i16 > 2) {
                        i15 = (i15 << 8) + (bArr2[i20] & FlagsParser.UNKNOWN_FLAGS);
                    }
                } else {
                    i15 = i19;
                }
            }
            this._quad1 = i13;
            this._quad2 = i15;
            return this._symbols.findName(i13, i15);
        }
        int i21 = i12 + 1;
        int i22 = i21 + 1;
        int i23 = ((bArr2[i21] & FlagsParser.UNKNOWN_FLAGS) | ((bArr2[i12] & FlagsParser.UNKNOWN_FLAGS) << 8)) << 8;
        int i24 = i22 + 1;
        int i25 = (i23 | (bArr2[i22] & FlagsParser.UNKNOWN_FLAGS)) << 8;
        int i26 = i24 + 1;
        int i27 = i25 | (bArr2[i24] & FlagsParser.UNKNOWN_FLAGS);
        if (i < 13) {
            int i28 = i26 + 1;
            int i29 = bArr2[i26] & FlagsParser.UNKNOWN_FLAGS;
            int i30 = i - 9;
            if (i30 > 0) {
                int i31 = i29 << 8;
                int i32 = i28 + 1;
                int i33 = i31 + (bArr2[i28] & FlagsParser.UNKNOWN_FLAGS);
                if (i30 > 1) {
                    int i34 = i33 << 8;
                    int i35 = i32 + 1;
                    i29 = i34 + (bArr2[i32] & FlagsParser.UNKNOWN_FLAGS);
                    if (i30 > 2) {
                        i29 = (i29 << 8) + (bArr2[i35] & FlagsParser.UNKNOWN_FLAGS);
                    }
                } else {
                    i29 = i33;
                }
            }
            this._quad1 = i13;
            this._quad2 = i27;
            this._quad3 = i29;
            return this._symbols.findName(i13, i27, i29);
        }
        return _findDecodedLong(i, i13, i27);
    }

    private final String _findDecodedLong(int i, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7 = (i + 3) >> 2;
        int[] iArr = this._quadBuffer;
        if (i7 > iArr.length) {
            this._quadBuffer = _growArrayTo(iArr, i7);
        }
        int[] iArr2 = this._quadBuffer;
        iArr2[0] = i2;
        iArr2[1] = i3;
        int i8 = this._inputPtr + 8;
        byte[] bArr = this._inputBuffer;
        int i9 = i - 8;
        int i10 = 2;
        while (true) {
            int i11 = i8 + 1;
            int i12 = i11 + 1;
            int i13 = (((bArr[i8] & FlagsParser.UNKNOWN_FLAGS) << 8) | (bArr[i11] & FlagsParser.UNKNOWN_FLAGS)) << 8;
            int i14 = i12 + 1;
            int i15 = (i13 | (bArr[i12] & FlagsParser.UNKNOWN_FLAGS)) << 8;
            i4 = i14 + 1;
            i5 = i10 + 1;
            this._quadBuffer[i10] = i15 | (bArr[i14] & FlagsParser.UNKNOWN_FLAGS);
            i9 -= 4;
            if (i9 <= 3) {
                break;
            }
            i8 = i4;
            i10 = i5;
        }
        if (i9 > 0) {
            int i16 = bArr[i4] & FlagsParser.UNKNOWN_FLAGS;
            if (i9 > 1) {
                int i17 = i4 + 1;
                i16 = (i16 << 8) + (bArr[i17] & FlagsParser.UNKNOWN_FLAGS);
                if (i9 > 2) {
                    i16 = (i16 << 8) + (bArr[i17 + 1] & FlagsParser.UNKNOWN_FLAGS);
                }
            }
            i6 = i5 + 1;
            this._quadBuffer[i5] = i16;
        } else {
            i6 = i5;
        }
        return this._symbols.findName(this._quadBuffer, i6);
    }

    private final void _finishChunkedText() {
        int i;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = UTF8_UNIT_CODES;
        int length = emptyAndGetCurrentSegment.length;
        byte[] bArr = this._inputBuffer;
        this._chunkEnd = this._inputPtr;
        this._chunkLeft = 0;
        int i2 = length;
        int i3 = 0;
        while (true) {
            if (this._inputPtr >= this._chunkEnd) {
                if (this._chunkLeft == 0) {
                    int _decodeChunkLength = _decodeChunkLength(3);
                    if (_decodeChunkLength < 0) {
                        this._textBuffer.setCurrentLength(i3);
                        return;
                    }
                    this._chunkLeft = _decodeChunkLength;
                    int i4 = this._inputPtr + _decodeChunkLength;
                    int i5 = this._inputEnd;
                    if (i4 <= i5) {
                        this._chunkLeft = 0;
                        this._chunkEnd = i4;
                    } else {
                        this._chunkLeft = i4 - i5;
                        this._chunkEnd = i5;
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                    int i6 = this._inputPtr + this._chunkLeft;
                    int i7 = this._inputEnd;
                    if (i6 <= i7) {
                        this._chunkLeft = 0;
                        this._chunkEnd = i6;
                    } else {
                        this._chunkLeft = i6 - i7;
                        this._chunkEnd = i7;
                    }
                }
            }
            int i8 = this._inputPtr;
            this._inputPtr = i8 + 1;
            int i9 = bArr[i8] & FlagsParser.UNKNOWN_FLAGS;
            int i10 = iArr[i9];
            if (i10 == 0 && i3 < i2) {
                i = i3 + 1;
                emptyAndGetCurrentSegment[i3] = (char) i9;
            } else {
                if (i10 != 0) {
                    if (i10 == 1) {
                        int _nextChunkedByte = _nextChunkedByte();
                        if ((_nextChunkedByte & AppearanceLibrary.APPEARANCE_GENERIC_WATCH) != 128) {
                            _reportInvalidOther(_nextChunkedByte & 255, this._inputPtr);
                            throw null;
                        }
                        i9 = ((i9 & 31) << 6) | (_nextChunkedByte & 63);
                    } else if (i10 == 2) {
                        i9 = _decodeChunkedUTF8_3(i9);
                    } else if (i10 == 3) {
                        int _decodeChunkedUTF8_4 = _decodeChunkedUTF8_4(i9);
                        if (i3 >= emptyAndGetCurrentSegment.length) {
                            emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                            i2 = emptyAndGetCurrentSegment.length;
                            i3 = 0;
                        }
                        emptyAndGetCurrentSegment[i3] = (char) (55296 | (_decodeChunkedUTF8_4 >> 10));
                        i9 = (_decodeChunkedUTF8_4 & 1023) | 56320;
                        i3++;
                    } else {
                        _reportInvalidChar(i9);
                        throw null;
                    }
                }
                if (i3 >= i2) {
                    emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                    i2 = emptyAndGetCurrentSegment.length;
                    i3 = 0;
                }
                i = i3 + 1;
                emptyAndGetCurrentSegment[i3] = (char) i9;
            }
            i3 = i;
        }
    }

    private final void _finishLongText(int i) {
        int i2;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = UTF8_UNIT_CODES;
        int length = emptyAndGetCurrentSegment.length;
        char[] cArr = emptyAndGetCurrentSegment;
        int i3 = 0;
        while (true) {
            i--;
            if (i >= 0) {
                int _nextByte = _nextByte() & 255;
                int i4 = iArr[_nextByte];
                if (i4 != 0 || i3 >= length) {
                    i -= i4;
                    if (i >= 0) {
                        if (i4 != 0) {
                            if (i4 == 1) {
                                int _nextByte2 = _nextByte();
                                if ((_nextByte2 & AppearanceLibrary.APPEARANCE_GENERIC_WATCH) != 128) {
                                    _reportInvalidOther(_nextByte2 & 255, this._inputPtr);
                                    throw null;
                                }
                                _nextByte = ((_nextByte & 31) << 6) | (_nextByte2 & 63);
                            } else if (i4 == 2) {
                                _nextByte = _decodeUTF8_3(_nextByte);
                            } else if (i4 == 3) {
                                int _decodeUTF8_4 = _decodeUTF8_4(_nextByte);
                                int i5 = i3 + 1;
                                cArr[i3] = (char) (55296 | (_decodeUTF8_4 >> 10));
                                if (i5 >= cArr.length) {
                                    cArr = this._textBuffer.finishCurrentSegment();
                                    length = cArr.length;
                                    i5 = 0;
                                }
                                _nextByte = (_decodeUTF8_4 & 1023) | 56320;
                                i3 = i5;
                            } else {
                                _reportInvalidChar(_nextByte);
                                throw null;
                            }
                        }
                        if (i3 >= length) {
                            char[] finishCurrentSegment = this._textBuffer.finishCurrentSegment();
                            length = finishCurrentSegment.length;
                            cArr = finishCurrentSegment;
                            i3 = 0;
                        }
                        i2 = i3 + 1;
                        cArr[i3] = (char) _nextByte;
                    } else {
                        throw _constructError("Malformed UTF-8 character at end of long (non-chunked) text segment");
                    }
                } else {
                    i2 = i3 + 1;
                    cArr[i3] = (char) _nextByte;
                }
                i3 = i2;
            } else {
                this._textBuffer.setCurrentLength(i3);
                return;
            }
        }
    }

    private final String _finishShortText(int i) {
        int i2;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        if (emptyAndGetCurrentSegment.length < i) {
            emptyAndGetCurrentSegment = this._textBuffer.expandCurrentSegment(i);
        }
        int i3 = 0;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + i;
        byte[] bArr = this._inputBuffer;
        int i5 = i + i4;
        while (true) {
            byte b2 = bArr[i4];
            if (b2 >= 0) {
                int i6 = i3 + 1;
                emptyAndGetCurrentSegment[i3] = (char) b2;
                i4++;
                if (i4 == i5) {
                    return this._textBuffer.setCurrentAndReturn(i6);
                }
                i3 = i6;
            } else {
                int[] iArr = UTF8_UNIT_CODES;
                while (true) {
                    int i7 = i4 + 1;
                    int i8 = bArr[i4] & FlagsParser.UNKNOWN_FLAGS;
                    int i9 = iArr[i8];
                    if (i9 != 0) {
                        if (i9 == 1) {
                            i2 = i7 + 1;
                            i8 = ((i8 & 31) << 6) | (bArr[i7] & 63);
                        } else if (i9 == 2) {
                            int i10 = i7 + 1;
                            int i11 = ((i8 & 15) << 12) | ((bArr[i7] & 63) << 6);
                            i7 = i10 + 1;
                            i8 = i11 | (bArr[i10] & 63);
                        } else if (i9 == 3) {
                            int i12 = i7 + 1;
                            int i13 = ((i8 & 7) << 18) | ((bArr[i7] & 63) << 12);
                            int i14 = i12 + 1;
                            int i15 = i13 | ((bArr[i12] & 63) << 6);
                            i2 = i14 + 1;
                            int i16 = (i15 | (bArr[i14] & 63)) - 65536;
                            emptyAndGetCurrentSegment[i3] = (char) (55296 | (i16 >> 10));
                            i8 = (i16 & 1023) | 56320;
                            i3++;
                        } else {
                            _reportError("Invalid byte " + Integer.toHexString(i8) + " in Unicode text block");
                            throw null;
                        }
                        i7 = i2;
                    }
                    int i17 = i3 + 1;
                    emptyAndGetCurrentSegment[i3] = (char) i8;
                    if (i7 >= i5) {
                        return this._textBuffer.setCurrentAndReturn(i17);
                    }
                    i4 = i7;
                    i3 = i17;
                }
            }
        }
    }

    private static int[] _growArrayTo(int[] iArr, int i) {
        return Arrays.copyOf(iArr, i + 4);
    }

    private static final long _long(int i, int i2) {
        return (i << 32) + ((i2 << 32) >>> 32);
    }

    private final int _nextByte() {
        int i = this._inputPtr;
        if (i < this._inputEnd) {
            byte b2 = this._inputBuffer[i];
            this._inputPtr = i + 1;
            return b2;
        }
        loadMoreGuaranteed();
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        return bArr[i2];
    }

    private final int _nextChunkedByte() {
        int i = this._inputPtr;
        if (i >= this._chunkEnd) {
            return _nextChunkedByte2();
        }
        byte b2 = this._inputBuffer[i];
        this._inputPtr = i + 1;
        return b2;
    }

    private final int _nextChunkedByte2() {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
            int i = this._chunkLeft;
            if (i > 0) {
                int i2 = this._inputPtr + i;
                int i3 = this._inputEnd;
                if (i2 <= i3) {
                    this._chunkLeft = 0;
                    this._chunkEnd = i2;
                } else {
                    this._chunkLeft = i2 - i3;
                    this._chunkEnd = i3;
                }
                byte[] bArr = this._inputBuffer;
                int i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                return bArr[i4];
            }
        }
        int _decodeChunkLength = _decodeChunkLength(3);
        if (_decodeChunkLength >= 0) {
            int i5 = this._inputPtr + _decodeChunkLength;
            int i6 = this._inputEnd;
            if (i5 <= i6) {
                this._chunkLeft = 0;
                this._chunkEnd = i5;
            } else {
                this._chunkLeft = i5 - i6;
                this._chunkEnd = i6;
            }
            byte[] bArr2 = this._inputBuffer;
            int i7 = this._inputPtr;
            this._inputPtr = i7 + 1;
            return bArr2[i7];
        }
        _reportInvalidEOF(": chunked Text ends with partial UTF-8 character", JsonToken.VALUE_STRING);
        throw null;
    }

    private int _readAndWriteBytes(OutputStream outputStream, int i) {
        int i2 = i;
        while (i2 > 0) {
            int i3 = this._inputEnd;
            int i4 = this._inputPtr;
            int i5 = i3 - i4;
            if (i4 >= i3) {
                loadMoreGuaranteed();
                i5 = this._inputEnd - this._inputPtr;
            }
            int min = Math.min(i5, i2);
            outputStream.write(this._inputBuffer, this._inputPtr, min);
            this._inputPtr += min;
            i2 -= min;
        }
        this._tokenIncomplete = false;
        return i;
    }

    private final int _slow16() {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = bArr[i] & FlagsParser.UNKNOWN_FLAGS;
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        return (i2 << 8) + (bArr2[i3] & FlagsParser.UNKNOWN_FLAGS);
    }

    private final int _slow32() {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b2 = bArr[i];
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int i3 = (b2 << 8) + (bArr2[i2] & FlagsParser.UNKNOWN_FLAGS);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        int i5 = (i3 << 8) + (bArr3[i4] & FlagsParser.UNKNOWN_FLAGS);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr4 = this._inputBuffer;
        int i6 = this._inputPtr;
        this._inputPtr = i6 + 1;
        return (i5 << 8) + (bArr4[i6] & FlagsParser.UNKNOWN_FLAGS);
    }

    private final long _slow64() {
        return _long(_decode32Bits(), _decode32Bits());
    }

    protected void _checkNumericValue(int i) {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return;
        }
        _reportError("Current token (" + getCurrentToken() + ") not numeric, can not use numeric value accessors");
        throw null;
    }

    protected void _closeInput() {
        if (this._inputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._inputStream.close();
            }
            this._inputStream = null;
        }
    }

    protected final JsonToken _decodeFieldName() {
        String _decodeLongerName;
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b2 = bArr[i];
        if (((b2 >> 5) & 7) != 3) {
            if (b2 == -1) {
                if (!this._parsingContext.hasExpectedLength()) {
                    this._parsingContext = this._parsingContext.getParent();
                    return JsonToken.END_OBJECT;
                }
                _reportUnexpectedBreak();
                throw null;
            }
            _decodeNonStringName(b2);
            return JsonToken.FIELD_NAME;
        }
        int i2 = b2 & 31;
        if (i2 > 23) {
            int _decodeExplicitLength = _decodeExplicitLength(i2);
            if (_decodeExplicitLength < 0) {
                _decodeLongerName = _decodeChunkedName();
            } else {
                _decodeLongerName = _decodeLongerName(_decodeExplicitLength);
            }
        } else if (i2 == 0) {
            _decodeLongerName = "";
        } else {
            String _findDecodedFromSymbols = _findDecodedFromSymbols(i2);
            if (_findDecodedFromSymbols != null) {
                this._inputPtr += i2;
                _decodeLongerName = _findDecodedFromSymbols;
            } else {
                _decodeLongerName = _addDecodedToSymbols(i2, _decodeShortName(i2));
            }
        }
        this._parsingContext.setCurrentName(_decodeLongerName);
        return JsonToken.FIELD_NAME;
    }

    protected final void _decodeNonStringName(int i) {
        String str;
        int i2 = (i >> 5) & 7;
        if (i2 == 0) {
            str = _numberToName(i, false);
        } else if (i2 == 1) {
            str = _numberToName(i, true);
        } else {
            if (i2 != 2) {
                if ((i & 255) == 255) {
                    _reportUnexpectedBreak();
                    throw null;
                }
                throw _constructError("Unsupported major type (" + i2 + ") for CBOR Objects, not (yet?) supported, only Strings");
            }
            str = new String(_finishBytes(_decodeExplicitLength(i & 31)), UTF8);
        }
        this._parsingContext.setCurrentName(str);
    }

    protected JsonToken _decodeUndefinedValue() {
        return JsonToken.VALUE_NULL;
    }

    protected byte[] _finishBytes(int i) {
        if (i < 0) {
            ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
            while (true) {
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                int i3 = bArr[i2] & FlagsParser.UNKNOWN_FLAGS;
                if (i3 == 255) {
                    return _getByteArrayBuilder.toByteArray();
                }
                int i4 = i3 >> 5;
                if (i4 == 2) {
                    int _decodeExplicitLength = _decodeExplicitLength(i3 & 31);
                    if (_decodeExplicitLength < 0) {
                        throw _constructError("Illegal chunked-length indicator within chunked-length value (type 2)");
                    }
                    while (_decodeExplicitLength > 0) {
                        int i5 = this._inputEnd;
                        int i6 = this._inputPtr;
                        int i7 = i5 - i6;
                        if (i6 >= i5) {
                            loadMoreGuaranteed();
                            i7 = this._inputEnd - this._inputPtr;
                        }
                        int min = Math.min(i7, _decodeExplicitLength);
                        _getByteArrayBuilder.write(this._inputBuffer, this._inputPtr, min);
                        this._inputPtr += min;
                        _decodeExplicitLength -= min;
                    }
                } else {
                    throw _constructError("Mismatched chunk in chunked content: expected 2 but encountered " + i4);
                }
            }
        } else {
            if (i == 0) {
                return ParserMinimalBase.NO_BYTES;
            }
            byte[] bArr2 = new byte[i];
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            int i8 = 0;
            while (true) {
                int min2 = Math.min(i, this._inputEnd - this._inputPtr);
                System.arraycopy(this._inputBuffer, this._inputPtr, bArr2, i8, min2);
                this._inputPtr += min2;
                i8 += min2;
                i -= min2;
                if (i <= 0) {
                    return bArr2;
                }
                loadMoreGuaranteed();
            }
        }
    }

    protected String _finishTextToken(int i) {
        this._tokenIncomplete = false;
        int i2 = (i >> 5) & 7;
        int i3 = i & 31;
        if (i2 == 3) {
            int _decodeExplicitLength = _decodeExplicitLength(i3);
            if (_decodeExplicitLength <= 0) {
                if (_decodeExplicitLength == 0) {
                    this._textBuffer.resetWithEmpty();
                    return "";
                }
                _finishChunkedText();
                return this._textBuffer.contentsAsString();
            }
            if (_decodeExplicitLength > this._inputEnd - this._inputPtr) {
                if (_decodeExplicitLength >= this._inputBuffer.length) {
                    _finishLongText(_decodeExplicitLength);
                    return this._textBuffer.contentsAsString();
                }
                _loadToHaveAtLeast(_decodeExplicitLength);
            }
            return _finishShortText(_decodeExplicitLength);
        }
        _throwInternal();
        throw null;
    }

    protected void _finishToken() {
        this._tokenIncomplete = false;
        int i = this._typeByte;
        int i2 = (i >> 5) & 7;
        int i3 = i & 31;
        if (i2 != 3) {
            if (i2 == 2) {
                this._binaryValue = _finishBytes(_decodeExplicitLength(i3));
                return;
            } else {
                _throwInternal();
                throw null;
            }
        }
        int _decodeExplicitLength = _decodeExplicitLength(i3);
        if (_decodeExplicitLength <= 0) {
            if (_decodeExplicitLength < 0) {
                _finishChunkedText();
                return;
            } else {
                this._textBuffer.resetWithEmpty();
                return;
            }
        }
        if (_decodeExplicitLength > this._inputEnd - this._inputPtr) {
            if (_decodeExplicitLength >= this._inputBuffer.length) {
                _finishLongText(_decodeExplicitLength);
                return;
            }
            _loadToHaveAtLeast(_decodeExplicitLength);
        }
        _finishShortText(_decodeExplicitLength);
    }

    protected ByteArrayBuilder _getByteArrayBuilder() {
        ByteArrayBuilder byteArrayBuilder = this._byteArrayBuilder;
        if (byteArrayBuilder == null) {
            this._byteArrayBuilder = new ByteArrayBuilder();
        } else {
            byteArrayBuilder.reset();
        }
        return this._byteArrayBuilder;
    }

    protected JsonToken _handleCBOREOF() {
        this._tagValue = -1;
        close();
        this._currToken = null;
        return null;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase
    protected void _handleEOF() {
        if (this._parsingContext.inRoot()) {
            return;
        }
        _reportInvalidEOF(String.format(": expected close marker for %s (start marker at %s)", this._parsingContext.inArray() ? "Array" : "Object", this._parsingContext.getStartLocation(this._ioContext.getSourceReference())), null);
        throw null;
    }

    protected JsonToken _handleTaggedArray(int i, int i2) {
        BigDecimal valueOf;
        this._parsingContext = this._parsingContext.createChildArrayContext(i2);
        if (i != 4) {
            JsonToken jsonToken = JsonToken.START_ARRAY;
            this._currToken = jsonToken;
            return jsonToken;
        }
        this._currToken = JsonToken.START_ARRAY;
        if (i2 == 2) {
            JsonToken nextToken = nextToken();
            if (nextToken == JsonToken.VALUE_NUMBER_INT) {
                int i3 = -getIntValue();
                JsonToken nextToken2 = nextToken();
                if (nextToken2 == JsonToken.VALUE_NUMBER_INT) {
                    if (getNumberType() == JsonParser.NumberType.BIG_INTEGER) {
                        valueOf = new BigDecimal(getBigIntegerValue(), i3);
                    } else {
                        valueOf = BigDecimal.valueOf(getLongValue(), i3);
                    }
                    JsonToken nextToken3 = nextToken();
                    if (nextToken3 == JsonToken.END_ARRAY) {
                        this._numberBigDecimal = valueOf;
                        this._numTypesValid = 16;
                        JsonToken jsonToken2 = JsonToken.VALUE_NUMBER_FLOAT;
                        this._currToken = jsonToken2;
                        return jsonToken2;
                    }
                    _reportError("Unexpected token (" + nextToken3 + ") after 2 elements of 'bigfloat' value");
                    throw null;
                }
                _reportError("Unexpected token (" + nextToken2 + ") as the second part of 'bigfloat' value: should get VALUE_NUMBER_INT");
                throw null;
            }
            _reportError("Unexpected token (" + nextToken + ") as the first part of 'bigfloat' value: should get VALUE_NUMBER_INT");
            throw null;
        }
        _reportError("Unexpected array size (" + i2 + ") for tagged 'bigfloat' value; should have exactly 2 number elements");
        throw null;
    }

    protected JsonToken _handleTaggedBinary(int i) {
        boolean z;
        if (i == 2) {
            z = false;
        } else {
            if (i != 3) {
                JsonToken jsonToken = JsonToken.VALUE_EMBEDDED_OBJECT;
                this._currToken = jsonToken;
                return jsonToken;
            }
            z = true;
        }
        _finishToken();
        BigInteger bigInteger = new BigInteger(this._binaryValue);
        if (z) {
            bigInteger = bigInteger.negate();
        }
        this._numberBigInt = bigInteger;
        this._numTypesValid = 4;
        this._tagValue = -1;
        JsonToken jsonToken2 = JsonToken.VALUE_NUMBER_INT;
        this._currToken = jsonToken2;
        return jsonToken2;
    }

    protected void _invalidToken(int i) {
        int i2 = i & 255;
        if (i2 == 255) {
            throw _constructError("Mismatched BREAK byte (0xFF): encountered where value expected");
        }
        throw _constructError("Invalid CBOR value token (first byte): 0x" + Integer.toHexString(i2));
    }

    protected final void _loadToHaveAtLeast(int i) {
        if (this._inputStream != null) {
            int i2 = this._inputEnd;
            int i3 = this._inputPtr;
            int i4 = i2 - i3;
            if (i4 > 0 && i3 > 0) {
                byte[] bArr = this._inputBuffer;
                System.arraycopy(bArr, i3, bArr, 0, i4);
                this._inputEnd = i4;
            } else {
                this._inputEnd = 0;
            }
            this._currInputProcessed += this._inputPtr;
            this._inputPtr = 0;
            while (true) {
                int i5 = this._inputEnd;
                if (i5 >= i) {
                    return;
                }
                InputStream inputStream = this._inputStream;
                byte[] bArr2 = this._inputBuffer;
                int read = inputStream.read(bArr2, i5, bArr2.length - i5);
                if (read < 1) {
                    _closeInput();
                    if (read == 0) {
                        throw new IOException("InputStream.read() returned 0 characters when trying to read " + i4 + " bytes");
                    }
                    throw _constructError("Needed to read " + i + " bytes, missed " + i + " before end-of-input");
                }
                this._inputEnd += read;
            }
        } else {
            throw _constructError("Needed to read " + i + " bytes, reached end-of-input");
        }
    }

    protected String _numberToName(int i, boolean z) {
        int i2 = i & 31;
        if (i2 > 23) {
            switch (i2) {
                case 24:
                    i2 = _decode8Bits();
                    break;
                case 25:
                    i2 = _decode16Bits();
                    break;
                case 26:
                    i2 = _decode32Bits();
                    break;
                case 27:
                    long _decode64Bits = _decode64Bits();
                    if (z) {
                        _decode64Bits = (-_decode64Bits) - 1;
                    }
                    return String.valueOf(_decode64Bits);
                default:
                    throw _constructError("Invalid length indicator for ints (" + i2 + "), token 0x" + Integer.toHexString(i));
            }
        }
        if (z) {
            i2 = (-i2) - 1;
        }
        return String.valueOf(i2);
    }

    protected void _releaseBuffers() {
        byte[] bArr;
        if (this._bufferRecyclable && (bArr = this._inputBuffer) != null) {
            this._inputBuffer = null;
            this._ioContext.releaseReadIOBuffer(bArr);
        }
        this._textBuffer.releaseBuffers();
        char[] cArr = this._nameCopyBuffer;
        if (cArr != null) {
            this._nameCopyBuffer = null;
            this._ioContext.releaseNameCopyBuffer(cArr);
        }
    }

    protected void _reportInvalidChar(int i) {
        if (i < 32) {
            _throwInvalidSpace(i);
            throw null;
        }
        _reportInvalidInitial(i);
        throw null;
    }

    protected void _reportInvalidInitial(int i) {
        _reportError("Invalid UTF-8 start byte 0x" + Integer.toHexString(i));
        throw null;
    }

    protected void _reportInvalidOther(int i) {
        _reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(i));
        throw null;
    }

    protected void _reportUnexpectedBreak() {
        if (!this._parsingContext.inRoot()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected Break (0xFF) token in definite length (");
            sb.append(this._parsingContext.getExpectedLength());
            sb.append(") ");
            sb.append(this._parsingContext.inObject() ? "Object" : "Array");
            throw _constructError(sb.toString());
        }
        throw _constructError("Unexpected Break (0xFF) token in Root context");
    }

    protected void _skipBytes(int i) {
        while (true) {
            int min = Math.min(i, this._inputEnd - this._inputPtr);
            this._inputPtr += min;
            i -= min;
            if (i <= 0) {
                return;
            } else {
                loadMoreGuaranteed();
            }
        }
    }

    protected void _skipBytesL(long j) {
        while (j > 2147483647L) {
            _skipBytes(Preference.DEFAULT_ORDER);
            j -= 2147483647L;
        }
        _skipBytes((int) j);
    }

    protected void _skipChunked(int i) {
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            int i3 = bArr[i2] & FlagsParser.UNKNOWN_FLAGS;
            if (i3 == 255) {
                return;
            }
            int i4 = i3 >> 5;
            if (i4 != i) {
                throw _constructError("Mismatched chunk in chunked content: expected " + i + " but encountered " + i4);
            }
            int i5 = i3 & 31;
            if (i5 <= 23) {
                if (i5 > 0) {
                    _skipBytes(i5);
                }
            } else if (i5 != 31) {
                switch (i5) {
                    case 24:
                        _skipBytes(_decode8Bits());
                        break;
                    case 25:
                        _skipBytes(_decode16Bits());
                        break;
                    case 26:
                        _skipBytes(_decode32Bits());
                        break;
                    case 27:
                        _skipBytesL(_decode64Bits());
                        break;
                    default:
                        _invalidToken(this._typeByte);
                        throw null;
                }
            } else {
                throw _constructError("Illegal chunked-length indicator within chunked-length value (type " + i + ")");
            }
        }
    }

    protected void _skipIncomplete() {
        this._tokenIncomplete = false;
        int i = (this._typeByte >> 5) & 7;
        if (i != 3 && i == 3) {
            _throwInternal();
            throw null;
        }
        int i2 = this._typeByte;
        int i3 = i2 & 31;
        if (i3 <= 23) {
            if (i3 > 0) {
                _skipBytes(i3);
            }
        } else {
            if (i3 != 31) {
                switch (i3) {
                    case 24:
                        _skipBytes(_decode8Bits());
                        return;
                    case 25:
                        _skipBytes(_decode16Bits());
                        return;
                    case 26:
                        _skipBytes(_decode32Bits());
                        return;
                    case 27:
                        _skipBytesL(_decode64Bits());
                        return;
                    default:
                        _invalidToken(i2);
                        throw null;
                }
            }
            _skipChunked(i);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this._closed) {
            return;
        }
        this._closed = true;
        this._symbols.release();
        try {
            _closeInput();
        } finally {
            _releaseBuffers();
        }
    }

    protected void convertNumberToBigDecimal() {
        int i = this._numTypesValid;
        if ((i & 40) != 0) {
            this._numberBigDecimal = NumberInput.parseBigDecimal(getText());
        } else if ((i & 4) != 0) {
            this._numberBigDecimal = new BigDecimal(this._numberBigInt);
        } else if ((i & 2) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf(this._numberLong);
        } else if ((i & 1) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf(this._numberInt);
        } else {
            _throwInternal();
            throw null;
        }
        this._numTypesValid |= 16;
    }

    protected void convertNumberToBigInteger() {
        int i = this._numTypesValid;
        if ((i & 16) != 0) {
            this._numberBigInt = this._numberBigDecimal.toBigInteger();
        } else if ((i & 2) != 0) {
            this._numberBigInt = BigInteger.valueOf(this._numberLong);
        } else if ((i & 1) != 0) {
            this._numberBigInt = BigInteger.valueOf(this._numberInt);
        } else if ((i & 8) != 0) {
            this._numberBigInt = BigDecimal.valueOf(this._numberDouble).toBigInteger();
        } else if ((i & 32) != 0) {
            this._numberBigInt = BigDecimal.valueOf(this._numberFloat).toBigInteger();
        } else {
            _throwInternal();
            throw null;
        }
        this._numTypesValid |= 4;
    }

    protected void convertNumberToDouble() {
        int i = this._numTypesValid;
        if ((i & 16) != 0) {
            this._numberDouble = this._numberBigDecimal.doubleValue();
        } else if ((i & 32) != 0) {
            this._numberDouble = this._numberFloat;
        } else if ((i & 4) != 0) {
            this._numberDouble = this._numberBigInt.doubleValue();
        } else if ((i & 2) != 0) {
            this._numberDouble = this._numberLong;
        } else if ((i & 1) != 0) {
            this._numberDouble = this._numberInt;
        } else {
            _throwInternal();
            throw null;
        }
        this._numTypesValid |= 8;
    }

    protected void convertNumberToFloat() {
        int i = this._numTypesValid;
        if ((i & 16) != 0) {
            this._numberFloat = this._numberBigDecimal.floatValue();
        } else if ((i & 4) != 0) {
            this._numberFloat = this._numberBigInt.floatValue();
        } else if ((i & 8) != 0) {
            this._numberFloat = (float) this._numberDouble;
        } else if ((i & 2) != 0) {
            this._numberFloat = (float) this._numberLong;
        } else if ((i & 1) != 0) {
            this._numberFloat = this._numberInt;
        } else {
            _throwInternal();
            throw null;
        }
        this._numTypesValid |= 32;
    }

    protected void convertNumberToInt() {
        int i = this._numTypesValid;
        if ((i & 2) != 0) {
            long j = this._numberLong;
            int i2 = (int) j;
            if (i2 == j) {
                this._numberInt = i2;
            } else {
                _reportError("Numeric value (" + getText() + ") out of range of int");
                throw null;
            }
        } else if ((i & 4) != 0) {
            if (BI_MIN_INT.compareTo(this._numberBigInt) <= 0 && BI_MAX_INT.compareTo(this._numberBigInt) >= 0) {
                this._numberInt = this._numberBigInt.intValue();
            } else {
                reportOverflowInt();
                throw null;
            }
        } else if ((i & 8) != 0) {
            double d2 = this._numberDouble;
            if (d2 >= -2.147483648E9d && d2 <= 2.147483647E9d) {
                this._numberInt = (int) d2;
            } else {
                reportOverflowInt();
                throw null;
            }
        } else if ((i & 32) != 0) {
            float f2 = this._numberFloat;
            if (f2 >= -2.147483648E9d && f2 <= 2.147483647E9d) {
                this._numberInt = (int) f2;
            } else {
                reportOverflowInt();
                throw null;
            }
        } else if ((i & 16) != 0) {
            if (BD_MIN_INT.compareTo(this._numberBigDecimal) <= 0 && BD_MAX_INT.compareTo(this._numberBigDecimal) >= 0) {
                this._numberInt = this._numberBigDecimal.intValue();
            } else {
                reportOverflowInt();
                throw null;
            }
        } else {
            _throwInternal();
            throw null;
        }
        this._numTypesValid |= 1;
    }

    protected void convertNumberToLong() {
        int i = this._numTypesValid;
        if ((i & 1) != 0) {
            this._numberLong = this._numberInt;
        } else if ((i & 4) != 0) {
            if (BI_MIN_LONG.compareTo(this._numberBigInt) <= 0 && BI_MAX_LONG.compareTo(this._numberBigInt) >= 0) {
                this._numberLong = this._numberBigInt.longValue();
            } else {
                reportOverflowLong();
                throw null;
            }
        } else if ((i & 8) != 0) {
            double d2 = this._numberDouble;
            if (d2 >= -9.223372036854776E18d && d2 <= 9.223372036854776E18d) {
                this._numberLong = (long) d2;
            } else {
                reportOverflowLong();
                throw null;
            }
        } else if ((i & 32) != 0) {
            float f2 = this._numberFloat;
            if (f2 >= -9.223372036854776E18d && f2 <= 9.223372036854776E18d) {
                this._numberLong = f2;
            } else {
                reportOverflowInt();
                throw null;
            }
        } else if ((i & 16) != 0) {
            if (BD_MIN_LONG.compareTo(this._numberBigDecimal) <= 0 && BD_MAX_LONG.compareTo(this._numberBigDecimal) >= 0) {
                this._numberLong = this._numberBigDecimal.longValue();
            } else {
                reportOverflowLong();
                throw null;
            }
        } else {
            _throwInternal();
            throw null;
        }
        this._numTypesValid |= 2;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public BigInteger getBigIntegerValue() {
        int i = this._numTypesValid;
        if ((i & 4) == 0) {
            if (i == 0) {
                _checkNumericValue(4);
            }
            if ((this._numTypesValid & 4) == 0) {
                convertNumberToBigInteger();
            }
        }
        return this._numberBigInt;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) {
        if (this._tokenIncomplete) {
            _finishToken();
        }
        if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
            return this._binaryValue;
        }
        _reportError("Current token (" + getCurrentToken() + ") not VALUE_EMBEDDED_OBJECT, can not access as binary");
        throw null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonLocation getCurrentLocation() {
        long j = this._currInputProcessed + this._inputPtr;
        return new JsonLocation(this._ioContext.getSourceReference(), j, -1L, -1, (int) j);
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
        int i = this._numTypesValid;
        if ((i & 16) == 0) {
            if (i == 0) {
                _checkNumericValue(16);
            }
            if ((this._numTypesValid & 16) == 0) {
                convertNumberToBigDecimal();
            }
        }
        return this._numberBigDecimal;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public double getDoubleValue() {
        int i = this._numTypesValid;
        if ((i & 8) == 0) {
            if (i == 0) {
                _checkNumericValue(8);
            }
            if ((this._numTypesValid & 8) == 0) {
                convertNumberToDouble();
            }
        }
        return this._numberDouble;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Object getEmbeddedObject() {
        if (this._tokenIncomplete) {
            _finishToken();
        }
        if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
            return this._binaryValue;
        }
        return null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public float getFloatValue() {
        int i = this._numTypesValid;
        if ((i & 32) == 0) {
            if (i == 0) {
                _checkNumericValue(32);
            }
            if ((this._numTypesValid & 32) == 0) {
                convertNumberToFloat();
            }
        }
        return this._numberFloat;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getIntValue() {
        int i = this._numTypesValid;
        if ((i & 1) == 0) {
            if (i == 0) {
                _checkNumericValue(1);
            }
            if ((this._numTypesValid & 1) == 0) {
                convertNumberToInt();
            }
        }
        return this._numberInt;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long getLongValue() {
        int i = this._numTypesValid;
        if ((i & 2) == 0) {
            if (i == 0) {
                _checkNumericValue(2);
            }
            if ((this._numTypesValid & 2) == 0) {
                convertNumberToLong();
            }
        }
        return this._numberLong;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonParser.NumberType getNumberType() {
        if (this._numTypesValid == 0) {
            _checkNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            int i = this._numTypesValid;
            if ((i & 1) != 0) {
                return JsonParser.NumberType.INT;
            }
            if ((i & 2) != 0) {
                return JsonParser.NumberType.LONG;
            }
            return JsonParser.NumberType.BIG_INTEGER;
        }
        int i2 = this._numTypesValid;
        if ((i2 & 16) != 0) {
            return JsonParser.NumberType.BIG_DECIMAL;
        }
        if ((i2 & 8) != 0) {
            return JsonParser.NumberType.DOUBLE;
        }
        return JsonParser.NumberType.FLOAT;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Number getNumberValue() {
        if (this._numTypesValid == 0) {
            _checkNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            int i = this._numTypesValid;
            if ((i & 1) != 0) {
                return Integer.valueOf(this._numberInt);
            }
            if ((i & 2) != 0) {
                return Long.valueOf(this._numberLong);
            }
            if ((i & 4) != 0) {
                return this._numberBigInt;
            }
            return this._numberBigDecimal;
        }
        int i2 = this._numTypesValid;
        if ((i2 & 16) != 0) {
            return this._numberBigDecimal;
        }
        if ((i2 & 8) != 0) {
            return Double.valueOf(this._numberDouble);
        }
        if ((i2 & 32) != 0) {
            return Float.valueOf(this._numberFloat);
        }
        _throwInternal();
        throw null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String getText() {
        JsonToken jsonToken = this._currToken;
        if (this._tokenIncomplete && jsonToken == JsonToken.VALUE_STRING) {
            return _finishTextToken(this._typeByte);
        }
        if (jsonToken == JsonToken.VALUE_STRING) {
            return this._textBuffer.contentsAsString();
        }
        if (jsonToken == null) {
            return null;
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            return this._parsingContext.getCurrentName();
        }
        if (jsonToken.isNumeric()) {
            return getNumberValue().toString();
        }
        return this._currToken.asString();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public char[] getTextCharacters() {
        if (this._currToken == null) {
            return null;
        }
        if (this._tokenIncomplete) {
            _finishToken();
        }
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            return this._textBuffer.getTextBuffer();
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            return this._parsingContext.getCurrentName().toCharArray();
        }
        if (jsonToken != JsonToken.VALUE_NUMBER_INT && jsonToken != JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonToken.asCharArray();
        }
        return getNumberValue().toString().toCharArray();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getTextLength() {
        if (this._currToken == null) {
            return 0;
        }
        if (this._tokenIncomplete) {
            _finishToken();
        }
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            return this._textBuffer.size();
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            return this._parsingContext.getCurrentName().length();
        }
        if (jsonToken != JsonToken.VALUE_NUMBER_INT && jsonToken != JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonToken.asCharArray().length;
        }
        return getNumberValue().toString().length();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getTextOffset() {
        return 0;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonLocation getTokenLocation() {
        Object sourceReference = this._ioContext.getSourceReference();
        long j = this._tokenInputTotal;
        return new JsonLocation(sourceReference, j, -1L, -1, (int) j);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString() {
        if (this._tokenIncomplete && this._currToken == JsonToken.VALUE_STRING) {
            return _finishTextToken(this._typeByte);
        }
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            return this._textBuffer.contentsAsString();
        }
        if (jsonToken == null || jsonToken == JsonToken.VALUE_NULL || !jsonToken.isScalarValue()) {
            return null;
        }
        return getText();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean hasTextCharacters() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            return this._textBuffer.hasTextAsCharacters();
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            return this._nameCopied;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean isNaN() {
        if (this._currToken != JsonToken.VALUE_NUMBER_FLOAT) {
            return false;
        }
        int i = this._numTypesValid;
        if ((i & 8) != 0) {
            double d2 = this._numberDouble;
            return Double.isNaN(d2) || Double.isInfinite(d2);
        }
        if ((i & 32) == 0) {
            return false;
        }
        float f2 = this._numberFloat;
        return Float.isNaN(f2) || Float.isInfinite(f2);
    }

    protected final boolean loadMore() {
        InputStream inputStream = this._inputStream;
        if (inputStream != null) {
            this._currInputProcessed += this._inputEnd;
            byte[] bArr = this._inputBuffer;
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read > 0) {
                this._inputPtr = 0;
                this._inputEnd = read;
                return true;
            }
            _closeInput();
            if (read == 0) {
                throw new IOException("InputStream.read() returned 0 characters when trying to read " + this._inputBuffer.length + " bytes");
            }
        }
        return false;
    }

    protected final void loadMoreGuaranteed() {
        if (loadMore()) {
            return;
        }
        _reportInvalidEOF();
        throw null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String nextFieldName() {
        String _decodeLongerName;
        if (this._parsingContext.inObject() && this._currToken != JsonToken.FIELD_NAME) {
            this._numTypesValid = 0;
            if (this._tokenIncomplete) {
                _skipIncomplete();
            }
            this._tokenInputTotal = this._currInputProcessed + this._inputPtr;
            this._binaryValue = null;
            this._tagValue = -1;
            if (!this._parsingContext.expectMoreValues()) {
                this._parsingContext = this._parsingContext.getParent();
                this._currToken = JsonToken.END_OBJECT;
                return null;
            }
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            byte b2 = bArr[i];
            if (((b2 >> 5) & 7) != 3) {
                if (b2 == -1) {
                    if (!this._parsingContext.hasExpectedLength()) {
                        this._parsingContext = this._parsingContext.getParent();
                        this._currToken = JsonToken.END_OBJECT;
                        return null;
                    }
                    _reportUnexpectedBreak();
                    throw null;
                }
                _decodeNonStringName(b2);
                this._currToken = JsonToken.FIELD_NAME;
                return getText();
            }
            int i2 = b2 & 31;
            if (i2 > 23) {
                int _decodeExplicitLength = _decodeExplicitLength(i2);
                if (_decodeExplicitLength < 0) {
                    _decodeLongerName = _decodeChunkedName();
                } else {
                    _decodeLongerName = _decodeLongerName(_decodeExplicitLength);
                }
            } else if (i2 == 0) {
                _decodeLongerName = "";
            } else {
                String _findDecodedFromSymbols = _findDecodedFromSymbols(i2);
                if (_findDecodedFromSymbols != null) {
                    this._inputPtr += i2;
                    _decodeLongerName = _findDecodedFromSymbols;
                } else {
                    _decodeLongerName = _addDecodedToSymbols(i2, _decodeShortName(i2));
                }
            }
            this._parsingContext.setCurrentName(_decodeLongerName);
            this._currToken = JsonToken.FIELD_NAME;
            return _decodeLongerName;
        }
        if (nextToken() == JsonToken.FIELD_NAME) {
            return getCurrentName();
        }
        return null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String nextTextValue() {
        this._numTypesValid = 0;
        if (this._tokenIncomplete) {
            _skipIncomplete();
        }
        this._tokenInputTotal = this._currInputProcessed + this._inputPtr;
        this._binaryValue = null;
        this._tagValue = -1;
        if (this._parsingContext.inObject()) {
            if (this._currToken != JsonToken.FIELD_NAME) {
                this._tagValue = -1;
                if (!this._parsingContext.expectMoreValues()) {
                    this._parsingContext = this._parsingContext.getParent();
                    this._currToken = JsonToken.END_OBJECT;
                    return null;
                }
                this._currToken = _decodeFieldName();
                return null;
            }
        } else if (!this._parsingContext.expectMoreValues()) {
            this._tagValue = -1;
            this._parsingContext = this._parsingContext.getParent();
            this._currToken = JsonToken.END_ARRAY;
            return null;
        }
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _handleCBOREOF();
            return null;
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b2 = bArr[i];
        int i2 = (b2 >> 5) & 7;
        if (i2 == 6) {
            this._tagValue = Integer.valueOf(_decodeTag(b2 & 31)).intValue();
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _handleCBOREOF();
                return null;
            }
            byte[] bArr2 = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            b2 = bArr2[i3];
            i2 = (b2 >> 5) & 7;
        } else {
            this._tagValue = -1;
        }
        int i4 = b2 & 31;
        switch (i2) {
            case 0:
                this._numTypesValid = 1;
                if (i4 <= 23) {
                    this._numberInt = i4;
                } else {
                    int i5 = i4 - 24;
                    if (i5 == 0) {
                        this._numberInt = _decode8Bits();
                    } else if (i5 == 1) {
                        this._numberInt = _decode16Bits();
                    } else if (i5 == 2) {
                        int _decode32Bits = _decode32Bits();
                        if (_decode32Bits < 0) {
                            this._numberLong = _decode32Bits & 4294967295L;
                            this._numTypesValid = 2;
                        } else {
                            this._numberInt = _decode32Bits;
                        }
                    } else if (i5 == 3) {
                        long _decode64Bits = _decode64Bits();
                        if (_decode64Bits >= 0) {
                            this._numberLong = _decode64Bits;
                            this._numTypesValid = 2;
                        } else {
                            this._numberBigInt = _bigPositive(_decode64Bits);
                            this._numTypesValid = 4;
                        }
                    } else {
                        _invalidToken(b2);
                        throw null;
                    }
                }
                this._currToken = JsonToken.VALUE_NUMBER_INT;
                return null;
            case 1:
                this._numTypesValid = 1;
                if (i4 <= 23) {
                    this._numberInt = (-i4) - 1;
                } else {
                    int i6 = i4 - 24;
                    if (i6 == 0) {
                        this._numberInt = (-_decode8Bits()) - 1;
                    } else if (i6 == 1) {
                        this._numberInt = (-_decode16Bits()) - 1;
                    } else if (i6 == 2) {
                        int _decode32Bits2 = _decode32Bits();
                        if (_decode32Bits2 < 0) {
                            this._numberLong = (-(_decode32Bits2 & 4294967295L)) - 1;
                            this._numTypesValid = 2;
                        } else {
                            this._numberInt = (-_decode32Bits2) - 1;
                        }
                    } else if (i6 == 3) {
                        long _decode64Bits2 = _decode64Bits();
                        if (_decode64Bits2 >= 0) {
                            this._numberLong = _decode64Bits2;
                            this._numTypesValid = 2;
                        } else {
                            this._numberBigInt = _bigNegative(_decode64Bits2);
                            this._numTypesValid = 4;
                        }
                    } else {
                        _invalidToken(b2);
                        throw null;
                    }
                }
                this._currToken = JsonToken.VALUE_NUMBER_INT;
                return null;
            case 2:
                this._typeByte = b2;
                this._tokenIncomplete = true;
                this._currToken = JsonToken.VALUE_EMBEDDED_OBJECT;
                return null;
            case 3:
                this._typeByte = b2;
                this._tokenIncomplete = true;
                this._currToken = JsonToken.VALUE_STRING;
                return _finishTextToken(b2);
            case 4:
                this._currToken = JsonToken.START_ARRAY;
                this._parsingContext = this._parsingContext.createChildArrayContext(_decodeExplicitLength(i4));
                return null;
            case 5:
                this._currToken = JsonToken.START_OBJECT;
                this._parsingContext = this._parsingContext.createChildObjectContext(_decodeExplicitLength(i4));
                return null;
            case 6:
                _reportError("Multiple tags not allowed per value (first tag: " + this._tagValue + ")");
                throw null;
            default:
                switch (i4) {
                    case 20:
                        this._currToken = JsonToken.VALUE_FALSE;
                        return null;
                    case 21:
                        this._currToken = JsonToken.VALUE_TRUE;
                        return null;
                    case 22:
                        this._currToken = JsonToken.VALUE_NULL;
                        return null;
                    case 23:
                        this._currToken = _decodeUndefinedValue();
                        return null;
                    case 24:
                    case 28:
                    case 29:
                    case 30:
                    default:
                        _invalidToken(b2);
                        throw null;
                    case 25:
                        this._numberFloat = _decodeHalfSizeFloat();
                        this._numTypesValid = 32;
                        this._currToken = JsonToken.VALUE_NUMBER_FLOAT;
                        return null;
                    case 26:
                        this._numberFloat = Float.intBitsToFloat(_decode32Bits());
                        this._numTypesValid = 32;
                        this._currToken = JsonToken.VALUE_NUMBER_FLOAT;
                        return null;
                    case 27:
                        this._numberDouble = Double.longBitsToDouble(_decode64Bits());
                        this._numTypesValid = 8;
                        this._currToken = JsonToken.VALUE_NUMBER_FLOAT;
                        return null;
                    case 31:
                        if (this._parsingContext.inArray() && !this._parsingContext.hasExpectedLength()) {
                            this._parsingContext = this._parsingContext.getParent();
                            this._currToken = JsonToken.END_ARRAY;
                            return null;
                        }
                        _reportUnexpectedBreak();
                        throw null;
                }
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken nextToken() {
        this._numTypesValid = 0;
        if (this._tokenIncomplete) {
            _skipIncomplete();
        }
        this._tokenInputTotal = this._currInputProcessed + this._inputPtr;
        this._binaryValue = null;
        if (this._parsingContext.inObject()) {
            if (this._currToken != JsonToken.FIELD_NAME) {
                this._tagValue = -1;
                if (!this._parsingContext.expectMoreValues()) {
                    this._parsingContext = this._parsingContext.getParent();
                    JsonToken jsonToken = JsonToken.END_OBJECT;
                    this._currToken = jsonToken;
                    return jsonToken;
                }
                JsonToken _decodeFieldName = _decodeFieldName();
                this._currToken = _decodeFieldName;
                return _decodeFieldName;
            }
        } else if (!this._parsingContext.expectMoreValues()) {
            this._tagValue = -1;
            this._parsingContext = this._parsingContext.getParent();
            JsonToken jsonToken2 = JsonToken.END_ARRAY;
            this._currToken = jsonToken2;
            return jsonToken2;
        }
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            return _handleCBOREOF();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b2 = bArr[i];
        int i2 = (b2 >> 5) & 7;
        if (i2 == 6) {
            this._tagValue = Integer.valueOf(_decodeTag(b2 & 31)).intValue();
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                return _handleCBOREOF();
            }
            byte[] bArr2 = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            b2 = bArr2[i3];
            i2 = (b2 >> 5) & 7;
        } else {
            this._tagValue = -1;
        }
        int i4 = b2 & 31;
        switch (i2) {
            case 0:
                this._numTypesValid = 1;
                if (i4 <= 23) {
                    this._numberInt = i4;
                } else {
                    int i5 = i4 - 24;
                    if (i5 == 0) {
                        this._numberInt = _decode8Bits();
                    } else if (i5 == 1) {
                        this._numberInt = _decode16Bits();
                    } else if (i5 == 2) {
                        int _decode32Bits = _decode32Bits();
                        if (_decode32Bits >= 0) {
                            this._numberInt = _decode32Bits;
                        } else {
                            this._numberLong = _decode32Bits & 4294967295L;
                            this._numTypesValid = 2;
                        }
                    } else if (i5 == 3) {
                        long _decode64Bits = _decode64Bits();
                        if (_decode64Bits >= 0) {
                            this._numberLong = _decode64Bits;
                            this._numTypesValid = 2;
                        } else {
                            this._numberBigInt = _bigPositive(_decode64Bits);
                            this._numTypesValid = 4;
                        }
                    } else {
                        _invalidToken(b2);
                        throw null;
                    }
                }
                JsonToken jsonToken3 = JsonToken.VALUE_NUMBER_INT;
                this._currToken = jsonToken3;
                return jsonToken3;
            case 1:
                this._numTypesValid = 1;
                if (i4 <= 23) {
                    this._numberInt = (-i4) - 1;
                } else {
                    int i6 = i4 - 24;
                    if (i6 == 0) {
                        this._numberInt = (-_decode8Bits()) - 1;
                    } else if (i6 == 1) {
                        this._numberInt = (-_decode16Bits()) - 1;
                    } else if (i6 == 2) {
                        int _decode32Bits2 = _decode32Bits();
                        if (_decode32Bits2 < 0) {
                            this._numberLong = (-(_decode32Bits2 & 4294967295L)) - 1;
                            this._numTypesValid = 2;
                        } else {
                            this._numberInt = (-_decode32Bits2) - 1;
                        }
                    } else if (i6 == 3) {
                        long _decode64Bits2 = _decode64Bits();
                        if (_decode64Bits2 >= 0) {
                            this._numberLong = (-_decode64Bits2) - 1;
                            this._numTypesValid = 2;
                        } else {
                            this._numberBigInt = _bigNegative(_decode64Bits2);
                            this._numTypesValid = 4;
                        }
                    } else {
                        _invalidToken(b2);
                        throw null;
                    }
                }
                JsonToken jsonToken4 = JsonToken.VALUE_NUMBER_INT;
                this._currToken = jsonToken4;
                return jsonToken4;
            case 2:
                this._typeByte = b2;
                this._tokenIncomplete = true;
                int i7 = this._tagValue;
                if (i7 >= 0) {
                    return _handleTaggedBinary(i7);
                }
                JsonToken jsonToken5 = JsonToken.VALUE_EMBEDDED_OBJECT;
                this._currToken = jsonToken5;
                return jsonToken5;
            case 3:
                this._typeByte = b2;
                this._tokenIncomplete = true;
                JsonToken jsonToken6 = JsonToken.VALUE_STRING;
                this._currToken = jsonToken6;
                return jsonToken6;
            case 4:
                int _decodeExplicitLength = _decodeExplicitLength(i4);
                int i8 = this._tagValue;
                if (i8 >= 0) {
                    return _handleTaggedArray(i8, _decodeExplicitLength);
                }
                this._parsingContext = this._parsingContext.createChildArrayContext(_decodeExplicitLength);
                JsonToken jsonToken7 = JsonToken.START_ARRAY;
                this._currToken = jsonToken7;
                return jsonToken7;
            case 5:
                this._currToken = JsonToken.START_OBJECT;
                this._parsingContext = this._parsingContext.createChildObjectContext(_decodeExplicitLength(i4));
                return this._currToken;
            case 6:
                _reportError("Multiple tags not allowed per value (first tag: " + this._tagValue + ")");
                throw null;
            default:
                switch (i4) {
                    case 20:
                        JsonToken jsonToken8 = JsonToken.VALUE_FALSE;
                        this._currToken = jsonToken8;
                        return jsonToken8;
                    case 21:
                        JsonToken jsonToken9 = JsonToken.VALUE_TRUE;
                        this._currToken = jsonToken9;
                        return jsonToken9;
                    case 22:
                        JsonToken jsonToken10 = JsonToken.VALUE_NULL;
                        this._currToken = jsonToken10;
                        return jsonToken10;
                    case 23:
                        JsonToken _decodeUndefinedValue = _decodeUndefinedValue();
                        this._currToken = _decodeUndefinedValue;
                        return _decodeUndefinedValue;
                    case 24:
                    case 28:
                    case 29:
                    case 30:
                    default:
                        _invalidToken(b2);
                        throw null;
                    case 25:
                        this._numberFloat = _decodeHalfSizeFloat();
                        this._numTypesValid = 32;
                        JsonToken jsonToken11 = JsonToken.VALUE_NUMBER_FLOAT;
                        this._currToken = jsonToken11;
                        return jsonToken11;
                    case 26:
                        this._numberFloat = Float.intBitsToFloat(_decode32Bits());
                        this._numTypesValid = 32;
                        JsonToken jsonToken12 = JsonToken.VALUE_NUMBER_FLOAT;
                        this._currToken = jsonToken12;
                        return jsonToken12;
                    case 27:
                        this._numberDouble = Double.longBitsToDouble(_decode64Bits());
                        this._numTypesValid = 8;
                        JsonToken jsonToken13 = JsonToken.VALUE_NUMBER_FLOAT;
                        this._currToken = jsonToken13;
                        return jsonToken13;
                    case 31:
                        if (this._parsingContext.inArray() && !this._parsingContext.hasExpectedLength()) {
                            this._parsingContext = this._parsingContext.getParent();
                            JsonToken jsonToken14 = JsonToken.END_ARRAY;
                            this._currToken = jsonToken14;
                            return jsonToken14;
                        }
                        _reportUnexpectedBreak();
                        throw null;
                }
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) {
        if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
            int i = 0;
            if (!this._tokenIncomplete) {
                byte[] bArr = this._binaryValue;
                if (bArr == null) {
                    return 0;
                }
                int length = bArr.length;
                outputStream.write(bArr, 0, length);
                return length;
            }
            this._tokenIncomplete = false;
            int _decodeExplicitLength = _decodeExplicitLength(this._typeByte & 31);
            if (_decodeExplicitLength >= 0) {
                _readAndWriteBytes(outputStream, _decodeExplicitLength);
                return _decodeExplicitLength;
            }
            while (true) {
                int _decodeChunkLength = _decodeChunkLength(2);
                if (_decodeChunkLength < 0) {
                    return i;
                }
                _readAndWriteBytes(outputStream, _decodeChunkLength);
                i += _decodeChunkLength;
            }
        } else {
            _reportError("Current token (" + getCurrentToken() + ") not VALUE_EMBEDDED_OBJECT, can not access as binary");
            throw null;
        }
    }

    protected void _reportInvalidOther(int i, int i2) {
        this._inputPtr = i2;
        _reportInvalidOther(i);
        throw null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public CBORReadContext getParsingContext() {
        return this._parsingContext;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString(String str) {
        JsonToken jsonToken = this._currToken;
        return (jsonToken == JsonToken.VALUE_STRING || !(jsonToken == null || jsonToken == JsonToken.VALUE_NULL || !jsonToken.isScalarValue())) ? getText() : str;
    }
}
