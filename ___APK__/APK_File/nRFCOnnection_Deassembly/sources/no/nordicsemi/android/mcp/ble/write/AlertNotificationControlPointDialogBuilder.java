package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class AlertNotificationControlPointDialogBuilder extends WriteDialogBuilder {
    private Spinner mCategoriesSpinner;
    private Spinner mCommandsSpinner;

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_alert_notif_control_point, (ViewGroup) null);
        this.mCommandsSpinner = (Spinner) inflate.findViewById(R.id.command);
        this.mCategoriesSpinner = (Spinner) inflate.findViewById(R.id.category);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        byte[] bArr = new byte[2];
        bArr[0] = (byte) this.mCommandsSpinner.getSelectedItemPosition();
        int selectedItemPosition = this.mCategoriesSpinner.getSelectedItemPosition();
        if (selectedItemPosition >= 10) {
            selectedItemPosition += 241;
        }
        bArr[1] = (byte) selectedItemPosition;
        return bArr;
    }
}
