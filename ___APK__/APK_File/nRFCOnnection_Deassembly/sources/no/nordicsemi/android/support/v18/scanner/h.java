package no.nordicsemi.android.support.v18.scanner;

import java.util.Arrays;

/* loaded from: classes.dex */
class h {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            return obj == obj2;
        }
        if ((obj instanceof Object[]) && (obj2 instanceof Object[])) {
            return Arrays.deepEquals((Object[]) obj, (Object[]) obj2);
        }
        if ((obj instanceof boolean[]) && (obj2 instanceof boolean[])) {
            return Arrays.equals((boolean[]) obj, (boolean[]) obj2);
        }
        if ((obj instanceof byte[]) && (obj2 instanceof byte[])) {
            return Arrays.equals((byte[]) obj, (byte[]) obj2);
        }
        if ((obj instanceof char[]) && (obj2 instanceof char[])) {
            return Arrays.equals((char[]) obj, (char[]) obj2);
        }
        if ((obj instanceof double[]) && (obj2 instanceof double[])) {
            return Arrays.equals((double[]) obj, (double[]) obj2);
        }
        if ((obj instanceof float[]) && (obj2 instanceof float[])) {
            return Arrays.equals((float[]) obj, (float[]) obj2);
        }
        if ((obj instanceof int[]) && (obj2 instanceof int[])) {
            return Arrays.equals((int[]) obj, (int[]) obj2);
        }
        if ((obj instanceof long[]) && (obj2 instanceof long[])) {
            return Arrays.equals((long[]) obj, (long[]) obj2);
        }
        if ((obj instanceof short[]) && (obj2 instanceof short[])) {
            return Arrays.equals((short[]) obj, (short[]) obj2);
        }
        return obj.equals(obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean b(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(Object obj) {
        return obj == null ? "null" : obj.toString();
    }
}
