package hanati.snmp.mibparser.smi;

import hanati.snmp.mibparser.phase.xref.XRefProblemReporter;
import hanati.snmp.mibparser.util.token.IdToken;

public class SmiOidValue extends SmiValue {
    private static final long serialVersionUID = 2299663627379488267L;
    private OidComponent lastOidComponent;
    private SmiOidNode node;

    public SmiOidValue(IdToken idToken, SmiModule module) {
        super(idToken, module);
    }

    public SmiOidValue(IdToken idToken, SmiModule internalModule, SmiOidNode node) {
        super(idToken, internalModule);
        this.node = node;
    }

    public OidComponent getLastOidComponent() {
        return this.lastOidComponent;
    }

    public void setLastOidComponent(OidComponent lastOidComponent) {
        this.lastOidComponent = lastOidComponent;
    }

    public int[] getOid() {
        return this.node.getOid();
    }

    public String getOidStr() {
        return this.node.getOidStr();
    }

    public SmiOidNode resolveOid(XRefProblemReporter reporter) {
        if (this.node == null) {
            this.node = this.lastOidComponent.resolveNode(this.getModule(), reporter);
            if (this.node != null) {
                this.node.getValues().add(this);
            }
        }

        return this.node;
    }

    public SmiOidNode getNode() {
        return this.node;
    }
}
