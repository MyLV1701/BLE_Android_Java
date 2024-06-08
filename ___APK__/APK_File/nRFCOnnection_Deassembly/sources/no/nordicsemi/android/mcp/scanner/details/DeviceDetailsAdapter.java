package no.nordicsemi.android.mcp.scanner.details;

import androidx.appcompat.app.e;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.p;
import java.util.ArrayList;
import java.util.List;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.util.ParcelableTimespan;

/* loaded from: classes.dex */
public class DeviceDetailsAdapter extends p {
    private Device mDevice;
    private ArrayList<ParcelableTimespan> mScannerTimespans;
    private List<String> mTitles;
    private List<Integer> mUnionIndex;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeviceDetailsAdapter(e eVar, Device device, ArrayList<ParcelableTimespan> arrayList) {
        super(eVar.getSupportFragmentManager());
        this.mTitles = new ArrayList();
        this.mUnionIndex = new ArrayList();
        this.mDevice = device;
        this.mScannerTimespans = arrayList;
        this.mTitles.add(eVar.getString(R.string.details_history));
        this.mTitles.add(eVar.getString(R.string.details_packet_details));
        for (int i = 0; i < device.getInfoSize(); i++) {
            DataUnion.Data selectedData = device.getInfo(i).getSelectedData();
            if (selectedData.showAsTab) {
                this.mTitles.add(selectedData.key);
                this.mUnionIndex.add(Integer.valueOf(i));
            }
        }
    }

    @Override // androidx.viewpager.widget.a
    public int getCount() {
        return this.mTitles.size();
    }

    @Override // androidx.fragment.app.p
    public Fragment getItem(int i) {
        if (i == 0) {
            return HistoryFragment.getInstance(this.mDevice, this.mScannerTimespans);
        }
        if (i == 1) {
            return FlagsAndServicesFragment.getInstance(this.mDevice);
        }
        return ValueFragment.getInstance(this.mDevice, this.mUnionIndex.get(i - 2).intValue(), this.mScannerTimespans);
    }

    @Override // androidx.viewpager.widget.a
    public CharSequence getPageTitle(int i) {
        return this.mTitles.get(i);
    }
}
