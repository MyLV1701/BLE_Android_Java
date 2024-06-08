package no.nordicsemi.android.ble.callback.profile;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;
import no.nordicsemi.android.ble.data.Data;
import no.nordicsemi.android.ble.response.ReadResponse;

/* loaded from: classes.dex */
public class ProfileReadResponse extends ReadResponse implements ProfileDataCallback, Parcelable {
    public static final Parcelable.Creator<ProfileReadResponse> CREATOR = new Parcelable.Creator<ProfileReadResponse>() { // from class: no.nordicsemi.android.ble.callback.profile.ProfileReadResponse.1
        @Override // android.os.Parcelable.Creator
        public ProfileReadResponse createFromParcel(Parcel parcel) {
            return new ProfileReadResponse(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ProfileReadResponse[] newArray(int i) {
            return new ProfileReadResponse[i];
        }
    };
    private boolean valid;

    public ProfileReadResponse() {
        this.valid = true;
    }

    public boolean isValid() {
        return this.valid;
    }

    @Override // no.nordicsemi.android.ble.callback.profile.ProfileDataCallback
    public void onInvalidDataReceived(BluetoothDevice bluetoothDevice, Data data) {
        this.valid = false;
    }

    @Override // no.nordicsemi.android.ble.response.ReadResponse, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeByte(this.valid ? (byte) 1 : (byte) 0);
    }

    protected ProfileReadResponse(Parcel parcel) {
        super(parcel);
        this.valid = true;
        this.valid = parcel.readByte() != 0;
    }
}
