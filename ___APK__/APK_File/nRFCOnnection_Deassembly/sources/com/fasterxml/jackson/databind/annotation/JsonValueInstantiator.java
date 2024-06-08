package com.fasterxml.jackson.databind.annotation;

import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface JsonValueInstantiator {
    Class<? extends ValueInstantiator> value();
}
