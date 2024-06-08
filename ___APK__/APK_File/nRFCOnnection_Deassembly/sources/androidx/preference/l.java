package androidx.preference;

import android.R;
import android.util.SparseArray;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class l extends RecyclerView.d0 {

    /* renamed from: a, reason: collision with root package name */
    private final SparseArray<View> f1662a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f1663b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f1664c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(View view) {
        super(view);
        this.f1662a = new SparseArray<>(4);
        this.f1662a.put(R.id.title, view.findViewById(R.id.title));
        this.f1662a.put(R.id.summary, view.findViewById(R.id.summary));
        this.f1662a.put(R.id.icon, view.findViewById(R.id.icon));
        SparseArray<View> sparseArray = this.f1662a;
        int i = p.icon_frame;
        sparseArray.put(i, view.findViewById(i));
        this.f1662a.put(R.id.icon_frame, view.findViewById(R.id.icon_frame));
    }

    public View a(int i) {
        View view = this.f1662a.get(i);
        if (view != null) {
            return view;
        }
        View findViewById = this.itemView.findViewById(i);
        if (findViewById != null) {
            this.f1662a.put(i, findViewById);
        }
        return findViewById;
    }

    public boolean b() {
        return this.f1664c;
    }

    public void b(boolean z) {
        this.f1664c = z;
    }

    public boolean a() {
        return this.f1663b;
    }

    public void a(boolean z) {
        this.f1663b = z;
    }
}
