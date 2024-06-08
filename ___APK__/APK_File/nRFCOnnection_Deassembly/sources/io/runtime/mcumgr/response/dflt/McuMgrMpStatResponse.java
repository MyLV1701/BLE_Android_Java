package io.runtime.mcumgr.response.dflt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.runtime.mcumgr.response.McuMgrResponse;
import java.util.Map;

/* loaded from: classes.dex */
public class McuMgrMpStatResponse extends McuMgrResponse {

    @JsonProperty("mpools")
    public Map<String, MpStat> mpools;

    /* loaded from: classes.dex */
    public static class MpStat {

        @JsonProperty("blksiz")
        public int blksiz;

        @JsonProperty("min")
        public int min;

        @JsonProperty("nblks")
        public int nblks;

        @JsonProperty("nfree")
        public int nfree;

        @JsonCreator
        public MpStat() {
        }
    }

    @JsonCreator
    public McuMgrMpStatResponse() {
    }
}
