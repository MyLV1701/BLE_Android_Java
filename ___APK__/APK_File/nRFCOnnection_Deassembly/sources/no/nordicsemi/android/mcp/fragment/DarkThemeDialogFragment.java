package no.nordicsemi.android.mcp.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.d;
import androidx.appcompat.app.g;
import androidx.preference.j;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.settings.SettingsActivity;
import no.nordicsemi.android.mcp.settings.SettingsFragment;

/* loaded from: classes.dex */
public class DarkThemeDialogFragment extends androidx.fragment.app.c {
    public /* synthetic */ void a(View view) {
        dismiss();
    }

    public /* synthetic */ void b(View view) {
        g.e(2);
        j.a(requireContext()).edit().putInt(SettingsFragment.SETTINGS_THEME, 2).apply();
        dismiss();
        requireActivity().recreate();
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        d.a aVar = new d.a(requireContext());
        View inflate = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_choose_theme, (ViewGroup) null);
        inflate.findViewById(R.id.option_1).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.fragment.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DarkThemeDialogFragment.this.a(view);
            }
        });
        inflate.findViewById(R.id.option_2).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.fragment.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DarkThemeDialogFragment.this.b(view);
            }
        });
        aVar.c(R.string.theme_title);
        aVar.a(R.drawable.ic_nav_theme_normal);
        aVar.b(inflate);
        aVar.c(R.string.theme_action, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.fragment.c
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DarkThemeDialogFragment.this.a(dialogInterface, i);
            }
        });
        return aVar.a();
    }

    public /* synthetic */ void a(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent(requireContext(), (Class<?>) SettingsActivity.class);
        intent.putExtra(SettingsActivity.EXTRA_THEME, true);
        startActivity(intent);
    }
}
