package no.nordicsemi.android.mcp.connection.macro;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.d;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class AddDelayDialogFragment extends androidx.fragment.app.c implements DialogInterface.OnClickListener {
    private static final int DEFAULT_DELAY = 29;
    private static final String LAST_DELAY = "macro_last_delay";
    private SeekBar mDelayBar;

    /* loaded from: classes.dex */
    public interface Listener {
        void onDelayAdded(long j);
    }

    public /* synthetic */ void a(View view) {
        this.mDelayBar.setProgress(r2.getProgress() - 1);
    }

    public /* synthetic */ void b(View view) {
        SeekBar seekBar = this.mDelayBar;
        seekBar.setProgress(seekBar.getProgress() + 1);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        Listener listener = (Listener) getParentFragment();
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putInt(LAST_DELAY, this.mDelayBar.getProgress()).apply();
        listener.onDelayAdded((this.mDelayBar.getProgress() + 1) * 10);
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_macro_add_delay, (ViewGroup) null);
        final TextView textView = (TextView) inflate.findViewById(R.id.value);
        this.mDelayBar = (SeekBar) inflate.findViewById(R.id.delay);
        this.mDelayBar.setProgress(PreferenceManager.getDefaultSharedPreferences(getContext()).getInt(LAST_DELAY, 29));
        this.mDelayBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: no.nordicsemi.android.mcp.connection.macro.AddDelayDialogFragment.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                textView.setText(AddDelayDialogFragment.this.getString(R.string.ms, Integer.valueOf((i + 1) * 10)));
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        inflate.findViewById(R.id.action_subtract).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.macro.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddDelayDialogFragment.this.a(view);
            }
        });
        inflate.findViewById(R.id.action_add).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.macro.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddDelayDialogFragment.this.b(view);
            }
        });
        textView.setText(getString(R.string.ms, Integer.valueOf((this.mDelayBar.getProgress() + 1) * 10)));
        d.a aVar = new d.a(requireContext());
        aVar.c(R.string.macros_dialog_add_delay);
        aVar.b(inflate);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c(R.string.ok, this);
        return aVar.a();
    }
}
