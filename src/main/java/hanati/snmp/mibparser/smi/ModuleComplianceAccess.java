package hanati.snmp.mibparser.smi;

public enum ModuleComplianceAccess {
    NOT_ACCESSIBLE(AccessAll.NOT_ACCESSIBLE),
    ACCESSIBLE_FOR_NOTIFY(AccessAll.ACCESSIBLE_FOR_NOTIFY),
    READ_ONLY(AccessAll.READ_ONLY),
    READ_WRITE(AccessAll.READ_WRITE),
    READ_CREATE(AccessAll.READ_CREATE);

    private AccessAll accessAll;

    private ModuleComplianceAccess(AccessAll accessAll) {
        this.accessAll = accessAll;
    }

    public AccessAll getAccessAll() {
        return this.accessAll;
    }

    public String toString() {
        return this.accessAll.toString();
    }

    public static ModuleComplianceAccess find(String keyword, boolean mandatory) {
        return (ModuleComplianceAccess)Util.find(ModuleComplianceAccess.class, keyword, mandatory);
    }
}
