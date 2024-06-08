package no.nordicsemi.android.mcp.domain.common;

import org.simpleframework.xml.Attribute;

/* loaded from: classes.dex */
public abstract class HasDescription {

    @Attribute(required = false)
    private String description;

    public HasDescription() {
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public HasDescription(@Attribute(name = "description") String str) {
        this.description = str;
    }
}
