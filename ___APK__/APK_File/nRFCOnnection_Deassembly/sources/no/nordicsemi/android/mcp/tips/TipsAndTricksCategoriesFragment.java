package no.nordicsemi.android.mcp.tips;

import a.f.l.e0;
import a.f.l.r;
import a.f.l.w;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.tips.TipsAndTricksCategoriesAdapter;

/* loaded from: classes.dex */
public class TipsAndTricksCategoriesFragment extends Fragment {
    public static final String PREFS_TIPS_SHOWN_COUNT = "tips_shown";
    public static final int TIPS_COUNT = 13;

    private int getActionBarHeight() {
        TypedValue typedValue = new TypedValue();
        if (requireActivity().getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics());
        }
        return getResources().getDimensionPixelOffset(R.dimen.status_bar_plus_toolbar_height);
    }

    public /* synthetic */ e0 a(RecyclerView recyclerView, View view, e0 e0Var) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.tips_card_margin);
        recyclerView.setPadding(dimensionPixelSize, e0Var.e() + getActionBarHeight(), dimensionPixelSize, dimensionPixelSize);
        return e0Var;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setEnterTransition(new a.n.d(1));
        setExitTransition(new a.n.d(2));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_tips_and_tricks_categories, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        w.L(view);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(android.R.id.list);
        w.a(view, new r() { // from class: no.nordicsemi.android.mcp.tips.d
            @Override // a.f.l.r
            public final e0 a(View view2, e0 e0Var) {
                return TipsAndTricksCategoriesFragment.this.a(recyclerView, view2, e0Var);
            }
        });
        recyclerView.setHasFixedSize(true);
        int integer = getResources().getInteger(R.integer.tips_categories_colnum);
        TipsAndTricksCategoriesAdapter tipsAndTricksCategoriesAdapter = new TipsAndTricksCategoriesAdapter(requireContext(), (TipsAndTricksCategoriesAdapter.CategoryListener) getActivity(), integer);
        recyclerView.setAdapter(tipsAndTricksCategoriesAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), integer);
        gridLayoutManager.a(tipsAndTricksCategoriesAdapter.getSpanSizeLookup());
        recyclerView.setLayoutManager(gridLayoutManager);
        PreferenceManager.getDefaultSharedPreferences(requireContext()).edit().putInt(PREFS_TIPS_SHOWN_COUNT, tipsAndTricksCategoriesAdapter.getTipsCount()).apply();
    }
}
