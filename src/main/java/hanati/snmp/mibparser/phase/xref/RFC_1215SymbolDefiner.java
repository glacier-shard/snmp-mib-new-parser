package hanati.snmp.mibparser.phase.xref;

public class RFC_1215SymbolDefiner extends AbstractSymbolDefiner {
    public RFC_1215SymbolDefiner() {
        super("RFC-1215");
    }

    public void defineSymbols() {
        this.addTrapTypeMacro();
    }
}
