package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new C0082a();

    /* renamed from: b, reason: collision with root package name */
    private final i f2449b;

    /* renamed from: c, reason: collision with root package name */
    private final i f2450c;

    /* renamed from: d, reason: collision with root package name */
    private final i f2451d;

    /* renamed from: e, reason: collision with root package name */
    private final b f2452e;

    /* renamed from: f, reason: collision with root package name */
    private final int f2453f;
    private final int g;

    /* renamed from: com.google.android.material.datepicker.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0082a implements Parcelable.Creator<a> {
        C0082a() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public a createFromParcel(Parcel parcel) {
            return new a((i) parcel.readParcelable(i.class.getClassLoader()), (i) parcel.readParcelable(i.class.getClassLoader()), (i) parcel.readParcelable(i.class.getClassLoader()), (b) parcel.readParcelable(b.class.getClassLoader()), null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public a[] newArray(int i) {
            return new a[i];
        }
    }

    /* loaded from: classes.dex */
    public interface b extends Parcelable {
        boolean a(long j);
    }

    /* synthetic */ a(i iVar, i iVar2, i iVar3, b bVar, C0082a c0082a) {
        this(iVar, iVar2, iVar3, bVar);
    }

    public b d() {
        return this.f2452e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i e() {
        return this.f2450c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f2449b.equals(aVar.f2449b) && this.f2450c.equals(aVar.f2450c) && this.f2451d.equals(aVar.f2451d) && this.f2452e.equals(aVar.f2452e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i g() {
        return this.f2451d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i h() {
        return this.f2449b;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f2449b, this.f2450c, this.f2451d, this.f2452e});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int i() {
        return this.f2453f;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f2449b, 0);
        parcel.writeParcelable(this.f2450c, 0);
        parcel.writeParcelable(this.f2451d, 0);
        parcel.writeParcelable(this.f2452e, 0);
    }

    private a(i iVar, i iVar2, i iVar3, b bVar) {
        this.f2449b = iVar;
        this.f2450c = iVar2;
        this.f2451d = iVar3;
        this.f2452e = bVar;
        if (iVar.compareTo(iVar3) <= 0) {
            if (iVar3.compareTo(iVar2) <= 0) {
                this.g = iVar.b(iVar2) + 1;
                this.f2453f = (iVar2.f2495e - iVar.f2495e) + 1;
                return;
            }
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        throw new IllegalArgumentException("start Month cannot be after current Month");
    }
}
