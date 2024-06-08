package a.f.h;

import android.os.Build;
import android.os.CancellationSignal;

/* loaded from: classes.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private boolean f179a;

    /* renamed from: b, reason: collision with root package name */
    private InterfaceC0009a f180b;

    /* renamed from: c, reason: collision with root package name */
    private Object f181c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f182d;

    /* renamed from: a.f.h.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0009a {
        void a();
    }

    private void e() {
        while (this.f182d) {
            try {
                wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    public void a() {
        synchronized (this) {
            if (this.f179a) {
                return;
            }
            this.f179a = true;
            this.f182d = true;
            InterfaceC0009a interfaceC0009a = this.f180b;
            Object obj = this.f181c;
            if (interfaceC0009a != null) {
                try {
                    interfaceC0009a.a();
                } catch (Throwable th) {
                    synchronized (this) {
                        this.f182d = false;
                        notifyAll();
                        throw th;
                    }
                }
            }
            if (obj != null && Build.VERSION.SDK_INT >= 16) {
                ((CancellationSignal) obj).cancel();
            }
            synchronized (this) {
                this.f182d = false;
                notifyAll();
            }
        }
    }

    public Object b() {
        Object obj;
        if (Build.VERSION.SDK_INT < 16) {
            return null;
        }
        synchronized (this) {
            if (this.f181c == null) {
                this.f181c = new CancellationSignal();
                if (this.f179a) {
                    ((CancellationSignal) this.f181c).cancel();
                }
            }
            obj = this.f181c;
        }
        return obj;
    }

    public boolean c() {
        boolean z;
        synchronized (this) {
            z = this.f179a;
        }
        return z;
    }

    public void d() {
        if (c()) {
            throw new b();
        }
    }

    public void a(InterfaceC0009a interfaceC0009a) {
        synchronized (this) {
            e();
            if (this.f180b == interfaceC0009a) {
                return;
            }
            this.f180b = interfaceC0009a;
            if (this.f179a && interfaceC0009a != null) {
                interfaceC0009a.a();
            }
        }
    }
}
