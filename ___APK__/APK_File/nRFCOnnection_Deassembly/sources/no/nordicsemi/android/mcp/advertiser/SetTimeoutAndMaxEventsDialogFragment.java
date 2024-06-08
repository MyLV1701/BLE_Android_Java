package no.nordicsemi.android.mcp.advertiser;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import com.google.android.material.textfield.TextInputLayout;
import no.nordicsemi.android.mcp.R;

@TargetApi(26)
/* loaded from: classes.dex */
public class SetTimeoutAndMaxEventsDialogFragment extends SetTimeoutDialogFragment {
    private static final String ARG_ID = "id";
    private static final int DEFAULT_MAX_EVENTS = 10;
    private static final String PREFS_LAST_MAX_EVENTS = "adv_last_max_events";
    private static final String PREFS_LAST_PACKET_MAX_EVENTS = "adv_last_packet_max_events_";
    private static final String PREFS_OPTION_NO_MAXIMUM = "adv_option_no_maximum";
    private EditText mMaxEventsView;
    private RadioButton mOptionMaximumSet;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(RadioButton radioButton, TextInputLayout textInputLayout, RadioButton radioButton2, View view) {
        radioButton.setChecked(false);
        textInputLayout.setEnabled(true);
        radioButton2.setChecked(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(RadioButton radioButton, RadioButton radioButton2, EditText editText, TextInputLayout textInputLayout, View view) {
        radioButton.setChecked(false);
        radioButton2.setChecked(true);
        editText.setText((CharSequence) null);
        textInputLayout.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void c(RadioButton radioButton, TextInputLayout textInputLayout, RadioButton radioButton2, View view) {
        radioButton.setChecked(true);
        textInputLayout.setEnabled(false);
        radioButton2.setChecked(false);
    }

    public static androidx.fragment.app.c getInstance(long j) {
        SetTimeoutAndMaxEventsDialogFragment setTimeoutAndMaxEventsDialogFragment = new SetTimeoutAndMaxEventsDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("id", j);
        setTimeoutAndMaxEventsDialogFragment.setArguments(bundle);
        return setTimeoutAndMaxEventsDialogFragment;
    }

    public static int getMaxExtendedAdvertisingEvents(Context context, long j) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (defaultSharedPreferences.getBoolean(PREFS_OPTION_NO_MAXIMUM, false)) {
            return 0;
        }
        return defaultSharedPreferences.getInt(PREFS_LAST_PACKET_MAX_EVENTS + j, 0);
    }

    @Override // no.nordicsemi.android.mcp.advertiser.SetTimeoutDialogFragment
    protected int getMaxEvents() {
        if (!this.mOptionMaximumSet.isChecked()) {
            return 0;
        }
        String trim = this.mMaxEventsView.getText().toString().trim();
        if (!trim.isEmpty()) {
            try {
                int parseInt = Integer.parseInt(trim);
                if (parseInt >= 1 && parseInt <= 255) {
                    return parseInt;
                }
                this.mMaxEventsView.setError(getResources().getString(R.string.adv_max_events_not_valid));
                throw new NumberFormatException();
            } catch (Exception unused) {
                this.mMaxEventsView.setError(getResources().getString(R.string.adv_max_events_error));
                throw new NumberFormatException();
            }
        }
        this.mMaxEventsView.setError(getResources().getString(R.string.adv_timeout_error));
        throw new NumberFormatException();
    }

    @Override // no.nordicsemi.android.mcp.advertiser.SetTimeoutDialogFragment
    protected int getViewId() {
        return R.layout.dialog_set_advertising_timeout_max_events;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.advertiser.SetTimeoutDialogFragment
    public View setupView(long j) {
        int i;
        View view = super.setupView(j);
        final RadioButton radioButton = (RadioButton) view.findViewById(R.id.option_no_maximum);
        final RadioButton radioButton2 = (RadioButton) view.findViewById(R.id.option_maximum_set);
        this.mOptionMaximumSet = radioButton2;
        final TextInputLayout textInputLayout = (TextInputLayout) view.findViewById(R.id.value_max_events_container);
        final EditText editText = (EditText) view.findViewById(R.id.value_max_events);
        this.mMaxEventsView = editText;
        radioButton.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.c0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SetTimeoutAndMaxEventsDialogFragment.c(radioButton, textInputLayout, radioButton2, view2);
            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.e0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SetTimeoutAndMaxEventsDialogFragment.a(radioButton, textInputLayout, radioButton2, view2);
            }
        });
        view.findViewById(R.id.action_clear_max_events).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.d0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SetTimeoutAndMaxEventsDialogFragment.b(radioButton, radioButton2, editText, textInputLayout, view2);
            }
        });
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean z = defaultSharedPreferences.getBoolean(PREFS_OPTION_NO_MAXIMUM, true);
        radioButton.setChecked(z);
        radioButton2.setChecked(!z);
        if (defaultSharedPreferences.contains(PREFS_LAST_PACKET_MAX_EVENTS + j)) {
            i = defaultSharedPreferences.getInt(PREFS_LAST_PACKET_MAX_EVENTS + j, 10);
        } else {
            i = defaultSharedPreferences.getInt(PREFS_LAST_MAX_EVENTS, 10);
        }
        editText.setText(String.valueOf(i));
        textInputLayout.setEnabled(!z);
        return view;
    }
}
