package hanati.snmp.mibparser.smi;

public enum AccessAll implements AccessPermissions {
    READ_ONLY,
    READ_WRITE,
    WRITE_ONLY,
    NOT_ACCESSIBLE,
    ACCESSIBLE_FOR_NOTIFY,
    READ_CREATE,
    NOT_IMPLEMENTED;

    private String keyword = this.name().toLowerCase().replace('_', '-');

    private AccessAll() {
    }

    public String toString() {
        return this.keyword;
    }

    public static AccessAll find(String keyword, boolean mandatory) {
        return (AccessAll)Util.find(AccessAll.class, keyword, mandatory);
    }

    public boolean isCreateWritable() {
        return this.isWritable() || this == READ_CREATE;
    }

    public boolean isReadable() {
        return this == READ_ONLY || this == READ_WRITE || this == READ_CREATE;
    }

    public boolean isWritable() {
        return this == READ_WRITE || this == WRITE_ONLY;
    }
}
