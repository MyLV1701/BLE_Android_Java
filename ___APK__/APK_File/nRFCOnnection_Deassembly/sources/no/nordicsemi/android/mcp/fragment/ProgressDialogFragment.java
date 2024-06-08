package no.nordicsemi.android.mcp.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;

/* loaded from: classes.dex */
public class ProgressDialogFragment extends androidx.fragment.app.c {
    public static final String ARGS_MESSAGE_RES_ID = "message_res_id";

    public static ProgressDialogFragment getInstance(int i) {
        ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_MESSAGE_RES_ID, i);
        progressDialogFragment.setArguments(bundle);
        return progressDialogFragment;
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCancelable(false);
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        ProgressDialog progressDialog = new ProgressDialog(requireContext(), getTheme());
        progressDialog.setMessage(getString(arguments.getInt(ARGS_MESSAGE_RES_ID)));
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(0);
        return progressDialog;
    }
}
