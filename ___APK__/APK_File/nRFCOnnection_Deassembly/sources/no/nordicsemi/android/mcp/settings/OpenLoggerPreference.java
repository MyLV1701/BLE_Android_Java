package no.nordicsemi.android.mcp.settings;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.Toast;
import androidx.preference.Preference;
import no.nordicsemi.android.log.LogContract;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class OpenLoggerPreference extends Preference {
    public OpenLoggerPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public void onClick() {
        Context context = getContext();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setType(LogContract.Session.CONTENT_TYPE);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setFlags(268435456);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
            return;
        }
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=no.nordicsemi.android.log")));
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(getContext(), R.string.no_application_play, 0).show();
        }
    }

    public OpenLoggerPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
