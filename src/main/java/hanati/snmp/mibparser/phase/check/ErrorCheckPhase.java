package hanati.snmp.mibparser.phase.check;

import hanati.snmp.mibparser.exception.SmiException;
import hanati.snmp.mibparser.phase.Phase;
import hanati.snmp.mibparser.smi.SmiMib;

public class ErrorCheckPhase implements Phase {
    public ErrorCheckPhase() {
    }

    public Object getOptions() {
        return null;
    }

    public SmiMib process(SmiMib mib) throws SmiException {
        return mib;
    }
}
