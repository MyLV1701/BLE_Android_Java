package no.nordicsemi.android.mcp.advertiser;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class EditOreoExtendedAdvertisementDialogFragment extends EditOreoAdvertisementDialogFragment implements ItemEditedListener, View.OnClickListener {
    protected CheckBox mAdvertisingExtensionMode;
    protected CheckBox mAnonymous;
    protected CheckBox mIncludeTxPower;
    protected Spinner mPrimaryPhy;
    protected Spinner mSecondaryPhy;

    public static androidx.fragment.app.c getInstance(long j) {
        EditOreoExtendedAdvertisementDialogFragment editOreoExtendedAdvertisementDialogFragment = new EditOreoExtendedAdvertisementDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("id", j);
        editOreoExtendedAdvertisementDialogFragment.setArguments(bundle);
        return editOreoExtendedAdvertisementDialogFragment;
    }

    private int getPrimaryPhy() {
        return this.mPrimaryPhy.getSelectedItemPosition() != 1 ? 1 : 3;
    }

    private int getSecondaryPhy() {
        int selectedItemPosition = this.mSecondaryPhy.getSelectedItemPosition();
        if (selectedItemPosition != 1) {
            return selectedItemPosition != 2 ? 1 : 3;
        }
        return 2;
    }

    private int primaryPhyToIndex(int i) {
        return i != 3 ? 0 : 1;
    }

    private int secondaryPhyToIndex(int i) {
        if (i != 2) {
            return i != 3 ? 0 : 2;
        }
        return 1;
    }

    public /* synthetic */ void c(CompoundButton compoundButton, boolean z) {
        onModeChanged(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.advertiser.EditOreoAdvertisementDialogFragment, no.nordicsemi.android.mcp.advertiser.EditAdvertisementDialogFragment
    public void createAdvancedView(View view) {
        super.createAdvancedView(view);
        this.mPrimaryPhy = (Spinner) view.findViewById(R.id.adv_primary_phy);
        this.mPrimaryPhy.setEnabled(false);
        this.mSecondaryPhy = (Spinner) view.findViewById(R.id.adv_secondary_phy);
        this.mSecondaryPhy.setEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.advertiser.EditOreoAdvertisementDialogFragment, no.nordicsemi.android.mcp.advertiser.EditAdvertisementDialogFragment
    public void createView(View view) {
        super.createView(view);
        this.mAdvertisingExtensionMode = (CheckBox) view.findViewById(R.id.adv_extension);
        this.mAdvertisingExtensionMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.advertiser.r
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                EditOreoExtendedAdvertisementDialogFragment.this.c(compoundButton, z);
            }
        });
        this.mIncludeTxPower = (CheckBox) view.findViewById(R.id.adv_include_tx_power);
        this.mIncludeTxPower.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.advertiser.q
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                EditOreoExtendedAdvertisementDialogFragment.this.d(compoundButton, z);
            }
        });
        this.mAnonymous = (CheckBox) view.findViewById(R.id.adv_anonymous);
        this.mAnonymous.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.advertiser.p
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                EditOreoExtendedAdvertisementDialogFragment.this.e(compoundButton, z);
            }
        });
    }

    public /* synthetic */ void d(CompoundButton compoundButton, boolean z) {
        onIncludeTxPowerChanged(z);
    }

    public /* synthetic */ void e(CompoundButton compoundButton, boolean z) {
        onAnonymousChanged(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.advertiser.EditOreoAdvertisementDialogFragment, no.nordicsemi.android.mcp.advertiser.EditAdvertisementDialogFragment
    public void fill(Cursor cursor) {
        super.fill(cursor);
        boolean z = false;
        boolean z2 = cursor.getInt(2) == 1;
        boolean z3 = cursor.getInt(3) == 1;
        boolean z4 = cursor.getInt(4) == 1;
        boolean z5 = cursor.getInt(7) == 1;
        boolean z6 = cursor.getInt(8) == 1;
        this.mAnonymous.setChecked(z4);
        this.mIncludeTxPower.setChecked(z5);
        this.mAdvertisingExtensionMode.setChecked(!z6);
        if (z6) {
            this.mIncludeTxPower.setEnabled(false);
            this.mAnonymous.setEnabled(false);
            return;
        }
        this.mConnectable.setEnabled((z3 || z4) ? false : true);
        this.mScannable.setEnabled((z2 || z4) ? false : true);
        CheckBox checkBox = this.mAnonymous;
        if (!z2 && !z3) {
            z = true;
        }
        checkBox.setEnabled(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.advertiser.EditOreoAdvertisementDialogFragment, no.nordicsemi.android.mcp.advertiser.EditAdvertisementDialogFragment
    public void fillAdvanced(Cursor cursor) {
        super.fillAdvanced(cursor);
        int i = cursor.getInt(9);
        int i2 = cursor.getInt(10);
        this.mPrimaryPhy.setSelection(primaryPhyToIndex(i));
        this.mSecondaryPhy.setSelection(secondaryPhyToIndex(i2));
        boolean z = cursor.getInt(8) == 1;
        this.mPrimaryPhy.setEnabled(!z);
        this.mSecondaryPhy.setEnabled(!z);
    }

    @Override // no.nordicsemi.android.mcp.advertiser.EditAdvertisementDialogFragment
    protected int getViewId() {
        return R.layout.dialog_advertisement_extended_edit;
    }

    protected void onAnonymousChanged(boolean z) {
        this.mAdvertisingExtensionMode.setEnabled(!z);
        this.mConnectable.setEnabled(!z);
        this.mScannable.setEnabled(!z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.advertiser.EditOreoAdvertisementDialogFragment, no.nordicsemi.android.mcp.advertiser.EditAdvertisementDialogFragment
    public void onConnectableChanged(boolean z) {
        if (this.mAdvertisingExtensionMode.isChecked()) {
            this.mAdapter.setConnectible(z);
            this.mAdapter.notifyDataSetChanged();
            this.mScannable.setEnabled(!z);
            this.mAnonymous.setEnabled((z || this.mScannable.isChecked()) ? false : true);
            return;
        }
        super.onConnectableChanged(z);
    }

    protected void onIncludeTxPowerChanged(boolean z) {
    }

    protected void onModeChanged(boolean z) {
        this.mIncludeTxPower.setEnabled(z);
        this.mPrimaryPhy.setEnabled(z);
        this.mSecondaryPhy.setEnabled(z);
        this.mAdapter.setAdvertisingExtensionMode(z);
        this.mAdapter.notifyDataSetChanged();
        if (!z) {
            this.mIncludeTxPower.setChecked(false);
            this.mAnonymous.setChecked(false);
            this.mAnonymous.setEnabled(false);
            if (this.mConnectable.isChecked()) {
                this.mScannable.setChecked(true);
            }
            this.mConnectable.setEnabled(true);
            return;
        }
        if (this.mConnectable.isChecked() && this.mScannable.isChecked()) {
            this.mScannable.setChecked(false);
        } else if (this.mScannable.isChecked()) {
            this.mConnectable.setEnabled(false);
        }
        this.mAnonymous.setEnabled((this.mConnectable.isChecked() || this.mScannable.isChecked()) ? false : true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.advertiser.EditOreoAdvertisementDialogFragment
    public void onScannableChanged(boolean z) {
        super.onScannableChanged(z);
        if (this.mAdvertisingExtensionMode.isChecked()) {
            this.mConnectable.setEnabled(!z);
            this.mAnonymous.setEnabled((z || this.mConnectable.isChecked()) ? false : true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.advertiser.EditOreoAdvertisementDialogFragment, no.nordicsemi.android.mcp.advertiser.EditAdvertisementDialogFragment
    public long update(long j) {
        return this.mDatabase.updateAdvertisingPacket(super.update(j), !this.mAdvertisingExtensionMode.isChecked(), this.mIncludeTxPower.isChecked(), this.mAnonymous.isChecked(), getPrimaryPhy(), getSecondaryPhy());
    }
}
