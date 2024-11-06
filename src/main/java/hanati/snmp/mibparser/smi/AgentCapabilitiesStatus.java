package hanati.snmp.mibparser.smi;

public enum AgentCapabilitiesStatus {
    CURRENT(StatusAll.CURRENT),
    OBSOLETE(StatusAll.OBSOLETE),
    DEPRECATED(StatusAll.DEPRECATED);

    private StatusAll m_statusAll;

    private AgentCapabilitiesStatus(StatusAll statusAll) {
        this.m_statusAll = statusAll;
    }

    public StatusAll getStatusAll() {
        return this.m_statusAll;
    }

    public String toString() {
        return this.m_statusAll.toString();
    }

    public static AgentCapabilitiesStatus find(String keyword, boolean mandatory) {
        return (AgentCapabilitiesStatus)Util.find(AgentCapabilitiesStatus.class, keyword, mandatory);
    }
}
