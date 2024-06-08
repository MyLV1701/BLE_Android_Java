package no.nordicsemi.android.mcp.tutorial;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.app.e;
import androidx.viewpager.widget.ViewPager;
import no.nordicsemi.android.mcp.MainActivity;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class TutorialActivity extends e {
    public static final int CURRENT_TUTORIAL_VERSION = 9;
    public static final String EXTRA_LAST_VERSION = "no.nordicsemi.android.mcp.EXTRA_LAST_VERSION";
    private static final int PAGE_ADVERTISER = 7;
    private static final int PAGE_AUTOMATED_TESTS = 16;
    private static final int PAGE_BONDED = 6;
    private static final int PAGE_CONNECT = 4;
    private static final int PAGE_CONNECTED_CLIENT = 9;
    private static final int PAGE_CONNECTED_LOG = 12;
    private static final int PAGE_CONNECTED_MACROS = 13;
    private static final int PAGE_CONNECTED_RENAME = 11;
    private static final int PAGE_CONNECTED_SERVER = 10;
    private static final int PAGE_DEFINITIONS = 14;
    private static final int PAGE_DFU = 15;
    private static final int PAGE_FILTER = 2;
    private static final int PAGE_GATT_SERVER = 8;
    private static final int PAGE_RSSI_GRAPH = 5;
    private static final int PAGE_SCANNER = 1;
    private static final int PAGE_SCANNER_EXPANDED = 3;
    private static final int PAGE_UPDATE_FILTER = 1;
    private static final int PAGE_UPDATE_WELCOME = 0;
    private static final int PAGE_UPDATE_ZEPHYR = 2;
    private static final int PAGE_WELCOME = 0;
    private View mAdvertiser;
    private View mAutomatedTests;
    private View mBonded;
    private View mConnectedClient;
    private View mConnectedLog;
    private View mConnectedMacros;
    private ImageView mConnectedPane;
    private View mConnectedRename;
    private View mConnectedRenameDialog;
    private View mDefinitions;
    private View mDfu;
    private View mDim;
    private View mDoneButton;
    private View mFilter;
    private View mGattServer;
    private View mMainConnect;
    private View mMainPane;
    private View mMainPaneBottom;
    private View mMainPaneColors;
    private View mMenu;
    private View mNRFConnectLogo;
    private View mPhone;
    private View mUpdateFilter;
    private View mUpdateScanner;
    private View mUpdateZephyr;

    public /* synthetic */ void a(View view) {
        onBackPressed();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        PreferenceManager.getDefaultSharedPreferences(this).edit().putInt(MainActivity.PREF_TUTORIAL_VERSION_SHOWN, 9).apply();
    }

    @Override // androidx.appcompat.app.e, androidx.fragment.app.d, androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_tutorial);
        final int intExtra = getIntent().getIntExtra(EXTRA_LAST_VERSION, 0);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.tutorial.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TutorialActivity.this.a(view);
            }
        };
        this.mNRFConnectLogo = findViewById(R.id.tutorial_app_icon);
        this.mPhone = findViewById(R.id.phone);
        this.mMainPane = findViewById(R.id.main_pane);
        this.mMainPaneColors = findViewById(R.id.main_pane_colors);
        this.mMainPaneBottom = findViewById(R.id.main_pane_bottom);
        this.mMainConnect = findViewById(R.id.main_connect);
        this.mFilter = findViewById(R.id.main_filter);
        this.mBonded = findViewById(R.id.main_tabs_bonded);
        this.mAdvertiser = findViewById(R.id.main_tabs_advertiser);
        this.mDim = findViewById(R.id.tutorial_dim);
        this.mMenu = findViewById(R.id.tutorial_menu);
        this.mGattServer = findViewById(R.id.tutorial_gatt_server);
        this.mConnectedLog = findViewById(R.id.tutorial_connected_log);
        this.mConnectedPane = (ImageView) findViewById(R.id.tutorial_connected_pane);
        this.mConnectedClient = findViewById(R.id.tutorial_connected_client);
        this.mConnectedRename = findViewById(R.id.tutorial_connected_rename);
        this.mConnectedRenameDialog = findViewById(R.id.tutorial_connected_rename_dialog);
        this.mConnectedMacros = findViewById(R.id.tutorial_connected_macros);
        this.mDefinitions = findViewById(R.id.tutorial_definitions);
        this.mDfu = findViewById(R.id.dfu);
        this.mAutomatedTests = findViewById(R.id.automated_tests);
        this.mUpdateScanner = findViewById(R.id.tutorial_update_scanner);
        this.mUpdateFilter = findViewById(R.id.tutorial_update_filter);
        this.mUpdateZephyr = findViewById(R.id.tutorial_update_zephyr);
        this.mDoneButton = findViewById(R.id.action_done);
        this.mDoneButton.setOnClickListener(onClickListener);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new PagesAdapter(this, intExtra, 9));
        com.viewpagerindicator.a aVar = (com.viewpagerindicator.a) findViewById(R.id.indicator);
        aVar.setViewPager(viewPager);
        aVar.setOnPageChangeListener(new ViewPager.j() { // from class: no.nordicsemi.android.mcp.tutorial.TutorialActivity.1
            private int mState;

            private float dp2px(float f2) {
                return TypedValue.applyDimension(1, f2, TutorialActivity.this.getResources().getDisplayMetrics());
            }

            @Override // androidx.viewpager.widget.ViewPager.j
            public void onPageScrollStateChanged(int i) {
                this.mState = i;
            }

            @Override // androidx.viewpager.widget.ViewPager.j
            public void onPageScrolled(int i, float f2, int i2) {
                if (intExtra != 0) {
                    if (i == 0) {
                        float f3 = f2 * 3.0f;
                        TutorialActivity.this.mNRFConnectLogo.setAlpha(1.0f - f3);
                        TutorialActivity.this.mPhone.setAlpha((-1.0f) + f3);
                        TutorialActivity.this.mUpdateScanner.setAlpha(1.0f);
                        TutorialActivity.this.mUpdateFilter.setAlpha(f3 - 2.0f);
                        return;
                    }
                    if (i != 1) {
                        return;
                    }
                    TutorialActivity.this.mNRFConnectLogo.setAlpha(0.0f);
                    TutorialActivity.this.mPhone.setAlpha(1.0f);
                    TutorialActivity.this.mUpdateScanner.setAlpha(1.0f);
                    TutorialActivity.this.mUpdateFilter.setAlpha(1.0f - (f2 * 3.0f));
                    TutorialActivity.this.mUpdateZephyr.setAlpha((f2 * 2.0f) - 1.0f);
                    TutorialActivity.this.mDoneButton.setAlpha((f2 * 4.0f) - 3.0f);
                    return;
                }
                switch (i) {
                    case 0:
                        float f4 = f2 * 2.0f;
                        TutorialActivity.this.mNRFConnectLogo.setAlpha(1.0f - f4);
                        TutorialActivity.this.mPhone.setAlpha(f4 - 1.0f);
                        return;
                    case 1:
                        TutorialActivity.this.mNRFConnectLogo.setAlpha(0.0f);
                        TutorialActivity.this.mPhone.setAlpha(1.0f);
                        TutorialActivity.this.mFilter.setAlpha(f2);
                        return;
                    case 2:
                        TutorialActivity.this.mFilter.setAlpha(1.0f - (f2 * 5.0f));
                        TutorialActivity.this.mMainConnect.setAlpha(0.0f);
                        ((FrameLayout.LayoutParams) TutorialActivity.this.mMainPaneBottom.getLayoutParams()).topMargin = (int) dp2px(Math.max(105.0f, (f2 * 135.0f) + 40.0f));
                        TutorialActivity.this.mMainPaneBottom.requestLayout();
                        return;
                    case 3:
                        TutorialActivity.this.mMainConnect.setAlpha(f2 * 5.0f);
                        float f5 = (f2 * 0.4f) + 0.6f;
                        TutorialActivity.this.mMainConnect.setScaleX(f5);
                        TutorialActivity.this.mMainConnect.setScaleY(f5);
                        return;
                    case 4:
                        float f6 = 1.0f - (f2 * 5.0f);
                        TutorialActivity.this.mMainConnect.setAlpha(f6);
                        float f7 = (f6 * 0.4f) + 0.6f;
                        TutorialActivity.this.mMainConnect.setScaleX(f7);
                        TutorialActivity.this.mMainConnect.setScaleY(f7);
                        ((FrameLayout.LayoutParams) TutorialActivity.this.mMainPane.getLayoutParams()).leftMargin = (int) dp2px(Math.max(0.0f, (175.5f * f2) - 58.5f));
                        TutorialActivity.this.mMainPane.requestLayout();
                        TutorialActivity.this.mMainPaneColors.setAlpha((f2 * 2.0f) - 1.0f);
                        return;
                    case 5:
                        TutorialActivity.this.mMainConnect.setAlpha(0.0f);
                        TutorialActivity.this.mAdvertiser.setAlpha(0.0f);
                        float f8 = f2 * 2.0f;
                        ((FrameLayout.LayoutParams) TutorialActivity.this.mMainPane.getLayoutParams()).leftMargin = (int) dp2px(Math.max((1.0f - f8) * 117.0f, 0.0f));
                        TutorialActivity.this.mMainPane.requestLayout();
                        TutorialActivity.this.mMainPaneColors.setAlpha(1.0f - (f2 * 4.0f));
                        TutorialActivity.this.mBonded.setAlpha(f8 - 1.0f);
                        return;
                    case 6:
                        TutorialActivity.this.mAdvertiser.setAlpha((f2 * 2.0f) - 0.5f);
                        ((FrameLayout.LayoutParams) TutorialActivity.this.mMenu.getLayoutParams()).leftMargin = (int) dp2px(-88.0f);
                        TutorialActivity.this.mMenu.requestLayout();
                        TutorialActivity.this.mMenu.setAlpha(1.0f);
                        TutorialActivity.this.mDim.setAlpha(0.0f);
                        return;
                    case 7:
                        float f9 = f2 * 3.0f;
                        if (f9 <= 1.0f) {
                            TutorialActivity.this.mDim.setAlpha(f9);
                            TutorialActivity.this.mMenu.setAlpha(1.0f);
                        } else {
                            float f10 = 2.0f - (f2 * 2.0f);
                            TutorialActivity.this.mMenu.setAlpha(f10);
                            TutorialActivity.this.mDim.setAlpha(f10);
                        }
                        ((FrameLayout.LayoutParams) TutorialActivity.this.mMenu.getLayoutParams()).leftMargin = (int) dp2px(Math.min((264.0f * f2) - 88.0f, 0.0f));
                        TutorialActivity.this.mMenu.requestLayout();
                        TutorialActivity.this.mGattServer.setAlpha((f2 * 2.0f) - 1.0f);
                        return;
                    case 8:
                        TutorialActivity.this.mDim.setAlpha(0.0f);
                        ((FrameLayout.LayoutParams) TutorialActivity.this.mMenu.getLayoutParams()).leftMargin = (int) dp2px(-88.0f);
                        TutorialActivity.this.mMenu.requestLayout();
                        TutorialActivity.this.mGattServer.setAlpha(1.0f - (f2 * 3.0f));
                        TutorialActivity.this.mConnectedLog.setAlpha(0.0f);
                        TutorialActivity.this.mConnectedPane.setAlpha(0.0f);
                        TutorialActivity.this.mConnectedClient.setAlpha((f2 * 2.0f) - 1.0f);
                        return;
                    case 9:
                        TutorialActivity.this.mConnectedLog.setAlpha(1.0f);
                        TutorialActivity.this.mConnectedPane.setAlpha(1.0f);
                        TutorialActivity.this.mConnectedClient.setAlpha(1.0f - f2);
                        return;
                    case 10:
                        TutorialActivity.this.mConnectedPane.setImageResource(R.drawable.tutorial_connected_pane);
                        TutorialActivity.this.mConnectedClient.setAlpha(0.0f);
                        float f11 = f2 * 3.0f;
                        TutorialActivity.this.mConnectedRename.setAlpha(f11);
                        TutorialActivity.this.mConnectedRenameDialog.setAlpha(f11 - 2.0f);
                        return;
                    case 11:
                        TutorialActivity.this.mConnectedPane.setImageResource(R.drawable.tutorial_connected_pane_renamed);
                        TutorialActivity.this.mConnectedRename.setAlpha(0.0f);
                        TutorialActivity.this.mConnectedRenameDialog.setAlpha(1.0f - (f2 * 4.0f));
                        ((FrameLayout.LayoutParams) TutorialActivity.this.mConnectedPane.getLayoutParams()).leftMargin = (int) dp2px(Math.max(-2.0f, (f2 * 177.0f) - 55.0f));
                        TutorialActivity.this.mConnectedPane.requestLayout();
                        return;
                    case 12:
                        ((FrameLayout.LayoutParams) TutorialActivity.this.mConnectedPane.getLayoutParams()).leftMargin = (int) dp2px(Math.max(-2.0f, ((1.0f - (f2 * 3.0f)) * 124.0f) - 2.0f));
                        TutorialActivity.this.mConnectedPane.requestLayout();
                        ((FrameLayout.LayoutParams) TutorialActivity.this.mConnectedMacros.getLayoutParams()).bottomMargin = (int) dp2px((f2 * 170.0f) - 170.0f);
                        TutorialActivity.this.mConnectedPane.requestLayout();
                        return;
                    case 13:
                        float f12 = f2 * 3.0f;
                        if (f12 <= 1.0f) {
                            TutorialActivity.this.mDim.setAlpha(f12);
                            TutorialActivity.this.mMenu.setAlpha(1.0f);
                        } else {
                            float f13 = 2.0f - (f2 * 2.0f);
                            TutorialActivity.this.mMenu.setAlpha(f13);
                            TutorialActivity.this.mDim.setAlpha(f13);
                        }
                        ((FrameLayout.LayoutParams) TutorialActivity.this.mMenu.getLayoutParams()).leftMargin = (int) dp2px(Math.min((264.0f * f2) - 88.0f, 0.0f));
                        TutorialActivity.this.mMenu.requestLayout();
                        TutorialActivity.this.mDefinitions.setAlpha((f2 * 2.0f) - 1.0f);
                        return;
                    case 14:
                        float f14 = 1.5f * f2;
                        TutorialActivity.this.mPhone.setAlpha(1.0f - f14);
                        TutorialActivity.this.mDfu.setAlpha(f14 - 0.5f);
                        float f15 = (f2 * 0.4f) + 0.6f;
                        TutorialActivity.this.mDfu.setScaleX(f15);
                        TutorialActivity.this.mDfu.setScaleY(f15);
                        return;
                    case 15:
                        TutorialActivity.this.mDfu.setAlpha(1.0f - f2);
                        float f16 = 1.0f - (f2 * 0.4f);
                        TutorialActivity.this.mDfu.setScaleX(f16);
                        TutorialActivity.this.mDfu.setScaleY(f16);
                        TutorialActivity.this.mAutomatedTests.setAlpha((1.7f * f2) - 0.7f);
                        TutorialActivity.this.mDoneButton.setAlpha((f2 * 4.0f) - 3.0f);
                        return;
                    default:
                        return;
                }
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Failed to find 'out' block for switch in B:7:0x000d. Please report as an issue. */
            /* JADX WARN: Removed duplicated region for block: B:11:0x003a  */
            /* JADX WARN: Removed duplicated region for block: B:16:0x0092  */
            /* JADX WARN: Removed duplicated region for block: B:19:0x00b4  */
            /* JADX WARN: Removed duplicated region for block: B:23:0x00df  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x00f5  */
            /* JADX WARN: Removed duplicated region for block: B:33:0x012f  */
            /* JADX WARN: Removed duplicated region for block: B:36:0x0159  */
            /* JADX WARN: Removed duplicated region for block: B:39:0x0194  */
            @Override // androidx.viewpager.widget.ViewPager.j
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onPageSelected(int r6) {
                /*
                    Method dump skipped, instructions count: 554
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.tutorial.TutorialActivity.AnonymousClass1.onPageSelected(int):void");
            }
        });
    }
}
