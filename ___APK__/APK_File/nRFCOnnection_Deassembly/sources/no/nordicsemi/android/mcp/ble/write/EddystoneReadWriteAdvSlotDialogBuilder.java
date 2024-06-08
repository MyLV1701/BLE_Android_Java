package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import java.io.ByteArrayOutputStream;
import javax.crypto.spec.SecretKeySpec;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.EddystoneUtils;

/* loaded from: classes.dex */
public class EddystoneReadWriteAdvSlotDialogBuilder extends WriteDialogBuilder {
    private static final int BEACON_LOCK_CODE_LENGTH = 16;
    private static final int ECDH_KEY_LENGTH = 32;
    private static final String PATTERN_EID1 = "[0-9a-fA-F]{64}";
    private static final String PATTERN_EID2 = "[0-9a-fA-F]{32}";
    private static final String PATTERN_IDENTITY_KEY = "[0-9a-fA-F]{32}";
    private static final String PATTERN_INSTANCE_ID = "[0-9a-fA-F]{12}";
    private static final String PATTERN_NAMESPACE_ID = "[0-9a-fA-F]{20}";
    private static final String PATTERN_URL = "[-a-zA-Z0-9+&@#/%?=~_|!:,.;]+[-a-zA-Z0-9+&@#/%=~_|]";
    private static final int TYPE_CLEAR_SLOT = 256;
    private static final int TYPE_EID = 48;
    private static final int TYPE_TLM = 32;
    private static final int TYPE_UID = 0;
    private static final int TYPE_URL = 16;
    private int mFrameType;

    /* loaded from: classes.dex */
    private class ViewHolder {
        private LinearLayout eidContainer;
        private EditText eidData;
        private EditText identityKey;
        private LinearLayout identityKeyContainer;
        private EditText instanceId;
        private EditText namespaceId;
        private Spinner securityType;
        private EditText timerExponent;
        private LinearLayout uidContainer;
        private LinearLayout urlContainer;
        private EditText urlData;
        private Spinner urlScheme;

        private ViewHolder() {
        }
    }

    private int calculateEncodedUrlLength(String str) {
        String replaceAll = str.replaceAll("\\.com/", "\\$0").replaceAll("\\.org/", "\\$1").replaceAll("\\.edu/", "\\$2").replaceAll("\\.net/", "\\$3").replaceAll("\\.info/", "\\$4").replaceAll("\\.biz/", "\\$5").replaceAll("\\.gov/", "\\$6").replaceAll("\\.com", "\\$7").replaceAll("\\.org", "\\$8").replaceAll("\\.edu", "\\$9").replaceAll("\\.net", "\\$a").replaceAll("\\.info", "\\$b").replaceAll("\\.biz", "\\$c").replaceAll("\\.gov", "\\$d");
        int i = 0;
        int i2 = 0;
        while (i < replaceAll.length()) {
            i2++;
            if (replaceAll.charAt(i) == '$') {
                i++;
            }
            i++;
        }
        return i2;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_es_rw_adv_slot, (ViewGroup) null);
        final ViewHolder viewHolder = new ViewHolder();
        Spinner spinner = (Spinner) inflate.findViewById(R.id.frame_types);
        Spinner spinner2 = viewHolder.securityType = (Spinner) inflate.findViewById(R.id.security_type);
        viewHolder.uidContainer = (LinearLayout) inflate.findViewById(R.id.uid_container);
        viewHolder.urlContainer = (LinearLayout) inflate.findViewById(R.id.url_container);
        viewHolder.eidContainer = (LinearLayout) inflate.findViewById(R.id.eid_container);
        viewHolder.identityKeyContainer = (LinearLayout) inflate.findViewById(R.id.identity_key_container);
        viewHolder.namespaceId = (EditText) inflate.findViewById(R.id.namespace_id);
        viewHolder.instanceId = (EditText) inflate.findViewById(R.id.instance_id);
        viewHolder.urlScheme = (Spinner) inflate.findViewById(R.id.url_scheme);
        viewHolder.urlData = (EditText) inflate.findViewById(R.id.url_data);
        viewHolder.eidData = (EditText) inflate.findViewById(R.id.eid_data);
        viewHolder.timerExponent = (EditText) inflate.findViewById(R.id.timer_exponent);
        viewHolder.identityKey = (EditText) inflate.findViewById(R.id.identity_key);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: no.nordicsemi.android.mcp.ble.write.EddystoneReadWriteAdvSlotDialogBuilder.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    EddystoneReadWriteAdvSlotDialogBuilder.this.mFrameType = 0;
                    viewHolder.uidContainer.setVisibility(0);
                    viewHolder.urlContainer.setVisibility(8);
                    viewHolder.eidContainer.setVisibility(8);
                    return;
                }
                if (i == 1) {
                    EddystoneReadWriteAdvSlotDialogBuilder.this.mFrameType = 16;
                    viewHolder.urlContainer.setVisibility(0);
                    viewHolder.uidContainer.setVisibility(8);
                    viewHolder.eidContainer.setVisibility(8);
                    return;
                }
                if (i == 2) {
                    EddystoneReadWriteAdvSlotDialogBuilder.this.mFrameType = 32;
                    viewHolder.uidContainer.setVisibility(8);
                    viewHolder.urlContainer.setVisibility(8);
                    viewHolder.eidContainer.setVisibility(8);
                    return;
                }
                if (i == 3) {
                    EddystoneReadWriteAdvSlotDialogBuilder.this.mFrameType = 48;
                    viewHolder.eidContainer.setVisibility(0);
                    viewHolder.uidContainer.setVisibility(8);
                    viewHolder.urlContainer.setVisibility(8);
                    return;
                }
                if (i != 4) {
                    return;
                }
                EddystoneReadWriteAdvSlotDialogBuilder.this.mFrameType = 256;
                viewHolder.uidContainer.setVisibility(8);
                viewHolder.urlContainer.setVisibility(8);
                viewHolder.eidContainer.setVisibility(8);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: no.nordicsemi.android.mcp.ble.write.EddystoneReadWriteAdvSlotDialogBuilder.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    viewHolder.eidData.setHint(EddystoneReadWriteAdvSlotDialogBuilder.this.getString(R.string.length_ecdh_key));
                    viewHolder.eidData.setText(EddystoneUtils.getLastServicePublicECDH(EddystoneReadWriteAdvSlotDialogBuilder.this.getContext()));
                    viewHolder.identityKeyContainer.setVisibility(8);
                    return;
                }
                if (i != 1) {
                    return;
                }
                viewHolder.eidData.setHint(EddystoneReadWriteAdvSlotDialogBuilder.this.getString(R.string.lock_code_length));
                viewHolder.eidData.setText(EddystoneUtils.getLastLockKey(EddystoneReadWriteAdvSlotDialogBuilder.this.getContext()));
                viewHolder.identityKeyContainer.setVisibility(0);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinner.setSelection(0);
        spinner2.setSelection(0);
        inflate.setTag(viewHolder);
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        int i = this.mFrameType;
        int i2 = 0;
        if (i == 0) {
            byte[] bArr = new byte[17];
            bArr[0] = 0;
            ParserUtils.setByteArrayValue(bArr, 1, viewHolder.namespaceId.getText().toString().trim());
            ParserUtils.setByteArrayValue(bArr, 11, viewHolder.instanceId.getText().toString().trim());
            return bArr;
        }
        if (i == 16) {
            int selectedItemPosition = viewHolder.urlScheme.getSelectedItemPosition();
            String trim = viewHolder.urlData.getText().toString().trim();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(16);
            byteArrayOutputStream.write(selectedItemPosition);
            String replaceAll = trim.replaceAll("\\.com/", "\\$0").replaceAll("\\.org/", "\\$1").replaceAll("\\.edu/", "\\$2").replaceAll("\\.net/", "\\$3").replaceAll("\\.info/", "\\$4").replaceAll("\\.biz/", "\\$5").replaceAll("\\.gov/", "\\$6").replaceAll("\\.com", "\\$7").replaceAll("\\.org", "\\$8").replaceAll("\\.edu", "\\$9").replaceAll("\\.net", "\\$a").replaceAll("\\.info", "\\$b").replaceAll("\\.biz", "\\$c").replaceAll("\\.gov", "\\$d");
            byte[] bytes = replaceAll.getBytes();
            while (i2 < replaceAll.length()) {
                if (replaceAll.charAt(i2) != '$') {
                    byteArrayOutputStream.write(bytes[i2]);
                } else {
                    i2++;
                    byteArrayOutputStream.write((byte) Integer.parseInt(replaceAll.substring(i2, i2 + 1), 16));
                }
                i2++;
            }
            return byteArrayOutputStream.toByteArray();
        }
        if (i == 32) {
            return new byte[]{32};
        }
        if (i != 48) {
            if (i != 256) {
                return null;
            }
            return new byte[0];
        }
        String trim2 = viewHolder.eidData.getText().toString().trim();
        String trim3 = viewHolder.timerExponent.getText().toString().trim();
        if (viewHolder.securityType.getSelectedItemPosition() == 0) {
            EddystoneUtils.storeServicePublicECDH(getContext(), trim2);
            byte[] bArr2 = new byte[34];
            bArr2[0] = 48;
            ParserUtils.setByteArrayValue(bArr2, 1, trim2);
            bArr2[33] = (byte) Integer.parseInt(trim3);
            return bArr2;
        }
        EddystoneUtils.storeLockKey(getContext(), trim2);
        byte[] bArr3 = new byte[16];
        ParserUtils.setByteArrayValue(bArr3, 0, viewHolder.identityKey.getText().toString().trim());
        byte[] bArr4 = new byte[18];
        bArr4[0] = 48;
        byte[] bArr5 = new byte[16];
        ParserUtils.setByteArrayValue(bArr5, 0, trim2);
        byte[] aes128Encrypt = EddystoneUtils.aes128Encrypt(bArr3, new SecretKeySpec(bArr5, "AES"));
        if (aes128Encrypt != null) {
            System.arraycopy(aes128Encrypt, 0, bArr4, 1, aes128Encrypt.length);
        }
        bArr4[17] = (byte) Integer.parseInt(trim3);
        return bArr4;
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0189  */
    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected boolean isValueValid(android.view.View r6) {
        /*
            Method dump skipped, instructions count: 410
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.ble.write.EddystoneReadWriteAdvSlotDialogBuilder.isValueValid(android.view.View):boolean");
    }
}
