package no.nordicsemi.android.mcp.connection.macro;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.appcompat.app.d;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class MoveMacroDialogFragment extends androidx.fragment.app.c implements View.OnClickListener {
    private static final String GROUP_ID = "group_id";
    private static final String ID = "id";
    private static final String POSITION = "position";
    private Cursor mCursor;
    private DatabaseHelper mDatabaseHelper;
    private Spinner mSpinner;

    /* loaded from: classes.dex */
    public interface Listener {
        void onMacroMoved(int i);
    }

    public static MoveMacroDialogFragment getInstance(int i, long j, long j2) {
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, i);
        bundle.putLong("id", j);
        bundle.putLong("group_id", j2);
        MoveMacroDialogFragment moveMacroDialogFragment = new MoveMacroDialogFragment();
        moveMacroDialogFragment.setArguments(bundle);
        return moveMacroDialogFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Bundle arguments = getArguments();
        int i = arguments.getInt(POSITION);
        this.mDatabaseHelper.moveMacro(arguments.getLong("id"), this.mSpinner.getSelectedItemId());
        ((Listener) getParentFragment()).onMacroMoved(i);
        dismiss();
    }

    @Override // androidx.fragment.app.c, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDatabaseHelper = new DatabaseHelper(getContext());
        Bundle arguments = getArguments();
        long j = arguments.getLong("id");
        long j2 = arguments.getLong("group_id");
        Cursor macro = this.mDatabaseHelper.getMacro(j2);
        try {
            long j3 = macro.moveToNext() ? macro.getLong(3) : 0L;
            macro.close();
            this.mCursor = this.mDatabaseHelper.getMacroGroups(j2, j);
            if (j2 > 0) {
                MatrixCursor matrixCursor = new MatrixCursor(new String[]{"_id", "name"}, 1);
                matrixCursor.addRow(new Object[]{Long.valueOf(j3), "Parent folder"});
                this.mCursor = new MergeCursor(new Cursor[]{matrixCursor, this.mCursor});
            }
        } catch (Throwable th) {
            macro.close();
            throw th;
        }
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        View inflate = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_macro_move, (ViewGroup) null);
        this.mSpinner = (Spinner) inflate.findViewById(R.id.spinner);
        this.mSpinner.setAdapter((SpinnerAdapter) new a.g.a.d(requireContext(), android.R.layout.simple_list_item_1, this.mCursor, new String[]{"name"}, new int[]{android.R.id.text1}, 0));
        d.a aVar = new d.a(requireContext());
        aVar.c(R.string.menu_action_move);
        aVar.b(inflate);
        aVar.a(false);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c(R.string.save, null);
        androidx.appcompat.app.d c2 = aVar.c();
        Button b2 = c2.b(-1);
        b2.setOnClickListener(this);
        b2.setEnabled(this.mCursor.getCount() > 0);
        return c2;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mCursor.close();
    }
}
