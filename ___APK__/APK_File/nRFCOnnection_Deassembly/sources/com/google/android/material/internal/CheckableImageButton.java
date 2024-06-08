package com.google.android.material.internal;

import a.f.l.w;
import android.R;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;
import android.widget.ImageButton;
import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
public class CheckableImageButton extends androidx.appcompat.widget.m implements Checkable {
    private static final int[] g = {R.attr.state_checked};

    /* renamed from: d, reason: collision with root package name */
    private boolean f2565d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f2566e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f2567f;

    /* loaded from: classes.dex */
    class a extends a.f.l.a {
        a() {
        }

        @Override // a.f.l.a
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setChecked(CheckableImageButton.this.isChecked());
        }

        @Override // a.f.l.a
        public void onInitializeAccessibilityNodeInfo(View view, a.f.l.f0.c cVar) {
            super.onInitializeAccessibilityNodeInfo(view, cVar);
            cVar.b(CheckableImageButton.this.a());
            cVar.c(CheckableImageButton.this.isChecked());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b extends a.h.a.a {
        public static final Parcelable.Creator<b> CREATOR = new a();

        /* renamed from: d, reason: collision with root package name */
        boolean f2569d;

        /* loaded from: classes.dex */
        static class a implements Parcelable.ClassLoaderCreator<b> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            public b[] newArray(int i) {
                return new b[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public b createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new b(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public b createFromParcel(Parcel parcel) {
                return new b(parcel, null);
            }
        }

        public b(Parcelable parcelable) {
            super(parcelable);
        }

        private void a(Parcel parcel) {
            this.f2569d = parcel.readInt() == 1;
        }

        @Override // a.h.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f2569d ? 1 : 0);
        }

        public b(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            a(parcel);
        }
    }

    public CheckableImageButton(Context context) {
        this(context, null);
    }

    public boolean a() {
        return this.f2566e;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f2565d;
    }

    @Override // android.widget.ImageView, android.view.View
    public int[] onCreateDrawableState(int i) {
        if (this.f2565d) {
            return ImageButton.mergeDrawableStates(super.onCreateDrawableState(i + g.length), g);
        }
        return super.onCreateDrawableState(i);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof b)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        b bVar = (b) parcelable;
        super.onRestoreInstanceState(bVar.d());
        setChecked(bVar.f2569d);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        b bVar = new b(super.onSaveInstanceState());
        bVar.f2569d = this.f2565d;
        return bVar;
    }

    public void setCheckable(boolean z) {
        if (this.f2566e != z) {
            this.f2566e = z;
            sendAccessibilityEvent(0);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (!this.f2566e || this.f2565d == z) {
            return;
        }
        this.f2565d = z;
        refreshDrawableState();
        sendAccessibilityEvent(DfuBaseService.ERROR_REMOTE_TYPE_SECURE_BUTTONLESS);
    }

    public void setPressable(boolean z) {
        this.f2567f = z;
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        if (this.f2567f) {
            super.setPressed(z);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.f2565d);
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.a.a.imageButtonStyle);
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2566e = true;
        this.f2567f = true;
        w.a(this, new a());
    }
}
