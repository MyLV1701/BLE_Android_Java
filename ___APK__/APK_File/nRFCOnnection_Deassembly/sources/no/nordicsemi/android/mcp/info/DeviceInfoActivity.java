package no.nordicsemi.android.mcp.info;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.t;
import no.nordicsemi.android.mcp.BaseActivity;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class DeviceInfoActivity extends BaseActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.BaseActivity, androidx.appcompat.app.e, androidx.fragment.app.d, androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_with_fragment);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_actionbar));
        getSupportActionBar().d(true);
        if (bundle == null) {
            t b2 = getSupportFragmentManager().b();
            b2.a(R.id.content, new DeviceInfoFragment());
            b2.a();
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }
}
