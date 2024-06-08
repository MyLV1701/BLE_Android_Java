package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothGattDescriptor;
import android.text.TextUtils;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

/* loaded from: classes.dex */
public abstract class DescriptorOperationWithValue extends DescriptorOperation {
    protected byte[] bytes;

    @Attribute(required = false)
    protected String value;

    @Attribute(required = false)
    protected String valueString;

    public DescriptorOperationWithValue() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AssertValue createValueAssert() {
        if (!TextUtils.isEmpty(this.value)) {
            return new AssertValue("Assert value equals 0x" + this.value, this.value);
        }
        if (!TextUtils.isEmpty(this.valueString)) {
            return new AssertValue("Assert value equals '" + this.valueString + "'", this.valueString, true);
        }
        return new AssertValue("Assert value is empty", "");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.DescriptorOperation, no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    @Validate
    public void validate() {
        super.validate();
        if (this.value == null && this.valueString == null) {
            throw new PersistenceException("No value specified for send-indication. Use 'value' for binary data or 'value-string' for text.", new Object[0]);
        }
        if (this.value != null && this.valueString != null) {
            throw new PersistenceException("Both value-string and value specified for send-indication", new Object[0]);
        }
        String str = this.value;
        if (str != null) {
            this.bytes = hexStringToByteArray(str);
        } else {
            this.bytes = this.valueString.getBytes();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DescriptorOperationWithValue(IBluetoothLeBasicConnection iBluetoothLeBasicConnection, BluetoothGattDescriptor bluetoothGattDescriptor, boolean z) {
        super(iBluetoothLeBasicConnection, bluetoothGattDescriptor, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DescriptorOperationWithValue(DescriptorOperationWithValueAssertion descriptorOperationWithValueAssertion) {
        super(descriptorOperationWithValueAssertion);
        try {
            this.value = descriptorOperationWithValueAssertion.assertValue.value;
            this.valueString = descriptorOperationWithValueAssertion.assertValue.valueString;
            validate();
        } catch (PersistenceException unused) {
        }
    }
}
