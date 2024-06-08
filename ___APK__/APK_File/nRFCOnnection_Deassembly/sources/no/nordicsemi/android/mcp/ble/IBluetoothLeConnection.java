package no.nordicsemi.android.mcp.ble;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.net.Uri;
import android.util.Pair;
import java.util.List;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.connection.macro.domain.Macro;
import no.nordicsemi.android.mcp.connection.macro.domain.MacroHandler;

/* loaded from: classes.dex */
public interface IBluetoothLeConnection extends IBluetoothLeBasicConnection {
    boolean areServicesDiscovered();

    boolean canDisconnect();

    boolean containsKey(String str);

    void enableAllServices();

    Object get(String str);

    Context getContext();

    Macro getCurrentlyRecordingMacro();

    Device getDevice();

    Pair<List<Integer>, List<Float>> getDfuAvgSpeedValues();

    int getDfuMaxAvgSpeed();

    Pair<List<Integer>, List<Float>> getDfuSpeedValues();

    String getLogContent();

    Uri getLogSessionEntriesUri();

    MacroHandler getMacroStatus(int i);

    boolean isConnected();

    boolean isConnectedToServer();

    boolean isConnectionAttemptDone();

    boolean isMacroRunning();

    boolean isMacroTracked(int i);

    boolean isPredefinedServerService(BluetoothGattService bluetoothGattService);

    boolean isRecordingMacro();

    void newLogSession(boolean z);

    void notifyDatasetChanged(boolean z);

    Object put(String str, Object obj);

    void readAllCharacteristics();

    void registerEddystoneSlot(String str, byte[] bArr);

    Object remove(String str);

    void setAsCentral(boolean z);

    void setAutoConnect(boolean z);

    void setPreferredPhy(Integer num);

    Macro startRecordingMacro();

    Macro stopRecordingMacro();

    MacroHandler trackMacro(int i, Macro macro);

    void unlockEddystone(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr);

    void untrackMacro(int i);
}
