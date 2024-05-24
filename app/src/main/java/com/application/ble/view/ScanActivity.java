package com.application.ble.view;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.application.ble.R;
import com.application.ble.controller.BLEBroadcastManager;
import com.application.ble.view.utils.ViewControllerUtils;


/**
 * ① 起動画面
 */
public class ScanActivity extends AppCompatActivity {

    final String SCAN_LOCAL_BROADCAST = "scan-action-local-broadcast";
    Button btnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(BLEBroadcastManager.scanBroadReceiver, new IntentFilter(SCAN_LOCAL_BROADCAST));
        btnScan = (Button) findViewById(R.id.btn_scan);
        btnScan.setOnClickListener(view -> {
            //Di chuyen den man hinh Device List
            Intent i = new Intent(getApplicationContext(),DevicesListActivity.class);
            startActivity(i);

            //TODO: Them xu li khi bam nut SCAN
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewControllerUtils.hideNavigationBar(getWindow());
        findViewById(R.id.app_name).findViewById(R.id.btnNavigation).setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(BLEBroadcastManager.scanBroadReceiver);
    }
}