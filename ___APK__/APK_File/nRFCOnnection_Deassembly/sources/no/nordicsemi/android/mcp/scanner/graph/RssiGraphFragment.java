package no.nordicsemi.android.mcp.scanner.graph;

import a.f.d.b;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.OverScroller;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.t;
import com.androidplot.ui.AnchorPosition;
import com.androidplot.ui.DynamicTableModel;
import com.androidplot.ui.SizeLayoutType;
import com.androidplot.ui.SizeMetrics;
import com.androidplot.ui.XLayoutStyle;
import com.androidplot.ui.YLayoutStyle;
import com.androidplot.util.PixelUtils;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.LineAndPointRenderer;
import com.androidplot.xy.XYLegendWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import java.security.InvalidParameterException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat;
import no.nordicsemi.android.mcp.scanner.DeviceListFragment2;

/* loaded from: classes.dex */
public class RssiGraphFragment extends Fragment implements DeviceListFragment2.OnDeviceListChangedListener, BluetoothLeScannerCompat.DeviceListener, BluetoothLeScannerCompat.ScannerListener, View.OnTouchListener {
    private static final float GRAPH_SPAN = 90.0f;
    private static final int LEGEND_ITEM_HEIGHT_IN_DP = 18;
    private static final int LEGEND_WIDTH_IN_DP = 130;
    private static final Object LOCK = new Object();
    private static final int REFRESH_RATE = 2;
    private static final int RSSI_AVG_HISTORY_SIZE = 10;
    private static final long RSSI_AVG_TIMEOUT = 4000;
    private static final int RSSI_HISTORY_SIZE = 20;
    private static final String SIS_OFFSET = "OFFSET";
    private static final String SIS_SHOW_LEGEND = "SHOW_LEGEND";
    private static final String TAG = "RssiGraphFragment";
    private int[] mColors;
    private DataFragment mDataFragment;
    private ArrayList<String> mFilteredDevices;
    private FlingAnimation mFlingAnimation;
    private SparseArray<LineAndPointFormatter> mFormatters;
    private Handler mHandler;
    private float mLastX;
    private int mMaxFlingVelocity;
    private boolean mPanelOpened;
    private XYPlot mPlot;
    private ScheduledFuture<?> mScanningProcess;
    private float mScrollOffset;
    private boolean mShowLegend;
    private boolean mStarted;
    private VelocityTracker mVelocityTracker;
    private final ScheduledExecutorService mExecutor = Executors.newSingleThreadScheduledExecutor();
    private Runnable refreshTask = new Runnable() { // from class: no.nordicsemi.android.mcp.scanner.graph.RssiGraphFragment.1
        /* JADX WARN: Code restructure failed: missing block: B:39:0x00a2, code lost:
        
            if (r1.removeSeries(r10, com.androidplot.xy.LineAndPointRenderer.class) != false) goto L33;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 292
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.scanner.graph.RssiGraphFragment.AnonymousClass1.run():void");
        }
    };

    /* loaded from: classes.dex */
    public static class DataFragment extends Fragment {
        private final ArrayList<ParcelableXSeries> mSeries = new ArrayList<>();
        private final ArrayList<RssiAccumulator> mRssiAccumulators = new ArrayList<>();

        @Override // androidx.fragment.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            setRetainInstance(true);
        }
    }

    /* loaded from: classes.dex */
    private class FlingAnimation implements Runnable {
        private OverScroller mScroller;

        /* JADX INFO: Access modifiers changed from: private */
        public void startOverfling(int i) {
            this.mScroller.fling((int) RssiGraphFragment.this.mScrollOffset, 0, i, 0, 90, ((ParcelableXSeries) RssiGraphFragment.this.mDataFragment.mSeries.get(0)).size(), 0, 0, 0, 0);
            RssiGraphFragment.this.mPlot.postOnAnimation(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void stopOverfling() {
            this.mScroller.abortAnimation();
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean computeScrollOffset = this.mScroller.computeScrollOffset();
            RssiGraphFragment.this.mScrollOffset = r0.getCurrX();
            if (RssiGraphFragment.this.mPlot != null) {
                RssiGraphFragment.this.mPlot.setDomainBoundaries(Float.valueOf(RssiGraphFragment.this.mScrollOffset), Float.valueOf(RssiGraphFragment.this.mScrollOffset - RssiGraphFragment.GRAPH_SPAN), BoundaryMode.FIXED);
                RssiGraphFragment.this.mPlot.redraw();
                if (computeScrollOffset) {
                    RssiGraphFragment.this.mPlot.postOnAnimation(this);
                }
            }
        }

        private FlingAnimation() {
            this.mScroller = new OverScroller(RssiGraphFragment.this.getActivity());
            this.mScroller.setFriction(0.05f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getColor(int i) {
        int[] iArr = this.mColors;
        return iArr[i % iArr.length];
    }

    private void refresh(boolean z) {
        if (this.mStarted) {
            return;
        }
        this.mStarted = z;
        if (this.mScanningProcess == null) {
            if (z) {
                this.mScanningProcess = this.mExecutor.scheduleAtFixedRate(this.refreshTask, 500L, 500L, TimeUnit.MILLISECONDS);
            } else {
                this.mExecutor.execute(this.refreshTask);
            }
        }
    }

    private void scroll(float f2) {
        this.mScrollOffset += f2 * (GRAPH_SPAN / this.mPlot.getWidth());
        if (this.mScrollOffset < GRAPH_SPAN) {
            this.mScrollOffset = GRAPH_SPAN;
        }
        if (this.mScrollOffset > ((ParcelableXSeries) this.mDataFragment.mSeries.get(0)).size()) {
            this.mScrollOffset = ((ParcelableXSeries) this.mDataFragment.mSeries.get(0)).size();
        }
    }

    public /* synthetic */ void a() {
        XYPlot xYPlot = this.mPlot;
        if (xYPlot != null) {
            xYPlot.redraw();
        }
    }

    public XYSeries getSeries(String str) {
        synchronized (LOCK) {
            ArrayList arrayList = this.mDataFragment.mRssiAccumulators;
            for (int i = 0; i < arrayList.size(); i++) {
                if (str.equals(((RssiAccumulator) arrayList.get(i)).getAddress())) {
                    return (XYSeries) this.mDataFragment.mSeries.get(i);
                }
            }
            throw new InvalidParameterException("No series for device with address: " + str);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mDataFragment = (DataFragment) requireActivity().getSupportFragmentManager().b("data");
        if (this.mDataFragment == null) {
            this.mDataFragment = new DataFragment();
            t b2 = requireActivity().getSupportFragmentManager().b();
            b2.a(this.mDataFragment, "data");
            b2.a();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFilteredDevices = new ArrayList<>();
        this.mHandler = new Handler();
        this.mColors = getResources().getIntArray(R.array.hover_colors);
        this.mFormatters = new SparseArray<>(this.mColors.length);
        this.mShowLegend = false;
        this.mScrollOffset = GRAPH_SPAN;
        if (bundle != null) {
            this.mShowLegend = bundle.getBoolean(SIS_SHOW_LEGEND);
            this.mScrollOffset = bundle.getFloat(SIS_OFFSET);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (requireParentFragment().isMenuVisible() && this.mPanelOpened) {
            menuInflater.inflate(R.menu.graph, menu);
            menu.findItem(R.id.action_show_legend).setChecked(this.mShowLegend);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_rssi_graph, viewGroup, false);
        XYPlot xYPlot = (XYPlot) inflate.findViewById(R.id.plot);
        this.mPlot = xYPlot;
        xYPlot.setRangeBoundaries(-100, Float.valueOf(-0.1f), BoundaryMode.FIXED);
        xYPlot.setRangeStepValue(11.0d);
        xYPlot.setRangeValueFormat(new DecimalFormat("0"));
        xYPlot.setDomainBoundaries(Float.valueOf(this.mScrollOffset), Float.valueOf(this.mScrollOffset - GRAPH_SPAN), BoundaryMode.FIXED);
        xYPlot.setDomainStepValue(10.0d);
        xYPlot.setDomainValueFormat(new DecimalFormat("0"));
        xYPlot.setOnTouchListener(this);
        xYPlot.getGraphWidget().setDomainScale(0.5f);
        setHasOptionsMenu(true);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.mHandler = null;
        this.mStarted = false;
        this.mExecutor.shutdownNow();
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.mVelocityTracker = null;
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.mPlot.clear();
        this.mPlot = null;
        super.onDestroyView();
    }

    @Override // no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat.DeviceListener
    public void onDeviceUpdated(Device device) {
        RssiAccumulator rssiAccumulator;
        String address = device.getAddress(getContext());
        synchronized (LOCK) {
            Iterator it = this.mDataFragment.mRssiAccumulators.iterator();
            while (true) {
                if (!it.hasNext()) {
                    rssiAccumulator = null;
                    break;
                } else {
                    rssiAccumulator = (RssiAccumulator) it.next();
                    if (address.equals(rssiAccumulator.getAddress())) {
                        break;
                    }
                }
            }
        }
        if (rssiAccumulator == null) {
            rssiAccumulator = new RssiAccumulator(address, 20);
            rssiAccumulator.setLength(10);
            rssiAccumulator.setTimeLength(RSSI_AVG_TIMEOUT);
            synchronized (LOCK) {
                this.mDataFragment.mRssiAccumulators.add(rssiAccumulator);
            }
        }
        rssiAccumulator.setName(device.getName() == null ? getString(R.string.not_available) : device.getName());
        rssiAccumulator.put(device.getRssi());
    }

    public void onDevicesCleared() {
        this.mScrollOffset = GRAPH_SPAN;
        synchronized (LOCK) {
            this.mDataFragment.mRssiAccumulators.clear();
            this.mDataFragment.mSeries.clear();
        }
        this.mFilteredDevices.clear();
        this.mPlot.clear();
        this.mPlot.setDomainBoundaries(Float.valueOf(this.mScrollOffset), Float.valueOf(this.mScrollOffset - GRAPH_SPAN), BoundaryMode.FIXED);
        this.mPlot.getLegendWidget().setSize(new SizeMetrics(PixelUtils.dpToPix(0.0f), SizeLayoutType.ABSOLUTE, PixelUtils.dpToPix(130.0f), SizeLayoutType.ABSOLUTE));
        this.mPlot.getLegendWidget().refreshLayout();
        this.mPlot.redraw();
    }

    @Override // no.nordicsemi.android.mcp.scanner.DeviceListFragment2.OnDeviceListChangedListener
    public void onFilteredDevicesChanged(List<Device> list) {
        ArrayList<String> arrayList = this.mFilteredDevices;
        arrayList.clear();
        Iterator<Device> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getAddress(getContext()));
        }
        refresh(false);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int i = 0;
        if (menuItem.getItemId() != R.id.action_show_legend) {
            return false;
        }
        boolean z = !this.mShowLegend;
        this.mShowLegend = z;
        menuItem.setChecked(z);
        List<XYSeries> seriesListForRenderer = this.mPlot.getSeriesListForRenderer(LineAndPointRenderer.class);
        if (seriesListForRenderer != null && this.mShowLegend) {
            i = seriesListForRenderer.size();
        }
        this.mPlot.getLegendWidget().setSize(new SizeMetrics(PixelUtils.dpToPix(i * 18), SizeLayoutType.ABSOLUTE, PixelUtils.dpToPix(130.0f), SizeLayoutType.ABSOLUTE));
        this.mPlot.getLegendWidget().refreshLayout();
        this.mPlot.redraw();
        return true;
    }

    public void onPanelClosed(View view) {
        this.mPanelOpened = false;
        requireActivity().invalidateOptionsMenu();
    }

    public void onPanelOpened(View view) {
        this.mPanelOpened = true;
        requireActivity().invalidateOptionsMenu();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mHandler.postDelayed(new Runnable() { // from class: no.nordicsemi.android.mcp.scanner.graph.a
            @Override // java.lang.Runnable
            public final void run() {
                RssiGraphFragment.this.a();
            }
        }, 10L);
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(SIS_SHOW_LEGEND, this.mShowLegend);
        bundle.putFloat(SIS_OFFSET, this.mScrollOffset);
    }

    @Override // no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat.ScannerListener
    public void onScanningStarted() {
        refresh(true);
    }

    @Override // no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat.ScannerListener
    public void onScanningStopped() {
        this.mStarted = false;
        this.mScanningProcess.cancel(false);
        this.mScanningProcess = null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0036, code lost:
    
        if (r5 != 3) goto L30;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouch(android.view.View r5, android.view.MotionEvent r6) {
        /*
            r4 = this;
            no.nordicsemi.android.mcp.scanner.graph.RssiGraphFragment$DataFragment r5 = r4.mDataFragment
            java.util.ArrayList r5 = no.nordicsemi.android.mcp.scanner.graph.RssiGraphFragment.DataFragment.access$400(r5)
            boolean r5 = r5.isEmpty()
            r0 = 0
            if (r5 != 0) goto Lb0
            no.nordicsemi.android.mcp.scanner.graph.RssiGraphFragment$DataFragment r5 = r4.mDataFragment
            java.util.ArrayList r5 = no.nordicsemi.android.mcp.scanner.graph.RssiGraphFragment.DataFragment.access$400(r5)
            java.lang.Object r5 = r5.get(r0)
            no.nordicsemi.android.mcp.scanner.graph.ParcelableXSeries r5 = (no.nordicsemi.android.mcp.scanner.graph.ParcelableXSeries) r5
            int r5 = r5.size()
            float r5 = (float) r5
            r1 = 1119092736(0x42b40000, float:90.0)
            int r5 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r5 >= 0) goto L26
            goto Lb0
        L26:
            int r5 = r6.getAction()
            r5 = r5 & 255(0xff, float:3.57E-43)
            r0 = 1
            if (r5 == 0) goto L90
            r2 = 0
            if (r5 == r0) goto L67
            r3 = 2
            if (r5 == r3) goto L3a
            r6 = 3
            if (r5 == r6) goto L88
            goto Lb0
        L3a:
            android.view.VelocityTracker r5 = r4.mVelocityTracker
            r5.addMovement(r6)
            float r5 = r4.mLastX
            float r6 = r6.getX()
            r4.mLastX = r6
            float r6 = r4.mLastX
            float r6 = r6 - r5
            r4.scroll(r6)
            com.androidplot.xy.XYPlot r5 = r4.mPlot
            float r6 = r4.mScrollOffset
            java.lang.Float r6 = java.lang.Float.valueOf(r6)
            float r2 = r4.mScrollOffset
            float r2 = r2 - r1
            java.lang.Float r1 = java.lang.Float.valueOf(r2)
            com.androidplot.xy.BoundaryMode r2 = com.androidplot.xy.BoundaryMode.FIXED
            r5.setDomainBoundaries(r6, r1, r2)
            com.androidplot.xy.XYPlot r5 = r4.mPlot
            r5.redraw()
            goto Lb0
        L67:
            android.view.VelocityTracker r5 = r4.mVelocityTracker
            r6 = 300(0x12c, float:4.2E-43)
            int r1 = r4.mMaxFlingVelocity
            float r1 = (float) r1
            r5.computeCurrentVelocity(r6, r1)
            no.nordicsemi.android.mcp.scanner.graph.RssiGraphFragment$FlingAnimation r5 = r4.mFlingAnimation
            if (r5 != 0) goto L7c
            no.nordicsemi.android.mcp.scanner.graph.RssiGraphFragment$FlingAnimation r5 = new no.nordicsemi.android.mcp.scanner.graph.RssiGraphFragment$FlingAnimation
            r5.<init>()
            r4.mFlingAnimation = r5
        L7c:
            no.nordicsemi.android.mcp.scanner.graph.RssiGraphFragment$FlingAnimation r5 = r4.mFlingAnimation
            android.view.VelocityTracker r6 = r4.mVelocityTracker
            float r6 = r6.getXVelocity()
            int r6 = (int) r6
            no.nordicsemi.android.mcp.scanner.graph.RssiGraphFragment.FlingAnimation.access$1200(r5, r6)
        L88:
            android.view.VelocityTracker r5 = r4.mVelocityTracker
            r5.recycle()
            r4.mVelocityTracker = r2
            goto Lb0
        L90:
            android.view.VelocityTracker r5 = r4.mVelocityTracker
            if (r5 != 0) goto L9b
            android.view.VelocityTracker r5 = android.view.VelocityTracker.obtain()
            r4.mVelocityTracker = r5
            goto L9e
        L9b:
            r5.clear()
        L9e:
            no.nordicsemi.android.mcp.scanner.graph.RssiGraphFragment$FlingAnimation r5 = r4.mFlingAnimation
            if (r5 == 0) goto La5
            no.nordicsemi.android.mcp.scanner.graph.RssiGraphFragment.FlingAnimation.access$1000(r5)
        La5:
            android.view.VelocityTracker r5 = r4.mVelocityTracker
            r5.addMovement(r6)
            float r5 = r6.getX()
            r4.mLastX = r5
        Lb0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.scanner.graph.RssiGraphFragment.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        this.mMaxFlingVelocity = ViewConfiguration.get(getActivity()).getScaledMaximumFlingVelocity();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        XYLegendWidget legendWidget = this.mPlot.getLegendWidget();
        legendWidget.position(PixelUtils.dpToPix(55.0f), XLayoutStyle.ABSOLUTE_FROM_LEFT, PixelUtils.dpToPix(24.0f), YLayoutStyle.ABSOLUTE_FROM_TOP, AnchorPosition.LEFT_TOP);
        legendWidget.setSize(new SizeMetrics(PixelUtils.dpToPix(this.mShowLegend ? this.mDataFragment.mSeries.size() * 18 : 0.0f), SizeLayoutType.ABSOLUTE, PixelUtils.dpToPix(130.0f), SizeLayoutType.ABSOLUTE));
        legendWidget.setTableModel(new DynamicTableModel(1, this.mDataFragment.mSeries.size()));
        Paint paint = new Paint();
        paint.setColor(b.a(requireContext(), R.color.actionBarColor));
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(30);
        legendWidget.setBackgroundPaint(paint);
        legendWidget.setPadding(8.0f, 0.0f, 1.0f, 8.0f);
        int i = 0;
        synchronized (LOCK) {
            Iterator it = this.mDataFragment.mSeries.iterator();
            while (it.hasNext()) {
                this.mPlot.addSeries((XYSeries) it.next(), new LineAndPointFormatter(Integer.valueOf(getColor(i)), null, null, null));
                i++;
            }
        }
    }
}
