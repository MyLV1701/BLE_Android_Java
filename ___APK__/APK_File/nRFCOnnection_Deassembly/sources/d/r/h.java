package d.r;

import no.nordicsemi.android.ble.error.GattError;

/* loaded from: classes.dex */
public final class h {

    /* renamed from: b, reason: collision with root package name */
    private static h[] f3172b = new h[0];

    /* renamed from: c, reason: collision with root package name */
    public static h f3173c = new h(0, "horizontal");

    /* renamed from: a, reason: collision with root package name */
    private int f3174a;

    static {
        new h(255, "vertical");
        new h(90, "up 90");
        new h(180, "down 90");
        new h(45, "up 45");
        new h(GattError.GATT_ILLEGAL_PARAMETER, "down 45");
        new h(255, "stacked");
    }

    protected h(int i, String str) {
        this.f3174a = i;
        h[] hVarArr = f3172b;
        f3172b = new h[hVarArr.length + 1];
        System.arraycopy(hVarArr, 0, f3172b, 0, hVarArr.length);
        f3172b[hVarArr.length] = this;
    }

    public int a() {
        return this.f3174a;
    }

    public static h a(int i) {
        int i2 = 0;
        while (true) {
            h[] hVarArr = f3172b;
            if (i2 < hVarArr.length) {
                if (hVarArr[i2].a() == i) {
                    return f3172b[i2];
                }
                i2++;
            } else {
                return f3173c;
            }
        }
    }
}
