package no.nordicsemi.android.mcp.connection.macro.domain;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.domain.common.HasName;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

/* loaded from: classes.dex */
public class Macro extends HasName {

    @ElementList(entry = "assert-server-service", inline = true, required = false)
    private List<AssertService> assertServerServices;

    @ElementList(entry = "assert-service", inline = true, required = false)
    private List<AssertService> assertServices;

    @Attribute(required = false)
    private Icon icon;
    private NewOperationObserver mNewOperationObserver;

    @ElementListUnion({@ElementList(entry = "read", inline = true, type = Read.class), @ElementList(entry = "read-descriptor", inline = true, type = ReadDescriptor.class), @ElementList(entry = "write", inline = true, type = Write.class), @ElementList(entry = "write-descriptor", inline = true, type = WriteDescriptor.class), @ElementList(entry = "send-notification", inline = true, type = SendNotification.class), @ElementList(entry = "send-indication", inline = true, type = SendIndication.class), @ElementList(entry = "set-value", inline = true, type = SetCharacteristicValue.class), @ElementList(entry = "set-descriptor-value", inline = true, type = SetDescriptorValue.class), @ElementList(entry = "wait-for-notification", inline = true, type = WaitForNotification.class), @ElementList(entry = "wait-for-indication", inline = true, type = WaitForIndication.class), @ElementList(entry = "wait-for-read", inline = true, type = WaitForRead.class), @ElementList(entry = "wait-for-write", inline = true, type = WaitForWrite.class), @ElementList(entry = "wait-for-read-descriptor", inline = true, type = WaitForReadDescriptor.class), @ElementList(entry = "wait-for-write-descriptor", inline = true, type = WaitForWriteDescriptor.class), @ElementList(entry = "request-mtu", inline = true, type = RequestMtu.class), @ElementList(entry = "request-connection-priority", inline = true, type = RequestConnectionPriority.class), @ElementList(entry = "sleep", inline = true, type = Sleep.class), @ElementList(entry = "sleep-if", inline = true, type = SleepIf.class), @ElementList(entry = "sleep-until", inline = true, type = SleepUntil.class), @ElementList(entry = "unlock-eddystone", inline = true, type = UnlockEddystone.class), @ElementList(entry = "read-rssi", inline = true, type = ReadRSSI.class), @ElementList(entry = "begin-reliable-write", inline = true, type = BeginReliableWrite.class), @ElementList(entry = "execute-reliable-write", inline = true, type = ExecuteReliableWrite.class), @ElementList(entry = "abort-reliable-write", inline = true, type = AbortReliableWrite.class), @ElementList(entry = "wait-for-execute-write", inline = true, type = WaitForExecuteWrite.class), @ElementList(entry = "read-phy", inline = true, type = ReadPhy.class), @ElementList(entry = "set-preferred-phy", inline = true, type = SetPreferredPhy.class), @ElementList(entry = "wait-for-phy-update", inline = true, type = WaitForPhyUpdate.class)})
    private List<Operation> operations;

    /* loaded from: classes.dex */
    public enum Icon {
        LEFT(R.drawable.ic_macro_icon_left),
        UP(R.drawable.ic_macro_icon_up),
        HOME(R.drawable.ic_macro_icon_home),
        DOWN(R.drawable.ic_macro_icon_down),
        RIGHT(R.drawable.ic_macro_icon_right),
        REWIND(R.drawable.ic_macro_icon_rewind),
        PLAY(R.drawable.ic_macro_icon_play),
        PAUSE(R.drawable.ic_macro_icon_pause),
        STOP(R.drawable.ic_macro_icon_stop),
        FORWARD(R.drawable.ic_macro_icon_fast_forward),
        MAGIC(R.drawable.ic_macro_icon_magic),
        PHYSICAL_WEB(R.drawable.ic_macro_icon_physical_web),
        EDDYSTONE(R.drawable.ic_macro_icon_eddystone),
        NORDIC(R.drawable.ic_macro_icon_nordic),
        LOCK(R.drawable.ic_macro_icon_lock),
        ALARM(R.drawable.ic_macro_icon_alarm),
        SETTINGS(R.drawable.ic_macro_icon_settings),
        STAR(R.drawable.ic_macro_icon_star),
        PLUS(R.drawable.ic_macro_icon_plus),
        MINUS(R.drawable.ic_macro_icon_minus),
        BRIGHTNESS_HIGH(R.drawable.ic_macro_icon_brightness_high),
        BRIGHTNESS_LOW(R.drawable.ic_macro_icon_brightness_low),
        DOWNLOAD(R.drawable.ic_macro_icon_download),
        UPLOAD(R.drawable.ic_macro_icon_upload),
        PRINT(R.drawable.ic_macro_icon_print),
        FLASH(R.drawable.ic_macro_icon_flash_on),
        FLASH_OFF(R.drawable.ic_macro_icon_flash_off),
        LED_ON(R.drawable.ic_macro_icon_led_on),
        LED_OFF(R.drawable.ic_macro_icon_led_off),
        BATTERY(R.drawable.ic_macro_icon_battery),
        INFO(R.drawable.ic_macro_icon_info),
        MESSAGE(R.drawable.ic_macro_icon_message),
        ROCKET(R.drawable.ic_macro_icon_rocket),
        PARACHUTE(R.drawable.ic_macro_icon_parachute),
        PIKACHU(R.drawable.ic_macro_icon_pokemon),
        NUMBER_1(R.drawable.ic_macro_icon_1),
        NUMBER_2(R.drawable.ic_macro_icon_2),
        NUMBER_3(R.drawable.ic_macro_icon_3),
        NUMBER_4(R.drawable.ic_macro_icon_4),
        NUMBER_5(R.drawable.ic_macro_icon_5),
        NUMBER_6(R.drawable.ic_macro_icon_6),
        NUMBER_7(R.drawable.ic_macro_icon_7),
        NUMBER_8(R.drawable.ic_macro_icon_8),
        NUMBER_9(R.drawable.ic_macro_icon_9),
        CLOSE(R.drawable.ic_macro_icon_close),
        DIT(R.drawable.ic_macro_icon_dit),
        DAH(R.drawable.ic_macro_icon_dah),
        SPACE(R.drawable.ic_macro_icon_space),
        SNAKE(R.drawable.ic_macro_icon_snake),
        CLOCK(R.drawable.ic_macro_icon_clock);

        private int value;

        Icon(int i) {
            this.value = i;
        }

        public int getResourceId() {
            return this.value;
        }
    }

    /* loaded from: classes.dex */
    public interface NewOperationObserver {
        void onOperationAdded();
    }

    public Macro() {
        super("");
        this.icon = Icon.PLAY;
        this.assertServices = new ArrayList(4);
        this.assertServerServices = new ArrayList(4);
        this.operations = new ArrayList();
    }

    private void assertOperation(DatabaseHelper databaseHelper, CharacteristicOperation characteristicOperation) {
        List<AssertService> list = characteristicOperation.isClientOperation() ? this.assertServices : this.assertServerServices;
        for (AssertService assertService : list) {
            if (assertService.matches(characteristicOperation)) {
                assertService.assertOperation(databaseHelper, characteristicOperation);
                return;
            }
        }
        list.add(new AssertService(databaseHelper, characteristicOperation));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void abortOperation(IBluetoothLeBasicConnection iBluetoothLeBasicConnection) {
        iBluetoothLeBasicConnection.abortOperation();
    }

    public void addOperation(Operation operation) {
        this.operations.add(operation);
        NewOperationObserver newOperationObserver = this.mNewOperationObserver;
        if (newOperationObserver != null) {
            newOperationObserver.onOperationAdded();
        }
    }

    public void ensureAllOperationsAsserted(DatabaseHelper databaseHelper) {
        for (Operation operation : this.operations) {
            if (operation instanceof CharacteristicOperation) {
                assertOperation(databaseHelper, (CharacteristicOperation) operation);
            }
        }
    }

    public List<String> getCompatibilityIssues(IBluetoothLeBasicConnection iBluetoothLeBasicConnection) {
        ArrayList arrayList = new ArrayList();
        Iterator<AssertService> it = this.assertServices.iterator();
        while (it.hasNext()) {
            it.next().check(iBluetoothLeBasicConnection, true, arrayList);
        }
        if (!this.assertServerServices.isEmpty() && iBluetoothLeBasicConnection.getServerGattServices(false) == null) {
            arrayList.add("GATT Server disabled");
        } else {
            Iterator<AssertService> it2 = this.assertServerServices.iterator();
            while (it2.hasNext()) {
                it2.next().check(iBluetoothLeBasicConnection, false, arrayList);
            }
        }
        return arrayList;
    }

    public Icon getIcon() {
        return this.icon;
    }

    public int getIconRes() {
        return this.icon.value;
    }

    public List<Operation> getOperations() {
        return this.operations;
    }

    public void invalidate() {
        Iterator<Operation> it = this.operations.iterator();
        while (it.hasNext()) {
            it.next().invalidate();
        }
    }

    public boolean isRecorded() {
        return this.mNewOperationObserver != null;
    }

    public Macro mirror(DatabaseHelper databaseHelper) {
        Macro macro = new Macro(getName() + " - mirror");
        Iterator<Operation> it = this.operations.iterator();
        while (it.hasNext()) {
            Operation[] mirror = it.next().mirror(databaseHelper);
            if (mirror != null) {
                for (Operation operation : mirror) {
                    macro.addOperation(operation);
                }
            }
        }
        macro.ensureAllOperationsAsserted(databaseHelper);
        return macro;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int runOperation(int i, IBluetoothLeConnection iBluetoothLeConnection) {
        return this.operations.get(i).run(iBluetoothLeConnection);
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public void setRecorderObserver(NewOperationObserver newOperationObserver) {
        this.mNewOperationObserver = newOperationObserver;
    }

    @Validate
    protected void validate() {
        if (!TextUtils.isEmpty(getName())) {
            setName(getName().trim());
            return;
        }
        throw new PersistenceException("Name must not be empty", new Object[0]);
    }

    public void addOperation(DatabaseHelper databaseHelper, CharacteristicOperation characteristicOperation) {
        assertOperation(databaseHelper, characteristicOperation);
        addOperation(characteristicOperation);
    }

    public Macro(@Attribute(name = "name") String str) {
        super(str);
        this.icon = Icon.PLAY;
        this.assertServices = new ArrayList(4);
        this.assertServerServices = new ArrayList(4);
        this.operations = new ArrayList();
    }
}
