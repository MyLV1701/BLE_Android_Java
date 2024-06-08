package no.nordicsemi.android.mcp.tips.domain;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class TipsCategory implements Parcelable {
    public static final Parcelable.Creator<TipsCategory> CREATOR = new Parcelable.Creator<TipsCategory>() { // from class: no.nordicsemi.android.mcp.tips.domain.TipsCategory.1
        @Override // android.os.Parcelable.Creator
        public TipsCategory createFromParcel(Parcel parcel) {
            return new TipsCategory(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public TipsCategory[] newArray(int i) {
            return new TipsCategory[i];
        }
    };
    private String image;
    private List<Tip> tips;
    private String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getImageResId(Context context) {
        return context.getResources().getIdentifier(this.image, "drawable", context.getPackageName());
    }

    public Tip getTip(int i) {
        return this.tips.get(i);
    }

    public List<Tip> getTips() {
        return this.tips;
    }

    public int getTipsCount() {
        return this.tips.size();
    }

    public String getTitle() {
        return this.title;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.image);
        parcel.writeList(this.tips);
    }

    private TipsCategory() {
    }

    private TipsCategory(Parcel parcel) {
        this.title = parcel.readString();
        this.image = parcel.readString();
        this.tips = new ArrayList();
        parcel.readList(this.tips, Tip.class.getClassLoader());
    }
}
