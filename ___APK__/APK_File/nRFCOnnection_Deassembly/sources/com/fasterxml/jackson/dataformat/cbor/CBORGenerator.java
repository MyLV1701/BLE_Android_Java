package com.fasterxml.jackson.dataformat.cbor;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.DupDetector;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import no.nordicsemi.android.mcp.ble.parser.AppearanceLibrary;

/* loaded from: classes.dex */
public class CBORGenerator extends GeneratorBase {
    private static final int[] NO_INTS = new int[0];
    protected boolean _bufferRecyclable;
    protected int _bytesWritten;
    protected CBORWriteContext _cborContext;
    protected boolean _cfgMinimalInts;
    protected char[] _charBuffer;
    protected int _currentRemainingElements;
    protected int[] _elementCounts;
    protected int _elementCountsPtr;
    protected int _formatFeatures;
    protected final IOContext _ioContext;
    protected final OutputStream _out;
    protected byte[] _outputBuffer;
    protected final int _outputEnd;
    protected int _outputTail;

    /* loaded from: classes.dex */
    public enum Feature implements FormatFeature {
        WRITE_MINIMAL_INTS(true),
        WRITE_TYPE_HEADER(false);

        protected final boolean _defaultState;
        protected final int _mask = 1 << ordinal();

        Feature(boolean z) {
            this._defaultState = z;
        }

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

        public boolean enabledIn(int i) {
            return (i & getMask()) != 0;
        }

        public int getMask() {
            return this._mask;
        }
    }

    public CBORGenerator(IOContext iOContext, int i, int i2, ObjectCodec objectCodec, OutputStream outputStream) {
        super(i, objectCodec, null);
        this._outputTail = 0;
        this._elementCounts = NO_INTS;
        this._currentRemainingElements = -2;
        this._cborContext = CBORWriteContext.createRootContext(JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(i) ? DupDetector.rootDetector(this) : null);
        this._formatFeatures = i2;
        this._cfgMinimalInts = Feature.WRITE_MINIMAL_INTS.enabledIn(i2);
        this._ioContext = iOContext;
        this._out = outputStream;
        this._bufferRecyclable = true;
        this._outputBuffer = iOContext.allocWriteEncodingBuffer(16000);
        this._outputEnd = this._outputBuffer.length;
        this._charBuffer = iOContext.allocConcatBuffer();
        int length = this._charBuffer.length;
        if (this._outputEnd >= 770) {
            return;
        }
        throw new IllegalStateException("Internal encoding buffer length (" + this._outputEnd + ") too short, must be at least 770");
    }

    private int _convertSurrogate(int i, int i2) {
        if (i2 >= 56320 && i2 <= 57343) {
            return ((i - 55296) << 10) + 65536 + (i2 - 56320);
        }
        throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(i2) + "; illegal combination");
    }

    private final int _encode(int i, char[] cArr, int i2, int i3) {
        byte[] bArr = this._outputBuffer;
        int i4 = i;
        int i5 = i2;
        while (true) {
            char c2 = cArr[i5];
            if (c2 > 127) {
                return _shortUTF8Encode2(cArr, i5, i3, i4, i);
            }
            int i6 = i4 + 1;
            bArr[i4] = (byte) c2;
            i5++;
            if (i5 >= i3) {
                return i6 - i;
            }
            i4 = i6;
        }
    }

    private final int _encode2(int i, int i2, String str, int i3, int i4) {
        int i5;
        byte[] bArr = this._outputBuffer;
        while (i < i3) {
            int i6 = i + 1;
            char charAt = str.charAt(i);
            if (charAt <= 127) {
                i5 = i2 + 1;
                bArr[i2] = (byte) charAt;
            } else if (charAt < 2048) {
                int i7 = i2 + 1;
                bArr[i2] = (byte) ((charAt >> 6) | AppearanceLibrary.APPEARANCE_GENERIC_WATCH);
                i2 = i7 + 1;
                bArr[i7] = (byte) ((charAt & '?') | 128);
                i = i6;
            } else if (charAt < 55296 || charAt > 57343) {
                int i8 = i2 + 1;
                bArr[i2] = (byte) ((charAt >> '\f') | 224);
                int i9 = i8 + 1;
                bArr[i8] = (byte) (((charAt >> 6) & 63) | 128);
                i5 = i9 + 1;
                bArr[i9] = (byte) ((charAt & '?') | 128);
            } else {
                if (charAt > 56319) {
                    _throwIllegalSurrogate(charAt);
                    throw null;
                }
                if (i6 < i3) {
                    int i10 = i6 + 1;
                    int _convertSurrogate = _convertSurrogate(charAt, str.charAt(i6));
                    if (_convertSurrogate <= 1114111) {
                        int i11 = i2 + 1;
                        bArr[i2] = (byte) ((_convertSurrogate >> 18) | 240);
                        int i12 = i11 + 1;
                        bArr[i11] = (byte) (((_convertSurrogate >> 12) & 63) | 128);
                        int i13 = i12 + 1;
                        bArr[i12] = (byte) (((_convertSurrogate >> 6) & 63) | 128);
                        i2 = i13 + 1;
                        bArr[i13] = (byte) ((_convertSurrogate & 63) | 128);
                        i = i10;
                    } else {
                        _throwIllegalSurrogate(_convertSurrogate);
                        throw null;
                    }
                } else {
                    _throwIllegalSurrogate(charAt);
                    throw null;
                }
            }
            i = i6;
            i2 = i5;
        }
        return i2 - i4;
    }

    private final void _ensureRoomForOutput(int i) {
        if (this._outputTail + i >= this._outputEnd) {
            _flushBuffer();
        }
    }

    private void _failSizedArrayOrObject() {
        _reportError(String.format("%s size mismatch: number of element encoded is not equal to reported array/map size.", this._cborContext.typeDesc()));
        throw null;
    }

    private final void _pushRemainingElements() {
        int[] iArr = this._elementCounts;
        if (iArr.length == this._elementCountsPtr) {
            this._elementCounts = Arrays.copyOf(iArr, iArr.length + 10);
        }
        int[] iArr2 = this._elementCounts;
        int i = this._elementCountsPtr;
        this._elementCountsPtr = i + 1;
        iArr2[i] = this._currentRemainingElements;
    }

    private final int _shortUTF8Encode2(char[] cArr, int i, int i2, int i3, int i4) {
        int i5;
        byte[] bArr = this._outputBuffer;
        while (i < i2) {
            int i6 = i + 1;
            char c2 = cArr[i];
            if (c2 <= 127) {
                i5 = i3 + 1;
                bArr[i3] = (byte) c2;
            } else if (c2 < 2048) {
                int i7 = i3 + 1;
                bArr[i3] = (byte) ((c2 >> 6) | AppearanceLibrary.APPEARANCE_GENERIC_WATCH);
                i3 = i7 + 1;
                bArr[i7] = (byte) ((c2 & '?') | 128);
                i = i6;
            } else if (c2 < 55296 || c2 > 57343) {
                int i8 = i3 + 1;
                bArr[i3] = (byte) ((c2 >> '\f') | 224);
                int i9 = i8 + 1;
                bArr[i8] = (byte) (((c2 >> 6) & 63) | 128);
                i5 = i9 + 1;
                bArr[i9] = (byte) ((c2 & '?') | 128);
            } else {
                if (c2 > 56319) {
                    _throwIllegalSurrogate(c2);
                    throw null;
                }
                if (i6 < i2) {
                    int i10 = i6 + 1;
                    int _convertSurrogate = _convertSurrogate(c2, cArr[i6]);
                    if (_convertSurrogate <= 1114111) {
                        int i11 = i3 + 1;
                        bArr[i3] = (byte) ((_convertSurrogate >> 18) | 240);
                        int i12 = i11 + 1;
                        bArr[i11] = (byte) (((_convertSurrogate >> 12) & 63) | 128);
                        int i13 = i12 + 1;
                        bArr[i12] = (byte) (((_convertSurrogate >> 6) & 63) | 128);
                        i3 = i13 + 1;
                        bArr[i13] = (byte) ((_convertSurrogate & 63) | 128);
                        i = i10;
                    } else {
                        _throwIllegalSurrogate(_convertSurrogate);
                        throw null;
                    }
                } else {
                    _throwIllegalSurrogate(c2);
                    throw null;
                }
            }
            i = i6;
            i3 = i5;
        }
        return i3 - i4;
    }

    private void _throwIllegalSurrogate(int i) {
        if (i > 1114111) {
            throw new IllegalArgumentException("Illegal character point (0x" + Integer.toHexString(i) + ") to output; max is 0x10FFFF as per RFC 4627");
        }
        if (i < 55296) {
            throw new IllegalArgumentException("Illegal character point (0x" + Integer.toHexString(i) + ") to output");
        }
        if (i <= 56319) {
            throw new IllegalArgumentException("Unmatched first part of surrogate pair (0x" + Integer.toHexString(i) + ")");
        }
        throw new IllegalArgumentException("Unmatched second part of surrogate pair (0x" + Integer.toHexString(i) + ")");
    }

    private final void _writeByte(byte b2) {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b2;
    }

    private final void _writeBytes(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return;
        }
        int i3 = this._outputTail;
        if (i3 + i2 >= this._outputEnd) {
            _writeBytesLong(bArr, i, i2);
        } else {
            System.arraycopy(bArr, i, this._outputBuffer, i3, i2);
            this._outputTail += i2;
        }
    }

    private final void _writeBytesLong(byte[] bArr, int i, int i2) {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        while (true) {
            int min = Math.min(i2, this._outputEnd - this._outputTail);
            System.arraycopy(bArr, i, this._outputBuffer, this._outputTail, min);
            this._outputTail += min;
            i2 -= min;
            if (i2 == 0) {
                return;
            }
            i += min;
            _flushBuffer();
        }
    }

    private final void _writeIntValue(int i) {
        int i2;
        if (i < 0) {
            i = (-i) - 1;
            i2 = 32;
        } else {
            i2 = 0;
        }
        _writeLengthMarker(i2, i);
    }

    private final void _writeLengthMarker(int i, int i2) {
        _ensureRoomForOutput(5);
        if (i2 < 24) {
            byte[] bArr = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr[i3] = (byte) (i + i2);
            return;
        }
        if (i2 <= 255) {
            byte[] bArr2 = this._outputBuffer;
            int i4 = this._outputTail;
            this._outputTail = i4 + 1;
            bArr2[i4] = (byte) (i + 24);
            int i5 = this._outputTail;
            this._outputTail = i5 + 1;
            bArr2[i5] = (byte) i2;
            return;
        }
        byte b2 = (byte) i2;
        int i6 = i2 >> 8;
        if (i6 <= 255) {
            byte[] bArr3 = this._outputBuffer;
            int i7 = this._outputTail;
            this._outputTail = i7 + 1;
            bArr3[i7] = (byte) (i + 25);
            int i8 = this._outputTail;
            this._outputTail = i8 + 1;
            bArr3[i8] = (byte) i6;
            int i9 = this._outputTail;
            this._outputTail = i9 + 1;
            bArr3[i9] = b2;
            return;
        }
        byte[] bArr4 = this._outputBuffer;
        int i10 = this._outputTail;
        this._outputTail = i10 + 1;
        bArr4[i10] = (byte) (i + 26);
        int i11 = this._outputTail;
        this._outputTail = i11 + 1;
        bArr4[i11] = (byte) (i6 >> 16);
        int i12 = this._outputTail;
        this._outputTail = i12 + 1;
        bArr4[i12] = (byte) (i6 >> 8);
        int i13 = this._outputTail;
        this._outputTail = i13 + 1;
        bArr4[i13] = (byte) i6;
        int i14 = this._outputTail;
        this._outputTail = i14 + 1;
        bArr4[i14] = b2;
    }

    private final void _writeLongValue(long j) {
        _ensureRoomForOutput(9);
        if (j < 0) {
            j = -(j + 1);
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = 59;
        } else {
            byte[] bArr2 = this._outputBuffer;
            int i2 = this._outputTail;
            this._outputTail = i2 + 1;
            bArr2[i2] = 27;
        }
        int i3 = (int) (j >> 32);
        byte[] bArr3 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr3[i4] = (byte) (i3 >> 24);
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr3[i5] = (byte) (i3 >> 16);
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        bArr3[i6] = (byte) (i3 >> 8);
        int i7 = this._outputTail;
        this._outputTail = i7 + 1;
        bArr3[i7] = (byte) i3;
        int i8 = (int) j;
        int i9 = this._outputTail;
        this._outputTail = i9 + 1;
        bArr3[i9] = (byte) (i8 >> 24);
        int i10 = this._outputTail;
        this._outputTail = i10 + 1;
        bArr3[i10] = (byte) (i8 >> 16);
        int i11 = this._outputTail;
        this._outputTail = i11 + 1;
        bArr3[i11] = (byte) (i8 >> 8);
        int i12 = this._outputTail;
        this._outputTail = i12 + 1;
        bArr3[i12] = (byte) i8;
    }

    private final void _writeNumberNoCheck(int i) {
        int i2;
        byte b2;
        int i3;
        if (i < 0) {
            i = (-i) - 1;
            i2 = 32;
        } else {
            i2 = 0;
        }
        _ensureRoomForOutput(5);
        if (!this._cfgMinimalInts) {
            b2 = (byte) i;
            i3 = i >> 8;
        } else {
            if (i < 24) {
                byte[] bArr = this._outputBuffer;
                int i4 = this._outputTail;
                this._outputTail = i4 + 1;
                bArr[i4] = (byte) (i2 + i);
                return;
            }
            if (i <= 255) {
                byte[] bArr2 = this._outputBuffer;
                int i5 = this._outputTail;
                this._outputTail = i5 + 1;
                bArr2[i5] = (byte) (i2 + 24);
                int i6 = this._outputTail;
                this._outputTail = i6 + 1;
                bArr2[i6] = (byte) i;
                return;
            }
            b2 = (byte) i;
            i3 = i >> 8;
            if (i3 <= 255) {
                byte[] bArr3 = this._outputBuffer;
                int i7 = this._outputTail;
                this._outputTail = i7 + 1;
                bArr3[i7] = (byte) (i2 + 25);
                int i8 = this._outputTail;
                this._outputTail = i8 + 1;
                bArr3[i8] = (byte) i3;
                int i9 = this._outputTail;
                this._outputTail = i9 + 1;
                bArr3[i9] = b2;
                return;
            }
        }
        byte[] bArr4 = this._outputBuffer;
        int i10 = this._outputTail;
        this._outputTail = i10 + 1;
        bArr4[i10] = (byte) (i2 + 26);
        int i11 = this._outputTail;
        this._outputTail = i11 + 1;
        bArr4[i11] = (byte) (i3 >> 16);
        int i12 = this._outputTail;
        this._outputTail = i12 + 1;
        bArr4[i12] = (byte) (i3 >> 8);
        int i13 = this._outputTail;
        this._outputTail = i13 + 1;
        bArr4[i13] = (byte) i3;
        int i14 = this._outputTail;
        this._outputTail = i14 + 1;
        bArr4[i14] = b2;
    }

    private final void closeComplexElement() {
        int i = this._currentRemainingElements;
        int i2 = -2;
        if (i == -2) {
            _writeByte((byte) -1);
        } else if (i != 0) {
            _reportError(String.format("%s size mismatch: expected %d more elements", this._cborContext.typeDesc(), Integer.valueOf(this._currentRemainingElements)));
            throw null;
        }
        int i3 = this._elementCountsPtr;
        if (i3 != 0) {
            int[] iArr = this._elementCounts;
            int i4 = i3 - 1;
            this._elementCountsPtr = i4;
            i2 = iArr[i4];
        }
        this._currentRemainingElements = i2;
    }

    protected final void _ensureSpace(int i) {
        if (this._outputTail + i + 3 > this._outputEnd) {
            _flushBuffer();
        }
    }

    protected final void _flushBuffer() {
        int i = this._outputTail;
        if (i > 0) {
            this._bytesWritten += i;
            this._out.write(this._outputBuffer, 0, i);
            this._outputTail = 0;
        }
    }

    protected UnsupportedOperationException _notSupported() {
        return new UnsupportedOperationException();
    }

    protected void _releaseBuffers() {
        byte[] bArr = this._outputBuffer;
        if (bArr != null && this._bufferRecyclable) {
            this._outputBuffer = null;
            this._ioContext.releaseWriteEncodingBuffer(bArr);
        }
        char[] cArr = this._charBuffer;
        if (cArr != null) {
            this._charBuffer = null;
            this._ioContext.releaseConcatBuffer(cArr);
        }
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase
    protected final void _verifyValueWrite(String str) {
        if (this._cborContext.writeValue()) {
            int i = this._currentRemainingElements;
            if (i != -2) {
                int i2 = i - 1;
                if (i2 >= 0) {
                    this._currentRemainingElements = i2;
                    return;
                } else {
                    _failSizedArrayOrObject();
                    throw null;
                }
            }
            return;
        }
        _reportError("Can not " + str + ", expecting field name/id");
        throw null;
    }

    protected void _write(BigInteger bigInteger) {
        if (bigInteger.signum() < 0) {
            _writeByte((byte) -61);
            bigInteger = bigInteger.negate();
        } else {
            _writeByte((byte) -62);
        }
        byte[] byteArray = bigInteger.toByteArray();
        int length = byteArray.length;
        _writeLengthMarker(64, length);
        _writeBytes(byteArray, 0, length);
    }

    protected final void _writeChunkedString(char[] cArr, int i, int i2) {
        _writeByte(Byte.MAX_VALUE);
        while (true) {
            int i3 = 3996;
            if (i2 <= 3996) {
                break;
            }
            _ensureSpace(11991);
            int i4 = this._outputTail;
            int i5 = i + 3996;
            char c2 = cArr[i5 - 1];
            if (c2 >= 55296 && c2 <= 56319) {
                i5--;
                i3 = 3995;
            }
            int _encode = _encode(this._outputTail + 3, cArr, i, i5);
            byte[] bArr = this._outputBuffer;
            int i6 = i4 + 1;
            bArr[i4] = 121;
            int i7 = i6 + 1;
            bArr[i6] = (byte) (_encode >> 8);
            bArr[i7] = (byte) _encode;
            this._outputTail = i7 + 1 + _encode;
            i += i3;
            i2 -= i3;
        }
        if (i2 > 0) {
            _writeString(cArr, i, i2);
        }
        _writeByte((byte) -1);
    }

    protected final void _writeString(String str) {
        int length = str.length();
        if (length == 0) {
            _writeByte((byte) 96);
            return;
        }
        if (length <= 23) {
            _ensureSpace(71);
            int _encode = _encode(this._outputTail + 1, str, length);
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            if (_encode <= 23) {
                bArr[i] = (byte) (_encode + 96);
                this._outputTail = i + 1 + _encode;
                return;
            }
            int i2 = i + 1;
            System.arraycopy(bArr, i2, bArr, i + 2, _encode);
            bArr[i] = 120;
            bArr[i2] = (byte) _encode;
            this._outputTail = i2 + 1 + _encode;
            return;
        }
        char[] cArr = this._charBuffer;
        if (length > cArr.length) {
            cArr = new char[Math.max(cArr.length + 32, length)];
            this._charBuffer = cArr;
        }
        str.getChars(0, length, cArr, 0);
        _writeString(cArr, 0, length);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public boolean canWriteBinaryNatively() {
        return true;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this._outputBuffer != null && isEnabled(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT)) {
            while (true) {
                JsonStreamContext outputContext = getOutputContext();
                if (outputContext.inArray()) {
                    writeEndArray();
                } else if (!outputContext.inObject()) {
                    break;
                } else {
                    writeEndObject();
                }
            }
        }
        super.close();
        _flushBuffer();
        if (!this._ioContext.isResourceManaged() && !isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
            if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
                this._out.flush();
            }
        } else {
            this._out.close();
        }
        _releaseBuffers();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator, java.io.Flushable
    public final void flush() {
        _flushBuffer();
        if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
            this._out.flush();
        }
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public JsonStreamContext getOutputContext() {
        return this._cborContext;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator overrideFormatFeatures(int i, int i2) {
        int i3 = this._formatFeatures;
        int i4 = (i & i2) | ((i2 ^ (-1)) & i3);
        if (i3 != i4) {
            this._formatFeatures = i4;
            this._cfgMinimalInts = Feature.WRITE_MINIMAL_INTS.enabledIn(i4);
        }
        return this;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator overrideStdFeatures(int i, int i2) {
        int i3 = this._features;
        int i4 = (i & i2) | ((i2 ^ (-1)) & i3);
        if (i3 != i4) {
            this._features = i4;
        }
        return this;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void setCurrentValue(Object obj) {
        this._cborContext.setCurrentValue(obj);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator setPrettyPrinter(PrettyPrinter prettyPrinter) {
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeArray(int[] iArr, int i, int i2) {
        _verifyOffsets(iArr.length, i, i2);
        _verifyValueWrite("write int array");
        _writeLengthMarker(128, i2);
        int i3 = i2 + i;
        while (i < i3) {
            _writeNumberNoCheck(iArr[i]);
            i++;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) {
        if (bArr == null) {
            writeNull();
            return;
        }
        _verifyValueWrite("write Binary value");
        _writeLengthMarker(64, i2);
        _writeBytes(bArr, i, i2);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBoolean(boolean z) {
        _verifyValueWrite("write boolean value");
        if (z) {
            _writeByte((byte) -11);
        } else {
            _writeByte((byte) -12);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeEndArray() {
        if (this._cborContext.inArray()) {
            closeComplexElement();
            this._cborContext = this._cborContext.getParent();
        } else {
            _reportError("Current context not Array but " + this._cborContext.typeDesc());
            throw null;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeEndObject() {
        if (this._cborContext.inObject()) {
            closeComplexElement();
            this._cborContext = this._cborContext.getParent();
        } else {
            _reportError("Current context not Object but " + this._cborContext.typeDesc());
            throw null;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeFieldId(long j) {
        if (this._cborContext.writeFieldId(j)) {
            _writeNumberNoCheck(j);
        } else {
            _reportError("Can not write a field id, expecting a value");
            throw null;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeFieldName(String str) {
        if (this._cborContext.writeFieldName(str)) {
            _writeString(str);
        } else {
            _reportError("Can not write a field name, expecting a value");
            throw null;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNull() {
        _verifyValueWrite("write null value");
        _writeByte((byte) -10);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(int i) {
        int i2;
        byte b2;
        int i3;
        _verifyValueWrite("write number");
        if (i < 0) {
            i = (-i) - 1;
            i2 = 32;
        } else {
            i2 = 0;
        }
        _ensureRoomForOutput(5);
        if (!this._cfgMinimalInts) {
            b2 = (byte) i;
            i3 = i >> 8;
        } else {
            if (i < 24) {
                byte[] bArr = this._outputBuffer;
                int i4 = this._outputTail;
                this._outputTail = i4 + 1;
                bArr[i4] = (byte) (i2 + i);
                return;
            }
            if (i <= 255) {
                byte[] bArr2 = this._outputBuffer;
                int i5 = this._outputTail;
                this._outputTail = i5 + 1;
                bArr2[i5] = (byte) (i2 + 24);
                int i6 = this._outputTail;
                this._outputTail = i6 + 1;
                bArr2[i6] = (byte) i;
                return;
            }
            b2 = (byte) i;
            i3 = i >> 8;
            if (i3 <= 255) {
                byte[] bArr3 = this._outputBuffer;
                int i7 = this._outputTail;
                this._outputTail = i7 + 1;
                bArr3[i7] = (byte) (i2 + 25);
                int i8 = this._outputTail;
                this._outputTail = i8 + 1;
                bArr3[i8] = (byte) i3;
                int i9 = this._outputTail;
                this._outputTail = i9 + 1;
                bArr3[i9] = b2;
                return;
            }
        }
        byte[] bArr4 = this._outputBuffer;
        int i10 = this._outputTail;
        this._outputTail = i10 + 1;
        bArr4[i10] = (byte) (i2 + 26);
        int i11 = this._outputTail;
        this._outputTail = i11 + 1;
        bArr4[i11] = (byte) (i3 >> 16);
        int i12 = this._outputTail;
        this._outputTail = i12 + 1;
        bArr4[i12] = (byte) (i3 >> 8);
        int i13 = this._outputTail;
        this._outputTail = i13 + 1;
        bArr4[i13] = (byte) i3;
        int i14 = this._outputTail;
        this._outputTail = i14 + 1;
        bArr4[i14] = b2;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str) {
        throw _notSupported();
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(String str) {
        throw _notSupported();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartArray() {
        _verifyValueWrite("start an array");
        this._cborContext = this._cborContext.createChildArrayContext(null);
        if (this._elementCountsPtr > 0) {
            _pushRemainingElements();
        }
        this._currentRemainingElements = -2;
        _writeByte((byte) -97);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartObject() {
        _verifyValueWrite("start an object");
        this._cborContext = this._cborContext.createChildObjectContext(null);
        if (this._elementCountsPtr > 0) {
            _pushRemainingElements();
        }
        this._currentRemainingElements = -2;
        _writeByte((byte) -65);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(String str) {
        if (str == null) {
            writeNull();
        } else {
            _verifyValueWrite("write String value");
            _writeString(str);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStringField(String str, String str2) {
        if (this._cborContext.writeFieldName(str)) {
            _writeString(str);
            if (str2 == null) {
                writeNull();
                return;
            } else {
                _verifyValueWrite("write String value");
                _writeString(str2);
                return;
            }
        }
        _reportError("Can not write a field name, expecting a value");
        throw null;
    }

    public void writeTag(int i) {
        if (i >= 0) {
            _writeLengthMarker(AppearanceLibrary.APPEARANCE_GENERIC_WATCH, i);
            return;
        }
        throw new IllegalArgumentException("Can not write negative tag ids (" + i + ")");
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(char[] cArr, int i, int i2) {
        throw _notSupported();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(char c2) {
        throw _notSupported();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeFieldName(SerializableString serializableString) {
        if (this._cborContext.writeFieldName(serializableString.getValue())) {
            byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
            int length = asUnquotedUTF8.length;
            if (length == 0) {
                _writeByte((byte) 96);
                return;
            } else {
                _writeLengthMarker(96, length);
                _writeBytes(asUnquotedUTF8, 0, length);
                return;
            }
        }
        _reportError("Can not write a field name, expecting a value");
        throw null;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeString(SerializableString serializableString) {
        _verifyValueWrite("write String value");
        byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
        int length = asUnquotedUTF8.length;
        if (length == 0) {
            _writeByte((byte) 96);
        } else {
            _writeLengthMarker(96, length);
            _writeBytes(asUnquotedUTF8, 0, length);
        }
    }

    private final int _encode(int i, String str, int i2) {
        byte[] bArr = this._outputBuffer;
        int i3 = i;
        int i4 = 0;
        while (i4 < i2) {
            char charAt = str.charAt(i4);
            if (charAt > 127) {
                return _encode2(i4, i3, str, i2, i);
            }
            bArr[i3] = (byte) charAt;
            i4++;
            i3++;
        }
        return i3 - i;
    }

    private final int _writeBytes(InputStream inputStream, int i) {
        while (i > 0) {
            int i2 = this._outputEnd - this._outputTail;
            if (i2 <= 0) {
                _flushBuffer();
                i2 = this._outputEnd - this._outputTail;
            }
            int read = inputStream.read(this._outputBuffer, this._outputTail, i2);
            if (read < 0) {
                break;
            }
            this._outputTail += read;
            i -= read;
        }
        return i;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeArray(long[] jArr, int i, int i2) {
        _verifyOffsets(jArr.length, i, i2);
        _verifyValueWrite("write int array");
        _writeLengthMarker(128, i2);
        int i3 = i2 + i;
        while (i < i3) {
            _writeNumberNoCheck(jArr[i]);
            i++;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public int writeBinary(InputStream inputStream, int i) {
        if (i >= 0) {
            _verifyValueWrite("write Binary value");
            _writeLengthMarker(64, i);
            int _writeBytes = _writeBytes(inputStream, i);
            if (_writeBytes <= 0) {
                return i;
            }
            _reportError("Too few bytes available: missing " + _writeBytes + " bytes (out of " + i + ")");
            throw null;
        }
        throw new UnsupportedOperationException("Must pass actual length for CBOR encoded data");
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeStartArray(int i) {
        _verifyValueWrite("start an array");
        this._cborContext = this._cborContext.createChildArrayContext(null);
        _pushRemainingElements();
        this._currentRemainingElements = i;
        _writeLengthMarker(128, i);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartObject(Object obj) {
        _verifyValueWrite("start an object");
        this._cborContext = this._cborContext.createChildObjectContext(obj);
        if (this._elementCountsPtr > 0) {
            _pushRemainingElements();
        }
        this._currentRemainingElements = -2;
        _writeByte((byte) -65);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeArray(double[] dArr, int i, int i2) {
        _verifyOffsets(dArr.length, i, i2);
        _verifyValueWrite("write int array");
        _writeLengthMarker(128, i2);
        int i3 = i2 + i;
        while (i < i3) {
            _writeNumberNoCheck(dArr[i]);
            i++;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i) {
        writeBinary(inputStream, i);
        return i;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(char[] cArr, int i, int i2) {
        _verifyValueWrite("write String value");
        if (i2 == 0) {
            _writeByte((byte) 96);
        } else {
            _writeString(cArr, i, i2);
        }
    }

    private final void _writeNumberNoCheck(long j) {
        if (this._cfgMinimalInts && j <= 2147483647L && j >= -2147483648L) {
            _writeNumberNoCheck((int) j);
            return;
        }
        _ensureRoomForOutput(9);
        if (j < 0) {
            j = -(j + 1);
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = 59;
        } else {
            byte[] bArr2 = this._outputBuffer;
            int i2 = this._outputTail;
            this._outputTail = i2 + 1;
            bArr2[i2] = 27;
        }
        int i3 = (int) (j >> 32);
        byte[] bArr3 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr3[i4] = (byte) (i3 >> 24);
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr3[i5] = (byte) (i3 >> 16);
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        bArr3[i6] = (byte) (i3 >> 8);
        int i7 = this._outputTail;
        this._outputTail = i7 + 1;
        bArr3[i7] = (byte) i3;
        int i8 = (int) j;
        int i9 = this._outputTail;
        this._outputTail = i9 + 1;
        bArr3[i9] = (byte) (i8 >> 24);
        int i10 = this._outputTail;
        this._outputTail = i10 + 1;
        bArr3[i10] = (byte) (i8 >> 16);
        int i11 = this._outputTail;
        this._outputTail = i11 + 1;
        bArr3[i11] = (byte) (i8 >> 8);
        int i12 = this._outputTail;
        this._outputTail = i12 + 1;
        bArr3[i12] = (byte) i8;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(long j) {
        if (this._cfgMinimalInts && j <= 2147483647L && j >= -2147483648L) {
            writeNumber((int) j);
            return;
        }
        _verifyValueWrite("write number");
        _ensureRoomForOutput(9);
        if (j < 0) {
            j = -(j + 1);
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = 59;
        } else {
            byte[] bArr2 = this._outputBuffer;
            int i2 = this._outputTail;
            this._outputTail = i2 + 1;
            bArr2[i2] = 27;
        }
        int i3 = (int) (j >> 32);
        byte[] bArr3 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr3[i4] = (byte) (i3 >> 24);
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr3[i5] = (byte) (i3 >> 16);
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        bArr3[i6] = (byte) (i3 >> 8);
        int i7 = this._outputTail;
        this._outputTail = i7 + 1;
        bArr3[i7] = (byte) i3;
        int i8 = (int) j;
        int i9 = this._outputTail;
        this._outputTail = i9 + 1;
        bArr3[i9] = (byte) (i8 >> 24);
        int i10 = this._outputTail;
        this._outputTail = i10 + 1;
        bArr3[i10] = (byte) (i8 >> 16);
        int i11 = this._outputTail;
        this._outputTail = i11 + 1;
        bArr3[i11] = (byte) (i8 >> 8);
        int i12 = this._outputTail;
        this._outputTail = i12 + 1;
        bArr3[i12] = (byte) i8;
    }

    protected final void _writeString(char[] cArr, int i, int i2) {
        if (i2 <= 23) {
            _ensureSpace(71);
            int _encode = _encode(this._outputTail + 1, cArr, i, i2 + i);
            byte[] bArr = this._outputBuffer;
            int i3 = this._outputTail;
            if (_encode <= 23) {
                bArr[i3] = (byte) (_encode + 96);
                this._outputTail = i3 + 1 + _encode;
                return;
            }
            int i4 = i3 + 1;
            System.arraycopy(bArr, i4, bArr, i3 + 2, _encode);
            bArr[i3] = 120;
            bArr[i4] = (byte) _encode;
            this._outputTail = i4 + 1 + _encode;
            return;
        }
        if (i2 > 255) {
            if (i2 <= 3996) {
                _ensureSpace(11991);
                int i5 = this._outputTail;
                int _encode2 = _encode(i5 + 3, cArr, i, i2 + i);
                byte[] bArr2 = this._outputBuffer;
                int i6 = i5 + 1;
                bArr2[i5] = 121;
                int i7 = i6 + 1;
                bArr2[i6] = (byte) (_encode2 >> 8);
                bArr2[i7] = (byte) _encode2;
                this._outputTail = i7 + 1 + _encode2;
                return;
            }
            _writeChunkedString(cArr, i, i2);
            return;
        }
        _ensureSpace(768);
        int _encode3 = _encode(this._outputTail + 2, cArr, i, i2 + i);
        byte[] bArr3 = this._outputBuffer;
        int i8 = this._outputTail;
        if (_encode3 <= 255) {
            int i9 = i8 + 1;
            bArr3[i8] = 120;
            bArr3[i9] = (byte) _encode3;
            this._outputTail = i9 + 1 + _encode3;
            return;
        }
        System.arraycopy(bArr3, i8 + 2, bArr3, i8 + 3, _encode3);
        int i10 = i8 + 1;
        bArr3[i8] = 121;
        int i11 = i10 + 1;
        bArr3[i10] = (byte) (_encode3 >> 8);
        bArr3[i11] = (byte) _encode3;
        this._outputTail = i11 + 1 + _encode3;
    }

    private final void _writeNumberNoCheck(double d2) {
        _ensureRoomForOutput(11);
        long doubleToRawLongBits = Double.doubleToRawLongBits(d2);
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = -5;
        int i2 = (int) (doubleToRawLongBits >> 32);
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = (byte) (i2 >> 24);
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr[i4] = (byte) (i2 >> 16);
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr[i5] = (byte) (i2 >> 8);
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        bArr[i6] = (byte) i2;
        int i7 = (int) doubleToRawLongBits;
        int i8 = this._outputTail;
        this._outputTail = i8 + 1;
        bArr[i8] = (byte) (i7 >> 24);
        int i9 = this._outputTail;
        this._outputTail = i9 + 1;
        bArr[i9] = (byte) (i7 >> 16);
        int i10 = this._outputTail;
        this._outputTail = i10 + 1;
        bArr[i10] = (byte) (i7 >> 8);
        int i11 = this._outputTail;
        this._outputTail = i11 + 1;
        bArr[i11] = (byte) i7;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigInteger bigInteger) {
        if (bigInteger == null) {
            writeNull();
        } else {
            _verifyValueWrite("write number");
            _write(bigInteger);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(double d2) {
        _verifyValueWrite("write number");
        _ensureRoomForOutput(11);
        long doubleToRawLongBits = Double.doubleToRawLongBits(d2);
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = -5;
        int i2 = (int) (doubleToRawLongBits >> 32);
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = (byte) (i2 >> 24);
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr[i4] = (byte) (i2 >> 16);
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr[i5] = (byte) (i2 >> 8);
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        bArr[i6] = (byte) i2;
        int i7 = (int) doubleToRawLongBits;
        int i8 = this._outputTail;
        this._outputTail = i8 + 1;
        bArr[i8] = (byte) (i7 >> 24);
        int i9 = this._outputTail;
        this._outputTail = i9 + 1;
        bArr[i9] = (byte) (i7 >> 16);
        int i10 = this._outputTail;
        this._outputTail = i10 + 1;
        bArr[i10] = (byte) (i7 >> 8);
        int i11 = this._outputTail;
        this._outputTail = i11 + 1;
        bArr[i11] = (byte) i7;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(float f2) {
        _ensureRoomForOutput(6);
        _verifyValueWrite("write number");
        int floatToRawIntBits = Float.floatToRawIntBits(f2);
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = -6;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr[i2] = (byte) (floatToRawIntBits >> 24);
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = (byte) (floatToRawIntBits >> 16);
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr[i4] = (byte) (floatToRawIntBits >> 8);
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr[i5] = (byte) floatToRawIntBits;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            writeNull();
            return;
        }
        _verifyValueWrite("write number");
        _writeByte((byte) -60);
        _writeByte((byte) -126);
        _writeIntValue(-bigDecimal.scale());
        BigInteger unscaledValue = bigDecimal.unscaledValue();
        int bitLength = unscaledValue.bitLength();
        if (bitLength <= 31) {
            _writeIntValue(unscaledValue.intValue());
        } else if (bitLength <= 63) {
            _writeLongValue(unscaledValue.longValue());
        } else {
            _write(unscaledValue);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(String str) {
        writeString(str);
    }
}
