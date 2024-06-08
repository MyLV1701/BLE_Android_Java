package no.nordicsemi.android.mcp.ble.write;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.method.KeyListener;
import android.text.method.TextKeyListener;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.ble.write.DefaultDialogBuilder;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.widget.HexKeyListener;
import no.nordicsemi.android.mcp.widget.NumberKeyListener;
import no.nordicsemi.android.mcp.widget.SwipeDismissListViewTouchListener;

/* loaded from: classes.dex */
public class DefaultDialogBuilder extends WriteDialogBuilder {
    private static final int FORMAT_BEACON_MAJOR_MINOR = 2;
    private static final int FORMAT_BYTE = 0;
    private static final int FORMAT_BYTE_ARRAY = 3;
    private static final int FORMAT_TEXT = 1;
    private static final String SHOW_PARSING_DISABLED_MESSAGE = "show_parsing_disabled_message";
    private static final String SIS_TAB_SELECTED = "tab_selected";
    private static final String SIS_VALUES = "tab_values";
    private DatabaseHelper mDatabaseHelper;
    private SwipeDismissListViewTouchListener mDismissListener;
    private LayoutInflater mInflater;
    private EditText mNameView;
    private View mNewContentView;
    private View mNewTabView;
    private Resources mResources;
    private View mSavedContentView;
    private SavedPacketsAdapter mSavedPacketsAdapter;
    private ListView mSavedPacketsListView;
    private View mSavedTabView;
    private boolean mShowParsingDisabledMessage;
    private int mTabSelected;
    private ArrayList<Value> mValues;
    private RecyclerView.g mValuesAdapter;

    /* loaded from: classes.dex */
    private class FormatAdapter extends BaseAdapter {
        private FormatAdapter() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return 12;
        }

        @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            TextView textView = (TextView) view;
            if (textView == null) {
                textView = (TextView) DefaultDialogBuilder.this.mInflater.inflate(R.layout.simple_spinner_dropdown_item, viewGroup, false);
            }
            textView.setText(DefaultDialogBuilder.this.mResources.getStringArray(no.nordicsemi.android.mcp.R.array.formats)[i]);
            return textView;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return Integer.valueOf(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            switch (i) {
                case 1:
                    return 0L;
                case 2:
                    return 17L;
                case 3:
                    return 18L;
                case 4:
                    return 20L;
                case 5:
                    return 33L;
                case 6:
                    return 34L;
                case 7:
                    return 36L;
                case 8:
                    return 50L;
                case 9:
                    return 52L;
                case 10:
                    return 1L;
                case 11:
                    return 2L;
                default:
                    return 3L;
            }
        }

        int getPosition(int i) {
            if (i == 0) {
                return 1;
            }
            if (i == 1) {
                return 10;
            }
            if (i == 2) {
                return 11;
            }
            if (i == 17) {
                return 2;
            }
            if (i == 18) {
                return 3;
            }
            if (i == 20) {
                return 4;
            }
            if (i == 36) {
                return 7;
            }
            if (i == 50) {
                return 8;
            }
            if (i == 52) {
                return 9;
            }
            if (i != 33) {
                return i != 34 ? 0 : 6;
            }
            return 5;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = (TextView) view;
            if (textView == null) {
                textView = (TextView) DefaultDialogBuilder.this.mInflater.inflate(R.layout.simple_spinner_item, viewGroup, false);
            }
            textView.setText(DefaultDialogBuilder.this.mResources.getStringArray(no.nordicsemi.android.mcp.R.array.formats)[i]);
            return textView;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class SavedPacketsAdapter extends CursorAdapter {
        private static final int TYPE_DELETED = 1;
        private static final int TYPE_ENTRY = 0;
        private static final int TYPE_TITLE = 2;

        /* loaded from: classes.dex */
        private class BasedPacketViewHolder {
            TextView data;
            TextView name;
            TextView time;

            private BasedPacketViewHolder() {
            }
        }

        /* loaded from: classes.dex */
        private class Data {
            byte[] data;
            String name;

            public Data(String str, byte[] bArr) {
                this.name = str;
                this.data = bArr;
            }
        }

        /* loaded from: classes.dex */
        private class DeletedPacketViewHolder extends BasedPacketViewHolder {
            View actionUndo;

            private DeletedPacketViewHolder() {
                super();
            }
        }

        /* loaded from: classes.dex */
        private class PacketViewHolder extends BasedPacketViewHolder {
            View actionEdit;

            private PacketViewHolder() {
                super();
            }
        }

        /* loaded from: classes.dex */
        private class TitleViewHolder {
            TextView info;
            TextView title;

            private TitleViewHolder() {
            }
        }

        SavedPacketsAdapter(Context context, Cursor cursor) {
            super(context, cursor, 0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean canDismiss(int i) {
            return getItemViewType(i) == 0 && ((Cursor) getItem(i)).getInt(3) == 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public byte[] getSavedData(int i) {
            return DefaultDialogBuilder.readValue(((Cursor) getItem(i)).getBlob(4));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isFirstRecent() {
            return ((Cursor) super.getItem(0)).getInt(3) == 1;
        }

        public /* synthetic */ void a(View view) {
            DefaultDialogBuilder.this.mDatabaseHelper.undoDeleteSavedPacket(((Long) view.getTag()).longValue());
            changeCursor(DefaultDialogBuilder.this.mDatabaseHelper.getSavedPackets());
            DefaultDialogBuilder.this.mDismissListener.cancelDismiss();
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            return false;
        }

        public /* synthetic */ void b(View view) {
            Data data = (Data) view.getTag();
            DefaultDialogBuilder.this.loadValues(data.data);
            DefaultDialogBuilder.this.mNameView.setText(data.name);
            DefaultDialogBuilder.this.setTabSelected(0);
        }

        @Override // android.widget.CursorAdapter
        public void bindView(View view, Context context, Cursor cursor) {
            long j = cursor.getLong(0);
            String string = cursor.getString(1);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(cursor.getLong(2));
            boolean z = cursor.getInt(3) == 1;
            byte[] blob = cursor.getBlob(4);
            if (!(cursor.getInt(5) == 1)) {
                PacketViewHolder packetViewHolder = (PacketViewHolder) view.getTag();
                packetViewHolder.name.setText(string);
                packetViewHolder.time.setText(String.format(Locale.US, "%1$te %1$tB %1$tY, %1$tR", calendar));
                packetViewHolder.data.setText(ParserUtils.bytesToHex(DefaultDialogBuilder.readValue(blob), 0, blob.length, true));
                View view2 = packetViewHolder.actionEdit;
                if (z) {
                    string = null;
                }
                view2.setTag(new Data(string, blob));
                return;
            }
            ((DeletedPacketViewHolder) view.getTag()).actionUndo.setTag(Long.valueOf(j));
        }

        @Override // android.widget.CursorAdapter, android.widget.Adapter
        public int getCount() {
            int count = super.getCount();
            if (count == 0) {
                return 0;
            }
            if (count == 1) {
                return 2;
            }
            return count + (isFirstRecent() ? 2 : 1);
        }

        @Override // android.widget.CursorAdapter, android.widget.Adapter
        public Object getItem(int i) {
            boolean isFirstRecent = isFirstRecent();
            int i2 = no.nordicsemi.android.mcp.R.string.alert_saved_title_others;
            if (i == 0) {
                if (isFirstRecent) {
                    i2 = no.nordicsemi.android.mcp.R.string.alert_saved_title_recent;
                }
                return Integer.valueOf(i2);
            }
            if (i == 1) {
                return super.getItem(0);
            }
            if (i == 2) {
                return isFirstRecent ? Integer.valueOf(no.nordicsemi.android.mcp.R.string.alert_saved_title_others) : super.getItem(1);
            }
            return super.getItem(i - (isFirstRecent ? 2 : 1));
        }

        @Override // android.widget.CursorAdapter, android.widget.Adapter
        public long getItemId(int i) {
            if (i > getCount()) {
                return 0L;
            }
            if (i == 0) {
                return -1L;
            }
            if (i == 1) {
                return super.getItemId(0);
            }
            boolean isFirstRecent = isFirstRecent();
            if (i != 2) {
                return super.getItemId(i - (isFirstRecent ? 2 : 1));
            }
            if (isFirstRecent) {
                return -2L;
            }
            return super.getItemId(1);
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i) {
            if (i == 0) {
                return 2;
            }
            if (i == 2 && isFirstRecent()) {
                return 2;
            }
            return ((Cursor) getItem(i)).getInt(5) == 1 ? 1 : 0;
        }

        @Override // android.widget.CursorAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (getItemViewType(i) != 2) {
                if (i == 1) {
                    return super.getView(0, view, viewGroup);
                }
                return super.getView(i - (isFirstRecent() ? 2 : 1), view, viewGroup);
            }
            if (view == null) {
                view = DefaultDialogBuilder.this.mInflater.inflate(no.nordicsemi.android.mcp.R.layout.dialog_write_default_saved_item_title, viewGroup, false);
                TitleViewHolder titleViewHolder = new TitleViewHolder();
                titleViewHolder.title = (TextView) view.findViewById(no.nordicsemi.android.mcp.R.id.title);
                titleViewHolder.info = (TextView) view.findViewById(no.nordicsemi.android.mcp.R.id.info);
                view.setTag(titleViewHolder);
            }
            TitleViewHolder titleViewHolder2 = (TitleViewHolder) view.getTag();
            int intValue = ((Integer) getItem(i)).intValue();
            titleViewHolder2.title.setText(intValue);
            titleViewHolder2.info.setVisibility(intValue != no.nordicsemi.android.mcp.R.string.alert_saved_title_others ? 8 : 0);
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return 3;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i) {
            return getItemViewType(i) == 0;
        }

        @Override // android.widget.CursorAdapter
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            if (cursor.getInt(5) == 1) {
                View inflate = DefaultDialogBuilder.this.mInflater.inflate(no.nordicsemi.android.mcp.R.layout.dialog_write_default_saved_item_deleted, viewGroup, false);
                DeletedPacketViewHolder deletedPacketViewHolder = new DeletedPacketViewHolder();
                deletedPacketViewHolder.name = (TextView) inflate.findViewById(no.nordicsemi.android.mcp.R.id.display_name);
                deletedPacketViewHolder.time = (TextView) inflate.findViewById(no.nordicsemi.android.mcp.R.id.time);
                deletedPacketViewHolder.data = (TextView) inflate.findViewById(no.nordicsemi.android.mcp.R.id.data);
                deletedPacketViewHolder.actionUndo = inflate.findViewById(no.nordicsemi.android.mcp.R.id.undo);
                deletedPacketViewHolder.actionUndo.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.ble.write.a
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        DefaultDialogBuilder.SavedPacketsAdapter.this.a(view);
                    }
                });
                inflate.setTag(deletedPacketViewHolder);
                return inflate;
            }
            View inflate2 = DefaultDialogBuilder.this.mInflater.inflate(no.nordicsemi.android.mcp.R.layout.dialog_write_default_saved_item, viewGroup, false);
            PacketViewHolder packetViewHolder = new PacketViewHolder();
            packetViewHolder.name = (TextView) inflate2.findViewById(no.nordicsemi.android.mcp.R.id.display_name);
            packetViewHolder.time = (TextView) inflate2.findViewById(no.nordicsemi.android.mcp.R.id.time);
            packetViewHolder.data = (TextView) inflate2.findViewById(no.nordicsemi.android.mcp.R.id.data);
            packetViewHolder.actionEdit = inflate2.findViewById(no.nordicsemi.android.mcp.R.id.edit);
            packetViewHolder.actionEdit.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.ble.write.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DefaultDialogBuilder.SavedPacketsAdapter.this.b(view);
                }
            });
            inflate2.setTag(packetViewHolder);
            return inflate2;
        }
    }

    /* loaded from: classes.dex */
    public static class Value implements Parcelable {
        public static final Parcelable.Creator<Value> CREATOR = new Parcelable.Creator<Value>() { // from class: no.nordicsemi.android.mcp.ble.write.DefaultDialogBuilder.Value.1
            @Override // android.os.Parcelable.Creator
            public Value createFromParcel(Parcel parcel) {
                Value value = new Value();
                value.exponent = parcel.readString();
                value.value = parcel.readString();
                value.format = parcel.readInt();
                value.valid = parcel.readInt() == 1;
                return value;
            }

            @Override // android.os.Parcelable.Creator
            public Value[] newArray(int i) {
                return new Value[i];
            }
        };
        private String exponent;
        private int format = 3;
        private boolean valid = true;
        private String value;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.exponent);
            parcel.writeString(this.value);
            parcel.writeInt(this.format);
            parcel.writeInt(this.valid ? 1 : 0);
        }
    }

    public static DefaultDialogBuilder getInstance(boolean z) {
        DefaultDialogBuilder defaultDialogBuilder = new DefaultDialogBuilder();
        Bundle bundle = new Bundle();
        bundle.putBoolean(SHOW_PARSING_DISABLED_MESSAGE, z);
        defaultDialogBuilder.setArguments(bundle);
        return defaultDialogBuilder;
    }

    private byte[] getValueToSave() {
        int length;
        Iterator<Value> it = this.mValues.iterator();
        int i = 3;
        int i2 = 0;
        while (it.hasNext()) {
            Value next = it.next();
            int i3 = i + 1;
            int i4 = next.format;
            if (i4 == 0) {
                i = i3 + 1;
                i2++;
            } else if (i4 == 1) {
                if (next.value != null) {
                    length = next.value.getBytes().length;
                    i = i3 + length + 2;
                    i2 += length;
                }
                length = 0;
                i = i3 + length + 2;
                i2 += length;
            } else if (i4 != 3) {
                i = i3 + ParserUtils.getTypeLen(next.format);
                length = ParserUtils.getTypeLen(next.format);
                i2 += length;
            } else {
                if (next.value != null) {
                    length = next.value.length() / 2;
                    i = i3 + length + 2;
                    i2 += length;
                }
                length = 0;
                i = i3 + length + 2;
                i2 += length;
            }
        }
        byte[] bArr = new byte[i];
        ParserUtils.setValue(bArr, 0, i2, 18);
        bArr[2] = (byte) this.mValues.size();
        Iterator<Value> it2 = this.mValues.iterator();
        int i5 = 3;
        while (it2.hasNext()) {
            Value next2 = it2.next();
            int i6 = i5 + 1;
            bArr[i5] = (byte) next2.format;
            int i7 = next2.format;
            if (i7 == 0) {
                i5 = i6 + 1;
                bArr[i6] = Integer.valueOf(next2.value, 16).byteValue();
            } else if (i7 == 1) {
                i5 = ParserUtils.setValue(bArr, ParserUtils.setValue(bArr, i6, next2.value != null ? next2.value.getBytes().length : 0, 18), next2.value);
            } else if (i7 == 2) {
                int parseInt = Integer.parseInt(next2.value);
                int i8 = i6 + 1;
                bArr[i6] = (byte) ((parseInt >> 8) & 255);
                bArr[i8] = (byte) (parseInt & 255);
                i5 = i8 + 1;
            } else if (i7 != 3) {
                i5 = (i7 == 20 || i7 == 36) ? ParserUtils.setValue(bArr, i6, Long.parseLong(next2.value), next2.format) : (i7 == 50 || i7 == 52) ? ParserUtils.setValue(bArr, i6, Integer.parseInt(next2.value), Integer.parseInt(next2.exponent), next2.format) : ParserUtils.setValue(bArr, i6, Integer.parseInt(next2.value), next2.format);
            } else {
                i5 = ParserUtils.setByteArrayValue(bArr, ParserUtils.setValue(bArr, i6, next2.value != null ? next2.value.length() / 2 : 0, 18), next2.value);
            }
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadValues(byte[] bArr) {
        int intValue;
        this.mValues.clear();
        int i = 3;
        while (i < bArr.length) {
            Value value = new Value();
            int i2 = i + 1;
            byte b2 = bArr[i];
            value.format = b2;
            value.valid = true;
            if (b2 != 0) {
                if (b2 == 1) {
                    intValue = ParserUtils.getIntValue(bArr, 18, i2);
                    i2 += 2;
                    value.value = new String(bArr, i2, intValue);
                } else if (b2 == 2) {
                    int i3 = i2 + 1;
                    value.value = String.valueOf((bArr[i3] & FlagsParser.UNKNOWN_FLAGS) | ((bArr[i2] & FlagsParser.UNKNOWN_FLAGS) << 8));
                    i = i3 + 1;
                } else if (b2 == 3) {
                    intValue = ParserUtils.getIntValue(bArr, 18, i2);
                    i2 += 2;
                    value.value = ParserUtils.bytesToHex(bArr, i2, intValue, false);
                } else if (b2 == 50 || b2 == 52) {
                    value.value = String.valueOf(ParserUtils.getMantissa(bArr, b2, i2));
                    value.exponent = String.valueOf(ParserUtils.getExponent(bArr, b2, i2));
                    intValue = ParserUtils.getTypeLen(b2);
                } else {
                    value.value = String.valueOf(ParserUtils.getIntValue(bArr, b2, i2));
                    intValue = ParserUtils.getTypeLen(b2);
                }
                i = i2 + intValue;
            } else {
                value.value = String.format(Locale.US, "%02X", Integer.valueOf(bArr[i2] & FlagsParser.UNKNOWN_FLAGS)).toUpperCase(Locale.US);
                i = i2 + 1;
            }
            this.mValues.add(value);
        }
        this.mValuesAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] readValue(byte[] bArr) {
        byte[] bArr2 = new byte[ParserUtils.getIntValue(bArr, 18, 0)];
        int i = 3;
        int i2 = 0;
        while (i < bArr.length) {
            int i3 = i + 1;
            byte b2 = bArr[i];
            int i4 = 2;
            if (b2 != 0) {
                if (b2 != 1) {
                    if (b2 != 2) {
                        if (b2 != 3) {
                            i4 = ParserUtils.getTypeLen(b2);
                        }
                    }
                }
                i4 = ParserUtils.getIntValue(bArr, 18, i3);
                i3 += 2;
            } else {
                i4 = 1;
            }
            System.arraycopy(bArr, i3, bArr2, i2, i4);
            i = i3 + i4;
            i2 += i4;
        }
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTabSelected(int i) {
        if (i == 0) {
            this.mNewTabView.setSelected(true);
            this.mSavedTabView.setSelected(false);
            this.mNewContentView.setVisibility(0);
            this.mSavedContentView.setVisibility(8);
            setNeutralButtonVisible(0);
        } else if (i == 1) {
            hideSoftKeyboard(this.mNewContentView);
            this.mNewTabView.setSelected(false);
            this.mSavedTabView.setSelected(true);
            this.mNewContentView.setVisibility(8);
            this.mSavedContentView.setVisibility(0);
            setNeutralButtonVisible(8);
        }
        this.mDatabaseHelper.removeDeletedSavedPackets();
        this.mDismissListener.cancelDismiss();
        this.mSavedPacketsAdapter.changeCursor(this.mDatabaseHelper.getSavedPackets());
        this.mTabSelected = i;
    }

    private boolean validate() {
        int parseInt;
        int parseInt2;
        boolean z;
        boolean z2;
        Iterator<Value> it = this.mValues.iterator();
        boolean z3 = true;
        while (it.hasNext()) {
            Value next = it.next();
            int i = next.format;
            if (i == 0) {
                try {
                    Integer.valueOf(next.value, 16);
                    next.valid = next.value.matches("^[0-9a-fA-F][0-9a-fA-F]$");
                } catch (NumberFormatException unused) {
                    next.valid = false;
                }
            } else if (i == 20) {
                try {
                    long parseLong = Long.parseLong(next.value);
                    next.valid = parseLong == (4294967295L & parseLong);
                } catch (NumberFormatException unused2) {
                    next.valid = false;
                }
            } else if (i == 36) {
                try {
                    Integer.parseInt(next.value);
                } catch (NumberFormatException unused3) {
                    next.valid = false;
                }
            } else if (i == 50) {
                try {
                    int parseInt3 = Integer.parseInt(next.exponent);
                    next.valid = -14 <= parseInt3 && parseInt3 <= 15 && ((parseInt = Integer.parseInt(next.value) & (-1024)) == 0 || parseInt == -1024);
                } catch (NumberFormatException unused4) {
                    next.valid = false;
                }
            } else if (i != 52) {
                if (i != 2) {
                    if (i == 3) {
                        next.valid = TextUtils.isEmpty(next.value) || (next.value.length() % 2 == 0 && next.value.matches("^[0-9a-fA-F]+$"));
                    } else if (i == 17) {
                        try {
                            int parseInt4 = Integer.parseInt(next.value) & (-256);
                            if (parseInt4 != 0 && parseInt4 != -256) {
                                z2 = false;
                                next.valid = z2;
                            }
                            z2 = true;
                            next.valid = z2;
                        } catch (NumberFormatException unused5) {
                            next.valid = false;
                        }
                    } else if (i != 18) {
                        if (i == 33) {
                            try {
                                int parseInt5 = Integer.parseInt(next.value);
                                next.valid = -128 <= parseInt5 && parseInt5 <= 127;
                            } catch (NumberFormatException unused6) {
                                next.valid = false;
                            }
                        } else if (i != 34) {
                            next.valid = true;
                        } else {
                            try {
                                int parseInt6 = Integer.parseInt(next.value);
                                next.valid = -32768 <= parseInt6 && parseInt6 <= 32767;
                            } catch (NumberFormatException unused7) {
                                next.valid = false;
                            }
                        }
                    }
                }
                try {
                    int parseInt7 = Integer.parseInt(next.value) & (-65536);
                    if (parseInt7 != 0 && parseInt7 != -65536) {
                        z = false;
                        next.valid = z;
                    }
                    z = true;
                    next.valid = z;
                } catch (NumberFormatException unused8) {
                    next.valid = false;
                }
            } else {
                try {
                    int parseInt8 = Integer.parseInt(next.exponent);
                    next.valid = -126 <= parseInt8 && parseInt8 <= 127 && ((parseInt2 = Integer.parseInt(next.value) & (-8388608)) == 0 || parseInt2 == -8388608);
                } catch (NumberFormatException unused9) {
                    next.valid = false;
                }
            }
            if (!next.valid) {
                z3 = false;
            }
        }
        return z3;
    }

    public /* synthetic */ void a(View view) {
        setTabSelected(0);
    }

    public /* synthetic */ void b(View view) {
        setTabSelected(1);
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createTitleView(Context context, int i) {
        View inflate = this.mInflater.inflate(no.nordicsemi.android.mcp.R.layout.dialog_write_default_title, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.title)).setText(i);
        View findViewById = inflate.findViewById(no.nordicsemi.android.mcp.R.id.tab_new);
        this.mNewTabView = findViewById;
        View findViewById2 = inflate.findViewById(no.nordicsemi.android.mcp.R.id.tab_saved);
        this.mSavedTabView = findViewById2;
        if (Build.VERSION.SDK_INT < 21) {
            int color = requireActivity().getResources().getColor(no.nordicsemi.android.mcp.R.color.actionBarColorDark);
            Drawable i2 = androidx.core.graphics.drawable.a.i(findViewById.getBackground());
            androidx.core.graphics.drawable.a.b(i2, color);
            findViewById.setBackground(i2);
            Drawable i3 = androidx.core.graphics.drawable.a.i(findViewById2.getBackground());
            androidx.core.graphics.drawable.a.b(i3, color);
            findViewById2.setBackground(i3);
        }
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.ble.write.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DefaultDialogBuilder.this.a(view);
            }
        });
        findViewById2.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.ble.write.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DefaultDialogBuilder.this.b(view);
            }
        });
        setTabSelected(this.mTabSelected);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = this.mInflater.inflate(no.nordicsemi.android.mcp.R.layout.dialog_write_default, (ViewGroup) null);
        View findViewById = inflate.findViewById(no.nordicsemi.android.mcp.R.id.tab_new_content);
        this.mNewContentView = findViewById;
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        ValueAdapter valueAdapter = new ValueAdapter();
        this.mValuesAdapter = valueAdapter;
        recyclerView.setAdapter(valueAdapter);
        inflate.findViewById(no.nordicsemi.android.mcp.R.id.message).setVisibility(this.mShowParsingDisabledMessage ? 0 : 8);
        View findViewById2 = inflate.findViewById(no.nordicsemi.android.mcp.R.id.tab_saved_content);
        this.mSavedContentView = findViewById2;
        ListView listView = (ListView) findViewById2.findViewById(R.id.list);
        this.mSavedPacketsListView = listView;
        listView.setEmptyView(findViewById2.findViewById(R.id.empty));
        SavedPacketsAdapter savedPacketsAdapter = new SavedPacketsAdapter(getActivity(), this.mDatabaseHelper.getSavedPackets());
        this.mSavedPacketsAdapter = savedPacketsAdapter;
        listView.setAdapter((ListAdapter) savedPacketsAdapter);
        final SwipeDismissListViewTouchListener swipeDismissListViewTouchListener = new SwipeDismissListViewTouchListener(listView, new SwipeDismissListViewTouchListener.DismissCallbacks() { // from class: no.nordicsemi.android.mcp.ble.write.DefaultDialogBuilder.1
            @Override // no.nordicsemi.android.mcp.widget.SwipeDismissListViewTouchListener.DismissCallbacks
            public boolean canDismiss(int i) {
                return DefaultDialogBuilder.this.mSavedPacketsAdapter.canDismiss(i);
            }

            @Override // no.nordicsemi.android.mcp.widget.SwipeDismissListViewTouchListener.DismissCallbacks
            public void onDismiss(ListView listView2, int[] iArr) {
                DefaultDialogBuilder.this.mDatabaseHelper.removeDeletedSavedPackets();
                DefaultDialogBuilder.this.mSavedPacketsAdapter.changeCursor(DefaultDialogBuilder.this.mDatabaseHelper.getSavedPackets());
            }

            @Override // no.nordicsemi.android.mcp.widget.SwipeDismissListViewTouchListener.DismissCallbacks
            public void onMarkDismissed(ListView listView2, int[] iArr) {
                for (int i : iArr) {
                    DefaultDialogBuilder.this.mDatabaseHelper.deleteSavedPacket(DefaultDialogBuilder.this.mSavedPacketsAdapter.getItemId(i));
                }
                DefaultDialogBuilder.this.mSavedPacketsAdapter.changeCursor(DefaultDialogBuilder.this.mDatabaseHelper.getSavedPackets());
            }
        });
        this.mDismissListener = swipeDismissListViewTouchListener;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: no.nordicsemi.android.mcp.ble.write.h
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                DefaultDialogBuilder.a(SwipeDismissListViewTouchListener.this, adapterView, view, i, j);
            }
        });
        listView.setOnTouchListener(swipeDismissListViewTouchListener);
        listView.setOnScrollListener(swipeDismissListViewTouchListener.makeScrollListener());
        this.mNameView = (EditText) findViewById.findViewById(no.nordicsemi.android.mcp.R.id.display_name);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected int getNeutralButtonTitleResId() {
        return no.nordicsemi.android.mcp.R.string.save;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        int length;
        if (this.mTabSelected == 1) {
            return this.mSavedPacketsAdapter.getSavedData(this.mSavedPacketsListView.getCheckedItemPosition());
        }
        Iterator<Value> it = this.mValues.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            Value next = it.next();
            int i3 = next.format;
            if (i3 == 0) {
                i2++;
            } else if (i3 != 1) {
                if (i3 != 3) {
                    length = ParserUtils.getTypeLen(next.format);
                } else {
                    if (next.value != null) {
                        length = next.value.getBytes().length / 2;
                    }
                    length = 0;
                }
                i2 += length;
            } else {
                if (next.value != null) {
                    length = next.value.getBytes().length;
                    i2 += length;
                }
                length = 0;
                i2 += length;
            }
        }
        byte[] bArr = new byte[i2];
        Iterator<Value> it2 = this.mValues.iterator();
        while (it2.hasNext()) {
            Value next2 = it2.next();
            int i4 = next2.format;
            if (i4 == 0) {
                bArr[i] = Integer.valueOf(next2.value, 16).byteValue();
                i++;
            } else if (i4 == 1) {
                i = ParserUtils.setValue(bArr, i, next2.value);
            } else if (i4 == 2) {
                int parseInt = Integer.parseInt(next2.value);
                int i5 = i + 1;
                bArr[i] = (byte) ((parseInt >> 8) & 255);
                i = i5 + 1;
                bArr[i5] = (byte) (parseInt & 255);
            } else if (i4 == 3) {
                i = ParserUtils.setByteArrayValue(bArr, i, next2.value);
            } else if (i4 == 20 || i4 == 36) {
                i = ParserUtils.setValue(bArr, i, Long.parseLong(next2.value), next2.format);
            } else if (i4 == 50 || i4 == 52) {
                i = ParserUtils.setValue(bArr, i, Integer.parseInt(next2.value), Integer.parseInt(next2.exponent), next2.format);
            } else {
                i = ParserUtils.setValue(bArr, i, Integer.parseInt(next2.value), next2.format);
            }
        }
        this.mDatabaseHelper.savePacket("Auto Save", getValueToSave(), true);
        return bArr;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected void hideSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) requireActivity().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected boolean isValueValid(View view) {
        if (this.mTabSelected != 0) {
            return this.mSavedPacketsListView.getCheckedItemPosition() != -1;
        }
        this.mNameView.requestFocus();
        boolean validate = validate();
        if (!validate) {
            this.mValuesAdapter.notifyDataSetChanged();
        }
        return validate;
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        this.mDatabaseHelper = new DatabaseHelper(context);
        super.onAttach(context);
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder, androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mInflater = LayoutInflater.from(new ContextThemeWrapper(getActivity(), no.nordicsemi.android.mcp.R.style.AppTheme_DialogTheme));
        this.mResources = getResources();
        this.mShowParsingDisabledMessage = getArguments().getBoolean(SHOW_PARSING_DISABLED_MESSAGE);
        if (bundle != null) {
            this.mTabSelected = bundle.getInt(SIS_TAB_SELECTED);
            this.mValues = bundle.getParcelableArrayList(SIS_VALUES);
        } else {
            this.mValues = new ArrayList<>();
            this.mValues.add(new Value());
        }
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.mDatabaseHelper.removeDeletedSavedPackets();
        super.onDestroyView();
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected void onNeutralButtonClick(androidx.appcompat.app.d dVar, View view) {
        if (isValueValid(view)) {
            EditText editText = this.mNameView;
            if (TextUtils.isEmpty(editText.getText())) {
                editText.setError(requireActivity().getString(no.nordicsemi.android.mcp.R.string.alert_value_invalid_empty));
                editText.requestFocus();
                return;
            }
            editText.setError(null);
            hideSoftKeyboard(view);
            editText.requestFocus();
            this.mDatabaseHelper.savePacket(editText.getText().toString(), getValueToSave(), false);
            this.mSavedPacketsAdapter.changeCursor(this.mDatabaseHelper.getSavedPackets());
            this.mSavedPacketsListView.setItemChecked(this.mSavedPacketsAdapter.isFirstRecent() ? 3 : 1, true);
            setTabSelected(1);
        }
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(SIS_TAB_SELECTED, this.mTabSelected);
        bundle.putParcelableArrayList(SIS_VALUES, this.mValues);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(SwipeDismissListViewTouchListener swipeDismissListViewTouchListener, AdapterView adapterView, View view, int i, long j) {
        if (swipeDismissListViewTouchListener.getItemDismissedCount() > 0) {
            swipeDismissListViewTouchListener.dismissMarked();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ValueAdapter extends RecyclerView.g<RecyclerView.d0> {
        private static final int TYPE_FOOTER = 1;
        private static final int TYPE_ITEM = 0;
        private KeyListener hexKeyListener;
        private InputFilter[] justTwoCharacters;
        private InputFilter[] noFilter;
        private KeyListener numberKeyListener;
        private KeyListener numberSignedKeyListener;
        private KeyListener textKeyListener;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class FooterViewHolder extends RecyclerView.d0 {
            FooterViewHolder(View view) {
                super(view);
                view.findViewById(no.nordicsemi.android.mcp.R.id.action_add).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.ble.write.d
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        DefaultDialogBuilder.ValueAdapter.FooterViewHolder.this.a(view2);
                    }
                });
            }

            public /* synthetic */ void a(View view) {
                DefaultDialogBuilder.this.mValues.add(new Value());
                ValueAdapter.this.notifyItemInserted(DefaultDialogBuilder.this.mValues.size() - 1);
            }
        }

        /* loaded from: classes.dex */
        public class ViewHolder extends RecyclerView.d0 {
            View delete;
            EditText exponent;
            Spinner format;
            TextView prefix;
            EditText value;

            public ViewHolder(View view) {
                super(view);
            }
        }

        private ValueAdapter() {
            this.textKeyListener = new TextKeyListener(TextKeyListener.Capitalize.NONE, false);
            this.numberKeyListener = new NumberKeyListener() { // from class: no.nordicsemi.android.mcp.ble.write.DefaultDialogBuilder.ValueAdapter.2
                @Override // no.nordicsemi.android.mcp.widget.NumberKeyListener, android.text.method.NumberKeyListener
                protected char[] getAcceptedChars() {
                    return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
                }

                @Override // no.nordicsemi.android.mcp.widget.NumberKeyListener, android.text.method.KeyListener
                public int getInputType() {
                    return 524434;
                }
            };
            this.numberSignedKeyListener = new NumberKeyListener();
            this.hexKeyListener = new HexKeyListener();
            this.justTwoCharacters = new InputFilter[]{new InputFilter.LengthFilter(2)};
            this.noFilter = new InputFilter[0];
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void a(View view, boolean z) {
            if (z) {
                return;
            }
            EditText editText = (EditText) view;
            ((Value) view.getTag()).exponent = editText.getText().toString();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void b(View view, boolean z) {
            if (z) {
                return;
            }
            EditText editText = (EditText) view;
            ((Value) view.getTag()).value = editText.getText().toString();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            return DefaultDialogBuilder.this.mValues.size() + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemViewType(int i) {
            return i < DefaultDialogBuilder.this.mValues.size() ? 0 : 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void onBindViewHolder(RecyclerView.d0 d0Var, int i) {
            if (getItemViewType(i) == 0) {
                ViewHolder viewHolder = (ViewHolder) d0Var;
                Value value = (Value) DefaultDialogBuilder.this.mValues.get(i);
                int position = ((FormatAdapter) viewHolder.format.getAdapter()).getPosition(value.format);
                viewHolder.exponent.setTag(value);
                viewHolder.value.setTag(value);
                viewHolder.format.setTag(value);
                viewHolder.delete.setTag(value);
                AdapterView.OnItemSelectedListener onItemSelectedListener = viewHolder.format.getOnItemSelectedListener();
                viewHolder.format.setOnItemSelectedListener(null);
                viewHolder.format.setSelection(position);
                viewHolder.format.setOnItemSelectedListener(onItemSelectedListener);
                viewHolder.exponent.setText(value.exponent);
                viewHolder.exponent.setVisibility(8);
                viewHolder.value.setText(value.value);
                viewHolder.value.setHint(no.nordicsemi.android.mcp.R.string.alert_hint);
                viewHolder.prefix.setVisibility(4);
                int i2 = value.format;
                if (i2 == 0) {
                    viewHolder.prefix.setVisibility(0);
                    viewHolder.value.setKeyListener(this.hexKeyListener);
                    viewHolder.value.setFilters(this.justTwoCharacters);
                } else if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 == 3) {
                            viewHolder.prefix.setVisibility(0);
                            viewHolder.value.setFilters(this.noFilter);
                            viewHolder.value.setKeyListener(this.hexKeyListener);
                        } else if (i2 != 17 && i2 != 18 && i2 != 20) {
                            if (i2 != 50 && i2 != 52) {
                                viewHolder.value.setFilters(this.noFilter);
                                viewHolder.value.setKeyListener(this.numberSignedKeyListener);
                            } else {
                                viewHolder.value.setHint(no.nordicsemi.android.mcp.R.string.alert_hint_mantissa);
                                viewHolder.exponent.setVisibility(0);
                                viewHolder.value.setFilters(this.noFilter);
                                viewHolder.value.setKeyListener(this.numberSignedKeyListener);
                            }
                        }
                    }
                    viewHolder.value.setFilters(this.noFilter);
                    viewHolder.value.setKeyListener(this.numberKeyListener);
                } else {
                    viewHolder.value.setFilters(this.noFilter);
                    viewHolder.value.setKeyListener(this.textKeyListener);
                }
                viewHolder.delete.setVisibility(i != 0 ? 0 : 4);
                if (!value.valid) {
                    viewHolder.value.setError(DefaultDialogBuilder.this.getString(TextUtils.isEmpty(value.value) ? no.nordicsemi.android.mcp.R.string.alert_value_invalid_empty : no.nordicsemi.android.mcp.R.string.alert_value_invalid));
                } else {
                    viewHolder.value.setError(null);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public RecyclerView.d0 onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i == 0) {
                View inflate = DefaultDialogBuilder.this.mInflater.inflate(no.nordicsemi.android.mcp.R.layout.dialog_write_default_item, viewGroup, false);
                ViewHolder viewHolder = new ViewHolder(inflate);
                viewHolder.prefix = (TextView) inflate.findViewById(no.nordicsemi.android.mcp.R.id.prefix);
                viewHolder.exponent = (EditText) inflate.findViewById(no.nordicsemi.android.mcp.R.id.exponent);
                viewHolder.exponent.setKeyListener(this.numberSignedKeyListener);
                viewHolder.value = (EditText) inflate.findViewById(no.nordicsemi.android.mcp.R.id.value);
                viewHolder.exponent.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: no.nordicsemi.android.mcp.ble.write.e
                    @Override // android.view.View.OnFocusChangeListener
                    public final void onFocusChange(View view, boolean z) {
                        DefaultDialogBuilder.ValueAdapter.a(view, z);
                    }
                });
                viewHolder.value.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: no.nordicsemi.android.mcp.ble.write.c
                    @Override // android.view.View.OnFocusChangeListener
                    public final void onFocusChange(View view, boolean z) {
                        DefaultDialogBuilder.ValueAdapter.b(view, z);
                    }
                });
                viewHolder.format = (Spinner) inflate.findViewById(no.nordicsemi.android.mcp.R.id.format);
                viewHolder.format.setAdapter((SpinnerAdapter) new FormatAdapter());
                viewHolder.format.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: no.nordicsemi.android.mcp.ble.write.DefaultDialogBuilder.ValueAdapter.1
                    @Override // android.widget.AdapterView.OnItemSelectedListener
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
                        Value value = (Value) adapterView.getTag();
                        int i3 = (int) j;
                        if (value.format != i3) {
                            value.format = i3;
                            ValueAdapter valueAdapter = ValueAdapter.this;
                            valueAdapter.notifyItemChanged(DefaultDialogBuilder.this.mValues.indexOf(value));
                        }
                    }

                    @Override // android.widget.AdapterView.OnItemSelectedListener
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
                viewHolder.delete = inflate.findViewById(no.nordicsemi.android.mcp.R.id.delete);
                viewHolder.delete.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.ble.write.f
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        DefaultDialogBuilder.ValueAdapter.this.a(view);
                    }
                });
                return viewHolder;
            }
            return new FooterViewHolder(DefaultDialogBuilder.this.mInflater.inflate(no.nordicsemi.android.mcp.R.layout.dialog_write_default_footer, viewGroup, false));
        }

        public /* synthetic */ void a(View view) {
            if (DefaultDialogBuilder.this.mValues.size() == 1) {
                return;
            }
            Value value = (Value) view.getTag();
            int indexOf = DefaultDialogBuilder.this.mValues.indexOf(value);
            DefaultDialogBuilder.this.mValues.remove(value);
            notifyItemRemoved(indexOf);
        }
    }
}
