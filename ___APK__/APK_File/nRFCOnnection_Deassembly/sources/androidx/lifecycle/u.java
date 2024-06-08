package androidx.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class u {

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, Object> f1563a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private volatile boolean f1564b = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        this.f1564b = true;
        Map<String, Object> map = this.f1563a;
        if (map != null) {
            synchronized (map) {
                Iterator<Object> it = this.f1563a.values().iterator();
                while (it.hasNext()) {
                    a(it.next());
                }
            }
        }
        b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public <T> T a(String str, T t) {
        Object obj;
        synchronized (this.f1563a) {
            obj = this.f1563a.get(str);
            if (obj == 0) {
                this.f1563a.put(str, t);
            }
        }
        if (obj != 0) {
            t = obj;
        }
        if (this.f1564b) {
            a(t);
        }
        return t;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T> T a(String str) {
        T t;
        Map<String, Object> map = this.f1563a;
        if (map == null) {
            return null;
        }
        synchronized (map) {
            t = (T) this.f1563a.get(str);
        }
        return t;
    }

    private static void a(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }
    }
}
