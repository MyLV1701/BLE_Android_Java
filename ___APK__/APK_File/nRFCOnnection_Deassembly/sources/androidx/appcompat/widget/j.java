package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.widget.j0;

/* loaded from: classes.dex */
public final class j {

    /* renamed from: b, reason: collision with root package name */
    private static final PorterDuff.Mode f1011b = PorterDuff.Mode.SRC_IN;

    /* renamed from: c, reason: collision with root package name */
    private static j f1012c;

    /* renamed from: a, reason: collision with root package name */
    private j0 f1013a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a implements j0.e {

        /* renamed from: a, reason: collision with root package name */
        private final int[] f1014a = {a.a.e.abc_textfield_search_default_mtrl_alpha, a.a.e.abc_textfield_default_mtrl_alpha, a.a.e.abc_ab_share_pack_mtrl_alpha};

        /* renamed from: b, reason: collision with root package name */
        private final int[] f1015b = {a.a.e.abc_ic_commit_search_api_mtrl_alpha, a.a.e.abc_seekbar_tick_mark_material, a.a.e.abc_ic_menu_share_mtrl_alpha, a.a.e.abc_ic_menu_copy_mtrl_am_alpha, a.a.e.abc_ic_menu_cut_mtrl_alpha, a.a.e.abc_ic_menu_selectall_mtrl_alpha, a.a.e.abc_ic_menu_paste_mtrl_am_alpha};

        /* renamed from: c, reason: collision with root package name */
        private final int[] f1016c = {a.a.e.abc_textfield_activated_mtrl_alpha, a.a.e.abc_textfield_search_activated_mtrl_alpha, a.a.e.abc_cab_background_top_mtrl_alpha, a.a.e.abc_text_cursor_material, a.a.e.abc_text_select_handle_left_mtrl_dark, a.a.e.abc_text_select_handle_middle_mtrl_dark, a.a.e.abc_text_select_handle_right_mtrl_dark, a.a.e.abc_text_select_handle_left_mtrl_light, a.a.e.abc_text_select_handle_middle_mtrl_light, a.a.e.abc_text_select_handle_right_mtrl_light};

        /* renamed from: d, reason: collision with root package name */
        private final int[] f1017d = {a.a.e.abc_popup_background_mtrl_mult, a.a.e.abc_cab_background_internal_bg, a.a.e.abc_menu_hardkey_panel_mtrl_mult};

        /* renamed from: e, reason: collision with root package name */
        private final int[] f1018e = {a.a.e.abc_tab_indicator_material, a.a.e.abc_textfield_search_material};

        /* renamed from: f, reason: collision with root package name */
        private final int[] f1019f = {a.a.e.abc_btn_check_material, a.a.e.abc_btn_radio_material, a.a.e.abc_btn_check_material_anim, a.a.e.abc_btn_radio_material_anim};

        a() {
        }

        private ColorStateList a(Context context) {
            return b(context, 0);
        }

        private ColorStateList b(Context context) {
            return b(context, o0.b(context, a.a.a.colorAccent));
        }

        private ColorStateList c(Context context) {
            return b(context, o0.b(context, a.a.a.colorButtonNormal));
        }

        private ColorStateList d(Context context) {
            int[][] iArr = new int[3];
            int[] iArr2 = new int[3];
            ColorStateList c2 = o0.c(context, a.a.a.colorSwitchThumbNormal);
            if (c2 != null && c2.isStateful()) {
                iArr[0] = o0.f1067b;
                iArr2[0] = c2.getColorForState(iArr[0], 0);
                iArr[1] = o0.f1070e;
                iArr2[1] = o0.b(context, a.a.a.colorControlActivated);
                iArr[2] = o0.f1071f;
                iArr2[2] = c2.getDefaultColor();
            } else {
                iArr[0] = o0.f1067b;
                iArr2[0] = o0.a(context, a.a.a.colorSwitchThumbNormal);
                iArr[1] = o0.f1070e;
                iArr2[1] = o0.b(context, a.a.a.colorControlActivated);
                iArr[2] = o0.f1071f;
                iArr2[2] = o0.b(context, a.a.a.colorSwitchThumbNormal);
            }
            return new ColorStateList(iArr, iArr2);
        }

        @Override // androidx.appcompat.widget.j0.e
        public Drawable a(j0 j0Var, Context context, int i) {
            if (i == a.a.e.abc_cab_background_top_material) {
                return new LayerDrawable(new Drawable[]{j0Var.a(context, a.a.e.abc_cab_background_internal_bg), j0Var.a(context, a.a.e.abc_cab_background_top_mtrl_alpha)});
            }
            return null;
        }

        private ColorStateList b(Context context, int i) {
            int b2 = o0.b(context, a.a.a.colorControlHighlight);
            return new ColorStateList(new int[][]{o0.f1067b, o0.f1069d, o0.f1068c, o0.f1071f}, new int[]{o0.a(context, a.a.a.colorButtonNormal), a.f.e.a.b(b2, i), a.f.e.a.b(b2, i), i});
        }

        private void a(Drawable drawable, int i, PorterDuff.Mode mode) {
            if (a0.a(drawable)) {
                drawable = drawable.mutate();
            }
            if (mode == null) {
                mode = j.f1011b;
            }
            drawable.setColorFilter(j.a(i, mode));
        }

        private boolean a(int[] iArr, int i) {
            for (int i2 : iArr) {
                if (i2 == i) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.appcompat.widget.j0.e
        public ColorStateList a(Context context, int i) {
            if (i == a.a.e.abc_edit_text_material) {
                return a.a.k.a.a.b(context, a.a.c.abc_tint_edittext);
            }
            if (i == a.a.e.abc_switch_track_mtrl_alpha) {
                return a.a.k.a.a.b(context, a.a.c.abc_tint_switch_track);
            }
            if (i == a.a.e.abc_switch_thumb_material) {
                return d(context);
            }
            if (i == a.a.e.abc_btn_default_mtrl_shape) {
                return c(context);
            }
            if (i == a.a.e.abc_btn_borderless_material) {
                return a(context);
            }
            if (i == a.a.e.abc_btn_colored_material) {
                return b(context);
            }
            if (i != a.a.e.abc_spinner_mtrl_am_alpha && i != a.a.e.abc_spinner_textfield_background_material) {
                if (a(this.f1015b, i)) {
                    return o0.c(context, a.a.a.colorControlNormal);
                }
                if (a(this.f1018e, i)) {
                    return a.a.k.a.a.b(context, a.a.c.abc_tint_default);
                }
                if (a(this.f1019f, i)) {
                    return a.a.k.a.a.b(context, a.a.c.abc_tint_btn_checkable);
                }
                if (i == a.a.e.abc_seekbar_thumb_material) {
                    return a.a.k.a.a.b(context, a.a.c.abc_tint_seek_thumb);
                }
                return null;
            }
            return a.a.k.a.a.b(context, a.a.c.abc_tint_spinner);
        }

        @Override // androidx.appcompat.widget.j0.e
        public boolean b(Context context, int i, Drawable drawable) {
            if (i == a.a.e.abc_seekbar_track_material) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                a(layerDrawable.findDrawableByLayerId(R.id.background), o0.b(context, a.a.a.colorControlNormal), j.f1011b);
                a(layerDrawable.findDrawableByLayerId(R.id.secondaryProgress), o0.b(context, a.a.a.colorControlNormal), j.f1011b);
                a(layerDrawable.findDrawableByLayerId(R.id.progress), o0.b(context, a.a.a.colorControlActivated), j.f1011b);
                return true;
            }
            if (i != a.a.e.abc_ratingbar_material && i != a.a.e.abc_ratingbar_indicator_material && i != a.a.e.abc_ratingbar_small_material) {
                return false;
            }
            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
            a(layerDrawable2.findDrawableByLayerId(R.id.background), o0.a(context, a.a.a.colorControlNormal), j.f1011b);
            a(layerDrawable2.findDrawableByLayerId(R.id.secondaryProgress), o0.b(context, a.a.a.colorControlActivated), j.f1011b);
            a(layerDrawable2.findDrawableByLayerId(R.id.progress), o0.b(context, a.a.a.colorControlActivated), j.f1011b);
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0066 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:6:0x004b  */
        @Override // androidx.appcompat.widget.j0.e
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean a(android.content.Context r7, int r8, android.graphics.drawable.Drawable r9) {
            /*
                r6 = this;
                android.graphics.PorterDuff$Mode r0 = androidx.appcompat.widget.j.a()
                int[] r1 = r6.f1014a
                boolean r1 = r6.a(r1, r8)
                r2 = 16842801(0x1010031, float:2.3693695E-38)
                r3 = -1
                r4 = 0
                r5 = 1
                if (r1 == 0) goto L18
                int r2 = a.a.a.colorControlNormal
            L14:
                r1 = r0
                r8 = 1
                r0 = -1
                goto L49
            L18:
                int[] r1 = r6.f1016c
                boolean r1 = r6.a(r1, r8)
                if (r1 == 0) goto L23
                int r2 = a.a.a.colorControlActivated
                goto L14
            L23:
                int[] r1 = r6.f1017d
                boolean r1 = r6.a(r1, r8)
                if (r1 == 0) goto L2e
                android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
                goto L14
            L2e:
                int r1 = a.a.e.abc_list_divider_mtrl_alpha
                if (r8 != r1) goto L40
                r2 = 16842800(0x1010030, float:2.3693693E-38)
                r8 = 1109603123(0x42233333, float:40.8)
                int r8 = java.lang.Math.round(r8)
                r1 = r0
                r0 = r8
                r8 = 1
                goto L49
            L40:
                int r1 = a.a.e.abc_dialog_material_background
                if (r8 != r1) goto L45
                goto L14
            L45:
                r1 = r0
                r8 = 0
                r0 = -1
                r2 = 0
            L49:
                if (r8 == 0) goto L66
                boolean r8 = androidx.appcompat.widget.a0.a(r9)
                if (r8 == 0) goto L55
                android.graphics.drawable.Drawable r9 = r9.mutate()
            L55:
                int r7 = androidx.appcompat.widget.o0.b(r7, r2)
                android.graphics.PorterDuffColorFilter r7 = androidx.appcompat.widget.j.a(r7, r1)
                r9.setColorFilter(r7)
                if (r0 == r3) goto L65
                r9.setAlpha(r0)
            L65:
                return r5
            L66:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.j.a.a(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
        }

        @Override // androidx.appcompat.widget.j0.e
        public PorterDuff.Mode a(int i) {
            if (i == a.a.e.abc_switch_thumb_material) {
                return PorterDuff.Mode.MULTIPLY;
            }
            return null;
        }
    }

    public static synchronized j b() {
        j jVar;
        synchronized (j.class) {
            if (f1012c == null) {
                c();
            }
            jVar = f1012c;
        }
        return jVar;
    }

    public static synchronized void c() {
        synchronized (j.class) {
            if (f1012c == null) {
                f1012c = new j();
                f1012c.f1013a = j0.a();
                f1012c.f1013a.a(new a());
            }
        }
    }

    public synchronized Drawable a(Context context, int i) {
        return this.f1013a.a(context, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Drawable a(Context context, int i, boolean z) {
        return this.f1013a.a(context, i, z);
    }

    public synchronized void a(Context context) {
        this.f1013a.a(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized ColorStateList b(Context context, int i) {
        return this.f1013a.b(context, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Drawable drawable, r0 r0Var, int[] iArr) {
        j0.a(drawable, r0Var, iArr);
    }

    public static synchronized PorterDuffColorFilter a(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter a2;
        synchronized (j.class) {
            a2 = j0.a(i, mode);
        }
        return a2;
    }
}
