package no.nordicsemi.android.mcp.test.domain;

import java.util.Map;
import no.nordicsemi.android.mcp.database.CCCDContract;
import no.nordicsemi.android.mcp.domain.common.HasDescription;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

@Root
/* loaded from: classes.dex */
public class RunTestCommand extends HasDescription {

    @Attribute
    private String ref;
    private Test test;

    @ElementMap(attribute = true, entry = "set", inline = true, key = "name", required = false, value = CCCDContract.CCCDColumns.VALUE)
    private Map<String, String> variables;

    public RunTestCommand(@Attribute(name = "ref") String str) {
        super("Run-Test");
        this.ref = str;
    }

    public Map<String, String> getLocalVariables() {
        return this.variables;
    }

    public Test getTest() {
        return this.test;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getTestRef() {
        return this.ref;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matchesTest(Test test) {
        return this.ref.equals(test.getId());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTest(Test test) {
        this.test = test;
    }

    public RunTestCommand(@Attribute(name = "ref") String str, @Attribute(name = "description") String str2) {
        super(str2);
        this.ref = str;
    }
}
