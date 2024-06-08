package no.nordicsemi.android.dfu.internal;

import android.util.Log;
import c.a.b.e;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import no.nordicsemi.android.dfu.internal.manifest.Manifest;
import no.nordicsemi.android.dfu.internal.manifest.ManifestFile;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public class ArchiveInputStream extends InputStream {
    private static final String APPLICATION_BIN = "application.bin";
    private static final String APPLICATION_HEX = "application.hex";
    private static final String APPLICATION_INIT = "application.dat";
    private static final String BOOTLOADER_BIN = "bootloader.bin";
    private static final String BOOTLOADER_HEX = "bootloader.hex";
    private static final String MANIFEST = "manifest.json";
    private static final String SOFTDEVICE_BIN = "softdevice.bin";
    private static final String SOFTDEVICE_HEX = "softdevice.hex";
    private static final String SYSTEM_INIT = "system.dat";
    private static final String TAG = "DfuArchiveInputStream";
    private byte[] applicationBytes;
    private byte[] applicationInitBytes;
    private int applicationSize;
    private byte[] bootloaderBytes;
    private int bootloaderSize;
    private int bytesReadFromMarkedSource;
    private byte[] currentSource;
    private Manifest manifest;
    private byte[] markedSource;
    private byte[] softDeviceAndBootloaderBytes;
    private byte[] softDeviceBytes;
    private int softDeviceSize;
    private byte[] systemInitBytes;
    private int type;
    private final ZipInputStream zipInputStream;
    private CRC32 crc32 = new CRC32();
    private Map<String, byte[]> entries = new HashMap();
    private int bytesRead = 0;
    private int bytesReadFromCurrentSource = 0;

    /* JADX WARN: Removed duplicated region for block: B:77:0x028f A[Catch: all -> 0x0297, TRY_ENTER, TryCatch #0 {all -> 0x0297, blocks: (B:3:0x001d, B:5:0x0025, B:11:0x0035, B:13:0x005b, B:14:0x0085, B:17:0x008f, B:19:0x0093, B:21:0x0097, B:23:0x00bd, B:24:0x00c8, B:25:0x00e5, B:27:0x00e6, B:28:0x00ed, B:29:0x00ee, B:32:0x00f8, B:34:0x00fc, B:36:0x0122, B:37:0x012d, B:38:0x014a, B:39:0x014b, B:42:0x0155, B:44:0x0159, B:46:0x015d, B:48:0x0161, B:50:0x0187, B:51:0x0199, B:52:0x01b6, B:53:0x01b7, B:54:0x01be, B:57:0x0280, B:60:0x01c3, B:61:0x01ca, B:62:0x0066, B:63:0x0083, B:66:0x01cd, B:74:0x0247, B:77:0x028f, B:78:0x0296, B:79:0x024a, B:81:0x025a, B:82:0x0266, B:84:0x026a, B:85:0x0211, B:87:0x0221, B:88:0x022d, B:90:0x0231, B:91:0x01d1, B:93:0x01e1, B:94:0x01ed, B:96:0x01f1), top: B:2:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x025a A[Catch: all -> 0x0297, TryCatch #0 {all -> 0x0297, blocks: (B:3:0x001d, B:5:0x0025, B:11:0x0035, B:13:0x005b, B:14:0x0085, B:17:0x008f, B:19:0x0093, B:21:0x0097, B:23:0x00bd, B:24:0x00c8, B:25:0x00e5, B:27:0x00e6, B:28:0x00ed, B:29:0x00ee, B:32:0x00f8, B:34:0x00fc, B:36:0x0122, B:37:0x012d, B:38:0x014a, B:39:0x014b, B:42:0x0155, B:44:0x0159, B:46:0x015d, B:48:0x0161, B:50:0x0187, B:51:0x0199, B:52:0x01b6, B:53:0x01b7, B:54:0x01be, B:57:0x0280, B:60:0x01c3, B:61:0x01ca, B:62:0x0066, B:63:0x0083, B:66:0x01cd, B:74:0x0247, B:77:0x028f, B:78:0x0296, B:79:0x024a, B:81:0x025a, B:82:0x0266, B:84:0x026a, B:85:0x0211, B:87:0x0221, B:88:0x022d, B:90:0x0231, B:91:0x01d1, B:93:0x01e1, B:94:0x01ed, B:96:0x01f1), top: B:2:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x026a A[Catch: all -> 0x0297, TryCatch #0 {all -> 0x0297, blocks: (B:3:0x001d, B:5:0x0025, B:11:0x0035, B:13:0x005b, B:14:0x0085, B:17:0x008f, B:19:0x0093, B:21:0x0097, B:23:0x00bd, B:24:0x00c8, B:25:0x00e5, B:27:0x00e6, B:28:0x00ed, B:29:0x00ee, B:32:0x00f8, B:34:0x00fc, B:36:0x0122, B:37:0x012d, B:38:0x014a, B:39:0x014b, B:42:0x0155, B:44:0x0159, B:46:0x015d, B:48:0x0161, B:50:0x0187, B:51:0x0199, B:52:0x01b6, B:53:0x01b7, B:54:0x01be, B:57:0x0280, B:60:0x01c3, B:61:0x01ca, B:62:0x0066, B:63:0x0083, B:66:0x01cd, B:74:0x0247, B:77:0x028f, B:78:0x0296, B:79:0x024a, B:81:0x025a, B:82:0x0266, B:84:0x026a, B:85:0x0211, B:87:0x0221, B:88:0x022d, B:90:0x0231, B:91:0x01d1, B:93:0x01e1, B:94:0x01ed, B:96:0x01f1), top: B:2:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0221 A[Catch: all -> 0x0297, TryCatch #0 {all -> 0x0297, blocks: (B:3:0x001d, B:5:0x0025, B:11:0x0035, B:13:0x005b, B:14:0x0085, B:17:0x008f, B:19:0x0093, B:21:0x0097, B:23:0x00bd, B:24:0x00c8, B:25:0x00e5, B:27:0x00e6, B:28:0x00ed, B:29:0x00ee, B:32:0x00f8, B:34:0x00fc, B:36:0x0122, B:37:0x012d, B:38:0x014a, B:39:0x014b, B:42:0x0155, B:44:0x0159, B:46:0x015d, B:48:0x0161, B:50:0x0187, B:51:0x0199, B:52:0x01b6, B:53:0x01b7, B:54:0x01be, B:57:0x0280, B:60:0x01c3, B:61:0x01ca, B:62:0x0066, B:63:0x0083, B:66:0x01cd, B:74:0x0247, B:77:0x028f, B:78:0x0296, B:79:0x024a, B:81:0x025a, B:82:0x0266, B:84:0x026a, B:85:0x0211, B:87:0x0221, B:88:0x022d, B:90:0x0231, B:91:0x01d1, B:93:0x01e1, B:94:0x01ed, B:96:0x01f1), top: B:2:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0231 A[Catch: all -> 0x0297, TryCatch #0 {all -> 0x0297, blocks: (B:3:0x001d, B:5:0x0025, B:11:0x0035, B:13:0x005b, B:14:0x0085, B:17:0x008f, B:19:0x0093, B:21:0x0097, B:23:0x00bd, B:24:0x00c8, B:25:0x00e5, B:27:0x00e6, B:28:0x00ed, B:29:0x00ee, B:32:0x00f8, B:34:0x00fc, B:36:0x0122, B:37:0x012d, B:38:0x014a, B:39:0x014b, B:42:0x0155, B:44:0x0159, B:46:0x015d, B:48:0x0161, B:50:0x0187, B:51:0x0199, B:52:0x01b6, B:53:0x01b7, B:54:0x01be, B:57:0x0280, B:60:0x01c3, B:61:0x01ca, B:62:0x0066, B:63:0x0083, B:66:0x01cd, B:74:0x0247, B:77:0x028f, B:78:0x0296, B:79:0x024a, B:81:0x025a, B:82:0x0266, B:84:0x026a, B:85:0x0211, B:87:0x0221, B:88:0x022d, B:90:0x0231, B:91:0x01d1, B:93:0x01e1, B:94:0x01ed, B:96:0x01f1), top: B:2:0x001d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ArchiveInputStream(java.io.InputStream r5, int r6, int r7) {
        /*
            Method dump skipped, instructions count: 676
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.internal.ArchiveInputStream.<init>(java.io.InputStream, int, int):void");
    }

    private void parseZip(int i) {
        byte[] bArr = new byte[1024];
        String str = null;
        while (true) {
            ZipEntry nextEntry = this.zipInputStream.getNextEntry();
            if (nextEntry == null) {
                break;
            }
            String name = nextEntry.getName();
            if (nextEntry.isDirectory()) {
                Log.w(TAG, "A directory found in the ZIP: " + name + "!");
            } else {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int read = this.zipInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    } else {
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                if (name.toLowerCase(Locale.US).endsWith("hex")) {
                    HexInputStream hexInputStream = new HexInputStream(byteArray, i);
                    byteArray = new byte[hexInputStream.available()];
                    hexInputStream.read(byteArray);
                    hexInputStream.close();
                }
                if (MANIFEST.equals(name)) {
                    str = new String(byteArray, "UTF-8");
                } else {
                    this.entries.put(name, byteArray);
                }
            }
        }
        if (this.entries.isEmpty()) {
            throw new FileNotFoundException("No files found in the ZIP. Check if the URI provided is valid and the ZIP contains required files on root level, not in a directory.");
        }
        if (str != null) {
            this.manifest = ((ManifestFile) new e().a(str, ManifestFile.class)).getManifest();
            if (this.manifest == null) {
                Log.w(TAG, "Manifest failed to be parsed. Did you add \n-keep class no.nordicsemi.android.dfu.** { *; }\nto your proguard rules?");
                return;
            }
            return;
        }
        Log.w(TAG, "Manifest not found in the ZIP. It is recommended to use a distribution file created with: https://github.com/NordicSemiconductor/pc-nrfutil/ (for Legacy DFU use version 0.5.x)");
    }

    private int rawRead(byte[] bArr, int i, int i2) {
        int length = this.currentSource.length - this.bytesReadFromCurrentSource;
        if (i2 > length) {
            i2 = length;
        }
        System.arraycopy(this.currentSource, this.bytesReadFromCurrentSource, bArr, i, i2);
        this.bytesReadFromCurrentSource += i2;
        this.bytesRead += i2;
        this.crc32.update(bArr, i, i2);
        return i2;
    }

    private byte[] startNextFile() {
        byte[] bArr;
        if (this.currentSource == this.softDeviceBytes && (bArr = this.bootloaderBytes) != null && (this.type & 2) > 0) {
            this.currentSource = bArr;
        } else {
            byte[] bArr2 = this.currentSource;
            byte[] bArr3 = this.applicationBytes;
            if (bArr2 != bArr3 && bArr3 != null && (this.type & 4) > 0) {
                this.currentSource = bArr3;
                bArr = bArr3;
            } else {
                bArr = null;
                this.currentSource = null;
            }
        }
        this.bytesReadFromCurrentSource = 0;
        return bArr;
    }

    public int applicationImageSize() {
        if ((this.type & 4) > 0) {
            return this.applicationSize;
        }
        return 0;
    }

    @Override // java.io.InputStream
    public int available() {
        int softDeviceImageSize;
        int i;
        byte[] bArr = this.softDeviceAndBootloaderBytes;
        if (bArr != null && this.softDeviceSize == 0 && this.bootloaderSize == 0 && (this.type & 3) > 0) {
            softDeviceImageSize = bArr.length + applicationImageSize();
            i = this.bytesRead;
        } else {
            softDeviceImageSize = softDeviceImageSize() + bootloaderImageSize() + applicationImageSize();
            i = this.bytesRead;
        }
        return softDeviceImageSize - i;
    }

    public int bootloaderImageSize() {
        if ((this.type & 2) > 0) {
            return this.bootloaderSize;
        }
        return 0;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.softDeviceBytes = null;
        this.bootloaderBytes = null;
        this.applicationBytes = null;
        this.softDeviceAndBootloaderBytes = null;
        this.applicationSize = 0;
        this.bootloaderSize = 0;
        this.softDeviceSize = 0;
        this.currentSource = null;
        this.bytesReadFromCurrentSource = 0;
        this.bytesRead = 0;
        this.zipInputStream.close();
    }

    public void fullReset() {
        byte[] bArr;
        byte[] bArr2 = this.softDeviceBytes;
        if (bArr2 != null && (bArr = this.bootloaderBytes) != null && this.currentSource == bArr) {
            this.currentSource = bArr2;
        }
        this.bytesReadFromCurrentSource = 0;
        mark(0);
        reset();
    }

    public byte[] getApplicationInit() {
        return this.applicationInitBytes;
    }

    public int getBytesRead() {
        return this.bytesRead;
    }

    public int getContentType() {
        this.type = 0;
        if (this.softDeviceAndBootloaderBytes != null) {
            this.type |= 3;
        }
        if (this.softDeviceSize > 0) {
            this.type |= 1;
        }
        if (this.bootloaderSize > 0) {
            this.type |= 2;
        }
        if (this.applicationSize > 0) {
            this.type |= 4;
        }
        return this.type;
    }

    public long getCrc32() {
        return this.crc32.getValue();
    }

    public byte[] getSystemInit() {
        return this.systemInitBytes;
    }

    public boolean isSecureDfuRequired() {
        Manifest manifest = this.manifest;
        return manifest != null && manifest.isSecureDfuRequired();
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.markedSource = this.currentSource;
        this.bytesReadFromMarkedSource = this.bytesReadFromCurrentSource;
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.InputStream
    public int read() {
        byte[] bArr = new byte[1];
        if (read(bArr) == -1) {
            return -1;
        }
        return bArr[0] & FlagsParser.UNKNOWN_FLAGS;
    }

    @Override // java.io.InputStream
    public void reset() {
        byte[] bArr;
        this.currentSource = this.markedSource;
        int i = this.bytesReadFromMarkedSource;
        this.bytesReadFromCurrentSource = i;
        this.bytesRead = i;
        this.crc32.reset();
        if (this.currentSource == this.bootloaderBytes && (bArr = this.softDeviceBytes) != null) {
            this.crc32.update(bArr);
            this.bytesRead += this.softDeviceSize;
        }
        this.crc32.update(this.currentSource, 0, this.bytesReadFromCurrentSource);
    }

    public int setContentType(int i) {
        byte[] bArr;
        this.type = i;
        int i2 = i & 4;
        if (i2 > 0 && this.applicationBytes == null) {
            this.type &= -5;
        }
        int i3 = i & 3;
        if (i3 == 3) {
            if (this.softDeviceBytes == null && this.softDeviceAndBootloaderBytes == null) {
                this.type &= -2;
            }
            if (this.bootloaderBytes == null && this.softDeviceAndBootloaderBytes == null) {
                this.type &= -2;
            }
        } else if (this.softDeviceAndBootloaderBytes != null) {
            this.type &= -4;
        }
        if (i3 > 0 && (bArr = this.softDeviceAndBootloaderBytes) != null) {
            this.currentSource = bArr;
        } else if ((i & 1) > 0) {
            this.currentSource = this.softDeviceBytes;
        } else if ((i & 2) > 0) {
            this.currentSource = this.bootloaderBytes;
        } else if (i2 > 0) {
            this.currentSource = this.applicationBytes;
        }
        this.bytesReadFromCurrentSource = 0;
        mark(0);
        reset();
        return this.type;
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        return 0L;
    }

    public int softDeviceImageSize() {
        if ((this.type & 1) > 0) {
            return this.softDeviceSize;
        }
        return 0;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        int rawRead = rawRead(bArr, i, i2);
        return (i2 <= rawRead || startNextFile() == null) ? rawRead : rawRead + rawRead(bArr, i + rawRead, i2 - rawRead);
    }
}
