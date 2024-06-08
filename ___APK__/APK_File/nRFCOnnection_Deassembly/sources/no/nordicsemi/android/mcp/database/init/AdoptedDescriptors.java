package no.nordicsemi.android.mcp.database.init;

import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class AdoptedDescriptors {
    public static void initialize(DatabaseHelper databaseHelper) {
        databaseHelper.addDescriptor(10496L, "Characteristic Extended Properties", "org.bluetooth.descriptor.gatt.characteristic_extended_properties");
        databaseHelper.addDescriptor(10497L, "Characteristic User Description", "org.bluetooth.descriptor.gatt.characteristic_user_description", 1);
        databaseHelper.addDescriptor(10498L, "Client Characteristic Configuration", "org.bluetooth.descriptor.gatt.client_characteristic_configuration");
        databaseHelper.addDescriptor(10499L, "Server Characteristic Configuration", "org.bluetooth.descriptor.gatt.server_characteristic_configuration");
        databaseHelper.addDescriptor(10500L, "Characteristic Presentation Format", "org.bluetooth.descriptor.gatt.characteristic_presentation_format");
        databaseHelper.addDescriptor(10501L, "Characteristic Aggregate Format", "org.bluetooth.descriptor.gatt.characteristic_aggregate_format");
        databaseHelper.addDescriptor(10502L, "Valid Range", "org.bluetooth.descriptor.valid_range");
        databaseHelper.addDescriptor(10503L, "External Report Reference", "org.bluetooth.descriptor.external_report_reference");
        databaseHelper.addDescriptor(10504L, "Report Reference", "org.bluetooth.descriptor.report_reference");
    }

    public static void initializeDBVersion_2_2(DatabaseHelper databaseHelper) {
        databaseHelper.addDescriptor(10505L, "Number of Digitals", "org.bluetooth.descriptor.number_of_digitals");
        databaseHelper.addDescriptor(10506L, "Value Trigger Setting", "org.bluetooth.descriptor.value_trigger_setting");
        databaseHelper.addDescriptor(10507L, "Environmental Sensing Configuration", "org.bluetooth.descriptor.es_configuration");
        databaseHelper.addDescriptor(10508L, "Environmental Sensing Measurement", "org.bluetooth.descriptor.es_measurement");
        databaseHelper.addDescriptor(10509L, "Environmental Sensing Trigger Setting", "org.bluetooth.descriptor.es_trigger_setting");
        databaseHelper.addDescriptor(10510L, "Time Trigger Setting", "org.bluetooth.descriptor.time_trigger_setting");
    }
}
