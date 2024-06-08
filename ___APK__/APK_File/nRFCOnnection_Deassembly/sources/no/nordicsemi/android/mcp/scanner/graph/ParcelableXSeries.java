package no.nordicsemi.android.mcp.scanner.graph;

import android.graphics.Canvas;
import android.os.Parcel;
import android.os.Parcelable;
import com.androidplot.Plot;
import com.androidplot.PlotListener;
import com.androidplot.xy.XYSeries;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes.dex */
public class ParcelableXSeries implements XYSeries, PlotListener, Parcelable {
    private ReentrantReadWriteLock lock;
    private volatile String title;
    private volatile LinkedList<Float> yVals;
    private static final String TAG = ParcelableXSeries.class.getName();
    public static final Parcelable.Creator<ParcelableXSeries> CREATOR = new Parcelable.Creator<ParcelableXSeries>() { // from class: no.nordicsemi.android.mcp.scanner.graph.ParcelableXSeries.1
        @Override // android.os.Parcelable.Creator
        public ParcelableXSeries createFromParcel(Parcel parcel) {
            return new ParcelableXSeries(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ParcelableXSeries[] newArray(int i) {
            return new ParcelableXSeries[i];
        }
    };

    public void addAll(Collection<Float> collection) {
        this.lock.writeLock().lock();
        try {
            this.yVals.addAll(collection);
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public void addFirst(Float f2) {
        this.lock.writeLock().lock();
        try {
            this.yVals.addFirst(f2);
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public void addLast(Float f2) {
        this.lock.writeLock().lock();
        try {
            this.yVals.addLast(f2);
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public void clear() {
        this.lock.writeLock().lock();
        try {
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
        return Integer.valueOf(i);
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

    public Float removeFirst() {
        this.lock.writeLock().lock();
        try {
            if (size() > 0) {
                return this.yVals.removeFirst();
            }
            throw new NoSuchElementException();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public Float removeLast() {
        this.lock.writeLock().lock();
        try {
            if (size() > 0) {
                return this.yVals.removeLast();
            }
            throw new NoSuchElementException();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public void setTitle(String str) {
        this.lock.writeLock().lock();
        try {
            this.title = str;
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
        if (this.yVals != null) {
            return this.yVals.size();
        }
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeList(this.yVals);
    }

    public ParcelableXSeries(String str) {
        this.yVals = new LinkedList<>();
        this.title = null;
        this.lock = new ReentrantReadWriteLock(true);
        this.title = str;
    }

    @Override // com.androidplot.xy.XYSeries
    public List<Float> getY() {
        return this.yVals;
    }

    private ParcelableXSeries(Parcel parcel) {
        this.yVals = new LinkedList<>();
        this.title = null;
        this.lock = new ReentrantReadWriteLock(true);
        this.title = parcel.readString();
        parcel.readList(this.yVals, Float.class.getClassLoader());
    }
}
