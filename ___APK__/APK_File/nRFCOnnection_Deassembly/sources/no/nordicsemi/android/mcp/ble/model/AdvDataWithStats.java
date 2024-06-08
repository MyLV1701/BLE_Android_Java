package no.nordicsemi.android.mcp.ble.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class AdvDataWithStats extends AdvData implements Parcelable {
    public static final Parcelable.Creator<AdvDataWithStats> CREATOR = new Parcelable.Creator<AdvDataWithStats>() { // from class: no.nordicsemi.android.mcp.ble.model.AdvDataWithStats.1
        @Override // android.os.Parcelable.Creator
        public AdvDataWithStats createFromParcel(Parcel parcel) {
            return new AdvDataWithStats(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public AdvDataWithStats[] newArray(int i) {
            return new AdvDataWithStats[i];
        }
    };
    protected int count;
    private long intervalNanos;

    public AdvDataWithStats copy() {
        AdvDataWithStats advDataWithStats = new AdvDataWithStats();
        advDataWithStats.intervalNanos = this.intervalNanos;
        advDataWithStats.count = 1;
        return advDataWithStats;
    }

    @Override // no.nordicsemi.android.mcp.ble.model.AdvData, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getCount() {
        return this.count;
    }

    public long getIntervalNanos() {
        return this.intervalNanos;
    }

    public void increaseCount() {
        this.count++;
    }

    public void setIntervalIfLower(long j) {
        if (j <= 0) {
            return;
        }
        long j2 = this.intervalNanos;
        if (j2 == 0) {
            this.intervalNanos = j;
            return;
        }
        double d2 = j;
        double d3 = j2;
        Double.isNaN(d3);
        if (d2 < d3 * 0.7d && this.count < 10) {
            this.intervalNanos = j;
            return;
        }
        long j3 = this.intervalNanos;
        if (j < 3000000 + j3) {
            this.intervalNanos = ((this.intervalNanos * (r0 - 1)) + j) / Math.min(this.count, 10);
            return;
        }
        double d4 = j3;
        Double.isNaN(d4);
        if (d2 < d4 * 1.4d) {
            this.intervalNanos = ((j3 * 29) + j) / 30;
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.model.AdvData, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.intervalNanos);
        parcel.writeInt(this.count);
    }

    public AdvDataWithStats() {
        this.intervalNanos = 0L;
        this.count = 1;
    }

    public AdvDataWithStats(AdvDataWithStats advDataWithStats) {
        super(advDataWithStats);
        this.intervalNanos = 0L;
        this.intervalNanos = advDataWithStats.intervalNanos;
        this.count = 1;
    }

    private AdvDataWithStats(Parcel parcel) {
        super(parcel);
        this.intervalNanos = 0L;
        this.intervalNanos = parcel.readLong();
        this.count = parcel.readInt();
    }
}
