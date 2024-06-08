package no.nordicsemi.android.support.v18.scanner;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class n implements Parcelable {
    public static final Parcelable.Creator<n> CREATOR = new a();

    /* renamed from: b, reason: collision with root package name */
    private final long f3970b;

    /* renamed from: c, reason: collision with root package name */
    private final long f3971c;

    /* renamed from: d, reason: collision with root package name */
    private int f3972d;

    /* renamed from: e, reason: collision with root package name */
    private int f3973e;

    /* renamed from: f, reason: collision with root package name */
    private long f3974f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;
    private boolean k;
    private long l;
    private long m;
    private boolean n;
    private int o;

    /* loaded from: classes.dex */
    static class a implements Parcelable.Creator<n> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        public n createFromParcel(Parcel parcel) {
            return new n(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        public n[] newArray(int i) {
            return new n[i];
        }
    }

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private int f3975a = 0;

        /* renamed from: b, reason: collision with root package name */
        private int f3976b = 1;

        /* renamed from: c, reason: collision with root package name */
        private long f3977c = 0;

        /* renamed from: d, reason: collision with root package name */
        private int f3978d = 1;

        /* renamed from: e, reason: collision with root package name */
        private int f3979e = 3;

        /* renamed from: f, reason: collision with root package name */
        private boolean f3980f = true;
        private int g = 255;
        private boolean h = true;
        private boolean i = true;
        private boolean j = true;
        private long k = 10000;
        private long l = 10000;
        private long m = 0;
        private long n = 0;

        private boolean f(int i) {
            return i == 1 || i == 2 || i == 4 || i == 6;
        }

        public b a(int i) {
            if (f(i)) {
                this.f3976b = i;
                return this;
            }
            throw new IllegalArgumentException("invalid callback type - " + i);
        }

        public b b(int i) {
            if (i >= 1 && i <= 2) {
                this.f3978d = i;
                return this;
            }
            throw new IllegalArgumentException("invalid matchMode " + i);
        }

        public b c(int i) {
            if (i >= 1 && i <= 3) {
                this.f3979e = i;
                return this;
            }
            throw new IllegalArgumentException("invalid numOfMatches " + i);
        }

        public b d(int i) {
            this.g = i;
            return this;
        }

        public b e(int i) {
            if (i >= -1 && i <= 2) {
                this.f3975a = i;
                return this;
            }
            throw new IllegalArgumentException("invalid scan mode " + i);
        }

        public b d(boolean z) {
            this.h = z;
            return this;
        }

        public b b(boolean z) {
            this.i = z;
            return this;
        }

        public b c(boolean z) {
            this.j = z;
            return this;
        }

        private void b() {
            int i = this.f3975a;
            if (i == 1) {
                this.n = 2000L;
                this.m = 3000L;
            } else if (i != 2) {
                this.n = 500L;
                this.m = 4500L;
            } else {
                this.n = 0L;
                this.m = 0L;
            }
        }

        public b a(long j) {
            if (j >= 0) {
                this.f3977c = j;
                return this;
            }
            throw new IllegalArgumentException("reportDelay must be > 0");
        }

        public b a(boolean z) {
            this.f3980f = z;
            return this;
        }

        public b a(long j, long j2) {
            if (j > 0 && j2 > 0) {
                this.k = j;
                this.l = j2;
                return this;
            }
            throw new IllegalArgumentException("maxDeviceAgeMillis and taskIntervalMillis must be > 0");
        }

        public n a() {
            if (this.m == 0 && this.n == 0) {
                b();
            }
            return new n(this.f3975a, this.f3976b, this.f3977c, this.f3978d, this.f3979e, this.f3980f, this.g, this.h, this.i, this.j, this.k, this.l, this.n, this.m, null);
        }
    }

    /* synthetic */ n(int i, int i2, long j, int i3, int i4, boolean z, int i5, boolean z2, boolean z3, boolean z4, long j2, long j3, long j4, long j5, a aVar) {
        this(i, i2, j, i3, i4, z, i5, z2, z3, z4, j2, j3, j4, j5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d() {
        this.k = false;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int e() {
        return this.f3973e;
    }

    public boolean f() {
        return this.n;
    }

    public long g() {
        return this.l;
    }

    public long h() {
        return this.m;
    }

    public int i() {
        return this.g;
    }

    public int j() {
        return this.h;
    }

    public int k() {
        return this.o;
    }

    public long l() {
        return this.f3971c;
    }

    public long m() {
        return this.f3970b;
    }

    public long n() {
        return this.f3974f;
    }

    public int o() {
        return this.f3972d;
    }

    public boolean p() {
        return this.j;
    }

    public boolean q() {
        return this.k;
    }

    public boolean r() {
        return this.i;
    }

    public boolean s() {
        return this.f3971c > 0 && this.f3970b > 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3972d);
        parcel.writeInt(this.f3973e);
        parcel.writeLong(this.f3974f);
        parcel.writeInt(this.g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.n ? 1 : 0);
        parcel.writeInt(this.o);
        parcel.writeInt(this.i ? 1 : 0);
        parcel.writeInt(this.j ? 1 : 0);
        parcel.writeLong(this.f3970b);
        parcel.writeLong(this.f3971c);
    }

    /* synthetic */ n(Parcel parcel, a aVar) {
        this(parcel);
    }

    private n(int i, int i2, long j, int i3, int i4, boolean z, int i5, boolean z2, boolean z3, boolean z4, long j2, long j3, long j4, long j5) {
        this.f3972d = i;
        this.f3973e = i2;
        this.f3974f = j;
        this.h = i4;
        this.g = i3;
        this.n = z;
        this.o = i5;
        this.i = z2;
        this.j = z3;
        this.k = z4;
        this.l = 1000000 * j2;
        this.m = j3;
        this.f3970b = j4;
        this.f3971c = j5;
    }

    private n(Parcel parcel) {
        this.f3972d = parcel.readInt();
        this.f3973e = parcel.readInt();
        this.f3974f = parcel.readLong();
        this.g = parcel.readInt();
        this.h = parcel.readInt();
        this.n = parcel.readInt() != 0;
        this.o = parcel.readInt();
        this.i = parcel.readInt() == 1;
        this.j = parcel.readInt() == 1;
        this.f3970b = parcel.readLong();
        this.f3971c = parcel.readLong();
    }
}
