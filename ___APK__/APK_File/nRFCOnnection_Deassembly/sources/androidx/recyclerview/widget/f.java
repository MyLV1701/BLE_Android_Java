package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private static final Comparator<C0061f> f1838a = new a();

    /* loaded from: classes.dex */
    static class a implements Comparator<C0061f> {
        a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(C0061f c0061f, C0061f c0061f2) {
            int i = c0061f.f1852a - c0061f2.f1852a;
            return i == 0 ? c0061f.f1853b - c0061f2.f1853b : i;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class b {
        public abstract int a();

        public abstract boolean a(int i, int i2);

        public abstract int b();

        public abstract boolean b(int i, int i2);

        public Object c(int i, int i2) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a, reason: collision with root package name */
        int f1845a;

        /* renamed from: b, reason: collision with root package name */
        int f1846b;

        /* renamed from: c, reason: collision with root package name */
        boolean f1847c;

        public d(int i, int i2, boolean z) {
            this.f1845a = i;
            this.f1846b = i2;
            this.f1847c = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class e {

        /* renamed from: a, reason: collision with root package name */
        int f1848a;

        /* renamed from: b, reason: collision with root package name */
        int f1849b;

        /* renamed from: c, reason: collision with root package name */
        int f1850c;

        /* renamed from: d, reason: collision with root package name */
        int f1851d;

        public e() {
        }

        public e(int i, int i2, int i3, int i4) {
            this.f1848a = i;
            this.f1849b = i2;
            this.f1850c = i3;
            this.f1851d = i4;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.recyclerview.widget.f$f, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0061f {

        /* renamed from: a, reason: collision with root package name */
        int f1852a;

        /* renamed from: b, reason: collision with root package name */
        int f1853b;

        /* renamed from: c, reason: collision with root package name */
        int f1854c;

        /* renamed from: d, reason: collision with root package name */
        boolean f1855d;

        /* renamed from: e, reason: collision with root package name */
        boolean f1856e;

        C0061f() {
        }
    }

    public static c a(b bVar) {
        return a(bVar, true);
    }

    public static c a(b bVar, boolean z) {
        int b2 = bVar.b();
        int a2 = bVar.a();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new e(0, b2, 0, a2));
        int abs = Math.abs(b2 - a2) + b2 + a2;
        int i = abs * 2;
        int[] iArr = new int[i];
        int[] iArr2 = new int[i];
        ArrayList arrayList3 = new ArrayList();
        while (!arrayList2.isEmpty()) {
            e eVar = (e) arrayList2.remove(arrayList2.size() - 1);
            C0061f a3 = a(bVar, eVar.f1848a, eVar.f1849b, eVar.f1850c, eVar.f1851d, iArr, iArr2, abs);
            if (a3 != null) {
                if (a3.f1854c > 0) {
                    arrayList.add(a3);
                }
                a3.f1852a += eVar.f1848a;
                a3.f1853b += eVar.f1850c;
                e eVar2 = arrayList3.isEmpty() ? new e() : (e) arrayList3.remove(arrayList3.size() - 1);
                eVar2.f1848a = eVar.f1848a;
                eVar2.f1850c = eVar.f1850c;
                if (a3.f1856e) {
                    eVar2.f1849b = a3.f1852a;
                    eVar2.f1851d = a3.f1853b;
                } else if (a3.f1855d) {
                    eVar2.f1849b = a3.f1852a - 1;
                    eVar2.f1851d = a3.f1853b;
                } else {
                    eVar2.f1849b = a3.f1852a;
                    eVar2.f1851d = a3.f1853b - 1;
                }
                arrayList2.add(eVar2);
                if (a3.f1856e) {
                    if (a3.f1855d) {
                        int i2 = a3.f1852a;
                        int i3 = a3.f1854c;
                        eVar.f1848a = i2 + i3 + 1;
                        eVar.f1850c = a3.f1853b + i3;
                    } else {
                        int i4 = a3.f1852a;
                        int i5 = a3.f1854c;
                        eVar.f1848a = i4 + i5;
                        eVar.f1850c = a3.f1853b + i5 + 1;
                    }
                } else {
                    int i6 = a3.f1852a;
                    int i7 = a3.f1854c;
                    eVar.f1848a = i6 + i7;
                    eVar.f1850c = a3.f1853b + i7;
                }
                arrayList2.add(eVar);
            } else {
                arrayList3.add(eVar);
            }
        }
        Collections.sort(arrayList, f1838a);
        return new c(bVar, arrayList, iArr, iArr2, z);
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private final List<C0061f> f1839a;

        /* renamed from: b, reason: collision with root package name */
        private final int[] f1840b;

        /* renamed from: c, reason: collision with root package name */
        private final int[] f1841c;

        /* renamed from: d, reason: collision with root package name */
        private final b f1842d;

        /* renamed from: e, reason: collision with root package name */
        private final int f1843e;

        /* renamed from: f, reason: collision with root package name */
        private final int f1844f;
        private final boolean g;

        c(b bVar, List<C0061f> list, int[] iArr, int[] iArr2, boolean z) {
            this.f1839a = list;
            this.f1840b = iArr;
            this.f1841c = iArr2;
            Arrays.fill(this.f1840b, 0);
            Arrays.fill(this.f1841c, 0);
            this.f1842d = bVar;
            this.f1843e = bVar.b();
            this.f1844f = bVar.a();
            this.g = z;
            a();
            b();
        }

        private void a() {
            C0061f c0061f = this.f1839a.isEmpty() ? null : this.f1839a.get(0);
            if (c0061f != null && c0061f.f1852a == 0 && c0061f.f1853b == 0) {
                return;
            }
            C0061f c0061f2 = new C0061f();
            c0061f2.f1852a = 0;
            c0061f2.f1853b = 0;
            c0061f2.f1855d = false;
            c0061f2.f1854c = 0;
            c0061f2.f1856e = false;
            this.f1839a.add(0, c0061f2);
        }

        private void b() {
            int i = this.f1843e;
            int i2 = this.f1844f;
            for (int size = this.f1839a.size() - 1; size >= 0; size--) {
                C0061f c0061f = this.f1839a.get(size);
                int i3 = c0061f.f1852a;
                int i4 = c0061f.f1854c;
                int i5 = i3 + i4;
                int i6 = c0061f.f1853b + i4;
                if (this.g) {
                    while (i > i5) {
                        a(i, i2, size);
                        i--;
                    }
                    while (i2 > i6) {
                        b(i, i2, size);
                        i2--;
                    }
                }
                for (int i7 = 0; i7 < c0061f.f1854c; i7++) {
                    int i8 = c0061f.f1852a + i7;
                    int i9 = c0061f.f1853b + i7;
                    int i10 = this.f1842d.a(i8, i9) ? 1 : 2;
                    this.f1840b[i8] = (i9 << 5) | i10;
                    this.f1841c[i9] = (i8 << 5) | i10;
                }
                i = c0061f.f1852a;
                i2 = c0061f.f1853b;
            }
        }

        private void a(int i, int i2, int i3) {
            if (this.f1840b[i - 1] != 0) {
                return;
            }
            a(i, i2, i3, false);
        }

        private boolean a(int i, int i2, int i3, boolean z) {
            int i4;
            int i5;
            int i6;
            if (z) {
                i2--;
                i4 = i;
                i5 = i2;
            } else {
                i4 = i - 1;
                i5 = i4;
            }
            while (i3 >= 0) {
                C0061f c0061f = this.f1839a.get(i3);
                int i7 = c0061f.f1852a;
                int i8 = c0061f.f1854c;
                int i9 = i7 + i8;
                int i10 = c0061f.f1853b + i8;
                if (z) {
                    for (int i11 = i4 - 1; i11 >= i9; i11--) {
                        if (this.f1842d.b(i11, i5)) {
                            i6 = this.f1842d.a(i11, i5) ? 8 : 4;
                            this.f1841c[i5] = (i11 << 5) | 16;
                            this.f1840b[i11] = (i5 << 5) | i6;
                            return true;
                        }
                    }
                } else {
                    for (int i12 = i2 - 1; i12 >= i10; i12--) {
                        if (this.f1842d.b(i5, i12)) {
                            i6 = this.f1842d.a(i5, i12) ? 8 : 4;
                            int i13 = i - 1;
                            this.f1840b[i13] = (i12 << 5) | 16;
                            this.f1841c[i12] = (i13 << 5) | i6;
                            return true;
                        }
                    }
                }
                i4 = c0061f.f1852a;
                i2 = c0061f.f1853b;
                i3--;
            }
            return false;
        }

        private void b(int i, int i2, int i3) {
            if (this.f1841c[i2 - 1] != 0) {
                return;
            }
            a(i, i2, i3, true);
        }

        private void b(List<d> list, p pVar, int i, int i2, int i3) {
            if (!this.g) {
                pVar.a(i, i2);
                return;
            }
            for (int i4 = i2 - 1; i4 >= 0; i4--) {
                int i5 = i3 + i4;
                int i6 = this.f1840b[i5] & 31;
                if (i6 == 0) {
                    pVar.a(i + i4, 1);
                    Iterator<d> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().f1846b--;
                    }
                } else if (i6 == 4 || i6 == 8) {
                    int i7 = this.f1840b[i5] >> 5;
                    d a2 = a(list, i7, false);
                    pVar.b(i + i4, a2.f1846b - 1);
                    if (i6 == 4) {
                        pVar.a(a2.f1846b - 1, 1, this.f1842d.c(i5, i7));
                    }
                } else if (i6 == 16) {
                    list.add(new d(i5, i + i4, true));
                } else {
                    throw new IllegalStateException("unknown flag for pos " + i5 + " " + Long.toBinaryString(i6));
                }
            }
        }

        public void a(RecyclerView.g gVar) {
            a(new androidx.recyclerview.widget.b(gVar));
        }

        public void a(p pVar) {
            androidx.recyclerview.widget.c cVar;
            if (pVar instanceof androidx.recyclerview.widget.c) {
                cVar = (androidx.recyclerview.widget.c) pVar;
            } else {
                cVar = new androidx.recyclerview.widget.c(pVar);
            }
            ArrayList arrayList = new ArrayList();
            int i = this.f1843e;
            int i2 = this.f1844f;
            for (int size = this.f1839a.size() - 1; size >= 0; size--) {
                C0061f c0061f = this.f1839a.get(size);
                int i3 = c0061f.f1854c;
                int i4 = c0061f.f1852a + i3;
                int i5 = c0061f.f1853b + i3;
                if (i4 < i) {
                    b(arrayList, cVar, i4, i - i4, i4);
                }
                if (i5 < i2) {
                    a(arrayList, cVar, i4, i2 - i5, i5);
                }
                for (int i6 = i3 - 1; i6 >= 0; i6--) {
                    int[] iArr = this.f1840b;
                    int i7 = c0061f.f1852a;
                    if ((iArr[i7 + i6] & 31) == 2) {
                        cVar.a(i7 + i6, 1, this.f1842d.c(i7 + i6, c0061f.f1853b + i6));
                    }
                }
                i = c0061f.f1852a;
                i2 = c0061f.f1853b;
            }
            cVar.a();
        }

        private static d a(List<d> list, int i, boolean z) {
            int size = list.size() - 1;
            while (size >= 0) {
                d dVar = list.get(size);
                if (dVar.f1845a == i && dVar.f1847c == z) {
                    list.remove(size);
                    while (size < list.size()) {
                        list.get(size).f1846b += z ? 1 : -1;
                        size++;
                    }
                    return dVar;
                }
                size--;
            }
            return null;
        }

        private void a(List<d> list, p pVar, int i, int i2, int i3) {
            if (!this.g) {
                pVar.c(i, i2);
                return;
            }
            for (int i4 = i2 - 1; i4 >= 0; i4--) {
                int i5 = i3 + i4;
                int i6 = this.f1841c[i5] & 31;
                if (i6 == 0) {
                    pVar.c(i, 1);
                    Iterator<d> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().f1846b++;
                    }
                } else if (i6 == 4 || i6 == 8) {
                    int i7 = this.f1841c[i5] >> 5;
                    pVar.b(a(list, i7, true).f1846b, i);
                    if (i6 == 4) {
                        pVar.a(i, 1, this.f1842d.c(i7, i5));
                    }
                } else if (i6 == 16) {
                    list.add(new d(i5, i, false));
                } else {
                    throw new IllegalStateException("unknown flag for pos " + i5 + " " + Long.toBinaryString(i6));
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0042, code lost:
    
        if (r24[r13 - 1] < r24[r13 + r5]) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00ba, code lost:
    
        if (r25[r13 - 1] < r25[r13 + 1]) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00e3 A[LOOP:4: B:54:0x00cf->B:58:0x00e3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ee A[EDGE_INSN: B:59:0x00ee->B:60:0x00ee BREAK  A[LOOP:4: B:54:0x00cf->B:58:0x00e3], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static androidx.recyclerview.widget.f.C0061f a(androidx.recyclerview.widget.f.b r19, int r20, int r21, int r22, int r23, int[] r24, int[] r25, int r26) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.f.a(androidx.recyclerview.widget.f$b, int, int, int, int, int[], int[], int):androidx.recyclerview.widget.f$f");
    }
}
