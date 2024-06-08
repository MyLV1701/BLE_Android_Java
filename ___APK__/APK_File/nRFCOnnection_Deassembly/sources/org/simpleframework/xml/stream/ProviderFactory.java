package org.simpleframework.xml.stream;

/* loaded from: classes.dex */
final class ProviderFactory {
    ProviderFactory() {
    }

    public static Provider getInstance() {
        try {
            try {
                return new StreamProvider();
            } catch (Throwable unused) {
                return new PullProvider();
            }
        } catch (Throwable unused2) {
            return new DocumentProvider();
        }
    }
}
