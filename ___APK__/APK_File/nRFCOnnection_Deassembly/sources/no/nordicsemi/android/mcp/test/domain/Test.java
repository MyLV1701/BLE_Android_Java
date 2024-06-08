package no.nordicsemi.android.mcp.test.domain;

import java.util.LinkedList;
import java.util.List;
import no.nordicsemi.android.dfu.DfuBaseService;
import no.nordicsemi.android.mcp.domain.common.HasDescription;
import no.nordicsemi.android.mcp.test.domain.command.AbortReliableWrite;
import no.nordicsemi.android.mcp.test.domain.command.AssertNotification;
import no.nordicsemi.android.mcp.test.domain.command.AssertService;
import no.nordicsemi.android.mcp.test.domain.command.BeginReliableWrite;
import no.nordicsemi.android.mcp.test.domain.command.Bond;
import no.nordicsemi.android.mcp.test.domain.command.Connect;
import no.nordicsemi.android.mcp.test.domain.command.Disconnect;
import no.nordicsemi.android.mcp.test.domain.command.DiscoverServices;
import no.nordicsemi.android.mcp.test.domain.command.ExecuteReliableWrite;
import no.nordicsemi.android.mcp.test.domain.command.Read;
import no.nordicsemi.android.mcp.test.domain.command.ReadDescriptor;
import no.nordicsemi.android.mcp.test.domain.command.ReadPhy;
import no.nordicsemi.android.mcp.test.domain.command.ReadRSSI;
import no.nordicsemi.android.mcp.test.domain.command.Refresh;
import no.nordicsemi.android.mcp.test.domain.command.RequestConnectionPriority;
import no.nordicsemi.android.mcp.test.domain.command.RequestMTU;
import no.nordicsemi.android.mcp.test.domain.command.Scan;
import no.nordicsemi.android.mcp.test.domain.command.ScanFor;
import no.nordicsemi.android.mcp.test.domain.command.SendDFU;
import no.nordicsemi.android.mcp.test.domain.command.SetPreferredPhy;
import no.nordicsemi.android.mcp.test.domain.command.Sleep;
import no.nordicsemi.android.mcp.test.domain.command.SleepIf;
import no.nordicsemi.android.mcp.test.domain.command.SleepUntil;
import no.nordicsemi.android.mcp.test.domain.command.Unbond;
import no.nordicsemi.android.mcp.test.domain.command.Write;
import no.nordicsemi.android.mcp.test.domain.command.WriteDescriptor;
import no.nordicsemi.android.mcp.test.domain.common.HasTarget;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

@Root
/* loaded from: classes.dex */
public class Test extends HasDescription implements HasTarget {

    @Attribute
    private String id;

    @ElementListUnion({@ElementList(entry = "bond", inline = true, type = Bond.class), @ElementList(entry = "unbond", inline = true, type = Unbond.class), @ElementList(entry = "connect", inline = true, type = Connect.class), @ElementList(entry = "discover-services", inline = true, type = DiscoverServices.class), @ElementList(entry = "refresh", inline = true, type = Refresh.class), @ElementList(entry = "assert-service", inline = true, type = AssertService.class), @ElementList(entry = "read", inline = true, type = Read.class), @ElementList(entry = "read-descriptor", inline = true, type = ReadDescriptor.class), @ElementList(entry = "read-rssi", inline = true, type = ReadRSSI.class), @ElementList(entry = "write", inline = true, type = Write.class), @ElementList(entry = "write-descriptor", inline = true, type = WriteDescriptor.class), @ElementList(entry = "assert-notification", inline = true, type = AssertNotification.class), @ElementList(entry = "disconnect", inline = true, type = Disconnect.class), @ElementList(entry = "scan", inline = true, type = Scan.class), @ElementList(entry = "scan-for", inline = true, type = ScanFor.class), @ElementList(entry = "sleep", inline = true, type = Sleep.class), @ElementList(entry = "sleep-if", inline = true, type = SleepIf.class), @ElementList(entry = "sleep-until", inline = true, type = SleepUntil.class), @ElementList(entry = DfuBaseService.NOTIFICATION_CHANNEL_DFU, inline = true, type = SendDFU.class), @ElementList(entry = "request-mtu", inline = true, type = RequestMTU.class), @ElementList(entry = "request-connection-priority", inline = true, type = RequestConnectionPriority.class), @ElementList(entry = "begin-reliable-write", inline = true, type = BeginReliableWrite.class), @ElementList(entry = "execute-reliable-write", inline = true, type = ExecuteReliableWrite.class), @ElementList(entry = "abort-reliable-write", inline = true, type = AbortReliableWrite.class), @ElementList(entry = "read-phy", inline = true, type = ReadPhy.class), @ElementList(entry = "set-preferred-phy", inline = true, type = SetPreferredPhy.class)})
    private List<Operation> operations;

    @Attribute(required = false)
    private String target;
    private Target targetDevice;

    public Test(@Attribute(name = "id") String str) {
        super("Test");
        this.operations = new LinkedList();
        this.id = str;
    }

    @Validate
    private void validate() {
        if (this.target != null) {
            return;
        }
        for (Operation operation : this.operations) {
            if (operation.isTargetRequired() && operation.getTargetId() == null) {
                throw new PersistenceException("Target device not specified for %s", operation.getDescription());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getId() {
        return this.id;
    }

    public List<Operation> getOperations() {
        return this.operations;
    }

    @Override // no.nordicsemi.android.mcp.test.domain.common.HasTarget
    public Target getTarget() {
        return this.targetDevice;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getTargetId() {
        return this.target;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean matchesTarget(Target target) {
        String str = this.target;
        return str != null && str.equals(target.getId());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTarget(Target target) {
        this.targetDevice = target;
        for (Operation operation : this.operations) {
            if (operation.usesTestTarget() && operation.getTargetId() == null) {
                operation.setTarget(target);
            }
        }
    }

    @Override // no.nordicsemi.android.mcp.test.domain.common.HasTarget
    public boolean usesTestTarget() {
        return true;
    }

    public Test(@Attribute(name = "id") String str, @Attribute(name = "description") String str2) {
        super(str2);
        this.operations = new LinkedList();
        this.id = str;
    }
}
