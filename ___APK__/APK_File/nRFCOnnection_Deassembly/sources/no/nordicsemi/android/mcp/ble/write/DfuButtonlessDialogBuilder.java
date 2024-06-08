package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class DfuButtonlessDialogBuilder extends WriteDialogBuilder {
    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.textview, (ViewGroup) null);
        textView.setText(R.string.sec_dfu_enter_bootloader);
        return textView;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        return new byte[]{1};
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected boolean isValueValid(View view) {
        return true;
    }
}
