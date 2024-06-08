package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {
    static final PorterDuff.Mode j = PorterDuff.Mode.SRC_IN;

    /* renamed from: a, reason: collision with root package name */
    public int f1277a;

    /* renamed from: b, reason: collision with root package name */
    Object f1278b;

    /* renamed from: c, reason: collision with root package name */
    public byte[] f1279c;

    /* renamed from: d, reason: collision with root package name */
    public Parcelable f1280d;

    /* renamed from: e, reason: collision with root package name */
    public int f1281e;

    /* renamed from: f, reason: collision with root package name */
    public int f1282f;
    public ColorStateList g;
    PorterDuff.Mode h;
    public String i;

    public IconCompat() {
        this.f1277a = -1;
        this.f1279c = null;
        this.f1280d = null;
        this.f1281e = 0;
        this.f1282f = 0;
        this.g = null;
        this.h = j;
        this.i = null;
    }

    public static IconCompat a(Resources resources, String str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("Package must not be null.");
        }
        if (i != 0) {
            IconCompat iconCompat = new IconCompat(2);
            iconCompat.f1281e = i;
            if (resources != null) {
                try {
                    iconCompat.f1278b = resources.getResourceName(i);
                } catch (Resources.NotFoundException unused) {
                    throw new IllegalArgumentException("Icon resource cannot be found");
                }
            } else {
                iconCompat.f1278b = str;
            }
            return iconCompat;
        }
        throw new IllegalArgumentException("Drawable resource ID must not be 0");
    }

    private static String a(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "UNKNOWN" : "BITMAP_MASKABLE" : "URI" : "DATA" : "RESOURCE" : "BITMAP";
    }

    public String b() {
        if (this.f1277a == -1 && Build.VERSION.SDK_INT >= 23) {
            return b((Icon) this.f1278b);
        }
        if (this.f1277a == 2) {
            return ((String) this.f1278b).split(":", -1)[0];
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public int c() {
        if (this.f1277a == -1 && Build.VERSION.SDK_INT >= 23) {
            return c((Icon) this.f1278b);
        }
        return this.f1277a;
    }

    public void d() {
        this.h = PorterDuff.Mode.valueOf(this.i);
        int i = this.f1277a;
        if (i != -1) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        this.f1278b = this.f1279c;
                        return;
                    } else if (i != 4) {
                        if (i != 5) {
                            return;
                        }
                    }
                }
                this.f1278b = new String(this.f1279c, Charset.forName("UTF-16"));
                return;
            }
            Parcelable parcelable = this.f1280d;
            if (parcelable != null) {
                this.f1278b = parcelable;
                return;
            }
            byte[] bArr = this.f1279c;
            this.f1278b = bArr;
            this.f1277a = 3;
            this.f1281e = 0;
            this.f1282f = bArr.length;
            return;
        }
        Parcelable parcelable2 = this.f1280d;
        if (parcelable2 != null) {
            this.f1278b = parcelable2;
            return;
        }
        throw new IllegalArgumentException("Invalid icon");
    }

    public Icon e() {
        Icon createWithBitmap;
        int i = this.f1277a;
        if (i != -1) {
            if (i == 1) {
                createWithBitmap = Icon.createWithBitmap((Bitmap) this.f1278b);
            } else if (i == 2) {
                createWithBitmap = Icon.createWithResource(b(), this.f1281e);
            } else if (i == 3) {
                createWithBitmap = Icon.createWithData((byte[]) this.f1278b, this.f1281e, this.f1282f);
            } else if (i == 4) {
                createWithBitmap = Icon.createWithContentUri((String) this.f1278b);
            } else if (i == 5) {
                if (Build.VERSION.SDK_INT >= 26) {
                    createWithBitmap = Icon.createWithAdaptiveBitmap((Bitmap) this.f1278b);
                } else {
                    createWithBitmap = Icon.createWithBitmap(a((Bitmap) this.f1278b, false));
                }
            } else {
                throw new IllegalArgumentException("Unknown type");
            }
            ColorStateList colorStateList = this.g;
            if (colorStateList != null) {
                createWithBitmap.setTintList(colorStateList);
            }
            PorterDuff.Mode mode = this.h;
            if (mode != j) {
                createWithBitmap.setTintMode(mode);
            }
            return createWithBitmap;
        }
        return (Icon) this.f1278b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x002b, code lost:
    
        if (r1 != 5) goto L23;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00ae  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            r4 = this;
            int r0 = r4.f1277a
            r1 = -1
            if (r0 != r1) goto Lc
            java.lang.Object r0 = r4.f1278b
            java.lang.String r0 = java.lang.String.valueOf(r0)
            return r0
        Lc:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Icon(typ="
            r0.<init>(r1)
            int r1 = r4.f1277a
            java.lang.String r1 = a(r1)
            r0.append(r1)
            int r1 = r4.f1277a
            r2 = 1
            if (r1 == r2) goto L7a
            r3 = 2
            if (r1 == r3) goto L52
            r2 = 3
            if (r1 == r2) goto L39
            r2 = 4
            if (r1 == r2) goto L2e
            r2 = 5
            if (r1 == r2) goto L7a
            goto L9a
        L2e:
            java.lang.String r1 = " uri="
            r0.append(r1)
            java.lang.Object r1 = r4.f1278b
            r0.append(r1)
            goto L9a
        L39:
            java.lang.String r1 = " len="
            r0.append(r1)
            int r1 = r4.f1281e
            r0.append(r1)
            int r1 = r4.f1282f
            if (r1 == 0) goto L9a
            java.lang.String r1 = " off="
            r0.append(r1)
            int r1 = r4.f1282f
            r0.append(r1)
            goto L9a
        L52:
            java.lang.String r1 = " pkg="
            r0.append(r1)
            java.lang.String r1 = r4.b()
            r0.append(r1)
            java.lang.String r1 = " id="
            r0.append(r1)
            java.lang.Object[] r1 = new java.lang.Object[r2]
            r2 = 0
            int r3 = r4.a()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1[r2] = r3
            java.lang.String r2 = "0x%08x"
            java.lang.String r1 = java.lang.String.format(r2, r1)
            r0.append(r1)
            goto L9a
        L7a:
            java.lang.String r1 = " size="
            r0.append(r1)
            java.lang.Object r1 = r4.f1278b
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            int r1 = r1.getWidth()
            r0.append(r1)
            java.lang.String r1 = "x"
            r0.append(r1)
            java.lang.Object r1 = r4.f1278b
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            int r1 = r1.getHeight()
            r0.append(r1)
        L9a:
            android.content.res.ColorStateList r1 = r4.g
            if (r1 == 0) goto La8
            java.lang.String r1 = " tint="
            r0.append(r1)
            android.content.res.ColorStateList r1 = r4.g
            r0.append(r1)
        La8:
            android.graphics.PorterDuff$Mode r1 = r4.h
            android.graphics.PorterDuff$Mode r2 = androidx.core.graphics.drawable.IconCompat.j
            if (r1 == r2) goto Lb8
            java.lang.String r1 = " mode="
            r0.append(r1)
            android.graphics.PorterDuff$Mode r1 = r4.h
            r0.append(r1)
        Lb8:
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.drawable.IconCompat.toString():java.lang.String");
    }

    private static int c(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getType();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getType", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e2) {
            Log.e("IconCompat", "Unable to get icon type " + icon, e2);
            return -1;
        } catch (NoSuchMethodException e3) {
            Log.e("IconCompat", "Unable to get icon type " + icon, e3);
            return -1;
        } catch (InvocationTargetException e4) {
            Log.e("IconCompat", "Unable to get icon type " + icon, e4);
            return -1;
        }
    }

    private static String b(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResPackage();
        }
        try {
            return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e2) {
            Log.e("IconCompat", "Unable to get icon package", e2);
            return null;
        } catch (NoSuchMethodException e3) {
            Log.e("IconCompat", "Unable to get icon package", e3);
            return null;
        } catch (InvocationTargetException e4) {
            Log.e("IconCompat", "Unable to get icon package", e4);
            return null;
        }
    }

    public int a() {
        if (this.f1277a == -1 && Build.VERSION.SDK_INT >= 23) {
            return a((Icon) this.f1278b);
        }
        if (this.f1277a == 2) {
            return this.f1281e;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    private IconCompat(int i) {
        this.f1277a = -1;
        this.f1279c = null;
        this.f1280d = null;
        this.f1281e = 0;
        this.f1282f = 0;
        this.g = null;
        this.h = j;
        this.i = null;
        this.f1277a = i;
    }

    public void a(boolean z) {
        this.i = this.h.name();
        int i = this.f1277a;
        if (i == -1) {
            if (!z) {
                this.f1280d = (Parcelable) this.f1278b;
                return;
            }
            throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
        }
        if (i != 1) {
            if (i == 2) {
                this.f1279c = ((String) this.f1278b).getBytes(Charset.forName("UTF-16"));
                return;
            }
            if (i == 3) {
                this.f1279c = (byte[]) this.f1278b;
                return;
            } else if (i == 4) {
                this.f1279c = this.f1278b.toString().getBytes(Charset.forName("UTF-16"));
                return;
            } else if (i != 5) {
                return;
            }
        }
        if (z) {
            Bitmap bitmap = (Bitmap) this.f1278b;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
            this.f1279c = byteArrayOutputStream.toByteArray();
            return;
        }
        this.f1280d = (Parcelable) this.f1278b;
    }

    private static int a(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResId();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e2) {
            Log.e("IconCompat", "Unable to get icon resource", e2);
            return 0;
        } catch (NoSuchMethodException e3) {
            Log.e("IconCompat", "Unable to get icon resource", e3);
            return 0;
        } catch (InvocationTargetException e4) {
            Log.e("IconCompat", "Unable to get icon resource", e4);
            return 0;
        }
    }

    static Bitmap a(Bitmap bitmap, boolean z) {
        int min = (int) (Math.min(bitmap.getWidth(), bitmap.getHeight()) * 0.6666667f);
        Bitmap createBitmap = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(3);
        float f2 = min;
        float f3 = 0.5f * f2;
        float f4 = 0.9166667f * f3;
        if (z) {
            float f5 = 0.010416667f * f2;
            paint.setColor(0);
            paint.setShadowLayer(f5, 0.0f, f2 * 0.020833334f, 1023410176);
            canvas.drawCircle(f3, f3, f4, paint);
            paint.setShadowLayer(f5, 0.0f, 0.0f, 503316480);
            canvas.drawCircle(f3, f3, f4, paint);
            paint.clearShadowLayer();
        }
        paint.setColor(-16777216);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        Matrix matrix = new Matrix();
        matrix.setTranslate((-(bitmap.getWidth() - min)) / 2, (-(bitmap.getHeight() - min)) / 2);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        canvas.drawCircle(f3, f3, f4, paint);
        canvas.setBitmap(null);
        return createBitmap;
    }
}
