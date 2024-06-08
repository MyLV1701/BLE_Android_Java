package com.androidplot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.os.Build;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.androidplot.Series;
import com.androidplot.exception.PlotRenderException;
import com.androidplot.ui.AnchorPosition;
import com.androidplot.ui.BoxModel;
import com.androidplot.ui.Formatter;
import com.androidplot.ui.LayoutManager;
import com.androidplot.ui.Resizable;
import com.androidplot.ui.SeriesAndFormatterList;
import com.androidplot.ui.SeriesRenderer;
import com.androidplot.ui.SizeLayoutType;
import com.androidplot.ui.SizeMetrics;
import com.androidplot.ui.TextOrientationType;
import com.androidplot.ui.XLayoutStyle;
import com.androidplot.ui.YLayoutStyle;
import com.androidplot.ui.widget.TextLabelWidget;
import com.androidplot.util.Configurator;
import com.androidplot.util.DisplayDimensions;
import com.androidplot.util.PixelUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class Plot<SeriesType extends Series, FormatterType extends Formatter, RendererType extends SeriesRenderer> extends View implements Resizable {
    private static final String ATTR_RENDER_MODE = "renderMode";
    private static final String ATTR_TITLE = "title";
    private static final String TAG = Plot.class.getName();
    private static final String XML_ATTR_PREFIX = "androidplot";
    private Paint backgroundPaint;
    private Paint borderPaint;
    private float borderRadiusX;
    private float borderRadiusY;
    private BorderStyle borderStyle;
    private BoxModel boxModel;
    private DisplayDimensions displayDims;
    private boolean drawBorderEnabled;
    private boolean isIdle;
    private boolean keepRunning;
    private LayoutManager layoutManager;
    private final ArrayList<PlotListener> listeners;
    private final Plot<SeriesType, FormatterType, RendererType>.BufferedCanvas pingPong;
    private RenderMode renderMode;
    private final Object renderSynch;
    private Thread renderThread;
    private LinkedList<RendererType> renderers;
    private LinkedHashMap<Class, SeriesAndFormatterList<SeriesType, FormatterType>> seriesRegistry;
    private TextLabelWidget titleWidget;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.androidplot.Plot$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$androidplot$Plot$BorderStyle = new int[BorderStyle.values().length];

        static {
            try {
                $SwitchMap$com$androidplot$Plot$BorderStyle[BorderStyle.ROUNDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$androidplot$Plot$BorderStyle[BorderStyle.SQUARE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum BorderStyle {
        ROUNDED,
        SQUARE,
        NONE
    }

    /* loaded from: classes.dex */
    public enum RenderMode {
        USE_BACKGROUND_THREAD,
        USE_MAIN_THREAD
    }

    public Plot(Context context, String str) {
        this(context, str, RenderMode.USE_MAIN_THREAD);
    }

    private void init(Context context, AttributeSet attributeSet) {
        PixelUtils.init(getContext());
        this.layoutManager = new LayoutManager();
        LayoutManager layoutManager = this.layoutManager;
        SizeLayoutType sizeLayoutType = SizeLayoutType.ABSOLUTE;
        this.titleWidget = new TextLabelWidget(layoutManager, new SizeMetrics(25.0f, sizeLayoutType, 100.0f, sizeLayoutType), TextOrientationType.HORIZONTAL);
        this.titleWidget.position(0.0f, XLayoutStyle.RELATIVE_TO_CENTER, 0.0f, YLayoutStyle.ABSOLUTE_FROM_TOP, AnchorPosition.TOP_MIDDLE);
        onPreInit();
        this.layoutManager.moveToTop(this.titleWidget);
        if (context != null && attributeSet != null) {
            loadAttrs(attributeSet);
        }
        this.layoutManager.onPostInit();
        if (this.renderMode == RenderMode.USE_BACKGROUND_THREAD) {
            this.renderThread = new Thread(new Runnable() { // from class: com.androidplot.a
                @Override // java.lang.Runnable
                public final void run() {
                    Plot.this.a();
                }
            });
        }
    }

    private void loadAttrs(AttributeSet attributeSet) {
        if (attributeSet != null) {
            HashMap hashMap = new HashMap();
            for (int i = 0; i < attributeSet.getAttributeCount(); i++) {
                String attributeName = attributeSet.getAttributeName(i);
                if (attributeName.toUpperCase().startsWith(XML_ATTR_PREFIX.toUpperCase())) {
                    hashMap.put(attributeName.substring(12), attributeSet.getAttributeValue(i));
                }
            }
            Configurator.configure(getContext(), this, (HashMap<String, String>) hashMap);
        }
    }

    public /* synthetic */ void a() {
        this.keepRunning = true;
        while (this.keepRunning) {
            this.isIdle = false;
            synchronized (this.pingPong) {
                renderOnCanvas(this.pingPong.getCanvas());
                this.pingPong.swap();
            }
            synchronized (this.renderSynch) {
                postInvalidate();
                if (this.keepRunning) {
                    try {
                        this.renderSynch.wait();
                    } catch (InterruptedException unused) {
                        this.keepRunning = false;
                    }
                }
            }
        }
        System.out.println("AndroidPlot render thread finished.");
    }

    public synchronized boolean addListener(PlotListener plotListener) {
        boolean z;
        if (!this.listeners.contains(plotListener)) {
            z = this.listeners.add(plotListener);
        }
        return z;
    }

    public synchronized boolean addSeries(SeriesType seriestype, FormatterType formattertype) {
        Class<? extends SeriesRenderer> rendererClass = formattertype.getRendererClass();
        SeriesAndFormatterList<SeriesType, FormatterType> seriesAndFormatterList = this.seriesRegistry.get(rendererClass);
        if (seriesAndFormatterList == null) {
            if (getRenderer(rendererClass) == null) {
                this.renderers.add(formattertype.getRendererInstance(this));
            }
            seriesAndFormatterList = new SeriesAndFormatterList<>();
            this.seriesRegistry.put(rendererClass, seriesAndFormatterList);
        }
        if (seriestype instanceof PlotListener) {
            addListener((PlotListener) seriestype);
        }
        if (seriesAndFormatterList.contains(seriestype)) {
            return false;
        }
        seriesAndFormatterList.add(seriestype, formattertype);
        return true;
    }

    public void clear() {
        Iterator<SeriesAndFormatterList<SeriesType, FormatterType>> it = this.seriesRegistry.values().iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    protected void drawBackground(Canvas canvas, RectF rectF) {
        int i = AnonymousClass1.$SwitchMap$com$androidplot$Plot$BorderStyle[this.borderStyle.ordinal()];
        if (i == 1) {
            canvas.drawRoundRect(rectF, this.borderRadiusX, this.borderRadiusY, this.backgroundPaint);
        } else {
            if (i != 2) {
                return;
            }
            canvas.drawRect(rectF, this.backgroundPaint);
        }
    }

    protected void drawBorder(Canvas canvas, RectF rectF) {
        int i = AnonymousClass1.$SwitchMap$com$androidplot$Plot$BorderStyle[this.borderStyle.ordinal()];
        if (i == 1) {
            canvas.drawRoundRect(rectF, this.borderRadiusX, this.borderRadiusY, this.borderPaint);
        } else {
            if (i != 2) {
                return;
            }
            canvas.drawRect(rectF, this.borderPaint);
        }
    }

    public Paint getBackgroundPaint() {
        return this.backgroundPaint;
    }

    public Paint getBorderPaint() {
        return this.borderPaint;
    }

    public DisplayDimensions getDisplayDimensions() {
        return this.displayDims;
    }

    public FormatterType getFormatter(SeriesType seriestype, Class cls) {
        return this.seriesRegistry.get(cls).getFormatter((SeriesAndFormatterList<SeriesType, FormatterType>) seriestype);
    }

    public LayoutManager getLayoutManager() {
        return this.layoutManager;
    }

    public float getPlotMarginBottom() {
        return this.boxModel.getMarginBottom();
    }

    public float getPlotMarginLeft() {
        return this.boxModel.getMarginLeft();
    }

    public float getPlotMarginRight() {
        return this.boxModel.getMarginRight();
    }

    public float getPlotMarginTop() {
        return this.boxModel.getMarginTop();
    }

    public float getPlotPaddingBottom() {
        return this.boxModel.getPaddingBottom();
    }

    public float getPlotPaddingLeft() {
        return this.boxModel.getPaddingLeft();
    }

    public float getPlotPaddingRight() {
        return this.boxModel.getPaddingRight();
    }

    public float getPlotPaddingTop() {
        return this.boxModel.getPaddingTop();
    }

    public RenderMode getRenderMode() {
        return this.renderMode;
    }

    public RendererType getRenderer(Class cls) {
        Iterator<RendererType> it = this.renderers.iterator();
        while (it.hasNext()) {
            RendererType next = it.next();
            if (next.getClass() == cls) {
                return next;
            }
        }
        return null;
    }

    public List<RendererType> getRendererList() {
        return this.renderers;
    }

    public SeriesAndFormatterList<SeriesType, FormatterType> getSeriesAndFormatterListForRenderer(Class cls) {
        return this.seriesRegistry.get(cls);
    }

    public List<SeriesType> getSeriesListForRenderer(Class cls) {
        SeriesAndFormatterList<SeriesType, FormatterType> seriesAndFormatterList = this.seriesRegistry.get(cls);
        if (seriesAndFormatterList == null) {
            return null;
        }
        return seriesAndFormatterList.getSeriesList();
    }

    public Set<SeriesType> getSeriesSet() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<RendererType> it = getRendererList().iterator();
        while (it.hasNext()) {
            List<SeriesType> seriesListForRenderer = getSeriesListForRenderer(it.next().getClass());
            if (seriesListForRenderer != null) {
                linkedHashSet.addAll(seriesListForRenderer);
            }
        }
        return linkedHashSet;
    }

    public String getTitle() {
        return getTitleWidget().getText();
    }

    public TextLabelWidget getTitleWidget() {
        return this.titleWidget;
    }

    public boolean isEmpty() {
        return this.seriesRegistry.isEmpty();
    }

    protected boolean isHwAccelerationSupported() {
        return false;
    }

    @Override // com.androidplot.ui.Resizable
    public synchronized void layout(DisplayDimensions displayDimensions) {
        this.displayDims = displayDimensions;
        this.layoutManager.layout(this.displayDims);
    }

    protected void notifyListenersAfterDraw(Canvas canvas) {
        Iterator<PlotListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onAfterDraw(this, canvas);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyListenersBeforeDraw(Canvas canvas) {
        Iterator<PlotListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onBeforeDraw(this, canvas);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        synchronized (this.renderSynch) {
            this.keepRunning = false;
            this.renderSynch.notify();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        RenderMode renderMode = this.renderMode;
        if (renderMode == RenderMode.USE_BACKGROUND_THREAD) {
            synchronized (this.pingPong) {
                Bitmap bitmap = this.pingPong.getBitmap();
                if (bitmap != null) {
                    canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
                }
            }
            return;
        }
        if (renderMode == RenderMode.USE_MAIN_THREAD) {
            renderOnCanvas(canvas);
            return;
        }
        throw new IllegalArgumentException("Unsupported Render Mode: " + this.renderMode);
    }

    protected abstract void onPreInit();

    @Override // android.view.View
    protected synchronized void onSizeChanged(int i, int i2, int i3, int i4) {
        PixelUtils.init(getContext());
        if (Build.VERSION.SDK_INT >= 11 && !isHwAccelerationSupported() && isHardwareAccelerated()) {
            setLayerType(1, null);
        }
        if (this.renderMode == RenderMode.USE_BACKGROUND_THREAD) {
            this.pingPong.resize(i2, i);
        }
        RectF rectF = new RectF(0.0f, 0.0f, i, i2);
        RectF marginatedRect = this.boxModel.getMarginatedRect(rectF);
        layout(new DisplayDimensions(rectF, marginatedRect, this.boxModel.getPaddedRect(marginatedRect)));
        super.onSizeChanged(i, i2, i3, i4);
        if (this.renderThread != null && !this.renderThread.isAlive()) {
            this.renderThread.start();
        }
    }

    public void redraw() {
        RenderMode renderMode = this.renderMode;
        if (renderMode == RenderMode.USE_BACKGROUND_THREAD) {
            if (this.isIdle) {
                synchronized (this.renderSynch) {
                    this.renderSynch.notify();
                }
                return;
            }
            return;
        }
        if (renderMode == RenderMode.USE_MAIN_THREAD) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                invalidate();
                return;
            } else {
                postInvalidate();
                return;
            }
        }
        throw new IllegalArgumentException("Unsupported Render Mode: " + this.renderMode);
    }

    public synchronized boolean removeListener(PlotListener plotListener) {
        return this.listeners.remove(plotListener);
    }

    public synchronized boolean removeSeries(SeriesType seriestype, Class cls) {
        SeriesAndFormatterList<SeriesType, FormatterType> seriesAndFormatterList = this.seriesRegistry.get(cls);
        if (seriesAndFormatterList == null) {
            return false;
        }
        boolean remove = seriesAndFormatterList.remove(seriestype);
        if (seriesAndFormatterList.size() <= 0) {
            this.seriesRegistry.remove(cls);
        }
        if (seriestype instanceof PlotListener) {
            removeListener((PlotListener) seriestype);
        }
        return remove;
    }

    protected synchronized void renderOnCanvas(Canvas canvas) {
        try {
            notifyListenersBeforeDraw(canvas);
            try {
                canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                if (this.backgroundPaint != null) {
                    drawBackground(canvas, this.displayDims.marginatedRect);
                }
                this.layoutManager.draw(canvas);
                if (getBorderPaint() != null) {
                    drawBorder(canvas, this.displayDims.marginatedRect);
                }
            } catch (PlotRenderException e2) {
                Log.e(TAG, "Exception while rendering Plot.", e2);
                e2.printStackTrace();
            } catch (Exception e3) {
                Log.e(TAG, "Exception while rendering Plot.", e3);
            }
        } finally {
            this.isIdle = true;
            notifyListenersAfterDraw(canvas);
        }
    }

    public void setBackgroundPaint(Paint paint) {
        this.backgroundPaint = paint;
    }

    public void setBorderPaint(Paint paint) {
        if (paint == null) {
            this.borderPaint = null;
        } else {
            this.borderPaint = new Paint(paint);
            this.borderPaint.setStyle(Paint.Style.STROKE);
        }
    }

    public void setBorderStyle(BorderStyle borderStyle, Float f2, Float f3) {
        if (borderStyle == BorderStyle.ROUNDED) {
            if (f2 != null && f3 != null) {
                this.borderRadiusX = f2.floatValue();
                this.borderRadiusY = f3.floatValue();
            } else {
                throw new IllegalArgumentException("radiusX and radiusY cannot be null when using BorderStyle.ROUNDED");
            }
        }
        this.borderStyle = borderStyle;
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public void setMarkupEnabled(boolean z) {
        this.layoutManager.setMarkupEnabled(z);
    }

    public void setPlotMarginBottom(float f2) {
        this.boxModel.setMarginBottom(f2);
    }

    public void setPlotMarginLeft(float f2) {
        this.boxModel.setMarginLeft(f2);
    }

    public void setPlotMarginRight(float f2) {
        this.boxModel.setMarginRight(f2);
    }

    public void setPlotMarginTop(float f2) {
        this.boxModel.setMarginTop(f2);
    }

    public void setPlotMargins(float f2, float f3, float f4, float f5) {
        setPlotMarginLeft(f2);
        setPlotMarginTop(f3);
        setPlotMarginRight(f4);
        setPlotMarginBottom(f5);
    }

    public void setPlotPadding(float f2, float f3, float f4, float f5) {
        setPlotPaddingLeft(f2);
        setPlotPaddingTop(f3);
        setPlotPaddingRight(f4);
        setPlotPaddingBottom(f5);
    }

    public void setPlotPaddingBottom(float f2) {
        this.boxModel.setPaddingBottom(f2);
    }

    public void setPlotPaddingLeft(float f2) {
        this.boxModel.setPaddingLeft(f2);
    }

    public void setPlotPaddingRight(float f2) {
        this.boxModel.setPaddingRight(f2);
    }

    public void setPlotPaddingTop(float f2) {
        this.boxModel.setPaddingTop(f2);
    }

    public void setRenderMode(RenderMode renderMode) {
        this.renderMode = renderMode;
    }

    public void setTitle(String str) {
        this.titleWidget.setText(str);
    }

    public void setTitleWidget(TextLabelWidget textLabelWidget) {
        this.titleWidget = textLabelWidget;
    }

    /* loaded from: classes.dex */
    private class BufferedCanvas {
        private volatile Bitmap bgBuffer;
        private Canvas canvas;
        private volatile Bitmap fgBuffer;

        private BufferedCanvas() {
            this.canvas = new Canvas();
        }

        public Bitmap getBitmap() {
            return this.fgBuffer;
        }

        public synchronized Canvas getCanvas() {
            if (this.bgBuffer == null) {
                return null;
            }
            this.canvas.setBitmap(this.bgBuffer);
            return this.canvas;
        }

        public synchronized void resize(int i, int i2) {
            if (i2 > 0 && i > 0) {
                this.bgBuffer = Bitmap.createBitmap(i2, i, Bitmap.Config.ARGB_4444);
                this.fgBuffer = Bitmap.createBitmap(i2, i, Bitmap.Config.ARGB_4444);
            } else {
                this.bgBuffer = null;
                this.fgBuffer = null;
            }
        }

        public synchronized void swap() {
            Bitmap bitmap = this.bgBuffer;
            this.bgBuffer = this.fgBuffer;
            this.fgBuffer = bitmap;
        }

        /* synthetic */ BufferedCanvas(Plot plot, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public Plot(Context context, String str, RenderMode renderMode) {
        super(context);
        this.boxModel = new BoxModel(3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f);
        this.borderStyle = BorderStyle.SQUARE;
        this.borderRadiusX = 15.0f;
        this.borderRadiusY = 15.0f;
        this.drawBorderEnabled = true;
        this.displayDims = new DisplayDimensions();
        this.renderMode = RenderMode.USE_MAIN_THREAD;
        this.pingPong = new BufferedCanvas(this, null);
        this.renderSynch = new Object();
        this.keepRunning = false;
        this.isIdle = true;
        this.listeners = new ArrayList<>();
        this.seriesRegistry = new LinkedHashMap<>();
        this.renderers = new LinkedList<>();
        this.borderPaint = new Paint();
        this.borderPaint.setColor(Color.rgb(150, 150, 150));
        this.borderPaint.setStyle(Paint.Style.STROKE);
        this.borderPaint.setStrokeWidth(1.0f);
        this.borderPaint.setAntiAlias(true);
        this.backgroundPaint = new Paint();
        this.backgroundPaint.setColor(-12303292);
        this.backgroundPaint.setStyle(Paint.Style.FILL);
        this.renderMode = renderMode;
        init(null, null);
        setTitle(str);
    }

    public synchronized void removeSeries(SeriesType seriestype) {
        Iterator<Class> it = this.seriesRegistry.keySet().iterator();
        while (it.hasNext()) {
            this.seriesRegistry.get(it.next()).remove(seriestype);
        }
        Iterator<SeriesAndFormatterList<SeriesType, FormatterType>> it2 = this.seriesRegistry.values().iterator();
        while (it2.hasNext()) {
            if (it2.next().size() <= 0) {
                it2.remove();
            }
        }
        if (seriestype instanceof PlotListener) {
            removeListener((PlotListener) seriestype);
        }
    }

    public Plot(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.boxModel = new BoxModel(3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f);
        this.borderStyle = BorderStyle.SQUARE;
        this.borderRadiusX = 15.0f;
        this.borderRadiusY = 15.0f;
        this.drawBorderEnabled = true;
        this.displayDims = new DisplayDimensions();
        this.renderMode = RenderMode.USE_MAIN_THREAD;
        this.pingPong = new BufferedCanvas(this, null);
        this.renderSynch = new Object();
        this.keepRunning = false;
        this.isIdle = true;
        this.listeners = new ArrayList<>();
        this.seriesRegistry = new LinkedHashMap<>();
        this.renderers = new LinkedList<>();
        this.borderPaint = new Paint();
        this.borderPaint.setColor(Color.rgb(150, 150, 150));
        this.borderPaint.setStyle(Paint.Style.STROKE);
        this.borderPaint.setStrokeWidth(1.0f);
        this.borderPaint.setAntiAlias(true);
        this.backgroundPaint = new Paint();
        this.backgroundPaint.setColor(-12303292);
        this.backgroundPaint.setStyle(Paint.Style.FILL);
        init(context, attributeSet);
    }

    public Plot(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.boxModel = new BoxModel(3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f);
        this.borderStyle = BorderStyle.SQUARE;
        this.borderRadiusX = 15.0f;
        this.borderRadiusY = 15.0f;
        this.drawBorderEnabled = true;
        this.displayDims = new DisplayDimensions();
        this.renderMode = RenderMode.USE_MAIN_THREAD;
        this.pingPong = new BufferedCanvas(this, null);
        this.renderSynch = new Object();
        this.keepRunning = false;
        this.isIdle = true;
        this.listeners = new ArrayList<>();
        this.seriesRegistry = new LinkedHashMap<>();
        this.renderers = new LinkedList<>();
        this.borderPaint = new Paint();
        this.borderPaint.setColor(Color.rgb(150, 150, 150));
        this.borderPaint.setStyle(Paint.Style.STROKE);
        this.borderPaint.setStrokeWidth(1.0f);
        this.borderPaint.setAntiAlias(true);
        this.backgroundPaint = new Paint();
        this.backgroundPaint.setColor(-12303292);
        this.backgroundPaint.setStyle(Paint.Style.FILL);
        init(context, attributeSet);
    }
}
