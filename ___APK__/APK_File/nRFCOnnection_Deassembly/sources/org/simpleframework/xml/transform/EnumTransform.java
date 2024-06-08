package org.simpleframework.xml.transform;

/* loaded from: classes.dex */
class EnumTransform implements Transform<Enum> {
    private final Class type;

    public EnumTransform(Class cls) {
        this.type = cls;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.simpleframework.xml.transform.Transform
    public Enum read(String str) {
        return Enum.valueOf(this.type, str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(Enum r1) {
        return r1.name();
    }
}
