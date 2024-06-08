package f.b.c;

/* loaded from: classes.dex */
public final class a {
    public static String a(String str) {
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int i = 0; i != charArray.length; i++) {
            char c2 = charArray[i];
            if ('A' <= c2 && 'Z' >= c2) {
                charArray[i] = (char) ((c2 - 'A') + 97);
                z = true;
            }
        }
        return z ? new String(charArray) : str;
    }
}
