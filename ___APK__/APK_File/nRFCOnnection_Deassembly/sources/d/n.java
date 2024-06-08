package d;

import d.t.o.w2;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public abstract class n {
    public static d.t.m a(File file) {
        return a(file, new o());
    }

    public static String a() {
        return "2.6.12";
    }

    public static d.t.m a(File file, o oVar) {
        return new w2(new FileOutputStream(file), true, oVar);
    }

    public static d.t.m a(OutputStream outputStream) {
        return a(outputStream, new o());
    }

    public static d.t.m a(OutputStream outputStream, o oVar) {
        return new w2(outputStream, false, oVar);
    }
}
