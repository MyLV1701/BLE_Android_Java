package org.simpleframework.xml.stream;

import java.io.BufferedWriter;
import java.io.Writer;

/* loaded from: classes.dex */
class Formatter {
    private OutputBuffer buffer = new OutputBuffer();
    private Indenter indenter;
    private Tag last;
    private String prolog;
    private Writer result;
    private static final char[] NAMESPACE = {'x', 'm', 'l', 'n', 's'};
    private static final char[] LESS = {'&', 'l', 't', ';'};
    private static final char[] GREATER = {'&', 'g', 't', ';'};
    private static final char[] DOUBLE = {'&', 'q', 'u', 'o', 't', ';'};
    private static final char[] SINGLE = {'&', 'a', 'p', 'o', 's', ';'};
    private static final char[] AND = {'&', 'a', 'm', 'p', ';'};
    private static final char[] OPEN = {'<', '!', '-', '-', ' '};
    private static final char[] CLOSE = {' ', '-', '-', '>'};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum Tag {
        COMMENT,
        START,
        TEXT,
        END
    }

    public Formatter(Writer writer, Format format) {
        this.result = new BufferedWriter(writer, 1024);
        this.indenter = new Indenter(format);
        this.prolog = format.getProlog();
    }

    private void append(char c2) {
        this.buffer.append(c2);
    }

    private void data(String str) {
        write("<![CDATA[");
        write(str);
        write("]]>");
    }

    private void escape(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            escape(str.charAt(i));
        }
    }

    private boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    private boolean isText(char c2) {
        if (c2 == '\t' || c2 == '\n' || c2 == '\r' || c2 == ' ') {
            return true;
        }
        return c2 > ' ' && c2 <= '~' && c2 != 247;
    }

    private char[] symbol(char c2) {
        if (c2 == '\"') {
            return DOUBLE;
        }
        if (c2 == '<') {
            return LESS;
        }
        if (c2 == '>') {
            return GREATER;
        }
        if (c2 == '&') {
            return AND;
        }
        if (c2 != '\'') {
            return null;
        }
        return SINGLE;
    }

    private String unicode(char c2) {
        return Integer.toString(c2);
    }

    private void write(char c2) {
        this.buffer.write(this.result);
        this.buffer.clear();
        this.result.write(c2);
    }

    public void flush() {
        this.buffer.write(this.result);
        this.buffer.clear();
        this.result.flush();
    }

    public void writeAttribute(String str, String str2, String str3) {
        if (this.last == Tag.START) {
            write(' ');
            write(str, str3);
            write('=');
            write('\"');
            escape(str2);
            write('\"');
            return;
        }
        throw new NodeException("Start element required");
    }

    public void writeComment(String str) {
        String pVar = this.indenter.top();
        if (this.last == Tag.START) {
            append('>');
        }
        if (pVar != null) {
            append(pVar);
            append(OPEN);
            append(str);
            append(CLOSE);
        }
        this.last = Tag.COMMENT;
    }

    public void writeEnd(String str, String str2) {
        String pop = this.indenter.pop();
        Tag tag = this.last;
        if (tag == Tag.START) {
            write('/');
            write('>');
        } else {
            if (tag != Tag.TEXT) {
                write(pop);
            }
            if (this.last != Tag.START) {
                write('<');
                write('/');
                write(str, str2);
                write('>');
            }
        }
        this.last = Tag.END;
    }

    public void writeNamespace(String str, String str2) {
        if (this.last == Tag.START) {
            write(' ');
            write(NAMESPACE);
            if (!isEmpty(str2)) {
                write(':');
                write(str2);
            }
            write('=');
            write('\"');
            escape(str);
            write('\"');
            return;
        }
        throw new NodeException("Start element required");
    }

    public void writeProlog() {
        String str = this.prolog;
        if (str != null) {
            write(str);
            write("\n");
        }
    }

    public void writeStart(String str, String str2) {
        String push = this.indenter.push();
        if (this.last == Tag.START) {
            append('>');
        }
        flush();
        append(push);
        append('<');
        if (!isEmpty(str2)) {
            append(str2);
            append(':');
        }
        append(str);
        this.last = Tag.START;
    }

    public void writeText(String str) {
        writeText(str, Mode.ESCAPE);
    }

    private void append(char[] cArr) {
        this.buffer.append(cArr);
    }

    public void writeText(String str, Mode mode) {
        if (this.last == Tag.START) {
            write('>');
        }
        if (mode == Mode.DATA) {
            data(str);
        } else {
            escape(str);
        }
        this.last = Tag.TEXT;
    }

    private void append(String str) {
        this.buffer.append(str);
    }

    private void escape(char c2) {
        char[] symbol = symbol(c2);
        if (symbol != null) {
            write(symbol);
        } else {
            write(c2);
        }
    }

    private void write(char[] cArr) {
        this.buffer.write(this.result);
        this.buffer.clear();
        this.result.write(cArr);
    }

    private void write(String str) {
        this.buffer.write(this.result);
        this.buffer.clear();
        this.result.write(str);
    }

    private void write(String str, String str2) {
        this.buffer.write(this.result);
        this.buffer.clear();
        if (!isEmpty(str2)) {
            this.result.write(str2);
            this.result.write(58);
        }
        this.result.write(str);
    }
}
