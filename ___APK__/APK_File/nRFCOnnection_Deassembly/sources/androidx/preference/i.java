package androidx.preference;

import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class i {

    /* renamed from: e, reason: collision with root package name */
    private static final Class<?>[] f1644e = {Context.class, AttributeSet.class};

    /* renamed from: f, reason: collision with root package name */
    private static final HashMap<String, Constructor> f1645f = new HashMap<>();

    /* renamed from: a, reason: collision with root package name */
    private final Context f1646a;

    /* renamed from: b, reason: collision with root package name */
    private final Object[] f1647b = new Object[2];

    /* renamed from: c, reason: collision with root package name */
    private j f1648c;

    /* renamed from: d, reason: collision with root package name */
    private String[] f1649d;

    public i(Context context, j jVar) {
        this.f1646a = context;
        a(jVar);
    }

    private void a(j jVar) {
        this.f1648c = jVar;
        a(new String[]{Preference.class.getPackage().getName() + ".", SwitchPreference.class.getPackage().getName() + "."});
    }

    private Preference b(String str, AttributeSet attributeSet) {
        try {
            if (-1 == str.indexOf(46)) {
                return a(str, attributeSet);
            }
            return a(str, (String[]) null, attributeSet);
        } catch (InflateException e2) {
            throw e2;
        } catch (ClassNotFoundException e3) {
            InflateException inflateException = new InflateException(attributeSet.getPositionDescription() + ": Error inflating class (not found)" + str);
            inflateException.initCause(e3);
            throw inflateException;
        } catch (Exception e4) {
            InflateException inflateException2 = new InflateException(attributeSet.getPositionDescription() + ": Error inflating class " + str);
            inflateException2.initCause(e4);
            throw inflateException2;
        }
    }

    public void a(String[] strArr) {
        this.f1649d = strArr;
    }

    public Context a() {
        return this.f1646a;
    }

    public Preference a(int i, PreferenceGroup preferenceGroup) {
        XmlResourceParser xml = a().getResources().getXml(i);
        try {
            return a(xml, preferenceGroup);
        } finally {
            xml.close();
        }
    }

    public Preference a(XmlPullParser xmlPullParser, PreferenceGroup preferenceGroup) {
        int next;
        PreferenceGroup a2;
        synchronized (this.f1647b) {
            AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
            this.f1647b[0] = this.f1646a;
            do {
                try {
                    next = xmlPullParser.next();
                    if (next == 2) {
                        break;
                    }
                } catch (InflateException e2) {
                    throw e2;
                } catch (IOException e3) {
                    InflateException inflateException = new InflateException(xmlPullParser.getPositionDescription() + ": " + e3.getMessage());
                    inflateException.initCause(e3);
                    throw inflateException;
                } catch (XmlPullParserException e4) {
                    InflateException inflateException2 = new InflateException(e4.getMessage());
                    inflateException2.initCause(e4);
                    throw inflateException2;
                }
            } while (next != 1);
            if (next == 2) {
                a2 = a(preferenceGroup, (PreferenceGroup) b(xmlPullParser.getName(), asAttributeSet));
                a(xmlPullParser, a2, asAttributeSet);
            } else {
                throw new InflateException(xmlPullParser.getPositionDescription() + ": No start tag found!");
            }
        }
        return a2;
    }

    private PreferenceGroup a(PreferenceGroup preferenceGroup, PreferenceGroup preferenceGroup2) {
        if (preferenceGroup != null) {
            return preferenceGroup;
        }
        preferenceGroup2.onAttachedToHierarchy(this.f1648c);
        return preferenceGroup2;
    }

    private Preference a(String str, String[] strArr, AttributeSet attributeSet) {
        Class<?> cls;
        Constructor<?> constructor = f1645f.get(str);
        if (constructor == null) {
            try {
                try {
                    ClassLoader classLoader = this.f1646a.getClassLoader();
                    if (strArr != null && strArr.length != 0) {
                        cls = null;
                        ClassNotFoundException e2 = null;
                        for (String str2 : strArr) {
                            try {
                                cls = Class.forName(str2 + str, false, classLoader);
                                break;
                            } catch (ClassNotFoundException e3) {
                                e2 = e3;
                            }
                        }
                        if (cls == null) {
                            if (e2 == null) {
                                throw new InflateException(attributeSet.getPositionDescription() + ": Error inflating class " + str);
                            }
                            throw e2;
                        }
                        constructor = cls.getConstructor(f1644e);
                        constructor.setAccessible(true);
                        f1645f.put(str, constructor);
                    }
                    cls = Class.forName(str, false, classLoader);
                    constructor = cls.getConstructor(f1644e);
                    constructor.setAccessible(true);
                    f1645f.put(str, constructor);
                } catch (ClassNotFoundException e4) {
                    throw e4;
                }
            } catch (Exception e5) {
                InflateException inflateException = new InflateException(attributeSet.getPositionDescription() + ": Error inflating class " + str);
                inflateException.initCause(e5);
                throw inflateException;
            }
        }
        Object[] objArr = this.f1647b;
        objArr[1] = attributeSet;
        return (Preference) constructor.newInstance(objArr);
    }

    protected Preference a(String str, AttributeSet attributeSet) {
        return a(str, this.f1649d, attributeSet);
    }

    private void a(XmlPullParser xmlPullParser, Preference preference, AttributeSet attributeSet) {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if ((next == 3 && xmlPullParser.getDepth() <= depth) || next == 1) {
                return;
            }
            if (next == 2) {
                String name = xmlPullParser.getName();
                if ("intent".equals(name)) {
                    try {
                        preference.setIntent(Intent.parseIntent(a().getResources(), xmlPullParser, attributeSet));
                    } catch (IOException e2) {
                        XmlPullParserException xmlPullParserException = new XmlPullParserException("Error parsing preference");
                        xmlPullParserException.initCause(e2);
                        throw xmlPullParserException;
                    }
                } else if ("extra".equals(name)) {
                    a().getResources().parseBundleExtra("extra", attributeSet, preference.getExtras());
                    try {
                        a(xmlPullParser);
                    } catch (IOException e3) {
                        XmlPullParserException xmlPullParserException2 = new XmlPullParserException("Error parsing preference");
                        xmlPullParserException2.initCause(e3);
                        throw xmlPullParserException2;
                    }
                } else {
                    Preference b2 = b(name, attributeSet);
                    ((PreferenceGroup) preference).a(b2);
                    a(xmlPullParser, b2, attributeSet);
                }
            }
        }
    }

    private static void a(XmlPullParser xmlPullParser) {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            if (next == 3 && xmlPullParser.getDepth() <= depth) {
                return;
            }
        }
    }
}
