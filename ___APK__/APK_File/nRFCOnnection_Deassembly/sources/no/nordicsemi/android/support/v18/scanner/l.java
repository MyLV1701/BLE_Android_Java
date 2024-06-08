package no.nordicsemi.android.support.v18.scanner;

import android.os.ParcelUuid;
import android.util.Log;
import android.util.SparseArray;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public final class l {

    /* renamed from: a, reason: collision with root package name */
    private final int f3959a;

    /* renamed from: b, reason: collision with root package name */
    private final List<ParcelUuid> f3960b;

    /* renamed from: c, reason: collision with root package name */
    private final SparseArray<byte[]> f3961c;

    /* renamed from: d, reason: collision with root package name */
    private final Map<ParcelUuid, byte[]> f3962d;

    /* renamed from: e, reason: collision with root package name */
    private final int f3963e;

    /* renamed from: f, reason: collision with root package name */
    private final String f3964f;
    private final byte[] g;

    private l(List<ParcelUuid> list, SparseArray<byte[]> sparseArray, Map<ParcelUuid, byte[]> map, int i, int i2, String str, byte[] bArr) {
        this.f3960b = list;
        this.f3961c = sparseArray;
        this.f3962d = map;
        this.f3964f = str;
        this.f3959a = i;
        this.f3963e = i2;
        this.g = bArr;
    }

    public byte[] a(int i) {
        SparseArray<byte[]> sparseArray = this.f3961c;
        if (sparseArray == null) {
            return null;
        }
        return sparseArray.get(i);
    }

    public String b() {
        return this.f3964f;
    }

    public List<ParcelUuid> c() {
        return this.f3960b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || l.class != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.g, ((l) obj).g);
    }

    public String toString() {
        return "ScanRecord [advertiseFlags=" + this.f3959a + ", serviceUuids=" + this.f3960b + ", manufacturerSpecificData=" + f.a(this.f3961c) + ", serviceData=" + f.a(this.f3962d) + ", txPowerLevel=" + this.f3963e + ", deviceName=" + this.f3964f + "]";
    }

    public byte[] a(ParcelUuid parcelUuid) {
        Map<ParcelUuid, byte[]> map;
        if (parcelUuid == null || (map = this.f3962d) == null) {
            return null;
        }
        return map.get(parcelUuid);
    }

    public byte[] a() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static l a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int i = 0;
        ArrayList arrayList = null;
        SparseArray sparseArray = null;
        String str = null;
        HashMap hashMap = null;
        int i2 = -1;
        byte b2 = -2147483648;
        while (i < bArr.length) {
            try {
                int i3 = i + 1;
                int i4 = bArr[i] & FlagsParser.UNKNOWN_FLAGS;
                if (i4 != 0) {
                    int i5 = i4 - 1;
                    int i6 = i3 + 1;
                    int i7 = bArr[i3] & FlagsParser.UNKNOWN_FLAGS;
                    if (i7 != 22) {
                        if (i7 == 255) {
                            int i8 = ((bArr[i6 + 1] & FlagsParser.UNKNOWN_FLAGS) << 8) + (255 & bArr[i6]);
                            byte[] a2 = a(bArr, i6 + 2, i5 - 2);
                            if (sparseArray == null) {
                                sparseArray = new SparseArray();
                            }
                            sparseArray.put(i8, a2);
                        } else if (i7 != 32 && i7 != 33) {
                            switch (i7) {
                                case 1:
                                    i2 = bArr[i6] & FlagsParser.UNKNOWN_FLAGS;
                                    break;
                                case 2:
                                case 3:
                                    if (arrayList == null) {
                                        arrayList = new ArrayList();
                                    }
                                    a(bArr, i6, i5, 2, arrayList);
                                    break;
                                case 4:
                                case 5:
                                    if (arrayList == null) {
                                        arrayList = new ArrayList();
                                    }
                                    a(bArr, i6, i5, 4, arrayList);
                                    break;
                                case 6:
                                case 7:
                                    if (arrayList == null) {
                                        arrayList = new ArrayList();
                                    }
                                    a(bArr, i6, i5, 16, arrayList);
                                    break;
                                case 8:
                                case 9:
                                    str = new String(a(bArr, i6, i5));
                                    break;
                                case 10:
                                    b2 = bArr[i6];
                                    break;
                            }
                        }
                        i = i5 + i6;
                    }
                    int i9 = 4;
                    if (i7 != 32) {
                        i9 = i7 == 33 ? 16 : 2;
                    }
                    ParcelUuid a3 = g.a(a(bArr, i6, i9));
                    byte[] a4 = a(bArr, i6 + i9, i5 - i9);
                    if (hashMap == null) {
                        hashMap = new HashMap();
                    }
                    hashMap.put(a3, a4);
                    i = i5 + i6;
                } else {
                    return new l(arrayList, sparseArray, hashMap, i2, b2, str, bArr);
                }
            } catch (Exception unused) {
                Log.e("ScanRecord", "unable to parse scan record: " + Arrays.toString(bArr));
                return new l(null, null, null, -1, RecyclerView.UNDEFINED_DURATION, null, bArr);
            }
        }
        return new l(arrayList, sparseArray, hashMap, i2, b2, str, bArr);
    }

    private static int a(byte[] bArr, int i, int i2, int i3, List<ParcelUuid> list) {
        while (i2 > 0) {
            list.add(g.a(a(bArr, i, i3)));
            i2 -= i3;
            i += i3;
        }
        return i;
    }

    private static byte[] a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }
}
