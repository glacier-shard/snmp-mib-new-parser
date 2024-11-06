package hanati.snmp.mibparser.smi;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import hanati.snmp.mibparser.util.location.Location;
import hanati.snmp.mibparser.util.token.IdToken;

public class SmiConstants {
    public static final Set<String> SMI_DEFINITION_MODULE_NAMES = initSmiDefinitionModuleNames();
    public static final SmiMib JSMIPARSER_HARDCODED_MIB = new SmiMib(new SmiOptions(), (SmiCodeNamingStrategy)null);
    public static final SmiModule JSMIPARSER_HARDCODED_MODULE;
    public static final SmiType OBJECT_IDENTIFIER_TYPE;
    public static final SmiType OCTET_STRING_TYPE;
    public static final SmiType BIT_STRING_TYPE;
    public static final SmiType BITS_TYPE;
    public static final SmiType INTEGER_TYPE;
    public static final String[] MACRO_NAMES;
    public static final Location LOCATION;

    public SmiConstants() {
    }

    private static SmiType newType(String id, SmiPrimitiveType primitiveType) {
        return new SmiType(new IdToken(LOCATION, id), JSMIPARSER_HARDCODED_MODULE, primitiveType);
    }

    private static Set<String> initSmiDefinitionModuleNames() {
        return Collections.unmodifiableSet(new HashSet());
    }

    static {
        JSMIPARSER_HARDCODED_MODULE = new SmiModule(JSMIPARSER_HARDCODED_MIB, new IdToken((Location)null, "JSMIPARSER_HARDCODED_MIB"));
        OBJECT_IDENTIFIER_TYPE = newType("OBJECT IDENTIFIER", SmiPrimitiveType.OBJECT_IDENTIFIER);
        OCTET_STRING_TYPE = newType("OCTET STRING", SmiPrimitiveType.OCTET_STRING);
        BIT_STRING_TYPE = newType("BIT STRING", SmiPrimitiveType.BIT_STRING);
        BITS_TYPE = newType("BITS", SmiPrimitiveType.BITS);
        INTEGER_TYPE = newType("INTEGER", SmiPrimitiveType.INTEGER);
        MACRO_NAMES = new String[]{"AGENT-CAPABILITIES", "MODULE-COMPLIANCE", "MODULE-IDENTITY", "NOTIFICATION-GROUP", "NOTIFICATION-TYPE", "OBJECT-GROUP", "OBJECT-IDENTITY", "OBJECT-TYPE", "TEXTUAL-CONVENTION", "TRAP-TYPE"};
        LOCATION = new Location("JSMIPARSER_HARDCODED_MIB");
    }
}
