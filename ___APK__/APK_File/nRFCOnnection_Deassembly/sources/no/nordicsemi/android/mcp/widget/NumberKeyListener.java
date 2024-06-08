package no.nordicsemi.android.mcp.widget;

/* loaded from: classes.dex */
public class NumberKeyListener extends android.text.method.NumberKeyListener {
    @Override // android.text.method.NumberKeyListener
    protected char[] getAcceptedChars() {
        return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-'};
    }

    public int getInputType() {
        return 528530;
    }
}
