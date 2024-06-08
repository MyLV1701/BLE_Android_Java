package no.nordicsemi.android.mcp.widget.connection.macro;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.ref.WeakReference;
import no.nordicsemi.android.mcp.connection.macro.domain.Macro;
import no.nordicsemi.android.mcp.widget.ViewAnimator;

/* loaded from: classes.dex */
public class MacroViewAnimator extends ViewAnimator {
    public static final Parcelable.Creator<MacroViewAnimator> CREATOR = new Parcelable.Creator<MacroViewAnimator>() { // from class: no.nordicsemi.android.mcp.widget.connection.macro.MacroViewAnimator.1
        @Override // android.os.Parcelable.Creator
        public MacroViewAnimator createFromParcel(Parcel parcel) {
            return new MacroViewAnimator(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public MacroViewAnimator[] newArray(int i) {
            return new MacroViewAnimator[i];
        }
    };
    private WeakReference<Exception> mException;
    private boolean mLoop;
    private WeakReference<Macro> mMacro;

    public MacroViewAnimator(String str) {
        super(str);
    }

    public void checkException() {
        WeakReference<Exception> weakReference = this.mException;
        if (weakReference != null && weakReference.get() != null) {
            throw this.mException.get();
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.ViewAnimator, no.nordicsemi.android.mcp.widget.ViewSelector, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Macro getMacro() {
        WeakReference<Macro> weakReference = this.mMacro;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public boolean isLoopEnabled() {
        return this.mLoop;
    }

    public void setException(Exception exc) {
        this.mException = new WeakReference<>(exc);
    }

    public void setLoopEnabled(boolean z) {
        this.mLoop = z;
    }

    public void setMacro(Macro macro) {
        if (macro != null) {
            this.mMacro = new WeakReference<>(macro);
        } else {
            this.mMacro = null;
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.ViewAnimator, no.nordicsemi.android.mcp.widget.ViewSelector, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    public MacroViewAnimator(Parcel parcel) {
        super(parcel);
    }
}
