package hanati.snmp.mibparser.phase;

import hanati.snmp.mibparser.exception.SmiException;
import hanati.snmp.mibparser.smi.SmiMib;

public interface Phase {
    SmiMib process(SmiMib var1) throws SmiException;
}
