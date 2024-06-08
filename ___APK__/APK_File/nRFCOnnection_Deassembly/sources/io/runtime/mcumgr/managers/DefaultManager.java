package io.runtime.mcumgr.managers;

import io.runtime.mcumgr.McuManager;
import io.runtime.mcumgr.McuMgrCallback;
import io.runtime.mcumgr.McuMgrTransport;
import io.runtime.mcumgr.response.McuMgrResponse;
import io.runtime.mcumgr.response.dflt.McuMgrEchoResponse;
import io.runtime.mcumgr.response.dflt.McuMgrMpStatResponse;
import io.runtime.mcumgr.response.dflt.McuMgrReadDateTimeResponse;
import io.runtime.mcumgr.response.dflt.McuMgrTaskStatResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

/* loaded from: classes.dex */
public class DefaultManager extends McuManager {
    private static final int ID_CONS_ECHO_CTRL = 1;
    private static final int ID_DATETIME_STR = 4;
    private static final int ID_ECHO = 0;
    private static final int ID_MPSTATS = 3;
    private static final int ID_RESET = 5;
    private static final int ID_TASKSTATS = 2;

    public DefaultManager(McuMgrTransport mcuMgrTransport) {
        super(0, mcuMgrTransport);
    }

    public void consoleEcho(boolean z, McuMgrCallback<McuMgrResponse> mcuMgrCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("echo", Boolean.valueOf(z));
        send(2, 1, hashMap, McuMgrResponse.class, mcuMgrCallback);
    }

    public void echo(String str, McuMgrCallback<McuMgrEchoResponse> mcuMgrCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("d", str);
        send(2, 0, hashMap, McuMgrEchoResponse.class, mcuMgrCallback);
    }

    public void mpstat(McuMgrCallback<McuMgrMpStatResponse> mcuMgrCallback) {
        send(0, 3, null, McuMgrMpStatResponse.class, mcuMgrCallback);
    }

    public void readDatetime(McuMgrCallback<McuMgrReadDateTimeResponse> mcuMgrCallback) {
        send(0, 4, null, McuMgrReadDateTimeResponse.class, mcuMgrCallback);
    }

    public void reset(McuMgrCallback<McuMgrResponse> mcuMgrCallback) {
        send(2, 5, null, McuMgrResponse.class, mcuMgrCallback);
    }

    public void taskstats(McuMgrCallback<McuMgrTaskStatResponse> mcuMgrCallback) {
        send(0, 2, null, McuMgrTaskStatResponse.class, mcuMgrCallback);
    }

    public void writeDatetime(Date date, TimeZone timeZone, McuMgrCallback<McuMgrResponse> mcuMgrCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("datetime", McuManager.dateToString(date, timeZone));
        send(2, 4, hashMap, McuMgrResponse.class, mcuMgrCallback);
    }

    public McuMgrMpStatResponse mpstat() {
        return (McuMgrMpStatResponse) send(0, 3, null, McuMgrMpStatResponse.class);
    }

    public McuMgrReadDateTimeResponse readDatetime() {
        return (McuMgrReadDateTimeResponse) send(0, 4, null, McuMgrReadDateTimeResponse.class);
    }

    public McuMgrResponse reset() {
        return send(2, 5, null, McuMgrResponse.class);
    }

    public McuMgrTaskStatResponse taskstats() {
        return (McuMgrTaskStatResponse) send(0, 2, null, McuMgrTaskStatResponse.class);
    }

    public McuMgrResponse consoleEcho(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("echo", Boolean.valueOf(z));
        return send(2, 1, hashMap, McuMgrResponse.class);
    }

    public McuMgrEchoResponse echo(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("d", str);
        return (McuMgrEchoResponse) send(2, 0, hashMap, McuMgrEchoResponse.class);
    }

    public McuMgrResponse writeDatetime(Date date, TimeZone timeZone) {
        HashMap hashMap = new HashMap();
        hashMap.put("datetime", McuManager.dateToString(date, timeZone));
        return send(2, 4, hashMap, McuMgrResponse.class);
    }
}
