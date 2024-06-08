package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class j extends RecyclerView.n implements RecyclerView.q {
    private g A;
    private Rect C;
    private long D;

    /* renamed from: d, reason: collision with root package name */
    float f1889d;

    /* renamed from: e, reason: collision with root package name */
    float f1890e;

    /* renamed from: f, reason: collision with root package name */
    private float f1891f;
    private float g;
    float h;
    float i;
    private float j;
    private float k;
    f m;
    int o;
    private int q;
    RecyclerView r;
    VelocityTracker t;
    private List<RecyclerView.d0> u;
    private List<Integer> v;
    a.f.l.c z;

    /* renamed from: a, reason: collision with root package name */
    final List<View> f1886a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    private final float[] f1887b = new float[2];

    /* renamed from: c, reason: collision with root package name */
    RecyclerView.d0 f1888c = null;
    int l = -1;
    private int n = 0;
    List<h> p = new ArrayList();
    final Runnable s = new a();
    private RecyclerView.j w = null;
    View x = null;
    int y = -1;
    private final RecyclerView.s B = new b();

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            j jVar = j.this;
            if (jVar.f1888c == null || !jVar.c()) {
                return;
            }
            j jVar2 = j.this;
            RecyclerView.d0 d0Var = jVar2.f1888c;
            if (d0Var != null) {
                jVar2.a(d0Var);
            }
            j jVar3 = j.this;
            jVar3.r.removeCallbacks(jVar3.s);
            a.f.l.w.a(j.this.r, this);
        }
    }

    /* loaded from: classes.dex */
    class b implements RecyclerView.s {
        b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.s
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            int findPointerIndex;
            h a2;
            j.this.z.a(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                j.this.l = motionEvent.getPointerId(0);
                j.this.f1889d = motionEvent.getX();
                j.this.f1890e = motionEvent.getY();
                j.this.b();
                j jVar = j.this;
                if (jVar.f1888c == null && (a2 = jVar.a(motionEvent)) != null) {
                    j jVar2 = j.this;
                    jVar2.f1889d -= a2.i;
                    jVar2.f1890e -= a2.j;
                    jVar2.a(a2.f1904e, true);
                    if (j.this.f1886a.remove(a2.f1904e.itemView)) {
                        j jVar3 = j.this;
                        jVar3.m.clearView(jVar3.r, a2.f1904e);
                    }
                    j.this.a(a2.f1904e, a2.f1905f);
                    j jVar4 = j.this;
                    jVar4.a(motionEvent, jVar4.o, 0);
                }
            } else if (actionMasked != 3 && actionMasked != 1) {
                int i = j.this.l;
                if (i != -1 && (findPointerIndex = motionEvent.findPointerIndex(i)) >= 0) {
                    j.this.a(actionMasked, motionEvent, findPointerIndex);
                }
            } else {
                j jVar5 = j.this;
                jVar5.l = -1;
                jVar5.a((RecyclerView.d0) null, 0);
            }
            VelocityTracker velocityTracker = j.this.t;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            return j.this.f1888c != null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.s
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
            if (z) {
                j.this.a((RecyclerView.d0) null, 0);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.s
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            j.this.z.a(motionEvent);
            VelocityTracker velocityTracker = j.this.t;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            if (j.this.l == -1) {
                return;
            }
            int actionMasked = motionEvent.getActionMasked();
            int findPointerIndex = motionEvent.findPointerIndex(j.this.l);
            if (findPointerIndex >= 0) {
                j.this.a(actionMasked, motionEvent, findPointerIndex);
            }
            j jVar = j.this;
            RecyclerView.d0 d0Var = jVar.f1888c;
            if (d0Var == null) {
                return;
            }
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    if (findPointerIndex >= 0) {
                        jVar.a(motionEvent, jVar.o, findPointerIndex);
                        j.this.a(d0Var);
                        j jVar2 = j.this;
                        jVar2.r.removeCallbacks(jVar2.s);
                        j.this.s.run();
                        j.this.r.invalidate();
                        return;
                    }
                    return;
                }
                if (actionMasked != 3) {
                    if (actionMasked != 6) {
                        return;
                    }
                    int actionIndex = motionEvent.getActionIndex();
                    if (motionEvent.getPointerId(actionIndex) == j.this.l) {
                        j.this.l = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
                        j jVar3 = j.this;
                        jVar3.a(motionEvent, jVar3.o, actionIndex);
                        return;
                    }
                    return;
                }
                VelocityTracker velocityTracker2 = jVar.t;
                if (velocityTracker2 != null) {
                    velocityTracker2.clear();
                }
            }
            j.this.a((RecyclerView.d0) null, 0);
            j.this.l = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends h {
        final /* synthetic */ int n;
        final /* synthetic */ RecyclerView.d0 o;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(RecyclerView.d0 d0Var, int i, int i2, float f2, float f3, float f4, float f5, int i3, RecyclerView.d0 d0Var2) {
            super(d0Var, i, i2, f2, f3, f4, f5);
            this.n = i3;
            this.o = d0Var2;
        }

        @Override // androidx.recyclerview.widget.j.h, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            if (this.k) {
                return;
            }
            if (this.n <= 0) {
                j jVar = j.this;
                jVar.m.clearView(jVar.r, this.o);
            } else {
                j.this.f1886a.add(this.o.itemView);
                this.h = true;
                int i = this.n;
                if (i > 0) {
                    j.this.a(this, i);
                }
            }
            j jVar2 = j.this;
            View view = jVar2.x;
            View view2 = this.o.itemView;
            if (view == view2) {
                jVar2.c(view2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ h f1894b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f1895c;

        d(h hVar, int i) {
            this.f1894b = hVar;
            this.f1895c = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView recyclerView = j.this.r;
            if (recyclerView == null || !recyclerView.isAttachedToWindow()) {
                return;
            }
            h hVar = this.f1894b;
            if (hVar.k || hVar.f1904e.getAdapterPosition() == -1) {
                return;
            }
            RecyclerView.l itemAnimator = j.this.r.getItemAnimator();
            if ((itemAnimator == null || !itemAnimator.a((RecyclerView.l.a) null)) && !j.this.a()) {
                j.this.m.onSwiped(this.f1894b.f1904e, this.f1895c);
            } else {
                j.this.r.post(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements RecyclerView.j {
        e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.j
        public int a(int i, int i2) {
            j jVar = j.this;
            View view = jVar.x;
            if (view == null) {
                return i2;
            }
            int i3 = jVar.y;
            if (i3 == -1) {
                i3 = jVar.r.indexOfChild(view);
                j.this.y = i3;
            }
            return i2 == i + (-1) ? i3 : i2 < i3 ? i2 : i2 + 1;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class f {
        private static final int ABS_HORIZONTAL_DIR_FLAGS = 789516;
        public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
        public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
        private static final long DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS = 2000;
        static final int RELATIVE_DIR_FLAGS = 3158064;
        private static final Interpolator sDragScrollInterpolator = new a();
        private static final Interpolator sDragViewScrollCapInterpolator = new b();
        private int mCachedMaxScrollSpeed = -1;

        /* loaded from: classes.dex */
        static class a implements Interpolator {
            a() {
            }

            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f2) {
                return f2 * f2 * f2 * f2 * f2;
            }
        }

        /* loaded from: classes.dex */
        static class b implements Interpolator {
            b() {
            }

            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f2) {
                float f3 = f2 - 1.0f;
                return (f3 * f3 * f3 * f3 * f3) + 1.0f;
            }
        }

        public static int convertToRelativeDirection(int i, int i2) {
            int i3;
            int i4 = i & ABS_HORIZONTAL_DIR_FLAGS;
            if (i4 == 0) {
                return i;
            }
            int i5 = i & (i4 ^ (-1));
            if (i2 == 0) {
                i3 = i4 << 2;
            } else {
                int i6 = i4 << 1;
                i5 |= (-789517) & i6;
                i3 = (i6 & ABS_HORIZONTAL_DIR_FLAGS) << 2;
            }
            return i5 | i3;
        }

        public static k getDefaultUIUtil() {
            return l.f1907a;
        }

        private int getMaxDragScroll(RecyclerView recyclerView) {
            if (this.mCachedMaxScrollSpeed == -1) {
                this.mCachedMaxScrollSpeed = recyclerView.getResources().getDimensionPixelSize(a.m.b.item_touch_helper_max_drag_scroll_per_frame);
            }
            return this.mCachedMaxScrollSpeed;
        }

        public static int makeFlag(int i, int i2) {
            return i2 << (i * 8);
        }

        public static int makeMovementFlags(int i, int i2) {
            return makeFlag(2, i) | makeFlag(1, i2) | makeFlag(0, i2 | i);
        }

        public boolean canDropOver(RecyclerView recyclerView, RecyclerView.d0 d0Var, RecyclerView.d0 d0Var2) {
            return true;
        }

        public RecyclerView.d0 chooseDropTarget(RecyclerView.d0 d0Var, List<RecyclerView.d0> list, int i, int i2) {
            int i3;
            int bottom;
            int top;
            int abs;
            int left;
            int abs2;
            int right;
            int width = i + d0Var.itemView.getWidth();
            int height = i2 + d0Var.itemView.getHeight();
            int left2 = i - d0Var.itemView.getLeft();
            int top2 = i2 - d0Var.itemView.getTop();
            int size = list.size();
            RecyclerView.d0 d0Var2 = null;
            int i4 = -1;
            for (int i5 = 0; i5 < size; i5++) {
                RecyclerView.d0 d0Var3 = list.get(i5);
                if (left2 <= 0 || (right = d0Var3.itemView.getRight() - width) >= 0 || d0Var3.itemView.getRight() <= d0Var.itemView.getRight() || (i3 = Math.abs(right)) <= i4) {
                    i3 = i4;
                } else {
                    d0Var2 = d0Var3;
                }
                if (left2 < 0 && (left = d0Var3.itemView.getLeft() - i) > 0 && d0Var3.itemView.getLeft() < d0Var.itemView.getLeft() && (abs2 = Math.abs(left)) > i3) {
                    i3 = abs2;
                    d0Var2 = d0Var3;
                }
                if (top2 < 0 && (top = d0Var3.itemView.getTop() - i2) > 0 && d0Var3.itemView.getTop() < d0Var.itemView.getTop() && (abs = Math.abs(top)) > i3) {
                    i3 = abs;
                    d0Var2 = d0Var3;
                }
                if (top2 <= 0 || (bottom = d0Var3.itemView.getBottom() - height) >= 0 || d0Var3.itemView.getBottom() <= d0Var.itemView.getBottom() || (i4 = Math.abs(bottom)) <= i3) {
                    i4 = i3;
                } else {
                    d0Var2 = d0Var3;
                }
            }
            return d0Var2;
        }

        public abstract void clearView(RecyclerView recyclerView, RecyclerView.d0 d0Var);

        public int convertToAbsoluteDirection(int i, int i2) {
            int i3;
            int i4 = i & RELATIVE_DIR_FLAGS;
            if (i4 == 0) {
                return i;
            }
            int i5 = i & (i4 ^ (-1));
            if (i2 == 0) {
                i3 = i4 >> 2;
            } else {
                int i6 = i4 >> 1;
                i5 |= (-3158065) & i6;
                i3 = (i6 & RELATIVE_DIR_FLAGS) >> 2;
            }
            return i5 | i3;
        }

        final int getAbsoluteMovementFlags(RecyclerView recyclerView, RecyclerView.d0 d0Var) {
            return convertToAbsoluteDirection(getMovementFlags(recyclerView, d0Var), a.f.l.w.q(recyclerView));
        }

        public long getAnimationDuration(RecyclerView recyclerView, int i, float f2, float f3) {
            RecyclerView.l itemAnimator = recyclerView.getItemAnimator();
            if (itemAnimator == null) {
                return i == 8 ? 200L : 250L;
            }
            if (i == 8) {
                return itemAnimator.e();
            }
            return itemAnimator.f();
        }

        public int getBoundingBoxMargin() {
            return 0;
        }

        public float getMoveThreshold(RecyclerView.d0 d0Var) {
            return 0.5f;
        }

        public abstract int getMovementFlags(RecyclerView recyclerView, RecyclerView.d0 d0Var);

        public float getSwipeEscapeVelocity(float f2) {
            return f2;
        }

        public float getSwipeThreshold(RecyclerView.d0 d0Var) {
            return 0.5f;
        }

        public float getSwipeVelocityThreshold(float f2) {
            return f2;
        }

        boolean hasDragFlag(RecyclerView recyclerView, RecyclerView.d0 d0Var) {
            return (getAbsoluteMovementFlags(recyclerView, d0Var) & 16711680) != 0;
        }

        boolean hasSwipeFlag(RecyclerView recyclerView, RecyclerView.d0 d0Var) {
            return (getAbsoluteMovementFlags(recyclerView, d0Var) & 65280) != 0;
        }

        public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int i, int i2, int i3, long j) {
            int signum = (int) (((int) (((int) Math.signum(i2)) * getMaxDragScroll(recyclerView) * sDragViewScrollCapInterpolator.getInterpolation(Math.min(1.0f, (Math.abs(i2) * 1.0f) / i)))) * sDragScrollInterpolator.getInterpolation(j <= DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS ? ((float) j) / 2000.0f : 1.0f));
            return signum == 0 ? i2 > 0 ? 1 : -1 : signum;
        }

        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        public abstract boolean isLongPressDragEnabled();

        public abstract void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.d0 d0Var, float f2, float f3, int i, boolean z);

        public abstract void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.d0 d0Var, float f2, float f3, int i, boolean z);

        void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.d0 d0Var, List<h> list, int i, float f2, float f3) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                h hVar = list.get(i2);
                hVar.c();
                int save = canvas.save();
                onChildDraw(canvas, recyclerView, hVar.f1904e, hVar.i, hVar.j, hVar.f1905f, false);
                canvas.restoreToCount(save);
            }
            if (d0Var != null) {
                int save2 = canvas.save();
                onChildDraw(canvas, recyclerView, d0Var, f2, f3, i, true);
                canvas.restoreToCount(save2);
            }
        }

        void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.d0 d0Var, List<h> list, int i, float f2, float f3) {
            int size = list.size();
            boolean z = false;
            for (int i2 = 0; i2 < size; i2++) {
                h hVar = list.get(i2);
                int save = canvas.save();
                onChildDrawOver(canvas, recyclerView, hVar.f1904e, hVar.i, hVar.j, hVar.f1905f, false);
                canvas.restoreToCount(save);
            }
            if (d0Var != null) {
                int save2 = canvas.save();
                onChildDrawOver(canvas, recyclerView, d0Var, f2, f3, i, true);
                canvas.restoreToCount(save2);
            }
            for (int i3 = size - 1; i3 >= 0; i3--) {
                h hVar2 = list.get(i3);
                if (hVar2.l && !hVar2.h) {
                    list.remove(i3);
                } else if (!hVar2.l) {
                    z = true;
                }
            }
            if (z) {
                recyclerView.invalidate();
            }
        }

        public abstract boolean onMove(RecyclerView recyclerView, RecyclerView.d0 d0Var, RecyclerView.d0 d0Var2);

        /* JADX WARN: Multi-variable type inference failed */
        public void onMoved(RecyclerView recyclerView, RecyclerView.d0 d0Var, int i, RecyclerView.d0 d0Var2, int i2, int i3, int i4) {
            RecyclerView.o layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof i) {
                ((i) layoutManager).a(d0Var.itemView, d0Var2.itemView, i3, i4);
                return;
            }
            if (layoutManager.a()) {
                if (layoutManager.f(d0Var2.itemView) <= recyclerView.getPaddingLeft()) {
                    recyclerView.scrollToPosition(i2);
                }
                if (layoutManager.i(d0Var2.itemView) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                    recyclerView.scrollToPosition(i2);
                }
            }
            if (layoutManager.b()) {
                if (layoutManager.j(d0Var2.itemView) <= recyclerView.getPaddingTop()) {
                    recyclerView.scrollToPosition(i2);
                }
                if (layoutManager.e(d0Var2.itemView) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                    recyclerView.scrollToPosition(i2);
                }
            }
        }

        public abstract void onSelectedChanged(RecyclerView.d0 d0Var, int i);

        public abstract void onSwiped(RecyclerView.d0 d0Var, int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class g extends GestureDetector.SimpleOnGestureListener {

        /* renamed from: b, reason: collision with root package name */
        private boolean f1898b = true;

        g() {
        }

        void a() {
            this.f1898b = false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            View b2;
            RecyclerView.d0 childViewHolder;
            if (!this.f1898b || (b2 = j.this.b(motionEvent)) == null || (childViewHolder = j.this.r.getChildViewHolder(b2)) == null) {
                return;
            }
            j jVar = j.this;
            if (jVar.m.hasDragFlag(jVar.r, childViewHolder)) {
                int pointerId = motionEvent.getPointerId(0);
                int i = j.this.l;
                if (pointerId == i) {
                    int findPointerIndex = motionEvent.findPointerIndex(i);
                    float x = motionEvent.getX(findPointerIndex);
                    float y = motionEvent.getY(findPointerIndex);
                    j jVar2 = j.this;
                    jVar2.f1889d = x;
                    jVar2.f1890e = y;
                    jVar2.i = 0.0f;
                    jVar2.h = 0.0f;
                    if (jVar2.m.isLongPressDragEnabled()) {
                        j.this.a(childViewHolder, 2);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class h implements Animator.AnimatorListener {

        /* renamed from: a, reason: collision with root package name */
        final float f1900a;

        /* renamed from: b, reason: collision with root package name */
        final float f1901b;

        /* renamed from: c, reason: collision with root package name */
        final float f1902c;

        /* renamed from: d, reason: collision with root package name */
        final float f1903d;

        /* renamed from: e, reason: collision with root package name */
        final RecyclerView.d0 f1904e;

        /* renamed from: f, reason: collision with root package name */
        final int f1905f;
        boolean h;
        float i;
        float j;
        private float m;
        boolean k = false;
        boolean l = false;
        private final ValueAnimator g = ValueAnimator.ofFloat(0.0f, 1.0f);

        /* loaded from: classes.dex */
        class a implements ValueAnimator.AnimatorUpdateListener {
            a() {
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                h.this.a(valueAnimator.getAnimatedFraction());
            }
        }

        h(RecyclerView.d0 d0Var, int i, int i2, float f2, float f3, float f4, float f5) {
            this.f1905f = i2;
            this.f1904e = d0Var;
            this.f1900a = f2;
            this.f1901b = f3;
            this.f1902c = f4;
            this.f1903d = f5;
            this.g.addUpdateListener(new a());
            this.g.setTarget(d0Var.itemView);
            this.g.addListener(this);
            a(0.0f);
        }

        public void a(long j) {
            this.g.setDuration(j);
        }

        public void b() {
            this.f1904e.setIsRecyclable(false);
            this.g.start();
        }

        public void c() {
            float f2 = this.f1900a;
            float f3 = this.f1902c;
            if (f2 == f3) {
                this.i = this.f1904e.itemView.getTranslationX();
            } else {
                this.i = f2 + (this.m * (f3 - f2));
            }
            float f4 = this.f1901b;
            float f5 = this.f1903d;
            if (f4 == f5) {
                this.j = this.f1904e.itemView.getTranslationY();
            } else {
                this.j = f4 + (this.m * (f5 - f4));
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            a(1.0f);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (!this.l) {
                this.f1904e.setIsRecyclable(true);
            }
            this.l = true;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        public void a() {
            this.g.cancel();
        }

        public void a(float f2) {
            this.m = f2;
        }
    }

    /* loaded from: classes.dex */
    public interface i {
        void a(View view, View view2, int i, int i2);
    }

    public j(f fVar) {
        this.m = fVar;
    }

    private static boolean a(View view, float f2, float f3, float f4, float f5) {
        return f2 >= f4 && f2 <= f4 + ((float) view.getWidth()) && f3 >= f5 && f3 <= f5 + ((float) view.getHeight());
    }

    private void d() {
        if (Build.VERSION.SDK_INT >= 21) {
            return;
        }
        if (this.w == null) {
            this.w = new e();
        }
        this.r.setChildDrawingOrderCallback(this.w);
    }

    private void e() {
        this.r.removeItemDecoration(this);
        this.r.removeOnItemTouchListener(this.B);
        this.r.removeOnChildAttachStateChangeListener(this);
        for (int size = this.p.size() - 1; size >= 0; size--) {
            this.m.clearView(this.r, this.p.get(0).f1904e);
        }
        this.p.clear();
        this.x = null;
        this.y = -1;
        f();
        i();
    }

    private void f() {
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.t = null;
        }
    }

    private void g() {
        this.q = ViewConfiguration.get(this.r.getContext()).getScaledTouchSlop();
        this.r.addItemDecoration(this);
        this.r.addOnItemTouchListener(this.B);
        this.r.addOnChildAttachStateChangeListener(this);
        h();
    }

    private void h() {
        this.A = new g();
        this.z = new a.f.l.c(this.r.getContext(), this.A);
    }

    private void i() {
        g gVar = this.A;
        if (gVar != null) {
            gVar.a();
            this.A = null;
        }
        if (this.z != null) {
            this.z = null;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public void a(View view) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void b(Canvas canvas, RecyclerView recyclerView, RecyclerView.a0 a0Var) {
        float f2;
        float f3;
        if (this.f1888c != null) {
            a(this.f1887b);
            float[] fArr = this.f1887b;
            float f4 = fArr[0];
            f3 = fArr[1];
            f2 = f4;
        } else {
            f2 = 0.0f;
            f3 = 0.0f;
        }
        this.m.onDrawOver(canvas, recyclerView, this.f1888c, this.p, this.n, f2, f3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00c5, code lost:
    
        if (r1 > 0) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0104 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0101  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean c() {
        /*
            Method dump skipped, instructions count: 281
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.j.c():boolean");
    }

    public void a(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.r;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            e();
        }
        this.r = recyclerView;
        if (recyclerView != null) {
            Resources resources = recyclerView.getResources();
            this.f1891f = resources.getDimension(a.m.b.item_touch_helper_swipe_escape_velocity);
            this.g = resources.getDimension(a.m.b.item_touch_helper_swipe_escape_max_velocity);
            g();
        }
    }

    private List<RecyclerView.d0> b(RecyclerView.d0 d0Var) {
        RecyclerView.d0 d0Var2 = d0Var;
        List<RecyclerView.d0> list = this.u;
        if (list == null) {
            this.u = new ArrayList();
            this.v = new ArrayList();
        } else {
            list.clear();
            this.v.clear();
        }
        int boundingBoxMargin = this.m.getBoundingBoxMargin();
        int round = Math.round(this.j + this.h) - boundingBoxMargin;
        int round2 = Math.round(this.k + this.i) - boundingBoxMargin;
        int i2 = boundingBoxMargin * 2;
        int width = d0Var2.itemView.getWidth() + round + i2;
        int height = d0Var2.itemView.getHeight() + round2 + i2;
        int i3 = (round + width) / 2;
        int i4 = (round2 + height) / 2;
        RecyclerView.o layoutManager = this.r.getLayoutManager();
        int e2 = layoutManager.e();
        int i5 = 0;
        while (i5 < e2) {
            View d2 = layoutManager.d(i5);
            if (d2 != d0Var2.itemView && d2.getBottom() >= round2 && d2.getTop() <= height && d2.getRight() >= round && d2.getLeft() <= width) {
                RecyclerView.d0 childViewHolder = this.r.getChildViewHolder(d2);
                if (this.m.canDropOver(this.r, this.f1888c, childViewHolder)) {
                    int abs = Math.abs(i3 - ((d2.getLeft() + d2.getRight()) / 2));
                    int abs2 = Math.abs(i4 - ((d2.getTop() + d2.getBottom()) / 2));
                    int i6 = (abs * abs) + (abs2 * abs2);
                    int size = this.u.size();
                    int i7 = 0;
                    for (int i8 = 0; i8 < size && i6 > this.v.get(i8).intValue(); i8++) {
                        i7++;
                    }
                    this.u.add(i7, childViewHolder);
                    this.v.add(i7, Integer.valueOf(i6));
                }
            }
            i5++;
            d0Var2 = d0Var;
        }
        return this.u;
    }

    private void a(float[] fArr) {
        if ((this.o & 12) != 0) {
            fArr[0] = (this.j + this.h) - this.f1888c.itemView.getLeft();
        } else {
            fArr[0] = this.f1888c.itemView.getTranslationX();
        }
        if ((this.o & 3) != 0) {
            fArr[1] = (this.k + this.i) - this.f1888c.itemView.getTop();
        } else {
            fArr[1] = this.f1888c.itemView.getTranslationY();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void a(Canvas canvas, RecyclerView recyclerView, RecyclerView.a0 a0Var) {
        float f2;
        float f3;
        this.y = -1;
        if (this.f1888c != null) {
            a(this.f1887b);
            float[] fArr = this.f1887b;
            float f4 = fArr[0];
            f3 = fArr[1];
            f2 = f4;
        } else {
            f2 = 0.0f;
            f3 = 0.0f;
        }
        this.m.onDraw(canvas, recyclerView, this.f1888c, this.p, this.n, f2, f3);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0137  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void a(androidx.recyclerview.widget.RecyclerView.d0 r24, int r25) {
        /*
            Method dump skipped, instructions count: 335
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.j.a(androidx.recyclerview.widget.RecyclerView$d0, int):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public void b(View view) {
        c(view);
        RecyclerView.d0 childViewHolder = this.r.getChildViewHolder(view);
        if (childViewHolder == null) {
            return;
        }
        RecyclerView.d0 d0Var = this.f1888c;
        if (d0Var != null && childViewHolder == d0Var) {
            a((RecyclerView.d0) null, 0);
            return;
        }
        a(childViewHolder, false);
        if (this.f1886a.remove(childViewHolder.itemView)) {
            this.m.clearView(this.r, childViewHolder);
        }
    }

    private RecyclerView.d0 c(MotionEvent motionEvent) {
        View b2;
        RecyclerView.o layoutManager = this.r.getLayoutManager();
        int i2 = this.l;
        if (i2 == -1) {
            return null;
        }
        int findPointerIndex = motionEvent.findPointerIndex(i2);
        float x = motionEvent.getX(findPointerIndex) - this.f1889d;
        float y = motionEvent.getY(findPointerIndex) - this.f1890e;
        float abs = Math.abs(x);
        float abs2 = Math.abs(y);
        int i3 = this.q;
        if (abs < i3 && abs2 < i3) {
            return null;
        }
        if (abs > abs2 && layoutManager.a()) {
            return null;
        }
        if ((abs2 <= abs || !layoutManager.b()) && (b2 = b(motionEvent)) != null) {
            return this.r.getChildViewHolder(b2);
        }
        return null;
    }

    void b() {
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.t = VelocityTracker.obtain();
    }

    View b(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        RecyclerView.d0 d0Var = this.f1888c;
        if (d0Var != null) {
            View view = d0Var.itemView;
            if (a(view, x, y, this.j + this.h, this.k + this.i)) {
                return view;
            }
        }
        for (int size = this.p.size() - 1; size >= 0; size--) {
            h hVar = this.p.get(size);
            View view2 = hVar.f1904e.itemView;
            if (a(view2, x, y, hVar.i, hVar.j)) {
                return view2;
            }
        }
        return this.r.findChildViewUnder(x, y);
    }

    private int c(RecyclerView.d0 d0Var) {
        if (this.n == 2) {
            return 0;
        }
        int movementFlags = this.m.getMovementFlags(this.r, d0Var);
        int convertToAbsoluteDirection = (this.m.convertToAbsoluteDirection(movementFlags, a.f.l.w.q(this.r)) & 65280) >> 8;
        if (convertToAbsoluteDirection == 0) {
            return 0;
        }
        int i2 = (movementFlags & 65280) >> 8;
        if (Math.abs(this.h) > Math.abs(this.i)) {
            int b2 = b(d0Var, convertToAbsoluteDirection);
            if (b2 > 0) {
                return (i2 & b2) == 0 ? f.convertToRelativeDirection(b2, a.f.l.w.q(this.r)) : b2;
            }
            int c2 = c(d0Var, convertToAbsoluteDirection);
            if (c2 > 0) {
                return c2;
            }
        } else {
            int c3 = c(d0Var, convertToAbsoluteDirection);
            if (c3 > 0) {
                return c3;
            }
            int b3 = b(d0Var, convertToAbsoluteDirection);
            if (b3 > 0) {
                return (i2 & b3) == 0 ? f.convertToRelativeDirection(b3, a.f.l.w.q(this.r)) : b3;
            }
        }
        return 0;
    }

    private int b(RecyclerView.d0 d0Var, int i2) {
        if ((i2 & 12) == 0) {
            return 0;
        }
        int i3 = this.h > 0.0f ? 8 : 4;
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null && this.l > -1) {
            velocityTracker.computeCurrentVelocity(1000, this.m.getSwipeVelocityThreshold(this.g));
            float xVelocity = this.t.getXVelocity(this.l);
            float yVelocity = this.t.getYVelocity(this.l);
            int i4 = xVelocity <= 0.0f ? 4 : 8;
            float abs = Math.abs(xVelocity);
            if ((i4 & i2) != 0 && i3 == i4 && abs >= this.m.getSwipeEscapeVelocity(this.f1891f) && abs > Math.abs(yVelocity)) {
                return i4;
            }
        }
        float width = this.r.getWidth() * this.m.getSwipeThreshold(d0Var);
        if ((i2 & i3) == 0 || Math.abs(this.h) <= width) {
            return 0;
        }
        return i3;
    }

    void a(h hVar, int i2) {
        this.r.post(new d(hVar, i2));
    }

    boolean a() {
        int size = this.p.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!this.p.get(i2).l) {
                return true;
            }
        }
        return false;
    }

    void a(RecyclerView.d0 d0Var) {
        if (!this.r.isLayoutRequested() && this.n == 2) {
            float moveThreshold = this.m.getMoveThreshold(d0Var);
            int i2 = (int) (this.j + this.h);
            int i3 = (int) (this.k + this.i);
            if (Math.abs(i3 - d0Var.itemView.getTop()) >= d0Var.itemView.getHeight() * moveThreshold || Math.abs(i2 - d0Var.itemView.getLeft()) >= d0Var.itemView.getWidth() * moveThreshold) {
                List<RecyclerView.d0> b2 = b(d0Var);
                if (b2.size() == 0) {
                    return;
                }
                RecyclerView.d0 chooseDropTarget = this.m.chooseDropTarget(d0Var, b2, i2, i3);
                if (chooseDropTarget == null) {
                    this.u.clear();
                    this.v.clear();
                    return;
                }
                int adapterPosition = chooseDropTarget.getAdapterPosition();
                int adapterPosition2 = d0Var.getAdapterPosition();
                if (this.m.onMove(this.r, d0Var, chooseDropTarget)) {
                    this.m.onMoved(this.r, d0Var, adapterPosition2, chooseDropTarget, adapterPosition, i2, i3);
                }
            }
        }
    }

    private int c(RecyclerView.d0 d0Var, int i2) {
        if ((i2 & 3) == 0) {
            return 0;
        }
        int i3 = this.i > 0.0f ? 2 : 1;
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null && this.l > -1) {
            velocityTracker.computeCurrentVelocity(1000, this.m.getSwipeVelocityThreshold(this.g));
            float xVelocity = this.t.getXVelocity(this.l);
            float yVelocity = this.t.getYVelocity(this.l);
            int i4 = yVelocity <= 0.0f ? 1 : 2;
            float abs = Math.abs(yVelocity);
            if ((i4 & i2) != 0 && i4 == i3 && abs >= this.m.getSwipeEscapeVelocity(this.f1891f) && abs > Math.abs(xVelocity)) {
                return i4;
            }
        }
        float height = this.r.getHeight() * this.m.getSwipeThreshold(d0Var);
        if ((i2 & i3) == 0 || Math.abs(this.i) <= height) {
            return 0;
        }
        return i3;
    }

    void c(View view) {
        if (view == this.x) {
            this.x = null;
            if (this.w != null) {
                this.r.setChildDrawingOrderCallback(null);
            }
        }
    }

    void a(RecyclerView.d0 d0Var, boolean z) {
        for (int size = this.p.size() - 1; size >= 0; size--) {
            h hVar = this.p.get(size);
            if (hVar.f1904e == d0Var) {
                hVar.k |= z;
                if (!hVar.l) {
                    hVar.a();
                }
                this.p.remove(size);
                return;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void a(Rect rect, View view, RecyclerView recyclerView, RecyclerView.a0 a0Var) {
        rect.setEmpty();
    }

    void a(int i2, MotionEvent motionEvent, int i3) {
        RecyclerView.d0 c2;
        int absoluteMovementFlags;
        if (this.f1888c != null || i2 != 2 || this.n == 2 || !this.m.isItemViewSwipeEnabled() || this.r.getScrollState() == 1 || (c2 = c(motionEvent)) == null || (absoluteMovementFlags = (this.m.getAbsoluteMovementFlags(this.r, c2) & 65280) >> 8) == 0) {
            return;
        }
        float x = motionEvent.getX(i3);
        float y = motionEvent.getY(i3);
        float f2 = x - this.f1889d;
        float f3 = y - this.f1890e;
        float abs = Math.abs(f2);
        float abs2 = Math.abs(f3);
        int i4 = this.q;
        if (abs >= i4 || abs2 >= i4) {
            if (abs > abs2) {
                if (f2 < 0.0f && (absoluteMovementFlags & 4) == 0) {
                    return;
                }
                if (f2 > 0.0f && (absoluteMovementFlags & 8) == 0) {
                    return;
                }
            } else {
                if (f3 < 0.0f && (absoluteMovementFlags & 1) == 0) {
                    return;
                }
                if (f3 > 0.0f && (absoluteMovementFlags & 2) == 0) {
                    return;
                }
            }
            this.i = 0.0f;
            this.h = 0.0f;
            this.l = motionEvent.getPointerId(0);
            a(c2, 1);
        }
    }

    h a(MotionEvent motionEvent) {
        if (this.p.isEmpty()) {
            return null;
        }
        View b2 = b(motionEvent);
        for (int size = this.p.size() - 1; size >= 0; size--) {
            h hVar = this.p.get(size);
            if (hVar.f1904e.itemView == b2) {
                return hVar;
            }
        }
        return null;
    }

    void a(MotionEvent motionEvent, int i2, int i3) {
        float x = motionEvent.getX(i3);
        float y = motionEvent.getY(i3);
        this.h = x - this.f1889d;
        this.i = y - this.f1890e;
        if ((i2 & 4) == 0) {
            this.h = Math.max(0.0f, this.h);
        }
        if ((i2 & 8) == 0) {
            this.h = Math.min(0.0f, this.h);
        }
        if ((i2 & 1) == 0) {
            this.i = Math.max(0.0f, this.i);
        }
        if ((i2 & 2) == 0) {
            this.i = Math.min(0.0f, this.i);
        }
    }
}
