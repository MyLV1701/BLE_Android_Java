package com.application.ble.view.recycleViews;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.application.ble.R;


public class DevicesListHolder extends RecyclerView.ViewHolder {

    TextView txtDevicesName;
    Button btnConnect;

    ConstraintLayout layout;
    public DevicesListHolder(@NonNull View itemView) {
        super(itemView);
        layout = itemView.findViewById(R.id.devicesRow);
        txtDevicesName = itemView.findViewById(R.id.devicesName);
        btnConnect = itemView.findViewById(R.id.connectStatus);
    }
}
