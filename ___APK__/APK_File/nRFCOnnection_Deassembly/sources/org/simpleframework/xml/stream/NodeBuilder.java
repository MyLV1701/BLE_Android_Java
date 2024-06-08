package org.simpleframework.xml.stream;

import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;

/* loaded from: classes.dex */
public final class NodeBuilder {
    private static Provider PROVIDER = ProviderFactory.getInstance();

    public static InputNode read(InputStream inputStream) {
        return read(PROVIDER.provide(inputStream));
    }

    public static OutputNode write(Writer writer) {
        return write(writer, new Format());
    }

    public static InputNode read(Reader reader) {
        return read(PROVIDER.provide(reader));
    }

    public static OutputNode write(Writer writer, Format format) {
        return new NodeWriter(writer, format).writeRoot();
    }

    private static InputNode read(EventReader eventReader) {
        return new NodeReader(eventReader).readRoot();
    }
}
