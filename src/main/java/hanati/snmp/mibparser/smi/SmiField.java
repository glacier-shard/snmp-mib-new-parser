package hanati.snmp.mibparser.smi;

import hanati.snmp.mibparser.phase.xref.XRefProblemReporter;
import hanati.snmp.mibparser.util.token.IdToken;

public class SmiField {
    private SmiType parentType;
    private IdToken columnIdToken;
    private SmiVariable column;
    private SmiType type;

    public SmiField(SmiType parentType, IdToken columnIdToken, SmiType type) {
        this.parentType = parentType;
        this.columnIdToken = columnIdToken;
        this.type = type;
    }

    public SmiType getParentType() {
        return this.parentType;
    }

    public IdToken getColumnIdToken() {
        return this.columnIdToken;
    }

    public SmiVariable getColumn() {
        return this.column;
    }

    public SmiType getType() {
        return this.type;
    }

    public void resolveReferences(XRefProblemReporter reporter) {
        this.column = (SmiVariable)this.parentType.getModule().resolveReference(this.columnIdToken, SmiVariable.class, reporter);
    }
}
