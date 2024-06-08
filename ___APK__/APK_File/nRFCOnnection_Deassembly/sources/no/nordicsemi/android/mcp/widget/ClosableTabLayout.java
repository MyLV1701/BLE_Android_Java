package no.nordicsemi.android.mcp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class ClosableTabLayout extends TabLayout {
    private final View.OnClickListener mOnClickListener;
    private OnTabClosedListener mOnTabClosedListener;
    private ClosablePagerAdapter mPagerAdapter;

    /* loaded from: classes.dex */
    public interface OnTabClosedListener {
        void onTabClosed(int i);
    }

    public ClosableTabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOnClickListener = new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.widget.ClosableTabLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int c2 = ((TabLayout.h) view.getTag()).c();
                if (ClosableTabLayout.this.mOnTabClosedListener != null) {
                    ClosableTabLayout.this.mOnTabClosedListener.onTabClosed(c2);
                }
            }
        };
    }

    @Override // com.google.android.material.tabs.TabLayout
    public TabLayout.h newTab() {
        TabLayout.h newTab = super.newTab();
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.closable_tab, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(android.R.id.text2);
        View findViewById = inflate.findViewById(R.id.action_close);
        newTab.a(inflate);
        int tabCount = getTabCount();
        if (this.mPagerAdapter.isPageClosable(tabCount)) {
            findViewById.setVisibility(0);
            findViewById.setOnClickListener(this.mOnClickListener);
        }
        if (this.mPagerAdapter.getPageSubtitle(tabCount) != null) {
            textView.setText(this.mPagerAdapter.getPageSubtitle(tabCount));
            textView.setVisibility(0);
        }
        findViewById.setTag(newTab);
        return newTab;
    }

    public void setOnTabClosedListener(OnTabClosedListener onTabClosedListener) {
        this.mOnTabClosedListener = onTabClosedListener;
    }

    @Override // com.google.android.material.tabs.TabLayout
    public void setupWithViewPager(ViewPager viewPager) {
        this.mPagerAdapter = viewPager != null ? (ClosablePagerAdapter) viewPager.getAdapter() : null;
        super.setupWithViewPager(viewPager);
    }
}
