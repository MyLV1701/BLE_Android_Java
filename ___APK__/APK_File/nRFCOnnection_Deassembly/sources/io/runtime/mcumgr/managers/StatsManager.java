package io.runtime.mcumgr.managers;

import io.runtime.mcumgr.McuManager;
import io.runtime.mcumgr.McuMgrCallback;
import io.runtime.mcumgr.McuMgrTransport;
import io.runtime.mcumgr.response.stat.McuMgrStatListResponse;
import io.runtime.mcumgr.response.stat.McuMgrStatResponse;
import java.util.HashMap;

/* loaded from: classes.dex */
public class StatsManager extends McuManager {
    private static final int ID_LIST = 1;
    private static final int ID_READ = 0;

    public StatsManager(McuMgrTransport mcuMgrTransport) {
        super(2, mcuMgrTransport);
    }

    public void list(McuMgrCallback<McuMgrStatListResponse> mcuMgrCallback) {
        send(0, 1, null, McuMgrStatListResponse.class, mcuMgrCallback);
    }

    public void read(String str, McuMgrCallback<McuMgrStatResponse> mcuMgrCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("name", str);
        send(0, 0, hashMap, McuMgrStatResponse.class, mcuMgrCallback);
    }

    public McuMgrStatListResponse list() {
        return (McuMgrStatListResponse) send(0, 1, null, McuMgrStatListResponse.class);
    }

    public McuMgrStatResponse read(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("name", str);
        return (McuMgrStatResponse) send(0, 0, hashMap, McuMgrStatResponse.class);
    }
}
