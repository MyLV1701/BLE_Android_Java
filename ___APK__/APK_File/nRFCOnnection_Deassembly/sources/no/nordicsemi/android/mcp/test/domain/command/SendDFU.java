package no.nordicsemi.android.mcp.test.domain.command;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import no.nordicsemi.android.dfu.DfuBaseService;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.mcp.test.ConstantsManager;
import no.nordicsemi.android.mcp.test.Result;
import no.nordicsemi.android.mcp.test.domain.Operation;
import no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
/* loaded from: classes.dex */
public class SendDFU extends Operation {

    @Attribute
    private String file;

    @Attribute(required = false)
    private String initFile;

    @Attribute(required = false)
    private int type;

    public SendDFU() {
        super("DFU");
        this.type = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.Operation
    public OperationResult execute(Context context, ConstantsManager constantsManager, Result result, LogSession logSession) {
        String str;
        String str2 = Environment.getExternalStorageDirectory().getPath() + constantsManager.decode(this.file);
        File file = new File(str2);
        if (file.exists() && !file.isDirectory() && file.isFile() && file.canRead()) {
            if (this.initFile != null) {
                String str3 = Environment.getExternalStorageDirectory().getPath() + constantsManager.decode(this.initFile);
                File file2 = new File(str3);
                if (!file2.exists() || file2.isDirectory() || !file2.isFile() || !file.canRead()) {
                    logFail(result, logSession, "The init file " + str3 + " does not exist or is not a file");
                    return toResult(-1);
                }
                str = str3;
            } else {
                str = null;
            }
            return toResult(getTarget().dfu(this.type, str2.endsWith("zip") ? DfuBaseService.MIME_TYPE_ZIP : DfuBaseService.MIME_TYPE_OCTET_STREAM, str2, str, logSession));
        }
        logFail(result, logSession, "The file " + str2 + " does not exist or is not a file");
        return toResult(-1);
    }

    public SendDFU(@Attribute(name = "description") String str) {
        super(str);
        this.type = 0;
    }
}
