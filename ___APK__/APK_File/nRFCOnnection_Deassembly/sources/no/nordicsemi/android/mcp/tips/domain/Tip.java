package no.nordicsemi.android.mcp.tips.domain;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class Tip implements Parcelable {
    public static final Parcelable.Creator<Tip> CREATOR = new Parcelable.Creator<Tip>() { // from class: no.nordicsemi.android.mcp.tips.domain.Tip.1
        @Override // android.os.Parcelable.Creator
        public Tip createFromParcel(Parcel parcel) {
            return new Tip(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public Tip[] newArray(int i) {
            return new Tip[i];
        }
    };
    private String image;
    private String more;
    private String text;
    private String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getImageResId(Context context) {
        return context.getResources().getIdentifier(this.image, "drawable", context.getPackageName());
    }

    public String getMore() {
        return this.more;
    }

    public String getText() {
        return this.text;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.image);
        parcel.writeString(this.text);
        parcel.writeString(this.more);
    }

    private Tip() {
    }

    private Tip(Parcel parcel) {
        this.title = parcel.readString();
        this.image = parcel.readString();
        this.text = parcel.readString();
        this.more = parcel.readString();
    }
}
