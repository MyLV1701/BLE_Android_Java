package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class StringDialogBuilder extends WriteDialogBuilder {
    private static final String EXTRA_CHARSET = "charset";
    private EditText mValue;

    public static StringDialogBuilder utf16() {
        StringDialogBuilder stringDialogBuilder = new StringDialogBuilder();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_CHARSET, "UTF-16");
        stringDialogBuilder.setArguments(bundle);
        return stringDialogBuilder;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_string, (ViewGroup) null);
        EditText editText = (EditText) inflate.findViewById(R.id.value);
        this.mValue = editText;
        editText.setHint(R.string.alert_hint);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        try {
            return this.mValue.getText().toString().getBytes(getArguments() != null ? getArguments().getString(EXTRA_CHARSET) : "UTF-8");
        } catch (Exception unused) {
            return this.mValue.getText().toString().getBytes();
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected boolean isValueValid(View view) {
        return true;
    }
}
