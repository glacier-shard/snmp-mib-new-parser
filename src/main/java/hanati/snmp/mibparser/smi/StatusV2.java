package hanati.snmp.mibparser.smi;

public enum StatusV2 {
    CURRENT(StatusAll.CURRENT),
    DEPRECATED(StatusAll.DEPRECATED),
    OBSOLETE(StatusAll.OBSOLETE),
    MANDATORY(StatusAll.MANDATORY);

    private StatusAll statusAll;

    private StatusV2(StatusAll statusAll) {
        this.statusAll = statusAll;
    }

    public StatusAll getStatusAll() {
        return this.statusAll;
    }

    public String toString() {
        return this.statusAll.toString();
    }

    public static StatusV2 find(String keyword, boolean mandatory) {
        return (StatusV2)Util.find(StatusV2.class, keyword, mandatory);
    }
}
