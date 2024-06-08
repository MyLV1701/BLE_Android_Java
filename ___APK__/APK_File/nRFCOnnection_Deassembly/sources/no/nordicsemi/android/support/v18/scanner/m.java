package no.nordicsemi.android.support.v18.scanner;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class m implements Parcelable {
    public static final Parcelable.Creator<m> CREATOR = new a();

    /* renamed from: b, reason: collision with root package name */
    private BluetoothDevice f3965b;

    /* renamed from: c, reason: collision with root package name */
    private l f3966c;

    /* renamed from: d, reason: collision with root package name */
    private int f3967d;

    /* renamed from: e, reason: collision with root package name */
    private long f3968e;

    /* renamed from: f, reason: collision with root package name */
    private int f3969f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;

    /* loaded from: classes.dex */
    static class a implements Parcelable.Creator<m> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        public m createFromParcel(Parcel parcel) {
            return new m(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        public m[] newArray(int i) {
            return new m[i];
        }
    }

    /* synthetic */ m(Parcel parcel, a aVar) {
        this(parcel);
    }

    private void a(Parcel parcel) {
        this.f3965b = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(parcel);
        if (parcel.readInt() == 1) {
            this.f3966c = l.a(parcel.createByteArray());
        }
        this.f3967d = parcel.readInt();
        this.f3968e = parcel.readLong();
        this.f3969f = parcel.readInt();
        this.g = parcel.readInt();
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.j = parcel.readInt();
        this.k = parcel.readInt();
    }

    public BluetoothDevice d() {
        return this.f3965b;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int e() {
        return this.f3967d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || m.class != obj.getClass()) {
            return false;
        }
        m mVar = (m) obj;
        return h.b(this.f3965b, mVar.f3965b) && this.f3967d == mVar.f3967d && h.b(this.f3966c, mVar.f3966c) && this.f3968e == mVar.f3968e && this.f3969f == mVar.f3969f && this.g == mVar.g && this.h == mVar.h && this.i == mVar.i && this.j == mVar.j && this.k == mVar.k;
    }

    public l f() {
        return this.f3966c;
    }

    public long g() {
        return this.f3968e;
    }

    public int hashCode() {
        return h.a(this.f3965b, Integer.valueOf(this.f3967d), this.f3966c, Long.valueOf(this.f3968e), Integer.valueOf(this.f3969f), Integer.valueOf(this.g), Integer.valueOf(this.h), Integer.valueOf(this.i), Integer.valueOf(this.j), Integer.valueOf(this.k));
    }

    public String toString() {
        return "ScanResult{device=" + this.f3965b + ", scanRecord=" + h.a(this.f3966c) + ", rssi=" + this.f3967d + ", timestampNanos=" + this.f3968e + ", eventType=" + this.f3969f + ", primaryPhy=" + this.g + ", secondaryPhy=" + this.h + ", advertisingSid=" + this.i + ", txPower=" + this.j + ", periodicAdvertisingInterval=" + this.k + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        this.f3965b.writeToParcel(parcel, i);
        if (this.f3966c != null) {
            parcel.writeInt(1);
            parcel.writeByteArray(this.f3966c.a());
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.f3967d);
        parcel.writeLong(this.f3968e);
        parcel.writeInt(this.f3969f);
        parcel.writeInt(this.g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        parcel.writeInt(this.j);
        parcel.writeInt(this.k);
    }

    public m(BluetoothDevice bluetoothDevice, l lVar, int i, long j) {
        this.f3965b = bluetoothDevice;
        this.f3966c = lVar;
        this.f3967d = i;
        this.f3968e = j;
        this.f3969f = 17;
        this.g = 1;
        this.h = 0;
        this.i = 255;
        this.j = 127;
        this.k = 0;
    }

    public m(BluetoothDevice bluetoothDevice, int i, int i2, int i3, int i4, int i5, int i6, int i7, l lVar, long j) {
        this.f3965b = bluetoothDevice;
        this.f3969f = i;
        this.g = i2;
        this.h = i3;
        this.i = i4;
        this.j = i5;
        this.f3967d = i6;
        this.k = i7;
        this.f3966c = lVar;
        this.f3968e = j;
    }

    private m(Parcel parcel) {
        a(parcel);
    }
}
