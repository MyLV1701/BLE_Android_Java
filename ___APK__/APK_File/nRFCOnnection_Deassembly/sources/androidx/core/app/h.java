package androidx.core.app;

import android.app.Notification;
import android.app.RemoteInput;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.core.app.g;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import no.nordicsemi.android.dfu.DfuBaseService;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class h implements f {

    /* renamed from: a, reason: collision with root package name */
    private final Notification.Builder f1234a;

    /* renamed from: b, reason: collision with root package name */
    private final g.d f1235b;

    /* renamed from: c, reason: collision with root package name */
    private RemoteViews f1236c;

    /* renamed from: d, reason: collision with root package name */
    private RemoteViews f1237d;

    /* renamed from: e, reason: collision with root package name */
    private final List<Bundle> f1238e = new ArrayList();

    /* renamed from: f, reason: collision with root package name */
    private final Bundle f1239f = new Bundle();
    private int g;
    private RemoteViews h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(g.d dVar) {
        ArrayList<String> arrayList;
        this.f1235b = dVar;
        if (Build.VERSION.SDK_INT >= 26) {
            this.f1234a = new Notification.Builder(dVar.f1224a, dVar.I);
        } else {
            this.f1234a = new Notification.Builder(dVar.f1224a);
        }
        Notification notification = dVar.P;
        this.f1234a.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, dVar.h).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(dVar.f1227d).setContentText(dVar.f1228e).setContentInfo(dVar.j).setContentIntent(dVar.f1229f).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(dVar.g, (notification.flags & 128) != 0).setLargeIcon(dVar.i).setNumber(dVar.k).setProgress(dVar.r, dVar.s, dVar.t);
        if (Build.VERSION.SDK_INT < 21) {
            this.f1234a.setSound(notification.sound, notification.audioStreamType);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            this.f1234a.setSubText(dVar.p).setUsesChronometer(dVar.n).setPriority(dVar.l);
            Iterator<g.a> it = dVar.f1225b.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
            Bundle bundle = dVar.B;
            if (bundle != null) {
                this.f1239f.putAll(bundle);
            }
            if (Build.VERSION.SDK_INT < 20) {
                if (dVar.x) {
                    this.f1239f.putBoolean("android.support.localOnly", true);
                }
                String str = dVar.u;
                if (str != null) {
                    this.f1239f.putString("android.support.groupKey", str);
                    if (dVar.v) {
                        this.f1239f.putBoolean("android.support.isGroupSummary", true);
                    } else {
                        this.f1239f.putBoolean("android.support.useSideChannel", true);
                    }
                }
                String str2 = dVar.w;
                if (str2 != null) {
                    this.f1239f.putString("android.support.sortKey", str2);
                }
            }
            this.f1236c = dVar.F;
            this.f1237d = dVar.G;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            this.f1234a.setShowWhen(dVar.m);
            if (Build.VERSION.SDK_INT < 21 && (arrayList = dVar.Q) != null && !arrayList.isEmpty()) {
                Bundle bundle2 = this.f1239f;
                ArrayList<String> arrayList2 = dVar.Q;
                bundle2.putStringArray("android.people", (String[]) arrayList2.toArray(new String[arrayList2.size()]));
            }
        }
        if (Build.VERSION.SDK_INT >= 20) {
            this.f1234a.setLocalOnly(dVar.x).setGroup(dVar.u).setGroupSummary(dVar.v).setSortKey(dVar.w);
            this.g = dVar.M;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.f1234a.setCategory(dVar.A).setColor(dVar.C).setVisibility(dVar.D).setPublicVersion(dVar.E).setSound(notification.sound, notification.audioAttributes);
            Iterator<String> it2 = dVar.Q.iterator();
            while (it2.hasNext()) {
                this.f1234a.addPerson(it2.next());
            }
            this.h = dVar.H;
            if (dVar.f1226c.size() > 0) {
                Bundle bundle3 = dVar.b().getBundle("android.car.EXTENSIONS");
                bundle3 = bundle3 == null ? new Bundle() : bundle3;
                Bundle bundle4 = new Bundle();
                for (int i = 0; i < dVar.f1226c.size(); i++) {
                    bundle4.putBundle(Integer.toString(i), i.a(dVar.f1226c.get(i)));
                }
                bundle3.putBundle("invisible_actions", bundle4);
                dVar.b().putBundle("android.car.EXTENSIONS", bundle3);
                this.f1239f.putBundle("android.car.EXTENSIONS", bundle3);
            }
        }
        if (Build.VERSION.SDK_INT >= 24) {
            this.f1234a.setExtras(dVar.B).setRemoteInputHistory(dVar.q);
            RemoteViews remoteViews = dVar.F;
            if (remoteViews != null) {
                this.f1234a.setCustomContentView(remoteViews);
            }
            RemoteViews remoteViews2 = dVar.G;
            if (remoteViews2 != null) {
                this.f1234a.setCustomBigContentView(remoteViews2);
            }
            RemoteViews remoteViews3 = dVar.H;
            if (remoteViews3 != null) {
                this.f1234a.setCustomHeadsUpContentView(remoteViews3);
            }
        }
        if (Build.VERSION.SDK_INT >= 26) {
            this.f1234a.setBadgeIconType(dVar.J).setShortcutId(dVar.K).setTimeoutAfter(dVar.L).setGroupAlertBehavior(dVar.M);
            if (dVar.z) {
                this.f1234a.setColorized(dVar.y);
            }
            if (!TextUtils.isEmpty(dVar.I)) {
                this.f1234a.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f1234a.setAllowSystemGeneratedContextualActions(dVar.N);
            this.f1234a.setBubbleMetadata(g.c.a(dVar.O));
        }
    }

    @Override // androidx.core.app.f
    public Notification.Builder a() {
        return this.f1234a;
    }

    public Notification b() {
        Bundle a2;
        RemoteViews d2;
        RemoteViews b2;
        g.e eVar = this.f1235b.o;
        if (eVar != null) {
            eVar.a(this);
        }
        RemoteViews c2 = eVar != null ? eVar.c(this) : null;
        Notification c3 = c();
        if (c2 != null) {
            c3.contentView = c2;
        } else {
            RemoteViews remoteViews = this.f1235b.F;
            if (remoteViews != null) {
                c3.contentView = remoteViews;
            }
        }
        if (Build.VERSION.SDK_INT >= 16 && eVar != null && (b2 = eVar.b(this)) != null) {
            c3.bigContentView = b2;
        }
        if (Build.VERSION.SDK_INT >= 21 && eVar != null && (d2 = this.f1235b.o.d(this)) != null) {
            c3.headsUpContentView = d2;
        }
        if (Build.VERSION.SDK_INT >= 16 && eVar != null && (a2 = g.a(c3)) != null) {
            eVar.a(a2);
        }
        return c3;
    }

    protected Notification c() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            return this.f1234a.build();
        }
        if (i >= 24) {
            Notification build = this.f1234a.build();
            if (this.g != 0) {
                if (build.getGroup() != null && (build.flags & DfuBaseService.ERROR_REMOTE_TYPE_SECURE) != 0 && this.g == 2) {
                    a(build);
                }
                if (build.getGroup() != null && (build.flags & DfuBaseService.ERROR_REMOTE_TYPE_SECURE) == 0 && this.g == 1) {
                    a(build);
                }
            }
            return build;
        }
        if (i >= 21) {
            this.f1234a.setExtras(this.f1239f);
            Notification build2 = this.f1234a.build();
            RemoteViews remoteViews = this.f1236c;
            if (remoteViews != null) {
                build2.contentView = remoteViews;
            }
            RemoteViews remoteViews2 = this.f1237d;
            if (remoteViews2 != null) {
                build2.bigContentView = remoteViews2;
            }
            RemoteViews remoteViews3 = this.h;
            if (remoteViews3 != null) {
                build2.headsUpContentView = remoteViews3;
            }
            if (this.g != 0) {
                if (build2.getGroup() != null && (build2.flags & DfuBaseService.ERROR_REMOTE_TYPE_SECURE) != 0 && this.g == 2) {
                    a(build2);
                }
                if (build2.getGroup() != null && (build2.flags & DfuBaseService.ERROR_REMOTE_TYPE_SECURE) == 0 && this.g == 1) {
                    a(build2);
                }
            }
            return build2;
        }
        if (i >= 20) {
            this.f1234a.setExtras(this.f1239f);
            Notification build3 = this.f1234a.build();
            RemoteViews remoteViews4 = this.f1236c;
            if (remoteViews4 != null) {
                build3.contentView = remoteViews4;
            }
            RemoteViews remoteViews5 = this.f1237d;
            if (remoteViews5 != null) {
                build3.bigContentView = remoteViews5;
            }
            if (this.g != 0) {
                if (build3.getGroup() != null && (build3.flags & DfuBaseService.ERROR_REMOTE_TYPE_SECURE) != 0 && this.g == 2) {
                    a(build3);
                }
                if (build3.getGroup() != null && (build3.flags & DfuBaseService.ERROR_REMOTE_TYPE_SECURE) == 0 && this.g == 1) {
                    a(build3);
                }
            }
            return build3;
        }
        if (i >= 19) {
            SparseArray<Bundle> a2 = i.a(this.f1238e);
            if (a2 != null) {
                this.f1239f.putSparseParcelableArray("android.support.actionExtras", a2);
            }
            this.f1234a.setExtras(this.f1239f);
            Notification build4 = this.f1234a.build();
            RemoteViews remoteViews6 = this.f1236c;
            if (remoteViews6 != null) {
                build4.contentView = remoteViews6;
            }
            RemoteViews remoteViews7 = this.f1237d;
            if (remoteViews7 != null) {
                build4.bigContentView = remoteViews7;
            }
            return build4;
        }
        if (i >= 16) {
            Notification build5 = this.f1234a.build();
            Bundle a3 = g.a(build5);
            Bundle bundle = new Bundle(this.f1239f);
            for (String str : this.f1239f.keySet()) {
                if (a3.containsKey(str)) {
                    bundle.remove(str);
                }
            }
            a3.putAll(bundle);
            SparseArray<Bundle> a4 = i.a(this.f1238e);
            if (a4 != null) {
                g.a(build5).putSparseParcelableArray("android.support.actionExtras", a4);
            }
            RemoteViews remoteViews8 = this.f1236c;
            if (remoteViews8 != null) {
                build5.contentView = remoteViews8;
            }
            RemoteViews remoteViews9 = this.f1237d;
            if (remoteViews9 != null) {
                build5.bigContentView = remoteViews9;
            }
            return build5;
        }
        return this.f1234a.getNotification();
    }

    private void a(g.a aVar) {
        Notification.Action.Builder builder;
        Bundle bundle;
        int i = Build.VERSION.SDK_INT;
        if (i < 20) {
            if (i >= 16) {
                this.f1238e.add(i.a(this.f1234a, aVar));
                return;
            }
            return;
        }
        if (i >= 23) {
            IconCompat f2 = aVar.f();
            builder = new Notification.Action.Builder(f2 == null ? null : f2.e(), aVar.j(), aVar.a());
        } else {
            builder = new Notification.Action.Builder(aVar.e(), aVar.j(), aVar.a());
        }
        if (aVar.g() != null) {
            for (RemoteInput remoteInput : k.a(aVar.g())) {
                builder.addRemoteInput(remoteInput);
            }
        }
        if (aVar.d() != null) {
            bundle = new Bundle(aVar.d());
        } else {
            bundle = new Bundle();
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.b());
        if (Build.VERSION.SDK_INT >= 24) {
            builder.setAllowGeneratedReplies(aVar.b());
        }
        bundle.putInt("android.support.action.semanticAction", aVar.h());
        if (Build.VERSION.SDK_INT >= 28) {
            builder.setSemanticAction(aVar.h());
        }
        if (Build.VERSION.SDK_INT >= 29) {
            builder.setContextual(aVar.k());
        }
        bundle.putBoolean("android.support.action.showsUserInterface", aVar.i());
        builder.addExtras(bundle);
        this.f1234a.addAction(builder.build());
    }

    private void a(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
        notification.defaults &= -2;
        notification.defaults &= -3;
    }
}
