package a.f.i;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes.dex */
public class c {

    /* renamed from: b, reason: collision with root package name */
    private HandlerThread f211b;

    /* renamed from: c, reason: collision with root package name */
    private Handler f212c;

    /* renamed from: f, reason: collision with root package name */
    private final int f215f;
    private final int g;
    private final String h;

    /* renamed from: a, reason: collision with root package name */
    private final Object f210a = new Object();

    /* renamed from: e, reason: collision with root package name */
    private Handler.Callback f214e = new a();

    /* renamed from: d, reason: collision with root package name */
    private int f213d = 0;

    /* loaded from: classes.dex */
    class a implements Handler.Callback {
        a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                c.this.a();
                return true;
            }
            if (i != 1) {
                return true;
            }
            c.this.a((Runnable) message.obj);
            return true;
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Callable f217b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Handler f218c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ d f219d;

        /* loaded from: classes.dex */
        class a implements Runnable {

            /* renamed from: b, reason: collision with root package name */
            final /* synthetic */ Object f220b;

            a(Object obj) {
                this.f220b = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f219d.a(this.f220b);
            }
        }

        b(c cVar, Callable callable, Handler handler, d dVar) {
            this.f217b = callable;
            this.f218c = handler;
            this.f219d = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            try {
                obj = this.f217b.call();
            } catch (Exception unused) {
                obj = null;
            }
            this.f218c.post(new a(obj));
        }
    }

    /* renamed from: a.f.i.c$c, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class RunnableC0011c implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ AtomicReference f222b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Callable f223c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ ReentrantLock f224d;

        /* renamed from: e, reason: collision with root package name */
        final /* synthetic */ AtomicBoolean f225e;

        /* renamed from: f, reason: collision with root package name */
        final /* synthetic */ Condition f226f;

        RunnableC0011c(c cVar, AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
            this.f222b = atomicReference;
            this.f223c = callable;
            this.f224d = reentrantLock;
            this.f225e = atomicBoolean;
            this.f226f = condition;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f222b.set(this.f223c.call());
            } catch (Exception unused) {
            }
            this.f224d.lock();
            try {
                this.f225e.set(false);
                this.f226f.signal();
            } finally {
                this.f224d.unlock();
            }
        }
    }

    /* loaded from: classes.dex */
    public interface d<T> {
        void a(T t);
    }

    public c(String str, int i, int i2) {
        this.h = str;
        this.g = i;
        this.f215f = i2;
    }

    private void b(Runnable runnable) {
        synchronized (this.f210a) {
            if (this.f211b == null) {
                this.f211b = new HandlerThread(this.h, this.g);
                this.f211b.start();
                this.f212c = new Handler(this.f211b.getLooper(), this.f214e);
                this.f213d++;
            }
            this.f212c.removeMessages(0);
            this.f212c.sendMessage(this.f212c.obtainMessage(1, runnable));
        }
    }

    public <T> void a(Callable<T> callable, d<T> dVar) {
        b(new b(this, callable, new Handler(), dVar));
    }

    public <T> T a(Callable<T> callable, int i) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition newCondition = reentrantLock.newCondition();
        AtomicReference atomicReference = new AtomicReference();
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        b(new RunnableC0011c(this, atomicReference, callable, reentrantLock, atomicBoolean, newCondition));
        reentrantLock.lock();
        try {
            if (!atomicBoolean.get()) {
                return (T) atomicReference.get();
            }
            long nanos = TimeUnit.MILLISECONDS.toNanos(i);
            do {
                try {
                    nanos = newCondition.awaitNanos(nanos);
                } catch (InterruptedException unused) {
                }
                if (!atomicBoolean.get()) {
                    return (T) atomicReference.get();
                }
            } while (nanos > 0);
            throw new InterruptedException("timeout");
        } finally {
            reentrantLock.unlock();
        }
    }

    void a(Runnable runnable) {
        runnable.run();
        synchronized (this.f210a) {
            this.f212c.removeMessages(0);
            this.f212c.sendMessageDelayed(this.f212c.obtainMessage(0), this.f215f);
        }
    }

    void a() {
        synchronized (this.f210a) {
            if (this.f212c.hasMessages(1)) {
                return;
            }
            this.f211b.quit();
            this.f211b = null;
            this.f212c = null;
        }
    }
}
