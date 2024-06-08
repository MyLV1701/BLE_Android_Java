package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsFailProvider;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import java.lang.reflect.Array;
import java.util.Arrays;

/* loaded from: classes.dex */
public abstract class PrimitiveArrayDeserializers<T> extends StdDeserializer<T> implements ContextualDeserializer {
    private transient Object _emptyValue;
    protected final NullValueProvider _nuller;
    protected final Boolean _unwrapSingle;

    @JacksonStdImpl
    /* loaded from: classes.dex */
    static final class BooleanDeser extends PrimitiveArrayDeserializers<boolean[]> {
        public BooleanDeser() {
            super(boolean[].class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public boolean[] _constructEmpty() {
            return new boolean[0];
        }

        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nullValueProvider, Boolean bool) {
            return new BooleanDeser(this, nullValueProvider, bool);
        }

        protected BooleanDeser(BooleanDeser booleanDeser, NullValueProvider nullValueProvider, Boolean bool) {
            super(booleanDeser, nullValueProvider, bool);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public boolean[] _concat(boolean[] zArr, boolean[] zArr2) {
            int length = zArr.length;
            int length2 = zArr2.length;
            boolean[] copyOf = Arrays.copyOf(zArr, length + length2);
            System.arraycopy(zArr2, 0, copyOf, length, length2);
            return copyOf;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public boolean[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            boolean z;
            int i;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.BooleanBuilder booleanBuilder = deserializationContext.getArrayBuilders().getBooleanBuilder();
            boolean[] resetAndStart = booleanBuilder.resetAndStart();
            int i2 = 0;
            while (true) {
                try {
                    JsonToken nextToken = jsonParser.nextToken();
                    if (nextToken != JsonToken.END_ARRAY) {
                        try {
                            if (nextToken == JsonToken.VALUE_TRUE) {
                                z = true;
                            } else {
                                if (nextToken != JsonToken.VALUE_FALSE) {
                                    if (nextToken == JsonToken.VALUE_NULL) {
                                        if (this._nuller != null) {
                                            this._nuller.getNullValue(deserializationContext);
                                        } else {
                                            _verifyNullForPrimitive(deserializationContext);
                                        }
                                    } else {
                                        z = _parseBooleanPrimitive(jsonParser, deserializationContext);
                                    }
                                }
                                z = false;
                            }
                            resetAndStart[i2] = z;
                            i2 = i;
                        } catch (Exception e2) {
                            e = e2;
                            i2 = i;
                            throw JsonMappingException.wrapWithPath(e, resetAndStart, booleanBuilder.bufferedSize() + i2);
                        }
                        if (i2 >= resetAndStart.length) {
                            resetAndStart = booleanBuilder.appendCompletedChunk(resetAndStart, i2);
                            i2 = 0;
                        }
                        i = i2 + 1;
                    } else {
                        return booleanBuilder.completeAndClearBuffer(resetAndStart, i2);
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public boolean[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return new boolean[]{_parseBooleanPrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    static final class ByteDeser extends PrimitiveArrayDeserializers<byte[]> {
        public ByteDeser() {
            super(byte[].class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public byte[] _constructEmpty() {
            return new byte[0];
        }

        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nullValueProvider, Boolean bool) {
            return new ByteDeser(this, nullValueProvider, bool);
        }

        protected ByteDeser(ByteDeser byteDeser, NullValueProvider nullValueProvider, Boolean bool) {
            super(byteDeser, nullValueProvider, bool);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public byte[] _concat(byte[] bArr, byte[] bArr2) {
            int length = bArr.length;
            int length2 = bArr2.length;
            byte[] copyOf = Arrays.copyOf(bArr, length + length2);
            System.arraycopy(bArr2, 0, copyOf, length, length2);
            return copyOf;
        }

        /* JADX WARN: Removed duplicated region for block: B:33:0x008d A[Catch: Exception -> 0x00a5, TRY_LEAVE, TryCatch #2 {Exception -> 0x00a5, blocks: (B:19:0x005d, B:21:0x0065, B:23:0x0069, B:25:0x006e, B:27:0x0072, B:47:0x0076, B:30:0x007c, B:31:0x008a, B:33:0x008d, B:50:0x0081, B:53:0x0086), top: B:18:0x005d }] */
        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public byte[] deserialize(com.fasterxml.jackson.core.JsonParser r7, com.fasterxml.jackson.databind.DeserializationContext r8) {
            /*
                r6 = this;
                com.fasterxml.jackson.core.JsonToken r0 = r7.getCurrentToken()
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_STRING
                r2 = 0
                if (r0 != r1) goto L2e
                com.fasterxml.jackson.core.Base64Variant r1 = r8.getBase64Variant()     // Catch: com.fasterxml.jackson.core.JsonParseException -> L12
                byte[] r7 = r7.getBinaryValue(r1)     // Catch: com.fasterxml.jackson.core.JsonParseException -> L12
                return r7
            L12:
                r1 = move-exception
                java.lang.String r1 = r1.getOriginalMessage()
                java.lang.String r3 = "base64"
                boolean r3 = r1.contains(r3)
                if (r3 == 0) goto L2e
                java.lang.Class<byte[]> r0 = byte[].class
                java.lang.String r7 = r7.getText()
                java.lang.Object[] r2 = new java.lang.Object[r2]
                java.lang.Object r7 = r8.handleWeirdStringValue(r0, r7, r1, r2)
                byte[] r7 = (byte[]) r7
                return r7
            L2e:
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_EMBEDDED_OBJECT
                if (r0 != r1) goto L41
                java.lang.Object r0 = r7.getEmbeddedObject()
                if (r0 != 0) goto L3a
                r7 = 0
                return r7
            L3a:
                boolean r1 = r0 instanceof byte[]
                if (r1 == 0) goto L41
                byte[] r0 = (byte[]) r0
                return r0
            L41:
                boolean r0 = r7.isExpectedStartArrayToken()
                if (r0 != 0) goto L4e
                java.lang.Object r7 = r6.handleNonArray(r7, r8)
                byte[] r7 = (byte[]) r7
                return r7
            L4e:
                com.fasterxml.jackson.databind.util.ArrayBuilders r0 = r8.getArrayBuilders()
                com.fasterxml.jackson.databind.util.ArrayBuilders$ByteBuilder r0 = r0.getByteBuilder()
                java.lang.Object r1 = r0.resetAndStart()
                byte[] r1 = (byte[]) r1
                r3 = 0
            L5d:
                com.fasterxml.jackson.core.JsonToken r4 = r7.nextToken()     // Catch: java.lang.Exception -> La5
                com.fasterxml.jackson.core.JsonToken r5 = com.fasterxml.jackson.core.JsonToken.END_ARRAY     // Catch: java.lang.Exception -> La5
                if (r4 == r5) goto L9e
                com.fasterxml.jackson.core.JsonToken r5 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_INT     // Catch: java.lang.Exception -> La5
                if (r4 == r5) goto L86
                com.fasterxml.jackson.core.JsonToken r5 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_FLOAT     // Catch: java.lang.Exception -> La5
                if (r4 != r5) goto L6e
                goto L86
            L6e:
                com.fasterxml.jackson.core.JsonToken r5 = com.fasterxml.jackson.core.JsonToken.VALUE_NULL     // Catch: java.lang.Exception -> La5
                if (r4 != r5) goto L81
                com.fasterxml.jackson.databind.deser.NullValueProvider r4 = r6._nuller     // Catch: java.lang.Exception -> La5
                if (r4 == 0) goto L7c
                com.fasterxml.jackson.databind.deser.NullValueProvider r4 = r6._nuller     // Catch: java.lang.Exception -> La5
                r4.getNullValue(r8)     // Catch: java.lang.Exception -> La5
                goto L5d
            L7c:
                r6._verifyNullForPrimitive(r8)     // Catch: java.lang.Exception -> La5
                r4 = 0
                goto L8a
            L81:
                byte r4 = r6._parseBytePrimitive(r7, r8)     // Catch: java.lang.Exception -> La5
                goto L8a
            L86:
                byte r4 = r7.getByteValue()     // Catch: java.lang.Exception -> La5
            L8a:
                int r5 = r1.length     // Catch: java.lang.Exception -> La5
                if (r3 < r5) goto L95
                java.lang.Object r5 = r0.appendCompletedChunk(r1, r3)     // Catch: java.lang.Exception -> La5
                byte[] r5 = (byte[]) r5     // Catch: java.lang.Exception -> La5
                r1 = r5
                r3 = 0
            L95:
                int r5 = r3 + 1
                r1[r3] = r4     // Catch: java.lang.Exception -> L9b
                r3 = r5
                goto L5d
            L9b:
                r7 = move-exception
                r3 = r5
                goto La6
            L9e:
                java.lang.Object r7 = r0.completeAndClearBuffer(r1, r3)
                byte[] r7 = (byte[]) r7
                return r7
            La5:
                r7 = move-exception
            La6:
                int r8 = r0.bufferedSize()
                int r8 = r8 + r3
                com.fasterxml.jackson.databind.JsonMappingException r7 = com.fasterxml.jackson.databind.JsonMappingException.wrapWithPath(r7, r1, r8)
                goto Lb1
            Lb0:
                throw r7
            Lb1:
                goto Lb0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers.ByteDeser.deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext):byte[]");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public byte[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) {
            byte byteValue;
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken != JsonToken.VALUE_NUMBER_INT && currentToken != JsonToken.VALUE_NUMBER_FLOAT) {
                if (currentToken == JsonToken.VALUE_NULL) {
                    NullValueProvider nullValueProvider = this._nuller;
                    if (nullValueProvider != null) {
                        nullValueProvider.getNullValue(deserializationContext);
                        return (byte[]) getEmptyValue(deserializationContext);
                    }
                    _verifyNullForPrimitive(deserializationContext);
                    return null;
                }
                byteValue = ((Number) deserializationContext.handleUnexpectedToken(this._valueClass.getComponentType(), jsonParser)).byteValue();
            } else {
                byteValue = jsonParser.getByteValue();
            }
            return new byte[]{byteValue};
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    static final class CharDeser extends PrimitiveArrayDeserializers<char[]> {
        public CharDeser() {
            super(char[].class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public char[] _constructEmpty() {
            return new char[0];
        }

        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nullValueProvider, Boolean bool) {
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public char[] _concat(char[] cArr, char[] cArr2) {
            int length = cArr.length;
            int length2 = cArr2.length;
            char[] copyOf = Arrays.copyOf(cArr, length + length2);
            System.arraycopy(cArr2, 0, copyOf, length, length2);
            return copyOf;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public char[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            String text;
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                char[] textCharacters = jsonParser.getTextCharacters();
                int textOffset = jsonParser.getTextOffset();
                int textLength = jsonParser.getTextLength();
                char[] cArr = new char[textLength];
                System.arraycopy(textCharacters, textOffset, cArr, 0, textLength);
                return cArr;
            }
            if (jsonParser.isExpectedStartArrayToken()) {
                StringBuilder sb = new StringBuilder(64);
                while (true) {
                    JsonToken nextToken = jsonParser.nextToken();
                    if (nextToken != JsonToken.END_ARRAY) {
                        if (nextToken == JsonToken.VALUE_STRING) {
                            text = jsonParser.getText();
                        } else if (nextToken == JsonToken.VALUE_NULL) {
                            NullValueProvider nullValueProvider = this._nuller;
                            if (nullValueProvider != null) {
                                nullValueProvider.getNullValue(deserializationContext);
                            } else {
                                _verifyNullForPrimitive(deserializationContext);
                                text = "\u0000";
                            }
                        } else {
                            text = ((CharSequence) deserializationContext.handleUnexpectedToken(Character.TYPE, jsonParser)).toString();
                        }
                        if (text.length() == 1) {
                            sb.append(text.charAt(0));
                        } else {
                            deserializationContext.reportInputMismatch(this, "Cannot convert a JSON String of length %d into a char element of char array", Integer.valueOf(text.length()));
                            throw null;
                        }
                    } else {
                        return sb.toString().toCharArray();
                    }
                }
            } else {
                if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                    Object embeddedObject = jsonParser.getEmbeddedObject();
                    if (embeddedObject == null) {
                        return null;
                    }
                    if (embeddedObject instanceof char[]) {
                        return (char[]) embeddedObject;
                    }
                    if (embeddedObject instanceof String) {
                        return ((String) embeddedObject).toCharArray();
                    }
                    if (embeddedObject instanceof byte[]) {
                        return Base64Variants.getDefaultVariant().encode((byte[]) embeddedObject, false).toCharArray();
                    }
                }
                return (char[]) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public char[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return (char[]) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    static final class DoubleDeser extends PrimitiveArrayDeserializers<double[]> {
        public DoubleDeser() {
            super(double[].class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public double[] _constructEmpty() {
            return new double[0];
        }

        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nullValueProvider, Boolean bool) {
            return new DoubleDeser(this, nullValueProvider, bool);
        }

        protected DoubleDeser(DoubleDeser doubleDeser, NullValueProvider nullValueProvider, Boolean bool) {
            super(doubleDeser, nullValueProvider, bool);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public double[] _concat(double[] dArr, double[] dArr2) {
            int length = dArr.length;
            int length2 = dArr2.length;
            double[] copyOf = Arrays.copyOf(dArr, length + length2);
            System.arraycopy(dArr2, 0, copyOf, length, length2);
            return copyOf;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public double[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.DoubleBuilder doubleBuilder = deserializationContext.getArrayBuilders().getDoubleBuilder();
            double[] dArr = (double[]) doubleBuilder.resetAndStart();
            int i = 0;
            while (true) {
                try {
                    JsonToken nextToken = jsonParser.nextToken();
                    if (nextToken != JsonToken.END_ARRAY) {
                        if (nextToken == JsonToken.VALUE_NULL && this._nuller != null) {
                            this._nuller.getNullValue(deserializationContext);
                        } else {
                            double _parseDoublePrimitive = _parseDoublePrimitive(jsonParser, deserializationContext);
                            if (i >= dArr.length) {
                                dArr = (double[]) doubleBuilder.appendCompletedChunk(dArr, i);
                                i = 0;
                            }
                            int i2 = i + 1;
                            try {
                                dArr[i] = _parseDoublePrimitive;
                                i = i2;
                            } catch (Exception e2) {
                                e = e2;
                                i = i2;
                                throw JsonMappingException.wrapWithPath(e, dArr, doubleBuilder.bufferedSize() + i);
                            }
                        }
                    } else {
                        return (double[]) doubleBuilder.completeAndClearBuffer(dArr, i);
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public double[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return new double[]{_parseDoublePrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    static final class FloatDeser extends PrimitiveArrayDeserializers<float[]> {
        public FloatDeser() {
            super(float[].class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public float[] _constructEmpty() {
            return new float[0];
        }

        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nullValueProvider, Boolean bool) {
            return new FloatDeser(this, nullValueProvider, bool);
        }

        protected FloatDeser(FloatDeser floatDeser, NullValueProvider nullValueProvider, Boolean bool) {
            super(floatDeser, nullValueProvider, bool);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public float[] _concat(float[] fArr, float[] fArr2) {
            int length = fArr.length;
            int length2 = fArr2.length;
            float[] copyOf = Arrays.copyOf(fArr, length + length2);
            System.arraycopy(fArr2, 0, copyOf, length, length2);
            return copyOf;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public float[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.FloatBuilder floatBuilder = deserializationContext.getArrayBuilders().getFloatBuilder();
            float[] fArr = (float[]) floatBuilder.resetAndStart();
            int i = 0;
            while (true) {
                try {
                    JsonToken nextToken = jsonParser.nextToken();
                    if (nextToken != JsonToken.END_ARRAY) {
                        if (nextToken == JsonToken.VALUE_NULL && this._nuller != null) {
                            this._nuller.getNullValue(deserializationContext);
                        } else {
                            float _parseFloatPrimitive = _parseFloatPrimitive(jsonParser, deserializationContext);
                            if (i >= fArr.length) {
                                fArr = (float[]) floatBuilder.appendCompletedChunk(fArr, i);
                                i = 0;
                            }
                            int i2 = i + 1;
                            try {
                                fArr[i] = _parseFloatPrimitive;
                                i = i2;
                            } catch (Exception e2) {
                                e = e2;
                                i = i2;
                                throw JsonMappingException.wrapWithPath(e, fArr, floatBuilder.bufferedSize() + i);
                            }
                        }
                    } else {
                        return (float[]) floatBuilder.completeAndClearBuffer(fArr, i);
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public float[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return new float[]{_parseFloatPrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    static final class IntDeser extends PrimitiveArrayDeserializers<int[]> {
        public static final IntDeser instance = new IntDeser();

        public IntDeser() {
            super(int[].class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public int[] _constructEmpty() {
            return new int[0];
        }

        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nullValueProvider, Boolean bool) {
            return new IntDeser(this, nullValueProvider, bool);
        }

        protected IntDeser(IntDeser intDeser, NullValueProvider nullValueProvider, Boolean bool) {
            super(intDeser, nullValueProvider, bool);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public int[] _concat(int[] iArr, int[] iArr2) {
            int length = iArr.length;
            int length2 = iArr2.length;
            int[] copyOf = Arrays.copyOf(iArr, length + length2);
            System.arraycopy(iArr2, 0, copyOf, length, length2);
            return copyOf;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public int[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int intValue;
            int i;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.IntBuilder intBuilder = deserializationContext.getArrayBuilders().getIntBuilder();
            int[] iArr = (int[]) intBuilder.resetAndStart();
            int i2 = 0;
            while (true) {
                try {
                    JsonToken nextToken = jsonParser.nextToken();
                    if (nextToken != JsonToken.END_ARRAY) {
                        try {
                            if (nextToken == JsonToken.VALUE_NUMBER_INT) {
                                intValue = jsonParser.getIntValue();
                            } else if (nextToken == JsonToken.VALUE_NULL) {
                                if (this._nuller != null) {
                                    this._nuller.getNullValue(deserializationContext);
                                } else {
                                    _verifyNullForPrimitive(deserializationContext);
                                    intValue = 0;
                                }
                            } else {
                                intValue = _parseIntPrimitive(jsonParser, deserializationContext);
                            }
                            iArr[i2] = intValue;
                            i2 = i;
                        } catch (Exception e2) {
                            e = e2;
                            i2 = i;
                            throw JsonMappingException.wrapWithPath(e, iArr, intBuilder.bufferedSize() + i2);
                        }
                        if (i2 >= iArr.length) {
                            iArr = (int[]) intBuilder.appendCompletedChunk(iArr, i2);
                            i2 = 0;
                        }
                        i = i2 + 1;
                    } else {
                        return (int[]) intBuilder.completeAndClearBuffer(iArr, i2);
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public int[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return new int[]{_parseIntPrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    static final class LongDeser extends PrimitiveArrayDeserializers<long[]> {
        public static final LongDeser instance = new LongDeser();

        public LongDeser() {
            super(long[].class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public long[] _constructEmpty() {
            return new long[0];
        }

        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nullValueProvider, Boolean bool) {
            return new LongDeser(this, nullValueProvider, bool);
        }

        protected LongDeser(LongDeser longDeser, NullValueProvider nullValueProvider, Boolean bool) {
            super(longDeser, nullValueProvider, bool);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public long[] _concat(long[] jArr, long[] jArr2) {
            int length = jArr.length;
            int length2 = jArr2.length;
            long[] copyOf = Arrays.copyOf(jArr, length + length2);
            System.arraycopy(jArr2, 0, copyOf, length, length2);
            return copyOf;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public long[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            long longValue;
            int i;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.LongBuilder longBuilder = deserializationContext.getArrayBuilders().getLongBuilder();
            long[] jArr = (long[]) longBuilder.resetAndStart();
            int i2 = 0;
            while (true) {
                try {
                    JsonToken nextToken = jsonParser.nextToken();
                    if (nextToken != JsonToken.END_ARRAY) {
                        try {
                            if (nextToken == JsonToken.VALUE_NUMBER_INT) {
                                longValue = jsonParser.getLongValue();
                            } else if (nextToken == JsonToken.VALUE_NULL) {
                                if (this._nuller != null) {
                                    this._nuller.getNullValue(deserializationContext);
                                } else {
                                    _verifyNullForPrimitive(deserializationContext);
                                    longValue = 0;
                                }
                            } else {
                                longValue = _parseLongPrimitive(jsonParser, deserializationContext);
                            }
                            jArr[i2] = longValue;
                            i2 = i;
                        } catch (Exception e2) {
                            e = e2;
                            i2 = i;
                            throw JsonMappingException.wrapWithPath(e, jArr, longBuilder.bufferedSize() + i2);
                        }
                        if (i2 >= jArr.length) {
                            jArr = (long[]) longBuilder.appendCompletedChunk(jArr, i2);
                            i2 = 0;
                        }
                        i = i2 + 1;
                    } else {
                        return (long[]) longBuilder.completeAndClearBuffer(jArr, i2);
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public long[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return new long[]{_parseLongPrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    static final class ShortDeser extends PrimitiveArrayDeserializers<short[]> {
        public ShortDeser() {
            super(short[].class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public short[] _constructEmpty() {
            return new short[0];
        }

        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nullValueProvider, Boolean bool) {
            return new ShortDeser(this, nullValueProvider, bool);
        }

        protected ShortDeser(ShortDeser shortDeser, NullValueProvider nullValueProvider, Boolean bool) {
            super(shortDeser, nullValueProvider, bool);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public short[] _concat(short[] sArr, short[] sArr2) {
            int length = sArr.length;
            int length2 = sArr2.length;
            short[] copyOf = Arrays.copyOf(sArr, length + length2);
            System.arraycopy(sArr2, 0, copyOf, length, length2);
            return copyOf;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public short[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            short _parseShortPrimitive;
            int i;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.ShortBuilder shortBuilder = deserializationContext.getArrayBuilders().getShortBuilder();
            short[] resetAndStart = shortBuilder.resetAndStart();
            int i2 = 0;
            while (true) {
                try {
                    JsonToken nextToken = jsonParser.nextToken();
                    if (nextToken != JsonToken.END_ARRAY) {
                        try {
                            if (nextToken == JsonToken.VALUE_NULL) {
                                if (this._nuller != null) {
                                    this._nuller.getNullValue(deserializationContext);
                                } else {
                                    _verifyNullForPrimitive(deserializationContext);
                                    _parseShortPrimitive = 0;
                                }
                            } else {
                                _parseShortPrimitive = _parseShortPrimitive(jsonParser, deserializationContext);
                            }
                            resetAndStart[i2] = _parseShortPrimitive;
                            i2 = i;
                        } catch (Exception e2) {
                            e = e2;
                            i2 = i;
                            throw JsonMappingException.wrapWithPath(e, resetAndStart, shortBuilder.bufferedSize() + i2);
                        }
                        if (i2 >= resetAndStart.length) {
                            resetAndStart = shortBuilder.appendCompletedChunk(resetAndStart, i2);
                            i2 = 0;
                        }
                        i = i2 + 1;
                    } else {
                        return shortBuilder.completeAndClearBuffer(resetAndStart, i2);
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public short[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return new short[]{_parseShortPrimitive(jsonParser, deserializationContext)};
        }
    }

    protected PrimitiveArrayDeserializers(Class<T> cls) {
        super((Class<?>) cls);
        this._unwrapSingle = null;
        this._nuller = null;
    }

    public static JsonDeserializer<?> forType(Class<?> cls) {
        if (cls == Integer.TYPE) {
            return IntDeser.instance;
        }
        if (cls == Long.TYPE) {
            return LongDeser.instance;
        }
        if (cls == Byte.TYPE) {
            return new ByteDeser();
        }
        if (cls == Short.TYPE) {
            return new ShortDeser();
        }
        if (cls == Float.TYPE) {
            return new FloatDeser();
        }
        if (cls == Double.TYPE) {
            return new DoubleDeser();
        }
        if (cls == Boolean.TYPE) {
            return new BooleanDeser();
        }
        if (cls == Character.TYPE) {
            return new CharDeser();
        }
        throw new IllegalStateException();
    }

    protected abstract T _concat(T t, T t2);

    protected abstract T _constructEmpty();

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) {
        NullValueProvider nullValueProvider;
        Boolean findFormatFeature = findFormatFeature(deserializationContext, beanProperty, this._valueClass, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        Nulls findContentNullStyle = findContentNullStyle(deserializationContext, beanProperty);
        if (findContentNullStyle == Nulls.SKIP) {
            nullValueProvider = NullsConstantProvider.skipper();
        } else if (findContentNullStyle != Nulls.FAIL) {
            nullValueProvider = null;
        } else if (beanProperty == null) {
            nullValueProvider = NullsFailProvider.constructForRootValue(deserializationContext.constructType(this._valueClass));
        } else {
            nullValueProvider = NullsFailProvider.constructForProperty(beanProperty);
        }
        return (findFormatFeature == this._unwrapSingle && nullValueProvider == this._nuller) ? this : withResolved(nullValueProvider, findFormatFeature);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, T t) {
        T deserialize = deserialize(jsonParser, deserializationContext);
        return (t == null || Array.getLength(t) == 0) ? deserialize : _concat(t, deserialize);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.CONSTANT;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object getEmptyValue(DeserializationContext deserializationContext) {
        Object obj = this._emptyValue;
        if (obj != null) {
            return obj;
        }
        T _constructEmpty = _constructEmpty();
        this._emptyValue = _constructEmpty;
        return _constructEmpty;
    }

    protected T handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.hasToken(JsonToken.VALUE_STRING) && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
            return null;
        }
        Boolean bool = this._unwrapSingle;
        if (bool == Boolean.TRUE || (bool == null && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))) {
            return handleSingleElementUnwrapped(jsonParser, deserializationContext);
        }
        return (T) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
    }

    protected abstract T handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext);

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return Boolean.TRUE;
    }

    protected abstract PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nullValueProvider, Boolean bool);

    protected PrimitiveArrayDeserializers(PrimitiveArrayDeserializers<?> primitiveArrayDeserializers, NullValueProvider nullValueProvider, Boolean bool) {
        super(primitiveArrayDeserializers._valueClass);
        this._unwrapSingle = bool;
        this._nuller = nullValueProvider;
    }
}
