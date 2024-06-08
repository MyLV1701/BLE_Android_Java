package no.nordicsemi.android.mcp.database.init;

import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class AdoptedServices {
    public static void initialize(DatabaseHelper databaseHelper) {
        databaseHelper.addService(6144L, "Generic Access", "org.bluetooth.service.generic_access");
        databaseHelper.addService(6145L, "Generic Attribute", "org.bluetooth.service.generic_attribute");
        databaseHelper.addService(6146L, "Immediate Alert", "org.bluetooth.service.immediate_alert");
        databaseHelper.addService(6147L, "Link Loss", "org.bluetooth.service.link_loss");
        databaseHelper.addService(6148L, "Tx Power", "org.bluetooth.service.tx_power");
        databaseHelper.addService(6149L, "Current Time Service", "org.bluetooth.service.current_time");
        databaseHelper.addService(6150L, "Reference Time Update Service", "org.bluetooth.service.reference_time_update");
        databaseHelper.addService(6151L, "Next DST Change Service", "org.bluetooth.service.next_dst_change");
        databaseHelper.addService(6152L, "Glucose", "org.bluetooth.service.glucose");
        databaseHelper.addService(6153L, "Health Thermometer", "org.bluetooth.service.health_thermometer");
        databaseHelper.addService(6154L, "Device Information", "org.bluetooth.service.device_information");
        databaseHelper.addService(6157L, "Heart Rate", "org.bluetooth.service.heart_rate");
        databaseHelper.addService(6158L, "Phone Alert Status Service", "org.bluetooth.service.phone_alert_status");
        databaseHelper.addService(6159L, "Battery Service", "org.bluetooth.service.battery_service");
        databaseHelper.addService(6160L, "Blood Pressure", "org.bluetooth.service.blood_pressure");
        databaseHelper.addService(6161L, "Alert Notification Service", "org.bluetooth.service.alert_notification");
        databaseHelper.addService(6162L, "Human Interface Device", "org.bluetooth.service.human_interface_device");
        databaseHelper.addService(6163L, "Scan Parameters", "org.bluetooth.service.scan_parameters");
        databaseHelper.addService(6164L, "Running Speed and Cadence", "org.bluetooth.service.running_speed_and_cadence");
        databaseHelper.addService(6166L, "Cycling Speed and Cadence", "org.bluetooth.service.cycling_speed_and_cadence");
        databaseHelper.addService(6168L, "Cycling Power", "org.bluetooth.service.cycling_power");
        databaseHelper.addService(6169L, "Location and Navigation", "org.bluetooth.service.location_and_navigation");
    }

    public static void initializeDBVersion_1_11(DatabaseHelper databaseHelper) {
        databaseHelper.addService(6172L, "User Data", "org.bluetooth.service.user_data");
    }

    public static void initializeDBVersion_2_2(DatabaseHelper databaseHelper) {
        databaseHelper.addService(6170L, "Environmental Sensing", "org.bluetooth.service.environmental_sensing");
        databaseHelper.addService(6171L, "Body Composition", "org.bluetooth.service.body_composition");
        databaseHelper.addService(6173L, "Weight Scale", "org.bluetooth.service.weight_scale");
        databaseHelper.addService(6174L, "Bond Management", "org.bluetooth.service.bond_management");
        databaseHelper.addService(6175L, "Continuous Glucose Monitoring", "org.bluetooth.service.continuous_glucose_monitoring");
        databaseHelper.addService(6176L, "Internet Protocol Support", "org.bluetooth.service.internet_protocol_support");
        databaseHelper.addService(6177L, "Indoor Positioning", "org.bluetooth.service.indoor_positioning");
    }

    public static void initializeDBVersion_4_0(DatabaseHelper databaseHelper) {
        databaseHelper.addService(6165L, "Automation IO", "org.bluetooth.service.automation_io");
        databaseHelper.addService(6179L, "HTTP Proxy", "org.bluetooth.service.http_proxy");
    }

    public static void initializeDBVersion_4_0_4(DatabaseHelper databaseHelper) {
        databaseHelper.removeService(6192L);
        databaseHelper.addService(6178L, "Pulse Oximeter", "org.bluetooth.service.pulse_oximeter");
    }

    public static void initializeDBVersion_4_12_0(DatabaseHelper databaseHelper) {
        databaseHelper.removeService(6180L);
        databaseHelper.removeService(6181L);
        databaseHelper.addService(6180L, "Transport Discovery", "org.bluetooth.service.object_transfer");
        databaseHelper.addService(6181L, "Object Transfer Service", "org.bluetooth.service.transport_discovery");
        databaseHelper.addService(6182L, "Fitness Machine", "org.bluetooth.service.fitness_machine");
    }

    public static void initializeDBVersion_4_13_0(DatabaseHelper databaseHelper) {
        databaseHelper.addService(6183L, "Mesh Provisioning Service", "org.bluetooth.service.mesh_provisioning");
        databaseHelper.addService(6184L, "Mesh Proxy Service", "org.bluetooth.service.mesh_proxy");
    }

    public static void initializeDBVersion_4_20_1(DatabaseHelper databaseHelper) {
        databaseHelper.addService(6185L, "Reconnection Configuration", "org.bluetooth.service.reconnection_configuration");
        databaseHelper.addService(6202L, "Insulin Delivery", "org.bluetooth.service.insulin_delivery");
    }

    public static void initializeDBVersion_4_23_0(DatabaseHelper databaseHelper) {
        databaseHelper.addService(6203L, "Binary Sensor", "org.bluetooth.service.binary_sensor_service");
        databaseHelper.addService(6204L, "Emergency Configuration", "org.bluetooth.service.emergency_configuration_service");
    }
}
