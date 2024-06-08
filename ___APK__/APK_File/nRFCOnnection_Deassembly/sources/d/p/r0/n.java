package d.p.r0;

/* loaded from: classes.dex */
class n extends c {
    static {
        d.q.c.b(n.class);
    }

    n() {
    }

    @Override // d.p.r0.c, d.p.r0.s0
    public void a(StringBuffer stringBuffer) {
        d.p.i.a(g(), stringBuffer);
        stringBuffer.append(':');
        d.p.i.a(h(), stringBuffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(String str) {
        int indexOf = str.indexOf(":");
        d.q.a.a(indexOf != -1);
        String substring = str.substring(0, indexOf);
        String substring2 = str.substring(indexOf + 1);
        a(d.p.i.a(substring), d.p.i.a(substring2), 0, 65535, d.p.i.d(substring), d.p.i.d(substring2), false, false);
    }
}
