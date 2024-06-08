package c.a.a.a.b0;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class m {

    /* renamed from: a, reason: collision with root package name */
    @Deprecated
    public float f2071a;

    /* renamed from: b, reason: collision with root package name */
    @Deprecated
    public float f2072b;

    /* renamed from: c, reason: collision with root package name */
    @Deprecated
    public float f2073c;

    /* renamed from: d, reason: collision with root package name */
    @Deprecated
    public float f2074d;

    /* renamed from: e, reason: collision with root package name */
    @Deprecated
    public float f2075e;

    /* renamed from: f, reason: collision with root package name */
    @Deprecated
    public float f2076f;
    private final List<f> g = new ArrayList();
    private final List<g> h = new ArrayList();

    /* loaded from: classes.dex */
    class a extends g {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ List f2077b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Matrix f2078c;

        a(m mVar, List list, Matrix matrix) {
            this.f2077b = list;
            this.f2078c = matrix;
        }

        @Override // c.a.a.a.b0.m.g
        public void a(Matrix matrix, c.a.a.a.a0.a aVar, int i, Canvas canvas) {
            Iterator it = this.f2077b.iterator();
            while (it.hasNext()) {
                ((g) it.next()).a(this.f2078c, aVar, i, canvas);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b extends g {

        /* renamed from: b, reason: collision with root package name */
        private final d f2079b;

        public b(d dVar) {
            this.f2079b = dVar;
        }

        @Override // c.a.a.a.b0.m.g
        public void a(Matrix matrix, c.a.a.a.a0.a aVar, int i, Canvas canvas) {
            aVar.a(canvas, matrix, new RectF(this.f2079b.b(), this.f2079b.f(), this.f2079b.c(), this.f2079b.a()), i, this.f2079b.d(), this.f2079b.e());
        }
    }

    /* loaded from: classes.dex */
    public static class d extends f {
        private static final RectF h = new RectF();

        /* renamed from: b, reason: collision with root package name */
        @Deprecated
        public float f2083b;

        /* renamed from: c, reason: collision with root package name */
        @Deprecated
        public float f2084c;

        /* renamed from: d, reason: collision with root package name */
        @Deprecated
        public float f2085d;

        /* renamed from: e, reason: collision with root package name */
        @Deprecated
        public float f2086e;

        /* renamed from: f, reason: collision with root package name */
        @Deprecated
        public float f2087f;

        @Deprecated
        public float g;

        public d(float f2, float f3, float f4, float f5) {
            b(f2);
            f(f3);
            c(f4);
            a(f5);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float c() {
            return this.f2085d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float d() {
            return this.f2087f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float e() {
            return this.g;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float f() {
            return this.f2084c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float b() {
            return this.f2083b;
        }

        private void c(float f2) {
            this.f2085d = f2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(float f2) {
            this.f2087f = f2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(float f2) {
            this.g = f2;
        }

        private void f(float f2) {
            this.f2084c = f2;
        }

        @Override // c.a.a.a.b0.m.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f2090a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            h.set(b(), f(), c(), a());
            path.arcTo(h, d(), e(), false);
            path.transform(matrix);
        }

        private void b(float f2) {
            this.f2083b = f2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float a() {
            return this.f2086e;
        }

        private void a(float f2) {
            this.f2086e = f2;
        }
    }

    /* loaded from: classes.dex */
    public static class e extends f {

        /* renamed from: b, reason: collision with root package name */
        private float f2088b;

        /* renamed from: c, reason: collision with root package name */
        private float f2089c;

        @Override // c.a.a.a.b0.m.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f2090a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.f2088b, this.f2089c);
            path.transform(matrix);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class f {

        /* renamed from: a, reason: collision with root package name */
        protected final Matrix f2090a = new Matrix();

        public abstract void a(Matrix matrix, Path path);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class g {

        /* renamed from: a, reason: collision with root package name */
        static final Matrix f2091a = new Matrix();

        g() {
        }

        public abstract void a(Matrix matrix, c.a.a.a.a0.a aVar, int i, Canvas canvas);

        public final void a(c.a.a.a.a0.a aVar, int i, Canvas canvas) {
            a(f2091a, aVar, i, canvas);
        }
    }

    public m() {
        b(0.0f, 0.0f);
    }

    private float e() {
        return this.f2075e;
    }

    private float f() {
        return this.f2076f;
    }

    private void g(float f2) {
        this.f2072b = f2;
    }

    public void a(float f2, float f3, float f4, float f5) {
        f(f2);
        g(f3);
        d(f2);
        e(f3);
        b(f4);
        c((f4 + f5) % 360.0f);
        this.g.clear();
        this.h.clear();
    }

    public void b(float f2, float f3) {
        a(f2, f3, 270.0f, 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float c() {
        return this.f2071a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float d() {
        return this.f2072b;
    }

    private void c(float f2) {
        this.f2076f = f2;
    }

    private void d(float f2) {
        this.f2073c = f2;
    }

    private void e(float f2) {
        this.f2074d = f2;
    }

    private void f(float f2) {
        this.f2071a = f2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float b() {
        return this.f2074d;
    }

    private void b(float f2) {
        this.f2075e = f2;
    }

    /* loaded from: classes.dex */
    static class c extends g {

        /* renamed from: b, reason: collision with root package name */
        private final e f2080b;

        /* renamed from: c, reason: collision with root package name */
        private final float f2081c;

        /* renamed from: d, reason: collision with root package name */
        private final float f2082d;

        public c(e eVar, float f2, float f3) {
            this.f2080b = eVar;
            this.f2081c = f2;
            this.f2082d = f3;
        }

        @Override // c.a.a.a.b0.m.g
        public void a(Matrix matrix, c.a.a.a.a0.a aVar, int i, Canvas canvas) {
            RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(this.f2080b.f2089c - this.f2082d, this.f2080b.f2088b - this.f2081c), 0.0f);
            Matrix matrix2 = new Matrix(matrix);
            matrix2.preTranslate(this.f2081c, this.f2082d);
            matrix2.preRotate(a());
            aVar.a(canvas, matrix2, rectF, i);
        }

        float a() {
            return (float) Math.toDegrees(Math.atan((this.f2080b.f2089c - this.f2082d) / (this.f2080b.f2088b - this.f2081c)));
        }
    }

    public void a(float f2, float f3) {
        e eVar = new e();
        eVar.f2088b = f2;
        eVar.f2089c = f3;
        this.g.add(eVar);
        c cVar = new c(eVar, a(), b());
        a(cVar, cVar.a() + 270.0f, cVar.a() + 270.0f);
        d(f2);
        e(f3);
    }

    public void a(float f2, float f3, float f4, float f5, float f6, float f7) {
        d dVar = new d(f2, f3, f4, f5);
        dVar.d(f6);
        dVar.e(f7);
        this.g.add(dVar);
        b bVar = new b(dVar);
        float f8 = f6 + f7;
        boolean z = f7 < 0.0f;
        if (z) {
            f6 = (f6 + 180.0f) % 360.0f;
        }
        a(bVar, f6, z ? (180.0f + f8) % 360.0f : f8);
        double d2 = f8;
        d(((f2 + f4) * 0.5f) + (((f4 - f2) / 2.0f) * ((float) Math.cos(Math.toRadians(d2)))));
        e(((f3 + f5) * 0.5f) + (((f5 - f3) / 2.0f) * ((float) Math.sin(Math.toRadians(d2)))));
    }

    public void a(Matrix matrix, Path path) {
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            this.g.get(i).a(matrix, path);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public g a(Matrix matrix) {
        a(f());
        return new a(this, new ArrayList(this.h), matrix);
    }

    private void a(g gVar, float f2, float f3) {
        a(f2);
        this.h.add(gVar);
        b(f3);
    }

    private void a(float f2) {
        if (e() == f2) {
            return;
        }
        float e2 = ((f2 - e()) + 360.0f) % 360.0f;
        if (e2 > 180.0f) {
            return;
        }
        d dVar = new d(a(), b(), a(), b());
        dVar.d(e());
        dVar.e(e2);
        this.h.add(new b(dVar));
        b(f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float a() {
        return this.f2073c;
    }
}
