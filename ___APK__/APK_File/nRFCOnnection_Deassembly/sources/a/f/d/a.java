package a.f.d;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.OperationCanceledException;

/* loaded from: classes.dex */
public final class a {
    public static Cursor a(ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, a.f.h.a aVar) {
        Object b2;
        if (Build.VERSION.SDK_INT >= 16) {
            if (aVar != null) {
                try {
                    b2 = aVar.b();
                } catch (Exception e2) {
                    if (e2 instanceof OperationCanceledException) {
                        throw new a.f.h.b();
                    }
                    throw e2;
                }
            } else {
                b2 = null;
            }
            return contentResolver.query(uri, strArr, str, strArr2, str2, (CancellationSignal) b2);
        }
        if (aVar != null) {
            aVar.d();
        }
        return contentResolver.query(uri, strArr, str, strArr2, str2);
    }
}
