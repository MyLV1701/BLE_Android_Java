package c.a.a.a.b0;

import android.graphics.RectF;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class i implements c {

    /* renamed from: a, reason: collision with root package name */
    private final float f2046a;

    public i(float f2) {
        this.f2046a = f2;
    }

    @Override // c.a.a.a.b0.c
    public float a(RectF rectF) {
        return this.f2046a * rectF.height();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof i) && this.f2046a == ((i) obj).f2046a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f2046a)});
    }
}
