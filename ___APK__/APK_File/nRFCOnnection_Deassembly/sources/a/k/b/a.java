package a.k.b;

import a.f.k.i;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public abstract class a<D> extends c<D> {
    static final boolean DEBUG = false;
    static final String TAG = "AsyncTaskLoader";
    volatile a<D>.RunnableC0027a mCancellingTask;
    private final Executor mExecutor;
    Handler mHandler;
    long mLastLoadCompleteTime;
    volatile a<D>.RunnableC0027a mTask;
    long mUpdateThrottle;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a.k.b.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public final class RunnableC0027a extends d<Void, Void, D> implements Runnable {
        private final CountDownLatch k = new CountDownLatch(1);
        boolean l;

        RunnableC0027a() {
        }

        @Override // a.k.b.d
        protected void b(D d2) {
            try {
                a.this.dispatchOnCancelled(this, d2);
            } finally {
                this.k.countDown();
            }
        }

        @Override // a.k.b.d
        protected void c(D d2) {
            try {
                a.this.dispatchOnLoadComplete(this, d2);
            } finally {
                this.k.countDown();
            }
        }

        public void d() {
            try {
                this.k.await();
            } catch (InterruptedException unused) {
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.l = false;
            a.this.executePendingTask();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // a.k.b.d
        public D a(Void... voidArr) {
            try {
                return (D) a.this.onLoadInBackground();
            } catch (a.f.h.b e2) {
                if (a()) {
                    return null;
                }
                throw e2;
            }
        }
    }

    public a(Context context) {
        this(context, d.i);
    }

    public void cancelLoadInBackground() {
    }

    void dispatchOnCancelled(a<D>.RunnableC0027a runnableC0027a, D d2) {
        onCanceled(d2);
        if (this.mCancellingTask == runnableC0027a) {
            rollbackContentChanged();
            this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
            this.mCancellingTask = null;
            deliverCancellation();
            executePendingTask();
        }
    }

    void dispatchOnLoadComplete(a<D>.RunnableC0027a runnableC0027a, D d2) {
        if (this.mTask != runnableC0027a) {
            dispatchOnCancelled(runnableC0027a, d2);
            return;
        }
        if (isAbandoned()) {
            onCanceled(d2);
            return;
        }
        commitContentChanged();
        this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
        this.mTask = null;
        deliverResult(d2);
    }

    @Override // a.k.b.c
    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        if (this.mTask != null) {
            printWriter.print(str);
            printWriter.print("mTask=");
            printWriter.print(this.mTask);
            printWriter.print(" waiting=");
            printWriter.println(this.mTask.l);
        }
        if (this.mCancellingTask != null) {
            printWriter.print(str);
            printWriter.print("mCancellingTask=");
            printWriter.print(this.mCancellingTask);
            printWriter.print(" waiting=");
            printWriter.println(this.mCancellingTask.l);
        }
        if (this.mUpdateThrottle != 0) {
            printWriter.print(str);
            printWriter.print("mUpdateThrottle=");
            i.a(this.mUpdateThrottle, printWriter);
            printWriter.print(" mLastLoadCompleteTime=");
            i.a(this.mLastLoadCompleteTime, SystemClock.uptimeMillis(), printWriter);
            printWriter.println();
        }
    }

    void executePendingTask() {
        if (this.mCancellingTask != null || this.mTask == null) {
            return;
        }
        if (this.mTask.l) {
            this.mTask.l = false;
            this.mHandler.removeCallbacks(this.mTask);
        }
        if (this.mUpdateThrottle > 0 && SystemClock.uptimeMillis() < this.mLastLoadCompleteTime + this.mUpdateThrottle) {
            this.mTask.l = true;
            this.mHandler.postAtTime(this.mTask, this.mLastLoadCompleteTime + this.mUpdateThrottle);
        } else {
            this.mTask.a(this.mExecutor, null);
        }
    }

    public boolean isLoadInBackgroundCanceled() {
        return this.mCancellingTask != null;
    }

    public abstract D loadInBackground();

    @Override // a.k.b.c
    protected boolean onCancelLoad() {
        if (this.mTask == null) {
            return false;
        }
        if (!this.mStarted) {
            this.mContentChanged = true;
        }
        if (this.mCancellingTask != null) {
            if (this.mTask.l) {
                this.mTask.l = false;
                this.mHandler.removeCallbacks(this.mTask);
            }
            this.mTask = null;
            return false;
        }
        if (this.mTask.l) {
            this.mTask.l = false;
            this.mHandler.removeCallbacks(this.mTask);
            this.mTask = null;
            return false;
        }
        boolean a2 = this.mTask.a(false);
        if (a2) {
            this.mCancellingTask = this.mTask;
            cancelLoadInBackground();
        }
        this.mTask = null;
        return a2;
    }

    public void onCanceled(D d2) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // a.k.b.c
    public void onForceLoad() {
        super.onForceLoad();
        cancelLoad();
        this.mTask = new RunnableC0027a();
        executePendingTask();
    }

    protected D onLoadInBackground() {
        return loadInBackground();
    }

    public void setUpdateThrottle(long j) {
        this.mUpdateThrottle = j;
        if (j != 0) {
            this.mHandler = new Handler();
        }
    }

    public void waitForLoader() {
        a<D>.RunnableC0027a runnableC0027a = this.mTask;
        if (runnableC0027a != null) {
            runnableC0027a.d();
        }
    }

    private a(Context context, Executor executor) {
        super(context);
        this.mLastLoadCompleteTime = -10000L;
        this.mExecutor = executor;
    }
}
