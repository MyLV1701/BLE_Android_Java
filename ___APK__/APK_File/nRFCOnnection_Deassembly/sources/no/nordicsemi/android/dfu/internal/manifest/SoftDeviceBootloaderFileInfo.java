package no.nordicsemi.android.dfu.internal.manifest;

import c.a.b.v.c;

/* loaded from: classes.dex */
public class SoftDeviceBootloaderFileInfo extends FileInfo {

    @c("bl_size")
    private int bootloaderSize;

    @c("sd_size")
    private int softdeviceSize;

    public int getBootloaderSize() {
        return this.bootloaderSize;
    }

    public int getSoftdeviceSize() {
        return this.softdeviceSize;
    }
}
