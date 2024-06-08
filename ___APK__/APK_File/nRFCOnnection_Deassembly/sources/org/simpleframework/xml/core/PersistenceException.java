package org.simpleframework.xml.core;

/* loaded from: classes.dex */
public class PersistenceException extends Exception {
    public PersistenceException(String str, Object... objArr) {
        super(String.format(str, objArr));
    }

    public PersistenceException(Throwable th, String str, Object... objArr) {
        super(String.format(str, objArr), th);
    }
}
