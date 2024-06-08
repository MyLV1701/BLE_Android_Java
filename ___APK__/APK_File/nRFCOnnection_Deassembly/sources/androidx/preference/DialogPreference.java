package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/* loaded from: classes.dex */
public abstract class DialogPreference extends Preference {

    /* renamed from: b, reason: collision with root package name */
    private CharSequence f1571b;

    /* renamed from: c, reason: collision with root package name */
    private CharSequence f1572c;

    /* renamed from: d, reason: collision with root package name */
    private Drawable f1573d;

    /* renamed from: e, reason: collision with root package name */
    private CharSequence f1574e;

    /* renamed from: f, reason: collision with root package name */
    private CharSequence f1575f;
    private int g;

    /* loaded from: classes.dex */
    public interface a {
        <T extends Preference> T findPreference(CharSequence charSequence);
    }

    public DialogPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.DialogPreference, i, i2);
        this.f1571b = a.f.d.d.g.b(obtainStyledAttributes, t.DialogPreference_dialogTitle, t.DialogPreference_android_dialogTitle);
        if (this.f1571b == null) {
            this.f1571b = getTitle();
        }
        this.f1572c = a.f.d.d.g.b(obtainStyledAttributes, t.DialogPreference_dialogMessage, t.DialogPreference_android_dialogMessage);
        this.f1573d = a.f.d.d.g.a(obtainStyledAttributes, t.DialogPreference_dialogIcon, t.DialogPreference_android_dialogIcon);
        this.f1574e = a.f.d.d.g.b(obtainStyledAttributes, t.DialogPreference_positiveButtonText, t.DialogPreference_android_positiveButtonText);
        this.f1575f = a.f.d.d.g.b(obtainStyledAttributes, t.DialogPreference_negativeButtonText, t.DialogPreference_android_negativeButtonText);
        this.g = a.f.d.d.g.b(obtainStyledAttributes, t.DialogPreference_dialogLayout, t.DialogPreference_android_dialogLayout, 0);
        obtainStyledAttributes.recycle();
    }

    public Drawable a() {
        return this.f1573d;
    }

    public int b() {
        return this.g;
    }

    public CharSequence c() {
        return this.f1572c;
    }

    public CharSequence d() {
        return this.f1571b;
    }

    public CharSequence e() {
        return this.f1575f;
    }

    public CharSequence f() {
        return this.f1574e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public void onClick() {
        getPreferenceManager().a(this);
    }

    public DialogPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public DialogPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.f.d.d.g.a(context, m.dialogPreferenceStyle, R.attr.dialogPreferenceStyle));
    }
}
