package c.a.a.a.b0;

import android.graphics.RectF;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class b implements c {

    /* renamed from: a, reason: collision with root package name */
    private final c f2030a;

    /* renamed from: b, reason: collision with root package name */
    private final float f2031b;

    public b(float f2, c cVar) {
        while (cVar instanceof b) {
            cVar = ((b) cVar).f2030a;
            f2 += ((b) cVar).f2031b;
        }
        this.f2030a = cVar;
        this.f2031b = f2;
    }

    @Override // c.a.a.a.b0.c
    public float a(RectF rectF) {
        return Math.max(0.0f, this.f2030a.a(rectF) + this.f2031b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f2030a.equals(bVar.f2030a) && this.f2031b == bVar.f2031b;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f2030a, Float.valueOf(this.f2031b)});
    }
}
