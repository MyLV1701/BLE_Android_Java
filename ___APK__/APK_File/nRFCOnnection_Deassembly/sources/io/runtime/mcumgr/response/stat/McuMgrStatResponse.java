package io.runtime.mcumgr.response.stat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.runtime.mcumgr.response.McuMgrResponse;
import java.util.Map;

/* loaded from: classes.dex */
public class McuMgrStatResponse extends McuMgrResponse {

    @JsonProperty("fields")
    public Map<String, Long> fields;

    @JsonProperty("name")
    public String name;

    @JsonCreator
    public McuMgrStatResponse() {
    }
}
