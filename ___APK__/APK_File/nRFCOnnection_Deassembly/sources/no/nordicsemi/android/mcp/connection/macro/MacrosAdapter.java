package no.nordicsemi.android.mcp.connection.macro;

import a.k.a.a;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.connection.macro.MacrosAdapter;
import no.nordicsemi.android.mcp.connection.macro.domain.Macro;
import no.nordicsemi.android.mcp.connection.macro.domain.MacroHandler;
import no.nordicsemi.android.mcp.connection.macro.loader.LoadMacroLoader;
import no.nordicsemi.android.mcp.connection.macro.loader.MacroLoaderResult;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.widget.CursorRecyclerAdapter;
import no.nordicsemi.android.mcp.widget.RemovableViewHolder;
import no.nordicsemi.android.mcp.widget.ViewAnimator;
import no.nordicsemi.android.mcp.widget.connection.macro.MacroItemView;
import no.nordicsemi.android.mcp.widget.connection.macro.MacroViewAnimator;

/* loaded from: classes.dex */
public class MacrosAdapter extends CursorRecyclerAdapter<ViewHolder> implements Macro.NewOperationObserver, ViewAnimator.ExpandCollapseListener {
    private static final String ANIMATORS = "macro_animators";
    public static final String MACRO_GROUP = "macro_group";
    private static final long RECORDED_MACRO_ID = 92323278743L;
    private static final int TYPE_GROUP = 1;
    private static final int TYPE_MACRO = 0;
    private IBluetoothLeConnection mConnection;
    private long mCurrentGroupId;
    private Macro mCurrentlyRecordedMacro;
    private DatabaseHelper mDatabaseHelper;
    private Handler mHandler;
    private MacroActionListener mMacroActionListener;
    private Map<String, MacroViewAnimator> mMacroViewAnimators;
    private final BroadcastReceiver mRunningUpdateBroadcastReceiver;
    private final BroadcastReceiver mUpdateBroadcastReceiver;
    private Runnable notifyFirstChangedTask;

    /* loaded from: classes.dex */
    public interface MacroActionListener {
        void onExportMacro(long j);

        void onMacroGroupOpened(long j, String str, int i);

        void onMirrorMacro(Macro macro);

        void onMoveMacro(int i, long j);

        void onRenameMacro(int i, long j, Macro macro);

        void onRenameMacroGroup(int i, long j, String str);

        void onScrollToMacro(int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class MacroGroupViewHolder extends ViewHolder {
        private int count;
        private TextView detailsView;
        private String groupName;
        private TextView nameView;

        public /* synthetic */ void a(MacroActionListener macroActionListener, View view) {
            if (macroActionListener != null) {
                macroActionListener.onMacroGroupOpened(getItemId(), this.groupName, this.count);
            }
        }

        @Override // no.nordicsemi.android.mcp.connection.macro.MacrosAdapter.ViewHolder
        protected void assign(Cursor cursor) {
            this.groupName = cursor.getString(1);
            this.nameView.setText(this.groupName);
            this.count = cursor.getInt(3);
            TextView textView = this.detailsView;
            Resources resources = textView.getResources();
            int i = this.count;
            textView.setText(resources.getQuantityString(R.plurals.macros_group_details, i, Integer.valueOf(i)));
        }

        public /* synthetic */ void b(final MacroActionListener macroActionListener, View view) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.getMenuInflater().inflate(R.menu.macro_group, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: no.nordicsemi.android.mcp.connection.macro.d
                @Override // android.widget.PopupMenu.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    return MacrosAdapter.MacroGroupViewHolder.this.a(macroActionListener, menuItem);
                }
            });
            popupMenu.show();
        }

        private MacroGroupViewHolder(View view, final MacroActionListener macroActionListener) {
            super(view);
            getSwipeableView().setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.macro.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    MacrosAdapter.MacroGroupViewHolder.this.a(macroActionListener, view2);
                }
            });
            this.nameView = (TextView) view.findViewById(R.id.display_name);
            this.detailsView = (TextView) view.findViewById(R.id.details);
            this.moreAction.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.macro.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    MacrosAdapter.MacroGroupViewHolder.this.b(macroActionListener, view2);
                }
            });
        }

        public /* synthetic */ boolean a(MacroActionListener macroActionListener, MenuItem menuItem) {
            int itemId = menuItem.getItemId();
            if (itemId == R.id.action_export) {
                if (macroActionListener != null) {
                    macroActionListener.onExportMacro(getItemId());
                }
                return true;
            }
            if (itemId == R.id.action_move) {
                if (macroActionListener != null) {
                    macroActionListener.onMoveMacro(getAdapterPosition(), getItemId());
                }
                return true;
            }
            if (itemId != R.id.action_rename) {
                return false;
            }
            if (macroActionListener != null) {
                macroActionListener.onRenameMacroGroup(getAdapterPosition(), getItemId(), this.groupName);
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class MacroViewHolder extends ViewHolder implements a.InterfaceC0025a<MacroLoaderResult> {
        private MacroViewAnimator animator;
        private MacroItemView view;

        public /* synthetic */ void a(final MacroActionListener macroActionListener, View view) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.getMenuInflater().inflate(R.menu.macro, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: no.nordicsemi.android.mcp.connection.macro.g
                @Override // android.widget.PopupMenu.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    return MacrosAdapter.MacroViewHolder.this.a(macroActionListener, menuItem);
                }
            });
            popupMenu.show();
        }

        @Override // no.nordicsemi.android.mcp.connection.macro.MacrosAdapter.ViewHolder
        protected void assign(Cursor cursor) {
            int i = (int) cursor.getLong(0);
            String string = cursor.getString(1);
            String valueOf = String.valueOf(i);
            MacroViewAnimator macroViewAnimator = (MacroViewAnimator) MacrosAdapter.this.mMacroViewAnimators.get(valueOf);
            if (macroViewAnimator == null) {
                macroViewAnimator = new MacroViewAnimator(valueOf);
                MacrosAdapter.this.mMacroViewAnimators.put(valueOf, macroViewAnimator);
            }
            this.animator = macroViewAnimator;
            this.moreAction.setVisibility(0);
            try {
                macroViewAnimator.checkException();
                macroViewAnimator.setExpandCollapseListener(MacrosAdapter.this, cursor.getPosition());
                Macro macro = macroViewAnimator.getMacro();
                if (macro == null) {
                    MacroHandler macroStatus = MacrosAdapter.this.mConnection.getMacroStatus(i);
                    if (macroStatus != null) {
                        macro = macroStatus.getMacro();
                        macroViewAnimator.setMacro(macro);
                    } else {
                        this.view.setMacroLoading(i, string, macroViewAnimator);
                        a.k.a.a.a((androidx.appcompat.app.e) this.view.getContext()).b(i + 1000, null, this);
                        return;
                    }
                }
                if (MacrosAdapter.this.mConnection != null && !MacrosAdapter.this.mConnection.areServicesDiscovered()) {
                    macro.invalidate();
                }
                this.view.setMacro(i, macro, macroViewAnimator);
            } catch (Exception e2) {
                macroViewAnimator.setMacro(null);
                this.view.setMacro(i, e2, macroViewAnimator);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // no.nordicsemi.android.mcp.widget.RemovableViewHolder
        public boolean isRemovable() {
            return this.view.getMacro() == null || !(this.view.getMacro().isRecorded() || MacrosAdapter.this.mConnection.isMacroTracked((int) getItemId()));
        }

        @Override // a.k.a.a.InterfaceC0025a
        public a.k.b.c<MacroLoaderResult> onCreateLoader(int i, Bundle bundle) {
            return new LoadMacroLoader(this.view.getContext(), MacrosAdapter.this.mDatabaseHelper, i - 1000, getAdapterPosition());
        }

        @Override // a.k.a.a.InterfaceC0025a
        public /* bridge */ /* synthetic */ void onLoadFinished(a.k.b.c cVar, Object obj) {
            onLoadFinished((a.k.b.c<MacroLoaderResult>) cVar, (MacroLoaderResult) obj);
        }

        @Override // a.k.a.a.InterfaceC0025a
        public void onLoaderReset(a.k.b.c<MacroLoaderResult> cVar) {
        }

        @Override // no.nordicsemi.android.mcp.connection.macro.MacrosAdapter.ViewHolder
        protected void onViewAttached() {
            this.animator.bindView(this.view.getMainView(), this.view.getExpandableView());
        }

        @Override // no.nordicsemi.android.mcp.connection.macro.MacrosAdapter.ViewHolder
        protected void onViewDetached() {
            this.animator.unbindView();
        }

        private MacroViewHolder(MacroItemView macroItemView, final MacroActionListener macroActionListener) {
            super(macroItemView);
            this.view = macroItemView;
            this.moreAction.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.macro.f
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MacrosAdapter.MacroViewHolder.this.a(macroActionListener, view);
                }
            });
        }

        public void onLoadFinished(a.k.b.c<MacroLoaderResult> cVar, MacroLoaderResult macroLoaderResult) {
            Macro macro = macroLoaderResult.macro;
            if (macro != null) {
                this.animator.setMacro(macro);
                if (MacrosAdapter.this.mConnection != null && !MacrosAdapter.this.mConnection.areServicesDiscovered()) {
                    macroLoaderResult.macro.invalidate();
                }
                this.view.setMacro(cVar.getId() - 1000, macroLoaderResult.macro, this.animator);
            } else {
                this.animator.setMacro(null);
                this.view.setMacro(cVar.getId() - 1000, macroLoaderResult.exception, this.animator);
            }
            a.k.a.a.a((androidx.appcompat.app.e) this.view.getContext()).a(cVar.getId());
        }

        public /* synthetic */ boolean a(MacroActionListener macroActionListener, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_export /* 2131296357 */:
                    if (macroActionListener != null) {
                        macroActionListener.onExportMacro(getItemId());
                    }
                    return true;
                case R.id.action_mirror /* 2131296373 */:
                    if (macroActionListener != null) {
                        macroActionListener.onMirrorMacro(this.view.getMacro());
                    }
                    return true;
                case R.id.action_move /* 2131296378 */:
                    if (macroActionListener != null) {
                        macroActionListener.onMoveMacro(getAdapterPosition(), getItemId());
                    }
                    return true;
                case R.id.action_rename /* 2131296397 */:
                    if (macroActionListener != null) {
                        macroActionListener.onRenameMacro(getAdapterPosition(), getItemId(), this.view.getMacro());
                    }
                    return true;
                default:
                    return false;
            }
        }
    }

    /* loaded from: classes.dex */
    public abstract class ViewHolder extends RemovableViewHolder {
        View moreAction;

        public ViewHolder(View view) {
            super(view);
            this.moreAction = view.findViewById(R.id.action_more);
        }

        protected abstract void assign(Cursor cursor);

        protected void onViewAttached() {
        }

        protected void onViewDetached() {
        }
    }

    public MacrosAdapter(MacroActionListener macroActionListener) {
        super(null);
        this.mCurrentGroupId = -1L;
        this.notifyFirstChangedTask = new Runnable() { // from class: no.nordicsemi.android.mcp.connection.macro.h
            @Override // java.lang.Runnable
            public final void run() {
                MacrosAdapter.this.a();
            }
        };
        this.mUpdateBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.connection.macro.MacrosAdapter.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                long longExtra = intent.getLongExtra("id", 0L);
                Cursor cursor = MacrosAdapter.this.getCursor();
                cursor.moveToFirst();
                do {
                    if (cursor.getLong(0) == longExtra) {
                        MacrosAdapter.this.notifyItemChanged(cursor.getPosition() + (MacrosAdapter.this.mCurrentlyRecordedMacro != null ? 1 : 0));
                        return;
                    }
                } while (cursor.moveToNext());
                MacrosAdapter.this.notifyDataSetChanged();
            }
        };
        this.mRunningUpdateBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.connection.macro.MacrosAdapter.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                MacrosAdapter.this.notifyDataSetChanged();
            }
        };
        this.mMacroActionListener = macroActionListener;
        this.mHandler = new Handler();
        setHasStableIds(true);
    }

    public /* synthetic */ void a() {
        notifyItemChanged(0);
    }

    @Override // no.nordicsemi.android.mcp.widget.CursorRecyclerAdapter, androidx.recyclerview.widget.RecyclerView.g
    public int getItemCount() {
        int itemCount = super.getItemCount();
        return this.mCurrentlyRecordedMacro == null ? itemCount : itemCount + 1;
    }

    @Override // no.nordicsemi.android.mcp.widget.CursorRecyclerAdapter, androidx.recyclerview.widget.RecyclerView.g
    public long getItemId(int i) {
        if (this.mCurrentlyRecordedMacro == null) {
            return super.getItemId(i);
        }
        return i == 0 ? RECORDED_MACRO_ID : super.getItemId(i - 1);
    }

    @Override // no.nordicsemi.android.mcp.widget.CursorRecyclerAdapter, androidx.recyclerview.widget.RecyclerView.g
    public int getItemViewType(int i) {
        if (this.mCurrentlyRecordedMacro == null) {
            return super.getItemViewType(i);
        }
        if (i > 0) {
            return super.getItemViewType(i - 1);
        }
        return 0;
    }

    public void onItemAdded() {
        DatabaseHelper databaseHelper = this.mDatabaseHelper;
        if (databaseHelper != null) {
            changeCursor(databaseHelper.getMacrosNames(this.mCurrentGroupId));
            notifyItemInserted(0);
        }
    }

    public void onItemChangedAt(int i) {
        DatabaseHelper databaseHelper = this.mDatabaseHelper;
        if (databaseHelper != null) {
            changeCursor(databaseHelper.getMacrosNames(this.mCurrentGroupId));
            notifyItemChanged(i);
        }
    }

    public void onItemMoved() {
        DatabaseHelper databaseHelper = this.mDatabaseHelper;
        if (databaseHelper != null) {
            changeCursor(databaseHelper.getMacrosNames(this.mCurrentGroupId));
            notifyDataSetChanged();
        }
    }

    public void onItemRemovedAt(int i) {
        DatabaseHelper databaseHelper = this.mDatabaseHelper;
        if (databaseHelper != null) {
            changeCursor(databaseHelper.getMacrosNames(this.mCurrentGroupId));
            notifyItemRemoved(i);
        }
    }

    public void onItemRestoredAt(int i) {
        DatabaseHelper databaseHelper = this.mDatabaseHelper;
        if (databaseHelper != null) {
            changeCursor(databaseHelper.getMacrosNames(this.mCurrentGroupId));
            notifyItemInserted(i);
        }
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Macro.NewOperationObserver
    public void onOperationAdded() {
        this.mHandler.post(this.notifyFirstChangedTask);
    }

    public void onServiceConnected(Context context, DatabaseHelper databaseHelper, IBluetoothLeConnection iBluetoothLeConnection) {
        this.mDatabaseHelper = databaseHelper;
        this.mConnection = iBluetoothLeConnection;
        this.mMacroViewAnimators = (Map) iBluetoothLeConnection.get(ANIMATORS);
        if (this.mMacroViewAnimators == null) {
            this.mMacroViewAnimators = new HashMap();
            iBluetoothLeConnection.put(ANIMATORS, this.mMacroViewAnimators);
        }
        a.l.a.a.a(context).a(this.mUpdateBroadcastReceiver, new IntentFilter(MacroHandler.ACTION_MACRO_UPDATE + iBluetoothLeConnection.getDevice().getAddress(null)));
        a.l.a.a.a(context).a(this.mRunningUpdateBroadcastReceiver, new IntentFilter(MacroHandler.ACTION_MACRO_RUNNING_UPDATE + iBluetoothLeConnection.getDevice().getAddress(null)));
    }

    @Override // no.nordicsemi.android.mcp.widget.ViewAnimator.ExpandCollapseListener
    public void onViewCollapsed(int i) {
    }

    @Override // no.nordicsemi.android.mcp.widget.ViewAnimator.ExpandCollapseListener
    public void onViewExpanded(int i) {
        if (this.mCurrentlyRecordedMacro != null) {
            i++;
        }
        MacroActionListener macroActionListener = this.mMacroActionListener;
        if (macroActionListener != null) {
            macroActionListener.onScrollToMacro(i);
        }
    }

    public void setCurrentMacro(int i, Macro macro) {
        if (macro != null) {
            this.mCurrentlyRecordedMacro = macro;
            macro.setRecorderObserver(this);
            notifyItemInserted(0);
            return;
        }
        Macro macro2 = this.mCurrentlyRecordedMacro;
        if (macro2 == null) {
            onItemChangedAt(i);
            return;
        }
        macro2.setRecorderObserver(null);
        this.mCurrentlyRecordedMacro = null;
        this.mMacroViewAnimators.remove(String.valueOf(RECORDED_MACRO_ID));
        if (i >= 0) {
            onItemChangedAt(0);
        } else {
            notifyItemRemoved(0);
        }
    }

    public void setGroup(long j) {
        changeCursor(this.mDatabaseHelper.getMacrosNames(j));
        this.mCurrentGroupId = j;
        notifyDataSetChanged();
    }

    public void unregisterBroadcastReceiver(Context context) {
        a.l.a.a.a(context).a(this.mUpdateBroadcastReceiver);
        a.l.a.a.a(context).a(this.mRunningUpdateBroadcastReceiver);
    }

    public void updateMacro(long j, String str, Macro.Icon icon) {
        MacroViewAnimator macroViewAnimator;
        Macro macro;
        if (j <= 0 || (macroViewAnimator = this.mMacroViewAnimators.get(String.valueOf(j))) == null || (macro = macroViewAnimator.getMacro()) == null) {
            return;
        }
        macro.setName(str);
        macro.setIcon(icon);
    }

    @Override // no.nordicsemi.android.mcp.widget.CursorRecyclerAdapter, androidx.recyclerview.widget.RecyclerView.g
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (this.mCurrentlyRecordedMacro == null) {
            super.onBindViewHolder((MacrosAdapter) viewHolder, i);
            return;
        }
        if (i > 0) {
            super.onBindViewHolder((MacrosAdapter) viewHolder, i - 1);
            return;
        }
        MacroViewHolder macroViewHolder = (MacroViewHolder) viewHolder;
        String valueOf = String.valueOf(RECORDED_MACRO_ID);
        MacroViewAnimator macroViewAnimator = this.mMacroViewAnimators.get(valueOf);
        if (macroViewAnimator == null) {
            macroViewAnimator = new MacroViewAnimator(valueOf);
            this.mMacroViewAnimators.put(valueOf, macroViewAnimator);
        }
        macroViewAnimator.expand(true);
        macroViewHolder.animator = macroViewAnimator;
        macroViewHolder.moreAction.setVisibility(8);
        macroViewHolder.view.setMacro(0, this.mCurrentlyRecordedMacro, macroViewAnimator);
    }

    @Override // no.nordicsemi.android.mcp.widget.CursorRecyclerAdapter
    public void onBindViewHolderCursor(ViewHolder viewHolder, Cursor cursor) {
        viewHolder.assign(cursor);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 0) {
            return new MacroGroupViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.macro_group_item, viewGroup, false), this.mMacroActionListener);
        }
        MacroItemView macroItemView = (MacroItemView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.macro_item, viewGroup, false);
        macroItemView.setConnection(this.mConnection);
        return new MacroViewHolder(macroItemView, this.mMacroActionListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onViewAttachedToWindow(ViewHolder viewHolder) {
        viewHolder.onViewAttached();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onViewDetachedFromWindow(ViewHolder viewHolder) {
        viewHolder.onViewDetached();
    }

    @Override // no.nordicsemi.android.mcp.widget.CursorRecyclerAdapter
    public int getItemViewType(Cursor cursor) {
        return cursor.getInt(2);
    }
}
