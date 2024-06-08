package no.nordicsemi.android.mcp.scanner.details;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import no.nordicsemi.android.mcp.BaseActivity;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat;

/* loaded from: classes.dex */
public class DeviceDetailsDialogActivity extends BaseActivity implements HorizontalScrollListener {
    public static final String DEVICE_INDEX = "device_index";
    public static final String SCANNER_TIMESPANS = "timespans";
    private ViewPager mPager;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.BaseActivity, androidx.appcompat.app.e, androidx.fragment.app.d, androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_details);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_actionbar));
        getSupportActionBar().d(true);
        Intent intent = getIntent();
        Device devices = BluetoothLeScannerCompat.getDevices(intent.getIntExtra(DEVICE_INDEX, 0));
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("timespans");
        String name = devices.getName();
        if (TextUtils.isEmpty(name)) {
            name = getString(R.string.not_available);
        }
        setTitle(name);
        DeviceDetailsAdapter deviceDetailsAdapter = new DeviceDetailsAdapter(this, devices, parcelableArrayListExtra);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        this.mPager = viewPager;
        viewPager.setAdapter(deviceDetailsAdapter);
        viewPager.setPageMargin((int) TypedValue.applyDimension(1, 0.5f, getResources().getDisplayMetrics()));
        viewPager.setPageMarginDrawable(new ColorDrawable(-3815995));
        viewPager.addOnPageChangeListener(new TabLayout.i(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setVisibility(tabLayout.getTabCount() <= 1 ? 8 : 0);
        tabLayout.addOnTabSelectedListener(new TabLayout.k(viewPager));
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    @Override // no.nordicsemi.android.mcp.scanner.details.HorizontalScrollListener
    public void onScroll() {
        this.mPager.requestDisallowInterceptTouchEvent(true);
    }
}
