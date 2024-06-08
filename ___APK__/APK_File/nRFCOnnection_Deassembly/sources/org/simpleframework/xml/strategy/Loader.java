package org.simpleframework.xml.strategy;

/* loaded from: classes.dex */
class Loader {
    private static ClassLoader getCallerClassLoader() {
        return Loader.class.getClassLoader();
    }

    private static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public Class load(String str) {
        ClassLoader classLoader = getClassLoader();
        if (classLoader == null) {
            classLoader = getCallerClassLoader();
        }
        return classLoader.loadClass(str);
    }
}
