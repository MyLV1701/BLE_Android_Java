package a.g.a;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public abstract class c extends a {

    /* renamed from: b, reason: collision with root package name */
    private int f350b;

    /* renamed from: c, reason: collision with root package name */
    private int f351c;

    /* renamed from: d, reason: collision with root package name */
    private LayoutInflater f352d;

    @Deprecated
    public c(Context context, int i, Cursor cursor, boolean z) {
        super(context, cursor, z);
        this.f351c = i;
        this.f350b = i;
        this.f352d = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // a.g.a.a
    public View newDropDownView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f352d.inflate(this.f351c, viewGroup, false);
    }

    @Override // a.g.a.a
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f352d.inflate(this.f350b, viewGroup, false);
    }

    public c(Context context, int i, Cursor cursor, int i2) {
        super(context, cursor, i2);
        this.f351c = i;
        this.f350b = i;
        this.f352d = (LayoutInflater) context.getSystemService("layout_inflater");
    }
}
