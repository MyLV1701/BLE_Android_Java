package org.simpleframework.xml.transform;

import java.util.Date;

/* loaded from: classes.dex */
class DateTransform<T extends Date> implements Transform<T> {
    private final DateFactory<T> factory;

    public DateTransform(Class<T> cls) {
        this.factory = new DateFactory<>(cls);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public synchronized T read(String str) {
        return this.factory.getInstance(Long.valueOf(DateType.getDate(str).getTime()));
    }

    @Override // org.simpleframework.xml.transform.Transform
    public synchronized String write(T t) {
        return DateType.getText(t);
    }
}
