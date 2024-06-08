package com.fasterxml.jackson.dataformat.cbor;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import java.io.InputStream;

/* loaded from: classes.dex */
public class CBORParserBootstrapper {
    protected final boolean _bufferRecyclable;
    protected final IOContext _context;
    protected final InputStream _in;
    protected final byte[] _inputBuffer;
    protected int _inputEnd;
    protected int _inputPtr;

    public CBORParserBootstrapper(IOContext iOContext, InputStream inputStream) {
        this._context = iOContext;
        this._in = inputStream;
        this._inputBuffer = iOContext.allocReadIOBuffer();
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._bufferRecyclable = true;
    }

    public CBORParser constructParser(int i, int i2, int i3, ObjectCodec objectCodec, ByteQuadsCanonicalizer byteQuadsCanonicalizer) {
        ByteQuadsCanonicalizer makeChild = byteQuadsCanonicalizer.makeChild(i);
        ensureLoaded(1);
        return new CBORParser(this._context, i2, i3, objectCodec, makeChild, this._in, this._inputBuffer, this._inputPtr, this._inputEnd, this._bufferRecyclable);
    }

    protected boolean ensureLoaded(int i) {
        if (this._in == null) {
            return false;
        }
        int i2 = this._inputEnd - this._inputPtr;
        while (i2 < i) {
            InputStream inputStream = this._in;
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputEnd;
            int read = inputStream.read(bArr, i3, bArr.length - i3);
            if (read < 1) {
                return false;
            }
            this._inputEnd += read;
            i2 += read;
        }
        return true;
    }

    public CBORParserBootstrapper(IOContext iOContext, byte[] bArr, int i, int i2) {
        this._context = iOContext;
        this._in = null;
        this._inputBuffer = bArr;
        this._inputPtr = i;
        this._inputEnd = i + i2;
        this._bufferRecyclable = false;
    }
}
