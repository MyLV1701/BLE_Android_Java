package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.datepicker.g;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class p extends RecyclerView.g<b> {

    /* renamed from: a, reason: collision with root package name */
    private final g<?> f2511a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements View.OnClickListener {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f2512b;

        a(int i) {
            this.f2512b = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            p.this.f2511a.a(i.a(this.f2512b, p.this.f2511a.c().f2494d));
            p.this.f2511a.a(g.k.DAY);
        }
    }

    /* loaded from: classes.dex */
    public static class b extends RecyclerView.d0 {

        /* renamed from: a, reason: collision with root package name */
        final TextView f2514a;

        b(TextView textView) {
            super(textView);
            this.f2514a = textView;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(g<?> gVar) {
        this.f2511a = gVar;
    }

    private View.OnClickListener c(int i) {
        return new a(i);
    }

    int b(int i) {
        return this.f2511a.a().h().f2495e + i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemCount() {
        return this.f2511a.a().i();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(b bVar, int i) {
        int b2 = b(i);
        String string = bVar.f2514a.getContext().getString(c.a.a.a.j.mtrl_picker_navigate_to_year_description);
        bVar.f2514a.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(b2)));
        bVar.f2514a.setContentDescription(String.format(string, Integer.valueOf(b2)));
        c b3 = this.f2511a.b();
        Calendar d2 = o.d();
        com.google.android.material.datepicker.b bVar2 = d2.get(1) == b2 ? b3.f2465f : b3.f2463d;
        Iterator<Long> it = this.f2511a.d().b().iterator();
        while (it.hasNext()) {
            d2.setTimeInMillis(it.next().longValue());
            if (d2.get(1) == b2) {
                bVar2 = b3.f2464e;
            }
        }
        bVar2.a(bVar.f2514a);
        bVar.f2514a.setOnClickListener(c(b2));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public b onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new b((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(c.a.a.a.h.mtrl_calendar_year, viewGroup, false));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a(int i) {
        return i - this.f2511a.a().h().f2495e;
    }
}
