package c.a.a.a.m;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/* loaded from: classes.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    private long f2108a;

    /* renamed from: b, reason: collision with root package name */
    private long f2109b;

    /* renamed from: c, reason: collision with root package name */
    private TimeInterpolator f2110c;

    /* renamed from: d, reason: collision with root package name */
    private int f2111d;

    /* renamed from: e, reason: collision with root package name */
    private int f2112e;

    public i(long j, long j2) {
        this.f2108a = 0L;
        this.f2109b = 300L;
        this.f2110c = null;
        this.f2111d = 0;
        this.f2112e = 1;
        this.f2108a = j;
        this.f2109b = j2;
    }

    public void a(Animator animator) {
        animator.setStartDelay(a());
        animator.setDuration(b());
        animator.setInterpolator(c());
        if (animator instanceof ValueAnimator) {
            ValueAnimator valueAnimator = (ValueAnimator) animator;
            valueAnimator.setRepeatCount(d());
            valueAnimator.setRepeatMode(e());
        }
    }

    public long b() {
        return this.f2109b;
    }

    public TimeInterpolator c() {
        TimeInterpolator timeInterpolator = this.f2110c;
        return timeInterpolator != null ? timeInterpolator : a.f2094b;
    }

    public int d() {
        return this.f2111d;
    }

    public int e() {
        return this.f2112e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        if (a() == iVar.a() && b() == iVar.b() && d() == iVar.d() && e() == iVar.e()) {
            return c().getClass().equals(iVar.c().getClass());
        }
        return false;
    }

    public int hashCode() {
        return (((((((((int) (a() ^ (a() >>> 32))) * 31) + ((int) (b() ^ (b() >>> 32)))) * 31) + c().getClass().hashCode()) * 31) + d()) * 31) + e();
    }

    public String toString() {
        return '\n' + i.class.getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " delay: " + a() + " duration: " + b() + " interpolator: " + c().getClass() + " repeatCount: " + d() + " repeatMode: " + e() + "}\n";
    }

    private static TimeInterpolator b(ValueAnimator valueAnimator) {
        TimeInterpolator interpolator = valueAnimator.getInterpolator();
        if (!(interpolator instanceof AccelerateDecelerateInterpolator) && interpolator != null) {
            if (interpolator instanceof AccelerateInterpolator) {
                return a.f2095c;
            }
            return interpolator instanceof DecelerateInterpolator ? a.f2096d : interpolator;
        }
        return a.f2094b;
    }

    public long a() {
        return this.f2108a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static i a(ValueAnimator valueAnimator) {
        i iVar = new i(valueAnimator.getStartDelay(), valueAnimator.getDuration(), b(valueAnimator));
        iVar.f2111d = valueAnimator.getRepeatCount();
        iVar.f2112e = valueAnimator.getRepeatMode();
        return iVar;
    }

    public i(long j, long j2, TimeInterpolator timeInterpolator) {
        this.f2108a = 0L;
        this.f2109b = 300L;
        this.f2110c = null;
        this.f2111d = 0;
        this.f2112e = 1;
        this.f2108a = j;
        this.f2109b = j2;
        this.f2110c = timeInterpolator;
    }
}
