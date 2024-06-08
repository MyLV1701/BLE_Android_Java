package io.runtime.mcumgr.response.dflt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.runtime.mcumgr.response.McuMgrResponse;

/* loaded from: classes.dex */
public class McuMgrEchoResponse extends McuMgrResponse {

    @JsonProperty("r")
    public String r;

    @JsonCreator
    public McuMgrEchoResponse() {
    }
}
