package no.nordicsemi.android.support.v18.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* loaded from: classes.dex */
public final class k implements Parcelable {
    public static final Parcelable.Creator<k> CREATOR;

    /* renamed from: b, reason: collision with root package name */
    private final String f3948b;

    /* renamed from: c, reason: collision with root package name */
    private final String f3949c;

    /* renamed from: d, reason: collision with root package name */
    private final ParcelUuid f3950d;

    /* renamed from: e, reason: collision with root package name */
    private final ParcelUuid f3951e;

    /* renamed from: f, reason: collision with root package name */
    private final ParcelUuid f3952f;
    private final byte[] g;
    private final byte[] h;
    private final int i;
    private final byte[] j;
    private final byte[] k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a implements Parcelable.Creator<k> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        public k createFromParcel(Parcel parcel) {
            b bVar = new b();
            if (parcel.readInt() == 1) {
                bVar.b(parcel.readString());
            }
            if (parcel.readInt() == 1) {
                bVar.a(parcel.readString());
            }
            if (parcel.readInt() == 1) {
                ParcelUuid parcelUuid = (ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader());
                bVar.a(parcelUuid);
                if (parcel.readInt() == 1) {
                    bVar.a(parcelUuid, (ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader()));
                }
            }
            if (parcel.readInt() == 1) {
                ParcelUuid parcelUuid2 = (ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader());
                if (parcel.readInt() == 1) {
                    byte[] bArr = new byte[parcel.readInt()];
                    parcel.readByteArray(bArr);
                    if (parcel.readInt() == 0) {
                        bVar.a(parcelUuid2, bArr);
                    } else {
                        byte[] bArr2 = new byte[parcel.readInt()];
                        parcel.readByteArray(bArr2);
                        bVar.a(parcelUuid2, bArr, bArr2);
                    }
                }
            }
            int readInt = parcel.readInt();
            if (parcel.readInt() == 1) {
                byte[] bArr3 = new byte[parcel.readInt()];
                parcel.readByteArray(bArr3);
                if (parcel.readInt() == 0) {
                    bVar.a(readInt, bArr3);
                } else {
                    byte[] bArr4 = new byte[parcel.readInt()];
                    parcel.readByteArray(bArr4);
                    bVar.a(readInt, bArr3, bArr4);
                }
            }
            return bVar.a();
        }

        @Override // android.os.Parcelable.Creator
        public k[] newArray(int i) {
            return new k[i];
        }
    }

    static {
        new b().a();
        CREATOR = new a();
    }

    /* synthetic */ k(String str, String str2, ParcelUuid parcelUuid, ParcelUuid parcelUuid2, ParcelUuid parcelUuid3, byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4, a aVar) {
        this(str, str2, parcelUuid, parcelUuid2, parcelUuid3, bArr, bArr2, i, bArr3, bArr4);
    }

    public boolean a(m mVar) {
        if (mVar == null) {
            return false;
        }
        BluetoothDevice d2 = mVar.d();
        String str = this.f3949c;
        if (str != null && !str.equals(d2.getAddress())) {
            return false;
        }
        l f2 = mVar.f();
        if (f2 == null && (this.f3948b != null || this.f3950d != null || this.j != null || this.g != null)) {
            return false;
        }
        String str2 = this.f3948b;
        if (str2 != null && !str2.equals(f2.b())) {
            return false;
        }
        ParcelUuid parcelUuid = this.f3950d;
        if (parcelUuid != null && !a(parcelUuid, this.f3951e, f2.c())) {
            return false;
        }
        ParcelUuid parcelUuid2 = this.f3952f;
        if (parcelUuid2 != null && f2 != null && !a(this.g, this.h, f2.a(parcelUuid2))) {
            return false;
        }
        int i = this.i;
        return i < 0 || f2 == null || a(this.j, this.k, f2.a(i));
    }

    public String d() {
        return this.f3949c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String e() {
        return this.f3948b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || k.class != obj.getClass()) {
            return false;
        }
        k kVar = (k) obj;
        return h.b(this.f3948b, kVar.f3948b) && h.b(this.f3949c, kVar.f3949c) && this.i == kVar.i && h.a(this.j, kVar.j) && h.a(this.k, kVar.k) && h.b(this.f3952f, kVar.f3952f) && h.a(this.g, kVar.g) && h.a(this.h, kVar.h) && h.b(this.f3950d, kVar.f3950d) && h.b(this.f3951e, kVar.f3951e);
    }

    public byte[] f() {
        return this.j;
    }

    public byte[] g() {
        return this.k;
    }

    public int h() {
        return this.i;
    }

    public int hashCode() {
        return h.a(this.f3948b, this.f3949c, Integer.valueOf(this.i), Integer.valueOf(Arrays.hashCode(this.j)), Integer.valueOf(Arrays.hashCode(this.k)), this.f3952f, Integer.valueOf(Arrays.hashCode(this.g)), Integer.valueOf(Arrays.hashCode(this.h)), this.f3950d, this.f3951e);
    }

    public byte[] i() {
        return this.g;
    }

    public byte[] j() {
        return this.h;
    }

    public ParcelUuid k() {
        return this.f3952f;
    }

    public ParcelUuid l() {
        return this.f3950d;
    }

    public ParcelUuid m() {
        return this.f3951e;
    }

    public String toString() {
        return "BluetoothLeScanFilter [deviceName=" + this.f3948b + ", deviceAddress=" + this.f3949c + ", mUuid=" + this.f3950d + ", uuidMask=" + this.f3951e + ", serviceDataUuid=" + h.a(this.f3952f) + ", serviceData=" + Arrays.toString(this.g) + ", serviceDataMask=" + Arrays.toString(this.h) + ", manufacturerId=" + this.i + ", manufacturerData=" + Arrays.toString(this.j) + ", manufacturerDataMask=" + Arrays.toString(this.k) + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3948b == null ? 0 : 1);
        String str = this.f3948b;
        if (str != null) {
            parcel.writeString(str);
        }
        parcel.writeInt(this.f3949c == null ? 0 : 1);
        String str2 = this.f3949c;
        if (str2 != null) {
            parcel.writeString(str2);
        }
        parcel.writeInt(this.f3950d == null ? 0 : 1);
        ParcelUuid parcelUuid = this.f3950d;
        if (parcelUuid != null) {
            parcel.writeParcelable(parcelUuid, i);
            parcel.writeInt(this.f3951e == null ? 0 : 1);
            ParcelUuid parcelUuid2 = this.f3951e;
            if (parcelUuid2 != null) {
                parcel.writeParcelable(parcelUuid2, i);
            }
        }
        parcel.writeInt(this.f3952f == null ? 0 : 1);
        ParcelUuid parcelUuid3 = this.f3952f;
        if (parcelUuid3 != null) {
            parcel.writeParcelable(parcelUuid3, i);
            parcel.writeInt(this.g == null ? 0 : 1);
            byte[] bArr = this.g;
            if (bArr != null) {
                parcel.writeInt(bArr.length);
                parcel.writeByteArray(this.g);
                parcel.writeInt(this.h == null ? 0 : 1);
                byte[] bArr2 = this.h;
                if (bArr2 != null) {
                    parcel.writeInt(bArr2.length);
                    parcel.writeByteArray(this.h);
                }
            }
        }
        parcel.writeInt(this.i);
        parcel.writeInt(this.j == null ? 0 : 1);
        byte[] bArr3 = this.j;
        if (bArr3 != null) {
            parcel.writeInt(bArr3.length);
            parcel.writeByteArray(this.j);
            parcel.writeInt(this.k != null ? 1 : 0);
            byte[] bArr4 = this.k;
            if (bArr4 != null) {
                parcel.writeInt(bArr4.length);
                parcel.writeByteArray(this.k);
            }
        }
    }

    private k(String str, String str2, ParcelUuid parcelUuid, ParcelUuid parcelUuid2, ParcelUuid parcelUuid3, byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        this.f3948b = str;
        this.f3950d = parcelUuid;
        this.f3951e = parcelUuid2;
        this.f3949c = str2;
        this.f3952f = parcelUuid3;
        this.g = bArr;
        this.h = bArr2;
        this.i = i;
        this.j = bArr3;
        this.k = bArr4;
    }

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private String f3953a;

        /* renamed from: b, reason: collision with root package name */
        private String f3954b;

        /* renamed from: c, reason: collision with root package name */
        private ParcelUuid f3955c;

        /* renamed from: d, reason: collision with root package name */
        private ParcelUuid f3956d;

        /* renamed from: e, reason: collision with root package name */
        private ParcelUuid f3957e;

        /* renamed from: f, reason: collision with root package name */
        private byte[] f3958f;
        private byte[] g;
        private int h = -1;
        private byte[] i;
        private byte[] j;

        public b a(String str) {
            if (str != null && !BluetoothAdapter.checkBluetoothAddress(str)) {
                throw new IllegalArgumentException("invalid device address " + str);
            }
            this.f3954b = str;
            return this;
        }

        public b b(String str) {
            this.f3953a = str;
            return this;
        }

        public b a(ParcelUuid parcelUuid) {
            this.f3955c = parcelUuid;
            this.f3956d = null;
            return this;
        }

        public b a(ParcelUuid parcelUuid, ParcelUuid parcelUuid2) {
            if (parcelUuid2 != null && parcelUuid == null) {
                throw new IllegalArgumentException("uuid is null while uuidMask is not null!");
            }
            this.f3955c = parcelUuid;
            this.f3956d = parcelUuid2;
            return this;
        }

        public b a(ParcelUuid parcelUuid, byte[] bArr) {
            if (parcelUuid != null) {
                this.f3957e = parcelUuid;
                this.f3958f = bArr;
                this.g = null;
                return this;
            }
            throw new IllegalArgumentException("serviceDataUuid is null!");
        }

        public b a(ParcelUuid parcelUuid, byte[] bArr, byte[] bArr2) {
            if (parcelUuid != null) {
                if (bArr2 != null) {
                    if (bArr != null) {
                        if (bArr.length != bArr2.length) {
                            throw new IllegalArgumentException("size mismatch for service data and service data mask");
                        }
                    } else {
                        throw new IllegalArgumentException("serviceData is null while serviceDataMask is not null");
                    }
                }
                this.f3957e = parcelUuid;
                this.f3958f = bArr;
                this.g = bArr2;
                return this;
            }
            throw new IllegalArgumentException("serviceDataUuid is null");
        }

        public b a(int i, byte[] bArr) {
            if (bArr != null && i < 0) {
                throw new IllegalArgumentException("invalid manufacture id");
            }
            this.h = i;
            this.i = bArr;
            this.j = null;
            return this;
        }

        public b a(int i, byte[] bArr, byte[] bArr2) {
            if (bArr != null && i < 0) {
                throw new IllegalArgumentException("invalid manufacture id");
            }
            if (bArr2 != null) {
                if (bArr != null) {
                    if (bArr.length != bArr2.length) {
                        throw new IllegalArgumentException("size mismatch for manufacturerData and manufacturerDataMask");
                    }
                } else {
                    throw new IllegalArgumentException("manufacturerData is null while manufacturerDataMask is not null");
                }
            }
            this.h = i;
            this.i = bArr;
            this.j = bArr2;
            return this;
        }

        public k a() {
            return new k(this.f3953a, this.f3954b, this.f3955c, this.f3956d, this.f3957e, this.f3958f, this.g, this.h, this.i, this.j, null);
        }
    }

    private static boolean a(ParcelUuid parcelUuid, ParcelUuid parcelUuid2, List<ParcelUuid> list) {
        if (parcelUuid == null) {
            return true;
        }
        if (list == null) {
            return false;
        }
        Iterator<ParcelUuid> it = list.iterator();
        while (it.hasNext()) {
            if (a(parcelUuid.getUuid(), parcelUuid2 == null ? null : parcelUuid2.getUuid(), it.next().getUuid())) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(UUID uuid, UUID uuid2, UUID uuid3) {
        if (uuid2 == null) {
            return uuid.equals(uuid3);
        }
        if ((uuid.getLeastSignificantBits() & uuid2.getLeastSignificantBits()) != (uuid3.getLeastSignificantBits() & uuid2.getLeastSignificantBits())) {
            return false;
        }
        return (uuid.getMostSignificantBits() & uuid2.getMostSignificantBits()) == (uuid2.getMostSignificantBits() & uuid3.getMostSignificantBits());
    }

    private boolean a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null) {
            return bArr3 != null;
        }
        if (bArr3 == null || bArr3.length < bArr.length) {
            return false;
        }
        if (bArr2 == null) {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr3[i] != bArr[i]) {
                    return false;
                }
            }
            return true;
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            if ((bArr2[i2] & bArr3[i2]) != (bArr2[i2] & bArr[i2])) {
                return false;
            }
        }
        return true;
    }
}
