package com.androidplot.util;

import android.util.Log;
import com.androidplot.Plot;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class Redrawer implements Runnable {
    private static final String TAG = Redrawer.class.getName();
    private boolean keepAlive;
    private boolean keepRunning;
    private List<Plot> plots;
    private long sleepTime;

    public Redrawer(List<Plot> list, float f2, boolean z) {
        this.plots = list;
        setMaxRefreshRate(f2);
        new Thread(this).start();
        if (z) {
            run();
        }
    }

    public synchronized void finish() {
        this.keepRunning = false;
        this.keepAlive = false;
        notify();
    }

    public synchronized void pause() {
        this.keepRunning = false;
        notify();
        Log.d(TAG, "Redrawer paused.");
    }

    @Override // java.lang.Runnable
    public void run() {
        this.keepAlive = true;
        while (this.keepAlive) {
            try {
                if (this.keepRunning) {
                    Iterator<Plot> it = this.plots.iterator();
                    while (it.hasNext()) {
                        it.next().redraw();
                    }
                    synchronized (this) {
                        wait(this.sleepTime);
                    }
                } else {
                    synchronized (this) {
                        wait();
                    }
                }
            } catch (InterruptedException unused) {
            } catch (Throwable th) {
                Log.d(TAG, "Redrawer thread exited.");
                throw th;
            }
        }
        Log.d(TAG, "Redrawer thread exited.");
    }

    public void setMaxRefreshRate(float f2) {
        this.sleepTime = 1000.0f / f2;
        Log.d(TAG, "Set Redrawer refresh rate to " + f2 + "( " + this.sleepTime + " ms)");
    }

    public synchronized void start() {
        this.keepRunning = true;
        notify();
        Log.d(TAG, "Redrawer started.");
    }

    public Redrawer(Plot plot, float f2, boolean z) {
        this((List<Plot>) Arrays.asList(plot), f2, z);
    }
}
