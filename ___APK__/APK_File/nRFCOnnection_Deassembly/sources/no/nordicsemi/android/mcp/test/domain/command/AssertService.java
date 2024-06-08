package no.nordicsemi.android.mcp.test.domain.command;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.ContentValues;
import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.domain.common.exception.SyntaxException;
import no.nordicsemi.android.mcp.test.ConstantsManager;
import no.nordicsemi.android.mcp.test.Result;
import no.nordicsemi.android.mcp.test.domain.Operation;
import no.nordicsemi.android.mcp.test.domain.common.AssertValue;
import no.nordicsemi.android.mcp.test.domain.common.HasExpectedAndDescription;
import no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;

@Root
/* loaded from: classes.dex */
public class AssertService extends Operation {

    @ElementList(entry = "assert-characteristic", inline = true, required = false)
    private List<AssertCharacteristic> asserts;

    @Attribute(required = false)
    private String instanceId;

    @Attribute
    private String uuid;

    @Root(strict = false)
    /* loaded from: classes.dex */
    public static class AssertCharacteristic extends HasExpectedAndDescription {

        @Element(required = false)
        private AssertValue assertValue;

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
                super("Assert-CCCD");
            }

            @Override // no.nordicsemi.android.mcp.test.domain.command.AssertService.AssertCharacteristic.BaseAssertDescriptor
            protected String getInstanceId() {
                return "0";
            }

            @Override // no.nordicsemi.android.mcp.test.domain.command.AssertService.AssertCharacteristic.BaseAssertDescriptor
            protected String getUuid() {
                return CCCD_UUID;
            }

            public AssertCCCD(@Attribute(name = "description") String str) {
                super(str);
            }
        }

        /* loaded from: classes.dex */
        public static abstract class BaseAssertDescriptor extends HasExpectedAndDescription {

            @Element(required = false)
            private AssertValue assertValue;

            public BaseAssertDescriptor() {
                super("Assert-Descriptor");
            }

            protected OperationResult execute(Context context, BluetoothGattCharacteristic bluetoothGattCharacteristic, ConstantsManager constantsManager, Result result, LogSession logSession, List<ContentValues> list) {
                BluetoothGattDescriptor bluetoothGattDescriptor;
                String decode = constantsManager.decode(getUuid());
                String decode2 = constantsManager.decode(getInstanceId());
                try {
                    int parseInt = Integer.parseInt(decode2);
                    list.add(Logger.logEntry(logSession, 1, "Reading descriptors..."));
                    Iterator<BluetoothGattDescriptor> it = bluetoothGattCharacteristic.getDescriptors().iterator();
                    int i = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            bluetoothGattDescriptor = null;
                            break;
                        }
                        bluetoothGattDescriptor = it.next();
                        if (decode.equalsIgnoreCase(bluetoothGattDescriptor.getUuid().toString())) {
                            if (parseInt == i) {
                                break;
                            }
                            i++;
                        }
                    }
                    if (bluetoothGattDescriptor == null) {
                        logFail(result, logSession, "Descriptor (uuid=" + decode + ", instance-id=" + parseInt + ") not found", list);
                        return toResult(-4);
                    }
                    logSuccess(result, logSession, "Descriptor (uuid=" + decode + ", instance-id=" + parseInt + ") found", list);
                    if (this.assertValue != null) {
                        list.add(Logger.logEntry(logSession, 10, constantsManager.decode(this.assertValue.getDescription()) + "..."));
                        return this.assertValue.execute(context, bluetoothGattDescriptor, constantsManager, result, logSession);
                    }
                    return toResult(0);
                } catch (NumberFormatException unused) {
                    throw new SyntaxException("The descriptor-instance-id must be a number: " + decode2);
                }
            }

            protected abstract String getInstanceId();

            protected abstract String getUuid();

            public BaseAssertDescriptor(@Attribute(name = "description") String str) {
                super(str);
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
        }

        public AssertCharacteristic() {
            super("Assert-Characteristic");
            this.instanceId = "0";
        }

        protected OperationResult execute(Context context, BluetoothGattService bluetoothGattService, ConstantsManager constantsManager, Result result, LogSession logSession, List<ContentValues> list) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic;
            int i;
            boolean z;
            String decode = constantsManager.decode(this.uuid);
            String decode2 = constantsManager.decode(this.instanceId);
            try {
                int parseInt = Integer.parseInt(decode2);
                list.add(Logger.logEntry(logSession, 1, "Reading characteristics..."));
                Iterator<BluetoothGattCharacteristic> it = bluetoothGattService.getCharacteristics().iterator();
                int i2 = 0;
                while (true) {
                    if (!it.hasNext()) {
                        bluetoothGattCharacteristic = null;
                        break;
                    }
                    BluetoothGattCharacteristic next = it.next();
                    if (decode.equalsIgnoreCase(next.getUuid().toString())) {
                        if (parseInt == i2) {
                            bluetoothGattCharacteristic = next;
                            break;
                        }
                        i2++;
                    }
                }
                if (bluetoothGattCharacteristic == null) {
                    logFail(result, logSession, "Characteristic (uuid=" + decode + ", instance-id=" + parseInt + ") not found", list);
                    return toResult(-4);
                }
                logSuccess(result, logSession, "Characteristic (uuid=" + decode + ", instance-id=" + parseInt + ") found", list);
                List<Property> list2 = this.properties;
                if (list2 != null && !list2.isEmpty()) {
                    list.add(Logger.logEntry(logSession, 1, "Reading properties..."));
                    int properties = bluetoothGattCharacteristic.getProperties();
                    for (Property property : this.properties) {
                        if (Property.Requirement.MANDATORY.equals(property.requirement)) {
                            if ((property.name.mask & properties) == 0) {
                                logFail(result, logSession, "Required property " + property.name + " not found", list);
                                return toResult(-4);
                            }
                            logSuccess(result, logSession, "Required property " + property.name + " found", list);
                        }
                        if (Property.Requirement.EXCLUDED.equals(property.requirement)) {
                            if ((property.name.mask & properties) > 0) {
                                logFail(result, logSession, "Excluded property " + property.name + " found", list);
                                return toResult(-4);
                            }
                            logSuccess(result, logSession, "Excluded property " + property.name + " not found", list);
                        }
                    }
                }
                List<BaseAssertDescriptor> list3 = this.asserts;
                int i3 = 10;
                if (list3 == null || list3.isEmpty()) {
                    i = 10;
                    z = false;
                } else {
                    z = false;
                    for (BaseAssertDescriptor baseAssertDescriptor : this.asserts) {
                        list.add(Logger.logEntry(logSession, i3, constantsManager.decode(baseAssertDescriptor.getDescription()) + "..."));
                        OperationResult execute = baseAssertDescriptor.execute(context, bluetoothGattCharacteristic, constantsManager, result, logSession, list);
                        if (OperationResult.WARNING.equals(execute)) {
                            z = true;
                        } else if (OperationResult.FAIL.equals(execute)) {
                            return execute;
                        }
                        i3 = 10;
                    }
                    i = 10;
                }
                if (this.assertValue != null) {
                    list.add(Logger.logEntry(logSession, i, constantsManager.decode(this.assertValue.getDescription()) + "..."));
                    OperationResult execute2 = this.assertValue.execute(context, bluetoothGattCharacteristic, constantsManager, result, logSession);
                    if (OperationResult.WARNING.equals(execute2)) {
                        z = true;
                    } else if (OperationResult.FAIL.equals(execute2)) {
                        return execute2;
                    }
                }
                if (z) {
                    return OperationResult.WARNING;
                }
                return toResult(0);
            } catch (NumberFormatException unused) {
                throw new SyntaxException("The instance-id must be a number: " + decode2);
            }
        }

        @Root
        /* loaded from: classes.dex */
        public static class AssertDescriptor extends BaseAssertDescriptor {

            @Attribute(required = false)
            private String instanceId;

            @Attribute
            protected String uuid;

            public AssertDescriptor() {
                this.instanceId = "0";
            }

            @Override // no.nordicsemi.android.mcp.test.domain.command.AssertService.AssertCharacteristic.BaseAssertDescriptor
            protected String getInstanceId() {
                return this.instanceId;
            }

            @Override // no.nordicsemi.android.mcp.test.domain.command.AssertService.AssertCharacteristic.BaseAssertDescriptor
            protected String getUuid() {
                return this.uuid;
            }

            public AssertDescriptor(@Attribute(name = "description") String str) {
                super(str);
                this.instanceId = "0";
            }
        }

        public AssertCharacteristic(@Attribute(name = "description") String str) {
            super(str);
            this.instanceId = "0";
        }
    }

    public AssertService() {
        super("Assert-Service");
        this.instanceId = "0";
        this.asserts = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public OperationResult execute(Context context, ConstantsManager constantsManager, Result result, LogSession logSession) {
        ArrayList arrayList = new ArrayList(30);
        try {
            String decode = constantsManager.decode(this.uuid);
            String decode2 = constantsManager.decode(this.instanceId);
            try {
                int parseInt = Integer.parseInt(decode2);
                arrayList.add(Logger.logEntry(logSession, 1, "Reading services..."));
                BluetoothGattService service = getTarget().getService(decode, parseInt);
                if (service == null) {
                    logFail(result, logSession, "Service (uuid=" + decode + ", instance-id=" + parseInt + ") not found", arrayList);
                    return toResult(-4);
                }
                logSuccess(result, logSession, "Service (uuid=" + decode + ", instance-id=" + parseInt + ") found", arrayList);
                boolean z = false;
                for (AssertCharacteristic assertCharacteristic : this.asserts) {
                    arrayList.add(Logger.logEntry(logSession, 10, constantsManager.decode(assertCharacteristic.getDescription()) + "..."));
                    OperationResult execute = assertCharacteristic.execute(context, service, constantsManager, result, logSession, arrayList);
                    if (OperationResult.WARNING.equals(execute)) {
                        z = true;
                    } else if (OperationResult.FAIL.equals(execute)) {
                        return toResult(-4);
                    }
                }
                if (z) {
                    return OperationResult.WARNING;
                }
                return toResult(0);
            } catch (NumberFormatException unused) {
                throw new SyntaxException("The instance-id must be a number: " + decode2);
            }
        } finally {
            Logger.log(logSession, arrayList);
        }
    }

    public AssertService(@Attribute(name = "description") String str) {
        super(str);
        this.instanceId = "0";
        this.asserts = new ArrayList();
    }
}
