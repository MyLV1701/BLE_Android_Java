package org.simpleframework.xml.core;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
final class Reflector {
    Reflector() {
    }

    private static Class getArrayClass(Type type) {
        Class cls = getClass(((GenericArrayType) type).getGenericComponentType());
        if (cls != null) {
            return Array.newInstance((Class<?>) cls, 0).getClass();
        }
        return null;
    }

    private static Class getClass(ParameterizedType parameterizedType) {
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (actualTypeArguments.length > 0) {
            return getClass(actualTypeArguments[0]);
        }
        return null;
    }

    private static Class[] getClasses(ParameterizedType parameterizedType) {
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Class[] clsArr = new Class[actualTypeArguments.length];
        for (int i = 0; i < actualTypeArguments.length; i++) {
            clsArr[i] = getClass(actualTypeArguments[i]);
        }
        return clsArr;
    }

    public static Class getDependent(Field field) {
        ParameterizedType type = getType(field);
        if (type != null) {
            return getClass(type);
        }
        return Object.class;
    }

    public static Class[] getDependents(Field field) {
        ParameterizedType type = getType(field);
        return type != null ? getClasses(type) : new Class[0];
    }

    private static Class getGenericClass(Type type) {
        if (type instanceof GenericArrayType) {
            return getArrayClass(type);
        }
        return Object.class;
    }

    public static String getName(String str) {
        if (str.length() <= 0) {
            return str;
        }
        char[] charArray = str.toCharArray();
        char c2 = charArray[0];
        if (!isAcronym(charArray)) {
            charArray[0] = toLowerCase(c2);
        }
        return new String(charArray);
    }

    public static Class getParameterDependent(Method method, int i) {
        ParameterizedType parameterType = getParameterType(method, i);
        if (parameterType != null) {
            return getClass(parameterType);
        }
        return Object.class;
    }

    public static Class[] getParameterDependents(Method method, int i) {
        ParameterizedType parameterType = getParameterType(method, i);
        return parameterType != null ? getClasses(parameterType) : new Class[0];
    }

    private static ParameterizedType getParameterType(Method method, int i) {
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        if (genericParameterTypes.length <= i) {
            return null;
        }
        Type type = genericParameterTypes[i];
        if (type instanceof ParameterizedType) {
            return (ParameterizedType) type;
        }
        return null;
    }

    public static Class getReturnDependent(Method method) {
        ParameterizedType returnType = getReturnType(method);
        if (returnType != null) {
            return getClass(returnType);
        }
        return Object.class;
    }

    public static Class[] getReturnDependents(Method method) {
        ParameterizedType returnType = getReturnType(method);
        return returnType != null ? getClasses(returnType) : new Class[0];
    }

    private static ParameterizedType getReturnType(Method method) {
        Type genericReturnType = method.getGenericReturnType();
        if (genericReturnType instanceof ParameterizedType) {
            return (ParameterizedType) genericReturnType;
        }
        return null;
    }

    private static ParameterizedType getType(Field field) {
        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType) {
            return (ParameterizedType) genericType;
        }
        return null;
    }

    private static boolean isAcronym(char[] cArr) {
        if (cArr.length >= 2 && isUpperCase(cArr[0])) {
            return isUpperCase(cArr[1]);
        }
        return false;
    }

    private static boolean isUpperCase(char c2) {
        return Character.isUpperCase(c2);
    }

    private static char toLowerCase(char c2) {
        return Character.toLowerCase(c2);
    }

    public static Class[] getParameterDependents(Constructor constructor, int i) {
        ParameterizedType parameterType = getParameterType(constructor, i);
        return parameterType != null ? getClasses(parameterType) : new Class[0];
    }

    private static Class getClass(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        return getGenericClass(type);
    }

    public static Class getParameterDependent(Constructor constructor, int i) {
        ParameterizedType parameterType = getParameterType(constructor, i);
        if (parameterType != null) {
            return getClass(parameterType);
        }
        return Object.class;
    }

    private static ParameterizedType getParameterType(Constructor constructor, int i) {
        Type[] genericParameterTypes = constructor.getGenericParameterTypes();
        if (genericParameterTypes.length <= i) {
            return null;
        }
        Type type = genericParameterTypes[i];
        if (type instanceof ParameterizedType) {
            return (ParameterizedType) type;
        }
        return null;
    }
}
