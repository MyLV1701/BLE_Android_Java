package androidx.lifecycle;

import android.os.Bundle;
import androidx.lifecycle.g;
import androidx.savedstate.SavedStateRegistry;
import java.util.Iterator;

/* loaded from: classes.dex */
final class SavedStateHandleController implements h {

    /* renamed from: a, reason: collision with root package name */
    private final String f1523a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f1524b = false;

    /* renamed from: c, reason: collision with root package name */
    private final s f1525c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a implements SavedStateRegistry.a {
        a() {
        }

        @Override // androidx.savedstate.SavedStateRegistry.a
        public void a(androidx.savedstate.b bVar) {
            if (bVar instanceof x) {
                w viewModelStore = ((x) bVar).getViewModelStore();
                SavedStateRegistry savedStateRegistry = bVar.getSavedStateRegistry();
                Iterator<String> it = viewModelStore.b().iterator();
                while (it.hasNext()) {
                    SavedStateHandleController.a(viewModelStore.a(it.next()), savedStateRegistry, bVar.getLifecycle());
                }
                if (viewModelStore.b().isEmpty()) {
                    return;
                }
                savedStateRegistry.a(a.class);
                return;
            }
            throw new IllegalStateException("Internal error: OnRecreation should be registered only on componentsthat implement ViewModelStoreOwner");
        }
    }

    SavedStateHandleController(String str, s sVar) {
        this.f1523a = str;
        this.f1525c = sVar;
    }

    void a(SavedStateRegistry savedStateRegistry, g gVar) {
        if (!this.f1524b) {
            this.f1524b = true;
            gVar.a(this);
            savedStateRegistry.a(this.f1523a, this.f1525c.a());
            return;
        }
        throw new IllegalStateException("Already attached to lifecycleOwner");
    }

    boolean b() {
        return this.f1524b;
    }

    private static void b(final SavedStateRegistry savedStateRegistry, final g gVar) {
        g.b a2 = gVar.a();
        if (a2 != g.b.INITIALIZED && !a2.a(g.b.STARTED)) {
            gVar.a(new h() { // from class: androidx.lifecycle.SavedStateHandleController.1
                @Override // androidx.lifecycle.h
                public void a(j jVar, g.a aVar) {
                    if (aVar == g.a.ON_START) {
                        g.this.b(this);
                        savedStateRegistry.a(a.class);
                    }
                }
            });
        } else {
            savedStateRegistry.a(a.class);
        }
    }

    @Override // androidx.lifecycle.h
    public void a(j jVar, g.a aVar) {
        if (aVar == g.a.ON_DESTROY) {
            this.f1524b = false;
            jVar.getLifecycle().b(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public s a() {
        return this.f1525c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SavedStateHandleController a(SavedStateRegistry savedStateRegistry, g gVar, String str, Bundle bundle) {
        SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, s.a(savedStateRegistry.a(str), bundle));
        savedStateHandleController.a(savedStateRegistry, gVar);
        b(savedStateRegistry, gVar);
        return savedStateHandleController;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(u uVar, SavedStateRegistry savedStateRegistry, g gVar) {
        SavedStateHandleController savedStateHandleController = (SavedStateHandleController) uVar.a("androidx.lifecycle.savedstate.vm.tag");
        if (savedStateHandleController == null || savedStateHandleController.b()) {
            return;
        }
        savedStateHandleController.a(savedStateRegistry, gVar);
        b(savedStateRegistry, gVar);
    }
}
