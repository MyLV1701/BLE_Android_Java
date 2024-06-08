package no.nordicsemi.android.mcp.connection;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.d;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.androidplot.util.PixelUtils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import no.nordicsemi.android.log.LogContract;
import no.nordicsemi.android.mcp.MainActivity;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.TabsAdapter;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.connection.macro.AddConditionalSleepDialogFragment;
import no.nordicsemi.android.mcp.connection.macro.AddDelayDialogFragment;
import no.nordicsemi.android.mcp.connection.macro.MacrosAdapter;
import no.nordicsemi.android.mcp.connection.macro.MoveMacroDialogFragment;
import no.nordicsemi.android.mcp.connection.macro.SaveGroupDialogFragment;
import no.nordicsemi.android.mcp.connection.macro.SaveMacroDialogFragment;
import no.nordicsemi.android.mcp.connection.macro.domain.Macro;
import no.nordicsemi.android.mcp.connection.macro.domain.Sleep;
import no.nordicsemi.android.mcp.connection.macro.domain.SleepIf;
import no.nordicsemi.android.mcp.connection.macro.domain.SleepUntil;
import no.nordicsemi.android.mcp.connection.macro.loader.ImportMacroLoader;
import no.nordicsemi.android.mcp.connection.macro.loader.MacroLoaderResult;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.fragment.ProgressDialogFragment;
import no.nordicsemi.android.mcp.util.FileHelper;
import no.nordicsemi.android.mcp.widget.ItemTouchHelperAdapter;
import no.nordicsemi.android.mcp.widget.RemovableItemTouchHelperCallback;
import no.nordicsemi.android.mcp.widget.RemovableViewHolder;
import no.nordicsemi.android.mcp.widget.SlidingPaneLayout;
import no.nordicsemi.android.mcp.widget.connection.CharacteristicView;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.HyphenStyle;

/* loaded from: classes.dex */
public class MacroFragment extends DfuFragment implements SaveMacroDialogFragment.Listener, SaveGroupDialogFragment.Listener, MoveMacroDialogFragment.Listener, MacrosAdapter.MacroActionListener, AddDelayDialogFragment.Listener, SlidingPaneLayout.PanelSlideListener, NewMacrosListener, AddConditionalSleepDialogFragment.Listener, ItemTouchHelperAdapter, MainActivity.OnBackPressedListener, TabsAdapter.TabListener, CharacteristicView.OnConditionalSleepListener {
    private static final int LOAD_IMPORT_MACRO_LOADER_REQ = 10;
    private static final int REQUEST_EXPORT = 5;
    private static final int SELECT_MACRO_FILE_REQ = 4;
    private static final String SIS_CURRENT_MACRO_GROUP = "current_macro_group";
    private static final String SIS_MACRO_FAB_VISIBLE = "macro_fab_visible";
    private static final String SIS_MACRO_TO_BE_EXPORTED = "macro_to_be_exported";
    private static final String TAG = "MacroFragment";
    private View mBottomSheetBack;
    private BottomSheetBehavior mBottomSheetBehavior;
    private TextView mBottomSheetTitle;
    private ViewGroup mBottomSheetView;
    private long mCurrentMacroGroupId;
    protected DatabaseHelper mDatabaseHelper;
    private long mMacroIdToBeExported;
    private View mMacroRecordingIndicator;
    private MacrosAdapter mMacrosAdapter;
    private View mMacrosEmptyView;
    private FloatingActionButton mMacrosFab;
    private RecyclerView mMacrosRecyclerView;
    private FloatingActionButton mNewMacroFab;
    private FloatingActionButton mSecondaryFab;

    private void onBackToParentGroup() {
        Cursor macro = this.mDatabaseHelper.getMacro(this.mCurrentMacroGroupId);
        try {
            if (macro.moveToNext()) {
                long j = macro.getLong(3);
                if (j > 0) {
                    Cursor macro2 = this.mDatabaseHelper.getMacro(j);
                    try {
                        if (macro2.moveToNext()) {
                            onMacroGroupOpened(j, macro2.getString(1), 1);
                        }
                        if (macro2 != null) {
                            macro2.close();
                        }
                    } finally {
                    }
                } else {
                    onMacroGroupOpened(0L, null, 1);
                }
            }
            if (macro != null) {
                macro.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (macro != null) {
                    try {
                        macro.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    private void onImportMacro() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("text/xml");
        intent.addCategory("android.intent.category.OPENABLE");
        if (intent.resolveActivity(requireContext().getPackageManager()) != null) {
            startActivityForResult(intent, 4);
            return;
        }
        View inflate = requireActivity().getLayoutInflater().inflate(R.layout.app_file_browser, (ViewGroup) null);
        final ListView listView = (ListView) inflate.findViewById(android.R.id.list);
        listView.setAdapter((ListAdapter) new FileBrowserAppsAdapter(requireContext()));
        listView.setChoiceMode(1);
        listView.setItemChecked(0, true);
        d.a aVar = new d.a(requireContext());
        aVar.c(R.string.dfu_alert_no_filebrowser_title);
        aVar.b(inflate);
        aVar.a(R.string.no, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.c0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        aVar.c(R.string.yes, new DialogInterface.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.x
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MacroFragment.this.b(listView, dialogInterface, i);
            }
        });
        aVar.c();
    }

    private void refreshCurrentMacro() {
        IBluetoothLeConnection iBluetoothLeConnection = this.mConnection;
        if (iBluetoothLeConnection != null) {
            this.mCurrentMacroGroupId = 0L;
            if (iBluetoothLeConnection.containsKey(MacrosAdapter.MACRO_GROUP)) {
                this.mCurrentMacroGroupId = ((Long) this.mConnection.get(MacrosAdapter.MACRO_GROUP)).longValue();
                String macroName = this.mDatabaseHelper.getMacroName(this.mCurrentMacroGroupId);
                if (macroName != null) {
                    this.mBottomSheetBack.setVisibility(0);
                    this.mBottomSheetTitle.setText(macroName);
                } else {
                    this.mBottomSheetBack.setVisibility(8);
                    this.mBottomSheetTitle.setText(R.string.macros_title);
                    this.mCurrentMacroGroupId = 0L;
                }
            } else {
                this.mBottomSheetTitle.setText(R.string.macros_title);
            }
            this.mMacrosAdapter.setGroup(this.mCurrentMacroGroupId);
            if (this.mMacrosAdapter.getItemCount() == 0) {
                this.mMacrosEmptyView.setVisibility(0);
            } else {
                this.mMacrosEmptyView.setVisibility(8);
            }
        }
    }

    private void startRecordingMacro() {
        this.mMacrosAdapter.setCurrentMacro(0, this.mConnection.startRecordingMacro());
        if (canShowMacros()) {
            this.mBottomSheetBehavior.b(false);
            this.mBottomSheetBehavior.e(4);
        } else {
            this.mBottomSheetBehavior.b(true);
            this.mBottomSheetBehavior.e(5);
        }
        onMacroStateChanged();
        this.mNewMacroFab.setImageResource(R.drawable.fab_ic_stop);
        this.mMacroRecordingIndicator.setVisibility(0);
        this.mSecondaryFab.setImageResource(R.drawable.fab_ic_plus_1s);
        this.mMacrosEmptyView.setVisibility(8);
    }

    private void stopRecordingMacro() {
        IBluetoothLeConnection iBluetoothLeConnection = this.mConnection;
        if (iBluetoothLeConnection == null || !iBluetoothLeConnection.isRecordingMacro()) {
            return;
        }
        Macro stopRecordingMacro = this.mConnection.stopRecordingMacro();
        if (!stopRecordingMacro.getOperations().isEmpty()) {
            onRenameMacro(0, 0L, stopRecordingMacro);
        } else {
            onMacroCancelled();
        }
        onMacroStateChanged();
        this.mBottomSheetBehavior.b(true);
        this.mBottomSheetBehavior.e(3);
        this.mNewMacroFab.setImageResource(R.drawable.fab_ic_rec);
        this.mSecondaryFab.setImageResource(R.drawable.fab_ic_import);
        this.mMacroRecordingIndicator.setVisibility(8);
        refreshCurrentMacro();
    }

    public /* synthetic */ void a(View view) {
        onBackToParentGroup();
    }

    public /* synthetic */ void b(View view) {
        if (this.mConnection.isRecordingMacro()) {
            new AddDelayDialogFragment().show(getChildFragmentManager(), (String) null);
        } else {
            onImportMacro();
        }
    }

    public /* synthetic */ void c(View view) {
        SaveGroupDialogFragment.getInstance(this.mCurrentMacroGroupId).show(getChildFragmentManager(), (String) null);
    }

    boolean canShowMacros() {
        return true;
    }

    public /* synthetic */ void d(View view) {
        if (this.mConnection.isRecordingMacro()) {
            stopRecordingMacro();
        } else {
            if (!this.mConnection.areServicesDiscovered()) {
                Toast.makeText(requireContext(), R.string.macros_services_not_discovered, 0).show();
                return;
            }
            startRecordingMacro();
        }
        this.mMacrosRecyclerView.requestLayout();
        this.mMacrosRecyclerView.smoothScrollToPosition(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.DfuFragment
    public boolean ensureNoMacro() {
        if (this.mConnection.isMacroRunning()) {
            Toast.makeText(requireContext(), R.string.macros_macro_is_running, 0).show();
            return false;
        }
        if (!this.mConnection.isRecordingMacro()) {
            return true;
        }
        Toast.makeText(requireContext(), R.string.macros_macro_is_recording, 0).show();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void hideMacros() {
        this.mBottomSheetBehavior.b(true);
        this.mBottomSheetBehavior.e(5);
        this.mMacrosFab.b();
    }

    @Override // no.nordicsemi.android.mcp.connection.DfuFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        Uri data = intent != null ? intent.getData() : null;
        if (i2 == -1 && data != null) {
            if (i != 4) {
                if (i == 5) {
                    Pair<String, String> macroOrGroupXml = this.mDatabaseHelper.getMacroOrGroupXml(this.mMacroIdToBeExported);
                    this.mMacroIdToBeExported = 0L;
                    if (macroOrGroupXml != null) {
                        String str = (String) macroOrGroupXml.second;
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(requireContext().getContentResolver().openOutputStream(data));
                            outputStreamWriter.append((CharSequence) str);
                            outputStreamWriter.close();
                            Toast.makeText(requireContext(), R.string.file_saved, 0).show();
                        } catch (Exception e2) {
                            Log.e(TAG, "Saving macro failed", e2);
                            Toast.makeText(requireContext(), R.string.file_save_error, 0).show();
                        }
                    } else {
                        Toast.makeText(requireContext(), "Macro does not exist.", 0).show();
                        return;
                    }
                }
            } else if (data.getScheme().equals("file")) {
                String path = data.getPath();
                Bundle bundle = new Bundle();
                bundle.putString("no.nordicsemi.android.mcp.FILE_PATH", path);
                a.k.a.a.a(this).b(10, bundle, this);
            } else if (data.getScheme().equals(LogContract.Session.Content.CONTENT)) {
                Bundle extras = intent.getExtras();
                if (extras != null && extras.containsKey("android.intent.extra.STREAM")) {
                    data = (Uri) extras.getParcelable("android.intent.extra.STREAM");
                }
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("no.nordicsemi.android.mcp.FILE_URI", data);
                a.k.a.a.a(this).b(10, bundle2, this);
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public boolean onBackPressed() {
        if (this.mBottomSheetBehavior.b() != 3) {
            return false;
        }
        if (this.mCurrentMacroGroupId != 0) {
            onBackToParentGroup();
            return true;
        }
        if (this.mBottomSheetBehavior.c()) {
            this.mBottomSheetBehavior.e(5);
        } else {
            this.mBottomSheetBehavior.e(4);
        }
        return true;
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.AddConditionalSleepDialogFragment.Listener
    public void onConditionalSleepAdded(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, long j, boolean z, boolean z2) {
        Macro currentlyRecordingMacro = this.mConnection.getCurrentlyRecordingMacro();
        if (currentlyRecordingMacro != null) {
            if (z) {
                DatabaseHelper databaseHelper = this.mDatabaseHelper;
                currentlyRecordingMacro.addOperation(databaseHelper, new SleepUntil(databaseHelper, this.mConnection, bluetoothGattCharacteristic, bArr, j, z2));
            } else {
                DatabaseHelper databaseHelper2 = this.mDatabaseHelper;
                currentlyRecordingMacro.addOperation(databaseHelper2, new SleepIf(databaseHelper2, this.mConnection, bluetoothGattCharacteristic, bArr, j, z2));
            }
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.connection.CharacteristicView.OnConditionalSleepListener
    public void onConditionalSleepRequested(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        AddConditionalSleepDialogFragment.getInstance(bluetoothGattCharacteristic, z).show(getChildFragmentManager(), (String) null);
    }

    @Override // no.nordicsemi.android.mcp.connection.DfuFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDatabaseHelper = new DatabaseHelper(requireActivity());
        if (bundle != null) {
            this.mMacroIdToBeExported = bundle.getLong(SIS_MACRO_TO_BE_EXPORTED);
            this.mCurrentMacroGroupId = bundle.getLong(SIS_CURRENT_MACRO_GROUP);
        }
        if (getChildFragmentManager().b("progress") != null) {
            a.k.a.a.a(this).a(10, null, this);
        }
    }

    @Override // no.nordicsemi.android.mcp.connection.DfuFragment, a.k.a.a.InterfaceC0025a
    public a.k.b.c onCreateLoader(int i, Bundle bundle) {
        InputStream openInputStream;
        if (i == 10) {
            try {
                if (bundle.containsKey("no.nordicsemi.android.mcp.FILE_PATH")) {
                    openInputStream = new FileInputStream(bundle.getString("no.nordicsemi.android.mcp.FILE_PATH"));
                } else {
                    openInputStream = requireContext().getContentResolver().openInputStream((Uri) bundle.getParcelable("no.nordicsemi.android.mcp.FILE_URI"));
                }
                InputStream inputStream = openInputStream;
                ProgressDialogFragment.getInstance(R.string.macros_importing_in_progress).show(getChildFragmentManager(), "progress");
                return new ImportMacroLoader(requireContext(), this.mDatabaseHelper, inputStream, this.mCurrentMacroGroupId);
            } catch (IllegalStateException unused) {
            } catch (Exception unused2) {
                Toast.makeText(requireContext(), R.string.error_import_failed, 1).show();
            }
        }
        return super.onCreateLoader(i, bundle);
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.AddDelayDialogFragment.Listener
    public void onDelayAdded(long j) {
        Macro currentlyRecordingMacro = this.mConnection.getCurrentlyRecordingMacro();
        if (currentlyRecordingMacro != null) {
            currentlyRecordingMacro.addOperation(new Sleep(j));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mMacrosAdapter.unregisterBroadcastReceiver(requireContext());
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.MacrosAdapter.MacroActionListener
    public void onExportMacro(long j) {
        this.mMacroIdToBeExported = j;
        Pair<String, String> macroOrGroupXml = this.mDatabaseHelper.getMacroOrGroupXml(j);
        if (macroOrGroupXml != null) {
            String str = (String) macroOrGroupXml.first;
            String str2 = (String) macroOrGroupXml.second;
            String str3 = str + ".xml";
            if (Build.VERSION.SDK_INT >= 19) {
                Intent intent = new Intent("android.intent.action.CREATE_DOCUMENT");
                intent.addCategory("android.intent.category.OPENABLE");
                intent.setType("text/xml");
                intent.putExtra("android.intent.extra.TITLE", str3);
                startActivityForResult(intent, 5);
                return;
            }
            try {
                File file = new File(Environment.getExternalStorageDirectory(), FileHelper.NORDIC_FOLDER);
                if (!file.exists()) {
                    file.mkdir();
                }
                File file2 = new File(file, FileHelper.MACROS_FOLDER);
                if (!file2.exists()) {
                    file2.mkdir();
                }
                File file3 = new File(file2, str3);
                file3.createNewFile();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file3));
                outputStreamWriter.append((CharSequence) str2);
                outputStreamWriter.close();
                Uri contentUri = FileHelper.getContentUri(requireContext(), file3);
                if (contentUri != null) {
                    FileHelper.showFileNotification(requireContext(), contentUri, "text/xml");
                    return;
                }
                return;
            } catch (Exception e2) {
                Log.e(TAG, "Saving macro failed", e2);
                Toast.makeText(requireContext(), R.string.error_export_failed, 0).show();
                return;
            }
        }
        Toast.makeText(requireContext(), "Macro does not exist.", 0).show();
    }

    @Override // no.nordicsemi.android.mcp.widget.ItemTouchHelperAdapter
    public void onItemDismiss(RemovableViewHolder removableViewHolder) {
        final int adapterPosition = removableViewHolder.getAdapterPosition();
        this.mDatabaseHelper.deleteMacro(this.mMacrosAdapter.getItemId(adapterPosition));
        this.mMacrosAdapter.onItemRemovedAt(adapterPosition);
        final boolean z = this.mMacrosAdapter.getItemCount() == 0;
        if (z) {
            this.mMacrosEmptyView.setVisibility(0);
        }
        Snackbar a2 = Snackbar.a(this.mBottomSheetView, R.string.macros_deleted, 0);
        a2.e(getResources().getColor(R.color.variant));
        a2.a(R.string.undo, new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.b0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MacroFragment.this.a(adapterPosition, z, view);
            }
        });
        a2.a(new Snackbar.b() { // from class: no.nordicsemi.android.mcp.connection.MacroFragment.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.android.material.snackbar.Snackbar.b, com.google.android.material.snackbar.BaseTransientBottomBar.r
            public void onDismissed(Snackbar snackbar, int i) {
                if (i != 4) {
                    MacroFragment.this.mDatabaseHelper.removeDeletedMacros();
                }
            }
        });
        a2.k();
    }

    @Override // no.nordicsemi.android.mcp.connection.DfuFragment, a.k.a.a.InterfaceC0025a
    public void onLoadFinished(a.k.b.c cVar, Object obj) {
        final String localizedMessage;
        if (cVar.getId() != 10) {
            super.onLoadFinished(cVar, obj);
            return;
        }
        androidx.fragment.app.c cVar2 = (androidx.fragment.app.c) getChildFragmentManager().b("progress");
        if (cVar2 != null) {
            cVar2.dismiss();
        }
        View view = getView();
        if (view != null) {
            MacroLoaderResult macroLoaderResult = (MacroLoaderResult) obj;
            if (macroLoaderResult.loadedSuccessfully()) {
                this.mMacrosAdapter.onItemAdded();
                if (this.mMacrosAdapter.getItemCount() > 0) {
                    this.mMacrosEmptyView.setVisibility(8);
                }
                this.mMacrosRecyclerView.requestLayout();
                this.mMacrosRecyclerView.scrollToPosition(0);
                return;
            }
            Throwable th = macroLoaderResult.exception;
            Log.e(TAG, "Loading macro failed", th);
            if (th.getLocalizedMessage() != null) {
                localizedMessage = th.getLocalizedMessage();
            } else {
                localizedMessage = (th.getCause() == null || th.getCause().getLocalizedMessage() == null) ? "Unknown error" : th.getCause().getLocalizedMessage();
            }
            Snackbar a2 = Snackbar.a(view, R.string.macros_loading_failed, -2);
            a2.e(getResources().getColor(R.color.variant));
            a2.a(R.string.action_details, new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.w
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    MacroFragment.this.a(localizedMessage, view2);
                }
            });
            a2.k();
        }
    }

    @Override // no.nordicsemi.android.mcp.connection.DfuFragment, a.k.a.a.InterfaceC0025a
    public void onLoaderReset(a.k.b.c cVar) {
        super.onLoaderReset(cVar);
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.SaveMacroDialogFragment.Listener
    public void onMacroCancelled() {
        this.mMacrosAdapter.setCurrentMacro(-1, null);
        this.mMacrosRecyclerView.requestLayout();
        if (this.mMacrosAdapter.getItemCount() == 0) {
            this.mMacrosEmptyView.setVisibility(0);
        }
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.MacrosAdapter.MacroActionListener
    public void onMacroGroupOpened(long j, String str, int i) {
        if (j > 0) {
            this.mBottomSheetBack.setVisibility(0);
            this.mBottomSheetTitle.setText(str);
            this.mConnection.put(MacrosAdapter.MACRO_GROUP, Long.valueOf(j));
            this.mMacrosEmptyView.setVisibility(i == 0 ? 0 : 8);
        } else {
            this.mBottomSheetBack.setVisibility(8);
            this.mBottomSheetTitle.setText(R.string.macros_title);
            this.mConnection.remove(MacrosAdapter.MACRO_GROUP);
            this.mMacrosEmptyView.setVisibility(8);
        }
        this.mCurrentMacroGroupId = j;
        this.mMacrosAdapter.setGroup(j);
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.SaveGroupDialogFragment.Listener
    public void onMacroGroupSaved(int i) {
        if (i >= 0) {
            this.mMacrosAdapter.onItemChangedAt(i);
            return;
        }
        this.mMacrosAdapter.onItemAdded();
        this.mMacrosEmptyView.setVisibility(8);
        this.mMacrosRecyclerView.requestLayout();
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.MoveMacroDialogFragment.Listener
    public void onMacroMoved(int i) {
        this.mMacrosAdapter.onItemMoved();
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.SaveMacroDialogFragment.Listener
    public void onMacroSaved(int i, long j, String str, Macro.Icon icon) {
        this.mMacrosAdapter.updateMacro(j, str, icon);
        this.mMacrosAdapter.setCurrentMacro(i, null);
        this.mMacrosRecyclerView.requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onMacroStateChanged() {
    }

    @Override // no.nordicsemi.android.mcp.connection.NewMacrosListener
    public void onMacrosAdded() {
        this.mMacrosAdapter.onItemAdded();
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.MacrosAdapter.MacroActionListener
    public void onMirrorMacro(Macro macro) {
        Macro mirror = macro.mirror(this.mDatabaseHelper);
        String name = mirror.getName();
        try {
            Persister persister = new Persister(new Format(new HyphenStyle()));
            StringWriter stringWriter = new StringWriter();
            persister.write(mirror, stringWriter);
            this.mDatabaseHelper.addMacro(name, stringWriter.toString(), this.mCurrentMacroGroupId);
            this.mMacrosAdapter.onItemAdded();
        } catch (Exception e2) {
            Log.e(TAG, "Error while mirroring macro", e2);
        }
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.MacrosAdapter.MacroActionListener
    public void onMoveMacro(int i, long j) {
        MoveMacroDialogFragment.getInstance(i, j, this.mCurrentMacroGroupId).show(getChildFragmentManager(), (String) null);
    }

    @Override // no.nordicsemi.android.mcp.widget.SlidingPaneLayout.PanelSlideListener
    public void onPanelClosed(View view) {
        if (this.mConnection.isRecordingMacro()) {
            this.mBottomSheetBehavior.b(false);
            this.mBottomSheetBehavior.e(4);
        } else {
            if (this.mConnection.isDfuInProgress() || this.mBottomSheetBehavior.b() != 5) {
                return;
            }
            this.mMacrosFab.e();
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.SlidingPaneLayout.PanelSlideListener
    public void onPanelOpened(View view) {
        if (this.mConnection.isRecordingMacro()) {
            this.mBottomSheetBehavior.b(true);
            this.mBottomSheetBehavior.e(5);
        } else {
            this.mMacrosFab.b();
        }
    }

    @Override // no.nordicsemi.android.mcp.widget.SlidingPaneLayout.PanelSlideListener
    public void onPanelSlide(View view, float f2) {
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.MacrosAdapter.MacroActionListener
    public void onRenameMacro(int i, long j, Macro macro) {
        Macro.Icon icon;
        String str;
        String str2;
        try {
            if (macro != null) {
                String name = macro.getName();
                Macro.Icon icon2 = macro.getIcon();
                Persister persister = new Persister(new Format(new HyphenStyle()));
                StringWriter stringWriter = new StringWriter();
                persister.write(macro, stringWriter);
                str = stringWriter.toString();
                str2 = name;
                icon = icon2;
            } else {
                Cursor macro2 = this.mDatabaseHelper.getMacro(j);
                try {
                    if (macro2.moveToNext()) {
                        String string = macro2.getString(1);
                        String string2 = macro2.getString(2);
                        icon = Macro.Icon.PLAY;
                        macro2.close();
                        str = string2;
                        str2 = string;
                    } else {
                        Log.e(TAG, "Error while renaming macro. Macro not found");
                        return;
                    }
                } finally {
                    macro2.close();
                }
            }
            SaveMacroDialogFragment.getInstance(i, j, str2, icon, str, this.mCurrentMacroGroupId).show(getChildFragmentManager(), (String) null);
        } catch (Exception e2) {
            Log.e(TAG, "Error while renaming macro", e2);
        }
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.MacrosAdapter.MacroActionListener
    public void onRenameMacroGroup(int i, long j, String str) {
        SaveGroupDialogFragment.getInstance(i, j, str, this.mCurrentMacroGroupId).show(getChildFragmentManager(), (String) null);
    }

    @Override // no.nordicsemi.android.mcp.connection.DfuFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putLong(SIS_MACRO_TO_BE_EXPORTED, this.mMacroIdToBeExported);
        bundle.putLong(SIS_CURRENT_MACRO_GROUP, this.mCurrentMacroGroupId);
        bundle.putInt(SIS_MACRO_FAB_VISIBLE, (this.mMacrosFab.getVisibility() == 0 || this.mBottomSheetBehavior.b() == 3) ? 0 : 4);
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.MacrosAdapter.MacroActionListener
    public void onScrollToMacro(int i) {
        int I = ((LinearLayoutManager) this.mMacrosRecyclerView.getLayoutManager()).I();
        if ((I >= 0 || i > 0) && I < i) {
            this.mMacrosRecyclerView.smoothScrollBy(0, (int) PixelUtils.dpToPix(100.0f));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.mcp.connection.DfuFragment
    @SuppressLint({"RestrictedApi"})
    public void onServiceConnected(IBluetoothLeConnection iBluetoothLeConnection) {
        super.onServiceConnected(iBluetoothLeConnection);
        this.mMacrosAdapter.onServiceConnected(requireContext(), this.mDatabaseHelper, iBluetoothLeConnection);
        refreshCurrentMacro();
        Macro currentlyRecordingMacro = iBluetoothLeConnection.getCurrentlyRecordingMacro();
        if (currentlyRecordingMacro != null) {
            this.mMacrosAdapter.setCurrentMacro(0, currentlyRecordingMacro);
            this.mNewMacroFab.setImageResource(R.drawable.fab_ic_stop);
            this.mMacroRecordingIndicator.setVisibility(0);
            this.mBottomSheetBehavior.b(false);
            this.mBottomSheetBehavior.e(4);
            this.mMacrosFab.setVisibility(8);
            this.mSecondaryFab.setImageResource(R.drawable.fab_ic_plus_1s);
            return;
        }
        this.mBottomSheetBehavior.b(true);
        this.mBottomSheetBehavior.e(5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.mcp.connection.DfuFragment
    public void onServicesChanged() {
        super.onServicesChanged();
        this.mMacrosAdapter.notifyDataSetChanged();
        stopRecordingMacro();
    }

    @Override // no.nordicsemi.android.mcp.TabsAdapter.TabListener
    public void onTabClosed() {
    }

    @Override // no.nordicsemi.android.mcp.TabsAdapter.TabListener
    public void onTabOpen() {
        refreshCurrentMacro();
    }

    @Override // androidx.fragment.app.Fragment
    @SuppressLint({"RestrictedApi"})
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.bottom_sheet);
        this.mBottomSheetView = viewGroup;
        this.mBottomSheetBack = viewGroup.findViewById(R.id.action_back);
        this.mBottomSheetBack.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MacroFragment.this.a(view2);
            }
        });
        this.mBottomSheetTitle = (TextView) viewGroup.findViewById(R.id.title);
        this.mMacroRecordingIndicator = viewGroup.findViewById(R.id.progress_indicator);
        final BottomSheetBehavior b2 = BottomSheetBehavior.b(viewGroup);
        this.mBottomSheetBehavior = b2;
        FloatingActionButton floatingActionButton = (FloatingActionButton) viewGroup.findViewById(R.id.action_add_delay);
        this.mSecondaryFab = floatingActionButton;
        floatingActionButton.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MacroFragment.this.b(view2);
            }
        });
        ((FloatingActionButton) viewGroup.findViewById(R.id.action_new_group)).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MacroFragment.this.c(view2);
            }
        });
        FloatingActionButton floatingActionButton2 = (FloatingActionButton) viewGroup.findViewById(R.id.action_record);
        this.mNewMacroFab = floatingActionButton2;
        floatingActionButton2.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.u
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MacroFragment.this.d(view2);
            }
        });
        b2.c(true);
        b2.b(true);
        b2.e(5);
        b2.c((int) PixelUtils.dpToPix(72.0f));
        b2.a(new BottomSheetBehavior.e() { // from class: no.nordicsemi.android.mcp.connection.MacroFragment.1
            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.e
            public void onSlide(View view2, float f2) {
            }

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.e
            public void onStateChanged(View view2, int i) {
                if (i == 5 && MacroFragment.this.canShowMacros() && !MacroFragment.this.mConnection.isDfuInProgress()) {
                    MacroFragment.this.mMacrosFab.e();
                }
            }
        });
        RecyclerView recyclerView = (RecyclerView) viewGroup.findViewById(R.id.macros_list);
        this.mMacrosRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.addItemDecoration(new androidx.recyclerview.widget.g(view.getContext(), 1));
        recyclerView.setItemAnimator(null);
        recyclerView.setHasFixedSize(false);
        MacrosAdapter macrosAdapter = new MacrosAdapter(this);
        this.mMacrosAdapter = macrosAdapter;
        recyclerView.setAdapter(macrosAdapter);
        new androidx.recyclerview.widget.j(new RemovableItemTouchHelperCallback(this)).a(recyclerView);
        this.mMacrosEmptyView = viewGroup.findViewById(R.id.macros_empty);
        this.mMacrosFab = (FloatingActionButton) view.findViewById(R.id.fab);
        this.mMacrosFab.setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MacroFragment.this.a(b2, view2);
            }
        });
        if (bundle != null) {
            this.mMacrosFab.setVisibility(bundle.getInt(SIS_MACRO_FAB_VISIBLE));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void showMacros() {
        this.mMacrosFab.e();
    }

    public /* synthetic */ void a(BottomSheetBehavior bottomSheetBehavior, View view) {
        bottomSheetBehavior.e(3);
        this.mMacrosFab.setVisibility(8);
    }

    public /* synthetic */ void a(int i, boolean z, View view) {
        this.mDatabaseHelper.restoreDeletedMacros();
        this.mMacrosAdapter.onItemRestoredAt(i);
        this.mMacrosRecyclerView.requestLayout();
        boolean z2 = this.mMacrosAdapter.getItemCount() > 0;
        if (z && z2) {
            this.mMacrosEmptyView.setVisibility(8);
        }
    }

    public /* synthetic */ void b(ListView listView, DialogInterface dialogInterface, int i) {
        int checkedItemPosition = listView.getCheckedItemPosition();
        if (checkedItemPosition >= 0) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getResources().getStringArray(R.array.dfu_app_file_browser_action)[checkedItemPosition])));
        }
    }

    public /* synthetic */ void a(String str, View view) {
        d.a aVar = new d.a(requireContext());
        aVar.a(str);
        aVar.c(R.string.action_details);
        aVar.c(R.string.ok, null);
        aVar.c();
    }
}
