package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/* loaded from: classes.dex */
public class OptionalHandlerFactory implements Serializable {
    private static final Java7Handlers _jdk7Helper;
    public static final OptionalHandlerFactory instance;
    private static final Class<?> CLASS_DOM_NODE = Node.class;
    private static final Class<?> CLASS_DOM_DOCUMENT = Document.class;

    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    /* JADX WARN: Unreachable blocks removed: 1, instructions: 7 */
    static {
        Java7Handlers java7Handlers = null;
        try {
            java7Handlers = Java7Handlers.instance();
        } catch (Throwable unused) {
        }
        _jdk7Helper = java7Handlers;
        instance = new OptionalHandlerFactory();
    }

    protected OptionalHandlerFactory() {
    }

    private boolean hasSuperClassStartingWith(Class<?> cls, String str) {
        do {
            cls = cls.getSuperclass();
            if (cls == null || cls == Object.class) {
                return false;
            }
        } while (!cls.getName().startsWith(str));
        return true;
    }

    private Object instantiate(String str) {
        try {
            return ClassUtil.createInstance(Class.forName(str), false);
        } catch (Exception | LinkageError unused) {
            return null;
        }
    }

    public JsonDeserializer<?> findDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) {
        Object instantiate;
        JsonDeserializer<?> deserializerForJavaNioFilePath;
        Class<?> rawClass = javaType.getRawClass();
        Java7Handlers java7Handlers = _jdk7Helper;
        if (java7Handlers != null && (deserializerForJavaNioFilePath = java7Handlers.getDeserializerForJavaNioFilePath(rawClass)) != null) {
            return deserializerForJavaNioFilePath;
        }
        Class<?> cls = CLASS_DOM_NODE;
        if (cls != null && cls.isAssignableFrom(rawClass)) {
            return (JsonDeserializer) instantiate("com.fasterxml.jackson.databind.ext.DOMDeserializer$NodeDeserializer");
        }
        Class<?> cls2 = CLASS_DOM_DOCUMENT;
        if (cls2 != null && cls2.isAssignableFrom(rawClass)) {
            return (JsonDeserializer) instantiate("com.fasterxml.jackson.databind.ext.DOMDeserializer$DocumentDeserializer");
        }
        if ((rawClass.getName().startsWith("javax.xml.") || hasSuperClassStartingWith(rawClass, "javax.xml.")) && (instantiate = instantiate("com.fasterxml.jackson.databind.ext.CoreXMLDeserializers")) != null) {
            return ((Deserializers) instantiate).findBeanDeserializer(javaType, deserializationConfig, beanDescription);
        }
        return null;
    }

    public JsonSerializer<?> findSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription) {
        Object instantiate;
        JsonSerializer<?> serializerForJavaNioFilePath;
        Class<?> rawClass = javaType.getRawClass();
        Class<?> cls = CLASS_DOM_NODE;
        if (cls != null && cls.isAssignableFrom(rawClass)) {
            return (JsonSerializer) instantiate("com.fasterxml.jackson.databind.ext.DOMSerializer");
        }
        Java7Handlers java7Handlers = _jdk7Helper;
        if (java7Handlers != null && (serializerForJavaNioFilePath = java7Handlers.getSerializerForJavaNioFilePath(rawClass)) != null) {
            return serializerForJavaNioFilePath;
        }
        if ((rawClass.getName().startsWith("javax.xml.") || hasSuperClassStartingWith(rawClass, "javax.xml.")) && (instantiate = instantiate("com.fasterxml.jackson.databind.ext.CoreXMLSerializers")) != null) {
            return ((Serializers) instantiate).findSerializer(serializationConfig, javaType, beanDescription);
        }
        return null;
    }
}
