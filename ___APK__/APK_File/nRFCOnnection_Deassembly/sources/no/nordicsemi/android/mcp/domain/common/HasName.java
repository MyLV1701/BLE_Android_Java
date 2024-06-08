package no.nordicsemi.android.mcp.domain.common;

import org.simpleframework.xml.Attribute;

/* loaded from: classes.dex */
public abstract class HasName {

    @Attribute(required = false)
    private String name;

    public HasName() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public HasName(@Attribute(name = "name") String str) {
        this.name = str;
    }
}
