package org.simpleframework.xml.convert;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;

/* loaded from: classes.dex */
class ConverterScanner {
    private final ConverterFactory factory = new ConverterFactory();
    private final ScannerBuilder builder = new ScannerBuilder();

    private <T extends Annotation> T getAnnotation(Class<?> cls, Class<T> cls2) {
        return (T) this.builder.build(cls).scan(cls2);
    }

    private Convert getConvert(Type type, Class cls) {
        Convert convert = getConvert(type);
        return convert == null ? getConvert(cls) : convert;
    }

    private Class getType(Type type, Value value) {
        return value != null ? value.getType() : type.getType();
    }

    public Converter getConverter(Type type, Value value) {
        Convert convert = getConvert(type, getType(type, value));
        if (convert != null) {
            return this.factory.getInstance(convert);
        }
        return null;
    }

    private Convert getConvert(Type type) {
        Convert convert = (Convert) type.getAnnotation(Convert.class);
        if (convert == null || ((Element) type.getAnnotation(Element.class)) != null) {
            return convert;
        }
        throw new ConvertException("Element annotation required for %s", type);
    }

    private Class getType(Type type, Object obj) {
        return obj != null ? obj.getClass() : type.getType();
    }

    public Converter getConverter(Type type, Object obj) {
        Convert convert = getConvert(type, getType(type, obj));
        if (convert != null) {
            return this.factory.getInstance(convert);
        }
        return null;
    }

    private Convert getConvert(Class cls) {
        Convert convert = (Convert) getAnnotation(cls, Convert.class);
        if (convert == null || ((Root) getAnnotation(cls, Root.class)) != null) {
            return convert;
        }
        throw new ConvertException("Root annotation required for %s", cls);
    }
}
