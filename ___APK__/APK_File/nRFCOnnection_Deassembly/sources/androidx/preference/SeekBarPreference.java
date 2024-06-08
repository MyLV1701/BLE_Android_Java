package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.preference.Preference;

/* loaded from: classes.dex */
public class SeekBarPreference extends Preference {

    /* renamed from: b, reason: collision with root package name */
    int f1591b;

    /* renamed from: c, reason: collision with root package name */
    int f1592c;

    /* renamed from: d, reason: collision with root package name */
    private int f1593d;

    /* renamed from: e, reason: collision with root package name */
    private int f1594e;

    /* renamed from: f, reason: collision with root package name */
    boolean f1595f;
    SeekBar g;
    private TextView h;
    boolean i;
    private boolean j;
    boolean k;
    private SeekBar.OnSeekBarChangeListener l;
    private View.OnKeyListener m;

    /* loaded from: classes.dex */
    class a implements SeekBar.OnSeekBarChangeListener {
        a() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                SeekBarPreference seekBarPreference = SeekBarPreference.this;
                if (seekBarPreference.k || !seekBarPreference.f1595f) {
                    SeekBarPreference.this.a(seekBar);
                    return;
                }
            }
            SeekBarPreference seekBarPreference2 = SeekBarPreference.this;
            seekBarPreference2.d(i + seekBarPreference2.f1592c);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            SeekBarPreference.this.f1595f = true;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            SeekBarPreference.this.f1595f = false;
            int progress = seekBar.getProgress();
            SeekBarPreference seekBarPreference = SeekBarPreference.this;
            if (progress + seekBarPreference.f1592c != seekBarPreference.f1591b) {
                seekBarPreference.a(seekBar);
            }
        }
    }

    /* loaded from: classes.dex */
    class b implements View.OnKeyListener {
        b() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() != 0) {
                return false;
            }
            if ((!SeekBarPreference.this.i && (i == 21 || i == 22)) || i == 23 || i == 66) {
                return false;
            }
            SeekBar seekBar = SeekBarPreference.this.g;
            if (seekBar == null) {
                Log.e("SeekBarPreference", "SeekBar view is null and hence cannot be adjusted.");
                return false;
            }
            return seekBar.onKeyDown(i, keyEvent);
        }
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.l = new a();
        this.m = new b();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.SeekBarPreference, i, i2);
        this.f1592c = obtainStyledAttributes.getInt(t.SeekBarPreference_min, 0);
        a(obtainStyledAttributes.getInt(t.SeekBarPreference_android_max, 100));
        b(obtainStyledAttributes.getInt(t.SeekBarPreference_seekBarIncrement, 0));
        this.i = obtainStyledAttributes.getBoolean(t.SeekBarPreference_adjustable, true);
        this.j = obtainStyledAttributes.getBoolean(t.SeekBarPreference_showSeekBarValue, false);
        this.k = obtainStyledAttributes.getBoolean(t.SeekBarPreference_updatesContinuously, false);
        obtainStyledAttributes.recycle();
    }

    public final void a(int i) {
        int i2 = this.f1592c;
        if (i < i2) {
            i = i2;
        }
        if (i != this.f1593d) {
            this.f1593d = i;
            notifyChanged();
        }
    }

    public final void b(int i) {
        if (i != this.f1594e) {
            this.f1594e = Math.min(this.f1593d - this.f1592c, Math.abs(i));
            notifyChanged();
        }
    }

    public void c(int i) {
        a(i, true);
    }

    void d(int i) {
        TextView textView = this.h;
        if (textView != null) {
            textView.setText(String.valueOf(i));
        }
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(l lVar) {
        super.onBindViewHolder(lVar);
        lVar.itemView.setOnKeyListener(this.m);
        this.g = (SeekBar) lVar.a(p.seekbar);
        this.h = (TextView) lVar.a(p.seekbar_value);
        if (this.j) {
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(8);
            this.h = null;
        }
        SeekBar seekBar = this.g;
        if (seekBar == null) {
            Log.e("SeekBarPreference", "SeekBar view is null in onBindViewHolder.");
            return;
        }
        seekBar.setOnSeekBarChangeListener(this.l);
        this.g.setMax(this.f1593d - this.f1592c);
        int i = this.f1594e;
        if (i != 0) {
            this.g.setKeyProgressIncrement(i);
        } else {
            this.f1594e = this.g.getKeyProgressIncrement();
        }
        this.g.setProgress(this.f1591b - this.f1592c);
        d(this.f1591b);
        this.g.setEnabled(isEnabled());
    }

    @Override // androidx.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        return Integer.valueOf(typedArray.getInt(i, 0));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!parcelable.getClass().equals(c.class)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        c cVar = (c) parcelable;
        super.onRestoreInstanceState(cVar.getSuperState());
        this.f1591b = cVar.f1598b;
        this.f1592c = cVar.f1599c;
        this.f1593d = cVar.f1600d;
        notifyChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (isPersistent()) {
            return onSaveInstanceState;
        }
        c cVar = new c(onSaveInstanceState);
        cVar.f1598b = this.f1591b;
        cVar.f1599c = this.f1592c;
        cVar.f1600d = this.f1593d;
        return cVar;
    }

    @Override // androidx.preference.Preference
    protected void onSetInitialValue(Object obj) {
        if (obj == null) {
            obj = 0;
        }
        c(getPersistedInt(((Integer) obj).intValue()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c extends Preference.b {
        public static final Parcelable.Creator<c> CREATOR = new a();

        /* renamed from: b, reason: collision with root package name */
        int f1598b;

        /* renamed from: c, reason: collision with root package name */
        int f1599c;

        /* renamed from: d, reason: collision with root package name */
        int f1600d;

        /* loaded from: classes.dex */
        static class a implements Parcelable.Creator<c> {
            a() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public c createFromParcel(Parcel parcel) {
                return new c(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public c[] newArray(int i) {
                return new c[i];
            }
        }

        c(Parcel parcel) {
            super(parcel);
            this.f1598b = parcel.readInt();
            this.f1599c = parcel.readInt();
            this.f1600d = parcel.readInt();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f1598b);
            parcel.writeInt(this.f1599c);
            parcel.writeInt(this.f1600d);
        }

        c(Parcelable parcelable) {
            super(parcelable);
        }
    }

    private void a(int i, boolean z) {
        int i2 = this.f1592c;
        if (i < i2) {
            i = i2;
        }
        int i3 = this.f1593d;
        if (i > i3) {
            i = i3;
        }
        if (i != this.f1591b) {
            this.f1591b = i;
            d(this.f1591b);
            persistInt(i);
            if (z) {
                notifyChanged();
            }
        }
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    void a(SeekBar seekBar) {
        int progress = this.f1592c + seekBar.getProgress();
        if (progress != this.f1591b) {
            if (callChangeListener(Integer.valueOf(progress))) {
                a(progress, false);
            } else {
                seekBar.setProgress(this.f1591b - this.f1592c);
                d(this.f1591b);
            }
        }
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, m.seekBarPreferenceStyle);
    }
}
