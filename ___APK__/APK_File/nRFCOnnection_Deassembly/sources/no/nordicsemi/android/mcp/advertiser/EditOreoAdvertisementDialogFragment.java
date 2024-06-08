package no.nordicsemi.android.mcp.advertiser;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class EditOreoAdvertisementDialogFragment extends EditAdvertisementDialogFragment implements ItemEditedListener, View.OnClickListener {
    protected EditText mIntervalView;
    protected CheckBox mScannable;
    protected EditText mTxPowerLevelView;

    public static androidx.fragment.app.c getInstance(long j) {
        EditOreoAdvertisementDialogFragment editOreoAdvertisementDialogFragment = new EditOreoAdvertisementDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("id", j);
        editOreoAdvertisementDialogFragment.setArguments(bundle);
        return editOreoAdvertisementDialogFragment;
    }

    public /* synthetic */ void b(CompoundButton compoundButton, boolean z) {
        onScannableChanged(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.advertiser.EditAdvertisementDialogFragment
    public void createAdvancedView(View view) {
        final TextView textView = (TextView) view.findViewById(R.id.adv_interval_calculation);
        this.mIntervalView = (EditText) view.findViewById(R.id.adv_interval);
        this.mIntervalView.addTextChangedListener(new TextWatcher() { // from class: no.nordicsemi.android.mcp.advertiser.EditOreoAdvertisementDialogFragment.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                try {
                    double parseInt = Integer.parseInt(editable.toString());
                    Double.isNaN(parseInt);
                    textView.setText(EditOreoAdvertisementDialogFragment.this.getString(R.string.adv_interval_calc, Integer.valueOf((int) (parseInt * 0.625d))));
                } catch (NumberFormatException unused) {
                    EditOreoAdvertisementDialogFragment editOreoAdvertisementDialogFragment = EditOreoAdvertisementDialogFragment.this;
                    editOreoAdvertisementDialogFragment.mIntervalView.setError(editOreoAdvertisementDialogFragment.getString(R.string.adv_interval_parsing_error));
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.mTxPowerLevelView = (EditText) view.findViewById(R.id.adv_tx_power_level);
        this.mIntervalView.setText(String.valueOf(400));
        this.mTxPowerLevelView.setText(String.valueOf(-7));
        this.mAdapter.setScannable(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.advertiser.EditAdvertisementDialogFragment
    public void createView(View view) {
        super.createView(view);
        this.mScannable = (CheckBox) view.findViewById(R.id.adv_scannable);
        this.mScannable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.advertiser.o
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                EditOreoAdvertisementDialogFragment.this.b(compoundButton, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.advertiser.EditAdvertisementDialogFragment
    public void fill(Cursor cursor) {
        super.fill(cursor);
        boolean z = cursor.getInt(2) == 1;
        boolean z2 = cursor.getInt(3) == 1;
        this.mScannable.setEnabled(!z);
        this.mScannable.setChecked(z2);
        this.mAdapter.setScannable(z2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.advertiser.EditAdvertisementDialogFragment
    public void fillAdvanced(Cursor cursor) {
        this.mIntervalView.setText(String.valueOf(cursor.getInt(5)));
        this.mTxPowerLevelView.setText(String.valueOf(cursor.getInt(6)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.advertiser.EditAdvertisementDialogFragment
    public void onConnectableChanged(boolean z) {
        this.mScannable.setEnabled(!z);
        this.mScannable.setChecked(z || this.mAdapter.isScanResponseSet());
        super.onConnectableChanged(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onScannableChanged(boolean z) {
        this.mAdapter.setScannable(z);
        this.mAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.advertiser.EditAdvertisementDialogFragment
    public long update(long j) {
        return this.mDatabase.updateAdvertisingPacket(super.update(j), this.mScannable.isChecked());
    }

    @Override // no.nordicsemi.android.mcp.advertiser.EditAdvertisementDialogFragment
    protected long updateAdvanced(long j) {
        try {
            int parseInt = Integer.parseInt(this.mIntervalView.getText().toString());
            if (parseInt >= 160 && parseInt <= 16777215) {
                try {
                    int parseInt2 = Integer.parseInt(this.mTxPowerLevelView.getText().toString());
                    if (parseInt2 >= -127 && parseInt2 <= 1) {
                        return this.mDatabase.updateAdvertisingPacket(j, parseInt, parseInt2);
                    }
                    throw new NumberFormatException();
                } catch (NumberFormatException e2) {
                    this.mTxPowerLevelView.setError(getString(R.string.adv_tx_power_level_parsing_error));
                    throw e2;
                }
            }
            throw new NumberFormatException();
        } catch (NumberFormatException e3) {
            this.mIntervalView.setError(getString(R.string.adv_interval_parsing_error));
            throw e3;
        }
    }
}
