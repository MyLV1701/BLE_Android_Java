package no.nordicsemi.android.mcp.ble.dfu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import no.nordicsemi.android.mcp.MainActivity;

/* loaded from: classes.dex */
public class NotificationActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isTaskRoot()) {
            Intent intent = new Intent(getApplicationContext(), (Class<?>) MainActivity.class);
            intent.addFlags(268435456);
            intent.putExtras(getIntent().getExtras());
            startActivity(intent);
        }
        finish();
    }
}
