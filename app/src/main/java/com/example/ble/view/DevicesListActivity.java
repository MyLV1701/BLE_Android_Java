package com.example.ble.view;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ble.R;
import com.example.ble.controller.BLEBroadcastManager;
import com.example.ble.model.DevicesModel;
import com.example.ble.view.recycleViews.DevicesListAdapter;
import com.example.ble.view.utils.ViewControllerUtils;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * ② 検知したデバイス一覧画面
 */
public class DevicesListActivity extends AppCompatActivity {
    final String DEVICES_LIST_LOCAL_BROADCAST = "devices-list-action-local-broadcast";

    RecyclerView rvDevices;

    DevicesListAdapter adapter;

    ArrayList<DevicesModel> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices_list);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(BLEBroadcastManager.devicesListBroadReceiver, new IntentFilter(DEVICES_LIST_LOCAL_BROADCAST));
        rvDevices = (RecyclerView) findViewById(R.id.rvDevices);
        rvDevices.setHasFixedSize(true);
        rvDevices.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        for (int i = 0; i < 100; i++) {
            list.add(new DevicesModel(i));
        }
        adapter = new DevicesListAdapter(list, listener);
        rvDevices.setAdapter(adapter);
    }

    public void setId(int id){
        adapter.setId(id);
        rvDevices.scrollToPosition(id);
    }
    public final Consumer<Integer> listener = (position)-> {
        Log.d("CLICKED_BUTTON", " User is clicked at "+position);
        //Di chuyen den man hinh Device Info with ID
        Intent i = new Intent(getApplicationContext(),DevicesInfoActivity.class);
        i.putExtra("id","BLE "+position);
        startActivity(i);

        //TODO: Them xu li khi bam nut CONNECT
    };

    @Override
    protected void onResume() {
        super.onResume();
        ViewControllerUtils.hideNavigationBar(getWindow());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(BLEBroadcastManager.devicesListBroadReceiver);
    }
}