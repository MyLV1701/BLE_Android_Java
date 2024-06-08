package io.runtime.mcumgr.response.stat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.runtime.mcumgr.response.McuMgrResponse;

/* loaded from: classes.dex */
public class McuMgrStatListResponse extends McuMgrResponse {

    @JsonProperty("stat_list")
    public String[] stat_list;

    @JsonCreator
    public McuMgrStatListResponse() {
    }
}
