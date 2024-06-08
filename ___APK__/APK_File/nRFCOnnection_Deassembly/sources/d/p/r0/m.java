package d.p.r0;

/* loaded from: classes.dex */
class m extends b {
    private t o;
    private int p;

    static {
        d.q.c.b(m.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(String str, t tVar) {
        super(tVar);
        this.o = tVar;
        int lastIndexOf = str.lastIndexOf(":");
        d.q.a.a(lastIndexOf != -1);
        str.substring(0, lastIndexOf);
        String substring = str.substring(lastIndexOf + 1);
        int indexOf = str.indexOf(33);
        int a2 = d.p.i.a(str.substring(indexOf + 1, lastIndexOf));
        String substring2 = str.substring(0, indexOf);
        substring2.lastIndexOf(93);
        if (substring2.charAt(0) == '\'' && substring2.charAt(substring2.length() - 1) == '\'') {
            substring2 = substring2.substring(1, substring2.length() - 1);
        }
        this.p = tVar.b(substring2);
        if (this.p >= 0) {
            a(this.p, a2, d.p.i.a(substring), 0, 65535, true, true, true, true);
            return;
        }
        throw new v(v.g, substring2);
    }

    @Override // d.p.r0.b, d.p.r0.s0
    public void a(StringBuffer stringBuffer) {
        stringBuffer.append('\'');
        stringBuffer.append(this.o.b(this.p));
        stringBuffer.append('\'');
        stringBuffer.append('!');
        d.p.i.a(g(), stringBuffer);
        stringBuffer.append(':');
        d.p.i.a(h(), stringBuffer);
    }
}
