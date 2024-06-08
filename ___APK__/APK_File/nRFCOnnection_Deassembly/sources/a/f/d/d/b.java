package a.f.d.d;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private final Shader f144a;

    /* renamed from: b, reason: collision with root package name */
    private final ColorStateList f145b;

    /* renamed from: c, reason: collision with root package name */
    private int f146c;

    private b(Shader shader, ColorStateList colorStateList, int i) {
        this.f144a = shader;
        this.f145b = colorStateList;
        this.f146c = i;
    }

    static b a(Shader shader) {
        return new b(shader, null, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b b(int i) {
        return new b(null, null, i);
    }

    public boolean c() {
        return this.f144a != null;
    }

    public boolean d() {
        ColorStateList colorStateList;
        return this.f144a == null && (colorStateList = this.f145b) != null && colorStateList.isStateful();
    }

    public boolean e() {
        return c() || this.f146c != 0;
    }

    static b a(ColorStateList colorStateList) {
        return new b(null, colorStateList, colorStateList.getDefaultColor());
    }

    public Shader b() {
        return this.f144a;
    }

    public static b b(Resources resources, int i, Resources.Theme theme) {
        try {
            return a(resources, i, theme);
        } catch (Exception e2) {
            Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", e2);
            return null;
        }
    }

    public int a() {
        return this.f146c;
    }

    public void a(int i) {
        this.f146c = i;
    }

    public boolean a(int[] iArr) {
        if (d()) {
            ColorStateList colorStateList = this.f145b;
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (colorForState != this.f146c) {
                this.f146c = colorForState;
                return true;
            }
        }
        return false;
    }

    private static b a(Resources resources, int i, Resources.Theme theme) {
        int next;
        XmlResourceParser xml = resources.getXml(i);
        AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
        do {
            next = xml.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            String name = xml.getName();
            char c2 = 65535;
            int hashCode = name.hashCode();
            if (hashCode != 89650992) {
                if (hashCode == 1191572447 && name.equals("selector")) {
                    c2 = 0;
                }
            } else if (name.equals("gradient")) {
                c2 = 1;
            }
            if (c2 == 0) {
                return a(a.a(resources, xml, asAttributeSet, theme));
            }
            if (c2 == 1) {
                return a(d.a(resources, xml, asAttributeSet, theme));
            }
            throw new XmlPullParserException(xml.getPositionDescription() + ": unsupported complex color tag " + name);
        }
        throw new XmlPullParserException("No start tag found");
    }
}
