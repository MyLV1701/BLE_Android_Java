package no.nordicsemi.android.ble.data;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;

/* loaded from: classes.dex */
public class MutableData extends Data {
    private static final int FLOAT_EXPONENT_MAX = 127;
    private static final int FLOAT_EXPONENT_MIN = -128;
    private static final int FLOAT_MANTISSA_MAX = 8388605;
    private static final int FLOAT_NAN = 8388607;
    private static final int FLOAT_NEGATIVE_INFINITY = 8388610;
    private static final int FLOAT_POSITIVE_INFINITY = 8388606;
    private static final int FLOAT_PRECISION = 10000000;
    private static final int SFLOAT_EXPONENT_MAX = 7;
    private static final int SFLOAT_EXPONENT_MIN = -8;
    private static final int SFLOAT_MANTISSA_MAX = 2045;
    private static final float SFLOAT_MAX = 2.045E10f;
    private static final float SFLOAT_MIN = -2.045E10f;
    private static final int SFLOAT_NAN = 2047;
    private static final int SFLOAT_NEGATIVE_INFINITY = 2050;
    private static final int SFLOAT_POSITIVE_INFINITY = 2046;
    private static final int SFLOAT_PRECISION = 10000;

    public MutableData() {
    }

    private static int floatToInt(float f2) {
        if (Float.isNaN(f2)) {
            return FLOAT_NAN;
        }
        if (f2 == Float.POSITIVE_INFINITY) {
            return FLOAT_POSITIVE_INFINITY;
        }
        if (f2 == Float.NEGATIVE_INFINITY) {
            return FLOAT_NEGATIVE_INFINITY;
        }
        int i = f2 >= 0.0f ? 1 : -1;
        float abs = Math.abs(f2);
        int i2 = 0;
        while (abs > 8388605.0f) {
            abs /= 10.0f;
            i2++;
            if (i2 > FLOAT_EXPONENT_MAX) {
                return i > 0 ? FLOAT_POSITIVE_INFINITY : FLOAT_NEGATIVE_INFINITY;
            }
        }
        while (abs < 1.0f) {
            abs *= 10.0f;
            i2--;
            if (i2 < FLOAT_EXPONENT_MIN) {
                return 0;
            }
        }
        double round = Math.round(abs * 1.0E7f);
        double round2 = Math.round(abs) * FLOAT_PRECISION;
        Double.isNaN(round);
        Double.isNaN(round2);
        double abs2 = Math.abs(round - round2);
        while (abs2 > 0.5d && i2 > FLOAT_EXPONENT_MIN) {
            float f3 = abs * 10.0f;
            if (f3 > 8388605.0f) {
                break;
            }
            i2--;
            double round3 = Math.round(f3 * 1.0E7f);
            double round4 = Math.round(f3) * FLOAT_PRECISION;
            Double.isNaN(round3);
            Double.isNaN(round4);
            abs2 = Math.abs(round3 - round4);
            abs = f3;
        }
        return (Math.round(i * abs) & 16777215) | (i2 << 24);
    }

    public static MutableData from(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return new MutableData(bluetoothGattCharacteristic.getValue());
    }

    private static int intToSignedBits(int i, int i2) {
        if (i >= 0) {
            return i;
        }
        int i3 = 1 << (i2 - 1);
        return (i & (i3 - 1)) + i3;
    }

    private static long longToSignedBits(long j, int i) {
        if (j >= 0) {
            return j;
        }
        long j2 = 1 << (i - 1);
        return (j & (j2 - 1)) + j2;
    }

    private static int sfloatToInt(float f2) {
        if (Float.isNaN(f2)) {
            return SFLOAT_NAN;
        }
        if (f2 > SFLOAT_MAX) {
            return SFLOAT_POSITIVE_INFINITY;
        }
        if (f2 < SFLOAT_MIN) {
            return SFLOAT_NEGATIVE_INFINITY;
        }
        int i = f2 >= 0.0f ? 1 : -1;
        float abs = Math.abs(f2);
        int i2 = 0;
        while (abs > 2045.0f) {
            abs /= 10.0f;
            i2++;
            if (i2 > 7) {
                return i > 0 ? SFLOAT_POSITIVE_INFINITY : SFLOAT_NEGATIVE_INFINITY;
            }
        }
        while (abs < 1.0f) {
            abs *= 10.0f;
            i2--;
            if (i2 < SFLOAT_EXPONENT_MIN) {
                return 0;
            }
        }
        double round = Math.round(abs * 10000.0f);
        double round2 = Math.round(abs) * SFLOAT_PRECISION;
        Double.isNaN(round);
        Double.isNaN(round2);
        double abs2 = Math.abs(round - round2);
        while (abs2 > 0.5d && i2 > SFLOAT_EXPONENT_MIN) {
            float f3 = abs * 10.0f;
            if (f3 > 2045.0f) {
                break;
            }
            i2--;
            double round3 = Math.round(f3 * 10000.0f);
            double round4 = Math.round(f3) * SFLOAT_PRECISION;
            Double.isNaN(round3);
            Double.isNaN(round4);
            abs2 = Math.abs(round3 - round4);
            abs = f3;
        }
        return (Math.round(i * abs) & 4095) | ((i2 & 15) << 12);
    }

    public boolean setByte(int i, int i2) {
        int i3 = i2 + 1;
        if (this.mValue == null) {
            this.mValue = new byte[i3];
        }
        byte[] bArr = this.mValue;
        if (i3 > bArr.length) {
            return false;
        }
        bArr[i2] = (byte) i;
        return true;
    }

    public boolean setValue(byte[] bArr) {
        this.mValue = bArr;
        return true;
    }

    public MutableData(byte[] bArr) {
        super(bArr);
    }

    public static MutableData from(BluetoothGattDescriptor bluetoothGattDescriptor) {
        return new MutableData(bluetoothGattDescriptor.getValue());
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x001d. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x001a. Please report as an issue. */
    public boolean setValue(int i, int i2, int i3) {
        int typeLen = Data.getTypeLen(i2) + i3;
        if (this.mValue == null) {
            this.mValue = new byte[typeLen];
        }
        if (typeLen > this.mValue.length) {
            return false;
        }
        switch (i2) {
            case 17:
                this.mValue[i3] = (byte) (i & 255);
                return true;
            case 18:
                byte[] bArr = this.mValue;
                bArr[i3] = (byte) (i & 255);
                bArr[i3 + 1] = (byte) ((i >> 8) & 255);
                return true;
            case 19:
                byte[] bArr2 = this.mValue;
                int i4 = i3 + 1;
                bArr2[i3] = (byte) (i & 255);
                bArr2[i4] = (byte) ((i >> 8) & 255);
                bArr2[i4 + 1] = (byte) ((i >> 16) & 255);
                return true;
            case 20:
                byte[] bArr3 = this.mValue;
                int i5 = i3 + 1;
                bArr3[i3] = (byte) (i & 255);
                int i6 = i5 + 1;
                bArr3[i5] = (byte) ((i >> 8) & 255);
                bArr3[i6] = (byte) ((i >> 16) & 255);
                bArr3[i6 + 1] = (byte) ((i >> 24) & 255);
                return true;
            default:
                switch (i2) {
                    case 33:
                        i = intToSignedBits(i, 8);
                        this.mValue[i3] = (byte) (i & 255);
                        return true;
                    case 34:
                        i = intToSignedBits(i, 16);
                        byte[] bArr4 = this.mValue;
                        bArr4[i3] = (byte) (i & 255);
                        bArr4[i3 + 1] = (byte) ((i >> 8) & 255);
                        return true;
                    case 35:
                        i = intToSignedBits(i, 24);
                        byte[] bArr22 = this.mValue;
                        int i42 = i3 + 1;
                        bArr22[i3] = (byte) (i & 255);
                        bArr22[i42] = (byte) ((i >> 8) & 255);
                        bArr22[i42 + 1] = (byte) ((i >> 16) & 255);
                        return true;
                    case 36:
                        i = intToSignedBits(i, 32);
                        byte[] bArr32 = this.mValue;
                        int i52 = i3 + 1;
                        bArr32[i3] = (byte) (i & 255);
                        int i62 = i52 + 1;
                        bArr32[i52] = (byte) ((i >> 8) & 255);
                        bArr32[i62] = (byte) ((i >> 16) & 255);
                        bArr32[i62 + 1] = (byte) ((i >> 24) & 255);
                        return true;
                    default:
                        return false;
                }
        }
    }

    public boolean setValue(int i, int i2, int i3, int i4) {
        int typeLen = Data.getTypeLen(i3) + i4;
        if (this.mValue == null) {
            this.mValue = new byte[typeLen];
        }
        if (typeLen > this.mValue.length) {
            return false;
        }
        if (i3 == 50) {
            int intToSignedBits = intToSignedBits(i, 12);
            int intToSignedBits2 = intToSignedBits(i2, 4);
            byte[] bArr = this.mValue;
            int i5 = i4 + 1;
            bArr[i4] = (byte) (intToSignedBits & 255);
            bArr[i5] = (byte) ((intToSignedBits >> 8) & 15);
            bArr[i5] = (byte) (bArr[i5] + ((byte) ((intToSignedBits2 & 15) << 4)));
            return true;
        }
        if (i3 != 52) {
            return false;
        }
        int intToSignedBits3 = intToSignedBits(i, 24);
        int intToSignedBits4 = intToSignedBits(i2, 8);
        byte[] bArr2 = this.mValue;
        int i6 = i4 + 1;
        bArr2[i4] = (byte) (intToSignedBits3 & 255);
        int i7 = i6 + 1;
        bArr2[i6] = (byte) ((intToSignedBits3 >> 8) & 255);
        int i8 = i7 + 1;
        bArr2[i7] = (byte) ((intToSignedBits3 >> 16) & 255);
        bArr2[i8] = (byte) (bArr2[i8] + ((byte) (intToSignedBits4 & 255)));
        return true;
    }

    public boolean setValue(long j, int i, int i2) {
        int typeLen = Data.getTypeLen(i) + i2;
        if (this.mValue == null) {
            this.mValue = new byte[typeLen];
        }
        if (typeLen > this.mValue.length) {
            return false;
        }
        if (i != 20) {
            if (i != 36) {
                return false;
            }
            j = longToSignedBits(j, 32);
        }
        byte[] bArr = this.mValue;
        int i3 = i2 + 1;
        bArr[i2] = (byte) (j & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((j >> 8) & 255);
        bArr[i4] = (byte) ((j >> 16) & 255);
        bArr[i4 + 1] = (byte) ((j >> 24) & 255);
        return true;
    }

    public boolean setValue(float f2, int i, int i2) {
        int typeLen = Data.getTypeLen(i) + i2;
        if (this.mValue == null) {
            this.mValue = new byte[typeLen];
        }
        if (typeLen > this.mValue.length) {
            return false;
        }
        if (i == 50) {
            int sfloatToInt = sfloatToInt(f2);
            byte[] bArr = this.mValue;
            bArr[i2] = (byte) (sfloatToInt & 255);
            bArr[i2 + 1] = (byte) ((sfloatToInt >> 8) & 255);
            return true;
        }
        if (i != 52) {
            return false;
        }
        int floatToInt = floatToInt(f2);
        byte[] bArr2 = this.mValue;
        int i3 = i2 + 1;
        bArr2[i2] = (byte) (floatToInt & 255);
        int i4 = i3 + 1;
        bArr2[i3] = (byte) ((floatToInt >> 8) & 255);
        int i5 = i4 + 1;
        bArr2[i4] = (byte) ((floatToInt >> 16) & 255);
        bArr2[i5] = (byte) (bArr2[i5] + ((byte) ((floatToInt >> 24) & 255)));
        return true;
    }
}
