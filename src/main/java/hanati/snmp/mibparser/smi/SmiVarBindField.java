package hanati.snmp.mibparser.smi;

public enum SmiVarBindField {
    INTEGER_VALUE,
    STRING_VALUE,
    OBJECTID_VALUE,
    IPADDRESS_VALUE,
    COUNTER_VALUE,
    TIMETICKS_VALUE,
    ARBITRARY_VALUE,
    BIG_COUNTER_VALUE,
    UNSIGNED_INTEGER_VALUE;

    private SmiVarBindField() {
    }
}
