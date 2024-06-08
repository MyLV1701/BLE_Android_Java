package c.a.a.a.b0;

import android.graphics.RectF;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class a implements c {

    /* renamed from: a, reason: collision with root package name */
    private final float f2029a;

    public a(float f2) {
        this.f2029a = f2;
    }

    @Override // c.a.a.a.b0.c
    public float a(RectF rectF) {
        return this.f2029a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof a) && this.f2029a == ((a) obj).f2029a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f2029a)});
    }
}
