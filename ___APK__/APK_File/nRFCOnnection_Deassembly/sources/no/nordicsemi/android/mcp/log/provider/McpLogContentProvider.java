package no.nordicsemi.android.mcp.log.provider;

import android.net.Uri;
import no.nordicsemi.android.log.localprovider.LocalLogContentProvider;
import no.nordicsemi.android.mcp.log.LocalLogContract;

/* loaded from: classes.dex */
public class McpLogContentProvider extends LocalLogContentProvider {
    @Override // no.nordicsemi.android.log.localprovider.LocalLogContentProvider
    protected Uri getAuthorityUri() {
        return LocalLogContract.AUTHORITY_URI;
    }
}
