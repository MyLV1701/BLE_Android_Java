package io.runtime.mcumgr.response.log;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.runtime.mcumgr.response.McuMgrResponse;

/* loaded from: classes.dex */
public class McuMgrLevelListResponse extends McuMgrResponse {

    @JsonProperty("level_map")
    public String[] level_map;

    @JsonCreator
    public McuMgrLevelListResponse() {
    }
}
