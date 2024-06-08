package no.nordicsemi.android.mcp.database.init;

import no.nordicsemi.android.mcp.ble.parser.gap.TxPowerLevelParser;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class AdoptedCharacteristics {
    public static void initialize(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(10819L, "Alert Category ID", "org.bluetooth.characteristic.alert_category_id");
        databaseHelper.addCharacteristic(10818L, "Alert Category ID Bit Mask", "org.bluetooth.characteristic.alert_category_id_bit_mask");
        databaseHelper.addCharacteristic(10758L, "Alert Level", "org.bluetooth.characteristic.alert_level");
        databaseHelper.addCharacteristic(10820L, "Alert Notification Control Point", "org.bluetooth.characteristic.alert_notification_control_point");
        databaseHelper.addCharacteristic(10815L, "Alert Status", "org.bluetooth.characteristic.alert_status");
        databaseHelper.addCharacteristic(10753L, "Appearance", "org.bluetooth.characteristic.gap.appearance");
        databaseHelper.addCharacteristic(10777L, "Battery Level", "org.bluetooth.characteristic.battery_level");
        databaseHelper.addCharacteristic(10825L, "Blood Pressure Feature", "org.bluetooth.characteristic.blood_pressure_feature");
        databaseHelper.addCharacteristic(10805L, "Blood Pressure Measurement", "org.bluetooth.characteristic.blood_pressure_measurement");
        databaseHelper.addCharacteristic(10808L, "Body Sensor Location", "org.bluetooth.characteristic.body_sensor_location");
        databaseHelper.addCharacteristic(10786L, "Boot Keyboard Input Report", "org.bluetooth.characteristic.boot_keyboard_input_report");
        databaseHelper.addCharacteristic(10802L, "Boot Keyboard Output Report", "org.bluetooth.characteristic.boot_keyboard_output_report");
        databaseHelper.addCharacteristic(10803L, "Boot Mouse Input Report", "org.bluetooth.characteristic.boot_mouse_input_report");
        databaseHelper.addCharacteristic(10844L, "CSC Feature", "org.bluetooth.characteristic.csc_feature");
        databaseHelper.addCharacteristic(10843L, "CSC Measurement", "org.bluetooth.characteristic.csc_measurement");
        databaseHelper.addCharacteristic(10795L, "Current Time", "org.bluetooth.characteristic.current_time");
        databaseHelper.addCharacteristic(10854L, "Cycling Power Control Point", "org.bluetooth.characteristic.cycling_power_control_point");
        databaseHelper.addCharacteristic(10853L, "Cycling Power Feature", "org.bluetooth.characteristic.cycling_power_feature");
        databaseHelper.addCharacteristic(10851L, "Cycling Power Measurement", "org.bluetooth.cycling_power_measurement");
        databaseHelper.addCharacteristic(10852L, "Cycling Power Vector", "org.bluetooth.characteristic.cycling_power_vector");
        databaseHelper.addCharacteristic(10760L, "Date Time", "org.bluetooth.characteristic.date_time");
        databaseHelper.addCharacteristic(10762L, "Day Date Time", "org.bluetooth.characteristic.day_date_time");
        databaseHelper.addCharacteristic(10761L, "Day of Week", "org.bluetooth.characteristic.day_of_week");
        databaseHelper.addCharacteristic(10752L, "Device Name", "org.bluetooth.characteristic.gap.device_name", (Integer) 1);
        databaseHelper.addCharacteristic(10765L, "DST Offset", "org.bluetooth.characteristic.dst_offset");
        databaseHelper.addCharacteristic(10764L, "Exact Time 256", "org.bluetooth.characteristic.exact_time_256");
        databaseHelper.addCharacteristic(10790L, "Firmware Revision String", "org.bluetooth.characteristic.firmware_revision_string", (Integer) 1);
        databaseHelper.addCharacteristic(10833L, "Glucose Feature", "org.bluetooth.characteristic.glucose_feature");
        databaseHelper.addCharacteristic(10776L, "Glucose Measurement", "org.bluetooth.characteristic.glucose_measurement");
        databaseHelper.addCharacteristic(10804L, "Glucose Measurement Context", "org.bluetooth.characteristic.glucose_measurement_context");
        databaseHelper.addCharacteristic(10791L, "Hardware Revision String", "org.bluetooth.characteristic.hardware_revision_string", (Integer) 1);
        databaseHelper.addCharacteristic(10809L, "Heart Rate Control Point", "org.bluetooth.characteristic.heart_rate_control_point");
        databaseHelper.addCharacteristic(10807L, "Heart Rate Measurement", "org.bluetooth.characteristic.heart_rate_measurement");
        databaseHelper.addCharacteristic(10828L, "HID Control Point", "org.bluetooth.characteristic.hid_control_point");
        databaseHelper.addCharacteristic(10826L, "HID Information", "org.bluetooth.characteristic.hid_information");
        databaseHelper.addCharacteristic(10794L, "IEEE 11073-20601 Regulatory Certification Data List", "org.bluetooth.characteristic.ieee_11073-20601_regulatory_certification_data_list");
        databaseHelper.addCharacteristic(10806L, "Intermediate Cuff Pressure", "org.bluetooth.characteristic.intermediate_blood_pressure");
        databaseHelper.addCharacteristic(10782L, "Intermediate Temperature", "org.bluetooth.characteristic.intermediate_temperature");
        databaseHelper.addCharacteristic(10859L, "LN Control Point", "org.bluetooth.ln_control_point");
        databaseHelper.addCharacteristic(10858L, "LN Feature", "org.bluetooth.characteristic.ln_feature");
        databaseHelper.addCharacteristic(10767L, "Local Time Information", "org.bluetooth.characteristic.local_time_information");
        databaseHelper.addCharacteristic(10855L, "Location and Speed", "org.bluetooth.location_and_speed");
        databaseHelper.addCharacteristic(10793L, "Manufacturer Name String", "org.bluetooth.characteristic.manufacturer_name_string", (Integer) 1);
        databaseHelper.addCharacteristic(10785L, "Measurement Interval", "org.bluetooth.characteristic.measurement_interval");
        databaseHelper.addCharacteristic(10788L, "Model Number String", "org.bluetooth.characteristic.model_number_string", (Integer) 1);
        databaseHelper.addCharacteristic(10856L, "Navigation", "org.bluetooth.characteristic.navigation");
        databaseHelper.addCharacteristic(10822L, "New Alert", "org.bluetooth.characteristic.new_alert");
        databaseHelper.addCharacteristic(10756L, "Peripheral Preferred Connection Parameters", "org.bluetooth.characteristic.gap.peripheral_preferred_connection_parameters");
        databaseHelper.addCharacteristic(10754L, "Peripheral Privacy Flag", "org.bluetooth.characteristic.gap.peripheral_privacy_flag");
        databaseHelper.addCharacteristic(10832L, "PnP ID", "org.bluetooth.characteristic.pnp_id");
        databaseHelper.addCharacteristic(10857L, "Position Quality", "org.bluetooth.position_quality");
        databaseHelper.addCharacteristic(10830L, "Protocol Mode", "org.bluetooth.characteristic.protocol_mode");
        databaseHelper.addCharacteristic(10755L, "Reconnection Address", "org.bluetooth.characteristic.gap.reconnection_address");
        databaseHelper.addCharacteristic(10834L, "Record Access Control Point", "org.bluetooth.characteristic.record_access_control_point");
        databaseHelper.addCharacteristic(10772L, "Reference Time Information", "org.bluetooth.characteristic.reference_time_information");
        databaseHelper.addCharacteristic(10829L, "Report", "org.bluetooth.characteristic.report");
        databaseHelper.addCharacteristic(10827L, "Report Map", "org.bluetooth.characteristic.report_map");
        databaseHelper.addCharacteristic(10816L, "Ringer Control Point", "org.bluetooth.characteristic.ringer_control_point");
        databaseHelper.addCharacteristic(10817L, "Ringer Setting", "org.bluetooth.characteristic.ringer_setting");
        databaseHelper.addCharacteristic(10836L, "RSC Feature", "org.bluetooth.characteristic.rsc_feature");
        databaseHelper.addCharacteristic(10835L, "RSC Measurement", "org.bluetooth.characteristic.rsc_measurement");
        databaseHelper.addCharacteristic(10837L, "SC Control Point", "org.bluetooth.characteristic.sc_control_point");
        databaseHelper.addCharacteristic(10831L, "Scan Interval Window", "org.bluetooth.characteristic.scan_interval_window");
        databaseHelper.addCharacteristic(10801L, "Scan Refresh", "org.bluetooth.characteristic.scan_refresh");
        databaseHelper.addCharacteristic(10845L, "Sensor Location", "org.bluetooth.characteristic.sensor_location");
        databaseHelper.addCharacteristic(10789L, "Serial Number String", "org.bluetooth.characteristic.serial_number_string", (Integer) 1);
        databaseHelper.addCharacteristic(10757L, "Service Changed", "org.bluetooth.characteristic.gatt.service_changed");
        databaseHelper.addCharacteristic(10792L, "Software Revision String", "org.bluetooth.characteristic.software_revision_string", (Integer) 1);
        databaseHelper.addCharacteristic(10823L, "Supported New Alert Category", "org.bluetooth.characteristic.supported_new_alert_category");
        databaseHelper.addCharacteristic(10824L, "Supported Unread Alert Category", "org.bluetooth.characteristic.supported_unread_alert_category");
        databaseHelper.addCharacteristic(10787L, "System ID", "org.bluetooth.characteristic.system_id");
        databaseHelper.addCharacteristic(10780L, "Temperature Measurement", "org.bluetooth.characteristic.temperature_measurement");
        databaseHelper.addCharacteristic(10781L, "Temperature Type", "org.bluetooth.characteristic.temperature_type");
        databaseHelper.addCharacteristic(10770L, "Time Accuracy", "org.bluetooth.characteristic.time_accuracy");
        databaseHelper.addCharacteristic(10771L, "Time Source", "org.bluetooth.characteristic.time_source");
        databaseHelper.addCharacteristic(10774L, "Time Update Control Point", "org.bluetooth.characteristic.time_update_control_point");
        databaseHelper.addCharacteristic(10775L, "Time Update State", "org.bluetooth.characteristic.time_update_state");
        databaseHelper.addCharacteristic(10769L, "Time with DST", "org.bluetooth.characteristic.time_with_dst");
        databaseHelper.addCharacteristic(10766L, "Time Zone", "org.bluetooth.characteristic.time_zone");
        databaseHelper.addCharacteristic(10759L, TxPowerLevelParser.TX_POWER_LEVEL, "org.bluetooth.characteristic.tx_power_level");
        databaseHelper.addCharacteristic(10821L, "Unread Alert Status", "org.bluetooth.characteristic.unread_alert_status");
    }

    public static void initializeDBVersion_1_11(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(10878L, "Aerobic Heart Rate Lower Limit", "org.bluetooth.characteristic.aerobic_heart_rate_lower_limit");
        databaseHelper.addCharacteristic(10884L, "Aerobic Heart Rate Upper Limit", "org.bluetooth.characteristic.aerobic_heart_rate_upper_limit");
        databaseHelper.addCharacteristic(10879L, "Aerobic Threshold", "org.bluetooth.characteristic.aerobic_threshold");
        databaseHelper.addCharacteristic(10880L, "Age", "org.bluetooth.characteristic.age");
        databaseHelper.addCharacteristic(10881L, "Anaerobic Heart Rate Lower Limit", "org.bluetooth.characteristic.anaerobic_heart_rate_lower_limit");
        databaseHelper.addCharacteristic(10882L, "Anaerobic Heart Rate Upper Limit", "org.bluetooth.characteristic.anaerobic_heart_rate_upper_limit");
        databaseHelper.addCharacteristic(10883L, "Anaerobic Threshold", "org.bluetooth.characteristic.anaerobic_threshold");
        databaseHelper.addCharacteristic(10905L, "Database Change Increment", "org.bluetooth.characteristic.database_change_increment");
        databaseHelper.addCharacteristic(10885L, "Date of Birth", "org.bluetooth.characteristic.date_of_birth");
        databaseHelper.addCharacteristic(10886L, "Date of Threshold Assessment", "org.bluetooth.characteristic.date_of_threshold_assessment");
        databaseHelper.addCharacteristic(10887L, "Email Address", "org.bluetooth.characteristic.email_address", (Integer) 1);
        databaseHelper.addCharacteristic(10888L, "Fat Burn Heart Rate Lower Limit", "org.bluetooth.characteristic.fat_burn_heart_rate_lower_limit");
        databaseHelper.addCharacteristic(10889L, "Fat Burn Heart Rate Upper Limit", "org.bluetooth.characteristic.fat_burn_heart_rate_upper_limit");
        databaseHelper.addCharacteristic(10890L, "First Name", "org.bluetooth.characteristic.first_name", (Integer) 1);
        databaseHelper.addCharacteristic(10891L, "Five Zone Heart Rate Limits", "org.bluetooth.characteristic.five_zone_heart_rate_limits");
        databaseHelper.addCharacteristic(10892L, "Gender", "org.bluetooth.characteristic.gender");
        databaseHelper.addCharacteristic(10893L, "Heart Rate Max", "org.bluetooth.characteristic.heart_rate_max");
        databaseHelper.addCharacteristic(10894L, "Height", "org.bluetooth.characteristic.height");
        databaseHelper.addCharacteristic(10895L, "Hip Circumference", "org.bluetooth.characteristic.hip_circumference");
        databaseHelper.addCharacteristic(10914L, "Language", "org.bluetooth.characteristic.language", (Integer) 1);
        databaseHelper.addCharacteristic(10896L, "Last Name", "org.bluetooth.characteristic.last_name", (Integer) 1);
        databaseHelper.addCharacteristic(10897L, "Maximum Recommended Heart Rate", "org.bluetooth.characteristic.maximum_recommended_heart_rate");
        databaseHelper.addCharacteristic(10898L, "Resting Heart Rate", "org.bluetooth.characteristic.resting_heart_rate");
        databaseHelper.addCharacteristic(10899L, "Sport Type for Aerobic and Anaerobic Thresholds", "org.bluetooth.characteristic.sport_type_for_aerobic_and_anaerobic_thresholds");
        databaseHelper.addCharacteristic(10901L, "Two Zone Heart Rate Limit", "org.bluetooth.characteristic.two_zone_heart_rate_limit");
        databaseHelper.addCharacteristic(10906L, "User Index", "org.bluetooth.characteristic.user_index");
        databaseHelper.addCharacteristic(10902L, "VO2 Max", "org.bluetooth.characteristic.vo2_max");
        databaseHelper.addCharacteristic(10903L, "Waist Circumference", "org.bluetooth.characteristic.waist_circumference");
        databaseHelper.addCharacteristic(10904L, "Weight", "org.bluetooth.characteristic.weight");
    }

    public static void initializeDBVersion_2_2(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(10931L, "Altitude", "org.bluetooth.characteristic.altitude");
        databaseHelper.addCharacteristic(10867L, "Apparent Wind Direction ", "org.bluetooth.characteristic.apparent_wind_direction");
        databaseHelper.addCharacteristic(10866L, "Apparent Wind Speed", "org.bluetooth.characteristic.apparent_wind_speed");
        databaseHelper.addCharacteristic(10915L, "Barometric Pressure Trend", "org.bluetooth.characteristic.barometric_pressure_trend");
        databaseHelper.addCharacteristic(10907L, "Body Composition Feature", "org.bluetooth.characteristic.body_composition_feature");
        databaseHelper.addCharacteristic(10908L, "Body Composition Measurement", "org.bluetooth.characteristic.body_composition_measurement");
        databaseHelper.addCharacteristic(10916L, "Bond Management Control Point", "org.bluetooth.characteristic.bond_management_control_point");
        databaseHelper.addCharacteristic(10917L, "Bond Management Feature", "org.bluetooth.characteristic.bond_management_feature");
        databaseHelper.addCharacteristic(10918L, "Central Address Resolution", "org.bluetooth.characteristic.gap.central_address_resolution_support");
        databaseHelper.addCharacteristic(10920L, "CGM Feature", "org.bluetooth.characteristic.cgm_feature");
        databaseHelper.addCharacteristic(10919L, "CGM Measurement", "org.bluetooth.characteristic.cgm_measurement");
        databaseHelper.addCharacteristic(10923L, "CGM Session Run Time", "org.bluetooth.characteristic.cgm_session_run_time");
        databaseHelper.addCharacteristic(10922L, "CGM Session Start Time", "org.bluetooth.characteristic.cgm_session_start_time");
        databaseHelper.addCharacteristic(10924L, "CGM Specific Ops Control Point", "org.bluetooth.characteristic.cgm_specific_ops_control_point");
        databaseHelper.addCharacteristic(10921L, "CGM Status", "org.bluetooth.characteristic.cgm_status");
        databaseHelper.addCharacteristic(10877L, "Descriptor Value Changed", "org.bluetooth.characteristic.descriptor_value_changed");
        databaseHelper.addCharacteristic(10875L, "Dew Point", "org.bluetooth.characteristic.dew_point");
        databaseHelper.addCharacteristic(10860L, "Elevation", "org.bluetooth.characteristic.elevation");
        databaseHelper.addCharacteristic(10930L, "Floor Number", "org.bluetooth.characteristic.floor_number");
        databaseHelper.addCharacteristic(10868L, "Gust Factor", "org.bluetooth.characteristic.gust_factor");
        databaseHelper.addCharacteristic(10874L, "Heat Index", "org.bluetooth.characteristic.heat_index");
        databaseHelper.addCharacteristic(10863L, "Humidity", "org.bluetooth.characteristic.humidity");
        databaseHelper.addCharacteristic(10925L, "Indoor Positioning Configuration", "org.bluetooth.characteristic.indoor_positioning_configuration");
        databaseHelper.addCharacteristic(10871L, "Irradiance", "org.bluetooth.characteristic.irradiance");
        databaseHelper.addCharacteristic(10926L, "Latitude", "org.bluetooth.characteristic.latitude");
        databaseHelper.addCharacteristic(10929L, "Local East Coordinate", "org.bluetooth.characteristic.local_east_coordinate");
        databaseHelper.addCharacteristic(10928L, "Local North Coordinate", "org.bluetooth.characteristic.local_north_coordinate");
        databaseHelper.addCharacteristic(10933L, "Location Name", "org.bluetooth.characteristic.location_name", (Integer) 1);
        databaseHelper.addCharacteristic(10927L, "Longitude", "org.bluetooth.characteristic.longitude");
        databaseHelper.addCharacteristic(10796L, "Magnetic Declination", "org.bluetooth.characteristic.magnetic_declination");
        databaseHelper.addCharacteristic(10912L, "Magnetic Flux Density - 2D", "org.bluetooth.characteristic.magnetic_flux_density_2D");
        databaseHelper.addCharacteristic(10913L, "Magnetic Flux Density - 3D", "org.bluetooth.characteristic.magnetic_flux_density_3D");
        databaseHelper.addCharacteristic(10869L, "Pollen Concentration", "org.bluetooth.characteristic.pollen_concentration");
        databaseHelper.addCharacteristic(10861L, "Pressure", "org.bluetooth.characteristic.pressure");
        databaseHelper.addCharacteristic(10872L, "Rainfall", "org.bluetooth.characteristic.rainfall");
        databaseHelper.addCharacteristic(10862L, "Temperature", "org.bluetooth.characteristic.temperature");
        databaseHelper.addCharacteristic(10865L, "True Wind Direction", "org.bluetooth.characteristic.true_wind_direction");
        databaseHelper.addCharacteristic(10864L, "True Wind Speed", "org.bluetooth.characteristic.true_wind_speed");
        databaseHelper.addCharacteristic(10932L, "Uncertainty", "org.bluetooth.characteristic.uncertainty");
        databaseHelper.addCharacteristic(10870L, "UV Index", "org.bluetooth.characteristic.uv_index");
        databaseHelper.addCharacteristic(10909L, "Weight Measurement", "org.bluetooth.characteristic.weight_measurement");
        databaseHelper.addCharacteristic(10910L, "Weight Scale Feature", "org.bluetooth.characteristic.weight_scale_feature");
        databaseHelper.addCharacteristic(10873L, "Wind Chill", "org.bluetooth.characteristic.wind_chill");
    }

    public static void initializeDBVersion_3_2_1(DatabaseHelper databaseHelper) {
        databaseHelper.removeCharacteristic(10911L);
        databaseHelper.addCharacteristic(10900L, "Three Zone Heart Rate Limits", "org.bluetooth.characteristic.three_zone_heart_rate_limits");
        databaseHelper.addCharacteristic(10911L, "User Control Point", "org.bluetooth.characteristic.user_control_point");
    }

    public static void initializeDBVersion_4_0(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(10838L, "Digital", "org.bluetooth.characteristic.digital");
        databaseHelper.addCharacteristic(10840L, "Analog", "org.bluetooth.characteristic.analog");
        databaseHelper.addCharacteristic(10842L, "Aggregate", "org.bluetooth.characteristic.aggregate");
        databaseHelper.addCharacteristic(10934L, "URI", "org.bluetooth.characteristic.uri", (Integer) 1);
        databaseHelper.addCharacteristic(10935L, "HTTP Headers", "org.bluetooth.characteristic.http_headers", (Integer) 1);
        databaseHelper.addCharacteristic(10936L, "HTTP Status Code", "org.bluetooth.characteristic.http_status_code");
        databaseHelper.addCharacteristic(10937L, "HTTP Entity Body", "org.bluetooth.characteristic.http_entity_body", (Integer) 1);
        databaseHelper.addCharacteristic(10938L, "HTTP Control Point", "org.bluetooth.characteristic.http_control_point");
        databaseHelper.addCharacteristic(10939L, "HTTPS Security", "org.bluetooth.characteristic.https_security");
        databaseHelper.addCharacteristic(10940L, "TDS Control Point", "org.bluetooth.characteristic.tds_control_point");
        databaseHelper.addCharacteristic(10941L, "OTS Feature", "org.bluetooth.characteristic.ots_feature");
        databaseHelper.addCharacteristic(10942L, "Object Name", "org.bluetooth.characteristic.object_name", (Integer) 1);
        databaseHelper.addCharacteristic(10943L, "Object Type", "org.bluetooth.characteristic.object_type");
        databaseHelper.addCharacteristic(10944L, "Object Size", "org.bluetooth.characteristic.object_size");
        databaseHelper.addCharacteristic(10945L, "Object First-Created", "org.bluetooth.characteristic.object_first_created");
        databaseHelper.addCharacteristic(10946L, "Object Last-Modified", "org.bluetooth.characteristic.object_last_modified");
        databaseHelper.addCharacteristic(10947L, "Object ID", "org.bluetooth.characteristic.object_id");
        databaseHelper.addCharacteristic(10948L, "Object Properties", "org.bluetooth.characteristic.object_properties");
        databaseHelper.addCharacteristic(10949L, "Object Action Control Point", "org.bluetooth.characteristic.object_action_control_point");
        databaseHelper.addCharacteristic(10950L, "Object List Control Point", "org.bluetooth.characteristic.object_list_control_point");
        databaseHelper.addCharacteristic(10951L, "Object List Filter", "org.bluetooth.characteristic.object_list_filter");
        databaseHelper.addCharacteristic(10952L, "Object Changed", "org.bluetooth.characteristic.object_changed");
        databaseHelper.addCharacteristic(10846L, "PLX Spot-Check Measurement", "org.bluetooth.characteristic.plx_spot_check_measurement");
        databaseHelper.addCharacteristic(10847L, "PLX Continuous Measurement", "org.bluetooth.characteristic.plx_continuous_measurement");
        databaseHelper.addCharacteristic(10848L, "PLX Features", "org.bluetooth.characteristic.plx_features");
    }

    public static void initializeDBVersion_4_12_0(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(10956L, "Fitness Machine Feature", "org.bluetooth.characteristic.fitness_machine_feature");
        databaseHelper.addCharacteristic(10957L, "Treadmill Data", "org.bluetooth.characteristic.treadmill_data");
        databaseHelper.addCharacteristic(10958L, "Cross Trainer Data", "org.bluetooth.characteristic.cross_trainer_data");
        databaseHelper.addCharacteristic(10959L, "Step Climber Data", "org.bluetooth.characteristic.step_climber_data");
        databaseHelper.addCharacteristic(10960L, "Stair Climber Data", "org.bluetooth.characteristic.stair_climber_data");
        databaseHelper.addCharacteristic(10961L, "Rower Data", "org.bluetooth.characteristic.rower_data");
        databaseHelper.addCharacteristic(10962L, "Indoor Bike Data", "org.bluetooth.characteristic.indoor_bike_data");
        databaseHelper.addCharacteristic(10963L, "Training Status", "org.bluetooth.characteristic.training_status");
        databaseHelper.addCharacteristic(10964L, "Supported Speed Range", "org.bluetooth.characteristic.supported_speed_range");
        databaseHelper.addCharacteristic(10965L, "Supported Inclination Range", "org.bluetooth.characteristic.supported_inclination_range");
        databaseHelper.addCharacteristic(10966L, "Supported Resistance Level Range", "org.bluetooth.characteristic.supported_resistance_level_range");
        databaseHelper.addCharacteristic(10967L, "Supported Heart Rate Range", "org.bluetooth.characteristic.supported_heart_rate_range");
        databaseHelper.addCharacteristic(10968L, "Supported Power Range", "org.bluetooth.characteristic.supported_power_range");
        databaseHelper.addCharacteristic(10969L, "Fitness Machine Control Point", "org.bluetooth.characteristic.fitness_machine_control_point");
        databaseHelper.addCharacteristic(10970L, "Fitness Machine Status", "org.bluetooth.characteristic.fitness_machine_status");
    }

    public static void initializeDBVersion_4_13_0(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(10971L, "Mesh Provisioning Data In", "org.bluetooth.characteristic.mesh_provisioning_data_in");
        databaseHelper.addCharacteristic(10972L, "Mesh Provisioning Data Out", "org.bluetooth.characteristic.mesh_provisioning_data_out");
        databaseHelper.addCharacteristic(10973L, "Mesh Proxy Data In", "org.bluetooth.characteristic.mesh_proxy_data_in");
        databaseHelper.addCharacteristic(10974L, "Mesh Proxy Data Out", "org.bluetooth.characteristic.mesh_proxy_data_out");
    }

    public static void initializeDBVersion_4_17_0(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(10850L, "Pulse Oximetry Control Point", "org.bluetooth.characteristic.pulse_oximetry_control_point");
        databaseHelper.addCharacteristic(10810L, "Removable", "org.bluetooth.characteristic.removable");
        databaseHelper.addCharacteristic(10811L, "Service Required", "org.bluetooth.characteristic.service_required");
        databaseHelper.addCharacteristic(10812L, "Scientific Temperature Celsius", "org.bluetooth.characteristic.scientific_temperature_celsius");
        databaseHelper.addCharacteristic(10813L, "String", "org.bluetooth.characteristic.string");
        databaseHelper.addCharacteristic(10814L, "Network Availability", "org.bluetooth.characteristic.network_availability");
        databaseHelper.addCharacteristic(10763L, "Exact Time 100", "org.bluetooth.characteristic.exact_time_100");
        databaseHelper.addCharacteristic(10768L, "Secondary Time Zone", "org.bluetooth.characteristic.secondary_time_zone");
        databaseHelper.addCharacteristic(10773L, "Time Broadcast", "org.bluetooth.characteristic.time_broadcast");
        databaseHelper.addCharacteristic(10778L, "Battery Power State", "org.bluetooth.characteristic.battery_power_state");
        databaseHelper.addCharacteristic(10779L, "Battery Level State", "org.bluetooth.characteristic.battery_level_state");
        databaseHelper.addCharacteristic(10783L, "Temperature Celsius", "org.bluetooth.characteristic.temperature_celsius");
        databaseHelper.addCharacteristic(10784L, "Temperature Fahrenheit", "org.bluetooth.characteristic.temperature_fahrenheit");
        databaseHelper.addCharacteristic(10799L, "Position 2D", "org.bluetooth.characteristic.position_2d");
        databaseHelper.addCharacteristic(10800L, "Position 3D", "org.bluetooth.characteristic.position_3d");
        databaseHelper.addCharacteristic(10839L, "Digital Output", "org.bluetooth.characteristic.digital_output");
        databaseHelper.addCharacteristic(10841L, "Analog Output", "org.bluetooth.characteristic.analog_output");
        databaseHelper.addCharacteristic(10953L, "Resolvable Private Address Only", "org.bluetooth.characteristic.resolvable_private_address_only");
    }

    public static void initializeDBVersion_4_20_1(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(11037L, "RC Feature", "org.bluetooth.characteristic.rc_feature");
        databaseHelper.addCharacteristic(11038L, "RC Settings", "org.bluetooth.characteristic.rc_settings");
        databaseHelper.addCharacteristic(11039L, "RC Control Point", "org.bluetooth.characteristic.reconnection_configuration_control_point");
        databaseHelper.addCharacteristic(11040L, "IDD Status Changed", "org.bluetooth.characteristic.idd_status_changed");
        databaseHelper.addCharacteristic(11041L, "IDD Status Status", "org.bluetooth.characteristic.idd_status");
        databaseHelper.addCharacteristic(11042L, "IDD Annunciation Status", "org.bluetooth.characteristic.idd_annunciation_status");
        databaseHelper.addCharacteristic(11043L, "IDD Features", "org.bluetooth.characteristic.idd_features");
        databaseHelper.addCharacteristic(11044L, "IDD Status Reader Control Point", "org.bluetooth.characteristic.idd_status_reader_control_point");
        databaseHelper.addCharacteristic(11045L, "IDD Command Control Point", "org.bluetooth.characteristic.idd_command_control_point");
        databaseHelper.addCharacteristic(11046L, "IDD Command Data", "org.bluetooth.characteristic.idd_command_data");
        databaseHelper.addCharacteristic(11047L, "IDD Record Access Control Point", "org.bluetooth.characteristic.idd_record_access_control_point");
        databaseHelper.addCharacteristic(11048L, "IDD History Data", "org.bluetooth.characteristic.idd_history_data");
    }

    public static void initializeDBVersion_4_23_0(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(11063L, "Registered User", "org.bluetooth.characteristic.registered_user");
        databaseHelper.addCharacteristic(11051L, "BSS Control Point", "org.bluetooth.characteristic.bss_control_point");
        databaseHelper.addCharacteristic(11052L, "BSS Response", "org.bluetooth.characteristic.bss_response");
        databaseHelper.addCharacteristic(11053L, "Emergency ID", "org.bluetooth.characteristic.emergency_id");
        databaseHelper.addCharacteristic(11054L, "Emergency Text", "org.bluetooth.characteristic.emergency_text");
        databaseHelper.addCharacteristic(11049L, "Client Supported Features", "org.bluetooth.characteristic.client_supported_features");
        databaseHelper.addCharacteristic(11050L, "Database Hash", "org.bluetooth.characteristic.database_hash");
    }
}
