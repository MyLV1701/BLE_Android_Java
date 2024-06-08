package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class i implements Comparable<i>, Parcelable {
    public static final Parcelable.Creator<i> CREATOR = new a();

    /* renamed from: b, reason: collision with root package name */
    private final Calendar f2492b;

    /* renamed from: c, reason: collision with root package name */
    private final String f2493c;

    /* renamed from: d, reason: collision with root package name */
    final int f2494d;

    /* renamed from: e, reason: collision with root package name */
    final int f2495e;

    /* renamed from: f, reason: collision with root package name */
    final int f2496f;
    final int g;

    /* loaded from: classes.dex */
    static class a implements Parcelable.Creator<i> {
        a() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public i createFromParcel(Parcel parcel) {
            return i.a(parcel.readInt(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public i[] newArray(int i) {
            return new i[i];
        }
    }

    private i(Calendar calendar) {
        calendar.set(5, 1);
        this.f2492b = o.a(calendar);
        this.f2494d = this.f2492b.get(2);
        this.f2495e = this.f2492b.get(1);
        this.f2496f = this.f2492b.getMaximum(7);
        this.g = this.f2492b.getActualMaximum(5);
        this.f2493c = o.e().format(this.f2492b.getTime());
        this.f2492b.getTimeInMillis();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static i a(int i, int i2) {
        Calendar b2 = o.b();
        b2.set(1, i);
        b2.set(2, i2);
        return new i(b2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static i g() {
        return new i(o.d());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b(i iVar) {
        if (this.f2492b instanceof GregorianCalendar) {
            return ((iVar.f2495e - this.f2495e) * 12) + (iVar.f2494d - this.f2494d);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int d() {
        int firstDayOfWeek = this.f2492b.get(7) - this.f2492b.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.f2496f : firstDayOfWeek;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String e() {
        return this.f2493c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return this.f2494d == iVar.f2494d && this.f2495e == iVar.f2495e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long f() {
        return this.f2492b.getTimeInMillis();
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f2494d), Integer.valueOf(this.f2495e)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2495e);
        parcel.writeInt(this.f2494d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i b(int i) {
        Calendar a2 = o.a(this.f2492b);
        a2.add(2, i);
        return new i(a2);
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(i iVar) {
        return this.f2492b.compareTo(iVar.f2492b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long a(int i) {
        Calendar a2 = o.a(this.f2492b);
        a2.set(5, i);
        return a2.getTimeInMillis();
    }
}
