package no.nordicsemi.android.mcp.test.domain.command;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;
import java.util.Iterator;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.domain.common.exception.SyntaxException;
import no.nordicsemi.android.mcp.test.ConstantsManager;
import no.nordicsemi.android.mcp.test.Result;
import no.nordicsemi.android.mcp.test.domain.CommOperation;
import no.nordicsemi.android.mcp.test.domain.common.AssertValue;
import no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
/* loaded from: classes.dex */
public class ReadDescriptor extends CommOperation {

    @Element(required = false)
    private AssertValue assertValue;

    @Attribute(required = false)
    private String descriptorInstanceId;

    @Attribute
    private String uuid;

    public ReadDescriptor() {
        super("Read-Descriptor");
        this.descriptorInstanceId = "0";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public OperationResult execute(Context context, ConstantsManager constantsManager, Result result, LogSession logSession) {
        BluetoothGattCharacteristic characteristic = getCharacteristic(context, constantsManager, result, logSession);
        if (characteristic == null) {
            return toResult(-3);
        }
        String decode = constantsManager.decode(this.uuid);
        String decode2 = constantsManager.decode(this.descriptorInstanceId);
        try {
            int parseInt = Integer.parseInt(decode2);
            BluetoothGattDescriptor bluetoothGattDescriptor = null;
            int i = 0;
            Iterator<BluetoothGattDescriptor> it = characteristic.getDescriptors().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BluetoothGattDescriptor next = it.next();
                if (decode.equalsIgnoreCase(next.getUuid().toString())) {
                    if (parseInt == i) {
                        bluetoothGattDescriptor = next;
                        break;
                    }
                    i++;
                }
            }
            if (bluetoothGattDescriptor == null) {
                logFail(result, logSession, "Descriptor (uuid=" + decode + ", instance-id=" + parseInt + ") not found");
                return toResult(-3);
            }
            int read = getTarget().read(bluetoothGattDescriptor, logSession);
            if (read != 0) {
                logFail(result, logSession, "Reading descriptor failed");
                return toResult(read);
            }
            logSuccess(result, logSession, "Reading descriptor succeeded");
            if (this.assertValue != null) {
                Logger.a(logSession, constantsManager.decode(this.assertValue.getDescription()) + "...");
                return this.assertValue.execute(context, bluetoothGattDescriptor, constantsManager, result, logSession);
            }
            return toResult(read);
        } catch (NumberFormatException unused) {
            throw new SyntaxException("The descriptor-instance-id must be a number: " + decode2);
        }
    }

    public ReadDescriptor(@Attribute(name = "description") String str) {
        super(str);
        this.descriptorInstanceId = "0";
    }
}
