package no.nordicsemi.android.mcp.tips;

import a.f.l.e0;
import a.f.l.r;
import a.f.l.w;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.t;
import no.nordicsemi.android.mcp.BaseActivity;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.tips.TipsAndTricksCategoriesAdapter;
import no.nordicsemi.android.mcp.tips.domain.TipsCategory;

/* loaded from: classes.dex */
public class TipsAndTricksActivity extends BaseActivity implements TipsAndTricksCategoriesAdapter.CategoryListener {
    private boolean isCrossShowing = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ e0 a(View view, View view2, e0 e0Var) {
        ((RelativeLayout.LayoutParams) view2.getLayoutParams()).height = e0Var.e();
        ((RelativeLayout.LayoutParams) view.getLayoutParams()).topMargin = e0Var.e();
        return e0Var;
    }

    private void toggleAnimation() {
        a.o.a.a.c a2 = a.o.a.a.c.a(this, this.isCrossShowing ? R.drawable.vector_cross_to_back_arrow : R.drawable.vector_back_arrow_to_cross);
        getSupportActionBar().a(a2);
        a2.start();
        this.isCrossShowing = !this.isCrossShowing;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        toggleAnimation();
    }

    @Override // no.nordicsemi.android.mcp.tips.TipsAndTricksCategoriesAdapter.CategoryListener
    public void onCategoryOpen(TipsCategory tipsCategory, View view) {
        TipsAndTricksFragment tipsAndTricksFragment = TipsAndTricksFragment.getInstance(tipsCategory);
        t b2 = getSupportFragmentManager().b();
        b2.a(view, "first");
        b2.b(R.id.container, tipsAndTricksFragment);
        b2.a((String) null);
        b2.a();
        toggleAnimation();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.BaseActivity, androidx.appcompat.app.e, androidx.fragment.app.d, androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_tips_and_tricks);
        final View findViewById = findViewById(R.id.appbar);
        w.a(findViewById(R.id.fake_appbar), new r() { // from class: no.nordicsemi.android.mcp.tips.a
            @Override // a.f.l.r
            public final e0 a(View view, e0 e0Var) {
                TipsAndTricksActivity.a(findViewById, view, e0Var);
                return e0Var;
            }
        });
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_actionbar));
        getSupportActionBar().c(R.drawable.vector_cross_to_back_arrow);
        setTitle((CharSequence) null);
        if (bundle == null) {
            TipsAndTricksCategoriesFragment tipsAndTricksCategoriesFragment = new TipsAndTricksCategoriesFragment();
            t b2 = getSupportFragmentManager().b();
            b2.a(R.id.container, tipsAndTricksCategoriesFragment);
            b2.a();
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }
}
