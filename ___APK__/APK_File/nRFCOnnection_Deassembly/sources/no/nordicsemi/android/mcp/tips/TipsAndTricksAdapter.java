package no.nordicsemi.android.mcp.tips;

import a.f.l.w;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.tips.TipsAndTricksAdapter;
import no.nordicsemi.android.mcp.tips.domain.Tip;
import no.nordicsemi.android.mcp.tips.domain.TipsCategory;
import no.nordicsemi.android.mcp.widget.RecycledPagerAdapter;

/* loaded from: classes.dex */
public class TipsAndTricksAdapter extends RecycledPagerAdapter<ViewHolder> {
    private TipsCategory mCategory;

    /* loaded from: classes.dex */
    public static class ViewHolder extends RecycledPagerAdapter.ViewHolder {
        private Button actionMore;
        private View card;
        private ImageView image;
        private TextView more;
        private TextView text;
        private Toolbar toolbar;

        ViewHolder(View view) {
            super(view);
            this.card = view;
            this.toolbar = (Toolbar) view.findViewById(R.id.toolbar_actionbar);
            this.image = (ImageView) view.findViewById(R.id.image);
            this.text = (TextView) view.findViewById(R.id.text);
            this.more = (TextView) view.findViewById(R.id.text2);
            this.actionMore = (Button) view.findViewById(R.id.action_more);
            this.actionMore.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.tips.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    TipsAndTricksAdapter.ViewHolder.this.a(view2);
                }
            });
        }

        public /* synthetic */ void a(View view) {
            this.actionMore.setVisibility(8);
            this.more.setVisibility(0);
        }

        void assign(Tip tip) {
            this.toolbar.setTitle(tip.getTitle());
            ImageView imageView = this.image;
            imageView.setImageResource(tip.getImageResId(imageView.getContext()));
            Drawable drawable = this.image.getDrawable();
            if (drawable != null && (drawable instanceof AnimationDrawable)) {
                ((AnimationDrawable) drawable).start();
            }
            this.text.setText(tip.getText());
            this.more.setText(tip.getMore());
            this.more.setVisibility(8);
            this.actionMore.setVisibility(tip.getMore() != null ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TipsAndTricksAdapter(TipsCategory tipsCategory) {
        this.mCategory = tipsCategory;
    }

    @Override // androidx.viewpager.widget.a
    public int getCount() {
        return this.mCategory.getTipsCount();
    }

    @Override // no.nordicsemi.android.mcp.widget.RecycledPagerAdapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.assign(this.mCategory.getTip(i));
        if (i == 0) {
            w.a(viewHolder.card, "first");
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // no.nordicsemi.android.mcp.widget.RecycledPagerAdapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tip_and_trick_item, viewGroup, false));
    }
}
