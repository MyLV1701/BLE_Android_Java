package no.nordicsemi.android.mcp.tutorial;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.d;
import androidx.fragment.app.p;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class PagesAdapter extends p {
    private final int mPagesCount;
    private final int mTextRes;
    private final int mTitleRes;
    private final String mVersionName;

    /* loaded from: classes.dex */
    public static class PageFragment extends Fragment {
        private static final String ARG_POSITION = "position";
        private static final String ARG_TEXT_RES = "textRes";
        private static final String ARG_TITLE_RES = "titleRes";
        private static final String ARG_VERSION = "versionName";

        /* JADX INFO: Access modifiers changed from: private */
        public static PageFragment getInstance(int i, int i2, int i3, String str) {
            PageFragment pageFragment = new PageFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ARG_POSITION, i3);
            bundle.putInt(ARG_TITLE_RES, i);
            bundle.putInt(ARG_TEXT_RES, i2);
            bundle.putString(ARG_VERSION, str);
            pageFragment.setArguments(bundle);
            return pageFragment;
        }

        @Override // androidx.fragment.app.Fragment
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            View inflate = layoutInflater.inflate(R.layout.fragment_tutorial_page, viewGroup, false);
            TextView textView = (TextView) inflate.findViewById(R.id.title);
            TextView textView2 = (TextView) inflate.findViewById(R.id.text);
            Bundle arguments = getArguments();
            int i = arguments.getInt(ARG_POSITION);
            textView.setText(getResources().getStringArray(arguments.getInt(ARG_TITLE_RES))[i]);
            textView2.setText(getResources().getStringArray(arguments.getInt(ARG_TEXT_RES))[i].replaceAll("VERSION", arguments.getString(ARG_VERSION)));
            return inflate;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PagesAdapter(d dVar, int i, int i2) {
        super(dVar.getSupportFragmentManager());
        String str;
        try {
            str = dVar.getPackageManager().getPackageInfo(dVar.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            str = "4";
        }
        this.mVersionName = str;
        if (i == 0) {
            this.mPagesCount = dVar.getResources().getStringArray(R.array.tutorial_title).length;
            this.mTitleRes = R.array.tutorial_title;
            this.mTextRes = R.array.tutorial_text;
        } else {
            this.mPagesCount = dVar.getResources().getStringArray(R.array.tutorial_title_update).length;
            this.mTitleRes = R.array.tutorial_title_update;
            this.mTextRes = R.array.tutorial_text_update;
        }
    }

    @Override // androidx.viewpager.widget.a
    public int getCount() {
        return this.mPagesCount;
    }

    @Override // androidx.fragment.app.p
    public Fragment getItem(int i) {
        return PageFragment.getInstance(this.mTitleRes, this.mTextRes, i, this.mVersionName);
    }
}
