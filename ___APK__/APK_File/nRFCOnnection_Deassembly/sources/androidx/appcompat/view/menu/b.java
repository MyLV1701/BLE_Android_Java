package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.n;
import androidx.appcompat.view.menu.o;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class b implements n {

    /* renamed from: b, reason: collision with root package name */
    protected Context f760b;

    /* renamed from: c, reason: collision with root package name */
    protected Context f761c;

    /* renamed from: d, reason: collision with root package name */
    protected g f762d;

    /* renamed from: e, reason: collision with root package name */
    protected LayoutInflater f763e;

    /* renamed from: f, reason: collision with root package name */
    private n.a f764f;
    private int g;
    private int h;
    protected o i;
    private int j;

    public b(Context context, int i, int i2) {
        this.f760b = context;
        this.f763e = LayoutInflater.from(context);
        this.g = i;
        this.h = i2;
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(Context context, g gVar) {
        this.f761c = context;
        LayoutInflater.from(this.f761c);
        this.f762d = gVar;
    }

    public abstract void a(j jVar, o.a aVar);

    public abstract boolean a(int i, j jVar);

    @Override // androidx.appcompat.view.menu.n
    public boolean a(g gVar, j jVar) {
        return false;
    }

    public o b(ViewGroup viewGroup) {
        if (this.i == null) {
            this.i = (o) this.f763e.inflate(this.g, viewGroup, false);
            this.i.a(this.f762d);
            a(true);
        }
        return this.i;
    }

    @Override // androidx.appcompat.view.menu.n
    public boolean b(g gVar, j jVar) {
        return false;
    }

    public n.a d() {
        return this.f764f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.n
    public void a(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.i;
        if (viewGroup == null) {
            return;
        }
        g gVar = this.f762d;
        int i = 0;
        if (gVar != null) {
            gVar.b();
            ArrayList<j> n = this.f762d.n();
            int size = n.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                j jVar = n.get(i3);
                if (a(i2, jVar)) {
                    View childAt = viewGroup.getChildAt(i2);
                    j itemData = childAt instanceof o.a ? ((o.a) childAt).getItemData() : null;
                    View a2 = a(jVar, childAt, viewGroup);
                    if (jVar != itemData) {
                        a2.setPressed(false);
                        a2.jumpDrawablesToCurrentState();
                    }
                    if (a2 != childAt) {
                        a(a2, i2);
                    }
                    i2++;
                }
            }
            i = i2;
        }
        while (i < viewGroup.getChildCount()) {
            if (!a(viewGroup, i)) {
                i++;
            }
        }
    }

    protected void a(View view, int i) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.i).addView(view, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(ViewGroup viewGroup, int i) {
        viewGroup.removeViewAt(i);
        return true;
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(n.a aVar) {
        this.f764f = aVar;
    }

    public o.a a(ViewGroup viewGroup) {
        return (o.a) this.f763e.inflate(this.h, viewGroup, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View a(j jVar, View view, ViewGroup viewGroup) {
        o.a aVar;
        if (view instanceof o.a) {
            aVar = (o.a) view;
        } else {
            aVar = a(viewGroup);
        }
        a(jVar, aVar);
        return (View) aVar;
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(g gVar, boolean z) {
        n.a aVar = this.f764f;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // androidx.appcompat.view.menu.n
    public boolean a(s sVar) {
        n.a aVar = this.f764f;
        if (aVar != null) {
            return aVar.a(sVar);
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.n
    public int a() {
        return this.j;
    }

    public void a(int i) {
        this.j = i;
    }
}
