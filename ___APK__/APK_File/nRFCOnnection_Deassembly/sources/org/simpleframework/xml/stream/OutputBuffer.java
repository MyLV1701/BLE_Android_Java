package org.simpleframework.xml.stream;

import java.io.Writer;

/* loaded from: classes.dex */
class OutputBuffer {
    private StringBuilder text = new StringBuilder();

    public void append(char c2) {
        this.text.append(c2);
    }

    public void clear() {
        this.text.setLength(0);
    }

    public void write(Writer writer) {
        writer.append((CharSequence) this.text);
    }

    public void append(String str) {
        this.text.append(str);
    }

    public void append(char[] cArr) {
        this.text.append(cArr, 0, cArr.length);
    }

    public void append(char[] cArr, int i, int i2) {
        this.text.append(cArr, i, i2);
    }

    public void append(String str, int i, int i2) {
        this.text.append((CharSequence) str, i, i2);
    }
}
