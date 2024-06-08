package no.nordicsemi.android.mcp.database.init;

import android.bluetooth.BluetoothGattService;
import no.nordicsemi.android.mcp.database.DatabaseUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ThirdPartyServices {
    public static void initialize(DatabaseHelper databaseHelper) {
        databaseHelper.addService(23296205844446L, 1523193452336828707L, "Device Firmware Update Service", null);
    }

    public static void initializeDBVersion_2_0(DatabaseHelper databaseHelper) {
        databaseHelper.addService(7944349750023943059L, -2258021889238840674L, "Nordic UART Service", null);
        databaseHelper.addService(-4749680296649670530L, -8970875957782147596L, "Anki Drive Vehicle Service", null);
    }

    public static void initializeDBVersion_2_1(DatabaseHelper databaseHelper) {
        databaseHelper.addService(-7684806573819759190L, -6875724801706301267L, "Beacon Config", null);
        databaseHelper.addService(-5522119412161426087L, -8103675199899129497L, "URI Beacon Config (V1)", null);
    }

    public static void initializeDBVersion_3_2(DatabaseHelper databaseHelper) {
        databaseHelper.addService(-8628896877831879602L, -4233388132819706284L, "Digital Bird Service", null);
        databaseHelper.removeService(-1293623256560549702L, -6082505226419863080L);
        databaseHelper.addService(-1293623256560549702L, -6082505226419863080L, "Eddystone-URL Configuration Service", null);
    }

    public static void initializeDBVersion_4_0(DatabaseHelper databaseHelper) {
        databaseHelper.addService(23240371269598L, 1523193452336828707L, "Nordic LED Button Service", null);
        databaseHelper.addService(-6430274714198391490L, -7630548399806483608L, "Candy Dispenser Service", null);
    }

    public static void initializeDBVersion_4_1(DatabaseHelper databaseHelper) {
        databaseHelper.addService(-6644932604928504865L, -8486575981020192107L, "Eddystone Configuration Service", null);
    }

    public static void initializeDBVersion_4_12(DatabaseHelper databaseHelper) {
        databaseHelper.addService(-1195704598951278285L, -7273222140190261182L, "Thingy Configuration Service", null);
        databaseHelper.addService(-1195703499439650509L, -7273222140190261182L, "Thingy Environment Service", null);
        databaseHelper.addService(-1195702399928022733L, -7273222140190261182L, "Thingy User Interface Service", null);
        databaseHelper.addService(-1195701300416394957L, -7273222140190261182L, "Thingy Motion Service", null);
        databaseHelper.addService(-1195700200904767181L, -7273222140190261182L, "Thingy Sound Service", null);
    }

    public static void initializeDBVersion_4_14_4(DatabaseHelper databaseHelper) {
        databaseHelper.addService(-1631139436351895798L, -6889669494057227864L, "micro:bit Accelerometer Service", null);
        databaseHelper.addService(-1630880479888718070L, -6889669494057227864L, "micro:bit Magnetometer Service", null);
        databaseHelper.addService(-1630979805302405366L, -6889669494057227864L, "micro:bit Button Service", null);
        databaseHelper.addService(-1631127169925298422L, -6889669494057227864L, "micro:bit IO Pin Service", null);
        databaseHelper.addService(-1630908770838296822L, -6889669494057227864L, "micro:bit LED Service", null);
        databaseHelper.addService(-1630985109587015926L, -6889669494057227864L, "micro:bit Event Service", null);
        databaseHelper.addService(-1630985105292048630L, -6889669494057227864L, "micro:bit DFU Control Service", null);
        databaseHelper.addService(-1631040836787681526L, -6889669494057227864L, "micro:bit Temperature Service", null);
    }

    public static void initializeDBVersion_4_20_0(DatabaseHelper databaseHelper) {
        databaseHelper.addService(-8263018873684013869L, -8751749364119983484L, "SMP Service", null);
    }

    public static void initializeDBVersion_4_4(DatabaseHelper databaseHelper) {
        databaseHelper.addService(8720644747813605017L, -6624993934312668976L, "Apple Notification Center Service", null);
        databaseHelper.addService(-8515374324558445766L, -8145669210717685540L, "Apple Media Service", null);
    }

    public static void initializeDBVersion_4_8(DatabaseHelper databaseHelper) {
        databaseHelper.addService(-8196551313441075360L, -6937650605005804976L, "Experimental Buttonless DFU Service", null);
    }

    public static void initializeDBVersion_SecDFU(DatabaseHelper databaseHelper) {
        databaseHelper.addService(65113L, "Secure DFU Service", null);
    }

    public static boolean isDfuService(BluetoothGattService bluetoothGattService) {
        return isService(bluetoothGattService, 23296205844446L, 1523193452336828707L) || isService(bluetoothGattService, 279658205548544L, DatabaseUtils.LSB) || isService(bluetoothGattService, -8196551313441075360L, -6937650605005804976L);
    }

    public static boolean isEddystoneConfigService(BluetoothGattService bluetoothGattService) {
        return isService(bluetoothGattService, -6644932604928504865L, -8486575981020192107L);
    }

    public static boolean isMcuMgrService(BluetoothGattService bluetoothGattService) {
        return isService(bluetoothGattService, -8263018873684013869L, -8751749364119983484L);
    }

    public static boolean isMicrobitService(BluetoothGattService bluetoothGattService) {
        return isService(bluetoothGattService, -1630908770838296822L, -6889669494057227864L);
    }

    private static boolean isService(BluetoothGattService bluetoothGattService, long j, long j2) {
        return bluetoothGattService.getUuid().getMostSignificantBits() == j && bluetoothGattService.getUuid().getLeastSignificantBits() == j2;
    }

    public static boolean isThingyConfigService(BluetoothGattService bluetoothGattService) {
        return isService(bluetoothGattService, -1195704598951278285L, -7273222140190261182L);
    }
}
