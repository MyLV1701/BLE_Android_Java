package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class f extends Drawable.ConstantState {

    /* renamed from: a, reason: collision with root package name */
    int f1292a;

    /* renamed from: b, reason: collision with root package name */
    Drawable.ConstantState f1293b;

    /* renamed from: c, reason: collision with root package name */
    ColorStateList f1294c;

    /* renamed from: d, reason: collision with root package name */
    PorterDuff.Mode f1295d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(f fVar) {
        this.f1294c = null;
        this.f1295d = d.h;
        if (fVar != null) {
            this.f1292a = fVar.f1292a;
            this.f1293b = fVar.f1293b;
            this.f1294c = fVar.f1294c;
            this.f1295d = fVar.f1295d;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a() {
        return this.f1293b != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i = this.f1292a;
        Drawable.ConstantState constantState = this.f1293b;
        return i | (constantState != null ? constantState.getChangingConfigurations() : 0);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable() {
        return newDrawable(null);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable(Resources resources) {
        if (Build.VERSION.SDK_INT >= 21) {
            return new e(this, resources);
        }
        return new d(this, resources);
    }
}
