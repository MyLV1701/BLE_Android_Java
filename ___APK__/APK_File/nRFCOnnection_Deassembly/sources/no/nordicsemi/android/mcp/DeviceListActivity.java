package no.nordicsemi.android.mcp;

import android.content.Intent;
import android.os.Bundle;

/* loaded from: classes.dex */
public class DeviceListActivity extends androidx.appcompat.app.e {
    @Override // androidx.appcompat.app.e, androidx.fragment.app.d, androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isTaskRoot()) {
            Intent intent = new Intent(getApplicationContext(), (Class<?>) MainActivity.class);
            intent.addFlags(268435456);
            if (getIntent() != null && getIntent().getExtras() != null) {
                intent.putExtras(getIntent().getExtras());
            }
            startActivity(intent);
        }
        finish();
    }
}
