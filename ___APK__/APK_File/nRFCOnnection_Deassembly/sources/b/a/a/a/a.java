package b.a.a.a;

import com.androidplot.xy.XYSeries;
import java.io.Closeable;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

/* loaded from: classes.dex */
public class a implements Closeable {

    /* renamed from: b, reason: collision with root package name */
    private Writer f2018b;

    /* renamed from: c, reason: collision with root package name */
    private PrintWriter f2019c;

    /* renamed from: d, reason: collision with root package name */
    private char f2020d;

    /* renamed from: e, reason: collision with root package name */
    private char f2021e;

    /* renamed from: f, reason: collision with root package name */
    private char f2022f;
    private String g;

    public a(Writer writer, char c2) {
        this(writer, c2, '\"');
    }

    private boolean b(String str) {
        return (str.indexOf(this.f2021e) == -1 && str.indexOf(this.f2022f) == -1) ? false : true;
    }

    public void a(String[] strArr) {
        if (strArr == null) {
            return;
        }
        StringBuilder sb = new StringBuilder(128);
        for (int i = 0; i < strArr.length; i++) {
            if (i != 0) {
                sb.append(this.f2020d);
            }
            String str = strArr[i];
            if (str != null) {
                char c2 = this.f2021e;
                if (c2 != 0) {
                    sb.append(c2);
                }
                boolean b2 = b(str);
                String str2 = str;
                if (b2) {
                    str2 = a(str);
                }
                sb.append((CharSequence) str2);
                char c3 = this.f2021e;
                if (c3 != 0) {
                    sb.append(c3);
                }
            }
        }
        sb.append(this.g);
        this.f2019c.write(sb.toString());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        a();
        this.f2019c.close();
        this.f2018b.close();
    }

    public a(Writer writer, char c2, char c3) {
        this(writer, c2, c3, '\"');
    }

    public a(Writer writer, char c2, char c3, char c4) {
        this(writer, c2, c3, c4, "\n");
    }

    public a(Writer writer, char c2, char c3, char c4, String str) {
        this.f2018b = writer;
        this.f2019c = new PrintWriter(writer);
        this.f2020d = c2;
        this.f2021e = c3;
        this.f2022f = c4;
        this.g = str;
    }

    public void a(Float[] fArr) {
        if (fArr == null) {
            return;
        }
        StringBuilder sb = new StringBuilder(128);
        for (int i = 0; i < fArr.length; i++) {
            if (i != 0) {
                sb.append(this.f2020d);
            }
            Float f2 = fArr[i];
            if (f2 != null) {
                sb.append(f2);
            }
        }
        sb.append(this.g);
        this.f2019c.write(sb.toString());
    }

    public void a(List<XYSeries> list) {
        Float[] fArr = new Float[list.size()];
        int i = 0;
        for (XYSeries xYSeries : list) {
            if (i < xYSeries.size()) {
                i = xYSeries.size();
            }
        }
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = 0;
            for (XYSeries xYSeries2 : list) {
                if (xYSeries2.size() > i2) {
                    fArr[i3] = xYSeries2.getY(i2);
                } else {
                    fArr[i3] = null;
                }
                i3++;
            }
            a(fArr);
        }
    }

    protected StringBuilder a(String str) {
        StringBuilder sb = new StringBuilder(128);
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            char c2 = this.f2022f;
            if (c2 != 0 && charAt == this.f2021e) {
                sb.append(c2);
                sb.append(charAt);
            } else {
                char c3 = this.f2022f;
                if (c3 != 0 && charAt == c3) {
                    sb.append(c3);
                    sb.append(charAt);
                } else {
                    sb.append(charAt);
                }
            }
        }
        return sb;
    }

    public void a() {
        this.f2019c.flush();
    }
}
