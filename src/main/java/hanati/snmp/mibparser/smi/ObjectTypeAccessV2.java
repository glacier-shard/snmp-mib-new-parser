package hanati.snmp.mibparser.smi;

public enum ObjectTypeAccessV2 implements AccessPermissions {
    NOT_ACCESSIBLE(AccessAll.NOT_ACCESSIBLE),
    ACCESSIBLE_FOR_NOTIFY(AccessAll.ACCESSIBLE_FOR_NOTIFY),
    READ_ONLY(AccessAll.READ_ONLY),
    READ_WRITE(AccessAll.READ_WRITE),
    READ_CREATE(AccessAll.READ_CREATE);

    private AccessAll accessAll;

    private ObjectTypeAccessV2(AccessAll accessAll) {
        this.accessAll = accessAll;
    }

    public AccessAll getAccessAll() {
        return this.accessAll;
    }

    public String toString() {
        return this.accessAll.toString();
    }

    public static ObjectTypeAccessV2 find(String keyword, boolean mandatory) {
        return (ObjectTypeAccessV2)Util.find(ObjectTypeAccessV2.class, keyword, mandatory);
    }

    public boolean isCreateWritable() {
        return this.isWritable() || this == READ_CREATE;
    }

    public boolean isReadable() {
        return this == READ_ONLY || this == READ_WRITE || this == READ_CREATE;
    }

    public boolean isWritable() {
        return this == READ_WRITE;
    }
}
