package androidx.appcompat.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import java.util.Calendar;

/* loaded from: classes.dex */
class m {

    /* renamed from: d, reason: collision with root package name */
    private static m f723d;

    /* renamed from: a, reason: collision with root package name */
    private final Context f724a;

    /* renamed from: b, reason: collision with root package name */
    private final LocationManager f725b;

    /* renamed from: c, reason: collision with root package name */
    private final a f726c = new a();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        boolean f727a;

        /* renamed from: b, reason: collision with root package name */
        long f728b;

        /* renamed from: c, reason: collision with root package name */
        long f729c;

        /* renamed from: d, reason: collision with root package name */
        long f730d;

        /* renamed from: e, reason: collision with root package name */
        long f731e;

        /* renamed from: f, reason: collision with root package name */
        long f732f;

        a() {
        }
    }

    m(Context context, LocationManager locationManager) {
        this.f724a = context;
        this.f725b = locationManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static m a(Context context) {
        if (f723d == null) {
            Context applicationContext = context.getApplicationContext();
            f723d = new m(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return f723d;
    }

    @SuppressLint({"MissingPermission"})
    private Location b() {
        Location a2 = a.f.d.c.a(this.f724a, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? a("network") : null;
        Location a3 = a.f.d.c.a(this.f724a, "android.permission.ACCESS_FINE_LOCATION") == 0 ? a("gps") : null;
        return (a3 == null || a2 == null) ? a3 != null ? a3 : a2 : a3.getTime() > a2.getTime() ? a3 : a2;
    }

    private boolean c() {
        return this.f726c.f732f > System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a() {
        a aVar = this.f726c;
        if (c()) {
            return aVar.f727a;
        }
        Location b2 = b();
        if (b2 != null) {
            a(b2);
            return aVar.f727a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i = Calendar.getInstance().get(11);
        return i < 6 || i >= 22;
    }

    private Location a(String str) {
        try {
            if (this.f725b.isProviderEnabled(str)) {
                return this.f725b.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e2) {
            Log.d("TwilightManager", "Failed to get last known location", e2);
            return null;
        }
    }

    private void a(Location location) {
        long j;
        a aVar = this.f726c;
        long currentTimeMillis = System.currentTimeMillis();
        l a2 = l.a();
        a2.a(currentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j2 = a2.f720a;
        a2.a(currentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = a2.f722c == 1;
        long j3 = a2.f721b;
        long j4 = a2.f720a;
        boolean z2 = z;
        a2.a(86400000 + currentTimeMillis, location.getLatitude(), location.getLongitude());
        long j5 = a2.f721b;
        if (j3 == -1 || j4 == -1) {
            j = 43200000 + currentTimeMillis;
        } else {
            j = (currentTimeMillis > j4 ? 0 + j5 : currentTimeMillis > j3 ? 0 + j4 : 0 + j3) + 60000;
        }
        aVar.f727a = z2;
        aVar.f728b = j2;
        aVar.f729c = j3;
        aVar.f730d = j4;
        aVar.f731e = j5;
        aVar.f732f = j;
    }
}
