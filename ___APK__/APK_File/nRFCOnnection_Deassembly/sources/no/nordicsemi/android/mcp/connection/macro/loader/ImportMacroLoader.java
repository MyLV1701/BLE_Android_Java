package no.nordicsemi.android.mcp.connection.macro.loader;

import a.k.b.a;
import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ListIterator;
import no.nordicsemi.android.mcp.connection.macro.domain.Group;
import no.nordicsemi.android.mcp.connection.macro.domain.Macro;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.domain.common.HasName;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.HyphenStyle;

/* loaded from: classes.dex */
public class ImportMacroLoader extends a<MacroLoaderResult> {
    private final DatabaseHelper mDatabaseHelper;
    private final long mGroupId;
    private final InputStream mInputStream;
    private MacroLoaderResult mResult;

    public ImportMacroLoader(Context context, DatabaseHelper databaseHelper, InputStream inputStream, long j) {
        super(context);
        this.mDatabaseHelper = databaseHelper;
        this.mInputStream = inputStream;
        this.mGroupId = j;
    }

    private void addMacro(Serializer serializer, Macro macro, long j) {
        String ensureUniqueName = ensureUniqueName(macro);
        macro.ensureAllOperationsAsserted(this.mDatabaseHelper);
        StringWriter stringWriter = new StringWriter();
        serializer.write(macro, stringWriter);
        this.mDatabaseHelper.addMacro(ensureUniqueName, stringWriter.toString(), j);
    }

    private void addMacroGroup(Serializer serializer, Group group, long j) {
        long addMacroGroup = this.mDatabaseHelper.addMacroGroup(ensureUniqueName(group), j);
        ListIterator<Macro> listIterator = group.getMacros().listIterator(group.getMacros().size());
        while (listIterator.hasPrevious()) {
            addMacro(serializer, listIterator.previous(), addMacroGroup);
        }
        ListIterator<Group> listIterator2 = group.getGroups().listIterator(group.getGroups().size());
        while (listIterator2.hasPrevious()) {
            addMacroGroup(serializer, listIterator2.previous(), addMacroGroup);
        }
    }

    private String ensureUniqueName(HasName hasName) {
        String name = hasName.getName();
        while (this.mDatabaseHelper.macroExists(name)) {
            int lastIndexOf = name.lastIndexOf(32);
            if (lastIndexOf == -1) {
                name = name + " 2";
            } else {
                int i = lastIndexOf + 1;
                try {
                    name = name.substring(0, i) + (Integer.parseInt(name.substring(i)) + 1);
                } catch (NumberFormatException unused) {
                    name = name + " 2";
                }
            }
        }
        hasName.setName(name);
        return name;
    }

    @Override // a.k.b.c
    protected void onStartLoading() {
        MacroLoaderResult macroLoaderResult = this.mResult;
        if (macroLoaderResult != null) {
            deliverResult(macroLoaderResult);
        } else {
            forceLoad();
        }
    }

    @Override // a.k.b.a
    public MacroLoaderResult loadInBackground() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.mInputStream));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append("\n");
                } else {
                    String sb2 = sb.toString();
                    Persister persister = new Persister(new Format(new HyphenStyle()));
                    try {
                        Macro macro = (Macro) persister.read(Macro.class, sb2);
                        addMacro(persister, macro, this.mGroupId);
                        MacroLoaderResult macroLoaded = MacroLoaderResult.macroLoaded(0, macro);
                        this.mResult = macroLoaded;
                        return macroLoaded;
                    } catch (Exception e2) {
                        try {
                            addMacroGroup(persister, (Group) persister.read(Group.class, sb2), this.mGroupId);
                            MacroLoaderResult groupLoaded = MacroLoaderResult.groupLoaded(0);
                            this.mResult = groupLoaded;
                            return groupLoaded;
                        } catch (Exception e3) {
                            if (sb2.startsWith("<group")) {
                                throw e3;
                            }
                            throw e2;
                        }
                    }
                }
            }
        } catch (Exception e4) {
            Throwable cause = e4.getCause();
            Throwable th = e4;
            if (cause != null) {
                th = e4.getCause();
            }
            MacroLoaderResult loadingFailed = MacroLoaderResult.loadingFailed(0, th);
            this.mResult = loadingFailed;
            return loadingFailed;
        }
    }
}
