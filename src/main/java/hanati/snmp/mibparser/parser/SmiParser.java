package hanati.snmp.mibparser.parser;

import hanati.snmp.mibparser.exception.SmiException;
import hanati.snmp.mibparser.smi.SmiMib;

public interface SmiParser {
    SmiMib parse() throws SmiException;
}
