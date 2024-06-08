package no.nordicsemi.android.mcp.widget.bonded;

import android.os.Parcel;
import android.os.Parcelable;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.widget.ViewSelector;

/* loaded from: classes.dex */
public class DeviceViewSelector extends ViewSelector {
    public static final Parcelable.Creator<DeviceViewSelector> CREATOR = new Parcelable.Creator<DeviceViewSelector>() { // from class: no.nordicsemi.android.mcp.widget.bonded.DeviceViewSelector.1
        @Override // android.os.Parcelable.Creator
        public DeviceViewSelector createFromParcel(Parcel parcel) {
            return new DeviceViewSelector(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public DeviceViewSelector[] newArray(int i) {
            return new DeviceViewSelector[i];
        }
    };
    private final Device device;

    public DeviceViewSelector(Device device) {
        super(device.getDeviceHash());
        this.device = device;
    }

    @Override // no.nordicsemi.android.mcp.widget.ViewSelector, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Device getDevice() {
        return this.device;
    }

    @Override // no.nordicsemi.android.mcp.widget.ViewSelector, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.device, i);
    }

    public DeviceViewSelector(Parcel parcel) {
        super(parcel);
        this.device = (Device) parcel.readParcelable(Device.class.getClassLoader());
    }
}
