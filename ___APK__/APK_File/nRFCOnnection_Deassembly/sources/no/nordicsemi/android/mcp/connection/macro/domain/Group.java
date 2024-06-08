package no.nordicsemi.android.mcp.connection.macro.domain;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import no.nordicsemi.android.mcp.domain.common.HasName;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

/* loaded from: classes.dex */
public class Group extends HasName {

    @ElementList(entry = "group", inline = true, required = false)
    private List<Group> groups;

    @ElementList(entry = "macro", inline = true, required = false)
    private List<Macro> macros;

    public Group() {
        super("");
        this.groups = new ArrayList(4);
        this.macros = new ArrayList(8);
    }

    public List<Group> getGroups() {
        return this.groups;
    }

    public List<Macro> getMacros() {
        return this.macros;
    }

    @Validate
    protected void validate() {
        if (!TextUtils.isEmpty(getName())) {
            setName(getName().trim());
            return;
        }
        throw new PersistenceException("Name must not be empty", new Object[0]);
    }

    public Group(@Attribute(name = "name") String str) {
        super(str);
        this.groups = new ArrayList(4);
        this.macros = new ArrayList(8);
    }
}
