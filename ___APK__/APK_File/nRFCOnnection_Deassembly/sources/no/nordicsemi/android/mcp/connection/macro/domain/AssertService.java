package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.domain.common.HasDescription;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Complete;
import org.simpleframework.xml.core.Persist;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

/* loaded from: classes.dex */
public class AssertService extends HasDescription {

    @ElementList(entry = "assert-characteristic", inline = true, required = false)
    private List<AssertCharacteristic> asserts;

    @Attribute(required = false)
    private String instanceId;

    @Attribute
    private String uuid;

    @Root(strict = false)
    /* loaded from: classes.dex */
    public static class AssertCharacteristic extends HasDescription {

        @ElementListUnion({@ElementList(entry = "assert-descriptor", inline = true, required = false, type = AssertDescriptor.class), @ElementList(entry = "assert-cccd", inline = true, required = false, type = AssertCCCD.class)})
        private List<BaseAssertDescriptor> asserts;

        @Attribute(required = false)
        private String instanceId;

        @ElementList(entry = "property", inline = true, required = false)
        private List<Property> properties;

        @Attribute
        private String uuid;

        @Root(name = "assert-cccd")
        /* loaded from: classes.dex */
        public static class AssertCCCD extends BaseAssertDescriptor {
            private static final String CCCD_UUID = "00002902-0000-1000-8000-00805f9b34fb";

            public AssertCCCD() {
                setDescription("Ensure CCCD");
            }

            @Override // no.nordicsemi.android.mcp.connection.macro.domain.AssertService.AssertCharacteristic.BaseAssertDescriptor
            protected String getInstanceId() {
                return "0";
            }

            @Override // no.nordicsemi.android.mcp.connection.macro.domain.AssertService.AssertCharacteristic.BaseAssertDescriptor
            protected String getUuid() {
                return CCCD_UUID;
            }
        }

        /* loaded from: classes.dex */
        public static abstract class BaseAssertDescriptor extends HasDescription {
            /* JADX INFO: Access modifiers changed from: private */
            public void check(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, List<String> list) {
                String str;
                Iterator<BluetoothGattDescriptor> it = bluetoothGattCharacteristic.getDescriptors().iterator();
                int i = -1;
                while (it.hasNext()) {
                    if (getUuid().equals(it.next().getUuid().toString())) {
                        i++;
                        if (getInstanceId().equals(String.valueOf(i))) {
                            return;
                        }
                    }
                }
                if ("0".equals(getInstanceId())) {
                    str = "";
                } else {
                    str = " with instance ID: " + getInstanceId();
                }
                if (z) {
                    list.add(getDescription() + str + " failed");
                    return;
                }
                list.add(getDescription() + str + " failed on server");
            }

            protected abstract String getInstanceId();

            protected abstract String getUuid();
        }

        @Complete
        private void afterSerialization() {
            if (this.instanceId == null) {
                this.instanceId = "0";
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void assertDescriptor(DatabaseHelper databaseHelper, DescriptorOperation descriptorOperation) {
            for (BaseAssertDescriptor baseAssertDescriptor : this.asserts) {
                if (descriptorOperation.uuid.equals(baseAssertDescriptor.getUuid()) && descriptorOperation.descriptorInstanceId.equals(baseAssertDescriptor.getInstanceId())) {
                    return;
                }
            }
            if (descriptorOperation.uuid.equals("00002902-0000-1000-8000-00805f9b34fb")) {
                this.asserts.add(new AssertCCCD());
            } else {
                this.asserts.add(new AssertDescriptor(databaseHelper, descriptorOperation));
            }
        }

        @Persist
        private void beforeSerialization() {
            if ("0".equals(this.instanceId)) {
                this.instanceId = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void check(BluetoothGattService bluetoothGattService, boolean z, List<String> list) {
            int i = -1;
            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                if (this.uuid.equals(bluetoothGattCharacteristic.getUuid().toString())) {
                    i++;
                    if (this.instanceId.equals(String.valueOf(i))) {
                        int properties = bluetoothGattCharacteristic.getProperties();
                        for (Property property : this.properties) {
                            if ((property.name.mask & properties) == 0 && Property.Requirement.MANDATORY.equals(property.requirement)) {
                                if (z) {
                                    list.add(getDescription() + " has " + property.name.name() + " property failed");
                                } else {
                                    list.add(getDescription() + " on server has " + property.name.name() + " property failed");
                                }
                            }
                            if ((property.name.mask & properties) > 0 && Property.Requirement.EXCLUDED.equals(property.requirement)) {
                                list.add(getDescription() + " on server without " + property.name.name() + " property failed");
                            }
                        }
                        Iterator<BaseAssertDescriptor> it = this.asserts.iterator();
                        while (it.hasNext()) {
                            it.next().check(bluetoothGattCharacteristic, z, list);
                        }
                        return;
                    }
                }
            }
            String str = "0".equals(this.instanceId) ? "" : " with instance ID: " + this.instanceId;
            if (z) {
                list.add(getDescription() + str + " failed");
                return;
            }
            list.add(getDescription() + str + " failed on server");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateProperties(CharacteristicOperation characteristicOperation) {
            Property requiredProperty = characteristicOperation.getRequiredProperty();
            if (requiredProperty != null) {
                this.properties.remove(requiredProperty);
                this.properties.add(requiredProperty);
            }
        }

        @Validate
        private void validate() {
            try {
                UUID.fromString(this.uuid);
            } catch (IllegalArgumentException unused) {
                throw new PersistenceException("'%s' is not a valid characteristic UUID", this.uuid);
            }
        }

        @Root
        /* loaded from: classes.dex */
        public static class AssertDescriptor extends BaseAssertDescriptor {

            @Attribute(required = false)
            private String instanceId;

            @Attribute
            private String uuid;

            public AssertDescriptor() {
                this.instanceId = "0";
            }

            @Complete
            private void afterSerialization() {
                if (this.instanceId == null) {
                    this.instanceId = "0";
                }
            }

            @Persist
            private void beforeSerialization() {
                if ("0".equals(this.instanceId)) {
                    this.instanceId = null;
                }
            }

            @Override // no.nordicsemi.android.mcp.connection.macro.domain.AssertService.AssertCharacteristic.BaseAssertDescriptor
            protected String getInstanceId() {
                return this.instanceId;
            }

            @Override // no.nordicsemi.android.mcp.connection.macro.domain.AssertService.AssertCharacteristic.BaseAssertDescriptor
            protected String getUuid() {
                return this.uuid;
            }

            AssertDescriptor(DatabaseHelper databaseHelper, DescriptorOperation descriptorOperation) {
                this.instanceId = "0";
                this.uuid = descriptorOperation.uuid;
                this.instanceId = descriptorOperation.descriptorInstanceId;
                String descriptorName = databaseHelper.getDescriptorName(UUID.fromString(this.uuid));
                StringBuilder sb = new StringBuilder();
                sb.append("Ensure ");
                if (descriptorName == null) {
                    descriptorName = this.uuid + " descriptor";
                }
                sb.append(descriptorName);
                setDescription(sb.toString());
            }
        }

        @Root
        /* loaded from: classes.dex */
        public static class Property {

            @Attribute
            private Name name;

            @Attribute(required = false)
            private Requirement requirement = Requirement.MANDATORY;

            /* loaded from: classes.dex */
            public enum Name {
                BROADCAST(1),
                READ(2),
                WRITE(8),
                WRITE_WITHOUT_RESPONSE(4),
                NOTIFY(16),
                INDICATE(32),
                SIGNED_WRITE(64),
                EXTENDED_PROPERTIES(128);

                private int mask;

                Name(int i) {
                    this.mask = i;
                }
            }

            /* loaded from: classes.dex */
            public enum Requirement {
                MANDATORY,
                EXCLUDED,
                OPTIONAL
            }

            public Property() {
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Property) && this.name.mask == ((Property) obj).name.mask;
            }

            public Property(Name name) {
                this.name = name;
            }
        }

        private AssertCharacteristic() {
            this.instanceId = "0";
            this.properties = new ArrayList(4);
            this.asserts = new ArrayList(2);
        }

        private AssertCharacteristic(DatabaseHelper databaseHelper, CharacteristicOperation characteristicOperation) {
            this.instanceId = "0";
            this.properties = new ArrayList(4);
            this.asserts = new ArrayList(2);
            this.uuid = characteristicOperation.characteristicUuid;
            this.instanceId = characteristicOperation.characteristicInstanceId;
            String characteristicName = databaseHelper.getCharacteristicName(UUID.fromString(this.uuid));
            StringBuilder sb = new StringBuilder();
            sb.append("Ensure ");
            if (characteristicName == null) {
                characteristicName = this.uuid + " characteristic";
            }
            sb.append(characteristicName);
            setDescription(sb.toString());
            updateProperties(characteristicOperation);
        }
    }

    public AssertService() {
        this.instanceId = "0";
        this.asserts = new ArrayList(4);
    }

    @Complete
    private void afterSerialization() {
        if (this.instanceId == null) {
            this.instanceId = "0";
        }
    }

    @Persist
    private void beforeSerialization() {
        if ("0".equals(this.instanceId)) {
            this.instanceId = null;
        }
    }

    private AssertCharacteristic getOrCreateAssertCharacteristic(DatabaseHelper databaseHelper, CharacteristicOperation characteristicOperation) {
        for (AssertCharacteristic assertCharacteristic : this.asserts) {
            if (assertCharacteristic.uuid.equals(characteristicOperation.characteristicUuid) && assertCharacteristic.instanceId.equals(characteristicOperation.characteristicInstanceId)) {
                assertCharacteristic.updateProperties(characteristicOperation);
                return assertCharacteristic;
            }
        }
        AssertCharacteristic assertCharacteristic2 = new AssertCharacteristic(databaseHelper, characteristicOperation);
        this.asserts.add(assertCharacteristic2);
        return assertCharacteristic2;
    }

    @Validate
    private void validate() {
        try {
            UUID.fromString(this.uuid);
        } catch (IllegalArgumentException unused) {
            throw new PersistenceException("'%s' is not a valid service UUID", this.uuid);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void assertOperation(DatabaseHelper databaseHelper, CharacteristicOperation characteristicOperation) {
        AssertCharacteristic orCreateAssertCharacteristic = getOrCreateAssertCharacteristic(databaseHelper, characteristicOperation);
        if (characteristicOperation instanceof DescriptorOperation) {
            orCreateAssertCharacteristic.assertDescriptor(databaseHelper, (DescriptorOperation) characteristicOperation);
        }
    }

    public void check(IBluetoothLeBasicConnection iBluetoothLeBasicConnection, boolean z, List<String> list) {
        int i = -1;
        for (BluetoothGattService bluetoothGattService : z ? iBluetoothLeBasicConnection.getSupportedGattServices() : iBluetoothLeBasicConnection.getServerGattServices(true)) {
            if (this.uuid.equals(bluetoothGattService.getUuid().toString())) {
                i++;
                if (this.instanceId.equals(String.valueOf(i))) {
                    Iterator<AssertCharacteristic> it = this.asserts.iterator();
                    while (it.hasNext()) {
                        it.next().check(bluetoothGattService, z, list);
                    }
                    return;
                }
            }
        }
        String str = "0".equals(this.instanceId) ? "" : " with instance ID: " + this.instanceId;
        if (z) {
            list.add(getDescription() + str + " failed");
            return;
        }
        list.add(getDescription() + str + " failed on server");
    }

    public boolean matches(CharacteristicOperation characteristicOperation) {
        return this.uuid.equals(characteristicOperation.serviceUuid) && this.instanceId.equals(characteristicOperation.serviceInstanceId);
    }

    public AssertService(DatabaseHelper databaseHelper, CharacteristicOperation characteristicOperation) {
        this.instanceId = "0";
        this.asserts = new ArrayList(4);
        this.uuid = characteristicOperation.serviceUuid;
        this.instanceId = characteristicOperation.serviceInstanceId;
        String serviceName = databaseHelper.getServiceName(UUID.fromString(this.uuid));
        StringBuilder sb = new StringBuilder();
        sb.append("Ensure ");
        if (serviceName == null) {
            serviceName = this.uuid + " service";
        }
        sb.append(serviceName);
        setDescription(sb.toString());
        assertOperation(databaseHelper, characteristicOperation);
    }
}
