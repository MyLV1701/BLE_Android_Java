package a.f.l.f0;

import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private final Object f318a;

    /* loaded from: classes.dex */
    static class a extends AccessibilityNodeProvider {

        /* renamed from: a, reason: collision with root package name */
        final d f319a;

        a(d dVar) {
            this.f319a = dVar;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            c a2 = this.f319a.a(i);
            if (a2 == null) {
                return null;
            }
            return a2.z();
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            List<c> a2 = this.f319a.a(str, i);
            if (a2 == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int size = a2.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(a2.get(i2).z());
            }
            return arrayList;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int i, int i2, Bundle bundle) {
            return this.f319a.a(i, i2, bundle);
        }
    }

    /* loaded from: classes.dex */
    static class b extends a {
        b(d dVar) {
            super(dVar);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int i) {
            c b2 = this.f319a.b(i);
            if (b2 == null) {
                return null;
            }
            return b2.z();
        }
    }

    public d() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 19) {
            this.f318a = new b(this);
        } else if (i >= 16) {
            this.f318a = new a(this);
        } else {
            this.f318a = null;
        }
    }

    public c a(int i) {
        return null;
    }

    public Object a() {
        return this.f318a;
    }

    public List<c> a(String str, int i) {
        return null;
    }

    public boolean a(int i, int i2, Bundle bundle) {
        return false;
    }

    public c b(int i) {
        return null;
    }

    public d(Object obj) {
        this.f318a = obj;
    }
}
