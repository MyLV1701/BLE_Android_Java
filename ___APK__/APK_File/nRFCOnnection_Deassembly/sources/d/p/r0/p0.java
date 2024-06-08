package d.p.r0;

import java.util.Stack;

/* loaded from: classes.dex */
abstract class p0 extends s0 {

    /* renamed from: e, reason: collision with root package name */
    private s0[] f3098e = new s0[0];

    public abstract void a(Stack stack);

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(s0 s0Var) {
        s0Var.a(this);
        s0[] s0VarArr = this.f3098e;
        s0[] s0VarArr2 = new s0[s0VarArr.length + 1];
        System.arraycopy(s0VarArr, 0, s0VarArr2, 0, s0VarArr.length);
        s0VarArr2[this.f3098e.length] = s0Var;
        this.f3098e = s0VarArr2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public s0[] g() {
        return this.f3098e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int h();

    /* JADX INFO: Access modifiers changed from: protected */
    public void i() {
        int i = 0;
        while (true) {
            s0[] s0VarArr = this.f3098e;
            if (i >= s0VarArr.length) {
                return;
            }
            s0VarArr[i].d();
            i++;
        }
    }
}
