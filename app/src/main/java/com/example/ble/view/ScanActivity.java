package com.example.ble.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;

import com.example.ble.R;
import com.example.ble.controller.BLEBroadcastManager;
import com.example.ble.view.utils.ViewControllerUtils;

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
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewControllerUtils.hideNavigationBar(getWindow());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(BLEBroadcastManager.scanBroadReceiver);
    }
}