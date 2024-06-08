package com.google.android.material.datepicker;

import a.f.l.w;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.datepicker.g;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class k extends RecyclerView.g<b> {

    /* renamed from: a, reason: collision with root package name */
    private final com.google.android.material.datepicker.a f2502a;

    /* renamed from: b, reason: collision with root package name */
    private final d<?> f2503b;

    /* renamed from: c, reason: collision with root package name */
    private final g.l f2504c;

    /* renamed from: d, reason: collision with root package name */
    private final int f2505d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements AdapterView.OnItemClickListener {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ MaterialCalendarGridView f2506b;

        a(MaterialCalendarGridView materialCalendarGridView) {
            this.f2506b = materialCalendarGridView;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.f2506b.getAdapter().e(i)) {
                k.this.f2504c.a(this.f2506b.getAdapter().getItem(i).longValue());
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b extends RecyclerView.d0 {

        /* renamed from: a, reason: collision with root package name */
        final TextView f2508a;

        /* renamed from: b, reason: collision with root package name */
        final MaterialCalendarGridView f2509b;

        b(LinearLayout linearLayout, boolean z) {
            super(linearLayout);
            this.f2508a = (TextView) linearLayout.findViewById(c.a.a.a.f.month_title);
            w.a((View) this.f2508a, true);
            this.f2509b = (MaterialCalendarGridView) linearLayout.findViewById(c.a.a.a.f.month_grid);
            if (z) {
                return;
            }
            this.f2508a.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(Context context, d<?> dVar, com.google.android.material.datepicker.a aVar, g.l lVar) {
        i h = aVar.h();
        i e2 = aVar.e();
        i g = aVar.g();
        if (h.compareTo(g) <= 0) {
            if (g.compareTo(e2) <= 0) {
                this.f2505d = (j.f2497f * g.a(context)) + (h.a(context) ? g.a(context) : 0);
                this.f2502a = aVar;
                this.f2503b = dVar;
                this.f2504c = lVar;
                setHasStableIds(true);
                return;
            }
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        throw new IllegalArgumentException("firstPage cannot be after currentPage");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharSequence b(int i) {
        return a(i).e();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemCount() {
        return this.f2502a.f();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long getItemId(int i) {
        return this.f2502a.h().b(i).f();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(b bVar, int i) {
        i b2 = this.f2502a.h().b(i);
        bVar.f2508a.setText(b2.e());
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) bVar.f2509b.findViewById(c.a.a.a.f.month_grid);
        if (materialCalendarGridView.getAdapter() != null && b2.equals(materialCalendarGridView.getAdapter().f2498b)) {
            materialCalendarGridView.getAdapter().notifyDataSetChanged();
        } else {
            j jVar = new j(b2, this.f2503b, this.f2502a);
            materialCalendarGridView.setNumColumns(b2.f2496f);
            materialCalendarGridView.setAdapter((ListAdapter) jVar);
        }
        materialCalendarGridView.setOnItemClickListener(new a(materialCalendarGridView));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public b onCreateViewHolder(ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(c.a.a.a.h.mtrl_calendar_month_labeled, viewGroup, false);
        if (h.a(viewGroup.getContext())) {
            linearLayout.setLayoutParams(new RecyclerView.p(-1, this.f2505d));
            return new b(linearLayout, true);
        }
        return new b(linearLayout, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i a(int i) {
        return this.f2502a.h().b(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a(i iVar) {
        return this.f2502a.h().b(iVar);
    }
}
