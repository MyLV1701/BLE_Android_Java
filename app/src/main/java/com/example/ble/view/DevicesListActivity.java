package com.example.ble.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ble.R;
import com.example.ble.model.DevicesModel;
import com.example.ble.view.recycleViews.DevicesListAdapter;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * ② 検知したデバイス一覧画面
 */
public class DevicesListActivity extends AppCompatActivity {
    RecyclerView rvDevices;

    DevicesListAdapter adapter;

    ArrayList<DevicesModel> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices_list);
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
        Log.e("CLICKED_BUTTON", " User is clicked at "+position);
        //TODO: Di chuyen den man hinh detail vs position
        Intent i = new Intent(getApplicationContext(),DevicesInfoActivity.class);
        startActivity(i);
    };
}