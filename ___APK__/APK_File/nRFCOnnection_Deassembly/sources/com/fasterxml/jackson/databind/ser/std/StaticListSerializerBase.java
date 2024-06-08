package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.util.Collection;

/* loaded from: classes.dex */
public abstract class StaticListSerializerBase<T extends Collection<?>> extends StdSerializer<T> implements ContextualSerializer {
    protected final Boolean _unwrapSingle;

    /* JADX INFO: Access modifiers changed from: protected */
    public StaticListSerializerBase(Class<?> cls) {
        super(cls, false);
        this._unwrapSingle = null;
    }

    public abstract JsonSerializer<?> _withResolved(BeanProperty beanProperty, Boolean bool);

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x002a  */
    @Override // com.fasterxml.jackson.databind.ser.ContextualSerializer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.fasterxml.jackson.databind.JsonSerializer<?> createContextual(com.fasterxml.jackson.databind.SerializerProvider r5, com.fasterxml.jackson.databind.BeanProperty r6) {
        /*
            r4 = this;
            r0 = 0
            if (r6 == 0) goto L18
            com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r5.getAnnotationIntrospector()
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r2 = r6.getMember()
            if (r2 == 0) goto L18
            java.lang.Object r1 = r1.findContentSerializer(r2)
            if (r1 == 0) goto L18
            com.fasterxml.jackson.databind.JsonSerializer r1 = r5.serializerInstance(r2, r1)
            goto L19
        L18:
            r1 = r0
        L19:
            java.lang.Class r2 = r4.handledType()
            com.fasterxml.jackson.annotation.JsonFormat$Value r2 = r4.findFormatOverrides(r5, r6, r2)
            if (r2 == 0) goto L2a
            com.fasterxml.jackson.annotation.JsonFormat$Feature r3 = com.fasterxml.jackson.annotation.JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED
            java.lang.Boolean r2 = r2.getFeature(r3)
            goto L2b
        L2a:
            r2 = r0
        L2b:
            com.fasterxml.jackson.databind.JsonSerializer r1 = r4.findContextualConvertingSerializer(r5, r6, r1)
            if (r1 != 0) goto L37
            java.lang.Class<java.lang.String> r1 = java.lang.String.class
            com.fasterxml.jackson.databind.JsonSerializer r1 = r5.findValueSerializer(r1, r6)
        L37:
            boolean r3 = r4.isDefaultSerializer(r1)
            if (r3 == 0) goto L47
            java.lang.Boolean r5 = r4._unwrapSingle
            if (r2 != r5) goto L42
            return r4
        L42:
            com.fasterxml.jackson.databind.JsonSerializer r5 = r4._withResolved(r6, r2)
            return r5
        L47:
            com.fasterxml.jackson.databind.ser.std.CollectionSerializer r6 = new com.fasterxml.jackson.databind.ser.std.CollectionSerializer
            java.lang.Class<java.lang.String> r2 = java.lang.String.class
            com.fasterxml.jackson.databind.JavaType r5 = r5.constructType(r2)
            r2 = 1
            r6.<init>(r5, r2, r0, r1)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.StaticListSerializerBase.createContextual(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.BeanProperty):com.fasterxml.jackson.databind.JsonSerializer");
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public boolean isEmpty(SerializerProvider serializerProvider, T t) {
        return t == null || t.size() == 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StaticListSerializerBase(StaticListSerializerBase<?> staticListSerializerBase, Boolean bool) {
        super(staticListSerializerBase);
        this._unwrapSingle = bool;
    }
}
