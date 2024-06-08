package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class q implements Parcelable {
    public static final Parcelable.Creator<q> CREATOR = new a();

    /* renamed from: b, reason: collision with root package name */
    final String f1431b;

    /* renamed from: c, reason: collision with root package name */
    final String f1432c;

    /* renamed from: d, reason: collision with root package name */
    final boolean f1433d;

    /* renamed from: e, reason: collision with root package name */
    final int f1434e;

    /* renamed from: f, reason: collision with root package name */
    final int f1435f;
    final String g;
    final boolean h;
    final boolean i;
    final boolean j;
    final Bundle k;
    final boolean l;
    final int m;
    Bundle n;

    /* loaded from: classes.dex */
    static class a implements Parcelable.Creator<q> {
        a() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public q createFromParcel(Parcel parcel) {
            return new q(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public q[] newArray(int i) {
            return new q[i];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public q(Fragment fragment) {
        this.f1431b = fragment.getClass().getName();
        this.f1432c = fragment.mWho;
        this.f1433d = fragment.mFromLayout;
        this.f1434e = fragment.mFragmentId;
        this.f1435f = fragment.mContainerId;
        this.g = fragment.mTag;
        this.h = fragment.mRetainInstance;
        this.i = fragment.mRemoving;
        this.j = fragment.mDetached;
        this.k = fragment.mArguments;
        this.l = fragment.mHidden;
        this.m = fragment.mMaxState.ordinal();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f1431b);
        sb.append(" (");
        sb.append(this.f1432c);
        sb.append(")}:");
        if (this.f1433d) {
            sb.append(" fromLayout");
        }
        if (this.f1435f != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.f1435f));
        }
        String str = this.g;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.g);
        }
        if (this.h) {
            sb.append(" retainInstance");
        }
        if (this.i) {
            sb.append(" removing");
        }
        if (this.j) {
            sb.append(" detached");
        }
        if (this.l) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1431b);
        parcel.writeString(this.f1432c);
        parcel.writeInt(this.f1433d ? 1 : 0);
        parcel.writeInt(this.f1434e);
        parcel.writeInt(this.f1435f);
        parcel.writeString(this.g);
        parcel.writeInt(this.h ? 1 : 0);
        parcel.writeInt(this.i ? 1 : 0);
        parcel.writeInt(this.j ? 1 : 0);
        parcel.writeBundle(this.k);
        parcel.writeInt(this.l ? 1 : 0);
        parcel.writeBundle(this.n);
        parcel.writeInt(this.m);
    }

    q(Parcel parcel) {
        this.f1431b = parcel.readString();
        this.f1432c = parcel.readString();
        this.f1433d = parcel.readInt() != 0;
        this.f1434e = parcel.readInt();
        this.f1435f = parcel.readInt();
        this.g = parcel.readString();
        this.h = parcel.readInt() != 0;
        this.i = parcel.readInt() != 0;
        this.j = parcel.readInt() != 0;
        this.k = parcel.readBundle();
        this.l = parcel.readInt() != 0;
        this.n = parcel.readBundle();
        this.m = parcel.readInt();
    }
}
