package a.f.l;

import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public class q {

    /* renamed from: a, reason: collision with root package name */
    private int f325a;

    /* renamed from: b, reason: collision with root package name */
    private int f326b;

    public q(ViewGroup viewGroup) {
    }

    public void a(View view, View view2, int i) {
        a(view, view2, i, 0);
    }

    public void a(View view, View view2, int i, int i2) {
        if (i2 == 1) {
            this.f326b = i;
        } else {
            this.f325a = i;
        }
    }

    public int a() {
        return this.f325a | this.f326b;
    }

    public void a(View view) {
        a(view, 0);
    }

    public void a(View view, int i) {
        if (i == 1) {
            this.f326b = 0;
        } else {
            this.f325a = 0;
        }
    }
}
