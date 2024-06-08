package no.nordicsemi.android.mcp.connection;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.d;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class ZipInfoFragment extends androidx.fragment.app.c {
    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        View inflate = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_zip_info, (ViewGroup) null);
        d.a aVar = new d.a(requireContext());
        aVar.b(inflate);
        aVar.c(R.string.info);
        aVar.c(R.string.ok, null);
        return aVar.a();
    }
}
