package org.simpleframework.xml.transform;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

/* loaded from: classes.dex */
public class RegistryMatcher implements Matcher {
    private final Cache<Transform> transforms = new ConcurrentCache();
    private final Cache<Class> types = new ConcurrentCache();

    private Transform create(Class cls) {
        Class fetch = this.types.fetch(cls);
        if (fetch != null) {
            return create(cls, fetch);
        }
        return null;
    }

    public void bind(Class cls, Class cls2) {
        this.types.cache(cls, cls2);
    }

    @Override // org.simpleframework.xml.transform.Matcher
    public Transform match(Class cls) {
        Transform fetch = this.transforms.fetch(cls);
        return fetch == null ? create(cls) : fetch;
    }

    public void bind(Class cls, Transform transform) {
        this.transforms.cache(cls, transform);
    }

    private Transform create(Class cls, Class cls2) {
        Transform transform = (Transform) cls2.newInstance();
        if (transform != null) {
            this.transforms.cache(cls, transform);
        }
        return transform;
    }
}
