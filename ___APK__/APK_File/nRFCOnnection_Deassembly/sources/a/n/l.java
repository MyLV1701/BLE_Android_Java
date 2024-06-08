package a.n;

import android.view.ViewGroup;

/* loaded from: classes.dex */
public class l {

    /* renamed from: a, reason: collision with root package name */
    private ViewGroup f489a;

    /* renamed from: b, reason: collision with root package name */
    private Runnable f490b;

    public void a() {
        Runnable runnable;
        if (a(this.f489a) != this || (runnable = this.f490b) == null) {
            return;
        }
        runnable.run();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(ViewGroup viewGroup, l lVar) {
        viewGroup.setTag(j.transition_current_scene, lVar);
    }

    public static l a(ViewGroup viewGroup) {
        return (l) viewGroup.getTag(j.transition_current_scene);
    }
}
