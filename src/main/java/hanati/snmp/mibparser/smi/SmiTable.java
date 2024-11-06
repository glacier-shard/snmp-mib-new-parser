package hanati.snmp.mibparser.smi;

import hanati.snmp.mibparser.util.token.IdToken;

public class SmiTable extends SmiObjectType {
    public SmiTable(IdToken idToken, SmiModule module) {
        super(idToken, module);
    }

    public SmiRow getRow() {
        return (SmiRow)((SmiOidNode)this.getNode().getChildren().iterator().next()).getSingleValue(SmiRow.class);
    }

    public String getCodeId() {
        return this.getModule().getMib().getCodeNamingStrategy().getTableId(this);
    }
}
