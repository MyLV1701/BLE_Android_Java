package androidx.recyclerview.widget;

import androidx.recyclerview.widget.a;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class q {

    /* renamed from: a, reason: collision with root package name */
    final a f1916a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface a {
        a.b a(int i, int i2, int i3, Object obj);

        void a(a.b bVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public q(a aVar) {
        this.f1916a = aVar;
    }

    private void c(List<a.b> list, int i, a.b bVar, int i2, a.b bVar2) {
        int i3 = bVar.f1787d < bVar2.f1785b ? -1 : 0;
        if (bVar.f1785b < bVar2.f1785b) {
            i3++;
        }
        int i4 = bVar2.f1785b;
        int i5 = bVar.f1785b;
        if (i4 <= i5) {
            bVar.f1785b = i5 + bVar2.f1787d;
        }
        int i6 = bVar2.f1785b;
        int i7 = bVar.f1787d;
        if (i6 <= i7) {
            bVar.f1787d = i7 + bVar2.f1787d;
        }
        bVar2.f1785b += i3;
        list.set(i, bVar2);
        list.set(i2, bVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(List<a.b> list) {
        while (true) {
            int b2 = b(list);
            if (b2 == -1) {
                return;
            } else {
                a(list, b2, b2 + 1);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void b(java.util.List<androidx.recyclerview.widget.a.b> r9, int r10, androidx.recyclerview.widget.a.b r11, int r12, androidx.recyclerview.widget.a.b r13) {
        /*
            r8 = this;
            int r0 = r11.f1787d
            int r1 = r13.f1785b
            r2 = 4
            r3 = 0
            r4 = 1
            if (r0 >= r1) goto Ld
            int r1 = r1 - r4
            r13.f1785b = r1
            goto L20
        Ld:
            int r5 = r13.f1787d
            int r1 = r1 + r5
            if (r0 >= r1) goto L20
            int r5 = r5 - r4
            r13.f1787d = r5
            androidx.recyclerview.widget.q$a r0 = r8.f1916a
            int r1 = r11.f1785b
            java.lang.Object r5 = r13.f1786c
            androidx.recyclerview.widget.a$b r0 = r0.a(r2, r1, r4, r5)
            goto L21
        L20:
            r0 = r3
        L21:
            int r1 = r11.f1785b
            int r5 = r13.f1785b
            if (r1 > r5) goto L2b
            int r5 = r5 + r4
            r13.f1785b = r5
            goto L41
        L2b:
            int r6 = r13.f1787d
            int r7 = r5 + r6
            if (r1 >= r7) goto L41
            int r5 = r5 + r6
            int r5 = r5 - r1
            androidx.recyclerview.widget.q$a r3 = r8.f1916a
            int r1 = r1 + r4
            java.lang.Object r4 = r13.f1786c
            androidx.recyclerview.widget.a$b r3 = r3.a(r2, r1, r5, r4)
            int r1 = r13.f1787d
            int r1 = r1 - r5
            r13.f1787d = r1
        L41:
            r9.set(r12, r11)
            int r11 = r13.f1787d
            if (r11 <= 0) goto L4c
            r9.set(r10, r13)
            goto L54
        L4c:
            r9.remove(r10)
            androidx.recyclerview.widget.q$a r11 = r8.f1916a
            r11.a(r13)
        L54:
            if (r0 == 0) goto L59
            r9.add(r10, r0)
        L59:
            if (r3 == 0) goto L5e
            r9.add(r10, r3)
        L5e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.q.b(java.util.List, int, androidx.recyclerview.widget.a$b, int, androidx.recyclerview.widget.a$b):void");
    }

    private void a(List<a.b> list, int i, int i2) {
        a.b bVar = list.get(i);
        a.b bVar2 = list.get(i2);
        int i3 = bVar2.f1784a;
        if (i3 == 1) {
            c(list, i, bVar, i2, bVar2);
        } else if (i3 == 2) {
            a(list, i, bVar, i2, bVar2);
        } else {
            if (i3 != 4) {
                return;
            }
            b(list, i, bVar, i2, bVar2);
        }
    }

    void a(List<a.b> list, int i, a.b bVar, int i2, a.b bVar2) {
        boolean z;
        int i3 = bVar.f1785b;
        int i4 = bVar.f1787d;
        boolean z2 = false;
        if (i3 < i4) {
            if (bVar2.f1785b == i3 && bVar2.f1787d == i4 - i3) {
                z = false;
                z2 = true;
            } else {
                z = false;
            }
        } else if (bVar2.f1785b == i4 + 1 && bVar2.f1787d == i3 - i4) {
            z = true;
            z2 = true;
        } else {
            z = true;
        }
        int i5 = bVar.f1787d;
        int i6 = bVar2.f1785b;
        if (i5 < i6) {
            bVar2.f1785b = i6 - 1;
        } else {
            int i7 = bVar2.f1787d;
            if (i5 < i6 + i7) {
                bVar2.f1787d = i7 - 1;
                bVar.f1784a = 2;
                bVar.f1787d = 1;
                if (bVar2.f1787d == 0) {
                    list.remove(i2);
                    this.f1916a.a(bVar2);
                    return;
                }
                return;
            }
        }
        int i8 = bVar.f1785b;
        int i9 = bVar2.f1785b;
        a.b bVar3 = null;
        if (i8 <= i9) {
            bVar2.f1785b = i9 + 1;
        } else {
            int i10 = bVar2.f1787d;
            if (i8 < i9 + i10) {
                bVar3 = this.f1916a.a(2, i8 + 1, (i9 + i10) - i8, null);
                bVar2.f1787d = bVar.f1785b - bVar2.f1785b;
            }
        }
        if (z2) {
            list.set(i, bVar2);
            list.remove(i2);
            this.f1916a.a(bVar);
            return;
        }
        if (z) {
            if (bVar3 != null) {
                int i11 = bVar.f1785b;
                if (i11 > bVar3.f1785b) {
                    bVar.f1785b = i11 - bVar3.f1787d;
                }
                int i12 = bVar.f1787d;
                if (i12 > bVar3.f1785b) {
                    bVar.f1787d = i12 - bVar3.f1787d;
                }
            }
            int i13 = bVar.f1785b;
            if (i13 > bVar2.f1785b) {
                bVar.f1785b = i13 - bVar2.f1787d;
            }
            int i14 = bVar.f1787d;
            if (i14 > bVar2.f1785b) {
                bVar.f1787d = i14 - bVar2.f1787d;
            }
        } else {
            if (bVar3 != null) {
                int i15 = bVar.f1785b;
                if (i15 >= bVar3.f1785b) {
                    bVar.f1785b = i15 - bVar3.f1787d;
                }
                int i16 = bVar.f1787d;
                if (i16 >= bVar3.f1785b) {
                    bVar.f1787d = i16 - bVar3.f1787d;
                }
            }
            int i17 = bVar.f1785b;
            if (i17 >= bVar2.f1785b) {
                bVar.f1785b = i17 - bVar2.f1787d;
            }
            int i18 = bVar.f1787d;
            if (i18 >= bVar2.f1785b) {
                bVar.f1787d = i18 - bVar2.f1787d;
            }
        }
        list.set(i, bVar2);
        if (bVar.f1785b != bVar.f1787d) {
            list.set(i2, bVar);
        } else {
            list.remove(i2);
        }
        if (bVar3 != null) {
            list.add(i, bVar3);
        }
    }

    private int b(List<a.b> list) {
        boolean z = false;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (list.get(size).f1784a != 8) {
                z = true;
            } else if (z) {
                return size;
            }
        }
        return -1;
    }
}
