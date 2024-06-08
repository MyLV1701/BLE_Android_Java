package androidx.core.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class m implements Iterable<Intent> {

    /* renamed from: b, reason: collision with root package name */
    private final ArrayList<Intent> f1275b = new ArrayList<>();

    /* renamed from: c, reason: collision with root package name */
    private final Context f1276c;

    /* loaded from: classes.dex */
    public interface a {
        Intent getSupportParentActivityIntent();
    }

    private m(Context context) {
        this.f1276c = context;
    }

    public static m a(Context context) {
        return new m(context);
    }

    @Override // java.lang.Iterable
    @Deprecated
    public Iterator<Intent> iterator() {
        return this.f1275b.iterator();
    }

    public m a(Intent intent) {
        this.f1275b.add(intent);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public m a(Activity activity) {
        Intent supportParentActivityIntent = activity instanceof a ? ((a) activity).getSupportParentActivityIntent() : null;
        if (supportParentActivityIntent == null) {
            supportParentActivityIntent = e.a(activity);
        }
        if (supportParentActivityIntent != null) {
            ComponentName component = supportParentActivityIntent.getComponent();
            if (component == null) {
                component = supportParentActivityIntent.resolveActivity(this.f1276c.getPackageManager());
            }
            a(component);
            a(supportParentActivityIntent);
        }
        return this;
    }

    public m a(ComponentName componentName) {
        int size = this.f1275b.size();
        try {
            Intent a2 = e.a(this.f1276c, componentName);
            while (a2 != null) {
                this.f1275b.add(size, a2);
                a2 = e.a(this.f1276c, a2.getComponent());
            }
            return this;
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e2);
        }
    }

    public void a() {
        a((Bundle) null);
    }

    public void a(Bundle bundle) {
        if (!this.f1275b.isEmpty()) {
            ArrayList<Intent> arrayList = this.f1275b;
            Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
            intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
            if (a.f.d.b.a(this.f1276c, intentArr, bundle)) {
                return;
            }
            Intent intent = new Intent(intentArr[intentArr.length - 1]);
            intent.addFlags(268435456);
            this.f1276c.startActivity(intent);
            return;
        }
        throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
    }
}
