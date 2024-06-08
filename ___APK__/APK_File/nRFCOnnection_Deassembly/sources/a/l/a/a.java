package a.l.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import no.nordicsemi.android.mcp.database.UuidColumns;

/* loaded from: classes.dex */
public final class a {

    /* renamed from: f, reason: collision with root package name */
    private static final Object f412f = new Object();
    private static a g;

    /* renamed from: a, reason: collision with root package name */
    private final Context f413a;

    /* renamed from: b, reason: collision with root package name */
    private final HashMap<BroadcastReceiver, ArrayList<c>> f414b = new HashMap<>();

    /* renamed from: c, reason: collision with root package name */
    private final HashMap<String, ArrayList<c>> f415c = new HashMap<>();

    /* renamed from: d, reason: collision with root package name */
    private final ArrayList<b> f416d = new ArrayList<>();

    /* renamed from: e, reason: collision with root package name */
    private final Handler f417e;

    /* renamed from: a.l.a.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class HandlerC0030a extends Handler {
        HandlerC0030a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                super.handleMessage(message);
            } else {
                a.this.a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        final Intent f419a;

        /* renamed from: b, reason: collision with root package name */
        final ArrayList<c> f420b;

        b(Intent intent, ArrayList<c> arrayList) {
            this.f419a = intent;
            this.f420b = arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        final IntentFilter f421a;

        /* renamed from: b, reason: collision with root package name */
        final BroadcastReceiver f422b;

        /* renamed from: c, reason: collision with root package name */
        boolean f423c;

        /* renamed from: d, reason: collision with root package name */
        boolean f424d;

        c(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
            this.f421a = intentFilter;
            this.f422b = broadcastReceiver;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Receiver{");
            sb.append(this.f422b);
            sb.append(" filter=");
            sb.append(this.f421a);
            if (this.f424d) {
                sb.append(" DEAD");
            }
            sb.append("}");
            return sb.toString();
        }
    }

    private a(Context context) {
        this.f413a = context;
        this.f417e = new HandlerC0030a(context.getMainLooper());
    }

    public static a a(Context context) {
        a aVar;
        synchronized (f412f) {
            if (g == null) {
                g = new a(context.getApplicationContext());
            }
            aVar = g;
        }
        return aVar;
    }

    public void a(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        synchronized (this.f414b) {
            c cVar = new c(intentFilter, broadcastReceiver);
            ArrayList<c> arrayList = this.f414b.get(broadcastReceiver);
            if (arrayList == null) {
                arrayList = new ArrayList<>(1);
                this.f414b.put(broadcastReceiver, arrayList);
            }
            arrayList.add(cVar);
            for (int i = 0; i < intentFilter.countActions(); i++) {
                String action = intentFilter.getAction(i);
                ArrayList<c> arrayList2 = this.f415c.get(action);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>(1);
                    this.f415c.put(action, arrayList2);
                }
                arrayList2.add(cVar);
            }
        }
    }

    public void a(BroadcastReceiver broadcastReceiver) {
        synchronized (this.f414b) {
            ArrayList<c> remove = this.f414b.remove(broadcastReceiver);
            if (remove == null) {
                return;
            }
            for (int size = remove.size() - 1; size >= 0; size--) {
                c cVar = remove.get(size);
                cVar.f424d = true;
                for (int i = 0; i < cVar.f421a.countActions(); i++) {
                    String action = cVar.f421a.getAction(i);
                    ArrayList<c> arrayList = this.f415c.get(action);
                    if (arrayList != null) {
                        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                            c cVar2 = arrayList.get(size2);
                            if (cVar2.f422b == broadcastReceiver) {
                                cVar2.f424d = true;
                                arrayList.remove(size2);
                            }
                        }
                        if (arrayList.size() <= 0) {
                            this.f415c.remove(action);
                        }
                    }
                }
            }
        }
    }

    public boolean a(Intent intent) {
        String str;
        ArrayList arrayList;
        int i;
        ArrayList<c> arrayList2;
        String str2;
        synchronized (this.f414b) {
            String action = intent.getAction();
            String resolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.f413a.getContentResolver());
            Uri data = intent.getData();
            String scheme = intent.getScheme();
            Set<String> categories = intent.getCategories();
            boolean z = (intent.getFlags() & 8) != 0;
            if (z) {
                Log.v("LocalBroadcastManager", "Resolving type " + resolveTypeIfNeeded + " scheme " + scheme + " of intent " + intent);
            }
            ArrayList<c> arrayList3 = this.f415c.get(intent.getAction());
            if (arrayList3 != null) {
                if (z) {
                    Log.v("LocalBroadcastManager", "Action list: " + arrayList3);
                }
                ArrayList arrayList4 = null;
                int i2 = 0;
                while (i2 < arrayList3.size()) {
                    c cVar = arrayList3.get(i2);
                    if (z) {
                        Log.v("LocalBroadcastManager", "Matching against filter " + cVar.f421a);
                    }
                    if (cVar.f423c) {
                        if (z) {
                            Log.v("LocalBroadcastManager", "  Filter's target already added");
                        }
                        i = i2;
                        arrayList2 = arrayList3;
                        str = action;
                        str2 = resolveTypeIfNeeded;
                        arrayList = arrayList4;
                    } else {
                        str = action;
                        arrayList = arrayList4;
                        i = i2;
                        arrayList2 = arrayList3;
                        str2 = resolveTypeIfNeeded;
                        int match = cVar.f421a.match(action, resolveTypeIfNeeded, scheme, data, categories, "LocalBroadcastManager");
                        if (match >= 0) {
                            if (z) {
                                Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(match));
                            }
                            arrayList4 = arrayList == null ? new ArrayList() : arrayList;
                            arrayList4.add(cVar);
                            cVar.f423c = true;
                            i2 = i + 1;
                            action = str;
                            arrayList3 = arrayList2;
                            resolveTypeIfNeeded = str2;
                        } else if (z) {
                            Log.v("LocalBroadcastManager", "  Filter did not match: " + (match != -4 ? match != -3 ? match != -2 ? match != -1 ? "unknown reason" : UuidColumns.SPECIFICATION_TYPE : "data" : "action" : "category"));
                        }
                    }
                    arrayList4 = arrayList;
                    i2 = i + 1;
                    action = str;
                    arrayList3 = arrayList2;
                    resolveTypeIfNeeded = str2;
                }
                ArrayList arrayList5 = arrayList4;
                if (arrayList5 != null) {
                    for (int i3 = 0; i3 < arrayList5.size(); i3++) {
                        ((c) arrayList5.get(i3)).f423c = false;
                    }
                    this.f416d.add(new b(intent, arrayList5));
                    if (!this.f417e.hasMessages(1)) {
                        this.f417e.sendEmptyMessage(1);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    void a() {
        b[] bVarArr;
        while (true) {
            synchronized (this.f414b) {
                int size = this.f416d.size();
                if (size <= 0) {
                    return;
                }
                bVarArr = new b[size];
                this.f416d.toArray(bVarArr);
                this.f416d.clear();
            }
            for (b bVar : bVarArr) {
                int size2 = bVar.f420b.size();
                for (int i = 0; i < size2; i++) {
                    c cVar = bVar.f420b.get(i);
                    if (!cVar.f424d) {
                        cVar.f422b.onReceive(this.f413a, bVar.f419a);
                    }
                }
            }
        }
    }
}
