package a.f.j;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;

/* loaded from: classes.dex */
public class c implements Spannable {

    /* renamed from: b, reason: collision with root package name */
    private final Spannable f244b;

    /* renamed from: c, reason: collision with root package name */
    private final a f245c;

    /* renamed from: d, reason: collision with root package name */
    private final PrecomputedText f246d;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private final TextPaint f247a;

        /* renamed from: b, reason: collision with root package name */
        private final TextDirectionHeuristic f248b;

        /* renamed from: c, reason: collision with root package name */
        private final int f249c;

        /* renamed from: d, reason: collision with root package name */
        private final int f250d;

        /* renamed from: a.f.j.c$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class C0013a {

            /* renamed from: a, reason: collision with root package name */
            private final TextPaint f251a;

            /* renamed from: b, reason: collision with root package name */
            private TextDirectionHeuristic f252b;

            /* renamed from: c, reason: collision with root package name */
            private int f253c;

            /* renamed from: d, reason: collision with root package name */
            private int f254d;

            public C0013a(TextPaint textPaint) {
                this.f251a = textPaint;
                if (Build.VERSION.SDK_INT >= 23) {
                    this.f253c = 1;
                    this.f254d = 1;
                } else {
                    this.f254d = 0;
                    this.f253c = 0;
                }
                if (Build.VERSION.SDK_INT >= 18) {
                    this.f252b = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.f252b = null;
                }
            }

            public C0013a a(int i) {
                this.f253c = i;
                return this;
            }

            public C0013a b(int i) {
                this.f254d = i;
                return this;
            }

            public C0013a a(TextDirectionHeuristic textDirectionHeuristic) {
                this.f252b = textDirectionHeuristic;
                return this;
            }

            public a a() {
                return new a(this.f251a, this.f252b, this.f253c, this.f254d);
            }
        }

        @SuppressLint({"NewApi"})
        a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
            if (Build.VERSION.SDK_INT >= 29) {
                new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i).setHyphenationFrequency(i2).setTextDirection(textDirectionHeuristic).build();
            }
            this.f247a = textPaint;
            this.f248b = textDirectionHeuristic;
            this.f249c = i;
            this.f250d = i2;
        }

        public int a() {
            return this.f249c;
        }

        public int b() {
            return this.f250d;
        }

        public TextDirectionHeuristic c() {
            return this.f248b;
        }

        public TextPaint d() {
            return this.f247a;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (a(aVar)) {
                return Build.VERSION.SDK_INT < 18 || this.f248b == aVar.c();
            }
            return false;
        }

        public int hashCode() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 24) {
                return a.f.k.c.a(Float.valueOf(this.f247a.getTextSize()), Float.valueOf(this.f247a.getTextScaleX()), Float.valueOf(this.f247a.getTextSkewX()), Float.valueOf(this.f247a.getLetterSpacing()), Integer.valueOf(this.f247a.getFlags()), this.f247a.getTextLocales(), this.f247a.getTypeface(), Boolean.valueOf(this.f247a.isElegantTextHeight()), this.f248b, Integer.valueOf(this.f249c), Integer.valueOf(this.f250d));
            }
            if (i >= 21) {
                return a.f.k.c.a(Float.valueOf(this.f247a.getTextSize()), Float.valueOf(this.f247a.getTextScaleX()), Float.valueOf(this.f247a.getTextSkewX()), Float.valueOf(this.f247a.getLetterSpacing()), Integer.valueOf(this.f247a.getFlags()), this.f247a.getTextLocale(), this.f247a.getTypeface(), Boolean.valueOf(this.f247a.isElegantTextHeight()), this.f248b, Integer.valueOf(this.f249c), Integer.valueOf(this.f250d));
            }
            if (i >= 18) {
                return a.f.k.c.a(Float.valueOf(this.f247a.getTextSize()), Float.valueOf(this.f247a.getTextScaleX()), Float.valueOf(this.f247a.getTextSkewX()), Integer.valueOf(this.f247a.getFlags()), this.f247a.getTextLocale(), this.f247a.getTypeface(), this.f248b, Integer.valueOf(this.f249c), Integer.valueOf(this.f250d));
            }
            if (i >= 17) {
                return a.f.k.c.a(Float.valueOf(this.f247a.getTextSize()), Float.valueOf(this.f247a.getTextScaleX()), Float.valueOf(this.f247a.getTextSkewX()), Integer.valueOf(this.f247a.getFlags()), this.f247a.getTextLocale(), this.f247a.getTypeface(), this.f248b, Integer.valueOf(this.f249c), Integer.valueOf(this.f250d));
            }
            return a.f.k.c.a(Float.valueOf(this.f247a.getTextSize()), Float.valueOf(this.f247a.getTextScaleX()), Float.valueOf(this.f247a.getTextSkewX()), Integer.valueOf(this.f247a.getFlags()), this.f247a.getTypeface(), this.f248b, Integer.valueOf(this.f249c), Integer.valueOf(this.f250d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f247a.getTextSize());
            sb.append(", textScaleX=" + this.f247a.getTextScaleX());
            sb.append(", textSkewX=" + this.f247a.getTextSkewX());
            if (Build.VERSION.SDK_INT >= 21) {
                sb.append(", letterSpacing=" + this.f247a.getLetterSpacing());
                sb.append(", elegantTextHeight=" + this.f247a.isElegantTextHeight());
            }
            int i = Build.VERSION.SDK_INT;
            if (i >= 24) {
                sb.append(", textLocale=" + this.f247a.getTextLocales());
            } else if (i >= 17) {
                sb.append(", textLocale=" + this.f247a.getTextLocale());
            }
            sb.append(", typeface=" + this.f247a.getTypeface());
            if (Build.VERSION.SDK_INT >= 26) {
                sb.append(", variationSettings=" + this.f247a.getFontVariationSettings());
            }
            sb.append(", textDir=" + this.f248b);
            sb.append(", breakStrategy=" + this.f249c);
            sb.append(", hyphenationFrequency=" + this.f250d);
            sb.append("}");
            return sb.toString();
        }

        public boolean a(a aVar) {
            if ((Build.VERSION.SDK_INT >= 23 && (this.f249c != aVar.a() || this.f250d != aVar.b())) || this.f247a.getTextSize() != aVar.d().getTextSize() || this.f247a.getTextScaleX() != aVar.d().getTextScaleX() || this.f247a.getTextSkewX() != aVar.d().getTextSkewX()) {
                return false;
            }
            if ((Build.VERSION.SDK_INT >= 21 && (this.f247a.getLetterSpacing() != aVar.d().getLetterSpacing() || !TextUtils.equals(this.f247a.getFontFeatureSettings(), aVar.d().getFontFeatureSettings()))) || this.f247a.getFlags() != aVar.d().getFlags()) {
                return false;
            }
            int i = Build.VERSION.SDK_INT;
            if (i >= 24) {
                if (!this.f247a.getTextLocales().equals(aVar.d().getTextLocales())) {
                    return false;
                }
            } else if (i >= 17 && !this.f247a.getTextLocale().equals(aVar.d().getTextLocale())) {
                return false;
            }
            return this.f247a.getTypeface() == null ? aVar.d().getTypeface() == null : this.f247a.getTypeface().equals(aVar.d().getTypeface());
        }

        public a(PrecomputedText.Params params) {
            this.f247a = params.getTextPaint();
            this.f248b = params.getTextDirection();
            this.f249c = params.getBreakStrategy();
            this.f250d = params.getHyphenationFrequency();
            int i = Build.VERSION.SDK_INT;
        }
    }

    public a a() {
        return this.f245c;
    }

    public PrecomputedText b() {
        Spannable spannable = this.f244b;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.f244b.charAt(i);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f244b.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f244b.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f244b.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        if (Build.VERSION.SDK_INT >= 29) {
            return (T[]) this.f246d.getSpans(i, i2, cls);
        }
        return (T[]) this.f244b.getSpans(i, i2, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f244b.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.f244b.nextSpanTransition(i, i2, cls);
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void removeSpan(Object obj) {
        if (!(obj instanceof MetricAffectingSpan)) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.f246d.removeSpan(obj);
                return;
            } else {
                this.f244b.removeSpan(obj);
                return;
            }
        }
        throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void setSpan(Object obj, int i, int i2, int i3) {
        if (!(obj instanceof MetricAffectingSpan)) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.f246d.setSpan(obj, i, i2, i3);
                return;
            } else {
                this.f244b.setSpan(obj, i, i2, i3);
                return;
            }
        }
        throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return this.f244b.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f244b.toString();
    }
}
