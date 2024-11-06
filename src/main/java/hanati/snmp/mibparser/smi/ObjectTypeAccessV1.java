package hanati.snmp.mibparser.smi;

public enum ObjectTypeAccessV1 implements AccessPermissions {
    READ_ONLY(AccessAll.READ_ONLY),
    READ_WRITE(AccessAll.READ_WRITE),
    WRITE_ONLY(AccessAll.WRITE_ONLY),
    NOT_ACCESSIBLE(AccessAll.NOT_ACCESSIBLE);

    private AccessAll accessAll;

    private ObjectTypeAccessV1(AccessAll accessAll) {
        this.accessAll = accessAll;
    }

    public AccessAll getAccessAll() {
        return this.accessAll;
    }

    public String toString() {
        return this.accessAll.toString();
    }

    public static ObjectTypeAccessV1 find(String keyword, boolean mandatory) {
        return (ObjectTypeAccessV1)Util.find(ObjectTypeAccessV1.class, keyword, mandatory);
    }

    public boolean isCreateWritable() {
        return this.isWritable();
    }

    public boolean isReadable() {
        return this == READ_ONLY || this == READ_WRITE;
    }

    public boolean isWritable() {
        return this == WRITE_ONLY || this == READ_WRITE;
    }
}
