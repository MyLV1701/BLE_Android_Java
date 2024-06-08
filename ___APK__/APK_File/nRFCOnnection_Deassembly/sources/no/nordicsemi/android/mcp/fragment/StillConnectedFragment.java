package no.nordicsemi.android.mcp.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.d;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class StillConnectedFragment extends androidx.fragment.app.c {
    private static final String PARAM_SAVE_LOG = "save_log";

    /* loaded from: classes.dex */
    public interface DisconnectAndCloseListener {
        void onDisconnectAndClose(boolean z);
    }

    public static androidx.fragment.app.c getInstance(boolean z) {
        StillConnectedFragment stillConnectedFragment = new StillConnectedFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(PARAM_SAVE_LOG, z);
        stillConnectedFragment.setArguments(bundle);
        return stillConnectedFragment;
    }

    public /* synthetic */ void a(DialogInterface dialogInterface, int i) {
        ((DisconnectAndCloseListener) getParentFragment()).onDisconnectAndClose(getArguments().getBoolean(PARAM_SAVE_LOG));
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        d.a aVar = new d.a(requireContext());
        aVar.c(R.string.still_connected_title);
        aVar.b(R.string.still_connected_message);
        aVar.b(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c(R.string.yes, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.fragment.f
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                StillConnectedFragment.this.a(dialogInterface, i);
            }
        });
        return aVar.a();
    }
}
