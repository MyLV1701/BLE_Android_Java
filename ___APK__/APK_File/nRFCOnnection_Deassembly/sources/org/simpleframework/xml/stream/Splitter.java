package org.simpleframework.xml.stream;

/* loaded from: classes.dex */
abstract class Splitter {
    protected StringBuilder builder = new StringBuilder();
    protected int count;
    protected int off;
    protected char[] text;

    public Splitter(String str) {
        this.text = str.toCharArray();
        this.count = this.text.length;
    }

    private boolean acronym() {
        int i = this.off;
        int i2 = 0;
        while (i < this.count && isUpper(this.text[i])) {
            i2++;
            i++;
        }
        if (i2 > 1) {
            if (i < this.count && isUpper(this.text[i - 1])) {
                i--;
            }
            char[] cArr = this.text;
            int i3 = this.off;
            commit(cArr, i3, i - i3);
            this.off = i;
        }
        return i2 > 1;
    }

    private boolean isDigit(char c2) {
        return Character.isDigit(c2);
    }

    private boolean isLetter(char c2) {
        return Character.isLetter(c2);
    }

    private boolean isSpecial(char c2) {
        return !Character.isLetterOrDigit(c2);
    }

    private boolean isUpper(char c2) {
        return Character.isUpperCase(c2);
    }

    private boolean number() {
        int i = this.off;
        int i2 = 0;
        while (i < this.count && isDigit(this.text[i])) {
            i2++;
            i++;
        }
        if (i2 > 0) {
            char[] cArr = this.text;
            int i3 = this.off;
            commit(cArr, i3, i - i3);
        }
        this.off = i;
        return i2 > 0;
    }

    private void token() {
        int i = this.off;
        while (i < this.count) {
            char c2 = this.text[i];
            if (!isLetter(c2) || (i > this.off && isUpper(c2))) {
                break;
            } else {
                i++;
            }
        }
        int i2 = this.off;
        if (i > i2) {
            parse(this.text, i2, i - i2);
            char[] cArr = this.text;
            int i3 = this.off;
            commit(cArr, i3, i - i3);
        }
        this.off = i;
    }

    protected abstract void commit(char[] cArr, int i, int i2);

    protected abstract void parse(char[] cArr, int i, int i2);

    public String process() {
        while (this.off < this.count) {
            while (true) {
                int i = this.off;
                if (i >= this.count || !isSpecial(this.text[i])) {
                    break;
                }
                this.off++;
            }
            if (!acronym()) {
                token();
                number();
            }
        }
        return this.builder.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public char toLower(char c2) {
        return Character.toLowerCase(c2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public char toUpper(char c2) {
        return Character.toUpperCase(c2);
    }
}
