package androidx.activity;

import android.annotation.SuppressLint;
import androidx.lifecycle.g;
import androidx.lifecycle.h;
import androidx.lifecycle.j;
import java.util.ArrayDeque;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* renamed from: a, reason: collision with root package name */
    private final Runnable f599a;

    /* renamed from: b, reason: collision with root package name */
    final ArrayDeque<b> f600b = new ArrayDeque<>();

    /* loaded from: classes.dex */
    private class LifecycleOnBackPressedCancellable implements h, androidx.activity.a {

        /* renamed from: a, reason: collision with root package name */
        private final g f601a;

        /* renamed from: b, reason: collision with root package name */
        private final b f602b;

        /* renamed from: c, reason: collision with root package name */
        private androidx.activity.a f603c;

        LifecycleOnBackPressedCancellable(g gVar, b bVar) {
            this.f601a = gVar;
            this.f602b = bVar;
            gVar.a(this);
        }

        @Override // androidx.lifecycle.h
        public void a(j jVar, g.a aVar) {
            if (aVar == g.a.ON_START) {
                this.f603c = OnBackPressedDispatcher.this.a(this.f602b);
                return;
            }
            if (aVar == g.a.ON_STOP) {
                androidx.activity.a aVar2 = this.f603c;
                if (aVar2 != null) {
                    aVar2.cancel();
                    return;
                }
                return;
            }
            if (aVar == g.a.ON_DESTROY) {
                cancel();
            }
        }

        @Override // androidx.activity.a
        public void cancel() {
            this.f601a.b(this);
            this.f602b.b(this);
            androidx.activity.a aVar = this.f603c;
            if (aVar != null) {
                aVar.cancel();
                this.f603c = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class a implements androidx.activity.a {

        /* renamed from: a, reason: collision with root package name */
        private final b f605a;

        a(b bVar) {
            this.f605a = bVar;
        }

        @Override // androidx.activity.a
        public void cancel() {
            OnBackPressedDispatcher.this.f600b.remove(this.f605a);
            this.f605a.b(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.f599a = runnable;
    }

    androidx.activity.a a(b bVar) {
        this.f600b.add(bVar);
        a aVar = new a(bVar);
        bVar.a(aVar);
        return aVar;
    }

    @SuppressLint({"LambdaLast"})
    public void a(j jVar, b bVar) {
        g lifecycle = jVar.getLifecycle();
        if (lifecycle.a() == g.b.DESTROYED) {
            return;
        }
        bVar.a(new LifecycleOnBackPressedCancellable(lifecycle, bVar));
    }

    public void a() {
        Iterator<b> descendingIterator = this.f600b.descendingIterator();
        while (descendingIterator.hasNext()) {
            b next = descendingIterator.next();
            if (next.b()) {
                next.a();
                return;
            }
        }
        Runnable runnable = this.f599a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
