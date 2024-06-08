package a.f.e;

import a.f.d.d.c;
import a.f.i.b;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class h extends i {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // a.f.e.i
    public b.f a(b.f[] fVarArr, int i) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // a.f.e.i
    public Typeface a(Context context, InputStream inputStream) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override // a.f.e.i
    public Typeface a(Context context, CancellationSignal cancellationSignal, b.f[] fVarArr, int i) {
        int i2;
        ParcelFileDescriptor openFileDescriptor;
        ContentResolver contentResolver = context.getContentResolver();
        int length = fVarArr.length;
        FontFamily.Builder builder = null;
        while (true) {
            int i3 = 1;
            if (i2 >= length) {
                if (builder == null) {
                    return null;
                }
                return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle((i & 1) != 0 ? 700 : 400, (i & 2) != 0 ? 1 : 0)).build();
            }
            b.f fVar = fVarArr[i2];
            try {
                openFileDescriptor = contentResolver.openFileDescriptor(fVar.c(), "r", cancellationSignal);
            } catch (IOException unused) {
                continue;
            }
            if (openFileDescriptor != null) {
                try {
                    Font.Builder weight = new Font.Builder(openFileDescriptor).setWeight(fVar.d());
                    if (!fVar.e()) {
                        i3 = 0;
                    }
                    Font build = weight.setSlant(i3).setTtcIndex(fVar.b()).build();
                    if (builder == null) {
                        builder = new FontFamily.Builder(build);
                    } else {
                        builder.addFont(build);
                    }
                    if (openFileDescriptor == null) {
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                        break;
                    } catch (Throwable th2) {
                        if (openFileDescriptor != null) {
                            try {
                                openFileDescriptor.close();
                            } catch (Throwable unused2) {
                            }
                        }
                        throw th2;
                        break;
                    }
                }
            } else {
                i2 = openFileDescriptor == null ? i2 + 1 : 0;
            }
            openFileDescriptor.close();
        }
    }

    @Override // a.f.e.i
    public Typeface a(Context context, c.b bVar, Resources resources, int i) {
        c.C0006c[] a2 = bVar.a();
        int length = a2.length;
        FontFamily.Builder builder = null;
        int i2 = 0;
        while (true) {
            int i3 = 1;
            if (i2 >= length) {
                break;
            }
            c.C0006c c0006c = a2[i2];
            try {
                Font.Builder weight = new Font.Builder(resources, c0006c.b()).setWeight(c0006c.e());
                if (!c0006c.f()) {
                    i3 = 0;
                }
                Font build = weight.setSlant(i3).setTtcIndex(c0006c.c()).setFontVariationSettings(c0006c.d()).build();
                if (builder == null) {
                    builder = new FontFamily.Builder(build);
                } else {
                    builder.addFont(build);
                }
            } catch (IOException unused) {
            }
            i2++;
        }
        if (builder == null) {
            return null;
        }
        return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle((i & 1) != 0 ? 700 : 400, (i & 2) != 0 ? 1 : 0)).build();
    }

    @Override // a.f.e.i
    public Typeface a(Context context, Resources resources, int i, String str, int i2) {
        try {
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(new Font.Builder(resources, i).build()).build()).setStyle(new FontStyle((i2 & 1) != 0 ? 700 : 400, (i2 & 2) != 0 ? 1 : 0)).build();
        } catch (IOException unused) {
            return null;
        }
    }
}
