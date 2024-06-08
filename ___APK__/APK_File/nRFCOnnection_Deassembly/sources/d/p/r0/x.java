package d.p.r0;

import no.nordicsemi.android.ble.error.GattError;
import no.nordicsemi.android.dfu.DfuBaseService;
import no.nordicsemi.android.log.LogContract;
import no.nordicsemi.android.mcp.ble.parser.AppearanceLibrary;
import no.nordicsemi.android.mcp.database.AdvertisingContract;
import no.nordicsemi.android.mcp.database.CCCDContract;
import no.nordicsemi.android.mcp.database.UuidColumns;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class x {

    /* renamed from: d, reason: collision with root package name */
    private static x[] f3120d;

    /* renamed from: e, reason: collision with root package name */
    public static final x f3121e;

    /* renamed from: f, reason: collision with root package name */
    public static final x f3122f;
    public static final x g;
    public static final x h;
    public static final x i;

    /* renamed from: a, reason: collision with root package name */
    private final int f3123a;

    /* renamed from: b, reason: collision with root package name */
    private final String f3124b;

    /* renamed from: c, reason: collision with root package name */
    private final int f3125c;

    static {
        d.q.c.b(x.class);
        f3120d = new x[0];
        new x(0, "count", 255);
        f3121e = new x(1, "", 255);
        new x(2, "isna", 1);
        new x(3, "iserror", 1);
        f3122f = new x(4, "sum", 255);
        new x(5, "average", 255);
        new x(6, "min", 255);
        new x(7, "max", 255);
        new x(8, "row", 255);
        new x(9, "column", 255);
        new x(10, "na", 0);
        new x(11, "npv", 255);
        new x(12, "stdev", 255);
        new x(13, "dollar", 2);
        new x(14, "fixed", 255);
        new x(15, "sin", 1);
        new x(16, "cos", 1);
        new x(17, "tan", 1);
        new x(18, "atan", 1);
        new x(19, "pi", 0);
        new x(20, "sqrt", 1);
        new x(21, "exp", 1);
        new x(22, "ln", 1);
        new x(23, "log10", 1);
        new x(24, "abs", 1);
        new x(25, "int", 1);
        new x(26, "sign", 1);
        new x(27, "round", 2);
        new x(28, "lookup", 2);
        new x(29, "index", 3);
        new x(30, "rept", 2);
        new x(31, "mid", 3);
        new x(32, "len", 1);
        new x(33, CCCDContract.CCCDColumns.VALUE, 1);
        new x(34, "true", 0);
        new x(35, "false", 0);
        new x(36, "and", 255);
        new x(37, "or", 255);
        new x(38, "not", 1);
        new x(39, "mod", 2);
        new x(40, "dcount", 3);
        new x(41, "dsum", 3);
        new x(42, "daverage", 3);
        new x(43, "dmin", 3);
        new x(44, "dmax", 3);
        new x(45, "dstdev", 3);
        new x(46, "var", 255);
        new x(47, "dvar", 3);
        new x(48, "text", 2);
        new x(49, "linest", 255);
        new x(50, "trend", 255);
        new x(51, "logest", 255);
        new x(52, "growth", 255);
        new x(56, "pv", 255);
        new x(57, "fv", 255);
        new x(58, "nper", 255);
        new x(59, "pmt", 255);
        new x(60, "rate", 255);
        new x(63, "rand", 0);
        new x(64, "match", 3);
        new x(65, "date", 3);
        new x(66, LogContract.LogColumns.TIME, 3);
        new x(67, "day", 1);
        new x(68, "month", 1);
        new x(69, "year", 1);
        new x(70, "weekday", 2);
        new x(71, "hour", 1);
        new x(72, "minute", 1);
        new x(73, "second", 1);
        new x(74, "now", 0);
        new x(75, "areas", 255);
        new x(76, "rows", 1);
        new x(77, "columns", 255);
        new x(78, "offset", 255);
        new x(82, "search", 255);
        new x(83, "transpose", 255);
        new x(84, "error", 1);
        new x(86, UuidColumns.SPECIFICATION_TYPE, 1);
        new x(97, "atan2", 1);
        new x(98, "asin", 1);
        new x(99, "acos", 1);
        new x(100, "choose", 255);
        new x(101, "hlookup", 255);
        new x(102, "vlookup", 255);
        new x(105, "isref", 1);
        new x(109, "log", 255);
        new x(111, "char", 1);
        new x(112, "lower", 1);
        new x(113, "upper", 1);
        new x(114, "proper", 1);
        new x(115, "left", 255);
        new x(116, "right", 255);
        new x(117, "exact", 2);
        new x(118, "trim", 1);
        new x(119, "replace", 4);
        new x(120, "substitute", 255);
        new x(121, "code", 1);
        new x(124, "find", 255);
        new x(125, "cell", 2);
        new x(126, "iserr", 1);
        new x(127, "istext", 1);
        new x(128, "isnumber", 1);
        new x(GattError.GATT_INTERNAL_ERROR, "isblank", 1);
        new x(GattError.GATT_WRONG_STATE, "t", 1);
        new x(GattError.GATT_DB_FULL, "n", 1);
        new x(GattError.GATT_SERVICE_STARTED, "datevalue", 1);
        new x(GattError.GATT_ENCRYPTED_NO_MITM, "timevalue", 1);
        new x(GattError.GATT_NOT_ENCRYPTED, "sln", 3);
        new x(GattError.GATT_CONGESTED, "syd", 3);
        new x(144, "ddb", 255);
        new x(148, "indirect", 255);
        new x(162, "clean", 1);
        new x(163, "mdeterm", 255);
        new x(164, "minverse", 255);
        new x(165, "mmult", 255);
        new x(167, "ipmt", 255);
        new x(168, "ppmt", 255);
        new x(169, "counta", 255);
        new x(183, "product", 255);
        new x(184, "fact", 1);
        new x(189, "dproduct", 3);
        new x(190, "isnontext", 1);
        new x(AppearanceLibrary.APPEARANCE_GENERIC_SPORT_WATCH, "stdevp", 255);
        new x(194, "varp", 255);
        new x(195, "dstdevp", 255);
        new x(196, "dvarp", 255);
        new x(197, "trunc", 255);
        new x(198, "islogical", 1);
        new x(199, "dcounta", 255);
        new x(205, "findb", 255);
        new x(206, "searchb", 3);
        new x(207, "replaceb", 4);
        new x(208, "leftb", 255);
        new x(209, "rightb", 255);
        new x(210, "midb", 3);
        new x(211, "lenb", 1);
        new x(212, "roundup", 2);
        new x(213, "rounddown", 2);
        new x(216, "rank", 255);
        new x(219, "address", 255);
        new x(220, "days360", 255);
        new x(221, "today", 0);
        new x(222, "vdb", 255);
        new x(227, "median", 255);
        g = new x(228, "sumproduct", 255);
        new x(229, "sinh", 1);
        new x(230, "cosh", 1);
        new x(231, "tanh", 1);
        new x(232, "asinh", 1);
        new x(233, "acosh", 1);
        new x(234, "atanh", 1);
        new x(244, "info", 1);
        new x(269, "avedev", 255);
        new x(270, "betadist", 255);
        new x(271, "gammaln", 1);
        new x(272, "betainv", 255);
        new x(273, "binomdist", 4);
        new x(274, "chidist", 2);
        new x(275, "chiinv", 2);
        new x(276, "combin", 2);
        new x(277, "confidence", 3);
        new x(278, "critbinom", 3);
        new x(279, "even", 1);
        new x(280, "expondist", 3);
        new x(281, "fdist", 3);
        new x(282, "finv", 3);
        new x(DfuBaseService.NOTIFICATION_ID, "fisher", 1);
        new x(284, "fisherinv", 1);
        new x(285, "floor", 2);
        new x(286, "gammadist", 4);
        new x(287, "gammainv", 3);
        new x(288, "ceiling", 2);
        new x(289, "hypgeomdist", 4);
        new x(290, "lognormdist", 3);
        new x(291, "loginv", 3);
        new x(292, "negbinomdist", 3);
        new x(293, "normdist", 4);
        new x(294, "normsdist", 1);
        new x(295, "norminv", 3);
        new x(296, "normsinv", 1);
        new x(297, "standardize", 3);
        new x(298, "odd", 1);
        new x(299, "permut", 2);
        new x(300, "poisson", 3);
        new x(301, "tdist", 3);
        new x(302, "weibull", 4);
        new x(303, "sumxmy2", 255);
        new x(304, "sumx2my2", 255);
        new x(305, "sumx2py2", 255);
        new x(306, "chitest", 255);
        new x(307, "correl", 255);
        new x(308, "covar", 255);
        new x(309, "forecast", 255);
        new x(310, "ftest", 255);
        new x(311, "intercept", 255);
        new x(312, "pearson", 255);
        new x(313, "rsq", 255);
        new x(314, "steyx", 255);
        new x(315, "slope", 2);
        new x(316, "ttest", 255);
        new x(317, "prob", 255);
        new x(318, "devsq", 255);
        new x(319, "geomean", 255);
        new x(320, "harmean", 255);
        new x(321, "sumsq", 255);
        new x(322, "kurt", 255);
        new x(323, "skew", 255);
        new x(324, "ztest", 255);
        new x(325, "large", 255);
        new x(326, "small", 255);
        new x(327, "quartile", 255);
        new x(328, "percentile", 255);
        new x(329, "percentrank", 255);
        new x(330, AdvertisingContract.OldConfigColumns.MODE, 255);
        new x(331, "trimmean", 255);
        new x(332, "tinv", 2);
        new x(336, "concatenate", 255);
        new x(337, "power", 2);
        new x(342, "radians", 1);
        new x(343, "degrees", 1);
        new x(344, "subtotal", 255);
        new x(345, "sumif", 255);
        new x(346, "countif", 2);
        new x(347, "countblank", 1);
        new x(359, "hyperlink", 2);
        new x(361, "averagea", 255);
        new x(362, "maxa", 255);
        new x(363, "mina", 255);
        new x(364, "stdevpa", 255);
        new x(365, "varpa", 255);
        new x(366, "stdeva", 255);
        new x(367, "vara", 255);
        h = new x(65534, "if", 255);
        i = new x(65535, "", 0);
    }

    private x(int i2, String str, int i3) {
        this.f3123a = i2;
        this.f3124b = str;
        this.f3125c = i3;
        x[] xVarArr = f3120d;
        x[] xVarArr2 = new x[xVarArr.length + 1];
        System.arraycopy(xVarArr, 0, xVarArr2, 0, xVarArr.length);
        xVarArr2[f3120d.length] = this;
        f3120d = xVarArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static x[] d() {
        return f3120d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        return this.f3123a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b() {
        return this.f3125c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String c() {
        return this.f3124b;
    }

    public int hashCode() {
        return this.f3123a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String a(d.o oVar) {
        return oVar.g().a(this);
    }

    public static x a(int i2) {
        x xVar;
        int i3 = 0;
        while (true) {
            x[] xVarArr = f3120d;
            if (i3 >= xVarArr.length) {
                xVar = null;
                break;
            }
            if (xVarArr[i3].f3123a == i2) {
                xVar = xVarArr[i3];
                break;
            }
            i3++;
        }
        return xVar != null ? xVar : i;
    }

    public static x a(String str, d.o oVar) {
        x a2 = oVar.g().a(str);
        return a2 != null ? a2 : i;
    }
}
