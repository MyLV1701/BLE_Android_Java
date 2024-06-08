package a.k.b;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
abstract class d<Params, Progress, Result> {
    private static final ThreadFactory g = new a();
    private static final BlockingQueue<Runnable> h = new LinkedBlockingQueue(10);
    public static final Executor i = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, h, g);
    private static f j;

    /* renamed from: d, reason: collision with root package name */
    private volatile g f398d = g.PENDING;

    /* renamed from: e, reason: collision with root package name */
    final AtomicBoolean f399e = new AtomicBoolean();

    /* renamed from: f, reason: collision with root package name */
    final AtomicBoolean f400f = new AtomicBoolean();

    /* renamed from: b, reason: collision with root package name */
    private final h<Params, Result> f396b = new b();

    /* renamed from: c, reason: collision with root package name */
    private final FutureTask<Result> f397c = new c(this.f396b);

    /* loaded from: classes.dex */
    static class a implements ThreadFactory {

        /* renamed from: a, reason: collision with root package name */
        private final AtomicInteger f401a = new AtomicInteger(1);

        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "ModernAsyncTask #" + this.f401a.getAndIncrement());
        }
    }

    /* loaded from: classes.dex */
    class b extends h<Params, Result> {
        b() {
        }

        @Override // java.util.concurrent.Callable
        public Result call() {
            d.this.f400f.set(true);
            Result result = null;
            try {
                Process.setThreadPriority(10);
                result = (Result) d.this.a((Object[]) this.f411a);
                Binder.flushPendingCommands();
                return result;
            } finally {
            }
        }
    }

    /* loaded from: classes.dex */
    class c extends FutureTask<Result> {
        c(Callable callable) {
            super(callable);
        }

        @Override // java.util.concurrent.FutureTask
        protected void done() {
            try {
                d.this.e(get());
            } catch (InterruptedException e2) {
                Log.w("AsyncTask", e2);
            } catch (CancellationException unused) {
                d.this.e(null);
            } catch (ExecutionException e3) {
                throw new RuntimeException("An error occurred while executing doInBackground()", e3.getCause());
            } catch (Throwable th) {
                throw new RuntimeException("An error occurred while executing doInBackground()", th);
            }
        }
    }

    /* renamed from: a.k.b.d$d, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class C0029d {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f404a = new int[g.values().length];

        static {
            try {
                f404a[g.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f404a[g.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class e<Data> {

        /* renamed from: a, reason: collision with root package name */
        final d f405a;

        /* renamed from: b, reason: collision with root package name */
        final Data[] f406b;

        e(d dVar, Data... dataArr) {
            this.f405a = dVar;
            this.f406b = dataArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class f extends Handler {
        f() {
            super(Looper.getMainLooper());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            e eVar = (e) message.obj;
            int i = message.what;
            if (i == 1) {
                eVar.f405a.a((d) eVar.f406b[0]);
            } else {
                if (i != 2) {
                    return;
                }
                eVar.f405a.b((Object[]) eVar.f406b);
            }
        }
    }

    /* loaded from: classes.dex */
    public enum g {
        PENDING,
        RUNNING,
        FINISHED
    }

    /* loaded from: classes.dex */
    private static abstract class h<Params, Result> implements Callable<Result> {

        /* renamed from: a, reason: collision with root package name */
        Params[] f411a;

        h() {
        }
    }

    private static Handler d() {
        f fVar;
        synchronized (d.class) {
            if (j == null) {
                j = new f();
            }
            fVar = j;
        }
        return fVar;
    }

    protected abstract Result a(Params... paramsArr);

    public final boolean a() {
        return this.f399e.get();
    }

    protected void b() {
    }

    protected void b(Result result) {
        b();
    }

    protected void b(Progress... progressArr) {
    }

    protected void c() {
    }

    protected void c(Result result) {
    }

    void e(Result result) {
        if (this.f400f.get()) {
            return;
        }
        d(result);
    }

    public final boolean a(boolean z) {
        this.f399e.set(true);
        return this.f397c.cancel(z);
    }

    public final d<Params, Progress, Result> a(Executor executor, Params... paramsArr) {
        if (this.f398d != g.PENDING) {
            int i2 = C0029d.f404a[this.f398d.ordinal()];
            if (i2 == 1) {
                throw new IllegalStateException("Cannot execute task: the task is already running.");
            }
            if (i2 != 2) {
                throw new IllegalStateException("We should never reach this state");
            }
            throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
        }
        this.f398d = g.RUNNING;
        c();
        this.f396b.f411a = paramsArr;
        executor.execute(this.f397c);
        return this;
    }

    Result d(Result result) {
        d().obtainMessage(1, new e(this, result)).sendToTarget();
        return result;
    }

    void a(Result result) {
        if (a()) {
            b((d<Params, Progress, Result>) result);
        } else {
            c(result);
        }
        this.f398d = g.FINISHED;
    }
}
