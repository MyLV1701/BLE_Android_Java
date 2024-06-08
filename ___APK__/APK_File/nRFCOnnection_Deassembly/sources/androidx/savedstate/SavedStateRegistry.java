package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.lifecycle.e;
import androidx.lifecycle.g;
import androidx.lifecycle.j;
import androidx.savedstate.Recreator;
import java.util.Map;

@SuppressLint({"RestrictedApi"})
/* loaded from: classes.dex */
public final class SavedStateRegistry {

    /* renamed from: b, reason: collision with root package name */
    private Bundle f1945b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f1946c;

    /* renamed from: d, reason: collision with root package name */
    private Recreator.a f1947d;

    /* renamed from: a, reason: collision with root package name */
    private a.b.a.b.b<String, b> f1944a = new a.b.a.b.b<>();

    /* renamed from: e, reason: collision with root package name */
    boolean f1948e = true;

    /* loaded from: classes.dex */
    public interface a {
        void a(androidx.savedstate.b bVar);
    }

    /* loaded from: classes.dex */
    public interface b {
        Bundle a();
    }

    public Bundle a(String str) {
        if (this.f1946c) {
            Bundle bundle = this.f1945b;
            if (bundle == null) {
                return null;
            }
            Bundle bundle2 = bundle.getBundle(str);
            this.f1945b.remove(str);
            if (this.f1945b.isEmpty()) {
                this.f1945b = null;
            }
            return bundle2;
        }
        throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
    }

    public void a(String str, b bVar) {
        if (this.f1944a.b(str, bVar) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }

    public void a(Class<? extends a> cls) {
        if (this.f1948e) {
            if (this.f1947d == null) {
                this.f1947d = new Recreator.a(this);
            }
            try {
                cls.getDeclaredConstructor(new Class[0]);
                this.f1947d.a(cls.getName());
                return;
            } catch (NoSuchMethodException e2) {
                throw new IllegalArgumentException("Class" + cls.getSimpleName() + " must have default constructor in order to be automatically recreated", e2);
            }
        }
        throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(g gVar, Bundle bundle) {
        if (!this.f1946c) {
            if (bundle != null) {
                this.f1945b = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
            }
            gVar.a(new e() { // from class: androidx.savedstate.SavedStateRegistry.1
                @Override // androidx.lifecycle.h
                public void a(j jVar, g.a aVar) {
                    if (aVar == g.a.ON_START) {
                        SavedStateRegistry.this.f1948e = true;
                    } else if (aVar == g.a.ON_STOP) {
                        SavedStateRegistry.this.f1948e = false;
                    }
                }
            });
            this.f1946c = true;
            return;
        }
        throw new IllegalStateException("SavedStateRegistry was already restored.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = this.f1945b;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        a.b.a.b.b<String, b>.d b2 = this.f1944a.b();
        while (b2.hasNext()) {
            Map.Entry next = b2.next();
            bundle2.putBundle((String) next.getKey(), ((b) next.getValue()).a());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }
}
