package com.androidplot.util;

import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class MultiSynch {

    /* loaded from: classes.dex */
    public interface Action {
        void run(Object[] objArr);
    }

    public static void run(Object[] objArr, Set set, Action action) {
        run(objArr, set.toArray(), action, 0);
    }

    public static void run(Object[] objArr, List list, Action action) {
        run(objArr, list.toArray(), action, 0);
    }

    public static void run(Object[] objArr, Object[] objArr2, Action action) {
        run(objArr, objArr2, action, 0);
    }

    private static void run(Object[] objArr, Object[] objArr2, Action action, int i) {
        if (objArr2 != null) {
            synchronized (objArr2[i]) {
                if (i < objArr2.length - 1) {
                    run(objArr, objArr2, action, i + 1);
                } else {
                    action.run(objArr);
                }
            }
        }
        action.run(objArr);
    }
}
