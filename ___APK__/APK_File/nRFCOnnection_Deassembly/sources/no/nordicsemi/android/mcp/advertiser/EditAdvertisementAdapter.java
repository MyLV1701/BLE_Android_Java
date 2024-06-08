package no.nordicsemi.android.mcp.advertiser;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter;
import no.nordicsemi.android.mcp.advertiser.model.Item;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public class EditAdvertisementAdapter extends RecyclerView.g<RecyclerView.d0> {
    private static final String SIS_ADVERTISEMENT = "adv_data";
    private static final String SIS_SCAN_RESPONSE = "scan_response_data";
    private static final int TYPE_ACTION_ADD = 2;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_TITLE = 1;
    private ArrayList<Item> mAdvertisement;
    private boolean mConnectable;
    private Fragment mParentFragment;
    private ArrayList<Item> mScanResponse;
    private View.OnTouchListener mTouchListener;
    private boolean mAdvertisingExtensionMode = false;
    private boolean mScannable = true;
    private View.OnClickListener onAddListener = new AnonymousClass1();
    private View.OnClickListener onRemoveListener = new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.f
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            EditAdvertisementAdapter.this.a(view);
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        private boolean hasField(List<Item> list, int i) {
            Iterator<Item> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().getType() == i) {
                    return true;
                }
            }
            return false;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x0089, code lost:
        
            return true;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public /* synthetic */ boolean a(java.util.List r5, android.view.MenuItem r6) {
            /*
                r4 = this;
                int r6 = r6.getItemId()
                r0 = -1
                r1 = 1
                r2 = 0
                r3 = 0
                switch(r6) {
                    case 2131296449: goto L78;
                    case 2131296450: goto L64;
                    case 2131296456: goto L47;
                    case 2131296457: goto L2a;
                    case 2131296458: goto Ld;
                    default: goto Lb;
                }
            Lb:
                goto L89
            Ld:
                no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter r6 = no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter.this
                java.util.ArrayList r6 = no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter.access$700(r6)
                if (r5 != r6) goto L17
                r5 = 1
                goto L18
            L17:
                r5 = 0
            L18:
                no.nordicsemi.android.mcp.advertiser.EditServiceUuidDialogFragment r5 = no.nordicsemi.android.mcp.advertiser.EditServiceUuidDialogFragment.getInstance(r5, r0, r3, r2)
                no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter r6 = no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter.this
                androidx.fragment.app.Fragment r6 = no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter.access$1300(r6)
                androidx.fragment.app.l r6 = r6.getChildFragmentManager()
                r5.show(r6, r2)
                goto L89
            L2a:
                no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter r6 = no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter.this
                java.util.ArrayList r6 = no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter.access$700(r6)
                if (r5 != r6) goto L34
                r5 = 1
                goto L35
            L34:
                r5 = 0
            L35:
                no.nordicsemi.android.mcp.advertiser.EditServiceDataDialogFragment r5 = no.nordicsemi.android.mcp.advertiser.EditServiceDataDialogFragment.getInstance(r5, r0, r3, r2)
                no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter r6 = no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter.this
                androidx.fragment.app.Fragment r6 = no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter.access$1300(r6)
                androidx.fragment.app.l r6 = r6.getChildFragmentManager()
                r5.show(r6, r2)
                goto L89
            L47:
                no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter r6 = no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter.this
                java.util.ArrayList r6 = no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter.access$700(r6)
                if (r5 != r6) goto L51
                r5 = 1
                goto L52
            L51:
                r5 = 0
            L52:
                no.nordicsemi.android.mcp.advertiser.EditManufacturerDataDialogFragment r5 = no.nordicsemi.android.mcp.advertiser.EditManufacturerDataDialogFragment.getInstance(r5, r0, r3, r2)
                no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter r6 = no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter.this
                androidx.fragment.app.Fragment r6 = no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter.access$1300(r6)
                androidx.fragment.app.l r6 = r6.getChildFragmentManager()
                r5.show(r6, r2)
                goto L89
            L64:
                no.nordicsemi.android.mcp.advertiser.model.Item r6 = new no.nordicsemi.android.mcp.advertiser.model.Item
                r0 = 10
                byte[] r2 = new byte[r1]
                r2[r3] = r3
                r6.<init>(r0, r2)
                r5.add(r6)
                no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter r5 = no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter.this
                r5.notifyDataSetChanged()
                goto L89
            L78:
                no.nordicsemi.android.mcp.advertiser.model.Item r6 = new no.nordicsemi.android.mcp.advertiser.model.Item
                r0 = 9
                byte[] r2 = new byte[r3]
                r6.<init>(r0, r2)
                r5.add(r6)
                no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter r5 = no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter.this
                r5.notifyDataSetChanged()
            L89:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.advertiser.EditAdvertisementAdapter.AnonymousClass1.a(java.util.List, android.view.MenuItem):boolean");
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            final List<Item> associatedData = ((ActionViewHolder) view.getTag()).getAssociatedData();
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.advertisement_add);
            popupMenu.getMenu().findItem(R.id.adv_include_local_name).setEnabled(!hasField(associatedData, 9));
            popupMenu.getMenu().findItem(R.id.adv_include_tx_power).setEnabled(!hasField(associatedData, 10));
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.d
                @Override // android.widget.PopupMenu.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    return EditAdvertisementAdapter.AnonymousClass1.this.a(associatedData, menuItem);
                }
            });
            popupMenu.show();
        }
    }

    /* loaded from: classes.dex */
    public class ActionViewHolder extends RecyclerView.d0 {
        ActionViewHolder(View view) {
            super(view);
            View findViewById = view.findViewById(R.id.action_add);
            findViewById.setOnClickListener(EditAdvertisementAdapter.this.onAddListener);
            findViewById.setOnTouchListener(EditAdvertisementAdapter.this.mTouchListener);
            findViewById.setTag(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public List<Item> getAssociatedData() {
            return getAdapterPosition() == EditAdvertisementAdapter.this.getAdvertisingDataViewCount() + (-1) ? EditAdvertisementAdapter.this.mAdvertisement : EditAdvertisementAdapter.this.mScanResponse;
        }
    }

    /* loaded from: classes.dex */
    public class TitleViewHolder extends RecyclerView.d0 {
        private TextView mTitle;

        TitleViewHolder(View view) {
            super(view);
            this.mTitle = (TextView) view;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onBind(int i) {
            this.mTitle.setText((EditAdvertisementAdapter.this.hasAdvertisingData() && i == 0) ? R.string.adv_title_adv_data : R.string.adv_title_scan_response);
        }
    }

    /* loaded from: classes.dex */
    public class ViewHolder extends RecyclerView.d0 {
        private View mDeleteAction;
        private Item mItem;
        private TextView mName;

        public ViewHolder(View view) {
            super(view);
            this.mName = (TextView) view.findViewById(R.id.display_name);
            this.mDeleteAction = view.findViewById(R.id.action_remove);
            this.mDeleteAction.setOnClickListener(EditAdvertisementAdapter.this.onRemoveListener);
            this.mDeleteAction.setOnTouchListener(EditAdvertisementAdapter.this.mTouchListener);
            this.mDeleteAction.setTag(this);
            view.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.advertiser.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    EditAdvertisementAdapter.ViewHolder.this.a(view2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public List<Item> getAssociatedData() {
            return getAdapterPosition() < EditAdvertisementAdapter.this.getAdvertisingDataViewCount() ? EditAdvertisementAdapter.this.mAdvertisement : EditAdvertisementAdapter.this.mScanResponse;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getDataPosition() {
            int adapterPosition = getAdapterPosition();
            if (adapterPosition < EditAdvertisementAdapter.this.getAdvertisingDataViewCount()) {
                return (adapterPosition - (EditAdvertisementAdapter.this.hasFlags() ? 1 : 0)) - 1;
            }
            return (adapterPosition - 1) - EditAdvertisementAdapter.this.getAdvertisingDataViewCount();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPacket(Item item) {
            this.mItem = item;
            if (item == null) {
                this.mName.setText(EditAdvertisementAdapter.this.mConnectable ? "Flags" : "Flags (On some devices)");
                this.mDeleteAction.setVisibility(8);
            } else {
                this.mName.setText(item.getDisplayName());
                this.mDeleteAction.setVisibility(0);
            }
        }

        public /* synthetic */ void a(View view) {
            Item item = this.mItem;
            if (item == null || !item.isEditable()) {
                return;
            }
            int type = this.mItem.getType();
            if (type == 3 || type == 5 || type == 7) {
                EditServiceUuidDialogFragment.getInstance(getAssociatedData() == EditAdvertisementAdapter.this.mAdvertisement, getDataPosition(), type, this.mItem.getRawData()).show(EditAdvertisementAdapter.this.mParentFragment.getChildFragmentManager(), (String) null);
                return;
            }
            if (type != 22) {
                if (type == 255) {
                    EditManufacturerDataDialogFragment.getInstance(getAssociatedData() == EditAdvertisementAdapter.this.mAdvertisement, getDataPosition(), type, this.mItem.getRawData()).show(EditAdvertisementAdapter.this.mParentFragment.getChildFragmentManager(), (String) null);
                    return;
                } else if (type != 32 && type != 33) {
                    return;
                }
            }
            EditServiceDataDialogFragment.getInstance(getAssociatedData() == EditAdvertisementAdapter.this.mAdvertisement, getDataPosition(), type, this.mItem.getRawData()).show(EditAdvertisementAdapter.this.mParentFragment.getChildFragmentManager(), (String) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EditAdvertisementAdapter(Fragment fragment, Bundle bundle) {
        setHasStableIds(false);
        this.mParentFragment = fragment;
        if (bundle != null) {
            this.mAdvertisement = bundle.getParcelableArrayList(SIS_ADVERTISEMENT);
            this.mScanResponse = bundle.getParcelableArrayList(SIS_SCAN_RESPONSE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getAdvertisingDataViewCount() {
        if (hasAdvertisingData()) {
            return (hasFlags() ? 1 : 0) + 2 + this.mAdvertisement.size();
        }
        return 0;
    }

    private byte[] getData(List<Item> list) {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream5 = new ByteArrayOutputStream();
        for (Item item : list) {
            try {
                byte[] rawData = item.getRawData();
                byte type = (byte) item.getType();
                byte length = (byte) (rawData.length + 1);
                if (type == 3) {
                    byteArrayOutputStream = byteArrayOutputStream3;
                } else if (type == 5) {
                    byteArrayOutputStream = byteArrayOutputStream4;
                } else if (type == 7) {
                    byteArrayOutputStream = byteArrayOutputStream5;
                } else {
                    byteArrayOutputStream2.write(length);
                    byteArrayOutputStream2.write(type);
                    byteArrayOutputStream = byteArrayOutputStream2;
                }
                byteArrayOutputStream.write(rawData);
            } catch (IOException e2) {
                Log.e("EditAdvAdapter", "Getting raw data failed", e2);
            }
        }
        try {
            if (byteArrayOutputStream3.size() > 0) {
                byteArrayOutputStream2.write(byteArrayOutputStream3.size() + 1);
                byteArrayOutputStream2.write(3);
                byteArrayOutputStream3.writeTo(byteArrayOutputStream2);
            }
            if (byteArrayOutputStream4.size() > 0) {
                byteArrayOutputStream2.write(byteArrayOutputStream4.size() + 1);
                byteArrayOutputStream2.write(5);
                byteArrayOutputStream4.writeTo(byteArrayOutputStream2);
            }
            if (byteArrayOutputStream5.size() > 0) {
                byteArrayOutputStream2.write(byteArrayOutputStream5.size() + 1);
                byteArrayOutputStream2.write(7);
                byteArrayOutputStream5.writeTo(byteArrayOutputStream2);
            }
        } catch (Exception e3) {
            Log.e("EditAdvAdapter", "Merging streams failed", e3);
        }
        return byteArrayOutputStream2.toByteArray();
    }

    private int getScanResponseViewCount() {
        if (hasScanResponse()) {
            return this.mScanResponse.size() + 2;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasAdvertisingData() {
        return (this.mAdvertisingExtensionMode && this.mScannable) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasFlags() {
        return this.mConnectable || (!this.mAdvertisingExtensionMode && this.mScannable && this.mScanResponse.size() > 0);
    }

    private boolean hasScanResponse() {
        return this.mScannable;
    }

    private ArrayList<Item> read(byte[] bArr) {
        ArrayList<Item> arrayList = new ArrayList<>();
        if (bArr != null && bArr.length > 0) {
            int i = 0;
            while (i < bArr.length) {
                int i2 = i + 1;
                int i3 = bArr[i] & FlagsParser.UNKNOWN_FLAGS;
                int i4 = bArr[i2] & FlagsParser.UNKNOWN_FLAGS;
                if (i4 == 3) {
                    for (int i5 = 0; i5 < i3 - 1; i5 += 2) {
                        arrayList.add(new Item(i4, Arrays.copyOfRange(bArr, i2 + 1 + i5, i2 + i5 + 3)));
                    }
                } else if (i4 == 5) {
                    for (int i6 = 0; i6 < i3 - 1; i6 += 4) {
                        arrayList.add(new Item(i4, Arrays.copyOfRange(bArr, i2 + 1 + i6, i2 + i6 + 5)));
                    }
                } else if (i4 != 7) {
                    if (i4 != 22 && i4 != 255) {
                        if (i4 == 9) {
                            arrayList.add(new Item(i4, new byte[0]));
                        } else if (i4 != 10 && i4 != 32 && i4 != 33) {
                        }
                    }
                    arrayList.add(new Item(i4, Arrays.copyOfRange(bArr, i2 + 1, i2 + i3)));
                } else {
                    for (int i7 = 0; i7 < i3 - 1; i7 += 16) {
                        arrayList.add(new Item(i4, Arrays.copyOfRange(bArr, i2 + 1 + i7, i2 + i7 + 17)));
                    }
                }
                i = i3 + i2;
            }
        }
        return arrayList;
    }

    public /* synthetic */ void a(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.getAssociatedData().remove(viewHolder.getDataPosition());
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getAdvertisingData() {
        return !hasAdvertisingData() ? new byte[0] : getData(this.mAdvertisement);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemCount() {
        return getAdvertisingDataViewCount() + getScanResponseViewCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemViewType(int i) {
        if (i == 0 || (hasAdvertisingData() && hasScanResponse() && i == getAdvertisingDataViewCount())) {
            return 1;
        }
        if (hasAdvertisingData() && i == getAdvertisingDataViewCount() - 1) {
            return 2;
        }
        return (hasScanResponse() && i == (getAdvertisingDataViewCount() + getScanResponseViewCount()) - 1) ? 2 : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getScanResponseData() {
        return !hasScanResponse() ? new byte[0] : getData(this.mScanResponse);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isScanResponseSet() {
        return !this.mScanResponse.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void load(byte[] bArr, byte[] bArr2) {
        this.mAdvertisement = read(bArr);
        this.mScanResponse = read(bArr2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onBindViewHolder(RecyclerView.d0 d0Var, int i) {
        if (d0Var instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) d0Var;
            viewHolder.setPacket((i == 1 && hasFlags()) ? null : (Item) viewHolder.getAssociatedData().get(viewHolder.getDataPosition()));
        } else if (d0Var instanceof TitleViewHolder) {
            ((TitleViewHolder) d0Var).onBind(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public RecyclerView.d0 onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new TitleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dialog_advertisement_edit_title, viewGroup, false));
        }
        if (i != 2) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dialog_advertisement_edit_item, viewGroup, false));
        }
        return new ActionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dialog_advertisement_edit_footer, viewGroup, false));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onItemEdited(boolean z, int i, int i2, byte[] bArr) {
        ArrayList<Item> arrayList = z ? this.mAdvertisement : this.mScanResponse;
        if (i == -1) {
            arrayList.add(new Item(i2, bArr));
        } else {
            arrayList.get(i).setData(i2, bArr);
        }
        notifyDataSetChanged();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelableArrayList(SIS_ADVERTISEMENT, this.mAdvertisement);
        bundle.putParcelableArrayList(SIS_SCAN_RESPONSE, this.mScanResponse);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAdvertisingExtensionMode(boolean z) {
        this.mAdvertisingExtensionMode = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setConnectible(boolean z) {
        this.mConnectable = z;
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.mTouchListener = onTouchListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setScannable(boolean z) {
        this.mScannable = z;
    }
}
