package io.runtime.mcumgr.managers;

import f.a.b;
import f.a.c;
import io.runtime.mcumgr.McuManager;
import io.runtime.mcumgr.McuMgrCallback;
import io.runtime.mcumgr.McuMgrTransport;
import io.runtime.mcumgr.exception.McuMgrException;
import io.runtime.mcumgr.response.McuMgrResponse;
import io.runtime.mcumgr.response.log.McuMgrLevelListResponse;
import io.runtime.mcumgr.response.log.McuMgrLogListResponse;
import io.runtime.mcumgr.response.log.McuMgrLogResponse;
import io.runtime.mcumgr.response.log.McuMgrModuleListResponse;
import io.runtime.mcumgr.util.CBOR;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class LogManager extends McuManager {
    private static final int ID_APPEND = 2;
    private static final int ID_CLEAR = 1;
    private static final int ID_LEVEL_LIST = 4;
    private static final int ID_LOGS_LIST = 5;
    private static final int ID_MODULE_LIST = 3;
    private static final int ID_READ = 0;
    private static final b LOG = c.a((Class<?>) LogManager.class);

    /* loaded from: classes.dex */
    public static class State {
        private ArrayList<McuMgrLogResponse.Entry> mEntries;
        private String mName;
        private long mNextIndex;

        public State(String str) {
            this(str, 0L);
        }

        public List<McuMgrLogResponse.Entry> getEntries() {
            return this.mEntries;
        }

        public String getName() {
            return this.mName;
        }

        public long getNextIndex() {
            return this.mNextIndex;
        }

        public void reset() {
            this.mNextIndex = 0L;
            this.mEntries = new ArrayList<>();
        }

        public void setNextIndex(long j) {
            this.mNextIndex = j;
        }

        public State(String str, long j) {
            this.mNextIndex = 0L;
            this.mEntries = new ArrayList<>();
            this.mName = str;
            this.mNextIndex = j;
        }
    }

    public LogManager(McuMgrTransport mcuMgrTransport) {
        super(4, mcuMgrTransport);
    }

    public void clear(McuMgrCallback<McuMgrResponse> mcuMgrCallback) {
        send(2, 1, null, McuMgrResponse.class, mcuMgrCallback);
    }

    public synchronized Map<String, State> getAll() {
        HashMap hashMap = new HashMap();
        try {
            McuMgrLogListResponse logsList = logsList();
            LOG.c("Available logs: {}", logsList.toString());
            if (logsList.log_list == null) {
                LOG.c("No logs available on this device");
                return hashMap;
            }
            for (String str : logsList.log_list) {
                LOG.c("Getting logs from: {}", str);
                State state = (State) hashMap.get(str);
                if (state == null) {
                    state = new State(str);
                    hashMap.put(str, state);
                }
                State allFromState = getAllFromState(state);
                hashMap.put(allFromState.getName(), allFromState);
            }
            return hashMap;
        } catch (McuMgrException e2) {
            LOG.a("Transport error while getting available logs", (Throwable) e2);
            return hashMap;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0046, code lost:
    
        io.runtime.mcumgr.managers.LogManager.LOG.a("No logs returned in the response.");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public io.runtime.mcumgr.managers.LogManager.State getAllFromState(io.runtime.mcumgr.managers.LogManager.State r6) {
        /*
            r5 = this;
            if (r6 == 0) goto L4e
        L2:
            io.runtime.mcumgr.response.log.McuMgrLogResponse r0 = r5.showNext(r6)
            if (r0 != 0) goto L10
            f.a.b r0 = io.runtime.mcumgr.managers.LogManager.LOG
            java.lang.String r1 = "Show logs resulted in an error"
            r0.a(r1)
            goto L4d
        L10:
            io.runtime.mcumgr.response.log.McuMgrLogResponse$LogResult[] r0 = r0.logs
            if (r0 == 0) goto L46
            int r1 = r0.length
            if (r1 != 0) goto L18
            goto L46
        L18:
            r1 = 0
            r0 = r0[r1]
            io.runtime.mcumgr.response.log.McuMgrLogResponse$Entry[] r1 = r0.entries
            if (r1 == 0) goto L3e
            int r2 = r1.length
            if (r2 != 0) goto L23
            goto L3e
        L23:
            int r2 = r1.length
            int r2 = r2 + (-1)
            r1 = r1[r2]
            long r1 = r1.index
            r3 = 1
            long r1 = r1 + r3
            r6.setNextIndex(r1)
            java.util.List r1 = r6.getEntries()
            io.runtime.mcumgr.response.log.McuMgrLogResponse$Entry[] r0 = r0.entries
            java.util.List r0 = java.util.Arrays.asList(r0)
            r1.addAll(r0)
            goto L2
        L3e:
            f.a.b r0 = io.runtime.mcumgr.managers.LogManager.LOG
            java.lang.String r1 = "No more entries left for this log."
            r0.e(r1)
            goto L4d
        L46:
            f.a.b r0 = io.runtime.mcumgr.managers.LogManager.LOG
            java.lang.String r1 = "No logs returned in the response."
            r0.a(r1)
        L4d:
            return r6
        L4e:
            java.lang.NullPointerException r6 = new java.lang.NullPointerException
            java.lang.String r0 = "State must not be null!"
            r6.<init>(r0)
            goto L57
        L56:
            throw r6
        L57:
            goto L56
        */
        throw new UnsupportedOperationException("Method not decompiled: io.runtime.mcumgr.managers.LogManager.getAllFromState(io.runtime.mcumgr.managers.LogManager$State):io.runtime.mcumgr.managers.LogManager$State");
    }

    public void levelList(McuMgrCallback<McuMgrLevelListResponse> mcuMgrCallback) {
        send(0, 4, null, McuMgrLevelListResponse.class, mcuMgrCallback);
    }

    public void logsList(McuMgrCallback<McuMgrLogListResponse> mcuMgrCallback) {
        send(0, 5, null, McuMgrLogListResponse.class, mcuMgrCallback);
    }

    public void moduleList(McuMgrCallback<McuMgrModuleListResponse> mcuMgrCallback) {
        send(0, 3, null, McuMgrModuleListResponse.class, mcuMgrCallback);
    }

    public void show(String str, Long l, Date date, McuMgrCallback<McuMgrLogResponse> mcuMgrCallback) {
        HashMap hashMap = new HashMap();
        if (str != null) {
            hashMap.put("log_name", str);
        }
        if (l != null) {
            hashMap.put("index", l);
            if (date != null) {
                hashMap.put("ts", McuManager.dateToString(date, null));
            }
        }
        send(0, 0, hashMap, McuMgrLogResponse.class, mcuMgrCallback);
    }

    public McuMgrLogResponse showNext(State state) {
        LOG.a("Show logs: name={}, nextIndex={}", state.getName(), Long.valueOf(state.getNextIndex()));
        try {
            McuMgrLogResponse show = show(state.getName(), Long.valueOf(state.getNextIndex()), null);
            LOG.b("Show logs response: {}", CBOR.toString(show.getPayload()));
            return show;
        } catch (McuMgrException e2) {
            LOG.a("Requesting next set of logs failed", (Throwable) e2);
            return null;
        } catch (IOException e3) {
            LOG.a("Parsing response failed", (Throwable) e3);
            return null;
        }
    }

    public McuMgrResponse clear() {
        return send(2, 1, null, McuMgrResponse.class);
    }

    public McuMgrLevelListResponse levelList() {
        return (McuMgrLevelListResponse) send(0, 4, null, McuMgrLevelListResponse.class);
    }

    public McuMgrLogListResponse logsList() {
        return (McuMgrLogListResponse) send(0, 5, null, McuMgrLogListResponse.class);
    }

    public McuMgrModuleListResponse moduleList() {
        return (McuMgrModuleListResponse) send(0, 3, null, McuMgrModuleListResponse.class);
    }

    public McuMgrLogResponse show(String str, Long l, Date date) {
        HashMap hashMap = new HashMap();
        if (str != null) {
            hashMap.put("log_name", str);
        }
        if (l != null) {
            hashMap.put("index", l);
            if (date != null) {
                hashMap.put("ts", McuManager.dateToString(date, null));
            }
        }
        return (McuMgrLogResponse) send(0, 0, hashMap, McuMgrLogResponse.class);
    }
}
