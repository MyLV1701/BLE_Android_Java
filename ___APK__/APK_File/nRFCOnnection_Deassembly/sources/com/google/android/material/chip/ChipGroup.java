package com.google.android.material.chip;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import c.a.a.a.k;
import com.google.android.material.internal.l;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ChipGroup extends com.google.android.material.internal.c {

    /* renamed from: e, reason: collision with root package name */
    private int f2443e;

    /* renamed from: f, reason: collision with root package name */
    private int f2444f;
    private boolean g;
    private boolean h;
    private d i;
    private final b j;
    private e k;
    private int l;
    private boolean m;

    /* loaded from: classes.dex */
    private class b implements CompoundButton.OnCheckedChangeListener {
        private b() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (ChipGroup.this.m) {
                return;
            }
            if (ChipGroup.this.getCheckedChipIds().isEmpty() && ChipGroup.this.h) {
                ChipGroup.this.a(compoundButton.getId(), true);
                ChipGroup.this.setCheckedId(compoundButton.getId());
                return;
            }
            int id = compoundButton.getId();
            if (z) {
                if (ChipGroup.this.l != -1 && ChipGroup.this.l != id && ChipGroup.this.g) {
                    ChipGroup chipGroup = ChipGroup.this;
                    chipGroup.a(chipGroup.l, false);
                }
                ChipGroup.this.setCheckedId(id);
                return;
            }
            if (ChipGroup.this.l == id) {
                ChipGroup.this.setCheckedId(-1);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class c extends ViewGroup.MarginLayoutParams {
        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public c(int i, int i2) {
            super(i, i2);
        }
    }

    /* loaded from: classes.dex */
    public interface d {
        void a(ChipGroup chipGroup, int i);
    }

    /* loaded from: classes.dex */
    private class e implements ViewGroup.OnHierarchyChangeListener {

        /* renamed from: b, reason: collision with root package name */
        private ViewGroup.OnHierarchyChangeListener f2446b;

        private e() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            int hashCode;
            if (view == ChipGroup.this && (view2 instanceof Chip)) {
                if (view2.getId() == -1) {
                    if (Build.VERSION.SDK_INT >= 17) {
                        hashCode = View.generateViewId();
                    } else {
                        hashCode = view2.hashCode();
                    }
                    view2.setId(hashCode);
                }
                ((Chip) view2).setOnCheckedChangeListenerInternal(ChipGroup.this.j);
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.f2446b;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            if (view == ChipGroup.this && (view2 instanceof Chip)) {
                ((Chip) view2).setOnCheckedChangeListenerInternal(null);
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.f2446b;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }

    public ChipGroup(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckedId(int i) {
        this.l = i;
        d dVar = this.i;
        if (dVar == null || !this.g) {
            return;
        }
        dVar.a(this, i);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof Chip) {
            Chip chip = (Chip) view;
            if (chip.isChecked()) {
                int i2 = this.l;
                if (i2 != -1 && this.g) {
                    a(i2, false);
                }
                setCheckedId(chip.getId());
            }
        }
        super.addView(view, i, layoutParams);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof c);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new c(-2, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    public int getCheckedChipId() {
        if (this.g) {
            return this.l;
        }
        return -1;
    }

    public List<Integer> getCheckedChipIds() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if ((childAt instanceof Chip) && ((Chip) childAt).isChecked()) {
                arrayList.add(Integer.valueOf(childAt.getId()));
                if (this.g) {
                    return arrayList;
                }
            }
        }
        return arrayList;
    }

    public int getChipSpacingHorizontal() {
        return this.f2443e;
    }

    public int getChipSpacingVertical() {
        return this.f2444f;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        int i = this.l;
        if (i != -1) {
            a(i, true);
            setCheckedId(this.l);
        }
    }

    public void setChipSpacing(int i) {
        setChipSpacingHorizontal(i);
        setChipSpacingVertical(i);
    }

    public void setChipSpacingHorizontal(int i) {
        if (this.f2443e != i) {
            this.f2443e = i;
            setItemSpacing(i);
            requestLayout();
        }
    }

    public void setChipSpacingHorizontalResource(int i) {
        setChipSpacingHorizontal(getResources().getDimensionPixelOffset(i));
    }

    public void setChipSpacingResource(int i) {
        setChipSpacing(getResources().getDimensionPixelOffset(i));
    }

    public void setChipSpacingVertical(int i) {
        if (this.f2444f != i) {
            this.f2444f = i;
            setLineSpacing(i);
            requestLayout();
        }
    }

    public void setChipSpacingVerticalResource(int i) {
        setChipSpacingVertical(getResources().getDimensionPixelOffset(i));
    }

    @Deprecated
    public void setDividerDrawableHorizontal(Drawable drawable) {
        throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setDividerDrawableVertical(Drawable drawable) {
        throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setFlexWrap(int i) {
        throw new UnsupportedOperationException("Changing flex wrap not allowed. ChipGroup exposes a singleLine attribute instead.");
    }

    public void setOnCheckedChangeListener(d dVar) {
        this.i = dVar;
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.k.f2446b = onHierarchyChangeListener;
    }

    public void setSelectionRequired(boolean z) {
        this.h = z;
    }

    @Deprecated
    public void setShowDividerHorizontal(int i) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setShowDividerVertical(int i) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Override // com.google.android.material.internal.c
    public void setSingleLine(boolean z) {
        super.setSingleLine(z);
    }

    public void setSingleSelection(boolean z) {
        if (this.g != z) {
            this.g = z;
            b();
        }
    }

    public ChipGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.a.a.a.b.chipGroupStyle);
    }

    public void b() {
        this.m = true;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof Chip) {
                ((Chip) childAt).setChecked(false);
            }
        }
        this.m = false;
        setCheckedId(-1);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new c(layoutParams);
    }

    public void setSingleLine(int i) {
        setSingleLine(getResources().getBoolean(i));
    }

    public ChipGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = new b();
        this.k = new e();
        this.l = -1;
        this.m = false;
        TypedArray c2 = l.c(context, attributeSet, c.a.a.a.l.ChipGroup, i, k.Widget_MaterialComponents_ChipGroup, new int[0]);
        int dimensionPixelOffset = c2.getDimensionPixelOffset(c.a.a.a.l.ChipGroup_chipSpacing, 0);
        setChipSpacingHorizontal(c2.getDimensionPixelOffset(c.a.a.a.l.ChipGroup_chipSpacingHorizontal, dimensionPixelOffset));
        setChipSpacingVertical(c2.getDimensionPixelOffset(c.a.a.a.l.ChipGroup_chipSpacingVertical, dimensionPixelOffset));
        setSingleLine(c2.getBoolean(c.a.a.a.l.ChipGroup_singleLine, false));
        setSingleSelection(c2.getBoolean(c.a.a.a.l.ChipGroup_singleSelection, false));
        setSelectionRequired(c2.getBoolean(c.a.a.a.l.ChipGroup_selectionRequired, false));
        int resourceId = c2.getResourceId(c.a.a.a.l.ChipGroup_checkedChip, -1);
        if (resourceId != -1) {
            this.l = resourceId;
        }
        c2.recycle();
        super.setOnHierarchyChangeListener(this.k);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, boolean z) {
        View findViewById = findViewById(i);
        if (findViewById instanceof Chip) {
            this.m = true;
            ((Chip) findViewById).setChecked(z);
            this.m = false;
        }
    }

    public void setSingleSelection(int i) {
        setSingleSelection(getResources().getBoolean(i));
    }

    @Override // com.google.android.material.internal.c
    public boolean a() {
        return super.a();
    }
}
