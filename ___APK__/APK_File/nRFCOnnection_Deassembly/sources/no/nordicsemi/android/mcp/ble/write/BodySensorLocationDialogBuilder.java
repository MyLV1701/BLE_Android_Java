package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class BodySensorLocationDialogBuilder extends WriteDialogBuilder {
    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_spinner, (ViewGroup) null);
        ((TextView) inflate.findViewById(android.R.id.title)).setText(R.string.hrm_label);
        Spinner spinner = (Spinner) inflate.findViewById(R.id.value);
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, getResources().getTextArray(R.array.hrm_locations));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        inflate.setTag(spinner);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        return new byte[]{(byte) ((Spinner) view.getTag()).getSelectedItemPosition()};
    }
}
