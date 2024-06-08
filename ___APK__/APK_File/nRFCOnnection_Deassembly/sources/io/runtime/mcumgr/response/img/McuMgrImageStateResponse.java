package io.runtime.mcumgr.response.img;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.runtime.mcumgr.response.McuMgrResponse;

/* loaded from: classes.dex */
public class McuMgrImageStateResponse extends McuMgrResponse {
    public static final int SPLIT_STATUS_INVALID = 0;
    public static final int SPLIT_STATUS_MATCHING = 2;
    public static final int SPLIT_STATUS_NOT_MATCHING = 1;

    @JsonProperty("images")
    public ImageSlot[] images;

    @JsonProperty("splitStatus")
    public int splitStatus;

    /* loaded from: classes.dex */
    public static class ImageSlot {

        @JsonProperty("active")
        public boolean active;

        @JsonProperty("bootable")
        public boolean bootable;

        @JsonProperty("confirmed")
        public boolean confirmed;

        @JsonProperty("hash")
        public byte[] hash;

        @JsonProperty("pending")
        public boolean pending;

        @JsonProperty("permanent")
        public boolean permanent;

        @JsonProperty("slot")
        public int slot;

        @JsonProperty("version")
        public String version;

        @JsonCreator
        public ImageSlot() {
        }
    }

    @JsonCreator
    public McuMgrImageStateResponse() {
    }
}
