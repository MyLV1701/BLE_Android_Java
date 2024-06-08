package io.runtime.mcumgr.response.log;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.runtime.mcumgr.response.McuMgrResponse;
import java.util.Map;

/* loaded from: classes.dex */
public class McuMgrModuleListResponse extends McuMgrResponse {

    @JsonProperty("module_map")
    public Map<String, Integer> module_map;

    @JsonCreator
    public McuMgrModuleListResponse() {
    }
}
