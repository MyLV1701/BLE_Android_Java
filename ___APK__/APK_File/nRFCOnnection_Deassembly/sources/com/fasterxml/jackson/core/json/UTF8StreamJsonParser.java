package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import no.nordicsemi.android.mcp.ble.parser.AppearanceLibrary;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public class UTF8StreamJsonParser extends ParserBase {
    protected boolean _bufferRecyclable;
    protected byte[] _inputBuffer;
    protected InputStream _inputStream;
    protected int _nameStartCol;
    protected int _nameStartOffset;
    protected int _nameStartRow;
    protected ObjectCodec _objectCodec;
    private int _quad1;
    protected int[] _quadBuffer;
    protected final ByteQuadsCanonicalizer _symbols;
    protected boolean _tokenIncomplete;
    private static final int FEAT_MASK_TRAILING_COMMA = JsonParser.Feature.ALLOW_TRAILING_COMMA.getMask();
    private static final int FEAT_MASK_LEADING_ZEROS = JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS.getMask();
    private static final int FEAT_MASK_NON_NUM_NUMBERS = JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS.getMask();
    private static final int FEAT_MASK_ALLOW_MISSING = JsonParser.Feature.ALLOW_MISSING_VALUES.getMask();
    private static final int FEAT_MASK_ALLOW_SINGLE_QUOTES = JsonParser.Feature.ALLOW_SINGLE_QUOTES.getMask();
    private static final int FEAT_MASK_ALLOW_UNQUOTED_NAMES = JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES.getMask();
    private static final int FEAT_MASK_ALLOW_JAVA_COMMENTS = JsonParser.Feature.ALLOW_COMMENTS.getMask();
    private static final int FEAT_MASK_ALLOW_YAML_COMMENTS = JsonParser.Feature.ALLOW_YAML_COMMENTS.getMask();
    private static final int[] _icUTF8 = CharTypes.getInputCodeUtf8();
    protected static final int[] _icLatin1 = CharTypes.getInputCodeLatin1();

    public UTF8StreamJsonParser(IOContext iOContext, int i, InputStream inputStream, ObjectCodec objectCodec, ByteQuadsCanonicalizer byteQuadsCanonicalizer, byte[] bArr, int i2, int i3, int i4, boolean z) {
        super(iOContext, i);
        this._quadBuffer = new int[16];
        this._inputStream = inputStream;
        this._objectCodec = objectCodec;
        this._symbols = byteQuadsCanonicalizer;
        this._inputBuffer = bArr;
        this._inputPtr = i2;
        this._inputEnd = i3;
        this._currInputRowStart = i2 - i4;
        this._currInputProcessed = (-i2) + i4;
        this._bufferRecyclable = z;
    }

    private final void _checkMatchEnd(String str, int i, int i2) {
        if (Character.isJavaIdentifierPart((char) _decodeCharForError(i2))) {
            _reportInvalidToken(str.substring(0, i));
            throw null;
        }
    }

    private final void _closeArrayScope() {
        _updateLocation();
        if (this._parsingContext.inArray()) {
            this._parsingContext = this._parsingContext.clearAndGetParent();
        } else {
            _reportMismatchedEndMarker(93, '}');
            throw null;
        }
    }

    private final void _closeObjectScope() {
        _updateLocation();
        if (this._parsingContext.inObject()) {
            this._parsingContext = this._parsingContext.clearAndGetParent();
        } else {
            _reportMismatchedEndMarker(125, ']');
            throw null;
        }
    }

    private final JsonToken _closeScope(int i) {
        if (i == 125) {
            _closeObjectScope();
            JsonToken jsonToken = JsonToken.END_OBJECT;
            this._currToken = jsonToken;
            return jsonToken;
        }
        _closeArrayScope();
        JsonToken jsonToken2 = JsonToken.END_ARRAY;
        this._currToken = jsonToken2;
        return jsonToken2;
    }

    private final int _decodeUtf8_2(int i) {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b2 = bArr[i2];
        if ((b2 & 192) == 128) {
            return ((i & 31) << 6) | (b2 & 63);
        }
        _reportInvalidOther(b2 & FlagsParser.UNKNOWN_FLAGS, this._inputPtr);
        throw null;
    }

    private final int _decodeUtf8_3(int i) {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b2 = bArr[i3];
        if ((b2 & 192) == 128) {
            int i4 = (i2 << 6) | (b2 & 63);
            if (this._inputPtr >= this._inputEnd) {
                _loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int i5 = this._inputPtr;
            this._inputPtr = i5 + 1;
            byte b3 = bArr2[i5];
            if ((b3 & 192) == 128) {
                return (i4 << 6) | (b3 & 63);
            }
            _reportInvalidOther(b3 & FlagsParser.UNKNOWN_FLAGS, this._inputPtr);
            throw null;
        }
        _reportInvalidOther(b2 & FlagsParser.UNKNOWN_FLAGS, this._inputPtr);
        throw null;
    }

    private final int _decodeUtf8_3fast(int i) {
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b2 = bArr[i3];
        if ((b2 & 192) == 128) {
            int i4 = (i2 << 6) | (b2 & 63);
            int i5 = this._inputPtr;
            this._inputPtr = i5 + 1;
            byte b3 = bArr[i5];
            if ((b3 & 192) == 128) {
                return (i4 << 6) | (b3 & 63);
            }
            _reportInvalidOther(b3 & FlagsParser.UNKNOWN_FLAGS, this._inputPtr);
            throw null;
        }
        _reportInvalidOther(b2 & FlagsParser.UNKNOWN_FLAGS, this._inputPtr);
        throw null;
    }

    private final int _decodeUtf8_4(int i) {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b2 = bArr[i2];
        if ((b2 & 192) == 128) {
            int i3 = ((i & 7) << 6) | (b2 & 63);
            if (this._inputPtr >= this._inputEnd) {
                _loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            byte b3 = bArr2[i4];
            if ((b3 & 192) == 128) {
                int i5 = (i3 << 6) | (b3 & 63);
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i6 = this._inputPtr;
                this._inputPtr = i6 + 1;
                byte b4 = bArr3[i6];
                if ((b4 & 192) == 128) {
                    return ((i5 << 6) | (b4 & 63)) - 65536;
                }
                _reportInvalidOther(b4 & FlagsParser.UNKNOWN_FLAGS, this._inputPtr);
                throw null;
            }
            _reportInvalidOther(b3 & FlagsParser.UNKNOWN_FLAGS, this._inputPtr);
            throw null;
        }
        _reportInvalidOther(b2 & FlagsParser.UNKNOWN_FLAGS, this._inputPtr);
        throw null;
    }

    private final void _finishString2(char[] cArr, int i) {
        int[] iArr = _icUTF8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i2 = this._inputPtr;
            if (i2 >= this._inputEnd) {
                _loadMoreGuaranteed();
                i2 = this._inputPtr;
            }
            if (i >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            int min = Math.min(this._inputEnd, (cArr.length - i) + i2);
            while (true) {
                if (i2 < min) {
                    int i3 = i2 + 1;
                    int i4 = bArr[i2] & FlagsParser.UNKNOWN_FLAGS;
                    if (iArr[i4] != 0) {
                        this._inputPtr = i3;
                        if (i4 == 34) {
                            this._textBuffer.setCurrentLength(i);
                            return;
                        }
                        int i5 = iArr[i4];
                        if (i5 == 1) {
                            i4 = _decodeEscaped();
                        } else if (i5 == 2) {
                            i4 = _decodeUtf8_2(i4);
                        } else if (i5 != 3) {
                            if (i5 == 4) {
                                int _decodeUtf8_4 = _decodeUtf8_4(i4);
                                int i6 = i + 1;
                                cArr[i] = (char) (55296 | (_decodeUtf8_4 >> 10));
                                if (i6 >= cArr.length) {
                                    cArr = this._textBuffer.finishCurrentSegment();
                                    i6 = 0;
                                }
                                i4 = (_decodeUtf8_4 & 1023) | 56320;
                                i = i6;
                            } else if (i4 < 32) {
                                _throwUnquotedSpace(i4, "string value");
                            } else {
                                _reportInvalidChar(i4);
                                throw null;
                            }
                        } else if (this._inputEnd - this._inputPtr >= 2) {
                            i4 = _decodeUtf8_3fast(i4);
                        } else {
                            i4 = _decodeUtf8_3(i4);
                        }
                        if (i >= cArr.length) {
                            cArr = this._textBuffer.finishCurrentSegment();
                            i = 0;
                        }
                        cArr[i] = (char) i4;
                        i++;
                    } else {
                        cArr[i] = (char) i4;
                        i2 = i3;
                        i++;
                    }
                } else {
                    this._inputPtr = i2;
                    break;
                }
            }
        }
    }

    private final void _matchToken2(String str, int i) {
        int i2;
        int length = str.length();
        do {
            if ((this._inputPtr < this._inputEnd || _loadMore()) && this._inputBuffer[this._inputPtr] == str.charAt(i)) {
                this._inputPtr++;
                i++;
            } else {
                _reportInvalidToken(str.substring(0, i));
                throw null;
            }
        } while (i < length);
        if ((this._inputPtr < this._inputEnd || _loadMore()) && (i2 = this._inputBuffer[this._inputPtr] & FlagsParser.UNKNOWN_FLAGS) >= 48 && i2 != 93 && i2 != 125) {
            _checkMatchEnd(str, i, i2);
        }
    }

    private final JsonToken _nextAfterName() {
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        this._currToken = jsonToken;
        return jsonToken;
    }

    private final JsonToken _nextTokenNotInObject(int i) {
        if (i == 34) {
            this._tokenIncomplete = true;
            JsonToken jsonToken = JsonToken.VALUE_STRING;
            this._currToken = jsonToken;
            return jsonToken;
        }
        if (i == 45) {
            JsonToken _parseNegNumber = _parseNegNumber();
            this._currToken = _parseNegNumber;
            return _parseNegNumber;
        }
        if (i == 91) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            JsonToken jsonToken2 = JsonToken.START_ARRAY;
            this._currToken = jsonToken2;
            return jsonToken2;
        }
        if (i == 102) {
            _matchFalse();
            JsonToken jsonToken3 = JsonToken.VALUE_FALSE;
            this._currToken = jsonToken3;
            return jsonToken3;
        }
        if (i == 110) {
            _matchNull();
            JsonToken jsonToken4 = JsonToken.VALUE_NULL;
            this._currToken = jsonToken4;
            return jsonToken4;
        }
        if (i == 116) {
            _matchTrue();
            JsonToken jsonToken5 = JsonToken.VALUE_TRUE;
            this._currToken = jsonToken5;
            return jsonToken5;
        }
        if (i != 123) {
            switch (i) {
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    JsonToken _parsePosNumber = _parsePosNumber(i);
                    this._currToken = _parsePosNumber;
                    return _parsePosNumber;
                default:
                    JsonToken _handleUnexpectedValue = _handleUnexpectedValue(i);
                    this._currToken = _handleUnexpectedValue;
                    return _handleUnexpectedValue;
            }
        }
        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        JsonToken jsonToken6 = JsonToken.START_OBJECT;
        this._currToken = jsonToken6;
        return jsonToken6;
    }

    private static final int _padLastQuad(int i, int i2) {
        return i2 == 4 ? i : i | ((-1) << (i2 << 3));
    }

    private final JsonToken _parseFloat(char[] cArr, int i, int i2, boolean z, int i3) {
        char[] cArr2;
        int i4;
        int i5;
        boolean z2;
        int i6 = i2;
        int i7 = 0;
        if (i6 == 46) {
            cArr2 = cArr;
            int i8 = i;
            if (i8 >= cArr2.length) {
                cArr2 = this._textBuffer.finishCurrentSegment();
                i8 = 0;
            }
            i4 = i8 + 1;
            cArr2[i8] = (char) i6;
            int i9 = i6;
            int i10 = 0;
            while (true) {
                if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                    z2 = true;
                    break;
                }
                byte[] bArr = this._inputBuffer;
                int i11 = this._inputPtr;
                this._inputPtr = i11 + 1;
                i9 = bArr[i11] & FlagsParser.UNKNOWN_FLAGS;
                if (i9 < 48 || i9 > 57) {
                    break;
                }
                i10++;
                if (i4 >= cArr2.length) {
                    cArr2 = this._textBuffer.finishCurrentSegment();
                    i4 = 0;
                }
                cArr2[i4] = (char) i9;
                i4++;
            }
            z2 = false;
            if (i10 == 0) {
                reportUnexpectedNumberChar(i9, "Decimal point not followed by a digit");
                throw null;
            }
            int i12 = i9;
            i5 = i10;
            i6 = i12;
        } else {
            cArr2 = cArr;
            i4 = i;
            i5 = 0;
            z2 = false;
        }
        if (i6 == 101 || i6 == 69) {
            if (i4 >= cArr2.length) {
                cArr2 = this._textBuffer.finishCurrentSegment();
                i4 = 0;
            }
            int i13 = i4 + 1;
            cArr2[i4] = (char) i6;
            if (this._inputPtr >= this._inputEnd) {
                _loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int i14 = this._inputPtr;
            this._inputPtr = i14 + 1;
            i6 = bArr2[i14] & FlagsParser.UNKNOWN_FLAGS;
            if (i6 == 45 || i6 == 43) {
                if (i13 >= cArr2.length) {
                    cArr2 = this._textBuffer.finishCurrentSegment();
                    i13 = 0;
                }
                int i15 = i13 + 1;
                cArr2[i13] = (char) i6;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i16 = this._inputPtr;
                this._inputPtr = i16 + 1;
                i6 = bArr3[i16] & FlagsParser.UNKNOWN_FLAGS;
                i13 = i15;
            }
            char[] cArr3 = cArr2;
            int i17 = 0;
            while (i6 >= 48 && i6 <= 57) {
                i17++;
                if (i13 >= cArr3.length) {
                    cArr3 = this._textBuffer.finishCurrentSegment();
                    i13 = 0;
                }
                int i18 = i13 + 1;
                cArr3[i13] = (char) i6;
                if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                    i7 = i17;
                    i4 = i18;
                    z2 = true;
                    break;
                }
                byte[] bArr4 = this._inputBuffer;
                int i19 = this._inputPtr;
                this._inputPtr = i19 + 1;
                i6 = bArr4[i19] & FlagsParser.UNKNOWN_FLAGS;
                i13 = i18;
            }
            i7 = i17;
            i4 = i13;
            if (i7 == 0) {
                reportUnexpectedNumberChar(i6, "Exponent indicator not followed by a digit");
                throw null;
            }
        }
        if (!z2) {
            this._inputPtr--;
            if (this._parsingContext.inRoot()) {
                _verifyRootSpace(i6);
            }
        }
        this._textBuffer.setCurrentLength(i4);
        return resetFloat(z, i3, i5, i7);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0044, code lost:
    
        if (r3 == 46) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0048, code lost:
    
        if (r3 == 101) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004c, code lost:
    
        if (r3 != 69) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004f, code lost:
    
        r6._inputPtr--;
        r6._textBuffer.setCurrentLength(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0060, code lost:
    
        if (r6._parsingContext.inRoot() == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0062, code lost:
    
        _verifyRootSpace(r6._inputBuffer[r6._inputPtr] & no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser.UNKNOWN_FLAGS);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0071, code lost:
    
        return resetInt(r9, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0078, code lost:
    
        return _parseFloat(r1, r2, r3, r9, r5);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.fasterxml.jackson.core.JsonToken _parseNumber2(char[] r7, int r8, boolean r9, int r10) {
        /*
            r6 = this;
            r1 = r7
            r2 = r8
            r5 = r10
        L3:
            int r7 = r6._inputPtr
            int r8 = r6._inputEnd
            if (r7 < r8) goto L19
            boolean r7 = r6._loadMore()
            if (r7 != 0) goto L19
            com.fasterxml.jackson.core.util.TextBuffer r7 = r6._textBuffer
            r7.setCurrentLength(r2)
            com.fasterxml.jackson.core.JsonToken r7 = r6.resetInt(r9, r5)
            return r7
        L19:
            byte[] r7 = r6._inputBuffer
            int r8 = r6._inputPtr
            int r10 = r8 + 1
            r6._inputPtr = r10
            r7 = r7[r8]
            r3 = r7 & 255(0xff, float:3.57E-43)
            r7 = 57
            if (r3 > r7) goto L42
            r7 = 48
            if (r3 >= r7) goto L2e
            goto L42
        L2e:
            int r7 = r1.length
            if (r2 < r7) goto L39
            com.fasterxml.jackson.core.util.TextBuffer r7 = r6._textBuffer
            char[] r7 = r7.finishCurrentSegment()
            r2 = 0
            r1 = r7
        L39:
            int r7 = r2 + 1
            char r8 = (char) r3
            r1[r2] = r8
            int r5 = r5 + 1
            r2 = r7
            goto L3
        L42:
            r7 = 46
            if (r3 == r7) goto L72
            r7 = 101(0x65, float:1.42E-43)
            if (r3 == r7) goto L72
            r7 = 69
            if (r3 != r7) goto L4f
            goto L72
        L4f:
            int r7 = r6._inputPtr
            int r7 = r7 + (-1)
            r6._inputPtr = r7
            com.fasterxml.jackson.core.util.TextBuffer r7 = r6._textBuffer
            r7.setCurrentLength(r2)
            com.fasterxml.jackson.core.json.JsonReadContext r7 = r6._parsingContext
            boolean r7 = r7.inRoot()
            if (r7 == 0) goto L6d
            byte[] r7 = r6._inputBuffer
            int r8 = r6._inputPtr
            r7 = r7[r8]
            r7 = r7 & 255(0xff, float:3.57E-43)
            r6._verifyRootSpace(r7)
        L6d:
            com.fasterxml.jackson.core.JsonToken r7 = r6.resetInt(r9, r5)
            return r7
        L72:
            r0 = r6
            r4 = r9
            com.fasterxml.jackson.core.JsonToken r7 = r0._parseFloat(r1, r2, r3, r4, r5)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._parseNumber2(char[], int, boolean, int):com.fasterxml.jackson.core.JsonToken");
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0043, code lost:
    
        _reportInvalidEOF(" in a comment", null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0048, code lost:
    
        throw null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void _skipCComment() {
        /*
            r5 = this;
            int[] r0 = com.fasterxml.jackson.core.io.CharTypes.getInputCodeComment()
        L4:
            int r1 = r5._inputPtr
            int r2 = r5._inputEnd
            r3 = 0
            if (r1 < r2) goto L11
            boolean r1 = r5._loadMore()
            if (r1 == 0) goto L43
        L11:
            byte[] r1 = r5._inputBuffer
            int r2 = r5._inputPtr
            int r4 = r2 + 1
            r5._inputPtr = r4
            r1 = r1[r2]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r2 = r0[r1]
            if (r2 == 0) goto L4
            r4 = 2
            if (r2 == r4) goto L73
            r4 = 3
            if (r2 == r4) goto L6f
            r4 = 4
            if (r2 == r4) goto L6b
            r4 = 10
            if (r2 == r4) goto L60
            r4 = 13
            if (r2 == r4) goto L5c
            r4 = 42
            if (r2 != r4) goto L58
            int r1 = r5._inputPtr
            int r2 = r5._inputEnd
            if (r1 < r2) goto L49
            boolean r1 = r5._loadMore()
            if (r1 == 0) goto L43
            goto L49
        L43:
            java.lang.String r0 = " in a comment"
            r5._reportInvalidEOF(r0, r3)
            throw r3
        L49:
            byte[] r1 = r5._inputBuffer
            int r2 = r5._inputPtr
            r1 = r1[r2]
            r3 = 47
            if (r1 != r3) goto L4
            int r2 = r2 + 1
            r5._inputPtr = r2
            return
        L58:
            r5._reportInvalidChar(r1)
            throw r3
        L5c:
            r5._skipCR()
            goto L4
        L60:
            int r1 = r5._currInputRow
            int r1 = r1 + 1
            r5._currInputRow = r1
            int r1 = r5._inputPtr
            r5._currInputRowStart = r1
            goto L4
        L6b:
            r5._skipUtf8_4(r1)
            goto L4
        L6f:
            r5._skipUtf8_3()
            goto L4
        L73:
            r5._skipUtf8_2()
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._skipCComment():void");
    }

    private final int _skipColon() {
        int i = this._inputPtr;
        if (i + 4 >= this._inputEnd) {
            return _skipColon2(false);
        }
        byte[] bArr = this._inputBuffer;
        byte b2 = bArr[i];
        if (b2 == 58) {
            int i2 = i + 1;
            this._inputPtr = i2;
            byte b3 = bArr[i2];
            if (b3 > 32) {
                if (b3 != 47 && b3 != 35) {
                    this._inputPtr++;
                    return b3;
                }
                return _skipColon2(true);
            }
            if (b3 == 32 || b3 == 9) {
                byte[] bArr2 = this._inputBuffer;
                int i3 = this._inputPtr + 1;
                this._inputPtr = i3;
                byte b4 = bArr2[i3];
                if (b4 > 32) {
                    if (b4 != 47 && b4 != 35) {
                        this._inputPtr++;
                        return b4;
                    }
                    return _skipColon2(true);
                }
            }
            return _skipColon2(true);
        }
        if (b2 == 32 || b2 == 9) {
            byte[] bArr3 = this._inputBuffer;
            int i4 = this._inputPtr + 1;
            this._inputPtr = i4;
            b2 = bArr3[i4];
        }
        if (b2 == 58) {
            byte[] bArr4 = this._inputBuffer;
            int i5 = this._inputPtr + 1;
            this._inputPtr = i5;
            byte b5 = bArr4[i5];
            if (b5 > 32) {
                if (b5 != 47 && b5 != 35) {
                    this._inputPtr++;
                    return b5;
                }
                return _skipColon2(true);
            }
            if (b5 == 32 || b5 == 9) {
                byte[] bArr5 = this._inputBuffer;
                int i6 = this._inputPtr + 1;
                this._inputPtr = i6;
                byte b6 = bArr5[i6];
                if (b6 > 32) {
                    if (b6 != 47 && b6 != 35) {
                        this._inputPtr++;
                        return b6;
                    }
                    return _skipColon2(true);
                }
            }
            return _skipColon2(true);
        }
        return _skipColon2(false);
    }

    private final int _skipColon2(boolean z) {
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(" within/between " + this._parsingContext.typeDesc() + " entries", null);
                throw null;
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & FlagsParser.UNKNOWN_FLAGS;
            if (i2 > 32) {
                if (i2 == 47) {
                    _skipComment();
                } else if (i2 != 35 || !_skipYAMLComment()) {
                    if (z) {
                        return i2;
                    }
                    if (i2 != 58) {
                        _reportUnexpectedChar(i2, "was expecting a colon to separate field name and value");
                        throw null;
                    }
                    z = true;
                }
            } else if (i2 == 32) {
                continue;
            } else if (i2 == 10) {
                this._currInputRow++;
                this._currInputRowStart = this._inputPtr;
            } else if (i2 == 13) {
                _skipCR();
            } else if (i2 != 9) {
                _throwInvalidSpace(i2);
                throw null;
            }
        }
    }

    private final void _skipComment() {
        if ((this._features & FEAT_MASK_ALLOW_JAVA_COMMENTS) != 0) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(" in a comment", null);
                throw null;
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & FlagsParser.UNKNOWN_FLAGS;
            if (i2 == 47) {
                _skipLine();
                return;
            } else if (i2 == 42) {
                _skipCComment();
                return;
            } else {
                _reportUnexpectedChar(i2, "was expecting either '*' or '/' for a comment");
                throw null;
            }
        }
        _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        throw null;
    }

    private final void _skipLine() {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                return;
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & FlagsParser.UNKNOWN_FLAGS;
            int i3 = inputCodeComment[i2];
            if (i3 != 0) {
                if (i3 == 2) {
                    _skipUtf8_2();
                } else if (i3 == 3) {
                    _skipUtf8_3();
                } else if (i3 == 4) {
                    _skipUtf8_4(i2);
                } else if (i3 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                    return;
                } else if (i3 == 13) {
                    _skipCR();
                    return;
                } else if (i3 != 42 && i3 < 0) {
                    _reportInvalidChar(i2);
                    throw null;
                }
            }
        }
    }

    private final void _skipUtf8_2() {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b2 = bArr[i];
        if ((b2 & 192) == 128) {
            return;
        }
        _reportInvalidOther(b2 & FlagsParser.UNKNOWN_FLAGS, this._inputPtr);
        throw null;
    }

    private final void _skipUtf8_3() {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b2 = bArr[i];
        if ((b2 & 192) == 128) {
            if (this._inputPtr >= this._inputEnd) {
                _loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            byte b3 = bArr2[i2];
            if ((b3 & 192) == 128) {
                return;
            }
            _reportInvalidOther(b3 & FlagsParser.UNKNOWN_FLAGS, this._inputPtr);
            throw null;
        }
        _reportInvalidOther(b2 & FlagsParser.UNKNOWN_FLAGS, this._inputPtr);
        throw null;
    }

    private final void _skipUtf8_4(int i) {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b2 = bArr[i2];
        if ((b2 & 192) == 128) {
            if (this._inputPtr >= this._inputEnd) {
                _loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            byte b3 = bArr2[i3];
            if ((b3 & 192) == 128) {
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                byte b4 = bArr3[i4];
                if ((b4 & 192) == 128) {
                    return;
                }
                _reportInvalidOther(b4 & FlagsParser.UNKNOWN_FLAGS, this._inputPtr);
                throw null;
            }
            _reportInvalidOther(b3 & FlagsParser.UNKNOWN_FLAGS, this._inputPtr);
            throw null;
        }
        _reportInvalidOther(b2 & FlagsParser.UNKNOWN_FLAGS, this._inputPtr);
        throw null;
    }

    private final int _skipWS() {
        while (true) {
            int i = this._inputPtr;
            if (i < this._inputEnd) {
                byte[] bArr = this._inputBuffer;
                this._inputPtr = i + 1;
                int i2 = bArr[i] & FlagsParser.UNKNOWN_FLAGS;
                if (i2 > 32) {
                    if (i2 != 47 && i2 != 35) {
                        return i2;
                    }
                    this._inputPtr--;
                    return _skipWS2();
                }
                if (i2 != 32) {
                    if (i2 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = this._inputPtr;
                    } else if (i2 == 13) {
                        _skipCR();
                    } else if (i2 != 9) {
                        _throwInvalidSpace(i2);
                        throw null;
                    }
                }
            } else {
                return _skipWS2();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x0051, code lost:
    
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int _skipWS2() {
        /*
            r3 = this;
        L0:
            int r0 = r3._inputPtr
            int r1 = r3._inputEnd
            if (r0 < r1) goto L2e
            boolean r0 = r3._loadMore()
            if (r0 == 0) goto Ld
            goto L2e
        Ld:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unexpected end-of-input within/between "
            r0.append(r1)
            com.fasterxml.jackson.core.json.JsonReadContext r1 = r3._parsingContext
            java.lang.String r1 = r1.typeDesc()
            r0.append(r1)
            java.lang.String r1 = " entries"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.fasterxml.jackson.core.JsonParseException r0 = r3._constructError(r0)
            throw r0
        L2e:
            byte[] r0 = r3._inputBuffer
            int r1 = r3._inputPtr
            int r2 = r1 + 1
            r3._inputPtr = r2
            r0 = r0[r1]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r1 = 32
            if (r0 <= r1) goto L52
            r1 = 47
            if (r0 != r1) goto L46
            r3._skipComment()
            goto L0
        L46:
            r1 = 35
            if (r0 != r1) goto L51
            boolean r1 = r3._skipYAMLComment()
            if (r1 == 0) goto L51
            goto L0
        L51:
            return r0
        L52:
            if (r0 == r1) goto L0
            r1 = 10
            if (r0 != r1) goto L63
            int r0 = r3._currInputRow
            int r0 = r0 + 1
            r3._currInputRow = r0
            int r0 = r3._inputPtr
            r3._currInputRowStart = r0
            goto L0
        L63:
            r1 = 13
            if (r0 != r1) goto L6b
            r3._skipCR()
            goto L0
        L6b:
            r1 = 9
            if (r0 != r1) goto L70
            goto L0
        L70:
            r3._throwInvalidSpace(r0)
            r0 = 0
            goto L76
        L75:
            throw r0
        L76:
            goto L75
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._skipWS2():int");
    }

    private final int _skipWSOrEnd() {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            return _eofAsNextChar();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = bArr[i] & FlagsParser.UNKNOWN_FLAGS;
        if (i2 > 32) {
            if (i2 != 47 && i2 != 35) {
                return i2;
            }
            this._inputPtr--;
            return _skipWSOrEnd2();
        }
        if (i2 != 32) {
            if (i2 == 10) {
                this._currInputRow++;
                this._currInputRowStart = this._inputPtr;
            } else if (i2 == 13) {
                _skipCR();
            } else if (i2 != 9) {
                _throwInvalidSpace(i2);
                throw null;
            }
        }
        while (true) {
            int i3 = this._inputPtr;
            if (i3 < this._inputEnd) {
                byte[] bArr2 = this._inputBuffer;
                this._inputPtr = i3 + 1;
                int i4 = bArr2[i3] & FlagsParser.UNKNOWN_FLAGS;
                if (i4 > 32) {
                    if (i4 != 47 && i4 != 35) {
                        return i4;
                    }
                    this._inputPtr--;
                    return _skipWSOrEnd2();
                }
                if (i4 != 32) {
                    if (i4 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = this._inputPtr;
                    } else if (i4 == 13) {
                        _skipCR();
                    } else if (i4 != 9) {
                        _throwInvalidSpace(i4);
                        throw null;
                    }
                }
            } else {
                return _skipWSOrEnd2();
            }
        }
    }

    private final int _skipWSOrEnd2() {
        int i;
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                return _eofAsNextChar();
            }
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            i = bArr[i2] & FlagsParser.UNKNOWN_FLAGS;
            if (i > 32) {
                if (i == 47) {
                    _skipComment();
                } else if (i != 35 || !_skipYAMLComment()) {
                    break;
                }
            } else if (i == 32) {
                continue;
            } else if (i == 10) {
                this._currInputRow++;
                this._currInputRowStart = this._inputPtr;
            } else if (i == 13) {
                _skipCR();
            } else if (i != 9) {
                _throwInvalidSpace(i);
                throw null;
            }
        }
        return i;
    }

    private final boolean _skipYAMLComment() {
        if ((this._features & FEAT_MASK_ALLOW_YAML_COMMENTS) == 0) {
            return false;
        }
        _skipLine();
        return true;
    }

    private final void _updateLocation() {
        this._tokenInputRow = this._currInputRow;
        int i = this._inputPtr;
        this._tokenInputTotal = this._currInputProcessed + i;
        this._tokenInputCol = i - this._currInputRowStart;
    }

    private final void _updateNameLocation() {
        this._nameStartRow = this._currInputRow;
        int i = this._inputPtr;
        this._nameStartOffset = i;
        this._nameStartCol = i - this._currInputRowStart;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0029, code lost:
    
        if (r0 == 48) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
    
        if (r6._inputPtr < r6._inputEnd) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0035, code lost:
    
        if (_loadMore() == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0037, code lost:
    
        r0 = r6._inputBuffer;
        r1 = r6._inputPtr;
        r0 = r0[r1] & no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser.UNKNOWN_FLAGS;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003f, code lost:
    
        if (r0 < 48) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0041, code lost:
    
        if (r0 <= 57) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0044, code lost:
    
        r6._inputPtr = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0048, code lost:
    
        if (r0 == 48) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004b, code lost:
    
        return 48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004c, code lost:
    
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int _verifyNoLeadingZeroes() {
        /*
            r6 = this;
            int r0 = r6._inputPtr
            int r1 = r6._inputEnd
            r2 = 48
            if (r0 < r1) goto Lf
            boolean r0 = r6._loadMore()
            if (r0 != 0) goto Lf
            return r2
        Lf:
            byte[] r0 = r6._inputBuffer
            int r1 = r6._inputPtr
            r0 = r0[r1]
            r0 = r0 & 255(0xff, float:3.57E-43)
            if (r0 < r2) goto L54
            r3 = 57
            if (r0 <= r3) goto L1e
            goto L54
        L1e:
            int r4 = r6._features
            int r5 = com.fasterxml.jackson.core.json.UTF8StreamJsonParser.FEAT_MASK_LEADING_ZEROS
            r4 = r4 & r5
            if (r4 == 0) goto L4d
            int r1 = r1 + 1
            r6._inputPtr = r1
            if (r0 != r2) goto L4c
        L2b:
            int r1 = r6._inputPtr
            int r4 = r6._inputEnd
            if (r1 < r4) goto L37
            boolean r1 = r6._loadMore()
            if (r1 == 0) goto L4c
        L37:
            byte[] r0 = r6._inputBuffer
            int r1 = r6._inputPtr
            r0 = r0[r1]
            r0 = r0 & 255(0xff, float:3.57E-43)
            if (r0 < r2) goto L4b
            if (r0 <= r3) goto L44
            goto L4b
        L44:
            int r1 = r1 + 1
            r6._inputPtr = r1
            if (r0 == r2) goto L2b
            goto L4c
        L4b:
            return r2
        L4c:
            return r0
        L4d:
            java.lang.String r0 = "Leading zeroes not allowed"
            r6.reportInvalidNumber(r0)
            r0 = 0
            throw r0
        L54:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._verifyNoLeadingZeroes():int");
    }

    private final void _verifyRootSpace(int i) {
        this._inputPtr++;
        if (i != 9) {
            if (i == 10) {
                this._currInputRow++;
                this._currInputRowStart = this._inputPtr;
            } else if (i == 13) {
                _skipCR();
            } else {
                if (i == 32) {
                    return;
                }
                _reportMissingRootWS(i);
                throw null;
            }
        }
    }

    private final String addName(int[] iArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6 = ((i << 2) - 4) + i2;
        if (i2 < 4) {
            int i7 = i - 1;
            i3 = iArr[i7];
            iArr[i7] = i3 << ((4 - i2) << 3);
        } else {
            i3 = 0;
        }
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i8 = 0;
        int i9 = 0;
        while (i8 < i6) {
            int i10 = (iArr[i8 >> 2] >> ((3 - (i8 & 3)) << 3)) & 255;
            i8++;
            if (i10 > 127) {
                if ((i10 & 224) == 192) {
                    i4 = i10 & 31;
                    i5 = 1;
                } else if ((i10 & 240) == 224) {
                    i4 = i10 & 15;
                    i5 = 2;
                } else {
                    if ((i10 & 248) != 240) {
                        _reportInvalidInitial(i10);
                        throw null;
                    }
                    i4 = i10 & 7;
                    i5 = 3;
                }
                if (i8 + i5 <= i6) {
                    int i11 = iArr[i8 >> 2] >> ((3 - (i8 & 3)) << 3);
                    i8++;
                    if ((i11 & AppearanceLibrary.APPEARANCE_GENERIC_WATCH) != 128) {
                        _reportInvalidOther(i11);
                        throw null;
                    }
                    int i12 = (i4 << 6) | (i11 & 63);
                    if (i5 > 1) {
                        int i13 = iArr[i8 >> 2] >> ((3 - (i8 & 3)) << 3);
                        i8++;
                        if ((i13 & AppearanceLibrary.APPEARANCE_GENERIC_WATCH) != 128) {
                            _reportInvalidOther(i13);
                            throw null;
                        }
                        int i14 = (i13 & 63) | (i12 << 6);
                        if (i5 > 2) {
                            int i15 = iArr[i8 >> 2] >> ((3 - (i8 & 3)) << 3);
                            i8++;
                            if ((i15 & AppearanceLibrary.APPEARANCE_GENERIC_WATCH) != 128) {
                                _reportInvalidOther(i15 & 255);
                                throw null;
                            }
                            i12 = (i14 << 6) | (i15 & 63);
                        } else {
                            i12 = i14;
                        }
                    }
                    if (i5 > 2) {
                        int i16 = i12 - 65536;
                        if (i9 >= emptyAndGetCurrentSegment.length) {
                            emptyAndGetCurrentSegment = this._textBuffer.expandCurrentSegment();
                        }
                        emptyAndGetCurrentSegment[i9] = (char) ((i16 >> 10) + 55296);
                        i10 = (i16 & 1023) | 56320;
                        i9++;
                    } else {
                        i10 = i12;
                    }
                } else {
                    _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
                    throw null;
                }
            }
            if (i9 >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.expandCurrentSegment();
            }
            emptyAndGetCurrentSegment[i9] = (char) i10;
            i9++;
        }
        String str = new String(emptyAndGetCurrentSegment, 0, i9);
        if (i2 < 4) {
            iArr[i - 1] = i3;
        }
        return this._symbols.addName(str, iArr, i);
    }

    private final String findName(int i, int i2) {
        int _padLastQuad = _padLastQuad(i, i2);
        String findName = this._symbols.findName(_padLastQuad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = _padLastQuad;
        return addName(iArr, 1, i2);
    }

    private int nextByte() {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        return bArr[i] & FlagsParser.UNKNOWN_FLAGS;
    }

    private final String parseName(int i, int i2, int i3) {
        return parseEscapedName(this._quadBuffer, 0, i, i2, i3);
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected void _closeInput() {
        if (this._inputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._inputStream.close();
            }
            this._inputStream = null;
        }
    }

    protected final byte[] _decodeBase64(Base64Variant base64Variant) {
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                _loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & FlagsParser.UNKNOWN_FLAGS;
            if (i2 > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char(i2);
                if (decodeBase64Char < 0) {
                    if (i2 == 34) {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, i2, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr2 = this._inputBuffer;
                int i3 = this._inputPtr;
                this._inputPtr = i3 + 1;
                int i4 = bArr2[i3] & FlagsParser.UNKNOWN_FLAGS;
                int decodeBase64Char2 = base64Variant.decodeBase64Char(i4);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, i4, 1);
                }
                int i5 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i6 = this._inputPtr;
                this._inputPtr = i6 + 1;
                int i7 = bArr3[i6] & FlagsParser.UNKNOWN_FLAGS;
                int decodeBase64Char3 = base64Variant.decodeBase64Char(i7);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (i7 == 34) {
                            _getByteArrayBuilder.append(i5 >> 4);
                            if (!base64Variant.usesPadding()) {
                                return _getByteArrayBuilder.toByteArray();
                            }
                            this._inputPtr--;
                            _handleBase64MissingPadding(base64Variant);
                            throw null;
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant, i7, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            _loadMoreGuaranteed();
                        }
                        byte[] bArr4 = this._inputBuffer;
                        int i8 = this._inputPtr;
                        this._inputPtr = i8 + 1;
                        int i9 = bArr4[i8] & FlagsParser.UNKNOWN_FLAGS;
                        if (!base64Variant.usesPaddingChar(i9) && _decodeBase64Escape(base64Variant, i9, 3) != -2) {
                            throw reportInvalidBase64Char(base64Variant, i9, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        _getByteArrayBuilder.append(i5 >> 4);
                    }
                }
                int i10 = (i5 << 6) | decodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr5 = this._inputBuffer;
                int i11 = this._inputPtr;
                this._inputPtr = i11 + 1;
                int i12 = bArr5[i11] & FlagsParser.UNKNOWN_FLAGS;
                int decodeBase64Char4 = base64Variant.decodeBase64Char(i12);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (i12 == 34) {
                            _getByteArrayBuilder.appendTwoBytes(i10 >> 2);
                            if (!base64Variant.usesPadding()) {
                                return _getByteArrayBuilder.toByteArray();
                            }
                            this._inputPtr--;
                            _handleBase64MissingPadding(base64Variant);
                            throw null;
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant, i12, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        _getByteArrayBuilder.appendTwoBytes(i10 >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes((i10 << 6) | decodeBase64Char4);
            }
        }
    }

    protected int _decodeCharForError(int i) {
        int i2;
        char c2;
        int i3 = i & 255;
        if (i3 <= 127) {
            return i3;
        }
        if ((i3 & 224) == 192) {
            i2 = i3 & 31;
            c2 = 1;
        } else if ((i3 & 240) == 224) {
            i2 = i3 & 15;
            c2 = 2;
        } else {
            if ((i3 & 248) != 240) {
                _reportInvalidInitial(i3 & 255);
                throw null;
            }
            i2 = i3 & 7;
            c2 = 3;
        }
        int nextByte = nextByte();
        if ((nextByte & AppearanceLibrary.APPEARANCE_GENERIC_WATCH) != 128) {
            _reportInvalidOther(nextByte & 255);
            throw null;
        }
        int i4 = (i2 << 6) | (nextByte & 63);
        if (c2 <= 1) {
            return i4;
        }
        int nextByte2 = nextByte();
        if ((nextByte2 & AppearanceLibrary.APPEARANCE_GENERIC_WATCH) != 128) {
            _reportInvalidOther(nextByte2 & 255);
            throw null;
        }
        int i5 = (i4 << 6) | (nextByte2 & 63);
        if (c2 <= 2) {
            return i5;
        }
        int nextByte3 = nextByte();
        if ((nextByte3 & AppearanceLibrary.APPEARANCE_GENERIC_WATCH) == 128) {
            return (i5 << 6) | (nextByte3 & 63);
        }
        _reportInvalidOther(nextByte3 & 255);
        throw null;
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected char _decodeEscaped() {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            _reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING);
            throw null;
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b2 = bArr[i];
        if (b2 == 34 || b2 == 47 || b2 == 92) {
            return (char) b2;
        }
        if (b2 == 98) {
            return '\b';
        }
        if (b2 == 102) {
            return '\f';
        }
        if (b2 == 110) {
            return '\n';
        }
        if (b2 == 114) {
            return '\r';
        }
        if (b2 == 116) {
            return '\t';
        }
        if (b2 != 117) {
            char _decodeCharForError = (char) _decodeCharForError(b2);
            _handleUnrecognizedCharacterEscape(_decodeCharForError);
            return _decodeCharForError;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING);
                throw null;
            }
            byte[] bArr2 = this._inputBuffer;
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            int i5 = bArr2[i4] & FlagsParser.UNKNOWN_FLAGS;
            int charToHex = CharTypes.charToHex(i5);
            if (charToHex < 0) {
                _reportUnexpectedChar(i5, "expected a hex-digit for character escape sequence");
                throw null;
            }
            i2 = (i2 << 4) | charToHex;
        }
        return (char) i2;
    }

    protected String _finishAndReturnString() {
        int i = this._inputPtr;
        if (i >= this._inputEnd) {
            _loadMoreGuaranteed();
            i = this._inputPtr;
        }
        int i2 = 0;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        int min = Math.min(this._inputEnd, emptyAndGetCurrentSegment.length + i);
        byte[] bArr = this._inputBuffer;
        while (true) {
            if (i >= min) {
                break;
            }
            int i3 = bArr[i] & FlagsParser.UNKNOWN_FLAGS;
            if (iArr[i3] == 0) {
                i++;
                emptyAndGetCurrentSegment[i2] = (char) i3;
                i2++;
            } else if (i3 == 34) {
                this._inputPtr = i + 1;
                return this._textBuffer.setCurrentAndReturn(i2);
            }
        }
        this._inputPtr = i;
        _finishString2(emptyAndGetCurrentSegment, i2);
        return this._textBuffer.contentsAsString();
    }

    protected void _finishString() {
        int i = this._inputPtr;
        if (i >= this._inputEnd) {
            _loadMoreGuaranteed();
            i = this._inputPtr;
        }
        int i2 = 0;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        int min = Math.min(this._inputEnd, emptyAndGetCurrentSegment.length + i);
        byte[] bArr = this._inputBuffer;
        while (true) {
            if (i >= min) {
                break;
            }
            int i3 = bArr[i] & FlagsParser.UNKNOWN_FLAGS;
            if (iArr[i3] == 0) {
                i++;
                emptyAndGetCurrentSegment[i2] = (char) i3;
                i2++;
            } else if (i3 == 34) {
                this._inputPtr = i + 1;
                this._textBuffer.setCurrentLength(i2);
                return;
            }
        }
        this._inputPtr = i;
        _finishString2(emptyAndGetCurrentSegment, i2);
    }

    protected final String _getText2(JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        int id = jsonToken.id();
        if (id == 5) {
            return this._parsingContext.getCurrentName();
        }
        if (id != 6 && id != 7 && id != 8) {
            return jsonToken.asString();
        }
        return this._textBuffer.contentsAsString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0045, code lost:
    
        if (r6 != 39) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004f, code lost:
    
        r5 = r1[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0052, code lost:
    
        if (r5 == 1) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0055, code lost:
    
        if (r5 == 2) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0058, code lost:
    
        if (r5 == 3) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005b, code lost:
    
        if (r5 == 4) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006b, code lost:
    
        r5 = _decodeUtf8_4(r6);
        r6 = r4 + 1;
        r0[r4] = (char) (55296 | (r5 >> 10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x007b, code lost:
    
        if (r6 < r0.length) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007d, code lost:
    
        r0 = r9._textBuffer.finishCurrentSegment();
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0084, code lost:
    
        r5 = 56320 | (r5 & 1023);
        r4 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a8, code lost:
    
        if (r4 < r0.length) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00aa, code lost:
    
        r0 = r9._textBuffer.finishCurrentSegment();
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00b1, code lost:
    
        r0[r4] = (char) r5;
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x005f, code lost:
    
        if (r6 >= 32) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0061, code lost:
    
        _throwUnquotedSpace(r6, "string value");
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0066, code lost:
    
        _reportInvalidChar(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x006a, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0092, code lost:
    
        if ((r9._inputEnd - r9._inputPtr) < 2) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0094, code lost:
    
        r5 = _decodeUtf8_3fast(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0099, code lost:
    
        r5 = _decodeUtf8_3(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x009e, code lost:
    
        r5 = _decodeUtf8_2(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00a3, code lost:
    
        r5 = _decodeEscaped();
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0047, code lost:
    
        r9._textBuffer.setCurrentLength(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x004e, code lost:
    
        return com.fasterxml.jackson.core.JsonToken.VALUE_STRING;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected com.fasterxml.jackson.core.JsonToken _handleApos() {
        /*
            r9 = this;
            com.fasterxml.jackson.core.util.TextBuffer r0 = r9._textBuffer
            char[] r0 = r0.emptyAndGetCurrentSegment()
            int[] r1 = com.fasterxml.jackson.core.json.UTF8StreamJsonParser._icUTF8
            byte[] r2 = r9._inputBuffer
            r3 = 0
            r4 = 0
        Lc:
            int r5 = r9._inputPtr
            int r6 = r9._inputEnd
            if (r5 < r6) goto L15
            r9._loadMoreGuaranteed()
        L15:
            int r5 = r0.length
            if (r4 < r5) goto L1f
            com.fasterxml.jackson.core.util.TextBuffer r0 = r9._textBuffer
            char[] r0 = r0.finishCurrentSegment()
            r4 = 0
        L1f:
            int r5 = r9._inputEnd
            int r6 = r9._inputPtr
            int r7 = r0.length
            int r7 = r7 - r4
            int r6 = r6 + r7
            if (r6 >= r5) goto L29
            r5 = r6
        L29:
            int r6 = r9._inputPtr
            if (r6 >= r5) goto Lc
            int r7 = r6 + 1
            r9._inputPtr = r7
            r6 = r2[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            r7 = 39
            if (r6 == r7) goto L45
            r8 = r1[r6]
            if (r8 == 0) goto L3e
            goto L45
        L3e:
            int r7 = r4 + 1
            char r6 = (char) r6
            r0[r4] = r6
            r4 = r7
            goto L29
        L45:
            if (r6 != r7) goto L4f
            com.fasterxml.jackson.core.util.TextBuffer r0 = r9._textBuffer
            r0.setCurrentLength(r4)
            com.fasterxml.jackson.core.JsonToken r0 = com.fasterxml.jackson.core.JsonToken.VALUE_STRING
            return r0
        L4f:
            r5 = r1[r6]
            r7 = 1
            if (r5 == r7) goto La3
            r7 = 2
            if (r5 == r7) goto L9e
            r8 = 3
            if (r5 == r8) goto L8d
            r7 = 4
            if (r5 == r7) goto L6b
            r0 = 32
            if (r6 >= r0) goto L66
            java.lang.String r0 = "string value"
            r9._throwUnquotedSpace(r6, r0)
        L66:
            r9._reportInvalidChar(r6)
            r0 = 0
            throw r0
        L6b:
            int r5 = r9._decodeUtf8_4(r6)
            int r6 = r4 + 1
            r7 = 55296(0xd800, float:7.7486E-41)
            int r8 = r5 >> 10
            r7 = r7 | r8
            char r7 = (char) r7
            r0[r4] = r7
            int r4 = r0.length
            if (r6 < r4) goto L84
            com.fasterxml.jackson.core.util.TextBuffer r0 = r9._textBuffer
            char[] r0 = r0.finishCurrentSegment()
            r6 = 0
        L84:
            r4 = 56320(0xdc00, float:7.8921E-41)
            r5 = r5 & 1023(0x3ff, float:1.434E-42)
            r4 = r4 | r5
            r5 = r4
            r4 = r6
            goto La7
        L8d:
            int r5 = r9._inputEnd
            int r8 = r9._inputPtr
            int r5 = r5 - r8
            if (r5 < r7) goto L99
            int r5 = r9._decodeUtf8_3fast(r6)
            goto La7
        L99:
            int r5 = r9._decodeUtf8_3(r6)
            goto La7
        L9e:
            int r5 = r9._decodeUtf8_2(r6)
            goto La7
        La3:
            char r5 = r9._decodeEscaped()
        La7:
            int r6 = r0.length
            if (r4 < r6) goto Lb1
            com.fasterxml.jackson.core.util.TextBuffer r0 = r9._textBuffer
            char[] r0 = r0.finishCurrentSegment()
            r4 = 0
        Lb1:
            int r6 = r4 + 1
            char r5 = (char) r5
            r0[r4] = r5
            r4 = r6
            goto Lc
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._handleApos():com.fasterxml.jackson.core.JsonToken");
    }

    protected JsonToken _handleInvalidNumberStart(int i, boolean z) {
        String str;
        if (i == 73) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOFInValue(JsonToken.VALUE_NUMBER_FLOAT);
                throw null;
            }
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            i = bArr[i2];
            if (i == 78) {
                str = z ? "-INF" : "+INF";
            } else if (i == 110) {
                str = z ? "-Infinity" : "+Infinity";
            }
            _matchToken(str, 3);
            if ((this._features & FEAT_MASK_NON_NUM_NUMBERS) != 0) {
                return resetAsNaN(str, z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
            }
            _reportError("Non-standard token '%s': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow", str);
            throw null;
        }
        reportUnexpectedNumberChar(i, "expected digit (0-9) to follow minus sign, for valid numeric value");
        throw null;
    }

    protected String _handleOddName(int i) {
        if (i == 39 && (this._features & FEAT_MASK_ALLOW_SINGLE_QUOTES) != 0) {
            return _parseAposName();
        }
        if ((this._features & FEAT_MASK_ALLOW_UNQUOTED_NAMES) != 0) {
            int[] inputCodeUtf8JsNames = CharTypes.getInputCodeUtf8JsNames();
            if (inputCodeUtf8JsNames[i] == 0) {
                int[] iArr = this._quadBuffer;
                int i2 = 0;
                int i3 = i;
                int i4 = 0;
                int i5 = 0;
                while (true) {
                    if (i2 < 4) {
                        i2++;
                        i5 = (i5 << 8) | i3;
                    } else {
                        if (i4 >= iArr.length) {
                            iArr = ParserBase.growArrayBy(iArr, iArr.length);
                            this._quadBuffer = iArr;
                        }
                        iArr[i4] = i5;
                        i4++;
                        i5 = i3;
                        i2 = 1;
                    }
                    if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                        _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
                        throw null;
                    }
                    byte[] bArr = this._inputBuffer;
                    int i6 = this._inputPtr;
                    i3 = bArr[i6] & FlagsParser.UNKNOWN_FLAGS;
                    if (inputCodeUtf8JsNames[i3] != 0) {
                        if (i2 > 0) {
                            if (i4 >= iArr.length) {
                                int[] growArrayBy = ParserBase.growArrayBy(iArr, iArr.length);
                                this._quadBuffer = growArrayBy;
                                iArr = growArrayBy;
                            }
                            iArr[i4] = i5;
                            i4++;
                        }
                        String findName = this._symbols.findName(iArr, i4);
                        return findName == null ? addName(iArr, i4, i2) : findName;
                    }
                    this._inputPtr = i6 + 1;
                }
            } else {
                _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
                throw null;
            }
        } else {
            _reportUnexpectedChar((char) _decodeCharForError(i), "was expecting double-quote to start field name");
            throw null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x001c, code lost:
    
        if (r5 != 44) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0053, code lost:
    
        if ((r4._features & com.fasterxml.jackson.core.json.UTF8StreamJsonParser.FEAT_MASK_ALLOW_MISSING) == 0) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0055, code lost:
    
        r4._inputPtr--;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005c, code lost:
    
        return com.fasterxml.jackson.core.JsonToken.VALUE_NULL;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x004b, code lost:
    
        if (r4._parsingContext.inArray() == false) goto L51;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected com.fasterxml.jackson.core.JsonToken _handleUnexpectedValue(int r5) {
        /*
            Method dump skipped, instructions count: 218
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._handleUnexpectedValue(int):com.fasterxml.jackson.core.JsonToken");
    }

    protected final boolean _loadMore() {
        byte[] bArr;
        int length;
        int i = this._inputEnd;
        this._currInputProcessed += i;
        this._currInputRowStart -= i;
        this._nameStartOffset -= i;
        InputStream inputStream = this._inputStream;
        if (inputStream == null || (length = (bArr = this._inputBuffer).length) == 0) {
            return false;
        }
        int read = inputStream.read(bArr, 0, length);
        if (read > 0) {
            this._inputPtr = 0;
            this._inputEnd = read;
            return true;
        }
        _closeInput();
        if (read == 0) {
            throw new IOException("InputStream.read() returned 0 characters when trying to read " + this._inputBuffer.length + " bytes");
        }
        return false;
    }

    protected void _loadMoreGuaranteed() {
        if (_loadMore()) {
            return;
        }
        _reportInvalidEOF();
        throw null;
    }

    protected final void _matchFalse() {
        int i;
        int i2 = this._inputPtr;
        if (i2 + 4 < this._inputEnd) {
            byte[] bArr = this._inputBuffer;
            int i3 = i2 + 1;
            if (bArr[i2] == 97) {
                int i4 = i3 + 1;
                if (bArr[i3] == 108) {
                    int i5 = i4 + 1;
                    if (bArr[i4] == 115) {
                        int i6 = i5 + 1;
                        if (bArr[i5] == 101 && ((i = bArr[i6] & FlagsParser.UNKNOWN_FLAGS) < 48 || i == 93 || i == 125)) {
                            this._inputPtr = i6;
                            return;
                        }
                    }
                }
            }
        }
        _matchToken2("false", 1);
    }

    protected final void _matchNull() {
        int i;
        int i2 = this._inputPtr;
        if (i2 + 3 < this._inputEnd) {
            byte[] bArr = this._inputBuffer;
            int i3 = i2 + 1;
            if (bArr[i2] == 117) {
                int i4 = i3 + 1;
                if (bArr[i3] == 108) {
                    int i5 = i4 + 1;
                    if (bArr[i4] == 108 && ((i = bArr[i5] & FlagsParser.UNKNOWN_FLAGS) < 48 || i == 93 || i == 125)) {
                        this._inputPtr = i5;
                        return;
                    }
                }
            }
        }
        _matchToken2("null", 1);
    }

    protected final void _matchToken(String str, int i) {
        int length = str.length();
        if (this._inputPtr + length >= this._inputEnd) {
            _matchToken2(str, i);
            return;
        }
        while (this._inputBuffer[this._inputPtr] == str.charAt(i)) {
            this._inputPtr++;
            i++;
            if (i >= length) {
                int i2 = this._inputBuffer[this._inputPtr] & FlagsParser.UNKNOWN_FLAGS;
                if (i2 < 48 || i2 == 93 || i2 == 125) {
                    return;
                }
                _checkMatchEnd(str, i, i2);
                return;
            }
        }
        _reportInvalidToken(str.substring(0, i));
        throw null;
    }

    protected final void _matchTrue() {
        int i;
        int i2 = this._inputPtr;
        if (i2 + 3 < this._inputEnd) {
            byte[] bArr = this._inputBuffer;
            int i3 = i2 + 1;
            if (bArr[i2] == 114) {
                int i4 = i3 + 1;
                if (bArr[i3] == 117) {
                    int i5 = i4 + 1;
                    if (bArr[i4] == 101 && ((i = bArr[i5] & FlagsParser.UNKNOWN_FLAGS) < 48 || i == 93 || i == 125)) {
                        this._inputPtr = i5;
                        return;
                    }
                }
            }
        }
        _matchToken2("true", 1);
    }

    protected String _parseAposName() {
        int i;
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            _reportInvalidEOF(": was expecting closing ''' for field name", JsonToken.FIELD_NAME);
            throw null;
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int i3 = bArr[i2] & FlagsParser.UNKNOWN_FLAGS;
        if (i3 == 39) {
            return "";
        }
        int[] iArr = this._quadBuffer;
        int[] iArr2 = _icLatin1;
        int[] iArr3 = iArr;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i3 != 39) {
            if (iArr2[i3] != 0 && i3 != 34) {
                if (i3 != 92) {
                    _throwUnquotedSpace(i3, "name");
                } else {
                    i3 = _decodeEscaped();
                }
                if (i3 > 127) {
                    if (i4 >= 4) {
                        if (i5 >= iArr3.length) {
                            iArr3 = ParserBase.growArrayBy(iArr3, iArr3.length);
                            this._quadBuffer = iArr3;
                        }
                        iArr3[i5] = i6;
                        i5++;
                        i4 = 0;
                        i6 = 0;
                    }
                    if (i3 < 2048) {
                        i6 = (i6 << 8) | (i3 >> 6) | AppearanceLibrary.APPEARANCE_GENERIC_WATCH;
                        i4++;
                    } else {
                        int i7 = (i6 << 8) | (i3 >> 12) | 224;
                        int i8 = i4 + 1;
                        if (i8 >= 4) {
                            if (i5 >= iArr3.length) {
                                int[] growArrayBy = ParserBase.growArrayBy(iArr3, iArr3.length);
                                this._quadBuffer = growArrayBy;
                                iArr3 = growArrayBy;
                            }
                            iArr3[i5] = i7;
                            i5++;
                            i8 = 0;
                            i7 = 0;
                        }
                        i6 = (i7 << 8) | ((i3 >> 6) & 63) | 128;
                        i4 = i8 + 1;
                    }
                    i3 = (i3 & 63) | 128;
                }
            }
            if (i4 < 4) {
                i4++;
                i6 = i3 | (i6 << 8);
            } else {
                if (i5 >= iArr3.length) {
                    iArr3 = ParserBase.growArrayBy(iArr3, iArr3.length);
                    this._quadBuffer = iArr3;
                }
                iArr3[i5] = i6;
                i6 = i3;
                i5++;
                i4 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
                throw null;
            }
            byte[] bArr2 = this._inputBuffer;
            int i9 = this._inputPtr;
            this._inputPtr = i9 + 1;
            i3 = bArr2[i9] & FlagsParser.UNKNOWN_FLAGS;
        }
        if (i4 > 0) {
            if (i5 >= iArr3.length) {
                int[] growArrayBy2 = ParserBase.growArrayBy(iArr3, iArr3.length);
                this._quadBuffer = growArrayBy2;
                iArr3 = growArrayBy2;
            }
            i = i5 + 1;
            iArr3[i5] = _padLastQuad(i6, i4);
        } else {
            i = i5;
        }
        String findName = this._symbols.findName(iArr3, i);
        return findName == null ? addName(iArr3, i, i4) : findName;
    }

    protected final String _parseName(int i) {
        if (i != 34) {
            return _handleOddName(i);
        }
        int i2 = this._inputPtr;
        if (i2 + 13 > this._inputEnd) {
            return slowParseName();
        }
        byte[] bArr = this._inputBuffer;
        int[] iArr = _icLatin1;
        this._inputPtr = i2 + 1;
        int i3 = bArr[i2] & FlagsParser.UNKNOWN_FLAGS;
        if (iArr[i3] != 0) {
            return i3 == 34 ? "" : parseName(0, i3, 0);
        }
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        int i5 = bArr[i4] & FlagsParser.UNKNOWN_FLAGS;
        if (iArr[i5] != 0) {
            if (i5 == 34) {
                return findName(i3, 1);
            }
            return parseName(i3, i5, 1);
        }
        int i6 = (i3 << 8) | i5;
        int i7 = this._inputPtr;
        this._inputPtr = i7 + 1;
        int i8 = bArr[i7] & FlagsParser.UNKNOWN_FLAGS;
        if (iArr[i8] != 0) {
            if (i8 == 34) {
                return findName(i6, 2);
            }
            return parseName(i6, i8, 2);
        }
        int i9 = (i6 << 8) | i8;
        int i10 = this._inputPtr;
        this._inputPtr = i10 + 1;
        int i11 = bArr[i10] & FlagsParser.UNKNOWN_FLAGS;
        if (iArr[i11] != 0) {
            if (i11 == 34) {
                return findName(i9, 3);
            }
            return parseName(i9, i11, 3);
        }
        int i12 = (i9 << 8) | i11;
        int i13 = this._inputPtr;
        this._inputPtr = i13 + 1;
        int i14 = bArr[i13] & FlagsParser.UNKNOWN_FLAGS;
        if (iArr[i14] == 0) {
            this._quad1 = i12;
            return parseMediumName(i14);
        }
        if (i14 == 34) {
            return findName(i12, 4);
        }
        return parseName(i12, i14, 4);
    }

    protected JsonToken _parseNegNumber() {
        int i;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        emptyAndGetCurrentSegment[0] = '-';
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int i3 = bArr[i2] & FlagsParser.UNKNOWN_FLAGS;
        if (i3 <= 48) {
            if (i3 != 48) {
                return _handleInvalidNumberStart(i3, true);
            }
            i3 = _verifyNoLeadingZeroes();
        } else if (i3 > 57) {
            return _handleInvalidNumberStart(i3, true);
        }
        int i4 = 2;
        emptyAndGetCurrentSegment[1] = (char) i3;
        int min = Math.min(this._inputEnd, (this._inputPtr + emptyAndGetCurrentSegment.length) - 2);
        int i5 = 1;
        while (true) {
            int i6 = this._inputPtr;
            if (i6 >= min) {
                return _parseNumber2(emptyAndGetCurrentSegment, i4, true, i5);
            }
            byte[] bArr2 = this._inputBuffer;
            this._inputPtr = i6 + 1;
            i = bArr2[i6] & FlagsParser.UNKNOWN_FLAGS;
            if (i < 48 || i > 57) {
                break;
            }
            i5++;
            emptyAndGetCurrentSegment[i4] = (char) i;
            i4++;
        }
        if (i != 46 && i != 101 && i != 69) {
            this._inputPtr--;
            this._textBuffer.setCurrentLength(i4);
            if (this._parsingContext.inRoot()) {
                _verifyRootSpace(i);
            }
            return resetInt(true, i5);
        }
        return _parseFloat(emptyAndGetCurrentSegment, i4, i, true, i5);
    }

    protected JsonToken _parsePosNumber(int i) {
        int i2;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        if (i == 48) {
            i = _verifyNoLeadingZeroes();
        }
        emptyAndGetCurrentSegment[0] = (char) i;
        int min = Math.min(this._inputEnd, (this._inputPtr + emptyAndGetCurrentSegment.length) - 1);
        int i3 = 1;
        int i4 = 1;
        while (true) {
            int i5 = this._inputPtr;
            if (i5 >= min) {
                return _parseNumber2(emptyAndGetCurrentSegment, i3, false, i4);
            }
            byte[] bArr = this._inputBuffer;
            this._inputPtr = i5 + 1;
            i2 = bArr[i5] & FlagsParser.UNKNOWN_FLAGS;
            if (i2 < 48 || i2 > 57) {
                break;
            }
            i4++;
            emptyAndGetCurrentSegment[i3] = (char) i2;
            i3++;
        }
        if (i2 != 46 && i2 != 101 && i2 != 69) {
            this._inputPtr--;
            this._textBuffer.setCurrentLength(i3);
            if (this._parsingContext.inRoot()) {
                _verifyRootSpace(i2);
            }
            return resetInt(false, i4);
        }
        return _parseFloat(emptyAndGetCurrentSegment, i3, i2, false, i4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0038, code lost:
    
        if (r10 < 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x012a, code lost:
    
        r16._tokenIncomplete = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x012c, code lost:
    
        if (r8 <= 0) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x012e, code lost:
    
        r7 = r7 + r8;
        r18.write(r19, 0, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0132, code lost:
    
        return r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:?, code lost:
    
        return r7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected int _readBinary(com.fasterxml.jackson.core.Base64Variant r17, java.io.OutputStream r18, byte[] r19) {
        /*
            Method dump skipped, instructions count: 375
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._readBinary(com.fasterxml.jackson.core.Base64Variant, java.io.OutputStream, byte[]):int");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.base.ParserBase
    public void _releaseBuffers() {
        byte[] bArr;
        super._releaseBuffers();
        this._symbols.release();
        if (!this._bufferRecyclable || (bArr = this._inputBuffer) == null) {
            return;
        }
        this._inputBuffer = ParserMinimalBase.NO_BYTES;
        this._ioContext.releaseReadIOBuffer(bArr);
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

    protected void _reportInvalidToken(String str) {
        _reportInvalidToken(str, _validJsonTokenList());
        throw null;
    }

    protected final void _skipCR() {
        if (this._inputPtr < this._inputEnd || _loadMore()) {
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            if (bArr[i] == 10) {
                this._inputPtr = i + 1;
            }
        }
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    protected void _skipString() {
        this._tokenIncomplete = false;
        int[] iArr = _icUTF8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i = this._inputPtr;
            int i2 = this._inputEnd;
            if (i >= i2) {
                _loadMoreGuaranteed();
                i = this._inputPtr;
                i2 = this._inputEnd;
            }
            while (true) {
                if (i < i2) {
                    int i3 = i + 1;
                    int i4 = bArr[i] & FlagsParser.UNKNOWN_FLAGS;
                    if (iArr[i4] != 0) {
                        this._inputPtr = i3;
                        if (i4 == 34) {
                            return;
                        }
                        int i5 = iArr[i4];
                        if (i5 == 1) {
                            _decodeEscaped();
                        } else if (i5 == 2) {
                            _skipUtf8_2();
                        } else if (i5 == 3) {
                            _skipUtf8_3();
                        } else if (i5 == 4) {
                            _skipUtf8_4(i4);
                        } else if (i4 < 32) {
                            _throwUnquotedSpace(i4, "string value");
                        } else {
                            _reportInvalidChar(i4);
                            throw null;
                        }
                    } else {
                        i = i3;
                    }
                } else {
                    this._inputPtr = i;
                    break;
                }
            }
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_STRING && (jsonToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null)) {
            _reportError("Current token (" + this._currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
            throw null;
        }
        if (this._tokenIncomplete) {
            try {
                this._binaryValue = _decodeBase64(base64Variant);
                this._tokenIncomplete = false;
            } catch (IllegalArgumentException e2) {
                throw _constructError("Failed to decode VALUE_STRING as base64 (" + base64Variant + "): " + e2.getMessage());
            }
        } else if (this._binaryValue == null) {
            ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
            _decodeBase64(getText(), _getByteArrayBuilder, base64Variant);
            this._binaryValue = _getByteArrayBuilder.toByteArray();
        }
        return this._binaryValue;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonLocation getCurrentLocation() {
        return new JsonLocation(_getSourceReference(), this._currInputProcessed + this._inputPtr, -1L, this._currInputRow, (this._inputPtr - this._currInputRowStart) + 1);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String getText() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                return _finishAndReturnString();
            }
            return this._textBuffer.contentsAsString();
        }
        return _getText2(jsonToken);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public char[] getTextCharacters() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return null;
        }
        int id = jsonToken.id();
        if (id != 5) {
            if (id != 6) {
                if (id != 7 && id != 8) {
                    return this._currToken.asCharArray();
                }
            } else if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.getTextBuffer();
        }
        if (!this._nameCopied) {
            String currentName = this._parsingContext.getCurrentName();
            int length = currentName.length();
            char[] cArr = this._nameCopyBuffer;
            if (cArr == null) {
                this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(length);
            } else if (cArr.length < length) {
                this._nameCopyBuffer = new char[length];
            }
            currentName.getChars(0, length, this._nameCopyBuffer, 0);
            this._nameCopied = true;
        }
        return this._nameCopyBuffer;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getTextLength() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return 0;
        }
        int id = jsonToken.id();
        if (id != 5) {
            if (id != 6) {
                if (id != 7 && id != 8) {
                    return this._currToken.asCharArray().length;
                }
            } else if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.size();
        }
        return this._parsingContext.getCurrentName().length();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0014, code lost:
    
        if (r0 != 8) goto L18;
     */
    @Override // com.fasterxml.jackson.core.JsonParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int getTextOffset() {
        /*
            r3 = this;
            com.fasterxml.jackson.core.JsonToken r0 = r3._currToken
            r1 = 0
            if (r0 == 0) goto L27
            int r0 = r0.id()
            r2 = 5
            if (r0 == r2) goto L27
            r2 = 6
            if (r0 == r2) goto L17
            r2 = 7
            if (r0 == r2) goto L20
            r2 = 8
            if (r0 == r2) goto L20
            goto L27
        L17:
            boolean r0 = r3._tokenIncomplete
            if (r0 == 0) goto L20
            r3._tokenIncomplete = r1
            r3._finishString()
        L20:
            com.fasterxml.jackson.core.util.TextBuffer r0 = r3._textBuffer
            int r0 = r0.getTextOffset()
            return r0
        L27:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser.getTextOffset():int");
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonLocation getTokenLocation() {
        if (this._currToken == JsonToken.FIELD_NAME) {
            return new JsonLocation(_getSourceReference(), this._currInputProcessed + (this._nameStartOffset - 1), -1L, this._nameStartRow, this._nameStartCol);
        }
        return new JsonLocation(_getSourceReference(), this._tokenInputTotal - 1, -1L, this._tokenInputRow, this._tokenInputCol);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_NUMBER_INT && jsonToken != JsonToken.VALUE_NUMBER_FLOAT) {
            return super.getValueAsInt(0);
        }
        int i = this._numTypesValid;
        if ((i & 1) == 0) {
            if (i == 0) {
                return _parseIntValue();
            }
            if ((i & 1) == 0) {
                convertNumberToInt();
            }
        }
        return this._numberInt;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                return _finishAndReturnString();
            }
            return this._textBuffer.contentsAsString();
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            return getCurrentName();
        }
        return super.getValueAsString(null);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String nextFieldName() {
        JsonToken _parseNegNumber;
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            _nextAfterName();
            return null;
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return null;
        }
        this._binaryValue = null;
        if (_skipWSOrEnd == 93) {
            _closeArrayScope();
            this._currToken = JsonToken.END_ARRAY;
            return null;
        }
        if (_skipWSOrEnd == 125) {
            _closeObjectScope();
            this._currToken = JsonToken.END_OBJECT;
            return null;
        }
        if (this._parsingContext.expectComma()) {
            if (_skipWSOrEnd == 44) {
                _skipWSOrEnd = _skipWS();
                if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0 && (_skipWSOrEnd == 93 || _skipWSOrEnd == 125)) {
                    _closeScope(_skipWSOrEnd);
                    return null;
                }
            } else {
                _reportUnexpectedChar(_skipWSOrEnd, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
                throw null;
            }
        }
        if (!this._parsingContext.inObject()) {
            _updateLocation();
            _nextTokenNotInObject(_skipWSOrEnd);
            return null;
        }
        _updateNameLocation();
        String _parseName = _parseName(_skipWSOrEnd);
        this._parsingContext.setCurrentName(_parseName);
        this._currToken = JsonToken.FIELD_NAME;
        int _skipColon = _skipColon();
        _updateLocation();
        if (_skipColon == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return _parseName;
        }
        if (_skipColon == 45) {
            _parseNegNumber = _parseNegNumber();
        } else if (_skipColon == 91) {
            _parseNegNumber = JsonToken.START_ARRAY;
        } else if (_skipColon == 102) {
            _matchFalse();
            _parseNegNumber = JsonToken.VALUE_FALSE;
        } else if (_skipColon == 110) {
            _matchNull();
            _parseNegNumber = JsonToken.VALUE_NULL;
        } else if (_skipColon == 116) {
            _matchTrue();
            _parseNegNumber = JsonToken.VALUE_TRUE;
        } else if (_skipColon != 123) {
            switch (_skipColon) {
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    _parseNegNumber = _parsePosNumber(_skipColon);
                    break;
                default:
                    _parseNegNumber = _handleUnexpectedValue(_skipColon);
                    break;
            }
        } else {
            _parseNegNumber = JsonToken.START_OBJECT;
        }
        this._nextToken = _parseNegNumber;
        return _parseName;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String nextTextValue() {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_STRING) {
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    return _finishAndReturnString();
                }
                return this._textBuffer.contentsAsString();
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        }
        if (nextToken() == JsonToken.VALUE_STRING) {
            return getText();
        }
        return null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken nextToken() {
        JsonToken _parseNegNumber;
        if (this._currToken == JsonToken.FIELD_NAME) {
            return _nextAfterName();
        }
        this._numTypesValid = 0;
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return null;
        }
        this._binaryValue = null;
        if (_skipWSOrEnd == 93) {
            _closeArrayScope();
            JsonToken jsonToken = JsonToken.END_ARRAY;
            this._currToken = jsonToken;
            return jsonToken;
        }
        if (_skipWSOrEnd == 125) {
            _closeObjectScope();
            JsonToken jsonToken2 = JsonToken.END_OBJECT;
            this._currToken = jsonToken2;
            return jsonToken2;
        }
        if (this._parsingContext.expectComma()) {
            if (_skipWSOrEnd == 44) {
                _skipWSOrEnd = _skipWS();
                if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0 && (_skipWSOrEnd == 93 || _skipWSOrEnd == 125)) {
                    return _closeScope(_skipWSOrEnd);
                }
            } else {
                _reportUnexpectedChar(_skipWSOrEnd, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
                throw null;
            }
        }
        if (!this._parsingContext.inObject()) {
            _updateLocation();
            return _nextTokenNotInObject(_skipWSOrEnd);
        }
        _updateNameLocation();
        this._parsingContext.setCurrentName(_parseName(_skipWSOrEnd));
        this._currToken = JsonToken.FIELD_NAME;
        int _skipColon = _skipColon();
        _updateLocation();
        if (_skipColon == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return this._currToken;
        }
        if (_skipColon == 45) {
            _parseNegNumber = _parseNegNumber();
        } else if (_skipColon == 91) {
            _parseNegNumber = JsonToken.START_ARRAY;
        } else if (_skipColon == 102) {
            _matchFalse();
            _parseNegNumber = JsonToken.VALUE_FALSE;
        } else if (_skipColon == 110) {
            _matchNull();
            _parseNegNumber = JsonToken.VALUE_NULL;
        } else if (_skipColon == 116) {
            _matchTrue();
            _parseNegNumber = JsonToken.VALUE_TRUE;
        } else if (_skipColon != 123) {
            switch (_skipColon) {
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    _parseNegNumber = _parsePosNumber(_skipColon);
                    break;
                default:
                    _parseNegNumber = _handleUnexpectedValue(_skipColon);
                    break;
            }
        } else {
            _parseNegNumber = JsonToken.START_OBJECT;
        }
        this._nextToken = _parseNegNumber;
        return this._currToken;
    }

    protected final String parseEscapedName(int[] iArr, int i, int i2, int i3, int i4) {
        int[] iArr2 = _icLatin1;
        while (true) {
            if (iArr2[i3] != 0) {
                if (i3 == 34) {
                    if (i4 > 0) {
                        if (i >= iArr.length) {
                            iArr = ParserBase.growArrayBy(iArr, iArr.length);
                            this._quadBuffer = iArr;
                        }
                        iArr[i] = _padLastQuad(i2, i4);
                        i++;
                    }
                    String findName = this._symbols.findName(iArr, i);
                    return findName == null ? addName(iArr, i, i4) : findName;
                }
                if (i3 != 92) {
                    _throwUnquotedSpace(i3, "name");
                } else {
                    i3 = _decodeEscaped();
                }
                if (i3 > 127) {
                    if (i4 >= 4) {
                        if (i >= iArr.length) {
                            iArr = ParserBase.growArrayBy(iArr, iArr.length);
                            this._quadBuffer = iArr;
                        }
                        iArr[i] = i2;
                        i++;
                        i2 = 0;
                        i4 = 0;
                    }
                    if (i3 < 2048) {
                        i2 = (i2 << 8) | (i3 >> 6) | AppearanceLibrary.APPEARANCE_GENERIC_WATCH;
                        i4++;
                    } else {
                        int i5 = (i2 << 8) | (i3 >> 12) | 224;
                        int i6 = i4 + 1;
                        if (i6 >= 4) {
                            if (i >= iArr.length) {
                                iArr = ParserBase.growArrayBy(iArr, iArr.length);
                                this._quadBuffer = iArr;
                            }
                            iArr[i] = i5;
                            i++;
                            i5 = 0;
                            i6 = 0;
                        }
                        i2 = (i5 << 8) | ((i3 >> 6) & 63) | 128;
                        i4 = i6 + 1;
                    }
                    i3 = (i3 & 63) | 128;
                }
            }
            if (i4 < 4) {
                i4++;
                i2 = (i2 << 8) | i3;
            } else {
                if (i >= iArr.length) {
                    iArr = ParserBase.growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                iArr[i] = i2;
                i2 = i3;
                i++;
                i4 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
                throw null;
            }
            byte[] bArr = this._inputBuffer;
            int i7 = this._inputPtr;
            this._inputPtr = i7 + 1;
            i3 = bArr[i7] & FlagsParser.UNKNOWN_FLAGS;
        }
    }

    protected final String parseLongName(int i, int i2, int i3) {
        int[] iArr = this._quadBuffer;
        iArr[0] = this._quad1;
        iArr[1] = i2;
        iArr[2] = i3;
        byte[] bArr = this._inputBuffer;
        int[] iArr2 = _icLatin1;
        int i4 = i;
        int i5 = 3;
        while (true) {
            int i6 = this._inputPtr;
            if (i6 + 4 <= this._inputEnd) {
                this._inputPtr = i6 + 1;
                int i7 = bArr[i6] & FlagsParser.UNKNOWN_FLAGS;
                if (iArr2[i7] != 0) {
                    if (i7 == 34) {
                        return findName(this._quadBuffer, i5, i4, 1);
                    }
                    return parseEscapedName(this._quadBuffer, i5, i4, i7, 1);
                }
                int i8 = (i4 << 8) | i7;
                int i9 = this._inputPtr;
                this._inputPtr = i9 + 1;
                int i10 = bArr[i9] & FlagsParser.UNKNOWN_FLAGS;
                if (iArr2[i10] != 0) {
                    if (i10 == 34) {
                        return findName(this._quadBuffer, i5, i8, 2);
                    }
                    return parseEscapedName(this._quadBuffer, i5, i8, i10, 2);
                }
                int i11 = (i8 << 8) | i10;
                int i12 = this._inputPtr;
                this._inputPtr = i12 + 1;
                int i13 = bArr[i12] & FlagsParser.UNKNOWN_FLAGS;
                if (iArr2[i13] != 0) {
                    if (i13 == 34) {
                        return findName(this._quadBuffer, i5, i11, 3);
                    }
                    return parseEscapedName(this._quadBuffer, i5, i11, i13, 3);
                }
                int i14 = (i11 << 8) | i13;
                int i15 = this._inputPtr;
                this._inputPtr = i15 + 1;
                i4 = bArr[i15] & FlagsParser.UNKNOWN_FLAGS;
                if (iArr2[i4] != 0) {
                    if (i4 == 34) {
                        return findName(this._quadBuffer, i5, i14, 4);
                    }
                    return parseEscapedName(this._quadBuffer, i5, i14, i4, 4);
                }
                int[] iArr3 = this._quadBuffer;
                if (i5 >= iArr3.length) {
                    this._quadBuffer = ParserBase.growArrayBy(iArr3, i5);
                }
                this._quadBuffer[i5] = i14;
                i5++;
            } else {
                return parseEscapedName(this._quadBuffer, i5, 0, i4, 0);
            }
        }
    }

    protected final String parseMediumName(int i) {
        byte[] bArr = this._inputBuffer;
        int[] iArr = _icLatin1;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int i3 = bArr[i2] & FlagsParser.UNKNOWN_FLAGS;
        if (iArr[i3] != 0) {
            if (i3 == 34) {
                return findName(this._quad1, i, 1);
            }
            return parseName(this._quad1, i, i3, 1);
        }
        int i4 = (i << 8) | i3;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        int i6 = bArr[i5] & FlagsParser.UNKNOWN_FLAGS;
        if (iArr[i6] != 0) {
            if (i6 == 34) {
                return findName(this._quad1, i4, 2);
            }
            return parseName(this._quad1, i4, i6, 2);
        }
        int i7 = (i4 << 8) | i6;
        int i8 = this._inputPtr;
        this._inputPtr = i8 + 1;
        int i9 = bArr[i8] & FlagsParser.UNKNOWN_FLAGS;
        if (iArr[i9] != 0) {
            if (i9 == 34) {
                return findName(this._quad1, i7, 3);
            }
            return parseName(this._quad1, i7, i9, 3);
        }
        int i10 = (i7 << 8) | i9;
        int i11 = this._inputPtr;
        this._inputPtr = i11 + 1;
        int i12 = bArr[i11] & FlagsParser.UNKNOWN_FLAGS;
        if (iArr[i12] == 0) {
            return parseMediumName2(i12, i10);
        }
        if (i12 == 34) {
            return findName(this._quad1, i10, 4);
        }
        return parseName(this._quad1, i10, i12, 4);
    }

    protected final String parseMediumName2(int i, int i2) {
        byte[] bArr = this._inputBuffer;
        int[] iArr = _icLatin1;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        int i4 = bArr[i3] & FlagsParser.UNKNOWN_FLAGS;
        if (iArr[i4] != 0) {
            if (i4 == 34) {
                return findName(this._quad1, i2, i, 1);
            }
            return parseName(this._quad1, i2, i, i4, 1);
        }
        int i5 = (i << 8) | i4;
        int i6 = this._inputPtr;
        this._inputPtr = i6 + 1;
        int i7 = bArr[i6] & FlagsParser.UNKNOWN_FLAGS;
        if (iArr[i7] != 0) {
            if (i7 == 34) {
                return findName(this._quad1, i2, i5, 2);
            }
            return parseName(this._quad1, i2, i5, i7, 2);
        }
        int i8 = (i5 << 8) | i7;
        int i9 = this._inputPtr;
        this._inputPtr = i9 + 1;
        int i10 = bArr[i9] & FlagsParser.UNKNOWN_FLAGS;
        if (iArr[i10] != 0) {
            if (i10 == 34) {
                return findName(this._quad1, i2, i8, 3);
            }
            return parseName(this._quad1, i2, i8, i10, 3);
        }
        int i11 = (i8 << 8) | i10;
        int i12 = this._inputPtr;
        this._inputPtr = i12 + 1;
        int i13 = bArr[i12] & FlagsParser.UNKNOWN_FLAGS;
        if (iArr[i13] == 0) {
            return parseLongName(i13, i2, i11);
        }
        if (i13 == 34) {
            return findName(this._quad1, i2, i11, 4);
        }
        return parseName(this._quad1, i2, i11, i13, 4);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) {
        if (this._tokenIncomplete && this._currToken == JsonToken.VALUE_STRING) {
            byte[] allocBase64Buffer = this._ioContext.allocBase64Buffer();
            try {
                return _readBinary(base64Variant, outputStream, allocBase64Buffer);
            } finally {
                this._ioContext.releaseBase64Buffer(allocBase64Buffer);
            }
        }
        byte[] binaryValue = getBinaryValue(base64Variant);
        outputStream.write(binaryValue);
        return binaryValue.length;
    }

    protected String slowParseName() {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            _reportInvalidEOF(": was expecting closing '\"' for name", JsonToken.FIELD_NAME);
            throw null;
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = bArr[i] & FlagsParser.UNKNOWN_FLAGS;
        return i2 == 34 ? "" : parseEscapedName(this._quadBuffer, 0, 0, i2, 0);
    }

    private final String parseName(int i, int i2, int i3, int i4) {
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        return parseEscapedName(iArr, 1, i2, i3, i4);
    }

    protected void _reportInvalidOther(int i, int i2) {
        this._inputPtr = i2;
        _reportInvalidOther(i);
        throw null;
    }

    protected void _reportInvalidToken(String str, String str2) {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                break;
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char _decodeCharForError = (char) _decodeCharForError(bArr[i]);
            if (!Character.isJavaIdentifierPart(_decodeCharForError)) {
                break;
            }
            sb.append(_decodeCharForError);
            if (sb.length() >= 256) {
                sb.append("...");
                break;
            }
        }
        _reportError("Unrecognized token '%s': was expecting %s", sb, str2);
        throw null;
    }

    private final String parseName(int i, int i2, int i3, int i4, int i5) {
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        iArr[1] = i2;
        return parseEscapedName(iArr, 2, i3, i4, i5);
    }

    private final String findName(int i, int i2, int i3) {
        int _padLastQuad = _padLastQuad(i2, i3);
        String findName = this._symbols.findName(i, _padLastQuad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        iArr[1] = _padLastQuad;
        return addName(iArr, 2, i3);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt(int i) {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_NUMBER_INT && jsonToken != JsonToken.VALUE_NUMBER_FLOAT) {
            return super.getValueAsInt(i);
        }
        int i2 = this._numTypesValid;
        if ((i2 & 1) == 0) {
            if (i2 == 0) {
                return _parseIntValue();
            }
            if ((i2 & 1) == 0) {
                convertNumberToInt();
            }
        }
        return this._numberInt;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString(String str) {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                return _finishAndReturnString();
            }
            return this._textBuffer.contentsAsString();
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            return getCurrentName();
        }
        return super.getValueAsString(str);
    }

    private final String findName(int i, int i2, int i3, int i4) {
        int _padLastQuad = _padLastQuad(i3, i4);
        String findName = this._symbols.findName(i, i2, _padLastQuad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        iArr[1] = i2;
        iArr[2] = _padLastQuad(_padLastQuad, i4);
        return addName(iArr, 3, i4);
    }

    private final String findName(int[] iArr, int i, int i2, int i3) {
        if (i >= iArr.length) {
            iArr = ParserBase.growArrayBy(iArr, iArr.length);
            this._quadBuffer = iArr;
        }
        int i4 = i + 1;
        iArr[i] = _padLastQuad(i2, i3);
        String findName = this._symbols.findName(iArr, i4);
        return findName == null ? addName(iArr, i4, i3) : findName;
    }
}
