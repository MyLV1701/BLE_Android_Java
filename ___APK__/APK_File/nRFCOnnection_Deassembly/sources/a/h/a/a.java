package a.h.a;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public abstract class a implements Parcelable {

    /* renamed from: b, reason: collision with root package name */
    private final Parcelable f356b;

    /* renamed from: c, reason: collision with root package name */
    public static final a f355c = new C0021a();
    public static final Parcelable.Creator<a> CREATOR = new b();

    /* renamed from: a.h.a.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0021a extends a {
        C0021a() {
            super((C0021a) null);
        }
    }

    /* loaded from: classes.dex */
    static class b implements Parcelable.ClassLoaderCreator<a> {
        b() {
        }

        @Override // android.os.Parcelable.Creator
        public a[] newArray(int i) {
            return new a[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        public a createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel.readParcelable(classLoader) == null) {
                return a.f355c;
            }
            throw new IllegalStateException("superState must be null");
        }

        @Override // android.os.Parcelable.Creator
        public a createFromParcel(Parcel parcel) {
            return createFromParcel(parcel, (ClassLoader) null);
        }
    }

    /* synthetic */ a(C0021a c0021a) {
        this();
    }

    public final Parcelable d() {
        return this.f356b;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f356b, i);
    }

    private a() {
        this.f356b = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public a(Parcelable parcelable) {
        if (parcelable != null) {
            this.f356b = parcelable == f355c ? null : parcelable;
            return;
        }
        throw new IllegalArgumentException("superState must not be null");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public a(Parcel parcel, ClassLoader classLoader) {
        Parcelable readParcelable = parcel.readParcelable(classLoader);
        this.f356b = readParcelable == null ? f355c : readParcelable;
    }
}
