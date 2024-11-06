package hanati.snmp.mibparser.smi;

public enum StatusV1 {
    MANDATORY(StatusAll.MANDATORY),
    OPTIONAL(StatusAll.OPTIONAL),
    OBSOLETE(StatusAll.OBSOLETE),
    DEPRECATED(StatusAll.DEPRECATED);

    private StatusAll statusAll;

    private StatusV1(StatusAll statusAll) {
        this.statusAll = statusAll;
    }

    public StatusAll getStatusAll() {
        return this.statusAll;
    }

    public String toString() {
        return this.statusAll.toString();
    }

    public static StatusV1 find(String keyword, boolean mandatory) {
        return (StatusV1)Util.find(StatusV1.class, keyword, mandatory);
    }
}
