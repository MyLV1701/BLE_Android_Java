package d.p.r0;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/* loaded from: classes.dex */
class b1 implements u0 {
    private static d.q.c i = d.q.c.b(b1.class);

    /* renamed from: a, reason: collision with root package name */
    private String f3056a;

    /* renamed from: b, reason: collision with root package name */
    private String f3057b;

    /* renamed from: c, reason: collision with root package name */
    private s0 f3058c;

    /* renamed from: d, reason: collision with root package name */
    private Stack f3059d;

    /* renamed from: e, reason: collision with root package name */
    private d.o f3060e;

    /* renamed from: f, reason: collision with root package name */
    private t f3061f;
    private d.p.l0 g;
    private r0 h;

    public b1(String str, t tVar, d.p.l0 l0Var, d.o oVar, r0 r0Var) {
        this.f3056a = str;
        this.f3060e = oVar;
        this.f3061f = tVar;
        this.g = l0Var;
        this.h = r0Var;
    }

    private s0 a(Iterator it) {
        Stack stack = new Stack();
        Stack stack2 = new Stack();
        boolean z = false;
        Stack stack3 = null;
        s0 s0Var = null;
        while (it.hasNext() && !z) {
            s0 s0Var2 = (s0) it.next();
            s0Var2.a(this.h);
            if (s0Var2 instanceof o0) {
                a((o0) s0Var2, stack);
            } else if (s0Var2 instanceof c1) {
                a((c1) s0Var2, it, stack);
            } else if (s0Var2 instanceof p0) {
                p0 p0Var = (p0) s0Var2;
                if (p0Var instanceof d1) {
                    d1 d1Var = (d1) p0Var;
                    if (!stack.isEmpty() && !(s0Var instanceof p0)) {
                        p0Var = d1Var.j();
                    } else {
                        p0Var = d1Var.k();
                    }
                }
                if (stack2.empty()) {
                    stack2.push(p0Var);
                } else {
                    p0 p0Var2 = (p0) stack2.peek();
                    if (p0Var.h() < p0Var2.h()) {
                        stack2.push(p0Var);
                    } else if (p0Var.h() == p0Var2.h() && (p0Var instanceof l1)) {
                        stack2.push(p0Var);
                    } else {
                        stack2.pop();
                        p0Var2.a(stack);
                        stack.push(p0Var2);
                        stack2.push(p0Var);
                    }
                }
            } else if (s0Var2 instanceof d) {
                while (!stack2.isEmpty()) {
                    p0 p0Var3 = (p0) stack2.pop();
                    p0Var3.a(stack);
                    stack.push(p0Var3);
                }
                if (stack3 == null) {
                    stack3 = new Stack();
                }
                stack3.push(stack.pop());
                stack.clear();
            } else if (s0Var2 instanceof n0) {
                s0 a2 = a(it);
                q0 q0Var = new q0();
                a2.a(q0Var);
                q0Var.b(a2);
                stack.push(q0Var);
            } else if (s0Var2 instanceof l) {
                z = true;
            }
            s0Var = s0Var2;
        }
        while (!stack2.isEmpty()) {
            p0 p0Var4 = (p0) stack2.pop();
            p0Var4.a(stack);
            stack.push(p0Var4);
        }
        s0 s0Var3 = stack.empty() ? null : (s0) stack.pop();
        if (stack3 != null && s0Var3 != null) {
            stack3.push(s0Var3);
        }
        this.f3059d = stack3;
        if (!stack.empty() || !stack2.empty()) {
            i.b("Formula " + this.f3056a + " has a non-empty parse stack");
        }
        return s0Var3;
    }

    private ArrayList d() {
        ArrayList arrayList = new ArrayList();
        o1 o1Var = new o1(new StringReader(this.f3056a));
        o1Var.a(this.f3061f);
        o1Var.a(this.g);
        try {
            for (s0 b2 = o1Var.b(); b2 != null; b2 = o1Var.b()) {
                arrayList.add(b2);
            }
        } catch (IOException e2) {
            i.b(e2.toString());
        } catch (Error unused) {
            throw new v(v.f3116e, this.f3056a + " at char  " + o1Var.a());
        }
        return arrayList;
    }

    @Override // d.p.r0.u0
    public void b() {
        this.f3058c = a(d().iterator());
    }

    @Override // d.p.r0.u0
    public String c() {
        if (this.f3057b == null) {
            StringBuffer stringBuffer = new StringBuffer();
            this.f3058c.a(stringBuffer);
            this.f3057b = stringBuffer.toString();
        }
        return this.f3057b;
    }

    @Override // d.p.r0.u0
    public byte[] a() {
        byte[] a2 = this.f3058c.a();
        if (!this.f3058c.c()) {
            return a2;
        }
        byte[] bArr = new byte[a2.length + 4];
        System.arraycopy(a2, 0, bArr, 4, a2.length);
        bArr[0] = i1.L.a();
        bArr[1] = 1;
        return bArr;
    }

    private void a(c1 c1Var, Iterator it, Stack stack) {
        Stack stack2;
        s0 a2 = a(it);
        if (c1Var.a(this.f3060e) != x.i) {
            if (c1Var.a(this.f3060e) == x.f3122f && this.f3059d == null) {
                e eVar = new e(c1Var, this.f3060e);
                eVar.b(a2);
                stack.push(eVar);
                return;
            }
            if (c1Var.a(this.f3060e) == x.h) {
                e eVar2 = new e(c1Var, this.f3060e);
                n1 n1Var = new n1(this.f3060e);
                int size = this.f3059d.size();
                while (r2 < size) {
                    n1Var.b((s0) this.f3059d.get(r2));
                    r2++;
                }
                eVar2.a(n1Var);
                stack.push(eVar2);
                return;
            }
            if (c1Var.a(this.f3060e).b() == 255) {
                Stack stack3 = this.f3059d;
                if (stack3 == null) {
                    n1 n1Var2 = new n1(c1Var.a(this.f3060e), a2 != null ? 1 : 0, this.f3060e);
                    if (a2 != null) {
                        n1Var2.b(a2);
                    }
                    stack.push(n1Var2);
                    return;
                }
                int size2 = stack3.size();
                n1 n1Var3 = new n1(c1Var.a(this.f3060e), size2, this.f3060e);
                s0[] s0VarArr = new s0[size2];
                for (int i2 = 0; i2 < size2; i2++) {
                    s0VarArr[(size2 - i2) - 1] = (s0) this.f3059d.pop();
                }
                while (r2 < s0VarArr.length) {
                    n1Var3.b(s0VarArr[r2]);
                    r2++;
                }
                stack.push(n1Var3);
                this.f3059d.clear();
                this.f3059d = null;
                return;
            }
            h hVar = new h(c1Var.a(this.f3060e), this.f3060e);
            int b2 = c1Var.a(this.f3060e).b();
            if (b2 == 1) {
                hVar.b(a2);
            } else {
                if ((this.f3059d == null && b2 != 0) || ((stack2 = this.f3059d) != null && b2 != stack2.size())) {
                    throw new v(v.f3117f);
                }
                while (r2 < b2) {
                    hVar.b((s0) this.f3059d.get(r2));
                    r2++;
                }
            }
            stack.push(hVar);
            return;
        }
        throw new v(v.f3114c);
    }

    private void a(o0 o0Var, Stack stack) {
        boolean z = o0Var instanceof b0;
        if (!z) {
            stack.push(o0Var);
            return;
        }
        if (z) {
            b0 b0Var = (b0) o0Var;
            if (!b0Var.h()) {
                stack.push(b0Var);
            } else {
                stack.push(new q(b0Var.g()));
            }
        }
    }
}
