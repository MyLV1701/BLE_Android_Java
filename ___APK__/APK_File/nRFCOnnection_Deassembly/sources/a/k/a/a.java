package a.k.a;

import a.k.b.c;
import android.os.Bundle;
import androidx.lifecycle.j;
import androidx.lifecycle.x;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a.k.a.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0025a<D> {
        c<D> onCreateLoader(int i, Bundle bundle);

        void onLoadFinished(c<D> cVar, D d2);

        void onLoaderReset(c<D> cVar);
    }

    public static <T extends j & x> a a(T t) {
        return new b(t, t.getViewModelStore());
    }

    public abstract <D> c<D> a(int i, Bundle bundle, InterfaceC0025a<D> interfaceC0025a);

    public abstract void a();

    public abstract void a(int i);

    @Deprecated
    public abstract void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract <D> c<D> b(int i, Bundle bundle, InterfaceC0025a<D> interfaceC0025a);
}
