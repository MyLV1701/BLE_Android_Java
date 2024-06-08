package androidx.lifecycle;

import androidx.lifecycle.g;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class FullLifecycleObserverAdapter implements h {

    /* renamed from: a, reason: collision with root package name */
    private final c f1504a;

    /* renamed from: b, reason: collision with root package name */
    private final h f1505b;

    /* loaded from: classes.dex */
    static /* synthetic */ class a {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1506a = new int[g.a.values().length];

        static {
            try {
                f1506a[g.a.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1506a[g.a.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1506a[g.a.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1506a[g.a.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1506a[g.a.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1506a[g.a.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1506a[g.a.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FullLifecycleObserverAdapter(c cVar, h hVar) {
        this.f1504a = cVar;
        this.f1505b = hVar;
    }

    @Override // androidx.lifecycle.h
    public void a(j jVar, g.a aVar) {
        switch (a.f1506a[aVar.ordinal()]) {
            case 1:
                this.f1504a.c(jVar);
                break;
            case 2:
                this.f1504a.e(jVar);
                break;
            case 3:
                this.f1504a.a(jVar);
                break;
            case 4:
                this.f1504a.d(jVar);
                break;
            case 5:
                this.f1504a.f(jVar);
                break;
            case 6:
                this.f1504a.b(jVar);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        h hVar = this.f1505b;
        if (hVar != null) {
            hVar.a(jVar, aVar);
        }
    }
}
