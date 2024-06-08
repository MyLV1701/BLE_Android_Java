package no.nordicsemi.android.mcp.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;

/* loaded from: classes.dex */
public class ParcelableTimespan implements Parcelable {
    public static final Parcelable.Creator<ParcelableTimespan> CREATOR = new Parcelable.Creator<ParcelableTimespan>() { // from class: no.nordicsemi.android.mcp.util.ParcelableTimespan.1
        @Override // android.os.Parcelable.Creator
        public ParcelableTimespan createFromParcel(Parcel parcel) {
            return new ParcelableTimespan(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ParcelableTimespan[] newArray(int i) {
            return new ParcelableTimespan[i];
        }
    };
    public long end;
    public long start;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ParcelableTimespan start() {
        this.start = SystemClock.elapsedRealtime();
        this.end = 0L;
        return this;
    }

    public ParcelableTimespan stop() {
        this.end = SystemClock.elapsedRealtime();
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.start);
        parcel.writeLong(this.end);
    }

    public ParcelableTimespan() {
    }

    private ParcelableTimespan(Parcel parcel) {
        this.start = parcel.readLong();
        this.end = parcel.readLong();
    }
}
