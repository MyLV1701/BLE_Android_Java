package no.nordicsemi.android.mcp.advertiser.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.gap.CompleteLocalNameParser;
import no.nordicsemi.android.mcp.ble.parser.gap.TxPowerLevelParser;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
public class Item implements Parcelable {
    public static final int COMPLETE_LOCAL_NAME = 9;
    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() { // from class: no.nordicsemi.android.mcp.advertiser.model.Item.1
        @Override // android.os.Parcelable.Creator
        public Item createFromParcel(Parcel parcel) {
            return new Item(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public Item[] newArray(int i) {
            return new Item[i];
        }
    };
    public static final int MANUFACTURER_SPECIFIC_DATA = 255;
    public static final int SERVICES_COMPLETE_LIST_128_BIT = 7;
    public static final int SERVICES_COMPLETE_LIST_16_BIT = 3;
    public static final int SERVICES_COMPLETE_LIST_32_BIT = 5;
    public static final int SERVICE_DATA_128_BIT = 33;
    public static final int SERVICE_DATA_16_BIT = 22;
    public static final int SERVICE_DATA_32_BIT = 32;
    public static final int TX_POWER_LEVEL = 10;
    private byte[] data;
    private String displayName;
    private boolean editable;
    private int eirType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public byte[] getRawData() {
        return this.data;
    }

    public int getType() {
        return this.eirType;
    }

    public boolean isEditable() {
        return this.editable;
    }

    public void setData(int i, byte[] bArr) {
        this.data = bArr;
        this.eirType = i & 255;
        this.editable = (i == 9 || i == 10) ? false : true;
        if (i == 3) {
            this.displayName = String.format(Locale.US, "16-bit Service UUID: 0x%04X", Integer.valueOf(ParserUtils.decodeUuid16(bArr, 0)));
            return;
        }
        if (i == 5) {
            this.displayName = String.format(Locale.US, "32-bit Service UUID: 0x%08X", Integer.valueOf(ParserUtils.decodeUuid32(bArr, 0)));
            return;
        }
        if (i == 7) {
            this.displayName = "128-bit Service UUID: " + ParserUtils.decodeUuid128(bArr, 0).toString();
            return;
        }
        if (i == 22) {
            this.displayName = String.format(Locale.US, "Service Data:\nUUID: 0x%04X Data: %s", Integer.valueOf(ParserUtils.decodeUuid16(bArr, 0)), ParserUtils.bytesToHex(bArr, 2, bArr.length - 2, true));
            return;
        }
        if (i == 255) {
            this.displayName = String.format(Locale.US, "Manufacturer Data:\nCompany ID: 0x%04X Data: %s", Integer.valueOf(ParserUtils.decodeUuid16(bArr, 0)), ParserUtils.bytesToHex(bArr, 2, bArr.length - 2, true));
            return;
        }
        if (i == 9) {
            this.displayName = CompleteLocalNameParser.COMPLETE_LOCAL_NAME;
            return;
        }
        if (i == 10) {
            this.displayName = TxPowerLevelParser.TX_POWER_LEVEL;
        } else if (i == 32) {
            this.displayName = String.format(Locale.US, "Service Data:\nUUID: 0x%08X Data: %s", Integer.valueOf(ParserUtils.decodeUuid32(bArr, 0)), ParserUtils.bytesToHex(bArr, 4, bArr.length - 4, true));
        } else {
            if (i != 33) {
                return;
            }
            this.displayName = String.format(Locale.US, "Service Data:\nUUID: %s Data: %s", ParserUtils.decodeUuid128(bArr, 0).toString(), ParserUtils.bytesToHex(bArr, 16, bArr.length - 16, true));
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.displayName);
        parcel.writeByteArray(this.data);
        parcel.writeInt(this.eirType);
        parcel.writeInt(this.editable ? 1 : 0);
    }

    public Item(int i, byte[] bArr) {
        setData(i, bArr);
    }

    private Item(Parcel parcel) {
        this.displayName = parcel.readString();
        this.data = parcel.createByteArray();
        this.eirType = parcel.readInt();
        this.editable = parcel.readInt() == 1;
    }
}
