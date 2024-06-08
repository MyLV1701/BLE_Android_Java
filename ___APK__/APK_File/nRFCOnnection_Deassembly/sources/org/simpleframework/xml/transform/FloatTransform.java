package org.simpleframework.xml.transform;

/* loaded from: classes.dex */
class FloatTransform implements Transform<Float> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.simpleframework.xml.transform.Transform
    public Float read(String str) {
        return Float.valueOf(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(Float f2) {
        return f2.toString();
    }
}
