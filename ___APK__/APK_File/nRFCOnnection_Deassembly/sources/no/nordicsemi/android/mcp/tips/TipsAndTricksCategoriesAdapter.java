package no.nordicsemi.android.mcp.tips;

import a.f.l.w;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.InputStreamReader;
import java.io.Reader;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.tips.TipsAndTricksCategoriesAdapter;
import no.nordicsemi.android.mcp.tips.domain.TipsAndTricks;
import no.nordicsemi.android.mcp.tips.domain.TipsCategory;

/* loaded from: classes.dex */
public class TipsAndTricksCategoriesAdapter extends RecyclerView.g<ViewHolder> {
    private static final int TYPE_CATEGORY = 1;
    private static final int TYPE_INTRO = 0;
    private final int mColSpan;
    private final CategoryListener mListener;
    private final TipsAndTricks mTipsAndTricks;

    /* loaded from: classes.dex */
    interface CategoryListener {
        void onCategoryOpen(TipsCategory tipsCategory, View view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SpanSizeLookup extends GridLayoutManager.c {
        private final int colSpan;

        @Override // androidx.recyclerview.widget.GridLayoutManager.c
        public int getSpanSize(int i) {
            if (i == 0) {
                return this.colSpan;
            }
            return 1;
        }

        private SpanSizeLookup(int i) {
            this.colSpan = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ViewHolder extends RecyclerView.d0 {
        private final CardView card;
        private final ImageView image;
        private final TextView text;
        private final Toolbar toolbar;

        public /* synthetic */ void a(TipsCategory tipsCategory, View view) {
            TipsAndTricksCategoriesAdapter.this.mListener.onCategoryOpen(tipsCategory, this.card);
        }

        protected void assign(final TipsCategory tipsCategory) {
            w.a(this.card, tipsCategory.getTitle());
            this.card.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.tips.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TipsAndTricksCategoriesAdapter.ViewHolder.this.a(tipsCategory, view);
                }
            });
            this.text.setText(tipsCategory.getTitle());
            this.text.setCompoundDrawablesWithIntrinsicBounds(0, tipsCategory.getImageResId(this.text.getContext()), 0, 0);
        }

        private ViewHolder(View view) {
            super(view);
            this.card = (CardView) view;
            this.toolbar = (Toolbar) view.findViewById(R.id.toolbar_actionbar);
            this.image = (ImageView) view.findViewById(R.id.image);
            this.text = (TextView) view.findViewById(R.id.text);
        }

        protected void assign(TipsAndTricks tipsAndTricks) {
            this.toolbar.setTitle(tipsAndTricks.getTitle());
            this.image.setBackgroundResource(R.color.variant);
            ImageView imageView = this.image;
            imageView.setImageResource(tipsAndTricks.getImageResId(imageView.getContext()));
            this.text.setText(tipsAndTricks.getText());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TipsAndTricksCategoriesAdapter(Context context, CategoryListener categoryListener, int i) {
        this.mColSpan = i;
        this.mListener = categoryListener;
        this.mTipsAndTricks = (TipsAndTricks) new c.a.b.e().a((Reader) new InputStreamReader(context.getResources().openRawResource(R.raw.tips_and_tricks)), TipsAndTricks.class);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemCount() {
        return this.mTipsAndTricks.getCategoriesCount() + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemViewType(int i) {
        return i == 0 ? 0 : 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SpanSizeLookup getSpanSizeLookup() {
        return new SpanSizeLookup(this.mColSpan);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTipsCount() {
        return this.mTipsAndTricks.getTipsCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (getItemViewType(i) != 0) {
            viewHolder.assign(this.mTipsAndTricks.getCategory(i - 1));
        } else {
            viewHolder.assign(this.mTipsAndTricks);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i != 0) {
            return new ViewHolder(from.inflate(R.layout.tip_and_trick_category, viewGroup, false));
        }
        return new ViewHolder(from.inflate(R.layout.tip_and_trick_intro, viewGroup, false));
    }
}
