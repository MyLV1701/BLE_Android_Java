package no.nordicsemi.android.mcp.test.domain;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import java.util.Iterator;
import java.util.UUID;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.mcp.domain.common.exception.SyntaxException;
import no.nordicsemi.android.mcp.test.ConstantsManager;
import no.nordicsemi.android.mcp.test.Result;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

@Root
/* loaded from: classes.dex */
public abstract class CommOperation extends TimeoutOperation {

    @Attribute(required = false)
    private String characteristicInstanceId;

    @Attribute
    private String characteristicUuid;

    @Attribute(required = false)
    private String serviceInstanceId;

    @Attribute
    private String serviceUuid;

    public CommOperation(@Attribute(name = "description") String str) {
        super(str);
        this.serviceInstanceId = "0";
        this.characteristicInstanceId = "0";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BluetoothGattCharacteristic getCharacteristic(Context context, ConstantsManager constantsManager, Result result, LogSession logSession) {
        BluetoothGattCharacteristic bluetoothGattCharacteristic;
        String decode = constantsManager.decode(this.serviceUuid);
        String decode2 = constantsManager.decode(this.serviceInstanceId);
        try {
            int parseInt = Integer.parseInt(decode2);
            String decode3 = constantsManager.decode(this.characteristicUuid);
            String decode4 = constantsManager.decode(this.characteristicInstanceId);
            try {
                int parseInt2 = Integer.parseInt(decode4);
                BluetoothGattService service = getTarget().getService(decode, parseInt);
                if (service == null) {
                    logFail(result, logSession, "Service (uuid=" + decode + ", instance-id=" + parseInt + ") not found");
                    return null;
                }
                int i = 0;
                Iterator<BluetoothGattCharacteristic> it = service.getCharacteristics().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        bluetoothGattCharacteristic = null;
                        break;
                    }
                    bluetoothGattCharacteristic = it.next();
                    if (decode3.equalsIgnoreCase(bluetoothGattCharacteristic.getUuid().toString())) {
                        if (parseInt2 == i) {
                            break;
                        }
                        i++;
                    }
                }
                if (bluetoothGattCharacteristic != null) {
                    return bluetoothGattCharacteristic;
                }
                logFail(result, logSession, "Characteristic (uuid=" + decode3 + ", instance-id=" + parseInt2 + ") not found");
                return null;
            } catch (NumberFormatException unused) {
                throw new SyntaxException("The characteristic-instance-id must be a number: " + decode4);
            }
        } catch (NumberFormatException unused2) {
            throw new SyntaxException("The service-instance-id must be a number: " + decode2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] hexStringToByteArray(String str) {
        int length = str.length();
        if (length % 2 != 1) {
            byte[] bArr = new byte[length / 2];
            for (int i = 0; i < length; i += 2) {
                int digit = Character.digit(str.charAt(i), 16);
                int digit2 = Character.digit(str.charAt(i + 1), 16);
                if (digit != -1 && digit2 != -1) {
                    bArr[i / 2] = (byte) ((digit << 4) + digit2);
                } else {
                    throw new PersistenceException("'%s' is not a valid byte array", str);
                }
            }
            return bArr;
        }
        throw new PersistenceException("The byte array '%s' must have even number of characters", str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.common.HasExpectedAndDescription
    @Validate
    public void validate() {
        super.validate();
        if (!this.serviceUuid.matches(ConstantsManager.CONSTANT_REGEX)) {
            try {
                UUID.fromString(this.serviceUuid);
            } catch (IllegalArgumentException unused) {
                throw new PersistenceException("'%s' is not a valid service UUID", this.serviceUuid);
            }
        }
        if (this.characteristicUuid.matches(ConstantsManager.CONSTANT_REGEX)) {
            return;
        }
        try {
            UUID.fromString(this.characteristicUuid);
        } catch (IllegalArgumentException unused2) {
            throw new PersistenceException("'%s' is not a valid characteristic UUID", this.characteristicUuid);
        }
    }
}
