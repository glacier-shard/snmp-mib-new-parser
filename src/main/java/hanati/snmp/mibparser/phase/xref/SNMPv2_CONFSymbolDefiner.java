package hanati.snmp.mibparser.phase.xref;

public class SNMPv2_CONFSymbolDefiner extends AbstractSymbolDefiner {
    protected SNMPv2_CONFSymbolDefiner() {
        super("SNMPv2-CONF");
    }

    protected void defineSymbols() {
        super.defineSymbols();
        this.addMacro("OBJECT-GROUP");
        this.addMacro("NOTIFICATION-GROUP");
        this.addMacro("MODULE-COMPLIANCE");
        this.addMacro("AGENT-CAPABILITIES");
    }
}
