package no.nordicsemi.android.mcp.connection;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.d;
import java.util.UUID;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.definitions.JSONDefinitionConverter;

/* loaded from: classes.dex */
public class EditNameDialogFragment extends androidx.fragment.app.c implements DialogInterface.OnClickListener {
    private static final String FORMAT = "format";
    private static final String NAME = "name";
    private static final String TYPE = "type";
    static final int TYPE_CHARACTERISTIC = 2;
    static final int TYPE_SERVICE = 1;
    private static final String UUID = "uuid";
    private EditText mEditText;
    private RadioGroup mFormatView;

    private Integer getFormat() {
        return this.mFormatView.getCheckedRadioButtonId() == R.id.format_text ? 1 : null;
    }

    public static androidx.fragment.app.c getInstance(int i, UUID uuid, String str, Integer num) {
        EditNameDialogFragment editNameDialogFragment = new EditNameDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", i);
        bundle.putParcelable(UUID, new ParcelUuid(uuid));
        bundle.putString("name", str);
        if (num != null) {
            bundle.putInt("format", num.intValue());
        }
        editNameDialogFragment.setArguments(bundle);
        return editNameDialogFragment;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        String trim = this.mEditText.getText().toString().trim();
        Bundle requireArguments = requireArguments();
        int i2 = requireArguments.getInt("type");
        UUID uuid = ((ParcelUuid) requireArguments.getParcelable(UUID)).getUuid();
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        if (i2 == 1) {
            databaseHelper.addUpdateOrRemoveService(mostSignificantBits, leastSignificantBits, trim);
        } else {
            databaseHelper.addUpdateOrRemoveCharacteristic(mostSignificantBits, leastSignificantBits, trim, getFormat());
        }
        JSONDefinitionConverter.onUserDefinitionsChanged(getContext(), databaseHelper);
    }

    @Override // androidx.fragment.app.c
    public Dialog onCreateDialog(Bundle bundle) {
        Context requireContext = requireContext();
        Bundle arguments = getArguments();
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_change_name, (ViewGroup) null);
        RadioGroup radioGroup = (RadioGroup) inflate.findViewById(R.id.format);
        this.mFormatView = radioGroup;
        radioGroup.setVisibility(arguments.getInt("type") == 1 ? 8 : 0);
        radioGroup.check(arguments.getInt("format", -1) == 1 ? R.id.format_text : R.id.format_none);
        ((TextView) inflate.findViewById(R.id.text)).setText(arguments.getInt("type") == 1 ? R.string.alert_change_name_text_service : R.string.alert_change_name_text_characteristic);
        final EditText editText = (EditText) inflate.findViewById(R.id.display_name);
        this.mEditText = editText;
        editText.setText(arguments.getString("name"));
        inflate.findViewById(R.id.action_clear).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                editText.setText((CharSequence) null);
            }
        });
        final View findViewById = inflate.findViewById(R.id.info);
        d.a aVar = new d.a(requireContext);
        aVar.c(R.string.alert_change_name_title);
        aVar.b(inflate);
        aVar.b(R.string.info, (DialogInterface.OnClickListener) null);
        aVar.a(R.string.cancel, (DialogInterface.OnClickListener) null);
        aVar.c(R.string.ok, this);
        androidx.appcompat.app.d c2 = aVar.c();
        c2.setCanceledOnTouchOutside(false);
        c2.b(-3).setOnClickListener(new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.connection.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                findViewById.setVisibility(0);
            }
        });
        return c2;
    }

    @Override // androidx.fragment.app.c, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        ((DeviceDetailsFragment2) requireParentFragment()).refreshAndCloseActionMode();
    }
}
