package io.runtime.mcumgr.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import f.a.b;
import f.a.c;
import io.runtime.mcumgr.McuMgrErrorCode;
import io.runtime.mcumgr.McuMgrHeader;
import io.runtime.mcumgr.McuMgrScheme;
import io.runtime.mcumgr.exception.McuMgrCoapException;
import io.runtime.mcumgr.util.CBOR;
import java.io.IOException;
import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
/* loaded from: classes.dex */
public class McuMgrResponse {
    private static final b LOG = c.a((Class<?>) McuMgrResponse.class);
    private byte[] mBytes;
    private McuMgrHeader mHeader;
    private byte[] mPayload;
    private McuMgrErrorCode mReturnCode;
    private McuMgrScheme mScheme;

    @JsonProperty("rc")
    public int rc = 0;
    private int mCoapCode = 0;

    @JsonCreator
    public McuMgrResponse() {
    }

    public static <T extends McuMgrResponse> T buildCoapResponse(McuMgrScheme mcuMgrScheme, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, int i2, Class<T> cls) {
        if (i != 4 && i != 5) {
            T t = (T) CBOR.toObject(bArr3, cls);
            t.initFields(mcuMgrScheme, bArr, McuMgrHeader.fromBytes(bArr2), bArr3, McuMgrErrorCode.valueOf(t.rc));
            t.setCoapCode((i * 100) + i2);
            return t;
        }
        LOG.a("Received CoAP Error response, throwing McuMgrCoapException");
        throw new McuMgrCoapException(bArr, i, i2);
    }

    public static <T extends McuMgrResponse> T buildResponse(McuMgrScheme mcuMgrScheme, byte[] bArr, Class<T> cls) {
        if (!mcuMgrScheme.isCoap()) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 8, bArr.length);
            McuMgrHeader fromBytes = McuMgrHeader.fromBytes(Arrays.copyOf(bArr, 8));
            T t = (T) CBOR.toObject(copyOfRange, cls);
            t.initFields(mcuMgrScheme, bArr, fromBytes, copyOfRange, McuMgrErrorCode.valueOf(t.rc));
            return t;
        }
        throw new IllegalArgumentException("Cannot use this method with a CoAP scheme");
    }

    public static int getExpectedLength(McuMgrScheme mcuMgrScheme, byte[] bArr) {
        if (!mcuMgrScheme.isCoap()) {
            if (bArr.length >= 8) {
                return McuMgrHeader.fromBytes(Arrays.copyOf(bArr, 8)).getLen() + 8;
            }
            throw new IOException("Invalid McuMgrHeader");
        }
        throw new UnsupportedOperationException("Method not implemented for CoAP");
    }

    public byte[] getBytes() {
        return this.mBytes;
    }

    public int getCoapCode() {
        return this.mCoapCode;
    }

    public McuMgrHeader getHeader() {
        return this.mHeader;
    }

    public byte[] getPayload() {
        return this.mPayload;
    }

    public McuMgrErrorCode getReturnCode() {
        return this.mReturnCode;
    }

    public int getReturnCodeValue() {
        McuMgrErrorCode mcuMgrErrorCode = this.mReturnCode;
        if (mcuMgrErrorCode == null) {
            LOG.c("Response does not contain a McuMgr return code.");
            return 0;
        }
        return mcuMgrErrorCode.value();
    }

    public McuMgrScheme getScheme() {
        return this.mScheme;
    }

    void initFields(McuMgrScheme mcuMgrScheme, byte[] bArr, McuMgrHeader mcuMgrHeader, byte[] bArr2, McuMgrErrorCode mcuMgrErrorCode) {
        this.mScheme = mcuMgrScheme;
        this.mBytes = bArr;
        this.mHeader = mcuMgrHeader;
        this.mPayload = bArr2;
        this.mReturnCode = mcuMgrErrorCode;
    }

    public boolean isSuccess() {
        return this.rc == McuMgrErrorCode.OK.value();
    }

    void setCoapCode(int i) {
        this.mCoapCode = i;
    }

    public String toString() {
        try {
            return CBOR.toString(this.mPayload);
        } catch (IOException e2) {
            LOG.a("Failed to parse response", (Throwable) e2);
            return "Failed to parse response";
        }
    }
}
