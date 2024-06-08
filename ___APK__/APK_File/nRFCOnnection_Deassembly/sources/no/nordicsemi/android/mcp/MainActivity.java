package no.nordicsemi.android.mcp;

import a.f.l.e0;
import a.f.l.r;
import a.f.l.w;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.d;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.t;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import no.nordicsemi.android.mcp.MenuFragment;
import no.nordicsemi.android.mcp.ble.BluetoothLeService;
import no.nordicsemi.android.mcp.ble.parser.utils.CompanyIdentifier2;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.fragment.DarkThemeDialogFragment;
import no.nordicsemi.android.mcp.settings.SettingsFragment;
import no.nordicsemi.android.mcp.tutorial.TutorialActivity;
import no.nordicsemi.android.mcp.widget.ClosableTabLayout;
import no.nordicsemi.android.mcp.widget.scanner.AnimationListenerAdapter;

/* loaded from: classes.dex */
public class MainActivity extends BaseActivity implements MenuFragment.MenuListener {
    public static final String PREF_DARK_THEME_DIALOG_SHOWN = "mcp_dark_theme_dialog_shown";
    public static final String PREF_LAST_ANDROID_VERSION = "mcp_last_android_version";
    public static final String PREF_SCAN_BATCHING_DISABLED = "mcp_scan_batching_disabled";
    public static final String PREF_TUTORIAL_VERSION_SHOWN = "mcp_tutorial_version_shown";
    private OnBackPressedListener mBackPressedListener;
    private TextView mBleDisabledStatusView;
    private View mBleDisabledView;
    private Button mBleEnableAction;
    private DrawerLayout mDrawerLayout;
    private DrawerListener mDrawerListener;
    private androidx.appcompat.app.b mDrawerToggle;
    private Handler mHandler;
    private MenuFragment mMenuFragment;
    private ClosableTabLayout.OnTabClosedListener mOnTabClosedListener;
    private Runnable mTabSwitchTask;
    private ClosableTabLayout mToolbarTabLayout;
    private View mToolbarTabLayoutActionMode;
    private BroadcastReceiver mBluetoothStateBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.MainActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (MainActivity.this.isBleSupported()) {
                switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10)) {
                    case 10:
                        MainActivity.this.mBleDisabledStatusView.setText(R.string.ble_adapter_disabled);
                        MainActivity.this.mBleDisabledView.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.alert_state_off));
                        MainActivity.this.mBleEnableAction.setVisibility(0);
                        MainActivity.this.mBleDisabledView.setVisibility(0);
                        break;
                    case 11:
                        MainActivity.this.mBleDisabledStatusView.setText(R.string.ble_adapter_enabling);
                        MainActivity.this.mBleDisabledView.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.alert_state_turning_on));
                        MainActivity.this.mBleEnableAction.setVisibility(8);
                        MainActivity.this.mBleDisabledView.setVisibility(0);
                        break;
                    case 12:
                        MainActivity.this.mBleDisabledView.setVisibility(8);
                        break;
                    case 13:
                        MainActivity.this.mBleDisabledStatusView.setText(R.string.ble_adapter_disabling);
                        MainActivity.this.mBleDisabledView.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.alert_state_turning_off));
                        MainActivity.this.mBleEnableAction.setVisibility(8);
                        MainActivity.this.mBleDisabledView.setVisibility(0);
                        break;
                }
                MainActivity.this.invalidateOptionsMenu();
            }
        }
    };
    private BroadcastReceiver mServerFailedBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.MainActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this, R.string.server_failed, 1).show();
        }
    };

    /* loaded from: classes.dex */
    public interface DrawerListener {
        void onDrawerSlide(View view, float f2);
    }

    /* loaded from: classes.dex */
    public interface OnBackPressedListener {
        boolean onBackPressed();
    }

    @TargetApi(21)
    private void changeStatusBarColor(int i, int i2) {
        ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), Integer.valueOf(i), Integer.valueOf(i2));
        ofObject.setDuration(200L);
        ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: no.nordicsemi.android.mcp.a
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                MainActivity.this.a(valueAnimator);
            }
        });
        ofObject.start();
    }

    private void enableBle() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            defaultAdapter.enable();
        }
    }

    private boolean isBleEnabled() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isBleSupported() {
        return getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    private void setTabsVisible(boolean z) {
        if (!z) {
            this.mToolbarTabLayoutActionMode.setVisibility(0);
            this.mToolbarTabLayoutActionMode.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        } else {
            Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
            loadAnimation.setAnimationListener(new AnimationListenerAdapter() { // from class: no.nordicsemi.android.mcp.MainActivity.5
                @Override // no.nordicsemi.android.mcp.widget.scanner.AnimationListenerAdapter, android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    MainActivity.this.mToolbarTabLayoutActionMode.setVisibility(8);
                }
            });
            this.mToolbarTabLayoutActionMode.startAnimation(loadAnimation);
        }
    }

    public /* synthetic */ void a(View view) {
        enableBle();
    }

    public /* synthetic */ void b(View view) {
        d.a aVar = new d.a(this);
        aVar.b(R.string.settings_scanning_batching_disabled_test);
        aVar.c(R.string.settings_information);
        aVar.c(R.string.ok, null);
        aVar.c();
    }

    @Override // androidx.fragment.app.d
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof MenuFragment) {
            this.mMenuFragment = (MenuFragment) fragment;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.mDrawerLayout.e(8388611)) {
            this.mDrawerLayout.a(8388611);
            return;
        }
        OnBackPressedListener onBackPressedListener = this.mBackPressedListener;
        if (onBackPressedListener == null || !onBackPressedListener.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override // androidx.appcompat.app.e, androidx.fragment.app.d, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mDrawerToggle.onConfigurationChanged(configuration);
    }

    @Override // no.nordicsemi.android.mcp.BaseActivity, androidx.appcompat.app.e, androidx.fragment.app.d, androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    @SuppressLint({"NewApi"})
    protected void onCreate(Bundle bundle) {
        androidx.appcompat.app.g.e(PreferenceManager.getDefaultSharedPreferences(this).getInt(SettingsFragment.SETTINGS_THEME, -1));
        super.onCreate(bundle);
        registerReceiver(this.mBluetoothStateBroadcastReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        if (!new DatabaseHelper(this).isServiceDefined(6203)) {
            Intent intent = new Intent(this, (Class<?>) SplashscreenActivity.class);
            intent.addFlags(65536);
            startActivity(intent);
            finish();
            return;
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int i = defaultSharedPreferences.getInt(PREF_TUTORIAL_VERSION_SHOWN, 0);
        if (i < 9) {
            Intent intent2 = new Intent(this, (Class<?>) TutorialActivity.class);
            intent2.putExtra(TutorialActivity.EXTRA_LAST_VERSION, i);
            intent2.addFlags(65536);
            startActivity(intent2);
            defaultSharedPreferences.edit().putBoolean(PREF_DARK_THEME_DIALOG_SHOWN, true).apply();
        } else {
            if (!defaultSharedPreferences.getBoolean(PREF_DARK_THEME_DIALOG_SHOWN, (getResources().getConfiguration().uiMode & 32) != 0)) {
                new DarkThemeDialogFragment().show(getSupportFragmentManager(), (String) null);
            }
            defaultSharedPreferences.edit().putBoolean(PREF_DARK_THEME_DIALOG_SHOWN, true).apply();
        }
        CompanyIdentifier2.init(this);
        int i2 = defaultSharedPreferences.getInt(PREF_LAST_ANDROID_VERSION, 0);
        int i3 = Build.VERSION.SDK_INT;
        if (i2 < i3) {
            SharedPreferences.Editor edit = defaultSharedPreferences.edit();
            edit.putInt(PREF_LAST_ANDROID_VERSION, i3);
            edit.putString(SettingsFragment.SETTINGS_SCANNING_MODE_LOLLIPOP, String.valueOf(2));
            if (i3 >= 21) {
                edit.putString(SettingsFragment.SETTINGS_SCANNING_API, "21");
                edit.putBoolean(SettingsFragment.SETTINGS_SCANNING_BATCHING, false);
            } else {
                edit.putString(SettingsFragment.SETTINGS_SCANNING_API, "18");
                edit.putBoolean(SettingsFragment.SETTINGS_SCANNING_BATCHING, false);
            }
            edit.apply();
        }
        setContentView(R.layout.activity_main);
        this.mHandler = new Handler();
        this.mBleDisabledView = findViewById(R.id.ble_disabled);
        this.mBleDisabledView.setVisibility((!isBleSupported() || isBleEnabled()) ? 8 : 0);
        this.mBleDisabledStatusView = (TextView) this.mBleDisabledView.findViewById(R.id.status);
        Button button = (Button) this.mBleDisabledView.findViewById(R.id.action_enable);
        this.mBleEnableAction = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.this.a(view);
            }
        });
        if (!defaultSharedPreferences.getBoolean(PREF_SCAN_BATCHING_DISABLED, false)) {
            if (defaultSharedPreferences.getBoolean(SettingsFragment.SETTINGS_SCANNING_BATCHING, false)) {
                defaultSharedPreferences.edit().putBoolean(SettingsFragment.SETTINGS_SCANNING_BATCHING, false).apply();
                Snackbar a2 = Snackbar.a(findViewById(R.id.content), R.string.settings_scanning_batching_disabled_msg, 0);
                a2.e(getResources().getColor(R.color.variant));
                a2.a(R.string.action_more, new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.b
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        MainActivity.this.b(view);
                    }
                });
                a2.k();
            }
            defaultSharedPreferences.edit().putBoolean(PREF_SCAN_BATCHING_DISABLED, true).apply();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);
        androidx.appcompat.app.a supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.d(true);
        }
        this.mToolbarTabLayout = (ClosableTabLayout) findViewById(R.id.toolbar_tabs);
        this.mToolbarTabLayout.setOnTabClosedListener(this.mOnTabClosedListener);
        this.mToolbarTabLayoutActionMode = findViewById(R.id.toolbar_tabs_action_mode);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.mDrawerLayout = drawerLayout;
        drawerLayout.b(R.drawable.drawer_shadow, 8388611);
        this.mDrawerToggle = new androidx.appcompat.app.b(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) { // from class: no.nordicsemi.android.mcp.MainActivity.3
            @Override // androidx.appcompat.app.b, androidx.drawerlayout.widget.DrawerLayout.d
            public void onDrawerSlide(View view, float f2) {
                super.onDrawerSlide(view, 0.0f);
                if (MainActivity.this.mDrawerListener != null) {
                    MainActivity.this.mDrawerListener.onDrawerSlide(view, f2);
                }
            }
        };
        drawerLayout.a(this.mDrawerToggle);
        w.a(findViewById(R.id.insetsReceiver), new r() { // from class: no.nordicsemi.android.mcp.d
            @Override // a.f.l.r
            public final e0 a(View view, e0 e0Var) {
                return MainActivity.this.a(view, e0Var);
            }
        });
        a.l.a.a.a(this).a(this.mServerFailedBroadcastReceiver, new IntentFilter(BluetoothLeService.ACTION_SERVER_FAILED_TO_START));
        if (bundle == null) {
            t b2 = getSupportFragmentManager().b();
            b2.a(R.id.content, new TabsFragment(), TabsFragment.FRAGMENT_ID);
            b2.a();
        }
    }

    @Override // no.nordicsemi.android.mcp.BaseActivity, androidx.appcompat.app.e, androidx.fragment.app.d, android.app.Activity
    protected void onDestroy() {
        ClosableTabLayout closableTabLayout = this.mToolbarTabLayout;
        if (closableTabLayout != null) {
            closableTabLayout.setOnTabClosedListener(null);
            this.mToolbarTabLayout.setupWithViewPager(null);
        }
        this.mToolbarTabLayout = null;
        if (this.mDrawerListener != null) {
            this.mDrawerLayout.b(this.mDrawerToggle);
        }
        this.mDrawerLayout = null;
        this.mDrawerToggle = null;
        this.mDrawerListener = null;
        this.mOnTabClosedListener = null;
        this.mBackPressedListener = null;
        this.mHandler = null;
        unregisterReceiver(this.mBluetoothStateBroadcastReceiver);
        this.mBluetoothStateBroadcastReceiver = null;
        a.l.a.a.a(this).a(this.mServerFailedBroadcastReceiver);
        this.mServerFailedBroadcastReceiver = null;
        super.onDestroy();
    }

    @Override // no.nordicsemi.android.mcp.MenuFragment.MenuListener
    public void onMenuItemClicked(Fragment fragment) {
        t b2 = getSupportFragmentManager().b();
        b2.b(R.id.content, fragment, null);
        b2.a();
        this.mDrawerLayout.a(8388611);
    }

    @Override // androidx.appcompat.app.e, android.app.Activity
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        this.mDrawerToggle.syncState();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        this.mDrawerToggle.syncState();
    }

    @Override // androidx.appcompat.app.e, androidx.appcompat.app.f
    public void onSupportActionModeFinished(a.a.o.b bVar) {
        super.onSupportActionModeFinished(bVar);
        setTabsVisible(true);
        if (Build.VERSION.SDK_INT >= 21) {
            changeStatusBarColor(getResources().getColor(R.color.actionModeDark), getResources().getColor(R.color.actionBarColorDark));
        }
    }

    @Override // androidx.appcompat.app.e, androidx.appcompat.app.f
    public void onSupportActionModeStarted(a.a.o.b bVar) {
        super.onSupportActionModeStarted(bVar);
        setTabsVisible(false);
        if (Build.VERSION.SDK_INT >= 21) {
            changeStatusBarColor(getResources().getColor(R.color.actionBarColorDark), getResources().getColor(R.color.actionModeDark));
        }
    }

    public void selectTab(final int i) {
        this.mHandler.removeCallbacks(this.mTabSwitchTask);
        Handler handler = this.mHandler;
        Runnable runnable = new Runnable() { // from class: no.nordicsemi.android.mcp.c
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.this.a(i);
            }
        };
        this.mTabSwitchTask = runnable;
        handler.postDelayed(runnable, 100L);
    }

    public void setDrawerListener(DrawerListener drawerListener) {
        this.mDrawerListener = drawerListener;
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.mBackPressedListener = onBackPressedListener;
    }

    public void setOnTabCloseListener(ClosableTabLayout.OnTabClosedListener onTabClosedListener) {
        ClosableTabLayout closableTabLayout = this.mToolbarTabLayout;
        if (closableTabLayout != null) {
            closableTabLayout.setOnTabClosedListener(onTabClosedListener);
        } else {
            this.mOnTabClosedListener = onTabClosedListener;
        }
    }

    public void setupWithViewPager(ViewPager viewPager) {
        this.mToolbarTabLayout.setupWithViewPager(viewPager);
    }

    @Override // no.nordicsemi.android.mcp.BaseActivity
    protected boolean shouldFinishOnBroadcast() {
        return false;
    }

    public /* synthetic */ e0 a(View view, e0 e0Var) {
        int e2 = e0Var.e();
        MenuFragment menuFragment = this.mMenuFragment;
        if (menuFragment != null) {
            menuFragment.setStatusBarHeight(e2);
        }
        return e0Var.a();
    }

    @Override // no.nordicsemi.android.mcp.MenuFragment.MenuListener
    public void onMenuItemClicked(final Intent intent) {
        this.mDrawerLayout.a(new DrawerLayout.g() { // from class: no.nordicsemi.android.mcp.MainActivity.4
            @Override // androidx.drawerlayout.widget.DrawerLayout.g, androidx.drawerlayout.widget.DrawerLayout.d
            public void onDrawerClosed(View view) {
                MainActivity.this.mDrawerLayout.b(this);
                MainActivity.this.startActivity(intent);
            }
        });
        this.mDrawerLayout.a(8388611);
    }

    public /* synthetic */ void a(ValueAnimator valueAnimator) {
        getWindow().setStatusBarColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    public /* synthetic */ void a(int i) {
        TabLayout.h tabAt;
        ClosableTabLayout closableTabLayout = this.mToolbarTabLayout;
        if (closableTabLayout != null && i < closableTabLayout.getTabCount() && (tabAt = closableTabLayout.getTabAt(i)) != null) {
            tabAt.i();
        }
        this.mTabSwitchTask = null;
    }
}
