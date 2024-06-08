package androidx.appcompat.app;

import android.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
class c {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f658a = {R.attr.homeAsUpIndicator};

    /* loaded from: classes.dex */
    static class a {

        /* renamed from: a, reason: collision with root package name */
        public Method f659a;

        /* renamed from: b, reason: collision with root package name */
        public Method f660b;

        /* renamed from: c, reason: collision with root package name */
        public ImageView f661c;

        a(Activity activity) {
            try {
                this.f659a = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", Drawable.class);
                this.f660b = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", Integer.TYPE);
            } catch (NoSuchMethodException unused) {
                View findViewById = activity.findViewById(R.id.home);
                if (findViewById == null) {
                    return;
                }
                ViewGroup viewGroup = (ViewGroup) findViewById.getParent();
                if (viewGroup.getChildCount() != 2) {
                    return;
                }
                View childAt = viewGroup.getChildAt(0);
                View childAt2 = childAt.getId() != 16908332 ? childAt : viewGroup.getChildAt(1);
                if (childAt2 instanceof ImageView) {
                    this.f661c = (ImageView) childAt2;
                }
            }
        }
    }

    public static a a(Activity activity, Drawable drawable, int i) {
        a aVar = new a(activity);
        if (aVar.f659a != null) {
            try {
                ActionBar actionBar = activity.getActionBar();
                aVar.f659a.invoke(actionBar, drawable);
                aVar.f660b.invoke(actionBar, Integer.valueOf(i));
            } catch (Exception e2) {
                Log.w("ActionBarDrawerToggleHC", "Couldn't set home-as-up indicator via JB-MR2 API", e2);
            }
        } else {
            ImageView imageView = aVar.f661c;
            if (imageView != null) {
                imageView.setImageDrawable(drawable);
            } else {
                Log.w("ActionBarDrawerToggleHC", "Couldn't set home-as-up indicator");
            }
        }
        return aVar;
    }

    public static a a(a aVar, Activity activity, int i) {
        if (aVar == null) {
            aVar = new a(activity);
        }
        if (aVar.f659a != null) {
            try {
                ActionBar actionBar = activity.getActionBar();
                aVar.f660b.invoke(actionBar, Integer.valueOf(i));
                if (Build.VERSION.SDK_INT <= 19) {
                    actionBar.setSubtitle(actionBar.getSubtitle());
                }
            } catch (Exception e2) {
                Log.w("ActionBarDrawerToggleHC", "Couldn't set content description via JB-MR2 API", e2);
            }
        }
        return aVar;
    }

    public static Drawable a(Activity activity) {
        TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(f658a);
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }
}
