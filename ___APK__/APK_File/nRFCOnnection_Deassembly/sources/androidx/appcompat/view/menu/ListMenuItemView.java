package androidx.appcompat.view.menu;

import a.f.l.w;
import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.view.menu.o;
import androidx.appcompat.widget.t0;

/* loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements o.a, AbsListView.SelectionBoundsAdjuster {

    /* renamed from: b, reason: collision with root package name */
    private j f749b;

    /* renamed from: c, reason: collision with root package name */
    private ImageView f750c;

    /* renamed from: d, reason: collision with root package name */
    private RadioButton f751d;

    /* renamed from: e, reason: collision with root package name */
    private TextView f752e;

    /* renamed from: f, reason: collision with root package name */
    private CheckBox f753f;
    private TextView g;
    private ImageView h;
    private ImageView i;
    private LinearLayout j;
    private Drawable k;
    private int l;
    private Context m;
    private boolean n;
    private Drawable o;
    private boolean p;
    private LayoutInflater q;
    private boolean r;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.a.a.listMenuViewStyle);
    }

    private void b() {
        this.f753f = (CheckBox) getInflater().inflate(a.a.g.abc_list_menu_item_checkbox, (ViewGroup) this, false);
        a(this.f753f);
    }

    private void c() {
        this.f750c = (ImageView) getInflater().inflate(a.a.g.abc_list_menu_item_icon, (ViewGroup) this, false);
        a(this.f750c, 0);
    }

    private void d() {
        this.f751d = (RadioButton) getInflater().inflate(a.a.g.abc_list_menu_item_radio, (ViewGroup) this, false);
        a(this.f751d);
    }

    private LayoutInflater getInflater() {
        if (this.q == null) {
            this.q = LayoutInflater.from(getContext());
        }
        return this.q;
    }

    private void setSubMenuArrowVisible(boolean z) {
        ImageView imageView = this.h;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 8);
        }
    }

    @Override // androidx.appcompat.view.menu.o.a
    public void a(j jVar, int i) {
        this.f749b = jVar;
        setVisibility(jVar.isVisible() ? 0 : 8);
        setTitle(jVar.a(this));
        setCheckable(jVar.isCheckable());
        a(jVar.m(), jVar.d());
        setIcon(jVar.getIcon());
        setEnabled(jVar.isEnabled());
        setSubMenuArrowVisible(jVar.hasSubMenu());
        setContentDescription(jVar.getContentDescription());
    }

    @Override // androidx.appcompat.view.menu.o.a
    public boolean a() {
        return false;
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.i;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.i.getLayoutParams();
        rect.top += this.i.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    @Override // androidx.appcompat.view.menu.o.a
    public j getItemData() {
        return this.f749b;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        w.a(this, this.k);
        this.f752e = (TextView) findViewById(a.a.f.title);
        int i = this.l;
        if (i != -1) {
            this.f752e.setTextAppearance(this.m, i);
        }
        this.g = (TextView) findViewById(a.a.f.shortcut);
        this.h = (ImageView) findViewById(a.a.f.submenuarrow);
        ImageView imageView = this.h;
        if (imageView != null) {
            imageView.setImageDrawable(this.o);
        }
        this.i = (ImageView) findViewById(a.a.f.group_divider);
        this.j = (LinearLayout) findViewById(a.a.f.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.f750c != null && this.n) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f750c.getLayoutParams();
            if (layoutParams.height > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = layoutParams.height;
            }
        }
        super.onMeasure(i, i2);
    }

    public void setCheckable(boolean z) {
        CompoundButton compoundButton;
        CompoundButton compoundButton2;
        if (!z && this.f751d == null && this.f753f == null) {
            return;
        }
        if (this.f749b.i()) {
            if (this.f751d == null) {
                d();
            }
            compoundButton = this.f751d;
            compoundButton2 = this.f753f;
        } else {
            if (this.f753f == null) {
                b();
            }
            compoundButton = this.f753f;
            compoundButton2 = this.f751d;
        }
        if (z) {
            compoundButton.setChecked(this.f749b.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (compoundButton2 == null || compoundButton2.getVisibility() == 8) {
                return;
            }
            compoundButton2.setVisibility(8);
            return;
        }
        CheckBox checkBox = this.f753f;
        if (checkBox != null) {
            checkBox.setVisibility(8);
        }
        RadioButton radioButton = this.f751d;
        if (radioButton != null) {
            radioButton.setVisibility(8);
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if (this.f749b.i()) {
            if (this.f751d == null) {
                d();
            }
            compoundButton = this.f751d;
        } else {
            if (this.f753f == null) {
                b();
            }
            compoundButton = this.f753f;
        }
        compoundButton.setChecked(z);
    }

    public void setForceShowIcon(boolean z) {
        this.r = z;
        this.n = z;
    }

    public void setGroupDividerEnabled(boolean z) {
        ImageView imageView = this.i;
        if (imageView != null) {
            imageView.setVisibility((this.p || !z) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z = this.f749b.l() || this.r;
        if (z || this.n) {
            if (this.f750c == null && drawable == null && !this.n) {
                return;
            }
            if (this.f750c == null) {
                c();
            }
            if (drawable == null && !this.n) {
                this.f750c.setVisibility(8);
                return;
            }
            ImageView imageView = this.f750c;
            if (!z) {
                drawable = null;
            }
            imageView.setImageDrawable(drawable);
            if (this.f750c.getVisibility() != 0) {
                this.f750c.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence != null) {
            this.f752e.setText(charSequence);
            if (this.f752e.getVisibility() != 0) {
                this.f752e.setVisibility(0);
                return;
            }
            return;
        }
        if (this.f752e.getVisibility() != 8) {
            this.f752e.setVisibility(8);
        }
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        t0 a2 = t0.a(getContext(), attributeSet, a.a.j.MenuView, i, 0);
        this.k = a2.b(a.a.j.MenuView_android_itemBackground);
        this.l = a2.g(a.a.j.MenuView_android_itemTextAppearance, -1);
        this.n = a2.a(a.a.j.MenuView_preserveIconSpacing, false);
        this.m = context;
        this.o = a2.b(a.a.j.MenuView_subMenuArrow);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{R.attr.divider}, a.a.a.dropDownListViewStyle, 0);
        this.p = obtainStyledAttributes.hasValue(0);
        a2.a();
        obtainStyledAttributes.recycle();
    }

    private void a(View view) {
        a(view, -1);
    }

    private void a(View view, int i) {
        LinearLayout linearLayout = this.j;
        if (linearLayout != null) {
            linearLayout.addView(view, i);
        } else {
            addView(view, i);
        }
    }

    public void a(boolean z, char c2) {
        int i = (z && this.f749b.m()) ? 0 : 8;
        if (i == 0) {
            this.g.setText(this.f749b.e());
        }
        if (this.g.getVisibility() != i) {
            this.g.setVisibility(i);
        }
    }
}
