package no.nordicsemi.android.mcp.tips;

import a.f.l.e0;
import a.f.l.r;
import a.f.l.w;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.androidplot.util.PixelUtils;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.tips.domain.TipsCategory;

/* loaded from: classes.dex */
public class TipsAndTricksFragment extends Fragment {
    private static final String CATEGORY = "category";

    private int getActionBarHeight() {
        TypedValue typedValue = new TypedValue();
        if (requireActivity().getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics());
        }
        return getResources().getDimensionPixelOffset(R.dimen.status_bar_plus_toolbar_height);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TipsAndTricksFragment getInstance(TipsCategory tipsCategory) {
        TipsAndTricksFragment tipsAndTricksFragment = new TipsAndTricksFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(CATEGORY, tipsCategory);
        tipsAndTricksFragment.setArguments(bundle);
        return tipsAndTricksFragment;
    }

    public /* synthetic */ e0 a(ViewPager viewPager, View view, e0 e0Var) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.tips_card_margin);
        viewPager.setPadding(dimensionPixelSize, e0Var.e() + getActionBarHeight(), dimensionPixelSize, dimensionPixelSize);
        return e0Var;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setSharedElementEnterTransition(new a.n.b());
        setEnterTransition(new a.n.d(1));
        setExitTransition(new a.n.d(2));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_tips_and_tricks, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        w.L(view);
        TipsCategory tipsCategory = (TipsCategory) getArguments().getParcelable(CATEGORY);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        w.a(view, new r() { // from class: no.nordicsemi.android.mcp.tips.e
            @Override // a.f.l.r
            public final e0 a(View view2, e0 e0Var) {
                return TipsAndTricksFragment.this.a(viewPager, view2, e0Var);
            }
        });
        viewPager.setAdapter(new TipsAndTricksAdapter(tipsCategory));
        viewPager.setPageMargin((int) PixelUtils.dpToPix(12.0f));
    }
}
