package c.a.a.a.b0;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

/* loaded from: classes.dex */
public class l {

    /* renamed from: a, reason: collision with root package name */
    private final m[] f2060a = new m[4];

    /* renamed from: b, reason: collision with root package name */
    private final Matrix[] f2061b = new Matrix[4];

    /* renamed from: c, reason: collision with root package name */
    private final Matrix[] f2062c = new Matrix[4];

    /* renamed from: d, reason: collision with root package name */
    private final PointF f2063d = new PointF();

    /* renamed from: e, reason: collision with root package name */
    private final m f2064e = new m();

    /* renamed from: f, reason: collision with root package name */
    private final float[] f2065f = new float[2];
    private final float[] g = new float[2];

    /* loaded from: classes.dex */
    public interface a {
        void a(m mVar, Matrix matrix, int i);

        void b(m mVar, Matrix matrix, int i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        public final k f2066a;

        /* renamed from: b, reason: collision with root package name */
        public final Path f2067b;

        /* renamed from: c, reason: collision with root package name */
        public final RectF f2068c;

        /* renamed from: d, reason: collision with root package name */
        public final a f2069d;

        /* renamed from: e, reason: collision with root package name */
        public final float f2070e;

        b(k kVar, float f2, RectF rectF, a aVar, Path path) {
            this.f2069d = aVar;
            this.f2066a = kVar;
            this.f2070e = f2;
            this.f2068c = rectF;
            this.f2067b = path;
        }
    }

    public l() {
        for (int i = 0; i < 4; i++) {
            this.f2060a[i] = new m();
            this.f2061b[i] = new Matrix();
            this.f2062c[i] = new Matrix();
        }
    }

    private float a(int i) {
        return (i + 1) * 90;
    }

    private void b(int i) {
        this.f2065f[0] = this.f2060a[i].a();
        this.f2065f[1] = this.f2060a[i].b();
        this.f2061b[i].mapPoints(this.f2065f);
        float a2 = a(i);
        this.f2062c[i].reset();
        Matrix matrix = this.f2062c[i];
        float[] fArr = this.f2065f;
        matrix.setTranslate(fArr[0], fArr[1]);
        this.f2062c[i].preRotate(a2);
    }

    private void c(b bVar, int i) {
        b(i, bVar.f2066a).a(this.f2060a[i], 90.0f, bVar.f2070e, bVar.f2068c, a(i, bVar.f2066a));
        float a2 = a(i);
        this.f2061b[i].reset();
        a(i, bVar.f2068c, this.f2063d);
        Matrix matrix = this.f2061b[i];
        PointF pointF = this.f2063d;
        matrix.setTranslate(pointF.x, pointF.y);
        this.f2061b[i].preRotate(a2);
    }

    public void a(k kVar, float f2, RectF rectF, Path path) {
        a(kVar, f2, rectF, null, path);
    }

    public void a(k kVar, float f2, RectF rectF, a aVar, Path path) {
        path.rewind();
        b bVar = new b(kVar, f2, rectF, aVar, path);
        for (int i = 0; i < 4; i++) {
            c(bVar, i);
            b(i);
        }
        for (int i2 = 0; i2 < 4; i2++) {
            a(bVar, i2);
            b(bVar, i2);
        }
        path.close();
    }

    private void b(b bVar, int i) {
        int i2 = (i + 1) % 4;
        this.f2065f[0] = this.f2060a[i].a();
        this.f2065f[1] = this.f2060a[i].b();
        this.f2061b[i].mapPoints(this.f2065f);
        this.g[0] = this.f2060a[i2].c();
        this.g[1] = this.f2060a[i2].d();
        this.f2061b[i2].mapPoints(this.g);
        float f2 = this.f2065f[0];
        float[] fArr = this.g;
        float max = Math.max(((float) Math.hypot(f2 - fArr[0], r0[1] - fArr[1])) - 0.001f, 0.0f);
        float a2 = a(bVar.f2068c, i);
        this.f2064e.b(0.0f, 0.0f);
        c(i, bVar.f2066a).a(max, a2, bVar.f2070e, this.f2064e);
        this.f2064e.a(this.f2062c[i], bVar.f2067b);
        a aVar = bVar.f2069d;
        if (aVar != null) {
            aVar.a(this.f2064e, this.f2062c[i], i);
        }
    }

    private void a(b bVar, int i) {
        this.f2065f[0] = this.f2060a[i].c();
        this.f2065f[1] = this.f2060a[i].d();
        this.f2061b[i].mapPoints(this.f2065f);
        if (i == 0) {
            Path path = bVar.f2067b;
            float[] fArr = this.f2065f;
            path.moveTo(fArr[0], fArr[1]);
        } else {
            Path path2 = bVar.f2067b;
            float[] fArr2 = this.f2065f;
            path2.lineTo(fArr2[0], fArr2[1]);
        }
        this.f2060a[i].a(this.f2061b[i], bVar.f2067b);
        a aVar = bVar.f2069d;
        if (aVar != null) {
            aVar.b(this.f2060a[i], this.f2061b[i], i);
        }
    }

    private f c(int i, k kVar) {
        if (i == 1) {
            return kVar.a();
        }
        if (i == 2) {
            return kVar.f();
        }
        if (i != 3) {
            return kVar.g();
        }
        return kVar.h();
    }

    private float a(RectF rectF, int i) {
        float[] fArr = this.f2065f;
        m[] mVarArr = this.f2060a;
        fArr[0] = mVarArr[i].f2073c;
        fArr[1] = mVarArr[i].f2074d;
        this.f2061b[i].mapPoints(fArr);
        if (i != 1 && i != 3) {
            return Math.abs(rectF.centerY() - this.f2065f[1]);
        }
        return Math.abs(rectF.centerX() - this.f2065f[0]);
    }

    private c a(int i, k kVar) {
        if (i == 1) {
            return kVar.e();
        }
        if (i == 2) {
            return kVar.c();
        }
        if (i != 3) {
            return kVar.l();
        }
        return kVar.j();
    }

    private d b(int i, k kVar) {
        if (i == 1) {
            return kVar.d();
        }
        if (i == 2) {
            return kVar.b();
        }
        if (i != 3) {
            return kVar.k();
        }
        return kVar.i();
    }

    private void a(int i, RectF rectF, PointF pointF) {
        if (i == 1) {
            pointF.set(rectF.right, rectF.bottom);
            return;
        }
        if (i == 2) {
            pointF.set(rectF.left, rectF.bottom);
        } else if (i != 3) {
            pointF.set(rectF.right, rectF.top);
        } else {
            pointF.set(rectF.left, rectF.top);
        }
    }
}
