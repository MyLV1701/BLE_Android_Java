package a.n;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

/* loaded from: classes.dex */
class h<T> extends Property<T, Float> {

    /* renamed from: a, reason: collision with root package name */
    private final Property<T, PointF> f461a;

    /* renamed from: b, reason: collision with root package name */
    private final PathMeasure f462b;

    /* renamed from: c, reason: collision with root package name */
    private final float f463c;

    /* renamed from: d, reason: collision with root package name */
    private final float[] f464d;

    /* renamed from: e, reason: collision with root package name */
    private final PointF f465e;

    /* renamed from: f, reason: collision with root package name */
    private float f466f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(Property<T, PointF> property, Path path) {
        super(Float.class, property.getName());
        this.f464d = new float[2];
        this.f465e = new PointF();
        this.f461a = property;
        this.f462b = new PathMeasure(path, false);
        this.f463c = this.f462b.getLength();
    }

    @Override // android.util.Property
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void set(T t, Float f2) {
        this.f466f = f2.floatValue();
        this.f462b.getPosTan(this.f463c * f2.floatValue(), this.f464d, null);
        PointF pointF = this.f465e;
        float[] fArr = this.f464d;
        pointF.x = fArr[0];
        pointF.y = fArr[1];
        this.f461a.set(t, pointF);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.util.Property
    public /* bridge */ /* synthetic */ Float get(Object obj) {
        return get((h<T>) obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.util.Property
    public Float get(T t) {
        return Float.valueOf(this.f466f);
    }
}
