package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import org.simpleframework.xml.strategy.Name;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class j implements LayoutInflater.Factory2 {

    /* renamed from: b, reason: collision with root package name */
    private final l f1397b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(l lVar) {
        this.f1397b = lVar;
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        if (FragmentContainerView.class.getName().equals(str)) {
            return new FragmentContainerView(context, attributeSet, this.f1397b);
        }
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, Name.LABEL);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.i.c.Fragment);
        if (attributeValue == null) {
            attributeValue = obtainStyledAttributes.getString(a.i.c.Fragment_android_name);
        }
        int resourceId = obtainStyledAttributes.getResourceId(a.i.c.Fragment_android_id, -1);
        String string = obtainStyledAttributes.getString(a.i.c.Fragment_android_tag);
        obtainStyledAttributes.recycle();
        if (attributeValue == null || !h.b(context.getClassLoader(), attributeValue)) {
            return null;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
        }
        Fragment a2 = resourceId != -1 ? this.f1397b.a(resourceId) : null;
        if (a2 == null && string != null) {
            a2 = this.f1397b.b(string);
        }
        if (a2 == null && id != -1) {
            a2 = this.f1397b.a(id);
        }
        if (l.d(2)) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + attributeValue + " existing=" + a2);
        }
        if (a2 == null) {
            a2 = this.f1397b.o().a(context.getClassLoader(), attributeValue);
            a2.mFromLayout = true;
            a2.mFragmentId = resourceId != 0 ? resourceId : id;
            a2.mContainerId = id;
            a2.mTag = string;
            a2.mInLayout = true;
            l lVar = this.f1397b;
            a2.mFragmentManager = lVar;
            i<?> iVar = lVar.p;
            a2.mHost = iVar;
            a2.onInflate(iVar.c(), attributeSet, a2.mSavedFragmentState);
            this.f1397b.a(a2);
            this.f1397b.j(a2);
        } else if (!a2.mInLayout) {
            a2.mInLayout = true;
            i<?> iVar2 = this.f1397b.p;
            a2.mHost = iVar2;
            a2.onInflate(iVar2.c(), attributeSet, a2.mSavedFragmentState);
        } else {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + attributeValue);
        }
        l lVar2 = this.f1397b;
        if (lVar2.o < 1 && a2.mFromLayout) {
            lVar2.a(a2, 1);
        } else {
            this.f1397b.j(a2);
        }
        View view2 = a2.mView;
        if (view2 != null) {
            if (resourceId != 0) {
                view2.setId(resourceId);
            }
            if (a2.mView.getTag() == null) {
                a2.mView.setTag(string);
            }
            return a2.mView;
        }
        throw new IllegalStateException("Fragment " + attributeValue + " did not create a view.");
    }
}
