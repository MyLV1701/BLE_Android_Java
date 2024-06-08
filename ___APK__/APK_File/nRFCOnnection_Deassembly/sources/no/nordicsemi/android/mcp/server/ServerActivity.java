package no.nordicsemi.android.mcp.server;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.SpinnerAdapter;
import androidx.appcompat.widget.Toolbar;
import no.nordicsemi.android.mcp.BaseActivity;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.widget.ClosableSpinner;

/* loaded from: classes.dex */
public class ServerActivity extends BaseActivity {
    private ClosableSpinner mToolbarSpinner;

    public void closeToolbarSpinner() {
        this.mToolbarSpinner.close();
    }

    public long getToolbarSpinnerSelectionId() {
        return this.mToolbarSpinner.getSelectedItemId();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getSupportFragmentManager().n() > 0) {
            getSupportFragmentManager().y();
        } else {
            super.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.BaseActivity, androidx.appcompat.app.e, androidx.fragment.app.d, androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_server);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().d(true);
        getSupportActionBar().e(false);
        this.mToolbarSpinner = (ClosableSpinner) toolbar.findViewById(R.id.toolbar_spinner);
        if (bundle == null) {
            androidx.fragment.app.t b2 = getSupportFragmentManager().b();
            b2.a(R.id.content, new ServerFragment());
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

    public void setToolbarSpinnerAdapter(SpinnerAdapter spinnerAdapter, AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.mToolbarSpinner.setAdapter(spinnerAdapter);
        this.mToolbarSpinner.setOnItemSelectedListener(onItemSelectedListener);
        this.mToolbarSpinner.setVisibility(0);
    }

    public void setToolbarSpinnerSelection(int i) {
        this.mToolbarSpinner.setSelection(i);
    }
}
