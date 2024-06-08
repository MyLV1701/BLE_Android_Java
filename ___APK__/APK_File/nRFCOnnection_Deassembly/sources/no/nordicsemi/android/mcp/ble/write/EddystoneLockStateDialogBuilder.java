package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
public class EddystoneLockStateDialogBuilder extends WriteDialogBuilder {
    private static final String PATTERN_TX_POWER = "[0-9a-fA-F]{32}";
    private static final String TAG = "EddystoneLockStateDB";

    /* loaded from: classes.dex */
    private class ViewHolder {
        private Spinner lockStates;
        private EditText newLockCode;
        private LinearLayout newLockCodeContainer;
        private EditText oldLockCode;
        private LinearLayout oldLockCodeContainer;
        private TextView titleNewLockCode;
        private TextView titleOldLockCode;

        private ViewHolder() {
        }
    }

    private byte[] aes128Encrypt(byte[] bArr, SecretKeySpec secretKeySpec) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            try {
                cipher.init(1, secretKeySpec);
                try {
                    return cipher.doFinal(bArr);
                } catch (BadPaddingException | IllegalBlockSizeException e2) {
                    Log.e(TAG, "Error executing cipher", e2);
                    return null;
                }
            } catch (InvalidKeyException e3) {
                Log.e(TAG, "Error initializing cipher instance", e3);
                return null;
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e4) {
            Log.e(TAG, "Error constructing cipher instance", e4);
            return null;
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_es_lock_state, (ViewGroup) null);
        final ViewHolder viewHolder = new ViewHolder();
        Spinner spinner = viewHolder.lockStates = (Spinner) inflate.findViewById(R.id.frame_types);
        viewHolder.oldLockCode = (EditText) inflate.findViewById(R.id.old_lock_code);
        viewHolder.newLockCode = (EditText) inflate.findViewById(R.id.new_lock_code);
        viewHolder.titleOldLockCode = (TextView) inflate.findViewById(R.id.title_old_lock_code);
        viewHolder.titleNewLockCode = (TextView) inflate.findViewById(R.id.title_new_lock_code);
        viewHolder.oldLockCodeContainer = (LinearLayout) inflate.findViewById(R.id.old_lock_code_container);
        viewHolder.newLockCodeContainer = (LinearLayout) inflate.findViewById(R.id.new_lock_code_container);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: no.nordicsemi.android.mcp.ble.write.EddystoneLockStateDialogBuilder.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    viewHolder.titleOldLockCode.setVisibility(8);
                    viewHolder.oldLockCodeContainer.setVisibility(8);
                    viewHolder.titleNewLockCode.setVisibility(8);
                    viewHolder.newLockCodeContainer.setVisibility(8);
                    return;
                }
                if (i == 1) {
                    viewHolder.titleOldLockCode.setVisibility(0);
                    viewHolder.oldLockCodeContainer.setVisibility(0);
                    viewHolder.titleNewLockCode.setVisibility(0);
                    viewHolder.newLockCodeContainer.setVisibility(0);
                    return;
                }
                if (i != 2) {
                    return;
                }
                viewHolder.titleOldLockCode.setVisibility(8);
                viewHolder.oldLockCodeContainer.setVisibility(8);
                viewHolder.titleNewLockCode.setVisibility(8);
                viewHolder.newLockCodeContainer.setVisibility(8);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinner.setSelection(0);
        inflate.setTag(viewHolder);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        int selectedItemPosition = viewHolder.lockStates.getSelectedItemPosition();
        String obj = viewHolder.oldLockCode.getText().toString();
        String obj2 = viewHolder.newLockCode.getText().toString();
        if (selectedItemPosition == 0 || selectedItemPosition == 2) {
            return new byte[]{(byte) selectedItemPosition};
        }
        byte[] bArr = new byte[17];
        bArr[0] = (byte) (selectedItemPosition - 1);
        byte[] bArr2 = new byte[16];
        ParserUtils.setByteArrayValue(bArr2, 0, obj);
        byte[] bArr3 = new byte[16];
        ParserUtils.setByteArrayValue(bArr3, 0, obj2);
        byte[] aes128Encrypt = aes128Encrypt(bArr3, new SecretKeySpec(bArr2, "AES"));
        System.arraycopy(aes128Encrypt, 0, bArr, 1, aes128Encrypt.length);
        return bArr;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected boolean isValueValid(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (viewHolder.oldLockCodeContainer.getVisibility() == 0) {
            String trim = viewHolder.oldLockCode.getText().toString().trim();
            if (trim.isEmpty()) {
                viewHolder.oldLockCode.setError("Please enter old lock code");
                return false;
            }
            if (!trim.matches(PATTERN_TX_POWER)) {
                viewHolder.oldLockCode.setError("Please enter a valid value for old lock code");
                return false;
            }
        }
        if (viewHolder.newLockCodeContainer.getVisibility() != 0) {
            return true;
        }
        String trim2 = viewHolder.newLockCode.getText().toString().trim();
        if (trim2.isEmpty()) {
            viewHolder.newLockCode.setError("Please enter new lock code");
            return false;
        }
        if (trim2.matches(PATTERN_TX_POWER)) {
            return true;
        }
        viewHolder.newLockCode.setError("Please enter a valid value for new lock code");
        return false;
    }
}
