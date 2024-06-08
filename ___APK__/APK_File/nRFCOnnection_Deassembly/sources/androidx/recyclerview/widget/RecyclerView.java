package androidx.recyclerview.widget;

import a.f.l.f0.c;
import android.R;
import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.preference.Preference;
import androidx.recyclerview.widget.a;
import androidx.recyclerview.widget.d;
import androidx.recyclerview.widget.i;
import androidx.recyclerview.widget.s;
import androidx.recyclerview.widget.w;
import androidx.recyclerview.widget.x;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import no.nordicsemi.android.ble.error.GattError;
import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements a.f.l.u, a.f.l.j, a.f.l.k {
    static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC;
    static final boolean ALLOW_THREAD_GAP_WORK;
    static final boolean DEBUG = false;
    static final int DEFAULT_ORIENTATION = 1;
    static final boolean DISPATCH_TEMP_DETACH = false;
    private static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION;
    static final boolean FORCE_INVALIDATE_DISPLAY_LIST;
    static final long FOREVER_NS = Long.MAX_VALUE;
    public static final int HORIZONTAL = 0;
    private static final boolean IGNORE_DETACHED_FOCUSED_CHILD;
    private static final int INVALID_POINTER = -1;
    public static final int INVALID_TYPE = -1;
    private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
    static final int MAX_SCROLL_DURATION = 2000;
    private static final int[] NESTED_SCROLLING_ATTRS = {R.attr.nestedScrollingEnabled};
    public static final long NO_ID = -1;
    public static final int NO_POSITION = -1;
    static final boolean POST_UPDATES_ON_ANIMATION;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    static final String TAG = "RecyclerView";
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
    static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
    private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
    static final String TRACE_NESTED_PREFETCH_TAG = "RV Nested Prefetch";
    private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
    private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
    static final String TRACE_PREFETCH_TAG = "RV Prefetch";
    static final String TRACE_SCROLL_TAG = "RV Scroll";
    public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
    static final boolean VERBOSE_TRACING = false;
    public static final int VERTICAL = 1;
    static final Interpolator sQuinticInterpolator;
    androidx.recyclerview.widget.s mAccessibilityDelegate;
    private final AccessibilityManager mAccessibilityManager;
    g mAdapter;
    androidx.recyclerview.widget.a mAdapterHelper;
    boolean mAdapterUpdateDuringMeasure;
    private EdgeEffect mBottomGlow;
    private j mChildDrawingOrderCallback;
    androidx.recyclerview.widget.d mChildHelper;
    boolean mClipToPadding;
    boolean mDataSetHasChangedAfterLayout;
    boolean mDispatchItemsChangedEvent;
    private int mDispatchScrollCounter;
    private int mEatenAccessibilityChangeFlags;
    private k mEdgeEffectFactory;
    boolean mEnableFastScroller;
    boolean mFirstLayoutComplete;
    androidx.recyclerview.widget.i mGapWorker;
    boolean mHasFixedSize;
    private boolean mIgnoreMotionEventTillDown;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private int mInterceptRequestLayoutDepth;
    private s mInterceptingOnItemTouchListener;
    boolean mIsAttached;
    l mItemAnimator;
    private l.b mItemAnimatorListener;
    private Runnable mItemAnimatorRunner;
    final ArrayList<n> mItemDecorations;
    boolean mItemsAddedOrRemoved;
    boolean mItemsChanged;
    private int mLastTouchX;
    private int mLastTouchY;
    o mLayout;
    private int mLayoutOrScrollCounter;
    boolean mLayoutSuppressed;
    boolean mLayoutWasDefered;
    private EdgeEffect mLeftGlow;
    private final int mMaxFlingVelocity;
    private final int mMinFlingVelocity;
    private final int[] mMinMaxLayoutPositions;
    private final int[] mNestedOffsets;
    private final x mObserver;
    private List<q> mOnChildAttachStateListeners;
    private r mOnFlingListener;
    private final ArrayList<s> mOnItemTouchListeners;
    final List<d0> mPendingAccessibilityImportanceChange;
    private y mPendingSavedState;
    boolean mPostedAnimatorRunner;
    i.b mPrefetchRegistry;
    private boolean mPreserveFocusAfterLayout;
    final v mRecycler;
    w mRecyclerListener;
    final int[] mReusableIntPair;
    private EdgeEffect mRightGlow;
    private float mScaledHorizontalScrollFactor;
    private float mScaledVerticalScrollFactor;
    private t mScrollListener;
    private List<t> mScrollListeners;
    private final int[] mScrollOffset;
    private int mScrollPointerId;
    private int mScrollState;
    private a.f.l.m mScrollingChildHelper;
    final a0 mState;
    final Rect mTempRect;
    private final Rect mTempRect2;
    final RectF mTempRectF;
    private EdgeEffect mTopGlow;
    private int mTouchSlop;
    final Runnable mUpdateChildViewsRunnable;
    private VelocityTracker mVelocityTracker;
    final c0 mViewFlinger;
    private final x.b mViewInfoProcessCallback;
    final androidx.recyclerview.widget.x mViewInfoStore;

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView recyclerView = RecyclerView.this;
            if (!recyclerView.mFirstLayoutComplete || recyclerView.isLayoutRequested()) {
                return;
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            if (!recyclerView2.mIsAttached) {
                recyclerView2.requestLayout();
            } else if (recyclerView2.mLayoutSuppressed) {
                recyclerView2.mLayoutWasDefered = true;
            } else {
                recyclerView2.consumePendingUpdateOperations();
            }
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            l lVar = RecyclerView.this.mItemAnimator;
            if (lVar != null) {
                lVar.i();
            }
            RecyclerView.this.mPostedAnimatorRunner = false;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class b0 {
        public abstract View a(v vVar, int i, int i2);
    }

    /* loaded from: classes.dex */
    static class c implements Interpolator {
        c() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    }

    /* loaded from: classes.dex */
    class d implements x.b {
        d() {
        }

        @Override // androidx.recyclerview.widget.x.b
        public void a(d0 d0Var, l.c cVar, l.c cVar2) {
            RecyclerView.this.animateAppearance(d0Var, cVar, cVar2);
        }

        @Override // androidx.recyclerview.widget.x.b
        public void b(d0 d0Var, l.c cVar, l.c cVar2) {
            RecyclerView.this.mRecycler.c(d0Var);
            RecyclerView.this.animateDisappearance(d0Var, cVar, cVar2);
        }

        @Override // androidx.recyclerview.widget.x.b
        public void c(d0 d0Var, l.c cVar, l.c cVar2) {
            d0Var.setIsRecyclable(false);
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.mDataSetHasChangedAfterLayout) {
                if (recyclerView.mItemAnimator.a(d0Var, d0Var, cVar, cVar2)) {
                    RecyclerView.this.postAnimationRunner();
                }
            } else if (recyclerView.mItemAnimator.c(d0Var, cVar, cVar2)) {
                RecyclerView.this.postAnimationRunner();
            }
        }

        @Override // androidx.recyclerview.widget.x.b
        public void a(d0 d0Var) {
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.mLayout.a(d0Var.itemView, recyclerView.mRecycler);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class d0 {
        static final int FLAG_ADAPTER_FULLUPDATE = 1024;
        static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
        static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
        static final int FLAG_BOUND = 1;
        static final int FLAG_IGNORE = 128;
        static final int FLAG_INVALID = 4;
        static final int FLAG_MOVED = 2048;
        static final int FLAG_NOT_RECYCLABLE = 16;
        static final int FLAG_REMOVED = 8;
        static final int FLAG_RETURNED_FROM_SCRAP = 32;
        static final int FLAG_TMP_DETACHED = 256;
        static final int FLAG_UPDATE = 2;
        private static final List<Object> FULLUPDATE_PAYLOADS = Collections.emptyList();
        static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
        public final View itemView;
        int mFlags;
        WeakReference<RecyclerView> mNestedRecyclerView;
        RecyclerView mOwnerRecyclerView;
        int mPosition = -1;
        int mOldPosition = -1;
        long mItemId = -1;
        int mItemViewType = -1;
        int mPreLayoutPosition = -1;
        d0 mShadowedHolder = null;
        d0 mShadowingHolder = null;
        List<Object> mPayloads = null;
        List<Object> mUnmodifiedPayloads = null;
        private int mIsRecyclableCount = 0;
        v mScrapContainer = null;
        boolean mInChangeScrap = false;
        private int mWasImportantForAccessibilityBeforeHidden = 0;
        int mPendingAccessibilityState = -1;

        public d0(View view) {
            if (view != null) {
                this.itemView = view;
                return;
            }
            throw new IllegalArgumentException("itemView may not be null");
        }

        private void createPayloadsIfNeeded() {
            if (this.mPayloads == null) {
                this.mPayloads = new ArrayList();
                this.mUnmodifiedPayloads = Collections.unmodifiableList(this.mPayloads);
            }
        }

        void addChangePayload(Object obj) {
            if (obj == null) {
                addFlags(1024);
            } else if ((1024 & this.mFlags) == 0) {
                createPayloadsIfNeeded();
                this.mPayloads.add(obj);
            }
        }

        void addFlags(int i) {
            this.mFlags = i | this.mFlags;
        }

        void clearOldPosition() {
            this.mOldPosition = -1;
            this.mPreLayoutPosition = -1;
        }

        void clearPayload() {
            List<Object> list = this.mPayloads;
            if (list != null) {
                list.clear();
            }
            this.mFlags &= -1025;
        }

        void clearReturnedFromScrapFlag() {
            this.mFlags &= -33;
        }

        void clearTmpDetachFlag() {
            this.mFlags &= -257;
        }

        boolean doesTransientStatePreventRecycling() {
            return (this.mFlags & 16) == 0 && a.f.l.w.C(this.itemView);
        }

        void flagRemovedAndOffsetPosition(int i, int i2, boolean z) {
            addFlags(8);
            offsetPosition(i2, z);
            this.mPosition = i;
        }

        public final int getAdapterPosition() {
            RecyclerView recyclerView = this.mOwnerRecyclerView;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.getAdapterPositionFor(this);
        }

        public final long getItemId() {
            return this.mItemId;
        }

        public final int getItemViewType() {
            return this.mItemViewType;
        }

        public final int getLayoutPosition() {
            int i = this.mPreLayoutPosition;
            return i == -1 ? this.mPosition : i;
        }

        public final int getOldPosition() {
            return this.mOldPosition;
        }

        @Deprecated
        public final int getPosition() {
            int i = this.mPreLayoutPosition;
            return i == -1 ? this.mPosition : i;
        }

        List<Object> getUnmodifiedPayloads() {
            if ((this.mFlags & 1024) == 0) {
                List<Object> list = this.mPayloads;
                if (list != null && list.size() != 0) {
                    return this.mUnmodifiedPayloads;
                }
                return FULLUPDATE_PAYLOADS;
            }
            return FULLUPDATE_PAYLOADS;
        }

        boolean hasAnyOfTheFlags(int i) {
            return (i & this.mFlags) != 0;
        }

        boolean isAdapterPositionUnknown() {
            return (this.mFlags & 512) != 0 || isInvalid();
        }

        boolean isAttachedToTransitionOverlay() {
            return (this.itemView.getParent() == null || this.itemView.getParent() == this.mOwnerRecyclerView) ? false : true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean isBound() {
            return (this.mFlags & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean isInvalid() {
            return (this.mFlags & 4) != 0;
        }

        public final boolean isRecyclable() {
            return (this.mFlags & 16) == 0 && !a.f.l.w.C(this.itemView);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean isRemoved() {
            return (this.mFlags & 8) != 0;
        }

        boolean isScrap() {
            return this.mScrapContainer != null;
        }

        boolean isTmpDetached() {
            return (this.mFlags & 256) != 0;
        }

        boolean isUpdated() {
            return (this.mFlags & 2) != 0;
        }

        boolean needsUpdate() {
            return (this.mFlags & 2) != 0;
        }

        void offsetPosition(int i, boolean z) {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
            if (this.mPreLayoutPosition == -1) {
                this.mPreLayoutPosition = this.mPosition;
            }
            if (z) {
                this.mPreLayoutPosition += i;
            }
            this.mPosition += i;
            if (this.itemView.getLayoutParams() != null) {
                ((p) this.itemView.getLayoutParams()).f1724c = true;
            }
        }

        void onEnteredHiddenState(RecyclerView recyclerView) {
            int i = this.mPendingAccessibilityState;
            if (i != -1) {
                this.mWasImportantForAccessibilityBeforeHidden = i;
            } else {
                this.mWasImportantForAccessibilityBeforeHidden = a.f.l.w.n(this.itemView);
            }
            recyclerView.setChildImportantForAccessibilityInternal(this, 4);
        }

        void onLeftHiddenState(RecyclerView recyclerView) {
            recyclerView.setChildImportantForAccessibilityInternal(this, this.mWasImportantForAccessibilityBeforeHidden);
            this.mWasImportantForAccessibilityBeforeHidden = 0;
        }

        void resetInternal() {
            this.mFlags = 0;
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1L;
            this.mPreLayoutPosition = -1;
            this.mIsRecyclableCount = 0;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            clearPayload();
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            this.mPendingAccessibilityState = -1;
            RecyclerView.clearNestedRecyclerViewIfNotNested(this);
        }

        void saveOldPosition() {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
        }

        void setFlags(int i, int i2) {
            this.mFlags = (i & i2) | (this.mFlags & (i2 ^ (-1)));
        }

        public final void setIsRecyclable(boolean z) {
            int i = this.mIsRecyclableCount;
            this.mIsRecyclableCount = z ? i - 1 : i + 1;
            int i2 = this.mIsRecyclableCount;
            if (i2 < 0) {
                this.mIsRecyclableCount = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
                return;
            }
            if (!z && i2 == 1) {
                this.mFlags |= 16;
            } else if (z && this.mIsRecyclableCount == 0) {
                this.mFlags &= -17;
            }
        }

        void setScrapContainer(v vVar, boolean z) {
            this.mScrapContainer = vVar;
            this.mInChangeScrap = z;
        }

        boolean shouldBeKeptAsChild() {
            return (this.mFlags & 16) != 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean shouldIgnore() {
            return (this.mFlags & 128) != 0;
        }

        void stopIgnoring() {
            this.mFlags &= -129;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder((getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName()) + "{" + Integer.toHexString(hashCode()) + " position=" + this.mPosition + " id=" + this.mItemId + ", oldPos=" + this.mOldPosition + ", pLpos:" + this.mPreLayoutPosition);
            if (isScrap()) {
                sb.append(" scrap ");
                sb.append(this.mInChangeScrap ? "[changeScrap]" : "[attachedScrap]");
            }
            if (isInvalid()) {
                sb.append(" invalid");
            }
            if (!isBound()) {
                sb.append(" unbound");
            }
            if (needsUpdate()) {
                sb.append(" update");
            }
            if (isRemoved()) {
                sb.append(" removed");
            }
            if (shouldIgnore()) {
                sb.append(" ignored");
            }
            if (isTmpDetached()) {
                sb.append(" tmpDetached");
            }
            if (!isRecyclable()) {
                sb.append(" not recyclable(" + this.mIsRecyclableCount + ")");
            }
            if (isAdapterPositionUnknown()) {
                sb.append(" undefined adapter position");
            }
            if (this.itemView.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        void unScrap() {
            this.mScrapContainer.c(this);
        }

        boolean wasReturnedFromScrap() {
            return (this.mFlags & 32) != 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements d.b {
        e() {
        }

        @Override // androidx.recyclerview.widget.d.b
        public int a() {
            return RecyclerView.this.getChildCount();
        }

        @Override // androidx.recyclerview.widget.d.b
        public void b() {
            int a2 = a();
            for (int i = 0; i < a2; i++) {
                View a3 = a(i);
                RecyclerView.this.dispatchChildDetached(a3);
                a3.clearAnimation();
            }
            RecyclerView.this.removeAllViews();
        }

        @Override // androidx.recyclerview.widget.d.b
        public void c(int i) {
            View childAt = RecyclerView.this.getChildAt(i);
            if (childAt != null) {
                RecyclerView.this.dispatchChildDetached(childAt);
                childAt.clearAnimation();
            }
            RecyclerView.this.removeViewAt(i);
        }

        @Override // androidx.recyclerview.widget.d.b
        public int d(View view) {
            return RecyclerView.this.indexOfChild(view);
        }

        @Override // androidx.recyclerview.widget.d.b
        public void a(View view, int i) {
            RecyclerView.this.addView(view, i);
            RecyclerView.this.dispatchChildAttached(view);
        }

        @Override // androidx.recyclerview.widget.d.b
        public View a(int i) {
            return RecyclerView.this.getChildAt(i);
        }

        @Override // androidx.recyclerview.widget.d.b
        public void a(View view, int i, ViewGroup.LayoutParams layoutParams) {
            d0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt != null) {
                if (!childViewHolderInt.isTmpDetached() && !childViewHolderInt.shouldIgnore()) {
                    throw new IllegalArgumentException("Called attach on a child which is not detached: " + childViewHolderInt + RecyclerView.this.exceptionLabel());
                }
                childViewHolderInt.clearTmpDetachFlag();
            }
            RecyclerView.this.attachViewToParent(view, i, layoutParams);
        }

        @Override // androidx.recyclerview.widget.d.b
        public void c(View view) {
            d0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt != null) {
                childViewHolderInt.onLeftHiddenState(RecyclerView.this);
            }
        }

        @Override // androidx.recyclerview.widget.d.b
        public d0 b(View view) {
            return RecyclerView.getChildViewHolderInt(view);
        }

        @Override // androidx.recyclerview.widget.d.b
        public void b(int i) {
            d0 childViewHolderInt;
            View a2 = a(i);
            if (a2 != null && (childViewHolderInt = RecyclerView.getChildViewHolderInt(a2)) != null) {
                if (childViewHolderInt.isTmpDetached() && !childViewHolderInt.shouldIgnore()) {
                    throw new IllegalArgumentException("called detach on an already detached child " + childViewHolderInt + RecyclerView.this.exceptionLabel());
                }
                childViewHolderInt.addFlags(256);
            }
            RecyclerView.this.detachViewFromParent(i);
        }

        @Override // androidx.recyclerview.widget.d.b
        public void a(View view) {
            d0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt != null) {
                childViewHolderInt.onEnteredHiddenState(RecyclerView.this);
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class g<VH extends d0> {
        private final h mObservable = new h();
        private boolean mHasStableIds = false;

        public final void bindViewHolder(VH vh, int i) {
            vh.mPosition = i;
            if (hasStableIds()) {
                vh.mItemId = getItemId(i);
            }
            vh.setFlags(1, 519);
            a.f.h.c.a(RecyclerView.TRACE_BIND_VIEW_TAG);
            onBindViewHolder(vh, i, vh.getUnmodifiedPayloads());
            vh.clearPayload();
            ViewGroup.LayoutParams layoutParams = vh.itemView.getLayoutParams();
            if (layoutParams instanceof p) {
                ((p) layoutParams).f1724c = true;
            }
            a.f.h.c.a();
        }

        public final VH createViewHolder(ViewGroup viewGroup, int i) {
            try {
                a.f.h.c.a(RecyclerView.TRACE_CREATE_VIEW_TAG);
                VH onCreateViewHolder = onCreateViewHolder(viewGroup, i);
                if (onCreateViewHolder.itemView.getParent() == null) {
                    onCreateViewHolder.mItemViewType = i;
                    return onCreateViewHolder;
                }
                throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
            } finally {
                a.f.h.c.a();
            }
        }

        public abstract int getItemCount();

        public long getItemId(int i) {
            return -1L;
        }

        public int getItemViewType(int i) {
            return 0;
        }

        public final boolean hasObservers() {
            return this.mObservable.a();
        }

        public final boolean hasStableIds() {
            return this.mHasStableIds;
        }

        public final void notifyDataSetChanged() {
            this.mObservable.b();
        }

        public final void notifyItemChanged(int i) {
            this.mObservable.b(i, 1);
        }

        public final void notifyItemInserted(int i) {
            this.mObservable.c(i, 1);
        }

        public final void notifyItemMoved(int i, int i2) {
            this.mObservable.a(i, i2);
        }

        public final void notifyItemRangeChanged(int i, int i2) {
            this.mObservable.b(i, i2);
        }

        public final void notifyItemRangeInserted(int i, int i2) {
            this.mObservable.c(i, i2);
        }

        public final void notifyItemRangeRemoved(int i, int i2) {
            this.mObservable.d(i, i2);
        }

        public final void notifyItemRemoved(int i) {
            this.mObservable.d(i, 1);
        }

        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        }

        public abstract void onBindViewHolder(VH vh, int i);

        public void onBindViewHolder(VH vh, int i, List<Object> list) {
            onBindViewHolder(vh, i);
        }

        public abstract VH onCreateViewHolder(ViewGroup viewGroup, int i);

        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        }

        public boolean onFailedToRecycleView(VH vh) {
            return false;
        }

        public void onViewAttachedToWindow(VH vh) {
        }

        public void onViewDetachedFromWindow(VH vh) {
        }

        public void onViewRecycled(VH vh) {
        }

        public void registerAdapterDataObserver(i iVar) {
            this.mObservable.registerObserver(iVar);
        }

        public void setHasStableIds(boolean z) {
            if (!hasObservers()) {
                this.mHasStableIds = z;
                return;
            }
            throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
        }

        public void unregisterAdapterDataObserver(i iVar) {
            this.mObservable.unregisterObserver(iVar);
        }

        public final void notifyItemChanged(int i, Object obj) {
            this.mObservable.a(i, 1, obj);
        }

        public final void notifyItemRangeChanged(int i, int i2, Object obj) {
            this.mObservable.a(i, i2, obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class h extends Observable<i> {
        h() {
        }

        public boolean a() {
            return !((Observable) this).mObservers.isEmpty();
        }

        public void b() {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).a();
            }
        }

        public void c(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).b(i, i2);
            }
        }

        public void d(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).c(i, i2);
            }
        }

        public void a(int i, int i2, Object obj) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).a(i, i2, obj);
            }
        }

        public void b(int i, int i2) {
            a(i, i2, null);
        }

        public void a(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).a(i, i2, 1);
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class i {
        public void a() {
        }

        public void a(int i, int i2) {
        }

        public void a(int i, int i2, int i3) {
        }

        public void a(int i, int i2, Object obj) {
            a(i, i2);
        }

        public void b(int i, int i2) {
        }

        public void c(int i, int i2) {
        }
    }

    /* loaded from: classes.dex */
    public interface j {
        int a(int i, int i2);
    }

    /* loaded from: classes.dex */
    public static class k {
        protected EdgeEffect a(RecyclerView recyclerView, int i) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    /* loaded from: classes.dex */
    public static abstract class l {

        /* renamed from: a, reason: collision with root package name */
        private b f1701a = null;

        /* renamed from: b, reason: collision with root package name */
        private ArrayList<a> f1702b = new ArrayList<>();

        /* renamed from: c, reason: collision with root package name */
        private long f1703c = 120;

        /* renamed from: d, reason: collision with root package name */
        private long f1704d = 120;

        /* renamed from: e, reason: collision with root package name */
        private long f1705e = 250;

        /* renamed from: f, reason: collision with root package name */
        private long f1706f = 250;

        /* loaded from: classes.dex */
        public interface a {
            void a();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public interface b {
            void a(d0 d0Var);
        }

        /* loaded from: classes.dex */
        public static class c {

            /* renamed from: a, reason: collision with root package name */
            public int f1707a;

            /* renamed from: b, reason: collision with root package name */
            public int f1708b;

            public c a(d0 d0Var) {
                a(d0Var, 0);
                return this;
            }

            public c a(d0 d0Var, int i) {
                View view = d0Var.itemView;
                this.f1707a = view.getLeft();
                this.f1708b = view.getTop();
                view.getRight();
                view.getBottom();
                return this;
            }
        }

        public void a(long j) {
            this.f1703c = j;
        }

        public abstract boolean a(d0 d0Var);

        public abstract boolean a(d0 d0Var, d0 d0Var2, c cVar, c cVar2);

        public abstract boolean a(d0 d0Var, c cVar, c cVar2);

        public abstract void b();

        public final void b(d0 d0Var) {
            d(d0Var);
            b bVar = this.f1701a;
            if (bVar != null) {
                bVar.a(d0Var);
            }
        }

        public abstract boolean b(d0 d0Var, c cVar, c cVar2);

        public long c() {
            return this.f1703c;
        }

        public abstract void c(d0 d0Var);

        public abstract boolean c(d0 d0Var, c cVar, c cVar2);

        public long d() {
            return this.f1706f;
        }

        public void d(d0 d0Var) {
        }

        public long e() {
            return this.f1705e;
        }

        public long f() {
            return this.f1704d;
        }

        public abstract boolean g();

        public c h() {
            return new c();
        }

        public abstract void i();

        static int e(d0 d0Var) {
            int i = d0Var.mFlags & 14;
            if (d0Var.isInvalid()) {
                return 4;
            }
            if ((i & 4) != 0) {
                return i;
            }
            int oldPosition = d0Var.getOldPosition();
            int adapterPosition = d0Var.getAdapterPosition();
            return (oldPosition == -1 || adapterPosition == -1 || oldPosition == adapterPosition) ? i : i | DfuBaseService.ERROR_REMOTE_TYPE_SECURE_BUTTONLESS;
        }

        void a(b bVar) {
            this.f1701a = bVar;
        }

        public c a(a0 a0Var, d0 d0Var, int i, List<Object> list) {
            c h = h();
            h.a(d0Var);
            return h;
        }

        public c a(a0 a0Var, d0 d0Var) {
            c h = h();
            h.a(d0Var);
            return h;
        }

        public final boolean a(a aVar) {
            boolean g = g();
            if (aVar != null) {
                if (!g) {
                    aVar.a();
                } else {
                    this.f1702b.add(aVar);
                }
            }
            return g;
        }

        public boolean a(d0 d0Var, List<Object> list) {
            return a(d0Var);
        }

        public final void a() {
            int size = this.f1702b.size();
            for (int i = 0; i < size; i++) {
                this.f1702b.get(i).a();
            }
            this.f1702b.clear();
        }
    }

    /* loaded from: classes.dex */
    private class m implements l.b {
        m() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l.b
        public void a(d0 d0Var) {
            d0Var.setIsRecyclable(true);
            if (d0Var.mShadowedHolder != null && d0Var.mShadowingHolder == null) {
                d0Var.mShadowedHolder = null;
            }
            d0Var.mShadowingHolder = null;
            if (d0Var.shouldBeKeptAsChild() || RecyclerView.this.removeAnimatingView(d0Var.itemView) || !d0Var.isTmpDetached()) {
                return;
            }
            RecyclerView.this.removeDetachedView(d0Var.itemView, false);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class n {
        @Deprecated
        public void a(Canvas canvas, RecyclerView recyclerView) {
        }

        public void a(Canvas canvas, RecyclerView recyclerView, a0 a0Var) {
            a(canvas, recyclerView);
        }

        @Deprecated
        public void b(Canvas canvas, RecyclerView recyclerView) {
        }

        public void b(Canvas canvas, RecyclerView recyclerView, a0 a0Var) {
            b(canvas, recyclerView);
        }

        @Deprecated
        public void a(Rect rect, int i, RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
        }

        public void a(Rect rect, View view, RecyclerView recyclerView, a0 a0Var) {
            a(rect, ((p) view.getLayoutParams()).a(), recyclerView);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class o {

        /* renamed from: a, reason: collision with root package name */
        androidx.recyclerview.widget.d f1710a;

        /* renamed from: b, reason: collision with root package name */
        RecyclerView f1711b;
        z g;
        int m;
        boolean n;
        private int o;
        private int p;
        private int q;
        private int r;

        /* renamed from: c, reason: collision with root package name */
        private final w.b f1712c = new a();

        /* renamed from: d, reason: collision with root package name */
        private final w.b f1713d = new b();

        /* renamed from: e, reason: collision with root package name */
        androidx.recyclerview.widget.w f1714e = new androidx.recyclerview.widget.w(this.f1712c);

        /* renamed from: f, reason: collision with root package name */
        androidx.recyclerview.widget.w f1715f = new androidx.recyclerview.widget.w(this.f1713d);
        boolean h = false;
        boolean i = false;
        boolean j = false;
        private boolean k = true;
        private boolean l = true;

        /* loaded from: classes.dex */
        class a implements w.b {
            a() {
            }

            @Override // androidx.recyclerview.widget.w.b
            public View a(int i) {
                return o.this.d(i);
            }

            @Override // androidx.recyclerview.widget.w.b
            public int b() {
                return o.this.o();
            }

            @Override // androidx.recyclerview.widget.w.b
            public int a() {
                return o.this.r() - o.this.p();
            }

            @Override // androidx.recyclerview.widget.w.b
            public int b(View view) {
                return o.this.i(view) + ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).rightMargin;
            }

            @Override // androidx.recyclerview.widget.w.b
            public int a(View view) {
                return o.this.f(view) - ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).leftMargin;
            }
        }

        /* loaded from: classes.dex */
        class b implements w.b {
            b() {
            }

            @Override // androidx.recyclerview.widget.w.b
            public View a(int i) {
                return o.this.d(i);
            }

            @Override // androidx.recyclerview.widget.w.b
            public int b() {
                return o.this.q();
            }

            @Override // androidx.recyclerview.widget.w.b
            public int a() {
                return o.this.h() - o.this.n();
            }

            @Override // androidx.recyclerview.widget.w.b
            public int b(View view) {
                return o.this.e(view) + ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).bottomMargin;
            }

            @Override // androidx.recyclerview.widget.w.b
            public int a(View view) {
                return o.this.j(view) - ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).topMargin;
            }
        }

        /* loaded from: classes.dex */
        public interface c {
            void a(int i, int i2);
        }

        /* loaded from: classes.dex */
        public static class d {

            /* renamed from: a, reason: collision with root package name */
            public int f1718a;

            /* renamed from: b, reason: collision with root package name */
            public int f1719b;

            /* renamed from: c, reason: collision with root package name */
            public boolean f1720c;

            /* renamed from: d, reason: collision with root package name */
            public boolean f1721d;
        }

        public void A() {
            this.h = true;
        }

        boolean B() {
            return false;
        }

        void C() {
            z zVar = this.g;
            if (zVar != null) {
                zVar.h();
            }
        }

        public boolean D() {
            return false;
        }

        public int a(int i, v vVar, a0 a0Var) {
            return 0;
        }

        public int a(a0 a0Var) {
            return 0;
        }

        public View a(View view, int i, v vVar, a0 a0Var) {
            return null;
        }

        public void a(int i, int i2, a0 a0Var, c cVar) {
        }

        public void a(int i, c cVar) {
        }

        public void a(Rect rect, int i, int i2) {
            c(a(i, rect.width() + o() + p(), m()), a(i2, rect.height() + q() + n(), l()));
        }

        public void a(Parcelable parcelable) {
        }

        public void a(g gVar, g gVar2) {
        }

        public void a(RecyclerView recyclerView, int i, int i2) {
        }

        public void a(RecyclerView recyclerView, int i, int i2, int i3) {
        }

        public boolean a() {
            return false;
        }

        public boolean a(p pVar) {
            return pVar != null;
        }

        public boolean a(v vVar, a0 a0Var, View view, int i, Bundle bundle) {
            return false;
        }

        public boolean a(RecyclerView recyclerView, ArrayList<View> arrayList, int i, int i2) {
            return false;
        }

        public int b(int i, v vVar, a0 a0Var) {
            return 0;
        }

        public int b(a0 a0Var) {
            return 0;
        }

        void b(int i, int i2) {
            this.q = View.MeasureSpec.getSize(i);
            this.o = View.MeasureSpec.getMode(i);
            if (this.o == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.q = 0;
            }
            this.r = View.MeasureSpec.getSize(i2);
            this.p = View.MeasureSpec.getMode(i2);
            if (this.p != 0 || RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                return;
            }
            this.r = 0;
        }

        public void b(RecyclerView recyclerView) {
        }

        public void b(RecyclerView recyclerView, int i, int i2) {
        }

        public boolean b() {
            return false;
        }

        public int c(a0 a0Var) {
            return 0;
        }

        public int c(v vVar, a0 a0Var) {
            return 0;
        }

        public View c(View view) {
            View findContainingItemView;
            RecyclerView recyclerView = this.f1711b;
            if (recyclerView == null || (findContainingItemView = recyclerView.findContainingItemView(view)) == null || this.f1710a.c(findContainingItemView)) {
                return null;
            }
            return findContainingItemView;
        }

        public abstract p c();

        @Deprecated
        public void c(RecyclerView recyclerView) {
        }

        public void c(RecyclerView recyclerView, int i, int i2) {
        }

        public int d() {
            return -1;
        }

        public int d(a0 a0Var) {
            return 0;
        }

        public View d(View view, int i) {
            return null;
        }

        void d(int i, int i2) {
            int e2 = e();
            if (e2 == 0) {
                this.f1711b.defaultOnMeasure(i, i2);
                return;
            }
            int i3 = Preference.DEFAULT_ORDER;
            int i4 = Preference.DEFAULT_ORDER;
            int i5 = RecyclerView.UNDEFINED_DURATION;
            int i6 = RecyclerView.UNDEFINED_DURATION;
            for (int i7 = 0; i7 < e2; i7++) {
                View d2 = d(i7);
                Rect rect = this.f1711b.mTempRect;
                b(d2, rect);
                int i8 = rect.left;
                if (i8 < i3) {
                    i3 = i8;
                }
                int i9 = rect.right;
                if (i9 > i5) {
                    i5 = i9;
                }
                int i10 = rect.top;
                if (i10 < i4) {
                    i4 = i10;
                }
                int i11 = rect.bottom;
                if (i11 > i6) {
                    i6 = i11;
                }
            }
            this.f1711b.mTempRect.set(i3, i4, i5, i6);
            a(this.f1711b.mTempRect, i, i2);
        }

        public void d(RecyclerView recyclerView) {
        }

        public boolean d(v vVar, a0 a0Var) {
            return false;
        }

        public int e(a0 a0Var) {
            return 0;
        }

        public void e(v vVar, a0 a0Var) {
            Log.e(RecyclerView.TAG, "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public int f(a0 a0Var) {
            return 0;
        }

        void f(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.f1711b = null;
                this.f1710a = null;
                this.q = 0;
                this.r = 0;
            } else {
                this.f1711b = recyclerView;
                this.f1710a = recyclerView.mChildHelper;
                this.q = recyclerView.getWidth();
                this.r = recyclerView.getHeight();
            }
            this.o = 1073741824;
            this.p = 1073741824;
        }

        public View g() {
            View focusedChild;
            RecyclerView recyclerView = this.f1711b;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.f1710a.c(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public void g(int i) {
        }

        public void g(a0 a0Var) {
        }

        public void h(int i) {
            if (d(i) != null) {
                this.f1710a.e(i);
            }
        }

        public int i() {
            return this.p;
        }

        public void i(int i) {
        }

        public int j() {
            RecyclerView recyclerView = this.f1711b;
            g adapter = recyclerView != null ? recyclerView.getAdapter() : null;
            if (adapter != null) {
                return adapter.getItemCount();
            }
            return 0;
        }

        public int k() {
            return a.f.l.w.q(this.f1711b);
        }

        public int l(View view) {
            return ((p) view.getLayoutParams()).a();
        }

        public int m(View view) {
            return ((p) view.getLayoutParams()).f1723b.right;
        }

        public int n() {
            RecyclerView recyclerView = this.f1711b;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        public void o(View view) {
            this.f1710a.d(view);
        }

        public int p() {
            RecyclerView recyclerView = this.f1711b;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        public int q() {
            RecyclerView recyclerView = this.f1711b;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        public int r() {
            return this.q;
        }

        public int s() {
            return this.o;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean t() {
            int e2 = e();
            for (int i = 0; i < e2; i++) {
                ViewGroup.LayoutParams layoutParams = d(i).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }

        public boolean u() {
            return this.i;
        }

        public boolean v() {
            return this.j;
        }

        public final boolean w() {
            return this.l;
        }

        public boolean x() {
            z zVar = this.g;
            return zVar != null && zVar.e();
        }

        public Parcelable y() {
            return null;
        }

        public void z() {
            RecyclerView recyclerView = this.f1711b;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public int e() {
            androidx.recyclerview.widget.d dVar = this.f1710a;
            if (dVar != null) {
                return dVar.a();
            }
            return 0;
        }

        public int i(View view) {
            return view.getRight() + m(view);
        }

        public int k(View view) {
            return ((p) view.getLayoutParams()).f1723b.left;
        }

        public int l() {
            return a.f.l.w.r(this.f1711b);
        }

        public int m() {
            return a.f.l.w.s(this.f1711b);
        }

        public int n(View view) {
            return ((p) view.getLayoutParams()).f1723b.top;
        }

        public int o() {
            RecyclerView recyclerView = this.f1711b;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        public void e(int i) {
            RecyclerView recyclerView = this.f1711b;
            if (recyclerView != null) {
                recyclerView.offsetChildrenHorizontal(i);
            }
        }

        public int h() {
            return this.r;
        }

        public int j(View view) {
            return view.getTop() - n(view);
        }

        public View c(int i) {
            int e2 = e();
            for (int i2 = 0; i2 < e2; i2++) {
                View d2 = d(i2);
                d0 childViewHolderInt = RecyclerView.getChildViewHolderInt(d2);
                if (childViewHolderInt != null && childViewHolderInt.getLayoutPosition() == i && !childViewHolderInt.shouldIgnore() && (this.f1711b.mState.d() || !childViewHolderInt.isRemoved())) {
                    return d2;
                }
            }
            return null;
        }

        public int g(View view) {
            Rect rect = ((p) view.getLayoutParams()).f1723b;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public int h(View view) {
            Rect rect = ((p) view.getLayoutParams()).f1723b;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public int e(View view) {
            return view.getBottom() + d(view);
        }

        public static int a(int i, int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            if (mode != Integer.MIN_VALUE) {
                return mode != 1073741824 ? Math.max(i2, i3) : size;
            }
            return Math.min(size, Math.max(i2, i3));
        }

        void e(RecyclerView recyclerView) {
            b(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }

        public void b(RecyclerView recyclerView, v vVar) {
            c(recyclerView);
        }

        public void c(View view, int i) {
            a(view, i, (p) view.getLayoutParams());
        }

        public void a(String str) {
            RecyclerView recyclerView = this.f1711b;
            if (recyclerView != null) {
                recyclerView.assertNotInLayoutOrScroll(str);
            }
        }

        public void b(z zVar) {
            z zVar2 = this.g;
            if (zVar2 != null && zVar != zVar2 && zVar2.e()) {
                this.g.h();
            }
            this.g = zVar;
            this.g.a(this.f1711b, this);
        }

        void c(v vVar) {
            int e2 = vVar.e();
            for (int i = e2 - 1; i >= 0; i--) {
                View c2 = vVar.c(i);
                d0 childViewHolderInt = RecyclerView.getChildViewHolderInt(c2);
                if (!childViewHolderInt.shouldIgnore()) {
                    childViewHolderInt.setIsRecyclable(false);
                    if (childViewHolderInt.isTmpDetached()) {
                        this.f1711b.removeDetachedView(c2, false);
                    }
                    l lVar = this.f1711b.mItemAnimator;
                    if (lVar != null) {
                        lVar.c(childViewHolderInt);
                    }
                    childViewHolderInt.setIsRecyclable(true);
                    vVar.a(c2);
                }
            }
            vVar.c();
            if (e2 > 0) {
                this.f1711b.invalidate();
            }
        }

        public boolean f() {
            RecyclerView recyclerView = this.f1711b;
            return recyclerView != null && recyclerView.mClipToPadding;
        }

        void a(RecyclerView recyclerView) {
            this.i = true;
            b(recyclerView);
        }

        public View d(int i) {
            androidx.recyclerview.widget.d dVar = this.f1710a;
            if (dVar != null) {
                return dVar.c(i);
            }
            return null;
        }

        public void f(int i) {
            RecyclerView recyclerView = this.f1711b;
            if (recyclerView != null) {
                recyclerView.offsetChildrenVertical(i);
            }
        }

        public int d(View view) {
            return ((p) view.getLayoutParams()).f1723b.bottom;
        }

        private boolean d(RecyclerView recyclerView, int i, int i2) {
            View focusedChild = recyclerView.getFocusedChild();
            if (focusedChild == null) {
                return false;
            }
            int o = o();
            int q = q();
            int r = r() - p();
            int h = h() - n();
            Rect rect = this.f1711b.mTempRect;
            b(focusedChild, rect);
            return rect.left - i < r && rect.right - i > o && rect.top - i2 < h && rect.bottom - i2 > q;
        }

        void a(RecyclerView recyclerView, v vVar) {
            this.i = false;
            b(recyclerView, vVar);
        }

        public int f(View view) {
            return view.getLeft() - k(view);
        }

        public void b(View view) {
            b(view, -1);
        }

        public boolean a(Runnable runnable) {
            RecyclerView recyclerView = this.f1711b;
            if (recyclerView != null) {
                return recyclerView.removeCallbacks(runnable);
            }
            return false;
        }

        public void b(View view, int i) {
            a(view, i, false);
        }

        public void b(int i) {
            a(i, d(i));
        }

        public p a(ViewGroup.LayoutParams layoutParams) {
            if (layoutParams instanceof p) {
                return new p((p) layoutParams);
            }
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                return new p((ViewGroup.MarginLayoutParams) layoutParams);
            }
            return new p(layoutParams);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean b(View view, int i, int i2, p pVar) {
            return (this.k && b(view.getMeasuredWidth(), i, ((ViewGroup.MarginLayoutParams) pVar).width) && b(view.getMeasuredHeight(), i2, ((ViewGroup.MarginLayoutParams) pVar).height)) ? false : true;
        }

        private static boolean b(int i, int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            if (i3 > 0 && i != i3) {
                return false;
            }
            if (mode == Integer.MIN_VALUE) {
                return size >= i;
            }
            if (mode != 0) {
                return mode == 1073741824 && size == i;
            }
            return true;
        }

        private int[] c(View view, Rect rect) {
            int[] iArr = new int[2];
            int o = o();
            int q = q();
            int r = r() - p();
            int h = h() - n();
            int left = (view.getLeft() + rect.left) - view.getScrollX();
            int top = (view.getTop() + rect.top) - view.getScrollY();
            int width = rect.width() + left;
            int height = rect.height() + top;
            int i = left - o;
            int min = Math.min(0, i);
            int i2 = top - q;
            int min2 = Math.min(0, i2);
            int i3 = width - r;
            int max = Math.max(0, i3);
            int max2 = Math.max(0, height - h);
            if (k() != 1) {
                if (min == 0) {
                    min = Math.min(i, max);
                }
                max = min;
            } else if (max == 0) {
                max = Math.max(min, i3);
            }
            if (min2 == 0) {
                min2 = Math.min(i2, max2);
            }
            iArr[0] = max;
            iArr[1] = min2;
            return iArr;
        }

        public p a(Context context, AttributeSet attributeSet) {
            return new p(context, attributeSet);
        }

        public void b(View view, Rect rect) {
            RecyclerView.getDecoratedBoundsWithMarginsInt(view, rect);
        }

        public void a(RecyclerView recyclerView, a0 a0Var, int i) {
            Log.e(RecyclerView.TAG, "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public void b(v vVar) {
            for (int e2 = e() - 1; e2 >= 0; e2--) {
                if (!RecyclerView.getChildViewHolderInt(d(e2)).shouldIgnore()) {
                    a(e2, vVar);
                }
            }
        }

        public void a(View view) {
            a(view, -1);
        }

        public void a(View view, int i) {
            a(view, i, true);
        }

        private void a(View view, int i, boolean z) {
            d0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (!z && !childViewHolderInt.isRemoved()) {
                this.f1711b.mViewInfoStore.g(childViewHolderInt);
            } else {
                this.f1711b.mViewInfoStore.a(childViewHolderInt);
            }
            p pVar = (p) view.getLayoutParams();
            if (!childViewHolderInt.wasReturnedFromScrap() && !childViewHolderInt.isScrap()) {
                if (view.getParent() == this.f1711b) {
                    int b2 = this.f1710a.b(view);
                    if (i == -1) {
                        i = this.f1710a.a();
                    }
                    if (b2 == -1) {
                        throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.f1711b.indexOfChild(view) + this.f1711b.exceptionLabel());
                    }
                    if (b2 != i) {
                        this.f1711b.mLayout.a(b2, i);
                    }
                } else {
                    this.f1710a.a(view, i, false);
                    pVar.f1724c = true;
                    z zVar = this.g;
                    if (zVar != null && zVar.e()) {
                        this.g.b(view);
                    }
                }
            } else {
                if (childViewHolderInt.isScrap()) {
                    childViewHolderInt.unScrap();
                } else {
                    childViewHolderInt.clearReturnedFromScrapFlag();
                }
                this.f1710a.a(view, i, view.getLayoutParams(), false);
            }
            if (pVar.f1725d) {
                childViewHolderInt.itemView.invalidate();
                pVar.f1725d = false;
            }
        }

        public int b(v vVar, a0 a0Var) {
            RecyclerView recyclerView = this.f1711b;
            if (recyclerView == null || recyclerView.mAdapter == null || !b()) {
                return 1;
            }
            return this.f1711b.mAdapter.getItemCount();
        }

        public void c(int i, int i2) {
            this.f1711b.setMeasuredDimension(i, i2);
        }

        private void a(int i, View view) {
            this.f1710a.a(i);
        }

        public void a(View view, int i, p pVar) {
            d0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.isRemoved()) {
                this.f1711b.mViewInfoStore.a(childViewHolderInt);
            } else {
                this.f1711b.mViewInfoStore.g(childViewHolderInt);
            }
            this.f1710a.a(view, i, pVar, childViewHolderInt.isRemoved());
        }

        public void a(int i, int i2) {
            View d2 = d(i);
            if (d2 != null) {
                b(i);
                c(d2, i2);
            } else {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i + this.f1711b.toString());
            }
        }

        public void a(View view, v vVar) {
            o(view);
            vVar.b(view);
        }

        public void a(int i, v vVar) {
            View d2 = d(i);
            h(i);
            vVar.b(d2);
        }

        public void a(v vVar) {
            for (int e2 = e() - 1; e2 >= 0; e2--) {
                a(vVar, e2, d(e2));
            }
        }

        private void a(v vVar, int i, View view) {
            d0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.shouldIgnore()) {
                return;
            }
            if (childViewHolderInt.isInvalid() && !childViewHolderInt.isRemoved() && !this.f1711b.mAdapter.hasStableIds()) {
                h(i);
                vVar.b(childViewHolderInt);
            } else {
                b(i);
                vVar.c(view);
                this.f1711b.mViewInfoStore.d(childViewHolderInt);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean a(View view, int i, int i2, p pVar) {
            return (!view.isLayoutRequested() && this.k && b(view.getWidth(), i, ((ViewGroup.MarginLayoutParams) pVar).width) && b(view.getHeight(), i2, ((ViewGroup.MarginLayoutParams) pVar).height)) ? false : true;
        }

        public void a(View view, int i, int i2) {
            p pVar = (p) view.getLayoutParams();
            Rect itemDecorInsetsForChild = this.f1711b.getItemDecorInsetsForChild(view);
            int i3 = i + itemDecorInsetsForChild.left + itemDecorInsetsForChild.right;
            int i4 = i2 + itemDecorInsetsForChild.top + itemDecorInsetsForChild.bottom;
            int a2 = a(r(), s(), o() + p() + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin + i3, ((ViewGroup.MarginLayoutParams) pVar).width, a());
            int a3 = a(h(), i(), q() + n() + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin + i4, ((ViewGroup.MarginLayoutParams) pVar).height, b());
            if (a(view, a2, a3, pVar)) {
                view.measure(a2, a3);
            }
        }

        public static int a(int i, int i2, int i3, int i4, boolean z) {
            int i5;
            int i6 = i - i3;
            int i7 = 0;
            int max = Math.max(0, i6);
            if (z) {
                if (i4 < 0) {
                    if (i4 == -1) {
                        if (i2 == Integer.MIN_VALUE || (i2 != 0 && i2 == 1073741824)) {
                            i5 = max;
                        } else {
                            i2 = 0;
                            i5 = 0;
                        }
                        i7 = i2;
                        max = i5;
                    }
                    max = 0;
                }
                max = i4;
                i7 = 1073741824;
            } else {
                if (i4 < 0) {
                    if (i4 == -1) {
                        i7 = i2;
                    } else {
                        if (i4 == -2) {
                            if (i2 == Integer.MIN_VALUE || i2 == 1073741824) {
                                i7 = RecyclerView.UNDEFINED_DURATION;
                            }
                        }
                        max = 0;
                    }
                }
                max = i4;
                i7 = 1073741824;
            }
            return View.MeasureSpec.makeMeasureSpec(max, i7);
        }

        public void a(View view, int i, int i2, int i3, int i4) {
            p pVar = (p) view.getLayoutParams();
            Rect rect = pVar.f1723b;
            view.layout(i + rect.left + ((ViewGroup.MarginLayoutParams) pVar).leftMargin, i2 + rect.top + ((ViewGroup.MarginLayoutParams) pVar).topMargin, (i3 - rect.right) - ((ViewGroup.MarginLayoutParams) pVar).rightMargin, (i4 - rect.bottom) - ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
        }

        public void a(View view, boolean z, Rect rect) {
            Matrix matrix;
            if (z) {
                Rect rect2 = ((p) view.getLayoutParams()).f1723b;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.f1711b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
                RectF rectF = this.f1711b.mTempRectF;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public void a(View view, Rect rect) {
            RecyclerView recyclerView = this.f1711b;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.getItemDecorInsetsForChild(view));
            }
        }

        public boolean a(RecyclerView recyclerView, View view, Rect rect, boolean z) {
            return a(recyclerView, view, rect, z, false);
        }

        public boolean a(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
            int[] c2 = c(view, rect);
            int i = c2[0];
            int i2 = c2[1];
            if ((z2 && !d(recyclerView, i, i2)) || (i == 0 && i2 == 0)) {
                return false;
            }
            if (z) {
                recyclerView.scrollBy(i, i2);
            } else {
                recyclerView.smoothScrollBy(i, i2);
            }
            return true;
        }

        public boolean a(View view, boolean z, boolean z2) {
            boolean z3 = this.f1714e.a(view, 24579) && this.f1715f.a(view, 24579);
            return z ? z3 : !z3;
        }

        @Deprecated
        public boolean a(RecyclerView recyclerView, View view, View view2) {
            return x() || recyclerView.isComputingLayout();
        }

        public boolean a(RecyclerView recyclerView, a0 a0Var, View view, View view2) {
            return a(recyclerView, view, view2);
        }

        public void a(RecyclerView recyclerView, int i, int i2, Object obj) {
            c(recyclerView, i, i2);
        }

        public void a(v vVar, a0 a0Var, int i, int i2) {
            this.f1711b.defaultOnMeasure(i, i2);
        }

        void a(z zVar) {
            if (this.g == zVar) {
                this.g = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a(a.f.l.f0.c cVar) {
            RecyclerView recyclerView = this.f1711b;
            a(recyclerView.mRecycler, recyclerView.mState, cVar);
        }

        public void a(v vVar, a0 a0Var, a.f.l.f0.c cVar) {
            if (this.f1711b.canScrollVertically(-1) || this.f1711b.canScrollHorizontally(-1)) {
                cVar.a(DfuBaseService.ERROR_REMOTE_MASK);
                cVar.m(true);
            }
            if (this.f1711b.canScrollVertically(1) || this.f1711b.canScrollHorizontally(1)) {
                cVar.a(4096);
                cVar.m(true);
            }
            cVar.a(c.b.a(b(vVar, a0Var), a(vVar, a0Var), d(vVar, a0Var), c(vVar, a0Var)));
        }

        public void a(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f1711b;
            a(recyclerView.mRecycler, recyclerView.mState, accessibilityEvent);
        }

        public void a(v vVar, a0 a0Var, AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f1711b;
            if (recyclerView == null || accessibilityEvent == null) {
                return;
            }
            boolean z = true;
            if (!recyclerView.canScrollVertically(1) && !this.f1711b.canScrollVertically(-1) && !this.f1711b.canScrollHorizontally(-1) && !this.f1711b.canScrollHorizontally(1)) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            g gVar = this.f1711b.mAdapter;
            if (gVar != null) {
                accessibilityEvent.setItemCount(gVar.getItemCount());
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a(View view, a.f.l.f0.c cVar) {
            d0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt == null || childViewHolderInt.isRemoved() || this.f1710a.c(childViewHolderInt.itemView)) {
                return;
            }
            RecyclerView recyclerView = this.f1711b;
            a(recyclerView.mRecycler, recyclerView.mState, view, cVar);
        }

        public void a(v vVar, a0 a0Var, View view, a.f.l.f0.c cVar) {
            cVar.b(c.C0018c.a(b() ? l(view) : 0, 1, a() ? l(view) : 0, 1, false, false));
        }

        public int a(v vVar, a0 a0Var) {
            RecyclerView recyclerView = this.f1711b;
            if (recyclerView == null || recyclerView.mAdapter == null || !a()) {
                return 1;
            }
            return this.f1711b.mAdapter.getItemCount();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean a(int i, Bundle bundle) {
            RecyclerView recyclerView = this.f1711b;
            return a(recyclerView.mRecycler, recyclerView.mState, i, bundle);
        }

        public boolean a(v vVar, a0 a0Var, int i, Bundle bundle) {
            int h;
            int r;
            int i2;
            int i3;
            RecyclerView recyclerView = this.f1711b;
            if (recyclerView == null) {
                return false;
            }
            if (i == 4096) {
                h = recyclerView.canScrollVertically(1) ? (h() - q()) - n() : 0;
                if (this.f1711b.canScrollHorizontally(1)) {
                    r = (r() - o()) - p();
                    i2 = h;
                    i3 = r;
                }
                i2 = h;
                i3 = 0;
            } else if (i != 8192) {
                i3 = 0;
                i2 = 0;
            } else {
                h = recyclerView.canScrollVertically(-1) ? -((h() - q()) - n()) : 0;
                if (this.f1711b.canScrollHorizontally(-1)) {
                    r = -((r() - o()) - p());
                    i2 = h;
                    i3 = r;
                }
                i2 = h;
                i3 = 0;
            }
            if (i2 == 0 && i3 == 0) {
                return false;
            }
            this.f1711b.smoothScrollBy(i3, i2, null, RecyclerView.UNDEFINED_DURATION, true);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean a(View view, int i, Bundle bundle) {
            RecyclerView recyclerView = this.f1711b;
            return a(recyclerView.mRecycler, recyclerView.mState, view, i, bundle);
        }

        public static d a(Context context, AttributeSet attributeSet, int i, int i2) {
            d dVar = new d();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.m.d.RecyclerView, i, i2);
            dVar.f1718a = obtainStyledAttributes.getInt(a.m.d.RecyclerView_android_orientation, 1);
            dVar.f1719b = obtainStyledAttributes.getInt(a.m.d.RecyclerView_spanCount, 1);
            dVar.f1720c = obtainStyledAttributes.getBoolean(a.m.d.RecyclerView_reverseLayout, false);
            dVar.f1721d = obtainStyledAttributes.getBoolean(a.m.d.RecyclerView_stackFromEnd, false);
            obtainStyledAttributes.recycle();
            return dVar;
        }
    }

    /* loaded from: classes.dex */
    public interface q {
        void a(View view);

        void b(View view);
    }

    /* loaded from: classes.dex */
    public static abstract class r {
        public abstract boolean a(int i, int i2);
    }

    /* loaded from: classes.dex */
    public interface s {
        boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent);

        void onRequestDisallowInterceptTouchEvent(boolean z);

        void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent);
    }

    /* loaded from: classes.dex */
    public static abstract class t {
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        }
    }

    /* loaded from: classes.dex */
    public final class v {

        /* renamed from: a, reason: collision with root package name */
        final ArrayList<d0> f1732a = new ArrayList<>();

        /* renamed from: b, reason: collision with root package name */
        ArrayList<d0> f1733b = null;

        /* renamed from: c, reason: collision with root package name */
        final ArrayList<d0> f1734c = new ArrayList<>();

        /* renamed from: d, reason: collision with root package name */
        private final List<d0> f1735d = Collections.unmodifiableList(this.f1732a);

        /* renamed from: e, reason: collision with root package name */
        private int f1736e = 2;

        /* renamed from: f, reason: collision with root package name */
        int f1737f = 2;
        u g;
        private b0 h;

        public v() {
        }

        private void e(d0 d0Var) {
            if (RecyclerView.this.isAccessibilityEnabled()) {
                View view = d0Var.itemView;
                if (a.f.l.w.n(view) == 0) {
                    a.f.l.w.i(view, 1);
                }
                androidx.recyclerview.widget.s sVar = RecyclerView.this.mAccessibilityDelegate;
                if (sVar == null) {
                    return;
                }
                a.f.l.a a2 = sVar.a();
                if (a2 instanceof s.a) {
                    ((s.a) a2).b(view);
                }
                a.f.l.w.a(view, a2);
            }
        }

        public void a() {
            this.f1732a.clear();
            i();
        }

        View b(int i, boolean z) {
            return a(i, z, RecyclerView.FOREVER_NS).itemView;
        }

        void c(View view) {
            d0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (!childViewHolderInt.hasAnyOfTheFlags(12) && childViewHolderInt.isUpdated() && !RecyclerView.this.canReuseUpdatedViewHolder(childViewHolderInt)) {
                if (this.f1733b == null) {
                    this.f1733b = new ArrayList<>();
                }
                childViewHolderInt.setScrapContainer(this, true);
                this.f1733b.add(childViewHolderInt);
                return;
            }
            if (childViewHolderInt.isInvalid() && !childViewHolderInt.isRemoved() && !RecyclerView.this.mAdapter.hasStableIds()) {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + RecyclerView.this.exceptionLabel());
            }
            childViewHolderInt.setScrapContainer(this, false);
            this.f1732a.add(childViewHolderInt);
        }

        boolean d(d0 d0Var) {
            if (d0Var.isRemoved()) {
                return RecyclerView.this.mState.d();
            }
            int i = d0Var.mPosition;
            if (i >= 0 && i < RecyclerView.this.mAdapter.getItemCount()) {
                if (RecyclerView.this.mState.d() || RecyclerView.this.mAdapter.getItemViewType(d0Var.mPosition) == d0Var.getItemViewType()) {
                    return !RecyclerView.this.mAdapter.hasStableIds() || d0Var.getItemId() == RecyclerView.this.mAdapter.getItemId(d0Var.mPosition);
                }
                return false;
            }
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + d0Var + RecyclerView.this.exceptionLabel());
        }

        public void f(int i) {
            this.f1736e = i;
            j();
        }

        void g() {
            int size = this.f1734c.size();
            for (int i = 0; i < size; i++) {
                p pVar = (p) this.f1734c.get(i).itemView.getLayoutParams();
                if (pVar != null) {
                    pVar.f1724c = true;
                }
            }
        }

        void h() {
            int size = this.f1734c.size();
            for (int i = 0; i < size; i++) {
                d0 d0Var = this.f1734c.get(i);
                if (d0Var != null) {
                    d0Var.addFlags(6);
                    d0Var.addChangePayload(null);
                }
            }
            g gVar = RecyclerView.this.mAdapter;
            if (gVar == null || !gVar.hasStableIds()) {
                i();
            }
        }

        void i() {
            for (int size = this.f1734c.size() - 1; size >= 0; size--) {
                e(size);
            }
            this.f1734c.clear();
            if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                RecyclerView.this.mPrefetchRegistry.a();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void j() {
            o oVar = RecyclerView.this.mLayout;
            this.f1737f = this.f1736e + (oVar != null ? oVar.m : 0);
            for (int size = this.f1734c.size() - 1; size >= 0 && this.f1734c.size() > this.f1737f; size--) {
                e(size);
            }
        }

        public void b(View view) {
            d0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (childViewHolderInt.isScrap()) {
                childViewHolderInt.unScrap();
            } else if (childViewHolderInt.wasReturnedFromScrap()) {
                childViewHolderInt.clearReturnedFromScrapFlag();
            }
            b(childViewHolderInt);
            if (RecyclerView.this.mItemAnimator == null || childViewHolderInt.isRecyclable()) {
                return;
            }
            RecyclerView.this.mItemAnimator.c(childViewHolderInt);
        }

        private boolean a(d0 d0Var, int i, int i2, long j) {
            d0Var.mOwnerRecyclerView = RecyclerView.this;
            int itemViewType = d0Var.getItemViewType();
            long nanoTime = RecyclerView.this.getNanoTime();
            if (j != RecyclerView.FOREVER_NS && !this.g.a(itemViewType, nanoTime, j)) {
                return false;
            }
            RecyclerView.this.mAdapter.bindViewHolder(d0Var, i);
            this.g.a(d0Var.getItemViewType(), RecyclerView.this.getNanoTime() - nanoTime);
            e(d0Var);
            if (!RecyclerView.this.mState.d()) {
                return true;
            }
            d0Var.mPreLayoutPosition = i2;
            return true;
        }

        public List<d0> f() {
            return this.f1735d;
        }

        private void f(d0 d0Var) {
            View view = d0Var.itemView;
            if (view instanceof ViewGroup) {
                a((ViewGroup) view, false);
            }
        }

        public View d(int i) {
            return b(i, false);
        }

        void e(int i) {
            a(this.f1734c.get(i), true);
            this.f1734c.remove(i);
        }

        void b(d0 d0Var) {
            boolean z;
            if (!d0Var.isScrap() && d0Var.itemView.getParent() == null) {
                if (!d0Var.isTmpDetached()) {
                    if (!d0Var.shouldIgnore()) {
                        boolean doesTransientStatePreventRecycling = d0Var.doesTransientStatePreventRecycling();
                        g gVar = RecyclerView.this.mAdapter;
                        if ((gVar != null && doesTransientStatePreventRecycling && gVar.onFailedToRecycleView(d0Var)) || d0Var.isRecyclable()) {
                            if (this.f1737f <= 0 || d0Var.hasAnyOfTheFlags(526)) {
                                z = false;
                            } else {
                                int size = this.f1734c.size();
                                if (size >= this.f1737f && size > 0) {
                                    e(0);
                                    size--;
                                }
                                if (RecyclerView.ALLOW_THREAD_GAP_WORK && size > 0 && !RecyclerView.this.mPrefetchRegistry.a(d0Var.mPosition)) {
                                    int i = size - 1;
                                    while (i >= 0) {
                                        if (!RecyclerView.this.mPrefetchRegistry.a(this.f1734c.get(i).mPosition)) {
                                            break;
                                        } else {
                                            i--;
                                        }
                                    }
                                    size = i + 1;
                                }
                                this.f1734c.add(size, d0Var);
                                z = true;
                            }
                            if (!z) {
                                a(d0Var, true);
                                r1 = true;
                            }
                        } else {
                            z = false;
                        }
                        RecyclerView.this.mViewInfoStore.h(d0Var);
                        if (z || r1 || !doesTransientStatePreventRecycling) {
                            return;
                        }
                        d0Var.mOwnerRecyclerView = null;
                        return;
                    }
                    throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + RecyclerView.this.exceptionLabel());
                }
                throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + d0Var + RecyclerView.this.exceptionLabel());
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Scrapped or attached views may not be recycled. isScrap:");
            sb.append(d0Var.isScrap());
            sb.append(" isAttached:");
            sb.append(d0Var.itemView.getParent() != null);
            sb.append(RecyclerView.this.exceptionLabel());
            throw new IllegalArgumentException(sb.toString());
        }

        u d() {
            if (this.g == null) {
                this.g = new u();
            }
            return this.g;
        }

        void c(d0 d0Var) {
            if (d0Var.mInChangeScrap) {
                this.f1733b.remove(d0Var);
            } else {
                this.f1732a.remove(d0Var);
            }
            d0Var.mScrapContainer = null;
            d0Var.mInChangeScrap = false;
            d0Var.clearReturnedFromScrapFlag();
        }

        public int a(int i) {
            if (i >= 0 && i < RecyclerView.this.mState.a()) {
                return !RecyclerView.this.mState.d() ? i : RecyclerView.this.mAdapterHelper.b(i);
            }
            throw new IndexOutOfBoundsException("invalid position " + i + ". State item count is " + RecyclerView.this.mState.a() + RecyclerView.this.exceptionLabel());
        }

        int e() {
            return this.f1732a.size();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x005f  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x0184  */
        /* JADX WARN: Removed duplicated region for block: B:75:0x01a1  */
        /* JADX WARN: Removed duplicated region for block: B:78:0x01c4  */
        /* JADX WARN: Removed duplicated region for block: B:84:0x01fd  */
        /* JADX WARN: Removed duplicated region for block: B:87:0x0227 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:91:0x020b  */
        /* JADX WARN: Removed duplicated region for block: B:97:0x01d3  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public androidx.recyclerview.widget.RecyclerView.d0 a(int r17, boolean r18, long r19) {
            /*
                Method dump skipped, instructions count: 614
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.v.a(int, boolean, long):androidx.recyclerview.widget.RecyclerView$d0");
        }

        View c(int i) {
            return this.f1732a.get(i).itemView;
        }

        void c() {
            this.f1732a.clear();
            ArrayList<d0> arrayList = this.f1733b;
            if (arrayList != null) {
                arrayList.clear();
            }
        }

        void c(int i, int i2) {
            int i3;
            int i4 = i2 + i;
            for (int size = this.f1734c.size() - 1; size >= 0; size--) {
                d0 d0Var = this.f1734c.get(size);
                if (d0Var != null && (i3 = d0Var.mPosition) >= i && i3 < i4) {
                    d0Var.addFlags(2);
                    e(size);
                }
            }
        }

        d0 b(int i) {
            int size;
            int b2;
            ArrayList<d0> arrayList = this.f1733b;
            if (arrayList != null && (size = arrayList.size()) != 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    d0 d0Var = this.f1733b.get(i2);
                    if (!d0Var.wasReturnedFromScrap() && d0Var.getLayoutPosition() == i) {
                        d0Var.addFlags(32);
                        return d0Var;
                    }
                }
                if (RecyclerView.this.mAdapter.hasStableIds() && (b2 = RecyclerView.this.mAdapterHelper.b(i)) > 0 && b2 < RecyclerView.this.mAdapter.getItemCount()) {
                    long itemId = RecyclerView.this.mAdapter.getItemId(b2);
                    for (int i3 = 0; i3 < size; i3++) {
                        d0 d0Var2 = this.f1733b.get(i3);
                        if (!d0Var2.wasReturnedFromScrap() && d0Var2.getItemId() == itemId) {
                            d0Var2.addFlags(32);
                            return d0Var2;
                        }
                    }
                }
            }
            return null;
        }

        void b(int i, int i2) {
            int i3;
            int i4;
            int i5;
            int i6;
            if (i < i2) {
                i4 = i;
                i3 = i2;
                i5 = -1;
            } else {
                i3 = i;
                i4 = i2;
                i5 = 1;
            }
            int size = this.f1734c.size();
            for (int i7 = 0; i7 < size; i7++) {
                d0 d0Var = this.f1734c.get(i7);
                if (d0Var != null && (i6 = d0Var.mPosition) >= i4 && i6 <= i3) {
                    if (i6 == i) {
                        d0Var.offsetPosition(i2 - i, false);
                    } else {
                        d0Var.offsetPosition(i5, false);
                    }
                }
            }
        }

        void b() {
            int size = this.f1734c.size();
            for (int i = 0; i < size; i++) {
                this.f1734c.get(i).clearOldPosition();
            }
            int size2 = this.f1732a.size();
            for (int i2 = 0; i2 < size2; i2++) {
                this.f1732a.get(i2).clearOldPosition();
            }
            ArrayList<d0> arrayList = this.f1733b;
            if (arrayList != null) {
                int size3 = arrayList.size();
                for (int i3 = 0; i3 < size3; i3++) {
                    this.f1733b.get(i3).clearOldPosition();
                }
            }
        }

        private void a(ViewGroup viewGroup, boolean z) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof ViewGroup) {
                    a((ViewGroup) childAt, true);
                }
            }
            if (z) {
                if (viewGroup.getVisibility() == 4) {
                    viewGroup.setVisibility(0);
                    viewGroup.setVisibility(4);
                } else {
                    int visibility = viewGroup.getVisibility();
                    viewGroup.setVisibility(4);
                    viewGroup.setVisibility(visibility);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a(d0 d0Var, boolean z) {
            RecyclerView.clearNestedRecyclerViewIfNotNested(d0Var);
            View view = d0Var.itemView;
            androidx.recyclerview.widget.s sVar = RecyclerView.this.mAccessibilityDelegate;
            if (sVar != null) {
                a.f.l.a a2 = sVar.a();
                a.f.l.w.a(view, a2 instanceof s.a ? ((s.a) a2).a(view) : null);
            }
            if (z) {
                a(d0Var);
            }
            d0Var.mOwnerRecyclerView = null;
            d().a(d0Var);
        }

        void a(View view) {
            d0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            childViewHolderInt.mScrapContainer = null;
            childViewHolderInt.mInChangeScrap = false;
            childViewHolderInt.clearReturnedFromScrapFlag();
            b(childViewHolderInt);
        }

        d0 a(int i, boolean z) {
            View b2;
            int size = this.f1732a.size();
            for (int i2 = 0; i2 < size; i2++) {
                d0 d0Var = this.f1732a.get(i2);
                if (!d0Var.wasReturnedFromScrap() && d0Var.getLayoutPosition() == i && !d0Var.isInvalid() && (RecyclerView.this.mState.h || !d0Var.isRemoved())) {
                    d0Var.addFlags(32);
                    return d0Var;
                }
            }
            if (!z && (b2 = RecyclerView.this.mChildHelper.b(i)) != null) {
                d0 childViewHolderInt = RecyclerView.getChildViewHolderInt(b2);
                RecyclerView.this.mChildHelper.f(b2);
                int b3 = RecyclerView.this.mChildHelper.b(b2);
                if (b3 != -1) {
                    RecyclerView.this.mChildHelper.a(b3);
                    c(b2);
                    childViewHolderInt.addFlags(8224);
                    return childViewHolderInt;
                }
                throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + childViewHolderInt + RecyclerView.this.exceptionLabel());
            }
            int size2 = this.f1734c.size();
            for (int i3 = 0; i3 < size2; i3++) {
                d0 d0Var2 = this.f1734c.get(i3);
                if (!d0Var2.isInvalid() && d0Var2.getLayoutPosition() == i && !d0Var2.isAttachedToTransitionOverlay()) {
                    if (!z) {
                        this.f1734c.remove(i3);
                    }
                    return d0Var2;
                }
            }
            return null;
        }

        d0 a(long j, int i, boolean z) {
            for (int size = this.f1732a.size() - 1; size >= 0; size--) {
                d0 d0Var = this.f1732a.get(size);
                if (d0Var.getItemId() == j && !d0Var.wasReturnedFromScrap()) {
                    if (i == d0Var.getItemViewType()) {
                        d0Var.addFlags(32);
                        if (d0Var.isRemoved() && !RecyclerView.this.mState.d()) {
                            d0Var.setFlags(2, 14);
                        }
                        return d0Var;
                    }
                    if (!z) {
                        this.f1732a.remove(size);
                        RecyclerView.this.removeDetachedView(d0Var.itemView, false);
                        a(d0Var.itemView);
                    }
                }
            }
            int size2 = this.f1734c.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return null;
                }
                d0 d0Var2 = this.f1734c.get(size2);
                if (d0Var2.getItemId() == j && !d0Var2.isAttachedToTransitionOverlay()) {
                    if (i == d0Var2.getItemViewType()) {
                        if (!z) {
                            this.f1734c.remove(size2);
                        }
                        return d0Var2;
                    }
                    if (!z) {
                        e(size2);
                        return null;
                    }
                }
            }
        }

        void a(d0 d0Var) {
            w wVar = RecyclerView.this.mRecyclerListener;
            if (wVar != null) {
                wVar.a(d0Var);
            }
            g gVar = RecyclerView.this.mAdapter;
            if (gVar != null) {
                gVar.onViewRecycled(d0Var);
            }
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.mState != null) {
                recyclerView.mViewInfoStore.h(d0Var);
            }
        }

        void a(g gVar, g gVar2, boolean z) {
            a();
            d().a(gVar, gVar2, z);
        }

        void a(int i, int i2) {
            int size = this.f1734c.size();
            for (int i3 = 0; i3 < size; i3++) {
                d0 d0Var = this.f1734c.get(i3);
                if (d0Var != null && d0Var.mPosition >= i) {
                    d0Var.offsetPosition(i2, true);
                }
            }
        }

        void a(int i, int i2, boolean z) {
            int i3 = i + i2;
            for (int size = this.f1734c.size() - 1; size >= 0; size--) {
                d0 d0Var = this.f1734c.get(size);
                if (d0Var != null) {
                    int i4 = d0Var.mPosition;
                    if (i4 >= i3) {
                        d0Var.offsetPosition(-i2, z);
                    } else if (i4 >= i) {
                        d0Var.addFlags(8);
                        e(size);
                    }
                }
            }
        }

        void a(b0 b0Var) {
            this.h = b0Var;
        }

        void a(u uVar) {
            u uVar2 = this.g;
            if (uVar2 != null) {
                uVar2.c();
            }
            this.g = uVar;
            if (this.g == null || RecyclerView.this.getAdapter() == null) {
                return;
            }
            this.g.a();
        }
    }

    /* loaded from: classes.dex */
    public interface w {
        void a(d0 d0Var);
    }

    /* loaded from: classes.dex */
    public static abstract class z {

        /* renamed from: b, reason: collision with root package name */
        private RecyclerView f1741b;

        /* renamed from: c, reason: collision with root package name */
        private o f1742c;

        /* renamed from: d, reason: collision with root package name */
        private boolean f1743d;

        /* renamed from: e, reason: collision with root package name */
        private boolean f1744e;

        /* renamed from: f, reason: collision with root package name */
        private View f1745f;
        private boolean h;

        /* renamed from: a, reason: collision with root package name */
        private int f1740a = -1;
        private final a g = new a(0, 0);

        /* loaded from: classes.dex */
        public static class a {

            /* renamed from: a, reason: collision with root package name */
            private int f1746a;

            /* renamed from: b, reason: collision with root package name */
            private int f1747b;

            /* renamed from: c, reason: collision with root package name */
            private int f1748c;

            /* renamed from: d, reason: collision with root package name */
            private int f1749d;

            /* renamed from: e, reason: collision with root package name */
            private Interpolator f1750e;

            /* renamed from: f, reason: collision with root package name */
            private boolean f1751f;
            private int g;

            public a(int i, int i2) {
                this(i, i2, RecyclerView.UNDEFINED_DURATION, null);
            }

            private void b() {
                if (this.f1750e != null && this.f1748c < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                }
                if (this.f1748c < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }

            public void a(int i) {
                this.f1749d = i;
            }

            public a(int i, int i2, int i3, Interpolator interpolator) {
                this.f1749d = -1;
                this.f1751f = false;
                this.g = 0;
                this.f1746a = i;
                this.f1747b = i2;
                this.f1748c = i3;
                this.f1750e = interpolator;
            }

            boolean a() {
                return this.f1749d >= 0;
            }

            void a(RecyclerView recyclerView) {
                int i = this.f1749d;
                if (i >= 0) {
                    this.f1749d = -1;
                    recyclerView.jumpToPositionForSmoothScroller(i);
                    this.f1751f = false;
                } else {
                    if (this.f1751f) {
                        b();
                        recyclerView.mViewFlinger.a(this.f1746a, this.f1747b, this.f1748c, this.f1750e);
                        this.g++;
                        if (this.g > 10) {
                            Log.e(RecyclerView.TAG, "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                        }
                        this.f1751f = false;
                        return;
                    }
                    this.g = 0;
                }
            }

            public void a(int i, int i2, int i3, Interpolator interpolator) {
                this.f1746a = i;
                this.f1747b = i2;
                this.f1748c = i3;
                this.f1750e = interpolator;
                this.f1751f = true;
            }
        }

        /* loaded from: classes.dex */
        public interface b {
            PointF a(int i);
        }

        protected abstract void a(int i, int i2, a0 a0Var, a aVar);

        protected abstract void a(View view, a0 a0Var, a aVar);

        void a(RecyclerView recyclerView, o oVar) {
            recyclerView.mViewFlinger.b();
            if (this.h) {
                Log.w(RecyclerView.TAG, "An instance of " + getClass().getSimpleName() + " was started more than once. Each instance of" + getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
            }
            this.f1741b = recyclerView;
            this.f1742c = oVar;
            int i = this.f1740a;
            if (i != -1) {
                this.f1741b.mState.f1686a = i;
                this.f1744e = true;
                this.f1743d = true;
                this.f1745f = b(c());
                f();
                this.f1741b.mViewFlinger.a();
                this.h = true;
                return;
            }
            throw new IllegalArgumentException("Invalid target position");
        }

        public o b() {
            return this.f1742c;
        }

        public void c(int i) {
            this.f1740a = i;
        }

        public boolean d() {
            return this.f1743d;
        }

        public boolean e() {
            return this.f1744e;
        }

        protected abstract void f();

        protected abstract void g();

        /* JADX INFO: Access modifiers changed from: protected */
        public final void h() {
            if (this.f1744e) {
                this.f1744e = false;
                g();
                this.f1741b.mState.f1686a = -1;
                this.f1745f = null;
                this.f1740a = -1;
                this.f1743d = false;
                this.f1742c.a(this);
                this.f1742c = null;
                this.f1741b = null;
            }
        }

        public View b(int i) {
            return this.f1741b.mLayout.c(i);
        }

        public int c() {
            return this.f1740a;
        }

        protected void b(View view) {
            if (a(view) == c()) {
                this.f1745f = view;
            }
        }

        public PointF a(int i) {
            Object b2 = b();
            if (b2 instanceof b) {
                return ((b) b2).a(i);
            }
            Log.w(RecyclerView.TAG, "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + b.class.getCanonicalName());
            return null;
        }

        void a(int i, int i2) {
            PointF a2;
            RecyclerView recyclerView = this.f1741b;
            if (this.f1740a == -1 || recyclerView == null) {
                h();
            }
            if (this.f1743d && this.f1745f == null && this.f1742c != null && (a2 = a(this.f1740a)) != null && (a2.x != 0.0f || a2.y != 0.0f)) {
                recyclerView.scrollStep((int) Math.signum(a2.x), (int) Math.signum(a2.y), null);
            }
            this.f1743d = false;
            View view = this.f1745f;
            if (view != null) {
                if (a(view) == this.f1740a) {
                    a(this.f1745f, recyclerView.mState, this.g);
                    this.g.a(recyclerView);
                    h();
                } else {
                    Log.e(RecyclerView.TAG, "Passed over target position while smooth scrolling.");
                    this.f1745f = null;
                }
            }
            if (this.f1744e) {
                a(i, i2, recyclerView.mState, this.g);
                boolean a3 = this.g.a();
                this.g.a(recyclerView);
                if (a3 && this.f1744e) {
                    this.f1743d = true;
                    recyclerView.mViewFlinger.a();
                }
            }
        }

        public int a(View view) {
            return this.f1741b.getChildLayoutPosition(view);
        }

        public int a() {
            return this.f1741b.mLayout.e();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void a(PointF pointF) {
            float f2 = pointF.x;
            float f3 = pointF.y;
            float sqrt = (float) Math.sqrt((f2 * f2) + (f3 * f3));
            pointF.x /= sqrt;
            pointF.y /= sqrt;
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        FORCE_INVALIDATE_DISPLAY_LIST = i2 == 18 || i2 == 19 || i2 == 20;
        ALLOW_SIZE_IN_UNSPECIFIED_SPEC = Build.VERSION.SDK_INT >= 23;
        POST_UPDATES_ON_ANIMATION = Build.VERSION.SDK_INT >= 16;
        ALLOW_THREAD_GAP_WORK = Build.VERSION.SDK_INT >= 21;
        FORCE_ABS_FOCUS_SEARCH_DIRECTION = Build.VERSION.SDK_INT <= 15;
        IGNORE_DETACHED_FOCUSED_CHILD = Build.VERSION.SDK_INT <= 15;
        Class<?> cls = Integer.TYPE;
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class, cls, cls};
        sQuinticInterpolator = new c();
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    private void addAnimatingView(d0 d0Var) {
        View view = d0Var.itemView;
        boolean z2 = view.getParent() == this;
        this.mRecycler.c(getChildViewHolder(view));
        if (d0Var.isTmpDetached()) {
            this.mChildHelper.a(view, -1, view.getLayoutParams(), true);
        } else if (!z2) {
            this.mChildHelper.a(view, true);
        } else {
            this.mChildHelper.a(view);
        }
    }

    private void animateChange(d0 d0Var, d0 d0Var2, l.c cVar, l.c cVar2, boolean z2, boolean z3) {
        d0Var.setIsRecyclable(false);
        if (z2) {
            addAnimatingView(d0Var);
        }
        if (d0Var != d0Var2) {
            if (z3) {
                addAnimatingView(d0Var2);
            }
            d0Var.mShadowedHolder = d0Var2;
            addAnimatingView(d0Var);
            this.mRecycler.c(d0Var);
            d0Var2.setIsRecyclable(false);
            d0Var2.mShadowingHolder = d0Var;
        }
        if (this.mItemAnimator.a(d0Var, d0Var2, cVar, cVar2)) {
            postAnimationRunner();
        }
    }

    private void cancelScroll() {
        resetScroll();
        setScrollState(0);
    }

    static void clearNestedRecyclerViewIfNotNested(d0 d0Var) {
        WeakReference<RecyclerView> weakReference = d0Var.mNestedRecyclerView;
        if (weakReference != null) {
            RecyclerView recyclerView = weakReference.get();
            while (recyclerView != null) {
                if (recyclerView == d0Var.itemView) {
                    return;
                }
                Object parent = recyclerView.getParent();
                recyclerView = parent instanceof View ? (View) parent : null;
            }
            d0Var.mNestedRecyclerView = null;
        }
    }

    private void createLayoutManager(Context context, String str, AttributeSet attributeSet, int i2, int i3) {
        ClassLoader classLoader;
        Constructor constructor;
        if (str != null) {
            String trim = str.trim();
            if (trim.isEmpty()) {
                return;
            }
            String fullClassName = getFullClassName(context, trim);
            try {
                if (isInEditMode()) {
                    classLoader = getClass().getClassLoader();
                } else {
                    classLoader = context.getClassLoader();
                }
                Class<? extends U> asSubclass = Class.forName(fullClassName, false, classLoader).asSubclass(o.class);
                Object[] objArr = null;
                try {
                    constructor = asSubclass.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
                    objArr = new Object[]{context, attributeSet, Integer.valueOf(i2), Integer.valueOf(i3)};
                } catch (NoSuchMethodException e2) {
                    try {
                        constructor = asSubclass.getConstructor(new Class[0]);
                    } catch (NoSuchMethodException e3) {
                        e3.initCause(e2);
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + fullClassName, e3);
                    }
                }
                constructor.setAccessible(true);
                setLayoutManager((o) constructor.newInstance(objArr));
            } catch (ClassCastException e4) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + fullClassName, e4);
            } catch (ClassNotFoundException e5) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + fullClassName, e5);
            } catch (IllegalAccessException e6) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + fullClassName, e6);
            } catch (InstantiationException e7) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, e7);
            } catch (InvocationTargetException e8) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, e8);
            }
        }
    }

    private boolean didChildRangeChange(int i2, int i3) {
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        int[] iArr = this.mMinMaxLayoutPositions;
        return (iArr[0] == i2 && iArr[1] == i3) ? false : true;
    }

    private void dispatchContentChangedIfNecessary() {
        int i2 = this.mEatenAccessibilityChangeFlags;
        this.mEatenAccessibilityChangeFlags = 0;
        if (i2 == 0 || !isAccessibilityEnabled()) {
            return;
        }
        AccessibilityEvent obtain = AccessibilityEvent.obtain();
        obtain.setEventType(DfuBaseService.ERROR_REMOTE_TYPE_SECURE_BUTTONLESS);
        a.f.l.f0.b.a(obtain, i2);
        sendAccessibilityEventUnchecked(obtain);
    }

    private void dispatchLayoutStep1() {
        this.mState.a(1);
        fillRemainingScrollValues(this.mState);
        this.mState.j = false;
        startInterceptRequestLayout();
        this.mViewInfoStore.a();
        onEnterLayoutOrScroll();
        processAdapterUpdatesAndSetAnimationFlags();
        saveFocusInfo();
        a0 a0Var = this.mState;
        a0Var.i = a0Var.k && this.mItemsChanged;
        this.mItemsChanged = false;
        this.mItemsAddedOrRemoved = false;
        a0 a0Var2 = this.mState;
        a0Var2.h = a0Var2.l;
        a0Var2.f1691f = this.mAdapter.getItemCount();
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        if (this.mState.k) {
            int a2 = this.mChildHelper.a();
            for (int i2 = 0; i2 < a2; i2++) {
                d0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.c(i2));
                if (!childViewHolderInt.shouldIgnore() && (!childViewHolderInt.isInvalid() || this.mAdapter.hasStableIds())) {
                    this.mViewInfoStore.c(childViewHolderInt, this.mItemAnimator.a(this.mState, childViewHolderInt, l.e(childViewHolderInt), childViewHolderInt.getUnmodifiedPayloads()));
                    if (this.mState.i && childViewHolderInt.isUpdated() && !childViewHolderInt.isRemoved() && !childViewHolderInt.shouldIgnore() && !childViewHolderInt.isInvalid()) {
                        this.mViewInfoStore.a(getChangedHolderKey(childViewHolderInt), childViewHolderInt);
                    }
                }
            }
        }
        if (this.mState.l) {
            saveOldPositions();
            a0 a0Var3 = this.mState;
            boolean z2 = a0Var3.g;
            a0Var3.g = false;
            this.mLayout.e(this.mRecycler, a0Var3);
            this.mState.g = z2;
            for (int i3 = 0; i3 < this.mChildHelper.a(); i3++) {
                d0 childViewHolderInt2 = getChildViewHolderInt(this.mChildHelper.c(i3));
                if (!childViewHolderInt2.shouldIgnore() && !this.mViewInfoStore.c(childViewHolderInt2)) {
                    int e2 = l.e(childViewHolderInt2);
                    boolean hasAnyOfTheFlags = childViewHolderInt2.hasAnyOfTheFlags(DfuBaseService.ERROR_REMOTE_MASK);
                    if (!hasAnyOfTheFlags) {
                        e2 |= 4096;
                    }
                    l.c a3 = this.mItemAnimator.a(this.mState, childViewHolderInt2, e2, childViewHolderInt2.getUnmodifiedPayloads());
                    if (hasAnyOfTheFlags) {
                        recordAnimationInfoIfBouncedHiddenView(childViewHolderInt2, a3);
                    } else {
                        this.mViewInfoStore.a(childViewHolderInt2, a3);
                    }
                }
            }
            clearOldPositions();
        } else {
            clearOldPositions();
        }
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        this.mState.f1690e = 2;
    }

    private void dispatchLayoutStep2() {
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        this.mState.a(6);
        this.mAdapterHelper.b();
        this.mState.f1691f = this.mAdapter.getItemCount();
        a0 a0Var = this.mState;
        a0Var.f1689d = 0;
        a0Var.h = false;
        this.mLayout.e(this.mRecycler, a0Var);
        a0 a0Var2 = this.mState;
        a0Var2.g = false;
        this.mPendingSavedState = null;
        a0Var2.k = a0Var2.k && this.mItemAnimator != null;
        this.mState.f1690e = 4;
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
    }

    private void dispatchLayoutStep3() {
        this.mState.a(4);
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        a0 a0Var = this.mState;
        a0Var.f1690e = 1;
        if (a0Var.k) {
            for (int a2 = this.mChildHelper.a() - 1; a2 >= 0; a2--) {
                d0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.c(a2));
                if (!childViewHolderInt.shouldIgnore()) {
                    long changedHolderKey = getChangedHolderKey(childViewHolderInt);
                    l.c a3 = this.mItemAnimator.a(this.mState, childViewHolderInt);
                    d0 a4 = this.mViewInfoStore.a(changedHolderKey);
                    if (a4 != null && !a4.shouldIgnore()) {
                        boolean b2 = this.mViewInfoStore.b(a4);
                        boolean b3 = this.mViewInfoStore.b(childViewHolderInt);
                        if (b2 && a4 == childViewHolderInt) {
                            this.mViewInfoStore.b(childViewHolderInt, a3);
                        } else {
                            l.c f2 = this.mViewInfoStore.f(a4);
                            this.mViewInfoStore.b(childViewHolderInt, a3);
                            l.c e2 = this.mViewInfoStore.e(childViewHolderInt);
                            if (f2 == null) {
                                handleMissingPreInfoForChangeError(changedHolderKey, childViewHolderInt, a4);
                            } else {
                                animateChange(a4, childViewHolderInt, f2, e2, b2, b3);
                            }
                        }
                    } else {
                        this.mViewInfoStore.b(childViewHolderInt, a3);
                    }
                }
            }
            this.mViewInfoStore.a(this.mViewInfoProcessCallback);
        }
        this.mLayout.c(this.mRecycler);
        a0 a0Var2 = this.mState;
        a0Var2.f1688c = a0Var2.f1691f;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        a0Var2.k = false;
        a0Var2.l = false;
        this.mLayout.h = false;
        ArrayList<d0> arrayList = this.mRecycler.f1733b;
        if (arrayList != null) {
            arrayList.clear();
        }
        o oVar = this.mLayout;
        if (oVar.n) {
            oVar.m = 0;
            oVar.n = false;
            this.mRecycler.j();
        }
        this.mLayout.g(this.mState);
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        this.mViewInfoStore.a();
        int[] iArr = this.mMinMaxLayoutPositions;
        if (didChildRangeChange(iArr[0], iArr[1])) {
            dispatchOnScrolled(0, 0);
        }
        recoverFocusFromState();
        resetFocusInfo();
    }

    private boolean dispatchToOnItemTouchListeners(MotionEvent motionEvent) {
        s sVar = this.mInterceptingOnItemTouchListener;
        if (sVar == null) {
            if (motionEvent.getAction() == 0) {
                return false;
            }
            return findInterceptingOnItemTouchListener(motionEvent);
        }
        sVar.onTouchEvent(this, motionEvent);
        int action = motionEvent.getAction();
        if (action == 3 || action == 1) {
            this.mInterceptingOnItemTouchListener = null;
        }
        return true;
    }

    private boolean findInterceptingOnItemTouchListener(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int size = this.mOnItemTouchListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            s sVar = this.mOnItemTouchListeners.get(i2);
            if (sVar.onInterceptTouchEvent(this, motionEvent) && action != 3) {
                this.mInterceptingOnItemTouchListener = sVar;
                return true;
            }
        }
        return false;
    }

    private void findMinMaxChildLayoutPositions(int[] iArr) {
        int a2 = this.mChildHelper.a();
        if (a2 == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i2 = Preference.DEFAULT_ORDER;
        int i3 = UNDEFINED_DURATION;
        for (int i4 = 0; i4 < a2; i4++) {
            d0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.c(i4));
            if (!childViewHolderInt.shouldIgnore()) {
                int layoutPosition = childViewHolderInt.getLayoutPosition();
                if (layoutPosition < i2) {
                    i2 = layoutPosition;
                }
                if (layoutPosition > i3) {
                    i3 = layoutPosition;
                }
            }
        }
        iArr[0] = i2;
        iArr[1] = i3;
    }

    static RecyclerView findNestedRecyclerView(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            RecyclerView findNestedRecyclerView = findNestedRecyclerView(viewGroup.getChildAt(i2));
            if (findNestedRecyclerView != null) {
                return findNestedRecyclerView;
            }
        }
        return null;
    }

    private View findNextViewToFocus() {
        d0 findViewHolderForAdapterPosition;
        int i2 = this.mState.m;
        if (i2 == -1) {
            i2 = 0;
        }
        int a2 = this.mState.a();
        for (int i3 = i2; i3 < a2; i3++) {
            d0 findViewHolderForAdapterPosition2 = findViewHolderForAdapterPosition(i3);
            if (findViewHolderForAdapterPosition2 == null) {
                break;
            }
            if (findViewHolderForAdapterPosition2.itemView.hasFocusable()) {
                return findViewHolderForAdapterPosition2.itemView;
            }
        }
        int min = Math.min(a2, i2);
        do {
            min--;
            if (min < 0 || (findViewHolderForAdapterPosition = findViewHolderForAdapterPosition(min)) == null) {
                return null;
            }
        } while (!findViewHolderForAdapterPosition.itemView.hasFocusable());
        return findViewHolderForAdapterPosition.itemView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static d0 getChildViewHolderInt(View view) {
        if (view == null) {
            return null;
        }
        return ((p) view.getLayoutParams()).f1722a;
    }

    static void getDecoratedBoundsWithMarginsInt(View view, Rect rect) {
        p pVar = (p) view.getLayoutParams();
        Rect rect2 = pVar.f1723b;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) pVar).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) pVar).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) pVar).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
    }

    private int getDeepestFocusedViewWithId(View view) {
        int id = view.getId();
        while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
            view = ((ViewGroup) view).getFocusedChild();
            if (view.getId() != -1) {
                id = view.getId();
            }
        }
        return id;
    }

    private String getFullClassName(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        }
        if (str.contains(".")) {
            return str;
        }
        return RecyclerView.class.getPackage().getName() + '.' + str;
    }

    private a.f.l.m getScrollingChildHelper() {
        if (this.mScrollingChildHelper == null) {
            this.mScrollingChildHelper = new a.f.l.m(this);
        }
        return this.mScrollingChildHelper;
    }

    private void handleMissingPreInfoForChangeError(long j2, d0 d0Var, d0 d0Var2) {
        int a2 = this.mChildHelper.a();
        for (int i2 = 0; i2 < a2; i2++) {
            d0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.c(i2));
            if (childViewHolderInt != d0Var && getChangedHolderKey(childViewHolderInt) == j2) {
                g gVar = this.mAdapter;
                if (gVar != null && gVar.hasStableIds()) {
                    throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + childViewHolderInt + " \n View Holder 2:" + d0Var + exceptionLabel());
                }
                throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + childViewHolderInt + " \n View Holder 2:" + d0Var + exceptionLabel());
            }
        }
        Log.e(TAG, "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + d0Var2 + " cannot be found but it is necessary for " + d0Var + exceptionLabel());
    }

    private boolean hasUpdatedView() {
        int a2 = this.mChildHelper.a();
        for (int i2 = 0; i2 < a2; i2++) {
            d0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.c(i2));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.isUpdated()) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint({"InlinedApi"})
    private void initAutofill() {
        if (a.f.l.w.o(this) == 0) {
            a.f.l.w.j(this, 8);
        }
    }

    private void initChildrenHelper() {
        this.mChildHelper = new androidx.recyclerview.widget.d(new e());
    }

    private boolean isPreferredNextFocus(View view, View view2, int i2) {
        int i3;
        if (view2 == null || view2 == this || findContainingItemView(view2) == null) {
            return false;
        }
        if (view == null || findContainingItemView(view) == null) {
            return true;
        }
        this.mTempRect.set(0, 0, view.getWidth(), view.getHeight());
        this.mTempRect2.set(0, 0, view2.getWidth(), view2.getHeight());
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        offsetDescendantRectToMyCoords(view2, this.mTempRect2);
        char c2 = 65535;
        int i4 = this.mLayout.k() == 1 ? -1 : 1;
        Rect rect = this.mTempRect;
        int i5 = rect.left;
        int i6 = this.mTempRect2.left;
        if ((i5 < i6 || rect.right <= i6) && this.mTempRect.right < this.mTempRect2.right) {
            i3 = 1;
        } else {
            Rect rect2 = this.mTempRect;
            int i7 = rect2.right;
            int i8 = this.mTempRect2.right;
            i3 = ((i7 > i8 || rect2.left >= i8) && this.mTempRect.left > this.mTempRect2.left) ? -1 : 0;
        }
        Rect rect3 = this.mTempRect;
        int i9 = rect3.top;
        int i10 = this.mTempRect2.top;
        if ((i9 < i10 || rect3.bottom <= i10) && this.mTempRect.bottom < this.mTempRect2.bottom) {
            c2 = 1;
        } else {
            Rect rect4 = this.mTempRect;
            int i11 = rect4.bottom;
            int i12 = this.mTempRect2.bottom;
            if ((i11 <= i12 && rect4.top < i12) || this.mTempRect.top <= this.mTempRect2.top) {
                c2 = 0;
            }
        }
        if (i2 == 1) {
            return c2 < 0 || (c2 == 0 && i3 * i4 <= 0);
        }
        if (i2 == 2) {
            return c2 > 0 || (c2 == 0 && i3 * i4 >= 0);
        }
        if (i2 == 17) {
            return i3 < 0;
        }
        if (i2 == 33) {
            return c2 < 0;
        }
        if (i2 == 66) {
            return i3 > 0;
        }
        if (i2 == 130) {
            return c2 > 0;
        }
        throw new IllegalArgumentException("Invalid direction: " + i2 + exceptionLabel());
    }

    private void onPointerUp(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mScrollPointerId) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.mScrollPointerId = motionEvent.getPointerId(i2);
            int x2 = (int) (motionEvent.getX(i2) + 0.5f);
            this.mLastTouchX = x2;
            this.mInitialTouchX = x2;
            int y2 = (int) (motionEvent.getY(i2) + 0.5f);
            this.mLastTouchY = y2;
            this.mInitialTouchY = y2;
        }
    }

    private boolean predictiveItemAnimationsEnabled() {
        return this.mItemAnimator != null && this.mLayout.D();
    }

    private void processAdapterUpdatesAndSetAnimationFlags() {
        if (this.mDataSetHasChangedAfterLayout) {
            this.mAdapterHelper.f();
            if (this.mDispatchItemsChangedEvent) {
                this.mLayout.d(this);
            }
        }
        if (predictiveItemAnimationsEnabled()) {
            this.mAdapterHelper.e();
        } else {
            this.mAdapterHelper.b();
        }
        boolean z2 = false;
        boolean z3 = this.mItemsAddedOrRemoved || this.mItemsChanged;
        this.mState.k = this.mFirstLayoutComplete && this.mItemAnimator != null && (this.mDataSetHasChangedAfterLayout || z3 || this.mLayout.h) && (!this.mDataSetHasChangedAfterLayout || this.mAdapter.hasStableIds());
        a0 a0Var = this.mState;
        if (a0Var.k && z3 && !this.mDataSetHasChangedAfterLayout && predictiveItemAnimationsEnabled()) {
            z2 = true;
        }
        a0Var.l = z2;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void pullGlows(float r7, float r8, float r9, float r10) {
        /*
            r6 = this;
            r0 = 1065353216(0x3f800000, float:1.0)
            r1 = 1
            r2 = 0
            int r3 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r3 >= 0) goto L21
            r6.ensureLeftGlow()
            android.widget.EdgeEffect r3 = r6.mLeftGlow
            float r4 = -r8
            int r5 = r6.getWidth()
            float r5 = (float) r5
            float r4 = r4 / r5
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            float r9 = r0 - r9
            androidx.core.widget.d.a(r3, r4, r9)
        L1f:
            r9 = 1
            goto L3c
        L21:
            int r3 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r3 <= 0) goto L3b
            r6.ensureRightGlow()
            android.widget.EdgeEffect r3 = r6.mRightGlow
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r4 = r8 / r4
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            androidx.core.widget.d.a(r3, r4, r9)
            goto L1f
        L3b:
            r9 = 0
        L3c:
            int r3 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r3 >= 0) goto L56
            r6.ensureTopGlow()
            android.widget.EdgeEffect r9 = r6.mTopGlow
            float r0 = -r10
            int r3 = r6.getHeight()
            float r3 = (float) r3
            float r0 = r0 / r3
            int r3 = r6.getWidth()
            float r3 = (float) r3
            float r7 = r7 / r3
            androidx.core.widget.d.a(r9, r0, r7)
            goto L72
        L56:
            int r3 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r3 <= 0) goto L71
            r6.ensureBottomGlow()
            android.widget.EdgeEffect r9 = r6.mBottomGlow
            int r3 = r6.getHeight()
            float r3 = (float) r3
            float r3 = r10 / r3
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r7 = r7 / r4
            float r0 = r0 - r7
            androidx.core.widget.d.a(r9, r3, r0)
            goto L72
        L71:
            r1 = r9
        L72:
            if (r1 != 0) goto L7c
            int r7 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r7 != 0) goto L7c
            int r7 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r7 == 0) goto L7f
        L7c:
            a.f.l.w.K(r6)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.pullGlows(float, float, float, float):void");
    }

    private void recoverFocusFromState() {
        View view;
        if (!this.mPreserveFocusAfterLayout || this.mAdapter == null || !hasFocus() || getDescendantFocusability() == 393216) {
            return;
        }
        if (getDescendantFocusability() == 131072 && isFocused()) {
            return;
        }
        if (!isFocused()) {
            View focusedChild = getFocusedChild();
            if (IGNORE_DETACHED_FOCUSED_CHILD && (focusedChild.getParent() == null || !focusedChild.hasFocus())) {
                if (this.mChildHelper.a() == 0) {
                    requestFocus();
                    return;
                }
            } else if (!this.mChildHelper.c(focusedChild)) {
                return;
            }
        }
        View view2 = null;
        d0 findViewHolderForItemId = (this.mState.n == -1 || !this.mAdapter.hasStableIds()) ? null : findViewHolderForItemId(this.mState.n);
        if (findViewHolderForItemId != null && !this.mChildHelper.c(findViewHolderForItemId.itemView) && findViewHolderForItemId.itemView.hasFocusable()) {
            view2 = findViewHolderForItemId.itemView;
        } else if (this.mChildHelper.a() > 0) {
            view2 = findNextViewToFocus();
        }
        if (view2 != null) {
            int i2 = this.mState.o;
            if (i2 == -1 || (view = view2.findViewById(i2)) == null || !view.isFocusable()) {
                view = view2;
            }
            view.requestFocus();
        }
    }

    private void releaseGlows() {
        boolean z2;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            z2 = this.mLeftGlow.isFinished();
        } else {
            z2 = false;
        }
        EdgeEffect edgeEffect2 = this.mTopGlow;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            z2 |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mRightGlow;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            z2 |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            z2 |= this.mBottomGlow.isFinished();
        }
        if (z2) {
            a.f.l.w.K(this);
        }
    }

    private void requestChildOnScreen(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        this.mTempRect.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof p) {
            p pVar = (p) layoutParams;
            if (!pVar.f1724c) {
                Rect rect = pVar.f1723b;
                Rect rect2 = this.mTempRect;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.mTempRect);
            offsetRectIntoDescendantCoords(view, this.mTempRect);
        }
        this.mLayout.a(this, view, this.mTempRect, !this.mFirstLayoutComplete, view2 == null);
    }

    private void resetFocusInfo() {
        a0 a0Var = this.mState;
        a0Var.n = -1L;
        a0Var.m = -1;
        a0Var.o = -1;
    }

    private void resetScroll() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        stopNestedScroll(0);
        releaseGlows();
    }

    private void saveFocusInfo() {
        int adapterPosition;
        View focusedChild = (this.mPreserveFocusAfterLayout && hasFocus() && this.mAdapter != null) ? getFocusedChild() : null;
        d0 findContainingViewHolder = focusedChild != null ? findContainingViewHolder(focusedChild) : null;
        if (findContainingViewHolder == null) {
            resetFocusInfo();
            return;
        }
        this.mState.n = this.mAdapter.hasStableIds() ? findContainingViewHolder.getItemId() : -1L;
        a0 a0Var = this.mState;
        if (this.mDataSetHasChangedAfterLayout) {
            adapterPosition = -1;
        } else {
            adapterPosition = findContainingViewHolder.isRemoved() ? findContainingViewHolder.mOldPosition : findContainingViewHolder.getAdapterPosition();
        }
        a0Var.m = adapterPosition;
        this.mState.o = getDeepestFocusedViewWithId(findContainingViewHolder.itemView);
    }

    private void setAdapterInternal(g gVar, boolean z2, boolean z3) {
        g gVar2 = this.mAdapter;
        if (gVar2 != null) {
            gVar2.unregisterAdapterDataObserver(this.mObserver);
            this.mAdapter.onDetachedFromRecyclerView(this);
        }
        if (!z2 || z3) {
            removeAndRecycleViews();
        }
        this.mAdapterHelper.f();
        g gVar3 = this.mAdapter;
        this.mAdapter = gVar;
        if (gVar != null) {
            gVar.registerAdapterDataObserver(this.mObserver);
            gVar.onAttachedToRecyclerView(this);
        }
        o oVar = this.mLayout;
        if (oVar != null) {
            oVar.a(gVar3, this.mAdapter);
        }
        this.mRecycler.a(gVar3, this.mAdapter, z2);
        this.mState.g = true;
    }

    private void stopScrollersInternal() {
        this.mViewFlinger.b();
        o oVar = this.mLayout;
        if (oVar != null) {
            oVar.C();
        }
    }

    void absorbGlows(int i2, int i3) {
        if (i2 < 0) {
            ensureLeftGlow();
            if (this.mLeftGlow.isFinished()) {
                this.mLeftGlow.onAbsorb(-i2);
            }
        } else if (i2 > 0) {
            ensureRightGlow();
            if (this.mRightGlow.isFinished()) {
                this.mRightGlow.onAbsorb(i2);
            }
        }
        if (i3 < 0) {
            ensureTopGlow();
            if (this.mTopGlow.isFinished()) {
                this.mTopGlow.onAbsorb(-i3);
            }
        } else if (i3 > 0) {
            ensureBottomGlow();
            if (this.mBottomGlow.isFinished()) {
                this.mBottomGlow.onAbsorb(i3);
            }
        }
        if (i2 == 0 && i3 == 0) {
            return;
        }
        a.f.l.w.K(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        o oVar = this.mLayout;
        if (oVar == null || !oVar.a(this, arrayList, i2, i3)) {
            super.addFocusables(arrayList, i2, i3);
        }
    }

    public void addItemDecoration(n nVar, int i2) {
        o oVar = this.mLayout;
        if (oVar != null) {
            oVar.a("Cannot add item decoration during a scroll  or layout");
        }
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i2 < 0) {
            this.mItemDecorations.add(nVar);
        } else {
            this.mItemDecorations.add(i2, nVar);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void addOnChildAttachStateChangeListener(q qVar) {
        if (this.mOnChildAttachStateListeners == null) {
            this.mOnChildAttachStateListeners = new ArrayList();
        }
        this.mOnChildAttachStateListeners.add(qVar);
    }

    public void addOnItemTouchListener(s sVar) {
        this.mOnItemTouchListeners.add(sVar);
    }

    public void addOnScrollListener(t tVar) {
        if (this.mScrollListeners == null) {
            this.mScrollListeners = new ArrayList();
        }
        this.mScrollListeners.add(tVar);
    }

    void animateAppearance(d0 d0Var, l.c cVar, l.c cVar2) {
        d0Var.setIsRecyclable(false);
        if (this.mItemAnimator.a(d0Var, cVar, cVar2)) {
            postAnimationRunner();
        }
    }

    void animateDisappearance(d0 d0Var, l.c cVar, l.c cVar2) {
        addAnimatingView(d0Var);
        d0Var.setIsRecyclable(false);
        if (this.mItemAnimator.b(d0Var, cVar, cVar2)) {
            postAnimationRunner();
        }
    }

    void assertInLayoutOrScroll(String str) {
        if (isComputingLayout()) {
            return;
        }
        if (str == null) {
            throw new IllegalStateException("Cannot call this method unless RecyclerView is computing a layout or scrolling" + exceptionLabel());
        }
        throw new IllegalStateException(str + exceptionLabel());
    }

    void assertNotInLayoutOrScroll(String str) {
        if (isComputingLayout()) {
            if (str == null) {
                throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + exceptionLabel());
            }
            throw new IllegalStateException(str);
        }
        if (this.mDispatchScrollCounter > 0) {
            Log.w(TAG, "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + exceptionLabel()));
        }
    }

    boolean canReuseUpdatedViewHolder(d0 d0Var) {
        l lVar = this.mItemAnimator;
        return lVar == null || lVar.a(d0Var, d0Var.getUnmodifiedPayloads());
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof p) && this.mLayout.a((p) layoutParams);
    }

    void clearOldPositions() {
        int b2 = this.mChildHelper.b();
        for (int i2 = 0; i2 < b2; i2++) {
            d0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.d(i2));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.clearOldPosition();
            }
        }
        this.mRecycler.b();
    }

    public void clearOnChildAttachStateChangeListeners() {
        List<q> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            list.clear();
        }
    }

    public void clearOnScrollListeners() {
        List<t> list = this.mScrollListeners;
        if (list != null) {
            list.clear();
        }
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        o oVar = this.mLayout;
        if (oVar != null && oVar.a()) {
            return this.mLayout.a(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        o oVar = this.mLayout;
        if (oVar != null && oVar.a()) {
            return this.mLayout.b(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        o oVar = this.mLayout;
        if (oVar != null && oVar.a()) {
            return this.mLayout.c(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        o oVar = this.mLayout;
        if (oVar != null && oVar.b()) {
            return this.mLayout.d(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        o oVar = this.mLayout;
        if (oVar != null && oVar.b()) {
            return this.mLayout.e(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        o oVar = this.mLayout;
        if (oVar != null && oVar.b()) {
            return this.mLayout.f(this.mState);
        }
        return 0;
    }

    void considerReleasingGlowsOnScroll(int i2, int i3) {
        boolean z2;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect == null || edgeEffect.isFinished() || i2 <= 0) {
            z2 = false;
        } else {
            this.mLeftGlow.onRelease();
            z2 = this.mLeftGlow.isFinished();
        }
        EdgeEffect edgeEffect2 = this.mRightGlow;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i2 < 0) {
            this.mRightGlow.onRelease();
            z2 |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i3 > 0) {
            this.mTopGlow.onRelease();
            z2 |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i3 < 0) {
            this.mBottomGlow.onRelease();
            z2 |= this.mBottomGlow.isFinished();
        }
        if (z2) {
            a.f.l.w.K(this);
        }
    }

    void consumePendingUpdateOperations() {
        if (this.mFirstLayoutComplete && !this.mDataSetHasChangedAfterLayout) {
            if (this.mAdapterHelper.c()) {
                if (this.mAdapterHelper.c(4) && !this.mAdapterHelper.c(11)) {
                    a.f.h.c.a(TRACE_HANDLE_ADAPTER_UPDATES_TAG);
                    startInterceptRequestLayout();
                    onEnterLayoutOrScroll();
                    this.mAdapterHelper.e();
                    if (!this.mLayoutWasDefered) {
                        if (hasUpdatedView()) {
                            dispatchLayout();
                        } else {
                            this.mAdapterHelper.a();
                        }
                    }
                    stopInterceptRequestLayout(true);
                    onExitLayoutOrScroll();
                    a.f.h.c.a();
                    return;
                }
                if (this.mAdapterHelper.c()) {
                    a.f.h.c.a(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
                    dispatchLayout();
                    a.f.h.c.a();
                    return;
                }
                return;
            }
            return;
        }
        a.f.h.c.a(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
        dispatchLayout();
        a.f.h.c.a();
    }

    void defaultOnMeasure(int i2, int i3) {
        setMeasuredDimension(o.a(i2, getPaddingLeft() + getPaddingRight(), a.f.l.w.s(this)), o.a(i3, getPaddingTop() + getPaddingBottom(), a.f.l.w.r(this)));
    }

    void dispatchChildAttached(View view) {
        d0 childViewHolderInt = getChildViewHolderInt(view);
        onChildAttachedToWindow(view);
        g gVar = this.mAdapter;
        if (gVar != null && childViewHolderInt != null) {
            gVar.onViewAttachedToWindow(childViewHolderInt);
        }
        List<q> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mOnChildAttachStateListeners.get(size).a(view);
            }
        }
    }

    void dispatchChildDetached(View view) {
        d0 childViewHolderInt = getChildViewHolderInt(view);
        onChildDetachedFromWindow(view);
        g gVar = this.mAdapter;
        if (gVar != null && childViewHolderInt != null) {
            gVar.onViewDetachedFromWindow(childViewHolderInt);
        }
        List<q> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mOnChildAttachStateListeners.get(size).b(view);
            }
        }
    }

    void dispatchLayout() {
        if (this.mAdapter == null) {
            Log.e(TAG, "No adapter attached; skipping layout");
            return;
        }
        if (this.mLayout == null) {
            Log.e(TAG, "No layout manager attached; skipping layout");
            return;
        }
        a0 a0Var = this.mState;
        a0Var.j = false;
        if (a0Var.f1690e == 1) {
            dispatchLayoutStep1();
            this.mLayout.e(this);
            dispatchLayoutStep2();
        } else if (!this.mAdapterHelper.d() && this.mLayout.r() == getWidth() && this.mLayout.h() == getHeight()) {
            this.mLayout.e(this);
        } else {
            this.mLayout.e(this);
            dispatchLayoutStep2();
        }
        dispatchLayoutStep3();
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f2, float f3, boolean z2) {
        return getScrollingChildHelper().a(f2, f3, z2);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f2, float f3) {
        return getScrollingChildHelper().a(f2, f3);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().a(i2, i3, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return getScrollingChildHelper().a(i2, i3, i4, i5, iArr);
    }

    void dispatchOnScrollStateChanged(int i2) {
        o oVar = this.mLayout;
        if (oVar != null) {
            oVar.g(i2);
        }
        onScrollStateChanged(i2);
        t tVar = this.mScrollListener;
        if (tVar != null) {
            tVar.onScrollStateChanged(this, i2);
        }
        List<t> list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mScrollListeners.get(size).onScrollStateChanged(this, i2);
            }
        }
    }

    void dispatchOnScrolled(int i2, int i3) {
        this.mDispatchScrollCounter++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i2, scrollY - i3);
        onScrolled(i2, i3);
        t tVar = this.mScrollListener;
        if (tVar != null) {
            tVar.onScrolled(this, i2, i3);
        }
        List<t> list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mScrollListeners.get(size).onScrolled(this, i2, i3);
            }
        }
        this.mDispatchScrollCounter--;
    }

    void dispatchPendingImportantForAccessibilityChanges() {
        int i2;
        for (int size = this.mPendingAccessibilityImportanceChange.size() - 1; size >= 0; size--) {
            d0 d0Var = this.mPendingAccessibilityImportanceChange.get(size);
            if (d0Var.itemView.getParent() == this && !d0Var.shouldIgnore() && (i2 = d0Var.mPendingAccessibilityState) != -1) {
                a.f.l.w.i(d0Var.itemView, i2);
                d0Var.mPendingAccessibilityState = -1;
            }
        }
        this.mPendingAccessibilityImportanceChange.clear();
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z2;
        boolean z3;
        super.draw(canvas);
        int size = this.mItemDecorations.size();
        boolean z4 = false;
        for (int i2 = 0; i2 < size; i2++) {
            this.mItemDecorations.get(i2).b(canvas, this, this.mState);
        }
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z2 = false;
        } else {
            int save = canvas.save();
            int paddingBottom = this.mClipToPadding ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.mLeftGlow;
            z2 = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(save);
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int save2 = canvas.save();
            if (this.mClipToPadding) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.mTopGlow;
            z2 |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(save2);
        }
        EdgeEffect edgeEffect5 = this.mRightGlow;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int save3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.mClipToPadding ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(-paddingTop, -width);
            EdgeEffect edgeEffect6 = this.mRightGlow;
            z2 |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(save3);
        }
        EdgeEffect edgeEffect7 = this.mBottomGlow;
        if (edgeEffect7 == null || edgeEffect7.isFinished()) {
            z3 = z2;
        } else {
            int save4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.mClipToPadding) {
                canvas.translate((-getWidth()) + getPaddingRight(), (-getHeight()) + getPaddingBottom());
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.mBottomGlow;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z4 = true;
            }
            z3 = z4 | z2;
            canvas.restoreToCount(save4);
        }
        if (!z3 && this.mItemAnimator != null && this.mItemDecorations.size() > 0 && this.mItemAnimator.g()) {
            z3 = true;
        }
        if (z3) {
            a.f.l.w.K(this);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j2) {
        return super.drawChild(canvas, view, j2);
    }

    void ensureBottomGlow() {
        if (this.mBottomGlow != null) {
            return;
        }
        this.mBottomGlow = this.mEdgeEffectFactory.a(this, 3);
        if (this.mClipToPadding) {
            this.mBottomGlow.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            this.mBottomGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    void ensureLeftGlow() {
        if (this.mLeftGlow != null) {
            return;
        }
        this.mLeftGlow = this.mEdgeEffectFactory.a(this, 0);
        if (this.mClipToPadding) {
            this.mLeftGlow.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            this.mLeftGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    void ensureRightGlow() {
        if (this.mRightGlow != null) {
            return;
        }
        this.mRightGlow = this.mEdgeEffectFactory.a(this, 2);
        if (this.mClipToPadding) {
            this.mRightGlow.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            this.mRightGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    void ensureTopGlow() {
        if (this.mTopGlow != null) {
            return;
        }
        this.mTopGlow = this.mEdgeEffectFactory.a(this, 1);
        if (this.mClipToPadding) {
            this.mTopGlow.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            this.mTopGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    String exceptionLabel() {
        return " " + super.toString() + ", adapter:" + this.mAdapter + ", layout:" + this.mLayout + ", context:" + getContext();
    }

    final void fillRemainingScrollValues(a0 a0Var) {
        if (getScrollState() == 2) {
            OverScroller overScroller = this.mViewFlinger.f1695d;
            a0Var.p = overScroller.getFinalX() - overScroller.getCurrX();
            a0Var.q = overScroller.getFinalY() - overScroller.getCurrY();
        } else {
            a0Var.p = 0;
            a0Var.q = 0;
        }
    }

    public View findChildViewUnder(float f2, float f3) {
        for (int a2 = this.mChildHelper.a() - 1; a2 >= 0; a2--) {
            View c2 = this.mChildHelper.c(a2);
            float translationX = c2.getTranslationX();
            float translationY = c2.getTranslationY();
            if (f2 >= c2.getLeft() + translationX && f2 <= c2.getRight() + translationX && f3 >= c2.getTop() + translationY && f3 <= c2.getBottom() + translationY) {
                return c2;
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:?, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View findContainingItemView(android.view.View r3) {
        /*
            r2 = this;
            android.view.ViewParent r0 = r3.getParent()
        L4:
            if (r0 == 0) goto L14
            if (r0 == r2) goto L14
            boolean r1 = r0 instanceof android.view.View
            if (r1 == 0) goto L14
            r3 = r0
            android.view.View r3 = (android.view.View) r3
            android.view.ViewParent r0 = r3.getParent()
            goto L4
        L14:
            if (r0 != r2) goto L17
            goto L18
        L17:
            r3 = 0
        L18:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.findContainingItemView(android.view.View):android.view.View");
    }

    public d0 findContainingViewHolder(View view) {
        View findContainingItemView = findContainingItemView(view);
        if (findContainingItemView == null) {
            return null;
        }
        return getChildViewHolder(findContainingItemView);
    }

    public d0 findViewHolderForAdapterPosition(int i2) {
        d0 d0Var = null;
        if (this.mDataSetHasChangedAfterLayout) {
            return null;
        }
        int b2 = this.mChildHelper.b();
        for (int i3 = 0; i3 < b2; i3++) {
            d0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.d(i3));
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && getAdapterPositionFor(childViewHolderInt) == i2) {
                if (!this.mChildHelper.c(childViewHolderInt.itemView)) {
                    return childViewHolderInt;
                }
                d0Var = childViewHolderInt;
            }
        }
        return d0Var;
    }

    public d0 findViewHolderForItemId(long j2) {
        g gVar = this.mAdapter;
        d0 d0Var = null;
        if (gVar != null && gVar.hasStableIds()) {
            int b2 = this.mChildHelper.b();
            for (int i2 = 0; i2 < b2; i2++) {
                d0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.d(i2));
                if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && childViewHolderInt.getItemId() == j2) {
                    if (!this.mChildHelper.c(childViewHolderInt.itemView)) {
                        return childViewHolderInt;
                    }
                    d0Var = childViewHolderInt;
                }
            }
        }
        return d0Var;
    }

    public d0 findViewHolderForLayoutPosition(int i2) {
        return findViewHolderForPosition(i2, false);
    }

    @Deprecated
    public d0 findViewHolderForPosition(int i2) {
        return findViewHolderForPosition(i2, false);
    }

    public boolean fling(int i2, int i3) {
        o oVar = this.mLayout;
        if (oVar == null) {
            Log.e(TAG, "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        }
        if (this.mLayoutSuppressed) {
            return false;
        }
        boolean a2 = oVar.a();
        boolean b2 = this.mLayout.b();
        if (!a2 || Math.abs(i2) < this.mMinFlingVelocity) {
            i2 = 0;
        }
        if (!b2 || Math.abs(i3) < this.mMinFlingVelocity) {
            i3 = 0;
        }
        if (i2 == 0 && i3 == 0) {
            return false;
        }
        float f2 = i2;
        float f3 = i3;
        if (!dispatchNestedPreFling(f2, f3)) {
            boolean z2 = a2 || b2;
            dispatchNestedFling(f2, f3, z2);
            r rVar = this.mOnFlingListener;
            if (rVar != null && rVar.a(i2, i3)) {
                return true;
            }
            if (z2) {
                int i4 = a2 ? 1 : 0;
                if (b2) {
                    i4 |= 2;
                }
                startNestedScroll(i4, 1);
                int i5 = this.mMaxFlingVelocity;
                int max = Math.max(-i5, Math.min(i2, i5));
                int i6 = this.mMaxFlingVelocity;
                this.mViewFlinger.a(max, Math.max(-i6, Math.min(i3, i6)));
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i2) {
        View view2;
        boolean z2;
        View d2 = this.mLayout.d(view, i2);
        if (d2 != null) {
            return d2;
        }
        boolean z3 = (this.mAdapter == null || this.mLayout == null || isComputingLayout() || this.mLayoutSuppressed) ? false : true;
        FocusFinder focusFinder = FocusFinder.getInstance();
        if (z3 && (i2 == 2 || i2 == 1)) {
            if (this.mLayout.b()) {
                int i3 = i2 == 2 ? GattError.GATT_WRONG_STATE : 33;
                z2 = focusFinder.findNextFocus(this, view, i3) == null;
                if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                    i2 = i3;
                }
            } else {
                z2 = false;
            }
            if (!z2 && this.mLayout.a()) {
                int i4 = (this.mLayout.k() == 1) ^ (i2 == 2) ? 66 : 17;
                z2 = focusFinder.findNextFocus(this, view, i4) == null;
                if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                    i2 = i4;
                }
            }
            if (z2) {
                consumePendingUpdateOperations();
                if (findContainingItemView(view) == null) {
                    return null;
                }
                startInterceptRequestLayout();
                this.mLayout.a(view, i2, this.mRecycler, this.mState);
                stopInterceptRequestLayout(false);
            }
            view2 = focusFinder.findNextFocus(this, view, i2);
        } else {
            View findNextFocus = focusFinder.findNextFocus(this, view, i2);
            if (findNextFocus == null && z3) {
                consumePendingUpdateOperations();
                if (findContainingItemView(view) == null) {
                    return null;
                }
                startInterceptRequestLayout();
                view2 = this.mLayout.a(view, i2, this.mRecycler, this.mState);
                stopInterceptRequestLayout(false);
            } else {
                view2 = findNextFocus;
            }
        }
        if (view2 == null || view2.hasFocusable()) {
            return isPreferredNextFocus(view, view2, i2) ? view2 : super.focusSearch(view, i2);
        }
        if (getFocusedChild() == null) {
            return super.focusSearch(view, i2);
        }
        requestChildOnScreen(view2, null);
        return view;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        o oVar = this.mLayout;
        if (oVar != null) {
            return oVar.c();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + exceptionLabel());
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        o oVar = this.mLayout;
        if (oVar != null) {
            return oVar.a(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + exceptionLabel());
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public g getAdapter() {
        return this.mAdapter;
    }

    int getAdapterPositionFor(d0 d0Var) {
        if (d0Var.hasAnyOfTheFlags(524) || !d0Var.isBound()) {
            return -1;
        }
        return this.mAdapterHelper.a(d0Var.mPosition);
    }

    @Override // android.view.View
    public int getBaseline() {
        o oVar = this.mLayout;
        if (oVar != null) {
            return oVar.d();
        }
        return super.getBaseline();
    }

    long getChangedHolderKey(d0 d0Var) {
        return this.mAdapter.hasStableIds() ? d0Var.getItemId() : d0Var.mPosition;
    }

    public int getChildAdapterPosition(View view) {
        d0 childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getAdapterPosition();
        }
        return -1;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        j jVar = this.mChildDrawingOrderCallback;
        if (jVar == null) {
            return super.getChildDrawingOrder(i2, i3);
        }
        return jVar.a(i2, i3);
    }

    public long getChildItemId(View view) {
        d0 childViewHolderInt;
        g gVar = this.mAdapter;
        if (gVar == null || !gVar.hasStableIds() || (childViewHolderInt = getChildViewHolderInt(view)) == null) {
            return -1L;
        }
        return childViewHolderInt.getItemId();
    }

    public int getChildLayoutPosition(View view) {
        d0 childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getLayoutPosition();
        }
        return -1;
    }

    @Deprecated
    public int getChildPosition(View view) {
        return getChildAdapterPosition(view);
    }

    public d0 getChildViewHolder(View view) {
        ViewParent parent = view.getParent();
        if (parent != null && parent != this) {
            throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
        }
        return getChildViewHolderInt(view);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.mClipToPadding;
    }

    public androidx.recyclerview.widget.s getCompatAccessibilityDelegate() {
        return this.mAccessibilityDelegate;
    }

    public void getDecoratedBoundsWithMargins(View view, Rect rect) {
        getDecoratedBoundsWithMarginsInt(view, rect);
    }

    public k getEdgeEffectFactory() {
        return this.mEdgeEffectFactory;
    }

    public l getItemAnimator() {
        return this.mItemAnimator;
    }

    Rect getItemDecorInsetsForChild(View view) {
        p pVar = (p) view.getLayoutParams();
        if (!pVar.f1724c) {
            return pVar.f1723b;
        }
        if (this.mState.d() && (pVar.b() || pVar.d())) {
            return pVar.f1723b;
        }
        Rect rect = pVar.f1723b;
        rect.set(0, 0, 0, 0);
        int size = this.mItemDecorations.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTempRect.set(0, 0, 0, 0);
            this.mItemDecorations.get(i2).a(this.mTempRect, view, this, this.mState);
            int i3 = rect.left;
            Rect rect2 = this.mTempRect;
            rect.left = i3 + rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        pVar.f1724c = false;
        return rect;
    }

    public n getItemDecorationAt(int i2) {
        int itemDecorationCount = getItemDecorationCount();
        if (i2 >= 0 && i2 < itemDecorationCount) {
            return this.mItemDecorations.get(i2);
        }
        throw new IndexOutOfBoundsException(i2 + " is an invalid index for size " + itemDecorationCount);
    }

    public int getItemDecorationCount() {
        return this.mItemDecorations.size();
    }

    public o getLayoutManager() {
        return this.mLayout;
    }

    public int getMaxFlingVelocity() {
        return this.mMaxFlingVelocity;
    }

    public int getMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getNanoTime() {
        if (ALLOW_THREAD_GAP_WORK) {
            return System.nanoTime();
        }
        return 0L;
    }

    public r getOnFlingListener() {
        return this.mOnFlingListener;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.mPreserveFocusAfterLayout;
    }

    public u getRecycledViewPool() {
        return this.mRecycler.d();
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    public boolean hasFixedSize() {
        return this.mHasFixedSize;
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().a();
    }

    public boolean hasPendingAdapterUpdates() {
        return !this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.c();
    }

    void initAdapterManager() {
        this.mAdapterHelper = new androidx.recyclerview.widget.a(new f());
    }

    void initFastScroller(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable != null && drawable != null && stateListDrawable2 != null && drawable2 != null) {
            Resources resources = getContext().getResources();
            new androidx.recyclerview.widget.h(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(a.m.b.fastscroll_default_thickness), resources.getDimensionPixelSize(a.m.b.fastscroll_minimum_range), resources.getDimensionPixelOffset(a.m.b.fastscroll_margin));
        } else {
            throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + exceptionLabel());
        }
    }

    void invalidateGlows() {
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }

    public void invalidateItemDecorations() {
        if (this.mItemDecorations.size() == 0) {
            return;
        }
        o oVar = this.mLayout;
        if (oVar != null) {
            oVar.a("Cannot invalidate item decorations during a scroll or layout");
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    boolean isAccessibilityEnabled() {
        AccessibilityManager accessibilityManager = this.mAccessibilityManager;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    public boolean isAnimating() {
        l lVar = this.mItemAnimator;
        return lVar != null && lVar.g();
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.mIsAttached;
    }

    public boolean isComputingLayout() {
        return this.mLayoutOrScrollCounter > 0;
    }

    @Deprecated
    public boolean isLayoutFrozen() {
        return isLayoutSuppressed();
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.mLayoutSuppressed;
    }

    @Override // android.view.View, a.f.l.l
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().b();
    }

    void jumpToPositionForSmoothScroller(int i2) {
        if (this.mLayout == null) {
            return;
        }
        setScrollState(2);
        this.mLayout.i(i2);
        awakenScrollBars();
    }

    void markItemDecorInsetsDirty() {
        int b2 = this.mChildHelper.b();
        for (int i2 = 0; i2 < b2; i2++) {
            ((p) this.mChildHelper.d(i2).getLayoutParams()).f1724c = true;
        }
        this.mRecycler.g();
    }

    void markKnownViewsInvalid() {
        int b2 = this.mChildHelper.b();
        for (int i2 = 0; i2 < b2; i2++) {
            d0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.d(i2));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.addFlags(6);
            }
        }
        markItemDecorInsetsDirty();
        this.mRecycler.h();
    }

    public void offsetChildrenHorizontal(int i2) {
        int a2 = this.mChildHelper.a();
        for (int i3 = 0; i3 < a2; i3++) {
            this.mChildHelper.c(i3).offsetLeftAndRight(i2);
        }
    }

    public void offsetChildrenVertical(int i2) {
        int a2 = this.mChildHelper.a();
        for (int i3 = 0; i3 < a2; i3++) {
            this.mChildHelper.c(i3).offsetTopAndBottom(i2);
        }
    }

    void offsetPositionRecordsForInsert(int i2, int i3) {
        int b2 = this.mChildHelper.b();
        for (int i4 = 0; i4 < b2; i4++) {
            d0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.d(i4));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= i2) {
                childViewHolderInt.offsetPosition(i3, false);
                this.mState.g = true;
            }
        }
        this.mRecycler.a(i2, i3);
        requestLayout();
    }

    void offsetPositionRecordsForMove(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int b2 = this.mChildHelper.b();
        if (i2 < i3) {
            i5 = i2;
            i4 = i3;
            i6 = -1;
        } else {
            i4 = i2;
            i5 = i3;
            i6 = 1;
        }
        for (int i8 = 0; i8 < b2; i8++) {
            d0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.d(i8));
            if (childViewHolderInt != null && (i7 = childViewHolderInt.mPosition) >= i5 && i7 <= i4) {
                if (i7 == i2) {
                    childViewHolderInt.offsetPosition(i3 - i2, false);
                } else {
                    childViewHolderInt.offsetPosition(i6, false);
                }
                this.mState.g = true;
            }
        }
        this.mRecycler.b(i2, i3);
        requestLayout();
    }

    void offsetPositionRecordsForRemove(int i2, int i3, boolean z2) {
        int i4 = i2 + i3;
        int b2 = this.mChildHelper.b();
        for (int i5 = 0; i5 < b2; i5++) {
            d0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.d(i5));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                int i6 = childViewHolderInt.mPosition;
                if (i6 >= i4) {
                    childViewHolderInt.offsetPosition(-i3, z2);
                    this.mState.g = true;
                } else if (i6 >= i2) {
                    childViewHolderInt.flagRemovedAndOffsetPosition(i2 - 1, -i3, z2);
                    this.mState.g = true;
                }
            }
        }
        this.mRecycler.a(i2, i3, z2);
        requestLayout();
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x004f, code lost:
    
        if (r0 >= 30.0f) goto L22;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onAttachedToWindow() {
        /*
            r4 = this;
            super.onAttachedToWindow()
            r0 = 0
            r4.mLayoutOrScrollCounter = r0
            r1 = 1
            r4.mIsAttached = r1
            boolean r2 = r4.mFirstLayoutComplete
            if (r2 == 0) goto L14
            boolean r2 = r4.isLayoutRequested()
            if (r2 != 0) goto L14
            goto L15
        L14:
            r1 = 0
        L15:
            r4.mFirstLayoutComplete = r1
            androidx.recyclerview.widget.RecyclerView$o r1 = r4.mLayout
            if (r1 == 0) goto L1e
            r1.a(r4)
        L1e:
            r4.mPostedAnimatorRunner = r0
            boolean r0 = androidx.recyclerview.widget.RecyclerView.ALLOW_THREAD_GAP_WORK
            if (r0 == 0) goto L67
            java.lang.ThreadLocal<androidx.recyclerview.widget.i> r0 = androidx.recyclerview.widget.i.f1872f
            java.lang.Object r0 = r0.get()
            androidx.recyclerview.widget.i r0 = (androidx.recyclerview.widget.i) r0
            r4.mGapWorker = r0
            androidx.recyclerview.widget.i r0 = r4.mGapWorker
            if (r0 != 0) goto L62
            androidx.recyclerview.widget.i r0 = new androidx.recyclerview.widget.i
            r0.<init>()
            r4.mGapWorker = r0
            android.view.Display r0 = a.f.l.w.k(r4)
            r1 = 1114636288(0x42700000, float:60.0)
            boolean r2 = r4.isInEditMode()
            if (r2 != 0) goto L52
            if (r0 == 0) goto L52
            float r0 = r0.getRefreshRate()
            r2 = 1106247680(0x41f00000, float:30.0)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 < 0) goto L52
            goto L54
        L52:
            r0 = 1114636288(0x42700000, float:60.0)
        L54:
            androidx.recyclerview.widget.i r1 = r4.mGapWorker
            r2 = 1315859240(0x4e6e6b28, float:1.0E9)
            float r2 = r2 / r0
            long r2 = (long) r2
            r1.f1875d = r2
            java.lang.ThreadLocal<androidx.recyclerview.widget.i> r0 = androidx.recyclerview.widget.i.f1872f
            r0.set(r1)
        L62:
            androidx.recyclerview.widget.i r0 = r4.mGapWorker
            r0.a(r4)
        L67:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onAttachedToWindow():void");
    }

    public void onChildAttachedToWindow(View view) {
    }

    public void onChildDetachedFromWindow(View view) {
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        androidx.recyclerview.widget.i iVar;
        super.onDetachedFromWindow();
        l lVar = this.mItemAnimator;
        if (lVar != null) {
            lVar.b();
        }
        stopScroll();
        this.mIsAttached = false;
        o oVar = this.mLayout;
        if (oVar != null) {
            oVar.a(this, this.mRecycler);
        }
        this.mPendingAccessibilityImportanceChange.clear();
        removeCallbacks(this.mItemAnimatorRunner);
        this.mViewInfoStore.b();
        if (!ALLOW_THREAD_GAP_WORK || (iVar = this.mGapWorker) == null) {
            return;
        }
        iVar.b(this);
        this.mGapWorker = null;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.mItemDecorations.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mItemDecorations.get(i2).a(canvas, this, this.mState);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onEnterLayoutOrScroll() {
        this.mLayoutOrScrollCounter++;
    }

    void onExitLayoutOrScroll() {
        onExitLayoutOrScroll(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0066  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onGenericMotionEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            androidx.recyclerview.widget.RecyclerView$o r0 = r5.mLayout
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            boolean r0 = r5.mLayoutSuppressed
            if (r0 == 0) goto Lb
            return r1
        Lb:
            int r0 = r6.getAction()
            r2 = 8
            if (r0 != r2) goto L77
            int r0 = r6.getSource()
            r0 = r0 & 2
            r2 = 0
            if (r0 == 0) goto L3c
            androidx.recyclerview.widget.RecyclerView$o r0 = r5.mLayout
            boolean r0 = r0.b()
            if (r0 == 0) goto L2c
            r0 = 9
            float r0 = r6.getAxisValue(r0)
            float r0 = -r0
            goto L2d
        L2c:
            r0 = 0
        L2d:
            androidx.recyclerview.widget.RecyclerView$o r3 = r5.mLayout
            boolean r3 = r3.a()
            if (r3 == 0) goto L61
            r3 = 10
            float r3 = r6.getAxisValue(r3)
            goto L62
        L3c:
            int r0 = r6.getSource()
            r3 = 4194304(0x400000, float:5.877472E-39)
            r0 = r0 & r3
            if (r0 == 0) goto L60
            r0 = 26
            float r0 = r6.getAxisValue(r0)
            androidx.recyclerview.widget.RecyclerView$o r3 = r5.mLayout
            boolean r3 = r3.b()
            if (r3 == 0) goto L55
            float r0 = -r0
            goto L61
        L55:
            androidx.recyclerview.widget.RecyclerView$o r3 = r5.mLayout
            boolean r3 = r3.a()
            if (r3 == 0) goto L60
            r3 = r0
            r0 = 0
            goto L62
        L60:
            r0 = 0
        L61:
            r3 = 0
        L62:
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L6a
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 == 0) goto L77
        L6a:
            float r2 = r5.mScaledHorizontalScrollFactor
            float r3 = r3 * r2
            int r2 = (int) r3
            float r3 = r5.mScaledVerticalScrollFactor
            float r0 = r0 * r3
            int r0 = (int) r0
            r5.scrollByInternal(r2, r0, r6)
        L77:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z2;
        if (this.mLayoutSuppressed) {
            return false;
        }
        this.mInterceptingOnItemTouchListener = null;
        if (findInterceptingOnItemTouchListener(motionEvent)) {
            cancelScroll();
            return true;
        }
        o oVar = this.mLayout;
        if (oVar == null) {
            return false;
        }
        boolean a2 = oVar.a();
        boolean b2 = this.mLayout.b();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.mIgnoreMotionEventTillDown) {
                this.mIgnoreMotionEventTillDown = false;
            }
            this.mScrollPointerId = motionEvent.getPointerId(0);
            int x2 = (int) (motionEvent.getX() + 0.5f);
            this.mLastTouchX = x2;
            this.mInitialTouchX = x2;
            int y2 = (int) (motionEvent.getY() + 0.5f);
            this.mLastTouchY = y2;
            this.mInitialTouchY = y2;
            if (this.mScrollState == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                setScrollState(1);
                stopNestedScroll(1);
            }
            int[] iArr = this.mNestedOffsets;
            iArr[1] = 0;
            iArr[0] = 0;
            int i2 = a2 ? 1 : 0;
            if (b2) {
                i2 |= 2;
            }
            startNestedScroll(i2, 0);
        } else if (actionMasked == 1) {
            this.mVelocityTracker.clear();
            stopNestedScroll(0);
        } else if (actionMasked == 2) {
            int findPointerIndex = motionEvent.findPointerIndex(this.mScrollPointerId);
            if (findPointerIndex < 0) {
                Log.e(TAG, "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                return false;
            }
            int x3 = (int) (motionEvent.getX(findPointerIndex) + 0.5f);
            int y3 = (int) (motionEvent.getY(findPointerIndex) + 0.5f);
            if (this.mScrollState != 1) {
                int i3 = x3 - this.mInitialTouchX;
                int i4 = y3 - this.mInitialTouchY;
                if (!a2 || Math.abs(i3) <= this.mTouchSlop) {
                    z2 = false;
                } else {
                    this.mLastTouchX = x3;
                    z2 = true;
                }
                if (b2 && Math.abs(i4) > this.mTouchSlop) {
                    this.mLastTouchY = y3;
                    z2 = true;
                }
                if (z2) {
                    setScrollState(1);
                }
            }
        } else if (actionMasked == 3) {
            cancelScroll();
        } else if (actionMasked == 5) {
            this.mScrollPointerId = motionEvent.getPointerId(actionIndex);
            int x4 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.mLastTouchX = x4;
            this.mInitialTouchX = x4;
            int y4 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.mLastTouchY = y4;
            this.mInitialTouchY = y4;
        } else if (actionMasked == 6) {
            onPointerUp(motionEvent);
        }
        return this.mScrollState == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        a.f.h.c.a(TRACE_ON_LAYOUT_TAG);
        dispatchLayout();
        a.f.h.c.a();
        this.mFirstLayoutComplete = true;
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        o oVar = this.mLayout;
        if (oVar == null) {
            defaultOnMeasure(i2, i3);
            return;
        }
        boolean z2 = false;
        if (oVar.v()) {
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.mLayout.a(this.mRecycler, this.mState, i2, i3);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z2 = true;
            }
            if (z2 || this.mAdapter == null) {
                return;
            }
            if (this.mState.f1690e == 1) {
                dispatchLayoutStep1();
            }
            this.mLayout.b(i2, i3);
            this.mState.j = true;
            dispatchLayoutStep2();
            this.mLayout.d(i2, i3);
            if (this.mLayout.B()) {
                this.mLayout.b(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                this.mState.j = true;
                dispatchLayoutStep2();
                this.mLayout.d(i2, i3);
                return;
            }
            return;
        }
        if (this.mHasFixedSize) {
            this.mLayout.a(this.mRecycler, this.mState, i2, i3);
            return;
        }
        if (this.mAdapterUpdateDuringMeasure) {
            startInterceptRequestLayout();
            onEnterLayoutOrScroll();
            processAdapterUpdatesAndSetAnimationFlags();
            onExitLayoutOrScroll();
            a0 a0Var = this.mState;
            if (a0Var.l) {
                a0Var.h = true;
            } else {
                this.mAdapterHelper.b();
                this.mState.h = false;
            }
            this.mAdapterUpdateDuringMeasure = false;
            stopInterceptRequestLayout(false);
        } else if (this.mState.l) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        g gVar = this.mAdapter;
        if (gVar != null) {
            this.mState.f1691f = gVar.getItemCount();
        } else {
            this.mState.f1691f = 0;
        }
        startInterceptRequestLayout();
        this.mLayout.a(this.mRecycler, this.mState, i2, i3);
        stopInterceptRequestLayout(false);
        this.mState.h = false;
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (isComputingLayout()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i2, rect);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof y)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        this.mPendingSavedState = (y) parcelable;
        super.onRestoreInstanceState(this.mPendingSavedState.d());
        o oVar = this.mLayout;
        if (oVar == null || (parcelable2 = this.mPendingSavedState.f1739d) == null) {
            return;
        }
        oVar.a(parcelable2);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        y yVar = new y(super.onSaveInstanceState());
        y yVar2 = this.mPendingSavedState;
        if (yVar2 != null) {
            yVar.a(yVar2);
        } else {
            o oVar = this.mLayout;
            if (oVar != null) {
                yVar.f1739d = oVar.y();
            } else {
                yVar.f1739d = null;
            }
        }
        return yVar;
    }

    public void onScrollStateChanged(int i2) {
    }

    public void onScrolled(int i2, int i3) {
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 == i4 && i3 == i5) {
            return;
        }
        invalidateGlows();
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00f8  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            Method dump skipped, instructions count: 489
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    void postAnimationRunner() {
        if (this.mPostedAnimatorRunner || !this.mIsAttached) {
            return;
        }
        a.f.l.w.a(this, this.mItemAnimatorRunner);
        this.mPostedAnimatorRunner = true;
    }

    void processDataSetCompletelyChanged(boolean z2) {
        this.mDispatchItemsChangedEvent = z2 | this.mDispatchItemsChangedEvent;
        this.mDataSetHasChangedAfterLayout = true;
        markKnownViewsInvalid();
    }

    void recordAnimationInfoIfBouncedHiddenView(d0 d0Var, l.c cVar) {
        d0Var.setFlags(0, DfuBaseService.ERROR_REMOTE_MASK);
        if (this.mState.i && d0Var.isUpdated() && !d0Var.isRemoved() && !d0Var.shouldIgnore()) {
            this.mViewInfoStore.a(getChangedHolderKey(d0Var), d0Var);
        }
        this.mViewInfoStore.c(d0Var, cVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeAndRecycleViews() {
        l lVar = this.mItemAnimator;
        if (lVar != null) {
            lVar.b();
        }
        o oVar = this.mLayout;
        if (oVar != null) {
            oVar.b(this.mRecycler);
            this.mLayout.c(this.mRecycler);
        }
        this.mRecycler.a();
    }

    boolean removeAnimatingView(View view) {
        startInterceptRequestLayout();
        boolean e2 = this.mChildHelper.e(view);
        if (e2) {
            d0 childViewHolderInt = getChildViewHolderInt(view);
            this.mRecycler.c(childViewHolderInt);
            this.mRecycler.b(childViewHolderInt);
        }
        stopInterceptRequestLayout(!e2);
        return e2;
    }

    @Override // android.view.ViewGroup
    protected void removeDetachedView(View view, boolean z2) {
        d0 childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.isTmpDetached()) {
                childViewHolderInt.clearTmpDetachFlag();
            } else if (!childViewHolderInt.shouldIgnore()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + childViewHolderInt + exceptionLabel());
            }
        }
        view.clearAnimation();
        dispatchChildDetached(view);
        super.removeDetachedView(view, z2);
    }

    public void removeItemDecoration(n nVar) {
        o oVar = this.mLayout;
        if (oVar != null) {
            oVar.a("Cannot remove item decoration during a scroll  or layout");
        }
        this.mItemDecorations.remove(nVar);
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void removeItemDecorationAt(int i2) {
        int itemDecorationCount = getItemDecorationCount();
        if (i2 >= 0 && i2 < itemDecorationCount) {
            removeItemDecoration(getItemDecorationAt(i2));
            return;
        }
        throw new IndexOutOfBoundsException(i2 + " is an invalid index for size " + itemDecorationCount);
    }

    public void removeOnChildAttachStateChangeListener(q qVar) {
        List<q> list = this.mOnChildAttachStateListeners;
        if (list == null) {
            return;
        }
        list.remove(qVar);
    }

    public void removeOnItemTouchListener(s sVar) {
        this.mOnItemTouchListeners.remove(sVar);
        if (this.mInterceptingOnItemTouchListener == sVar) {
            this.mInterceptingOnItemTouchListener = null;
        }
    }

    public void removeOnScrollListener(t tVar) {
        List<t> list = this.mScrollListeners;
        if (list != null) {
            list.remove(tVar);
        }
    }

    void repositionShadowingViews() {
        d0 d0Var;
        int a2 = this.mChildHelper.a();
        for (int i2 = 0; i2 < a2; i2++) {
            View c2 = this.mChildHelper.c(i2);
            d0 childViewHolder = getChildViewHolder(c2);
            if (childViewHolder != null && (d0Var = childViewHolder.mShadowingHolder) != null) {
                View view = d0Var.itemView;
                int left = c2.getLeft();
                int top = c2.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.mLayout.a(this, this.mState, view, view2) && view2 != null) {
            requestChildOnScreen(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z2) {
        return this.mLayout.a(this, view, rect, z2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z2) {
        int size = this.mOnItemTouchListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mOnItemTouchListeners.get(i2).onRequestDisallowInterceptTouchEvent(z2);
        }
        super.requestDisallowInterceptTouchEvent(z2);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.mInterceptRequestLayoutDepth == 0 && !this.mLayoutSuppressed) {
            super.requestLayout();
        } else {
            this.mLayoutWasDefered = true;
        }
    }

    void saveOldPositions() {
        int b2 = this.mChildHelper.b();
        for (int i2 = 0; i2 < b2; i2++) {
            d0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.d(i2));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.saveOldPosition();
            }
        }
    }

    @Override // android.view.View
    public void scrollBy(int i2, int i3) {
        o oVar = this.mLayout;
        if (oVar == null) {
            Log.e(TAG, "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.mLayoutSuppressed) {
            return;
        }
        boolean a2 = oVar.a();
        boolean b2 = this.mLayout.b();
        if (a2 || b2) {
            if (!a2) {
                i2 = 0;
            }
            if (!b2) {
                i3 = 0;
            }
            scrollByInternal(i2, i3, null);
        }
    }

    boolean scrollByInternal(int i2, int i3, MotionEvent motionEvent) {
        int i4;
        int i5;
        int i6;
        int i7;
        consumePendingUpdateOperations();
        if (this.mAdapter != null) {
            int[] iArr = this.mReusableIntPair;
            iArr[0] = 0;
            iArr[1] = 0;
            scrollStep(i2, i3, iArr);
            int[] iArr2 = this.mReusableIntPair;
            int i8 = iArr2[0];
            int i9 = iArr2[1];
            i5 = i9;
            i4 = i8;
            i6 = i2 - i8;
            i7 = i3 - i9;
        } else {
            i4 = 0;
            i5 = 0;
            i6 = 0;
            i7 = 0;
        }
        if (!this.mItemDecorations.isEmpty()) {
            invalidate();
        }
        int[] iArr3 = this.mReusableIntPair;
        iArr3[0] = 0;
        iArr3[1] = 0;
        int i10 = i4;
        dispatchNestedScroll(i4, i5, i6, i7, this.mScrollOffset, 0, iArr3);
        int[] iArr4 = this.mReusableIntPair;
        int i11 = i6 - iArr4[0];
        int i12 = i7 - iArr4[1];
        boolean z2 = (iArr4[0] == 0 && iArr4[1] == 0) ? false : true;
        int i13 = this.mLastTouchX;
        int[] iArr5 = this.mScrollOffset;
        this.mLastTouchX = i13 - iArr5[0];
        this.mLastTouchY -= iArr5[1];
        int[] iArr6 = this.mNestedOffsets;
        iArr6[0] = iArr6[0] + iArr5[0];
        iArr6[1] = iArr6[1] + iArr5[1];
        if (getOverScrollMode() != 2) {
            if (motionEvent != null && !a.f.l.i.d(motionEvent, 8194)) {
                pullGlows(motionEvent.getX(), i11, motionEvent.getY(), i12);
            }
            considerReleasingGlowsOnScroll(i2, i3);
        }
        if (i10 != 0 || i5 != 0) {
            dispatchOnScrolled(i10, i5);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        return (!z2 && i10 == 0 && i5 == 0) ? false : true;
    }

    void scrollStep(int i2, int i3, int[] iArr) {
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        a.f.h.c.a(TRACE_SCROLL_TAG);
        fillRemainingScrollValues(this.mState);
        int a2 = i2 != 0 ? this.mLayout.a(i2, this.mRecycler, this.mState) : 0;
        int b2 = i3 != 0 ? this.mLayout.b(i3, this.mRecycler, this.mState) : 0;
        a.f.h.c.a();
        repositionShadowingViews();
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        if (iArr != null) {
            iArr[0] = a2;
            iArr[1] = b2;
        }
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        Log.w(TAG, "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollToPosition(int i2) {
        if (this.mLayoutSuppressed) {
            return;
        }
        stopScroll();
        o oVar = this.mLayout;
        if (oVar == null) {
            Log.e(TAG, "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            oVar.i(i2);
            awakenScrollBars();
        }
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (shouldDeferAccessibilityEvent(accessibilityEvent)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    public void setAccessibilityDelegateCompat(androidx.recyclerview.widget.s sVar) {
        this.mAccessibilityDelegate = sVar;
        a.f.l.w.a(this, this.mAccessibilityDelegate);
    }

    public void setAdapter(g gVar) {
        setLayoutFrozen(false);
        setAdapterInternal(gVar, false, true);
        processDataSetCompletelyChanged(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(j jVar) {
        if (jVar == this.mChildDrawingOrderCallback) {
            return;
        }
        this.mChildDrawingOrderCallback = jVar;
        setChildrenDrawingOrderEnabled(this.mChildDrawingOrderCallback != null);
    }

    boolean setChildImportantForAccessibilityInternal(d0 d0Var, int i2) {
        if (isComputingLayout()) {
            d0Var.mPendingAccessibilityState = i2;
            this.mPendingAccessibilityImportanceChange.add(d0Var);
            return false;
        }
        a.f.l.w.i(d0Var.itemView, i2);
        return true;
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z2) {
        if (z2 != this.mClipToPadding) {
            invalidateGlows();
        }
        this.mClipToPadding = z2;
        super.setClipToPadding(z2);
        if (this.mFirstLayoutComplete) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(k kVar) {
        a.f.k.h.a(kVar);
        this.mEdgeEffectFactory = kVar;
        invalidateGlows();
    }

    public void setHasFixedSize(boolean z2) {
        this.mHasFixedSize = z2;
    }

    public void setItemAnimator(l lVar) {
        l lVar2 = this.mItemAnimator;
        if (lVar2 != null) {
            lVar2.b();
            this.mItemAnimator.a((l.b) null);
        }
        this.mItemAnimator = lVar;
        l lVar3 = this.mItemAnimator;
        if (lVar3 != null) {
            lVar3.a(this.mItemAnimatorListener);
        }
    }

    public void setItemViewCacheSize(int i2) {
        this.mRecycler.f(i2);
    }

    @Deprecated
    public void setLayoutFrozen(boolean z2) {
        suppressLayout(z2);
    }

    public void setLayoutManager(o oVar) {
        if (oVar == this.mLayout) {
            return;
        }
        stopScroll();
        if (this.mLayout != null) {
            l lVar = this.mItemAnimator;
            if (lVar != null) {
                lVar.b();
            }
            this.mLayout.b(this.mRecycler);
            this.mLayout.c(this.mRecycler);
            this.mRecycler.a();
            if (this.mIsAttached) {
                this.mLayout.a(this, this.mRecycler);
            }
            this.mLayout.f((RecyclerView) null);
            this.mLayout = null;
        } else {
            this.mRecycler.a();
        }
        this.mChildHelper.c();
        this.mLayout = oVar;
        if (oVar != null) {
            if (oVar.f1711b == null) {
                this.mLayout.f(this);
                if (this.mIsAttached) {
                    this.mLayout.a(this);
                }
            } else {
                throw new IllegalArgumentException("LayoutManager " + oVar + " is already attached to a RecyclerView:" + oVar.f1711b.exceptionLabel());
            }
        }
        this.mRecycler.j();
        requestLayout();
    }

    @Override // android.view.ViewGroup
    @Deprecated
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        if (Build.VERSION.SDK_INT < 18) {
            if (layoutTransition == null) {
                suppressLayout(false);
                return;
            } else if (layoutTransition.getAnimator(0) == null && layoutTransition.getAnimator(1) == null && layoutTransition.getAnimator(2) == null && layoutTransition.getAnimator(3) == null && layoutTransition.getAnimator(4) == null) {
                suppressLayout(true);
                return;
            }
        }
        if (layoutTransition == null) {
            super.setLayoutTransition(null);
            return;
        }
        throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z2) {
        getScrollingChildHelper().a(z2);
    }

    public void setOnFlingListener(r rVar) {
        this.mOnFlingListener = rVar;
    }

    @Deprecated
    public void setOnScrollListener(t tVar) {
        this.mScrollListener = tVar;
    }

    public void setPreserveFocusAfterLayout(boolean z2) {
        this.mPreserveFocusAfterLayout = z2;
    }

    public void setRecycledViewPool(u uVar) {
        this.mRecycler.a(uVar);
    }

    public void setRecyclerListener(w wVar) {
        this.mRecyclerListener = wVar;
    }

    void setScrollState(int i2) {
        if (i2 == this.mScrollState) {
            return;
        }
        this.mScrollState = i2;
        if (i2 != 2) {
            stopScrollersInternal();
        }
        dispatchOnScrollStateChanged(i2);
    }

    public void setScrollingTouchSlop(int i2) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i2 != 0) {
            if (i2 != 1) {
                Log.w(TAG, "setScrollingTouchSlop(): bad argument constant " + i2 + "; using default value");
            } else {
                this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
                return;
            }
        }
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(b0 b0Var) {
        this.mRecycler.a(b0Var);
    }

    boolean shouldDeferAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (!isComputingLayout()) {
            return false;
        }
        int a2 = accessibilityEvent != null ? a.f.l.f0.b.a(accessibilityEvent) : 0;
        if (a2 == 0) {
            a2 = 0;
        }
        this.mEatenAccessibilityChangeFlags = a2 | this.mEatenAccessibilityChangeFlags;
        return true;
    }

    public void smoothScrollBy(int i2, int i3) {
        smoothScrollBy(i2, i3, null);
    }

    public void smoothScrollToPosition(int i2) {
        if (this.mLayoutSuppressed) {
            return;
        }
        o oVar = this.mLayout;
        if (oVar == null) {
            Log.e(TAG, "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            oVar.a(this, this.mState, i2);
        }
    }

    void startInterceptRequestLayout() {
        this.mInterceptRequestLayoutDepth++;
        if (this.mInterceptRequestLayoutDepth != 1 || this.mLayoutSuppressed) {
            return;
        }
        this.mLayoutWasDefered = false;
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i2) {
        return getScrollingChildHelper().b(i2);
    }

    void stopInterceptRequestLayout(boolean z2) {
        if (this.mInterceptRequestLayoutDepth < 1) {
            this.mInterceptRequestLayoutDepth = 1;
        }
        if (!z2 && !this.mLayoutSuppressed) {
            this.mLayoutWasDefered = false;
        }
        if (this.mInterceptRequestLayoutDepth == 1) {
            if (z2 && this.mLayoutWasDefered && !this.mLayoutSuppressed && this.mLayout != null && this.mAdapter != null) {
                dispatchLayout();
            }
            if (!this.mLayoutSuppressed) {
                this.mLayoutWasDefered = false;
            }
        }
        this.mInterceptRequestLayoutDepth--;
    }

    @Override // android.view.View, a.f.l.l
    public void stopNestedScroll() {
        getScrollingChildHelper().c();
    }

    public void stopScroll() {
        setScrollState(0);
        stopScrollersInternal();
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z2) {
        if (z2 != this.mLayoutSuppressed) {
            assertNotInLayoutOrScroll("Do not suppressLayout in layout or scroll");
            if (!z2) {
                this.mLayoutSuppressed = false;
                if (this.mLayoutWasDefered && this.mLayout != null && this.mAdapter != null) {
                    requestLayout();
                }
                this.mLayoutWasDefered = false;
                return;
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
            this.mLayoutSuppressed = true;
            this.mIgnoreMotionEventTillDown = true;
            stopScroll();
        }
    }

    public void swapAdapter(g gVar, boolean z2) {
        setLayoutFrozen(false);
        setAdapterInternal(gVar, true, z2);
        processDataSetCompletelyChanged(true);
        requestLayout();
    }

    void viewRangeUpdate(int i2, int i3, Object obj) {
        int i4;
        int b2 = this.mChildHelper.b();
        int i5 = i2 + i3;
        for (int i6 = 0; i6 < b2; i6++) {
            View d2 = this.mChildHelper.d(i6);
            d0 childViewHolderInt = getChildViewHolderInt(d2);
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && (i4 = childViewHolderInt.mPosition) >= i2 && i4 < i5) {
                childViewHolderInt.addFlags(2);
                childViewHolderInt.addChangePayload(obj);
                ((p) d2.getLayoutParams()).f1724c = true;
            }
        }
        this.mRecycler.c(i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f implements a.InterfaceC0059a {
        f() {
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0059a
        public d0 a(int i) {
            d0 findViewHolderForPosition = RecyclerView.this.findViewHolderForPosition(i, true);
            if (findViewHolderForPosition == null || RecyclerView.this.mChildHelper.c(findViewHolderForPosition.itemView)) {
                return null;
            }
            return findViewHolderForPosition;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0059a
        public void b(int i, int i2) {
            RecyclerView.this.offsetPositionRecordsForRemove(i, i2, false);
            RecyclerView.this.mItemsAddedOrRemoved = true;
        }

        void c(a.b bVar) {
            int i = bVar.f1784a;
            if (i == 1) {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.mLayout.a(recyclerView, bVar.f1785b, bVar.f1787d);
                return;
            }
            if (i == 2) {
                RecyclerView recyclerView2 = RecyclerView.this;
                recyclerView2.mLayout.b(recyclerView2, bVar.f1785b, bVar.f1787d);
            } else if (i == 4) {
                RecyclerView recyclerView3 = RecyclerView.this;
                recyclerView3.mLayout.a(recyclerView3, bVar.f1785b, bVar.f1787d, bVar.f1786c);
            } else {
                if (i != 8) {
                    return;
                }
                RecyclerView recyclerView4 = RecyclerView.this;
                recyclerView4.mLayout.a(recyclerView4, bVar.f1785b, bVar.f1787d, 1);
            }
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0059a
        public void d(int i, int i2) {
            RecyclerView.this.offsetPositionRecordsForRemove(i, i2, true);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.mItemsAddedOrRemoved = true;
            recyclerView.mState.f1689d += i2;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0059a
        public void a(int i, int i2, Object obj) {
            RecyclerView.this.viewRangeUpdate(i, i2, obj);
            RecyclerView.this.mItemsChanged = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0059a
        public void b(a.b bVar) {
            c(bVar);
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0059a
        public void a(a.b bVar) {
            c(bVar);
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0059a
        public void a(int i, int i2) {
            RecyclerView.this.offsetPositionRecordsForMove(i, i2);
            RecyclerView.this.mItemsAddedOrRemoved = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0059a
        public void c(int i, int i2) {
            RecyclerView.this.offsetPositionRecordsForInsert(i, i2);
            RecyclerView.this.mItemsAddedOrRemoved = true;
        }
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.m.a.recyclerViewStyle);
    }

    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        return getScrollingChildHelper().a(i2, i3, iArr, iArr2, i4);
    }

    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr, int i6) {
        return getScrollingChildHelper().a(i2, i3, i4, i5, iArr, i6);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0036 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    androidx.recyclerview.widget.RecyclerView.d0 findViewHolderForPosition(int r6, boolean r7) {
        /*
            r5 = this;
            androidx.recyclerview.widget.d r0 = r5.mChildHelper
            int r0 = r0.b()
            r1 = 0
            r2 = 0
        L8:
            if (r2 >= r0) goto L3a
            androidx.recyclerview.widget.d r3 = r5.mChildHelper
            android.view.View r3 = r3.d(r2)
            androidx.recyclerview.widget.RecyclerView$d0 r3 = getChildViewHolderInt(r3)
            if (r3 == 0) goto L37
            boolean r4 = r3.isRemoved()
            if (r4 != 0) goto L37
            if (r7 == 0) goto L23
            int r4 = r3.mPosition
            if (r4 == r6) goto L2a
            goto L37
        L23:
            int r4 = r3.getLayoutPosition()
            if (r4 == r6) goto L2a
            goto L37
        L2a:
            androidx.recyclerview.widget.d r1 = r5.mChildHelper
            android.view.View r4 = r3.itemView
            boolean r1 = r1.c(r4)
            if (r1 == 0) goto L36
            r1 = r3
            goto L37
        L36:
            return r3
        L37:
            int r2 = r2 + 1
            goto L8
        L3a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.findViewHolderForPosition(int, boolean):androidx.recyclerview.widget.RecyclerView$d0");
    }

    public boolean hasNestedScrollingParent(int i2) {
        return getScrollingChildHelper().a(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onExitLayoutOrScroll(boolean z2) {
        this.mLayoutOrScrollCounter--;
        if (this.mLayoutOrScrollCounter < 1) {
            this.mLayoutOrScrollCounter = 0;
            if (z2) {
                dispatchContentChangedIfNecessary();
                dispatchPendingImportantForAccessibilityChanges();
            }
        }
    }

    public void smoothScrollBy(int i2, int i3, Interpolator interpolator) {
        smoothScrollBy(i2, i3, interpolator, UNDEFINED_DURATION);
    }

    public boolean startNestedScroll(int i2, int i3) {
        return getScrollingChildHelper().a(i2, i3);
    }

    public void stopNestedScroll(int i2) {
        getScrollingChildHelper().c(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c0 implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        private int f1693b;

        /* renamed from: c, reason: collision with root package name */
        private int f1694c;

        /* renamed from: d, reason: collision with root package name */
        OverScroller f1695d;

        /* renamed from: e, reason: collision with root package name */
        Interpolator f1696e = RecyclerView.sQuinticInterpolator;

        /* renamed from: f, reason: collision with root package name */
        private boolean f1697f = false;
        private boolean g = false;

        c0() {
            this.f1695d = new OverScroller(RecyclerView.this.getContext(), RecyclerView.sQuinticInterpolator);
        }

        private void c() {
            RecyclerView.this.removeCallbacks(this);
            a.f.l.w.a(RecyclerView.this, this);
        }

        void a() {
            if (this.f1697f) {
                this.g = true;
            } else {
                c();
            }
        }

        public void b() {
            RecyclerView.this.removeCallbacks(this);
            this.f1695d.abortAnimation();
        }

        @Override // java.lang.Runnable
        public void run() {
            int i;
            int i2;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.mLayout == null) {
                b();
                return;
            }
            this.g = false;
            this.f1697f = true;
            recyclerView.consumePendingUpdateOperations();
            OverScroller overScroller = this.f1695d;
            if (overScroller.computeScrollOffset()) {
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i3 = currX - this.f1693b;
                int i4 = currY - this.f1694c;
                this.f1693b = currX;
                this.f1694c = currY;
                RecyclerView recyclerView2 = RecyclerView.this;
                int[] iArr = recyclerView2.mReusableIntPair;
                iArr[0] = 0;
                iArr[1] = 0;
                if (recyclerView2.dispatchNestedPreScroll(i3, i4, iArr, null, 1)) {
                    int[] iArr2 = RecyclerView.this.mReusableIntPair;
                    i3 -= iArr2[0];
                    i4 -= iArr2[1];
                }
                if (RecyclerView.this.getOverScrollMode() != 2) {
                    RecyclerView.this.considerReleasingGlowsOnScroll(i3, i4);
                }
                RecyclerView recyclerView3 = RecyclerView.this;
                if (recyclerView3.mAdapter != null) {
                    int[] iArr3 = recyclerView3.mReusableIntPair;
                    iArr3[0] = 0;
                    iArr3[1] = 0;
                    recyclerView3.scrollStep(i3, i4, iArr3);
                    RecyclerView recyclerView4 = RecyclerView.this;
                    int[] iArr4 = recyclerView4.mReusableIntPair;
                    i2 = iArr4[0];
                    i = iArr4[1];
                    i3 -= i2;
                    i4 -= i;
                    z zVar = recyclerView4.mLayout.g;
                    if (zVar != null && !zVar.d() && zVar.e()) {
                        int a2 = RecyclerView.this.mState.a();
                        if (a2 == 0) {
                            zVar.h();
                        } else if (zVar.c() >= a2) {
                            zVar.c(a2 - 1);
                            zVar.a(i2, i);
                        } else {
                            zVar.a(i2, i);
                        }
                    }
                } else {
                    i = 0;
                    i2 = 0;
                }
                if (!RecyclerView.this.mItemDecorations.isEmpty()) {
                    RecyclerView.this.invalidate();
                }
                RecyclerView recyclerView5 = RecyclerView.this;
                int[] iArr5 = recyclerView5.mReusableIntPair;
                iArr5[0] = 0;
                iArr5[1] = 0;
                recyclerView5.dispatchNestedScroll(i2, i, i3, i4, null, 1, iArr5);
                int[] iArr6 = RecyclerView.this.mReusableIntPair;
                int i5 = i3 - iArr6[0];
                int i6 = i4 - iArr6[1];
                if (i2 != 0 || i != 0) {
                    RecyclerView.this.dispatchOnScrolled(i2, i);
                }
                if (!RecyclerView.this.awakenScrollBars()) {
                    RecyclerView.this.invalidate();
                }
                boolean z = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i5 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i6 != 0));
                z zVar2 = RecyclerView.this.mLayout.g;
                if (!(zVar2 != null && zVar2.d()) && z) {
                    if (RecyclerView.this.getOverScrollMode() != 2) {
                        int currVelocity = (int) overScroller.getCurrVelocity();
                        int i7 = i5 < 0 ? -currVelocity : i5 > 0 ? currVelocity : 0;
                        if (i6 < 0) {
                            currVelocity = -currVelocity;
                        } else if (i6 <= 0) {
                            currVelocity = 0;
                        }
                        RecyclerView.this.absorbGlows(i7, currVelocity);
                    }
                    if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                        RecyclerView.this.mPrefetchRegistry.a();
                    }
                } else {
                    a();
                    RecyclerView recyclerView6 = RecyclerView.this;
                    androidx.recyclerview.widget.i iVar = recyclerView6.mGapWorker;
                    if (iVar != null) {
                        iVar.a(recyclerView6, i2, i);
                    }
                }
            }
            z zVar3 = RecyclerView.this.mLayout.g;
            if (zVar3 != null && zVar3.d()) {
                zVar3.a(0, 0);
            }
            this.f1697f = false;
            if (this.g) {
                c();
            } else {
                RecyclerView.this.setScrollState(0);
                RecyclerView.this.stopNestedScroll(1);
            }
        }

        public void a(int i, int i2) {
            RecyclerView.this.setScrollState(2);
            this.f1694c = 0;
            this.f1693b = 0;
            Interpolator interpolator = this.f1696e;
            Interpolator interpolator2 = RecyclerView.sQuinticInterpolator;
            if (interpolator != interpolator2) {
                this.f1696e = interpolator2;
                this.f1695d = new OverScroller(RecyclerView.this.getContext(), RecyclerView.sQuinticInterpolator);
            }
            this.f1695d.fling(0, 0, i, i2, RecyclerView.UNDEFINED_DURATION, Preference.DEFAULT_ORDER, RecyclerView.UNDEFINED_DURATION, Preference.DEFAULT_ORDER);
            a();
        }

        public void a(int i, int i2, int i3, Interpolator interpolator) {
            if (i3 == Integer.MIN_VALUE) {
                i3 = a(i, i2, 0, 0);
            }
            int i4 = i3;
            if (interpolator == null) {
                interpolator = RecyclerView.sQuinticInterpolator;
            }
            if (this.f1696e != interpolator) {
                this.f1696e = interpolator;
                this.f1695d = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            this.f1694c = 0;
            this.f1693b = 0;
            RecyclerView.this.setScrollState(2);
            this.f1695d.startScroll(0, 0, i, i2, i4);
            if (Build.VERSION.SDK_INT < 23) {
                this.f1695d.computeScrollOffset();
            }
            a();
        }

        private float a(float f2) {
            return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
        }

        private int a(int i, int i2, int i3, int i4) {
            int i5;
            int abs = Math.abs(i);
            int abs2 = Math.abs(i2);
            boolean z = abs > abs2;
            int sqrt = (int) Math.sqrt((i3 * i3) + (i4 * i4));
            int sqrt2 = (int) Math.sqrt((i * i) + (i2 * i2));
            RecyclerView recyclerView = RecyclerView.this;
            int width = z ? recyclerView.getWidth() : recyclerView.getHeight();
            int i6 = width / 2;
            float f2 = width;
            float f3 = i6;
            float a2 = f3 + (a(Math.min(1.0f, (sqrt2 * 1.0f) / f2)) * f3);
            if (sqrt > 0) {
                i5 = Math.round(Math.abs(a2 / sqrt) * 1000.0f) * 4;
            } else {
                if (!z) {
                    abs = abs2;
                }
                i5 = (int) (((abs / f2) + 1.0f) * 300.0f);
            }
            return Math.min(i5, RecyclerView.MAX_SCROLL_DURATION);
        }
    }

    /* loaded from: classes.dex */
    public static class u {

        /* renamed from: a, reason: collision with root package name */
        SparseArray<a> f1726a = new SparseArray<>();

        /* renamed from: b, reason: collision with root package name */
        private int f1727b = 0;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public static class a {

            /* renamed from: a, reason: collision with root package name */
            final ArrayList<d0> f1728a = new ArrayList<>();

            /* renamed from: b, reason: collision with root package name */
            int f1729b = 5;

            /* renamed from: c, reason: collision with root package name */
            long f1730c = 0;

            /* renamed from: d, reason: collision with root package name */
            long f1731d = 0;

            a() {
            }
        }

        public d0 a(int i) {
            a aVar = this.f1726a.get(i);
            if (aVar == null || aVar.f1728a.isEmpty()) {
                return null;
            }
            ArrayList<d0> arrayList = aVar.f1728a;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (!arrayList.get(size).isAttachedToTransitionOverlay()) {
                    return arrayList.remove(size);
                }
            }
            return null;
        }

        public void b() {
            for (int i = 0; i < this.f1726a.size(); i++) {
                this.f1726a.valueAt(i).f1728a.clear();
            }
        }

        void c() {
            this.f1727b--;
        }

        void b(int i, long j) {
            a b2 = b(i);
            b2.f1730c = a(b2.f1730c, j);
        }

        boolean b(int i, long j, long j2) {
            long j3 = b(i).f1730c;
            return j3 == 0 || j + j3 < j2;
        }

        private a b(int i) {
            a aVar = this.f1726a.get(i);
            if (aVar != null) {
                return aVar;
            }
            a aVar2 = new a();
            this.f1726a.put(i, aVar2);
            return aVar2;
        }

        public void a(d0 d0Var) {
            int itemViewType = d0Var.getItemViewType();
            ArrayList<d0> arrayList = b(itemViewType).f1728a;
            if (this.f1726a.get(itemViewType).f1729b <= arrayList.size()) {
                return;
            }
            d0Var.resetInternal();
            arrayList.add(d0Var);
        }

        long a(long j, long j2) {
            return j == 0 ? j2 : ((j / 4) * 3) + (j2 / 4);
        }

        void a(int i, long j) {
            a b2 = b(i);
            b2.f1731d = a(b2.f1731d, j);
        }

        boolean a(int i, long j, long j2) {
            long j3 = b(i).f1731d;
            return j3 == 0 || j + j3 < j2;
        }

        void a() {
            this.f1727b++;
        }

        void a(g gVar, g gVar2, boolean z) {
            if (gVar != null) {
                c();
            }
            if (!z && this.f1727b == 0) {
                b();
            }
            if (gVar2 != null) {
                a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class x extends i {
        x() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void a() {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.mState.g = true;
            recyclerView.processDataSetCompletelyChanged(true);
            if (RecyclerView.this.mAdapterHelper.c()) {
                return;
            }
            RecyclerView.this.requestLayout();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void b(int i, int i2) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.b(i, i2)) {
                b();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void c(int i, int i2) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.c(i, i2)) {
                b();
            }
        }

        void b() {
            if (RecyclerView.POST_UPDATES_ON_ANIMATION) {
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.mHasFixedSize && recyclerView.mIsAttached) {
                    a.f.l.w.a(recyclerView, recyclerView.mUpdateChildViewsRunnable);
                    return;
                }
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            recyclerView2.mAdapterUpdateDuringMeasure = true;
            recyclerView2.requestLayout();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void a(int i, int i2, Object obj) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.a(i, i2, obj)) {
                b();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void a(int i, int i2, int i3) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.a(i, i2, i3)) {
                b();
            }
        }
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mObserver = new x();
        this.mRecycler = new v();
        this.mViewInfoStore = new androidx.recyclerview.widget.x();
        this.mUpdateChildViewsRunnable = new a();
        this.mTempRect = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRectF = new RectF();
        this.mItemDecorations = new ArrayList<>();
        this.mOnItemTouchListeners = new ArrayList<>();
        this.mInterceptRequestLayoutDepth = 0;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        this.mLayoutOrScrollCounter = 0;
        this.mDispatchScrollCounter = 0;
        this.mEdgeEffectFactory = new k();
        this.mItemAnimator = new androidx.recyclerview.widget.e();
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScaledHorizontalScrollFactor = Float.MIN_VALUE;
        this.mScaledVerticalScrollFactor = Float.MIN_VALUE;
        boolean z2 = true;
        this.mPreserveFocusAfterLayout = true;
        this.mViewFlinger = new c0();
        this.mPrefetchRegistry = ALLOW_THREAD_GAP_WORK ? new i.b() : null;
        this.mState = new a0();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.mItemAnimatorListener = new m();
        this.mPostedAnimatorRunner = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mNestedOffsets = new int[2];
        this.mReusableIntPair = new int[2];
        this.mPendingAccessibilityImportanceChange = new ArrayList();
        this.mItemAnimatorRunner = new b();
        this.mViewInfoProcessCallback = new d();
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mScaledHorizontalScrollFactor = a.f.l.x.b(viewConfiguration, context);
        this.mScaledVerticalScrollFactor = a.f.l.x.c(viewConfiguration, context);
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.mItemAnimator.a(this.mItemAnimatorListener);
        initAdapterManager();
        initChildrenHelper();
        initAutofill();
        if (a.f.l.w.n(this) == 0) {
            a.f.l.w.i(this, 1);
        }
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new androidx.recyclerview.widget.s(this));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.m.d.RecyclerView, i2, 0);
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, a.m.d.RecyclerView, attributeSet, obtainStyledAttributes, i2, 0);
        }
        String string = obtainStyledAttributes.getString(a.m.d.RecyclerView_layoutManager);
        if (obtainStyledAttributes.getInt(a.m.d.RecyclerView_android_descendantFocusability, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.mClipToPadding = obtainStyledAttributes.getBoolean(a.m.d.RecyclerView_android_clipToPadding, true);
        this.mEnableFastScroller = obtainStyledAttributes.getBoolean(a.m.d.RecyclerView_fastScrollEnabled, false);
        if (this.mEnableFastScroller) {
            initFastScroller((StateListDrawable) obtainStyledAttributes.getDrawable(a.m.d.RecyclerView_fastScrollVerticalThumbDrawable), obtainStyledAttributes.getDrawable(a.m.d.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable) obtainStyledAttributes.getDrawable(a.m.d.RecyclerView_fastScrollHorizontalThumbDrawable), obtainStyledAttributes.getDrawable(a.m.d.RecyclerView_fastScrollHorizontalTrackDrawable));
        }
        obtainStyledAttributes.recycle();
        createLayoutManager(context, string, attributeSet, i2, 0);
        if (Build.VERSION.SDK_INT >= 21) {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, NESTED_SCROLLING_ATTRS, i2, 0);
            if (Build.VERSION.SDK_INT >= 29) {
                saveAttributeDataForStyleable(context, NESTED_SCROLLING_ATTRS, attributeSet, obtainStyledAttributes2, i2, 0);
            }
            z2 = obtainStyledAttributes2.getBoolean(0, true);
            obtainStyledAttributes2.recycle();
        }
        setNestedScrollingEnabled(z2);
    }

    public final void dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr, int i6, int[] iArr2) {
        getScrollingChildHelper().a(i2, i3, i4, i5, iArr, i6, iArr2);
    }

    public void smoothScrollBy(int i2, int i3, Interpolator interpolator, int i4) {
        smoothScrollBy(i2, i3, interpolator, i4, false);
    }

    /* loaded from: classes.dex */
    public static class a0 {

        /* renamed from: b, reason: collision with root package name */
        private SparseArray<Object> f1687b;
        int m;
        long n;
        int o;
        int p;
        int q;

        /* renamed from: a, reason: collision with root package name */
        int f1686a = -1;

        /* renamed from: c, reason: collision with root package name */
        int f1688c = 0;

        /* renamed from: d, reason: collision with root package name */
        int f1689d = 0;

        /* renamed from: e, reason: collision with root package name */
        int f1690e = 1;

        /* renamed from: f, reason: collision with root package name */
        int f1691f = 0;
        boolean g = false;
        boolean h = false;
        boolean i = false;
        boolean j = false;
        boolean k = false;
        boolean l = false;

        void a(int i) {
            if ((this.f1690e & i) != 0) {
                return;
            }
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i) + " but it is " + Integer.toBinaryString(this.f1690e));
        }

        public int b() {
            return this.f1686a;
        }

        public boolean c() {
            return this.f1686a != -1;
        }

        public boolean d() {
            return this.h;
        }

        public boolean e() {
            return this.l;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.f1686a + ", mData=" + this.f1687b + ", mItemCount=" + this.f1691f + ", mIsMeasuring=" + this.j + ", mPreviousLayoutItemCount=" + this.f1688c + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.f1689d + ", mStructureChanged=" + this.g + ", mInPreLayout=" + this.h + ", mRunSimpleAnimations=" + this.k + ", mRunPredictiveAnimations=" + this.l + '}';
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a(g gVar) {
            this.f1690e = 1;
            this.f1691f = gVar.getItemCount();
            this.h = false;
            this.i = false;
            this.j = false;
        }

        public int a() {
            return this.h ? this.f1688c - this.f1689d : this.f1691f;
        }
    }

    /* loaded from: classes.dex */
    public static class p extends ViewGroup.MarginLayoutParams {

        /* renamed from: a, reason: collision with root package name */
        d0 f1722a;

        /* renamed from: b, reason: collision with root package name */
        final Rect f1723b;

        /* renamed from: c, reason: collision with root package name */
        boolean f1724c;

        /* renamed from: d, reason: collision with root package name */
        boolean f1725d;

        public p(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1723b = new Rect();
            this.f1724c = true;
            this.f1725d = false;
        }

        public int a() {
            return this.f1722a.getLayoutPosition();
        }

        public boolean b() {
            return this.f1722a.isUpdated();
        }

        public boolean c() {
            return this.f1722a.isRemoved();
        }

        public boolean d() {
            return this.f1722a.isInvalid();
        }

        public p(int i, int i2) {
            super(i, i2);
            this.f1723b = new Rect();
            this.f1724c = true;
            this.f1725d = false;
        }

        public p(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1723b = new Rect();
            this.f1724c = true;
            this.f1725d = false;
        }

        public p(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1723b = new Rect();
            this.f1724c = true;
            this.f1725d = false;
        }

        public p(p pVar) {
            super((ViewGroup.LayoutParams) pVar);
            this.f1723b = new Rect();
            this.f1724c = true;
            this.f1725d = false;
        }
    }

    /* loaded from: classes.dex */
    public static class y extends a.h.a.a {
        public static final Parcelable.Creator<y> CREATOR = new a();

        /* renamed from: d, reason: collision with root package name */
        Parcelable f1739d;

        /* loaded from: classes.dex */
        static class a implements Parcelable.ClassLoaderCreator<y> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            public y[] newArray(int i) {
                return new y[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public y createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new y(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public y createFromParcel(Parcel parcel) {
                return new y(parcel, null);
            }
        }

        y(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1739d = parcel.readParcelable(classLoader == null ? o.class.getClassLoader() : classLoader);
        }

        void a(y yVar) {
            this.f1739d = yVar.f1739d;
        }

        @Override // a.h.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.f1739d, 0);
        }

        y(Parcelable parcelable) {
            super(parcelable);
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        o oVar = this.mLayout;
        if (oVar != null) {
            return oVar.a(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + exceptionLabel());
    }

    void smoothScrollBy(int i2, int i3, Interpolator interpolator, int i4, boolean z2) {
        o oVar = this.mLayout;
        if (oVar == null) {
            Log.e(TAG, "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.mLayoutSuppressed) {
            return;
        }
        if (!oVar.a()) {
            i2 = 0;
        }
        if (!this.mLayout.b()) {
            i3 = 0;
        }
        if (i2 == 0 && i3 == 0) {
            return;
        }
        if (i4 == Integer.MIN_VALUE || i4 > 0) {
            if (z2) {
                int i5 = i2 != 0 ? 1 : 0;
                if (i3 != 0) {
                    i5 |= 2;
                }
                startNestedScroll(i5, 1);
            }
            this.mViewFlinger.a(i2, i3, i4, interpolator);
            return;
        }
        scrollBy(i2, i3);
    }

    public void addItemDecoration(n nVar) {
        addItemDecoration(nVar, -1);
    }
}
