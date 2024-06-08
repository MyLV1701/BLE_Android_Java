package no.nordicsemi.android.dfu.internal.manifest;

import c.a.b.v.c;

/* loaded from: classes.dex */
public class Manifest {
    private FileInfo application;
    private FileInfo bootloader;

    @c("bootloader_application")
    private FileInfo bootloaderApplication;
    private FileInfo softdevice;

    @c("softdevice_application")
    private FileInfo softdeviceApplication;

    @c("softdevice_bootloader")
    private SoftDeviceBootloaderFileInfo softdeviceBootloader;

    @c("softdevice_bootloader_application")
    private FileInfo softdeviceBootloaderApplication;

    public FileInfo getApplicationInfo() {
        FileInfo fileInfo = this.application;
        if (fileInfo != null) {
            return fileInfo;
        }
        FileInfo fileInfo2 = this.softdeviceApplication;
        if (fileInfo2 != null) {
            return fileInfo2;
        }
        FileInfo fileInfo3 = this.bootloaderApplication;
        return fileInfo3 != null ? fileInfo3 : this.softdeviceBootloaderApplication;
    }

    public FileInfo getBootloaderInfo() {
        return this.bootloader;
    }

    public SoftDeviceBootloaderFileInfo getSoftdeviceBootloaderInfo() {
        return this.softdeviceBootloader;
    }

    public FileInfo getSoftdeviceInfo() {
        return this.softdevice;
    }

    public boolean isSecureDfuRequired() {
        return (this.bootloaderApplication == null && this.softdeviceApplication == null && this.softdeviceBootloaderApplication == null) ? false : true;
    }
}
