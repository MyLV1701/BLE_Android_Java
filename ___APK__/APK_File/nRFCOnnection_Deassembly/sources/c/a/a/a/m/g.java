package c.a.a.a.m;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

/* loaded from: classes.dex */
public class g implements TypeEvaluator<Matrix> {

    /* renamed from: a, reason: collision with root package name */
    private final float[] f2103a = new float[9];

    /* renamed from: b, reason: collision with root package name */
    private final float[] f2104b = new float[9];

    /* renamed from: c, reason: collision with root package name */
    private final Matrix f2105c = new Matrix();

    public Matrix a(float f2, Matrix matrix, Matrix matrix2) {
        matrix.getValues(this.f2103a);
        matrix2.getValues(this.f2104b);
        for (int i = 0; i < 9; i++) {
            float[] fArr = this.f2104b;
            float f3 = fArr[i];
            float[] fArr2 = this.f2103a;
            fArr[i] = fArr2[i] + ((f3 - fArr2[i]) * f2);
        }
        this.f2105c.setValues(this.f2104b);
        return this.f2105c;
    }
}
