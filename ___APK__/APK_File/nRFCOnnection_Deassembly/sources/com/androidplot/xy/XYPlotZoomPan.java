package com.androidplot.xy;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.androidplot.Plot;

/* loaded from: classes.dex */
public class XYPlotZoomPan extends XYPlot implements View.OnTouchListener {
    private static final float MIN_DIST_2_FING = 5.0f;
    private PointF firstFingerPos;
    private float lastMaxX;
    private float lastMaxY;
    private float lastMinX;
    private float lastMinY;
    private boolean mCalledBySelf;
    private float mDistX;
    private boolean mZoomEnabled;
    private boolean mZoomEnabledInit;
    private boolean mZoomHorizontally;
    private boolean mZoomHorizontallyInit;
    private boolean mZoomVertically;
    private boolean mZoomVerticallyInit;
    private float maxXLimit;
    private float maxYLimit;
    private float minXLimit;
    private float minYLimit;
    private State mode;

    /* loaded from: classes.dex */
    private enum State {
        NONE,
        ONE_FINGER_DRAG,
        TWO_FINGERS_DRAG
    }

    public XYPlotZoomPan(Context context, String str, Plot.RenderMode renderMode) {
        super(context, str, renderMode);
        this.mode = State.NONE;
        this.minXLimit = Float.MAX_VALUE;
        this.maxXLimit = Float.MAX_VALUE;
        this.minYLimit = Float.MAX_VALUE;
        this.maxYLimit = Float.MAX_VALUE;
        this.lastMinX = Float.MAX_VALUE;
        this.lastMaxX = Float.MAX_VALUE;
        this.lastMinY = Float.MAX_VALUE;
        this.lastMaxY = Float.MAX_VALUE;
        setZoomEnabled(true);
    }

    private void calculatePan(PointF pointF, PointF pointF2, boolean z) {
        float f2;
        float f3;
        int height;
        if (z) {
            pointF2.x = getLastMinX();
            pointF2.y = getLastMaxX();
            f2 = pointF.x - this.firstFingerPos.x;
            f3 = pointF2.y - pointF2.x;
            height = getWidth();
        } else {
            pointF2.x = getLastMinY();
            pointF2.y = getLastMaxY();
            f2 = -(pointF.y - this.firstFingerPos.y);
            f3 = pointF2.y - pointF2.x;
            height = getHeight();
        }
        float f4 = f2 * (f3 / height);
        pointF2.x += f4;
        pointF2.y += f4;
        float f5 = pointF2.y;
        float f6 = pointF2.x;
        float f7 = f5 - f6;
        if (z) {
            if (f6 < getMinXLimit()) {
                pointF2.x = getMinXLimit();
                pointF2.y = pointF2.x + f7;
            }
            if (pointF2.y > getMaxXLimit()) {
                pointF2.y = getMaxXLimit();
                pointF2.x = pointF2.y - f7;
                return;
            }
            return;
        }
        if (f6 < getMinYLimit()) {
            pointF2.x = getMinYLimit();
            pointF2.y = pointF2.x + f7;
        }
        if (pointF2.y > getMaxYLimit()) {
            pointF2.y = getMaxYLimit();
            pointF2.x = pointF2.y - f7;
        }
    }

    private void calculateZoom(float f2, PointF pointF, boolean z) {
        float lastMaxY;
        float lastMinY;
        if (z) {
            lastMaxY = getLastMaxX();
            lastMinY = getLastMinX();
        } else {
            lastMaxY = getLastMaxY();
            lastMinY = getLastMinY();
        }
        float f3 = lastMaxY - lastMinY;
        float f4 = lastMaxY - (f3 / 2.0f);
        float f5 = (f3 * f2) / 2.0f;
        pointF.x = f4 - f5;
        pointF.y = f4 + f5;
        if (z) {
            if (pointF.x < getMinXLimit()) {
                pointF.x = getMinXLimit();
            }
            if (pointF.y > getMaxXLimit()) {
                pointF.y = getMaxXLimit();
                return;
            }
            return;
        }
        if (pointF.x < getMinYLimit()) {
            pointF.x = getMinYLimit();
        }
        if (pointF.y > getMaxYLimit()) {
            pointF.y = getMaxYLimit();
        }
    }

    private float getLastMaxX() {
        if (this.lastMaxX == Float.MAX_VALUE) {
            this.lastMaxX = getCalculatedMaxX().floatValue();
        }
        return this.lastMaxX;
    }

    private float getLastMaxY() {
        if (this.lastMaxY == Float.MAX_VALUE) {
            this.lastMaxY = getCalculatedMaxY().floatValue();
        }
        return this.lastMaxY;
    }

    private float getLastMinX() {
        if (this.lastMinX == Float.MAX_VALUE) {
            this.lastMinX = getCalculatedMinX().floatValue();
        }
        return this.lastMinX;
    }

    private float getLastMinY() {
        if (this.lastMinY == Float.MAX_VALUE) {
            this.lastMinY = getCalculatedMinY().floatValue();
        }
        return this.lastMinY;
    }

    private float getMaxXLimit() {
        if (this.maxXLimit == Float.MAX_VALUE) {
            this.maxXLimit = getCalculatedMaxX().floatValue();
            this.lastMaxX = this.maxXLimit;
        }
        return this.maxXLimit;
    }

    private float getMaxYLimit() {
        if (this.maxYLimit == Float.MAX_VALUE) {
            this.maxYLimit = getCalculatedMaxY().floatValue();
            this.lastMaxY = this.maxYLimit;
        }
        return this.maxYLimit;
    }

    private float getMinXLimit() {
        if (this.minXLimit == Float.MAX_VALUE) {
            this.minXLimit = getCalculatedMinX().floatValue();
            this.lastMinX = this.minXLimit;
        }
        return this.minXLimit;
    }

    private float getMinYLimit() {
        if (this.minYLimit == Float.MAX_VALUE) {
            this.minYLimit = getCalculatedMinY().floatValue();
            this.lastMinY = this.minYLimit;
        }
        return this.minYLimit;
    }

    private float getXDistance(MotionEvent motionEvent) {
        return motionEvent.getX(0) - motionEvent.getX(1);
    }

    private void pan(MotionEvent motionEvent) {
        PointF pointF = this.firstFingerPos;
        this.firstFingerPos = new PointF(motionEvent.getX(), motionEvent.getY());
        PointF pointF2 = new PointF();
        if (this.mZoomHorizontally) {
            calculatePan(pointF, pointF2, true);
            this.mCalledBySelf = true;
            super.setDomainBoundaries(Float.valueOf(pointF2.x), Float.valueOf(pointF2.y), BoundaryMode.FIXED);
            this.lastMinX = pointF2.x;
            this.lastMaxX = pointF2.y;
        }
        if (this.mZoomVertically) {
            calculatePan(pointF, pointF2, false);
            this.mCalledBySelf = true;
            super.setRangeBoundaries(Float.valueOf(pointF2.x), Float.valueOf(pointF2.y), BoundaryMode.FIXED);
            this.lastMinY = pointF2.x;
            this.lastMaxY = pointF2.y;
        }
        redraw();
    }

    private void zoom(MotionEvent motionEvent) {
        float f2 = this.mDistX;
        float xDistance = getXDistance(motionEvent);
        if (f2 <= 0.0f || xDistance >= 0.0f) {
            if (f2 >= 0.0f || xDistance <= 0.0f) {
                this.mDistX = xDistance;
                float f3 = f2 / this.mDistX;
                if (Float.isInfinite(f3) || Float.isNaN(f3)) {
                    return;
                }
                double d2 = f3;
                if (d2 <= -0.001d || d2 >= 0.001d) {
                    PointF pointF = new PointF();
                    if (this.mZoomHorizontally) {
                        calculateZoom(f3, pointF, true);
                        this.mCalledBySelf = true;
                        super.setDomainBoundaries(Float.valueOf(pointF.x), Float.valueOf(pointF.y), BoundaryMode.FIXED);
                        this.lastMinX = pointF.x;
                        this.lastMaxX = pointF.y;
                    }
                    if (this.mZoomVertically) {
                        calculateZoom(f3, pointF, false);
                        this.mCalledBySelf = true;
                        super.setRangeBoundaries(Float.valueOf(pointF.x), Float.valueOf(pointF.y), BoundaryMode.FIXED);
                        this.lastMinY = pointF.x;
                        this.lastMaxY = pointF.y;
                    }
                    redraw();
                }
            }
        }
    }

    public boolean getZoomEnabled() {
        return this.mZoomEnabled;
    }

    public boolean getZoomHorizontally() {
        return this.mZoomHorizontally;
    }

    public boolean getZoomVertically() {
        return this.mZoomVertically;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.firstFingerPos = new PointF(motionEvent.getX(), motionEvent.getY());
            this.mode = State.ONE_FINGER_DRAG;
            return true;
        }
        if (action == 2) {
            State state = this.mode;
            if (state == State.ONE_FINGER_DRAG) {
                pan(motionEvent);
                return true;
            }
            if (state != State.TWO_FINGERS_DRAG) {
                return true;
            }
            zoom(motionEvent);
            return true;
        }
        if (action != 5) {
            if (action != 6) {
                return true;
            }
            this.mode = State.NONE;
            return true;
        }
        this.mDistX = getXDistance(motionEvent);
        float f2 = this.mDistX;
        if (f2 <= MIN_DIST_2_FING && f2 >= -5.0f) {
            return true;
        }
        this.mode = State.TWO_FINGERS_DRAG;
        return true;
    }

    @Override // com.androidplot.xy.XYPlot
    public synchronized void setDomainBoundaries(Number number, BoundaryMode boundaryMode, Number number2, BoundaryMode boundaryMode2) {
        super.setDomainBoundaries(number, boundaryMode, number2, boundaryMode2);
        if (this.mCalledBySelf) {
            this.mCalledBySelf = false;
        } else {
            if (boundaryMode != BoundaryMode.FIXED) {
                number = getCalculatedMinX();
            }
            this.minXLimit = number.floatValue();
            this.maxXLimit = boundaryMode2 == BoundaryMode.FIXED ? number2.floatValue() : getCalculatedMaxX().floatValue();
            this.lastMinX = this.minXLimit;
            this.lastMaxX = this.maxXLimit;
        }
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        if (onTouchListener != this) {
            this.mZoomEnabled = false;
        }
        super.setOnTouchListener(onTouchListener);
    }

    @Override // com.androidplot.xy.XYPlot
    public synchronized void setRangeBoundaries(Number number, BoundaryMode boundaryMode, Number number2, BoundaryMode boundaryMode2) {
        super.setRangeBoundaries(number, boundaryMode, number2, boundaryMode2);
        if (this.mCalledBySelf) {
            this.mCalledBySelf = false;
        } else {
            if (boundaryMode != BoundaryMode.FIXED) {
                number = getCalculatedMinY();
            }
            this.minYLimit = number.floatValue();
            this.maxYLimit = boundaryMode2 == BoundaryMode.FIXED ? number2.floatValue() : getCalculatedMaxY().floatValue();
            this.lastMinY = this.minYLimit;
            this.lastMaxY = this.maxYLimit;
        }
    }

    public void setZoomEnabled(boolean z) {
        if (z) {
            setOnTouchListener(this);
        } else {
            setOnTouchListener(null);
        }
        this.mZoomEnabled = z;
        this.mZoomEnabledInit = true;
    }

    public void setZoomHorizontally(boolean z) {
        this.mZoomHorizontally = z;
        this.mZoomHorizontallyInit = true;
    }

    public void setZoomVertically(boolean z) {
        this.mZoomVertically = z;
        this.mZoomVerticallyInit = true;
    }

    @Override // com.androidplot.xy.XYPlot
    public synchronized void setDomainBoundaries(Number number, Number number2, BoundaryMode boundaryMode) {
        super.setDomainBoundaries(number, number2, boundaryMode);
        if (this.mCalledBySelf) {
            this.mCalledBySelf = false;
        } else {
            if (boundaryMode != BoundaryMode.FIXED) {
                number = getCalculatedMinX();
            }
            this.minXLimit = number.floatValue();
            this.maxXLimit = boundaryMode == BoundaryMode.FIXED ? number2.floatValue() : getCalculatedMaxX().floatValue();
            this.lastMinX = this.minXLimit;
            this.lastMaxX = this.maxXLimit;
        }
    }

    @Override // com.androidplot.xy.XYPlot
    public synchronized void setRangeBoundaries(Number number, Number number2, BoundaryMode boundaryMode) {
        super.setRangeBoundaries(number, number2, boundaryMode);
        if (this.mCalledBySelf) {
            this.mCalledBySelf = false;
        } else {
            if (boundaryMode != BoundaryMode.FIXED) {
                number = getCalculatedMinY();
            }
            this.minYLimit = number.floatValue();
            this.maxYLimit = boundaryMode == BoundaryMode.FIXED ? number2.floatValue() : getCalculatedMaxY().floatValue();
            this.lastMinY = this.minYLimit;
            this.lastMaxY = this.maxYLimit;
        }
    }

    public XYPlotZoomPan(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mode = State.NONE;
        this.minXLimit = Float.MAX_VALUE;
        this.maxXLimit = Float.MAX_VALUE;
        this.minYLimit = Float.MAX_VALUE;
        this.maxYLimit = Float.MAX_VALUE;
        this.lastMinX = Float.MAX_VALUE;
        this.lastMaxX = Float.MAX_VALUE;
        this.lastMinY = Float.MAX_VALUE;
        this.lastMaxY = Float.MAX_VALUE;
        if (this.mZoomEnabled || !this.mZoomEnabledInit) {
            setZoomEnabled(true);
        }
        if (!this.mZoomHorizontallyInit) {
            this.mZoomHorizontally = true;
        }
        if (this.mZoomVerticallyInit) {
            return;
        }
        this.mZoomVertically = true;
    }

    public XYPlotZoomPan(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mode = State.NONE;
        this.minXLimit = Float.MAX_VALUE;
        this.maxXLimit = Float.MAX_VALUE;
        this.minYLimit = Float.MAX_VALUE;
        this.maxYLimit = Float.MAX_VALUE;
        this.lastMinX = Float.MAX_VALUE;
        this.lastMaxX = Float.MAX_VALUE;
        this.lastMinY = Float.MAX_VALUE;
        this.lastMaxY = Float.MAX_VALUE;
        if (this.mZoomEnabled || !this.mZoomEnabledInit) {
            setZoomEnabled(true);
        }
        if (!this.mZoomHorizontallyInit) {
            this.mZoomHorizontally = true;
        }
        if (this.mZoomVerticallyInit) {
            return;
        }
        this.mZoomVertically = true;
    }

    public XYPlotZoomPan(Context context, String str) {
        super(context, str);
        this.mode = State.NONE;
        this.minXLimit = Float.MAX_VALUE;
        this.maxXLimit = Float.MAX_VALUE;
        this.minYLimit = Float.MAX_VALUE;
        this.maxYLimit = Float.MAX_VALUE;
        this.lastMinX = Float.MAX_VALUE;
        this.lastMaxX = Float.MAX_VALUE;
        this.lastMinY = Float.MAX_VALUE;
        this.lastMaxY = Float.MAX_VALUE;
    }
}
