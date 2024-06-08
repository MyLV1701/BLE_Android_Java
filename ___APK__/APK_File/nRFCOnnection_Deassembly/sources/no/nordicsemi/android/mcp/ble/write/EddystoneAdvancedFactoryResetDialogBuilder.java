package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class EddystoneAdvancedFactoryResetDialogBuilder extends WriteDialogBuilder {

    /* loaded from: classes.dex */
    private class ViewHolder {
        private CheckBox factoryReset;

        private ViewHolder() {
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_es_advanced_factory_reset, (ViewGroup) null);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.factoryReset = (CheckBox) inflate.findViewById(R.id.factory_reset);
        inflate.setTag(viewHolder);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        return new byte[]{11};
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected boolean isValueValid(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (viewHolder.factoryReset.isChecked()) {
            return true;
        }
        viewHolder.factoryReset.setError("Please select the checkbox to proceed with factor reset");
        return false;
    }
}
