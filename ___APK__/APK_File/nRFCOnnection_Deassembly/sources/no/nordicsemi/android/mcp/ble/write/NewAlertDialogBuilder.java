package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class NewAlertDialogBuilder extends WriteDialogBuilder {
    private Spinner mCategoriesSpinner;
    private EditText mMessageView;
    private EditText mNumberView;

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_new_alert, (ViewGroup) null);
        this.mCategoriesSpinner = (Spinner) inflate.findViewById(R.id.category);
        this.mNumberView = (EditText) inflate.findViewById(R.id.number);
        this.mMessageView = (EditText) inflate.findViewById(R.id.text);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        byte[] bytes = this.mMessageView.getText().toString().getBytes();
        byte[] bArr = new byte[bytes.length + 2];
        int selectedItemPosition = this.mCategoriesSpinner.getSelectedItemPosition();
        if (selectedItemPosition >= 10) {
            selectedItemPosition += 241;
        }
        bArr[0] = (byte) selectedItemPosition;
        bArr[1] = Integer.valueOf(this.mNumberView.getText().toString().trim()).byteValue();
        if (bytes.length > 0) {
            System.arraycopy(bytes, 0, bArr, 2, bytes.length);
        }
        return bArr;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected boolean isValueValid(View view) {
        try {
            int intValue = Integer.valueOf(this.mNumberView.getText().toString().trim()).intValue();
            if (intValue < 0 || intValue > 255) {
                throw new NumberFormatException();
            }
            return true;
        } catch (NumberFormatException unused) {
            this.mNumberView.setError(getString(R.string.alert_number_error));
            return false;
        }
    }
}
