package androidx.appcompat.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import no.nordicsemi.android.mcp.ble.parser.AppearanceLibrary;

/* loaded from: classes.dex */
public class SearchView extends e0 implements a.a.o.c {
    static final k r0 = new k();
    private Rect A;
    private Rect B;
    private int[] C;
    private int[] D;
    private final ImageView E;
    private final Drawable F;
    private final int G;
    private final int H;
    private final Intent I;
    private final Intent J;
    private final CharSequence K;
    private m L;
    private l M;
    View.OnFocusChangeListener N;
    private n O;
    private View.OnClickListener P;
    private boolean Q;
    private boolean R;
    a.g.a.a S;
    private boolean T;
    private CharSequence U;
    private boolean V;
    private boolean W;
    private int a0;
    private boolean b0;
    private CharSequence c0;
    private CharSequence d0;
    private boolean e0;
    private int f0;
    SearchableInfo g0;
    private Bundle h0;
    private final Runnable i0;
    private Runnable j0;
    private final WeakHashMap<String, Drawable.ConstantState> k0;
    private final View.OnClickListener l0;
    View.OnKeyListener m0;
    private final TextView.OnEditorActionListener n0;
    private final AdapterView.OnItemClickListener o0;
    private final AdapterView.OnItemSelectedListener p0;
    final SearchAutoComplete q;
    private TextWatcher q0;
    private final View r;
    private final View s;
    private final View t;
    final ImageView u;
    final ImageView v;
    final ImageView w;
    final ImageView x;
    private final View y;
    private p z;

    /* loaded from: classes.dex */
    public static class SearchAutoComplete extends androidx.appcompat.widget.d {

        /* renamed from: e, reason: collision with root package name */
        private int f888e;

        /* renamed from: f, reason: collision with root package name */
        private SearchView f889f;
        private boolean g;
        final Runnable h;

        /* loaded from: classes.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                SearchAutoComplete.this.b();
            }
        }

        public SearchAutoComplete(Context context) {
            this(context, null);
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i = configuration.screenWidthDp;
            int i2 = configuration.screenHeightDp;
            if (i >= 960 && i2 >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (i >= 600) {
                return AppearanceLibrary.APPEARANCE_GENERIC_WATCH;
            }
            if (i < 640 || i2 < 480) {
                return 160;
            }
            return AppearanceLibrary.APPEARANCE_GENERIC_WATCH;
        }

        boolean a() {
            return TextUtils.getTrimmedLength(getText()) == 0;
        }

        void b() {
            if (this.g) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.g = false;
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public boolean enoughToFilter() {
            return this.f888e <= 0 || super.enoughToFilter();
        }

        @Override // androidx.appcompat.widget.d, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.g) {
                removeCallbacks(this.h);
                post(this.h);
            }
            return onCreateInputConnection;
        }

        @Override // android.view.View
        protected void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        protected void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            this.f889f.j();
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                }
                if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.f889f.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.f889f.hasFocus() && getVisibility() == 0) {
                this.g = true;
                if (SearchView.a(getContext())) {
                    SearchView.r0.a(this, true);
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        protected void replaceText(CharSequence charSequence) {
        }

        void setImeVisibility(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z) {
                this.g = false;
                removeCallbacks(this.h);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (inputMethodManager.isActive(this)) {
                    this.g = false;
                    removeCallbacks(this.h);
                    inputMethodManager.showSoftInput(this, 0);
                    return;
                }
                this.g = true;
            }
        }

        void setSearchView(SearchView searchView) {
            this.f889f = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i) {
            super.setThreshold(i);
            this.f888e = i;
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, a.a.a.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.h = new a();
            this.f888e = getThreshold();
        }
    }

    /* loaded from: classes.dex */
    class a implements TextWatcher {
        a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            SearchView.this.b(charSequence);
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SearchView.this.l();
        }
    }

    /* loaded from: classes.dex */
    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.g.a.a aVar = SearchView.this.S;
            if (aVar instanceof n0) {
                aVar.changeCursor(null);
            }
        }
    }

    /* loaded from: classes.dex */
    class d implements View.OnFocusChangeListener {
        d() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            SearchView searchView = SearchView.this;
            View.OnFocusChangeListener onFocusChangeListener = searchView.N;
            if (onFocusChangeListener != null) {
                onFocusChangeListener.onFocusChange(searchView, z);
            }
        }
    }

    /* loaded from: classes.dex */
    class e implements View.OnLayoutChangeListener {
        e() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            SearchView.this.d();
        }
    }

    /* loaded from: classes.dex */
    class f implements View.OnClickListener {
        f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SearchView searchView = SearchView.this;
            if (view == searchView.u) {
                searchView.h();
                return;
            }
            if (view == searchView.w) {
                searchView.g();
                return;
            }
            if (view == searchView.v) {
                searchView.i();
            } else if (view == searchView.x) {
                searchView.k();
            } else if (view == searchView.q) {
                searchView.e();
            }
        }
    }

    /* loaded from: classes.dex */
    class g implements View.OnKeyListener {
        g() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            SearchView searchView = SearchView.this;
            if (searchView.g0 == null) {
                return false;
            }
            if (searchView.q.isPopupShowing() && SearchView.this.q.getListSelection() != -1) {
                return SearchView.this.a(view, i, keyEvent);
            }
            if (SearchView.this.q.a() || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || i != 66) {
                return false;
            }
            view.cancelLongPress();
            SearchView searchView2 = SearchView.this;
            searchView2.a(0, (String) null, searchView2.q.getText().toString());
            return true;
        }
    }

    /* loaded from: classes.dex */
    class h implements TextView.OnEditorActionListener {
        h() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            SearchView.this.i();
            return true;
        }
    }

    /* loaded from: classes.dex */
    class i implements AdapterView.OnItemClickListener {
        i() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            SearchView.this.a(i, 0, (String) null);
        }
    }

    /* loaded from: classes.dex */
    class j implements AdapterView.OnItemSelectedListener {
        j() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            SearchView.this.d(i);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* loaded from: classes.dex */
    public interface l {
        boolean a();
    }

    /* loaded from: classes.dex */
    public interface m {
        boolean a(String str);

        boolean b(String str);
    }

    /* loaded from: classes.dex */
    public interface n {
        boolean a(int i);

        boolean b(int i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class o extends a.h.a.a {
        public static final Parcelable.Creator<o> CREATOR = new a();

        /* renamed from: d, reason: collision with root package name */
        boolean f904d;

        /* loaded from: classes.dex */
        static class a implements Parcelable.ClassLoaderCreator<o> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            public o[] newArray(int i) {
                return new o[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public o createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new o(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public o createFromParcel(Parcel parcel) {
                return new o(parcel, null);
            }
        }

        o(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.f904d + "}";
        }

        @Override // a.h.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.f904d));
        }

        public o(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f904d = ((Boolean) parcel.readValue(null)).booleanValue();
        }
    }

    /* loaded from: classes.dex */
    private static class p extends TouchDelegate {

        /* renamed from: a, reason: collision with root package name */
        private final View f905a;

        /* renamed from: b, reason: collision with root package name */
        private final Rect f906b;

        /* renamed from: c, reason: collision with root package name */
        private final Rect f907c;

        /* renamed from: d, reason: collision with root package name */
        private final Rect f908d;

        /* renamed from: e, reason: collision with root package name */
        private final int f909e;

        /* renamed from: f, reason: collision with root package name */
        private boolean f910f;

        public p(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.f909e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            this.f906b = new Rect();
            this.f908d = new Rect();
            this.f907c = new Rect();
            a(rect, rect2);
            this.f905a = view;
        }

        public void a(Rect rect, Rect rect2) {
            this.f906b.set(rect);
            this.f908d.set(rect);
            Rect rect3 = this.f908d;
            int i = this.f909e;
            rect3.inset(-i, -i);
            this.f907c.set(rect2);
        }

        @Override // android.view.TouchDelegate
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z;
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            int action = motionEvent.getAction();
            boolean z2 = true;
            if (action == 0) {
                if (this.f906b.contains(x, y)) {
                    this.f910f = true;
                    z = true;
                }
                z = false;
            } else if (action == 1 || action == 2) {
                z = this.f910f;
                if (z && !this.f908d.contains(x, y)) {
                    z2 = false;
                }
            } else {
                if (action == 3) {
                    z = this.f910f;
                    this.f910f = false;
                }
                z = false;
            }
            if (!z) {
                return false;
            }
            if (z2 && !this.f907c.contains(x, y)) {
                motionEvent.setLocation(this.f905a.getWidth() / 2, this.f905a.getHeight() / 2);
            } else {
                Rect rect = this.f907c;
                motionEvent.setLocation(x - rect.left, y - rect.top);
            }
            return this.f905a.dispatchTouchEvent(motionEvent);
        }
    }

    public SearchView(Context context) {
        this(context, null);
    }

    private void b(boolean z) {
        this.R = z;
        int i2 = z ? 0 : 8;
        boolean z2 = !TextUtils.isEmpty(this.q.getText());
        this.u.setVisibility(i2);
        a(z2);
        this.r.setVisibility(z ? 8 : 0);
        this.E.setVisibility((this.E.getDrawable() == null || this.Q) ? 8 : 0);
        q();
        c(!z2);
        t();
    }

    private CharSequence c(CharSequence charSequence) {
        if (!this.Q || this.F == null) {
            return charSequence;
        }
        double textSize = this.q.getTextSize();
        Double.isNaN(textSize);
        int i2 = (int) (textSize * 1.25d);
        this.F.setBounds(0, 0, i2, i2);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.setSpan(new ImageSpan(this.F), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    private void e(int i2) {
        Editable text = this.q.getText();
        Cursor cursor = this.S.getCursor();
        if (cursor == null) {
            return;
        }
        if (cursor.moveToPosition(i2)) {
            CharSequence convertToString = this.S.convertToString(cursor);
            if (convertToString != null) {
                setQuery(convertToString);
                return;
            } else {
                setQuery(text);
                return;
            }
        }
        setQuery(text);
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(a.a.d.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(a.a.d.abc_search_view_preferred_width);
    }

    private void m() {
        this.q.dismissDropDown();
    }

    private boolean n() {
        SearchableInfo searchableInfo = this.g0;
        if (searchableInfo == null || !searchableInfo.getVoiceSearchEnabled()) {
            return false;
        }
        Intent intent = null;
        if (this.g0.getVoiceSearchLaunchWebSearch()) {
            intent = this.I;
        } else if (this.g0.getVoiceSearchLaunchRecognizer()) {
            intent = this.J;
        }
        return (intent == null || getContext().getPackageManager().resolveActivity(intent, 65536) == null) ? false : true;
    }

    private boolean o() {
        return (this.T || this.b0) && !f();
    }

    private void p() {
        post(this.i0);
    }

    private void q() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.q.getText());
        if (!z2 && (!this.Q || this.e0)) {
            z = false;
        }
        this.w.setVisibility(z ? 0 : 8);
        Drawable drawable = this.w.getDrawable();
        if (drawable != null) {
            drawable.setState(z2 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    private void r() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.q;
        if (queryHint == null) {
            queryHint = "";
        }
        searchAutoComplete.setHint(c(queryHint));
    }

    private void s() {
        this.q.setThreshold(this.g0.getSuggestThreshold());
        this.q.setImeOptions(this.g0.getImeOptions());
        int inputType = this.g0.getInputType();
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.g0.getSuggestAuthority() != null) {
                inputType = inputType | 65536 | 524288;
            }
        }
        this.q.setInputType(inputType);
        a.g.a.a aVar = this.S;
        if (aVar != null) {
            aVar.changeCursor(null);
        }
        if (this.g0.getSuggestAuthority() != null) {
            this.S = new n0(getContext(), this, this.g0, this.k0);
            this.q.setAdapter(this.S);
            ((n0) this.S).a(this.V ? 2 : 1);
        }
    }

    private void setQuery(CharSequence charSequence) {
        this.q.setText(charSequence);
        this.q.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    private void t() {
        this.t.setVisibility((o() && (this.v.getVisibility() == 0 || this.x.getVisibility() == 0)) ? 0 : 8);
    }

    public void a(CharSequence charSequence, boolean z) {
        this.q.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.q;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.d0 = charSequence;
        }
        if (!z || TextUtils.isEmpty(charSequence)) {
            return;
        }
        i();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        this.W = true;
        super.clearFocus();
        this.q.clearFocus();
        this.q.setImeVisibility(false);
        this.W = false;
    }

    void d() {
        int i2;
        if (this.y.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.s.getPaddingLeft();
            Rect rect = new Rect();
            boolean a2 = z0.a(this);
            int dimensionPixelSize = this.Q ? resources.getDimensionPixelSize(a.a.d.abc_dropdownitem_icon_width) + resources.getDimensionPixelSize(a.a.d.abc_dropdownitem_text_padding_left) : 0;
            this.q.getDropDownBackground().getPadding(rect);
            if (a2) {
                i2 = -rect.left;
            } else {
                i2 = paddingLeft - (rect.left + dimensionPixelSize);
            }
            this.q.setDropDownHorizontalOffset(i2);
            this.q.setDropDownWidth((((this.y.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
        }
    }

    public boolean f() {
        return this.R;
    }

    void g() {
        if (TextUtils.isEmpty(this.q.getText())) {
            if (this.Q) {
                l lVar = this.M;
                if (lVar == null || !lVar.a()) {
                    clearFocus();
                    b(true);
                    return;
                }
                return;
            }
            return;
        }
        this.q.setText("");
        this.q.requestFocus();
        this.q.setImeVisibility(true);
    }

    public int getImeOptions() {
        return this.q.getImeOptions();
    }

    public int getInputType() {
        return this.q.getInputType();
    }

    public int getMaxWidth() {
        return this.a0;
    }

    public CharSequence getQuery() {
        return this.q.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.U;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.g0;
        if (searchableInfo != null && searchableInfo.getHintId() != 0) {
            return getContext().getText(this.g0.getHintId());
        }
        return this.K;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSuggestionCommitIconResId() {
        return this.H;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSuggestionRowLayout() {
        return this.G;
    }

    public a.g.a.a getSuggestionsAdapter() {
        return this.S;
    }

    void h() {
        b(false);
        this.q.requestFocus();
        this.q.setImeVisibility(true);
        View.OnClickListener onClickListener = this.P;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    void i() {
        Editable text = this.q.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        m mVar = this.L;
        if (mVar == null || !mVar.b(text.toString())) {
            if (this.g0 != null) {
                a(0, (String) null, text.toString());
            }
            this.q.setImeVisibility(false);
            m();
        }
    }

    void j() {
        b(f());
        p();
        if (this.q.hasFocus()) {
            e();
        }
    }

    void k() {
        SearchableInfo searchableInfo = this.g0;
        if (searchableInfo == null) {
            return;
        }
        try {
            if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                getContext().startActivity(b(this.I, searchableInfo));
            } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                getContext().startActivity(a(this.J, searchableInfo));
            }
        } catch (ActivityNotFoundException unused) {
            Log.w("SearchView", "Could not find voice search activity");
        }
    }

    void l() {
        int[] iArr = this.q.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.s.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.t.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.i0);
        post(this.j0);
        super.onDetachedFromWindow();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.e0, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z) {
            a(this.q, this.A);
            Rect rect = this.B;
            Rect rect2 = this.A;
            rect.set(rect2.left, 0, rect2.right, i5 - i3);
            p pVar = this.z;
            if (pVar == null) {
                this.z = new p(this.B, this.A, this.q);
                setTouchDelegate(this.z);
            } else {
                pVar.a(this.B, this.A);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.e0, android.view.View
    public void onMeasure(int i2, int i3) {
        int i4;
        if (f()) {
            super.onMeasure(i2, i3);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            int i5 = this.a0;
            size = i5 > 0 ? Math.min(i5, size) : Math.min(getPreferredWidth(), size);
        } else if (mode == 0) {
            size = this.a0;
            if (size <= 0) {
                size = getPreferredWidth();
            }
        } else if (mode == 1073741824 && (i4 = this.a0) > 0) {
            size = Math.min(i4, size);
        }
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(getPreferredHeight(), size2);
        } else if (mode2 == 0) {
            size2 = getPreferredHeight();
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof o)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        o oVar = (o) parcelable;
        super.onRestoreInstanceState(oVar.d());
        b(oVar.f904d);
        requestLayout();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        o oVar = new o(super.onSaveInstanceState());
        oVar.f904d = f();
        return oVar;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        p();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i2, Rect rect) {
        if (this.W || !isFocusable()) {
            return false;
        }
        if (!f()) {
            boolean requestFocus = this.q.requestFocus(i2, rect);
            if (requestFocus) {
                b(false);
            }
            return requestFocus;
        }
        return super.requestFocus(i2, rect);
    }

    public void setAppSearchData(Bundle bundle) {
        this.h0 = bundle;
    }

    public void setIconified(boolean z) {
        if (z) {
            g();
        } else {
            h();
        }
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.Q == z) {
            return;
        }
        this.Q = z;
        b(z);
        r();
    }

    public void setImeOptions(int i2) {
        this.q.setImeOptions(i2);
    }

    public void setInputType(int i2) {
        this.q.setInputType(i2);
    }

    public void setMaxWidth(int i2) {
        this.a0 = i2;
        requestLayout();
    }

    public void setOnCloseListener(l lVar) {
        this.M = lVar;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.N = onFocusChangeListener;
    }

    public void setOnQueryTextListener(m mVar) {
        this.L = mVar;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.P = onClickListener;
    }

    public void setOnSuggestionListener(n nVar) {
        this.O = nVar;
    }

    public void setQueryHint(CharSequence charSequence) {
        this.U = charSequence;
        r();
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.V = z;
        a.g.a.a aVar = this.S;
        if (aVar instanceof n0) {
            ((n0) aVar).a(z ? 2 : 1);
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.g0 = searchableInfo;
        if (this.g0 != null) {
            s();
            r();
        }
        this.b0 = n();
        if (this.b0) {
            this.q.setPrivateImeOptions("nm");
        }
        b(f());
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.T = z;
        b(f());
    }

    public void setSuggestionsAdapter(a.g.a.a aVar) {
        this.S = aVar;
        this.q.setAdapter(this.S);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class k {

        /* renamed from: a, reason: collision with root package name */
        private Method f901a;

        /* renamed from: b, reason: collision with root package name */
        private Method f902b;

        /* renamed from: c, reason: collision with root package name */
        private Method f903c;

        k() {
            try {
                this.f901a = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.f901a.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                this.f902b = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.f902b.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                this.f903c = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                this.f903c.setAccessible(true);
            } catch (NoSuchMethodException unused3) {
            }
        }

        void a(AutoCompleteTextView autoCompleteTextView) {
            Method method = this.f902b;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        void b(AutoCompleteTextView autoCompleteTextView) {
            Method method = this.f901a;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        void a(AutoCompleteTextView autoCompleteTextView, boolean z) {
            Method method = this.f903c;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, Boolean.valueOf(z));
                } catch (Exception unused) {
                }
            }
        }
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.a.a.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.A = new Rect();
        this.B = new Rect();
        this.C = new int[2];
        this.D = new int[2];
        this.i0 = new b();
        this.j0 = new c();
        this.k0 = new WeakHashMap<>();
        this.l0 = new f();
        this.m0 = new g();
        this.n0 = new h();
        this.o0 = new i();
        this.p0 = new j();
        this.q0 = new a();
        t0 a2 = t0.a(context, attributeSet, a.a.j.SearchView, i2, 0);
        LayoutInflater.from(context).inflate(a2.g(a.a.j.SearchView_layout, a.a.g.abc_search_view), (ViewGroup) this, true);
        this.q = (SearchAutoComplete) findViewById(a.a.f.search_src_text);
        this.q.setSearchView(this);
        this.r = findViewById(a.a.f.search_edit_frame);
        this.s = findViewById(a.a.f.search_plate);
        this.t = findViewById(a.a.f.submit_area);
        this.u = (ImageView) findViewById(a.a.f.search_button);
        this.v = (ImageView) findViewById(a.a.f.search_go_btn);
        this.w = (ImageView) findViewById(a.a.f.search_close_btn);
        this.x = (ImageView) findViewById(a.a.f.search_voice_btn);
        this.E = (ImageView) findViewById(a.a.f.search_mag_icon);
        a.f.l.w.a(this.s, a2.b(a.a.j.SearchView_queryBackground));
        a.f.l.w.a(this.t, a2.b(a.a.j.SearchView_submitBackground));
        this.u.setImageDrawable(a2.b(a.a.j.SearchView_searchIcon));
        this.v.setImageDrawable(a2.b(a.a.j.SearchView_goIcon));
        this.w.setImageDrawable(a2.b(a.a.j.SearchView_closeIcon));
        this.x.setImageDrawable(a2.b(a.a.j.SearchView_voiceIcon));
        this.E.setImageDrawable(a2.b(a.a.j.SearchView_searchIcon));
        this.F = a2.b(a.a.j.SearchView_searchHintIcon);
        v0.a(this.u, getResources().getString(a.a.h.abc_searchview_description_search));
        this.G = a2.g(a.a.j.SearchView_suggestionRowLayout, a.a.g.abc_search_dropdown_item_icons_2line);
        this.H = a2.g(a.a.j.SearchView_commitIcon, 0);
        this.u.setOnClickListener(this.l0);
        this.w.setOnClickListener(this.l0);
        this.v.setOnClickListener(this.l0);
        this.x.setOnClickListener(this.l0);
        this.q.setOnClickListener(this.l0);
        this.q.addTextChangedListener(this.q0);
        this.q.setOnEditorActionListener(this.n0);
        this.q.setOnItemClickListener(this.o0);
        this.q.setOnItemSelectedListener(this.p0);
        this.q.setOnKeyListener(this.m0);
        this.q.setOnFocusChangeListener(new d());
        setIconifiedByDefault(a2.a(a.a.j.SearchView_iconifiedByDefault, true));
        int c2 = a2.c(a.a.j.SearchView_android_maxWidth, -1);
        if (c2 != -1) {
            setMaxWidth(c2);
        }
        this.K = a2.e(a.a.j.SearchView_defaultQueryHint);
        this.U = a2.e(a.a.j.SearchView_queryHint);
        int d2 = a2.d(a.a.j.SearchView_android_imeOptions, -1);
        if (d2 != -1) {
            setImeOptions(d2);
        }
        int d3 = a2.d(a.a.j.SearchView_android_inputType, -1);
        if (d3 != -1) {
            setInputType(d3);
        }
        setFocusable(a2.a(a.a.j.SearchView_android_focusable, true));
        a2.a();
        this.I = new Intent("android.speech.action.WEB_SEARCH");
        this.I.addFlags(268435456);
        this.I.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        this.J = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.J.addFlags(268435456);
        this.y = findViewById(this.q.getDropDownAnchor());
        View view = this.y;
        if (view != null) {
            view.addOnLayoutChangeListener(new e());
        }
        b(this.Q);
        r();
    }

    private void a(View view, Rect rect) {
        view.getLocationInWindow(this.C);
        getLocationInWindow(this.D);
        int[] iArr = this.C;
        int i2 = iArr[1];
        int[] iArr2 = this.D;
        int i3 = i2 - iArr2[1];
        int i4 = iArr[0] - iArr2[0];
        rect.set(i4, i3, view.getWidth() + i4, view.getHeight() + i3);
    }

    private void c(boolean z) {
        int i2;
        if (this.b0 && !f() && z) {
            i2 = 0;
            this.v.setVisibility(8);
        } else {
            i2 = 8;
        }
        this.x.setVisibility(i2);
    }

    void e() {
        if (Build.VERSION.SDK_INT >= 29) {
            this.q.refreshAutoCompleteResults();
        } else {
            r0.b(this.q);
            r0.a(this.q);
        }
    }

    @Override // a.a.o.c
    public void c() {
        a("", false);
        clearFocus();
        b(true);
        this.q.setImeOptions(this.f0);
        this.e0 = false;
    }

    private void a(boolean z) {
        this.v.setVisibility((this.T && o() && hasFocus() && (z || !this.b0)) ? 0 : 8);
    }

    void b(CharSequence charSequence) {
        Editable text = this.q.getText();
        this.d0 = text;
        boolean z = !TextUtils.isEmpty(text);
        a(z);
        c(!z);
        q();
        t();
        if (this.L != null && !TextUtils.equals(charSequence, this.c0)) {
            this.L.a(charSequence.toString());
        }
        this.c0 = charSequence.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(CharSequence charSequence) {
        setQuery(charSequence);
    }

    boolean a(View view, int i2, KeyEvent keyEvent) {
        if (this.g0 != null && this.S != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i2 == 66 || i2 == 84 || i2 == 61) {
                return a(this.q.getListSelection(), 0, (String) null);
            }
            if (i2 != 21 && i2 != 22) {
                if (i2 != 19 || this.q.getListSelection() == 0) {
                    return false;
                }
            } else {
                this.q.setSelection(i2 == 21 ? 0 : this.q.length());
                this.q.setListSelection(0);
                this.q.clearListSelection();
                r0.a(this.q, true);
                return true;
            }
        }
        return false;
    }

    boolean d(int i2) {
        n nVar = this.O;
        if (nVar != null && nVar.a(i2)) {
            return false;
        }
        e(i2);
        return true;
    }

    @Override // a.a.o.c
    public void b() {
        if (this.e0) {
            return;
        }
        this.e0 = true;
        this.f0 = this.q.getImeOptions();
        this.q.setImeOptions(this.f0 | 33554432);
        this.q.setText("");
        setIconified(false);
    }

    boolean a(int i2, int i3, String str) {
        n nVar = this.O;
        if (nVar != null && nVar.b(i2)) {
            return false;
        }
        b(i2, 0, null);
        this.q.setImeVisibility(false);
        m();
        return true;
    }

    private boolean b(int i2, int i3, String str) {
        Cursor cursor = this.S.getCursor();
        if (cursor == null || !cursor.moveToPosition(i2)) {
            return false;
        }
        a(a(cursor, i3, str));
        return true;
    }

    private void a(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            getContext().startActivity(intent);
        } catch (RuntimeException e2) {
            Log.e("SearchView", "Failed launch activity: " + intent, e2);
        }
    }

    private Intent b(Intent intent, SearchableInfo searchableInfo) {
        Intent intent2 = new Intent(intent);
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        intent2.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
        return intent2;
    }

    void a(int i2, String str, String str2) {
        getContext().startActivity(a("android.intent.action.SEARCH", (Uri) null, (String) null, str2, i2, str));
    }

    private Intent a(String str, Uri uri, String str2, String str3, int i2, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.d0);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.h0;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i2 != 0) {
            intent.putExtra("action_key", i2);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.g0.getSearchActivity());
        return intent;
    }

    private Intent a(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1073741824);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.h0;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        Resources resources = getResources();
        String string = searchableInfo.getVoiceLanguageModeId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageModeId()) : "free_form";
        String string2 = searchableInfo.getVoicePromptTextId() != 0 ? resources.getString(searchableInfo.getVoicePromptTextId()) : null;
        String string3 = searchableInfo.getVoiceLanguageId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageId()) : null;
        int voiceMaxResults = searchableInfo.getVoiceMaxResults() != 0 ? searchableInfo.getVoiceMaxResults() : 1;
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", string);
        intent3.putExtra("android.speech.extra.PROMPT", string2);
        intent3.putExtra("android.speech.extra.LANGUAGE", string3);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", voiceMaxResults);
        intent3.putExtra("calling_package", searchActivity != null ? searchActivity.flattenToShortString() : null);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    private Intent a(Cursor cursor, int i2, String str) {
        int i3;
        String a2;
        try {
            String a3 = n0.a(cursor, "suggest_intent_action");
            if (a3 == null) {
                a3 = this.g0.getSuggestIntentAction();
            }
            if (a3 == null) {
                a3 = "android.intent.action.SEARCH";
            }
            String str2 = a3;
            String a4 = n0.a(cursor, "suggest_intent_data");
            if (a4 == null) {
                a4 = this.g0.getSuggestIntentData();
            }
            if (a4 != null && (a2 = n0.a(cursor, "suggest_intent_data_id")) != null) {
                a4 = a4 + "/" + Uri.encode(a2);
            }
            return a(str2, a4 == null ? null : Uri.parse(a4), n0.a(cursor, "suggest_intent_extra_data"), n0.a(cursor, "suggest_intent_query"), i2, str);
        } catch (RuntimeException e2) {
            try {
                i3 = cursor.getPosition();
            } catch (RuntimeException unused) {
                i3 = -1;
            }
            Log.w("SearchView", "Search suggestions cursor at row " + i3 + " returned exception.", e2);
            return null;
        }
    }

    static boolean a(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }
}
