package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class SupportedAlertCategoriesDialogBuilder extends WriteDialogBuilder {
    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.dialog_write_supported_alert_categories, (ViewGroup) null);
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        boolean isChecked = ((CheckBox) view.findViewById(R.id.alert_category_0)).isChecked();
        boolean isChecked2 = ((CheckBox) view.findViewById(R.id.alert_category_1)).isChecked();
        boolean isChecked3 = ((CheckBox) view.findViewById(R.id.alert_category_2)).isChecked();
        boolean isChecked4 = ((CheckBox) view.findViewById(R.id.alert_category_3)).isChecked();
        boolean isChecked5 = ((CheckBox) view.findViewById(R.id.alert_category_4)).isChecked();
        boolean isChecked6 = ((CheckBox) view.findViewById(R.id.alert_category_5)).isChecked();
        boolean isChecked7 = ((CheckBox) view.findViewById(R.id.alert_category_6)).isChecked();
        boolean isChecked8 = ((CheckBox) view.findViewById(R.id.alert_category_7)).isChecked();
        boolean isChecked9 = ((CheckBox) view.findViewById(R.id.alert_category_8)).isChecked();
        boolean isChecked10 = ((CheckBox) view.findViewById(R.id.alert_category_9)).isChecked();
        byte[] bArr = new byte[(isChecked9 || isChecked10) ? 2 : 1];
        if (isChecked) {
            bArr[0] = (byte) (bArr[0] | 1);
        }
        if (isChecked2) {
            bArr[0] = (byte) (bArr[0] | 2);
        }
        if (isChecked3) {
            bArr[0] = (byte) (bArr[0] | 4);
        }
        if (isChecked4) {
            bArr[0] = (byte) (bArr[0] | 8);
        }
        if (isChecked5) {
            bArr[0] = (byte) (bArr[0] | 16);
        }
        if (isChecked6) {
            bArr[0] = (byte) (bArr[0] | 32);
        }
        if (isChecked7) {
            bArr[0] = (byte) (bArr[0] | 64);
        }
        if (isChecked8) {
            bArr[0] = (byte) (bArr[0] | 128);
        }
        if (isChecked9) {
            bArr[1] = (byte) (bArr[1] | 1);
        }
        if (isChecked10) {
            bArr[1] = (byte) (bArr[1] | 2);
        }
        return bArr;
    }
}
