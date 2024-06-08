package org.simpleframework.xml.stream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class HyphenBuilder implements Style {

    /* loaded from: classes.dex */
    private class Parser extends Splitter {
        @Override // org.simpleframework.xml.stream.Splitter
        protected void commit(char[] cArr, int i, int i2) {
            this.builder.append(cArr, i, i2);
            if (i + i2 < this.count) {
                this.builder.append('-');
            }
        }

        @Override // org.simpleframework.xml.stream.Splitter
        protected void parse(char[] cArr, int i, int i2) {
            cArr[i] = toLower(cArr[i]);
        }

        private Parser(String str) {
            super(str);
        }
    }

    @Override // org.simpleframework.xml.stream.Style
    public String getAttribute(String str) {
        if (str != null) {
            return new Parser(str).process();
        }
        return null;
    }

    @Override // org.simpleframework.xml.stream.Style
    public String getElement(String str) {
        if (str != null) {
            return new Parser(str).process();
        }
        return null;
    }
}
