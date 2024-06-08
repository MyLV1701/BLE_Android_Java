package a.n;

import a.n.m;
import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class q extends m {
    int M;
    private ArrayList<m> K = new ArrayList<>();
    private boolean L = true;
    boolean N = false;
    private int O = 0;

    /* loaded from: classes.dex */
    class a extends n {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ m f512a;

        a(q qVar, m mVar) {
            this.f512a = mVar;
        }

        @Override // a.n.m.f
        public void e(m mVar) {
            this.f512a.o();
            mVar.b(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b extends n {

        /* renamed from: a, reason: collision with root package name */
        q f513a;

        b(q qVar) {
            this.f513a = qVar;
        }

        @Override // a.n.n, a.n.m.f
        public void a(m mVar) {
            q qVar = this.f513a;
            if (qVar.N) {
                return;
            }
            qVar.p();
            this.f513a.N = true;
        }

        @Override // a.n.m.f
        public void e(m mVar) {
            q qVar = this.f513a;
            qVar.M--;
            if (qVar.M == 0) {
                qVar.N = false;
                qVar.b();
            }
            mVar.b(this);
        }
    }

    private void r() {
        b bVar = new b(this);
        Iterator<m> it = this.K.iterator();
        while (it.hasNext()) {
            it.next().a(bVar);
        }
        this.M = this.K.size();
    }

    @Override // a.n.m
    public void c(s sVar) {
        if (b(sVar.f518b)) {
            Iterator<m> it = this.K.iterator();
            while (it.hasNext()) {
                m next = it.next();
                if (next.b(sVar.f518b)) {
                    next.c(sVar);
                    sVar.f519c.add(next);
                }
            }
        }
    }

    @Override // a.n.m
    public void e(View view) {
        super.e(view);
        int size = this.K.size();
        for (int i = 0; i < size; i++) {
            this.K.get(i).e(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // a.n.m
    public void o() {
        if (this.K.isEmpty()) {
            p();
            b();
            return;
        }
        r();
        if (!this.L) {
            for (int i = 1; i < this.K.size(); i++) {
                this.K.get(i - 1).a(new a(this, this.K.get(i)));
            }
            m mVar = this.K.get(0);
            if (mVar != null) {
                mVar.o();
                return;
            }
            return;
        }
        Iterator<m> it = this.K.iterator();
        while (it.hasNext()) {
            it.next().o();
        }
    }

    public int q() {
        return this.K.size();
    }

    @Override // a.n.m
    /* renamed from: clone */
    public m mo2clone() {
        q qVar = (q) super.mo2clone();
        qVar.K = new ArrayList<>();
        int size = this.K.size();
        for (int i = 0; i < size; i++) {
            qVar.b(this.K.get(i).mo2clone());
        }
        return qVar;
    }

    @Override // a.n.m
    public q d(View view) {
        for (int i = 0; i < this.K.size(); i++) {
            this.K.get(i).d(view);
        }
        super.d(view);
        return this;
    }

    @Override // a.n.m
    public /* bridge */ /* synthetic */ m a(long j) {
        a(j);
        return this;
    }

    public q b(int i) {
        if (i == 0) {
            this.L = true;
        } else if (i == 1) {
            this.L = false;
        } else {
            throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i);
        }
        return this;
    }

    public q a(m mVar) {
        b(mVar);
        long j = this.f494d;
        if (j >= 0) {
            mVar.a(j);
        }
        if ((this.O & 1) != 0) {
            mVar.a(e());
        }
        if ((this.O & 2) != 0) {
            mVar.a(h());
        }
        if ((this.O & 4) != 0) {
            mVar.a(g());
        }
        if ((this.O & 8) != 0) {
            mVar.a(d());
        }
        return this;
    }

    private void b(m mVar) {
        this.K.add(mVar);
        mVar.s = this;
    }

    @Override // a.n.m
    public void c(View view) {
        super.c(view);
        int size = this.K.size();
        for (int i = 0; i < size; i++) {
            this.K.get(i).c(view);
        }
    }

    @Override // a.n.m
    public q b(long j) {
        super.b(j);
        return this;
    }

    @Override // a.n.m
    public q b(m.f fVar) {
        super.b(fVar);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a.n.m
    public void b(s sVar) {
        super.b(sVar);
        int size = this.K.size();
        for (int i = 0; i < size; i++) {
            this.K.get(i).b(sVar);
        }
    }

    public m a(int i) {
        if (i < 0 || i >= this.K.size()) {
            return null;
        }
        return this.K.get(i);
    }

    @Override // a.n.m
    public q a(long j) {
        ArrayList<m> arrayList;
        super.a(j);
        if (this.f494d >= 0 && (arrayList = this.K) != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                this.K.get(i).a(j);
            }
        }
        return this;
    }

    @Override // a.n.m
    public q a(TimeInterpolator timeInterpolator) {
        this.O |= 1;
        ArrayList<m> arrayList = this.K;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                this.K.get(i).a(timeInterpolator);
            }
        }
        super.a(timeInterpolator);
        return this;
    }

    @Override // a.n.m
    public q a(View view) {
        for (int i = 0; i < this.K.size(); i++) {
            this.K.get(i).a(view);
        }
        super.a(view);
        return this;
    }

    @Override // a.n.m
    public q a(m.f fVar) {
        super.a(fVar);
        return this;
    }

    @Override // a.n.m
    public void a(g gVar) {
        super.a(gVar);
        this.O |= 4;
        if (this.K != null) {
            for (int i = 0; i < this.K.size(); i++) {
                this.K.get(i).a(gVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // a.n.m
    public void a(ViewGroup viewGroup, t tVar, t tVar2, ArrayList<s> arrayList, ArrayList<s> arrayList2) {
        long i = i();
        int size = this.K.size();
        for (int i2 = 0; i2 < size; i2++) {
            m mVar = this.K.get(i2);
            if (i > 0 && (this.L || i2 == 0)) {
                long i3 = mVar.i();
                if (i3 > 0) {
                    mVar.b(i3 + i);
                } else {
                    mVar.b(i);
                }
            }
            mVar.a(viewGroup, tVar, tVar2, arrayList, arrayList2);
        }
    }

    @Override // a.n.m
    public void a(s sVar) {
        if (b(sVar.f518b)) {
            Iterator<m> it = this.K.iterator();
            while (it.hasNext()) {
                m next = it.next();
                if (next.b(sVar.f518b)) {
                    next.a(sVar);
                    sVar.f519c.add(next);
                }
            }
        }
    }

    @Override // a.n.m
    public void a(p pVar) {
        super.a(pVar);
        this.O |= 2;
        int size = this.K.size();
        for (int i = 0; i < size; i++) {
            this.K.get(i).a(pVar);
        }
    }

    @Override // a.n.m
    public void a(m.e eVar) {
        super.a(eVar);
        this.O |= 8;
        int size = this.K.size();
        for (int i = 0; i < size; i++) {
            this.K.get(i).a(eVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a.n.m
    public String a(String str) {
        String a2 = super.a(str);
        for (int i = 0; i < this.K.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(a2);
            sb.append("\n");
            sb.append(this.K.get(i).a(str + "  "));
            a2 = sb.toString();
        }
        return a2;
    }
}
