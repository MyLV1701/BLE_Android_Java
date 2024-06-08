package e.a.a.a;

import no.nordicsemi.android.ble.error.GattError;
import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
public class a {
    public static String a(int i) {
        if (i == 26) {
            return "HCI ERROR UNSUPPORTED REMOTE FEATURE";
        }
        if (i == 30) {
            return "HCI ERROR INVALID LMP PARAM";
        }
        if (i == 34) {
            return "GATT CONN LMP TIMEOUT";
        }
        if (i == 42) {
            return "HCI ERROR DIFF TRANSACTION COLLISION";
        }
        if (i == 257) {
            return "TOO MANY OPEN CONNECTIONS";
        }
        if (i == 58) {
            return "GATT CONTROLLER BUSY";
        }
        if (i == 59) {
            return "GATT UNACCEPT CONN INTERVAL";
        }
        switch (i) {
            case 1:
                return "GATT INVALID HANDLE";
            case 2:
                return "GATT READ NOT PERMIT";
            case 3:
                return "GATT WRITE NOT PERMIT";
            case 4:
                return "GATT INVALID PDU";
            case 5:
                return "GATT INSUF AUTHENTICATION";
            case 6:
                return "GATT REQ NOT SUPPORTED";
            case 7:
                return "GATT INVALID OFFSET";
            case 8:
                return "GATT INSUF AUTHORIZATION";
            case 9:
                return "GATT PREPARE Q FULL";
            case 10:
                return "GATT NOT FOUND";
            case 11:
                return "GATT NOT LONG";
            case 12:
                return "GATT INSUF KEY SIZE";
            case 13:
                return "GATT INVALID ATTR LEN";
            case 14:
                return "GATT ERR UNLIKELY";
            case 15:
                return "GATT INSUF ENCRYPTION";
            case 16:
                return "GATT UNSUPPORT GRP TYPE";
            case 17:
                return "GATT INSUF RESOURCE";
            default:
                switch (i) {
                    case 128:
                        return "GATT NO RESOURCES";
                    case GattError.GATT_INTERNAL_ERROR /* 129 */:
                        return "GATT INTERNAL ERROR";
                    case GattError.GATT_WRONG_STATE /* 130 */:
                        return "GATT WRONG STATE";
                    case GattError.GATT_DB_FULL /* 131 */:
                        return "GATT DB FULL";
                    case GattError.GATT_BUSY /* 132 */:
                        return "GATT BUSY";
                    case GattError.GATT_ERROR /* 133 */:
                        return "GATT ERROR";
                    case GattError.GATT_CMD_STARTED /* 134 */:
                        return "GATT CMD STARTED";
                    case GattError.GATT_ILLEGAL_PARAMETER /* 135 */:
                        return "GATT ILLEGAL PARAMETER";
                    case GattError.GATT_PENDING /* 136 */:
                        return "GATT PENDING";
                    case GattError.GATT_AUTH_FAIL /* 137 */:
                        return "GATT AUTH FAIL";
                    case GattError.GATT_MORE /* 138 */:
                        return "GATT MORE";
                    case GattError.GATT_INVALID_CFG /* 139 */:
                        return "GATT INVALID CFG";
                    case GattError.GATT_SERVICE_STARTED /* 140 */:
                        return "GATT SERVICE STARTED";
                    case GattError.GATT_ENCRYPTED_NO_MITM /* 141 */:
                        return "GATT ENCRYPTED NO MITM";
                    case GattError.GATT_NOT_ENCRYPTED /* 142 */:
                        return "GATT NOT ENCRYPTED";
                    case GattError.GATT_CONGESTED /* 143 */:
                        return "GATT CONGESTED";
                    default:
                        switch (i) {
                            case GattError.GATT_CCCD_CFG_ERROR /* 253 */:
                                return "GATT CCCD CFG ERROR";
                            case GattError.GATT_PROCEDURE_IN_PROGRESS /* 254 */:
                                return "GATT PROCEDURE IN PROGRESS";
                            case 255:
                                return "GATT VALUE OUT OF RANGE";
                            default:
                                switch (i) {
                                    case 4096:
                                        return "DFU DEVICE DISCONNECTED";
                                    case DfuBaseService.ERROR_FILE_NOT_FOUND /* 4097 */:
                                        return "DFU FILE NOT FOUND";
                                    case DfuBaseService.ERROR_FILE_ERROR /* 4098 */:
                                        return "DFU FILE ERROR";
                                    case DfuBaseService.ERROR_FILE_INVALID /* 4099 */:
                                        return "DFU NOT A VALID HEX FILE";
                                    case DfuBaseService.ERROR_FILE_IO_EXCEPTION /* 4100 */:
                                        return "DFU IO EXCEPTION";
                                    case DfuBaseService.ERROR_SERVICE_DISCOVERY_NOT_STARTED /* 4101 */:
                                        return "DFU SERVICE DISCOVERY NOT STARTED";
                                    case DfuBaseService.ERROR_SERVICE_NOT_FOUND /* 4102 */:
                                        return "DFU CHARACTERISTICS NOT FOUND";
                                    default:
                                        switch (i) {
                                            case DfuBaseService.ERROR_INVALID_RESPONSE /* 4104 */:
                                                return "DFU INVALID RESPONSE";
                                            case DfuBaseService.ERROR_FILE_TYPE_UNSUPPORTED /* 4105 */:
                                                return "DFU FILE TYPE NOT SUPPORTED";
                                            case DfuBaseService.ERROR_BLUETOOTH_DISABLED /* 4106 */:
                                                return "BLUETOOTH ADAPTER DISABLED";
                                            case DfuBaseService.ERROR_INIT_PACKET_REQUIRED /* 4107 */:
                                            case DfuBaseService.ERROR_FILE_SIZE_INVALID /* 4108 */:
                                                return "DFU INIT PACKET REQUIRED";
                                            case DfuBaseService.ERROR_CRC_ERROR /* 4109 */:
                                                return "DFU CRC ERROR";
                                            case DfuBaseService.ERROR_DEVICE_NOT_BONDED /* 4110 */:
                                                return "DFU DEVICE NOT BONDED";
                                            default:
                                                return "UNKNOWN (" + i + ")";
                                        }
                                }
                        }
                }
        }
    }

    public static String b(int i) {
        if (i == 0) {
            return "SUCCESS";
        }
        if (i == 1) {
            return "GATT CONN L2C FAILURE";
        }
        if (i == 8) {
            return "GATT CONN TIMEOUT";
        }
        if (i == 19) {
            return "GATT CONN TERMINATE PEER USER";
        }
        if (i == 22) {
            return "GATT CONN TERMINATE LOCAL HOST";
        }
        if (i == 34) {
            return "GATT CONN LMP TIMEOUT";
        }
        if (i == 62) {
            return "GATT CONN FAIL ESTABLISH";
        }
        if (i == 133) {
            return "GATT ERROR";
        }
        if (i == 256) {
            return "GATT CONN CANCEL ";
        }
        return "UNKNOWN (" + i + ")";
    }

    public static String c(int i) {
        int i2 = i & 3840;
        if (i2 == 256) {
            return b.a(i);
        }
        if (i2 == 512) {
            return c.a(i);
        }
        if (i2 == 1024) {
            return c.c(i);
        }
        if (i2 != 2048) {
            return "UNKNOWN (" + i + ")";
        }
        return c.b(i);
    }
}
