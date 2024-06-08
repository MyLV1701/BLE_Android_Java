package org.simpleframework.xml.transform;

/* loaded from: classes.dex */
class DefaultMatcher implements Matcher {
    private Matcher matcher;
    private Matcher primitive = new PrimitiveMatcher();
    private Matcher stock = new PackageMatcher();
    private Matcher array = new ArrayMatcher(this);

    public DefaultMatcher(Matcher matcher) {
        this.matcher = matcher;
    }

    private Transform matchType(Class cls) {
        if (cls.isArray()) {
            return this.array.match(cls);
        }
        if (cls.isPrimitive()) {
            return this.primitive.match(cls);
        }
        return this.stock.match(cls);
    }

    @Override // org.simpleframework.xml.transform.Matcher
    public Transform match(Class cls) {
        Transform match = this.matcher.match(cls);
        return match != null ? match : matchType(cls);
    }
}
