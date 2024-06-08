package org.simpleframework.xml.stream;

import java.io.InputStream;
import java.io.Reader;

/* loaded from: classes.dex */
interface Provider {
    EventReader provide(InputStream inputStream);

    EventReader provide(Reader reader);
}
