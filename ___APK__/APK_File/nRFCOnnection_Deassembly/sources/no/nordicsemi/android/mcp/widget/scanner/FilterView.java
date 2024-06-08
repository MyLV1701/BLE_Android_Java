package no.nordicsemi.android.mcp.widget.scanner;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.StrikethroughSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.i0;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.l;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Locale;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.TabsAdapter;
import no.nordicsemi.android.mcp.scanner.Filter;
import no.nordicsemi.android.mcp.widget.HexKeyListener;

/* loaded from: classes.dex */
public class FilterView extends RelativeLayout implements l.h, TabsAdapter.TabListener {
    private boolean mAttached;
    private boolean mBlockPropagating;
    private int mCalculatedContentHeight;
    private View mClearFilter;
    private int mCurrentContentHeight;
    private Animator mCurrentExpandCollapseAnimator;
    private Animation mCurrentHideShowAnimation;
    private TextView mExclude;
    private boolean mExpanded;
    private Filter mFilter;
    private EditText mFilterAdvertisingDataValue;
    private View mFilterContainer;
    private View mFilterContent;
    private TextView mFilterExcludes;
    private TextView mFilterHeader;
    private EditText mFilterValue;
    private View mHeader;
    private View mHover;
    private FilterListener mListener;
    private boolean mNoName;
    private CheckBox mOnlyFavoritesView;
    private int mRaiseElevation;
    private boolean mRaised;
    private SeekBar mRssiBar;
    private TextView mRssiValue;
    private boolean mWithName;
    private ViewTreeObserver.OnPreDrawListener preDrawListener;
    private TextWatcher textWatcher;

    /* loaded from: classes.dex */
    public interface FilterListener {
        void filterBy(Filter filter);

        void onFilterCollapsed();

        void onFilterExpanded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: no.nordicsemi.android.mcp.widget.scanner.FilterView.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        private boolean expanded;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.expanded ? 1 : 0);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.expanded = parcel.readInt() == 1;
        }
    }

    public FilterView(Context context) {
        super(context);
        this.preDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.FilterView.5
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (FilterView.this.mFilterContent == null) {
                    return false;
                }
                FilterView.this.mFilterContent.getViewTreeObserver().removeOnPreDrawListener(this);
                View view = (View) FilterView.this.mFilterContent.getParent();
                FilterView.this.mFilterContent.measure(View.MeasureSpec.makeMeasureSpec((view.getMeasuredWidth() - view.getPaddingLeft()) - view.getPaddingRight(), RecyclerView.UNDEFINED_DURATION), View.MeasureSpec.makeMeasureSpec(0, 0));
                FilterView filterView = FilterView.this;
                filterView.mCalculatedContentHeight = filterView.mFilterContent.getMeasuredHeight();
                return true;
            }
        };
        this.textWatcher = new TextWatcher() { // from class: no.nordicsemi.android.mcp.widget.scanner.FilterView.6
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (FilterView.this.mBlockPropagating) {
                    return;
                }
                FilterView.this.notifyListener();
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        };
        init(context);
    }

    private void closeBackStack() {
        ((androidx.appcompat.app.e) getContext()).getSupportFragmentManager().b(this);
    }

    private void collapse() {
        Animator animator = this.mCurrentExpandCollapseAnimator;
        if (animator != null) {
            animator.cancel();
        }
        ValueAnimator createSlideAnimator = createSlideAnimator(this.mCurrentContentHeight, 0);
        this.mCurrentExpandCollapseAnimator = createSlideAnimator;
        createSlideAnimator.addListener(new AnimatorListenerAdapter() { // from class: no.nordicsemi.android.mcp.widget.scanner.FilterView.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator2) {
                FilterView.this.mCurrentExpandCollapseAnimator = null;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                FilterView.this.mHover.setVisibility(8);
                FilterView.this.mFilterContent.setVisibility(8);
                FilterView.this.mFilterHeader.getCompoundDrawables()[2].setLevel(0);
                FilterView.this.mCurrentExpandCollapseAnimator = null;
                if (FilterView.this.mListener != null) {
                    FilterView.this.mListener.onFilterCollapsed();
                }
                if (FilterView.this.mRaised) {
                    return;
                }
                FilterView.this.lower();
            }
        });
        createSlideAnimator.start();
        ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mFilterValue.getWindowToken(), 0);
    }

    private ValueAnimator createSlideAnimator(int i, int i2) {
        ValueAnimator ofInt = ValueAnimator.ofInt(i, i2);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.v
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                FilterView.this.a(valueAnimator);
            }
        });
        double abs = Math.abs(i - i2);
        Double.isNaN(abs);
        ofInt.setDuration((long) (abs * 0.3d));
        return ofInt;
    }

    private void expand() {
        Animator animator = this.mCurrentExpandCollapseAnimator;
        if (animator != null) {
            animator.cancel();
        }
        rise();
        this.mFilterHeader.getCompoundDrawables()[2].setLevel(1);
        this.mHover.setVisibility(0);
        this.mFilterContent.setVisibility(0);
        ValueAnimator createSlideAnimator = createSlideAnimator(this.mCurrentContentHeight, this.mCalculatedContentHeight);
        this.mCurrentExpandCollapseAnimator = createSlideAnimator;
        createSlideAnimator.addListener(new AnimatorListenerAdapter() { // from class: no.nordicsemi.android.mcp.widget.scanner.FilterView.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator2) {
                FilterView.this.mCurrentExpandCollapseAnimator = null;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                FilterView.this.mCurrentExpandCollapseAnimator = null;
                FilterView.this.mFilterValue.requestFocus();
                if (FilterView.this.mListener != null) {
                    FilterView.this.mListener.onFilterExpanded();
                }
                ((InputMethodManager) FilterView.this.getContext().getSystemService("input_method")).toggleSoftInput(2, 0);
            }
        });
        createSlideAnimator.start();
        pushBackStack();
    }

    private CharSequence getExcludes(Filter.Excludes excludes) {
        if (!excludes.any()) {
            return getResources().getString(R.string.exclude_none);
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (excludes.apple) {
            int length = spannableStringBuilder.length();
            spannableStringBuilder.append(getResources().getText(R.string.filter_exclude_apple));
            spannableStringBuilder.setSpan(new StrikethroughSpan(), length, spannableStringBuilder.length(), 17);
            spannableStringBuilder.append((CharSequence) ", ");
        }
        if (excludes.microsoft) {
            int length2 = spannableStringBuilder.length();
            spannableStringBuilder.append((CharSequence) getResources().getString(R.string.filter_exclude_microsoft));
            spannableStringBuilder.setSpan(new StrikethroughSpan(), length2, spannableStringBuilder.length(), 17);
            spannableStringBuilder.append((CharSequence) ", ");
        }
        if (excludes.bluetoothMesh) {
            int length3 = spannableStringBuilder.length();
            spannableStringBuilder.append((CharSequence) getResources().getString(R.string.filter_exclude_mesh));
            spannableStringBuilder.setSpan(new StrikethroughSpan(), length3, spannableStringBuilder.length(), 17);
            spannableStringBuilder.append((CharSequence) ", ");
        }
        if (excludes.beacons) {
            int length4 = spannableStringBuilder.length();
            spannableStringBuilder.append((CharSequence) getResources().getString(R.string.filter_exclude_beacons));
            spannableStringBuilder.setSpan(new StrikethroughSpan(), length4, spannableStringBuilder.length(), 17);
            spannableStringBuilder.append((CharSequence) ", ");
        }
        spannableStringBuilder.delete(spannableStringBuilder.length() - 2, spannableStringBuilder.length());
        return spannableStringBuilder;
    }

    private void init(Context context) {
        if (isInEditMode()) {
            return;
        }
        this.mFilter = Filter.loadFilter(context);
        if (Build.VERSION.SDK_INT >= 21) {
            this.mRaiseElevation = context.getResources().getDimensionPixelOffset(R.dimen.filter_elevation);
        }
    }

    private void initBackStack() {
        ((androidx.appcompat.app.e) getContext()).getSupportFragmentManager().a((l.h) this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lower() {
        if (Build.VERSION.SDK_INT >= 21) {
            if (this.mRaised && this.mFilterContainer.getTranslationZ() == 0.0f) {
                return;
            }
            this.mFilterContainer.animate().cancel();
            this.mFilterContainer.animate().translationZ(0.0f).setDuration(100L);
            return;
        }
        this.mFilterContainer.getBackground().setLevel(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyListener() {
        EditText editText;
        if (!this.mAttached || isInEditMode() || (editText = this.mFilterValue) == null || this.mFilterAdvertisingDataValue == null || this.mRssiBar == null || this.mOnlyFavoritesView == null || this.mFilter == null) {
            return;
        }
        String trim = editText.getText().toString().trim();
        boolean z = this.mNoName;
        boolean z2 = this.mWithName;
        String upperCase = this.mFilterAdvertisingDataValue.getText().toString().trim().toUpperCase(Locale.US);
        int progress = (-40) - this.mRssiBar.getProgress();
        boolean isChecked = this.mOnlyFavoritesView.isChecked();
        updateHeaderValue(trim, z, z2, this.mFilter.getExcludes(), upperCase, progress, isChecked);
        this.mFilter.set(trim, z, z2, upperCase, progress, isChecked);
        this.mFilter.saveFilter(getContext());
        FilterListener filterListener = this.mListener;
        if (filterListener != null) {
            filterListener.filterBy(this.mFilter);
        }
    }

    private void popBackStack() {
        try {
            ((androidx.appcompat.app.e) getContext()).getSupportFragmentManager().y();
        } catch (IllegalStateException e2) {
            Log.w("FilterView", "Closing filter", e2);
        }
    }

    private void pushBackStack() {
        androidx.fragment.app.t b2 = ((androidx.appcompat.app.e) getContext()).getSupportFragmentManager().b();
        b2.a(new Fragment(), (String) null);
        b2.a((String) null);
        b2.b();
    }

    private void rise() {
        if (Build.VERSION.SDK_INT < 21) {
            this.mFilterContainer.getBackground().setLevel(1);
        } else {
            if (this.mFilterContainer.getTranslationZ() > 0.0f) {
                return;
            }
            this.mFilterContainer.setTranslationZ(1.0f);
            this.mFilterContainer.animate().cancel();
            this.mFilterContainer.animate().translationZ(this.mRaiseElevation).setDuration(40L).start();
        }
    }

    private void setupClearFilterAction() {
        this.mClearFilter = findViewById(R.id.action_clear_filter);
        this.mClearFilter.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterView.this.a(view);
            }
        });
    }

    private void setupExcludeView() {
        final Filter.Excludes excludes = this.mFilter.getExcludes();
        this.mExclude = (TextView) findViewById(R.id.exclude);
        this.mExclude.setText(getExcludes(excludes).toString());
        findViewById(R.id.action_exclude_clear).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterView.this.a(excludes, view);
            }
        });
        findViewById(R.id.action_exclude).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterView.this.b(excludes, view);
            }
        });
    }

    private void setupExpandCollapseAction() {
        this.mHeader = findViewById(R.id.header);
        this.mHeader.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.x
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterView.this.b(view);
            }
        });
        this.mFilterHeader = (TextView) findViewById(R.id.filter_header);
        this.mFilterExcludes = (TextView) findViewById(R.id.filter_excludes);
        this.mHover = findViewById(R.id.hover);
        this.mHover.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterView.this.c(view);
            }
        });
    }

    private void setupFavoritesSwitch() {
        final CheckBox checkBox = (CheckBox) findViewById(R.id.only_favorites_switch);
        this.mOnlyFavoritesView = checkBox;
        checkBox.setChecked(this.mFilter.isOnlyFavorites());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.l
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                FilterView.this.a(compoundButton, z);
            }
        });
        findViewById(R.id.only_favorites_label).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                checkBox.toggle();
            }
        });
    }

    @SuppressLint({"SetTextI18n"})
    private void setupFilterAdvertisingDataView() {
        EditText editText = (EditText) findViewById(R.id.filter_adv);
        this.mFilterAdvertisingDataValue = editText;
        editText.setKeyListener(new HexKeyListener());
        editText.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        editText.setText(this.mFilter.getFilterAdvertisingData());
        editText.addTextChangedListener(this.textWatcher);
        findViewById(R.id.action_adv_clear).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterView.this.d(view);
            }
        });
        findViewById(R.id.action_adv_more).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterView.this.e(view);
            }
        });
    }

    private void setupFilterView() {
        EditText editText = (EditText) findViewById(R.id.filter);
        this.mFilterValue = editText;
        editText.setText(this.mFilter.getFilter());
        editText.addTextChangedListener(this.textWatcher);
        findViewById(R.id.action_clear).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.u
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterView.this.f(view);
            }
        });
        this.mNoName = this.mFilter.isNoName();
        this.mWithName = this.mFilter.isWithName();
        findViewById(R.id.action_more).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterView.this.g(view);
            }
        });
    }

    private void setupRssiView() {
        SeekBar seekBar = (SeekBar) findViewById(R.id.rssi);
        this.mRssiBar = seekBar;
        int rssi = this.mFilter.getRssi();
        seekBar.setProgress((-40) - rssi);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.FilterView.7
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int i, boolean z) {
                FilterView.this.mRssiValue.setText(FilterView.this.getResources().getString(R.string.rssi, Integer.valueOf((-40) - i)));
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
                if (FilterView.this.mBlockPropagating) {
                    return;
                }
                FilterView.this.notifyListener();
            }
        });
        this.mRssiValue = (TextView) findViewById(R.id.rssi_value);
        this.mRssiValue.setText(getResources().getString(R.string.rssi, Integer.valueOf(rssi)));
    }

    private void setupView() {
        this.mFilterContainer = findViewById(R.id.filter_container);
        this.mFilterContent = findViewById(R.id.filter_content);
        this.mFilterContent.getViewTreeObserver().addOnPreDrawListener(this.preDrawListener);
        setupExpandCollapseAction();
        setupFilterView();
        setupFilterAdvertisingDataView();
        setupExcludeView();
        setupRssiView();
        setupFavoritesSwitch();
        setupClearFilterAction();
    }

    private void updateHeaderValue(String str, boolean z, boolean z2, Filter.Excludes excludes, String str2, int i, boolean z3) {
        boolean z4 = TextUtils.isEmpty(str) && !z && !z2 && TextUtils.isEmpty(str2) && i == -100 && !z3;
        boolean any = true ^ excludes.any();
        if (!z4) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            if (!TextUtils.isEmpty(str)) {
                spannableStringBuilder.append((CharSequence) str).append((CharSequence) ", ");
            }
            if (z) {
                spannableStringBuilder.append((CharSequence) getResources().getString(R.string.filter_no_name)).append((CharSequence) ", ");
            }
            if (z2) {
                spannableStringBuilder.append((CharSequence) getResources().getString(R.string.filter_with_name)).append((CharSequence) ", ");
            }
            if (i > -100) {
                spannableStringBuilder.append((CharSequence) String.valueOf(i)).append((CharSequence) " dBm, ");
            }
            if (z3) {
                spannableStringBuilder.append((CharSequence) getResources().getString(R.string.only_favorites)).append((CharSequence) ", ");
            }
            if (!TextUtils.isEmpty(str2)) {
                spannableStringBuilder.append((CharSequence) "0x").append((CharSequence) str2).append((CharSequence) ", ");
            }
            if (spannableStringBuilder.length() > 0) {
                spannableStringBuilder.delete(spannableStringBuilder.length() - 2, spannableStringBuilder.length());
            }
            this.mFilterHeader.setText(spannableStringBuilder);
        } else {
            this.mFilterHeader.setText(R.string.all_devices);
        }
        if (!any) {
            this.mFilterExcludes.setText(getExcludes(excludes));
        } else {
            this.mFilterExcludes.setText((CharSequence) null);
        }
        this.mClearFilter.setVisibility(z4 ? 8 : 0);
    }

    public /* synthetic */ void a(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.mCurrentContentHeight = intValue;
        ViewGroup.LayoutParams layoutParams = this.mFilterContent.getLayoutParams();
        layoutParams.height = intValue;
        this.mFilterContent.setLayoutParams(layoutParams);
        int i = this.mCalculatedContentHeight;
        if (i > 0) {
            this.mHover.setAlpha(this.mCurrentContentHeight / i);
        }
    }

    public /* synthetic */ void b(View view) {
        if (isExpanded()) {
            popBackStack();
        } else {
            expand();
        }
    }

    public /* synthetic */ void c(View view) {
        popBackStack();
    }

    public /* synthetic */ void d(View view) {
        this.mFilterAdvertisingDataValue.setText((CharSequence) null);
    }

    public /* synthetic */ void e(View view) {
        i0 i0Var = new i0(view.getContext(), view);
        i0Var.a(R.menu.filter_adv_data);
        i0Var.a(new i0.d() { // from class: no.nordicsemi.android.mcp.widget.scanner.o
            @Override // androidx.appcompat.widget.i0.d
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return FilterView.this.b(menuItem);
            }
        });
        i0Var.c();
    }

    public /* synthetic */ void f(View view) {
        this.mFilterValue.setText((CharSequence) null);
    }

    public /* synthetic */ void g(View view) {
        i0 i0Var = new i0(view.getContext(), view);
        i0Var.a(R.menu.filter_name);
        if (this.mNoName) {
            i0Var.a().findItem(R.id.action_no_name).setChecked(this.mNoName);
        } else if (this.mWithName) {
            i0Var.a().findItem(R.id.action_with_name).setChecked(this.mWithName);
        }
        i0Var.a(new i0.d() { // from class: no.nordicsemi.android.mcp.widget.scanner.m
            @Override // androidx.appcompat.widget.i0.d
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return FilterView.this.a(menuItem);
            }
        });
        i0Var.c();
    }

    public void hide(boolean z) {
        if (getVisibility() == 8) {
            return;
        }
        Animation animation = this.mCurrentHideShowAnimation;
        if (animation != null) {
            animation.cancel();
        }
        if (!z) {
            setVisibility(8);
            this.mCurrentHideShowAnimation = null;
        } else {
            Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.abc_slide_out_top);
            this.mCurrentHideShowAnimation = loadAnimation;
            loadAnimation.setAnimationListener(new AnimationListenerAdapter() { // from class: no.nordicsemi.android.mcp.widget.scanner.FilterView.2
                @Override // no.nordicsemi.android.mcp.widget.scanner.AnimationListenerAdapter, android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation2) {
                    FilterView.this.setVisibility(8);
                    FilterView.this.mCurrentHideShowAnimation = null;
                }
            });
            startAnimation(loadAnimation);
        }
    }

    public boolean isExpanded() {
        return this.mFilterContent.getVisibility() == 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttached = true;
        notifyListener();
    }

    @Override // no.nordicsemi.android.mcp.MainActivity.OnBackPressedListener
    public boolean onBackPressed() {
        return false;
    }

    @Override // androidx.fragment.app.l.h
    public void onBackStackChanged() {
        if (this.mExpanded) {
            collapse();
            this.mExpanded = false;
        } else {
            this.mExpanded = true;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        Animator animator = this.mCurrentExpandCollapseAnimator;
        if (animator != null) {
            animator.cancel();
        }
        this.mCurrentExpandCollapseAnimator = null;
        this.mCurrentHideShowAnimation = null;
        this.mFilterContent.getViewTreeObserver().removeOnPreDrawListener(this.preDrawListener);
        this.mFilterContainer = null;
        this.mFilterContent = null;
        this.mFilterValue.removeTextChangedListener(this.textWatcher);
        this.mFilterValue = null;
        this.mFilterAdvertisingDataValue.setKeyListener(null);
        this.mFilterAdvertisingDataValue.removeTextChangedListener(this.textWatcher);
        this.mFilterAdvertisingDataValue = null;
        this.mExclude = null;
        this.mHeader = null;
        this.mFilterHeader = null;
        this.mFilterExcludes = null;
        this.mClearFilter = null;
        this.mHover = null;
        this.mRssiBar.setOnSeekBarChangeListener(null);
        this.mRssiBar = null;
        this.mRssiValue = null;
        this.mOnlyFavoritesView.setOnCheckedChangeListener(null);
        this.mOnlyFavoritesView = null;
        this.textWatcher = null;
        this.preDrawListener = null;
        this.mAttached = false;
        super.onDetachedFromWindow();
        closeBackStack();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (isInEditMode()) {
            return;
        }
        setupView();
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.expanded) {
            this.mFilterContent.setVisibility(0);
            this.mHover.setVisibility(0);
            this.mHover.setAlpha(1.0f);
            this.mExpanded = true;
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.expanded = this.mExpanded;
        return savedState;
    }

    @Override // no.nordicsemi.android.mcp.TabsAdapter.TabListener
    public void onTabClosed() {
        closeBackStack();
        if (this.mExpanded) {
            collapse();
            popBackStack();
        }
        this.mExpanded = false;
    }

    @Override // no.nordicsemi.android.mcp.TabsAdapter.TabListener
    public void onTabOpen() {
        initBackStack();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        this.mHeader.setEnabled(z);
        if (z) {
            return;
        }
        popBackStack();
    }

    public void setFilterListener(FilterListener filterListener) {
        this.mListener = filterListener;
    }

    public void setRaised(boolean z) {
        this.mRaised = z;
        if (z) {
            rise();
        } else if (this.mHover.getAlpha() == 0.0f) {
            lower();
        }
    }

    public void show() {
        if (getVisibility() == 0) {
            return;
        }
        Animation animation = this.mCurrentHideShowAnimation;
        if (animation != null) {
            animation.cancel();
        }
        setVisibility(0);
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.abc_slide_in_top);
        this.mCurrentHideShowAnimation = loadAnimation;
        loadAnimation.setAnimationListener(new AnimationListenerAdapter() { // from class: no.nordicsemi.android.mcp.widget.scanner.FilterView.1
            @Override // no.nordicsemi.android.mcp.widget.scanner.AnimationListenerAdapter, android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation2) {
                FilterView.this.mCurrentHideShowAnimation = null;
            }
        });
        startAnimation(loadAnimation);
    }

    public /* synthetic */ boolean b(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.filter_adv_dfu_legacy /* 2131296639 */:
                this.mFilterAdvertisingDataValue.setText("23D1BCEA5F782315DEEF121230150000");
                return true;
            case R.id.filter_adv_dfu_secure /* 2131296640 */:
                this.mFilterAdvertisingDataValue.setText("59FE");
                return true;
            case R.id.filter_adv_eddystone /* 2131296641 */:
                this.mFilterAdvertisingDataValue.setText("0303AAFE");
                return true;
            case R.id.filter_adv_hrm /* 2131296642 */:
                this.mFilterAdvertisingDataValue.setText("0D18");
                return true;
            case R.id.filter_adv_ibeacon /* 2131296643 */:
                this.mFilterAdvertisingDataValue.setText("0201041AFF4C000215");
                return true;
            case R.id.filter_adv_nrfbeacon /* 2131296644 */:
                this.mFilterAdvertisingDataValue.setText("0201041AFF59000215");
                return true;
            case R.id.filter_adv_physical_web /* 2131296645 */:
                this.mFilterAdvertisingDataValue.setText("16AAFE10");
                return true;
            case R.id.filter_adv_thingy52 /* 2131296646 */:
                this.mFilterAdvertisingDataValue.setText("420074A9FF52109B3349359B000168EF");
                return true;
            default:
                return false;
        }
    }

    public FilterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.preDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.FilterView.5
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (FilterView.this.mFilterContent == null) {
                    return false;
                }
                FilterView.this.mFilterContent.getViewTreeObserver().removeOnPreDrawListener(this);
                View view = (View) FilterView.this.mFilterContent.getParent();
                FilterView.this.mFilterContent.measure(View.MeasureSpec.makeMeasureSpec((view.getMeasuredWidth() - view.getPaddingLeft()) - view.getPaddingRight(), RecyclerView.UNDEFINED_DURATION), View.MeasureSpec.makeMeasureSpec(0, 0));
                FilterView filterView = FilterView.this;
                filterView.mCalculatedContentHeight = filterView.mFilterContent.getMeasuredHeight();
                return true;
            }
        };
        this.textWatcher = new TextWatcher() { // from class: no.nordicsemi.android.mcp.widget.scanner.FilterView.6
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (FilterView.this.mBlockPropagating) {
                    return;
                }
                FilterView.this.notifyListener();
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        };
        init(context);
    }

    public /* synthetic */ void a(View view) {
        this.mBlockPropagating = true;
        this.mNoName = false;
        this.mWithName = false;
        this.mFilterValue.setHint(R.string.filter_hint);
        this.mRssiBar.setProgress(60);
        this.mOnlyFavoritesView.setChecked(false);
        this.mFilterValue.setText((CharSequence) null);
        this.mFilterAdvertisingDataValue.setText((CharSequence) null);
        this.mBlockPropagating = false;
        notifyListener();
    }

    public FilterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.preDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: no.nordicsemi.android.mcp.widget.scanner.FilterView.5
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (FilterView.this.mFilterContent == null) {
                    return false;
                }
                FilterView.this.mFilterContent.getViewTreeObserver().removeOnPreDrawListener(this);
                View view = (View) FilterView.this.mFilterContent.getParent();
                FilterView.this.mFilterContent.measure(View.MeasureSpec.makeMeasureSpec((view.getMeasuredWidth() - view.getPaddingLeft()) - view.getPaddingRight(), RecyclerView.UNDEFINED_DURATION), View.MeasureSpec.makeMeasureSpec(0, 0));
                FilterView filterView = FilterView.this;
                filterView.mCalculatedContentHeight = filterView.mFilterContent.getMeasuredHeight();
                return true;
            }
        };
        this.textWatcher = new TextWatcher() { // from class: no.nordicsemi.android.mcp.widget.scanner.FilterView.6
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (FilterView.this.mBlockPropagating) {
                    return;
                }
                FilterView.this.notifyListener();
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i22, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i22, int i3) {
            }
        };
        init(context);
    }

    public /* synthetic */ void b(final Filter.Excludes excludes, final View view) {
        i0 i0Var = new i0(view.getContext(), view);
        i0Var.a(R.menu.filter_exclude);
        i0Var.a().findItem(R.id.filter_exclude_apple).setChecked(excludes.apple);
        i0Var.a().findItem(R.id.filter_exclude_microsoft).setChecked(excludes.microsoft);
        i0Var.a().findItem(R.id.filter_exclude_mesh).setChecked(excludes.bluetoothMesh);
        i0Var.a().findItem(R.id.filter_exclude_beacons).setChecked(excludes.beacons);
        i0Var.a(new i0.d() { // from class: no.nordicsemi.android.mcp.widget.scanner.n
            @Override // androidx.appcompat.widget.i0.d
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return FilterView.this.a(excludes, view, menuItem);
            }
        });
        i0Var.a(new i0.c() { // from class: no.nordicsemi.android.mcp.widget.scanner.s
            @Override // androidx.appcompat.widget.i0.c
            public final void a(i0 i0Var2) {
                FilterView.this.a(i0Var2);
            }
        });
        i0Var.c();
    }

    public /* synthetic */ boolean a(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        this.mNoName = itemId == R.id.action_no_name;
        this.mWithName = itemId == R.id.action_with_name;
        this.mFilterValue.setHint(this.mNoName ? R.string.filter_hint_address_only : R.string.filter_hint);
        if (!this.mBlockPropagating) {
            notifyListener();
        }
        return true;
    }

    public /* synthetic */ void a(Filter.Excludes excludes, View view) {
        this.mExclude.setText(R.string.exclude_none);
        excludes.clear();
        if (this.mBlockPropagating) {
            return;
        }
        notifyListener();
    }

    public /* synthetic */ boolean a(Filter.Excludes excludes, View view, MenuItem menuItem) {
        menuItem.setChecked(!menuItem.isChecked());
        switch (menuItem.getItemId()) {
            case R.id.filter_exclude_apple /* 2131296649 */:
                excludes.apple = !excludes.apple;
                break;
            case R.id.filter_exclude_beacons /* 2131296650 */:
                excludes.beacons = !excludes.beacons;
                break;
            case R.id.filter_exclude_mesh /* 2131296651 */:
                excludes.bluetoothMesh = !excludes.bluetoothMesh;
                break;
            case R.id.filter_exclude_microsoft /* 2131296652 */:
                excludes.microsoft = !excludes.microsoft;
                break;
        }
        CharSequence excludes2 = getExcludes(excludes);
        this.mExclude.setText(excludes2.toString());
        if (excludes.any()) {
            this.mFilterExcludes.setText(excludes2);
        } else {
            this.mFilterExcludes.setText((CharSequence) null);
        }
        menuItem.setShowAsAction(8);
        menuItem.setActionView(new View(view.getContext()));
        return false;
    }

    public /* synthetic */ void a(i0 i0Var) {
        if (this.mBlockPropagating) {
            return;
        }
        notifyListener();
    }

    public /* synthetic */ void a(CompoundButton compoundButton, boolean z) {
        if (this.mBlockPropagating) {
            return;
        }
        notifyListener();
    }
}
