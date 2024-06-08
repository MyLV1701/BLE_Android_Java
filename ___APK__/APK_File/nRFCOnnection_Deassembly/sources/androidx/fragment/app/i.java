package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public abstract class i<E> extends f {

    /* renamed from: b, reason: collision with root package name */
    private final Activity f1393b;

    /* renamed from: c, reason: collision with root package name */
    private final Context f1394c;

    /* renamed from: d, reason: collision with root package name */
    private final Handler f1395d;

    /* renamed from: e, reason: collision with root package name */
    final l f1396e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(d dVar) {
        this(dVar, dVar, new Handler(), 0);
    }

    @Override // androidx.fragment.app.f
    public View a(int i) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Fragment fragment) {
    }

    public void a(Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i, Bundle bundle) {
        if (i == -1) {
            this.f1394c.startActivity(intent);
            return;
        }
        throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
    }

    public void a(Fragment fragment, String[] strArr, int i) {
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // androidx.fragment.app.f
    public boolean a() {
        return true;
    }

    public boolean a(String str) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Activity b() {
        return this.f1393b;
    }

    public boolean b(Fragment fragment) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Context c() {
        return this.f1394c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Handler d() {
        return this.f1395d;
    }

    public abstract E e();

    public LayoutInflater f() {
        return LayoutInflater.from(this.f1394c);
    }

    public void g() {
    }

    i(Activity activity, Context context, Handler handler, int i) {
        this.f1396e = new m();
        this.f1393b = activity;
        a.f.k.h.a(context, "context == null");
        this.f1394c = context;
        a.f.k.h.a(handler, "handler == null");
        this.f1395d = handler;
    }

    public void a(Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        if (i == -1) {
            androidx.core.app.a.a(this.f1393b, intentSender, i, intent, i2, i3, i4, bundle);
            return;
        }
        throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
    }
}
