package hanati.snmp.mibparser.phase.xref;

public class RFC_1212SymbolDefiner extends AbstractSymbolDefiner {
    public RFC_1212SymbolDefiner() {
        super("RFC-1212");
    }

    public void defineSymbols() {
        this.addObjectTypeMacro();
        this.addIndexSyntaxType();
    }
}
