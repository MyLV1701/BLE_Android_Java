package a.o.a.a;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class c extends h implements a.o.a.a.b {

    /* renamed from: c, reason: collision with root package name */
    private b f543c;

    /* renamed from: d, reason: collision with root package name */
    private Context f544d;

    /* renamed from: e, reason: collision with root package name */
    private ArgbEvaluator f545e;

    /* renamed from: f, reason: collision with root package name */
    final Drawable.Callback f546f;

    /* loaded from: classes.dex */
    class a implements Drawable.Callback {
        a() {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
            c.this.invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            c.this.scheduleSelf(runnable, j);
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            c.this.unscheduleSelf(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b extends Drawable.ConstantState {

        /* renamed from: a, reason: collision with root package name */
        int f548a;

        /* renamed from: b, reason: collision with root package name */
        i f549b;

        /* renamed from: c, reason: collision with root package name */
        AnimatorSet f550c;

        /* renamed from: d, reason: collision with root package name */
        ArrayList<Animator> f551d;

        /* renamed from: e, reason: collision with root package name */
        a.d.a<Animator, String> f552e;

        public b(Context context, b bVar, Drawable.Callback callback, Resources resources) {
            if (bVar != null) {
                this.f548a = bVar.f548a;
                i iVar = bVar.f549b;
                if (iVar != null) {
                    Drawable.ConstantState constantState = iVar.getConstantState();
                    if (resources != null) {
                        this.f549b = (i) constantState.newDrawable(resources);
                    } else {
                        this.f549b = (i) constantState.newDrawable();
                    }
                    i iVar2 = this.f549b;
                    iVar2.mutate();
                    this.f549b = iVar2;
                    this.f549b.setCallback(callback);
                    this.f549b.setBounds(bVar.f549b.getBounds());
                    this.f549b.a(false);
                }
                ArrayList<Animator> arrayList = bVar.f551d;
                if (arrayList != null) {
                    int size = arrayList.size();
                    this.f551d = new ArrayList<>(size);
                    this.f552e = new a.d.a<>(size);
                    for (int i = 0; i < size; i++) {
                        Animator animator = bVar.f551d.get(i);
                        Animator clone = animator.clone();
                        String str = bVar.f552e.get(animator);
                        clone.setTarget(this.f549b.a(str));
                        this.f551d.add(clone);
                        this.f552e.put(clone, str);
                    }
                    a();
                }
            }
        }

        public void a() {
            if (this.f550c == null) {
                this.f550c = new AnimatorSet();
            }
            this.f550c.playTogether(this.f551d);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f548a;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }
    }

    c() {
        this(null, null, null);
    }

    public static c a(Context context, int i) {
        int next;
        if (Build.VERSION.SDK_INT >= 24) {
            c cVar = new c(context);
            cVar.f558b = a.f.d.d.f.a(context.getResources(), i, context.getTheme());
            cVar.f558b.setCallback(cVar.f546f);
            new C0034c(cVar.f558b.getConstantState());
            return cVar;
        }
        try {
            XmlResourceParser xml = context.getResources().getXml(i);
            AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
            do {
                next = xml.next();
                if (next == 2) {
                    break;
                }
            } while (next != 1);
            if (next == 2) {
                return a(context, context.getResources(), xml, asAttributeSet, context.getTheme());
            }
            throw new XmlPullParserException("No start tag found");
        } catch (IOException e2) {
            Log.e("AnimatedVDCompat", "parser error", e2);
            return null;
        } catch (XmlPullParserException e3) {
            Log.e("AnimatedVDCompat", "parser error", e3);
            return null;
        }
    }

    @Override // a.o.a.a.h, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, theme);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return androidx.core.graphics.drawable.a.a(drawable);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        this.f543c.f549b.draw(canvas);
        if (this.f543c.f550c.isStarted()) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return androidx.core.graphics.drawable.a.c(drawable);
        }
        return this.f543c.f549b.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.f543c.f548a;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return androidx.core.graphics.drawable.a.d(drawable);
        }
        return this.f543c.f549b.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f558b;
        if (drawable == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new C0034c(drawable.getConstantState());
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return this.f543c.f549b.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return this.f543c.f549b.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return this.f543c.f549b.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if ("animated-vector".equals(name)) {
                    TypedArray a2 = a.f.d.d.g.a(resources, theme, attributeSet, a.o.a.a.a.f541e);
                    int resourceId = a2.getResourceId(0, 0);
                    if (resourceId != 0) {
                        i a3 = i.a(resources, resourceId, theme);
                        a3.a(false);
                        a3.setCallback(this.f546f);
                        i iVar = this.f543c.f549b;
                        if (iVar != null) {
                            iVar.setCallback(null);
                        }
                        this.f543c.f549b = a3;
                    }
                    a2.recycle();
                } else if ("target".equals(name)) {
                    TypedArray obtainAttributes = resources.obtainAttributes(attributeSet, a.o.a.a.a.f542f);
                    String string = obtainAttributes.getString(0);
                    int resourceId2 = obtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.f544d;
                        if (context != null) {
                            a(string, e.a(context, resourceId2));
                        } else {
                            obtainAttributes.recycle();
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                    }
                    obtainAttributes.recycle();
                } else {
                    continue;
                }
            }
            eventType = xmlPullParser.next();
        }
        this.f543c.a();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return androidx.core.graphics.drawable.a.f(drawable);
        }
        return this.f543c.f549b.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return ((AnimatedVectorDrawable) drawable).isRunning();
        }
        return this.f543c.f550c.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return drawable.isStateful();
        }
        return this.f543c.f549b.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.f543c.f549b.setBounds(rect);
        }
    }

    @Override // a.o.a.a.h, android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return drawable.setLevel(i);
        }
        return this.f543c.f549b.setLevel(i);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        return this.f543c.f549b.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else {
            this.f543c.f549b.setAlpha(i);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, z);
        } else {
            this.f543c.f549b.setAutoMirrored(z);
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.b(drawable, i);
        } else {
            this.f543c.f549b.setTint(i);
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, colorStateList);
        } else {
            this.f543c.f549b.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, mode);
        } else {
            this.f543c.f549b.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        this.f543c.f549b.setVisible(z, z2);
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
        } else {
            if (this.f543c.f550c.isStarted()) {
                return;
            }
            this.f543c.f550c.start();
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.f543c.f550c.end();
        }
    }

    private c(Context context) {
        this(context, null, null);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f558b;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f543c.f549b.setColorFilter(colorFilter);
        }
    }

    /* renamed from: a.o.a.a.c$c, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0034c extends Drawable.ConstantState {

        /* renamed from: a, reason: collision with root package name */
        private final Drawable.ConstantState f553a;

        public C0034c(Drawable.ConstantState constantState) {
            this.f553a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f553a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f553a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            c cVar = new c();
            cVar.f558b = this.f553a.newDrawable();
            cVar.f558b.setCallback(cVar.f546f);
            return cVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            c cVar = new c();
            cVar.f558b = this.f553a.newDrawable(resources);
            cVar.f558b.setCallback(cVar.f546f);
            return cVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            c cVar = new c();
            cVar.f558b = this.f553a.newDrawable(resources, theme);
            cVar.f558b.setCallback(cVar.f546f);
            return cVar;
        }
    }

    private c(Context context, b bVar, Resources resources) {
        this.f545e = null;
        this.f546f = new a();
        this.f544d = context;
        if (bVar != null) {
            this.f543c = bVar;
        } else {
            this.f543c = new b(context, bVar, this.f546f, resources);
        }
    }

    public static c a(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        c cVar = new c(context);
        cVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return cVar;
    }

    private void a(Animator animator) {
        ArrayList<Animator> childAnimations;
        if ((animator instanceof AnimatorSet) && (childAnimations = ((AnimatorSet) animator).getChildAnimations()) != null) {
            for (int i = 0; i < childAnimations.size(); i++) {
                a(childAnimations.get(i));
            }
        }
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            String propertyName = objectAnimator.getPropertyName();
            if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
                if (this.f545e == null) {
                    this.f545e = new ArgbEvaluator();
                }
                objectAnimator.setEvaluator(this.f545e);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        inflate(resources, xmlPullParser, attributeSet, null);
    }

    private void a(String str, Animator animator) {
        animator.setTarget(this.f543c.f549b.a(str));
        if (Build.VERSION.SDK_INT < 21) {
            a(animator);
        }
        b bVar = this.f543c;
        if (bVar.f551d == null) {
            bVar.f551d = new ArrayList<>();
            this.f543c.f552e = new a.d.a<>();
        }
        this.f543c.f551d.add(animator);
        this.f543c.f552e.put(animator, str);
    }
}
