package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import io.runtime.mcumgr.McuMgrHeader;
import io.runtime.mcumgr.util.CBOR;
import java.io.IOException;
import java.util.Arrays;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class SmpParser implements ICharacteristicParser {
    private static final int CONFIG_ID_CONFIG = 0;
    private static final int DEFAULT_ID_CONS_ECHO_CTRL = 1;
    private static final int DEFAULT_ID_DATETIME_STR = 4;
    private static final int DEFAULT_ID_ECHO = 0;
    private static final int DEFAULT_ID_MPSTATS = 3;
    private static final int DEFAULT_ID_RESET = 5;
    private static final int DEFAULT_ID_TASKSTATS = 2;
    private static final int FS_ID_FILE = 0;
    private static final int GROUP_CONFIG = 3;
    private static final int GROUP_CRASH = 5;
    private static final int GROUP_DEFAULT = 0;
    private static final int GROUP_FS = 8;
    private static final int GROUP_IMAGE = 1;
    private static final int GROUP_LOGS = 4;
    private static final int GROUP_PERUSER = 64;
    private static final int GROUP_RUN = 7;
    private static final int GROUP_SPLIT = 6;
    private static final int GROUP_STATS = 2;
    private static final int IMAGE_ID_CORELIST = 3;
    private static final int IMAGE_ID_CORELOAD = 4;
    private static final int IMAGE_ID_ERASE = 5;
    private static final int IMAGE_ID_ERASE_STATE = 6;
    private static final int IMAGE_ID_FILE = 2;
    private static final int IMAGE_ID_STATE = 0;
    private static final int IMAGE_ID_UPLOAD = 1;
    private static final int LOGS_ID_APPEND = 2;
    private static final int LOGS_ID_CLEAR = 1;
    private static final int LOGS_ID_LEVEL_LIST = 4;
    private static final int LOGS_ID_LOGS_LIST = 5;
    private static final int LOGS_ID_MODULE_LIST = 3;
    private static final int LOGS_ID_READ = 0;
    private static final int OP_READ = 0;
    private static final int OP_READ_RSP = 1;
    private static final int OP_WRITE = 2;
    private static final int OP_WRITE_RSP = 3;
    private static final int STATS_ID_LIST = 1;
    private static final int STATS_ID_READ = 0;
    private byte[] buffer;
    private int lastHash;
    private String lastValue;
    private int offset = 0;
    private int expectedLength = 0;

    private String command2String(int i, int i2) {
        switch (i) {
            case 0:
                return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "(UNKNOWN)" : "(RESET)" : "(DATETIME STR)" : "(MPSTATS)" : "(TASKSTATS)" : "(CONS ECHO CTRL)" : "(ECHO)";
            case 1:
                switch (i2) {
                    case 0:
                        return "(STATE)";
                    case 1:
                        return "(UPLOAD)";
                    case 2:
                        return "(FILE)";
                    case 3:
                        return "(CORE LIST)";
                    case 4:
                        return "(CORE LOAD)";
                    case 5:
                        return "(ERASE)";
                    case 6:
                        return "(ERASE STATE)";
                    default:
                        return "(UNKNOWN)";
                }
            case 2:
                return i2 != 0 ? i2 != 1 ? "(UNKNOWN)" : "(LIST)" : "(READ)";
            case 3:
                return i2 != 0 ? "(UNKNOWN)" : "(CONFIG)";
            case 4:
                return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "(UNKNOWN)" : "(LOGS LIST)" : "(LEVEL LIST)" : "(MODULE LIST)" : "(APPEND)" : "(CLEAR)" : "(READ)";
            case 5:
            case 6:
            case 7:
            default:
                return "(UNKNOWN)";
            case 8:
                return i2 != 0 ? "(UNKNOWN)" : "(FILE)";
        }
    }

    private String group2String(int i) {
        if (i == 64) {
            return "(PER USER)";
        }
        switch (i) {
            case 0:
                return "(OS)";
            case 1:
                return "(IMAGE)";
            case 2:
                return "(STATS)";
            case 3:
                return "(CONFIG)";
            case 4:
                return "(LOGS)";
            case 5:
                return "(CRASH)";
            case 6:
                return "(SPLIT)";
            case 7:
                return "(RUN)";
            case 8:
                return "(FS)";
            default:
                return "(UNKNOWN)";
        }
    }

    private String op2String(int i) {
        if (i == 0) {
            return "READ";
        }
        if (i == 1) {
            return "READ RESPONSE";
        }
        if (i == 2) {
            return "WRITE";
        }
        if (i == 3) {
            return "WRITE RESPONSE";
        }
        return "UNKNOWN (" + i + ")";
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public synchronized String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        int hashCode = Arrays.hashCode(value);
        if (hashCode == this.lastHash) {
            return this.lastValue;
        }
        this.lastHash = hashCode;
        if (this.buffer == null && value.length < 8) {
            return "Invalid data syntax: " + GeneralCharacteristicParser.parse(bluetoothGattCharacteristic);
        }
        StringBuilder sb = new StringBuilder();
        if (this.buffer == null) {
            McuMgrHeader fromBytes = McuMgrHeader.fromBytes(Arrays.copyOf(value, 8));
            sb.append("Operation: ");
            sb.append(op2String(fromBytes.getOp()));
            sb.append("\n");
            sb.append("Flags: ");
            sb.append(fromBytes.getFlags());
            sb.append("\n");
            sb.append("Length: ");
            sb.append(fromBytes.getLen());
            sb.append("\n");
            sb.append("Group Id: ");
            sb.append(fromBytes.getGroupId());
            sb.append(" ");
            sb.append(group2String(fromBytes.getGroupId()));
            sb.append("\n");
            sb.append("Sequence Num: ");
            sb.append(fromBytes.getSequenceNum());
            sb.append("\n");
            sb.append("Command Id: ");
            sb.append(fromBytes.getCommandId());
            sb.append(" ");
            sb.append(command2String(fromBytes.getGroupId(), fromBytes.getCommandId()));
            sb.append("\n");
            if (fromBytes.getLen() > 0) {
                this.buffer = new byte[fromBytes.getLen()];
                int length = value.length - 8;
                System.arraycopy(value, 8, this.buffer, this.offset, length);
                this.offset = length;
                this.expectedLength = fromBytes.getLen();
            }
        } else {
            int length2 = value.length;
            System.arraycopy(value, 0, this.buffer, this.offset, length2);
            this.offset += length2;
        }
        if (this.buffer == null) {
            sb.append("No message");
        } else if (this.offset == this.expectedLength) {
            try {
                sb.append("Message: ");
                sb.append(CBOR.toString(this.buffer));
            } catch (IOException unused) {
                sb.append("Invalid CBOR message: ");
                sb.append(GeneralCharacteristicParser.parse(value, 8));
            }
            this.buffer = null;
            this.offset = 0;
            this.expectedLength = 0;
        } else {
            sb.append("Incomplete CBOR message");
        }
        String sb2 = sb.toString();
        this.lastValue = sb2;
        return sb2;
    }
}
