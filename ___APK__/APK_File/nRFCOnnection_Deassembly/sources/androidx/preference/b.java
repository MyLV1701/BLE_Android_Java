package androidx.preference;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
final class b extends Preference {

    /* renamed from: b, reason: collision with root package name */
    private long f1609b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Context context, List<Preference> list, long j) {
        super(context);
        a();
        a(list);
        this.f1609b = j + 1000000;
    }

    private void a() {
        setLayoutResource(q.expand_button);
        setIcon(o.ic_arrow_down_24dp);
        setTitle(r.expand_button_title);
        setOrder(999);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.preference.Preference
    public long getId() {
        return this.f1609b;
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(l lVar) {
        super.onBindViewHolder(lVar);
        lVar.a(false);
    }

    private void a(List<Preference> list) {
        ArrayList arrayList = new ArrayList();
        CharSequence charSequence = null;
        for (Preference preference : list) {
            CharSequence title = preference.getTitle();
            boolean z = preference instanceof PreferenceGroup;
            if (z && !TextUtils.isEmpty(title)) {
                arrayList.add((PreferenceGroup) preference);
            }
            if (arrayList.contains(preference.getParent())) {
                if (z) {
                    arrayList.add((PreferenceGroup) preference);
                }
            } else if (!TextUtils.isEmpty(title)) {
                charSequence = charSequence == null ? title : getContext().getString(r.summary_collapsed_preference_list, charSequence, title);
            }
        }
        setSummary(charSequence);
    }
}
