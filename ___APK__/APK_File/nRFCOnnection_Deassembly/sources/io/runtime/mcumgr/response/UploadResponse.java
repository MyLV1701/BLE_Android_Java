package io.runtime.mcumgr.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/* loaded from: classes.dex */
public class UploadResponse extends McuMgrResponse {

    @JsonProperty("off")
    public int off;

    @JsonCreator
    public UploadResponse() {
    }
}
