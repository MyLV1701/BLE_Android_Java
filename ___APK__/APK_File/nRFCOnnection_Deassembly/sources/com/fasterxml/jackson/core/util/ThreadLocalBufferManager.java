package com.fasterxml.jackson.core.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
class ThreadLocalBufferManager {
    private final Map<SoftReference<BufferRecycler>, Boolean> _trackedRecyclers = new ConcurrentHashMap();
    private final ReferenceQueue<BufferRecycler> _refQueue = new ReferenceQueue<>();

    /* loaded from: classes.dex */
    private static final class ThreadLocalBufferManagerHolder {
        static final ThreadLocalBufferManager manager = new ThreadLocalBufferManager();
    }

    ThreadLocalBufferManager() {
    }

    public static ThreadLocalBufferManager instance() {
        return ThreadLocalBufferManagerHolder.manager;
    }

    private void removeSoftRefsClearedByGc() {
        while (true) {
            SoftReference softReference = (SoftReference) this._refQueue.poll();
            if (softReference == null) {
                return;
            } else {
                this._trackedRecyclers.remove(softReference);
            }
        }
    }

    public SoftReference<BufferRecycler> wrapAndTrack(BufferRecycler bufferRecycler) {
        SoftReference<BufferRecycler> softReference = new SoftReference<>(bufferRecycler, this._refQueue);
        this._trackedRecyclers.put(softReference, true);
        removeSoftRefsClearedByGc();
        return softReference;
    }
}
