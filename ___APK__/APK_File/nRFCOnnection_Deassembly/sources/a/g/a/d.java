package a.g.a;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/* loaded from: classes.dex */
public class d extends c {

    /* renamed from: e, reason: collision with root package name */
    protected int[] f353e;

    /* renamed from: f, reason: collision with root package name */
    protected int[] f354f;
    private int g;
    private a h;
    private b i;
    String[] j;

    /* loaded from: classes.dex */
    public interface a {
        CharSequence convertToString(Cursor cursor);
    }

    /* loaded from: classes.dex */
    public interface b {
        boolean a(View view, Cursor cursor, int i);
    }

    public d(Context context, int i, Cursor cursor, String[] strArr, int[] iArr, int i2) {
        super(context, i, cursor, i2);
        this.g = -1;
        this.f354f = iArr;
        this.j = strArr;
        a(cursor, strArr);
    }

    public void a(ImageView imageView, String str) {
        try {
            imageView.setImageResource(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            imageView.setImageURI(Uri.parse(str));
        }
    }

    @Override // a.g.a.a
    public void bindView(View view, Context context, Cursor cursor) {
        b bVar = this.i;
        int[] iArr = this.f354f;
        int length = iArr.length;
        int[] iArr2 = this.f353e;
        for (int i = 0; i < length; i++) {
            View findViewById = view.findViewById(iArr[i]);
            if (findViewById != null) {
                if (bVar != null ? bVar.a(findViewById, cursor, iArr2[i]) : false) {
                    continue;
                } else {
                    String string = cursor.getString(iArr2[i]);
                    if (string == null) {
                        string = "";
                    }
                    if (findViewById instanceof TextView) {
                        a((TextView) findViewById, string);
                    } else if (findViewById instanceof ImageView) {
                        a((ImageView) findViewById, string);
                    } else {
                        throw new IllegalStateException(findViewById.getClass().getName() + " is not a  view that can be bounds by this SimpleCursorAdapter");
                    }
                }
            }
        }
    }

    @Override // a.g.a.a, a.g.a.b.a
    public CharSequence convertToString(Cursor cursor) {
        a aVar = this.h;
        if (aVar != null) {
            return aVar.convertToString(cursor);
        }
        int i = this.g;
        if (i > -1) {
            return cursor.getString(i);
        }
        return super.convertToString(cursor);
    }

    @Override // a.g.a.a
    public Cursor swapCursor(Cursor cursor) {
        a(cursor, this.j);
        return super.swapCursor(cursor);
    }

    public void a(TextView textView, String str) {
        textView.setText(str);
    }

    private void a(Cursor cursor, String[] strArr) {
        if (cursor != null) {
            int length = strArr.length;
            int[] iArr = this.f353e;
            if (iArr == null || iArr.length != length) {
                this.f353e = new int[length];
            }
            for (int i = 0; i < length; i++) {
                this.f353e[i] = cursor.getColumnIndexOrThrow(strArr[i]);
            }
            return;
        }
        this.f353e = null;
    }
}
