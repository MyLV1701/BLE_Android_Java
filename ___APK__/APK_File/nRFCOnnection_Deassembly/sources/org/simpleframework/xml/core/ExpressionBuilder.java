package org.simpleframework.xml.core;

import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.LimitedCache;

/* loaded from: classes.dex */
class ExpressionBuilder {
    private final Cache<Expression> cache = new LimitedCache();
    private final Format format;
    private final Class type;

    public ExpressionBuilder(Detail detail, Support support) {
        this.format = support.getFormat();
        this.type = detail.getType();
    }

    private Expression create(String str) {
        PathParser pathParser = new PathParser(str, new ClassType(this.type), this.format);
        Cache<Expression> cache = this.cache;
        if (cache != null) {
            cache.cache(str, pathParser);
        }
        return pathParser;
    }

    public Expression build(String str) {
        Expression fetch = this.cache.fetch(str);
        return fetch == null ? create(str) : fetch;
    }
}
