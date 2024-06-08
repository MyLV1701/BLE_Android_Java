package no.nordicsemi.android.dfu.internal.manifest;

import c.a.b.v.c;

/* loaded from: classes.dex */
public class FileInfo {

    @c("bin_file")
    private String binFile;

    @c("dat_file")
    private String datFile;

    public String getBinFileName() {
        return this.binFile;
    }

    public String getDatFileName() {
        return this.datFile;
    }
}
