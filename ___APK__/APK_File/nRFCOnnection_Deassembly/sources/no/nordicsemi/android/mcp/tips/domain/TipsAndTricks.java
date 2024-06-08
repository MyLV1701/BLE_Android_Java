package no.nordicsemi.android.mcp.tips.domain;

import android.content.Context;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class TipsAndTricks {
    private List<TipsCategory> categories;
    private String image;
    private String text;
    private String title;

    public List<TipsCategory> getCategories() {
        return this.categories;
    }

    public int getCategoriesCount() {
        return this.categories.size();
    }

    public TipsCategory getCategory(int i) {
        return this.categories.get(i);
    }

    public int getImageResId(Context context) {
        return context.getResources().getIdentifier(this.image, "drawable", context.getPackageName());
    }

    public String getText() {
        return this.text;
    }

    public int getTipsCount() {
        Iterator<TipsCategory> it = this.categories.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().getTipsCount();
        }
        return i;
    }

    public String getTitle() {
        return this.title;
    }
}
