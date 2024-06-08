package a.f.i;

import a.f.d.d.f;
import a.f.e.j;
import a.f.i.c;
import a.f.k.h;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    static final a.d.e<String, Typeface> f189a = new a.d.e<>(16);

    /* renamed from: b, reason: collision with root package name */
    private static final a.f.i.c f190b = new a.f.i.c("fonts", 10, 10000);

    /* renamed from: c, reason: collision with root package name */
    static final Object f191c = new Object();

    /* renamed from: d, reason: collision with root package name */
    static final a.d.g<String, ArrayList<c.d<g>>> f192d = new a.d.g<>();

    /* renamed from: e, reason: collision with root package name */
    private static final Comparator<byte[]> f193e = new d();

    /* loaded from: classes.dex */
    static class a implements Callable<g> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f194a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ a.f.i.a f195b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f196c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f197d;

        a(Context context, a.f.i.a aVar, int i, String str) {
            this.f194a = context;
            this.f195b = aVar;
            this.f196c = i;
            this.f197d = str;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public g call() {
            g a2 = b.a(this.f194a, this.f195b, this.f196c);
            Typeface typeface = a2.f208a;
            if (typeface != null) {
                b.f189a.a(this.f197d, typeface);
            }
            return a2;
        }
    }

    /* renamed from: a.f.i.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0010b implements c.d<g> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ f.a f198a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Handler f199b;

        C0010b(f.a aVar, Handler handler) {
            this.f198a = aVar;
            this.f199b = handler;
        }

        @Override // a.f.i.c.d
        public void a(g gVar) {
            if (gVar == null) {
                this.f198a.a(1, this.f199b);
                return;
            }
            int i = gVar.f209b;
            if (i == 0) {
                this.f198a.a(gVar.f208a, this.f199b);
            } else {
                this.f198a.a(i, this.f199b);
            }
        }
    }

    /* loaded from: classes.dex */
    static class c implements c.d<g> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f200a;

        c(String str) {
            this.f200a = str;
        }

        @Override // a.f.i.c.d
        public void a(g gVar) {
            synchronized (b.f191c) {
                ArrayList<c.d<g>> arrayList = b.f192d.get(this.f200a);
                if (arrayList == null) {
                    return;
                }
                b.f192d.remove(this.f200a);
                for (int i = 0; i < arrayList.size(); i++) {
                    arrayList.get(i).a(gVar);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    static class d implements Comparator<byte[]> {
        d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(byte[] bArr, byte[] bArr2) {
            int i;
            int i2;
            if (bArr.length != bArr2.length) {
                i = bArr.length;
                i2 = bArr2.length;
            } else {
                for (int i3 = 0; i3 < bArr.length; i3++) {
                    if (bArr[i3] != bArr2[i3]) {
                        i = bArr[i3];
                        i2 = bArr2[i3];
                    }
                }
                return 0;
            }
            return i - i2;
        }
    }

    /* loaded from: classes.dex */
    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private final int f201a;

        /* renamed from: b, reason: collision with root package name */
        private final f[] f202b;

        public e(int i, f[] fVarArr) {
            this.f201a = i;
            this.f202b = fVarArr;
        }

        public f[] a() {
            return this.f202b;
        }

        public int b() {
            return this.f201a;
        }
    }

    /* loaded from: classes.dex */
    public static class f {

        /* renamed from: a, reason: collision with root package name */
        private final Uri f203a;

        /* renamed from: b, reason: collision with root package name */
        private final int f204b;

        /* renamed from: c, reason: collision with root package name */
        private final int f205c;

        /* renamed from: d, reason: collision with root package name */
        private final boolean f206d;

        /* renamed from: e, reason: collision with root package name */
        private final int f207e;

        public f(Uri uri, int i, int i2, boolean z, int i3) {
            h.a(uri);
            this.f203a = uri;
            this.f204b = i;
            this.f205c = i2;
            this.f206d = z;
            this.f207e = i3;
        }

        public int a() {
            return this.f207e;
        }

        public int b() {
            return this.f204b;
        }

        public Uri c() {
            return this.f203a;
        }

        public int d() {
            return this.f205c;
        }

        public boolean e() {
            return this.f206d;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class g {

        /* renamed from: a, reason: collision with root package name */
        final Typeface f208a;

        /* renamed from: b, reason: collision with root package name */
        final int f209b;

        g(Typeface typeface, int i) {
            this.f208a = typeface;
            this.f209b = i;
        }
    }

    static g a(Context context, a.f.i.a aVar, int i) {
        try {
            e a2 = a(context, (CancellationSignal) null, aVar);
            if (a2.b() == 0) {
                Typeface a3 = a.f.e.c.a(context, null, a2.a(), i);
                return new g(a3, a3 != null ? 0 : -3);
            }
            return new g(null, a2.b() == 1 ? -2 : -3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new g(null, -1);
        }
    }

    public static Typeface a(Context context, a.f.i.a aVar, f.a aVar2, Handler handler, boolean z, int i, int i2) {
        String str = aVar.c() + "-" + i2;
        Typeface b2 = f189a.b(str);
        if (b2 != null) {
            if (aVar2 != null) {
                aVar2.a(b2);
            }
            return b2;
        }
        if (z && i == -1) {
            g a2 = a(context, aVar, i2);
            if (aVar2 != null) {
                int i3 = a2.f209b;
                if (i3 == 0) {
                    aVar2.a(a2.f208a, handler);
                } else {
                    aVar2.a(i3, handler);
                }
            }
            return a2.f208a;
        }
        a aVar3 = new a(context, aVar, i2, str);
        if (z) {
            try {
                return ((g) f190b.a(aVar3, i)).f208a;
            } catch (InterruptedException unused) {
                return null;
            }
        }
        C0010b c0010b = aVar2 == null ? null : new C0010b(aVar2, handler);
        synchronized (f191c) {
            ArrayList<c.d<g>> arrayList = f192d.get(str);
            if (arrayList != null) {
                if (c0010b != null) {
                    arrayList.add(c0010b);
                }
                return null;
            }
            if (c0010b != null) {
                ArrayList<c.d<g>> arrayList2 = new ArrayList<>();
                arrayList2.add(c0010b);
                f192d.put(str, arrayList2);
            }
            f190b.a(aVar3, new c(str));
            return null;
        }
    }

    public static Map<Uri, ByteBuffer> a(Context context, f[] fVarArr, CancellationSignal cancellationSignal) {
        HashMap hashMap = new HashMap();
        for (f fVar : fVarArr) {
            if (fVar.a() == 0) {
                Uri c2 = fVar.c();
                if (!hashMap.containsKey(c2)) {
                    hashMap.put(c2, j.a(context, cancellationSignal, c2));
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public static e a(Context context, CancellationSignal cancellationSignal, a.f.i.a aVar) {
        ProviderInfo a2 = a(context.getPackageManager(), aVar, context.getResources());
        if (a2 == null) {
            return new e(1, null);
        }
        return new e(0, a(context, aVar, a2.authority, cancellationSignal));
    }

    public static ProviderInfo a(PackageManager packageManager, a.f.i.a aVar, Resources resources) {
        String d2 = aVar.d();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(d2, 0);
        if (resolveContentProvider != null) {
            if (resolveContentProvider.packageName.equals(aVar.e())) {
                List<byte[]> a2 = a(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
                Collections.sort(a2, f193e);
                List<List<byte[]>> a3 = a(aVar, resources);
                for (int i = 0; i < a3.size(); i++) {
                    ArrayList arrayList = new ArrayList(a3.get(i));
                    Collections.sort(arrayList, f193e);
                    if (a(a2, arrayList)) {
                        return resolveContentProvider;
                    }
                }
                return null;
            }
            throw new PackageManager.NameNotFoundException("Found content provider " + d2 + ", but package was not " + aVar.e());
        }
        throw new PackageManager.NameNotFoundException("No package found for authority: " + d2);
    }

    private static List<List<byte[]>> a(a.f.i.a aVar, Resources resources) {
        if (aVar.a() != null) {
            return aVar.a();
        }
        return a.f.d.d.c.a(resources, aVar.b());
    }

    private static boolean a(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> a(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x012b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static a.f.i.b.f[] a(android.content.Context r19, a.f.i.a r20, java.lang.String r21, android.os.CancellationSignal r22) {
        /*
            Method dump skipped, instructions count: 321
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a.f.i.b.a(android.content.Context, a.f.i.a, java.lang.String, android.os.CancellationSignal):a.f.i.b$f[]");
    }
}
