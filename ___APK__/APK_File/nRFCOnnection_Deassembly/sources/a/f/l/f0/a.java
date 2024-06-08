package a.f.l.f0;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* loaded from: classes.dex */
public final class a extends ClickableSpan {

    /* renamed from: b, reason: collision with root package name */
    private final int f303b;

    /* renamed from: c, reason: collision with root package name */
    private final c f304c;

    /* renamed from: d, reason: collision with root package name */
    private final int f305d;

    public a(int i, c cVar, int i2) {
        this.f303b = i;
        this.f304c = cVar;
        this.f305d = i2;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f303b);
        this.f304c.a(this.f305d, bundle);
    }
}
