package hanati.snmp.mibparser.smi;

import java.util.EnumSet;
import java.util.Set;

public enum StatusAll {
    MANDATORY(new MacroType[]{MacroType.OBJECT_TYPE_V1}),
    OPTIONAL(new MacroType[]{MacroType.OBJECT_TYPE_V1}),
    OBSOLETE(new MacroType[]{MacroType.OBJECT_TYPE_V1, MacroType.OBJECT_IDENTITY, MacroType.NOTIFICATION_TYPE, MacroType.TEXTUAL_CONVENTION, MacroType.OBJECT_GROUP, MacroType.NOTIFICATION_GROUP, MacroType.MODULE_COMPLIANCE, MacroType.AGENT_CAPABILITIES}),
    DEPRECATED(new MacroType[]{MacroType.OBJECT_TYPE_V1, MacroType.OBJECT_IDENTITY, MacroType.NOTIFICATION_TYPE, MacroType.TEXTUAL_CONVENTION, MacroType.OBJECT_GROUP, MacroType.NOTIFICATION_GROUP, MacroType.MODULE_COMPLIANCE}),
    CURRENT(new MacroType[]{MacroType.OBJECT_IDENTITY, MacroType.NOTIFICATION_TYPE, MacroType.TEXTUAL_CONVENTION, MacroType.OBJECT_GROUP, MacroType.NOTIFICATION_GROUP, MacroType.MODULE_COMPLIANCE, MacroType.AGENT_CAPABILITIES});

    private String keyword = this.name().toLowerCase();
    private Set<MacroType> supportedMacroTypes = EnumSet.noneOf(MacroType.class);

    private StatusAll(MacroType... macroTypes) {
        MacroType[] arr$ = macroTypes;
        int len$ = macroTypes.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            MacroType macroType = arr$[i$];
            this.supportedMacroTypes.add(macroType);
        }

    }

    public String toString() {
        return this.keyword;
    }

    public static StatusAll find(String keyword, boolean mandatory) {
        return (StatusAll)Util.find(StatusAll.class, keyword, mandatory);
    }

    public StatusV1 getStatusV1() {
        StatusV1[] arr$ = StatusV1.values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            StatusV1 statusV1 = arr$[i$];
            if (statusV1.getStatusAll() == this) {
                return statusV1;
            }
        }

        return null;
    }

    public StatusV2 getStatusV2() {
        StatusV2[] arr$ = StatusV2.values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            StatusV2 statusV2 = arr$[i$];
            if (statusV2.getStatusAll() == this) {
                return statusV2;
            }
        }

        return null;
    }

    public static StatusAll findV1(MacroType macroType, String keyword) {
        StatusAll result = find(keyword, true);
        if (result.isSupportedBy(macroType)) {
            return result;
        } else {
            throw new IllegalArgumentException("Status " + result + " is not supported by " + macroType);
        }
    }

    public boolean isSupportedBy(MacroType macroType) {
        return this.supportedMacroTypes.contains(macroType);
    }
}
