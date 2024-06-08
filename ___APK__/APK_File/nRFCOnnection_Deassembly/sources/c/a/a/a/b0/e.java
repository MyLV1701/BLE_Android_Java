package c.a.a.a.b0;

/* loaded from: classes.dex */
public class e extends d {

    /* renamed from: a, reason: collision with root package name */
    float f2032a = -1.0f;

    @Override // c.a.a.a.b0.d
    public void a(m mVar, float f2, float f3, float f4) {
        mVar.a(0.0f, f4 * f3, 180.0f, 180.0f - f2);
        double sin = Math.sin(Math.toRadians(f2));
        double d2 = f4;
        Double.isNaN(d2);
        double d3 = f3;
        Double.isNaN(d3);
        double sin2 = Math.sin(Math.toRadians(90.0f - f2));
        Double.isNaN(d2);
        Double.isNaN(d3);
        mVar.a((float) (sin * d2 * d3), (float) (sin2 * d2 * d3));
    }
}
