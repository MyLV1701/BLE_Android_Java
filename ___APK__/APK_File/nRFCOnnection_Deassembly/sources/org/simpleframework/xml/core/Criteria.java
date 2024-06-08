package org.simpleframework.xml.core;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public interface Criteria extends Iterable<Object> {
    void commit(Object obj);

    Variable get(Object obj);

    Variable get(Label label);

    Variable remove(Object obj);

    Variable resolve(String str);

    void set(Label label, Object obj);
}
