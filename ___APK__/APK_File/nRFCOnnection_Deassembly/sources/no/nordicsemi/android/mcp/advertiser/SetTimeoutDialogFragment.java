package no.nordicsemi.android.mcp.advertiser;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import androidx.appcompat.app.d;
import com.google.android.material.textfield.TextInputLayout;
import no.nordicsemi.android.mcp.R;

@TargetApi(21)
/* loaded from: classes.dex */
public class SetTimeoutDialogFragment extends androidx.fragment.app.c {
    private static final String ARG_ID = "id";
    private static final int DEFAULT_TIMEOUT = 10000;
    public static final String PREFS_DONT_SHOW_DIALOG_FOR_PACKET = "adv_dont_show_dialog_for_packet_";
    private static final String PREFS_LAST_MAX_EVENTS = "adv_last_max_events";
    private static final String PREFS_LAST_PACKET_TIMEOUT = "adv_last_packet_timeout_";
    private static final String PREFS_LAST_TIMEOUT = "adv_last_timeout";
    private static final String PREFS_OPTION_INDEFINITELY = "adv_option_indefinitely";
    private CheckBox mOptionRememberView;
    private RadioButton mOptionTimeout;
    private EditText mTimeoutView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(RadioButton radioButton, TextInputLayout textInputLayout, RadioButton radioButton2, View view) {
        radioButton.setChecked(false);
        textInputLayout.setEnabled(true);
        radioButton2.setChecked(true);
    }

    public static int getAdvertisementDuration(Context context, long j) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (defaultSharedPreferences.getBoolean(PREFS_OPTION_INDEFINITELY, false)) {
            return 0;
        }
        return defaultSharedPreferences.getInt(PREFS_LAST_PACKET_TIMEOUT + j, 0);
    }

    public static androidx.fragment.app.c getInstance(long j) {
        SetTimeoutDialogFragment setTimeoutDialogFragment = new SetTimeoutDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("id", j);
        setTimeoutDialogFragment.setArguments(bundle);
        return setTimeoutDialogFragment;
    }

    public static boolean shouldSkipDialog(Context context, long j) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREFS_DONT_SHOW_DIALOG_FOR_PACKET + j, false);
    }

    public /* synthetic */ void a(long j, androidx.appcompat.app.d dVar, View view) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
        try {
            int duration = getDuration();
            try {
                int maxEvents = getMaxEvents();
                if (duration > 0) {
                    edit.putInt(PREFS_LAST_TIMEOUT, duration).putInt(PREFS_LAST_PACKET_TIMEOUT + j, duration);
                }
                if (maxEvents > 0) {
                    edit.putInt(PREFS_LAST_MAX_EVENTS, maxEvents).putInt(PREFS_LAST_MAX_EVENTS + j, maxEvents);
                }
                edit.putBoolean(PREFS_OPTION_INDEFINITELY, duration == 0).putBoolean(PREFS_DONT_SHOW_DIALOG_FOR_PACKET + j, this.mOptionRememberView.isChecked()).apply();
                ((AdvertiserActionListener) getParentFragment()).onStartAdvertisement(j, duration, maxEvents);
                dVar.dismiss();
            } catch (NumberFormatException unused) {
                edit.remove(PREFS_LAST_MAX_EVENTS + j);
            }
        } catch (NumberFormatException unused2) {
            edit.remove(PREFS_LAST_PACKET_TIMEOUT + j);
        }
    }

    protected int getDuration() {
        if (!this.mOptionTimeout.isChecked()) {
            return 0;
        }
        String trim = this.mTimeoutView.getText().toString().trim();
        if (!trim.isEmpty()) {
            try {
                int parseInt = Integer.parseInt(trim);
                if ((Build.VERSION.SDK_INT < 26 && parseInt > 180000) || (Build.VERSION.SDK_INT >= 26 && parseInt > 655350)) {
                    this.mTimeoutView.setError(getResources().getString(R.string.adv_timeout_too_long_error));
                    throw new NumberFormatException();
                }
                return parseInt;
            } catch (Exception unused) {
                this.mTimeoutView.setError(getResources().getString(R.string.adv_timeout_error));
                throw new NumberFormatException();
            }
        }
        this.mTimeoutView.setError(getResources().getString(R.string.adv_timeout_error));
        throw new NumberFormatException();
    }

    protected int getMaxEvents() {
        return 0;
    }

    protected int getViewId() {
        return R.layout.dialog_set_advertising_timeout;
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        final long j = getArguments().getLong("id");
        View view = setupView(j);
        d.a aVar = new d.a(getContext());
        aVar.c(R.string.adv_timeout_title);
        aVar.b(view);
        aVar.c(R.string.ok, null);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        final androidx.appcompat.app.d c2 = aVar.c();
        c2.b(-1).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.i0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SetTimeoutDialogFragment.this.a(j, c2, view2);
            }
        });
        return c2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View setupView(long j) {
        int i;
        View inflate = LayoutInflater.from(getContext()).inflate(getViewId(), (ViewGroup) null);
        final RadioButton radioButton = (RadioButton) inflate.findViewById(R.id.option_indefinitely);
        final RadioButton radioButton2 = (RadioButton) inflate.findViewById(R.id.option_timeout);
        this.mOptionTimeout = radioButton2;
        final TextInputLayout textInputLayout = (TextInputLayout) inflate.findViewById(R.id.value_timeout_container);
        final EditText editText = (EditText) inflate.findViewById(R.id.value_timeout);
        this.mTimeoutView = editText;
        this.mOptionRememberView = (CheckBox) inflate.findViewById(R.id.option_remember);
        radioButton.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.f0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetTimeoutDialogFragment.a(radioButton, textInputLayout, radioButton2, view);
            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.h0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetTimeoutDialogFragment.b(radioButton, textInputLayout, radioButton2, view);
            }
        });
        inflate.findViewById(R.id.action_clear_timeout).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.g0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetTimeoutDialogFragment.a(radioButton, radioButton2, editText, textInputLayout, view);
            }
        });
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean z = defaultSharedPreferences.getBoolean(PREFS_OPTION_INDEFINITELY, true);
        radioButton.setChecked(z);
        radioButton2.setChecked(!z);
        if (defaultSharedPreferences.contains(PREFS_LAST_PACKET_TIMEOUT + j)) {
            i = defaultSharedPreferences.getInt(PREFS_LAST_PACKET_TIMEOUT + j, DEFAULT_TIMEOUT);
        } else {
            i = defaultSharedPreferences.getInt(PREFS_LAST_TIMEOUT, DEFAULT_TIMEOUT);
        }
        editText.setText(String.valueOf(i));
        textInputLayout.setEnabled(!z);
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(RadioButton radioButton, TextInputLayout textInputLayout, RadioButton radioButton2, View view) {
        radioButton.setChecked(true);
        textInputLayout.setEnabled(false);
        radioButton2.setChecked(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(RadioButton radioButton, RadioButton radioButton2, EditText editText, TextInputLayout textInputLayout, View view) {
        radioButton.setChecked(false);
        radioButton2.setChecked(true);
        editText.setText((CharSequence) null);
        textInputLayout.setEnabled(true);
    }
}
