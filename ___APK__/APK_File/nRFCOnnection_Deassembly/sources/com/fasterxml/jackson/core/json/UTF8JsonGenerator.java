package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberOutput;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import no.nordicsemi.android.mcp.ble.parser.AppearanceLibrary;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public class UTF8JsonGenerator extends JsonGeneratorImpl {
    protected boolean _bufferRecyclable;
    protected char[] _charBuffer;
    protected final int _charBufferLength;
    protected byte[] _outputBuffer;
    protected final int _outputEnd;
    protected final int _outputMaxContiguous;
    protected final OutputStream _outputStream;
    protected int _outputTail;
    protected byte _quoteChar;
    private static final byte[] HEX_CHARS = CharTypes.copyHexBytes();
    private static final byte[] NULL_BYTES = {110, 117, 108, 108};
    private static final byte[] TRUE_BYTES = {116, 114, 117, 101};
    private static final byte[] FALSE_BYTES = {102, 97, 108, 115, 101};

    public UTF8JsonGenerator(IOContext iOContext, int i, ObjectCodec objectCodec, OutputStream outputStream, char c2) {
        super(iOContext, i, objectCodec);
        this._outputStream = outputStream;
        this._quoteChar = (byte) c2;
        if (c2 != '\"') {
            this._outputEscapes = CharTypes.get7BitOutputEscapes(c2);
        }
        this._bufferRecyclable = true;
        this._outputBuffer = iOContext.allocWriteEncodingBuffer();
        this._outputEnd = this._outputBuffer.length;
        this._outputMaxContiguous = this._outputEnd >> 3;
        this._charBuffer = iOContext.allocConcatBuffer();
        this._charBufferLength = this._charBuffer.length;
        if (isEnabled(JsonGenerator.Feature.ESCAPE_NON_ASCII)) {
            setHighestNonEscapedChar(127);
        }
    }

    private final int _handleLongCustomEscape(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int length = bArr2.length;
        if (i + length > i2) {
            this._outputTail = i;
            _flushBuffer();
            int i4 = this._outputTail;
            if (length > bArr.length) {
                this._outputStream.write(bArr2, 0, length);
                return i4;
            }
            System.arraycopy(bArr2, 0, bArr, i4, length);
            i = i4 + length;
        }
        if ((i3 * 6) + i <= i2) {
            return i;
        }
        _flushBuffer();
        return this._outputTail;
    }

    private final int _outputMultiByteChar(int i, int i2) {
        byte[] bArr = this._outputBuffer;
        if (i >= 55296 && i <= 57343) {
            int i3 = i2 + 1;
            bArr[i2] = 92;
            int i4 = i3 + 1;
            bArr[i3] = 117;
            int i5 = i4 + 1;
            byte[] bArr2 = HEX_CHARS;
            bArr[i4] = bArr2[(i >> 12) & 15];
            int i6 = i5 + 1;
            bArr[i5] = bArr2[(i >> 8) & 15];
            int i7 = i6 + 1;
            bArr[i6] = bArr2[(i >> 4) & 15];
            int i8 = i7 + 1;
            bArr[i7] = bArr2[i & 15];
            return i8;
        }
        int i9 = i2 + 1;
        bArr[i2] = (byte) ((i >> 12) | 224);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (((i >> 6) & 63) | 128);
        int i11 = i10 + 1;
        bArr[i10] = (byte) ((i & 63) | 128);
        return i11;
    }

    private final int _outputRawMultiByteChar(int i, char[] cArr, int i2, int i3) {
        if (i >= 55296 && i <= 57343) {
            if (i2 < i3 && cArr != null) {
                _outputSurrogates(i, cArr[i2]);
                return i2 + 1;
            }
            _reportError(String.format("Split surrogate on writeRaw() input (last character): first character 0x%4x", Integer.valueOf(i)));
            throw null;
        }
        byte[] bArr = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr[i4] = (byte) ((i >> 12) | 224);
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr[i5] = (byte) (((i >> 6) & 63) | 128);
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        bArr[i6] = (byte) ((i & 63) | 128);
        return i2;
    }

    private final int _readMore(InputStream inputStream, byte[] bArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i < i2) {
            bArr[i4] = bArr[i];
            i4++;
            i++;
        }
        int min = Math.min(i3, bArr.length);
        do {
            int i5 = min - i4;
            if (i5 == 0) {
                break;
            }
            int read = inputStream.read(bArr, i4, i5);
            if (read < 0) {
                return i4;
            }
            i4 += read;
        } while (i4 < 3);
        return i4;
    }

    private final void _writeBytes(byte[] bArr) {
        int length = bArr.length;
        if (this._outputTail + length > this._outputEnd) {
            _flushBuffer();
            if (length > 512) {
                this._outputStream.write(bArr, 0, length);
                return;
            }
        }
        System.arraycopy(bArr, 0, this._outputBuffer, this._outputTail, length);
        this._outputTail += length;
    }

    private final int _writeCustomEscape(byte[] bArr, int i, SerializableString serializableString, int i2) {
        byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
        int length = asUnquotedUTF8.length;
        if (length > 6) {
            return _handleLongCustomEscape(bArr, i, this._outputEnd, asUnquotedUTF8, i2);
        }
        System.arraycopy(asUnquotedUTF8, 0, bArr, i, length);
        return i + length;
    }

    private final void _writeCustomStringSegment2(char[] cArr, int i, int i2) {
        if (this._outputTail + ((i2 - i) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int i3 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        int i4 = this._maximumNonEscapedChar;
        if (i4 <= 0) {
            i4 = 65535;
        }
        CharacterEscapes characterEscapes = this._characterEscapes;
        while (i < i2) {
            int i5 = i + 1;
            char c2 = cArr[i];
            if (c2 <= 127) {
                if (iArr[c2] == 0) {
                    bArr[i3] = (byte) c2;
                    i = i5;
                    i3++;
                } else {
                    int i6 = iArr[c2];
                    if (i6 > 0) {
                        int i7 = i3 + 1;
                        bArr[i3] = 92;
                        i3 = i7 + 1;
                        bArr[i7] = (byte) i6;
                    } else if (i6 == -2) {
                        SerializableString escapeSequence = characterEscapes.getEscapeSequence(c2);
                        if (escapeSequence != null) {
                            i3 = _writeCustomEscape(bArr, i3, escapeSequence, i2 - i5);
                        } else {
                            _reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(c2) + ", although was supposed to have one");
                            throw null;
                        }
                    } else {
                        i3 = _writeGenericEscape(c2, i3);
                    }
                }
            } else if (c2 > i4) {
                i3 = _writeGenericEscape(c2, i3);
            } else {
                SerializableString escapeSequence2 = characterEscapes.getEscapeSequence(c2);
                if (escapeSequence2 != null) {
                    i3 = _writeCustomEscape(bArr, i3, escapeSequence2, i2 - i5);
                } else if (c2 <= 2047) {
                    int i8 = i3 + 1;
                    bArr[i3] = (byte) ((c2 >> 6) | AppearanceLibrary.APPEARANCE_GENERIC_WATCH);
                    i3 = i8 + 1;
                    bArr[i8] = (byte) ((c2 & '?') | 128);
                } else {
                    i3 = _outputMultiByteChar(c2, i3);
                }
            }
            i = i5;
        }
        this._outputTail = i3;
    }

    private int _writeGenericEscape(int i, int i2) {
        int i3;
        byte[] bArr = this._outputBuffer;
        int i4 = i2 + 1;
        bArr[i2] = 92;
        int i5 = i4 + 1;
        bArr[i4] = 117;
        if (i > 255) {
            int i6 = 255 & (i >> 8);
            int i7 = i5 + 1;
            byte[] bArr2 = HEX_CHARS;
            bArr[i5] = bArr2[i6 >> 4];
            i3 = i7 + 1;
            bArr[i7] = bArr2[i6 & 15];
            i &= 255;
        } else {
            int i8 = i5 + 1;
            bArr[i5] = 48;
            i3 = i8 + 1;
            bArr[i8] = 48;
        }
        int i9 = i3 + 1;
        byte[] bArr3 = HEX_CHARS;
        bArr[i3] = bArr3[i >> 4];
        int i10 = i9 + 1;
        bArr[i9] = bArr3[i & 15];
        return i10;
    }

    private final void _writeNull() {
        if (this._outputTail + 4 >= this._outputEnd) {
            _flushBuffer();
        }
        System.arraycopy(NULL_BYTES, 0, this._outputBuffer, this._outputTail, 4);
        this._outputTail += 4;
    }

    private final void _writeQuotedInt(int i) {
        if (this._outputTail + 13 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr[i2] = this._quoteChar;
        this._outputTail = NumberOutput.outputInt(i, bArr, this._outputTail);
        byte[] bArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr2[i3] = this._quoteChar;
    }

    private final void _writeQuotedLong(long j) {
        if (this._outputTail + 23 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = this._quoteChar;
        this._outputTail = NumberOutput.outputLong(j, bArr, this._outputTail);
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = this._quoteChar;
    }

    private final void _writeQuotedRaw(String str) {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = this._quoteChar;
        writeRaw(str);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = this._quoteChar;
    }

    private final void _writeQuotedShort(short s) {
        if (this._outputTail + 8 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = this._quoteChar;
        this._outputTail = NumberOutput.outputInt(s, bArr, this._outputTail);
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = this._quoteChar;
    }

    private void _writeRawSegment(char[] cArr, int i, int i2) {
        while (i < i2) {
            do {
                char c2 = cArr[i];
                if (c2 > 127) {
                    int i3 = i + 1;
                    char c3 = cArr[i];
                    if (c3 < 2048) {
                        byte[] bArr = this._outputBuffer;
                        int i4 = this._outputTail;
                        this._outputTail = i4 + 1;
                        bArr[i4] = (byte) ((c3 >> 6) | AppearanceLibrary.APPEARANCE_GENERIC_WATCH);
                        int i5 = this._outputTail;
                        this._outputTail = i5 + 1;
                        bArr[i5] = (byte) ((c3 & '?') | 128);
                        i = i3;
                    } else {
                        i = _outputRawMultiByteChar(c3, cArr, i3, i2);
                    }
                } else {
                    byte[] bArr2 = this._outputBuffer;
                    int i6 = this._outputTail;
                    this._outputTail = i6 + 1;
                    bArr2[i6] = (byte) c2;
                    i++;
                }
            } while (i < i2);
            return;
        }
    }

    private final void _writeSegmentedRaw(char[] cArr, int i, int i2) {
        int i3 = this._outputEnd;
        byte[] bArr = this._outputBuffer;
        int i4 = i2 + i;
        while (i < i4) {
            do {
                char c2 = cArr[i];
                if (c2 >= 128) {
                    if (this._outputTail + 3 >= this._outputEnd) {
                        _flushBuffer();
                    }
                    int i5 = i + 1;
                    char c3 = cArr[i];
                    if (c3 < 2048) {
                        int i6 = this._outputTail;
                        this._outputTail = i6 + 1;
                        bArr[i6] = (byte) ((c3 >> 6) | AppearanceLibrary.APPEARANCE_GENERIC_WATCH);
                        int i7 = this._outputTail;
                        this._outputTail = i7 + 1;
                        bArr[i7] = (byte) ((c3 & '?') | 128);
                        i = i5;
                    } else {
                        i = _outputRawMultiByteChar(c3, cArr, i5, i4);
                    }
                } else {
                    if (this._outputTail >= i3) {
                        _flushBuffer();
                    }
                    int i8 = this._outputTail;
                    this._outputTail = i8 + 1;
                    bArr[i8] = (byte) c2;
                    i++;
                }
            } while (i < i4);
            return;
        }
    }

    private final void _writeStringSegment(char[] cArr, int i, int i2) {
        int i3 = i2 + i;
        int i4 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        while (i < i3) {
            char c2 = cArr[i];
            if (c2 > 127 || iArr[c2] != 0) {
                break;
            }
            bArr[i4] = (byte) c2;
            i++;
            i4++;
        }
        this._outputTail = i4;
        if (i < i3) {
            if (this._characterEscapes != null) {
                _writeCustomStringSegment2(cArr, i, i3);
            } else if (this._maximumNonEscapedChar == 0) {
                _writeStringSegment2(cArr, i, i3);
            } else {
                _writeStringSegmentASCII2(cArr, i, i3);
            }
        }
    }

    private final void _writeStringSegment2(char[] cArr, int i, int i2) {
        if (this._outputTail + ((i2 - i) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int i3 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        while (i < i2) {
            int i4 = i + 1;
            char c2 = cArr[i];
            if (c2 <= 127) {
                if (iArr[c2] == 0) {
                    bArr[i3] = (byte) c2;
                    i = i4;
                    i3++;
                } else {
                    int i5 = iArr[c2];
                    if (i5 > 0) {
                        int i6 = i3 + 1;
                        bArr[i3] = 92;
                        i3 = i6 + 1;
                        bArr[i6] = (byte) i5;
                    } else {
                        i3 = _writeGenericEscape(c2, i3);
                    }
                }
            } else if (c2 <= 2047) {
                int i7 = i3 + 1;
                bArr[i3] = (byte) ((c2 >> 6) | AppearanceLibrary.APPEARANCE_GENERIC_WATCH);
                i3 = i7 + 1;
                bArr[i7] = (byte) ((c2 & '?') | 128);
            } else {
                i3 = _outputMultiByteChar(c2, i3);
            }
            i = i4;
        }
        this._outputTail = i3;
    }

    private final void _writeStringSegmentASCII2(char[] cArr, int i, int i2) {
        if (this._outputTail + ((i2 - i) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int i3 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        int i4 = this._maximumNonEscapedChar;
        while (i < i2) {
            int i5 = i + 1;
            char c2 = cArr[i];
            if (c2 <= 127) {
                if (iArr[c2] == 0) {
                    bArr[i3] = (byte) c2;
                    i = i5;
                    i3++;
                } else {
                    int i6 = iArr[c2];
                    if (i6 > 0) {
                        int i7 = i3 + 1;
                        bArr[i3] = 92;
                        i3 = i7 + 1;
                        bArr[i7] = (byte) i6;
                    } else {
                        i3 = _writeGenericEscape(c2, i3);
                    }
                }
            } else if (c2 > i4) {
                i3 = _writeGenericEscape(c2, i3);
            } else if (c2 <= 2047) {
                int i8 = i3 + 1;
                bArr[i3] = (byte) ((c2 >> 6) | AppearanceLibrary.APPEARANCE_GENERIC_WATCH);
                i3 = i8 + 1;
                bArr[i8] = (byte) ((c2 & '?') | 128);
            } else {
                i3 = _outputMultiByteChar(c2, i3);
            }
            i = i5;
        }
        this._outputTail = i3;
    }

    private final void _writeStringSegments(String str, boolean z) {
        if (z) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = this._quoteChar;
        }
        int length = str.length();
        int i2 = 0;
        while (length > 0) {
            int min = Math.min(this._outputMaxContiguous, length);
            if (this._outputTail + min > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(str, i2, min);
            i2 += min;
            length -= min;
        }
        if (z) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr2[i3] = this._quoteChar;
        }
    }

    private final void _writeUnq(SerializableString serializableString) {
        int appendQuotedUTF8 = serializableString.appendQuotedUTF8(this._outputBuffer, this._outputTail);
        if (appendQuotedUTF8 < 0) {
            _writeBytes(serializableString.asQuotedUTF8());
        } else {
            this._outputTail += appendQuotedUTF8;
        }
    }

    protected final void _flushBuffer() {
        int i = this._outputTail;
        if (i > 0) {
            this._outputTail = 0;
            this._outputStream.write(this._outputBuffer, 0, i);
        }
    }

    protected final void _outputSurrogates(int i, int i2) {
        int _decodeSurrogate = _decodeSurrogate(i, i2);
        if (this._outputTail + 4 > this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = (byte) ((_decodeSurrogate >> 18) | 240);
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr[i4] = (byte) (((_decodeSurrogate >> 12) & 63) | 128);
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr[i5] = (byte) (((_decodeSurrogate >> 6) & 63) | 128);
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        bArr[i6] = (byte) ((_decodeSurrogate & 63) | 128);
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
        byte b2;
        int writeValue = this._writeContext.writeValue();
        if (this._cfgPrettyPrinter != null) {
            _verifyPrettyValueWrite(str, writeValue);
            return;
        }
        if (writeValue == 1) {
            b2 = 44;
        } else {
            if (writeValue != 2) {
                if (writeValue != 3) {
                    if (writeValue != 5) {
                        return;
                    }
                    _reportCantWriteValueExpectName(str);
                    throw null;
                }
                SerializableString serializableString = this._rootValueSeparator;
                if (serializableString != null) {
                    byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
                    if (asUnquotedUTF8.length > 0) {
                        _writeBytes(asUnquotedUTF8);
                        return;
                    }
                    return;
                }
                return;
            }
            b2 = 58;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b2;
    }

    protected final void _writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) {
        int i3 = i2 - 3;
        int i4 = this._outputEnd - 6;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        while (i <= i3) {
            if (this._outputTail > i4) {
                _flushBuffer();
            }
            int i5 = i + 1;
            int i6 = i5 + 1;
            int i7 = ((bArr[i] << 8) | (bArr[i5] & FlagsParser.UNKNOWN_FLAGS)) << 8;
            int i8 = i6 + 1;
            this._outputTail = base64Variant.encodeBase64Chunk(i7 | (bArr[i6] & FlagsParser.UNKNOWN_FLAGS), this._outputBuffer, this._outputTail);
            maxLineLength--;
            if (maxLineLength <= 0) {
                byte[] bArr2 = this._outputBuffer;
                int i9 = this._outputTail;
                this._outputTail = i9 + 1;
                bArr2[i9] = 92;
                int i10 = this._outputTail;
                this._outputTail = i10 + 1;
                bArr2[i10] = 110;
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
            i = i8;
        }
        int i11 = i2 - i;
        if (i11 > 0) {
            if (this._outputTail > i4) {
                _flushBuffer();
            }
            int i12 = i + 1;
            int i13 = bArr[i] << 16;
            if (i11 == 2) {
                i13 |= (bArr[i12] & FlagsParser.UNKNOWN_FLAGS) << 8;
            }
            this._outputTail = base64Variant.encodeBase64Partial(i13, i11, this._outputBuffer, this._outputTail);
        }
    }

    protected final void _writePPFieldName(String str) {
        int writeFieldName = this._writeContext.writeFieldName(str);
        if (writeFieldName != 4) {
            if (writeFieldName == 1) {
                this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
            } else {
                this._cfgPrettyPrinter.beforeObjectEntries(this);
            }
            if (this._cfgUnqNames) {
                _writeStringSegments(str, false);
                return;
            }
            int length = str.length();
            if (length > this._charBufferLength) {
                _writeStringSegments(str, true);
                return;
            }
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = this._quoteChar;
            str.getChars(0, length, this._charBuffer, 0);
            if (length <= this._outputMaxContiguous) {
                if (this._outputTail + length > this._outputEnd) {
                    _flushBuffer();
                }
                _writeStringSegment(this._charBuffer, 0, length);
            } else {
                _writeStringSegments(this._charBuffer, 0, length);
            }
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int i2 = this._outputTail;
            this._outputTail = i2 + 1;
            bArr2[i2] = this._quoteChar;
            return;
        }
        _reportError("Can not write a field name, expecting a value");
        throw null;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
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
        _flushBuffer();
        this._outputTail = 0;
        if (this._outputStream != null) {
            if (!this._ioContext.isResourceManaged() && !isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
                if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
                    this._outputStream.flush();
                }
            } else {
                this._outputStream.close();
            }
        }
        _releaseBuffers();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator, java.io.Flushable
    public void flush() {
        _flushBuffer();
        if (this._outputStream == null || !isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
            return;
        }
        this._outputStream.flush();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) {
        _verifyValueWrite("write a binary value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr2[i3] = this._quoteChar;
        _writeBinary(base64Variant, bArr, i, i2 + i);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr3[i4] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBoolean(boolean z) {
        _verifyValueWrite("write a boolean value");
        if (this._outputTail + 5 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = z ? TRUE_BYTES : FALSE_BYTES;
        int length = bArr.length;
        System.arraycopy(bArr, 0, this._outputBuffer, this._outputTail, length);
        this._outputTail += length;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeEndArray() {
        if (this._writeContext.inArray()) {
            PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
            if (prettyPrinter != null) {
                prettyPrinter.writeEndArray(this, this._writeContext.getEntryCount());
            } else {
                if (this._outputTail >= this._outputEnd) {
                    _flushBuffer();
                }
                byte[] bArr = this._outputBuffer;
                int i = this._outputTail;
                this._outputTail = i + 1;
                bArr[i] = 93;
            }
            this._writeContext = this._writeContext.clearAndGetParent();
            return;
        }
        _reportError("Current context not Array but " + this._writeContext.typeDesc());
        throw null;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeEndObject() {
        if (this._writeContext.inObject()) {
            PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
            if (prettyPrinter != null) {
                prettyPrinter.writeEndObject(this, this._writeContext.getEntryCount());
            } else {
                if (this._outputTail >= this._outputEnd) {
                    _flushBuffer();
                }
                byte[] bArr = this._outputBuffer;
                int i = this._outputTail;
                this._outputTail = i + 1;
                bArr[i] = 125;
            }
            this._writeContext = this._writeContext.clearAndGetParent();
            return;
        }
        _reportError("Current context not Object but " + this._writeContext.typeDesc());
        throw null;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(String str) {
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(str);
            return;
        }
        int writeFieldName = this._writeContext.writeFieldName(str);
        if (writeFieldName != 4) {
            if (writeFieldName == 1) {
                if (this._outputTail >= this._outputEnd) {
                    _flushBuffer();
                }
                byte[] bArr = this._outputBuffer;
                int i = this._outputTail;
                this._outputTail = i + 1;
                bArr[i] = 44;
            }
            if (this._cfgUnqNames) {
                _writeStringSegments(str, false);
                return;
            }
            int length = str.length();
            if (length > this._charBufferLength) {
                _writeStringSegments(str, true);
                return;
            }
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int i2 = this._outputTail;
            this._outputTail = i2 + 1;
            bArr2[i2] = this._quoteChar;
            if (length <= this._outputMaxContiguous) {
                if (this._outputTail + length > this._outputEnd) {
                    _flushBuffer();
                }
                _writeStringSegment(str, 0, length);
            } else {
                _writeStringSegments(str, 0, length);
            }
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr3 = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr3[i3] = this._quoteChar;
            return;
        }
        _reportError("Can not write a field name, expecting a value");
        throw null;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNull() {
        _verifyValueWrite("write a null");
        _writeNull();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(short s) {
        _verifyValueWrite("write a number");
        if (this._outputTail + 6 >= this._outputEnd) {
            _flushBuffer();
        }
        if (this._cfgNumbersAsStrings) {
            _writeQuotedShort(s);
        } else {
            this._outputTail = NumberOutput.outputInt(s, this._outputBuffer, this._outputTail);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str) {
        int length = str.length();
        char[] cArr = this._charBuffer;
        if (length <= cArr.length) {
            str.getChars(0, length, cArr, 0);
            writeRaw(cArr, 0, length);
        } else {
            writeRaw(str, 0, length);
        }
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(SerializableString serializableString) {
        _verifyValueWrite("write a raw (unencoded) value");
        int appendUnquotedUTF8 = serializableString.appendUnquotedUTF8(this._outputBuffer, this._outputTail);
        if (appendUnquotedUTF8 < 0) {
            _writeBytes(serializableString.asUnquotedUTF8());
        } else {
            this._outputTail += appendUnquotedUTF8;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartArray() {
        _verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext();
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartArray(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = 91;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartObject() {
        _verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext();
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartObject(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = 123;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(String str) {
        _verifyValueWrite("write a string");
        if (str == null) {
            _writeNull();
            return;
        }
        int length = str.length();
        if (length > this._outputMaxContiguous) {
            _writeStringSegments(str, true);
            return;
        }
        if (this._outputTail + length >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = this._quoteChar;
        _writeStringSegment(str, 0, length);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = this._quoteChar;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(int i) {
        _verifyValueWrite("write a number");
        if (this._outputTail + 11 >= this._outputEnd) {
            _flushBuffer();
        }
        if (this._cfgNumbersAsStrings) {
            _writeQuotedInt(i);
        } else {
            this._outputTail = NumberOutput.outputInt(i, this._outputBuffer, this._outputTail);
        }
    }

    public void writeRaw(String str, int i, int i2) {
        char c2;
        char[] cArr = this._charBuffer;
        int length = cArr.length;
        if (i2 <= length) {
            str.getChars(i, i + i2, cArr, 0);
            writeRaw(cArr, 0, i2);
            return;
        }
        int i3 = this._outputEnd;
        int min = Math.min(length, (i3 >> 2) + (i3 >> 4));
        int i4 = min * 3;
        while (i2 > 0) {
            int min2 = Math.min(min, i2);
            str.getChars(i, i + min2, cArr, 0);
            if (this._outputTail + i4 > this._outputEnd) {
                _flushBuffer();
            }
            if (min2 > 1 && (c2 = cArr[min2 - 1]) >= 55296 && c2 <= 56319) {
                min2--;
            }
            _writeRawSegment(cArr, 0, min2);
            i += min2;
            i2 -= min2;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeStartArray(int i) {
        _verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext();
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartArray(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr[i2] = 91;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeStartObject(Object obj) {
        _verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext(obj);
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartObject(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = 123;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i) {
        _verifyValueWrite("write a binary value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr[i2] = this._quoteChar;
        byte[] allocBase64Buffer = this._ioContext.allocBase64Buffer();
        try {
            if (i < 0) {
                i = _writeBinary(base64Variant, inputStream, allocBase64Buffer);
            } else {
                int _writeBinary = _writeBinary(base64Variant, inputStream, allocBase64Buffer, i);
                if (_writeBinary > 0) {
                    _reportError("Too few bytes available: missing " + _writeBinary + " bytes (out of " + i + ")");
                    throw null;
                }
            }
            this._ioContext.releaseBase64Buffer(allocBase64Buffer);
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr2[i3] = this._quoteChar;
            return i;
        } catch (Throwable th) {
            this._ioContext.releaseBase64Buffer(allocBase64Buffer);
            throw th;
        }
    }

    private final void _writeStringSegments(char[] cArr, int i, int i2) {
        do {
            int min = Math.min(this._outputMaxContiguous, i2);
            if (this._outputTail + min > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(cArr, i, min);
            i += min;
            i2 -= min;
        } while (i2 > 0);
    }

    private final void _writeStringSegment(String str, int i, int i2) {
        int i3 = i2 + i;
        int i4 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        while (i < i3) {
            char charAt = str.charAt(i);
            if (charAt > 127 || iArr[charAt] != 0) {
                break;
            }
            bArr[i4] = (byte) charAt;
            i++;
            i4++;
        }
        this._outputTail = i4;
        if (i < i3) {
            if (this._characterEscapes != null) {
                _writeCustomStringSegment2(str, i, i3);
            } else if (this._maximumNonEscapedChar == 0) {
                _writeStringSegment2(str, i, i3);
            } else {
                _writeStringSegmentASCII2(str, i, i3);
            }
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(long j) {
        _verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedLong(j);
            return;
        }
        if (this._outputTail + 21 >= this._outputEnd) {
            _flushBuffer();
        }
        this._outputTail = NumberOutput.outputLong(j, this._outputBuffer, this._outputTail);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(char[] cArr, int i, int i2) {
        _verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = this._quoteChar;
        if (i2 <= this._outputMaxContiguous) {
            if (this._outputTail + i2 > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(cArr, i, i2);
        } else {
            _writeStringSegments(cArr, i, i2);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr2[i4] = this._quoteChar;
    }

    private final void _writeStringSegments(String str, int i, int i2) {
        do {
            int min = Math.min(this._outputMaxContiguous, i2);
            if (this._outputTail + min > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(str, i, min);
            i += min;
            i2 -= min;
        } while (i2 > 0);
    }

    private final void _writeStringSegment2(String str, int i, int i2) {
        if (this._outputTail + ((i2 - i) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int i3 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        while (i < i2) {
            int i4 = i + 1;
            char charAt = str.charAt(i);
            if (charAt <= 127) {
                if (iArr[charAt] == 0) {
                    bArr[i3] = (byte) charAt;
                    i = i4;
                    i3++;
                } else {
                    int i5 = iArr[charAt];
                    if (i5 > 0) {
                        int i6 = i3 + 1;
                        bArr[i3] = 92;
                        i3 = i6 + 1;
                        bArr[i6] = (byte) i5;
                    } else {
                        i3 = _writeGenericEscape(charAt, i3);
                    }
                }
            } else if (charAt <= 2047) {
                int i7 = i3 + 1;
                bArr[i3] = (byte) ((charAt >> 6) | AppearanceLibrary.APPEARANCE_GENERIC_WATCH);
                i3 = i7 + 1;
                bArr[i7] = (byte) ((charAt & '?') | 128);
            } else {
                i3 = _outputMultiByteChar(charAt, i3);
            }
            i = i4;
        }
        this._outputTail = i3;
    }

    protected final int _writeBinary(Base64Variant base64Variant, InputStream inputStream, byte[] bArr, int i) {
        int _readMore;
        int i2 = this._outputEnd - 6;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        int i3 = -3;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i <= 2) {
                break;
            }
            if (i4 > i3) {
                int _readMore2 = _readMore(inputStream, bArr, i4, i5, i);
                if (_readMore2 < 3) {
                    i5 = _readMore2;
                    i4 = 0;
                    break;
                }
                i5 = _readMore2;
                i3 = _readMore2 - 3;
                i4 = 0;
            }
            if (this._outputTail > i2) {
                _flushBuffer();
            }
            int i6 = i4 + 1;
            int i7 = bArr[i4] << 8;
            int i8 = i6 + 1;
            i4 = i8 + 1;
            i -= 3;
            this._outputTail = base64Variant.encodeBase64Chunk((((bArr[i6] & FlagsParser.UNKNOWN_FLAGS) | i7) << 8) | (bArr[i8] & FlagsParser.UNKNOWN_FLAGS), this._outputBuffer, this._outputTail);
            maxLineLength--;
            if (maxLineLength <= 0) {
                byte[] bArr2 = this._outputBuffer;
                int i9 = this._outputTail;
                this._outputTail = i9 + 1;
                bArr2[i9] = 92;
                int i10 = this._outputTail;
                this._outputTail = i10 + 1;
                bArr2[i10] = 110;
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
        }
        if (i <= 0 || (_readMore = _readMore(inputStream, bArr, i4, i5, i)) <= 0) {
            return i;
        }
        if (this._outputTail > i2) {
            _flushBuffer();
        }
        int i11 = bArr[0] << 16;
        int i12 = 1;
        if (1 < _readMore) {
            i11 |= (bArr[1] & FlagsParser.UNKNOWN_FLAGS) << 8;
            i12 = 2;
        }
        this._outputTail = base64Variant.encodeBase64Partial(i11, i12, this._outputBuffer, this._outputTail);
        return i - i12;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(SerializableString serializableString) {
        int appendUnquotedUTF8 = serializableString.appendUnquotedUTF8(this._outputBuffer, this._outputTail);
        if (appendUnquotedUTF8 < 0) {
            _writeBytes(serializableString.asUnquotedUTF8());
        } else {
            this._outputTail += appendUnquotedUTF8;
        }
    }

    private final void _writeStringSegmentASCII2(String str, int i, int i2) {
        if (this._outputTail + ((i2 - i) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int i3 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        int i4 = this._maximumNonEscapedChar;
        while (i < i2) {
            int i5 = i + 1;
            char charAt = str.charAt(i);
            if (charAt <= 127) {
                if (iArr[charAt] == 0) {
                    bArr[i3] = (byte) charAt;
                    i = i5;
                    i3++;
                } else {
                    int i6 = iArr[charAt];
                    if (i6 > 0) {
                        int i7 = i3 + 1;
                        bArr[i3] = 92;
                        i3 = i7 + 1;
                        bArr[i7] = (byte) i6;
                    } else {
                        i3 = _writeGenericEscape(charAt, i3);
                    }
                }
            } else if (charAt > i4) {
                i3 = _writeGenericEscape(charAt, i3);
            } else if (charAt <= 2047) {
                int i8 = i3 + 1;
                bArr[i3] = (byte) ((charAt >> 6) | AppearanceLibrary.APPEARANCE_GENERIC_WATCH);
                i3 = i8 + 1;
                bArr[i8] = (byte) ((charAt & '?') | 128);
            } else {
                i3 = _outputMultiByteChar(charAt, i3);
            }
            i = i5;
        }
        this._outputTail = i3;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigInteger bigInteger) {
        _verifyValueWrite("write a number");
        if (bigInteger == null) {
            _writeNull();
        } else if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(bigInteger.toString());
        } else {
            writeRaw(bigInteger.toString());
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeRaw(char[] cArr, int i, int i2) {
        int i3 = i2 + i2 + i2;
        int i4 = this._outputTail + i3;
        int i5 = this._outputEnd;
        if (i4 > i5) {
            if (i5 < i3) {
                _writeSegmentedRaw(cArr, i, i2);
                return;
            }
            _flushBuffer();
        }
        int i6 = i2 + i;
        while (i < i6) {
            do {
                char c2 = cArr[i];
                if (c2 > 127) {
                    int i7 = i + 1;
                    char c3 = cArr[i];
                    if (c3 < 2048) {
                        byte[] bArr = this._outputBuffer;
                        int i8 = this._outputTail;
                        this._outputTail = i8 + 1;
                        bArr[i8] = (byte) ((c3 >> 6) | AppearanceLibrary.APPEARANCE_GENERIC_WATCH);
                        int i9 = this._outputTail;
                        this._outputTail = i9 + 1;
                        bArr[i9] = (byte) ((c3 & '?') | 128);
                        i = i7;
                    } else {
                        i = _outputRawMultiByteChar(c3, cArr, i7, i6);
                    }
                } else {
                    byte[] bArr2 = this._outputBuffer;
                    int i10 = this._outputTail;
                    this._outputTail = i10 + 1;
                    bArr2[i10] = (byte) c2;
                    i++;
                }
            } while (i < i6);
            return;
        }
    }

    protected final void _writePPFieldName(SerializableString serializableString) {
        int writeFieldName = this._writeContext.writeFieldName(serializableString.getValue());
        if (writeFieldName != 4) {
            if (writeFieldName == 1) {
                this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
            } else {
                this._cfgPrettyPrinter.beforeObjectEntries(this);
            }
            boolean z = !this._cfgUnqNames;
            if (z) {
                if (this._outputTail >= this._outputEnd) {
                    _flushBuffer();
                }
                byte[] bArr = this._outputBuffer;
                int i = this._outputTail;
                this._outputTail = i + 1;
                bArr[i] = this._quoteChar;
            }
            int appendQuotedUTF8 = serializableString.appendQuotedUTF8(this._outputBuffer, this._outputTail);
            if (appendQuotedUTF8 < 0) {
                _writeBytes(serializableString.asQuotedUTF8());
            } else {
                this._outputTail += appendQuotedUTF8;
            }
            if (z) {
                if (this._outputTail >= this._outputEnd) {
                    _flushBuffer();
                }
                byte[] bArr2 = this._outputBuffer;
                int i2 = this._outputTail;
                this._outputTail = i2 + 1;
                bArr2[i2] = this._quoteChar;
                return;
            }
            return;
        }
        _reportError("Can not write a field name, expecting a value");
        throw null;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(SerializableString serializableString) {
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(serializableString);
            return;
        }
        int writeFieldName = this._writeContext.writeFieldName(serializableString.getValue());
        if (writeFieldName != 4) {
            if (writeFieldName == 1) {
                if (this._outputTail >= this._outputEnd) {
                    _flushBuffer();
                }
                byte[] bArr = this._outputBuffer;
                int i = this._outputTail;
                this._outputTail = i + 1;
                bArr[i] = 44;
            }
            if (this._cfgUnqNames) {
                _writeUnq(serializableString);
                return;
            }
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int i2 = this._outputTail;
            this._outputTail = i2 + 1;
            bArr2[i2] = this._quoteChar;
            int appendQuotedUTF8 = serializableString.appendQuotedUTF8(bArr2, this._outputTail);
            if (appendQuotedUTF8 < 0) {
                _writeBytes(serializableString.asQuotedUTF8());
            } else {
                this._outputTail += appendQuotedUTF8;
            }
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr3 = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr3[i3] = this._quoteChar;
            return;
        }
        _reportError("Can not write a field name, expecting a value");
        throw null;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(double d2) {
        if (!this._cfgNumbersAsStrings && (!NumberOutput.notFinite(d2) || !JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS.enabledIn(this._features))) {
            _verifyValueWrite("write a number");
            writeRaw(String.valueOf(d2));
        } else {
            writeString(String.valueOf(d2));
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeString(SerializableString serializableString) {
        _verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = this._quoteChar;
        int appendQuotedUTF8 = serializableString.appendQuotedUTF8(bArr, this._outputTail);
        if (appendQuotedUTF8 < 0) {
            _writeBytes(serializableString.asQuotedUTF8());
        } else {
            this._outputTail += appendQuotedUTF8;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = this._quoteChar;
    }

    private final void _writeCustomStringSegment2(String str, int i, int i2) {
        if (this._outputTail + ((i2 - i) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int i3 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        int i4 = this._maximumNonEscapedChar;
        if (i4 <= 0) {
            i4 = 65535;
        }
        CharacterEscapes characterEscapes = this._characterEscapes;
        while (i < i2) {
            int i5 = i + 1;
            char charAt = str.charAt(i);
            if (charAt <= 127) {
                if (iArr[charAt] == 0) {
                    bArr[i3] = (byte) charAt;
                    i = i5;
                    i3++;
                } else {
                    int i6 = iArr[charAt];
                    if (i6 > 0) {
                        int i7 = i3 + 1;
                        bArr[i3] = 92;
                        i3 = i7 + 1;
                        bArr[i7] = (byte) i6;
                    } else if (i6 == -2) {
                        SerializableString escapeSequence = characterEscapes.getEscapeSequence(charAt);
                        if (escapeSequence != null) {
                            i3 = _writeCustomEscape(bArr, i3, escapeSequence, i2 - i5);
                        } else {
                            _reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(charAt) + ", although was supposed to have one");
                            throw null;
                        }
                    } else {
                        i3 = _writeGenericEscape(charAt, i3);
                    }
                }
            } else if (charAt > i4) {
                i3 = _writeGenericEscape(charAt, i3);
            } else {
                SerializableString escapeSequence2 = characterEscapes.getEscapeSequence(charAt);
                if (escapeSequence2 != null) {
                    i3 = _writeCustomEscape(bArr, i3, escapeSequence2, i2 - i5);
                } else if (charAt <= 2047) {
                    int i8 = i3 + 1;
                    bArr[i3] = (byte) ((charAt >> 6) | AppearanceLibrary.APPEARANCE_GENERIC_WATCH);
                    i3 = i8 + 1;
                    bArr[i8] = (byte) ((charAt & '?') | 128);
                } else {
                    i3 = _outputMultiByteChar(charAt, i3);
                }
            }
            i = i5;
        }
        this._outputTail = i3;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(float f2) {
        if (!this._cfgNumbersAsStrings && (!NumberOutput.notFinite(f2) || !JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS.enabledIn(this._features))) {
            _verifyValueWrite("write a number");
            writeRaw(String.valueOf(f2));
        } else {
            writeString(String.valueOf(f2));
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(char c2) {
        if (this._outputTail + 3 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        if (c2 <= 127) {
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = (byte) c2;
        } else {
            if (c2 < 2048) {
                int i2 = this._outputTail;
                this._outputTail = i2 + 1;
                bArr[i2] = (byte) ((c2 >> 6) | AppearanceLibrary.APPEARANCE_GENERIC_WATCH);
                int i3 = this._outputTail;
                this._outputTail = i3 + 1;
                bArr[i3] = (byte) ((c2 & '?') | 128);
                return;
            }
            _outputRawMultiByteChar(c2, null, 0, 0);
        }
    }

    protected final int _writeBinary(Base64Variant base64Variant, InputStream inputStream, byte[] bArr) {
        int _readMore;
        int i = this._outputEnd - 6;
        int i2 = -3;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i4 > i2) {
                _readMore = _readMore(inputStream, bArr, i4, i5, bArr.length);
                if (_readMore < 3) {
                    break;
                }
                i5 = _readMore;
                i2 = _readMore - 3;
                i4 = 0;
            }
            if (this._outputTail > i) {
                _flushBuffer();
            }
            int i6 = i4 + 1;
            int i7 = bArr[i4] << 8;
            int i8 = i6 + 1;
            i4 = i8 + 1;
            i3 += 3;
            this._outputTail = base64Variant.encodeBase64Chunk((((bArr[i6] & FlagsParser.UNKNOWN_FLAGS) | i7) << 8) | (bArr[i8] & FlagsParser.UNKNOWN_FLAGS), this._outputBuffer, this._outputTail);
            maxLineLength--;
            if (maxLineLength <= 0) {
                byte[] bArr2 = this._outputBuffer;
                int i9 = this._outputTail;
                this._outputTail = i9 + 1;
                bArr2[i9] = 92;
                int i10 = this._outputTail;
                this._outputTail = i10 + 1;
                bArr2[i10] = 110;
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
        }
        if (_readMore <= 0) {
            return i3;
        }
        if (this._outputTail > i) {
            _flushBuffer();
        }
        int i11 = bArr[0] << 16;
        int i12 = 1;
        if (1 < _readMore) {
            i11 |= (bArr[1] & FlagsParser.UNKNOWN_FLAGS) << 8;
            i12 = 2;
        }
        int i13 = i3 + i12;
        this._outputTail = base64Variant.encodeBase64Partial(i11, i12, this._outputBuffer, this._outputTail);
        return i13;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigDecimal bigDecimal) {
        _verifyValueWrite("write a number");
        if (bigDecimal == null) {
            _writeNull();
        } else if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(_asString(bigDecimal));
        } else {
            writeRaw(_asString(bigDecimal));
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(String str) {
        _verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(str);
        } else {
            writeRaw(str);
        }
    }
}
