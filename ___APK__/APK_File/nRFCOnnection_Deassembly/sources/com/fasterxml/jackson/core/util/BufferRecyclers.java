package com.fasterxml.jackson.core.util;

import java.lang.ref.SoftReference;

/* loaded from: classes.dex */
public class BufferRecyclers {
    private static final ThreadLocalBufferManager _bufferRecyclerTracker;
    protected static final ThreadLocal<SoftReference<BufferRecycler>> _recyclerRef;

    static {
        _bufferRecyclerTracker = "true".equals(System.getProperty("com.fasterxml.jackson.core.util.BufferRecyclers.trackReusableBuffers")) ? ThreadLocalBufferManager.instance() : null;
        _recyclerRef = new ThreadLocal<>();
    }

    public static BufferRecycler getBufferRecycler() {
        SoftReference<BufferRecycler> softReference;
        SoftReference<BufferRecycler> softReference2 = _recyclerRef.get();
        BufferRecycler bufferRecycler = softReference2 == null ? null : softReference2.get();
        if (bufferRecycler == null) {
            bufferRecycler = new BufferRecycler();
            ThreadLocalBufferManager threadLocalBufferManager = _bufferRecyclerTracker;
            if (threadLocalBufferManager != null) {
                softReference = threadLocalBufferManager.wrapAndTrack(bufferRecycler);
            } else {
                softReference = new SoftReference<>(bufferRecycler);
            }
            _recyclerRef.set(softReference);
        }
        return bufferRecycler;
    }
}
