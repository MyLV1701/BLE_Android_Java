package io.runtime.mcumgr.managers;

import io.runtime.mcumgr.McuManager;
import io.runtime.mcumgr.McuMgrCallback;
import io.runtime.mcumgr.McuMgrTransport;
import io.runtime.mcumgr.response.McuMgrResponse;
import io.runtime.mcumgr.response.config.McuMgrConfigReadResponse;
import java.util.HashMap;

/* loaded from: classes.dex */
public class CrashManager extends McuManager {
    private static final int ID_CRASH_TEST = 0;

    /* loaded from: classes.dex */
    public enum Test {
        DIV_0("div0"),
        JUMP_0("jump0"),
        REF_0("ref0"),
        ASSERT("assert"),
        WDOG("wdog");

        private final String value;

        Test(String str) {
            this.value = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }
    }

    public CrashManager(McuMgrTransport mcuMgrTransport) {
        super(5, mcuMgrTransport);
    }

    public McuMgrResponse test(Test test) {
        HashMap hashMap = new HashMap();
        hashMap.put("t", test.toString());
        return send(2, 0, hashMap, McuMgrConfigReadResponse.class);
    }

    public void test(Test test, McuMgrCallback<McuMgrResponse> mcuMgrCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("t", test.toString());
        send(2, 0, hashMap, McuMgrResponse.class, mcuMgrCallback);
    }
}
