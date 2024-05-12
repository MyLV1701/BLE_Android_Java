package com.example.ble.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BLEBroadcastManager {

    public static final BroadcastReceiver scanBroadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //TODO: Action when receiving anything in Scan Screen
        }
    };
    public static final BroadcastReceiver devicesListBroadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //TODO: Action when receiving anything in Devices List Screen
        }
    };
    public static final BroadcastReceiver devicesInfoBroadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //TODO: Action when receiving anything in Devices Info Screen
        }
    };
}
