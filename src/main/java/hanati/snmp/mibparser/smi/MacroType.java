package hanati.snmp.mibparser.smi;

public enum MacroType {
    OBJECT_TYPE_V1(SmiVersion.V1, "ACCESS"),
    OBJECT_TYPE_V2(SmiVersion.V2, "MAX-ACCESS"),
    OBJECT_IDENTITY(SmiVersion.V2, (String)null),
    NOTIFICATION_TYPE(SmiVersion.V2, (String)null),
    TEXTUAL_CONVENTION(SmiVersion.V2, (String)null),
    OBJECT_GROUP(SmiVersion.V2, (String)null),
    NOTIFICATION_GROUP(SmiVersion.V2, (String)null),
    MODULE_COMPLIANCE(SmiVersion.V2, "MIN-ACCESS"),
    AGENT_CAPABILITIES(SmiVersion.V2, "ACCESS"),
    TRAP_TYPE(SmiVersion.V1, (String)null);

    private final SmiVersion version;
    private final String accessFieldName;

    private MacroType(SmiVersion version, String accessFieldName) {
        this.version = version;
        this.accessFieldName = accessFieldName;
    }

    public SmiVersion getVersion() {
        return this.version;
    }

    public String getAccessFieldName() {
        return this.accessFieldName;
    }
}
