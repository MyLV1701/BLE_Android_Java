package no.nordicsemi.android.mcp.scanner.timeline;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.t;
import no.nordicsemi.android.mcp.BaseActivity;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class TimelineDialogActivity extends BaseActivity {
    public static final String DEVICES_INDEXES = "devices_indexes";
    public static final String SCANNER_TIMESPANS = "timespans";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.BaseActivity, androidx.appcompat.app.e, androidx.fragment.app.d, androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_timeline);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_actionbar));
        getSupportActionBar().d(true);
        if (bundle == null) {
            TimelineFragment timelineFragment = TimelineFragment.getInstance();
            t b2 = getSupportFragmentManager().b();
            b2.a(R.id.content, timelineFragment, (String) null);
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
