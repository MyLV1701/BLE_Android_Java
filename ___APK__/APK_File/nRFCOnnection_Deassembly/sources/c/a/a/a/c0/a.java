package c.a.a.a.c0;

import a.d.g;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class a extends a.h.a.a {
    public static final Parcelable.Creator<a> CREATOR = new C0065a();

    /* renamed from: d, reason: collision with root package name */
    public final g<String, Bundle> f2092d;

    /* renamed from: c.a.a.a.c0.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0065a implements Parcelable.ClassLoaderCreator<a> {
        C0065a() {
        }

        @Override // android.os.Parcelable.Creator
        public a[] newArray(int i) {
            return new a[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        public a createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new a(parcel, classLoader, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Parcelable.Creator
        public a createFromParcel(Parcel parcel) {
            return new a(parcel, null, 0 == true ? 1 : 0);
        }
    }

    /* synthetic */ a(Parcel parcel, ClassLoader classLoader, C0065a c0065a) {
        this(parcel, classLoader);
    }

    public String toString() {
        return "ExtendableSavedState{" + Integer.toHexString(System.identityHashCode(this)) + " states=" + this.f2092d + "}";
    }

    @Override // a.h.a.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        int size = this.f2092d.size();
        parcel.writeInt(size);
        String[] strArr = new String[size];
        Bundle[] bundleArr = new Bundle[size];
        for (int i2 = 0; i2 < size; i2++) {
            strArr[i2] = this.f2092d.b(i2);
            bundleArr[i2] = this.f2092d.d(i2);
        }
        parcel.writeStringArray(strArr);
        parcel.writeTypedArray(bundleArr, 0);
    }

    public a(Parcelable parcelable) {
        super(parcelable);
        this.f2092d = new g<>();
    }

    private a(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        int readInt = parcel.readInt();
        String[] strArr = new String[readInt];
        parcel.readStringArray(strArr);
        Bundle[] bundleArr = new Bundle[readInt];
        parcel.readTypedArray(bundleArr, Bundle.CREATOR);
        this.f2092d = new g<>(readInt);
        for (int i = 0; i < readInt; i++) {
            this.f2092d.put(strArr[i], bundleArr[i]);
        }
    }
}
