package no.nordicsemi.android.mcp.settings;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.t;
import no.nordicsemi.android.mcp.BaseActivity;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.settings.SettingsCategoriesFragment;

/* loaded from: classes.dex */
public class SettingsActivity extends BaseActivity implements SettingsCategoriesFragment.OnCategoryClickedListener {
    public static final String EXTRA_THEME = "mcp_extra_theme";

    @Override // no.nordicsemi.android.mcp.settings.SettingsCategoriesFragment.OnCategoryClickedListener
    public void onCategoryClicked(String str) {
        t b2 = getSupportFragmentManager().b();
        b2.b(R.id.content, SettingsFragment.getInstance(str));
        b2.a((String) null);
        b2.a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.BaseActivity, androidx.appcompat.app.e, androidx.fragment.app.d, androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_with_fragment);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_actionbar));
        getSupportActionBar().d(true);
        if (bundle == null) {
            t b2 = getSupportFragmentManager().b();
            b2.a(R.id.content, new SettingsCategoriesFragment());
            b2.a();
        }
    }

    @Override // no.nordicsemi.android.mcp.settings.SettingsCategoriesFragment.OnCategoryClickedListener
    public void onHelpClicked() {
        t b2 = getSupportFragmentManager().b();
        b2.b(R.id.content, new HelpFragment());
        b2.a((String) null);
        b2.a();
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
