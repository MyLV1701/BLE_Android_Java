package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: classes.dex */
public class g {

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        final Bundle f1211a;

        /* renamed from: b, reason: collision with root package name */
        private IconCompat f1212b;

        /* renamed from: c, reason: collision with root package name */
        private final k[] f1213c;

        /* renamed from: d, reason: collision with root package name */
        private final k[] f1214d;

        /* renamed from: e, reason: collision with root package name */
        private boolean f1215e;

        /* renamed from: f, reason: collision with root package name */
        boolean f1216f;
        private final int g;
        private final boolean h;

        @Deprecated
        public int i;
        public CharSequence j;
        public PendingIntent k;

        /* renamed from: androidx.core.app.g$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0049a {

            /* renamed from: a, reason: collision with root package name */
            private final IconCompat f1217a;

            /* renamed from: b, reason: collision with root package name */
            private final CharSequence f1218b;

            /* renamed from: c, reason: collision with root package name */
            private final PendingIntent f1219c;

            /* renamed from: d, reason: collision with root package name */
            private boolean f1220d;

            /* renamed from: e, reason: collision with root package name */
            private final Bundle f1221e;

            /* renamed from: f, reason: collision with root package name */
            private ArrayList<k> f1222f;
            private int g;
            private boolean h;
            private boolean i;

            public C0049a(int i, CharSequence charSequence, PendingIntent pendingIntent) {
                this(i != 0 ? IconCompat.a(null, "", i) : null, charSequence, pendingIntent, new Bundle(), null, true, 0, true, false);
            }

            private void b() {
                if (this.i && this.f1219c == null) {
                    throw new NullPointerException("Contextual Actions must contain a valid PendingIntent");
                }
            }

            public a a() {
                b();
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList<k> arrayList3 = this.f1222f;
                if (arrayList3 != null) {
                    Iterator<k> it = arrayList3.iterator();
                    while (it.hasNext()) {
                        k next = it.next();
                        if (next.h()) {
                            arrayList.add(next);
                        } else {
                            arrayList2.add(next);
                        }
                    }
                }
                k[] kVarArr = arrayList.isEmpty() ? null : (k[]) arrayList.toArray(new k[arrayList.size()]);
                return new a(this.f1217a, this.f1218b, this.f1219c, this.f1221e, arrayList2.isEmpty() ? null : (k[]) arrayList2.toArray(new k[arrayList2.size()]), kVarArr, this.f1220d, this.g, this.h, this.i);
            }

            private C0049a(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, k[] kVarArr, boolean z, int i, boolean z2, boolean z3) {
                this.f1220d = true;
                this.h = true;
                this.f1217a = iconCompat;
                this.f1218b = d.f(charSequence);
                this.f1219c = pendingIntent;
                this.f1221e = bundle;
                this.f1222f = kVarArr == null ? null : new ArrayList<>(Arrays.asList(kVarArr));
                this.f1220d = z;
                this.g = i;
                this.h = z2;
                this.i = z3;
            }
        }

        public a(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this(i != 0 ? IconCompat.a(null, "", i) : null, charSequence, pendingIntent);
        }

        public PendingIntent a() {
            return this.k;
        }

        public boolean b() {
            return this.f1215e;
        }

        public k[] c() {
            return this.f1214d;
        }

        public Bundle d() {
            return this.f1211a;
        }

        @Deprecated
        public int e() {
            return this.i;
        }

        public IconCompat f() {
            int i;
            if (this.f1212b == null && (i = this.i) != 0) {
                this.f1212b = IconCompat.a(null, "", i);
            }
            return this.f1212b;
        }

        public k[] g() {
            return this.f1213c;
        }

        public int h() {
            return this.g;
        }

        public boolean i() {
            return this.f1216f;
        }

        public CharSequence j() {
            return this.j;
        }

        public boolean k() {
            return this.h;
        }

        public a(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent) {
            this(iconCompat, charSequence, pendingIntent, new Bundle(), null, null, true, 0, true, false);
        }

        a(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, k[] kVarArr, k[] kVarArr2, boolean z, int i, boolean z2, boolean z3) {
            this.f1216f = true;
            this.f1212b = iconCompat;
            if (iconCompat != null && iconCompat.c() == 2) {
                this.i = iconCompat.a();
            }
            this.j = d.f(charSequence);
            this.k = pendingIntent;
            this.f1211a = bundle == null ? new Bundle() : bundle;
            this.f1213c = kVarArr;
            this.f1214d = kVarArr2;
            this.f1215e = z;
            this.g = i;
            this.f1216f = z2;
            this.h = z3;
        }
    }

    /* loaded from: classes.dex */
    public static class b extends e {

        /* renamed from: e, reason: collision with root package name */
        private CharSequence f1223e;

        public b a(CharSequence charSequence) {
            this.f1223e = d.f(charSequence);
            return this;
        }

        @Override // androidx.core.app.g.e
        public void a(f fVar) {
            if (Build.VERSION.SDK_INT >= 16) {
                Notification.BigTextStyle bigText = new Notification.BigTextStyle(fVar.a()).setBigContentTitle(this.f1231b).bigText(this.f1223e);
                if (this.f1233d) {
                    bigText.setSummaryText(this.f1232c);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class c {
        public static Notification.BubbleMetadata a(c cVar) {
            if (cVar == null) {
                return null;
            }
            new Notification.BubbleMetadata.Builder();
            cVar.a();
            throw null;
        }

        public boolean a() {
            throw null;
        }
    }

    /* loaded from: classes.dex */
    public static class d {
        String A;
        Bundle B;
        Notification E;
        RemoteViews F;
        RemoteViews G;
        RemoteViews H;
        String I;
        String K;
        long L;
        boolean N;
        c O;

        @Deprecated
        public ArrayList<String> Q;

        /* renamed from: a, reason: collision with root package name */
        public Context f1224a;

        /* renamed from: d, reason: collision with root package name */
        CharSequence f1227d;

        /* renamed from: e, reason: collision with root package name */
        CharSequence f1228e;

        /* renamed from: f, reason: collision with root package name */
        PendingIntent f1229f;
        PendingIntent g;
        RemoteViews h;
        Bitmap i;
        CharSequence j;
        int k;
        int l;
        boolean n;
        e o;
        CharSequence p;
        CharSequence[] q;
        int r;
        int s;
        boolean t;
        String u;
        boolean v;
        String w;
        boolean y;
        boolean z;

        /* renamed from: b, reason: collision with root package name */
        public ArrayList<a> f1225b = new ArrayList<>();

        /* renamed from: c, reason: collision with root package name */
        ArrayList<a> f1226c = new ArrayList<>();
        boolean m = true;
        boolean x = false;
        int C = 0;
        int D = 0;
        int J = 0;
        int M = 0;
        Notification P = new Notification();

        public d(Context context, String str) {
            this.f1224a = context;
            this.I = str;
            this.P.when = System.currentTimeMillis();
            this.P.audioStreamType = -1;
            this.l = 0;
            this.Q = new ArrayList<>();
            this.N = true;
        }

        protected static CharSequence f(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
        }

        public d a(CharSequence charSequence) {
            this.j = f(charSequence);
            return this;
        }

        public d b(CharSequence charSequence) {
            this.f1228e = f(charSequence);
            return this;
        }

        public d c(CharSequence charSequence) {
            this.f1227d = f(charSequence);
            return this;
        }

        public d d(int i) {
            this.P.icon = i;
            return this;
        }

        public d e(boolean z) {
            this.m = z;
            return this;
        }

        public d a(int i, int i2, boolean z) {
            this.r = i;
            this.s = i2;
            this.t = z;
            return this;
        }

        public d b(boolean z) {
            this.x = z;
            return this;
        }

        public d c(boolean z) {
            a(2, z);
            return this;
        }

        public d d(CharSequence charSequence) {
            this.p = f(charSequence);
            return this;
        }

        public d e(CharSequence charSequence) {
            this.P.tickerText = f(charSequence);
            return this;
        }

        public d b(int i) {
            Notification notification = this.P;
            notification.defaults = i;
            if ((i & 4) != 0) {
                notification.flags |= 1;
            }
            return this;
        }

        public d c(int i) {
            this.l = i;
            return this;
        }

        public d d(boolean z) {
            a(8, z);
            return this;
        }

        public d a(PendingIntent pendingIntent) {
            this.f1229f = pendingIntent;
            return this;
        }

        public Bundle b() {
            if (this.B == null) {
                this.B = new Bundle();
            }
            return this.B;
        }

        public d a(boolean z) {
            a(16, z);
            return this;
        }

        public d a(String str) {
            this.A = str;
            return this;
        }

        private void a(int i, boolean z) {
            if (z) {
                Notification notification = this.P;
                notification.flags = i | notification.flags;
            } else {
                Notification notification2 = this.P;
                notification2.flags = (i ^ (-1)) & notification2.flags;
            }
        }

        public d a(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this.f1225b.add(new a(i, charSequence, pendingIntent));
            return this;
        }

        public d a(a aVar) {
            this.f1225b.add(aVar);
            return this;
        }

        public d a(e eVar) {
            if (this.o != eVar) {
                this.o = eVar;
                e eVar2 = this.o;
                if (eVar2 != null) {
                    eVar2.a(this);
                }
            }
            return this;
        }

        public d a(int i) {
            this.C = i;
            return this;
        }

        public Notification a() {
            return new h(this).b();
        }
    }

    /* loaded from: classes.dex */
    public static abstract class e {

        /* renamed from: a, reason: collision with root package name */
        protected d f1230a;

        /* renamed from: b, reason: collision with root package name */
        CharSequence f1231b;

        /* renamed from: c, reason: collision with root package name */
        CharSequence f1232c;

        /* renamed from: d, reason: collision with root package name */
        boolean f1233d = false;

        public void a(Bundle bundle) {
        }

        public abstract void a(f fVar);

        public void a(d dVar) {
            if (this.f1230a != dVar) {
                this.f1230a = dVar;
                d dVar2 = this.f1230a;
                if (dVar2 != null) {
                    dVar2.a(this);
                }
            }
        }

        public RemoteViews b(f fVar) {
            return null;
        }

        public RemoteViews c(f fVar) {
            return null;
        }

        public RemoteViews d(f fVar) {
            return null;
        }
    }

    public static Bundle a(Notification notification) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 19) {
            return notification.extras;
        }
        if (i >= 16) {
            return i.a(notification);
        }
        return null;
    }
}
