package d.r;

/* loaded from: classes.dex */
public final class k {

    /* renamed from: b, reason: collision with root package name */
    private static k[] f3177b = new k[90];

    /* renamed from: c, reason: collision with root package name */
    public static final k f3178c;

    /* renamed from: a, reason: collision with root package name */
    private int f3179a;

    static {
        new k(0);
        new k(1);
        new k(2);
        new k(3);
        new k(4);
        new k(5);
        new k(6);
        new k(7);
        new k(8);
        f3178c = new k(9);
        new k(10);
        new k(11);
        new k(12);
        new k(13);
        new k(14);
        new k(15);
        new k(16);
        new k(17);
        new k(18);
        new k(19);
        new k(20);
        new k(21);
        new k(22);
        new k(23);
        new k(24);
        new k(25);
        new k(26);
        new k(27);
        new k(28);
        new k(29);
        new k(30);
        new k(31);
        new k(32);
        new k(33);
        new k(34);
        new k(35);
        new k(36);
        new k(37);
        new k(38);
        new k(39);
        new k(40);
        new k(41);
        new k(42);
        new k(43);
        new k(44);
        new k(45);
        new k(46);
        new k(47);
        new k(50);
        new k(51);
        new k(52);
        new k(53);
        new k(54);
        new k(55);
        new k(56);
        new k(57);
        new k(58);
        new k(59);
        new k(60);
        new k(61);
        new k(62);
        new k(63);
        new k(64);
        new k(65);
        new k(66);
        new k(67);
        new k(68);
        new k(69);
        new k(70);
        new k(75);
        new k(76);
        new k(77);
        new k(78);
        new k(79);
        new k(80);
        new k(81);
        new k(82);
        new k(83);
        new k(88);
        new k(89);
    }

    private k(int i, boolean z) {
        this.f3179a = i;
        k[] kVarArr = f3177b;
        if (i >= kVarArr.length && z) {
            k[] kVarArr2 = new k[i + 1];
            System.arraycopy(kVarArr, 0, kVarArr2, 0, kVarArr.length);
            f3177b = kVarArr2;
        }
        k[] kVarArr3 = f3177b;
        if (i < kVarArr3.length) {
            kVarArr3[i] = this;
        }
    }

    public int a() {
        return this.f3179a;
    }

    private k(int i) {
        this(i, true);
    }
}
