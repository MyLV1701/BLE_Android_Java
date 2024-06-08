package io.runtime.mcumgr.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class CBOR {
    private static final CBORFactory sFactory = new CBORFactory();

    public static byte[] toBytes(Object obj) {
        return new ObjectMapper(sFactory).writeValueAsBytes(obj);
    }

    public static <T> T toObject(byte[] bArr, Class<T> cls) {
        return (T) new ObjectMapper(sFactory).readValue(bArr, cls);
    }

    public static Map<String, Object> toObjectMap(byte[] bArr) {
        return (Map) new ObjectMapper(sFactory).readValue(new ByteArrayInputStream(bArr), new TypeReference<HashMap<String, Object>>() { // from class: io.runtime.mcumgr.util.CBOR.2
        });
    }

    public static String toString(byte[] bArr) {
        return new ObjectMapper(sFactory).readTree(bArr).toString();
    }

    public static Map<String, String> toStringMap(byte[] bArr) {
        return (Map) new ObjectMapper(sFactory).readValue(new ByteArrayInputStream(bArr), new TypeReference<HashMap<String, String>>() { // from class: io.runtime.mcumgr.util.CBOR.1
        });
    }

    public static <T> String toString(T t) {
        return new ObjectMapper(sFactory).valueToTree(t).toString();
    }
}
