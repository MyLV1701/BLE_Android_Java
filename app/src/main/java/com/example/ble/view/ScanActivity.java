package com.example.ble.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ble.R;

/**
 * ① 起動画面
 */
public class ScanActivity extends AppCompatActivity {
    Button btnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        btnScan = (Button) findViewById(R.id.btn_scan);
        btnScan.setOnClickListener(view -> {
            //Di chuyen den man hinh Device List
            Intent i = new Intent(getApplicationContext(),DevicesListActivity.class);
            startActivity(i);
        });
    }
}