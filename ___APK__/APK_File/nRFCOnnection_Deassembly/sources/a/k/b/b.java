package a.k.b;

import a.k.b.c;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

/* loaded from: classes.dex */
public class b extends a<Cursor> {

    /* renamed from: a, reason: collision with root package name */
    final c<Cursor>.a f389a;

    /* renamed from: b, reason: collision with root package name */
    Uri f390b;

    /* renamed from: c, reason: collision with root package name */
    String[] f391c;

    /* renamed from: d, reason: collision with root package name */
    String f392d;

    /* renamed from: e, reason: collision with root package name */
    String[] f393e;

    /* renamed from: f, reason: collision with root package name */
    String f394f;
    Cursor g;
    a.f.h.a h;

    public b(Context context, Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        super(context);
        this.f389a = new c.a();
        this.f390b = uri;
        this.f391c = strArr;
        this.f392d = str;
        this.f393e = strArr2;
        this.f394f = str2;
    }

    @Override // a.k.b.c
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void deliverResult(Cursor cursor) {
        if (isReset()) {
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        Cursor cursor2 = this.g;
        this.g = cursor;
        if (isStarted()) {
            super.deliverResult(cursor);
        }
        if (cursor2 == null || cursor2 == cursor || cursor2.isClosed()) {
            return;
        }
        cursor2.close();
    }

    @Override // a.k.b.a
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onCanceled(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            return;
        }
        cursor.close();
    }

    @Override // a.k.b.a
    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();
        synchronized (this) {
            if (this.h != null) {
                this.h.a();
            }
        }
    }

    @Override // a.k.b.a, a.k.b.c
    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("mUri=");
        printWriter.println(this.f390b);
        printWriter.print(str);
        printWriter.print("mProjection=");
        printWriter.println(Arrays.toString(this.f391c));
        printWriter.print(str);
        printWriter.print("mSelection=");
        printWriter.println(this.f392d);
        printWriter.print(str);
        printWriter.print("mSelectionArgs=");
        printWriter.println(Arrays.toString(this.f393e));
        printWriter.print(str);
        printWriter.print("mSortOrder=");
        printWriter.println(this.f394f);
        printWriter.print(str);
        printWriter.print("mCursor=");
        printWriter.println(this.g);
        printWriter.print(str);
        printWriter.print("mContentChanged=");
        printWriter.println(this.mContentChanged);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // a.k.b.c
    public void onReset() {
        super.onReset();
        onStopLoading();
        Cursor cursor = this.g;
        if (cursor != null && !cursor.isClosed()) {
            this.g.close();
        }
        this.g = null;
    }

    @Override // a.k.b.c
    protected void onStartLoading() {
        Cursor cursor = this.g;
        if (cursor != null) {
            deliverResult(cursor);
        }
        if (takeContentChanged() || this.g == null) {
            forceLoad();
        }
    }

    @Override // a.k.b.c
    protected void onStopLoading() {
        cancelLoad();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // a.k.b.a
    public Cursor loadInBackground() {
        synchronized (this) {
            if (!isLoadInBackgroundCanceled()) {
                this.h = new a.f.h.a();
            } else {
                throw new a.f.h.b();
            }
        }
        try {
            Cursor a2 = a.f.d.a.a(getContext().getContentResolver(), this.f390b, this.f391c, this.f392d, this.f393e, this.f394f, this.h);
            if (a2 != null) {
                try {
                    a2.getCount();
                    a2.registerContentObserver(this.f389a);
                } catch (RuntimeException e2) {
                    a2.close();
                    throw e2;
                }
            }
            synchronized (this) {
                this.h = null;
            }
            return a2;
        } catch (Throwable th) {
            synchronized (this) {
                this.h = null;
                throw th;
            }
        }
    }
}
