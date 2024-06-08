package no.nordicsemi.android.mcp.test.domain.common;

import android.content.ContentValues;
import java.util.List;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.domain.common.HasDescription;
import no.nordicsemi.android.mcp.test.Result;
import no.nordicsemi.android.mcp.test.domain.enumeration.ExpectedResult;
import no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

/* loaded from: classes.dex */
public abstract class HasExpectedAndDescription extends HasDescription {

    @Attribute(required = false)
    private ExpectedResult expected;

    @Attribute(required = false)
    private Integer expectedErrorCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: no.nordicsemi.android.mcp.test.domain.common.HasExpectedAndDescription$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$no$nordicsemi$android$mcp$test$domain$enumeration$ExpectedResult = new int[ExpectedResult.values().length];

        static {
            try {
                $SwitchMap$no$nordicsemi$android$mcp$test$domain$enumeration$ExpectedResult[ExpectedResult.FAIL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$mcp$test$domain$enumeration$ExpectedResult[ExpectedResult.FAIL_WARNING_ON_SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$mcp$test$domain$enumeration$ExpectedResult[ExpectedResult.SUCCESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$mcp$test$domain$enumeration$ExpectedResult[ExpectedResult.SUCCESS_WARNING_ON_FAIL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public HasExpectedAndDescription() {
        this.expected = ExpectedResult.SUCCESS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void logException(Result result, LogSession logSession, Exception exc) {
        Logger.e(logSession, exc.getLocalizedMessage());
        if (exc.getCause() != null) {
            Logger.e(logSession, exc.getCause().getLocalizedMessage());
        }
        Logger.setSessionMark(logSession, 3);
        String localizedMessage = exc.getLocalizedMessage();
        if (exc.getCause() != null) {
            localizedMessage = localizedMessage + "\n" + exc.getCause().getLocalizedMessage();
        }
        result.setOperationStatus(localizedMessage);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void logFail(Result result, LogSession logSession, String str) {
        logFail(result, logSession, str, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void logSuccess(Result result, LogSession logSession, String str) {
        logSuccess(result, logSession, str, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void logWarning(Result result, LogSession logSession, String str) {
        logWarning(result, logSession, str, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public OperationResult toResult(int i) {
        if (i == 0) {
            if (ExpectedResult.FAIL.equals(this.expected)) {
                return OperationResult.FAIL;
            }
            if (ExpectedResult.FAIL_WARNING_ON_SUCCESS.equals(this.expected)) {
                return OperationResult.WARNING;
            }
            return OperationResult.SUCCESS;
        }
        if (ExpectedResult.SUCCESS.equals(this.expected)) {
            return OperationResult.FAIL;
        }
        if (ExpectedResult.SUCCESS_WARNING_ON_FAIL.equals(this.expected)) {
            return OperationResult.WARNING;
        }
        Integer num = this.expectedErrorCode;
        if (num != null && i != num.intValue()) {
            return OperationResult.FAIL;
        }
        return OperationResult.SUCCESS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Validate
    public void validate() {
        if (this.expectedErrorCode != null && !ExpectedResult.FAIL.equals(this.expected)) {
            throw new PersistenceException("Expected Error Code may be set only if FAIL is expected", new Object[0]);
        }
        Integer num = this.expectedErrorCode;
        if (num != null && num.intValue() <= 0) {
            throw new PersistenceException("Expected Error Code must be grater than 0", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void logFail(Result result, LogSession logSession, String str, List<ContentValues> list) {
        int i;
        int i2 = AnonymousClass1.$SwitchMap$no$nordicsemi$android$mcp$test$domain$enumeration$ExpectedResult[this.expected.ordinal()];
        if (i2 == 3) {
            i = 20;
            result.setOperationStatus(str);
        } else if (i2 != 4) {
            i = 5;
        } else {
            i = 15;
            result.setOperationStatus(str);
        }
        if (list != null) {
            list.add(Logger.logEntry(logSession, i, str));
        } else {
            Logger.log(logSession, i, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void logSuccess(Result result, LogSession logSession, String str, List<ContentValues> list) {
        int i;
        int i2 = AnonymousClass1.$SwitchMap$no$nordicsemi$android$mcp$test$domain$enumeration$ExpectedResult[this.expected.ordinal()];
        if (i2 == 1) {
            i = 20;
            result.setOperationStatus(str);
        } else if (i2 != 2) {
            i = 5;
        } else {
            i = 15;
            result.setOperationStatus(str);
        }
        if (list != null) {
            list.add(Logger.logEntry(logSession, i, str));
        } else {
            Logger.log(logSession, i, str);
        }
    }

    protected void logWarning(Result result, LogSession logSession, String str, List<ContentValues> list) {
        result.setOperationStatus(str);
        if (list != null) {
            list.add(Logger.logEntry(logSession, 15, str));
        } else {
            Logger.log(logSession, 15, str);
        }
    }

    public HasExpectedAndDescription(@Attribute(name = "description") String str) {
        super(str);
        this.expected = ExpectedResult.SUCCESS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void logException(Result result, LogSession logSession, String str) {
        Logger.e(logSession, str);
        Logger.setSessionMark(logSession, 3);
        result.setOperationStatus(str);
    }
}
