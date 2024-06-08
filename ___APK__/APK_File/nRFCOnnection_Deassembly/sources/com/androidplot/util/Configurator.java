package com.androidplot.util;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public abstract class Configurator {
    protected static final String CFG_ELEMENT_NAME = "config";
    private static final String TAG = "com.androidplot.util.Configurator";

    protected static String argArrToString(Object[] objArr) {
        String str = "";
        for (Object obj : objArr) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(obj == null ? str + "[null] " : "[" + obj.getClass() + ": " + obj + "] ");
            str = sb.toString();
        }
        return str;
    }

    public static void configure(Context context, Object obj, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            try {
                HashMap hashMap = new HashMap();
                while (true) {
                    if (xml.getEventType() == 1) {
                        break;
                    }
                    xml.next();
                    String name = xml.getName();
                    if (xml.getEventType() == 2) {
                        if (name.equalsIgnoreCase(CFG_ELEMENT_NAME)) {
                            for (int i2 = 0; i2 < xml.getAttributeCount(); i2++) {
                                hashMap.put(xml.getAttributeName(i2), xml.getAttributeValue(i2));
                            }
                        }
                    }
                }
                configure(context, obj, (HashMap<String, String>) hashMap);
            } catch (IOException e2) {
                e2.printStackTrace();
            } catch (XmlPullParserException e3) {
                e3.printStackTrace();
            }
        } finally {
            xml.close();
        }
    }

    protected static Method getGetter(Class cls, String str) {
        return cls.getMethod("get" + str.substring(0, 1).toUpperCase() + str.substring(1, str.length()), new Class[0]);
    }

    protected static Object getObjectContaining(Object obj, String str) {
        if (obj != null) {
            int indexOf = str.indexOf(".");
            if (indexOf <= 0) {
                return obj;
            }
            String substring = str.substring(0, indexOf);
            String substring2 = str.substring(indexOf + 1, str.length());
            Method getter = getGetter(obj.getClass(), substring);
            if (getter != null) {
                return getObjectContaining(getter.invoke(obj, new Object[0]), substring2);
            }
            throw new NullPointerException("No getter found for field: " + substring + " within " + obj.getClass());
        }
        throw new NullPointerException("Attempt to call getObjectContaining(Object obj, String path) on a null Object instance.  Path was: " + str);
    }

    protected static Method getSetter(Class cls, String str) {
        String str2 = "set" + str;
        for (Method method : cls.getMethods()) {
            if (method.getName().equalsIgnoreCase(str2)) {
                return method;
            }
        }
        throw new NoSuchMethodException("No such public method (case insensitive): " + str2 + " in " + cls);
    }

    private static Object[] inflateParams(Context context, Class[] clsArr, String[] strArr) {
        Object[] objArr = new Object[clsArr.length];
        int i = 0;
        for (Class cls : clsArr) {
            if (Enum.class.isAssignableFrom(cls)) {
                objArr[i] = cls.getMethod("valueOf", String.class).invoke(null, strArr[i].toUpperCase());
            } else if (cls.equals(Float.TYPE)) {
                objArr[i] = Float.valueOf(parseFloatAttr(context, strArr[i]));
            } else if (cls.equals(Integer.TYPE)) {
                objArr[i] = Integer.valueOf(parseIntAttr(context, strArr[i]));
            } else if (cls.equals(Boolean.TYPE)) {
                objArr[i] = Boolean.valueOf(strArr[i]);
            } else if (cls.equals(String.class)) {
                objArr[i] = parseStringAttr(context, strArr[i]);
            } else {
                throw new IllegalArgumentException("Error inflating XML: Setter requires param of unsupported type: " + cls);
            }
            i++;
        }
        return objArr;
    }

    protected static float parseFloatAttr(Context context, String str) {
        try {
            try {
                return context.getResources().getDimension(parseResId(context, "@dimen", str));
            } catch (IllegalArgumentException unused) {
                return PixelUtils.stringToDimension(str);
            }
        } catch (Exception unused2) {
            return Float.parseFloat(str);
        }
    }

    protected static int parseIntAttr(Context context, String str) {
        try {
            try {
                return context.getResources().getColor(parseResId(context, "@color", str));
            } catch (IllegalArgumentException unused) {
                return Integer.parseInt(str);
            }
        } catch (IllegalArgumentException unused2) {
            return Color.parseColor(str);
        }
    }

    protected static int parseResId(Context context, String str, String str2) {
        if (str2.startsWith("@")) {
            return Integer.parseInt(str2.substring(1));
        }
        String[] split = str2.split("/");
        if (split.length > 1 && split[0].equalsIgnoreCase(str)) {
            String replace = split[0].replace("@", "");
            return context.getResources().getIdentifier(split[1], replace, context.getPackageName());
        }
        throw new IllegalArgumentException();
    }

    protected static String parseStringAttr(Context context, String str) {
        try {
            return context.getResources().getString(parseResId(context, "@string", str));
        } catch (IllegalArgumentException unused) {
            return str;
        }
    }

    public static void configure(Context context, Object obj, HashMap<String, String> hashMap) {
        for (String str : hashMap.keySet()) {
            try {
                configure(context, obj, str, hashMap.get(str));
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (NoSuchMethodException e3) {
                Log.w(TAG, "Error inflating XML: Setter for field \"" + str + "\" does not exist. ");
                e3.printStackTrace();
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
            }
        }
    }

    protected static void configure(Context context, Object obj, String str, String str2) {
        Object objectContaining = getObjectContaining(obj, str);
        if (objectContaining != null) {
            int lastIndexOf = str.lastIndexOf(".");
            if (lastIndexOf > 0) {
                str = str.substring(lastIndexOf + 1, str.length());
            }
            Method setter = getSetter(objectContaining.getClass(), str);
            Class<?>[] parameterTypes = setter.getParameterTypes();
            if (parameterTypes.length >= 1) {
                String[] split = str2.split("\\|");
                if (split.length == parameterTypes.length) {
                    setter.invoke(objectContaining, inflateParams(context, parameterTypes, split));
                    return;
                }
                throw new IllegalArgumentException("Error inflating XML: Unexpected number of arguments passed to \"" + setter.getName() + "\".  Expected: " + parameterTypes.length + " Got: " + split.length);
            }
            throw new IllegalArgumentException("Error inflating XML: no setter method found for param \"" + str + "\".");
        }
    }
}
