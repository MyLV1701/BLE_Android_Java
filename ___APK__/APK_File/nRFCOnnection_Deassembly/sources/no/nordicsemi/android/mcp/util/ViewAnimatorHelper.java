package no.nordicsemi.android.mcp.util;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* loaded from: classes.dex */
public class ViewAnimatorHelper {
    public static void collapse(final View view) {
        final int measuredHeight = view.getMeasuredHeight();
        Animation animation = new Animation() { // from class: no.nordicsemi.android.mcp.util.ViewAnimatorHelper.2
            @Override // android.view.animation.Animation
            protected void applyTransformation(float f2, Transformation transformation) {
                if (f2 == 1.0f) {
                    view.setVisibility(8);
                    return;
                }
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                int i = measuredHeight;
                layoutParams.height = i - ((int) (i * f2));
                view.requestLayout();
            }

            @Override // android.view.animation.Animation
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration((int) (measuredHeight / view.getContext().getResources().getDisplayMetrics().density));
        view.startAnimation(animation);
    }

    public static void expand(final View view) {
        view.measure(-1, -2);
        final int measuredHeight = view.getMeasuredHeight();
        view.getLayoutParams().height = 0;
        view.setVisibility(0);
        Animation animation = new Animation() { // from class: no.nordicsemi.android.mcp.util.ViewAnimatorHelper.1
            @Override // android.view.animation.Animation
            protected void applyTransformation(float f2, Transformation transformation) {
                view.getLayoutParams().height = f2 == 1.0f ? -2 : (int) (measuredHeight * f2);
                view.requestLayout();
            }

            @Override // android.view.animation.Animation
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration((int) (measuredHeight / view.getContext().getResources().getDisplayMetrics().density));
        view.startAnimation(animation);
    }
}
