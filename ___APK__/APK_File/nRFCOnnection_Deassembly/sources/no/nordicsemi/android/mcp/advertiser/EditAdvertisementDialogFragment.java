package no.nordicsemi.android.mcp.advertiser;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import androidx.appcompat.app.d;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import no.nordicsemi.android.ble.error.GattError;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.LollipopBluetoothLeAdvertiserImpl;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class EditAdvertisementDialogFragment extends androidx.fragment.app.c implements ItemEditedListener, View.OnClickListener {
    protected static final String ARG_ID = "id";
    protected EditAdvertisementAdapter mAdapter;
    protected CheckBox mConnectable;
    protected DatabaseHelper mDatabase;
    protected Spinner mModeSpinner;
    protected EditText mNameView;
    protected Spinner mTxPowerSpinner;

    private void createAdvancedExpander(final View view) {
        final View findViewById = view.findViewById(R.id.adv_advanced);
        ((CheckBox) view.findViewById(R.id.action_expand_options)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.advertiser.k
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                EditAdvertisementDialogFragment.this.a(findViewById, view, compoundButton, z);
            }
        });
    }

    public static androidx.fragment.app.c getInstance(long j) {
        EditAdvertisementDialogFragment editAdvertisementDialogFragment = new EditAdvertisementDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("id", j);
        editAdvertisementDialogFragment.setArguments(bundle);
        return editAdvertisementDialogFragment;
    }

    public /* synthetic */ void a(View view) {
        this.mNameView.setText((CharSequence) null);
        this.mNameView.requestFocus();
    }

    protected void createAdvancedView(View view) {
        this.mModeSpinner = (Spinner) view.findViewById(R.id.adv_mode);
        this.mTxPowerSpinner = (Spinner) view.findViewById(R.id.adv_tx_power);
        this.mTxPowerSpinner.setSelection(2);
        this.mModeSpinner.setSelection(1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void createView(final View view) {
        this.mNameView = (EditText) view.findViewById(R.id.display_name);
        view.findViewById(R.id.action_clear_name).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                EditAdvertisementDialogFragment.this.a(view2);
            }
        });
        View.OnTouchListener onTouchListener = new View.OnTouchListener() { // from class: no.nordicsemi.android.mcp.advertiser.j
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return EditAdvertisementDialogFragment.this.a(view, view2, motionEvent);
            }
        };
        RecyclerView recyclerView = (RecyclerView) view.findViewById(android.R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(null);
        recyclerView.setHasFixedSize(false);
        recyclerView.setOnTouchListener(onTouchListener);
        this.mAdapter.setOnTouchListener(onTouchListener);
        recyclerView.setAdapter(this.mAdapter);
        this.mConnectable = (CheckBox) view.findViewById(R.id.adv_connectible);
        this.mConnectable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.advertiser.g
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                EditAdvertisementDialogFragment.this.a(compoundButton, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fill(Cursor cursor) {
        String string = cursor.getString(1);
        boolean z = cursor.getInt(2) == 1;
        this.mNameView.setText(string);
        this.mConnectable.setChecked(z);
    }

    protected void fillAdvanced(Cursor cursor) {
        this.mModeSpinner.setSelection(LollipopBluetoothLeAdvertiserImpl.intervalToLollipopMode(cursor.getInt(5)));
        this.mTxPowerSpinner.setSelection(LollipopBluetoothLeAdvertiserImpl.txPowerLevelToLollipopTxPower(cursor.getInt(6)));
    }

    protected int getViewId() {
        return R.layout.dialog_advertisement_edit;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        long j = getArguments().getLong("id");
        try {
            updateAdvanced(update(j));
            ((AdvertiserFragment) getParentFragment()).onAdvertisementEdited(j);
            dismiss();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onConnectableChanged(boolean z) {
        this.mAdapter.setConnectible(z);
        this.mAdapter.notifyDataSetChanged();
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAdapter = new EditAdvertisementAdapter(this, bundle);
        this.mDatabase = new DatabaseHelper(getContext());
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        long j = getArguments().getLong("id");
        View inflate = LayoutInflater.from(new a.a.o.d(requireContext(), R.style.AppTheme_DialogTheme)).inflate(getViewId(), (ViewGroup) null);
        createView(inflate);
        createAdvancedView(inflate);
        createAdvancedExpander(inflate);
        Cursor advertisingPacket = this.mDatabase.getAdvertisingPacket(j);
        try {
            if (advertisingPacket.moveToNext()) {
                this.mAdapter.load(advertisingPacket.getBlob(11), advertisingPacket.getBlob(12));
                fill(advertisingPacket);
                fillAdvanced(advertisingPacket);
            } else {
                this.mAdapter.load(null, null);
            }
            advertisingPacket.close();
            int i = R.string.adv_alert_title_new;
            if (j > 0) {
                i = R.string.adv_alert_title_edit;
            }
            d.a aVar = new d.a(requireContext());
            aVar.a(false);
            aVar.c(i);
            aVar.b(inflate);
            aVar.c(R.string.ok, null);
            aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
            androidx.appcompat.app.d c2 = aVar.c();
            c2.setCanceledOnTouchOutside(false);
            c2.b(-1).setOnClickListener(this);
            return c2;
        } catch (Throwable th) {
            advertisingPacket.close();
            throw th;
        }
    }

    @Override // no.nordicsemi.android.mcp.advertiser.ItemEditedListener
    public void onItemEdited(boolean z, int i, int i2, byte[] bArr) {
        this.mAdapter.onItemEdited(z, i, i2, bArr);
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mAdapter.onSaveInstanceState(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long update(long j) {
        return this.mDatabase.updateAdvertisingPacket(j, this.mNameView.getText().toString().trim(), this.mConnectable.isChecked(), this.mAdapter.getAdvertisingData(), this.mAdapter.getScanResponseData());
    }

    protected long updateAdvanced(long j) {
        return this.mDatabase.updateAdvertisingPacket(j, LollipopBluetoothLeAdvertiserImpl.lollipopModeToInterval(this.mModeSpinner.getSelectedItemPosition()), LollipopBluetoothLeAdvertiserImpl.lollipopTxPowerTotxPowerLevel(this.mTxPowerSpinner.getSelectedItemPosition()));
    }

    public /* synthetic */ boolean a(View view, View view2, MotionEvent motionEvent) {
        ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mNameView.getWindowToken(), 0);
        return false;
    }

    public /* synthetic */ void a(CompoundButton compoundButton, boolean z) {
        onConnectableChanged(z);
    }

    public /* synthetic */ void a(View view, final View view2, CompoundButton compoundButton, boolean z) {
        ((InputMethodManager) compoundButton.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mNameView.getWindowToken(), 0);
        view.setVisibility(z ? 0 : 8);
        if (z) {
            view2.postDelayed(new Runnable() { // from class: no.nordicsemi.android.mcp.advertiser.i
                @Override // java.lang.Runnable
                public final void run() {
                    ((ScrollView) view2).fullScroll(GattError.GATT_WRONG_STATE);
                }
            }, 300L);
        }
    }
}
