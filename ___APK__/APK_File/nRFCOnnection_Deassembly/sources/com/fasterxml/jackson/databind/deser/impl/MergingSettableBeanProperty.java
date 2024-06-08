package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;

/* loaded from: classes.dex */
public class MergingSettableBeanProperty extends SettableBeanProperty.Delegating {
    protected final AnnotatedMember _accessor;

    protected MergingSettableBeanProperty(SettableBeanProperty settableBeanProperty, AnnotatedMember annotatedMember) {
        super(settableBeanProperty);
        this._accessor = annotatedMember;
    }

    public static MergingSettableBeanProperty construct(SettableBeanProperty settableBeanProperty, AnnotatedMember annotatedMember) {
        return new MergingSettableBeanProperty(settableBeanProperty, annotatedMember);
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty
    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        Object deserializeWith;
        Object value = this._accessor.getValue(obj);
        if (value == null) {
            deserializeWith = this.delegate.deserialize(jsonParser, deserializationContext);
        } else {
            deserializeWith = this.delegate.deserializeWith(jsonParser, deserializationContext, value);
        }
        if (deserializeWith != value) {
            this.delegate.set(obj, deserializeWith);
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty
    public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        Object deserializeWith;
        Object value = this._accessor.getValue(obj);
        if (value == null) {
            deserializeWith = this.delegate.deserialize(jsonParser, deserializationContext);
        } else {
            deserializeWith = this.delegate.deserializeWith(jsonParser, deserializationContext, value);
        }
        return (deserializeWith == value || deserializeWith == null) ? obj : this.delegate.setAndReturn(obj, deserializeWith);
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty.Delegating, com.fasterxml.jackson.databind.deser.SettableBeanProperty
    public void set(Object obj, Object obj2) {
        if (obj2 != null) {
            this.delegate.set(obj, obj2);
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty.Delegating, com.fasterxml.jackson.databind.deser.SettableBeanProperty
    public Object setAndReturn(Object obj, Object obj2) {
        return obj2 != null ? this.delegate.setAndReturn(obj, obj2) : obj;
    }

    @Override // com.fasterxml.jackson.databind.deser.SettableBeanProperty.Delegating
    protected SettableBeanProperty withDelegate(SettableBeanProperty settableBeanProperty) {
        return new MergingSettableBeanProperty(settableBeanProperty, this._accessor);
    }
}
