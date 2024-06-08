package org.simpleframework.xml.stream;

import java.io.InputStream;
import java.io.Reader;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

/* loaded from: classes.dex */
class StreamProvider implements Provider {
    private final XMLInputFactory factory = XMLInputFactory.newInstance();

    @Override // org.simpleframework.xml.stream.Provider
    public EventReader provide(InputStream inputStream) {
        return provide(this.factory.createXMLEventReader(inputStream));
    }

    @Override // org.simpleframework.xml.stream.Provider
    public EventReader provide(Reader reader) {
        return provide(this.factory.createXMLEventReader(reader));
    }

    private EventReader provide(XMLEventReader xMLEventReader) {
        return new StreamReader(xMLEventReader);
    }
}
