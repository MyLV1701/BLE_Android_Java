package no.nordicsemi.android.mcp.test.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import no.nordicsemi.android.mcp.database.CCCDContract;
import no.nordicsemi.android.mcp.domain.common.HasDescription;
import no.nordicsemi.android.mcp.test.domain.common.HasBindTarget;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

@Root
/* loaded from: classes.dex */
public class TestSuite extends HasDescription {

    @ElementList(entry = "run-test", inline = true)
    private List<RunTestCommand> runTestCommands;

    @ElementList(entry = "target", inline = true, required = false)
    private List<Target> targets;

    @ElementList(entry = "test", inline = true)
    private List<Test> tests;

    @ElementMap(attribute = true, entry = "set", inline = true, key = "name", required = false, value = CCCDContract.CCCDColumns.VALUE)
    private Map<String, String> variables;

    public TestSuite() {
        super("Test suite");
        this.variables = new HashMap();
        this.targets = new ArrayList();
    }

    public List<RunTestCommand> getRunTestCommands() {
        return this.runTestCommands;
    }

    public Map<String, String> getVariables() {
        return this.variables;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Validate
    public void validate() {
        HasBindTarget hasBindTarget;
        String bindTargetId;
        for (RunTestCommand runTestCommand : this.runTestCommands) {
            Iterator<Test> it = this.tests.iterator();
            while (true) {
                if (it.hasNext()) {
                    Test next = it.next();
                    if (runTestCommand.matchesTest(next)) {
                        runTestCommand.setTest(next);
                        break;
                    }
                }
            }
        }
        for (Test test : this.tests) {
            if (test.getTargetId() != null) {
                Iterator<Target> it2 = this.targets.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        Target next2 = it2.next();
                        if (test.matchesTarget(next2)) {
                            test.setTarget(next2);
                            break;
                        }
                    }
                }
            }
        }
        Iterator<Test> it3 = this.tests.iterator();
        while (it3.hasNext()) {
            for (Operation operation : it3.next().getOperations()) {
                if (operation.getTargetId() != null) {
                    Iterator<Target> it4 = this.targets.iterator();
                    while (true) {
                        if (!it4.hasNext()) {
                            break;
                        }
                        Target next3 = it4.next();
                        if (operation.matchesTarget(next3)) {
                            operation.setTarget(next3);
                            break;
                        }
                    }
                }
                if ((operation instanceof HasBindTarget) && (bindTargetId = (hasBindTarget = (HasBindTarget) operation).getBindTargetId()) != null) {
                    Iterator<Target> it5 = this.targets.iterator();
                    while (true) {
                        if (it5.hasNext()) {
                            Target next4 = it5.next();
                            if (bindTargetId.equals(next4.getId())) {
                                hasBindTarget.setBindTarget(next4);
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (RunTestCommand runTestCommand2 : this.runTestCommands) {
            if (runTestCommand2.getTest() == null) {
                throw new PersistenceException("Invalid test reference '%s'", runTestCommand2.getTestRef());
            }
        }
        for (Test test2 : this.tests) {
            if (test2.getTargetId() != null && test2.getTarget() == null) {
                throw new PersistenceException("Invalid target reference '%s'", test2.getTargetId());
            }
            for (Operation operation2 : test2.getOperations()) {
                if (operation2.isTargetRequired() && operation2.getTarget() == null) {
                    throw new PersistenceException("Invalid target reference '%s'", operation2.getTargetId());
                }
                if (operation2 instanceof HasBindTarget) {
                    HasBindTarget hasBindTarget2 = (HasBindTarget) operation2;
                    if (hasBindTarget2.getBindTargetId() != null && hasBindTarget2.getBindTarget() == null) {
                        throw new PersistenceException("Invalid bind-target reference '%s'", hasBindTarget2.getBindTargetId());
                    }
                }
            }
        }
    }

    public TestSuite(@Attribute(name = "description") String str) {
        super(str);
        this.variables = new HashMap();
        this.targets = new ArrayList();
    }
}
