package hanati.snmp.mibparser.smi;

import hanati.snmp.mibparser.phase.xref.XRefProblemReporter;
import hanati.snmp.mibparser.util.token.IdToken;

public class SmiObjectType extends SmiOidMacro {
    private static final long serialVersionUID = 6585155497226498011L;
    protected SmiType type;
    private IdToken accessToken;
    private IdToken maxAccessToken;
    private String description;
    private ObjectTypeAccessV1 accessV1;
    private ObjectTypeAccessV2 accessV2;
    private AccessAll accessAll;

    public SmiObjectType(IdToken idToken, SmiModule module) {
        super(idToken, module);
    }

    public SmiType getType() {
        return this.type;
    }

    public void setType(SmiType type) {
        this.type = type;
    }

    public void resolveReferences(XRefProblemReporter reporter) {
        this.type = this.type.resolveThis(reporter, (SmiType)null);
        if (this.accessToken != null) {
            this.accessV1 = ObjectTypeAccessV1.find(this.accessToken.getId(), false);
            if (this.accessV1 != null) {
                this.accessAll = this.accessV1.getAccessAll();
            } else {
                reporter.reportInvalidAccess(this.accessToken);
                this.accessAll = AccessAll.find(this.accessToken.getId(), false);
            }
        } else {
            this.accessV2 = ObjectTypeAccessV2.find(this.maxAccessToken.getId(), false);
            if (this.accessV2 != null) {
                this.accessAll = this.accessV2.getAccessAll();
            } else {
                reporter.reportInvalidMaxAccess(this.maxAccessToken);
                this.accessAll = AccessAll.find(this.maxAccessToken.getId(), false);
            }
        }

    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(StatusAll status) {
        this.status = status;
    }

    public IdToken getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(IdToken accessToken) {
        this.accessToken = accessToken;
    }

    public IdToken getMaxAccessToken() {
        return this.maxAccessToken;
    }

    public void setMaxAccessToken(IdToken maxAccessToken) {
        this.maxAccessToken = maxAccessToken;
    }

    public ObjectTypeAccessV1 getAccessV1() {
        return this.accessV1;
    }

    public ObjectTypeAccessV2 getAccessV2() {
        return this.accessV2;
    }

    public ObjectTypeAccessV2 getMaxAccess() {
        return this.accessV2;
    }

    public AccessAll getAccessAll() {
        return this.accessAll;
    }
}
