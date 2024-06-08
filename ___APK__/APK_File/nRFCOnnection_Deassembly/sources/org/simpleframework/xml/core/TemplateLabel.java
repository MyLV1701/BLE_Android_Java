package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;

/* loaded from: classes.dex */
abstract class TemplateLabel implements Label {
    private final KeyBuilder builder = new KeyBuilder(this);

    @Override // org.simpleframework.xml.core.Label
    public Type getDependent() {
        return null;
    }

    @Override // org.simpleframework.xml.core.Label
    public String getEntry() {
        return null;
    }

    @Override // org.simpleframework.xml.core.Label
    public Object getKey() {
        return this.builder.getKey();
    }

    @Override // org.simpleframework.xml.core.Label
    public Label getLabel(Class cls) {
        return this;
    }

    @Override // org.simpleframework.xml.core.Label
    public String[] getNames() {
        return new String[]{getPath(), getName()};
    }

    @Override // org.simpleframework.xml.core.Label
    public String[] getPaths() {
        return new String[]{getPath()};
    }

    @Override // org.simpleframework.xml.core.Label
    public Type getType(Class cls) {
        return getContact();
    }

    @Override // org.simpleframework.xml.core.Label
    public boolean isAttribute() {
        return false;
    }

    @Override // org.simpleframework.xml.core.Label
    public boolean isCollection() {
        return false;
    }

    @Override // org.simpleframework.xml.core.Label
    public boolean isInline() {
        return false;
    }

    @Override // org.simpleframework.xml.core.Label
    public boolean isText() {
        return false;
    }

    @Override // org.simpleframework.xml.core.Label
    public boolean isTextList() {
        return false;
    }

    @Override // org.simpleframework.xml.core.Label
    public boolean isUnion() {
        return false;
    }
}
