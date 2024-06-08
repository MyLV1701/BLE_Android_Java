package org.simpleframework.xml.transform;

import java.lang.reflect.Array;

/* loaded from: classes.dex */
class ArrayTransform implements Transform {
    private final Transform delegate;
    private final Class entry;
    private final StringArrayTransform split = new StringArrayTransform();

    public ArrayTransform(Transform transform, Class cls) {
        this.delegate = transform;
        this.entry = cls;
    }

    @Override // org.simpleframework.xml.transform.Transform
    public Object read(String str) {
        String[] read = this.split.read(str);
        return read(read, read.length);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(Object obj) {
        return write(obj, Array.getLength(obj));
    }

    private String write(Object obj, int i) {
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            Object obj2 = Array.get(obj, i2);
            if (obj2 != null) {
                strArr[i2] = this.delegate.write(obj2);
            }
        }
        return this.split.write(strArr);
    }

    private Object read(String[] strArr, int i) {
        Object newInstance = Array.newInstance((Class<?>) this.entry, i);
        for (int i2 = 0; i2 < i; i2++) {
            Object read = this.delegate.read(strArr[i2]);
            if (read != null) {
                Array.set(newInstance, i2, read);
            }
        }
        return newInstance;
    }
}
