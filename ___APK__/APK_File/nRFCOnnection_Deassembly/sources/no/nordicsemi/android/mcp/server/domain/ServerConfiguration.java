package no.nordicsemi.android.mcp.server.domain;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.ServiceContract;
import no.nordicsemi.android.mcp.domain.common.HasName;
import no.nordicsemi.android.mcp.server.domain.Descriptor;
import no.nordicsemi.android.mcp.server.domain.Service;
import no.nordicsemi.android.mcp.test.ConstantsManager;
import no.nordicsemi.android.mcp.util.AdoptedDescriptorsHelper;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Validate;

@Root
/* loaded from: classes.dex */
public class ServerConfiguration extends HasName {
    private ConstantsManager mConstantsManager;
    private int nextServiceInternalId;

    @ElementList(entry = ServiceContract.Service.SERVICE_CONTENT_DIRECTORY, inline = true, required = false)
    private List<Service> services;

    public ServerConfiguration() {
        super("New Server Configuration");
        this.nextServiceInternalId = 0;
        this.services = new ArrayList();
    }

    public static ServerConfiguration clone(String str, List<BluetoothGattService> list) {
        boolean z;
        ServerConfiguration serverConfiguration = new ServerConfiguration(str);
        for (BluetoothGattService bluetoothGattService : list) {
            Service service = new Service();
            serverConfiguration.addService(service);
            service.setConfiguration(Service.PredefinedService.CUSTOM, bluetoothGattService.getUuid());
            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                Characteristic characteristic = new Characteristic();
                service.addCharacteristic(characteristic);
                int properties = bluetoothGattCharacteristic.getProperties();
                for (BluetoothGattDescriptor bluetoothGattDescriptor : bluetoothGattCharacteristic.getDescriptors()) {
                    Descriptor.PredefinedDescriptors predefinedDescriptors = Descriptor.PredefinedDescriptors.CUSTOM;
                    if (AdoptedDescriptorsHelper.isCCCD(bluetoothGattDescriptor.getUuid())) {
                        predefinedDescriptors = Descriptor.PredefinedDescriptors.CCCD;
                    }
                    if (AdoptedDescriptorsHelper.isCEPD(bluetoothGattDescriptor.getUuid())) {
                        byte[] value = bluetoothGattDescriptor.getValue();
                        if (value != null && value.length == 2) {
                            properties |= ParserUtils.getIntValue(value, 18, 0) << 8;
                        }
                        predefinedDescriptors = Descriptor.PredefinedDescriptors.CEPD;
                    }
                    Descriptor descriptor = new Descriptor();
                    descriptor.setConfiguration(predefinedDescriptors, bluetoothGattDescriptor.getUuid());
                    if (bluetoothGattDescriptor.getValue() != null) {
                        descriptor.setValue(ParserUtils.bytesToHex(bluetoothGattDescriptor.getValue(), false));
                    }
                    characteristic.addDescriptor(descriptor);
                }
                int i = (properties & 2) > 0 ? 1 : 0;
                if ((properties & 12) > 0) {
                    i |= 16;
                }
                if ((properties & 64) > 0) {
                    i |= 128;
                }
                characteristic.setPermissions(i);
                characteristic.setUuid(bluetoothGattCharacteristic.getUuid());
                characteristic.setProperties(properties);
                if (bluetoothGattCharacteristic.getValue() != null) {
                    boolean z2 = bluetoothGattCharacteristic.getValue().length > 1;
                    if (z2) {
                        byte[] value2 = bluetoothGattCharacteristic.getValue();
                        int length = value2.length;
                        int i2 = 0;
                        z = false;
                        while (true) {
                            if (i2 >= length) {
                                break;
                            }
                            byte b2 = value2[i2];
                            if (b2 < 32 || b2 > 126) {
                                if (b2 != 0) {
                                    z2 = false;
                                    break;
                                }
                            } else {
                                z = true;
                            }
                            i2++;
                        }
                    } else {
                        z = false;
                    }
                    if (z2 && z) {
                        characteristic.setStringValue(new String(bluetoothGattCharacteristic.getValue()));
                    } else {
                        characteristic.setValue(ParserUtils.bytesToHex(bluetoothGattCharacteristic.getValue(), false));
                    }
                }
            }
        }
        return serverConfiguration;
    }

    @Validate
    private void validate() {
        for (Service service : this.services) {
            int i = this.nextServiceInternalId;
            this.nextServiceInternalId = i + 1;
            service.setInternalId(i);
            service.validateService();
        }
    }

    public void addService(Service service) {
        int i = this.nextServiceInternalId;
        this.nextServiceInternalId = i + 1;
        service.setInternalId(i);
        this.services.add(service);
    }

    public List<Service> getServices() {
        return this.services;
    }

    public void removeService(Service service) {
        this.services.remove(service);
    }

    public ServerConfiguration(@Attribute(name = "name") String str) {
        super(TextUtils.isEmpty(str) ? "New Server Configuration" : str);
        this.nextServiceInternalId = 0;
        this.services = new ArrayList();
    }
}
