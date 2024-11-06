package hanati.snmp.mibparser.smi;

public enum SmiPrimitiveType {
    ENUM(SmiVarBindField.INTEGER_VALUE, "", true, true),
    INTEGER(SmiVarBindField.INTEGER_VALUE, "Int", true, true),
    OCTET_STRING(SmiVarBindField.STRING_VALUE, "Octs", false, true),
    BIT_STRING(SmiVarBindField.STRING_VALUE, "BitS", false, true),
    OBJECT_IDENTIFIER(SmiVarBindField.OBJECTID_VALUE, "Oid", false, true),
    INTEGER_32(SmiVarBindField.INTEGER_VALUE, "Integer32", false, true),
    IP_ADDRESS(SmiVarBindField.IPADDRESS_VALUE, "IpAddress", false, true),
    COUNTER_32(SmiVarBindField.COUNTER_VALUE, "Counter32", false, true),
    GAUGE_32(SmiVarBindField.UNSIGNED_INTEGER_VALUE, "Gauge32", false, true),
    UNSIGNED_32(SmiVarBindField.UNSIGNED_INTEGER_VALUE, "Unsigned32", false, true),
    TIME_TICKS(SmiVarBindField.TIMETICKS_VALUE, "TimeTicks", false, true),
    OPAQUE(SmiVarBindField.ARBITRARY_VALUE, "Opaque", false, true),
    COUNTER_64(SmiVarBindField.BIG_COUNTER_VALUE, "Counter64", false, true),
    BITS(SmiVarBindField.STRING_VALUE, "Bits", false, true);

    private SmiVarBindField varBindField;
    private String xmlValue;
    private boolean namedNumbersSupported;
    private boolean rangesSupported;

    private SmiPrimitiveType(SmiVarBindField varBindField, String xmlValue, boolean allowsNamedNumbers, boolean allowsRanges) {
        this.varBindField = varBindField;
        this.xmlValue = xmlValue;
        this.namedNumbersSupported = allowsNamedNumbers;
        this.rangesSupported = allowsRanges;
    }

    public SmiVarBindField getVarBindField() {
        return this.varBindField;
    }

    public SmiPrimitiveType fromXmlValue(String xmlValue) {
        SmiPrimitiveType[] arr$ = values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            SmiPrimitiveType pt = arr$[i$];
            if (pt.xmlValue.equals(xmlValue)) {
                return pt;
            }
        }

        return null;
    }

    public String getXmlValue() {
        return this.xmlValue;
    }

    public boolean isNamedNumbersSupported() {
        return this.namedNumbersSupported;
    }

    public boolean isRangesSupported() {
        return this.rangesSupported;
    }
}
