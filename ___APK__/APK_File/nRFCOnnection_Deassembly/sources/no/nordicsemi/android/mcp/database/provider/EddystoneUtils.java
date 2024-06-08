package no.nordicsemi.android.mcp.database.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import f.b.b.a.a;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class EddystoneUtils {
    private static final String PREFS_EDDYSTONE_LOCK_KEY = "eddystone_lock_key";
    private static final String PREFS_EDDYSTONE_SERVICE_PUBLIC_ECDH = "eddystone_service_public_ecdh";
    private static final String TAG = "EddystoneUtils";

    static {
        Security.insertProviderAt(new a(), 1);
    }

    public static byte[] aes128Decode(byte[] bArr, SecretKeySpec secretKeySpec) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            try {
                cipher.init(2, secretKeySpec);
                try {
                    return cipher.doFinal(bArr);
                } catch (BadPaddingException | IllegalBlockSizeException e2) {
                    Log.e(TAG, "Error executing cipher", e2);
                    return null;
                }
            } catch (InvalidKeyException e3) {
                Log.e(TAG, "Error initializing cipher instance", e3);
                return null;
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e4) {
            Log.e(TAG, "Error constructing cipher instance", e4);
            return null;
        }
    }

    public static byte[] aes128Encrypt(byte[] bArr, SecretKeySpec secretKeySpec) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            try {
                cipher.init(1, secretKeySpec);
                try {
                    return cipher.doFinal(bArr);
                } catch (BadPaddingException | IllegalBlockSizeException e2) {
                    Log.e(TAG, "Error executing cipher", e2);
                    return null;
                }
            } catch (InvalidKeyException e3) {
                Log.e(TAG, "Error initializing cipher instance", e3);
                return null;
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e4) {
            Log.e(TAG, "Error constructing cipher instance", e4);
            return null;
        }
    }

    @TargetApi(19)
    private static byte[] aesEax128Decode(byte[] bArr, byte[] bArr2, byte[] bArr3, SecretKeySpec secretKeySpec) {
        try {
            Cipher cipher = Cipher.getInstance("AES/EAX/NoPadding", "SC");
            try {
                cipher.init(2, secretKeySpec, new GCMParameterSpec(16, bArr2));
                try {
                    cipher.update(bArr);
                    return cipher.doFinal(bArr3);
                } catch (BadPaddingException | IllegalBlockSizeException unused) {
                    return null;
                }
            } catch (InvalidAlgorithmParameterException | InvalidKeyException e2) {
                Log.e(TAG, "Error initializing cipher instance", e2);
                return null;
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e3) {
            Log.e(TAG, "Error constructing cipher instance", e3);
            return null;
        }
    }

    public static byte[] decodeETLM(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 19) {
            return aesEax128Decode(bArr, generateNonce(bArr2, i2, i), bArr3, new SecretKeySpec(bArr4, "AES"));
        }
        return null;
    }

    private static byte[] generateEidrData(int i, int i2) {
        byte[] bArr = new byte[16];
        bArr[11] = (byte) i;
        long j = (i2 >> i) << i;
        bArr[12] = (byte) ((j >> 24) & 255);
        bArr[13] = (byte) ((j >> 16) & 255);
        bArr[14] = (byte) ((j >> 8) & 255);
        bArr[15] = (byte) (j & 255);
        return bArr;
    }

    private static byte[] generateNonce(byte[] bArr, int i, int i2) {
        long j = (i2 >> i) << i;
        return new byte[]{(byte) ((j >> 24) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 8) & 255), (byte) (j & 255), bArr[0], bArr[1]};
    }

    private static byte[] generateTkData(int i) {
        byte[] bArr = new byte[16];
        bArr[11] = -1;
        int i2 = i >> 16;
        bArr[14] = (byte) ((i2 >> 8) & 255);
        bArr[15] = (byte) (i2 & 255);
        return bArr;
    }

    public static byte[] getEidr(byte[] bArr, int i, int i2) {
        byte[] aes128Encrypt;
        byte[] temporaryKey = getTemporaryKey(bArr, i);
        if (temporaryKey == null || (aes128Encrypt = aes128Encrypt(generateEidrData(i2, i), new SecretKeySpec(temporaryKey, "AES"))) == null) {
            return null;
        }
        return Arrays.copyOfRange(aes128Encrypt, 0, 8);
    }

    public static String getLastLockKey(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(PREFS_EDDYSTONE_LOCK_KEY, null);
    }

    public static String getLastServicePublicECDH(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(PREFS_EDDYSTONE_SERVICE_PUBLIC_ECDH, null);
    }

    private static byte[] getTemporaryKey(byte[] bArr, int i) {
        byte[] generateTkData = generateTkData(i);
        if (bArr == null) {
            return null;
        }
        return aes128Encrypt(generateTkData, new SecretKeySpec(bArr, "AES"));
    }

    public static void storeLockKey(Context context, String str) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(PREFS_EDDYSTONE_LOCK_KEY, str).apply();
    }

    public static void storeServicePublicECDH(Context context, String str) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(PREFS_EDDYSTONE_SERVICE_PUBLIC_ECDH, str).apply();
    }
}
