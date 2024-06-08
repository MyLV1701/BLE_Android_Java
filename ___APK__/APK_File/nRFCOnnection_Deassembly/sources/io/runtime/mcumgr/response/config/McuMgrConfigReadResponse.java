package io.runtime.mcumgr.response.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.runtime.mcumgr.response.McuMgrResponse;

/* loaded from: classes.dex */
public class McuMgrConfigReadResponse extends McuMgrResponse {

    @JsonProperty("val")
    public String val;

    @JsonCreator
    public McuMgrConfigReadResponse() {
    }
}
