package no.nordicsemi.android.mcp.definitions;

import android.content.Context;
import java.io.InputStream;

/* loaded from: classes.dex */
class ImportDefinitionsLoader extends a.k.b.a<Boolean> {
    public static final String TAG = "ImportDefinitionsLoader";
    private final InputStream mInputStream;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImportDefinitionsLoader(Context context, InputStream inputStream) {
        super(context);
        this.mInputStream = inputStream;
    }

    @Override // a.k.b.c
    protected void onStartLoading() {
        forceLoad();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // a.k.b.a
    public Boolean loadInBackground() {
        return Boolean.valueOf(JSONDefinitionConverter.fromJSON(getContext(), this.mInputStream));
    }
}
