package no.nordicsemi.android.mcp.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class ViewAnimator extends ViewSelector implements Parcelable {
    private static final int STATE_COLLAPSED = 0;
    private static final int STATE_COLLAPSING = 3;
    private static final int STATE_EXPANDED = 2;
    private static final int STATE_EXPANDING = 1;
    private boolean bound;
    private int calculatedHeight;
    private int currentHeight;
    private WeakReference<ExpandCollapseListener> expandCollapseListener;
    private ValueAnimator mCurrentAnimator;
    private boolean mScrollToPosition;
    private int position;
    private final ViewTreeObserver.OnPreDrawListener preDrawListener;
    private int state;
    private WeakReference<ViewGroup> viewToAnimate;
    private static final TimeInterpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    public static final Parcelable.Creator<ViewAnimator> CREATOR = new Parcelable.Creator<ViewAnimator>() { // from class: no.nordicsemi.android.mcp.widget.ViewAnimator.3
        @Override // android.os.Parcelable.Creator
        public ViewAnimator createFromParcel(Parcel parcel) {
            return new ViewAnimator(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ViewAnimator[] newArray(int i) {
            return new ViewAnimator[i];
        }
    };

    /* loaded from: classes.dex */
    public interface ExpandCollapseListener {
        void onViewCollapsed(int i);

        void onViewExpanded(int i);
    }

    public ViewAnimator(String str) {
        super(str);
        this.preDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: no.nordicsemi.android.mcp.widget.ViewAnimator.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                View view = ViewAnimator.this.viewToAnimate != null ? (View) ViewAnimator.this.viewToAnimate.get() : null;
                if (view == null) {
                    return false;
                }
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                if (ViewAnimator.this.calculatedHeight == 0) {
                    View view2 = (View) view.getParent();
                    int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec((view2.getMeasuredWidth() - view2.getPaddingLeft()) - view2.getPaddingRight(), 1073741824);
                    int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
                    view.forceLayout();
                    view.measure(makeMeasureSpec, makeMeasureSpec2);
                    ViewAnimator.this.calculatedHeight = view.getMeasuredHeight();
                }
                ViewAnimator viewAnimator = ViewAnimator.this;
                ValueAnimator valueAnimator = viewAnimator.mCurrentAnimator = viewAnimator.createSlideAnimator(viewAnimator.currentHeight, ViewAnimator.this.calculatedHeight);
                valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: no.nordicsemi.android.mcp.widget.ViewAnimator.1.1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationCancel(Animator animator) {
                        ViewAnimator.this.mCurrentAnimator = null;
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        if (ViewAnimator.this.mCurrentAnimator != null) {
                            ViewAnimator.this.state = 2;
                        }
                        if (!ViewAnimator.this.mScrollToPosition || ViewAnimator.this.expandCollapseListener == null || ViewAnimator.this.expandCollapseListener.get() == null) {
                            return;
                        }
                        ((ExpandCollapseListener) ViewAnimator.this.expandCollapseListener.get()).onViewExpanded(ViewAnimator.this.position);
                    }
                });
                valueAnimator.start();
                return true;
            }
        };
    }

    private void calculateHeightAsync(View view) {
        view.getViewTreeObserver().removeOnPreDrawListener(this.preDrawListener);
        view.getViewTreeObserver().addOnPreDrawListener(this.preDrawListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ValueAnimator createSlideAnimator(int i, int i2) {
        ValueAnimator ofInt = ValueAnimator.ofInt(i, i2);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: no.nordicsemi.android.mcp.widget.b
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ViewAnimator.this.a(valueAnimator);
            }
        });
        ofInt.setInterpolator(LINEAR_INTERPOLATOR);
        double abs = Math.abs(i - i2);
        Double.isNaN(abs);
        ofInt.setDuration((long) Math.min(300.0d, abs * 0.3d));
        return ofInt;
    }

    public /* synthetic */ void a(ValueAnimator valueAnimator) {
        ViewGroup viewGroup;
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.currentHeight = intValue;
        WeakReference<ViewGroup> weakReference = this.viewToAnimate;
        if (weakReference == null || (viewGroup = weakReference.get()) == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
        layoutParams.height = intValue;
        viewGroup.setLayoutParams(layoutParams);
    }

    @Override // no.nordicsemi.android.mcp.widget.ViewSelector
    public void bindView(View view, ViewGroup viewGroup) {
        super.bindView(view, viewGroup);
        if (view != null) {
            this.viewToAnimate = new WeakReference<>(viewGroup);
            viewGroup.setVisibility(this.state == 0 ? 8 : 0);
            ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
            layoutParams.height = this.currentHeight;
            viewGroup.setLayoutParams(layoutParams);
            this.bound = true;
            recalculateExpandableViewHeight();
            return;
        }
        this.viewToAnimate = null;
    }

    public void collapse() {
        if (this.viewToAnimate == null) {
            this.state = 0;
            return;
        }
        ValueAnimator valueAnimator = this.mCurrentAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.state = 3;
        ValueAnimator createSlideAnimator = createSlideAnimator(this.currentHeight, 0);
        this.mCurrentAnimator = createSlideAnimator;
        createSlideAnimator.addListener(new AnimatorListenerAdapter() { // from class: no.nordicsemi.android.mcp.widget.ViewAnimator.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                ViewAnimator.this.mCurrentAnimator = null;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                View view;
                if (ViewAnimator.this.mCurrentAnimator != null) {
                    ViewAnimator.this.state = 0;
                    if (ViewAnimator.this.viewToAnimate != null && (view = (View) ViewAnimator.this.viewToAnimate.get()) != null) {
                        view.setVisibility(8);
                    }
                    if (ViewAnimator.this.expandCollapseListener != null && ViewAnimator.this.expandCollapseListener.get() != null) {
                        ((ExpandCollapseListener) ViewAnimator.this.expandCollapseListener.get()).onViewCollapsed(ViewAnimator.this.position);
                    }
                }
                ViewAnimator.this.mCurrentAnimator = null;
            }
        });
        createSlideAnimator.start();
    }

    @Override // no.nordicsemi.android.mcp.widget.ViewSelector, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void expand(boolean z) {
        if (this.viewToAnimate == null) {
            this.state = 2;
            return;
        }
        ValueAnimator valueAnimator = this.mCurrentAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.viewToAnimate.get().setVisibility(0);
        this.state = 1;
        this.mScrollToPosition = z;
        calculateHeightAsync(this.viewToAnimate.get());
    }

    public void recalculateExpandableViewHeight() {
        this.calculatedHeight = 0;
        if (this.bound && this.state == 2) {
            expand(false);
        }
    }

    public void reinitialize() {
        this.bound = false;
    }

    public void setExpandCollapseListener(ExpandCollapseListener expandCollapseListener, int i) {
        this.expandCollapseListener = new WeakReference<>(expandCollapseListener);
        this.position = i;
    }

    public void toggle() {
        int i = this.state;
        if (i != 0 && i != 3) {
            collapse();
        } else {
            expand(true);
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.ViewSelector
    public void unbindView() {
        super.unbindView();
        this.bound = false;
    }

    @Override // no.nordicsemi.android.mcp.widget.ViewSelector, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.state);
    }

    public ViewAnimator(Parcel parcel) {
        super(parcel);
        this.preDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: no.nordicsemi.android.mcp.widget.ViewAnimator.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                View view = ViewAnimator.this.viewToAnimate != null ? (View) ViewAnimator.this.viewToAnimate.get() : null;
                if (view == null) {
                    return false;
                }
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                if (ViewAnimator.this.calculatedHeight == 0) {
                    View view2 = (View) view.getParent();
                    int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec((view2.getMeasuredWidth() - view2.getPaddingLeft()) - view2.getPaddingRight(), 1073741824);
                    int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
                    view.forceLayout();
                    view.measure(makeMeasureSpec, makeMeasureSpec2);
                    ViewAnimator.this.calculatedHeight = view.getMeasuredHeight();
                }
                ViewAnimator viewAnimator = ViewAnimator.this;
                ValueAnimator valueAnimator = viewAnimator.mCurrentAnimator = viewAnimator.createSlideAnimator(viewAnimator.currentHeight, ViewAnimator.this.calculatedHeight);
                valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: no.nordicsemi.android.mcp.widget.ViewAnimator.1.1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationCancel(Animator animator) {
                        ViewAnimator.this.mCurrentAnimator = null;
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        if (ViewAnimator.this.mCurrentAnimator != null) {
                            ViewAnimator.this.state = 2;
                        }
                        if (!ViewAnimator.this.mScrollToPosition || ViewAnimator.this.expandCollapseListener == null || ViewAnimator.this.expandCollapseListener.get() == null) {
                            return;
                        }
                        ((ExpandCollapseListener) ViewAnimator.this.expandCollapseListener.get()).onViewExpanded(ViewAnimator.this.position);
                    }
                });
                valueAnimator.start();
                return true;
            }
        };
        this.state = parcel.readInt();
    }
}
