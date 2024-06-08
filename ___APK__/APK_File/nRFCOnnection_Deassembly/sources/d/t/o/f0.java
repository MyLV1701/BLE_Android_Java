package d.t.o;

import java.io.File;
import java.io.OutputStream;
import java.io.RandomAccessFile;

/* loaded from: classes.dex */
class f0 implements a0 {

    /* renamed from: a, reason: collision with root package name */
    private File f3285a;

    /* renamed from: b, reason: collision with root package name */
    private RandomAccessFile f3286b;

    static {
        d.q.c.b(f0.class);
    }

    public f0(File file) {
        this.f3285a = File.createTempFile("jxl", ".tmp", file);
        this.f3285a.deleteOnExit();
        this.f3286b = new RandomAccessFile(this.f3285a, "rw");
    }

    @Override // d.t.o.a0
    public void a(byte[] bArr) {
        this.f3286b.write(bArr);
    }

    @Override // d.t.o.a0
    public void close() {
        this.f3286b.close();
        this.f3285a.delete();
    }

    @Override // d.t.o.a0
    public int getPosition() {
        return (int) this.f3286b.getFilePointer();
    }

    @Override // d.t.o.a0
    public void a(byte[] bArr, int i) {
        long filePointer = this.f3286b.getFilePointer();
        this.f3286b.seek(i);
        this.f3286b.write(bArr);
        this.f3286b.seek(filePointer);
    }

    @Override // d.t.o.a0
    public void a(OutputStream outputStream) {
        byte[] bArr = new byte[1024];
        this.f3286b.seek(0L);
        while (true) {
            int read = this.f3286b.read(bArr);
            if (read == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, read);
            }
        }
    }
}
