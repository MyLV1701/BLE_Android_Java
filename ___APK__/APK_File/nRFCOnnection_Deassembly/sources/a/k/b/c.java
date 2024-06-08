package a.k.b;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class c<D> {
    Context mContext;
    int mId;
    InterfaceC0028c<D> mListener;
    b<D> mOnLoadCanceledListener;
    boolean mStarted = false;
    boolean mAbandoned = false;
    boolean mReset = true;
    boolean mContentChanged = false;
    boolean mProcessingChange = false;

    /* loaded from: classes.dex */
    public final class a extends ContentObserver {
        public a() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            c.this.onContentChanged();
        }
    }

    /* loaded from: classes.dex */
    public interface b<D> {
        void a(c<D> cVar);
    }

    /* renamed from: a.k.b.c$c, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0028c<D> {
        void a(c<D> cVar, D d2);
    }

    public c(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void abandon() {
        this.mAbandoned = true;
        onAbandon();
    }

    public boolean cancelLoad() {
        return onCancelLoad();
    }

    public void commitContentChanged() {
        this.mProcessingChange = false;
    }

    public String dataToString(D d2) {
        StringBuilder sb = new StringBuilder(64);
        a.f.k.a.a(d2, sb);
        sb.append("}");
        return sb.toString();
    }

    public void deliverCancellation() {
        b<D> bVar = this.mOnLoadCanceledListener;
        if (bVar != null) {
            bVar.a(this);
        }
    }

    public void deliverResult(D d2) {
        InterfaceC0028c<D> interfaceC0028c = this.mListener;
        if (interfaceC0028c != null) {
            interfaceC0028c.a(this, d2);
        }
    }

    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mId=");
        printWriter.print(this.mId);
        printWriter.print(" mListener=");
        printWriter.println(this.mListener);
        if (this.mStarted || this.mContentChanged || this.mProcessingChange) {
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.print(this.mStarted);
            printWriter.print(" mContentChanged=");
            printWriter.print(this.mContentChanged);
            printWriter.print(" mProcessingChange=");
            printWriter.println(this.mProcessingChange);
        }
        if (this.mAbandoned || this.mReset) {
            printWriter.print(str);
            printWriter.print("mAbandoned=");
            printWriter.print(this.mAbandoned);
            printWriter.print(" mReset=");
            printWriter.println(this.mReset);
        }
    }

    public void forceLoad() {
        onForceLoad();
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getId() {
        return this.mId;
    }

    public boolean isAbandoned() {
        return this.mAbandoned;
    }

    public boolean isReset() {
        return this.mReset;
    }

    public boolean isStarted() {
        return this.mStarted;
    }

    protected void onAbandon() {
    }

    protected boolean onCancelLoad() {
        throw null;
    }

    public void onContentChanged() {
        if (this.mStarted) {
            forceLoad();
        } else {
            this.mContentChanged = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onForceLoad() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onReset() {
    }

    protected void onStartLoading() {
        throw null;
    }

    protected void onStopLoading() {
    }

    public void registerListener(int i, InterfaceC0028c<D> interfaceC0028c) {
        if (this.mListener == null) {
            this.mListener = interfaceC0028c;
            this.mId = i;
            return;
        }
        throw new IllegalStateException("There is already a listener registered");
    }

    public void registerOnLoadCanceledListener(b<D> bVar) {
        if (this.mOnLoadCanceledListener == null) {
            this.mOnLoadCanceledListener = bVar;
            return;
        }
        throw new IllegalStateException("There is already a listener registered");
    }

    public void reset() {
        onReset();
        this.mReset = true;
        this.mStarted = false;
        this.mAbandoned = false;
        this.mContentChanged = false;
        this.mProcessingChange = false;
    }

    public void rollbackContentChanged() {
        if (this.mProcessingChange) {
            onContentChanged();
        }
    }

    public final void startLoading() {
        this.mStarted = true;
        this.mReset = false;
        this.mAbandoned = false;
        onStartLoading();
    }

    public void stopLoading() {
        this.mStarted = false;
        onStopLoading();
    }

    public boolean takeContentChanged() {
        boolean z = this.mContentChanged;
        this.mContentChanged = false;
        this.mProcessingChange |= z;
        return z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        a.f.k.a.a(this, sb);
        sb.append(" id=");
        sb.append(this.mId);
        sb.append("}");
        return sb.toString();
    }

    public void unregisterListener(InterfaceC0028c<D> interfaceC0028c) {
        InterfaceC0028c<D> interfaceC0028c2 = this.mListener;
        if (interfaceC0028c2 == null) {
            throw new IllegalStateException("No listener register");
        }
        if (interfaceC0028c2 == interfaceC0028c) {
            this.mListener = null;
            return;
        }
        throw new IllegalArgumentException("Attempting to unregister the wrong listener");
    }

    public void unregisterOnLoadCanceledListener(b<D> bVar) {
        b<D> bVar2 = this.mOnLoadCanceledListener;
        if (bVar2 == null) {
            throw new IllegalStateException("No listener register");
        }
        if (bVar2 == bVar) {
            this.mOnLoadCanceledListener = null;
            return;
        }
        throw new IllegalArgumentException("Attempting to unregister the wrong listener");
    }
}
