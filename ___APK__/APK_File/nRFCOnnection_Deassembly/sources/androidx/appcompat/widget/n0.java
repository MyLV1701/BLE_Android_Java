package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;
import no.nordicsemi.android.log.LogContract;

@SuppressLint({"RestrictedAPI"})
/* loaded from: classes.dex */
class n0 extends a.g.a.c implements View.OnClickListener {

    /* renamed from: e, reason: collision with root package name */
    private final SearchView f1056e;

    /* renamed from: f, reason: collision with root package name */
    private final SearchableInfo f1057f;
    private final Context g;
    private final WeakHashMap<String, Drawable.ConstantState> h;
    private final int i;
    private boolean j;
    private int k;
    private ColorStateList l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public final TextView f1058a;

        /* renamed from: b, reason: collision with root package name */
        public final TextView f1059b;

        /* renamed from: c, reason: collision with root package name */
        public final ImageView f1060c;

        /* renamed from: d, reason: collision with root package name */
        public final ImageView f1061d;

        /* renamed from: e, reason: collision with root package name */
        public final ImageView f1062e;

        public a(View view) {
            this.f1058a = (TextView) view.findViewById(R.id.text1);
            this.f1059b = (TextView) view.findViewById(R.id.text2);
            this.f1060c = (ImageView) view.findViewById(R.id.icon1);
            this.f1061d = (ImageView) view.findViewById(R.id.icon2);
            this.f1062e = (ImageView) view.findViewById(a.a.f.edit_query);
        }
    }

    public n0(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, Drawable.ConstantState> weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), (Cursor) null, true);
        this.j = false;
        this.k = 1;
        this.m = -1;
        this.n = -1;
        this.o = -1;
        this.p = -1;
        this.q = -1;
        this.r = -1;
        this.f1056e = searchView;
        this.f1057f = searchableInfo;
        this.i = searchView.getSuggestionCommitIconResId();
        this.g = context;
        this.h = weakHashMap;
    }

    private Drawable b(Cursor cursor) {
        int i = this.p;
        if (i == -1) {
            return null;
        }
        Drawable b2 = b(cursor.getString(i));
        return b2 != null ? b2 : a(cursor);
    }

    private Drawable c(Cursor cursor) {
        int i = this.q;
        if (i == -1) {
            return null;
        }
        return b(cursor.getString(i));
    }

    private void d(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras == null || extras.getBoolean("in_progress")) {
        }
    }

    public void a(int i) {
        this.k = i;
    }

    @Override // a.g.a.a
    public void bindView(View view, Context context, Cursor cursor) {
        CharSequence a2;
        a aVar = (a) view.getTag();
        int i = this.r;
        int i2 = i != -1 ? cursor.getInt(i) : 0;
        if (aVar.f1058a != null) {
            a(aVar.f1058a, a(cursor, this.m));
        }
        if (aVar.f1059b != null) {
            String a3 = a(cursor, this.o);
            if (a3 != null) {
                a2 = a((CharSequence) a3);
            } else {
                a2 = a(cursor, this.n);
            }
            if (TextUtils.isEmpty(a2)) {
                TextView textView = aVar.f1058a;
                if (textView != null) {
                    textView.setSingleLine(false);
                    aVar.f1058a.setMaxLines(2);
                }
            } else {
                TextView textView2 = aVar.f1058a;
                if (textView2 != null) {
                    textView2.setSingleLine(true);
                    aVar.f1058a.setMaxLines(1);
                }
            }
            a(aVar.f1059b, a2);
        }
        ImageView imageView = aVar.f1060c;
        if (imageView != null) {
            a(imageView, b(cursor), 4);
        }
        ImageView imageView2 = aVar.f1061d;
        if (imageView2 != null) {
            a(imageView2, c(cursor), 8);
        }
        int i3 = this.k;
        if (i3 != 2 && (i3 != 1 || (i2 & 1) == 0)) {
            aVar.f1062e.setVisibility(8);
            return;
        }
        aVar.f1062e.setVisibility(0);
        aVar.f1062e.setTag(aVar.f1058a.getText());
        aVar.f1062e.setOnClickListener(this);
    }

    @Override // a.g.a.a, a.g.a.b.a
    public void changeCursor(Cursor cursor) {
        if (this.j) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        try {
            super.changeCursor(cursor);
            if (cursor != null) {
                this.m = cursor.getColumnIndex("suggest_text_1");
                this.n = cursor.getColumnIndex("suggest_text_2");
                this.o = cursor.getColumnIndex("suggest_text_2_url");
                this.p = cursor.getColumnIndex("suggest_icon_1");
                this.q = cursor.getColumnIndex("suggest_icon_2");
                this.r = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e2) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e2);
        }
    }

    @Override // a.g.a.a, a.g.a.b.a
    public CharSequence convertToString(Cursor cursor) {
        String a2;
        String a3;
        if (cursor == null) {
            return null;
        }
        String a4 = a(cursor, "suggest_intent_query");
        if (a4 != null) {
            return a4;
        }
        if (this.f1057f.shouldRewriteQueryFromData() && (a3 = a(cursor, "suggest_intent_data")) != null) {
            return a3;
        }
        if (!this.f1057f.shouldRewriteQueryFromText() || (a2 = a(cursor, "suggest_text_1")) == null) {
            return null;
        }
        return a2;
    }

    @Override // a.g.a.a, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i, view, viewGroup);
        } catch (RuntimeException e2) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e2);
            View newDropDownView = newDropDownView(this.mContext, this.mCursor, viewGroup);
            if (newDropDownView != null) {
                ((a) newDropDownView.getTag()).f1058a.setText(e2.toString());
            }
            return newDropDownView;
        }
    }

    @Override // a.g.a.a, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i, view, viewGroup);
        } catch (RuntimeException e2) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e2);
            View newView = newView(this.mContext, this.mCursor, viewGroup);
            if (newView != null) {
                ((a) newView.getTag()).f1058a.setText(e2.toString());
            }
            return newView;
        }
    }

    @Override // a.g.a.a, android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return false;
    }

    @Override // a.g.a.c, a.g.a.a
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View newView = super.newView(context, cursor, viewGroup);
        newView.setTag(new a(newView));
        ((ImageView) newView.findViewById(a.a.f.edit_query)).setImageResource(this.i);
        return newView;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        d(getCursor());
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        d(getCursor());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.f1056e.a((CharSequence) tag);
        }
    }

    @Override // a.g.a.a, a.g.a.b.a
    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        String charSequence2 = charSequence == null ? "" : charSequence.toString();
        if (this.f1056e.getVisibility() == 0 && this.f1056e.getWindowVisibility() == 0) {
            try {
                Cursor a2 = a(this.f1057f, charSequence2, 50);
                if (a2 != null) {
                    a2.getCount();
                    return a2;
                }
            } catch (RuntimeException e2) {
                Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e2);
            }
        }
        return null;
    }

    private CharSequence a(CharSequence charSequence) {
        if (this.l == null) {
            TypedValue typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(a.a.a.textColorSearchUrl, typedValue, true);
            this.l = this.mContext.getResources().getColorStateList(typedValue.resourceId);
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.l, null), 0, charSequence.length(), 33);
        return spannableString;
    }

    private Drawable b(String str) {
        if (str == null || str.isEmpty() || "0".equals(str)) {
            return null;
        }
        try {
            int parseInt = Integer.parseInt(str);
            String str2 = "android.resource://" + this.g.getPackageName() + "/" + parseInt;
            Drawable a2 = a(str2);
            if (a2 != null) {
                return a2;
            }
            Drawable c2 = a.f.d.b.c(this.g, parseInt);
            a(str2, c2);
            return c2;
        } catch (Resources.NotFoundException unused) {
            Log.w("SuggestionsAdapter", "Icon resource not found: " + str);
            return null;
        } catch (NumberFormatException unused2) {
            Drawable a3 = a(str);
            if (a3 != null) {
                return a3;
            }
            Drawable b2 = b(Uri.parse(str));
            a(str, b2);
            return b2;
        }
    }

    private void a(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    private void a(ImageView imageView, Drawable drawable, int i) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i);
            return;
        }
        imageView.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    private Drawable b(Uri uri) {
        try {
            if ("android.resource".equals(uri.getScheme())) {
                try {
                    return a(uri);
                } catch (Resources.NotFoundException unused) {
                    throw new FileNotFoundException("Resource does not exist: " + uri);
                }
            }
            InputStream openInputStream = this.g.getContentResolver().openInputStream(uri);
            if (openInputStream != null) {
                try {
                    return Drawable.createFromStream(openInputStream, null);
                } finally {
                    try {
                        openInputStream.close();
                    } catch (IOException e2) {
                        Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e2);
                    }
                }
            }
            throw new FileNotFoundException("Failed to open " + uri);
        } catch (FileNotFoundException e3) {
            Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e3.getMessage());
            return null;
        }
        Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e3.getMessage());
        return null;
    }

    private Drawable a(String str) {
        Drawable.ConstantState constantState = this.h.get(str);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable();
    }

    private void a(String str, Drawable drawable) {
        if (drawable != null) {
            this.h.put(str, drawable.getConstantState());
        }
    }

    private Drawable a(Cursor cursor) {
        Drawable b2 = b(this.f1057f.getSearchActivity());
        return b2 != null ? b2 : this.mContext.getPackageManager().getDefaultActivityIcon();
    }

    private Drawable a(ComponentName componentName) {
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, 128);
            int iconResource = activityInfo.getIconResource();
            if (iconResource == 0) {
                return null;
            }
            Drawable drawable = packageManager.getDrawable(componentName.getPackageName(), iconResource, activityInfo.applicationInfo);
            if (drawable != null) {
                return drawable;
            }
            Log.w("SuggestionsAdapter", "Invalid icon resource " + iconResource + " for " + componentName.flattenToShortString());
            return null;
        } catch (PackageManager.NameNotFoundException e2) {
            Log.w("SuggestionsAdapter", e2.toString());
            return null;
        }
    }

    private Drawable b(ComponentName componentName) {
        String flattenToShortString = componentName.flattenToShortString();
        if (this.h.containsKey(flattenToShortString)) {
            Drawable.ConstantState constantState = this.h.get(flattenToShortString);
            if (constantState == null) {
                return null;
            }
            return constantState.newDrawable(this.g.getResources());
        }
        Drawable a2 = a(componentName);
        this.h.put(flattenToShortString, a2 != null ? a2.getConstantState() : null);
        return a2;
    }

    public static String a(Cursor cursor, String str) {
        return a(cursor, cursor.getColumnIndex(str));
    }

    private static String a(Cursor cursor, int i) {
        if (i == -1) {
            return null;
        }
        try {
            return cursor.getString(i);
        } catch (Exception e2) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e2);
            return null;
        }
    }

    Drawable a(Uri uri) {
        int parseInt;
        String authority = uri.getAuthority();
        if (!TextUtils.isEmpty(authority)) {
            try {
                Resources resourcesForApplication = this.mContext.getPackageManager().getResourcesForApplication(authority);
                List<String> pathSegments = uri.getPathSegments();
                if (pathSegments != null) {
                    int size = pathSegments.size();
                    if (size == 1) {
                        try {
                            parseInt = Integer.parseInt(pathSegments.get(0));
                        } catch (NumberFormatException unused) {
                            throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                        }
                    } else if (size == 2) {
                        parseInt = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
                    } else {
                        throw new FileNotFoundException("More than two path segments: " + uri);
                    }
                    if (parseInt != 0) {
                        return resourcesForApplication.getDrawable(parseInt);
                    }
                    throw new FileNotFoundException("No resource found for: " + uri);
                }
                throw new FileNotFoundException("No path: " + uri);
            } catch (PackageManager.NameNotFoundException unused2) {
                throw new FileNotFoundException("No package found for authority: " + uri);
            }
        }
        throw new FileNotFoundException("No authority: " + uri);
    }

    Cursor a(SearchableInfo searchableInfo, String str, int i) {
        String suggestAuthority;
        String[] strArr = null;
        if (searchableInfo == null || (suggestAuthority = searchableInfo.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder fragment = new Uri.Builder().scheme(LogContract.Session.Content.CONTENT).authority(suggestAuthority).query("").fragment("");
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            fragment.appendEncodedPath(suggestPath);
        }
        fragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            fragment.appendPath(str);
        }
        String[] strArr2 = strArr;
        if (i > 0) {
            fragment.appendQueryParameter("limit", String.valueOf(i));
        }
        return this.mContext.getContentResolver().query(fragment.build(), null, suggestSelection, strArr2, null);
    }
}
