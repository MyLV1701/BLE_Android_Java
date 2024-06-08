package com.fasterxml.jackson.core.io;

import java.io.InputStream;
import java.io.Serializable;

/* loaded from: classes.dex */
public abstract class InputDecorator implements Serializable {
    public abstract InputStream decorate(IOContext iOContext, InputStream inputStream);

    public abstract InputStream decorate(IOContext iOContext, byte[] bArr, int i, int i2);
}
