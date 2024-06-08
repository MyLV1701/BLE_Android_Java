package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;

/* loaded from: classes.dex */
interface Extractor<T extends Annotation> {
    T[] getAnnotations();

    Label getLabel(T t);

    Class getType(T t);
}
