package hanati.snmp.mibparser.phase.xref;

import hanati.snmp.mibparser.smi.SmiModule;

public interface SymbolDefiner {
    String getModuleId();

    void defineSymbols(SmiModule var1);
}
