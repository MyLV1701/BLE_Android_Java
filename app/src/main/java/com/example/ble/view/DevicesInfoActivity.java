package com.example.ble.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ble.R;

/**
 * 制御画面
 */
public class DevicesInfoActivity extends AppCompatActivity {
    Button btnDisconnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices_info);
         btnDisconnect = findViewById(R.id.detail_info_disconnect);
         btnDisconnect.setOnClickListener(view -> {
             Intent i = new Intent(getApplicationContext(),DevicesListActivity.class);
             startActivity(i);
         });
    }
}