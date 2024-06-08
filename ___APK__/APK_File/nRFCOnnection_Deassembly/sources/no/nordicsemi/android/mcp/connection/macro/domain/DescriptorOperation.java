package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.util.Log;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.core.Complete;
import org.simpleframework.xml.core.Persist;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

/* loaded from: classes.dex */
public abstract class DescriptorOperation extends CharacteristicOperation {
    private static final String TAG = "DescriptorOperation";

    @Attribute(required = false)
    String descriptorInstanceId;
    private BluetoothGattDescriptor mDescriptor;

    @Attribute
    String uuid;

    public DescriptorOperation() {
        this.descriptorInstanceId = "0";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    @Complete
    public void afterSerialization() {
        super.afterSerialization();
        if (this.descriptorInstanceId == null) {
            this.descriptorInstanceId = "0";
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    @Persist
    public void beforeSerialization() {
        super.beforeSerialization();
        if ("0".equals(this.descriptorInstanceId)) {
            this.descriptorInstanceId = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothGattDescriptor getDescriptor(IBluetoothLeBasicConnection iBluetoothLeBasicConnection) {
        BluetoothGattDescriptor bluetoothGattDescriptor = this.mDescriptor;
        if (bluetoothGattDescriptor != null) {
            return bluetoothGattDescriptor;
        }
        BluetoothGattCharacteristic characteristic = getCharacteristic(iBluetoothLeBasicConnection);
        if (characteristic == null) {
            return null;
        }
        int i = 0;
        for (BluetoothGattDescriptor bluetoothGattDescriptor2 : characteristic.getDescriptors()) {
            if (this.uuid.equalsIgnoreCase(bluetoothGattDescriptor2.getUuid().toString())) {
                if (this.descriptorInstanceId.equals(String.valueOf(i))) {
                    this.mDescriptor = bluetoothGattDescriptor2;
                    return bluetoothGattDescriptor2;
                }
                i++;
            }
        }
        Log.e(TAG, "Descriptor not found");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    public String getName(DatabaseHelper databaseHelper) {
        String descriptorName = databaseHelper.getDescriptorName(UUID.fromString(this.uuid));
        if (descriptorName != null) {
            return descriptorName;
        }
        return "descriptor " + this.uuid;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getParentName(DatabaseHelper databaseHelper) {
        return super.getName(databaseHelper);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation, no.nordicsemi.android.mcp.connection.macro.domain.Operation
    public void invalidate() {
        super.invalidate();
        this.mDescriptor = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    @Validate
    public void validate() {
        super.validate();
        try {
            UUID.fromString(this.uuid);
            try {
                Integer.parseInt(this.descriptorInstanceId);
            } catch (NumberFormatException unused) {
                throw new PersistenceException("The descriptor-instance-id must be a number: " + this.descriptorInstanceId, new Object[0]);
            }
        } catch (IllegalArgumentException unused2) {
            throw new PersistenceException("'%s' is not a valid descriptor UUID", this.uuid);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DescriptorOperation(IBluetoothLeBasicConnection iBluetoothLeBasicConnection, BluetoothGattDescriptor bluetoothGattDescriptor, boolean z) {
        super(iBluetoothLeBasicConnection, bluetoothGattDescriptor.getCharacteristic(), z);
        this.descriptorInstanceId = "0";
        int i = 0;
        for (BluetoothGattDescriptor bluetoothGattDescriptor2 : bluetoothGattDescriptor.getCharacteristic().getDescriptors()) {
            if (bluetoothGattDescriptor2.getUuid().equals(bluetoothGattDescriptor.getUuid())) {
                if (bluetoothGattDescriptor2 == bluetoothGattDescriptor) {
                    this.mDescriptor = bluetoothGattDescriptor;
                    this.uuid = bluetoothGattDescriptor.getUuid().toString();
                    this.descriptorInstanceId = String.valueOf(i);
                    return;
                }
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DescriptorOperation(DescriptorOperation descriptorOperation) {
        super(descriptorOperation);
        this.descriptorInstanceId = "0";
        this.uuid = descriptorOperation.uuid;
        this.descriptorInstanceId = descriptorOperation.descriptorInstanceId;
    }
}
