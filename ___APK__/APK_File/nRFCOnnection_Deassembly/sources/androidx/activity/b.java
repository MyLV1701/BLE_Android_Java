package androidx.activity;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public abstract class b {

    /* renamed from: a, reason: collision with root package name */
    private boolean f607a;

    /* renamed from: b, reason: collision with root package name */
    private CopyOnWriteArrayList<a> f608b = new CopyOnWriteArrayList<>();

    public b(boolean z) {
        this.f607a = z;
    }

    public abstract void a();

    public final void a(boolean z) {
        this.f607a = z;
    }

    public final boolean b() {
        return this.f607a;
    }

    public final void c() {
        Iterator<a> it = this.f608b.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(a aVar) {
        this.f608b.add(aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(a aVar) {
        this.f608b.remove(aVar);
    }
}
