package no.nordicsemi.android.mcp.fragment;

import android.app.Dialog;
import android.os.Bundle;
import androidx.appcompat.app.d;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class AlertDialogFragment extends androidx.fragment.app.c {
    private static final String ARG_ICON_RES_ID = "icon_res_id";
    private static final String ARG_TEXT_RES_ID = "text_res_id";
    private static final String ARG_TITLE_RES_ID = "title_res_id";

    public static androidx.fragment.app.c getInstance(int i, int i2) {
        return getInstance(i, i2, 0);
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        d.a aVar = new d.a(requireContext());
        Bundle arguments = getArguments();
        int i = arguments.getInt(ARG_TITLE_RES_ID);
        int i2 = arguments.getInt(ARG_TEXT_RES_ID);
        int i3 = arguments.getInt(ARG_ICON_RES_ID);
        aVar.c(i);
        aVar.b(i2);
        aVar.c(R.string.ok, null);
        if (i3 > 0) {
            aVar.a(i3);
        }
        return aVar.a();
    }

    public static androidx.fragment.app.c getInstance(int i, int i2, int i3) {
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_TITLE_RES_ID, i);
        bundle.putInt(ARG_TEXT_RES_ID, i2);
        bundle.putInt(ARG_ICON_RES_ID, i3);
        alertDialogFragment.setArguments(bundle);
        return alertDialogFragment;
    }
}
