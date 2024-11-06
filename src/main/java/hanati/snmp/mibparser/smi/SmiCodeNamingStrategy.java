package hanati.snmp.mibparser.smi;

public interface SmiCodeNamingStrategy {
    String getModuleId(SmiModule var1);

    String getFullModuleId(SmiModule var1);

    String getCodeConstantId(SmiVariable var1);

    String getFullCodeConstantId(SmiVariable var1);

    String getTypeId(SmiType var1);

    String getSingleVariableEnumId(SmiVariable var1);

    String getVariableId(SmiVariable var1);

    String getEnumValueId(SmiNamedNumber var1);

    String getRequestMethodId(SmiVariable var1);

    String getGetterMethodId(SmiVariable var1);

    String getSetterMethodId(SmiVariable var1);

    String getFullCodeId(SmiSymbol var1);

    String getTableId(SmiTable var1);

    String getFullVariableOidClassId(SmiModule var1);

    String getVariableOidClassId(SmiModule var1);
}
