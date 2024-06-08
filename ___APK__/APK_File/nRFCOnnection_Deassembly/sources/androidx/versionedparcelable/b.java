package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
class b extends a {

    /* renamed from: d, reason: collision with root package name */
    private final SparseIntArray f1991d;

    /* renamed from: e, reason: collision with root package name */
    private final Parcel f1992e;

    /* renamed from: f, reason: collision with root package name */
    private final int f1993f;
    private final int g;
    private final String h;
    private int i;
    private int j;
    private int k;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new a.d.a(), new a.d.a(), new a.d.a());
    }

    @Override // androidx.versionedparcelable.a
    public boolean a(int i) {
        while (this.j < this.g) {
            int i2 = this.k;
            if (i2 == i) {
                return true;
            }
            if (String.valueOf(i2).compareTo(String.valueOf(i)) > 0) {
                return false;
            }
            this.f1992e.setDataPosition(this.j);
            int readInt = this.f1992e.readInt();
            this.k = this.f1992e.readInt();
            this.j += readInt;
        }
        return this.k == i;
    }

    @Override // androidx.versionedparcelable.a
    public void b(int i) {
        a();
        this.i = i;
        this.f1991d.put(i, this.f1992e.dataPosition());
        c(0);
        c(i);
    }

    @Override // androidx.versionedparcelable.a
    public void c(int i) {
        this.f1992e.writeInt(i);
    }

    @Override // androidx.versionedparcelable.a
    public boolean d() {
        return this.f1992e.readInt() != 0;
    }

    @Override // androidx.versionedparcelable.a
    public byte[] e() {
        int readInt = this.f1992e.readInt();
        if (readInt < 0) {
            return null;
        }
        byte[] bArr = new byte[readInt];
        this.f1992e.readByteArray(bArr);
        return bArr;
    }

    @Override // androidx.versionedparcelable.a
    protected CharSequence f() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.f1992e);
    }

    @Override // androidx.versionedparcelable.a
    public int g() {
        return this.f1992e.readInt();
    }

    @Override // androidx.versionedparcelable.a
    public <T extends Parcelable> T h() {
        return (T) this.f1992e.readParcelable(b.class.getClassLoader());
    }

    @Override // androidx.versionedparcelable.a
    public String i() {
        return this.f1992e.readString();
    }

    private b(Parcel parcel, int i, int i2, String str, a.d.a<String, Method> aVar, a.d.a<String, Method> aVar2, a.d.a<String, Class> aVar3) {
        super(aVar, aVar2, aVar3);
        this.f1991d = new SparseIntArray();
        this.i = -1;
        this.j = 0;
        this.k = -1;
        this.f1992e = parcel;
        this.f1993f = i;
        this.g = i2;
        this.j = this.f1993f;
        this.h = str;
    }

    @Override // androidx.versionedparcelable.a
    protected a b() {
        Parcel parcel = this.f1992e;
        int dataPosition = parcel.dataPosition();
        int i = this.j;
        if (i == this.f1993f) {
            i = this.g;
        }
        return new b(parcel, dataPosition, i, this.h + "  ", this.f1988a, this.f1989b, this.f1990c);
    }

    @Override // androidx.versionedparcelable.a
    public void a() {
        int i = this.i;
        if (i >= 0) {
            int i2 = this.f1991d.get(i);
            int dataPosition = this.f1992e.dataPosition();
            this.f1992e.setDataPosition(i2);
            this.f1992e.writeInt(dataPosition - i2);
            this.f1992e.setDataPosition(dataPosition);
        }
    }

    @Override // androidx.versionedparcelable.a
    public void a(byte[] bArr) {
        if (bArr != null) {
            this.f1992e.writeInt(bArr.length);
            this.f1992e.writeByteArray(bArr);
        } else {
            this.f1992e.writeInt(-1);
        }
    }

    @Override // androidx.versionedparcelable.a
    public void a(String str) {
        this.f1992e.writeString(str);
    }

    @Override // androidx.versionedparcelable.a
    public void a(Parcelable parcelable) {
        this.f1992e.writeParcelable(parcelable, 0);
    }

    @Override // androidx.versionedparcelable.a
    public void a(boolean z) {
        this.f1992e.writeInt(z ? 1 : 0);
    }

    @Override // androidx.versionedparcelable.a
    protected void a(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.f1992e, 0);
    }
}
