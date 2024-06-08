package no.nordicsemi.android.mcp.server.domain;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import no.nordicsemi.android.dfu.DfuBaseService;
import no.nordicsemi.android.mcp.database.DescriptorContract;
import no.nordicsemi.android.mcp.domain.common.HasName;
import no.nordicsemi.android.mcp.server.domain.Descriptor;
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
public class Characteristic extends HasName implements Parcelable {
    public static final Parcelable.Creator<Characteristic> CREATOR = new Parcelable.Creator<Characteristic>() { // from class: no.nordicsemi.android.mcp.server.domain.Characteristic.1
        @Override // android.os.Parcelable.Creator
        public Characteristic createFromParcel(Parcel parcel) {
            return new Characteristic(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public Characteristic[] newArray(int i) {
            return new Characteristic[i];
        }
    };

    @ElementList(entry = DescriptorContract.Descriptor.DESCRIPTOR_CONTENT_DIRECTORY, inline = true, required = false)
    private List<Descriptor> descriptors;

    @Attribute(required = false)
    private Boolean enabled;

    @Attribute(required = false)
    private String id;
    private int internalId;
    private int permissions;

    @ElementList(entry = "permission", inline = true, required = false)
    private List<Permission> permissionsList;
    private int properties;

    @ElementList(entry = "property", inline = true, required = false)
    private List<Property> propertiesList;
    private Service service;
    private UUID uuid;

    @Attribute(name = "uuid")
    private String uuidString;

    @Attribute(required = false)
    private String value;

    @Attribute(required = false)
    private String valueString;

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

    @Root
    /* loaded from: classes.dex */
    public static class Property {

        @Attribute
        private Name name;

        /* loaded from: classes.dex */
        public enum Name {
            BROADCAST(1),
            READ(2),
            WRITE(8),
            WRITE_WITHOUT_RESPONSE(4),
            NOTIFY(16),
            INDICATE(32),
            SIGNED_WRITE(64),
            RELIABLE_WRITE(256),
            WRITE_AUXILIARY(DfuBaseService.ERROR_REMOTE_TYPE_SECURE);

            private int mask;

            Name(int i) {
                this.mask = i;
            }

            public int getMask() {
                return this.mask;
            }
        }

        private Property() {
        }

        private Property(Name name) {
            this.name = name;
        }
    }

    public Characteristic() {
        this.enabled = Boolean.TRUE;
        this.propertiesList = new ArrayList();
        this.permissionsList = new ArrayList();
        this.descriptors = new ArrayList();
    }

    @Complete
    private void complete() {
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
        if (Boolean.FALSE.equals(this.enabled)) {
            return;
        }
        this.enabled = null;
    }

    @Validate
    private void validate() {
        if (this.value != null && this.valueString != null) {
            throw new PersistenceException("Both value-string and value specified for characteristic", new Object[0]);
        }
        List<Property> list = this.propertiesList;
        if (list != null && !list.isEmpty()) {
            Iterator<Property> it = this.propertiesList.iterator();
            while (it.hasNext()) {
                this.properties = it.next().name.mask | this.properties;
            }
            if ((this.properties & (Property.Name.RELIABLE_WRITE.getMask() | Property.Name.WRITE_AUXILIARY.getMask())) > 0) {
                this.properties |= 128;
            } else {
                this.properties &= -129;
            }
            Iterator<Permission> it2 = this.permissionsList.iterator();
            while (it2.hasNext()) {
                this.permissions = it2.next().name.mask | this.permissions;
            }
            return;
        }
        throw new PersistenceException("Characteristic must have at least one property", new Object[0]);
    }

    private void validateDescriptors() {
        boolean z = (this.properties & (Property.Name.NOTIFY.getMask() | Property.Name.INDICATE.getMask())) > 0;
        boolean z2 = (this.properties & (Property.Name.RELIABLE_WRITE.getMask() | Property.Name.WRITE_AUXILIARY.getMask())) > 0;
        ArrayList arrayList = null;
        Descriptor descriptor = null;
        Descriptor descriptor2 = null;
        for (Descriptor descriptor3 : this.descriptors) {
            if (AdoptedDescriptorsHelper.isCCCD(descriptor3.getUuid())) {
                if (descriptor == null) {
                    descriptor = descriptor3;
                } else {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(descriptor3);
                }
            } else if (AdoptedDescriptorsHelper.isCEPD(descriptor3.getUuid())) {
                if (descriptor2 == null) {
                    descriptor2 = descriptor3;
                } else {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(descriptor3);
                }
            }
        }
        if (arrayList != null) {
            this.descriptors.removeAll(arrayList);
        }
        if (!z && descriptor != null) {
            this.descriptors.remove(descriptor);
        }
        if (!z2 && descriptor2 != null) {
            this.descriptors.remove(descriptor2);
        }
        if (z && descriptor == null) {
            List<Descriptor> list = this.descriptors;
            descriptor = new Descriptor(this);
            list.add(descriptor);
        }
        if (descriptor != null) {
            descriptor.setConfiguration(Descriptor.PredefinedDescriptors.CCCD, null);
        }
        if (z2 && descriptor2 == null) {
            List<Descriptor> list2 = this.descriptors;
            descriptor2 = new Descriptor(this);
            list2.add(descriptor2);
        }
        if (descriptor2 != null) {
            descriptor2.setConfiguration(Descriptor.PredefinedDescriptors.CEPD, null);
        }
    }

    public void addDescriptor(Descriptor descriptor) {
        descriptor.setInternalId(this.internalId, this.descriptors.size());
        descriptor.setCharacteristic(this);
        this.descriptors.add(descriptor);
        validateDescriptors();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<Descriptor> getDescriptors() {
        return this.descriptors;
    }

    public int getInternalId() {
        return this.internalId;
    }

    public int getPermissions() {
        return this.permissions;
    }

    public int getProperties() {
        return this.properties;
    }

    public String getRawStringValue() {
        return this.valueString;
    }

    public String getRawValue() {
        return this.value;
    }

    public int getServerInternalId() {
        return this.internalId >> 8;
    }

    public Service getService() {
        return this.service;
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

    public void setProperties(int i) {
        this.properties = i;
        if (((Property.Name.RELIABLE_WRITE.getMask() | Property.Name.WRITE_AUXILIARY.getMask()) & i) > 0) {
            this.properties |= 128;
        } else {
            this.properties &= -129;
        }
        this.propertiesList.clear();
        for (Property.Name name : Property.Name.values()) {
            if ((name.mask & i) > 0) {
                this.propertiesList.add(new Property(name));
            }
        }
        validateDescriptors();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setService(Service service) {
        this.service = service;
    }

    public void setStringValue(String str) {
        this.valueString = str;
        this.value = null;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
        this.uuidString = uuid.toString();
    }

    public void setValue(String str) {
        this.valueString = null;
        this.value = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void validateCharacteristic() {
        String str = this.uuidString;
        int i = 0;
        if (!TextUtils.isEmpty(str)) {
            try {
                this.uuid = UUID.fromString(str);
                String str2 = this.value;
                if (str2 != null) {
                    int length = str2.length();
                    if (length % 2 == 1) {
                        throw new PersistenceException("The byte array '%s' must have even number of characters", this.value);
                    }
                    for (int i2 = 0; i2 < length; i2++) {
                        if (Character.digit(this.value.charAt(i2), 16) == -1) {
                            throw new PersistenceException("'%s' is not a valid byte array", this.value);
                        }
                    }
                }
                if (!this.descriptors.isEmpty()) {
                    for (Descriptor descriptor : this.descriptors) {
                        descriptor.setInternalId(this.internalId, i);
                        descriptor.setCharacteristic(this);
                        descriptor.validateDescriptor();
                        i++;
                    }
                }
                validateDescriptors();
                return;
            } catch (Exception unused) {
                throw new PersistenceException(str + " is not a valid UUID", new Object[0]);
            }
        }
        throw new PersistenceException("Characteristic UUID must be set", new Object[0]);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.internalId);
        parcel.writeInt(!Boolean.FALSE.equals(this.enabled) ? 1 : 0);
        parcel.writeString(this.uuidString);
        parcel.writeParcelable(new ParcelUuid(this.uuid), 0);
    }

    public Characteristic(@Attribute(name = "uuid") String str) {
        this.enabled = Boolean.TRUE;
        this.propertiesList = new ArrayList();
        this.permissionsList = new ArrayList();
        this.descriptors = new ArrayList();
        this.uuidString = str;
    }

    public Characteristic(Parcel parcel) {
        this.enabled = Boolean.TRUE;
        this.propertiesList = new ArrayList();
        this.permissionsList = new ArrayList();
        this.descriptors = new ArrayList();
        this.internalId = parcel.readInt();
        this.enabled = Boolean.valueOf(parcel.readInt() == 1);
        this.uuidString = parcel.readString();
        this.uuid = ((ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader())).getUuid();
    }
}
