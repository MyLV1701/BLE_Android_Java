package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.g;

/* loaded from: classes.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private final b f1950a;

    /* renamed from: b, reason: collision with root package name */
    private final SavedStateRegistry f1951b = new SavedStateRegistry();

    private a(b bVar) {
        this.f1950a = bVar;
    }

    public SavedStateRegistry a() {
        return this.f1951b;
    }

    public void b(Bundle bundle) {
        this.f1951b.a(bundle);
    }

    public void a(Bundle bundle) {
        g lifecycle = this.f1950a.getLifecycle();
        if (lifecycle.a() == g.b.INITIALIZED) {
            lifecycle.a(new Recreator(this.f1950a));
            this.f1951b.a(lifecycle, bundle);
            return;
        }
        throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
    }

    public static a a(b bVar) {
        return new a(bVar);
    }
}
