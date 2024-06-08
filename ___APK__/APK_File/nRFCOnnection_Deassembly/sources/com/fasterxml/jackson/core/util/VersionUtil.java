package com.fasterxml.jackson.core.util;

import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class VersionUtil {
    static {
        Pattern.compile("[-_./;:]");
    }

    public static final void throwInternal() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }
}
