package com.application.ble.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.application.ble.R;
import com.application.ble.controller.BLEBroadcastManager;
import com.application.ble.view.utils.ViewControllerUtils;


/**
 * 制御画面
 */
public class DevicesInfoActivity extends AppCompatActivity {
    final String DEVICES_INFO_LOCAL_BROADCAST = "devices-info-action-local-broadcast";

    Button btnDisconnect;
    TextView txtConnectStatus;
    Button btnUnlock1;
    Button btnUnlock2;
    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices_info);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(BLEBroadcastManager.devicesInfoBroadReceiver, new IntentFilter(DEVICES_INFO_LOCAL_BROADCAST));
        btnDisconnect = findViewById(R.id.detail_info_disconnect);
        btnDisconnect.setOnClickListener(view -> {
             Intent i = new Intent(getApplicationContext(),DevicesListActivity.class);
             i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             startActivity(i);
            //TODO: Them xu li khi bam nut DISCONNECT
        });
        String value = getIntent().getExtras().getString("id");
        txtTitle = findViewById(R.id.txt_title_detail);
        txtTitle.setText(value);
        txtConnectStatus = findViewById(R.id.detail_info_connect_status);

        btnUnlock1 = findViewById(R.id.detail_info_unlock1);
        btnUnlock1.setOnClickListener(view -> {
            //TODO: Them xu li khi bam nut UNLOCK1
        });

        btnUnlock2 = findViewById(R.id.detail_info_unlock2);
        btnUnlock2.setOnClickListener(view -> {
            //TODO: Them xu li khi bam nut UNLOCK2
        });

        findViewById(R.id.app_name).findViewById(R.id.btnNavigation).setOnClickListener(view -> finish());
    }
    @Override
    protected void onResume() {
        super.onResume();
        ViewControllerUtils.hideNavigationBar(getWindow());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(BLEBroadcastManager.devicesInfoBroadReceiver);
    }
}