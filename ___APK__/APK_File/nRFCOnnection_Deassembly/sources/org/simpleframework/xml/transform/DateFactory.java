package org.simpleframework.xml.transform;

import java.lang.reflect.Constructor;
import java.util.Date;

/* loaded from: classes.dex */
class DateFactory<T extends Date> {
    private final Constructor<T> factory;

    public DateFactory(Class<T> cls) {
        this(cls, Long.TYPE);
    }

    public T getInstance(Object... objArr) {
        return this.factory.newInstance(objArr);
    }

    public DateFactory(Class<T> cls, Class... clsArr) {
        this.factory = cls.getDeclaredConstructor(clsArr);
    }
}
