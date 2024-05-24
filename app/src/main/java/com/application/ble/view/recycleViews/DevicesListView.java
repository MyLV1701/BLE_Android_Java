package com.application.ble.view.recycleViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.application.ble.R;


public class DevicesListView extends LinearLayout {
    public DevicesListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.devices_view_row,this);
    }
}
