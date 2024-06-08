package androidx.recyclerview.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class g extends RecyclerView.n {

    /* renamed from: d, reason: collision with root package name */
    private static final int[] f1857d = {R.attr.listDivider};

    /* renamed from: a, reason: collision with root package name */
    private Drawable f1858a;

    /* renamed from: b, reason: collision with root package name */
    private int f1859b;

    /* renamed from: c, reason: collision with root package name */
    private final Rect f1860c = new Rect();

    public g(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(f1857d);
        this.f1858a = obtainStyledAttributes.getDrawable(0);
        if (this.f1858a == null) {
            Log.w("DividerItem", "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        obtainStyledAttributes.recycle();
        a(i);
    }

    private void c(Canvas canvas, RecyclerView recyclerView) {
        int height;
        int i;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            i = recyclerView.getPaddingTop();
            height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
            canvas.clipRect(recyclerView.getPaddingLeft(), i, recyclerView.getWidth() - recyclerView.getPaddingRight(), height);
        } else {
            height = recyclerView.getHeight();
            i = 0;
        }
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            recyclerView.getLayoutManager().b(childAt, this.f1860c);
            int round = this.f1860c.right + Math.round(childAt.getTranslationX());
            this.f1858a.setBounds(round - this.f1858a.getIntrinsicWidth(), i, round, height);
            this.f1858a.draw(canvas);
        }
        canvas.restore();
    }

    private void d(Canvas canvas, RecyclerView recyclerView) {
        int width;
        int i;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            i = recyclerView.getPaddingLeft();
            width = recyclerView.getWidth() - recyclerView.getPaddingRight();
            canvas.clipRect(i, recyclerView.getPaddingTop(), width, recyclerView.getHeight() - recyclerView.getPaddingBottom());
        } else {
            width = recyclerView.getWidth();
            i = 0;
        }
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            recyclerView.getDecoratedBoundsWithMargins(childAt, this.f1860c);
            int round = this.f1860c.bottom + Math.round(childAt.getTranslationY());
            this.f1858a.setBounds(i, round - this.f1858a.getIntrinsicHeight(), width, round);
            this.f1858a.draw(canvas);
        }
        canvas.restore();
    }

    public void a(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        this.f1859b = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void a(Canvas canvas, RecyclerView recyclerView, RecyclerView.a0 a0Var) {
        if (recyclerView.getLayoutManager() == null || this.f1858a == null) {
            return;
        }
        if (this.f1859b == 1) {
            d(canvas, recyclerView);
        } else {
            c(canvas, recyclerView);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void a(Rect rect, View view, RecyclerView recyclerView, RecyclerView.a0 a0Var) {
        Drawable drawable = this.f1858a;
        if (drawable == null) {
            rect.set(0, 0, 0, 0);
        } else if (this.f1859b == 1) {
            rect.set(0, 0, 0, drawable.getIntrinsicHeight());
        } else {
            rect.set(0, 0, drawable.getIntrinsicWidth(), 0);
        }
    }
}
