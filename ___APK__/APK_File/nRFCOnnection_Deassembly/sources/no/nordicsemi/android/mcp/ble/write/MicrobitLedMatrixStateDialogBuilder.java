package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TableRow;
import android.widget.TextView;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class MicrobitLedMatrixStateDialogBuilder extends WriteDialogBuilder {
    private static final byte[] VALUE_CLEAR = {0, 0, 0, 0, 0};
    private ViewGroup mView;

    private void setValue(byte[] bArr) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.ble.write.p
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MicrobitLedMatrixStateDialogBuilder.this.a(compoundButton, z);
            }
        };
        for (int i = 0; i < 5; i++) {
            TableRow tableRow = (TableRow) this.mView.getChildAt(i);
            byte b2 = bArr[i];
            ((TextView) tableRow.getChildAt(5)).setText(getString(R.string.microbit_hex, Byte.valueOf(b2)));
            for (int i2 = 0; i2 < 5; i2++) {
                CheckBox checkBox = (CheckBox) tableRow.getChildAt(i2);
                checkBox.setChecked(((1 << (4 - i2)) & b2) > 0);
                checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
            }
        }
    }

    public /* synthetic */ void a(CompoundButton compoundButton, boolean z) {
        TableRow tableRow = (TableRow) compoundButton.getParent();
        byte b2 = 0;
        for (int i = 0; i < 5; i++) {
            b2 = (byte) (((CheckBox) tableRow.getChildAt(i)).isChecked() ? b2 | (1 << (4 - i)) : b2 & ((1 << (4 - i)) ^ (-1)));
        }
        ((TextView) tableRow.getChildAt(5)).setText(getString(R.string.microbit_hex, Byte.valueOf(b2)));
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.dialog_write_microbit_led_matrix_state, (ViewGroup) null);
        this.mView = viewGroup;
        byte[] value = this.mCharacteristic.getValue();
        if (value == null || value.length != 5) {
            setValue(new byte[]{0, 0, 0, 0, 0});
        } else {
            setValue(value);
        }
        return viewGroup;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected int getNeutralButtonTitleResId() {
        return R.string.action_clear;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        byte[] bArr = new byte[5];
        for (int i = 0; i < 5; i++) {
            TableRow tableRow = (TableRow) ((ViewGroup) view).getChildAt(i);
            for (int i2 = 0; i2 < 5; i2++) {
                if (((CheckBox) tableRow.getChildAt(i2)).isChecked()) {
                    bArr[i] = (byte) (bArr[i] | (1 << (4 - i2)));
                }
            }
        }
        return bArr;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected boolean isValueValid(View view) {
        return true;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected void onNeutralButtonClick(androidx.appcompat.app.d dVar, View view) {
        setValue(VALUE_CLEAR);
    }
}
