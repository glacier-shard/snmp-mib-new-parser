package hanati.snmp.mibparser.smi;

import java.util.ArrayList;
import java.util.List;

import hanati.snmp.mibparser.util.token.IdToken;

public class SmiOidMacro extends SmiOidValue {
    private static final long serialVersionUID = 7169921949233977970L;
    protected StatusAll status;
    protected List<String> references = new ArrayList();

    public SmiOidMacro(IdToken idToken, SmiModule module) {
        super(idToken, module);
    }

    public StatusAll getStatus() {
        return this.status;
    }

    public StatusV1 getStatusV1() {
        return this.status.getStatusV1();
    }

    public StatusV2 getStatusV2() {
        return this.status.getStatusV2();
    }

    public List<String> getReferences() {
        return this.references;
    }

    public void addReference(String reference) {
        this.references.add(reference);
    }
}
