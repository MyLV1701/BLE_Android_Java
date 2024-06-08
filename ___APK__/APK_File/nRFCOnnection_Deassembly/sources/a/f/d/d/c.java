package a.f.d.d;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.Base64;
import android.util.TypedValue;
import android.util.Xml;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class c {

    /* loaded from: classes.dex */
    public interface a {
    }

    /* loaded from: classes.dex */
    public static final class b implements a {

        /* renamed from: a, reason: collision with root package name */
        private final C0006c[] f147a;

        public b(C0006c[] c0006cArr) {
            this.f147a = c0006cArr;
        }

        public C0006c[] a() {
            return this.f147a;
        }
    }

    /* renamed from: a.f.d.d.c$c, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0006c {

        /* renamed from: a, reason: collision with root package name */
        private final String f148a;

        /* renamed from: b, reason: collision with root package name */
        private int f149b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f150c;

        /* renamed from: d, reason: collision with root package name */
        private String f151d;

        /* renamed from: e, reason: collision with root package name */
        private int f152e;

        /* renamed from: f, reason: collision with root package name */
        private int f153f;

        public C0006c(String str, int i, boolean z, String str2, int i2, int i3) {
            this.f148a = str;
            this.f149b = i;
            this.f150c = z;
            this.f151d = str2;
            this.f152e = i2;
            this.f153f = i3;
        }

        public String a() {
            return this.f148a;
        }

        public int b() {
            return this.f153f;
        }

        public int c() {
            return this.f152e;
        }

        public String d() {
            return this.f151d;
        }

        public int e() {
            return this.f149b;
        }

        public boolean f() {
            return this.f150c;
        }
    }

    /* loaded from: classes.dex */
    public static final class d implements a {

        /* renamed from: a, reason: collision with root package name */
        private final a.f.i.a f154a;

        /* renamed from: b, reason: collision with root package name */
        private final int f155b;

        /* renamed from: c, reason: collision with root package name */
        private final int f156c;

        public d(a.f.i.a aVar, int i, int i2) {
            this.f154a = aVar;
            this.f156c = i;
            this.f155b = i2;
        }

        public int a() {
            return this.f156c;
        }

        public a.f.i.a b() {
            return this.f154a;
        }

        public int c() {
            return this.f155b;
        }
    }

    public static a a(XmlPullParser xmlPullParser, Resources resources) {
        int next;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return b(xmlPullParser, resources);
        }
        throw new XmlPullParserException("No start tag found");
    }

    private static a b(XmlPullParser xmlPullParser, Resources resources) {
        xmlPullParser.require(2, null, "font-family");
        if (xmlPullParser.getName().equals("font-family")) {
            return c(xmlPullParser, resources);
        }
        a(xmlPullParser);
        return null;
    }

    private static a c(XmlPullParser xmlPullParser, Resources resources) {
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), a.f.c.FontFamily);
        String string = obtainAttributes.getString(a.f.c.FontFamily_fontProviderAuthority);
        String string2 = obtainAttributes.getString(a.f.c.FontFamily_fontProviderPackage);
        String string3 = obtainAttributes.getString(a.f.c.FontFamily_fontProviderQuery);
        int resourceId = obtainAttributes.getResourceId(a.f.c.FontFamily_fontProviderCerts, 0);
        int integer = obtainAttributes.getInteger(a.f.c.FontFamily_fontProviderFetchStrategy, 1);
        int integer2 = obtainAttributes.getInteger(a.f.c.FontFamily_fontProviderFetchTimeout, 500);
        obtainAttributes.recycle();
        if (string != null && string2 != null && string3 != null) {
            while (xmlPullParser.next() != 3) {
                a(xmlPullParser);
            }
            return new d(new a.f.i.a(string, string2, string3, a(resources, resourceId)), integer, integer2);
        }
        ArrayList arrayList = new ArrayList();
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("font")) {
                    arrayList.add(d(xmlPullParser, resources));
                } else {
                    a(xmlPullParser);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new b((C0006c[]) arrayList.toArray(new C0006c[arrayList.size()]));
    }

    private static C0006c d(XmlPullParser xmlPullParser, Resources resources) {
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), a.f.c.FontFamilyFont);
        int i = obtainAttributes.getInt(obtainAttributes.hasValue(a.f.c.FontFamilyFont_fontWeight) ? a.f.c.FontFamilyFont_fontWeight : a.f.c.FontFamilyFont_android_fontWeight, 400);
        boolean z = 1 == obtainAttributes.getInt(obtainAttributes.hasValue(a.f.c.FontFamilyFont_fontStyle) ? a.f.c.FontFamilyFont_fontStyle : a.f.c.FontFamilyFont_android_fontStyle, 0);
        int i2 = obtainAttributes.hasValue(a.f.c.FontFamilyFont_ttcIndex) ? a.f.c.FontFamilyFont_ttcIndex : a.f.c.FontFamilyFont_android_ttcIndex;
        String string = obtainAttributes.getString(obtainAttributes.hasValue(a.f.c.FontFamilyFont_fontVariationSettings) ? a.f.c.FontFamilyFont_fontVariationSettings : a.f.c.FontFamilyFont_android_fontVariationSettings);
        int i3 = obtainAttributes.getInt(i2, 0);
        int i4 = obtainAttributes.hasValue(a.f.c.FontFamilyFont_font) ? a.f.c.FontFamilyFont_font : a.f.c.FontFamilyFont_android_font;
        int resourceId = obtainAttributes.getResourceId(i4, 0);
        String string2 = obtainAttributes.getString(i4);
        obtainAttributes.recycle();
        while (xmlPullParser.next() != 3) {
            a(xmlPullParser);
        }
        return new C0006c(string2, i, z, string, i3, resourceId);
    }

    private static int a(TypedArray typedArray, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return typedArray.getType(i);
        }
        TypedValue typedValue = new TypedValue();
        typedArray.getValue(i, typedValue);
        return typedValue.type;
    }

    public static List<List<byte[]>> a(Resources resources, int i) {
        if (i == 0) {
            return Collections.emptyList();
        }
        TypedArray obtainTypedArray = resources.obtainTypedArray(i);
        try {
            if (obtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (a(obtainTypedArray, 0) == 1) {
                for (int i2 = 0; i2 < obtainTypedArray.length(); i2++) {
                    int resourceId = obtainTypedArray.getResourceId(i2, 0);
                    if (resourceId != 0) {
                        arrayList.add(a(resources.getStringArray(resourceId)));
                    }
                }
            } else {
                arrayList.add(a(resources.getStringArray(i)));
            }
            return arrayList;
        } finally {
            obtainTypedArray.recycle();
        }
    }

    private static List<byte[]> a(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            arrayList.add(Base64.decode(str, 0));
        }
        return arrayList;
    }

    private static void a(XmlPullParser xmlPullParser) {
        int i = 1;
        while (i > 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i++;
            } else if (next == 3) {
                i--;
            }
        }
    }
}
