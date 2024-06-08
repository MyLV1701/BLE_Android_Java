package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;

/* loaded from: classes.dex */
public class ReaderBasedJsonParser extends ParserBase {
    protected boolean _bufferRecyclable;
    protected final int _hashSeed;
    protected char[] _inputBuffer;
    protected int _nameStartCol;
    protected long _nameStartOffset;
    protected int _nameStartRow;
    protected ObjectCodec _objectCodec;
    protected Reader _reader;
    protected final CharsToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;
    private static final int FEAT_MASK_TRAILING_COMMA = JsonParser.Feature.ALLOW_TRAILING_COMMA.getMask();
    private static final int FEAT_MASK_LEADING_ZEROS = JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS.getMask();
    private static final int FEAT_MASK_NON_NUM_NUMBERS = JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS.getMask();
    private static final int FEAT_MASK_ALLOW_MISSING = JsonParser.Feature.ALLOW_MISSING_VALUES.getMask();
    private static final int FEAT_MASK_ALLOW_SINGLE_QUOTES = JsonParser.Feature.ALLOW_SINGLE_QUOTES.getMask();
    private static final int FEAT_MASK_ALLOW_UNQUOTED_NAMES = JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES.getMask();
    private static final int FEAT_MASK_ALLOW_JAVA_COMMENTS = JsonParser.Feature.ALLOW_COMMENTS.getMask();
    private static final int FEAT_MASK_ALLOW_YAML_COMMENTS = JsonParser.Feature.ALLOW_YAML_COMMENTS.getMask();
    protected static final int[] _icLatin1 = CharTypes.getInputCodeLatin1();

    public ReaderBasedJsonParser(IOContext iOContext, int i, Reader reader, ObjectCodec objectCodec, CharsToNameCanonicalizer charsToNameCanonicalizer) {
        super(iOContext, i);
        this._reader = reader;
        this._inputBuffer = iOContext.allocTokenBuffer();
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._objectCodec = objectCodec;
        this._symbols = charsToNameCanonicalizer;
        this._hashSeed = charsToNameCanonicalizer.hashSeed();
        this._bufferRecyclable = true;
    }

    private final void _checkMatchEnd(String str, int i, int i2) {
        if (Character.isJavaIdentifierPart((char) i2)) {
            _reportInvalidToken(str.substring(0, i));
            throw null;
        }
    }

    private void _closeScope(int i) {
        if (i == 93) {
            _updateLocation();
            if (this._parsingContext.inArray()) {
                this._parsingContext = this._parsingContext.clearAndGetParent();
                this._currToken = JsonToken.END_ARRAY;
            } else {
                _reportMismatchedEndMarker(i, '}');
                throw null;
            }
        }
        if (i == 125) {
            _updateLocation();
            if (this._parsingContext.inObject()) {
                this._parsingContext = this._parsingContext.clearAndGetParent();
                this._currToken = JsonToken.END_OBJECT;
            } else {
                _reportMismatchedEndMarker(i, ']');
                throw null;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0069 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0061 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String _handleOddName2(int r5, int r6, int[] r7) {
        /*
            r4 = this;
            com.fasterxml.jackson.core.util.TextBuffer r0 = r4._textBuffer
            char[] r1 = r4._inputBuffer
            int r2 = r4._inputPtr
            int r2 = r2 - r5
            r0.resetWithShared(r1, r5, r2)
            com.fasterxml.jackson.core.util.TextBuffer r5 = r4._textBuffer
            char[] r5 = r5.getCurrentSegment()
            com.fasterxml.jackson.core.util.TextBuffer r0 = r4._textBuffer
            int r0 = r0.getCurrentSegmentSize()
            int r1 = r7.length
        L17:
            int r2 = r4._inputPtr
            int r3 = r4._inputEnd
            if (r2 < r3) goto L24
            boolean r2 = r4._loadMore()
            if (r2 != 0) goto L24
            goto L37
        L24:
            char[] r2 = r4._inputBuffer
            int r3 = r4._inputPtr
            char r2 = r2[r3]
            if (r2 >= r1) goto L31
            r3 = r7[r2]
            if (r3 == 0) goto L51
            goto L37
        L31:
            boolean r3 = java.lang.Character.isJavaIdentifierPart(r2)
            if (r3 != 0) goto L51
        L37:
            com.fasterxml.jackson.core.util.TextBuffer r5 = r4._textBuffer
            r5.setCurrentLength(r0)
            com.fasterxml.jackson.core.util.TextBuffer r5 = r4._textBuffer
            char[] r7 = r5.getTextBuffer()
            int r0 = r5.getTextOffset()
            int r5 = r5.size()
            com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer r1 = r4._symbols
            java.lang.String r5 = r1.findSymbol(r7, r0, r5, r6)
            return r5
        L51:
            int r3 = r4._inputPtr
            int r3 = r3 + 1
            r4._inputPtr = r3
            int r6 = r6 * 33
            int r6 = r6 + r2
            int r3 = r0 + 1
            r5[r0] = r2
            int r0 = r5.length
            if (r3 < r0) goto L69
            com.fasterxml.jackson.core.util.TextBuffer r5 = r4._textBuffer
            char[] r5 = r5.finishCurrentSegment()
            r0 = 0
            goto L17
        L69:
            r0 = r3
            goto L17
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser._handleOddName2(int, int, int[]):java.lang.String");
    }

    private final void _matchFalse() {
        int i;
        char c2;
        int i2 = this._inputPtr;
        if (i2 + 4 < this._inputEnd) {
            char[] cArr = this._inputBuffer;
            if (cArr[i2] == 'a') {
                int i3 = i2 + 1;
                if (cArr[i3] == 'l') {
                    int i4 = i3 + 1;
                    if (cArr[i4] == 's') {
                        int i5 = i4 + 1;
                        if (cArr[i5] == 'e' && ((c2 = cArr[(i = i5 + 1)]) < '0' || c2 == ']' || c2 == '}')) {
                            this._inputPtr = i;
                            return;
                        }
                    }
                }
            }
        }
        _matchToken("false", 1);
    }

    private final void _matchNull() {
        int i;
        char c2;
        int i2 = this._inputPtr;
        if (i2 + 3 < this._inputEnd) {
            char[] cArr = this._inputBuffer;
            if (cArr[i2] == 'u') {
                int i3 = i2 + 1;
                if (cArr[i3] == 'l') {
                    int i4 = i3 + 1;
                    if (cArr[i4] == 'l' && ((c2 = cArr[(i = i4 + 1)]) < '0' || c2 == ']' || c2 == '}')) {
                        this._inputPtr = i;
                        return;
                    }
                }
            }
        }
        _matchToken("null", 1);
    }

    private final void _matchToken2(String str, int i) {
        char c2;
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
        if ((this._inputPtr < this._inputEnd || _loadMore()) && (c2 = this._inputBuffer[this._inputPtr]) >= '0' && c2 != ']' && c2 != '}') {
            _checkMatchEnd(str, i, c2);
        }
    }

    private final void _matchTrue() {
        int i;
        char c2;
        int i2 = this._inputPtr;
        if (i2 + 3 < this._inputEnd) {
            char[] cArr = this._inputBuffer;
            if (cArr[i2] == 'r') {
                int i3 = i2 + 1;
                if (cArr[i3] == 'u') {
                    int i4 = i3 + 1;
                    if (cArr[i4] == 'e' && ((c2 = cArr[(i = i4 + 1)]) < '0' || c2 == ']' || c2 == '}')) {
                        this._inputPtr = i;
                        return;
                    }
                }
            }
        }
        _matchToken("true", 1);
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
        if (i != 44) {
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
            if (i != 93) {
                if (i == 102) {
                    _matchToken("false", 1);
                    JsonToken jsonToken3 = JsonToken.VALUE_FALSE;
                    this._currToken = jsonToken3;
                    return jsonToken3;
                }
                if (i == 110) {
                    _matchToken("null", 1);
                    JsonToken jsonToken4 = JsonToken.VALUE_NULL;
                    this._currToken = jsonToken4;
                    return jsonToken4;
                }
                if (i == 116) {
                    _matchToken("true", 1);
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
                            JsonToken _handleOddValue = _handleOddValue(i);
                            this._currToken = _handleOddValue;
                            return _handleOddValue;
                    }
                }
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                JsonToken jsonToken6 = JsonToken.START_OBJECT;
                this._currToken = jsonToken6;
                return jsonToken6;
            }
        }
        if ((this._features & FEAT_MASK_ALLOW_MISSING) != 0) {
            this._inputPtr--;
            JsonToken jsonToken7 = JsonToken.VALUE_NULL;
            this._currToken = jsonToken7;
            return jsonToken7;
        }
        JsonToken _handleOddValue2 = _handleOddValue(i);
        this._currToken = _handleOddValue2;
        return _handleOddValue2;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r8v0 ??, r8v1 ??, r8v20 ??, r8v13 ??, r8v7 ??, r8v5 ??, r8v11 ??, r8v9 ??, r8v3 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x0070 -> B:30:0x0050). Please report as a decompilation issue!!! */
    private final com.fasterxml.jackson.core.JsonToken _parseFloat(
    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r8v0 ??, r8v1 ??, r8v20 ??, r8v13 ??, r8v7 ??, r8v5 ??, r8v11 ??, r8v9 ??, r8v3 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r8v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:237)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:223)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:168)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:401)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        */

    private String _parseName2(int i, int i2, int i3) {
        this._textBuffer.resetWithShared(this._inputBuffer, i, this._inputPtr - i);
        char[] currentSegment = this._textBuffer.getCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
                throw null;
            }
            char[] cArr = this._inputBuffer;
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            char c2 = cArr[i4];
            if (c2 <= '\\') {
                if (c2 == '\\') {
                    c2 = _decodeEscaped();
                } else if (c2 <= i3) {
                    if (c2 == i3) {
                        this._textBuffer.setCurrentLength(currentSegmentSize);
                        TextBuffer textBuffer = this._textBuffer;
                        return this._symbols.findSymbol(textBuffer.getTextBuffer(), textBuffer.getTextOffset(), textBuffer.size(), i2);
                    }
                    if (c2 < ' ') {
                        _throwUnquotedSpace(c2, "name");
                    }
                }
            }
            i2 = (i2 * 33) + c2;
            int i5 = currentSegmentSize + 1;
            currentSegment[currentSegmentSize] = c2;
            if (i5 >= currentSegment.length) {
                currentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            } else {
                currentSegmentSize = i5;
            }
        }
    }

    private final JsonToken _parseNumber2(boolean z, int i) {
        int i2;
        char nextChar;
        boolean z2;
        int i3;
        char nextChar2;
        char nextChar3;
        int i4;
        this._inputPtr = z ? i + 1 : i;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i5 = 0;
        if (z) {
            emptyAndGetCurrentSegment[0] = '-';
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i6 = this._inputPtr;
        if (i6 < this._inputEnd) {
            char[] cArr = this._inputBuffer;
            this._inputPtr = i6 + 1;
            nextChar = cArr[i6];
        } else {
            nextChar = getNextChar("No digit following minus sign", JsonToken.VALUE_NUMBER_INT);
        }
        if (nextChar == '0') {
            nextChar = _verifyNoLeadingZeroes();
        }
        char[] cArr2 = emptyAndGetCurrentSegment;
        int i7 = 0;
        while (nextChar >= '0' && nextChar <= '9') {
            i7++;
            if (i2 >= cArr2.length) {
                cArr2 = this._textBuffer.finishCurrentSegment();
                i2 = 0;
            }
            int i8 = i2 + 1;
            cArr2[i2] = nextChar;
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                i2 = i8;
                nextChar = 0;
                z2 = true;
                break;
            }
            char[] cArr3 = this._inputBuffer;
            int i9 = this._inputPtr;
            this._inputPtr = i9 + 1;
            nextChar = cArr3[i9];
            i2 = i8;
        }
        z2 = false;
        if (i7 == 0) {
            return _handleInvalidNumberStart(nextChar, z);
        }
        if (nextChar == '.') {
            if (i2 >= cArr2.length) {
                cArr2 = this._textBuffer.finishCurrentSegment();
                i2 = 0;
            }
            int i10 = i2 + 1;
            cArr2[i2] = nextChar;
            int i11 = 0;
            while (true) {
                if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                    z2 = true;
                    break;
                }
                char[] cArr4 = this._inputBuffer;
                int i12 = this._inputPtr;
                this._inputPtr = i12 + 1;
                nextChar = cArr4[i12];
                if (nextChar < '0' || nextChar > '9') {
                    break;
                }
                i11++;
                if (i10 >= cArr2.length) {
                    cArr2 = this._textBuffer.finishCurrentSegment();
                    i10 = 0;
                }
                cArr2[i10] = nextChar;
                i10++;
            }
            if (i11 == 0) {
                reportUnexpectedNumberChar(nextChar, "Decimal point not followed by a digit");
                throw null;
            }
            int i13 = i10;
            i3 = i11;
            i2 = i13;
        } else {
            i3 = 0;
        }
        if (nextChar == 'e' || nextChar == 'E') {
            if (i2 >= cArr2.length) {
                cArr2 = this._textBuffer.finishCurrentSegment();
                i2 = 0;
            }
            int i14 = i2 + 1;
            cArr2[i2] = nextChar;
            int i15 = this._inputPtr;
            if (i15 < this._inputEnd) {
                char[] cArr5 = this._inputBuffer;
                this._inputPtr = i15 + 1;
                nextChar2 = cArr5[i15];
            } else {
                nextChar2 = getNextChar("expected a digit for number exponent");
            }
            if (nextChar2 == '-' || nextChar2 == '+') {
                if (i14 >= cArr2.length) {
                    cArr2 = this._textBuffer.finishCurrentSegment();
                    i14 = 0;
                }
                int i16 = i14 + 1;
                cArr2[i14] = nextChar2;
                int i17 = this._inputPtr;
                if (i17 < this._inputEnd) {
                    char[] cArr6 = this._inputBuffer;
                    this._inputPtr = i17 + 1;
                    nextChar3 = cArr6[i17];
                } else {
                    nextChar3 = getNextChar("expected a digit for number exponent");
                }
                nextChar = nextChar3;
                i4 = i16;
            } else {
                nextChar = nextChar2;
                i4 = i14;
            }
            int i18 = 0;
            while (nextChar <= '9' && nextChar >= '0') {
                i18++;
                if (i4 >= cArr2.length) {
                    cArr2 = this._textBuffer.finishCurrentSegment();
                    i4 = 0;
                }
                i2 = i4 + 1;
                cArr2[i4] = nextChar;
                if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                    i5 = i18;
                    z2 = true;
                    break;
                }
                char[] cArr7 = this._inputBuffer;
                int i19 = this._inputPtr;
                this._inputPtr = i19 + 1;
                nextChar = cArr7[i19];
                i4 = i2;
            }
            i2 = i4;
            i5 = i18;
            if (i5 == 0) {
                reportUnexpectedNumberChar(nextChar, "Exponent indicator not followed by a digit");
                throw null;
            }
        }
        if (!z2) {
            this._inputPtr--;
            if (this._parsingContext.inRoot()) {
                _verifyRootSpace(nextChar);
            }
        }
        this._textBuffer.setCurrentLength(i2);
        return reset(z, i7, i3, i5);
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x004f, code lost:
    
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int _skipAfterComma2() {
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
            char[] r0 = r3._inputBuffer
            int r1 = r3._inputPtr
            int r2 = r1 + 1
            r3._inputPtr = r2
            char r0 = r0[r1]
            r1 = 32
            if (r0 <= r1) goto L50
            r1 = 47
            if (r0 != r1) goto L44
            r3._skipComment()
            goto L0
        L44:
            r1 = 35
            if (r0 != r1) goto L4f
            boolean r1 = r3._skipYAMLComment()
            if (r1 == 0) goto L4f
            goto L0
        L4f:
            return r0
        L50:
            if (r0 >= r1) goto L0
            r1 = 10
            if (r0 != r1) goto L61
            int r0 = r3._currInputRow
            int r0 = r0 + 1
            r3._currInputRow = r0
            int r0 = r3._inputPtr
            r3._currInputRowStart = r0
            goto L0
        L61:
            r1 = 13
            if (r0 != r1) goto L69
            r3._skipCR()
            goto L0
        L69:
            r1 = 9
            if (r0 != r1) goto L6e
            goto L0
        L6e:
            r3._throwInvalidSpace(r0)
            r0 = 0
            goto L74
        L73:
            throw r0
        L74:
            goto L73
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser._skipAfterComma2():int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x002a, code lost:
    
        _reportInvalidEOF(" in a comment", null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x002f, code lost:
    
        throw null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void _skipCComment() {
        /*
            r4 = this;
        L0:
            int r0 = r4._inputPtr
            int r1 = r4._inputEnd
            r2 = 0
            if (r0 < r1) goto Ld
            boolean r0 = r4._loadMore()
            if (r0 == 0) goto L2a
        Ld:
            char[] r0 = r4._inputBuffer
            int r1 = r4._inputPtr
            int r3 = r1 + 1
            r4._inputPtr = r3
            char r0 = r0[r1]
            r1 = 42
            if (r0 > r1) goto L0
            if (r0 != r1) goto L3f
            int r0 = r4._inputPtr
            int r1 = r4._inputEnd
            if (r0 < r1) goto L30
            boolean r0 = r4._loadMore()
            if (r0 == 0) goto L2a
            goto L30
        L2a:
            java.lang.String r0 = " in a comment"
            r4._reportInvalidEOF(r0, r2)
            throw r2
        L30:
            char[] r0 = r4._inputBuffer
            int r1 = r4._inputPtr
            char r0 = r0[r1]
            r2 = 47
            if (r0 != r2) goto L0
            int r1 = r1 + 1
            r4._inputPtr = r1
            return
        L3f:
            r1 = 32
            if (r0 >= r1) goto L0
            r1 = 10
            if (r0 != r1) goto L52
            int r0 = r4._currInputRow
            int r0 = r0 + 1
            r4._currInputRow = r0
            int r0 = r4._inputPtr
            r4._currInputRowStart = r0
            goto L0
        L52:
            r1 = 13
            if (r0 != r1) goto L5a
            r4._skipCR()
            goto L0
        L5a:
            r1 = 9
            if (r0 != r1) goto L5f
            goto L0
        L5f:
            r4._throwInvalidSpace(r0)
            goto L64
        L63:
            throw r2
        L64:
            goto L63
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser._skipCComment():void");
    }

    private final int _skipColon() {
        int i = this._inputPtr;
        if (i + 4 >= this._inputEnd) {
            return _skipColon2(false);
        }
        char[] cArr = this._inputBuffer;
        char c2 = cArr[i];
        if (c2 == ':') {
            int i2 = i + 1;
            this._inputPtr = i2;
            char c3 = cArr[i2];
            if (c3 > ' ') {
                if (c3 != '/' && c3 != '#') {
                    this._inputPtr++;
                    return c3;
                }
                return _skipColon2(true);
            }
            if (c3 == ' ' || c3 == '\t') {
                char[] cArr2 = this._inputBuffer;
                int i3 = this._inputPtr + 1;
                this._inputPtr = i3;
                char c4 = cArr2[i3];
                if (c4 > ' ') {
                    if (c4 != '/' && c4 != '#') {
                        this._inputPtr++;
                        return c4;
                    }
                    return _skipColon2(true);
                }
            }
            return _skipColon2(true);
        }
        if (c2 == ' ' || c2 == '\t') {
            char[] cArr3 = this._inputBuffer;
            int i4 = this._inputPtr + 1;
            this._inputPtr = i4;
            c2 = cArr3[i4];
        }
        if (c2 == ':') {
            char[] cArr4 = this._inputBuffer;
            int i5 = this._inputPtr + 1;
            this._inputPtr = i5;
            char c5 = cArr4[i5];
            if (c5 > ' ') {
                if (c5 != '/' && c5 != '#') {
                    this._inputPtr++;
                    return c5;
                }
                return _skipColon2(true);
            }
            if (c5 == ' ' || c5 == '\t') {
                char[] cArr5 = this._inputBuffer;
                int i6 = this._inputPtr + 1;
                this._inputPtr = i6;
                char c6 = cArr5[i6];
                if (c6 > ' ') {
                    if (c6 != '/' && c6 != '#') {
                        this._inputPtr++;
                        return c6;
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
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c2 = cArr[i];
            if (c2 > ' ') {
                if (c2 == '/') {
                    _skipComment();
                } else if (c2 != '#' || !_skipYAMLComment()) {
                    if (z) {
                        return c2;
                    }
                    if (c2 != ':') {
                        _reportUnexpectedChar(c2, "was expecting a colon to separate field name and value");
                        throw null;
                    }
                    z = true;
                }
            } else if (c2 >= ' ') {
                continue;
            } else if (c2 == '\n') {
                this._currInputRow++;
                this._currInputRowStart = this._inputPtr;
            } else if (c2 == '\r') {
                _skipCR();
            } else if (c2 != '\t') {
                _throwInvalidSpace(c2);
                throw null;
            }
        }
    }

    private final int _skipComma(int i) {
        if (i != 44) {
            _reportUnexpectedChar(i, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
            throw null;
        }
        while (true) {
            int i2 = this._inputPtr;
            if (i2 < this._inputEnd) {
                char[] cArr = this._inputBuffer;
                this._inputPtr = i2 + 1;
                char c2 = cArr[i2];
                if (c2 > ' ') {
                    if (c2 != '/' && c2 != '#') {
                        return c2;
                    }
                    this._inputPtr--;
                    return _skipAfterComma2();
                }
                if (c2 < ' ') {
                    if (c2 == '\n') {
                        this._currInputRow++;
                        this._currInputRowStart = this._inputPtr;
                    } else if (c2 == '\r') {
                        _skipCR();
                    } else if (c2 != '\t') {
                        _throwInvalidSpace(c2);
                        throw null;
                    }
                }
            } else {
                return _skipAfterComma2();
            }
        }
    }

    private void _skipComment() {
        if ((this._features & FEAT_MASK_ALLOW_JAVA_COMMENTS) != 0) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(" in a comment", null);
                throw null;
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c2 = cArr[i];
            if (c2 == '/') {
                _skipLine();
                return;
            } else if (c2 == '*') {
                _skipCComment();
                return;
            } else {
                _reportUnexpectedChar(c2, "was expecting either '*' or '/' for a comment");
                throw null;
            }
        }
        _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        throw null;
    }

    private void _skipLine() {
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                return;
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c2 = cArr[i];
            if (c2 < ' ') {
                if (c2 == '\n') {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                    return;
                } else if (c2 == '\r') {
                    _skipCR();
                    return;
                } else if (c2 != '\t') {
                    _throwInvalidSpace(c2);
                    throw null;
                }
            }
        }
    }

    private final int _skipWSOrEnd() {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            return _eofAsNextChar();
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        char c2 = cArr[i];
        if (c2 > ' ') {
            if (c2 != '/' && c2 != '#') {
                return c2;
            }
            this._inputPtr--;
            return _skipWSOrEnd2();
        }
        if (c2 != ' ') {
            if (c2 == '\n') {
                this._currInputRow++;
                this._currInputRowStart = this._inputPtr;
            } else if (c2 == '\r') {
                _skipCR();
            } else if (c2 != '\t') {
                _throwInvalidSpace(c2);
                throw null;
            }
        }
        while (true) {
            int i2 = this._inputPtr;
            if (i2 < this._inputEnd) {
                char[] cArr2 = this._inputBuffer;
                this._inputPtr = i2 + 1;
                char c3 = cArr2[i2];
                if (c3 > ' ') {
                    if (c3 != '/' && c3 != '#') {
                        return c3;
                    }
                    this._inputPtr--;
                    return _skipWSOrEnd2();
                }
                if (c3 != ' ') {
                    if (c3 == '\n') {
                        this._currInputRow++;
                        this._currInputRowStart = this._inputPtr;
                    } else if (c3 == '\r') {
                        _skipCR();
                    } else if (c3 != '\t') {
                        _throwInvalidSpace(c3);
                        throw null;
                    }
                }
            } else {
                return _skipWSOrEnd2();
            }
        }
    }

    private int _skipWSOrEnd2() {
        char c2;
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                return _eofAsNextChar();
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            c2 = cArr[i];
            if (c2 > ' ') {
                if (c2 == '/') {
                    _skipComment();
                } else if (c2 != '#' || !_skipYAMLComment()) {
                    break;
                }
            } else if (c2 == ' ') {
                continue;
            } else if (c2 == '\n') {
                this._currInputRow++;
                this._currInputRowStart = this._inputPtr;
            } else if (c2 == '\r') {
                _skipCR();
            } else if (c2 != '\t') {
                _throwInvalidSpace(c2);
                throw null;
            }
        }
        return c2;
    }

    private boolean _skipYAMLComment() {
        if ((this._features & FEAT_MASK_ALLOW_YAML_COMMENTS) == 0) {
            return false;
        }
        _skipLine();
        return true;
    }

    private final void _updateLocation() {
        int i = this._inputPtr;
        this._tokenInputTotal = this._currInputProcessed + i;
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = i - this._currInputRowStart;
    }

    private final void _updateNameLocation() {
        int i = this._inputPtr;
        this._nameStartOffset = i;
        this._nameStartRow = this._currInputRow;
        this._nameStartCol = i - this._currInputRowStart;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0027, code lost:
    
        if (r0 == '0') goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002d, code lost:
    
        if (r6._inputPtr < r6._inputEnd) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0033, code lost:
    
        if (_loadMore() == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0035, code lost:
    
        r0 = r6._inputBuffer;
        r1 = r6._inputPtr;
        r0 = r0[r1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003b, code lost:
    
        if (r0 < '0') goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003d, code lost:
    
        if (r0 <= '9') goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0040, code lost:
    
        r6._inputPtr = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0044, code lost:
    
        if (r0 == '0') goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0047, code lost:
    
        return '0';
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0048, code lost:
    
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private char _verifyNLZ2() {
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
            char[] r0 = r6._inputBuffer
            int r1 = r6._inputPtr
            char r0 = r0[r1]
            if (r0 < r2) goto L50
            r3 = 57
            if (r0 <= r3) goto L1c
            goto L50
        L1c:
            int r4 = r6._features
            int r5 = com.fasterxml.jackson.core.json.ReaderBasedJsonParser.FEAT_MASK_LEADING_ZEROS
            r4 = r4 & r5
            if (r4 == 0) goto L49
            int r1 = r1 + 1
            r6._inputPtr = r1
            if (r0 != r2) goto L48
        L29:
            int r1 = r6._inputPtr
            int r4 = r6._inputEnd
            if (r1 < r4) goto L35
            boolean r1 = r6._loadMore()
            if (r1 == 0) goto L48
        L35:
            char[] r0 = r6._inputBuffer
            int r1 = r6._inputPtr
            char r0 = r0[r1]
            if (r0 < r2) goto L47
            if (r0 <= r3) goto L40
            goto L47
        L40:
            int r1 = r1 + 1
            r6._inputPtr = r1
            if (r0 == r2) goto L29
            goto L48
        L47:
            return r2
        L48:
            return r0
        L49:
            java.lang.String r0 = "Leading zeroes not allowed"
            r6.reportInvalidNumber(r0)
            r0 = 0
            throw r0
        L50:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser._verifyNLZ2():char");
    }

    private final char _verifyNoLeadingZeroes() {
        char c2;
        int i = this._inputPtr;
        if (i >= this._inputEnd || ((c2 = this._inputBuffer[i]) >= '0' && c2 <= '9')) {
            return _verifyNLZ2();
        }
        return '0';
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

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected void _closeInput() {
        if (this._reader != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._reader.close();
            }
            this._reader = null;
        }
    }

    protected byte[] _decodeBase64(Base64Variant base64Variant) {
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                _loadMoreGuaranteed();
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c2 = cArr[i];
            if (c2 > ' ') {
                int decodeBase64Char = base64Variant.decodeBase64Char(c2);
                if (decodeBase64Char < 0) {
                    if (c2 == '\"') {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, c2, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                char[] cArr2 = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                char c3 = cArr2[i2];
                int decodeBase64Char2 = base64Variant.decodeBase64Char(c3);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, c3, 1);
                }
                int i3 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                char[] cArr3 = this._inputBuffer;
                int i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                char c4 = cArr3[i4];
                int decodeBase64Char3 = base64Variant.decodeBase64Char(c4);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (c4 == '\"') {
                            _getByteArrayBuilder.append(i3 >> 4);
                            if (!base64Variant.usesPadding()) {
                                return _getByteArrayBuilder.toByteArray();
                            }
                            this._inputPtr--;
                            _handleBase64MissingPadding(base64Variant);
                            throw null;
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant, c4, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            _loadMoreGuaranteed();
                        }
                        char[] cArr4 = this._inputBuffer;
                        int i5 = this._inputPtr;
                        this._inputPtr = i5 + 1;
                        char c5 = cArr4[i5];
                        if (!base64Variant.usesPaddingChar(c5) && _decodeBase64Escape(base64Variant, c5, 3) != -2) {
                            throw reportInvalidBase64Char(base64Variant, c5, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        _getByteArrayBuilder.append(i3 >> 4);
                    }
                }
                int i6 = (i3 << 6) | decodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                char[] cArr5 = this._inputBuffer;
                int i7 = this._inputPtr;
                this._inputPtr = i7 + 1;
                char c6 = cArr5[i7];
                int decodeBase64Char4 = base64Variant.decodeBase64Char(c6);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (c6 == '\"') {
                            _getByteArrayBuilder.appendTwoBytes(i6 >> 2);
                            if (!base64Variant.usesPadding()) {
                                return _getByteArrayBuilder.toByteArray();
                            }
                            this._inputPtr--;
                            _handleBase64MissingPadding(base64Variant);
                            throw null;
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant, c6, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        _getByteArrayBuilder.appendTwoBytes(i6 >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes((i6 << 6) | decodeBase64Char4);
            }
        }
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected char _decodeEscaped() {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            _reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING);
            throw null;
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        char c2 = cArr[i];
        if (c2 == '\"' || c2 == '/' || c2 == '\\') {
            return c2;
        }
        if (c2 == 'b') {
            return '\b';
        }
        if (c2 == 'f') {
            return '\f';
        }
        if (c2 == 'n') {
            return '\n';
        }
        if (c2 == 'r') {
            return '\r';
        }
        if (c2 == 't') {
            return '\t';
        }
        if (c2 != 'u') {
            _handleUnrecognizedCharacterEscape(c2);
            return c2;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING);
                throw null;
            }
            char[] cArr2 = this._inputBuffer;
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            char c3 = cArr2[i4];
            int charToHex = CharTypes.charToHex(c3);
            if (charToHex < 0) {
                _reportUnexpectedChar(c3, "expected a hex-digit for character escape sequence");
                throw null;
            }
            i2 = (i2 << 4) | charToHex;
        }
        return (char) i2;
    }

    protected final void _finishString() {
        int i = this._inputPtr;
        int i2 = this._inputEnd;
        if (i < i2) {
            int[] iArr = _icLatin1;
            int length = iArr.length;
            while (true) {
                char[] cArr = this._inputBuffer;
                char c2 = cArr[i];
                if (c2 >= length || iArr[c2] == 0) {
                    i++;
                    if (i >= i2) {
                        break;
                    }
                } else if (c2 == '\"') {
                    TextBuffer textBuffer = this._textBuffer;
                    int i3 = this._inputPtr;
                    textBuffer.resetWithShared(cArr, i3, i - i3);
                    this._inputPtr = i + 1;
                    return;
                }
            }
        }
        TextBuffer textBuffer2 = this._textBuffer;
        char[] cArr2 = this._inputBuffer;
        int i4 = this._inputPtr;
        textBuffer2.resetWithCopy(cArr2, i4, i - i4);
        this._inputPtr = i;
        _finishString2();
    }

    protected void _finishString2() {
        char[] currentSegment = this._textBuffer.getCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        int[] iArr = _icLatin1;
        int length = iArr.length;
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(": was expecting closing quote for a string value", JsonToken.VALUE_STRING);
                throw null;
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c2 = cArr[i];
            if (c2 < length && iArr[c2] != 0) {
                if (c2 == '\"') {
                    this._textBuffer.setCurrentLength(currentSegmentSize);
                    return;
                } else if (c2 == '\\') {
                    c2 = _decodeEscaped();
                } else if (c2 < ' ') {
                    _throwUnquotedSpace(c2, "string value");
                }
            }
            if (currentSegmentSize >= currentSegment.length) {
                currentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            currentSegment[currentSegmentSize] = c2;
            currentSegmentSize++;
        }
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

    protected JsonToken _handleApos() {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(": was expecting closing quote for a string value", JsonToken.VALUE_STRING);
                throw null;
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c2 = cArr[i];
            if (c2 <= '\\') {
                if (c2 == '\\') {
                    c2 = _decodeEscaped();
                } else if (c2 <= '\'') {
                    if (c2 == '\'') {
                        this._textBuffer.setCurrentLength(currentSegmentSize);
                        return JsonToken.VALUE_STRING;
                    }
                    if (c2 < ' ') {
                        _throwUnquotedSpace(c2, "string value");
                    }
                }
            }
            if (currentSegmentSize >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            emptyAndGetCurrentSegment[currentSegmentSize] = c2;
            currentSegmentSize++;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r10v0 ??, r10v1 ??, r10v4 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    protected com.fasterxml.jackson.core.JsonToken _handleInvalidNumberStart(
    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r10v0 ??, r10v1 ??, r10v4 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r10v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:237)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:223)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:168)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:401)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        */

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0035, code lost:
    
        if (r9 < r4) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0037, code lost:
    
        r5 = r8._inputBuffer;
        r6 = r5[r9];
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003b, code lost:
    
        if (r6 >= r2) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003f, code lost:
    
        if (r0[r6] == 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0064, code lost:
    
        r1 = (r1 * 33) + r6;
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0069, code lost:
    
        if (r9 < r4) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0041, code lost:
    
        r0 = r8._inputPtr - 1;
        r8._inputPtr = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004d, code lost:
    
        return r8._symbols.findSymbol(r5, r0, r9 - r0, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0053, code lost:
    
        if (java.lang.Character.isJavaIdentifierPart(r6) != false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0055, code lost:
    
        r0 = r8._inputPtr - 1;
        r8._inputPtr = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0063, code lost:
    
        return r8._symbols.findSymbol(r8._inputBuffer, r0, r9 - r0, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006b, code lost:
    
        r2 = r8._inputPtr - 1;
        r8._inputPtr = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0074, code lost:
    
        return _handleOddName2(r2, r1, r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.lang.String _handleOddName(int r9) {
        /*
            r8 = this;
            r0 = 39
            if (r9 != r0) goto L10
            int r0 = r8._features
            int r1 = com.fasterxml.jackson.core.json.ReaderBasedJsonParser.FEAT_MASK_ALLOW_SINGLE_QUOTES
            r0 = r0 & r1
            if (r0 == 0) goto L10
            java.lang.String r9 = r8._parseAposName()
            return r9
        L10:
            int r0 = r8._features
            int r1 = com.fasterxml.jackson.core.json.ReaderBasedJsonParser.FEAT_MASK_ALLOW_UNQUOTED_NAMES
            r0 = r0 & r1
            r1 = 0
            if (r0 == 0) goto L7b
            int[] r0 = com.fasterxml.jackson.core.io.CharTypes.getInputCodeLatin1JsNames()
            int r2 = r0.length
            r3 = 1
            if (r9 >= r2) goto L28
            r4 = r0[r9]
            if (r4 != 0) goto L26
            r4 = 1
            goto L2d
        L26:
            r4 = 0
            goto L2d
        L28:
            char r4 = (char) r9
            boolean r4 = java.lang.Character.isJavaIdentifierPart(r4)
        L2d:
            if (r4 == 0) goto L75
            int r9 = r8._inputPtr
            int r1 = r8._hashSeed
            int r4 = r8._inputEnd
            if (r9 >= r4) goto L6b
        L37:
            char[] r5 = r8._inputBuffer
            char r6 = r5[r9]
            if (r6 >= r2) goto L4e
            r7 = r0[r6]
            if (r7 == 0) goto L64
            int r0 = r8._inputPtr
            int r0 = r0 - r3
            r8._inputPtr = r9
            com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer r2 = r8._symbols
            int r9 = r9 - r0
            java.lang.String r9 = r2.findSymbol(r5, r0, r9, r1)
            return r9
        L4e:
            char r5 = (char) r6
            boolean r5 = java.lang.Character.isJavaIdentifierPart(r5)
            if (r5 != 0) goto L64
            int r0 = r8._inputPtr
            int r0 = r0 - r3
            r8._inputPtr = r9
            com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer r2 = r8._symbols
            char[] r3 = r8._inputBuffer
            int r9 = r9 - r0
            java.lang.String r9 = r2.findSymbol(r3, r0, r9, r1)
            return r9
        L64:
            int r1 = r1 * 33
            int r1 = r1 + r6
            int r9 = r9 + 1
            if (r9 < r4) goto L37
        L6b:
            int r2 = r8._inputPtr
            int r2 = r2 - r3
            r8._inputPtr = r9
            java.lang.String r9 = r8._handleOddName2(r2, r1, r0)
            return r9
        L75:
            java.lang.String r0 = "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name"
            r8._reportUnexpectedChar(r9, r0)
            throw r1
        L7b:
            java.lang.String r0 = "was expecting double-quote to start field name"
            r8._reportUnexpectedChar(r9, r0)
            goto L82
        L81:
            throw r1
        L82:
            goto L81
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser._handleOddName(int):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0018, code lost:
    
        if (r5 != 44) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004d, code lost:
    
        if ((r4._features & com.fasterxml.jackson.core.json.ReaderBasedJsonParser.FEAT_MASK_ALLOW_MISSING) == 0) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x004f, code lost:
    
        r4._inputPtr--;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0056, code lost:
    
        return com.fasterxml.jackson.core.JsonToken.VALUE_NULL;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0045, code lost:
    
        if (r4._parsingContext.inArray() == false) goto L47;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected com.fasterxml.jackson.core.JsonToken _handleOddValue(int r5) {
        /*
            r4 = this;
            r0 = 39
            r1 = 0
            if (r5 == r0) goto L89
            r0 = 73
            r2 = 1
            if (r5 == r0) goto L70
            r0 = 78
            if (r5 == r0) goto L57
            r0 = 93
            if (r5 == r0) goto L3f
            r0 = 43
            if (r5 == r0) goto L1c
            r0 = 44
            if (r5 == r0) goto L48
            goto L95
        L1c:
            int r5 = r4._inputPtr
            int r0 = r4._inputEnd
            if (r5 < r0) goto L2f
            boolean r5 = r4._loadMore()
            if (r5 == 0) goto L29
            goto L2f
        L29:
            com.fasterxml.jackson.core.JsonToken r5 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_INT
            r4._reportInvalidEOFInValue(r5)
            throw r1
        L2f:
            char[] r5 = r4._inputBuffer
            int r0 = r4._inputPtr
            int r1 = r0 + 1
            r4._inputPtr = r1
            char r5 = r5[r0]
            r0 = 0
            com.fasterxml.jackson.core.JsonToken r5 = r4._handleInvalidNumberStart(r5, r0)
            return r5
        L3f:
            com.fasterxml.jackson.core.json.JsonReadContext r0 = r4._parsingContext
            boolean r0 = r0.inArray()
            if (r0 != 0) goto L48
            goto L95
        L48:
            int r0 = r4._features
            int r3 = com.fasterxml.jackson.core.json.ReaderBasedJsonParser.FEAT_MASK_ALLOW_MISSING
            r0 = r0 & r3
            if (r0 == 0) goto L95
            int r5 = r4._inputPtr
            int r5 = r5 - r2
            r4._inputPtr = r5
            com.fasterxml.jackson.core.JsonToken r5 = com.fasterxml.jackson.core.JsonToken.VALUE_NULL
            return r5
        L57:
            java.lang.String r5 = "NaN"
            r4._matchToken(r5, r2)
            int r0 = r4._features
            int r2 = com.fasterxml.jackson.core.json.ReaderBasedJsonParser.FEAT_MASK_NON_NUM_NUMBERS
            r0 = r0 & r2
            if (r0 == 0) goto L6a
            r0 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            com.fasterxml.jackson.core.JsonToken r5 = r4.resetAsNaN(r5, r0)
            return r5
        L6a:
            java.lang.String r5 = "Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow"
            r4._reportError(r5)
            throw r1
        L70:
            java.lang.String r5 = "Infinity"
            r4._matchToken(r5, r2)
            int r0 = r4._features
            int r2 = com.fasterxml.jackson.core.json.ReaderBasedJsonParser.FEAT_MASK_NON_NUM_NUMBERS
            r0 = r0 & r2
            if (r0 == 0) goto L83
            r0 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            com.fasterxml.jackson.core.JsonToken r5 = r4.resetAsNaN(r5, r0)
            return r5
        L83:
            java.lang.String r5 = "Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow"
            r4._reportError(r5)
            throw r1
        L89:
            int r0 = r4._features
            int r2 = com.fasterxml.jackson.core.json.ReaderBasedJsonParser.FEAT_MASK_ALLOW_SINGLE_QUOTES
            r0 = r0 & r2
            if (r0 == 0) goto L95
            com.fasterxml.jackson.core.JsonToken r5 = r4._handleApos()
            return r5
        L95:
            boolean r0 = java.lang.Character.isJavaIdentifierStart(r5)
            if (r0 == 0) goto Lb5
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = ""
            r0.append(r2)
            char r5 = (char) r5
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            java.lang.String r0 = r4._validJsonTokenList()
            r4._reportInvalidToken(r5, r0)
            throw r1
        Lb5:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "expected a valid value "
            r0.append(r2)
            java.lang.String r2 = r4._validJsonValueList()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r4._reportUnexpectedChar(r5, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser._handleOddValue(int):com.fasterxml.jackson.core.JsonToken");
    }

    protected boolean _loadMore() {
        int i = this._inputEnd;
        long j = i;
        this._currInputProcessed += j;
        this._currInputRowStart -= i;
        this._nameStartOffset -= j;
        Reader reader = this._reader;
        if (reader != null) {
            char[] cArr = this._inputBuffer;
            int read = reader.read(cArr, 0, cArr.length);
            if (read > 0) {
                this._inputPtr = 0;
                this._inputEnd = read;
                return true;
            }
            _closeInput();
            if (read == 0) {
                throw new IOException("Reader returned 0 characters when trying to read " + this._inputEnd);
            }
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
                char c2 = this._inputBuffer[this._inputPtr];
                if (c2 < '0' || c2 == ']' || c2 == '}') {
                    return;
                }
                _checkMatchEnd(str, i, c2);
                return;
            }
        }
        _reportInvalidToken(str.substring(0, i));
        throw null;
    }

    protected String _parseAposName() {
        int i = this._inputPtr;
        int i2 = this._hashSeed;
        int i3 = this._inputEnd;
        if (i < i3) {
            int[] iArr = _icLatin1;
            int length = iArr.length;
            do {
                char[] cArr = this._inputBuffer;
                char c2 = cArr[i];
                if (c2 == '\'') {
                    int i4 = this._inputPtr;
                    this._inputPtr = i + 1;
                    return this._symbols.findSymbol(cArr, i4, i - i4, i2);
                }
                if (c2 < length && iArr[c2] != 0) {
                    break;
                }
                i2 = (i2 * 33) + c2;
                i++;
            } while (i < i3);
        }
        int i5 = this._inputPtr;
        this._inputPtr = i;
        return _parseName2(i5, i2, 39);
    }

    protected final String _parseName() {
        int i = this._inputPtr;
        int i2 = this._hashSeed;
        int[] iArr = _icLatin1;
        while (true) {
            if (i >= this._inputEnd) {
                break;
            }
            char[] cArr = this._inputBuffer;
            char c2 = cArr[i];
            if (c2 >= iArr.length || iArr[c2] == 0) {
                i2 = (i2 * 33) + c2;
                i++;
            } else if (c2 == '\"') {
                int i3 = this._inputPtr;
                this._inputPtr = i + 1;
                return this._symbols.findSymbol(cArr, i3, i - i3, i2);
            }
        }
        int i4 = this._inputPtr;
        this._inputPtr = i;
        return _parseName2(i4, i2, 34);
    }

    protected final JsonToken _parseNegNumber() {
        int i = this._inputPtr;
        int i2 = i - 1;
        int i3 = this._inputEnd;
        if (i >= i3) {
            return _parseNumber2(true, i2);
        }
        int i4 = i + 1;
        char c2 = this._inputBuffer[i];
        if (c2 > '9' || c2 < '0') {
            this._inputPtr = i4;
            return _handleInvalidNumberStart(c2, true);
        }
        if (c2 == '0') {
            return _parseNumber2(true, i2);
        }
        int i5 = 1;
        while (i4 < i3) {
            int i6 = i4 + 1;
            char c3 = this._inputBuffer[i4];
            if (c3 < '0' || c3 > '9') {
                if (c3 != '.' && c3 != 'e' && c3 != 'E') {
                    int i7 = i6 - 1;
                    this._inputPtr = i7;
                    if (this._parsingContext.inRoot()) {
                        _verifyRootSpace(c3);
                    }
                    this._textBuffer.resetWithShared(this._inputBuffer, i2, i7 - i2);
                    return resetInt(true, i5);
                }
                this._inputPtr = i6;
                return _parseFloat(c3, i2, i6, true, i5);
            }
            i5++;
            i4 = i6;
        }
        return _parseNumber2(true, i2);
    }

    protected final JsonToken _parsePosNumber(int i) {
        int i2 = this._inputPtr;
        int i3 = i2 - 1;
        int i4 = this._inputEnd;
        if (i == 48) {
            return _parseNumber2(false, i3);
        }
        int i5 = 1;
        while (i2 < i4) {
            int i6 = i2 + 1;
            char c2 = this._inputBuffer[i2];
            if (c2 < '0' || c2 > '9') {
                if (c2 != '.' && c2 != 'e' && c2 != 'E') {
                    int i7 = i6 - 1;
                    this._inputPtr = i7;
                    if (this._parsingContext.inRoot()) {
                        _verifyRootSpace(c2);
                    }
                    this._textBuffer.resetWithShared(this._inputBuffer, i3, i7 - i3);
                    return resetInt(false, i5);
                }
                this._inputPtr = i6;
                return _parseFloat(c2, i3, i6, false, i5);
            }
            i5++;
            i2 = i6;
        }
        this._inputPtr = i3;
        return _parseNumber2(false, i3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0036, code lost:
    
        if (r10 < 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0120, code lost:
    
        r16._tokenIncomplete = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0122, code lost:
    
        if (r8 <= 0) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0124, code lost:
    
        r7 = r7 + r8;
        r18.write(r19, 0, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0128, code lost:
    
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
            Method dump skipped, instructions count: 365
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser._readBinary(com.fasterxml.jackson.core.Base64Variant, java.io.OutputStream, byte[]):int");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.base.ParserBase
    public void _releaseBuffers() {
        char[] cArr;
        super._releaseBuffers();
        this._symbols.release();
        if (!this._bufferRecyclable || (cArr = this._inputBuffer) == null) {
            return;
        }
        this._inputBuffer = null;
        this._ioContext.releaseTokenBuffer(cArr);
    }

    protected void _reportInvalidToken(String str) {
        _reportInvalidToken(str, _validJsonTokenList());
        throw null;
    }

    protected final void _skipCR() {
        if (this._inputPtr < this._inputEnd || _loadMore()) {
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            if (cArr[i] == '\n') {
                this._inputPtr = i + 1;
            }
        }
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    protected final void _skipString() {
        this._tokenIncomplete = false;
        int i = this._inputPtr;
        int i2 = this._inputEnd;
        char[] cArr = this._inputBuffer;
        while (true) {
            if (i >= i2) {
                this._inputPtr = i;
                if (_loadMore()) {
                    i = this._inputPtr;
                    i2 = this._inputEnd;
                } else {
                    _reportInvalidEOF(": was expecting closing quote for a string value", JsonToken.VALUE_STRING);
                    throw null;
                }
            }
            int i3 = i + 1;
            char c2 = cArr[i];
            if (c2 <= '\\') {
                if (c2 == '\\') {
                    this._inputPtr = i3;
                    _decodeEscaped();
                    i = this._inputPtr;
                    i2 = this._inputEnd;
                } else if (c2 <= '\"') {
                    if (c2 == '\"') {
                        this._inputPtr = i3;
                        return;
                    } else if (c2 < ' ') {
                        this._inputPtr = i3;
                        _throwUnquotedSpace(c2, "string value");
                    }
                }
            }
            i = i3;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) {
        byte[] bArr;
        if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT && (bArr = this._binaryValue) != null) {
            return bArr;
        }
        if (this._currToken == JsonToken.VALUE_STRING) {
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
        _reportError("Current token (" + this._currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        throw null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonLocation getCurrentLocation() {
        return new JsonLocation(_getSourceReference(), -1L, this._inputPtr + this._currInputProcessed, this._currInputRow, (this._inputPtr - this._currInputRowStart) + 1);
    }

    @Deprecated
    protected char getNextChar(String str) {
        return getNextChar(str, null);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public final String getText() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.contentsAsString();
        }
        return _getText2(jsonToken);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public final char[] getTextCharacters() {
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
    public final int getTextLength() {
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
    public final int getTextOffset() {
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser.getTextOffset():int");
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonLocation getTokenLocation() {
        if (this._currToken == JsonToken.FIELD_NAME) {
            return new JsonLocation(_getSourceReference(), -1L, this._currInputProcessed + (this._nameStartOffset - 1), this._nameStartRow, this._nameStartCol);
        }
        return new JsonLocation(_getSourceReference(), -1L, this._tokenInputTotal - 1, this._tokenInputRow, this._tokenInputCol);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public final String getValueAsString() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
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
        if (_skipWSOrEnd != 93 && _skipWSOrEnd != 125) {
            if (this._parsingContext.expectComma()) {
                _skipWSOrEnd = _skipComma(_skipWSOrEnd);
                if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0 && (_skipWSOrEnd == 93 || _skipWSOrEnd == 125)) {
                    _closeScope(_skipWSOrEnd);
                    return null;
                }
            }
            if (!this._parsingContext.inObject()) {
                _updateLocation();
                _nextTokenNotInObject(_skipWSOrEnd);
                return null;
            }
            _updateNameLocation();
            String _parseName = _skipWSOrEnd == 34 ? _parseName() : _handleOddName(_skipWSOrEnd);
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
                        _parseNegNumber = _handleOddValue(_skipColon);
                        break;
                }
            } else {
                _parseNegNumber = JsonToken.START_OBJECT;
            }
            this._nextToken = _parseNegNumber;
            return _parseName;
        }
        _closeScope(_skipWSOrEnd);
        return null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public final String nextTextValue() {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_STRING) {
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
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
    public final JsonToken nextToken() {
        JsonToken jsonToken;
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
        if (_skipWSOrEnd != 93 && _skipWSOrEnd != 125) {
            if (this._parsingContext.expectComma()) {
                _skipWSOrEnd = _skipComma(_skipWSOrEnd);
                if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0 && (_skipWSOrEnd == 93 || _skipWSOrEnd == 125)) {
                    _closeScope(_skipWSOrEnd);
                    return this._currToken;
                }
            }
            boolean inObject = this._parsingContext.inObject();
            if (inObject) {
                _updateNameLocation();
                this._parsingContext.setCurrentName(_skipWSOrEnd == 34 ? _parseName() : _handleOddName(_skipWSOrEnd));
                this._currToken = JsonToken.FIELD_NAME;
                _skipWSOrEnd = _skipColon();
            }
            _updateLocation();
            if (_skipWSOrEnd == 34) {
                this._tokenIncomplete = true;
                jsonToken = JsonToken.VALUE_STRING;
            } else if (_skipWSOrEnd == 45) {
                jsonToken = _parseNegNumber();
            } else if (_skipWSOrEnd == 91) {
                if (!inObject) {
                    this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                }
                jsonToken = JsonToken.START_ARRAY;
            } else if (_skipWSOrEnd == 102) {
                _matchFalse();
                jsonToken = JsonToken.VALUE_FALSE;
            } else if (_skipWSOrEnd == 110) {
                _matchNull();
                jsonToken = JsonToken.VALUE_NULL;
            } else if (_skipWSOrEnd == 116) {
                _matchTrue();
                jsonToken = JsonToken.VALUE_TRUE;
            } else if (_skipWSOrEnd == 123) {
                if (!inObject) {
                    this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                }
                jsonToken = JsonToken.START_OBJECT;
            } else if (_skipWSOrEnd != 125) {
                switch (_skipWSOrEnd) {
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
                        jsonToken = _parsePosNumber(_skipWSOrEnd);
                        break;
                    default:
                        jsonToken = _handleOddValue(_skipWSOrEnd);
                        break;
                }
            } else {
                _reportUnexpectedChar(_skipWSOrEnd, "expected a value");
                throw null;
            }
            if (inObject) {
                this._nextToken = jsonToken;
                return this._currToken;
            }
            this._currToken = jsonToken;
            return jsonToken;
        }
        _closeScope(_skipWSOrEnd);
        return this._currToken;
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

    protected void _reportInvalidToken(String str, String str2) {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                break;
            }
            char c2 = this._inputBuffer[this._inputPtr];
            if (!Character.isJavaIdentifierPart(c2)) {
                break;
            }
            this._inputPtr++;
            sb.append(c2);
            if (sb.length() >= 256) {
                sb.append("...");
                break;
            }
        }
        _reportError("Unrecognized token '%s': was expecting %s", sb, str2);
        throw null;
    }

    protected char getNextChar(String str, JsonToken jsonToken) {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            _reportInvalidEOF(str, jsonToken);
            throw null;
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        return cArr[i];
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public final String getValueAsString(String str) {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.contentsAsString();
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            return getCurrentName();
        }
        return super.getValueAsString(str);
    }
}
