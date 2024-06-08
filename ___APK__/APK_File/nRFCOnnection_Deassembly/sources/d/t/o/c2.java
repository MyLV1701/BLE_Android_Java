package d.t.o;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c2 {

    /* renamed from: a, reason: collision with root package name */
    private HashMap f3257a = new HashMap(100);

    /* renamed from: b, reason: collision with root package name */
    private ArrayList f3258b = new ArrayList(100);

    /* renamed from: c, reason: collision with root package name */
    private int f3259c = 0;

    public int a(String str) {
        Integer num = (Integer) this.f3257a.get(str);
        if (num == null) {
            num = new Integer(this.f3257a.size());
            this.f3257a.put(str, num);
            this.f3258b.add(str);
        }
        this.f3259c++;
        return num.intValue();
    }

    public String a(int i) {
        return (String) this.f3258b.get(i);
    }

    public void a(e0 e0Var) {
        x1 x1Var = new x1(this.f3259c, this.f3258b.size());
        b0 b0Var = new b0(this.f3258b.size());
        int w = b0Var.w();
        Iterator it = this.f3258b.iterator();
        int i = 0;
        String str = null;
        int i2 = 0;
        while (it.hasNext() && i == 0) {
            str = (String) it.next();
            int v = x1Var.v() + 4;
            int a2 = x1Var.a(str);
            if (i2 % w == 0) {
                b0Var.a(e0Var.a(), v);
            }
            i2++;
            i = a2;
        }
        e0Var.a(x1Var);
        if (i != 0 || it.hasNext()) {
            w1 a3 = a(str, i, e0Var);
            while (it.hasNext()) {
                String str2 = (String) it.next();
                int v2 = a3.v() + 4;
                int a4 = a3.a(str2);
                if (i2 % w == 0) {
                    b0Var.a(e0Var.a(), v2);
                }
                i2++;
                if (a4 != 0) {
                    e0Var.a(a3);
                    a3 = a(str2, a4, e0Var);
                }
            }
            e0Var.a(a3);
        }
        e0Var.a(b0Var);
    }

    private w1 a(String str, int i, e0 e0Var) {
        w1 w1Var = null;
        while (i != 0) {
            w1Var = new w1();
            if (i != str.length() && str.length() != 0) {
                i = w1Var.a(str.substring(str.length() - i), false);
            } else {
                i = w1Var.a(str, true);
            }
            if (i != 0) {
                e0Var.a(w1Var);
                w1Var = new w1();
            }
        }
        return w1Var;
    }
}
