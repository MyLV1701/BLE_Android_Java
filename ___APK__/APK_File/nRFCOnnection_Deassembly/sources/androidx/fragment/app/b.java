package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.t;
import androidx.lifecycle.g;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a();

    /* renamed from: b, reason: collision with root package name */
    final int[] f1364b;

    /* renamed from: c, reason: collision with root package name */
    final ArrayList<String> f1365c;

    /* renamed from: d, reason: collision with root package name */
    final int[] f1366d;

    /* renamed from: e, reason: collision with root package name */
    final int[] f1367e;

    /* renamed from: f, reason: collision with root package name */
    final int f1368f;
    final String g;
    final int h;
    final int i;
    final CharSequence j;
    final int k;
    final CharSequence l;
    final ArrayList<String> m;
    final ArrayList<String> n;
    final boolean o;

    /* loaded from: classes.dex */
    static class a implements Parcelable.Creator<b> {
        a() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public b[] newArray(int i) {
            return new b[i];
        }
    }

    public b(androidx.fragment.app.a aVar) {
        int size = aVar.f1439a.size();
        this.f1364b = new int[size * 5];
        if (aVar.g) {
            this.f1365c = new ArrayList<>(size);
            this.f1366d = new int[size];
            this.f1367e = new int[size];
            int i = 0;
            int i2 = 0;
            while (i < size) {
                t.a aVar2 = aVar.f1439a.get(i);
                int i3 = i2 + 1;
                this.f1364b[i2] = aVar2.f1445a;
                ArrayList<String> arrayList = this.f1365c;
                Fragment fragment = aVar2.f1446b;
                arrayList.add(fragment != null ? fragment.mWho : null);
                int[] iArr = this.f1364b;
                int i4 = i3 + 1;
                iArr[i3] = aVar2.f1447c;
                int i5 = i4 + 1;
                iArr[i4] = aVar2.f1448d;
                int i6 = i5 + 1;
                iArr[i5] = aVar2.f1449e;
                iArr[i6] = aVar2.f1450f;
                this.f1366d[i] = aVar2.g.ordinal();
                this.f1367e[i] = aVar2.h.ordinal();
                i++;
                i2 = i6 + 1;
            }
            this.f1368f = aVar.f1444f;
            this.g = aVar.i;
            this.h = aVar.t;
            this.i = aVar.j;
            this.j = aVar.k;
            this.k = aVar.l;
            this.l = aVar.m;
            this.m = aVar.n;
            this.n = aVar.o;
            this.o = aVar.p;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }

    public androidx.fragment.app.a a(l lVar) {
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(lVar);
        int i = 0;
        int i2 = 0;
        while (i < this.f1364b.length) {
            t.a aVar2 = new t.a();
            int i3 = i + 1;
            aVar2.f1445a = this.f1364b[i];
            if (l.d(2)) {
                Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i2 + " base fragment #" + this.f1364b[i3]);
            }
            String str = this.f1365c.get(i2);
            if (str != null) {
                aVar2.f1446b = lVar.a(str);
            } else {
                aVar2.f1446b = null;
            }
            aVar2.g = g.b.values()[this.f1366d[i2]];
            aVar2.h = g.b.values()[this.f1367e[i2]];
            int[] iArr = this.f1364b;
            int i4 = i3 + 1;
            aVar2.f1447c = iArr[i3];
            int i5 = i4 + 1;
            aVar2.f1448d = iArr[i4];
            int i6 = i5 + 1;
            aVar2.f1449e = iArr[i5];
            aVar2.f1450f = iArr[i6];
            aVar.f1440b = aVar2.f1447c;
            aVar.f1441c = aVar2.f1448d;
            aVar.f1442d = aVar2.f1449e;
            aVar.f1443e = aVar2.f1450f;
            aVar.a(aVar2);
            i2++;
            i = i6 + 1;
        }
        aVar.f1444f = this.f1368f;
        aVar.i = this.g;
        aVar.t = this.h;
        aVar.g = true;
        aVar.j = this.i;
        aVar.k = this.j;
        aVar.l = this.k;
        aVar.m = this.l;
        aVar.n = this.m;
        aVar.o = this.n;
        aVar.p = this.o;
        aVar.a(1);
        return aVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.f1364b);
        parcel.writeStringList(this.f1365c);
        parcel.writeIntArray(this.f1366d);
        parcel.writeIntArray(this.f1367e);
        parcel.writeInt(this.f1368f);
        parcel.writeString(this.g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        TextUtils.writeToParcel(this.j, parcel, 0);
        parcel.writeInt(this.k);
        TextUtils.writeToParcel(this.l, parcel, 0);
        parcel.writeStringList(this.m);
        parcel.writeStringList(this.n);
        parcel.writeInt(this.o ? 1 : 0);
    }

    public b(Parcel parcel) {
        this.f1364b = parcel.createIntArray();
        this.f1365c = parcel.createStringArrayList();
        this.f1366d = parcel.createIntArray();
        this.f1367e = parcel.createIntArray();
        this.f1368f = parcel.readInt();
        this.g = parcel.readString();
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.j = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.k = parcel.readInt();
        this.l = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.m = parcel.createStringArrayList();
        this.n = parcel.createStringArrayList();
        this.o = parcel.readInt() != 0;
    }
}
