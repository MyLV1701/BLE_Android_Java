package no.nordicsemi.android.mcp.server.domain;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import no.nordicsemi.android.mcp.database.CharacteristicContract;
import no.nordicsemi.android.mcp.database.DatabaseUtils;
import no.nordicsemi.android.mcp.domain.common.HasName;
import no.nordicsemi.android.mcp.server.domain.Descriptor;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Complete;
import org.simpleframework.xml.core.Persist;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

@Root
/* loaded from: classes.dex */
public class Service extends HasName implements Parcelable {
    public static final Parcelable.Creator<Service> CREATOR = new Parcelable.Creator<Service>() { // from class: no.nordicsemi.android.mcp.server.domain.Service.1
        @Override // android.os.Parcelable.Creator
        public Service createFromParcel(Parcel parcel) {
            return new Service(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public Service[] newArray(int i) {
            return new Service[i];
        }
    };

    @ElementList(entry = CharacteristicContract.Characteristic.CHARACTERISTIC_CONTENT_DIRECTORY, inline = true, required = false)
    private List<Characteristic> characteristics;

    @Attribute(required = false)
    private PredefinedService configure;

    @Attribute(required = false)
    private Boolean enabled;
    private int internalId;
    private List<Characteristic> predefinedCharacteristics;
    private UUID uuid;

    @Attribute(name = "uuid", required = false)
    private String uuidString;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: no.nordicsemi.android.mcp.server.domain.Service$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$no$nordicsemi$android$mcp$server$domain$Service$PredefinedService = new int[PredefinedService.values().length];

        static {
            try {
                $SwitchMap$no$nordicsemi$android$mcp$server$domain$Service$PredefinedService[PredefinedService.IMMEDIATE_ALERT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$mcp$server$domain$Service$PredefinedService[PredefinedService.LINK_LOSS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$mcp$server$domain$Service$PredefinedService[PredefinedService.CURRENT_TIME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$mcp$server$domain$Service$PredefinedService[PredefinedService.HEART_RATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum PredefinedService {
        CUSTOM(0),
        LINK_LOSS(1),
        IMMEDIATE_ALERT(2),
        CURRENT_TIME(3),
        HEART_RATE(4);

        private int position;

        PredefinedService(int i) {
            this.position = i;
        }

        public int getPosition() {
            return this.position;
        }
    }

    public Service() {
        this.enabled = Boolean.TRUE;
        this.configure = PredefinedService.CUSTOM;
    }

    @Complete
    private void complete() {
        if (this.configure == null) {
            this.configure = PredefinedService.CUSTOM;
        }
        if (this.enabled == null) {
            this.enabled = Boolean.TRUE;
        }
    }

    @Persist
    private void prepare() {
        if (PredefinedService.CUSTOM.equals(this.configure)) {
            this.configure = null;
        }
        if (Boolean.FALSE.equals(this.enabled)) {
            return;
        }
        this.enabled = null;
    }

    @Validate
    private void validate() {
        if (PredefinedService.CUSTOM.equals(this.configure) && this.uuidString == null) {
            throw new PersistenceException("Service UUID must be set", new Object[0]);
        }
    }

    private void validatePredefinedService() {
        int i = AnonymousClass2.$SwitchMap$no$nordicsemi$android$mcp$server$domain$Service$PredefinedService[this.configure.ordinal()];
        int i2 = 0;
        if (i == 1) {
            this.uuid = new UUID(26396869005312L, DatabaseUtils.LSB);
            Characteristic characteristic = new Characteristic("00002A06-0000-1000-8000-00805F9B34FB");
            characteristic.setPermissions(16);
            characteristic.setProperties(4);
            characteristic.setValue(null);
            this.predefinedCharacteristics = new ArrayList(1);
            this.predefinedCharacteristics.add(characteristic);
        } else if (i == 2) {
            this.uuid = new UUID(26401163972608L, DatabaseUtils.LSB);
            Characteristic characteristic2 = new Characteristic("00002A06-0000-1000-8000-00805F9B34FB");
            characteristic2.setPermissions(17);
            characteristic2.setProperties(10);
            characteristic2.setValue("00");
            this.predefinedCharacteristics = new ArrayList(1);
            this.predefinedCharacteristics.add(characteristic2);
        } else if (i == 3) {
            this.uuid = new UUID(26409753907200L, DatabaseUtils.LSB);
            Characteristic characteristic3 = new Characteristic("00002A2B-0000-1000-8000-00805F9B34FB");
            characteristic3.setPermissions(1);
            characteristic3.setProperties(18);
            Descriptor descriptor = new Descriptor();
            descriptor.setConfiguration(Descriptor.PredefinedDescriptors.CCCD, null);
            characteristic3.addDescriptor(descriptor);
            Characteristic characteristic4 = new Characteristic("00002A0F-0000-1000-8000-00805F9B34FB");
            characteristic4.setPermissions(1);
            characteristic4.setProperties(2);
            this.predefinedCharacteristics = new ArrayList(2);
            this.predefinedCharacteristics.add(characteristic3);
            this.predefinedCharacteristics.add(characteristic4);
        } else if (i == 4) {
            this.uuid = new UUID(26444113645568L, DatabaseUtils.LSB);
            Characteristic characteristic5 = new Characteristic("00002A37-0000-1000-8000-00805F9B34FB");
            characteristic5.setPermissions(0);
            characteristic5.setProperties(16);
            Descriptor descriptor2 = new Descriptor();
            descriptor2.setConfiguration(Descriptor.PredefinedDescriptors.CCCD, null);
            characteristic5.addDescriptor(descriptor2);
            Characteristic characteristic6 = new Characteristic("00002A38-0000-1000-8000-00805F9B34FB");
            characteristic6.setPermissions(1);
            characteristic6.setProperties(2);
            characteristic6.setValue("01");
            Characteristic characteristic7 = new Characteristic("00002A39-0000-1000-8000-00805F9B34FB");
            characteristic7.setPermissions(16);
            characteristic7.setProperties(8);
            this.predefinedCharacteristics = new ArrayList(3);
            this.predefinedCharacteristics.add(characteristic5);
            this.predefinedCharacteristics.add(characteristic6);
            this.predefinedCharacteristics.add(characteristic7);
        }
        setName(null);
        this.uuidString = null;
        this.characteristics = null;
        List<Characteristic> list = this.predefinedCharacteristics;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (Characteristic characteristic8 : this.predefinedCharacteristics) {
            characteristic8.setInternalId(this.internalId, i2);
            characteristic8.setService(this);
            characteristic8.validateCharacteristic();
            i2++;
        }
    }

    public void addCharacteristic(Characteristic characteristic) {
        if (this.characteristics == null) {
            this.characteristics = new ArrayList(4);
        }
        characteristic.setInternalId(this.internalId, this.characteristics.size());
        characteristic.setService(this);
        this.characteristics.add(characteristic);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<Characteristic> getCharacteristics() {
        List<Characteristic> list = this.predefinedCharacteristics;
        if (list != null) {
            return list;
        }
        if (this.characteristics == null) {
            this.characteristics = new ArrayList();
        }
        return this.characteristics;
    }

    public PredefinedService getConfiguration() {
        return this.configure;
    }

    public int getInternalId() {
        return this.internalId;
    }

    public int getType() {
        return 0;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public boolean isEnabled() {
        return !Boolean.FALSE.equals(this.enabled);
    }

    public boolean isPredefined() {
        return !PredefinedService.CUSTOM.equals(this.configure);
    }

    public void setConfiguration(PredefinedService predefinedService, UUID uuid) {
        if (predefinedService != null) {
            this.configure = predefinedService;
            this.predefinedCharacteristics = null;
            this.uuid = uuid;
            this.uuidString = uuid != null ? uuid.toString() : null;
            if (PredefinedService.CUSTOM.equals(predefinedService)) {
                return;
            }
            try {
                validateService();
                return;
            } catch (Exception unused) {
                return;
            }
        }
        throw new IllegalArgumentException("Configure argument may not be null. Use CUSTOM");
    }

    public void setEnabled(boolean z) {
        this.enabled = Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setInternalId(int i) {
        this.internalId = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void validateService() {
        if (!PredefinedService.CUSTOM.equals(this.configure)) {
            validatePredefinedService();
            return;
        }
        String str = this.uuidString;
        int i = 0;
        if (!TextUtils.isEmpty(str)) {
            try {
                this.uuid = UUID.fromString(str);
                List<Characteristic> list = this.characteristics;
                if (list == null || list.isEmpty()) {
                    return;
                }
                for (Characteristic characteristic : this.characteristics) {
                    characteristic.setInternalId(this.internalId, i);
                    characteristic.setService(this);
                    characteristic.validateCharacteristic();
                    i++;
                }
                return;
            } catch (Exception unused) {
                throw new PersistenceException(str + " is not a valid UUID", new Object[0]);
            }
        }
        throw new PersistenceException("Service UUID must be set", new Object[0]);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.internalId);
        parcel.writeInt(!Boolean.FALSE.equals(this.enabled) ? 1 : 0);
        parcel.writeSerializable(this.configure);
        parcel.writeString(this.uuidString);
        parcel.writeParcelable(new ParcelUuid(this.uuid), 0);
    }

    public Service(Parcel parcel) {
        this.enabled = Boolean.TRUE;
        this.configure = PredefinedService.CUSTOM;
        this.internalId = parcel.readInt();
        this.enabled = Boolean.valueOf(parcel.readInt() == 1);
        this.configure = (PredefinedService) parcel.readSerializable();
        this.uuidString = parcel.readString();
        this.uuid = ((ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader())).getUuid();
    }
}
