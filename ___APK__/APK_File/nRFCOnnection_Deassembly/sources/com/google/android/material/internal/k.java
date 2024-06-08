package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class k {

    /* renamed from: c, reason: collision with root package name */
    private float f2613c;

    /* renamed from: f, reason: collision with root package name */
    private c.a.a.a.y.d f2616f;

    /* renamed from: a, reason: collision with root package name */
    private final TextPaint f2611a = new TextPaint(1);

    /* renamed from: b, reason: collision with root package name */
    private final c.a.a.a.y.f f2612b = new a();

    /* renamed from: d, reason: collision with root package name */
    private boolean f2614d = true;

    /* renamed from: e, reason: collision with root package name */
    private WeakReference<b> f2615e = new WeakReference<>(null);

    /* loaded from: classes.dex */
    public interface b {
        void a();

        int[] getState();

        boolean onStateChange(int[] iArr);
    }

    public k(b bVar) {
        a(bVar);
    }

    public TextPaint b() {
        return this.f2611a;
    }

    /* loaded from: classes.dex */
    class a extends c.a.a.a.y.f {
        a() {
        }

        @Override // c.a.a.a.y.f
        public void a(Typeface typeface, boolean z) {
            if (z) {
                return;
            }
            k.this.f2614d = true;
            b bVar = (b) k.this.f2615e.get();
            if (bVar != null) {
                bVar.a();
            }
        }

        @Override // c.a.a.a.y.f
        public void a(int i) {
            k.this.f2614d = true;
            b bVar = (b) k.this.f2615e.get();
            if (bVar != null) {
                bVar.a();
            }
        }
    }

    public void a(b bVar) {
        this.f2615e = new WeakReference<>(bVar);
    }

    public void a(boolean z) {
        this.f2614d = z;
    }

    public float a(String str) {
        if (!this.f2614d) {
            return this.f2613c;
        }
        this.f2613c = a((CharSequence) str);
        this.f2614d = false;
        return this.f2613c;
    }

    private float a(CharSequence charSequence) {
        if (charSequence == null) {
            return 0.0f;
        }
        return this.f2611a.measureText(charSequence, 0, charSequence.length());
    }

    public c.a.a.a.y.d a() {
        return this.f2616f;
    }

    public void a(c.a.a.a.y.d dVar, Context context) {
        if (this.f2616f != dVar) {
            this.f2616f = dVar;
            if (dVar != null) {
                dVar.c(context, this.f2611a, this.f2612b);
                b bVar = this.f2615e.get();
                if (bVar != null) {
                    this.f2611a.drawableState = bVar.getState();
                }
                dVar.b(context, this.f2611a, this.f2612b);
                this.f2614d = true;
            }
            b bVar2 = this.f2615e.get();
            if (bVar2 != null) {
                bVar2.a();
                bVar2.onStateChange(bVar2.getState());
            }
        }
    }

    public void a(Context context) {
        this.f2616f.b(context, this.f2611a, this.f2612b);
    }
}
