package io.runtime.mcumgr.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/* loaded from: classes.dex */
public class DownloadResponse extends McuMgrResponse {

    @JsonProperty("data")
    public byte[] data;

    @JsonProperty("len")
    public int len;

    @JsonProperty("off")
    public int off;

    @JsonCreator
    public DownloadResponse() {
    }
}
