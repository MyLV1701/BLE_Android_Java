package no.nordicsemi.android.mcp.database.init;

import android.bluetooth.BluetoothGattCharacteristic;
import java.util.UUID;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ThirdPartyCharacteristics {
    public static final UUID EDDYSTONE_ACTIVE_SLOT_UUID = new UUID(-6644932596338570273L, -8486575981020192107L);
    public static final UUID EDDYSTONE_LOCK_STATE_UUID = new UUID(-6644932579158701089L, -8486575981020192107L);
    public static final UUID EDDYSTONE_UNLOCK_UUID = new UUID(-6644932574863733793L, -8486575981020192107L);
    public static final UUID EDDYSTONE_EID_IDENTITY_KEY_UUID = new UUID(-6644932566273799201L, -8486575981020192107L);
    public static final UUID EDDYSTONE_ADV_SLOT_DATA_UUID = new UUID(-6644932561978831905L, -8486575981020192107L);
    public static final UUID SMP_CHARACTERISTIC_UUID = new UUID(-2725108607093944831L, -5864207808377291704L);

    public static void initialize(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(23300500811742L, 1523193452336828707L, "DFU Control Point", (String) null);
        databaseHelper.addCharacteristic(23304795779038L, 1523193452336828707L, "DFU Packet", (String) null);
    }

    public static void initializeDBVersion_2_0(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(7944349754318910355L, -2258021889238840674L, "RX Characteristic", (String) null);
        databaseHelper.addCharacteristic(7944349758613877651L, -2258021889238840674L, "TX Characteristic", (String) null);
        databaseHelper.addCharacteristic(-4749680361074179970L, -8970875957782147596L, "Anki Drive Vehicle Service READ", (String) null);
        databaseHelper.addCharacteristic(-4749680356779212674L, -8970875957782147596L, "Anki Drive Vehicle Service WRITE", (String) null);
    }

    public static void initializeDBVersion_2_1(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(23313385713630L, 1523193452336828707L, "DFU Version", (String) null);
        databaseHelper.addCharacteristic(-7684806569524791894L, -6875724801706301267L, "Beacon UUID", (String) null);
        databaseHelper.addCharacteristic(-7684806565229824598L, -6875724801706301267L, "Calibration", (String) null);
        databaseHelper.addCharacteristic(-7684806560934857302L, -6875724801706301267L, "Major & Minor", (String) null);
        databaseHelper.addCharacteristic(-7684806556639890006L, -6875724801706301267L, "Manufacturer ID", (String) null);
        databaseHelper.addCharacteristic(-7684806552344922710L, -6875724801706301267L, "Connection Interval", (String) null);
        databaseHelper.addCharacteristic(-7684806548049955414L, -6875724801706301267L, "LED Config", (String) null);
        databaseHelper.addCharacteristic(-5522119407866458791L, -8103675199899129497L, "Data One", (String) null);
        databaseHelper.addCharacteristic(-5522119403571491495L, -8103675199899129497L, "Data Two", (String) null);
        databaseHelper.addCharacteristic(-5522119399276524199L, -8103675199899129497L, "Data Length", (String) null);
        databaseHelper.addCharacteristic(-1293623252265582406L, -6082505226419863080L, "Lock State", (String) null);
        databaseHelper.addCharacteristic(-1293623247970615110L, -6082505226419863080L, "Lock", (String) null);
        databaseHelper.addCharacteristic(-1293623243675647814L, -6082505226419863080L, "Unlock", (String) null);
        databaseHelper.addCharacteristic(-1293623239380680518L, -6082505226419863080L, "Data", (String) null);
        databaseHelper.addCharacteristic(-1293623235085713222L, -6082505226419863080L, "Flags", (String) null);
        databaseHelper.addCharacteristic(-1293623230790745926L, -6082505226419863080L, "Power Levels", (String) null);
        databaseHelper.addCharacteristic(-1293623226495778630L, -6082505226419863080L, "Power Mode", (String) null);
        databaseHelper.addCharacteristic(-1293623222200811334L, -6082505226419863080L, "Period", (String) null);
        databaseHelper.addCharacteristic(-1293623217905844038L, -6082505226419863080L, "Reset", (String) null);
    }

    public static void initializeDBVersion_3_2(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(-8628896873536912306L, -4233388132819706284L, "Fly Button Characteristic", (String) null);
    }

    public static void initializeDBVersion_4_0(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(23244666236894L, 1523193452336828707L, "Button", (String) null);
        databaseHelper.addCharacteristic(23248961204190L, 1523193452336828707L, "LED", (String) null);
        databaseHelper.addCharacteristic(-6430274709903424194L, -7630548399806483608L, "Candy Control Point", (String) null);
    }

    public static void initializeDBVersion_4_1(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(-6644932596338570273L, -8486575981020192107L, "Active Slot", (String) null);
        databaseHelper.addCharacteristic(-6644932592043602977L, -8486575981020192107L, "Advertising Interval", (String) null);
        databaseHelper.addCharacteristic(-6644932587748635681L, -8486575981020192107L, "Radio Tx Power", (String) null);
        databaseHelper.addCharacteristic(-6644932583453668385L, -8486575981020192107L, "(Advanced) Advertised Tx Power", (String) null);
        databaseHelper.addCharacteristic(-6644932579158701089L, -8486575981020192107L, "Lock State", (String) null);
        databaseHelper.addCharacteristic(-6644932574863733793L, -8486575981020192107L, "Unlock", (String) null);
        databaseHelper.addCharacteristic(-6644932570568766497L, -8486575981020192107L, "Public ECDH Key", (String) null);
        databaseHelper.addCharacteristic(-6644932566273799201L, -8486575981020192107L, "EID Identity Key", (String) null);
        databaseHelper.addCharacteristic(-6644932557683864609L, -8486575981020192107L, "(Advanced) Factory Reset", (String) null);
        databaseHelper.addCharacteristic(-6644932553388897313L, -8486575981020192107L, "(Advanced) Remain Connectable", (String) null);
    }

    public static void initializeDBVersion_4_12(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(-8157989228746813600L, -6937650605005804976L, "Secure Buttonless DFU", (String) null);
        databaseHelper.addCharacteristic(-1195704594656310989L, -7273222140190261182L, "Thingy Name Characteristic", null, 1);
        databaseHelper.addCharacteristic(-1195704590361343693L, -7273222140190261182L, "Thingy Advertising Parameters Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195704581771409101L, -7273222140190261182L, "Thingy Connection Parameters Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195704577476441805L, -7273222140190261182L, "Thingy Eddystone URL Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195704573181474509L, -7273222140190261182L, "Thingy Cloud Token Characteristic", null, 1);
        databaseHelper.addCharacteristic(-1195704568886507213L, -7273222140190261182L, "Thingy FW Version Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195704564591539917L, -7273222140190261182L, "Thingy MTU Request Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195703495144683213L, -7273222140190261182L, "Thingy Temperature Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195703490849715917L, -7273222140190261182L, "Thingy Pressure Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195703486554748621L, -7273222140190261182L, "Thingy Humidity Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195703482259781325L, -7273222140190261182L, "Thingy Air Quality Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195703477964814029L, -7273222140190261182L, "Thingy Light Intensity Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195703473669846733L, -7273222140190261182L, "Thingy Environment Configuration Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195702395633055437L, -7273222140190261182L, "Thingy LED Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195702391338088141L, -7273222140190261182L, "Thingy Button Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195702387043120845L, -7273222140190261182L, "Thingy External Pin Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195701296121427661L, -7273222140190261182L, "Thingy Motion Configuration Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195701291826460365L, -7273222140190261182L, "Thingy Tap Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195701287531493069L, -7273222140190261182L, "Thingy Orientation Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195701283236525773L, -7273222140190261182L, "Thingy Quaternion Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195701278941558477L, -7273222140190261182L, "Thingy Pedometer Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195701274646591181L, -7273222140190261182L, "Thingy Raw Data Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195701270351623885L, -7273222140190261182L, "Thingy Euler Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195701266056656589L, -7273222140190261182L, "Thingy Rotation Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195701261761689293L, -7273222140190261182L, "Thingy Heading Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195701257466721997L, -7273222140190261182L, "Thingy Gravity Vector Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195700196609799885L, -7273222140190261182L, "Thingy Sound Configuration Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195700192314832589L, -7273222140190261182L, "Thingy Speaker Data Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195700188019865293L, -7273222140190261182L, "Thingy Speaker Status Characteristic", (String) null);
        databaseHelper.addCharacteristic(-1195700183724897997L, -7273222140190261182L, "Thingy Microphone Characteristic", (String) null);
    }

    public static void initializeDBVersion_4_14_4(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(-1630925065944217846L, -6889669494057227864L, "micro:bit Accelerometer Data", (String) null);
        databaseHelper.addCharacteristic(-1630871357378181366L, -6889669494057227864L, "micro:bit Accelerometer Period", (String) null);
        databaseHelper.addCharacteristic(-1630871438982559990L, -6889669494057227864L, "micro:bit Magnetometer Data", (String) null);
        databaseHelper.addCharacteristic(-1631085452907952374L, -6889669494057227864L, "micro:bit Magnetometer Period", (String) null);
        databaseHelper.addCharacteristic(-1630981372965468406L, -6889669494057227864L, "micro:bit Magnetometer Bearing", (String) null);
        databaseHelper.addCharacteristic(-1630907177405430006L, -6889669494057227864L, "micro:bit Button A State", (String) null);
        databaseHelper.addCharacteristic(-1630907173110462710L, -6889669494057227864L, "micro:bit Button B State", (String) null);
        databaseHelper.addCharacteristic(-1630992458276059382L, -6889669494057227864L, "micro:bit Pin Data", (String) null);
        databaseHelper.addCharacteristic(-1631050075262335222L, -6889669494057227864L, "micro:bit Pin AD Configuration", (String) null);
        databaseHelper.addCharacteristic(-1630942988842744054L, -6889669494057227864L, "micro:bit Pin IO Configuration", (String) null);
        databaseHelper.addCharacteristic(-1630909848875088118L, -6889669494057227864L, "micro:bit PWM Control", (String) null);
        databaseHelper.addCharacteristic(-1631011738384251126L, -6889669494057227864L, "micro:bit LED Matrix State", (String) null);
        databaseHelper.addCharacteristic(-1630984839004076278L, -6889669494057227864L, "micro:bit LED Text", (String) null);
        databaseHelper.addCharacteristic(-1631133002490886390L, -6889669494057227864L, "micro:bit Scrolling Delay", (String) null);
        databaseHelper.addCharacteristic(-1630944852858550518L, -6889669494057227864L, "micro:bit Requirements", (String) null);
        databaseHelper.addCharacteristic(-1630980960648607990L, -6889669494057227864L, "micro:bit Event", (String) null);
        databaseHelper.addCharacteristic(-1631108164695013622L, -6889669494057227864L, "micro:bit Client Requirements", (String) null);
        databaseHelper.addCharacteristic(-1631055113258973430L, -6889669494057227864L, "micro:bit Client Event", (String) null);
        databaseHelper.addCharacteristic(-1630985100997081334L, -6889669494057227864L, "micro:bit DFU Control", (String) null);
        databaseHelper.addCharacteristic(-1630986617120536822L, -6889669494057227864L, "micro:bit Temperature", (String) null);
        databaseHelper.addCharacteristic(-1631117643687835894L, -6889669494057227864L, "micro:bit Temperature Period", (String) null);
    }

    public static void initializeDBVersion_4_20_0(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(-2725108607093944831L, -5864207808377291704L, "SMP Characteristic", (String) null);
    }

    public static void initializeDBVersion_4_4(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(-6935805052422372647L, -8333869438098858563L, "Notification Source", (String) null);
        databaseHelper.addCharacteristic(7625114183476005288L, -7484529865406096935L, "Control Point", (String) null);
        databaseHelper.addCharacteristic(2516042046487546805L, -4736463636774749189L, "Data Source", (String) null);
        databaseHelper.addCharacteristic(-7260785733043926390L, -5125362084237127230L, "Remote Command", (String) null);
        databaseHelper.addCharacteristic(3421798720303087903L, -7346290653278060286L, "Entity Update", (String) null);
        databaseHelper.addCharacteristic(-4128970125125925160L, -6436871298554800681L, "Entity Attribute", (String) null);
        databaseHelper.removeCharacteristic(-6644932600633537569L, -8486575981020192107L);
        databaseHelper.removeCharacteristic(-6644932561978831905L, -8486575981020192107L);
        databaseHelper.addCharacteristic(-6644932600633537569L, -8486575981020192107L, "Capabilities", (String) null);
        databaseHelper.addCharacteristic(-6644932561978831905L, -8486575981020192107L, "ADV Slot Data", (String) null);
    }

    public static void initializeDBVersion_4_8(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(-8196551313441075360L, -6937650605005804976L, "Experimental Buttonless DFU", (String) null);
        databaseHelper.addCharacteristic(-8157989233041780896L, -6937650605005804976L, "Buttonless DFU", (String) null);
    }

    public static void initializeDBVersion_SecDFU(DatabaseHelper databaseHelper) {
        databaseHelper.addCharacteristic(-8157989241631715488L, -6937650605005804976L, "DFU Control Point", (String) null);
        databaseHelper.addCharacteristic(-8157989237336748192L, -6937650605005804976L, "DFU Packet", (String) null);
    }

    private static boolean isCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic, long j, long j2) {
        return bluetoothGattCharacteristic.getUuid().getMostSignificantBits() == j && bluetoothGattCharacteristic.getUuid().getLeastSignificantBits() == j2;
    }

    public static boolean isDfuCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return isCharacteristic(bluetoothGattCharacteristic, 23300500811742L, 1523193452336828707L) || isCharacteristic(bluetoothGattCharacteristic, -8157989241631715488L, -6937650605005804976L) || isCharacteristic(bluetoothGattCharacteristic, -8196551313441075360L, -6937650605005804976L) || isCharacteristic(bluetoothGattCharacteristic, -8157989233041780896L, -6937650605005804976L) || isCharacteristic(bluetoothGattCharacteristic, -8157989228746813600L, -6937650605005804976L) || isCharacteristic(bluetoothGattCharacteristic, -2725108607093944831L, -5864207808377291704L);
    }
}
