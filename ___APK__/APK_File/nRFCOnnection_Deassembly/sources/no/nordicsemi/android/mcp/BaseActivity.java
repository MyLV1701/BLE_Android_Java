package no.nordicsemi.android.mcp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import no.nordicsemi.android.mcp.ble.BluetoothLeService;

/* loaded from: classes.dex */
public abstract class BaseActivity extends androidx.appcompat.app.e {
    private BroadcastReceiver mCloseAppBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.BaseActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BaseActivity.this.finish();
        }
    };
    private boolean mNewActivityStarted;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.e, androidx.fragment.app.d, androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (shouldFinishOnBroadcast()) {
            registerReceiver(this.mCloseAppBroadcastReceiver, new IntentFilter(BluetoothLeService.ACTION_STOP_NRF_CONNECT));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.e, androidx.fragment.app.d, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (shouldFinishOnBroadcast()) {
            unregisterReceiver(this.mCloseAppBroadcastReceiver);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.e, androidx.fragment.app.d, android.app.Activity
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(BluetoothLeService.ACTION_BACKGROUND_MODE_CHANGED);
        intent.putExtra(BluetoothLeService.EXTRA_BACKGROUND_MODE, false);
        a.l.a.a.a(this).a(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.e, androidx.fragment.app.d, android.app.Activity
    public void onStop() {
        super.onStop();
        if (!this.mNewActivityStarted && !isFinishing() && !isChangingConfigurations()) {
            Intent intent = new Intent(BluetoothLeService.ACTION_BACKGROUND_MODE_CHANGED);
            intent.putExtra(BluetoothLeService.EXTRA_BACKGROUND_MODE, true);
            a.l.a.a.a(this).a(intent);
        }
        this.mNewActivityStarted = false;
    }

    protected boolean shouldFinishOnBroadcast() {
        return true;
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        if (getPackageManager().queryIntentActivities(intent, 0).isEmpty()) {
            return;
        }
        this.mNewActivityStarted = intent.getData() == null;
        super.startActivity(intent);
    }

    @Override // androidx.fragment.app.d, android.app.Activity
    public void startActivityForResult(Intent intent, int i) {
        if (getPackageManager().queryIntentActivities(intent, 0).isEmpty()) {
            return;
        }
        super.startActivityForResult(intent, i);
    }
}
