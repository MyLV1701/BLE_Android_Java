package io.runtime.mcumgr.response.dflt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.runtime.mcumgr.response.McuMgrResponse;

/* loaded from: classes.dex */
public class McuMgrReadDateTimeResponse extends McuMgrResponse {

    @JsonProperty("datetime")
    public String datetime;

    @JsonCreator
    public McuMgrReadDateTimeResponse() {
    }
}
