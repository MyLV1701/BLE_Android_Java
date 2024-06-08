package no.nordicsemi.android.mcp.scanner.timeline;

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
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.ble.model.PacketData;
import no.nordicsemi.android.mcp.ble.scanner.BluetoothLeScannerCompat;
import no.nordicsemi.android.mcp.scanner.graph.ParcelableXYSeries;
import no.nordicsemi.android.mcp.scanner.timeline.TimelineFragment;
import no.nordicsemi.android.mcp.util.ParcelableTimespan;

/* loaded from: classes.dex */
public class TimelineFragment extends Fragment implements View.OnTouchListener {
    private static final float GRAPH_SPAN = 10000.0f;
    private static final String SIS_OFFSET = "offset";
    private int[] mColors;
    private DateFormat mDateFormat;
    private List<Device> mDevices;
    private FlingAnimation mFlingAnimation;
    private float mLastX;
    private int mMaxColors;
    private int mMaxFlingVelocity;
    private int mMaxValue;
    private XYPlot mPlot;
    private List<ParcelableTimespan> mScannerTimespans;
    private float mScrollOffset;
    private ParcelableXYSeries mSelectionIndicator;
    private TimelineDevice mTimelineDevice;
    private VelocityTracker mVelocityTracker;

    /* loaded from: classes.dex */
    private class FlingAnimation implements Runnable {
        private OverScroller mScroller;

        /* JADX INFO: Access modifiers changed from: private */
        public void startOverfling(int i) {
            this.mScroller.fling((int) TimelineFragment.this.mScrollOffset, 0, i, 0, 10000, TimelineFragment.this.mMaxValue, 0, 0, 0, 0);
            TimelineFragment.this.mPlot.postOnAnimation(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void stopOverfling() {
            this.mScroller.forceFinished(true);
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean computeScrollOffset = this.mScroller.computeScrollOffset();
            TimelineFragment.this.mScrollOffset = r0.getCurrX();
            TimelineFragment.this.mPlot.setDomainBoundaries(Float.valueOf(TimelineFragment.this.mScrollOffset), Float.valueOf(TimelineFragment.this.mScrollOffset - TimelineFragment.GRAPH_SPAN), BoundaryMode.FIXED);
            TimelineFragment.this.mPlot.redraw();
            if (computeScrollOffset) {
                TimelineFragment.this.mPlot.postOnAnimation(this);
            }
        }

        private FlingAnimation() {
            this.mScroller = new OverScroller(TimelineFragment.this.getActivity());
            this.mScroller.setFriction(0.025f);
        }
    }

    /* loaded from: classes.dex */
    public class PacketsAdapter extends RecyclerView.g<ViewHolder> {
        private View.OnTouchListener mPacketSelectionListener = new View.OnTouchListener() { // from class: no.nordicsemi.android.mcp.scanner.timeline.TimelineFragment.PacketsAdapter.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action != 0) {
                    if ((action != 1 && action != 3) || TimelineFragment.this.mSelectionIndicator == null) {
                        return false;
                    }
                    TimelineFragment.this.mPlot.removeSeries(TimelineFragment.this.mSelectionIndicator);
                    TimelineFragment.this.mPlot.redraw();
                    TimelineFragment.this.mSelectionIndicator = null;
                    return false;
                }
                if (TimelineFragment.this.mSelectionIndicator != null) {
                    return false;
                }
                int intValue = ((Integer) view.getTag()).intValue();
                TimelineFragment.this.mSelectionIndicator = new ParcelableXYSeries("Selection");
                TimelineFragment.this.mSelectionIndicator.addLast(Integer.valueOf(intValue), Float.valueOf(-100.0f));
                TimelineFragment.this.mSelectionIndicator.addLast(Integer.valueOf(intValue - 1), Float.valueOf(0.0f));
                TimelineFragment.this.mPlot.addSeries(TimelineFragment.this.mSelectionIndicator, new LineAndPointFormatter(-16744265, null, null, null));
                float f2 = intValue;
                if (TimelineFragment.this.mScrollOffset < f2 || TimelineFragment.this.mScrollOffset - TimelineFragment.GRAPH_SPAN > f2) {
                    if (TimelineFragment.this.mFlingAnimation != null) {
                        TimelineFragment.this.mFlingAnimation.stopOverfling();
                    }
                    TimelineFragment.this.mScrollOffset = Math.max(TimelineFragment.GRAPH_SPAN, Math.min(intValue + 5000, r6.mMaxValue));
                    TimelineFragment.this.mPlot.setDomainBoundaries(Float.valueOf(TimelineFragment.this.mScrollOffset), Float.valueOf(TimelineFragment.this.mScrollOffset - TimelineFragment.GRAPH_SPAN), BoundaryMode.FIXED);
                }
                TimelineFragment.this.mPlot.redraw();
                return false;
            }
        };
        private View.OnClickListener mEmptyOnClickListener = new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.scanner.timeline.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TimelineFragment.PacketsAdapter.a(view);
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

        private boolean didScanningStopInBetween(PacketData packetData, PacketData packetData2) {
            for (ParcelableTimespan parcelableTimespan : TimelineFragment.this.mScannerTimespans) {
                if (parcelableTimespan.start < packetData2.timestampNanos / 1000000 && parcelableTimespan.end > packetData.timestampNanos / 1000000) {
                    return false;
                }
            }
            return true;
        }

        private void setIntervalText(TextView textView, PacketData packetData, PacketData packetData2) {
            if (packetData2.last && didScanningStopInBetween(packetData, packetData2)) {
                textView.setText(R.string.interval_unknown);
                textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_signal_break_vertical, 0, 0, 0);
            } else {
                long j = (packetData.timestampNanos - packetData2.timestampNanos) / 1000000;
                if (j > 86400000) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(j);
                    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
                    int i = (int) (j / 86400000);
                    textView.setText(TimelineFragment.this.getResources().getQuantityString(R.plurals.days_time, i, Integer.valueOf(i), calendar));
                } else {
                    textView.setText(TimelineFragment.this.getString(R.string.interval, Long.valueOf(j)));
                    textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_signal_interval_vertical, 0, 0, 0);
                }
            }
            textView.setVisibility(0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            return TimelineFragment.this.mTimelineDevice.getPacketsHistory().size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            MarkedAdvDataWithStats markedAdvDataWithStats;
            int i2;
            int i3;
            long j;
            List<MarkedAdvDataWithStats> packetsHistory = TimelineFragment.this.mTimelineDevice.getPacketsHistory();
            List<PacketData> packetsMetaData = TimelineFragment.this.mTimelineDevice.getPacketsMetaData();
            MarkedAdvDataWithStats markedAdvDataWithStats2 = packetsHistory.get(i);
            List<DataUnion> allInfo = markedAdvDataWithStats2.getAllInfo();
            int i4 = 0;
            List<DataUnion> allInfo2 = packetsHistory.get(0).getAllInfo();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            int i5 = 2;
            if (Boolean.TRUE.equals(markedAdvDataWithStats2.isConnectible())) {
                SpannableString spannableString = new SpannableString(TimelineFragment.this.getString(R.string.key_value, "Connectable", "Yes"));
                spannableString.setSpan(new ForegroundColorSpan(-7829368), 0, 12, 0);
                spannableStringBuilder.append((CharSequence) spannableString);
            }
            int i6 = 1;
            while (i6 < allInfo.size()) {
                DataUnion.Data data = allInfo.get(i6).getData(allInfo2.size() == allInfo.size() ? allInfo2.get(i6).getSelectedIndex() : 0);
                if (i6 > 1 || Boolean.TRUE.equals(markedAdvDataWithStats2.isConnectible())) {
                    spannableStringBuilder.append((CharSequence) "\n");
                }
                TimelineFragment timelineFragment = TimelineFragment.this;
                Object[] objArr = new Object[i5];
                objArr[0] = data.key;
                objArr[1] = data.value;
                SpannableString spannableString2 = new SpannableString(timelineFragment.getString(R.string.key_value, objArr));
                spannableString2.setSpan(new ForegroundColorSpan(-7829368), 0, data.key.length() + 1, 0);
                spannableStringBuilder.append((CharSequence) spannableString2);
                i6++;
                i5 = 2;
            }
            if (allInfo.size() <= 2) {
                spannableStringBuilder.append((CharSequence) "\n").append((CharSequence) TimelineFragment.this.getString(R.string.empty_packet));
            }
            viewHolder.mDataView.setText(spannableStringBuilder);
            viewHolder.mIconView.setImageLevel(markedAdvDataWithStats2.getDeviceIndex() % TimelineFragment.this.mMaxColors);
            int i7 = 0;
            for (int i8 = 0; i8 < i; i8++) {
                i7 += packetsHistory.get(i8).getCount();
            }
            for (int i9 = 0; i9 < viewHolder.mPacketsContainer.getChildCount(); i9++) {
                viewHolder.mPacketsContainer.getChildAt(i9).setVisibility(8);
            }
            int count = markedAdvDataWithStats2.getCount();
            long currentTimeMillis = System.currentTimeMillis();
            long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            String format = TimelineFragment.this.mDateFormat.format(Calendar.getInstance().getTime());
            int i10 = i7;
            int i11 = 0;
            while (true) {
                int i12 = i7 + count;
                if (i10 >= i12) {
                    markedAdvDataWithStats = markedAdvDataWithStats2;
                    break;
                }
                if (i11 == viewHolder.mPacketsContainer.getChildCount() - 1) {
                    i10 = i12 - 1;
                }
                PacketData packetData = packetsMetaData.get(i10);
                ViewGroup viewGroup = (ViewGroup) viewHolder.mPacketsContainer.getChildAt(i11);
                int i13 = i7;
                ViewGroup viewGroup2 = (ViewGroup) viewGroup.getChildAt(i4);
                ImageView imageView = (ImageView) viewGroup2.getChildAt(i4);
                markedAdvDataWithStats = markedAdvDataWithStats2;
                TextView textView = (TextView) viewGroup2.getChildAt(1);
                int i14 = i10;
                TextView textView2 = (TextView) viewGroup2.getChildAt(2);
                TextView textView3 = (TextView) viewGroup.getChildAt(1);
                String str = format;
                viewGroup.setVisibility(0);
                if (i11 == viewHolder.mPacketsContainer.getChildCount() - 2 && i11 < count - 2) {
                    i2 = count;
                    textView.setText(TimelineFragment.this.getString(R.string.more_packets_available, Integer.valueOf((count - i11) - 1)));
                    imageView.setImageResource(R.drawable.ic_packet_indicator_more);
                    viewGroup2.setOnClickListener(null);
                    viewGroup2.setOnTouchListener(null);
                    viewGroup2.setClickable(false);
                    setIntervalText(textView3, packetsMetaData.get(i12 - 2), packetsMetaData.get(i12 - 1));
                    j = currentTimeMillis;
                    i3 = i11;
                } else {
                    i2 = count;
                    viewGroup2.setOnClickListener(this.mEmptyOnClickListener);
                    viewGroup2.setOnTouchListener(this.mPacketSelectionListener);
                    i3 = i11;
                    viewGroup2.setTag(Integer.valueOf((int) ((packetsMetaData.get(0).timestampNanos - packetData.timestampNanos) / 1000000)));
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(currentTimeMillis - ((elapsedRealtimeNanos - packetData.timestampNanos) / 1000000));
                    String format2 = TimelineFragment.this.mDateFormat.format(calendar.getTime());
                    if (str.equals(format2)) {
                        str = str;
                        textView.setText(TimelineFragment.this.getString(R.string.time, calendar));
                        j = currentTimeMillis;
                    } else {
                        str = str;
                        j = currentTimeMillis;
                        textView.setText(TimelineFragment.this.getString(R.string.date_time, format2, calendar));
                    }
                    imageView.setImageResource(R.drawable.ic_packet_indicator);
                    try {
                        setIntervalText(textView3, packetData, packetsMetaData.get(i14 + 1));
                    } catch (IndexOutOfBoundsException unused) {
                        textView3.setVisibility(8);
                    }
                    textView2.setText(TimelineFragment.this.getString(R.string.rssi, Integer.valueOf(packetData.rssi)));
                }
                imageView.setImageLevel(markedAdvDataWithStats.getDeviceIndex() % TimelineFragment.this.mMaxColors);
                i11 = i3 + 1;
                int i15 = i2;
                if (i11 == i15) {
                    break;
                }
                i10 = i14 + 1;
                count = i15;
                markedAdvDataWithStats2 = markedAdvDataWithStats;
                i7 = i13;
                format = str;
                currentTimeMillis = j;
                i4 = 0;
            }
            long intervalNanos = markedAdvDataWithStats.getIntervalNanos() / 1000000;
            if (intervalNanos > 0) {
                viewHolder.mAvgIntervalView.setText(TimelineFragment.this.getString(R.string.interval, Long.valueOf(intervalNanos)));
            } else {
                viewHolder.mAvgIntervalView.setText(R.string.not_available);
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
        Iterator<Device> it = this.mDevices.iterator();
        long j = 0;
        while (it.hasNext()) {
            List<PacketData> packetsMetaData = it.next().getPacketsMetaData();
            j = Math.max(packetsMetaData.get(0).timestampNanos / 1000000, j);
            this.mMaxValue = (int) Math.max(j - (packetsMetaData.get(packetsMetaData.size() - 1).timestampNanos / 1000000), this.mMaxValue);
        }
        for (Device device : this.mDevices) {
            List<PacketData> packetsMetaData2 = device.getPacketsMetaData();
            ParcelableXYSeries parcelableXYSeries = new ParcelableXYSeries("");
            Iterator<PacketData> it2 = packetsMetaData2.iterator();
            while (it2.hasNext()) {
                parcelableXYSeries.addLast(Integer.valueOf((int) (j - (it2.next().timestampNanos / 1000000))), Float.valueOf(Math.max(r13.rssi, -100.0f)));
                valueOf2 = valueOf2;
            }
            xYPlot.addSeries(parcelableXYSeries, new LineAndPointFormatter(null, Integer.valueOf(this.mColors[device.getDeviceIndex() % this.mMaxColors]), null, null));
            valueOf2 = valueOf2;
        }
        Float f2 = valueOf2;
        ParcelableXYSeries parcelableXYSeries2 = new ParcelableXYSeries("");
        long j2 = 2147483647L;
        for (ParcelableTimespan parcelableTimespan : this.mScannerTimespans) {
            parcelableXYSeries2.addLast(Integer.valueOf((int) j2), f2);
            parcelableXYSeries2.addLast(Integer.valueOf((int) ((j - parcelableTimespan.start) - 10)), f2);
            parcelableXYSeries2.addLast(Integer.valueOf((int) ((j - parcelableTimespan.start) - 11)), null);
            j2 = j - parcelableTimespan.end;
        }
        xYPlot.addSeries(parcelableXYSeries2, new LineAndPointFormatter(0, null, 2130706432, null));
    }

    public static TimelineFragment getInstance() {
        return new TimelineFragment();
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

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mMaxColors = getResources().getIntArray(R.array.hover_colors).length;
        this.mColors = getResources().getIntArray(R.array.hover_colors);
        this.mDateFormat = android.text.format.DateFormat.getDateFormat(getActivity());
        this.mScannerTimespans = requireActivity().getIntent().getParcelableArrayListExtra("timespans");
        int[] intArrayExtra = requireActivity().getIntent().getIntArrayExtra(TimelineDialogActivity.DEVICES_INDEXES);
        this.mDevices = new ArrayList(intArrayExtra.length);
        for (int i : intArrayExtra) {
            this.mDevices.add(BluetoothLeScannerCompat.getDevices(i));
        }
        this.mTimelineDevice = new TimelineDevice(this.mDevices);
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
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putFloat(SIS_OFFSET, this.mScrollOffset);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001c, code lost:
    
        if (r5 != 3) goto L31;
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
            if (r5 == 0) goto L79
            r2 = 0
            if (r5 == r1) goto L50
            r3 = 2
            if (r5 == r3) goto L20
            r6 = 3
            if (r5 == r6) goto L71
            goto L99
        L20:
            android.view.VelocityTracker r5 = r4.mVelocityTracker
            r5.addMovement(r6)
            float r5 = r4.mLastX
            float r6 = r6.getX()
            r4.mLastX = r6
            float r6 = r4.mLastX
            float r6 = r6 - r5
            boolean r5 = r4.scroll(r6)
            if (r5 == 0) goto L99
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
            goto L99
        L50:
            android.view.VelocityTracker r5 = r4.mVelocityTracker
            r6 = 10000(0x2710, float:1.4013E-41)
            int r0 = r4.mMaxFlingVelocity
            float r0 = (float) r0
            r5.computeCurrentVelocity(r6, r0)
            no.nordicsemi.android.mcp.scanner.timeline.TimelineFragment$FlingAnimation r5 = r4.mFlingAnimation
            if (r5 != 0) goto L65
            no.nordicsemi.android.mcp.scanner.timeline.TimelineFragment$FlingAnimation r5 = new no.nordicsemi.android.mcp.scanner.timeline.TimelineFragment$FlingAnimation
            r5.<init>()
            r4.mFlingAnimation = r5
        L65:
            no.nordicsemi.android.mcp.scanner.timeline.TimelineFragment$FlingAnimation r5 = r4.mFlingAnimation
            android.view.VelocityTracker r6 = r4.mVelocityTracker
            float r6 = r6.getXVelocity()
            int r6 = (int) r6
            no.nordicsemi.android.mcp.scanner.timeline.TimelineFragment.FlingAnimation.access$200(r5, r6)
        L71:
            android.view.VelocityTracker r5 = r4.mVelocityTracker
            r5.recycle()
            r4.mVelocityTracker = r2
            goto L99
        L79:
            android.view.VelocityTracker r5 = r4.mVelocityTracker
            if (r5 != 0) goto L84
            android.view.VelocityTracker r5 = android.view.VelocityTracker.obtain()
            r4.mVelocityTracker = r5
            goto L87
        L84:
            r5.clear()
        L87:
            no.nordicsemi.android.mcp.scanner.timeline.TimelineFragment$FlingAnimation r5 = r4.mFlingAnimation
            if (r5 == 0) goto L8e
            no.nordicsemi.android.mcp.scanner.timeline.TimelineFragment.FlingAnimation.access$000(r5)
        L8e:
            android.view.VelocityTracker r5 = r4.mVelocityTracker
            r5.addMovement(r6)
            float r5 = r6.getX()
            r4.mLastX = r5
        L99:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.scanner.timeline.TimelineFragment.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        this.mMaxFlingVelocity = ViewConfiguration.get(getActivity()).getScaledMaximumFlingVelocity();
        configurePlots(view);
        configureLists(view);
    }
}
