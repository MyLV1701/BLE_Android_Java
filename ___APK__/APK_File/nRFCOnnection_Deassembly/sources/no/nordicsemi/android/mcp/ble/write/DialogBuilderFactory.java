package no.nordicsemi.android.mcp.ble.write;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.preference.PreferenceManager;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.parser.UuidLibrary;
import no.nordicsemi.android.mcp.connection.DeviceDetailsFragment2;

/* loaded from: classes.dex */
public class DialogBuilderFactory extends UuidLibrary {
    private DialogBuilderFactory() {
    }

    public static void showDialog(DeviceDetailsFragment2 deviceDetailsFragment2, String str, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        WriteDialogBuilder defaultDialogBuilder;
        UUID uuid = bluetoothGattCharacteristic.getUuid();
        boolean z = true;
        if (PreferenceManager.getDefaultSharedPreferences(deviceDetailsFragment2.getActivity()).getBoolean(DeviceDetailsFragment2.PREFS_PARSE_KNOWN_CHAR, true)) {
            if (!UuidLibrary.DEVICE_NAME.equals(uuid) && !UuidLibrary.THINGY_DEVICE_NAME_CHARACTERISTIC.equals(uuid) && !UuidLibrary.THINGY_CLOUD_TOKEN.equals(uuid)) {
                if (!UuidLibrary.FIRMWARE_REVISION_STRING.equals(uuid) && !UuidLibrary.HARDWARE_REVISION_STRING.equals(uuid) && !UuidLibrary.MANUFACTURER_NAME_STRING.equals(uuid) && !UuidLibrary.SERIAL_NUMBER_STRING.equals(uuid) && !UuidLibrary.MODEL_NUMBER_STRING.equals(uuid) && !UuidLibrary.SOFTWARE_REVISION_STRING.equals(uuid)) {
                    if (!UuidLibrary.URI.equals(uuid) && !UuidLibrary.HTTP_HEADERS.equals(uuid) && !UuidLibrary.HTTP_ENTITY_BODY.equals(uuid)) {
                        if (!UuidLibrary.NORDIC_UART_TX.equals(uuid) && !UuidLibrary.NORDIC_UART_RX.equals(uuid)) {
                            if (UuidLibrary.LOCATION_NAME.equals(uuid)) {
                                defaultDialogBuilder = new StringDialogBuilder();
                            } else if (UuidLibrary.OBJECT_NAME.equals(uuid)) {
                                defaultDialogBuilder = new StringDialogBuilder();
                            } else if (!UuidLibrary.FIRST_NAME.equals(uuid) && !UuidLibrary.LAST_NAME.equals(uuid) && !UuidLibrary.EMAIL_ADDRESS.equals(uuid) && !UuidLibrary.LANGUAGE.equals(uuid)) {
                                if (UuidLibrary.MICROBIT_LED_TEXT.equals(uuid)) {
                                    defaultDialogBuilder = new StringDialogBuilder();
                                } else if (UuidLibrary.MICROBIT_LED_MATRIX_STATE.equals(uuid)) {
                                    defaultDialogBuilder = new MicrobitLedMatrixStateDialogBuilder();
                                } else if (UuidLibrary.HID_CONTROL_POINT.equals(uuid)) {
                                    defaultDialogBuilder = new HIDControlPointDialogBuilder();
                                } else if (UuidLibrary.PROTOCOL_MODE.equals(uuid)) {
                                    defaultDialogBuilder = new ProtocolModeDialogBuilder();
                                } else if (UuidLibrary.BODY_SENSOR_LOCATION.equals(uuid)) {
                                    defaultDialogBuilder = new BodySensorLocationDialogBuilder();
                                } else if (UuidLibrary.HEART_RATE_CONTROL_POINT.equals(uuid)) {
                                    defaultDialogBuilder = new HeartRateControlPointDialogBuilder();
                                } else if (UuidLibrary.DFU_CONTROL_POINT.equals(uuid)) {
                                    defaultDialogBuilder = new DfuControlPointDialogBuilder();
                                } else if (!UuidLibrary.SECURE_DFU_BUTTONLESS.equals(uuid) && !UuidLibrary.SECURE_DFU_BUTTONLESS_BONDS.equals(uuid) && !UuidLibrary.SECURE_DFU_EXPERIMENTAL_BUTTONLESS.equals(uuid) && !UuidLibrary.MICROBIT_DFU_CONTROL.equals(uuid)) {
                                    if (UuidLibrary.SECURE_DFU_CONTROL_POINT.equals(uuid)) {
                                        defaultDialogBuilder = new SecureDfuControlPointDialogBuilder();
                                    } else if (UuidLibrary.RECORD_ACCESS_CONTROL_POINT.equals(uuid)) {
                                        defaultDialogBuilder = new RecordAccessControlPointDialogBuilder();
                                    } else if (UuidLibrary.ALERT_LEVEL.equals(uuid)) {
                                        defaultDialogBuilder = new AlertLevelDialogBuilder();
                                    } else if (!UuidLibrary.BEACON_LED.equals(uuid) && !UuidLibrary.NORDIC_BLINKY_LED.equals(uuid)) {
                                        if (UuidLibrary.BEACON_MAJOR_MINOR.equals(uuid)) {
                                            defaultDialogBuilder = new BeaconMajorMinorBuilder();
                                        } else if (UuidLibrary.BEACON_CONN_INTERVAL.equals(uuid)) {
                                            defaultDialogBuilder = new BeaconConnIntervalBuilder();
                                        } else if (UuidLibrary.BEACON_RSSI.equals(uuid)) {
                                            defaultDialogBuilder = new BeaconCalibrationBuilder();
                                        } else if (UuidLibrary.URI_BEACON_V2_RESET.equals(uuid)) {
                                            defaultDialogBuilder = new ResetDialogBuilder();
                                        } else if (UuidLibrary.USER_CONTROL_POINT.equals(uuid)) {
                                            defaultDialogBuilder = new UserControlPointDialogBuilder();
                                        } else if (UuidLibrary.GENDER.equals(uuid)) {
                                            defaultDialogBuilder = new GenderDialogBuilder();
                                        } else if (UuidLibrary.NEW_ALERT.equals(uuid)) {
                                            defaultDialogBuilder = new NewAlertDialogBuilder();
                                        } else if (UuidLibrary.UNREAD_ALERT_STATUS.equals(uuid)) {
                                            defaultDialogBuilder = new UnreadAlertStatusDialogBuilder();
                                        } else if (!UuidLibrary.SUPPORTED_NEW_ALERT_CATEGORY.equals(uuid) && !UuidLibrary.SUPPORTED_UNREAD_ALERT_CATEGORY.equals(uuid)) {
                                            if (UuidLibrary.ALERT_NOTIFICATION_CONTROL_POINT.equals(uuid)) {
                                                defaultDialogBuilder = new AlertNotificationControlPointDialogBuilder();
                                            } else if (UuidLibrary.CANDY_CONTROL_POINT.equals(uuid)) {
                                                defaultDialogBuilder = new CandyControlPointDialogBuilder();
                                            } else if (UuidLibrary.EDDYSTONE_BROADCAST_CAPABILITIES.equals(uuid)) {
                                                defaultDialogBuilder = new EddystoneBroadcastCapabilitiesDialogBuilder();
                                            } else if (UuidLibrary.EDDYSTONE_ACTIVE_SLOT.equals(uuid)) {
                                                defaultDialogBuilder = new EddystoneActiveSlotDialogBuilder();
                                            } else if (UuidLibrary.EDDYSTONE_ADVERTISING_INTERVAL.equals(uuid)) {
                                                defaultDialogBuilder = new EddystoneAdvertisingIntervalDialogBuilder();
                                            } else if (UuidLibrary.EDDYSTONE_RADIO_TX_POWER.equals(uuid)) {
                                                defaultDialogBuilder = new EddystoneRadioTxPowerDialogBuilder();
                                            } else if (UuidLibrary.EDDYSTONE_ADVANCED_ADVERTISED_TX_POWER.equals(uuid)) {
                                                defaultDialogBuilder = new EddystoneAdvancedAdvertisedTxPowerDialogBuilder();
                                            } else if (UuidLibrary.EDDYSTONE_LOCK_STATE.equals(uuid)) {
                                                defaultDialogBuilder = new EddystoneLockStateDialogBuilder();
                                            } else if (UuidLibrary.EDDYSTONE_UNLOCK.equals(uuid)) {
                                                defaultDialogBuilder = new EddystoneUnlockDialogBuilder();
                                            } else if (UuidLibrary.EDDYSTONE_ECDH_KEY.equals(uuid)) {
                                                defaultDialogBuilder = new EddystoneEcdhKeyDialogBuilder();
                                            } else if (UuidLibrary.EDDYSTONE_EID_IDENTITY_KEY.equals(uuid)) {
                                                defaultDialogBuilder = new EddystoneEidIdentityKeyDialogBuilder();
                                            } else if (UuidLibrary.EDDYSTONE_READ_WRITE_ADV_SLOT.equals(uuid)) {
                                                defaultDialogBuilder = new EddystoneReadWriteAdvSlotDialogBuilder();
                                            } else if (UuidLibrary.EDDYSTONE_ADVANCED_FACTORY_RESET.equals(uuid)) {
                                                defaultDialogBuilder = new EddystoneAdvancedFactoryResetDialogBuilder();
                                            } else if (UuidLibrary.EDDYSTONE_ADVANCED_REMAIN_CONNECTABLE.equals(uuid)) {
                                                defaultDialogBuilder = new EddystoneAdvancedRemainConnectableDialogBuilder();
                                            } else if (UuidLibrary.SMP_CHARACTERISTIC.equals(uuid)) {
                                                defaultDialogBuilder = new McuMgrSmpDialogBuilder();
                                            } else {
                                                BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(UuidLibrary.CHARACTERISTIC_PRESENTATION_FORMAT);
                                                if (descriptor != null && descriptor.getValue() != null && descriptor.getValue().length == 7) {
                                                    if (descriptor.getValue()[0] == 25) {
                                                        defaultDialogBuilder = new StringDialogBuilder();
                                                    } else if (descriptor.getValue()[0] == 26) {
                                                        defaultDialogBuilder = StringDialogBuilder.utf16();
                                                    } else {
                                                        defaultDialogBuilder = DefaultDialogBuilder.getInstance(false);
                                                    }
                                                } else {
                                                    defaultDialogBuilder = DefaultDialogBuilder.getInstance(false);
                                                }
                                            }
                                        } else {
                                            defaultDialogBuilder = new SupportedAlertCategoriesDialogBuilder();
                                        }
                                    } else {
                                        defaultDialogBuilder = new OnOffDialogBuilder();
                                    }
                                } else {
                                    defaultDialogBuilder = new DfuButtonlessDialogBuilder();
                                }
                            } else {
                                defaultDialogBuilder = new StringDialogBuilder();
                            }
                        } else {
                            defaultDialogBuilder = new StringDialogBuilder();
                        }
                    } else {
                        defaultDialogBuilder = new StringDialogBuilder();
                    }
                } else {
                    defaultDialogBuilder = new StringDialogBuilder();
                }
            } else {
                defaultDialogBuilder = new StringDialogBuilder();
            }
        } else {
            if (!UuidLibrary.DEVICE_NAME.equals(uuid) && !UuidLibrary.FIRMWARE_REVISION_STRING.equals(uuid) && !UuidLibrary.HARDWARE_REVISION_STRING.equals(uuid) && !UuidLibrary.MANUFACTURER_NAME_STRING.equals(uuid) && !UuidLibrary.SERIAL_NUMBER_STRING.equals(uuid) && !UuidLibrary.MODEL_NUMBER_STRING.equals(uuid) && !UuidLibrary.SOFTWARE_REVISION_STRING.equals(uuid) && !UuidLibrary.THINGY_DEVICE_NAME_CHARACTERISTIC.equals(uuid) && !UuidLibrary.URI.equals(uuid) && !UuidLibrary.HTTP_HEADERS.equals(uuid) && !UuidLibrary.HTTP_ENTITY_BODY.equals(uuid) && !UuidLibrary.OBJECT_NAME.equals(uuid) && !UuidLibrary.NORDIC_UART_TX.equals(uuid) && !UuidLibrary.NORDIC_UART_RX.equals(uuid) && !UuidLibrary.LOCATION_NAME.equals(uuid) && !UuidLibrary.FIRST_NAME.equals(uuid) && !UuidLibrary.LAST_NAME.equals(uuid) && !UuidLibrary.EMAIL_ADDRESS.equals(uuid) && !UuidLibrary.LANGUAGE.equals(uuid) && !UuidLibrary.HID_CONTROL_POINT.equals(uuid) && !UuidLibrary.PROTOCOL_MODE.equals(uuid) && !UuidLibrary.BODY_SENSOR_LOCATION.equals(uuid) && !UuidLibrary.HEART_RATE_CONTROL_POINT.equals(uuid) && !UuidLibrary.DFU_CONTROL_POINT.equals(uuid) && !UuidLibrary.SECURE_DFU_BUTTONLESS.equals(uuid) && !UuidLibrary.SECURE_DFU_EXPERIMENTAL_BUTTONLESS.equals(uuid) && !UuidLibrary.MICROBIT_DFU_CONTROL.equals(uuid) && !UuidLibrary.MICROBIT_LED_MATRIX_STATE.equals(uuid) && !UuidLibrary.RECORD_ACCESS_CONTROL_POINT.equals(uuid) && !UuidLibrary.ALERT_LEVEL.equals(uuid) && !UuidLibrary.BEACON_LED.equals(uuid) && !UuidLibrary.BEACON_MAJOR_MINOR.equals(uuid) && !UuidLibrary.BEACON_CONN_INTERVAL.equals(uuid) && !UuidLibrary.BEACON_RSSI.equals(uuid) && !UuidLibrary.NORDIC_BLINKY_LED.equals(uuid) && !UuidLibrary.URI_BEACON_V2_RESET.equals(uuid) && !UuidLibrary.USER_CONTROL_POINT.equals(uuid) && !UuidLibrary.NEW_ALERT.equals(uuid) && !UuidLibrary.UNREAD_ALERT_STATUS.equals(uuid) && !UuidLibrary.SUPPORTED_UNREAD_ALERT_CATEGORY.equals(uuid) && !UuidLibrary.SUPPORTED_NEW_ALERT_CATEGORY.equals(uuid) && !UuidLibrary.ALERT_NOTIFICATION_CONTROL_POINT.equals(uuid) && !UuidLibrary.GENDER.equals(uuid) && !UuidLibrary.CANDY_CONTROL_POINT.equals(uuid) && !UuidLibrary.EDDYSTONE_BROADCAST_CAPABILITIES.equals(uuid) && !UuidLibrary.EDDYSTONE_ACTIVE_SLOT.equals(uuid) && !UuidLibrary.EDDYSTONE_ADVERTISING_INTERVAL.equals(uuid) && !UuidLibrary.EDDYSTONE_RADIO_TX_POWER.equals(uuid) && !UuidLibrary.EDDYSTONE_ADVANCED_ADVERTISED_TX_POWER.equals(uuid) && !UuidLibrary.EDDYSTONE_LOCK_STATE.equals(uuid) && !UuidLibrary.EDDYSTONE_UNLOCK.equals(uuid) && !UuidLibrary.EDDYSTONE_ECDH_KEY.equals(uuid) && !UuidLibrary.EDDYSTONE_EID_IDENTITY_KEY.equals(uuid) && !UuidLibrary.EDDYSTONE_READ_WRITE_ADV_SLOT.equals(uuid) && !UuidLibrary.EDDYSTONE_ADVANCED_FACTORY_RESET.equals(uuid) && !UuidLibrary.EDDYSTONE_ADVANCED_REMAIN_CONNECTABLE.equals(uuid) && !UuidLibrary.SMP_CHARACTERISTIC.equals(uuid)) {
                z = false;
            }
            defaultDialogBuilder = DefaultDialogBuilder.getInstance(z);
        }
        defaultDialogBuilder.setAction(i);
        defaultDialogBuilder.setDeviceAddress(str);
        defaultDialogBuilder.setCharacteristic(bluetoothGattCharacteristic);
        defaultDialogBuilder.show(deviceDetailsFragment2.getChildFragmentManager(), (String) null);
    }

    public static void showDialog(DeviceDetailsFragment2 deviceDetailsFragment2, String str, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        WriteDialogBuilder defaultDialogBuilder;
        UUID uuid = bluetoothGattDescriptor.getUuid();
        boolean z = true;
        if (PreferenceManager.getDefaultSharedPreferences(deviceDetailsFragment2.getActivity()).getBoolean(DeviceDetailsFragment2.PREFS_PARSE_KNOWN_CHAR, true)) {
            if (UuidLibrary.CHARACTERISTIC_USER_DESCRIPTION.equals(uuid)) {
                defaultDialogBuilder = new StringDialogBuilder();
            } else if (UuidLibrary.ENVIRONMENTAL_SENSING_CONFIGURATION.equals(uuid)) {
                defaultDialogBuilder = new ESConfigurationDescriptorDialogBuilder();
            } else {
                defaultDialogBuilder = DefaultDialogBuilder.getInstance(false);
            }
        } else {
            if (!UuidLibrary.CHARACTERISTIC_USER_DESCRIPTION.equals(uuid) && !UuidLibrary.ENVIRONMENTAL_SENSING_CONFIGURATION.equals(uuid)) {
                z = false;
            }
            defaultDialogBuilder = DefaultDialogBuilder.getInstance(z);
        }
        defaultDialogBuilder.setAction(i);
        defaultDialogBuilder.setDeviceAddress(str);
        defaultDialogBuilder.setDescriptor(bluetoothGattDescriptor);
        defaultDialogBuilder.show(deviceDetailsFragment2.getChildFragmentManager(), (String) null);
    }
}
