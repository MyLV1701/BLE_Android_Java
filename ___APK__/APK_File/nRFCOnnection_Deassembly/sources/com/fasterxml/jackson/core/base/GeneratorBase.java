package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import java.math.BigDecimal;

/* loaded from: classes.dex */
public abstract class GeneratorBase extends JsonGenerator {
    protected static final int DERIVED_FEATURES_MASK = (JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.getMask() | JsonGenerator.Feature.ESCAPE_NON_ASCII.getMask()) | JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION.getMask();
    protected boolean _cfgNumbersAsStrings;
    protected int _features;
    protected ObjectCodec _objectCodec;
    protected JsonWriteContext _writeContext;

    /* JADX INFO: Access modifiers changed from: protected */
    public GeneratorBase(int i, ObjectCodec objectCodec) {
        this._features = i;
        this._objectCodec = objectCodec;
        this._writeContext = JsonWriteContext.createRootContext(JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(i) ? DupDetector.rootDetector(this) : null);
        this._cfgNumbersAsStrings = JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.enabledIn(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String _asString(BigDecimal bigDecimal) {
        if (JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN.enabledIn(this._features)) {
            int scale = bigDecimal.scale();
            if (scale >= -9999 && scale <= 9999) {
                return bigDecimal.toPlainString();
            }
            _reportError(String.format("Attempt to write plain `java.math.BigDecimal` (see JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN) with illegal scale (%d): needs to be between [-%d, %d]", Integer.valueOf(scale), 9999, 9999));
            throw null;
        }
        return bigDecimal.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _checkStdFeatureChanges(int i, int i2) {
        if ((DERIVED_FEATURES_MASK & i2) == 0) {
            return;
        }
        this._cfgNumbersAsStrings = JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.enabledIn(i);
        if (JsonGenerator.Feature.ESCAPE_NON_ASCII.enabledIn(i2)) {
            if (JsonGenerator.Feature.ESCAPE_NON_ASCII.enabledIn(i)) {
                setHighestNonEscapedChar(127);
            } else {
                setHighestNonEscapedChar(0);
            }
        }
        if (JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(i2)) {
            if (JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(i)) {
                if (this._writeContext.getDupDetector() == null) {
                    JsonWriteContext jsonWriteContext = this._writeContext;
                    jsonWriteContext.withDupDetector(DupDetector.rootDetector(this));
                    this._writeContext = jsonWriteContext;
                    return;
                }
                return;
            }
            JsonWriteContext jsonWriteContext2 = this._writeContext;
            jsonWriteContext2.withDupDetector(null);
            this._writeContext = jsonWriteContext2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int _decodeSurrogate(int i, int i2) {
        if (i2 >= 56320 && i2 <= 57343) {
            return ((i - 55296) << 10) + 65536 + (i2 - 56320);
        }
        _reportError("Incomplete surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(i2));
        throw null;
    }

    protected abstract void _verifyValueWrite(String str);

    @Override // com.fasterxml.jackson.core.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator disable(JsonGenerator.Feature feature) {
        int mask = feature.getMask();
        this._features &= mask ^ (-1);
        if ((mask & DERIVED_FEATURES_MASK) != 0) {
            if (feature == JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS) {
                this._cfgNumbersAsStrings = false;
            } else if (feature == JsonGenerator.Feature.ESCAPE_NON_ASCII) {
                setHighestNonEscapedChar(0);
            } else if (feature == JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION) {
                JsonWriteContext jsonWriteContext = this._writeContext;
                jsonWriteContext.withDupDetector(null);
                this._writeContext = jsonWriteContext;
            }
        }
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public int getFeatureMask() {
        return this._features;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonStreamContext getOutputContext() {
        return this._writeContext;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final boolean isEnabled(JsonGenerator.Feature feature) {
        return (feature.getMask() & this._features) != 0;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator overrideStdFeatures(int i, int i2) {
        int i3 = this._features;
        int i4 = (i & i2) | ((i2 ^ (-1)) & i3);
        int i5 = i3 ^ i4;
        if (i5 != 0) {
            this._features = i4;
            _checkStdFeatureChanges(i4, i5);
        }
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void setCurrentValue(Object obj) {
        JsonWriteContext jsonWriteContext = this._writeContext;
        if (jsonWriteContext != null) {
            jsonWriteContext.setCurrentValue(obj);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    @Deprecated
    public JsonGenerator setFeatureMask(int i) {
        int i2 = this._features ^ i;
        this._features = i;
        if (i2 != 0) {
            _checkStdFeatureChanges(i, i2);
        }
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeObject(Object obj) {
        if (obj == null) {
            writeNull();
            return;
        }
        ObjectCodec objectCodec = this._objectCodec;
        if (objectCodec != null) {
            objectCodec.writeValue(this, obj);
        } else {
            _writeSimpleObject(obj);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(String str) {
        _verifyValueWrite("write raw value");
        writeRaw(str);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(SerializableString serializableString) {
        _verifyValueWrite("write raw value");
        writeRaw(serializableString);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GeneratorBase(int i, ObjectCodec objectCodec, JsonWriteContext jsonWriteContext) {
        this._features = i;
        this._objectCodec = objectCodec;
        this._writeContext = jsonWriteContext;
        this._cfgNumbersAsStrings = JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.enabledIn(i);
    }
}
