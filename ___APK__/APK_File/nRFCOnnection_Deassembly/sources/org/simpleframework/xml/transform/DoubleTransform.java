package org.simpleframework.xml.transform;

/* loaded from: classes.dex */
class DoubleTransform implements Transform<Double> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.simpleframework.xml.transform.Transform
    public Double read(String str) {
        return Double.valueOf(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(Double d2) {
        return d2.toString();
    }
}
