package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class n implements Parcelable {
    public static final Parcelable.Creator<n> CREATOR = new a();

    /* renamed from: b, reason: collision with root package name */
    ArrayList<q> f1422b;

    /* renamed from: c, reason: collision with root package name */
    ArrayList<String> f1423c;

    /* renamed from: d, reason: collision with root package name */
    b[] f1424d;

    /* renamed from: e, reason: collision with root package name */
    int f1425e;

    /* renamed from: f, reason: collision with root package name */
    String f1426f;

    /* loaded from: classes.dex */
    static class a implements Parcelable.Creator<n> {
        a() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public n createFromParcel(Parcel parcel) {
            return new n(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public n[] newArray(int i) {
            return new n[i];
        }
    }

    public n() {
        this.f1426f = null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f1422b);
        parcel.writeStringList(this.f1423c);
        parcel.writeTypedArray(this.f1424d, i);
        parcel.writeInt(this.f1425e);
        parcel.writeString(this.f1426f);
    }

    public n(Parcel parcel) {
        this.f1426f = null;
        this.f1422b = parcel.createTypedArrayList(q.CREATOR);
        this.f1423c = parcel.createStringArrayList();
        this.f1424d = (b[]) parcel.createTypedArray(b.CREATOR);
        this.f1425e = parcel.readInt();
        this.f1426f = parcel.readString();
    }
}
