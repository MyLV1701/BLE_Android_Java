package androidx.core.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.v4.app.a;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class j {

    /* renamed from: d, reason: collision with root package name */
    private static String f1244d;
    private static d g;

    /* renamed from: a, reason: collision with root package name */
    private final Context f1247a;

    /* renamed from: b, reason: collision with root package name */
    private final NotificationManager f1248b;

    /* renamed from: c, reason: collision with root package name */
    private static final Object f1243c = new Object();

    /* renamed from: e, reason: collision with root package name */
    private static Set<String> f1245e = new HashSet();

    /* renamed from: f, reason: collision with root package name */
    private static final Object f1246f = new Object();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a implements e {

        /* renamed from: a, reason: collision with root package name */
        final String f1249a;

        /* renamed from: b, reason: collision with root package name */
        final int f1250b;

        /* renamed from: c, reason: collision with root package name */
        final String f1251c;

        /* renamed from: d, reason: collision with root package name */
        final boolean f1252d = false;

        a(String str, int i, String str2) {
            this.f1249a = str;
            this.f1250b = i;
            this.f1251c = str2;
        }

        @Override // androidx.core.app.j.e
        public void a(android.support.v4.app.a aVar) {
            if (this.f1252d) {
                aVar.a(this.f1249a);
            } else {
                aVar.a(this.f1249a, this.f1250b, this.f1251c);
            }
        }

        public String toString() {
            return "CancelTask[packageName:" + this.f1249a + ", id:" + this.f1250b + ", tag:" + this.f1251c + ", all:" + this.f1252d + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b implements e {

        /* renamed from: a, reason: collision with root package name */
        final String f1253a;

        /* renamed from: b, reason: collision with root package name */
        final int f1254b;

        /* renamed from: c, reason: collision with root package name */
        final String f1255c;

        /* renamed from: d, reason: collision with root package name */
        final Notification f1256d;

        b(String str, int i, String str2, Notification notification) {
            this.f1253a = str;
            this.f1254b = i;
            this.f1255c = str2;
            this.f1256d = notification;
        }

        @Override // androidx.core.app.j.e
        public void a(android.support.v4.app.a aVar) {
            aVar.a(this.f1253a, this.f1254b, this.f1255c, this.f1256d);
        }

        public String toString() {
            return "NotifyTask[packageName:" + this.f1253a + ", id:" + this.f1254b + ", tag:" + this.f1255c + "]";
        }
    }

    /* loaded from: classes.dex */
    private static class c {

        /* renamed from: a, reason: collision with root package name */
        final ComponentName f1257a;

        /* renamed from: b, reason: collision with root package name */
        final IBinder f1258b;

        c(ComponentName componentName, IBinder iBinder) {
            this.f1257a = componentName;
            this.f1258b = iBinder;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d implements Handler.Callback, ServiceConnection {

        /* renamed from: a, reason: collision with root package name */
        private final Context f1259a;

        /* renamed from: c, reason: collision with root package name */
        private final Handler f1261c;

        /* renamed from: d, reason: collision with root package name */
        private final Map<ComponentName, a> f1262d = new HashMap();

        /* renamed from: e, reason: collision with root package name */
        private Set<String> f1263e = new HashSet();

        /* renamed from: b, reason: collision with root package name */
        private final HandlerThread f1260b = new HandlerThread("NotificationManagerCompat");

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class a {

            /* renamed from: a, reason: collision with root package name */
            final ComponentName f1264a;

            /* renamed from: c, reason: collision with root package name */
            android.support.v4.app.a f1266c;

            /* renamed from: b, reason: collision with root package name */
            boolean f1265b = false;

            /* renamed from: d, reason: collision with root package name */
            ArrayDeque<e> f1267d = new ArrayDeque<>();

            /* renamed from: e, reason: collision with root package name */
            int f1268e = 0;

            a(ComponentName componentName) {
                this.f1264a = componentName;
            }
        }

        d(Context context) {
            this.f1259a = context;
            this.f1260b.start();
            this.f1261c = new Handler(this.f1260b.getLooper(), this);
        }

        private void b(e eVar) {
            a();
            for (a aVar : this.f1262d.values()) {
                aVar.f1267d.add(eVar);
                c(aVar);
            }
        }

        private void c(a aVar) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Processing component " + aVar.f1264a + ", " + aVar.f1267d.size() + " queued tasks");
            }
            if (aVar.f1267d.isEmpty()) {
                return;
            }
            if (a(aVar) && aVar.f1266c != null) {
                while (true) {
                    e peek = aVar.f1267d.peek();
                    if (peek == null) {
                        break;
                    }
                    try {
                        if (Log.isLoggable("NotifManCompat", 3)) {
                            Log.d("NotifManCompat", "Sending task " + peek);
                        }
                        peek.a(aVar.f1266c);
                        aVar.f1267d.remove();
                    } catch (DeadObjectException unused) {
                        if (Log.isLoggable("NotifManCompat", 3)) {
                            Log.d("NotifManCompat", "Remote service has died: " + aVar.f1264a);
                        }
                    } catch (RemoteException e2) {
                        Log.w("NotifManCompat", "RemoteException communicating with " + aVar.f1264a, e2);
                    }
                }
                if (aVar.f1267d.isEmpty()) {
                    return;
                }
                d(aVar);
                return;
            }
            d(aVar);
        }

        private void d(a aVar) {
            if (this.f1261c.hasMessages(3, aVar.f1264a)) {
                return;
            }
            aVar.f1268e++;
            int i = aVar.f1268e;
            if (i > 6) {
                Log.w("NotifManCompat", "Giving up on delivering " + aVar.f1267d.size() + " tasks to " + aVar.f1264a + " after " + aVar.f1268e + " retries");
                aVar.f1267d.clear();
                return;
            }
            int i2 = (1 << (i - 1)) * 1000;
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Scheduling retry for " + i2 + " ms");
            }
            this.f1261c.sendMessageDelayed(this.f1261c.obtainMessage(3, aVar.f1264a), i2);
        }

        public void a(e eVar) {
            this.f1261c.obtainMessage(0, eVar).sendToTarget();
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                b((e) message.obj);
                return true;
            }
            if (i == 1) {
                c cVar = (c) message.obj;
                a(cVar.f1257a, cVar.f1258b);
                return true;
            }
            if (i == 2) {
                b((ComponentName) message.obj);
                return true;
            }
            if (i != 3) {
                return false;
            }
            a((ComponentName) message.obj);
            return true;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Connected to service " + componentName);
            }
            this.f1261c.obtainMessage(1, new c(componentName, iBinder)).sendToTarget();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Disconnected from service " + componentName);
            }
            this.f1261c.obtainMessage(2, componentName).sendToTarget();
        }

        private void a(ComponentName componentName, IBinder iBinder) {
            a aVar = this.f1262d.get(componentName);
            if (aVar != null) {
                aVar.f1266c = a.AbstractBinderC0036a.a(iBinder);
                aVar.f1268e = 0;
                c(aVar);
            }
        }

        private void b(ComponentName componentName) {
            a aVar = this.f1262d.get(componentName);
            if (aVar != null) {
                b(aVar);
            }
        }

        private void a(ComponentName componentName) {
            a aVar = this.f1262d.get(componentName);
            if (aVar != null) {
                c(aVar);
            }
        }

        private void b(a aVar) {
            if (aVar.f1265b) {
                this.f1259a.unbindService(this);
                aVar.f1265b = false;
            }
            aVar.f1266c = null;
        }

        private void a() {
            Set<String> b2 = j.b(this.f1259a);
            if (b2.equals(this.f1263e)) {
                return;
            }
            this.f1263e = b2;
            List<ResolveInfo> queryIntentServices = this.f1259a.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
            HashSet<ComponentName> hashSet = new HashSet();
            for (ResolveInfo resolveInfo : queryIntentServices) {
                if (b2.contains(resolveInfo.serviceInfo.packageName)) {
                    ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
                    if (resolveInfo.serviceInfo.permission != null) {
                        Log.w("NotifManCompat", "Permission present on component " + componentName + ", not adding listener record.");
                    } else {
                        hashSet.add(componentName);
                    }
                }
            }
            for (ComponentName componentName2 : hashSet) {
                if (!this.f1262d.containsKey(componentName2)) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Adding listener record for " + componentName2);
                    }
                    this.f1262d.put(componentName2, new a(componentName2));
                }
            }
            Iterator<Map.Entry<ComponentName, a>> it = this.f1262d.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<ComponentName, a> next = it.next();
                if (!hashSet.contains(next.getKey())) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Removing listener record for " + next.getKey());
                    }
                    b(next.getValue());
                    it.remove();
                }
            }
        }

        private boolean a(a aVar) {
            if (aVar.f1265b) {
                return true;
            }
            aVar.f1265b = this.f1259a.bindService(new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(aVar.f1264a), this, 33);
            if (aVar.f1265b) {
                aVar.f1268e = 0;
            } else {
                Log.w("NotifManCompat", "Unable to bind to listener " + aVar.f1264a);
                this.f1259a.unbindService(this);
            }
            return aVar.f1265b;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface e {
        void a(android.support.v4.app.a aVar);
    }

    private j(Context context) {
        this.f1247a = context;
        this.f1248b = (NotificationManager) this.f1247a.getSystemService("notification");
    }

    public static j a(Context context) {
        return new j(context);
    }

    public static Set<String> b(Context context) {
        Set<String> set;
        String string = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        synchronized (f1243c) {
            if (string != null) {
                if (!string.equals(f1244d)) {
                    String[] split = string.split(":", -1);
                    HashSet hashSet = new HashSet(split.length);
                    for (String str : split) {
                        ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
                        if (unflattenFromString != null) {
                            hashSet.add(unflattenFromString.getPackageName());
                        }
                    }
                    f1245e = hashSet;
                    f1244d = string;
                }
            }
            set = f1245e;
        }
        return set;
    }

    public void a(int i) {
        a((String) null, i);
    }

    public void a(String str, int i) {
        this.f1248b.cancel(str, i);
        if (Build.VERSION.SDK_INT <= 19) {
            a(new a(this.f1247a.getPackageName(), i, str));
        }
    }

    public void a(int i, Notification notification) {
        a(null, i, notification);
    }

    public void a(String str, int i, Notification notification) {
        if (a(notification)) {
            a(new b(this.f1247a.getPackageName(), i, str, notification));
            this.f1248b.cancel(str, i);
        } else {
            this.f1248b.notify(str, i, notification);
        }
    }

    private static boolean a(Notification notification) {
        Bundle a2 = g.a(notification);
        return a2 != null && a2.getBoolean("android.support.useSideChannel");
    }

    private void a(e eVar) {
        synchronized (f1246f) {
            if (g == null) {
                g = new d(this.f1247a.getApplicationContext());
            }
            g.a(eVar);
        }
    }
}
