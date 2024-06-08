package no.nordicsemi.android.mcp.test.domain.common;

import no.nordicsemi.android.mcp.test.domain.Target;

/* loaded from: classes.dex */
public interface HasBindTarget {
    Target getBindTarget();

    String getBindTargetId();

    void setBindTarget(Target target);
}
