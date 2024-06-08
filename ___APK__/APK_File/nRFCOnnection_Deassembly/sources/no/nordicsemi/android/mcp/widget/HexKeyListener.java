package no.nordicsemi.android.mcp.widget;

/* loaded from: classes.dex */
public class HexKeyListener extends android.text.method.NumberKeyListener {
    @Override // android.text.method.NumberKeyListener
    protected char[] getAcceptedChars() {
        return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'a', 'b', 'c', 'd', 'e', 'f'};
    }

    @Override // android.text.method.KeyListener
    public int getInputType() {
        return 528529;
    }
}
