package no.nordicsemi.android.mcp.settings;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.Toast;
import androidx.preference.Preference;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class AboutDfuPreference extends Preference {
    public AboutDfuPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public void onClick() {
        Context context = getContext();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.nordicsemi.com/en/DocLib/Content/SDK_Doc/nRF5_SDK/v15-2-0/ble_sdk_app_dfu_bootloader"));
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setFlags(268435456);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            Toast.makeText(getContext(), R.string.no_application, 1).show();
        }
    }

    public AboutDfuPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
