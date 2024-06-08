package no.nordicsemi.android.mcp.scanner.graph;

import android.graphics.Canvas;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.androidplot.Plot;
import com.androidplot.PlotListener;
import com.androidplot.xy.XYSeries;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes.dex */
public class ParcelableXYSeries implements XYSeries, PlotListener, Parcelable {
    private ReentrantReadWriteLock lock;
    private volatile String title;
    private volatile LinkedList<Integer> xVals;
    private volatile LinkedList<Float> yVals;
    private static final String TAG = ParcelableXYSeries.class.getName();
    public static final Parcelable.Creator<ParcelableXYSeries> CREATOR = new Parcelable.Creator<ParcelableXYSeries>() { // from class: no.nordicsemi.android.mcp.scanner.graph.ParcelableXYSeries.1
        @Override // android.os.Parcelable.Creator
        public ParcelableXYSeries createFromParcel(Parcel parcel) {
            return new ParcelableXYSeries(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ParcelableXYSeries[] newArray(int i) {
            return new ParcelableXYSeries[i];
        }
    };

    public void addAll(Pair<List<Integer>, List<Float>> pair) {
        this.lock.writeLock().lock();
        try {
            this.xVals.addAll((Collection) pair.first);
            this.yVals.addAll((Collection) pair.second);
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public void addFirst(Integer num, Float f2) {
        this.lock.writeLock().lock();
        try {
            this.xVals.addFirst(num);
            this.yVals.addFirst(f2);
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public void addLast(Integer num, Float f2) {
        this.lock.writeLock().lock();
        try {
            this.xVals.addLast(num);
            this.yVals.addLast(f2);
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public void clear() {
        this.lock.writeLock().lock();
        try {
            this.xVals.clear();
            this.yVals.clear();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.androidplot.Series
    public String getTitle() {
        return this.title;
    }

    @Override // com.androidplot.xy.XYSeries
    public Integer getX(int i) {
        return this.xVals.get(i);
    }

    @Override // com.androidplot.xy.XYSeries
    public Float getY(int i) {
        return this.yVals.get(i);
    }

    @Override // com.androidplot.PlotListener
    public void onAfterDraw(Plot plot, Canvas canvas) {
        this.lock.readLock().unlock();
    }

    @Override // com.androidplot.PlotListener
    public void onBeforeDraw(Plot plot, Canvas canvas) {
        this.lock.readLock().lock();
    }

    public void setTitle(String str) {
        this.lock.writeLock().lock();
        try {
            this.title = str;
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public void setX(Integer num, int i) {
        this.lock.writeLock().lock();
        try {
            this.xVals.set(i, num);
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public void setXY(Integer num, Float f2, int i) {
        this.lock.writeLock().lock();
        try {
            this.yVals.set(i, f2);
            this.xVals.set(i, num);
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public void setY(Float f2, int i) {
        this.lock.writeLock().lock();
        try {
            this.yVals.set(i, f2);
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override // com.androidplot.xy.XYSeries
    public int size() {
        return this.yVals.size();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeList(this.xVals);
        parcel.writeList(this.yVals);
    }

    public ParcelableXYSeries(String str) {
        this.xVals = new LinkedList<>();
        this.yVals = new LinkedList<>();
        this.title = null;
        this.lock = new ReentrantReadWriteLock(true);
        this.title = str;
    }

    @Override // com.androidplot.xy.XYSeries
    public List<Float> getY() {
        return this.yVals;
    }

    private ParcelableXYSeries(Parcel parcel) {
        this.xVals = new LinkedList<>();
        this.yVals = new LinkedList<>();
        this.title = null;
        this.lock = new ReentrantReadWriteLock(true);
        this.title = parcel.readString();
        parcel.readList(this.xVals, Integer.class.getClassLoader());
        parcel.readList(this.yVals, Float.class.getClassLoader());
    }
}
