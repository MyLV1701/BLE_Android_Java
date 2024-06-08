package io.runtime.mcumgr;

import f.a.b;
import f.a.c;
import io.runtime.mcumgr.exception.McuMgrException;
import io.runtime.mcumgr.response.McuMgrResponse;
import io.runtime.mcumgr.util.CBOR;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: classes.dex */
public abstract class McuManager {
    private static final String COAP_URI = "/omgr";
    private static final int DEFAULT_MTU = 515;
    protected static final int GROUP_CONFIG = 3;
    protected static final int GROUP_CRASH = 5;
    protected static final int GROUP_DEFAULT = 0;
    protected static final int GROUP_FS = 8;
    protected static final int GROUP_IMAGE = 1;
    protected static final int GROUP_LOGS = 4;
    protected static final int GROUP_PERUSER = 64;
    protected static final int GROUP_RUN = 7;
    protected static final int GROUP_SPLIT = 6;
    protected static final int GROUP_STATS = 2;
    private static final String HEADER_KEY = "_h";
    private static final b LOG = c.a((Class<?>) McuManager.class);
    private static final String MCUMGR_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZZZZZ";
    protected static final int OP_READ = 0;
    protected static final int OP_READ_RSP = 1;
    protected static final int OP_WRITE = 2;
    protected static final int OP_WRITE_RSP = 3;
    private final int mGroupId;
    protected int mMtu = DEFAULT_MTU;
    private final McuMgrTransport mTransporter;

    /* JADX INFO: Access modifiers changed from: protected */
    public McuManager(int i, McuMgrTransport mcuMgrTransport) {
        this.mGroupId = i;
        this.mTransporter = mcuMgrTransport;
    }

    public static byte[] buildPacket(McuMgrScheme mcuMgrScheme, int i, int i2, int i3, int i4, int i5, Map<String, Object> map) {
        Map map2 = map;
        if (map == null) {
            try {
                map2 = new HashMap();
            } catch (IOException e2) {
                throw new McuMgrException("An error occurred serializing CBOR payload", e2);
            }
        }
        HashMap hashMap = new HashMap(map2);
        hashMap.remove(HEADER_KEY);
        byte[] build = McuMgrHeader.build(i, i2, CBOR.toBytes(hashMap).length, i3, i4, i5);
        if (mcuMgrScheme.isCoap()) {
            if (map2.get(HEADER_KEY) == null) {
                map2.put(HEADER_KEY, build);
            }
            return CBOR.toBytes(map2);
        }
        byte[] bytes = CBOR.toBytes(map2);
        byte[] bArr = new byte[build.length + bytes.length];
        System.arraycopy(build, 0, bArr, 0, build.length);
        System.arraycopy(bytes, 0, bArr, build.length, bytes.length);
        return bArr;
    }

    public static String dateToString(Date date, TimeZone timeZone) {
        if (date == null) {
            date = new Date();
        }
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MCUMGR_DATE_FORMAT, new Locale("US"));
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(date);
    }

    public static Date stringToDate(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(MCUMGR_DATE_FORMAT, new Locale("US")).parse(str);
        } catch (ParseException e2) {
            LOG.a("Converting string to Date failed", (Throwable) e2);
            return null;
        }
    }

    public int getGroupId() {
        return this.mGroupId;
    }

    public synchronized int getMtu() {
        return this.mMtu;
    }

    public McuMgrScheme getScheme() {
        return this.mTransporter.getScheme();
    }

    public McuMgrTransport getTransporter() {
        return this.mTransporter;
    }

    public <T extends McuMgrResponse> void send(int i, int i2, Map<String, Object> map, Class<T> cls, McuMgrCallback<T> mcuMgrCallback) {
        send(i, 0, 0, i2, map, cls, mcuMgrCallback);
    }

    public synchronized boolean setUploadMtu(int i) {
        if (i < 20) {
            LOG.a("MTU is too small! Must be greater than 20.");
            return false;
        }
        if (i > 1024) {
            LOG.a("MTU is too large! Must be less than 1024.");
            return false;
        }
        this.mMtu = i;
        return true;
    }

    public <T extends McuMgrResponse> T send(int i, int i2, Map<String, Object> map, Class<T> cls) {
        return (T) send(i, 0, 0, i2, cls, map);
    }

    public <T extends McuMgrResponse> void send(int i, int i2, int i3, int i4, Map<String, Object> map, Class<T> cls, McuMgrCallback<T> mcuMgrCallback) {
        try {
            send(buildPacket(getScheme(), i, i2, this.mGroupId, i3, i4, map), cls, mcuMgrCallback);
        } catch (McuMgrException e2) {
            mcuMgrCallback.onError(e2);
        }
    }

    public <T extends McuMgrResponse> T send(int i, int i2, int i3, int i4, Class<T> cls, Map<String, Object> map) {
        return (T) send(buildPacket(getScheme(), i, i2, this.mGroupId, i3, i4, map), cls);
    }

    public <T extends McuMgrResponse> void send(byte[] bArr, Class<T> cls, McuMgrCallback<T> mcuMgrCallback) {
        this.mTransporter.send(bArr, cls, mcuMgrCallback);
    }

    public <T extends McuMgrResponse> T send(byte[] bArr, Class<T> cls) {
        return (T) this.mTransporter.send(bArr, cls);
    }
}
