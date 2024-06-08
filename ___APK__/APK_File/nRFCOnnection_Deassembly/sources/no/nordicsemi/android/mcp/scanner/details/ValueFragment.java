package no.nordicsemi.android.mcp.scanner.details;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
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
import no.nordicsemi.android.mcp.scanner.details.ValueFragment;
import no.nordicsemi.android.mcp.scanner.graph.ParcelableXYSeries;
import no.nordicsemi.android.mcp.util.ParcelableTimespan;

/* loaded from: classes.dex */
public class ValueFragment extends Fragment implements View.OnTouchListener {
    private static final String DEVICE_INDEX = "device_index";
    private static final float GRAPH_SPAN = 10000.0f;
    private static final String SCANNER_TIMESPANS = "scanner_timespans";
    private static final String SIS_OFFSET = "offset";
    private static final String UNION_INDEX = "index";
    private int[] mColors;
    private DateFormat mDateFormat;
    private Device mDevice;
    private FlingAnimation mFlingAnimation;
    private HorizontalScrollListener mHorizontalScrollListener;
    private float mLastX;
    private int mMaxFlingVelocity;
    private int mMaxValue;
    private XYPlot mPlot;
    private float mRangeMax;
    private float mRangeMin;
    private List<ParcelableTimespan> mScannerTimespans;
    private float mScrollOffset;
    private ParcelableXYSeries mSelectionIndicator;
    private int mUnionIndex;
    private VelocityTracker mVelocityTracker;

    /* loaded from: classes.dex */
    private class FlingAnimation implements Runnable {
        private OverScroller mScroller;

        /* JADX INFO: Access modifiers changed from: private */
        public void startOverfling(int i) {
            this.mScroller.fling((int) ValueFragment.this.mScrollOffset, 0, i, 0, 10000, ValueFragment.this.mMaxValue, 0, 0, 0, 0);
            ValueFragment.this.mPlot.postOnAnimation(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void stopOverfling() {
            this.mScroller.forceFinished(true);
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean computeScrollOffset = this.mScroller.computeScrollOffset();
            ValueFragment.this.mScrollOffset = r0.getCurrX();
            ValueFragment.this.mPlot.setDomainBoundaries(Float.valueOf(ValueFragment.this.mScrollOffset), Float.valueOf(ValueFragment.this.mScrollOffset - ValueFragment.GRAPH_SPAN), BoundaryMode.FIXED);
            ValueFragment.this.mPlot.redraw();
            if (computeScrollOffset) {
                ValueFragment.this.mPlot.postOnAnimation(this);
            }
        }

        private FlingAnimation() {
            this.mScroller = new OverScroller(ValueFragment.this.getActivity());
            this.mScroller.setFriction(0.025f);
        }
    }

    /* loaded from: classes.dex */
    public class PacketsAdapter extends RecyclerView.g<ViewHolder> {
        private View.OnTouchListener mPacketSelectionListener = new View.OnTouchListener() { // from class: no.nordicsemi.android.mcp.scanner.details.ValueFragment.PacketsAdapter.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action != 0) {
                    if ((action != 1 && action != 3) || ValueFragment.this.mSelectionIndicator == null) {
                        return false;
                    }
                    ValueFragment.this.mPlot.removeSeries(ValueFragment.this.mSelectionIndicator);
                    ValueFragment.this.mPlot.redraw();
                    ValueFragment.this.mSelectionIndicator = null;
                    return false;
                }
                if (ValueFragment.this.mSelectionIndicator != null) {
                    return false;
                }
                int intValue = ((Integer) view.getTag()).intValue();
                ValueFragment.this.mSelectionIndicator = new ParcelableXYSeries("Selection");
                ValueFragment.this.mSelectionIndicator.addLast(Integer.valueOf(intValue), Float.valueOf(ValueFragment.this.mRangeMin));
                ValueFragment.this.mSelectionIndicator.addLast(Integer.valueOf(intValue - 1), Float.valueOf(ValueFragment.this.mRangeMax));
                ValueFragment.this.mPlot.addSeries(ValueFragment.this.mSelectionIndicator, new LineAndPointFormatter(-16744265, null, null, null));
                float f2 = intValue;
                if (ValueFragment.this.mScrollOffset < f2 || ValueFragment.this.mScrollOffset - ValueFragment.GRAPH_SPAN > f2) {
                    if (ValueFragment.this.mFlingAnimation != null) {
                        ValueFragment.this.mFlingAnimation.stopOverfling();
                    }
                    ValueFragment.this.mScrollOffset = Math.max(ValueFragment.GRAPH_SPAN, Math.min(intValue + 5000, r6.mMaxValue));
                    ValueFragment.this.mPlot.setDomainBoundaries(Float.valueOf(ValueFragment.this.mScrollOffset), Float.valueOf(ValueFragment.this.mScrollOffset - ValueFragment.GRAPH_SPAN), BoundaryMode.FIXED);
                }
                ValueFragment.this.mPlot.redraw();
                return false;
            }
        };
        private View.OnClickListener mEmptyOnClickListener = new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.scanner.details.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ValueFragment.PacketsAdapter.a(view);
            }
        };

        /* loaded from: classes.dex */
        public class ViewHolder extends RecyclerView.d0 {
            private ImageView mBar;
            private TextView mData;
            private ImageView mIconFirst;
            private ImageView mIconLast;
            private View mPacketFirst;
            private TextView mPacketInterval;
            private View mPacketLast;
            private TextView mTimestampFirst;
            private TextView mTimestampLast;

            public ViewHolder(View view) {
                super(view);
                this.mBar = (ImageView) view.findViewById(R.id.bar);
                this.mData = (TextView) view.findViewById(R.id.data);
                this.mPacketLast = view.findViewById(R.id.packet_last);
                this.mIconLast = (ImageView) view.findViewById(R.id.icon_1);
                this.mTimestampLast = (TextView) view.findViewById(R.id.value_1);
                this.mPacketFirst = view.findViewById(R.id.packet_first);
                this.mIconFirst = (ImageView) view.findViewById(R.id.icon_2);
                this.mTimestampFirst = (TextView) view.findViewById(R.id.value_2);
                this.mPacketInterval = (TextView) view.findViewById(R.id.packet_interval);
            }
        }

        public PacketsAdapter() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void a(View view) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            return ValueFragment.this.mDevice.getPacketsHistory().size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            String str;
            long j;
            int selectedIndex = ValueFragment.this.mDevice.getInfo(ValueFragment.this.mUnionIndex).getSelectedIndex();
            List<AdvDataWithStats> packetsHistory = ValueFragment.this.mDevice.getPacketsHistory();
            List<PacketData> packetsMetaData = ValueFragment.this.mDevice.getPacketsMetaData();
            AdvDataWithStats advDataWithStats = packetsHistory.get(i);
            DataUnion.Data data = advDataWithStats.getAllInfo().get(ValueFragment.this.mUnionIndex).getData(selectedIndex);
            SpannableString spannableString = new SpannableString(ValueFragment.this.getString(R.string.key_value, data.key, data.value));
            spannableString.setSpan(new ForegroundColorSpan(-7829368), 0, data.key.length() + 1, 0);
            viewHolder.mData.setText(spannableString);
            viewHolder.mBar.setImageLevel(i % ValueFragment.this.mColors.length);
            viewHolder.mIconFirst.setImageLevel(i % ValueFragment.this.mColors.length);
            viewHolder.mIconLast.setImageLevel(i % ValueFragment.this.mColors.length);
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                i2 += packetsHistory.get(i3).getCount();
            }
            String format = ValueFragment.this.mDateFormat.format(Calendar.getInstance().getTime());
            PacketData packetData = packetsMetaData.get(i2);
            PacketData packetData2 = packetsMetaData.get((i2 + advDataWithStats.getCount()) - 1);
            long currentTimeMillis = System.currentTimeMillis();
            long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(currentTimeMillis - ((elapsedRealtimeNanos - packetData.timestampNanos) / 1000000));
            String format2 = ValueFragment.this.mDateFormat.format(calendar.getTime());
            if (format.equals(format2)) {
                str = format;
                viewHolder.mTimestampLast.setText(ValueFragment.this.getString(R.string.time, calendar));
                j = currentTimeMillis;
            } else {
                str = format;
                j = currentTimeMillis;
                viewHolder.mTimestampLast.setText(ValueFragment.this.getString(R.string.date_time, format2, calendar));
            }
            viewHolder.mPacketLast.setOnClickListener(this.mEmptyOnClickListener);
            viewHolder.mPacketLast.setOnTouchListener(this.mPacketSelectionListener);
            viewHolder.mPacketLast.setTag(Integer.valueOf((int) ((packetsMetaData.get(0).timestampNanos - packetData.timestampNanos) / 1000000)));
            if (advDataWithStats.getCount() <= 1) {
                viewHolder.mPacketInterval.setVisibility(8);
                viewHolder.mPacketFirst.setVisibility(8);
                return;
            }
            long j2 = packetData.timestampNanos;
            long j3 = packetData2.timestampNanos;
            long j4 = (j2 - j3) / 1000000;
            calendar.setTimeInMillis(j - ((elapsedRealtimeNanos - j3) / 1000000));
            String format3 = ValueFragment.this.mDateFormat.format(calendar.getTime());
            if (str.equals(format3)) {
                viewHolder.mTimestampFirst.setText(ValueFragment.this.getString(R.string.time, calendar));
            } else {
                viewHolder.mTimestampFirst.setText(ValueFragment.this.getString(R.string.date_time, format3, calendar));
            }
            calendar.setTimeInMillis(j4);
            calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
            if (j4 < 86400000) {
                viewHolder.mPacketInterval.setText(ValueFragment.this.getString(R.string.time, calendar));
            } else {
                int i4 = (int) (j4 / 86400000);
                viewHolder.mPacketInterval.setText(ValueFragment.this.getResources().getQuantityString(R.plurals.days_time, i4, Integer.valueOf(i4), calendar));
            }
            viewHolder.mPacketFirst.setOnClickListener(this.mEmptyOnClickListener);
            viewHolder.mPacketFirst.setOnTouchListener(this.mPacketSelectionListener);
            viewHolder.mPacketFirst.setTag(Integer.valueOf((int) ((packetsMetaData.get(0).timestampNanos - packetData2.timestampNanos) / 1000000)));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.device_details_value_item, viewGroup, false));
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
        DataUnion info = device.getInfo(this.mUnionIndex);
        DataUnion.Data selectedData = info.getSelectedData();
        xYPlot.setRangeLabel(selectedData.key);
        if ((selectedData.format & 240) < 48 && selectedData.divider == 1.0f) {
            xYPlot.setRangeValueFormat(new DecimalFormat("0"));
        }
        List<PacketData> packetsMetaData = device.getPacketsMetaData();
        long j = packetsMetaData.get(0).timestampNanos / 1000000;
        float f2 = -3.4028235E38f;
        float f3 = Float.MAX_VALUE;
        int i = 0;
        int i2 = 0;
        for (AdvDataWithStats advDataWithStats : device.getPacketsHistory()) {
            Float selectedValueFromPacket = info.getSelectedValueFromPacket(advDataWithStats.getRawData());
            if (selectedValueFromPacket == null) {
                break;
            }
            if (selectedValueFromPacket.floatValue() > f2 && selectedValueFromPacket.floatValue() != Float.POSITIVE_INFINITY) {
                f2 = selectedValueFromPacket.floatValue();
            }
            if (selectedValueFromPacket.floatValue() < f3 && selectedValueFromPacket.floatValue() != Float.NEGATIVE_INFINITY) {
                f3 = selectedValueFromPacket.floatValue();
            }
            ParcelableXYSeries parcelableXYSeries = new ParcelableXYSeries("");
            int i3 = i;
            while (i3 < advDataWithStats.getCount() + i) {
                DataUnion dataUnion = info;
                List<PacketData> list = packetsMetaData;
                int i4 = (int) (j - (packetsMetaData.get(i3).timestampNanos / 1000000));
                this.mMaxValue = i4;
                if (selectedValueFromPacket.floatValue() != Float.NEGATIVE_INFINITY && selectedValueFromPacket.floatValue() != Float.POSITIVE_INFINITY) {
                    parcelableXYSeries.addLast(Integer.valueOf(i4), selectedValueFromPacket);
                }
                i3++;
                info = dataUnion;
                packetsMetaData = list;
            }
            DataUnion dataUnion2 = info;
            List<PacketData> list2 = packetsMetaData;
            if (parcelableXYSeries.size() > 0) {
                xYPlot.addSeries(parcelableXYSeries, new LineAndPointFormatter(null, Integer.valueOf(this.mColors[i2]), null, null));
            }
            i2 = (i2 + 1) % this.mColors.length;
            i += advDataWithStats.getCount();
            info = dataUnion2;
            packetsMetaData = list2;
        }
        if (xYPlot.getSeriesSet().size() == 0) {
            xYPlot.setVisibility(8);
            return;
        }
        float f4 = ((f2 - f3) / 10.0f) + 2.0f;
        float f5 = f2 + f4;
        float f6 = f3 - f4;
        if (f5 == f6) {
            f6 -= f6 / 100.0f;
            f5 += f5 / 100.0f;
        }
        if (f5 < 1000.0f && f6 > -1000.0f) {
            f5 = (float) Math.floor(((float) ((f5 * 10) / 10)) + 0.5f);
            this.mRangeMax = f5;
            f6 = (float) Math.floor(((float) ((f6 * 10) / 10)) - 0.5f);
            this.mRangeMin = f6;
        } else {
            this.mRangeMax = f5;
            this.mRangeMin = f6;
        }
        xYPlot.getGraphWidget().setMarginLeft(TypedValue.applyDimension(1, (Math.max(String.valueOf(f6).length(), String.valueOf(f5).length()) - 1) * 10, getResources().getDisplayMetrics()));
        xYPlot.setRangeBoundaries(Float.valueOf(f6), Float.valueOf(f5), BoundaryMode.FIXED);
        ParcelableXYSeries parcelableXYSeries2 = new ParcelableXYSeries("");
        long j2 = 2147483647L;
        for (ParcelableTimespan parcelableTimespan : this.mScannerTimespans) {
            parcelableXYSeries2.addLast(Integer.valueOf((int) j2), Float.valueOf(f5));
            parcelableXYSeries2.addLast(Integer.valueOf((int) ((j - parcelableTimespan.start) - 10)), Float.valueOf(f5));
            parcelableXYSeries2.addLast(Integer.valueOf((int) ((j - parcelableTimespan.start) - 11)), null);
            j2 = j - parcelableTimespan.end;
        }
        xYPlot.addSeries(parcelableXYSeries2, new LineAndPointFormatter(0, null, 2130706432, null));
    }

    public static ValueFragment getInstance(Device device, int i, ArrayList<ParcelableTimespan> arrayList) {
        ValueFragment valueFragment = new ValueFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("device_index", device.getDeviceIndex());
        bundle.putInt(UNION_INDEX, i);
        bundle.putParcelableArrayList(SCANNER_TIMESPANS, arrayList);
        valueFragment.setArguments(bundle);
        return valueFragment;
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
        this.mUnionIndex = arguments.getInt(UNION_INDEX);
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
            no.nordicsemi.android.mcp.scanner.details.ValueFragment$FlingAnimation r5 = r4.mFlingAnimation
            if (r5 != 0) goto L6c
            no.nordicsemi.android.mcp.scanner.details.ValueFragment$FlingAnimation r5 = new no.nordicsemi.android.mcp.scanner.details.ValueFragment$FlingAnimation
            r5.<init>()
            r4.mFlingAnimation = r5
        L6c:
            no.nordicsemi.android.mcp.scanner.details.ValueFragment$FlingAnimation r5 = r4.mFlingAnimation
            android.view.VelocityTracker r6 = r4.mVelocityTracker
            float r6 = r6.getXVelocity()
            int r6 = (int) r6
            no.nordicsemi.android.mcp.scanner.details.ValueFragment.FlingAnimation.access$200(r5, r6)
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
            no.nordicsemi.android.mcp.scanner.details.ValueFragment$FlingAnimation r5 = r4.mFlingAnimation
            if (r5 == 0) goto L95
            no.nordicsemi.android.mcp.scanner.details.ValueFragment.FlingAnimation.access$000(r5)
        L95:
            android.view.VelocityTracker r5 = r4.mVelocityTracker
            r5.addMovement(r6)
            float r5 = r6.getX()
            r4.mLastX = r5
        La0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.scanner.details.ValueFragment.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        this.mMaxFlingVelocity = ViewConfiguration.get(getActivity()).getScaledMaximumFlingVelocity();
        configurePlots(view);
        configureLists(view);
    }
}
