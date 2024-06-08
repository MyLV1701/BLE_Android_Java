package io.runtime.mcumgr.managers;

import io.runtime.mcumgr.McuManager;
import io.runtime.mcumgr.McuMgrCallback;
import io.runtime.mcumgr.McuMgrTransport;
import io.runtime.mcumgr.response.McuMgrResponse;
import io.runtime.mcumgr.response.config.McuMgrConfigReadResponse;
import java.util.HashMap;

/* loaded from: classes.dex */
public class ConfigManager extends McuManager {
    private static final int ID_CONFIG = 0;

    public ConfigManager(McuMgrTransport mcuMgrTransport) {
        super(3, mcuMgrTransport);
    }

    public void read(String str, McuMgrCallback<McuMgrConfigReadResponse> mcuMgrCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("name", str);
        send(0, 0, hashMap, McuMgrConfigReadResponse.class, mcuMgrCallback);
    }

    public void write(String str, String str2, boolean z, McuMgrCallback<McuMgrResponse> mcuMgrCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("name", str);
        hashMap.put("val", str2);
        hashMap.put("save", Boolean.valueOf(z));
        send(2, 0, hashMap, McuMgrResponse.class, mcuMgrCallback);
    }

    public McuMgrConfigReadResponse read(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("name", str);
        return (McuMgrConfigReadResponse) send(0, 0, hashMap, McuMgrConfigReadResponse.class);
    }

    public McuMgrResponse write(String str, String str2, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("name", str);
        hashMap.put("val", str2);
        hashMap.put("save", Boolean.valueOf(z));
        return send(2, 0, hashMap, McuMgrResponse.class);
    }
}
