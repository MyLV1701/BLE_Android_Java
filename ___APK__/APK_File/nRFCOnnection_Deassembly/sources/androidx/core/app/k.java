package androidx.core.app;

import android.app.RemoteInput;
import android.os.Build;
import android.os.Bundle;
import java.util.Set;

/* loaded from: classes.dex */
public final class k {

    /* renamed from: a, reason: collision with root package name */
    private final String f1269a;

    /* renamed from: b, reason: collision with root package name */
    private final CharSequence f1270b;

    /* renamed from: c, reason: collision with root package name */
    private final CharSequence[] f1271c;

    /* renamed from: d, reason: collision with root package name */
    private final boolean f1272d;

    /* renamed from: e, reason: collision with root package name */
    private final int f1273e;

    /* renamed from: f, reason: collision with root package name */
    private final Bundle f1274f;
    private final Set<String> g;

    public boolean a() {
        return this.f1272d;
    }

    public Set<String> b() {
        return this.g;
    }

    public CharSequence[] c() {
        return this.f1271c;
    }

    public int d() {
        return this.f1273e;
    }

    public Bundle e() {
        return this.f1274f;
    }

    public CharSequence f() {
        return this.f1270b;
    }

    public String g() {
        return this.f1269a;
    }

    public boolean h() {
        return (a() || (c() != null && c().length != 0) || b() == null || b().isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RemoteInput[] a(k[] kVarArr) {
        if (kVarArr == null) {
            return null;
        }
        RemoteInput[] remoteInputArr = new RemoteInput[kVarArr.length];
        for (int i = 0; i < kVarArr.length; i++) {
            remoteInputArr[i] = a(kVarArr[i]);
        }
        return remoteInputArr;
    }

    static RemoteInput a(k kVar) {
        RemoteInput.Builder addExtras = new RemoteInput.Builder(kVar.g()).setLabel(kVar.f()).setChoices(kVar.c()).setAllowFreeFormInput(kVar.a()).addExtras(kVar.e());
        if (Build.VERSION.SDK_INT >= 29) {
            addExtras.setEditChoicesBeforeSending(kVar.d());
        }
        return addExtras.build();
    }
}
