package no.nordicsemi.android.mcp.scanner.graph;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;

/* loaded from: classes.dex */
public class RssiAccumulator implements Parcelable {
    public static final Parcelable.Creator<RssiAccumulator> CREATOR = new Parcelable.Creator<RssiAccumulator>() { // from class: no.nordicsemi.android.mcp.scanner.graph.RssiAccumulator.1
        @Override // android.os.Parcelable.Creator
        public RssiAccumulator createFromParcel(Parcel parcel) {
            return new RssiAccumulator(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public RssiAccumulator[] newArray(int i) {
            return new RssiAccumulator[i];
        }
    };
    private static final int DEFAULT_MAX_SIZE = 50;
    private static final long TIME_LENGTH_DISABLED = 0;
    private final int MAX_SIZE;
    private final String mAddress;
    private int mLength;
    private String mName;
    private int mStart;
    private int mTail;
    private long mTimeLength;
    private long[] mTimestampStack;
    private int[] mValueStack;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public float getAverage() {
        long uptimeMillis = SystemClock.uptimeMillis();
        int i = this.mTail;
        int i2 = 0;
        int i3 = 0;
        while (i != this.mStart) {
            i = ((i + r5) - 1) % this.MAX_SIZE;
            long j = this.mTimeLength;
            if (j != 0 && this.mTimestampStack[i] + j <= uptimeMillis) {
                break;
            }
            i2 += this.mValueStack[i];
            i3++;
        }
        if (i3 == 0) {
            return 0.0f;
        }
        return i2 / i3;
    }

    public float getAverageAndReset() {
        float average = getAverage();
        this.mStart = this.mTail;
        return average;
    }

    public String getName() {
        return this.mName;
    }

    public boolean isRecent() {
        if (this.mTimeLength == 0) {
            return true;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        int i = this.MAX_SIZE;
        return this.mTimestampStack[((this.mTail + i) - 1) % i] + this.mTimeLength > uptimeMillis;
    }

    public synchronized void put(int i) {
        this.mValueStack[this.mTail] = i;
        this.mTimestampStack[this.mTail] = SystemClock.uptimeMillis();
        this.mTail = (this.mTail + 1) % this.MAX_SIZE;
        if ((((this.MAX_SIZE + this.mTail) - this.mLength) - 1) % this.MAX_SIZE == this.mStart) {
            this.mStart = (this.mStart + 1) % this.MAX_SIZE;
        }
    }

    public void setLength(int i) {
        if (i < 1) {
            this.mLength = 1;
            return;
        }
        int i2 = this.MAX_SIZE;
        if (i > i2) {
            this.mLength = i2;
        } else {
            this.mLength = i;
        }
    }

    public void setName(String str) {
        this.mName = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTimeLength(long j) {
        if (j < 0) {
            this.mTimeLength = 0L;
        } else {
            this.mTimeLength = j;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.MAX_SIZE);
        parcel.writeString(this.mAddress);
        parcel.writeString(this.mName);
        parcel.writeInt(this.mStart);
        parcel.writeInt(this.mTail);
        parcel.writeInt(this.mLength);
        parcel.writeLong(this.mTimeLength);
        parcel.writeIntArray(this.mValueStack);
        parcel.writeLongArray(this.mTimestampStack);
    }

    public RssiAccumulator(String str) {
        this(str, 50);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RssiAccumulator(String str, int i) {
        this.MAX_SIZE = i;
        this.mAddress = str;
        int i2 = this.MAX_SIZE;
        this.mValueStack = new int[i2];
        this.mTimestampStack = new long[i2];
        this.mTimeLength = 10000L;
        this.mTail = 0;
        this.mStart = 0;
        this.mLength = i2;
    }

    private RssiAccumulator(Parcel parcel) {
        this.MAX_SIZE = parcel.readInt();
        this.mAddress = parcel.readString();
        this.mName = parcel.readString();
        this.mStart = parcel.readInt();
        this.mTail = parcel.readInt();
        this.mLength = parcel.readInt();
        this.mTimeLength = parcel.readLong();
        int[] iArr = new int[this.MAX_SIZE];
        this.mValueStack = iArr;
        parcel.readIntArray(iArr);
        long[] jArr = new long[this.MAX_SIZE];
        this.mTimestampStack = jArr;
        parcel.readLongArray(jArr);
    }
}
