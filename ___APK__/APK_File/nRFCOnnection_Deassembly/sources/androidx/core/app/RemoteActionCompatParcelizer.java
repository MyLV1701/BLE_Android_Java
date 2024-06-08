package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;

/* loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(androidx.versionedparcelable.a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f1183a = (IconCompat) aVar.a((androidx.versionedparcelable.a) remoteActionCompat.f1183a, 1);
        remoteActionCompat.f1184b = aVar.a(remoteActionCompat.f1184b, 2);
        remoteActionCompat.f1185c = aVar.a(remoteActionCompat.f1185c, 3);
        remoteActionCompat.f1186d = (PendingIntent) aVar.a((androidx.versionedparcelable.a) remoteActionCompat.f1186d, 4);
        remoteActionCompat.f1187e = aVar.a(remoteActionCompat.f1187e, 5);
        remoteActionCompat.f1188f = aVar.a(remoteActionCompat.f1188f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, androidx.versionedparcelable.a aVar) {
        aVar.a(false, false);
        aVar.b(remoteActionCompat.f1183a, 1);
        aVar.b(remoteActionCompat.f1184b, 2);
        aVar.b(remoteActionCompat.f1185c, 3);
        aVar.b(remoteActionCompat.f1186d, 4);
        aVar.b(remoteActionCompat.f1187e, 5);
        aVar.b(remoteActionCompat.f1188f, 6);
    }
}
