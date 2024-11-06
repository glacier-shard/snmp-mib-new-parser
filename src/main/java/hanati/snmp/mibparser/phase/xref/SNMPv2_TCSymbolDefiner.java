package hanati.snmp.mibparser.phase.xref;

public class SNMPv2_TCSymbolDefiner extends AbstractSymbolDefiner {
    public SNMPv2_TCSymbolDefiner(String moduleId) {
        super(moduleId);
    }

    protected void defineSymbols() {
        super.defineSymbols();
        this.defineTextualConventionMacro();
    }

    private void defineTextualConventionMacro() {
        this.addMacro("TEXTUAL-CONVENTION");
    }
}
