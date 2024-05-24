package com.application.ble.model;

public class DevicesModel {

    public DevicesModel(int id) {
        this.id = id;
        this.name = "BLE" + id;
        connectStatus = ConnectStatus.UNKNOWN;
    }

    int id;
    String name;
    ConnectStatus connectStatus;

    public String getName() {
        return name;
    }

    public ConnectStatus getConnectStatus() {
        return connectStatus;
    }
}
