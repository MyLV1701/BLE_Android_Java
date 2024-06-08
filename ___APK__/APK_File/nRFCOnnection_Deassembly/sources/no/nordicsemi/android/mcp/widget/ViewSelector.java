package no.nordicsemi.android.mcp.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class ViewSelector implements Parcelable {
    private static final int ACTIVATED_ITEM_FIRST = 0;
    private static final int ACTIVATED_NONE = -2;
    private static final int ACTIVATED_SELF = -1;
    public static final Parcelable.Creator<ViewAnimator> CREATOR = new Parcelable.Creator<ViewAnimator>() { // from class: no.nordicsemi.android.mcp.widget.ViewSelector.1
        @Override // android.os.Parcelable.Creator
        public ViewAnimator createFromParcel(Parcel parcel) {
            return new ViewAnimator(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ViewAnimator[] newArray(int i) {
            return new ViewAnimator[i];
        }
    };
    private int activated;
    private final String viewId;
    private WeakReference<ViewGroup> viewToAnimate;
    private WeakReference<View> viewToSelect;

    public ViewSelector(String str) {
        this.activated = -2;
        this.viewId = str;
    }

    public void bindView(View view, ViewGroup viewGroup) {
        if (view != null) {
            this.viewToSelect = new WeakReference<>(view);
            this.viewToAnimate = new WeakReference<>(viewGroup);
            view.setActivated(this.activated == -1);
            int i = this.activated;
            if (i >= 0) {
                viewGroup.getChildAt(i).setActivated(true);
                return;
            }
            return;
        }
        this.viewToSelect = null;
        this.viewToAnimate = null;
    }

    public void clearActivated() {
        WeakReference<ViewGroup> weakReference;
        ViewGroup viewGroup;
        WeakReference<View> weakReference2;
        View view;
        if (this.activated == -1 && (weakReference2 = this.viewToSelect) != null && (view = weakReference2.get()) != null) {
            view.setActivated(false);
        }
        if (this.activated >= 0 && (weakReference = this.viewToAnimate) != null && (viewGroup = weakReference.get()) != null) {
            viewGroup.getChildAt(this.activated).setActivated(false);
        }
        this.activated = -2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public View getActiveView() {
        int i = this.activated;
        if (i == -1) {
            return (View) this.viewToSelect.get().getParent();
        }
        if (i >= 0) {
            return this.viewToAnimate.get().getChildAt(this.activated);
        }
        return null;
    }

    public String getViewId() {
        return this.viewId;
    }

    public boolean isActivated() {
        return this.activated == -1;
    }

    public boolean isChildActivated(int i) {
        return this.activated == i;
    }

    public void setActivated(boolean z) {
        View view;
        this.activated = z ? -1 : -2;
        WeakReference<View> weakReference = this.viewToSelect;
        if (weakReference == null || (view = weakReference.get()) == null) {
            return;
        }
        view.setActivated(z);
    }

    public void setChildActivated(View view, int i, boolean z) {
        if (this.viewToAnimate != null) {
            view.setActivated(z);
            if (!z) {
                i = -2;
            }
            this.activated = i;
        }
    }

    public void unbindView() {
        ViewGroup viewGroup;
        WeakReference<ViewGroup> weakReference = this.viewToAnimate;
        if (weakReference == null || this.activated < 0 || (viewGroup = weakReference.get()) == null) {
            return;
        }
        viewGroup.getChildAt(this.activated).setActivated(false);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.viewId);
        parcel.writeInt(this.activated);
    }

    public ViewSelector(Parcel parcel) {
        this.activated = -2;
        this.viewId = parcel.readString();
        this.activated = parcel.readInt();
    }
}
