package a.n;

import android.view.View;
import android.view.WindowId;

/* loaded from: classes.dex */
class l0 implements m0 {

    /* renamed from: a, reason: collision with root package name */
    private final WindowId f491a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l0(View view) {
        this.f491a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        return (obj instanceof l0) && ((l0) obj).f491a.equals(this.f491a);
    }

    public int hashCode() {
        return this.f491a.hashCode();
    }
}
