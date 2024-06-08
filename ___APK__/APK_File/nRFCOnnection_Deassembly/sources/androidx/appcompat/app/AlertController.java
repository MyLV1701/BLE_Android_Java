package androidx.appcompat.app;

import a.f.l.w;
import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.e0;
import androidx.core.widget.NestedScrollView;
import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class AlertController {
    NestedScrollView A;
    private Drawable C;
    private ImageView D;
    private TextView E;
    private TextView F;
    private View G;
    ListAdapter H;
    private int J;
    private int K;
    int L;
    int M;
    int N;
    int O;
    private boolean P;
    Handler R;

    /* renamed from: a, reason: collision with root package name */
    private final Context f609a;

    /* renamed from: b, reason: collision with root package name */
    final i f610b;

    /* renamed from: c, reason: collision with root package name */
    private final Window f611c;

    /* renamed from: d, reason: collision with root package name */
    private final int f612d;

    /* renamed from: e, reason: collision with root package name */
    private CharSequence f613e;

    /* renamed from: f, reason: collision with root package name */
    private CharSequence f614f;
    ListView g;
    private View h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    Button o;
    private CharSequence p;
    Message q;
    private Drawable r;
    Button s;
    private CharSequence t;
    Message u;
    private Drawable v;
    Button w;
    private CharSequence x;
    Message y;
    private Drawable z;
    private boolean n = false;
    private int B = 0;
    int I = -1;
    private int Q = 0;
    private final View.OnClickListener S = new a();

    /* loaded from: classes.dex */
    public static class RecycleListView extends ListView {

        /* renamed from: b, reason: collision with root package name */
        private final int f615b;

        /* renamed from: c, reason: collision with root package name */
        private final int f616c;

        public RecycleListView(Context context) {
            this(context, null);
        }

        public void a(boolean z, boolean z2) {
            if (z2 && z) {
                return;
            }
            setPadding(getPaddingLeft(), z ? getPaddingTop() : this.f615b, getPaddingRight(), z2 ? getPaddingBottom() : this.f616c);
        }

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.a.j.RecycleListView);
            this.f616c = obtainStyledAttributes.getDimensionPixelOffset(a.a.j.RecycleListView_paddingBottomNoButtons, -1);
            this.f615b = obtainStyledAttributes.getDimensionPixelOffset(a.a.j.RecycleListView_paddingTopNoTitle, -1);
        }
    }

    /* loaded from: classes.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Message obtain;
            Message message;
            Message message2;
            Message message3;
            AlertController alertController = AlertController.this;
            if (view == alertController.o && (message3 = alertController.q) != null) {
                obtain = Message.obtain(message3);
            } else {
                AlertController alertController2 = AlertController.this;
                if (view == alertController2.s && (message2 = alertController2.u) != null) {
                    obtain = Message.obtain(message2);
                } else {
                    AlertController alertController3 = AlertController.this;
                    obtain = (view != alertController3.w || (message = alertController3.y) == null) ? null : Message.obtain(message);
                }
            }
            if (obtain != null) {
                obtain.sendToTarget();
            }
            AlertController alertController4 = AlertController.this;
            alertController4.R.obtainMessage(1, alertController4.f610b).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements NestedScrollView.b {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f618a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f619b;

        b(AlertController alertController, View view, View view2) {
            this.f618a = view;
            this.f619b = view2;
        }

        @Override // androidx.core.widget.NestedScrollView.b
        public void a(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
            AlertController.a(nestedScrollView, this.f618a, this.f619b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f620b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f621c;

        c(View view, View view2) {
            this.f620b = view;
            this.f621c = view2;
        }

        @Override // java.lang.Runnable
        public void run() {
            AlertController.a(AlertController.this.A, this.f620b, this.f621c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements AbsListView.OnScrollListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f623a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f624b;

        d(AlertController alertController, View view, View view2) {
            this.f623a = view;
            this.f624b = view2;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            AlertController.a(absListView, this.f623a, this.f624b);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f625b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f626c;

        e(View view, View view2) {
            this.f625b = view;
            this.f626c = view2;
        }

        @Override // java.lang.Runnable
        public void run() {
            AlertController.a(AlertController.this.g, this.f625b, this.f626c);
        }
    }

    /* loaded from: classes.dex */
    public static class f {
        public int A;
        public int B;
        public int C;
        public int D;
        public boolean[] F;
        public boolean G;
        public boolean H;
        public DialogInterface.OnMultiChoiceClickListener J;
        public Cursor K;
        public String L;
        public String M;
        public AdapterView.OnItemSelectedListener N;
        public e O;

        /* renamed from: a, reason: collision with root package name */
        public final Context f628a;

        /* renamed from: b, reason: collision with root package name */
        public final LayoutInflater f629b;

        /* renamed from: d, reason: collision with root package name */
        public Drawable f631d;

        /* renamed from: f, reason: collision with root package name */
        public CharSequence f633f;
        public View g;
        public CharSequence h;
        public CharSequence i;
        public Drawable j;
        public DialogInterface.OnClickListener k;
        public CharSequence l;
        public Drawable m;
        public DialogInterface.OnClickListener n;
        public CharSequence o;
        public Drawable p;
        public DialogInterface.OnClickListener q;
        public DialogInterface.OnCancelListener s;
        public DialogInterface.OnDismissListener t;
        public DialogInterface.OnKeyListener u;
        public CharSequence[] v;
        public ListAdapter w;
        public DialogInterface.OnClickListener x;
        public int y;
        public View z;

        /* renamed from: c, reason: collision with root package name */
        public int f630c = 0;

        /* renamed from: e, reason: collision with root package name */
        public int f632e = 0;
        public boolean E = false;
        public int I = -1;
        public boolean r = true;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a extends ArrayAdapter<CharSequence> {

            /* renamed from: b, reason: collision with root package name */
            final /* synthetic */ RecycleListView f634b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(Context context, int i, int i2, CharSequence[] charSequenceArr, RecycleListView recycleListView) {
                super(context, i, i2, charSequenceArr);
                this.f634b = recycleListView;
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(int i, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i, view, viewGroup);
                boolean[] zArr = f.this.F;
                if (zArr != null && zArr[i]) {
                    this.f634b.setItemChecked(i, true);
                }
                return view2;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class b extends CursorAdapter {

            /* renamed from: b, reason: collision with root package name */
            private final int f636b;

            /* renamed from: c, reason: collision with root package name */
            private final int f637c;

            /* renamed from: d, reason: collision with root package name */
            final /* synthetic */ RecycleListView f638d;

            /* renamed from: e, reason: collision with root package name */
            final /* synthetic */ AlertController f639e;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(Context context, Cursor cursor, boolean z, RecycleListView recycleListView, AlertController alertController) {
                super(context, cursor, z);
                this.f638d = recycleListView;
                this.f639e = alertController;
                Cursor cursor2 = getCursor();
                this.f636b = cursor2.getColumnIndexOrThrow(f.this.L);
                this.f637c = cursor2.getColumnIndexOrThrow(f.this.M);
            }

            @Override // android.widget.CursorAdapter
            public void bindView(View view, Context context, Cursor cursor) {
                ((CheckedTextView) view.findViewById(R.id.text1)).setText(cursor.getString(this.f636b));
                this.f638d.setItemChecked(cursor.getPosition(), cursor.getInt(this.f637c) == 1);
            }

            @Override // android.widget.CursorAdapter
            public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                return f.this.f629b.inflate(this.f639e.M, viewGroup, false);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class c implements AdapterView.OnItemClickListener {

            /* renamed from: b, reason: collision with root package name */
            final /* synthetic */ AlertController f641b;

            c(AlertController alertController) {
                this.f641b = alertController;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                f.this.x.onClick(this.f641b.f610b, i);
                if (f.this.H) {
                    return;
                }
                this.f641b.f610b.dismiss();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class d implements AdapterView.OnItemClickListener {

            /* renamed from: b, reason: collision with root package name */
            final /* synthetic */ RecycleListView f643b;

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ AlertController f644c;

            d(RecycleListView recycleListView, AlertController alertController) {
                this.f643b = recycleListView;
                this.f644c = alertController;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                boolean[] zArr = f.this.F;
                if (zArr != null) {
                    zArr[i] = this.f643b.isItemChecked(i);
                }
                f.this.J.onClick(this.f644c.f610b, i, this.f643b.isItemChecked(i));
            }
        }

        /* loaded from: classes.dex */
        public interface e {
            void a(ListView listView);
        }

        public f(Context context) {
            this.f628a = context;
            this.f629b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        private void b(AlertController alertController) {
            int i;
            ListAdapter listAdapter;
            RecycleListView recycleListView = (RecycleListView) this.f629b.inflate(alertController.L, (ViewGroup) null);
            if (this.G) {
                Cursor cursor = this.K;
                if (cursor == null) {
                    listAdapter = new a(this.f628a, alertController.M, R.id.text1, this.v, recycleListView);
                } else {
                    listAdapter = new b(this.f628a, cursor, false, recycleListView, alertController);
                }
            } else {
                if (this.H) {
                    i = alertController.N;
                } else {
                    i = alertController.O;
                }
                int i2 = i;
                Cursor cursor2 = this.K;
                if (cursor2 != null) {
                    listAdapter = new SimpleCursorAdapter(this.f628a, i2, cursor2, new String[]{this.L}, new int[]{R.id.text1});
                } else {
                    listAdapter = this.w;
                    if (listAdapter == null) {
                        listAdapter = new h(this.f628a, i2, R.id.text1, this.v);
                    }
                }
            }
            e eVar = this.O;
            if (eVar != null) {
                eVar.a(recycleListView);
            }
            alertController.H = listAdapter;
            alertController.I = this.I;
            if (this.x != null) {
                recycleListView.setOnItemClickListener(new c(alertController));
            } else if (this.J != null) {
                recycleListView.setOnItemClickListener(new d(recycleListView, alertController));
            }
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.N;
            if (onItemSelectedListener != null) {
                recycleListView.setOnItemSelectedListener(onItemSelectedListener);
            }
            if (this.H) {
                recycleListView.setChoiceMode(1);
            } else if (this.G) {
                recycleListView.setChoiceMode(2);
            }
            alertController.g = recycleListView;
        }

        public void a(AlertController alertController) {
            View view = this.g;
            if (view != null) {
                alertController.a(view);
            } else {
                CharSequence charSequence = this.f633f;
                if (charSequence != null) {
                    alertController.b(charSequence);
                }
                Drawable drawable = this.f631d;
                if (drawable != null) {
                    alertController.a(drawable);
                }
                int i = this.f630c;
                if (i != 0) {
                    alertController.c(i);
                }
                int i2 = this.f632e;
                if (i2 != 0) {
                    alertController.c(alertController.b(i2));
                }
            }
            CharSequence charSequence2 = this.h;
            if (charSequence2 != null) {
                alertController.a(charSequence2);
            }
            if (this.i != null || this.j != null) {
                alertController.a(-1, this.i, this.k, (Message) null, this.j);
            }
            if (this.l != null || this.m != null) {
                alertController.a(-2, this.l, this.n, (Message) null, this.m);
            }
            if (this.o != null || this.p != null) {
                alertController.a(-3, this.o, this.q, (Message) null, this.p);
            }
            if (this.v != null || this.K != null || this.w != null) {
                b(alertController);
            }
            View view2 = this.z;
            if (view2 != null) {
                if (this.E) {
                    alertController.a(view2, this.A, this.B, this.C, this.D);
                    return;
                } else {
                    alertController.b(view2);
                    return;
                }
            }
            int i3 = this.y;
            if (i3 != 0) {
                alertController.d(i3);
            }
        }
    }

    /* loaded from: classes.dex */
    private static final class g extends Handler {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<DialogInterface> f646a;

        public g(DialogInterface dialogInterface) {
            this.f646a = new WeakReference<>(dialogInterface);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == -3 || i == -2 || i == -1) {
                ((DialogInterface.OnClickListener) message.obj).onClick(this.f646a.get(), message.what);
            } else {
                if (i != 1) {
                    return;
                }
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class h extends ArrayAdapter<CharSequence> {
        public h(Context context, int i, int i2, CharSequence[] charSequenceArr) {
            super(context, i, i2, charSequenceArr);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }

    public AlertController(Context context, i iVar, Window window) {
        this.f609a = context;
        this.f610b = iVar;
        this.f611c = window;
        this.R = new g(iVar);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, a.a.j.AlertDialog, a.a.a.alertDialogStyle, 0);
        this.J = obtainStyledAttributes.getResourceId(a.a.j.AlertDialog_android_layout, 0);
        this.K = obtainStyledAttributes.getResourceId(a.a.j.AlertDialog_buttonPanelSideLayout, 0);
        this.L = obtainStyledAttributes.getResourceId(a.a.j.AlertDialog_listLayout, 0);
        this.M = obtainStyledAttributes.getResourceId(a.a.j.AlertDialog_multiChoiceItemLayout, 0);
        this.N = obtainStyledAttributes.getResourceId(a.a.j.AlertDialog_singleChoiceItemLayout, 0);
        this.O = obtainStyledAttributes.getResourceId(a.a.j.AlertDialog_listItemLayout, 0);
        this.P = obtainStyledAttributes.getBoolean(a.a.j.AlertDialog_showTitle, true);
        this.f612d = obtainStyledAttributes.getDimensionPixelSize(a.a.j.AlertDialog_buttonIconDimen, 0);
        obtainStyledAttributes.recycle();
        iVar.a(1);
    }

    private static boolean a(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(a.a.a.alertDialogCenterButtons, typedValue, true);
        return typedValue.data != 0;
    }

    static boolean c(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (c(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    public void b() {
        this.f610b.setContentView(c());
        d();
    }

    public void d(int i) {
        this.h = null;
        this.i = i;
        this.n = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void d() {
        View findViewById;
        ListAdapter listAdapter;
        View findViewById2;
        View findViewById3 = this.f611c.findViewById(a.a.f.parentPanel);
        View findViewById4 = findViewById3.findViewById(a.a.f.topPanel);
        View findViewById5 = findViewById3.findViewById(a.a.f.contentPanel);
        View findViewById6 = findViewById3.findViewById(a.a.f.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) findViewById3.findViewById(a.a.f.customPanel);
        c(viewGroup);
        View findViewById7 = viewGroup.findViewById(a.a.f.topPanel);
        View findViewById8 = viewGroup.findViewById(a.a.f.contentPanel);
        View findViewById9 = viewGroup.findViewById(a.a.f.buttonPanel);
        ViewGroup a2 = a(findViewById7, findViewById4);
        ViewGroup a3 = a(findViewById8, findViewById5);
        ViewGroup a4 = a(findViewById9, findViewById6);
        b(a3);
        a(a4);
        d(a2);
        boolean z = (viewGroup == null || viewGroup.getVisibility() == 8) ? false : true;
        boolean z2 = (a2 == null || a2.getVisibility() == 8) ? 0 : 1;
        boolean z3 = (a4 == null || a4.getVisibility() == 8) ? false : true;
        if (!z3 && a3 != null && (findViewById2 = a3.findViewById(a.a.f.textSpacerNoButtons)) != null) {
            findViewById2.setVisibility(0);
        }
        if (z2 != 0) {
            NestedScrollView nestedScrollView = this.A;
            if (nestedScrollView != null) {
                nestedScrollView.setClipToPadding(true);
            }
            View findViewById10 = (this.f614f == null && this.g == null) ? null : a2.findViewById(a.a.f.titleDividerNoCustom);
            if (findViewById10 != null) {
                findViewById10.setVisibility(0);
            }
        } else if (a3 != null && (findViewById = a3.findViewById(a.a.f.textSpacerNoTitle)) != null) {
            findViewById.setVisibility(0);
        }
        ListView listView = this.g;
        if (listView instanceof RecycleListView) {
            ((RecycleListView) listView).a(z2, z3);
        }
        if (!z) {
            View view = this.g;
            if (view == null) {
                view = this.A;
            }
            if (view != null) {
                a(a3, view, z2 | (z3 ? 2 : 0), 3);
            }
        }
        ListView listView2 = this.g;
        if (listView2 == null || (listAdapter = this.H) == null) {
            return;
        }
        listView2.setAdapter(listAdapter);
        int i = this.I;
        if (i > -1) {
            listView2.setItemChecked(i, true);
            listView2.setSelection(i);
        }
    }

    public void a(View view) {
        this.G = view;
    }

    public void b(CharSequence charSequence) {
        this.f613e = charSequence;
        TextView textView = this.E;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void a(CharSequence charSequence) {
        this.f614f = charSequence;
        TextView textView = this.F;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    private int c() {
        int i = this.K;
        if (i == 0) {
            return this.J;
        }
        return this.Q == 1 ? i : this.J;
    }

    public void b(View view) {
        this.h = view;
        this.i = 0;
        this.n = false;
    }

    public void a(View view, int i, int i2, int i3, int i4) {
        this.h = view;
        this.i = 0;
        this.n = true;
        this.j = i;
        this.k = i2;
        this.l = i3;
        this.m = i4;
    }

    public int b(int i) {
        TypedValue typedValue = new TypedValue();
        this.f609a.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.resourceId;
    }

    public void c(int i) {
        this.C = null;
        this.B = i;
        ImageView imageView = this.D;
        if (imageView != null) {
            if (i != 0) {
                imageView.setVisibility(0);
                this.D.setImageResource(this.B);
            } else {
                imageView.setVisibility(8);
            }
        }
    }

    public boolean b(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.A;
        return nestedScrollView != null && nestedScrollView.a(keyEvent);
    }

    private void b(ViewGroup viewGroup) {
        this.A = (NestedScrollView) this.f611c.findViewById(a.a.f.scrollView);
        this.A.setFocusable(false);
        this.A.setNestedScrollingEnabled(false);
        this.F = (TextView) viewGroup.findViewById(R.id.message);
        TextView textView = this.F;
        if (textView == null) {
            return;
        }
        CharSequence charSequence = this.f614f;
        if (charSequence != null) {
            textView.setText(charSequence);
            return;
        }
        textView.setVisibility(8);
        this.A.removeView(this.F);
        if (this.g != null) {
            ViewGroup viewGroup2 = (ViewGroup) this.A.getParent();
            int indexOfChild = viewGroup2.indexOfChild(this.A);
            viewGroup2.removeViewAt(indexOfChild);
            viewGroup2.addView(this.g, indexOfChild, new ViewGroup.LayoutParams(-1, -1));
            return;
        }
        viewGroup.setVisibility(8);
    }

    public void a(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message, Drawable drawable) {
        if (message == null && onClickListener != null) {
            message = this.R.obtainMessage(i, onClickListener);
        }
        if (i == -3) {
            this.x = charSequence;
            this.y = message;
            this.z = drawable;
        } else if (i == -2) {
            this.t = charSequence;
            this.u = message;
            this.v = drawable;
        } else {
            if (i == -1) {
                this.p = charSequence;
                this.q = message;
                this.r = drawable;
                return;
            }
            throw new IllegalArgumentException("Button does not exist");
        }
    }

    private void c(ViewGroup viewGroup) {
        View view = this.h;
        if (view == null) {
            view = this.i != 0 ? LayoutInflater.from(this.f609a).inflate(this.i, viewGroup, false) : null;
        }
        boolean z = view != null;
        if (!z || !c(view)) {
            this.f611c.setFlags(131072, 131072);
        }
        if (z) {
            FrameLayout frameLayout = (FrameLayout) this.f611c.findViewById(a.a.f.custom);
            frameLayout.addView(view, new ViewGroup.LayoutParams(-1, -1));
            if (this.n) {
                frameLayout.setPadding(this.j, this.k, this.l, this.m);
            }
            if (this.g != null) {
                ((e0.a) viewGroup.getLayoutParams()).f977a = 0.0f;
                return;
            }
            return;
        }
        viewGroup.setVisibility(8);
    }

    public void a(Drawable drawable) {
        this.C = drawable;
        this.B = 0;
        ImageView imageView = this.D;
        if (imageView != null) {
            if (drawable != null) {
                imageView.setVisibility(0);
                this.D.setImageDrawable(drawable);
            } else {
                imageView.setVisibility(8);
            }
        }
    }

    public ListView a() {
        return this.g;
    }

    public Button a(int i) {
        if (i == -3) {
            return this.w;
        }
        if (i == -2) {
            return this.s;
        }
        if (i != -1) {
            return null;
        }
        return this.o;
    }

    public boolean a(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.A;
        return nestedScrollView != null && nestedScrollView.a(keyEvent);
    }

    private ViewGroup a(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    private void d(ViewGroup viewGroup) {
        if (this.G != null) {
            viewGroup.addView(this.G, 0, new ViewGroup.LayoutParams(-1, -2));
            this.f611c.findViewById(a.a.f.title_template).setVisibility(8);
            return;
        }
        this.D = (ImageView) this.f611c.findViewById(R.id.icon);
        if ((!TextUtils.isEmpty(this.f613e)) && this.P) {
            this.E = (TextView) this.f611c.findViewById(a.a.f.alertTitle);
            this.E.setText(this.f613e);
            int i = this.B;
            if (i != 0) {
                this.D.setImageResource(i);
                return;
            }
            Drawable drawable = this.C;
            if (drawable != null) {
                this.D.setImageDrawable(drawable);
                return;
            } else {
                this.E.setPadding(this.D.getPaddingLeft(), this.D.getPaddingTop(), this.D.getPaddingRight(), this.D.getPaddingBottom());
                this.D.setVisibility(8);
                return;
            }
        }
        this.f611c.findViewById(a.a.f.title_template).setVisibility(8);
        this.D.setVisibility(8);
        viewGroup.setVisibility(8);
    }

    private void a(ViewGroup viewGroup, View view, int i, int i2) {
        View findViewById = this.f611c.findViewById(a.a.f.scrollIndicatorUp);
        View findViewById2 = this.f611c.findViewById(a.a.f.scrollIndicatorDown);
        if (Build.VERSION.SDK_INT >= 23) {
            w.a(view, i, i2);
            if (findViewById != null) {
                viewGroup.removeView(findViewById);
            }
            if (findViewById2 != null) {
                viewGroup.removeView(findViewById2);
                return;
            }
            return;
        }
        View view2 = null;
        if (findViewById != null && (i & 1) == 0) {
            viewGroup.removeView(findViewById);
            findViewById = null;
        }
        if (findViewById2 == null || (i & 2) != 0) {
            view2 = findViewById2;
        } else {
            viewGroup.removeView(findViewById2);
        }
        if (findViewById == null && view2 == null) {
            return;
        }
        if (this.f614f != null) {
            this.A.setOnScrollChangeListener(new b(this, findViewById, view2));
            this.A.post(new c(findViewById, view2));
            return;
        }
        ListView listView = this.g;
        if (listView != null) {
            listView.setOnScrollListener(new d(this, findViewById, view2));
            this.g.post(new e(findViewById, view2));
            return;
        }
        if (findViewById != null) {
            viewGroup.removeView(findViewById);
        }
        if (view2 != null) {
            viewGroup.removeView(view2);
        }
    }

    static void a(View view, View view2, View view3) {
        if (view2 != null) {
            view2.setVisibility(view.canScrollVertically(-1) ? 0 : 4);
        }
        if (view3 != null) {
            view3.setVisibility(view.canScrollVertically(1) ? 0 : 4);
        }
    }

    private void a(ViewGroup viewGroup) {
        int i;
        this.o = (Button) viewGroup.findViewById(R.id.button1);
        this.o.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.p) && this.r == null) {
            this.o.setVisibility(8);
            i = 0;
        } else {
            this.o.setText(this.p);
            Drawable drawable = this.r;
            if (drawable != null) {
                int i2 = this.f612d;
                drawable.setBounds(0, 0, i2, i2);
                this.o.setCompoundDrawables(this.r, null, null, null);
            }
            this.o.setVisibility(0);
            i = 1;
        }
        this.s = (Button) viewGroup.findViewById(R.id.button2);
        this.s.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.t) && this.v == null) {
            this.s.setVisibility(8);
        } else {
            this.s.setText(this.t);
            Drawable drawable2 = this.v;
            if (drawable2 != null) {
                int i3 = this.f612d;
                drawable2.setBounds(0, 0, i3, i3);
                this.s.setCompoundDrawables(this.v, null, null, null);
            }
            this.s.setVisibility(0);
            i |= 2;
        }
        this.w = (Button) viewGroup.findViewById(R.id.button3);
        this.w.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.x) && this.z == null) {
            this.w.setVisibility(8);
        } else {
            this.w.setText(this.x);
            Drawable drawable3 = this.r;
            if (drawable3 != null) {
                int i4 = this.f612d;
                drawable3.setBounds(0, 0, i4, i4);
                this.o.setCompoundDrawables(this.r, null, null, null);
            }
            this.w.setVisibility(0);
            i |= 4;
        }
        if (a(this.f609a)) {
            if (i == 1) {
                a(this.o);
            } else if (i == 2) {
                a(this.s);
            } else if (i == 4) {
                a(this.w);
            }
        }
        if (i != 0) {
            return;
        }
        viewGroup.setVisibility(8);
    }

    private void a(Button button) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams(layoutParams);
    }
}
