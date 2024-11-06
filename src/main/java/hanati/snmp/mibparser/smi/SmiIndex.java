package hanati.snmp.mibparser.smi;

import hanati.snmp.mibparser.phase.xref.XRefProblemReporter;

public class SmiIndex {
    private final ScopedId scopedId;
    private final SmiRow row;
    private final boolean implied;

    public SmiIndex(SmiRow row, ScopedId scopedId, boolean implied) {
        this.row = row;
        this.scopedId = scopedId;
        this.implied = implied;
    }

    public boolean isImplied() {
        return this.implied;
    }

    public SmiVariable getColumn() {
        return (SmiVariable)this.scopedId.getSymbol();
    }

    public SmiRow getRow() {
        return this.row;
    }

    public boolean isColumnFromOtherTable() {
        return this.row.getTable() != this.getColumn().getTable();
    }

    public void resolveReferences(XRefProblemReporter reporter) {
        this.scopedId.resolveReferences(reporter);
    }
}
