package com.google.android.material.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
class b {

    /* renamed from: e, reason: collision with root package name */
    private static b f2664e;

    /* renamed from: a, reason: collision with root package name */
    private final Object f2665a = new Object();

    /* renamed from: b, reason: collision with root package name */
    private final Handler f2666b = new Handler(Looper.getMainLooper(), new a());

    /* renamed from: c, reason: collision with root package name */
    private c f2667c;

    /* renamed from: d, reason: collision with root package name */
    private c f2668d;

    /* loaded from: classes.dex */
    class a implements Handler.Callback {
        a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 0) {
                return false;
            }
            b.this.a((c) message.obj);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.android.material.snackbar.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0088b {
        void a(int i);

        void d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a, reason: collision with root package name */
        final WeakReference<InterfaceC0088b> f2670a;

        /* renamed from: b, reason: collision with root package name */
        int f2671b;

        /* renamed from: c, reason: collision with root package name */
        boolean f2672c;

        c(int i, InterfaceC0088b interfaceC0088b) {
            this.f2670a = new WeakReference<>(interfaceC0088b);
            this.f2671b = i;
        }

        boolean a(InterfaceC0088b interfaceC0088b) {
            return interfaceC0088b != null && this.f2670a.get() == interfaceC0088b;
        }
    }

    private b() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b a() {
        if (f2664e == null) {
            f2664e = new b();
        }
        return f2664e;
    }

    private boolean f(InterfaceC0088b interfaceC0088b) {
        c cVar = this.f2667c;
        return cVar != null && cVar.a(interfaceC0088b);
    }

    private boolean g(InterfaceC0088b interfaceC0088b) {
        c cVar = this.f2668d;
        return cVar != null && cVar.a(interfaceC0088b);
    }

    public void b(InterfaceC0088b interfaceC0088b) {
        synchronized (this.f2665a) {
            if (f(interfaceC0088b)) {
                this.f2667c = null;
                if (this.f2668d != null) {
                    b();
                }
            }
        }
    }

    public void c(InterfaceC0088b interfaceC0088b) {
        synchronized (this.f2665a) {
            if (f(interfaceC0088b)) {
                b(this.f2667c);
            }
        }
    }

    public void d(InterfaceC0088b interfaceC0088b) {
        synchronized (this.f2665a) {
            if (f(interfaceC0088b) && !this.f2667c.f2672c) {
                this.f2667c.f2672c = true;
                this.f2666b.removeCallbacksAndMessages(this.f2667c);
            }
        }
    }

    public void e(InterfaceC0088b interfaceC0088b) {
        synchronized (this.f2665a) {
            if (f(interfaceC0088b) && this.f2667c.f2672c) {
                this.f2667c.f2672c = false;
                b(this.f2667c);
            }
        }
    }

    public void a(int i, InterfaceC0088b interfaceC0088b) {
        synchronized (this.f2665a) {
            if (f(interfaceC0088b)) {
                this.f2667c.f2671b = i;
                this.f2666b.removeCallbacksAndMessages(this.f2667c);
                b(this.f2667c);
                return;
            }
            if (g(interfaceC0088b)) {
                this.f2668d.f2671b = i;
            } else {
                this.f2668d = new c(i, interfaceC0088b);
            }
            if (this.f2667c == null || !a(this.f2667c, 4)) {
                this.f2667c = null;
                b();
            }
        }
    }

    private void b() {
        c cVar = this.f2668d;
        if (cVar != null) {
            this.f2667c = cVar;
            this.f2668d = null;
            InterfaceC0088b interfaceC0088b = this.f2667c.f2670a.get();
            if (interfaceC0088b != null) {
                interfaceC0088b.d();
            } else {
                this.f2667c = null;
            }
        }
    }

    private void b(c cVar) {
        int i = cVar.f2671b;
        if (i == -2) {
            return;
        }
        if (i <= 0) {
            i = i == -1 ? 1500 : 2750;
        }
        this.f2666b.removeCallbacksAndMessages(cVar);
        Handler handler = this.f2666b;
        handler.sendMessageDelayed(Message.obtain(handler, 0, cVar), i);
    }

    public void a(InterfaceC0088b interfaceC0088b, int i) {
        synchronized (this.f2665a) {
            if (f(interfaceC0088b)) {
                a(this.f2667c, i);
            } else if (g(interfaceC0088b)) {
                a(this.f2668d, i);
            }
        }
    }

    public boolean a(InterfaceC0088b interfaceC0088b) {
        boolean z;
        synchronized (this.f2665a) {
            z = f(interfaceC0088b) || g(interfaceC0088b);
        }
        return z;
    }

    private boolean a(c cVar, int i) {
        InterfaceC0088b interfaceC0088b = cVar.f2670a.get();
        if (interfaceC0088b == null) {
            return false;
        }
        this.f2666b.removeCallbacksAndMessages(cVar);
        interfaceC0088b.a(i);
        return true;
    }

    void a(c cVar) {
        synchronized (this.f2665a) {
            if (this.f2667c == cVar || this.f2668d == cVar) {
                a(cVar, 2);
            }
        }
    }
}
