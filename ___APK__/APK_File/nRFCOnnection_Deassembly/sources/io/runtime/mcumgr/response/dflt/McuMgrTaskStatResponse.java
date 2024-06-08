package io.runtime.mcumgr.response.dflt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.runtime.mcumgr.response.McuMgrResponse;
import java.util.Map;

/* loaded from: classes.dex */
public class McuMgrTaskStatResponse extends McuMgrResponse {
    public static final int OS_TASK_READY = 1;
    public static final int OS_TASK_SLEEP = 2;
    public static final int THREAD_DEAD_MASK = 8;
    public static final int THREAD_DUMMY_MASK = 1;
    public static final int THREAD_PENDING_MASK = 2;
    public static final int THREAD_PRESTART_MASK = 4;
    public static final int THREAD_QUEUED_MASK = 64;
    public static final int THREAD_SUSPENDED_MASK = 16;

    @JsonProperty("tasks")
    public Map<String, TaskStat> tasks;

    /* loaded from: classes.dex */
    public static class TaskStat {

        @JsonProperty("cswcnt")
        public long cswcnt;

        @JsonProperty("last_checkin")
        public long last_checkin;

        @JsonProperty("next_checkin")
        public long next_checkin;

        @JsonProperty("prio")
        public long prio;

        @JsonProperty("runtime")
        public long runtime;

        @JsonProperty("state")
        public long state;

        @JsonProperty("stksiz")
        public long stksiz;

        @JsonProperty("stkuse")
        public long stkuse;

        @JsonProperty("tid")
        public long tid;

        @JsonCreator
        public TaskStat() {
        }
    }

    @JsonCreator
    public McuMgrTaskStatResponse() {
    }
}
