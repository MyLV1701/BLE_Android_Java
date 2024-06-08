package a.k.a;

import a.d.h;
import a.k.a.a;
import a.k.b.c;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.lifecycle.j;
import androidx.lifecycle.o;
import androidx.lifecycle.p;
import androidx.lifecycle.u;
import androidx.lifecycle.v;
import androidx.lifecycle.w;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b extends a.k.a.a {

    /* renamed from: c, reason: collision with root package name */
    static boolean f380c = false;

    /* renamed from: a, reason: collision with root package name */
    private final j f381a;

    /* renamed from: b, reason: collision with root package name */
    private final c f382b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c extends u {

        /* renamed from: e, reason: collision with root package name */
        private static final v.b f386e = new a();

        /* renamed from: c, reason: collision with root package name */
        private h<a> f387c = new h<>();

        /* renamed from: d, reason: collision with root package name */
        private boolean f388d = false;

        /* loaded from: classes.dex */
        static class a implements v.b {
            a() {
            }

            @Override // androidx.lifecycle.v.b
            public <T extends u> T a(Class<T> cls) {
                return new c();
            }
        }

        c() {
        }

        static c a(w wVar) {
            return (c) new v(wVar, f386e).a(c.class);
        }

        void b(int i) {
            this.f387c.d(i);
        }

        void c() {
            this.f388d = false;
        }

        boolean d() {
            return this.f388d;
        }

        void e() {
            int b2 = this.f387c.b();
            for (int i = 0; i < b2; i++) {
                this.f387c.e(i).f();
            }
        }

        void f() {
            this.f388d = true;
        }

        void a(int i, a aVar) {
            this.f387c.c(i, aVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.lifecycle.u
        public void b() {
            super.b();
            int b2 = this.f387c.b();
            for (int i = 0; i < b2; i++) {
                this.f387c.e(i).a(true);
            }
            this.f387c.a();
        }

        <D> a<D> a(int i) {
            return this.f387c.a(i);
        }

        public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f387c.b() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i = 0; i < this.f387c.b(); i++) {
                    a e2 = this.f387c.e(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.f387c.c(i));
                    printWriter.print(": ");
                    printWriter.println(e2.toString());
                    e2.a(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(j jVar, w wVar) {
        this.f381a = jVar;
        this.f382b = c.a(wVar);
    }

    private <D> a.k.b.c<D> a(int i, Bundle bundle, a.InterfaceC0025a<D> interfaceC0025a, a.k.b.c<D> cVar) {
        try {
            this.f382b.f();
            a.k.b.c<D> onCreateLoader = interfaceC0025a.onCreateLoader(i, bundle);
            if (onCreateLoader != null) {
                if (onCreateLoader.getClass().isMemberClass() && !Modifier.isStatic(onCreateLoader.getClass().getModifiers())) {
                    throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + onCreateLoader);
                }
                a aVar = new a(i, bundle, onCreateLoader, cVar);
                if (f380c) {
                    Log.v("LoaderManager", "  Created new loader " + aVar);
                }
                this.f382b.a(i, aVar);
                this.f382b.c();
                return aVar.a(this.f381a, interfaceC0025a);
            }
            throw new IllegalArgumentException("Object returned from onCreateLoader must not be null");
        } catch (Throwable th) {
            this.f382b.c();
            throw th;
        }
    }

    @Override // a.k.a.a
    public <D> a.k.b.c<D> b(int i, Bundle bundle, a.InterfaceC0025a<D> interfaceC0025a) {
        if (!this.f382b.d()) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                if (f380c) {
                    Log.v("LoaderManager", "restartLoader in " + this + ": args=" + bundle);
                }
                a<D> a2 = this.f382b.a(i);
                return a(i, bundle, interfaceC0025a, a2 != null ? a2.a(false) : null);
            }
            throw new IllegalStateException("restartLoader must be called on the main thread");
        }
        throw new IllegalStateException("Called while creating a loader");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        a.f.k.a.a(this.f381a, sb);
        sb.append("}}");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a.k.a.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0026b<D> implements p<D> {

        /* renamed from: a, reason: collision with root package name */
        private final a.k.b.c<D> f383a;

        /* renamed from: b, reason: collision with root package name */
        private final a.InterfaceC0025a<D> f384b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f385c = false;

        C0026b(a.k.b.c<D> cVar, a.InterfaceC0025a<D> interfaceC0025a) {
            this.f383a = cVar;
            this.f384b = interfaceC0025a;
        }

        @Override // androidx.lifecycle.p
        public void a(D d2) {
            if (b.f380c) {
                Log.v("LoaderManager", "  onLoadFinished in " + this.f383a + ": " + this.f383a.dataToString(d2));
            }
            this.f384b.onLoadFinished(this.f383a, d2);
            this.f385c = true;
        }

        void b() {
            if (this.f385c) {
                if (b.f380c) {
                    Log.v("LoaderManager", "  Resetting: " + this.f383a);
                }
                this.f384b.onLoaderReset(this.f383a);
            }
        }

        public String toString() {
            return this.f384b.toString();
        }

        boolean a() {
            return this.f385c;
        }

        public void a(String str, PrintWriter printWriter) {
            printWriter.print(str);
            printWriter.print("mDeliveredData=");
            printWriter.println(this.f385c);
        }
    }

    /* loaded from: classes.dex */
    public static class a<D> extends o<D> implements c.InterfaceC0028c<D> {
        private final int k;
        private final Bundle l;
        private final a.k.b.c<D> m;
        private j n;
        private C0026b<D> o;
        private a.k.b.c<D> p;

        a(int i, Bundle bundle, a.k.b.c<D> cVar, a.k.b.c<D> cVar2) {
            this.k = i;
            this.l = bundle;
            this.m = cVar;
            this.p = cVar2;
            this.m.registerListener(i, this);
        }

        a.k.b.c<D> a(j jVar, a.InterfaceC0025a<D> interfaceC0025a) {
            C0026b<D> c0026b = new C0026b<>(this.m, interfaceC0025a);
            a(jVar, c0026b);
            C0026b<D> c0026b2 = this.o;
            if (c0026b2 != null) {
                a((p) c0026b2);
            }
            this.n = jVar;
            this.o = c0026b;
            return this.m;
        }

        @Override // androidx.lifecycle.o, androidx.lifecycle.LiveData
        public void b(D d2) {
            super.b((a<D>) d2);
            a.k.b.c<D> cVar = this.p;
            if (cVar != null) {
                cVar.reset();
                this.p = null;
            }
        }

        @Override // androidx.lifecycle.LiveData
        protected void c() {
            if (b.f380c) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            this.m.startLoading();
        }

        @Override // androidx.lifecycle.LiveData
        protected void d() {
            if (b.f380c) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            this.m.stopLoading();
        }

        a.k.b.c<D> e() {
            return this.m;
        }

        void f() {
            j jVar = this.n;
            C0026b<D> c0026b = this.o;
            if (jVar == null || c0026b == null) {
                return;
            }
            super.a((p) c0026b);
            a(jVar, c0026b);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.k);
            sb.append(" : ");
            a.f.k.a.a(this.m, sb);
            sb.append("}}");
            return sb.toString();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void a(p<? super D> pVar) {
            super.a((p) pVar);
            this.n = null;
            this.o = null;
        }

        a.k.b.c<D> a(boolean z) {
            if (b.f380c) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            this.m.cancelLoad();
            this.m.abandon();
            C0026b<D> c0026b = this.o;
            if (c0026b != null) {
                a((p) c0026b);
                if (z) {
                    c0026b.b();
                }
            }
            this.m.unregisterListener(this);
            if ((c0026b != null && !c0026b.a()) || z) {
                this.m.reset();
                return this.p;
            }
            return this.m;
        }

        @Override // a.k.b.c.InterfaceC0028c
        public void a(a.k.b.c<D> cVar, D d2) {
            if (b.f380c) {
                Log.v("LoaderManager", "onLoadComplete: " + this);
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                b((a<D>) d2);
                return;
            }
            if (b.f380c) {
                Log.w("LoaderManager", "onLoadComplete was incorrectly called on a background thread");
            }
            a((a<D>) d2);
        }

        public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.k);
            printWriter.print(" mArgs=");
            printWriter.println(this.l);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.m);
            this.m.dump(str + "  ", fileDescriptor, printWriter, strArr);
            if (this.o != null) {
                printWriter.print(str);
                printWriter.print("mCallbacks=");
                printWriter.println(this.o);
                this.o.a(str + "  ", printWriter);
            }
            printWriter.print(str);
            printWriter.print("mData=");
            printWriter.println(e().dataToString(a()));
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.println(b());
        }
    }

    @Override // a.k.a.a
    public <D> a.k.b.c<D> a(int i, Bundle bundle, a.InterfaceC0025a<D> interfaceC0025a) {
        if (!this.f382b.d()) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                a<D> a2 = this.f382b.a(i);
                if (f380c) {
                    Log.v("LoaderManager", "initLoader in " + this + ": args=" + bundle);
                }
                if (a2 == null) {
                    return a(i, bundle, interfaceC0025a, (a.k.b.c) null);
                }
                if (f380c) {
                    Log.v("LoaderManager", "  Re-using existing loader " + a2);
                }
                return a2.a(this.f381a, interfaceC0025a);
            }
            throw new IllegalStateException("initLoader must be called on the main thread");
        }
        throw new IllegalStateException("Called while creating a loader");
    }

    @Override // a.k.a.a
    public void a(int i) {
        if (!this.f382b.d()) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                if (f380c) {
                    Log.v("LoaderManager", "destroyLoader in " + this + " of " + i);
                }
                a a2 = this.f382b.a(i);
                if (a2 != null) {
                    a2.a(true);
                    this.f382b.b(i);
                    return;
                }
                return;
            }
            throw new IllegalStateException("destroyLoader must be called on the main thread");
        }
        throw new IllegalStateException("Called while creating a loader");
    }

    @Override // a.k.a.a
    public void a() {
        this.f382b.e();
    }

    @Override // a.k.a.a
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f382b.a(str, fileDescriptor, printWriter, strArr);
    }
}
