package no.nordicsemi.android.mcp.ble.parser;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.parser.gatt.ANCSControlPointParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ANCSDataSourceParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ANCSNotificationSourceParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.AgeParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.AlertCategoryIdBitMaskParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.AlertCategoryIdParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.AlertLevelParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.AlertNotificationControlPointParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.AlertStatusParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.AngleParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.AppearanceParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.BarometricPressureTrendParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.BatteryLevelParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.BatteryLevelStateParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.BatteryPowerStateParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.BeaconParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.BigEndianUIntParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.BloodPressureFeatureParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.BloodPressureMeasurementParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.BodySensorLocationParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.BondManagementFeatureParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ButtonStateParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.CGMFeatureParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.CGMMeasurementParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.CGMSessionRunTimeParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.CGMSessionStartTimeParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.CGMSpecificOpsControlPointParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.CGMStatusParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.CSCFeatureParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.CSCMeasurementParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.CandyControlPointParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.CelsiusParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.CentralAddressResolutionParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.CharacteristicPresentationFormatParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ClientSupportedFeaturesParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.CrossTrainerParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.CurrentTimeParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.DSTOffsetParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.DatabaseChangeIncrementParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.DateParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.DateTimeParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.DayDateTimeParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.DayOfWeekParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.DescriptorValueChangedParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.DfuControlPointParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.DfuVersionParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.EddystoneBroadcastCapabilitiesParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.EddystoneLockStateParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.EddystoneReadWriteAdvSlotParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ElevationParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ExactTime100Parser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ExactTime256Parser;
import no.nordicsemi.android.mcp.ble.parser.gatt.FitnessMachineControlPointParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.FitnessMachineFeatureParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.FitnessMachineStatusParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.FiveZoneHeartRateLimitsParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.FloorNumberParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.GenderParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.GeneralCharacteristicParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.GlucoseFeatureParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.GlucoseMeasurementContextParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.GlucoseMeasurementParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.GustFactorParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.HIDInformationParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.HeartRateMeasurementParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.HeartRateParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.HttpControlPointParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.HttpStatusCodeParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.HttpsSecurityParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.HumidityParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.IndoorBikeParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.IndoorPositioningConfigurationParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.IntermediateCuffPressureParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.IntermediateTemperatureParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.IrradianceParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.LEDParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.LengthHeightParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.LocalCoordinateParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.LocalTimeInformationParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.LockStateParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.MagneticFluxDensityParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.MicrobitButtonStateParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.MicrobitLedMatrixStateParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.NewAlertParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ObjectPropertiesParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ObjectSizeParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.PeripheralPreferredConnParamsParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.PeripheralPrivacyFlagParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.PnPIDParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.PollenConcentrationParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.PressureParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ProtocolModeParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.RCControlPointParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.RCFeaturesParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.RCSettingsParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.RSCFeatureParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.RSCMeasurementParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ReconnectionAddressParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.RecordAccessControlPointParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.RemovableParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.RowerDataParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.SIntParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ScanRefreshParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.SecureDfuButtonlessParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.SecureDfuControlPointParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.SensorLocationParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ServiceChangedParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.SmpParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.SportTypeForAerobicAndAnaerobicThresholdParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.StairClimberParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.StepClimberParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.StringParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.SupportedHeartRateRangeParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.SupportedInclinationRangeParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.SupportedPowerRangeParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.SupportedResistanceLevelRangeParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.SupportedSpeedRangeParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.TemperatureCelsiusParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.TemperatureFahrenheitParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.TemperatureMeasurementParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.TemperatureParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.TemperatureTypeParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyAdvertisingParamParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyAirQualityParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyConnectionParamParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyEddystoneUrlParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyEnvironmentConfigurationParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyEulerParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyExtPinsParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyGravityVectorParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyHeadingParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyLedParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyLightIntensityParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyMTURequestParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyMotionConfigurationParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyOrientationParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyPressureParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyQuaternionParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingySoundConfigurationParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingySpeakerStatusParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyStepCounterParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyTapCounterParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyTemperatureParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThingyVersionParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ThreeZoneHeartRateLimitsParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.TimeSourceParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.TimeZoneParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.TrainingStatusParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.TreadmillDataParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.TwoZoneHeartRateLimitsParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.TxPowerLevelParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.UIntParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.UnreadAlertStatusParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.UriBeaconDataParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.UriBeaconFlagsParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.UriBeaconTxPowerLevelsParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.UriBeaconTxPowerParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.UriBeaconV1DataOneParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.UserControlPointParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.UserIndexParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.VO2MaxParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.VelocityMPSParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.WeightMeasurementParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.WeightParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.WeightScaleFeatureParser;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class CharacteristicParser extends UuidLibrary {
    private static final Map<UUID, ICharacteristicParser> mParsers = new HashMap();
    private static final StringParser STRING_PARSER = new StringParser();

    private static ICharacteristicParser getParser(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        UUID uuid = bluetoothGattCharacteristic.getUuid();
        if (UuidLibrary.AEROBIC_HEART_RATE_LOWER_LIMIT.equals(uuid)) {
            return new HeartRateParser();
        }
        if (UuidLibrary.AEROBIC_HEART_RATE_UPPER_LIMIT.equals(uuid)) {
            return new HeartRateParser();
        }
        if (UuidLibrary.AEROBIC_THRESHOLD.equals(uuid)) {
            return new HeartRateParser();
        }
        if (UuidLibrary.AGE.equals(uuid)) {
            return new AgeParser();
        }
        if (UuidLibrary.ALERT_CATEGORY_ID.equals(uuid)) {
            return new AlertCategoryIdParser();
        }
        if (UuidLibrary.ALERT_CATEGORY_ID_BIT_MASK.equals(uuid)) {
            return new AlertCategoryIdBitMaskParser();
        }
        if (UuidLibrary.ALERT_LEVEL.equals(uuid)) {
            return new AlertLevelParser();
        }
        if (UuidLibrary.ALERT_NOTIFICATION_CONTROL_POINT.equals(uuid)) {
            return new AlertNotificationControlPointParser();
        }
        if (UuidLibrary.ALERT_STATUS.equals(uuid)) {
            return new AlertStatusParser();
        }
        if (UuidLibrary.ALTITUDE.equals(uuid)) {
            return new UIntParser("dm");
        }
        if (UuidLibrary.ANAEROBIC_HEART_RATE_LOWER_LIMIT.equals(uuid)) {
            return new HeartRateParser();
        }
        if (UuidLibrary.ANAEROBIC_HEART_RATE_UPPER_LIMIT.equals(uuid)) {
            return new HeartRateParser();
        }
        if (UuidLibrary.ANAEROBIC_THRESHOLD.equals(uuid)) {
            return new HeartRateParser();
        }
        if (UuidLibrary.APPARENT_WIND_DIRECTION.equals(uuid)) {
            return new AngleParser();
        }
        if (UuidLibrary.APPARENT_WIND_SPEED.equals(uuid)) {
            return new VelocityMPSParser();
        }
        if (UuidLibrary.APPEARANCE.equals(uuid)) {
            return new AppearanceParser();
        }
        if (UuidLibrary.BAROMETRIC_PRESSURE_TREND.equals(uuid)) {
            return new BarometricPressureTrendParser();
        }
        if (UuidLibrary.BATTERY_LEVEL.equals(uuid)) {
            return new BatteryLevelParser();
        }
        if (UuidLibrary.BATTERY_LEVEL_STATE.equals(uuid)) {
            return new BatteryLevelStateParser();
        }
        if (UuidLibrary.BATTERY_POWER_STATE.equals(uuid)) {
            return new BatteryPowerStateParser();
        }
        if (UuidLibrary.BLOOD_PRESSURE_FEATURE.equals(uuid)) {
            return new BloodPressureFeatureParser();
        }
        if (UuidLibrary.BLOOD_PRESSURE_MEASUREMENT.equals(uuid)) {
            return new BloodPressureMeasurementParser();
        }
        if (UuidLibrary.BODY_SENSOR_LOCATION.equals(uuid)) {
            return new BodySensorLocationParser();
        }
        if (UuidLibrary.BOND_MANAGEMENT_FEATURE.equals(uuid)) {
            return new BondManagementFeatureParser();
        }
        if (UuidLibrary.BOOT_KEYBOARD_INPUT_REPORT.equals(uuid)) {
            return new GeneralCharacteristicParser();
        }
        if (UuidLibrary.BOOT_KEYBOARD_OUTPUT_REPORT.equals(uuid)) {
            return new GeneralCharacteristicParser();
        }
        if (UuidLibrary.CENTRAL_ADDRESS_RESOLUTION.equals(uuid)) {
            return new CentralAddressResolutionParser();
        }
        if (UuidLibrary.CGM_FEATURE.equals(uuid)) {
            return new CGMFeatureParser();
        }
        if (UuidLibrary.CGM_MEASUREMENT.equals(uuid)) {
            return new CGMMeasurementParser();
        }
        if (UuidLibrary.CGM_SESSION_RUN_TIME.equals(uuid)) {
            return new CGMSessionRunTimeParser();
        }
        if (UuidLibrary.CGM_SESSION_START_TIME.equals(uuid)) {
            return new CGMSessionStartTimeParser();
        }
        if (UuidLibrary.CGM_SPECIFIC_OPS_CONTROL_POINT.equals(uuid)) {
            return new CGMSpecificOpsControlPointParser();
        }
        if (UuidLibrary.CGM_STATUS.equals(uuid)) {
            return new CGMStatusParser();
        }
        if (UuidLibrary.CLIENT_SUPPORTED_FEATURES.equals(uuid)) {
            return new ClientSupportedFeaturesParser();
        }
        if (UuidLibrary.CSC_FEATURE.equals(uuid)) {
            return new CSCFeatureParser();
        }
        if (UuidLibrary.CSC_MEASUREMENT.equals(uuid)) {
            return new CSCMeasurementParser();
        }
        if (UuidLibrary.CURRENT_TIME.equals(uuid)) {
            return new CurrentTimeParser();
        }
        if (UuidLibrary.CROSS_TRAINER_DATA.equals(uuid)) {
            return new CrossTrainerParser();
        }
        if (UuidLibrary.DATABASE_CHANGE_INCREMENT.equals(uuid)) {
            return new DatabaseChangeIncrementParser();
        }
        if (UuidLibrary.DATE_OF_BIRTH.equals(uuid)) {
            return new DateParser();
        }
        if (UuidLibrary.DATE_OF_THRESHOLD_ASSESSMENT.equals(uuid)) {
            return new DateParser();
        }
        if (UuidLibrary.DATE_TIME.equals(uuid)) {
            return new DateTimeParser();
        }
        if (UuidLibrary.DAY_DATE_TIME.equals(uuid)) {
            return new DayDateTimeParser();
        }
        if (UuidLibrary.DAY_OF_WEEK.equals(uuid)) {
            return new DayOfWeekParser();
        }
        if (UuidLibrary.DESCRIPTOR_VALUE_CHANGED.equals(uuid)) {
            return new DescriptorValueChangedParser();
        }
        if (UuidLibrary.STRING.equals(uuid)) {
            return STRING_PARSER;
        }
        if (UuidLibrary.DEVICE_NAME.equals(uuid)) {
            return STRING_PARSER;
        }
        if (UuidLibrary.DEW_POINT.equals(uuid)) {
            return new CelsiusParser();
        }
        if (UuidLibrary.DST_OFFSET.equals(uuid)) {
            return new DSTOffsetParser();
        }
        if (UuidLibrary.ELEVATION.equals(uuid)) {
            return new ElevationParser();
        }
        if (UuidLibrary.EMAIL_ADDRESS.equals(uuid)) {
            return STRING_PARSER;
        }
        if (UuidLibrary.EXACT_TIME_100.equals(uuid)) {
            return new ExactTime100Parser();
        }
        if (UuidLibrary.EXACT_TIME_256.equals(uuid)) {
            return new ExactTime256Parser();
        }
        if (UuidLibrary.FAT_BURN_HEART_RATE_LOWER_LIMIT.equals(uuid)) {
            return new HeartRateParser();
        }
        if (UuidLibrary.FAT_BURN_HEART_RATE_UPPER_LIMIT.equals(uuid)) {
            return new HeartRateParser();
        }
        if (UuidLibrary.FITNESS_MACHINE_CONTROL_POINT.equals(uuid)) {
            return new FitnessMachineControlPointParser();
        }
        if (UuidLibrary.FITNESS_MACHINE_FEATURE.equals(uuid)) {
            return new FitnessMachineFeatureParser();
        }
        if (UuidLibrary.FITNESS_MACHINE_STATUS.equals(uuid)) {
            return new FitnessMachineStatusParser();
        }
        if (UuidLibrary.FIRMWARE_REVISION_STRING.equals(uuid)) {
            return STRING_PARSER;
        }
        if (UuidLibrary.FIRST_NAME.equals(uuid)) {
            return STRING_PARSER;
        }
        if (UuidLibrary.FIVE_ZONE_HEART_RATE_LIMITS.equals(uuid)) {
            return new FiveZoneHeartRateLimitsParser();
        }
        if (UuidLibrary.FLOOR_NUMBER.equals(uuid)) {
            return new FloorNumberParser();
        }
        if (UuidLibrary.GENDER.equals(uuid)) {
            return new GenderParser();
        }
        if (UuidLibrary.GLUCOSE_FEATURE.equals(uuid)) {
            return new GlucoseFeatureParser();
        }
        if (UuidLibrary.GLUCOSE_MEASUREMENT.equals(uuid)) {
            return new GlucoseMeasurementParser();
        }
        if (UuidLibrary.GLUCOSE_MEASUREMENT_CONTEXT.equals(uuid)) {
            return new GlucoseMeasurementContextParser();
        }
        if (UuidLibrary.GUST_FACTOR.equals(uuid)) {
            return new GustFactorParser();
        }
        if (UuidLibrary.HARDWARE_REVISION_STRING.equals(uuid)) {
            return STRING_PARSER;
        }
        if (UuidLibrary.HEART_RATE_MAX.equals(uuid)) {
            return new HeartRateParser();
        }
        if (UuidLibrary.HEART_RATE_MEASUREMENT.equals(uuid)) {
            return new HeartRateMeasurementParser();
        }
        if (UuidLibrary.HEAT_INDEX.equals(uuid)) {
            return new CelsiusParser();
        }
        if (UuidLibrary.HEIGHT.equals(uuid)) {
            return new LengthHeightParser();
        }
        if (UuidLibrary.HID_INFORMATION.equals(uuid)) {
            return new HIDInformationParser();
        }
        if (UuidLibrary.HIP_CIRCUMFERENCE.equals(uuid)) {
            return new LengthHeightParser();
        }
        if (UuidLibrary.HTTP_CONTROL_POINT.equals(uuid)) {
            return new HttpControlPointParser();
        }
        if (UuidLibrary.HTTP_ENTITY_BODY.equals(uuid)) {
            return STRING_PARSER;
        }
        if (UuidLibrary.HTTP_HEADERS.equals(uuid)) {
            return STRING_PARSER;
        }
        if (UuidLibrary.HTTP_STATUS_CODE.equals(uuid)) {
            return new HttpStatusCodeParser();
        }
        if (UuidLibrary.HTTPS_SECURITY.equals(uuid)) {
            return new HttpsSecurityParser();
        }
        if (UuidLibrary.HUMIDITY.equals(uuid)) {
            return new HumidityParser();
        }
        if (UuidLibrary.INDOOR_BIKE_DATA.equals(uuid)) {
            return new IndoorBikeParser();
        }
        if (UuidLibrary.INDOOR_POSITIONING_CONFIGURATION.equals(uuid)) {
            return new IndoorPositioningConfigurationParser();
        }
        if (UuidLibrary.INTERMEDIATE_CUFF_PRESSURE.equals(uuid)) {
            return new IntermediateCuffPressureParser();
        }
        if (UuidLibrary.INTERMEDIATE_TEMPERATURE.equals(uuid)) {
            return new IntermediateTemperatureParser();
        }
        if (UuidLibrary.IRRADIANCE.equals(uuid)) {
            return new IrradianceParser();
        }
        if (UuidLibrary.LANGUAGE.equals(uuid)) {
            return STRING_PARSER;
        }
        if (UuidLibrary.LAST_NAME.equals(uuid)) {
            return STRING_PARSER;
        }
        if (UuidLibrary.LOCAL_EAST_COORDINATE.equals(uuid)) {
            return new LocalCoordinateParser();
        }
        if (UuidLibrary.LOCAL_NORTH_COORDINATE.equals(uuid)) {
            return new LocalCoordinateParser();
        }
        if (UuidLibrary.LOCAL_TIME_INFORMATION.equals(uuid)) {
            return new LocalTimeInformationParser();
        }
        if (UuidLibrary.LOCATION_NAME.equals(uuid)) {
            return STRING_PARSER;
        }
        if (UuidLibrary.MAGNETIC_DECLINATION.equals(uuid)) {
            return new AngleParser();
        }
        if (UuidLibrary.MAGNETIC_FLUX_DENSITY_2D.equals(uuid)) {
            return new MagneticFluxDensityParser();
        }
        if (UuidLibrary.MAGNETIC_FLUX_DENSITY_3D.equals(uuid)) {
            return new MagneticFluxDensityParser();
        }
        if (UuidLibrary.MANUFACTURER_NAME_STRING.equals(uuid)) {
            return STRING_PARSER;
        }
        if (UuidLibrary.MAXIMUM_RECOMMENDED_HEART_RATE.equals(uuid)) {
            return new HeartRateParser();
        }
        if (UuidLibrary.MEASUREMENT_INTERVAL.equals(uuid)) {
            return new UIntParser("s");
        }
        if (UuidLibrary.MODEL_NUMBER_STRING.equals(uuid)) {
            return STRING_PARSER;
        }
        if (UuidLibrary.NEW_ALERT.equals(uuid)) {
            return new NewAlertParser();
        }
        if (UuidLibrary.OBJECT_NAME.equals(uuid)) {
            return STRING_PARSER;
        }
        if (UuidLibrary.OBJECT_PROPERTIES.equals(uuid)) {
            return new ObjectPropertiesParser();
        }
        if (UuidLibrary.OBJECT_SIZE.equals(uuid)) {
            return new ObjectSizeParser();
        }
        if (UuidLibrary.OBJECT_FIRST_CREATED.equals(uuid)) {
            return new DateTimeParser();
        }
        if (UuidLibrary.OBJECT_LAST_MODIFIED.equals(uuid)) {
            return new DateTimeParser();
        }
        if (UuidLibrary.PERIPHERAL_PREFERRED_CONNECTION_PARAMETERS.equals(uuid)) {
            return new PeripheralPreferredConnParamsParser();
        }
        if (UuidLibrary.PERIPHERAL_PRIVACY_FLAG.equals(uuid)) {
            return new PeripheralPrivacyFlagParser();
        }
        if (UuidLibrary.PNP_ID.equals(uuid)) {
            return new PnPIDParser();
        }
        if (UuidLibrary.POLLEN_CONCENTRATION.equals(uuid)) {
            return new PollenConcentrationParser();
        }
        if (UuidLibrary.PRESSURE.equals(uuid)) {
            return new PressureParser();
        }
        if (UuidLibrary.PROTOCOL_MODE.equals(uuid)) {
            return new ProtocolModeParser();
        }
        if (UuidLibrary.RAINFALL.equals(uuid)) {
            return new UIntParser("mm");
        }
        if (UuidLibrary.RC_FEATURE.equals(uuid)) {
            return new RCFeaturesParser();
        }
        if (UuidLibrary.RC_SETTINGS.equals(uuid)) {
            return new RCSettingsParser();
        }
        if (UuidLibrary.RC_CONTROL_POINT.equals(uuid)) {
            return new RCControlPointParser();
        }
        if (UuidLibrary.RECONNECTION_ADDRESS.equals(uuid)) {
            return new ReconnectionAddressParser();
        }
        if (UuidLibrary.RECORD_ACCESS_CONTROL_POINT.equals(uuid)) {
            return new RecordAccessControlPointParser();
        }
        if (UuidLibrary.REMOVABLE.equals(uuid)) {
            return new RemovableParser();
        }
        if (UuidLibrary.REPORT.equals(uuid)) {
            return new GeneralCharacteristicParser();
        }
        if (UuidLibrary.REPORT_MAP.equals(uuid)) {
            return new GeneralCharacteristicParser();
        }
        if (UuidLibrary.RESTING_HEART_RATE.equals(uuid)) {
            return new HeartRateParser();
        }
        if (UuidLibrary.ROWER_DATA.equals(uuid)) {
            return new RowerDataParser();
        }
        if (UuidLibrary.RSC_FEATURE.equals(uuid)) {
            return new RSCFeatureParser();
        }
        if (UuidLibrary.RSC_MEASUREMENT.equals(uuid)) {
            return new RSCMeasurementParser();
        }
        if (UuidLibrary.SCAN_REFRESH.equals(uuid)) {
            return new ScanRefreshParser();
        }
        if (UuidLibrary.SENSOR_LOCATION.equals(uuid)) {
            return new SensorLocationParser();
        }
        if (UuidLibrary.SERIAL_NUMBER_STRING.equals(uuid)) {
            return STRING_PARSER;
        }
        if (UuidLibrary.SERVICE_CHANGED.equals(uuid)) {
            return new ServiceChangedParser();
        }
        if (UuidLibrary.SOFTWARE_REVISION_STRING.equals(uuid)) {
            return STRING_PARSER;
        }
        if (UuidLibrary.SPORT_TYPE_FOR_AEROBIC_AND_ANAEROBIC_THRESHOLDS.equals(uuid)) {
            return new SportTypeForAerobicAndAnaerobicThresholdParser();
        }
        if (UuidLibrary.STAIR_CLIMBER_DATA.equals(uuid)) {
            return new StairClimberParser();
        }
        if (UuidLibrary.STEP_CLIMBER_DATA.equals(uuid)) {
            return new StepClimberParser();
        }
        if (UuidLibrary.SUPPORTED_NEW_ALERT_CATEGORY.equals(uuid)) {
            return new AlertCategoryIdBitMaskParser();
        }
        if (UuidLibrary.SUPPORTED_UNREAD_ALERT_CATEGORY.equals(uuid)) {
            return new AlertCategoryIdBitMaskParser();
        }
        if (UuidLibrary.TEMPERATURE.equals(uuid)) {
            return new TemperatureParser();
        }
        if (UuidLibrary.TEMPERATURE_CELSIUS.equals(uuid)) {
            return new TemperatureCelsiusParser();
        }
        if (UuidLibrary.TEMPERATURE_FAHRENHEIT.equals(uuid)) {
            return new TemperatureFahrenheitParser();
        }
        if (UuidLibrary.TEMPERATURE_MEASUREMENT.equals(uuid)) {
            return new TemperatureMeasurementParser();
        }
        if (UuidLibrary.TEMPERATURE_TYPE.equals(uuid)) {
            return new TemperatureTypeParser();
        }
        if (UuidLibrary.THREE_ZONE_HEART_RATE_LIMITS.equals(uuid)) {
            return new ThreeZoneHeartRateLimitsParser();
        }
        if (UuidLibrary.TIME_SOURCE.equals(uuid)) {
            return new TimeSourceParser();
        }
        if (UuidLibrary.TIME_ZONE.equals(uuid)) {
            return new TimeZoneParser();
        }
        if (UuidLibrary.TRAINING_STATUS.equals(uuid)) {
            return new TrainingStatusParser();
        }
        if (UuidLibrary.TREADMILL_DATA.equals(uuid)) {
            return new TreadmillDataParser();
        }
        if (UuidLibrary.TRUE_WIND_DIRECTION.equals(uuid)) {
            return new AngleParser();
        }
        if (UuidLibrary.TRUE_WIND_SPEED.equals(uuid)) {
            return new VelocityMPSParser();
        }
        if (UuidLibrary.TWO_ZONE_HEART_RATE_LIMIT.equals(uuid)) {
            return new TwoZoneHeartRateLimitsParser();
        }
        if (UuidLibrary.TX_POWER_LEVEL.equals(uuid)) {
            return new TxPowerLevelParser();
        }
        if (UuidLibrary.UNREAD_ALERT_STATUS.equals(uuid)) {
            return new UnreadAlertStatusParser();
        }
        if (UuidLibrary.URI.equals(uuid)) {
            return STRING_PARSER;
        }
        if (UuidLibrary.USER_INDEX.equals(uuid)) {
            return new UserIndexParser();
        }
        if (UuidLibrary.USER_CONTROL_POINT.equals(uuid)) {
            return new UserControlPointParser();
        }
        if (UuidLibrary.UV_INDEX.equals(uuid)) {
            return new UIntParser(null);
        }
        if (UuidLibrary.VO2_MAX.equals(uuid)) {
            return new VO2MaxParser();
        }
        if (UuidLibrary.WAIST_CIRCUMFERENCE.equals(uuid)) {
            return new LengthHeightParser();
        }
        if (UuidLibrary.WEIGHT.equals(uuid)) {
            return new WeightParser();
        }
        if (UuidLibrary.WEIGHT_MEASUREMENT.equals(uuid)) {
            return new WeightMeasurementParser();
        }
        if (UuidLibrary.WEIGHT_SCALE_FEATURE.equals(uuid)) {
            return new WeightScaleFeatureParser();
        }
        if (UuidLibrary.WIND_CHILL.equals(uuid)) {
            return new CelsiusParser();
        }
        if (!UuidLibrary.DFU_PACKET.equals(uuid) && !UuidLibrary.SECURE_DFU_PACKET.equals(uuid)) {
            if (UuidLibrary.DFU_CONTROL_POINT.equals(uuid)) {
                return new DfuControlPointParser();
            }
            if (UuidLibrary.DFU_VERSION.equals(uuid)) {
                return new DfuVersionParser();
            }
            if (UuidLibrary.SECURE_DFU_CONTROL_POINT.equals(uuid)) {
                return new SecureDfuControlPointParser();
            }
            if (!UuidLibrary.SECURE_DFU_BUTTONLESS.equals(uuid) && !UuidLibrary.SECURE_DFU_BUTTONLESS_BONDS.equals(uuid) && !UuidLibrary.SECURE_DFU_EXPERIMENTAL_BUTTONLESS.equals(uuid)) {
                if (UuidLibrary.SUPPORTED_HEART_RATE_RANGE.equals(uuid)) {
                    return new SupportedHeartRateRangeParser();
                }
                if (UuidLibrary.SUPPORTED_INCLINATION_RANGE.equals(uuid)) {
                    return new SupportedInclinationRangeParser();
                }
                if (UuidLibrary.SUPPORTED_POWER_RANGE.equals(uuid)) {
                    return new SupportedPowerRangeParser();
                }
                if (UuidLibrary.SUPPORTED_RESISTANCE_LEVEL_RANGE.equals(uuid)) {
                    return new SupportedResistanceLevelRangeParser();
                }
                if (UuidLibrary.SUPPORTED_SPEED_RANGE.equals(uuid)) {
                    return new SupportedSpeedRangeParser();
                }
                if (UuidLibrary.NORDIC_UART_TX.equals(uuid)) {
                    return STRING_PARSER;
                }
                if (UuidLibrary.NORDIC_UART_RX.equals(uuid)) {
                    return STRING_PARSER;
                }
                if (UuidLibrary.BEACON_UUID.equals(uuid)) {
                    return new BeaconParser(0);
                }
                if (UuidLibrary.BEACON_RSSI.equals(uuid)) {
                    return new BeaconParser(2);
                }
                if (UuidLibrary.BEACON_MAJOR_MINOR.equals(uuid)) {
                    return new BeaconParser(1);
                }
                if (UuidLibrary.BEACON_MANUFACTURER_ID.equals(uuid)) {
                    return new BeaconParser(3);
                }
                if (UuidLibrary.BEACON_CONN_INTERVAL.equals(uuid)) {
                    return new BeaconParser(4);
                }
                if (UuidLibrary.BEACON_LED.equals(uuid)) {
                    return new BeaconParser(5);
                }
                if (UuidLibrary.NORDIC_BLINKY_LED.equals(uuid)) {
                    return new LEDParser();
                }
                if (UuidLibrary.NORDIC_BLINKY_BUTTON.equals(uuid)) {
                    return new ButtonStateParser();
                }
                if (UuidLibrary.URI_BEACON_V1_DATA_ONE.equals(uuid)) {
                    return new UriBeaconV1DataOneParser();
                }
                if (UuidLibrary.URI_BEACON_V1_DATA_TWO.equals(uuid)) {
                    return new UriBeaconDataParser();
                }
                if (UuidLibrary.URI_BEACON_V1_DATA_LENGTH.equals(uuid)) {
                    return new UIntParser(null);
                }
                if (UuidLibrary.URI_BEACON_V2_LOCK_STATE.equals(uuid)) {
                    return new LockStateParser();
                }
                if (UuidLibrary.URI_BEACON_V2_DATA.equals(uuid)) {
                    return new UriBeaconDataParser();
                }
                if (UuidLibrary.URI_BEACON_V2_FLAGS.equals(uuid)) {
                    return new UriBeaconFlagsParser();
                }
                if (UuidLibrary.URI_BEACON_V2_PERIOD.equals(uuid)) {
                    return new UIntParser("ms");
                }
                if (UuidLibrary.URI_BEACON_V2_POWER_MODE.equals(uuid)) {
                    return new UriBeaconTxPowerParser();
                }
                if (UuidLibrary.URI_BEACON_V2_POWER_LEVELS.equals(uuid)) {
                    return new UriBeaconTxPowerLevelsParser();
                }
                if (UuidLibrary.CANDY_CONTROL_POINT.equals(uuid)) {
                    return new CandyControlPointParser();
                }
                if (UuidLibrary.EDDYSTONE_BROADCAST_CAPABILITIES.equals(uuid)) {
                    return new EddystoneBroadcastCapabilitiesParser();
                }
                if (UuidLibrary.EDDYSTONE_ACTIVE_SLOT.equals(uuid)) {
                    return new UIntParser(null);
                }
                if (UuidLibrary.EDDYSTONE_ADVERTISING_INTERVAL.equals(uuid)) {
                    return new BigEndianUIntParser("ms");
                }
                if (UuidLibrary.EDDYSTONE_RADIO_TX_POWER.equals(uuid)) {
                    return new SIntParser("dBm");
                }
                if (UuidLibrary.EDDYSTONE_ADVANCED_ADVERTISED_TX_POWER.equals(uuid)) {
                    return new SIntParser("dBm");
                }
                if (UuidLibrary.EDDYSTONE_LOCK_STATE.equals(uuid)) {
                    return new EddystoneLockStateParser();
                }
                if (UuidLibrary.EDDYSTONE_READ_WRITE_ADV_SLOT.equals(uuid)) {
                    return new EddystoneReadWriteAdvSlotParser();
                }
                if (UuidLibrary.ANCS_NOTIFICATION_SOURCE.equals(uuid)) {
                    return new ANCSNotificationSourceParser();
                }
                if (UuidLibrary.ANCS_CONTROL_POINT.equals(uuid)) {
                    return new ANCSControlPointParser();
                }
                if (UuidLibrary.ANCS_DATA_SOURCE.equals(uuid)) {
                    return new ANCSDataSourceParser();
                }
                if (UuidLibrary.THINGY_DEVICE_NAME_CHARACTERISTIC.equals(uuid)) {
                    return STRING_PARSER;
                }
                if (UuidLibrary.THINGY_ADVERTISING_PARAM_CHARACTERISTIC.equals(uuid)) {
                    return new ThingyAdvertisingParamParser();
                }
                if (UuidLibrary.THINGY_CONNECTION_PARAM_CHARACTERISTIC.equals(uuid)) {
                    return new ThingyConnectionParamParser();
                }
                if (UuidLibrary.THINGY_EDDYSTONE_URL.equals(uuid)) {
                    return new ThingyEddystoneUrlParser();
                }
                if (UuidLibrary.THINGY_CLOUD_TOKEN.equals(uuid)) {
                    return STRING_PARSER;
                }
                if (UuidLibrary.THINGY_FW_VERSION.equals(uuid)) {
                    return new ThingyVersionParser();
                }
                if (UuidLibrary.THINGY_MTU_REQUEST.equals(uuid)) {
                    return new ThingyMTURequestParser();
                }
                if (UuidLibrary.THINGY_TEMPERATURE_CHARACTERISTIC.equals(uuid)) {
                    return new ThingyTemperatureParser();
                }
                if (UuidLibrary.THINGY_PRESSURE_CHARACTERISTIC.equals(uuid)) {
                    return new ThingyPressureParser();
                }
                if (UuidLibrary.THINGY_HUMIDITY_CHARACTERISTIC.equals(uuid)) {
                    return new UIntParser("%");
                }
                if (UuidLibrary.THINGY_AIR_QUALITY.equals(uuid)) {
                    return new ThingyAirQualityParser();
                }
                if (UuidLibrary.THINGY_COLOR_CHARACTERISTIC.equals(uuid)) {
                    return new ThingyLightIntensityParser();
                }
                if (UuidLibrary.THINGY_ENV_CONFIG_CHARACTERISTIC.equals(uuid)) {
                    return new ThingyEnvironmentConfigurationParser();
                }
                if (UuidLibrary.THINGY_MOTION_CONFIG_CHARACTERISTIC.equals(uuid)) {
                    return new ThingyMotionConfigurationParser();
                }
                if (UuidLibrary.THINGY_TAP_CHARACTERISTIC.equals(uuid)) {
                    return new ThingyTapCounterParser();
                }
                if (UuidLibrary.THINGY_ORIENTATION_CHARACTERISTIC.equals(uuid)) {
                    return new ThingyOrientationParser();
                }
                if (UuidLibrary.THINGY_QUATERNION_CHARACTERISTIC.equals(uuid)) {
                    return new ThingyQuaternionParser();
                }
                if (UuidLibrary.THINGY_PEDOMETER_CHARACTERISTIC.equals(uuid)) {
                    return new ThingyStepCounterParser();
                }
                if (UuidLibrary.THINGY_EULER_CHARACTERISTIC.equals(uuid)) {
                    return new ThingyEulerParser();
                }
                if (UuidLibrary.THINGY_HEADING_CHARACTERISTIC.equals(uuid)) {
                    return new ThingyHeadingParser();
                }
                if (UuidLibrary.THINGY_GRAVITY_VECTOR.equals(uuid)) {
                    return new ThingyGravityVectorParser();
                }
                if (UuidLibrary.THINGY_SOUND_CONFIG_CHARACTERISTIC.equals(uuid)) {
                    return new ThingySoundConfigurationParser();
                }
                if (UuidLibrary.THINGY_SPEAKER_STATUS_CHARACTERISTIC.equals(uuid)) {
                    return new ThingySpeakerStatusParser();
                }
                if (UuidLibrary.THINGY_LED_CHARACTERISTIC.equals(uuid)) {
                    return new ThingyLedParser();
                }
                if (UuidLibrary.THINGY_BUTTON_CHARACTERISTIC.equals(uuid)) {
                    return new ButtonStateParser();
                }
                if (UuidLibrary.THINGY_EXT_PIN_CHARACTERISTIC.equals(uuid)) {
                    return new ThingyExtPinsParser();
                }
                if (UuidLibrary.MICROBIT_TEMPERATURE.equals(uuid)) {
                    return new SIntParser("°C");
                }
                if (!UuidLibrary.MICROBIT_TEMPERATURE_PERIOD.equals(uuid) && !UuidLibrary.MICROBIT_SCROLLING_DELAY.equals(uuid) && !UuidLibrary.MICROBIT_MAGNETOMETER_PERIOD.equals(uuid) && !UuidLibrary.MICROBIT_ACCELEROMETER_PERIOD.equals(uuid)) {
                    if (UuidLibrary.MICROBIT_LED_MATRIX_STATE.equals(uuid)) {
                        return new MicrobitLedMatrixStateParser();
                    }
                    if (UuidLibrary.MICROBIT_LED_TEXT.equals(uuid)) {
                        return new StringParser();
                    }
                    if (!UuidLibrary.MICROBIT_BUTTON_A_STATE.equals(uuid) && !UuidLibrary.MICROBIT_BUTTON_B_STATE.equals(uuid)) {
                        if (UuidLibrary.MICROBIT_MAGNETOMETER_BEARING.equals(uuid)) {
                            return new UIntParser("");
                        }
                        if (UuidLibrary.SMP_CHARACTERISTIC.equals(uuid)) {
                            return new SmpParser();
                        }
                        return null;
                    }
                    return new MicrobitButtonStateParser();
                }
                return new UIntParser("ms");
            }
            return new SecureDfuButtonlessParser();
        }
        return new GeneralCharacteristicParser();
    }

    public static String getValueAsString(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic, Integer num, boolean z) {
        if (bluetoothGattCharacteristic.getValue() == null) {
            return null;
        }
        if (bluetoothGattCharacteristic.getValue().length == 0) {
            return "";
        }
        if (!z) {
            return GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        if (num != null && num.intValue() == 1) {
            return STRING_PARSER.parse(databaseHelper, bluetoothGattCharacteristic);
        }
        try {
            UUID uuid = bluetoothGattCharacteristic.getUuid();
            boolean containsKey = mParsers.containsKey(uuid);
            ICharacteristicParser iCharacteristicParser = mParsers.get(uuid);
            if (!containsKey) {
                iCharacteristicParser = getParser(bluetoothGattCharacteristic);
                mParsers.put(uuid, iCharacteristicParser);
            }
            if (iCharacteristicParser != null) {
                return iCharacteristicParser.parse(databaseHelper, bluetoothGattCharacteristic);
            }
            try {
                String parse = CharacteristicPresentationFormatParser.parse(bluetoothGattCharacteristic, bluetoothGattCharacteristic.getDescriptor(UuidLibrary.CHARACTERISTIC_PRESENTATION_FORMAT));
                if (parse != null) {
                    return parse;
                }
            } catch (Exception unused) {
            }
            return GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        } catch (Exception unused2) {
            return "Invalid data syntax: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
    }
}
