package no.nordicsemi.android.mcp.ble.parser.gap;

import no.nordicsemi.android.dfu.DfuBaseService;
import no.nordicsemi.android.mcp.ble.model.AdvData;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.AppearanceLibrary;

/* loaded from: classes.dex */
public class AppearanceParser implements AppearanceLibrary {
    private static short decodeAppearance(byte[] bArr, int i) {
        return (short) (((bArr[i + 1] & FlagsParser.UNKNOWN_FLAGS) << 8) | (bArr[i] & FlagsParser.UNKNOWN_FLAGS));
    }

    public static String getAppearanceAsString(int i) {
        if (i != 0) {
            if (i == 192) {
                return "[" + i + "] Generic Watch (Generic category)";
            }
            if (i == 193) {
                return "[" + i + "] Watch: Sports Watch (Watch subtype)";
            }
            if (i == 768) {
                return "[" + i + "] Generic Thermometer (Generic category)";
            }
            if (i == 769) {
                return "[" + i + "] Thermometer: Ear (Thermometer subtype)";
            }
            if (i == 832) {
                return "[" + i + "] Generic Heart rate Sensor (Generic category)";
            }
            if (i == 833) {
                return "[" + i + "] Heart Rate Sensor: Heart Rate Belt (Heart Rate Sensor subtype)";
            }
            if (i == 1280) {
                return "[" + i + "] Generic Network Device (Generic category)";
            }
            if (i != 1281) {
                switch (i) {
                    case 0:
                        break;
                    case 64:
                        return "[" + i + "] Generic Phone (Generic category)";
                    case 128:
                        return "[" + i + "] Generic Computer (Generic category)";
                    case 256:
                        return "[" + i + "] Generic Clock (Generic category)";
                    case 320:
                        return "[" + i + "] Generic Display (Generic category)";
                    case 384:
                        return "[" + i + "] Generic Remote Control (Generic category)";
                    case 448:
                        return "[" + i + "] Generic Eye-glasses (Generic category)";
                    case DfuBaseService.ERROR_REMOTE_TYPE_SECURE /* 512 */:
                        return "[" + i + "] Generic Tag (Generic category)";
                    case 576:
                        return "[" + i + "] Generic Keyring (Generic category)";
                    case 640:
                        return "[" + i + "] Generic Media Player (Generic category)";
                    case 704:
                        return "[" + i + "] Generic Barcode Scanner (Generic category)";
                    case 1024:
                        return "[" + i + "] Generic Glucose Meter (Generic category)";
                    case 1344:
                        return "[" + i + "] Generic Sensor (Generic category)";
                    case 1345:
                        return "[" + i + "] Motion Sensor (Sensor subtype)";
                    case 1346:
                        return "[" + i + "] Air Quality Sensor (Sensor subtype)";
                    case AppearanceLibrary.APPEARANCE_TEMPERATURE_SENSOR /* 1347 */:
                        return "[" + i + "] Temperature Sensor (Sensor subtype)";
                    case 1348:
                        return "[" + i + "] Humidity Sensor (Sensor subtype)";
                    case 1349:
                        return "[" + i + "] Leak Sensor (Sensor subtype)";
                    case 1350:
                        return "[" + i + "] Smoke Sensor (Sensor subtype)";
                    case 1351:
                        return "[" + i + "] Occupancy Sensor (Sensor subtype)";
                    case 1352:
                        return "[" + i + "] Contact Sensor (Sensor subtype)";
                    case 1353:
                        return "[" + i + "] Carbon Monoxide Sensor (Sensor subtype)";
                    case 1354:
                        return "[" + i + "] Carbon Dioxide Sensor (Sensor subtype)";
                    case 1355:
                        return "[" + i + "] Ambient Light Sensor (Sensor subtype)";
                    case 1356:
                        return "[" + i + "] Energy Sensor (Sensor subtype)";
                    case 1357:
                        return "[" + i + "] Color Light Sensor (Sensor subtype)";
                    case 1358:
                        return "[" + i + "] Rain Sensor (Sensor subtype)";
                    case 1359:
                        return "[" + i + "] Fire Sensor (Sensor subtype)";
                    case 1360:
                        return "[" + i + "] Wind Sensor (Sensor subtype)";
                    case AppearanceLibrary.APPEARANCE_PROXIMITY_SENSOR /* 1361 */:
                        return "[" + i + "] Proximity Sensor (Sensor subtype)";
                    case 1362:
                        return "[" + i + "] Multi-Sensor (Sensor subtype)";
                    case AppearanceLibrary.APPEARANCE_GENERIC_LIGHT /* 1408 */:
                        return "[" + i + "] Generic Light Fixtures (Generic category)";
                    case AppearanceLibrary.APPEARANCE_WALL_LIGHT /* 1409 */:
                        return "[" + i + "] Wall Light (Light Fixture subtype)";
                    case AppearanceLibrary.APPEARANCE_CEILING_LIGHT /* 1410 */:
                        return "[" + i + "] Ceiling Light (Light Fixture subtype)";
                    case AppearanceLibrary.APPEARANCE_FLOOR_LIGHT /* 1411 */:
                        return "[" + i + "] Floor Light (Light Fixture subtype)";
                    case AppearanceLibrary.APPEARANCE_CABINET_LIGHT /* 1412 */:
                        return "[" + i + "] Cabinet Light (Light Fixture subtype)";
                    case AppearanceLibrary.APPEARANCE_DESK_LIGHT /* 1413 */:
                        return "[" + i + "] Desk Light (Light Fixture subtype)";
                    case AppearanceLibrary.APPEARANCE_TROFFER_LIGHT /* 1414 */:
                        return "[" + i + "] Troffer Light (Light Fixture subtype)";
                    case AppearanceLibrary.APPEARANCE_PENDANT_LIGHT /* 1415 */:
                        return "[" + i + "] Pendant Light (Light Fixture subtype)";
                    case AppearanceLibrary.APPEARANCE_IN_GROUND_LIGHT /* 1416 */:
                        return "[" + i + "] In-ground Light (Light Fixture subtype)";
                    case AppearanceLibrary.APPEARANCE_FLOOD_LIGHT /* 1417 */:
                        return "[" + i + "] Flood Light (Light Fixture subtype)";
                    case AppearanceLibrary.APPEARANCE_UNDERWATER_LIGHT /* 1418 */:
                        return "[" + i + "] Underwater Light (Light Fixture subtype)";
                    case AppearanceLibrary.APPEARANCE_BOLLARD_WITH_LIGHT /* 1419 */:
                        return "[" + i + "] Bollard with Light (Light Fixture subtype)";
                    case AppearanceLibrary.APPEARANCE_PATHWAY_LIGHT /* 1420 */:
                        return "[" + i + "] Pathway Light (Light Fixture subtype)";
                    case AppearanceLibrary.APPEARANCE_GARDEN_LIGHT /* 1421 */:
                        return "[" + i + "] Garden Light (Light Fixture subtype)";
                    case AppearanceLibrary.APPEARANCE_POLE_TOP_LIGHT /* 1422 */:
                        return "[" + i + "] Pole-top Light (Light Fixture subtype)";
                    case AppearanceLibrary.APPEARANCE_SPOTLIGHT /* 1423 */:
                        return "[" + i + "] Spotlight (Light Fixture subtype)";
                    case AppearanceLibrary.APPEARANCE_LINEAR_LIGHT /* 1424 */:
                        return "[" + i + "] Linear Light (Light Fixture subtype)";
                    case AppearanceLibrary.APPEARANCE_STREET_LIGHT /* 1425 */:
                        return "[" + i + "] Street Light (Light Fixture subtype)";
                    case AppearanceLibrary.APPEARANCE_SHELVES_LIGHT /* 1426 */:
                        return "[" + i + "] Shelves Light (Light Fixture subtype)";
                    case AppearanceLibrary.APPEARANCE_HIGH_BAY_LOW_BAY_LIGHT /* 1427 */:
                        return "[" + i + "] High-bay / Low-bay Light (Light Fixture subtype)";
                    case AppearanceLibrary.APPEARANCE_EMERGENCY_EXIT_LIGH /* 1428 */:
                        return "[" + i + "] Emergency Exit Light (Light Fixture subtype)";
                    case 1472:
                        return "[" + i + "] Generic Fan (Generic category)";
                    case 1473:
                        return "[" + i + "] Ceiling Fan (Fan subtype)";
                    case 1474:
                        return "[" + i + "] Axial Fan (Fan subtype)";
                    case 1475:
                        return "[" + i + "] Exhaust Fan (Fan subtype)";
                    case 1476:
                        return "[" + i + "] Pedestal Fan (Fan subtype)";
                    case 1477:
                        return "[" + i + "] Desk Fan (Fan subtype)";
                    case 1478:
                        return "[" + i + "] Wall Fan (Fan subtype)";
                    case 1536:
                        return "[" + i + "] Generic HVAC (Generic category)";
                    case 1537:
                        return "[" + i + "] Thermostat (HVAC subtype)";
                    case 1600:
                        return "[" + i + "] Generic Air Conditioning (Generic category)";
                    case 1664:
                        return "[" + i + "] Generic Humidifier (Generic category)";
                    case 1728:
                        return "[" + i + "] Generic Heating (Generic category)";
                    case 1729:
                        return "[" + i + "] Radiator (Heater subtype)";
                    case 1730:
                        return "[" + i + "] Boiler (Heater subtype)";
                    case 1731:
                        return "[" + i + "] Heat Pump (Heater subtype)";
                    case 1732:
                        return "[" + i + "] Infrared Heater (Heater subtype)";
                    case 1733:
                        return "[" + i + "] Radiant Panel Heater (Heater subtype)";
                    case 1734:
                        return "[" + i + "] Fan Heater (Heater subtype)";
                    case 1735:
                        return "[" + i + "] Air Curtain (Heater subtype)";
                    case 1792:
                        return "[" + i + "] Generic Access Control (Generic category)";
                    case 1793:
                        return "[" + i + "] Access Door (Access subtype)";
                    case 1794:
                        return "[" + i + "] Garage Door (Access subtype)";
                    case 1795:
                        return "[" + i + "] Emergency Exit Door (Access subtype)";
                    case 1796:
                        return "[" + i + "] Access Lock (Access subtype)";
                    case 1797:
                        return "[" + i + "] Elevator (Access subtype)";
                    case 1798:
                        return "[" + i + "] Window (Access subtype)";
                    case 1799:
                        return "[" + i + "] Entrance Gate (Access subtype)";
                    case 1856:
                        return "[" + i + "] Generic Motorized Device (Generic category)";
                    case 1857:
                        return "[" + i + "] Motorized Gate (Motorized subtype)";
                    case 1858:
                        return "[" + i + "] Awning (Motorized subtype)";
                    case 1859:
                        return "[" + i + "] Blinds or Shades (Motorized subtype)";
                    case 1860:
                        return "[" + i + "] Curtains (Motorized subtype)";
                    case 1861:
                        return "[" + i + "] Screen (Motorized subtype)";
                    case 1920:
                        return "[" + i + "] Generic Power Device (Generic category)";
                    case 1921:
                        return "[" + i + "] Power Outlet (Power subtype)";
                    case 1922:
                        return "[" + i + "] Power Strip (Power subtype)";
                    case 1923:
                        return "[" + i + "] Plug (Power subtype)";
                    case 1924:
                        return "[" + i + "] Power Supply (Power subtype)";
                    case 1925:
                        return "[" + i + "] LED Driver (Power subtype)";
                    case 1926:
                        return "[" + i + "] Fluorescent Lamp Gear (Power subtype)";
                    case 1927:
                        return "[" + i + "] HID Lamp Gear (Power subtype)";
                    case AppearanceLibrary.APPEARANCE_GENERIC_LIGHT_SOURCE /* 1984 */:
                        return "[" + i + "] Generic Light Source (Generic category)";
                    case AppearanceLibrary.APPEARANCE_INCANDESCENT_LIGHT_BULB /* 1985 */:
                        return "[" + i + "] Incandescent Light Bulb (Light subtype)";
                    case AppearanceLibrary.APPEARANCE_LED_BULB /* 1986 */:
                        return "[" + i + "] LED Bulb (Light subtype)";
                    case AppearanceLibrary.APPEARANCE_HID_LAMP /* 1987 */:
                        return "[" + i + "] HID Lamp (Light subtype)";
                    case AppearanceLibrary.APPEARANCE_FLUORESCENT_LAMP /* 1988 */:
                        return "[" + i + "] Fluorescent Lamp (Light subtype)";
                    case AppearanceLibrary.APPEARANCE_LED_ARRAY /* 1989 */:
                        return "[" + i + "] LED Array (Light subtype)";
                    case AppearanceLibrary.APPEARANCE_MULTI_COLOR_LED_ARRAY /* 1990 */:
                        return "[" + i + "] Multi-Color LED Array (Light subtype)";
                    case 3136:
                        return "[" + i + "] Generic: Pulse Oximeter (Pulse Oximeter Generic Gategory)";
                    case 3137:
                        return "[" + i + "] Fingertip (Pulse Oximeter subtype)";
                    case 3138:
                        return "[" + i + "] Wrist Worn (Pulse Oximeter subtype)";
                    case 3200:
                        return "[" + i + "] Generic: Weight Scale (Weight Scale Generic Category)";
                    case 3264:
                        return "[" + i + "] Generic Personal Mobility Device (Personal Mobility Device)";
                    case 3265:
                        return "[" + i + "] Powered Wheelchair (Personal Mobility Device)";
                    case 3266:
                        return "[" + i + "] Mobility Scooter (Personal Mobility Device)";
                    case AppearanceLibrary.APPEARANCE_GENERIC_CONTINUOUS_GLUCOSE_METER /* 3328 */:
                        return "[" + i + "] Generic Continuous Glucose Monitor (Continuous Glucose Monitor)";
                    case 5184:
                        return "[" + i + "] Generic: Outdoor Sports Activity (Outdoor Sports Activity Generic Category)";
                    case 5185:
                        return "[" + i + "] Location Display Device (Outdoor Sports Activity subtype)";
                    case 5186:
                        return "[" + i + "] Location and Navigation Display Device (Outdoor Sports Activity subtype)";
                    case 5187:
                        return "[" + i + "] Location Pod (Outdoor Sports Activity subtype)";
                    case 5188:
                        return "[" + i + "] Location and Navigation Pod (Outdoor Sports Activity subtype)";
                    default:
                        switch (i) {
                            case AppearanceLibrary.APPEARANCE_GENERIC_BLOOD_PRESSURE /* 896 */:
                                return "[" + i + "] Generic Blood Pressure (Generic category)";
                            case AppearanceLibrary.APPEARANCE_BLOOD_PRESSURE_ARM /* 897 */:
                                return "[" + i + "] Blood Pressure: Arm (Blood Pressure subtype)";
                            case AppearanceLibrary.APPEARANCE_BLOOD_PRESSURE_WRIST /* 898 */:
                                return "[" + i + "] Blood Pressure: Wrist (Blood Pressure subtype)";
                            default:
                                switch (i) {
                                    case AppearanceLibrary.APPEARANCE_HID /* 960 */:
                                        return "[" + i + "] Human Interface Device (HID) (HID Generic)";
                                    case AppearanceLibrary.APPEARANCE_KEYBOARD /* 961 */:
                                        return "[" + i + "] Keyboard (HID subtype)";
                                    case AppearanceLibrary.APPEARANCE_MOUSE /* 962 */:
                                        return "[" + i + "] Mouse (HID subtype)";
                                    case 963:
                                        return "[" + i + "] Joystick (HID subtype)";
                                    case 964:
                                        return "[" + i + "] Gamepad (HID subtype)";
                                    case 965:
                                        return "[" + i + "] Digitizer Tablet (HID subtype)";
                                    case 966:
                                        return "[" + i + "] Card Reader (HID subtype)";
                                    case AppearanceLibrary.APPEARANCE_DIGITAL_PEN /* 967 */:
                                        return "[" + i + "] Digital Pen (HID subtype)";
                                    case 968:
                                        return "[" + i + "] Barcode Scanner (HID subtype)";
                                    default:
                                        switch (i) {
                                            case AppearanceLibrary.APPEARANCE_GENERIC_RUNNING_WALKING_SENSOR /* 1088 */:
                                                return "[" + i + "] Generic: Running Walking Sensor (Generic category)";
                                            case AppearanceLibrary.APPEARANCE_RUNNING_WALKING_SENSOR_IN_SHOE /* 1089 */:
                                                return "[" + i + "] Running Walking Sensor: In-Shoe (Running Walking Sensor subtype)";
                                            case AppearanceLibrary.APPEARANCE_RUNNING_WALKING_SENSOR_ON_SHOE /* 1090 */:
                                                return "[" + i + "] Running Walking Sensor: On-Shoe (Running Walking Sensor subtype)";
                                            case AppearanceLibrary.APPEARANCE_RUNNING_WALKING_SENSOR_ON_HIP /* 1091 */:
                                                return "[" + i + "] Running Walking Sensor: On-Hip (Running Walking Sensor subtype)";
                                            default:
                                                switch (i) {
                                                    case AppearanceLibrary.APPEARANCE_GENERIC_CYCLING /* 1152 */:
                                                        return "[" + i + "] Generic: Cycling (Generic category)";
                                                    case AppearanceLibrary.APPEARANCE_CYCLING_CYCLING_COMPUTER /* 1153 */:
                                                        return "[" + i + "] Cycling: Cycling Computer (Cycling subtype)";
                                                    case AppearanceLibrary.APPEARANCE_CYCLING_SPEED_SENSOR /* 1154 */:
                                                        return "[" + i + "] Cycling: Speed Sensor (Cycling subtype)";
                                                    case AppearanceLibrary.APPEARANCE_CYCLING_CADENCE_SENSOR /* 1155 */:
                                                        return "[" + i + "] Cycling: Cadence Sensor (Cycling subtype)";
                                                    case AppearanceLibrary.APPEARANCE_CYCLING_POWER_SENSOR /* 1156 */:
                                                        return "[" + i + "] Cycling: Power Sensor (Cycling subtype)";
                                                    case AppearanceLibrary.APPEARANCE_CYCLING_SPEED_AND_CADENCE_SENSOR /* 1157 */:
                                                        return "[" + i + "] Cycling: Speed and Cadence Sensor (Cycling subtype)";
                                                    default:
                                                        switch (i) {
                                                            case 1216:
                                                                return "[" + i + "] Generic Control Device (Generic category)";
                                                            case 1217:
                                                                return "[" + i + "] Switch (Control Device subtype)";
                                                            case 1218:
                                                                return "[" + i + "] Multi-switch (Control Device subtype)";
                                                            case 1219:
                                                                return "[" + i + "] Button (Control Device subtype)";
                                                            case 1220:
                                                                return "[" + i + "] Slider (Control Device subtype)";
                                                            case 1221:
                                                                return "[" + i + "] Rotary (Control Device subtype)";
                                                            case 1222:
                                                                return "[" + i + "] Touch-panel (Control Device subtype)";
                                                            default:
                                                                return "[" + i + "] Unknown";
                                                        }
                                                }
                                        }
                                }
                        }
                }
            } else {
                return "[" + i + "] Access Point (Generic Network subtype)";
            }
        }
        return "[" + i + "] Unknown";
    }

    public static void parse(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        short decodeAppearance = decodeAppearance(bArr, i);
        advData.setAppearance(decodeAppearance);
        dataUnion.addData("Appearance", getAppearanceAsString(decodeAppearance));
    }
}
