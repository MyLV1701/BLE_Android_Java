package org.simpleframework.xml.core;

import org.simpleframework.xml.filter.Filter;

/* loaded from: classes.dex */
class TemplateEngine {
    private Filter filter;
    private int off;
    private Template source = new Template();
    private Template name = new Template();
    private Template text = new Template();

    public TemplateEngine(Filter filter) {
        this.filter = filter;
    }

    private void name() {
        while (true) {
            int i = this.off;
            Template template = this.source;
            if (i >= template.count) {
                break;
            }
            char[] cArr = template.buf;
            this.off = i + 1;
            char c2 = cArr[i];
            if (c2 == '}') {
                replace();
                break;
            }
            this.name.append(c2);
        }
        if (this.name.length() > 0) {
            this.text.append("${");
            this.text.append(this.name);
        }
    }

    private void parse() {
        int i;
        while (true) {
            int i2 = this.off;
            Template template = this.source;
            int i3 = template.count;
            if (i2 >= i3) {
                return;
            }
            char[] cArr = template.buf;
            this.off = i2 + 1;
            char c2 = cArr[i2];
            if (c2 == '$' && (i = this.off) < i3) {
                this.off = i + 1;
                if (cArr[i] == '{') {
                    name();
                } else {
                    this.off--;
                }
            }
            this.text.append(c2);
        }
    }

    private void replace() {
        if (this.name.length() > 0) {
            replace(this.name);
        }
        this.name.clear();
    }

    public void clear() {
        this.name.clear();
        this.text.clear();
        this.source.clear();
        this.off = 0;
    }

    public String process(String str) {
        if (str.indexOf(36) < 0) {
            return str;
        }
        try {
            this.source.append(str);
            parse();
            return this.text.toString();
        } finally {
            clear();
        }
    }

    private void replace(Template template) {
        replace(template.toString());
    }

    private void replace(String str) {
        String replace = this.filter.replace(str);
        if (replace == null) {
            this.text.append("${");
            this.text.append(str);
            this.text.append("}");
            return;
        }
        this.text.append(replace);
    }
}
