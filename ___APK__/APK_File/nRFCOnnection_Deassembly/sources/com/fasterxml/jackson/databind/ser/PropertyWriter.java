package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ConcreteBeanPropertyBase;
import java.io.Serializable;

/* loaded from: classes.dex */
public abstract class PropertyWriter extends ConcreteBeanPropertyBase implements Serializable {
    /* JADX INFO: Access modifiers changed from: protected */
    public PropertyWriter(PropertyMetadata propertyMetadata) {
        super(propertyMetadata);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PropertyWriter(BeanPropertyDefinition beanPropertyDefinition) {
        super(beanPropertyDefinition.getMetadata());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PropertyWriter(PropertyWriter propertyWriter) {
        super(propertyWriter);
    }
}
