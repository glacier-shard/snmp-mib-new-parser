package hanati.snmp.mibparser.smi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hanati.snmp.mibparser.phase.xref.XRefProblemReporter;
import hanati.snmp.mibparser.util.token.IdToken;

public class SmiRow extends SmiObjectType {
    private List<SmiRow> parentRows = new ArrayList();
    private List<SmiRow> childRows = new ArrayList();
    private List<SmiIndex> indexes;
    private ScopedId augmentsId;

    public SmiRow(IdToken idToken, SmiModule module) {
        super(idToken, module);
    }

    public SmiTable getTable() {
        return (SmiTable)this.getNode().getParent().getSingleValue(SmiTable.class, this.getModule());
    }

    public List<SmiVariable> getColumns() {
        List<SmiVariable> result = new ArrayList();
        Iterator i$ = this.getNode().getChildren().iterator();

        while(i$.hasNext()) {
            SmiOidNode child = (SmiOidNode)i$.next();
            result.add(child.getSingleValue(SmiVariable.class));
        }

        return result;
    }

    public SmiRow getAugments() {
        return this.augmentsId != null ? (SmiRow)this.augmentsId.getSymbol() : null;
    }

    public void setAugmentsId(ScopedId augmentsId) {
        this.augmentsId = augmentsId;
    }

    public List<SmiIndex> getIndexes() {
        return this.indexes;
    }

    public List<SmiRow> getChildRows() {
        return this.childRows;
    }

    public List<SmiRow> getParentRows() {
        return this.parentRows;
    }

    public SmiVariable findColumn(String id) {
        Iterator i$ = this.getColumns().iterator();

        SmiVariable c;
        do {
            if (!i$.hasNext()) {
                return null;
            }

            c = (SmiVariable)i$.next();
        } while(!c.getId().equals(id));

        return c;
    }

    public SmiIndex addIndex(ScopedId scopedId, boolean isImplied) {
        if (this.indexes == null) {
            this.indexes = new ArrayList();
        }

        SmiIndex index = new SmiIndex(this, scopedId, isImplied);
        this.indexes.add(index);
        return index;
    }

    public boolean hasSameIndexes(SmiRow other) {
        boolean result = false;
        if (this.indexes.size() == other.indexes.size()) {
            boolean tmpResult = true;
            Iterator<SmiIndex> i = this.indexes.iterator();
            Iterator<SmiIndex> j = other.getIndexes().iterator();

            while(tmpResult && i.hasNext() && j.hasNext()) {
                SmiIndex i1 = (SmiIndex)i.next();
                SmiIndex i2 = (SmiIndex)j.next();
                if (i1.getColumn() != i2.getColumn()) {
                    tmpResult = false;
                }

                if (i1.isImplied() != i2.isImplied()) {
                    System.out.printf("implied index is not the same for %s and %s", this.getId(), other.getId()).println();
                    tmpResult = false;
                }
            }

            result = tmpResult;
        }

        return result;
    }

    public void addParentRow(SmiRow row) {
        this.parentRows.add(row);
        row.childRows.add(this);
    }

    public void resolveReferences(XRefProblemReporter reporter) {
        super.resolveReferences(reporter);
        if (this.indexes != null) {
            Iterator i$ = this.indexes.iterator();

            while(i$.hasNext()) {
                SmiIndex index = (SmiIndex)i$.next();
                index.resolveReferences(reporter);
            }
        } else {
            this.augmentsId.resolveReferences(reporter);
            SmiRow augmentedRow = this.getAugments();
            if (augmentedRow != null) {
                augmentedRow.childRows.add(this);
                this.parentRows.add(augmentedRow);
            }
        }

    }
}
