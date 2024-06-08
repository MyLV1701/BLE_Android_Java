package io.runtime.mcumgr.ble.callback;

import io.runtime.mcumgr.McuMgrScheme;
import io.runtime.mcumgr.response.McuMgrResponse;
import java.io.IOException;
import no.nordicsemi.android.ble.data.DataMerger;
import no.nordicsemi.android.ble.data.DataStream;

/* loaded from: classes.dex */
public class SmpMerger implements DataMerger {
    private Integer mExpectedLength;

    @Override // no.nordicsemi.android.ble.data.DataMerger
    public boolean merge(DataStream dataStream, byte[] bArr, int i) {
        dataStream.write(bArr);
        if (i == 0) {
            if (bArr == null) {
                return true;
            }
            try {
                this.mExpectedLength = Integer.valueOf(McuMgrResponse.getExpectedLength(McuMgrScheme.BLE, bArr));
            } catch (IOException unused) {
                return true;
            }
        }
        return dataStream.size() == this.mExpectedLength.intValue();
    }
}
