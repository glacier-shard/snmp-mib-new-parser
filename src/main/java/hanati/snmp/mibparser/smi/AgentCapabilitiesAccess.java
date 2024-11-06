package hanati.snmp.mibparser.smi;

public enum AgentCapabilitiesAccess {
    NOT_IMPLEMENTED(AccessAll.NOT_IMPLEMENTED),
    ACCESSIBLE_FOR_NOTIFY(AccessAll.ACCESSIBLE_FOR_NOTIFY),
    READ_ONLY(AccessAll.READ_ONLY),
    READ_WRITE(AccessAll.READ_WRITE),
    READ_CREATE(AccessAll.READ_CREATE),
    WRITE_ONLY(AccessAll.WRITE_ONLY);

    private AccessAll m_accessAll;

    private AgentCapabilitiesAccess(AccessAll accessAll) {
        this.m_accessAll = accessAll;
    }

    public AccessAll getAccessAll() {
        return this.m_accessAll;
    }

    public String toString() {
        return this.m_accessAll.toString();
    }

    public static AgentCapabilitiesAccess find(String keyword, boolean mandatory) {
        return (AgentCapabilitiesAccess)Util.find(AgentCapabilitiesAccess.class, keyword, mandatory);
    }
}
