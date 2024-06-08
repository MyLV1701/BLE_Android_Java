package c.a.a.a.b0;

import android.graphics.drawable.Drawable;
import android.view.View;

/* loaded from: classes.dex */
public class h {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static d a(int i) {
        if (i == 0) {
            return new j();
        }
        if (i != 1) {
            return a();
        }
        return new e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static f b() {
        return new f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static d a() {
        return new j();
    }

    public static void a(View view, float f2) {
        Drawable background = view.getBackground();
        if (background instanceof g) {
            ((g) background).b(f2);
        }
    }

    public static void a(View view) {
        Drawable background = view.getBackground();
        if (background instanceof g) {
            a(view, (g) background);
        }
    }

    public static void a(View view, g gVar) {
        if (gVar.q()) {
            gVar.d(com.google.android.material.internal.m.a(view));
        }
    }
}
