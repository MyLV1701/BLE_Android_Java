package no.nordicsemi.android.mcp.widget.scanner;

import android.os.Parcel;
import android.os.Parcelable;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.widget.ViewAnimator;

/* loaded from: classes.dex */
public class DeviceViewAnimator extends ViewAnimator {
    public static final Parcelable.Creator<DeviceViewAnimator> CREATOR = new Parcelable.Creator<DeviceViewAnimator>() { // from class: no.nordicsemi.android.mcp.widget.scanner.DeviceViewAnimator.1
        @Override // android.os.Parcelable.Creator
        public DeviceViewAnimator createFromParcel(Parcel parcel) {
            return new DeviceViewAnimator(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public DeviceViewAnimator[] newArray(int i) {
            return new DeviceViewAnimator[i];
        }
    };
    private final Device device;
    private final int deviceIndex;

    @Override // no.nordicsemi.android.mcp.widget.ViewAnimator, no.nordicsemi.android.mcp.widget.ViewSelector, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Device getDevice() {
        return this.device;
    }

    public int getDeviceIndex() {
        return this.deviceIndex;
    }

    @Override // no.nordicsemi.android.mcp.widget.ViewAnimator, no.nordicsemi.android.mcp.widget.ViewSelector, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.device, i);
        parcel.writeInt(this.deviceIndex);
    }

    public DeviceViewAnimator(Device device) {
        super(device.getDeviceHash());
        this.device = device;
        this.deviceIndex = device.getDeviceIndex();
    }

    private DeviceViewAnimator(Parcel parcel) {
        super(parcel);
        this.device = (Device) parcel.readParcelable(Device.class.getClassLoader());
        this.deviceIndex = parcel.readInt();
    }
}
