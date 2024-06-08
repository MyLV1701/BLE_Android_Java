package c.a.a.a.n;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;

/* loaded from: classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    public static final boolean f2126a;

    static {
        f2126a = Build.VERSION.SDK_INT < 18;
    }

    public static void a(Rect rect, float f2, float f3, float f4, float f5) {
        rect.set((int) (f2 - f4), (int) (f3 - f5), (int) (f2 + f4), (int) (f3 + f5));
    }

    public static void b(a aVar, View view, FrameLayout frameLayout) {
        if (aVar == null) {
            return;
        }
        if (f2126a) {
            frameLayout.setForeground(null);
        } else {
            view.getOverlay().remove(aVar);
        }
    }

    public static void c(a aVar, View view, FrameLayout frameLayout) {
        Rect rect = new Rect();
        (f2126a ? frameLayout : view).getDrawingRect(rect);
        aVar.setBounds(rect);
        aVar.a(view, frameLayout);
    }

    public static void a(a aVar, View view, FrameLayout frameLayout) {
        c(aVar, view, frameLayout);
        if (f2126a) {
            frameLayout.setForeground(aVar);
        } else {
            view.getOverlay().add(aVar);
        }
    }
}
