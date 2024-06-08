package com.fasterxml.jackson.dataformat.cbor;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.InputDecorator;
import com.fasterxml.jackson.dataformat.cbor.CBORGenerator;
import com.fasterxml.jackson.dataformat.cbor.CBORParser;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

/* loaded from: classes.dex */
public class CBORFactory extends JsonFactory {
    protected int _formatGeneratorFeatures;
    protected int _formatParserFeatures;
    static final int DEFAULT_CBOR_PARSER_FEATURE_FLAGS = CBORParser.Feature.collectDefaults();
    static final int DEFAULT_CBOR_GENERATOR_FEATURE_FLAGS = CBORGenerator.Feature.collectDefaults();

    public CBORFactory() {
        this(null);
    }

    private final CBORGenerator _createCBORGenerator(IOContext iOContext, int i, int i2, ObjectCodec objectCodec, OutputStream outputStream) {
        CBORGenerator cBORGenerator = new CBORGenerator(iOContext, i, i2, this._objectCodec, outputStream);
        if (CBORGenerator.Feature.WRITE_TYPE_HEADER.enabledIn(i2)) {
            cBORGenerator.writeTag(55799);
        }
        return cBORGenerator;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.JsonFactory
    public IOContext _createContext(Object obj, boolean z) {
        return super._createContext(obj, z);
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    protected /* bridge */ /* synthetic */ JsonGenerator _createGenerator(Writer writer, IOContext iOContext) {
        _createGenerator(writer, iOContext);
        throw null;
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    protected Writer _createWriter(OutputStream outputStream, JsonEncoding jsonEncoding, IOContext iOContext) {
        _nonByteTarget();
        throw null;
    }

    protected <T> T _nonByteTarget() {
        throw new UnsupportedOperationException("Can not create generator for non-byte-based target");
    }

    public CBORFactory(ObjectCodec objectCodec) {
        super(objectCodec);
        this._formatParserFeatures = DEFAULT_CBOR_PARSER_FEATURE_FLAGS;
        this._formatGeneratorFeatures = DEFAULT_CBOR_GENERATOR_FEATURE_FLAGS;
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    protected CBORGenerator _createGenerator(Writer writer, IOContext iOContext) {
        _nonByteTarget();
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.JsonFactory
    public CBORGenerator _createUTF8Generator(OutputStream outputStream, IOContext iOContext) {
        return _createCBORGenerator(iOContext, this._generatorFeatures, this._formatGeneratorFeatures, this._objectCodec, outputStream);
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public CBORGenerator createGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) {
        IOContext _createContext = _createContext(outputStream, false);
        return _createCBORGenerator(_createContext, this._generatorFeatures, this._formatGeneratorFeatures, this._objectCodec, _decorate(outputStream, _createContext));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.JsonFactory
    public CBORParser _createParser(InputStream inputStream, IOContext iOContext) {
        return new CBORParserBootstrapper(iOContext, inputStream).constructParser(this._factoryFeatures, this._parserFeatures, this._formatParserFeatures, this._objectCodec, this._byteSymbolCanonicalizer);
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public CBORParser createParser(InputStream inputStream) {
        IOContext _createContext = _createContext(inputStream, false);
        return _createParser(_decorate(inputStream, _createContext), _createContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.JsonFactory
    public CBORParser _createParser(byte[] bArr, int i, int i2, IOContext iOContext) {
        return new CBORParserBootstrapper(iOContext, bArr, i, i2).constructParser(this._factoryFeatures, this._parserFeatures, this._formatParserFeatures, this._objectCodec, this._byteSymbolCanonicalizer);
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public CBORParser createParser(byte[] bArr) {
        return createParser(bArr, 0, bArr.length);
    }

    public CBORParser createParser(byte[] bArr, int i, int i2) {
        InputStream decorate;
        IOContext _createContext = _createContext(bArr, true);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator != null && (decorate = inputDecorator.decorate(_createContext, bArr, 0, bArr.length)) != null) {
            return _createParser(decorate, _createContext);
        }
        return _createParser(bArr, i, i2, _createContext);
    }
}
