package no.nordicsemi.android.mcp.ble.write;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import io.runtime.mcumgr.McuMgrCallback;
import io.runtime.mcumgr.McuMgrScheme;
import io.runtime.mcumgr.McuMgrTransport;
import io.runtime.mcumgr.exception.McuMgrException;
import io.runtime.mcumgr.managers.DefaultManager;
import io.runtime.mcumgr.managers.ImageManager;
import io.runtime.mcumgr.managers.StatsManager;
import io.runtime.mcumgr.response.McuMgrResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.parser.CharacteristicParser;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.widget.HexKeyListener;

/* loaded from: classes.dex */
public class McuMgrSmpDialogBuilder extends WriteDialogBuilder {
    private static final int POS_IMAGE_CONFIRM = 4;
    private static final int POS_IMAGE_ERASE = 5;
    private static final int POS_IMAGE_READ_STATE = 2;
    private static final int POS_IMAGE_TEST = 3;
    private static final int POS_OS_ECHO = 0;
    private static final int POS_OS_RESET = 1;
    private static final int POS_STATS_LIST = 6;
    private static final int POS_STATS_READ_GROUP = 7;
    private Spinner mCommand;
    private EditText mEcho;
    private View mEchoLabel;
    private EditText mGroup;
    private View mGroupLabel;
    private EditText mHash;
    private TextView mHashLabel;

    /* loaded from: classes.dex */
    private class DummyTransport implements McuMgrTransport {
        private DummyTransport() {
        }

        @Override // io.runtime.mcumgr.McuMgrTransport
        public void addObserver(McuMgrTransport.ConnectionObserver connectionObserver) {
        }

        @Override // io.runtime.mcumgr.McuMgrTransport
        public void connect(McuMgrTransport.ConnectionCallback connectionCallback) {
        }

        @Override // io.runtime.mcumgr.McuMgrTransport
        public McuMgrScheme getScheme() {
            return McuMgrScheme.BLE;
        }

        @Override // io.runtime.mcumgr.McuMgrTransport
        public void release() {
        }

        @Override // io.runtime.mcumgr.McuMgrTransport
        public void removeObserver(McuMgrTransport.ConnectionObserver connectionObserver) {
        }

        @Override // io.runtime.mcumgr.McuMgrTransport
        public <T extends McuMgrResponse> T send(byte[] bArr, Class<T> cls) {
            try {
                return (T) McuMgrResponse.buildResponse(McuMgrScheme.BLE, bArr, cls);
            } catch (IOException unused) {
                throw new McuMgrException("Failed to create response");
            }
        }

        @Override // io.runtime.mcumgr.McuMgrTransport
        public <T extends McuMgrResponse> void send(byte[] bArr, Class<T> cls, McuMgrCallback<T> mcuMgrCallback) {
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected View createView(Context context) {
        String valueAsString;
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_write_mcumgr_smp, (ViewGroup) null);
        this.mCommand = (Spinner) inflate.findViewById(R.id.command);
        this.mEcho = (EditText) inflate.findViewById(R.id.echo);
        this.mEchoLabel = inflate.findViewById(R.id.echo_title);
        this.mHash = (EditText) inflate.findViewById(R.id.hash);
        this.mHash.setKeyListener(new HexKeyListener());
        this.mHashLabel = (TextView) inflate.findViewById(R.id.hash_title);
        this.mGroup = (EditText) inflate.findViewById(R.id.group);
        this.mGroupLabel = inflate.findViewById(R.id.group_title);
        this.mCommand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: no.nordicsemi.android.mcp.ble.write.McuMgrSmpDialogBuilder.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                boolean z = i == 0;
                boolean z2 = i == 7;
                boolean z3 = i == 3 || i == 4;
                boolean z4 = i == 4;
                McuMgrSmpDialogBuilder.this.mEcho.setVisibility(z ? 0 : 8);
                McuMgrSmpDialogBuilder.this.mEchoLabel.setVisibility(z ? 0 : 8);
                McuMgrSmpDialogBuilder.this.mHash.setVisibility(z3 ? 0 : 8);
                McuMgrSmpDialogBuilder.this.mHashLabel.setText(z4 ? R.string.mcumgr_hash_confirm : R.string.mcumgr_hash);
                McuMgrSmpDialogBuilder.this.mHashLabel.setVisibility(z3 ? 0 : 8);
                McuMgrSmpDialogBuilder.this.mGroup.setVisibility(z2 ? 0 : 8);
                McuMgrSmpDialogBuilder.this.mGroupLabel.setVisibility(z2 ? 0 : 8);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                McuMgrSmpDialogBuilder.this.mEcho.setVisibility(8);
                McuMgrSmpDialogBuilder.this.mEchoLabel.setVisibility(8);
                McuMgrSmpDialogBuilder.this.mGroup.setVisibility(8);
                McuMgrSmpDialogBuilder.this.mGroupLabel.setVisibility(8);
            }
        });
        if (this.mCharacteristic != null && (valueAsString = CharacteristicParser.getValueAsString(new DatabaseHelper(requireContext()), this.mCharacteristic, null, true)) != null && valueAsString.length() > 300) {
            Matcher matcher = Pattern.compile("\"slot\":1,.*\"hash\":\"([^\"]+)\"").matcher(valueAsString);
            if (matcher.find()) {
                this.mHash.setText(ParserUtils.bytesToHex(Base64.decode(matcher.group(1), 0), false));
            }
        }
        return inflate;
    }

    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    protected byte[] getValueFromView(View view) {
        McuMgrResponse mcuMgrResponse;
        byte[] bArr;
        try {
            switch (this.mCommand.getSelectedItemPosition()) {
                case 0:
                    mcuMgrResponse = new DefaultManager(new DummyTransport()).echo(this.mEcho.getText().toString());
                    break;
                case 1:
                    mcuMgrResponse = new DefaultManager(new DummyTransport()).reset();
                    break;
                case 2:
                    mcuMgrResponse = new ImageManager(new DummyTransport()).list();
                    break;
                case 3:
                    String obj = this.mHash.getText().toString();
                    byte[] bArr2 = new byte[obj.length() / 2];
                    ParserUtils.setByteArrayValue(bArr2, 0, obj);
                    mcuMgrResponse = new ImageManager(new DummyTransport()).test(bArr2);
                    break;
                case 4:
                    String obj2 = this.mHash.getText().toString();
                    if (TextUtils.isEmpty(obj2)) {
                        bArr = null;
                    } else {
                        bArr = new byte[obj2.length() / 2];
                        ParserUtils.setByteArrayValue(bArr, 0, obj2);
                    }
                    mcuMgrResponse = new ImageManager(new DummyTransport()).confirm(bArr);
                    break;
                case 5:
                    mcuMgrResponse = new ImageManager(new DummyTransport()).erase();
                    break;
                case 6:
                    mcuMgrResponse = new StatsManager(new DummyTransport()).list();
                    break;
                case 7:
                    mcuMgrResponse = new StatsManager(new DummyTransport()).read(this.mGroup.getText().toString());
                    break;
                default:
                    mcuMgrResponse = new McuMgrResponse();
                    break;
            }
        } catch (McuMgrException unused) {
            mcuMgrResponse = new McuMgrResponse();
        }
        return mcuMgrResponse.getBytes();
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0016, code lost:
    
        if (r5 != 4) goto L15;
     */
    @Override // no.nordicsemi.android.mcp.ble.write.WriteDialogBuilder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected boolean isValueValid(android.view.View r5) {
        /*
            r4 = this;
            android.widget.Spinner r5 = r4.mCommand
            int r5 = r5.getSelectedItemPosition()
            android.widget.EditText r0 = r4.mHash
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            r1 = 3
            r2 = 0
            r3 = 1
            if (r5 == r1) goto L19
            r1 = 4
            if (r5 == r1) goto L2c
            goto L41
        L19:
            boolean r5 = android.text.TextUtils.isEmpty(r0)
            if (r5 == 0) goto L2c
            android.widget.EditText r5 = r4.mHash
            r0 = 2131821031(0x7f1101e7, float:1.9274794E38)
            java.lang.String r0 = r4.getString(r0)
            r5.setError(r0)
            return r2
        L2c:
            int r5 = r0.length()
            int r5 = r5 % 2
            if (r5 != r3) goto L41
            android.widget.EditText r5 = r4.mHash
            r0 = 2131821032(0x7f1101e8, float:1.9274796E38)
            java.lang.String r0 = r4.getString(r0)
            r5.setError(r0)
            return r2
        L41:
            android.widget.EditText r5 = r4.mHash
            r0 = 0
            r5.setError(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.ble.write.McuMgrSmpDialogBuilder.isValueValid(android.view.View):boolean");
    }
}
