package no.nordicsemi.android.mcp.widget;

import androidx.fragment.app.l;
import androidx.fragment.app.s;

/* loaded from: classes.dex */
public abstract class ClosablePagerAdapter extends s {
    public ClosablePagerAdapter(l lVar) {
        super(lVar);
    }

    public CharSequence getPageSubtitle(int i) {
        return null;
    }

    public boolean isPageClosable(int i) {
        return false;
    }
}
