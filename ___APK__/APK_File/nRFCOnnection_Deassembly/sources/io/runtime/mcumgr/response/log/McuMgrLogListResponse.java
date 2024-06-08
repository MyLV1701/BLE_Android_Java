package io.runtime.mcumgr.response.log;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.runtime.mcumgr.response.McuMgrResponse;

/* loaded from: classes.dex */
public class McuMgrLogListResponse extends McuMgrResponse {

    @JsonProperty("log_list")
    public String[] log_list;

    @JsonCreator
    public McuMgrLogListResponse() {
    }
}
