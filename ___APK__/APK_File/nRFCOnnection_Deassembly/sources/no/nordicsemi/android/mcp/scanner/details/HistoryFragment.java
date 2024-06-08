package no.nordicsemi.android.mcp.scanner.details;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.OverScroller;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.g;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.XYPlot;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.model.AdvDataWithStats;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.ble.model.PacketData;
import no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat;
import no.nordicsemi.android.mcp.scanner.details.HistoryFragment;
import no.nordicsemi.android.mcp.scanner.graph.ParcelableXYSeries;
import no.nordicsemi.android.mcp.util.ParcelableTimespan;

/* loaded from: classes.dex */
public class HistoryFragment extends Fragment implements View.OnTouchListener {
    private static final String DEVICE_INDEX = "device_index";
    private static final float GRAPH_SPAN = 10000.0f;
    private static final String SCANNER_TIMESPANS = "scanner_timespans";
    private static final String SIS_OFFSET = "offset";
    private int[] mColors;
    private DateFormat mDateFormat;
    private Device mDevice;
    private FlingAnimation mFlingAnimation;
    private HorizontalScrollListener mHorizontalScrollListener;
    private float mLastX;
    private int mMaxFlingVelocity;
    private int mMaxValue;
    private XYPlot mPlot;
    private List<ParcelableTimespan> mScannerTimespans;
    private float mScrollOffset;
    private ParcelableXYSeries mSelectionIndicator;
    private VelocityTracker mVelocityTracker;

    /* loaded from: classes.dex */
    private class FlingAnimation implements Runnable {
        private OverScroller mScroller;

        /* JADX INFO: Access modifiers changed from: private */
        public void startOverfling(int i) {
            this.mScroller.fling((int) HistoryFragment.this.mScrollOffset, 0, i, 0, 10000, HistoryFragment.this.mMaxValue, 0, 0, 0, 0);
            HistoryFragment.this.mPlot.postOnAnimation(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void stopOverfling() {
            this.mScroller.forceFinished(true);
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean computeScrollOffset = this.mScroller.computeScrollOffset();
            HistoryFragment.this.mScrollOffset = r0.getCurrX();
            HistoryFragment.this.mPlot.setDomainBoundaries(Float.valueOf(HistoryFragment.this.mScrollOffset), Float.valueOf(HistoryFragment.this.mScrollOffset - HistoryFragment.GRAPH_SPAN), BoundaryMode.FIXED);
            HistoryFragment.this.mPlot.redraw();
            if (computeScrollOffset) {
                HistoryFragment.this.mPlot.postOnAnimation(this);
            }
        }

        private FlingAnimation() {
            this.mScroller = new OverScroller(HistoryFragment.this.getActivity());
            this.mScroller.setFriction(0.025f);
        }
    }

    /* loaded from: classes.dex */
    public class PacketsAdapter extends RecyclerView.g<ViewHolder> {
        private View.OnTouchListener mPacketSelectionListener = new View.OnTouchListener() { // from class: no.nordicsemi.android.mcp.scanner.details.HistoryFragment.PacketsAdapter.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action != 0) {
                    if ((action != 1 && action != 3) || HistoryFragment.this.mSelectionIndicator == null) {
                        return false;
                    }
                    HistoryFragment.this.mPlot.removeSeries(HistoryFragment.this.mSelectionIndicator);
                    HistoryFragment.this.mPlot.redraw();
                    HistoryFragment.this.mSelectionIndicator = null;
                    return false;
                }
                if (HistoryFragment.this.mSelectionIndicator != null) {
                    return false;
                }
                int intValue = ((Integer) view.getTag()).intValue();
                HistoryFragment.this.mSelectionIndicator = new ParcelableXYSeries("Selection");
                HistoryFragment.this.mSelectionIndicator.addLast(Integer.valueOf(intValue), Float.valueOf(-100.0f));
                HistoryFragment.this.mSelectionIndicator.addLast(Integer.valueOf(intValue - 1), Float.valueOf(0.0f));
                HistoryFragment.this.mPlot.addSeries(HistoryFragment.this.mSelectionIndicator, new LineAndPointFormatter(-16744265, null, null, null));
                float f2 = intValue;
                if (HistoryFragment.this.mScrollOffset < f2 || HistoryFragment.this.mScrollOffset - HistoryFragment.GRAPH_SPAN > f2) {
                    if (HistoryFragment.this.mFlingAnimation != null) {
                        HistoryFragment.this.mFlingAnimation.stopOverfling();
                    }
                    HistoryFragment.this.mScrollOffset = Math.max(HistoryFragment.GRAPH_SPAN, Math.min(intValue + 5000, r6.mMaxValue));
                    HistoryFragment.this.mPlot.setDomainBoundaries(Float.valueOf(HistoryFragment.this.mScrollOffset), Float.valueOf(HistoryFragment.this.mScrollOffset - HistoryFragment.GRAPH_SPAN), BoundaryMode.FIXED);
                }
                HistoryFragment.this.mPlot.redraw();
                return false;
            }
        };
        private View.OnClickListener mEmptyOnClickListener = new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.scanner.details.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HistoryFragment.PacketsAdapter.a(view);
            }
        };

        /* loaded from: classes.dex */
        public class ViewHolder extends RecyclerView.d0 {
            private TextView mAvgIntervalView;
            private TextView mDataView;
            private ImageView mIconView;
            private ViewGroup mPacketsContainer;

            public ViewHolder(View view) {
                super(view);
                this.mIconView = (ImageView) view.findViewById(R.id.bar);
                this.mDataView = (TextView) view.findViewById(R.id.data);
                this.mAvgIntervalView = (TextView) view.findViewById(R.id.adv_interval);
                this.mPacketsContainer = (ViewGroup) view.findViewById(R.id.packets_container);
            }
        }

        public PacketsAdapter() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void a(View view) {
        }

        private void setIntervalText(TextView textView, PacketData packetData, PacketData packetData2) {
            if (!packetData2.last) {
                long j = (packetData.timestampNanos - packetData2.timestampNanos) / 1000000;
                if (j > 86400000) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(j);
                    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
                    int i = (int) (j / 86400000);
                    textView.setText(HistoryFragment.this.getResources().getQuantityString(R.plurals.days_time, i, Integer.valueOf(i), calendar));
                } else {
                    textView.setText(HistoryFragment.this.getString(R.string.interval, Long.valueOf(j)));
                    textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_signal_interval_vertical, 0, 0, 0);
                }
            } else {
                textView.setText(R.string.interval_unknown);
                textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_signal_break_vertical, 0, 0, 0);
            }
            textView.setVisibility(0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            return HistoryFragment.this.mDevice.getPacketsHistory().size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            int i2;
            List<PacketData> list;
            long j;
            List<AdvDataWithStats> packetsHistory = HistoryFragment.this.mDevice.getPacketsHistory();
            List<PacketData> packetsMetaData = HistoryFragment.this.mDevice.getPacketsMetaData();
            AdvDataWithStats advDataWithStats = packetsHistory.get(i);
            List<DataUnion> allInfo = advDataWithStats.getAllInfo();
            List<DataUnion> allInfo2 = packetsHistory.get(0).getAllInfo();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            int i3 = 2;
            if (Boolean.TRUE.equals(advDataWithStats.isConnectible())) {
                SpannableString spannableString = new SpannableString(HistoryFragment.this.getString(R.string.key_value, "Connectable", "Yes"));
                spannableString.setSpan(new ForegroundColorSpan(-7829368), 0, 12, 0);
                spannableStringBuilder.append((CharSequence) spannableString);
            }
            int i4 = 1;
            while (i4 < allInfo.size()) {
                DataUnion.Data data = allInfo.get(i4).getData(allInfo2.size() == allInfo.size() ? allInfo2.get(i4).getSelectedIndex() : 0);
                if (i4 > 1 || Boolean.TRUE.equals(advDataWithStats.isConnectible())) {
                    spannableStringBuilder.append((CharSequence) "\n");
                }
                HistoryFragment historyFragment = HistoryFragment.this;
                Object[] objArr = new Object[i3];
                objArr[0] = data.key;
                objArr[1] = data.value;
                SpannableString spannableString2 = new SpannableString(historyFragment.getString(R.string.key_value, objArr));
                spannableString2.setSpan(new ForegroundColorSpan(-7829368), 0, data.key.length() + 1, 0);
                spannableStringBuilder.append((CharSequence) spannableString2);
                i4++;
                i3 = 2;
            }
            if (allInfo.size() <= 2) {
                spannableStringBuilder.append((CharSequence) "\n").append((CharSequence) HistoryFragment.this.getString(R.string.empty_packet));
            }
            viewHolder.mDataView.setText(spannableStringBuilder);
            viewHolder.mIconView.setImageLevel(i % HistoryFragment.this.mColors.length);
            int i5 = 0;
            for (int i6 = 0; i6 < i; i6++) {
                i5 += packetsHistory.get(i6).getCount();
            }
            for (int i7 = 0; i7 < viewHolder.mPacketsContainer.getChildCount(); i7++) {
                viewHolder.mPacketsContainer.getChildAt(i7).setVisibility(8);
            }
            int count = advDataWithStats.getCount();
            long currentTimeMillis = System.currentTimeMillis();
            long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            String format = HistoryFragment.this.mDateFormat.format(Calendar.getInstance().getTime());
            int i8 = i5;
            int i9 = 0;
            while (true) {
                int i10 = i5 + count;
                if (i8 >= i10) {
                    return;
                }
                if (i9 == viewHolder.mPacketsContainer.getChildCount() - 1) {
                    i8 = i10 - 1;
                }
                PacketData packetData = packetsMetaData.get(i8);
                ViewGroup viewGroup = (ViewGroup) viewHolder.mPacketsContainer.getChildAt(i9);
                int i11 = i5;
                AdvDataWithStats advDataWithStats2 = advDataWithStats;
                ViewGroup viewGroup2 = (ViewGroup) viewGroup.getChildAt(0);
                ImageView imageView = (ImageView) viewGroup2.getChildAt(0);
                TextView textView = (TextView) viewGroup2.getChildAt(1);
                int i12 = i8;
                TextView textView2 = (TextView) viewGroup2.getChildAt(2);
                TextView textView3 = (TextView) viewGroup.getChildAt(1);
                String str = format;
                viewGroup.setVisibility(0);
                if (i9 == viewHolder.mPacketsContainer.getChildCount() - 2 && i9 < count - 2) {
                    i2 = count;
                    textView.setText(HistoryFragment.this.getString(R.string.more_packets_available, Integer.valueOf((count - i9) - 1)));
                    imageView.setImageResource(R.drawable.ic_packet_indicator_more);
                    viewGroup2.setOnClickListener(null);
                    viewGroup2.setOnTouchListener(null);
                    viewGroup2.setClickable(false);
                    setIntervalText(textView3, packetsMetaData.get(i10 - 2), packetsMetaData.get(i10 - 1));
                    list = packetsMetaData;
                    j = currentTimeMillis;
                } else {
                    i2 = count;
                    viewGroup2.setOnClickListener(this.mEmptyOnClickListener);
                    viewGroup2.setOnTouchListener(this.mPacketSelectionListener);
                    list = packetsMetaData;
                    viewGroup2.setTag(Integer.valueOf((int) ((packetsMetaData.get(0).timestampNanos - packetData.timestampNanos) / 1000000)));
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(currentTimeMillis - ((elapsedRealtimeNanos - packetData.timestampNanos) / 1000000));
                    String format2 = HistoryFragment.this.mDateFormat.format(calendar.getTime());
                    if (str.equals(format2)) {
                        str = str;
                        textView.setText(HistoryFragment.this.getString(R.string.time, calendar));
                        j = currentTimeMillis;
                    } else {
                        str = str;
                        j = currentTimeMillis;
                        textView.setText(HistoryFragment.this.getString(R.string.date_time, format2, calendar));
                    }
                    imageView.setImageResource(R.drawable.ic_packet_indicator);
                    try {
                        setIntervalText(textView3, packetData, list.get(i12 + 1));
                    } catch (IndexOutOfBoundsException unused) {
                        textView3.setVisibility(8);
                    }
                    textView2.setText(HistoryFragment.this.getString(R.string.rssi, Integer.valueOf(packetData.rssi)));
                }
                imageView.setImageLevel(i % HistoryFragment.this.mColors.length);
                i9++;
                int i13 = i2;
                if (i9 == i13) {
                    if (i13 == 1) {
                        viewHolder.mAvgIntervalView.setText(R.string.not_available);
                        return;
                    }
                    return;
                }
                long intervalNanos = advDataWithStats2.getIntervalNanos() / 1000000;
                if (i13 <= 1 || intervalNanos <= 0) {
                    viewHolder.mAvgIntervalView.setText(R.string.not_available);
                } else {
                    viewHolder.mAvgIntervalView.setText(HistoryFragment.this.getString(R.string.interval, Long.valueOf(intervalNanos)));
                }
                i8 = i12 + 1;
                count = i13;
                packetsMetaData = list;
                i5 = i11;
                advDataWithStats = advDataWithStats2;
                format = str;
                currentTimeMillis = j;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.device_details_history_item, viewGroup, false));
        }
    }

    private void configureLists(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(android.R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.addItemDecoration(new g(view.getContext(), 1));
        recyclerView.setAdapter(new PacketsAdapter());
    }

    private void configurePlots(View view) {
        Device device = this.mDevice;
        XYPlot xYPlot = (XYPlot) view.findViewById(R.id.plot);
        this.mPlot = xYPlot;
        xYPlot.setDomainBoundaries(Float.valueOf(this.mScrollOffset), Float.valueOf(this.mScrollOffset - GRAPH_SPAN), BoundaryMode.FIXED);
        xYPlot.setDomainStepValue(11.0d);
        xYPlot.setDomainValueFormat(new DecimalFormat("0"));
        xYPlot.setRangeStepValue(6.0d);
        xYPlot.getGraphWidget().setDomainScale(0.001f);
        xYPlot.getLegendWidget().setVisible(false);
        xYPlot.setOnTouchListener(this);
        xYPlot.setRangeValueFormat(new DecimalFormat("0"));
        Float valueOf = Float.valueOf(-100.0f);
        Float valueOf2 = Float.valueOf(0.0f);
        xYPlot.setRangeBoundaries(valueOf, valueOf2, BoundaryMode.FIXED);
        List<PacketData> packetsMetaData = device.getPacketsMetaData();
        long j = packetsMetaData.get(0).timestampNanos / 1000000;
        int i = 0;
        int i2 = 0;
        for (AdvDataWithStats advDataWithStats : device.getPacketsHistory()) {
            ParcelableXYSeries parcelableXYSeries = new ParcelableXYSeries("");
            int i3 = i;
            while (i3 < i + advDataWithStats.getCount()) {
                List<PacketData> list = packetsMetaData;
                int i4 = (int) (j - (packetsMetaData.get(i3).timestampNanos / 1000000));
                this.mMaxValue = i4;
                parcelableXYSeries.addLast(Integer.valueOf(i4), Float.valueOf(Math.max(r15.rssi, -100.0f)));
                i3++;
                packetsMetaData = list;
            }
            xYPlot.addSeries(parcelableXYSeries, new LineAndPointFormatter(null, Integer.valueOf(this.mColors[i2]), null, null));
            i2 = (i2 + 1) % this.mColors.length;
            i += advDataWithStats.getCount();
            packetsMetaData = packetsMetaData;
        }
        ParcelableXYSeries parcelableXYSeries2 = new ParcelableXYSeries("");
        long j2 = 2147483647L;
        for (ParcelableTimespan parcelableTimespan : this.mScannerTimespans) {
            parcelableXYSeries2.addLast(Integer.valueOf((int) j2), valueOf2);
            parcelableXYSeries2.addLast(Integer.valueOf((int) ((j - parcelableTimespan.start) - 10)), valueOf2);
            parcelableXYSeries2.addLast(Integer.valueOf((int) ((j - parcelableTimespan.start) - 11)), null);
            j2 = j - parcelableTimespan.end;
        }
        xYPlot.addSeries(parcelableXYSeries2, new LineAndPointFormatter(0, null, 2130706432, null));
    }

    public static HistoryFragment getInstance(Device device, ArrayList<ParcelableTimespan> arrayList) {
        HistoryFragment historyFragment = new HistoryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("device_index", device.getDeviceIndex());
        bundle.putParcelableArrayList(SCANNER_TIMESPANS, arrayList);
        historyFragment.setArguments(bundle);
        return historyFragment;
    }

    private boolean scroll(float f2) {
        float width = f2 * (GRAPH_SPAN / this.mPlot.getWidth());
        if (this.mScrollOffset == GRAPH_SPAN && width < 0.0f) {
            return false;
        }
        if (this.mScrollOffset == this.mMaxValue && width > 0.0f) {
            return false;
        }
        this.mScrollOffset += width;
        float f3 = this.mScrollOffset;
        if (f3 < GRAPH_SPAN) {
            this.mScrollOffset = GRAPH_SPAN;
            return true;
        }
        int i = this.mMaxValue;
        if (f3 <= i) {
            return true;
        }
        this.mScrollOffset = i;
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HorizontalScrollListener) {
            this.mHorizontalScrollListener = (HorizontalScrollListener) context;
            return;
        }
        throw new UnsupportedOperationException("Parent activity must derive from HorizontalScrollListener");
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mDevice = BluetoothLeScannerCompat.getDevices(arguments.getInt("device_index"));
        this.mScannerTimespans = arguments.getParcelableArrayList(SCANNER_TIMESPANS);
        this.mColors = getResources().getIntArray(R.array.hover_colors);
        this.mDateFormat = android.text.format.DateFormat.getDateFormat(getActivity());
        this.mScrollOffset = GRAPH_SPAN;
        if (bundle != null) {
            this.mScrollOffset = bundle.getFloat(SIS_OFFSET);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.device_details_history, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.mVelocityTracker = null;
        this.mFlingAnimation = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mHorizontalScrollListener = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putFloat(SIS_OFFSET, this.mScrollOffset);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001c, code lost:
    
        if (r5 != 3) goto L33;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouch(android.view.View r5, android.view.MotionEvent r6) {
        /*
            r4 = this;
            int r5 = r4.mMaxValue
            float r5 = (float) r5
            r0 = 1176256512(0x461c4000, float:10000.0)
            int r5 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r5 >= 0) goto Lc
            r5 = 0
            return r5
        Lc:
            int r5 = r6.getAction()
            r5 = r5 & 255(0xff, float:3.57E-43)
            r1 = 1
            if (r5 == 0) goto L80
            r2 = 0
            if (r5 == r1) goto L57
            r3 = 2
            if (r5 == r3) goto L20
            r6 = 3
            if (r5 == r6) goto L78
            goto La0
        L20:
            android.view.VelocityTracker r5 = r4.mVelocityTracker
            r5.addMovement(r6)
            float r5 = r4.mLastX
            float r6 = r6.getX()
            r4.mLastX = r6
            float r6 = r4.mLastX
            float r6 = r6 - r5
            boolean r5 = r4.scroll(r6)
            if (r5 == 0) goto La0
            com.androidplot.xy.XYPlot r5 = r4.mPlot
            float r6 = r4.mScrollOffset
            java.lang.Float r6 = java.lang.Float.valueOf(r6)
            float r2 = r4.mScrollOffset
            float r2 = r2 - r0
            java.lang.Float r0 = java.lang.Float.valueOf(r2)
            com.androidplot.xy.BoundaryMode r2 = com.androidplot.xy.BoundaryMode.FIXED
            r5.setDomainBoundaries(r6, r0, r2)
            com.androidplot.xy.XYPlot r5 = r4.mPlot
            r5.redraw()
            no.nordicsemi.android.mcp.scanner.details.HorizontalScrollListener r5 = r4.mHorizontalScrollListener
            if (r5 == 0) goto La0
            r5.onScroll()
            goto La0
        L57:
            android.view.VelocityTracker r5 = r4.mVelocityTracker
            r6 = 10000(0x2710, float:1.4013E-41)
            int r0 = r4.mMaxFlingVelocity
            float r0 = (float) r0
            r5.computeCurrentVelocity(r6, r0)
            no.nordicsemi.android.mcp.scanner.details.HistoryFragment$FlingAnimation r5 = r4.mFlingAnimation
            if (r5 != 0) goto L6c
            no.nordicsemi.android.mcp.scanner.details.HistoryFragment$FlingAnimation r5 = new no.nordicsemi.android.mcp.scanner.details.HistoryFragment$FlingAnimation
            r5.<init>()
            r4.mFlingAnimation = r5
        L6c:
            no.nordicsemi.android.mcp.scanner.details.HistoryFragment$FlingAnimation r5 = r4.mFlingAnimation
            android.view.VelocityTracker r6 = r4.mVelocityTracker
            float r6 = r6.getXVelocity()
            int r6 = (int) r6
            no.nordicsemi.android.mcp.scanner.details.HistoryFragment.FlingAnimation.access$200(r5, r6)
        L78:
            android.view.VelocityTracker r5 = r4.mVelocityTracker
            r5.recycle()
            r4.mVelocityTracker = r2
            goto La0
        L80:
            android.view.VelocityTracker r5 = r4.mVelocityTracker
            if (r5 != 0) goto L8b
            android.view.VelocityTracker r5 = android.view.VelocityTracker.obtain()
            r4.mVelocityTracker = r5
            goto L8e
        L8b:
            r5.clear()
        L8e:
            no.nordicsemi.android.mcp.scanner.details.HistoryFragment$FlingAnimation r5 = r4.mFlingAnimation
            if (r5 == 0) goto L95
            no.nordicsemi.android.mcp.scanner.details.HistoryFragment.FlingAnimation.access$000(r5)
        L95:
            android.view.VelocityTracker r5 = r4.mVelocityTracker
            r5.addMovement(r6)
            float r5 = r6.getX()
            r4.mLastX = r5
        La0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.scanner.details.HistoryFragment.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        this.mMaxFlingVelocity = ViewConfiguration.get(getActivity()).getScaledMaximumFlingVelocity();
        configurePlots(view);
        configureLists(view);
    }
}
