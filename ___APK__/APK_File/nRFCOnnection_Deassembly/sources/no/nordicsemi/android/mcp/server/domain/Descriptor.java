package no.nordicsemi.android.mcp.server.domain;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import no.nordicsemi.android.mcp.database.DatabaseUtils;
import no.nordicsemi.android.mcp.domain.common.HasName;
import no.nordicsemi.android.mcp.server.domain.Characteristic;
import no.nordicsemi.android.mcp.util.AdoptedDescriptorsHelper;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Complete;
import org.simpleframework.xml.core.Persist;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

@Root
/* loaded from: classes.dex */
public class Descriptor extends HasName implements Parcelable {
    private static final UUID CHARACTERISTIC_EXTENDED_PROPERTIES = new UUID(45079976742912L, DatabaseUtils.LSB);
    private static final UUID CLIENT_CHARACTERISTIC_CONFIG = new UUID(45088566677504L, DatabaseUtils.LSB);
    public static final Parcelable.Creator<Descriptor> CREATOR = new Parcelable.Creator<Descriptor>() { // from class: no.nordicsemi.android.mcp.server.domain.Descriptor.1
        @Override // android.os.Parcelable.Creator
        public Descriptor createFromParcel(Parcel parcel) {
            return new Descriptor(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public Descriptor[] newArray(int i) {
            return new Descriptor[i];
        }
    };
    private Characteristic characteristic;

    @Attribute(required = false)
    private PredefinedDescriptors configure;

    @Attribute(required = false)
    private Boolean enabled;

    @Attribute(required = false)
    private String id;
    private int internalId;
    private int permissions;

    @ElementList(entry = "permission", inline = true, required = false)
    private List<Permission> permissionsList;
    private UUID uuid;

    @Attribute(name = "uuid", required = false)
    private String uuidString;

    @Attribute(required = false)
    private String value;

    @Attribute(required = false)
    private String valueString;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: no.nordicsemi.android.mcp.server.domain.Descriptor$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$no$nordicsemi$android$mcp$server$domain$Descriptor$PredefinedDescriptors = new int[PredefinedDescriptors.values().length];

        static {
            try {
                $SwitchMap$no$nordicsemi$android$mcp$server$domain$Descriptor$PredefinedDescriptors[PredefinedDescriptors.CCCD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$mcp$server$domain$Descriptor$PredefinedDescriptors[PredefinedDescriptors.CEPD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Root
    /* loaded from: classes.dex */
    public static class Permission {

        @Attribute
        private Name name;

        /* loaded from: classes.dex */
        public enum Name {
            READ(1),
            READ_ENCRYPTED(2),
            READ_ENCRYPTED_MITM(4),
            WRITE(16),
            WRITE_ENCRYPTED(32),
            WRITE_ENCRYPTED_MITM(64),
            WRITE_SIGNED(128),
            WRITE_SIGNED_MITM(256);

            private int mask;

            Name(int i) {
                this.mask = i;
            }

            public int getMask() {
                return this.mask;
            }
        }

        private Permission() {
        }

        private Permission(Name name) {
            this.name = name;
        }
    }

    /* loaded from: classes.dex */
    public enum PredefinedDescriptors {
        CUSTOM,
        CCCD,
        CEPD
    }

    public Descriptor() {
        this.configure = PredefinedDescriptors.CUSTOM;
        this.enabled = Boolean.TRUE;
        this.permissionsList = new ArrayList();
    }

    @Complete
    private void complete() {
        if (this.configure == null) {
            this.configure = PredefinedDescriptors.CUSTOM;
        } else {
            validatePredefinedDescriptor();
        }
        if (this.enabled == null) {
            this.enabled = Boolean.TRUE;
        }
    }

    private byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            int digit = Character.digit(str.charAt(i), 16);
            bArr[i / 2] = (byte) ((digit << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    @Persist
    private void prepare() {
        if (PredefinedDescriptors.CUSTOM.equals(this.configure)) {
            this.configure = null;
        } else {
            this.permissionsList.clear();
            this.value = null;
            this.valueString = null;
        }
        if (Boolean.FALSE.equals(this.enabled)) {
            return;
        }
        this.enabled = null;
    }

    @Validate
    private void validate() {
        if (this.configure == null && this.uuidString == null) {
            throw new PersistenceException("Descriptor UUID must be set", new Object[0]);
        }
        if (this.configure == null && this.value != null && this.valueString != null) {
            throw new PersistenceException("Both value-string and value specified for descriptor " + getName(), new Object[0]);
        }
        Iterator<Permission> it = this.permissionsList.iterator();
        while (it.hasNext()) {
            this.permissions = it.next().name.mask | this.permissions;
        }
    }

    private void validatePredefinedDescriptor() {
        int i = AnonymousClass2.$SwitchMap$no$nordicsemi$android$mcp$server$domain$Descriptor$PredefinedDescriptors[this.configure.ordinal()];
        if (i == 1) {
            this.uuid = CLIENT_CHARACTERISTIC_CONFIG;
            setPermissions(17);
            setValue("0000");
        } else if (i == 2) {
            this.uuid = CHARACTERISTIC_EXTENDED_PROPERTIES;
            setPermissions(1);
            int i2 = (this.characteristic.getProperties() & Characteristic.Property.Name.RELIABLE_WRITE.getMask()) <= 0 ? 0 : 1;
            setValue("0" + ((this.characteristic.getProperties() & Characteristic.Property.Name.WRITE_AUXILIARY.getMask()) > 0 ? i2 | 2 : i2 & (-3)) + "00");
        }
        setName(null);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Characteristic getCharacteristic() {
        return this.characteristic;
    }

    public int getCharacteristicInternalId() {
        return this.internalId >> 8;
    }

    public PredefinedDescriptors getConfiguration() {
        return this.configure;
    }

    public int getInternalId() {
        return this.internalId;
    }

    public int getPermissions() {
        return this.permissions;
    }

    public String getRawStringValue() {
        return this.valueString;
    }

    public String getRawValue() {
        return this.value;
    }

    public int getServerInternalId() {
        return this.internalId >> 16;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public byte[] getValue() {
        String str = this.value;
        if (str != null) {
            return hexStringToByteArray(str);
        }
        String str2 = this.valueString;
        if (str2 != null) {
            return str2.getBytes();
        }
        return null;
    }

    public String getValueAsString() {
        if (this.value == null) {
            return this.valueString;
        }
        return "0x" + this.value;
    }

    public boolean isEnabled() {
        return !Boolean.FALSE.equals(this.enabled);
    }

    public boolean isManaged() {
        return AdoptedDescriptorsHelper.isCCCD(this.uuid) || AdoptedDescriptorsHelper.isCEPD(this.uuid);
    }

    public boolean isPredefined() {
        return !PredefinedDescriptors.CUSTOM.equals(this.configure);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public void setConfiguration(PredefinedDescriptors predefinedDescriptors, UUID uuid) {
        if (predefinedDescriptors != null) {
            this.configure = predefinedDescriptors;
            this.uuid = uuid;
            this.uuidString = uuid != null ? uuid.toString() : null;
            if (PredefinedDescriptors.CUSTOM.equals(predefinedDescriptors)) {
                return;
            }
            try {
                validateDescriptor();
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
    public void setInternalId(int i, int i2) {
        this.internalId = (i << 8) | i2;
    }

    public void setPermissions(int i) {
        this.permissions = i;
        this.permissionsList.clear();
        for (Permission.Name name : Permission.Name.values()) {
            if ((name.mask & i) > 0) {
                this.permissionsList.add(new Permission(name));
            }
        }
    }

    public void setStringValue(String str) {
        this.valueString = str;
        this.value = null;
    }

    public void setValue(String str) {
        this.valueString = null;
        this.value = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void validateDescriptor() {
        if (!PredefinedDescriptors.CUSTOM.equals(this.configure)) {
            validatePredefinedDescriptor();
            return;
        }
        String str = this.uuidString;
        if (!TextUtils.isEmpty(str)) {
            String str2 = this.value;
            if (str2 != null) {
                int length = str2.length();
                if (length % 2 == 1) {
                    throw new PersistenceException("The byte array '%s' must have even number of characters", this.value);
                }
                for (int i = 0; i < length; i++) {
                    if (Character.digit(this.value.charAt(i), 16) == -1) {
                        throw new PersistenceException("'%s' is not a valid byte array", this.value);
                    }
                }
            }
            try {
                this.uuid = UUID.fromString(str);
                return;
            } catch (Exception unused) {
                throw new PersistenceException(str + " is not a valid UUID", new Object[0]);
            }
        }
        throw new PersistenceException("Descriptor UUID must be set", new Object[0]);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.internalId);
        parcel.writeInt(!Boolean.FALSE.equals(this.enabled) ? 1 : 0);
        parcel.writeSerializable(this.configure);
        parcel.writeString(this.uuidString);
        parcel.writeParcelable(new ParcelUuid(this.uuid), 0);
    }

    public Descriptor(Characteristic characteristic) {
        this.configure = PredefinedDescriptors.CUSTOM;
        this.enabled = Boolean.TRUE;
        this.permissionsList = new ArrayList();
        this.characteristic = characteristic;
    }

    public Descriptor(Parcel parcel) {
        this.configure = PredefinedDescriptors.CUSTOM;
        this.enabled = Boolean.TRUE;
        this.permissionsList = new ArrayList();
        this.internalId = parcel.readInt();
        this.enabled = Boolean.valueOf(parcel.readInt() == 1);
        this.configure = (PredefinedDescriptors) parcel.readSerializable();
        this.uuidString = parcel.readString();
        this.uuid = ((ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader())).getUuid();
    }
}
