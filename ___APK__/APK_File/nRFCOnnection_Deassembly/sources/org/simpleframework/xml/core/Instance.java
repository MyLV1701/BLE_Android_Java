package org.simpleframework.xml.core;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public interface Instance {
    Object getInstance();

    Class getType();

    boolean isReference();

    Object setInstance(Object obj);
}
