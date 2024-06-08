package io.runtime.mcumgr.image.tlv;

import io.runtime.mcumgr.exception.McuMgrException;
import io.runtime.mcumgr.util.ByteUtil;
import io.runtime.mcumgr.util.Endian;
import java.util.Arrays;

/* loaded from: classes.dex */
public class McuMgrImageTlvTrailerEntry {
    public final short length;
    public final byte type;
    public final byte[] value;

    private McuMgrImageTlvTrailerEntry(byte b2, short s, byte[] bArr) {
        this.type = b2;
        this.length = s;
        this.value = bArr;
    }

    public static McuMgrImageTlvTrailerEntry fromBytes(byte[] bArr, int i) {
        if (getMinSize() + i <= bArr.length) {
            int i2 = i + 1;
            byte b2 = bArr[i];
            int i3 = i2 + 1;
            short byteArrayToUnsignedInt = (short) ByteUtil.byteArrayToUnsignedInt(bArr, i3, Endian.LITTLE, 2);
            int i4 = i3 + 2;
            return new McuMgrImageTlvTrailerEntry(b2, byteArrayToUnsignedInt, Arrays.copyOfRange(bArr, i4, i4 + byteArrayToUnsignedInt));
        }
        throw new McuMgrException("The byte array is too short to be a McuMgrImageTlvTrailerEntry");
    }

    public static int getMinSize() {
        return 4;
    }

    public int getEntryLength() {
        return getMinSize() + this.length;
    }
}
