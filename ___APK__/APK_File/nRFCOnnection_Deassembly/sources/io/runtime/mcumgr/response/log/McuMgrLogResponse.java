package io.runtime.mcumgr.response.log;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.runtime.mcumgr.response.McuMgrResponse;
import io.runtime.mcumgr.util.ByteUtil;
import io.runtime.mcumgr.util.CBOR;
import java.io.IOException;
import java.nio.charset.Charset;
import no.nordicsemi.android.log.LogContract;
import no.nordicsemi.android.mcp.database.UuidColumns;

/* loaded from: classes.dex */
public class McuMgrLogResponse extends McuMgrResponse {

    @JsonProperty("logs")
    public LogResult[] logs;

    @JsonProperty("next_index")
    public long next_index;

    @JsonIgnoreProperties(ignoreUnknown = true)
    /* loaded from: classes.dex */
    public static class Entry {
        public static final String LOG_ENTRY_TYPE_BINARY = "bin";
        public static final String LOG_ENTRY_TYPE_CBOR = "cbor";
        public static final String LOG_ENTRY_TYPE_STRING = "str";
        public static final int LOG_LEVEL_CRITICAL = 4;
        public static final int LOG_LEVEL_DEBUG = 0;
        public static final int LOG_LEVEL_ERROR = 3;
        public static final int LOG_LEVEL_INFO = 1;
        public static final int LOG_LEVEL_WARN = 2;

        @JsonProperty("index")
        public long index;

        @JsonProperty(LogContract.LogColumns.LEVEL)
        public int level;

        @JsonProperty("module")
        public int module;

        @JsonProperty("msg")
        public byte[] msg;

        @JsonProperty("ts")
        public long ts;

        @JsonProperty(UuidColumns.SPECIFICATION_TYPE)
        public String type;

        @JsonCreator
        public Entry() {
        }

        public String getMessageString() {
            byte[] bArr = this.msg;
            if (bArr == null) {
                return null;
            }
            String str = this.type;
            if (str == null) {
                return ByteUtil.byteArrayToHex(bArr);
            }
            char c2 = 65535;
            int hashCode = str.hashCode();
            if (hashCode != 97543) {
                if (hashCode != 114225) {
                    if (hashCode == 3047042 && str.equals(LOG_ENTRY_TYPE_CBOR)) {
                        c2 = 1;
                    }
                } else if (str.equals(LOG_ENTRY_TYPE_STRING)) {
                    c2 = 0;
                }
            } else if (str.equals(LOG_ENTRY_TYPE_BINARY)) {
                c2 = 2;
            }
            if (c2 == 0) {
                return new String(this.msg, Charset.forName("UTF-8"));
            }
            if (c2 != 1) {
                if (c2 == 2) {
                    ByteUtil.byteArrayToHex(this.msg);
                }
                return ByteUtil.byteArrayToHex(this.msg);
            }
            try {
                return CBOR.toString(this.msg);
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class LogResult {
        public static final int LOG_TYPE_MEMORY = 1;
        public static final int LOG_TYPE_STORAGE = 2;
        public static final int LOG_TYPE_STREAM = 0;

        @JsonProperty("entries")
        public Entry[] entries;

        @JsonProperty("name")
        public String name;

        @JsonProperty(UuidColumns.SPECIFICATION_TYPE)
        public int type;

        @JsonCreator
        public LogResult() {
        }
    }

    @JsonCreator
    public McuMgrLogResponse() {
    }
}
