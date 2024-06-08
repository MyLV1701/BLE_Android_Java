package d.p;

/* loaded from: classes.dex */
public class e {

    /* renamed from: b, reason: collision with root package name */
    private static e[] f2854b = new e[0];

    /* renamed from: c, reason: collision with root package name */
    public static final e f2855c;

    /* renamed from: d, reason: collision with root package name */
    public static final e f2856d;

    /* renamed from: a, reason: collision with root package name */
    private int f2857a;

    static {
        new e("Consolidate_Area", 0);
        new e("Auto_Open", 1);
        new e("Auto_Open", 2);
        new e("Extract", 3);
        new e("Database", 4);
        new e("Criteria", 5);
        f2855c = new e("Print_Area", 6);
        f2856d = new e("Print_Titles", 7);
        new e("Recorder", 8);
        new e("Data_Form", 9);
        new e("Auto_Activate", 10);
        new e("Auto_Deactivate", 11);
        new e("Sheet_Title", 11);
        new e("_FilterDatabase", 13);
    }

    private e(String str, int i) {
        this.f2857a = i;
        e[] eVarArr = f2854b;
        f2854b = new e[eVarArr.length + 1];
        System.arraycopy(eVarArr, 0, f2854b, 0, eVarArr.length);
        f2854b[eVarArr.length] = this;
    }

    public int a() {
        return this.f2857a;
    }
}
