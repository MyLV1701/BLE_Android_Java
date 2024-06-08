package a.f.l;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/* loaded from: classes.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    private final a f287a;

    /* loaded from: classes.dex */
    interface a {
        boolean a(MotionEvent motionEvent);
    }

    /* loaded from: classes.dex */
    static class b implements a {
        private static final int v = ViewConfiguration.getLongPressTimeout();
        private static final int w = ViewConfiguration.getTapTimeout();
        private static final int x = ViewConfiguration.getDoubleTapTimeout();

        /* renamed from: a, reason: collision with root package name */
        private int f288a;

        /* renamed from: b, reason: collision with root package name */
        private int f289b;

        /* renamed from: c, reason: collision with root package name */
        private int f290c;

        /* renamed from: d, reason: collision with root package name */
        private int f291d;

        /* renamed from: e, reason: collision with root package name */
        private final Handler f292e;

        /* renamed from: f, reason: collision with root package name */
        final GestureDetector.OnGestureListener f293f;
        GestureDetector.OnDoubleTapListener g;
        boolean h;
        boolean i;
        private boolean j;
        private boolean k;
        private boolean l;
        MotionEvent m;
        private MotionEvent n;
        private boolean o;
        private float p;
        private float q;
        private float r;
        private float s;
        private boolean t;
        private VelocityTracker u;

        b(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            if (handler != null) {
                this.f292e = new a(handler);
            } else {
                this.f292e = new a();
            }
            this.f293f = onGestureListener;
            if (onGestureListener instanceof GestureDetector.OnDoubleTapListener) {
                a((GestureDetector.OnDoubleTapListener) onGestureListener);
            }
            a(context);
        }

        private void a(Context context) {
            if (context != null) {
                if (this.f293f != null) {
                    this.t = true;
                    ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
                    int scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
                    int scaledDoubleTapSlop = viewConfiguration.getScaledDoubleTapSlop();
                    this.f290c = viewConfiguration.getScaledMinimumFlingVelocity();
                    this.f291d = viewConfiguration.getScaledMaximumFlingVelocity();
                    this.f288a = scaledTouchSlop * scaledTouchSlop;
                    this.f289b = scaledDoubleTapSlop * scaledDoubleTapSlop;
                    return;
                }
                throw new IllegalArgumentException("OnGestureListener must not be null");
            }
            throw new IllegalArgumentException("Context must not be null");
        }

        private void b() {
            this.f292e.removeMessages(1);
            this.f292e.removeMessages(2);
            this.f292e.removeMessages(3);
            this.u.recycle();
            this.u = null;
            this.o = false;
            this.h = false;
            this.k = false;
            this.l = false;
            this.i = false;
            if (this.j) {
                this.j = false;
            }
        }

        private void c() {
            this.f292e.removeMessages(1);
            this.f292e.removeMessages(2);
            this.f292e.removeMessages(3);
            this.o = false;
            this.k = false;
            this.l = false;
            this.i = false;
            if (this.j) {
                this.j = false;
            }
        }

        /* loaded from: classes.dex */
        private class a extends Handler {
            a() {
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    b bVar = b.this;
                    bVar.f293f.onShowPress(bVar.m);
                    return;
                }
                if (i == 2) {
                    b.this.a();
                    return;
                }
                if (i == 3) {
                    b bVar2 = b.this;
                    GestureDetector.OnDoubleTapListener onDoubleTapListener = bVar2.g;
                    if (onDoubleTapListener != null) {
                        if (!bVar2.h) {
                            onDoubleTapListener.onSingleTapConfirmed(bVar2.m);
                            return;
                        } else {
                            bVar2.i = true;
                            return;
                        }
                    }
                    return;
                }
                throw new RuntimeException("Unknown message " + message);
            }

            a(Handler handler) {
                super(handler.getLooper());
            }
        }

        public void a(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
            this.g = onDoubleTapListener;
        }

        /* JADX WARN: Removed duplicated region for block: B:114:0x0208  */
        /* JADX WARN: Removed duplicated region for block: B:117:0x021f  */
        @Override // a.f.l.c.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean a(android.view.MotionEvent r13) {
            /*
                Method dump skipped, instructions count: 591
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: a.f.l.c.b.a(android.view.MotionEvent):boolean");
        }

        private boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, MotionEvent motionEvent3) {
            if (!this.l || motionEvent3.getEventTime() - motionEvent2.getEventTime() > x) {
                return false;
            }
            int x2 = ((int) motionEvent.getX()) - ((int) motionEvent3.getX());
            int y = ((int) motionEvent.getY()) - ((int) motionEvent3.getY());
            return (x2 * x2) + (y * y) < this.f289b;
        }

        void a() {
            this.f292e.removeMessages(3);
            this.i = false;
            this.j = true;
            this.f293f.onLongPress(this.m);
        }
    }

    /* renamed from: a.f.l.c$c, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0017c implements a {

        /* renamed from: a, reason: collision with root package name */
        private final GestureDetector f295a;

        C0017c(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            this.f295a = new GestureDetector(context, onGestureListener, handler);
        }

        @Override // a.f.l.c.a
        public boolean a(MotionEvent motionEvent) {
            return this.f295a.onTouchEvent(motionEvent);
        }
    }

    public c(Context context, GestureDetector.OnGestureListener onGestureListener) {
        this(context, onGestureListener, null);
    }

    public boolean a(MotionEvent motionEvent) {
        return this.f287a.a(motionEvent);
    }

    public c(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
        if (Build.VERSION.SDK_INT > 17) {
            this.f287a = new C0017c(context, onGestureListener, handler);
        } else {
            this.f287a = new b(context, onGestureListener, handler);
        }
    }
}
