package hanati.snmp.mibparser.phase.file;

import hanati.snmp.mibparser.smi.SmiPrimitiveType;
import hanati.snmp.mibparser.util.location.Location;
import hanati.snmp.mibparser.util.token.IdToken;

public class IntKeywordToken extends IdToken {
    private SmiPrimitiveType m_primitiveType;

    public IntKeywordToken(Location location, String value, SmiPrimitiveType primitiveType) {
        super(location, value);
        if (primitiveType == null) {
            throw new IllegalArgumentException("Primitive type is mandatory.");
        } else {
            this.m_primitiveType = primitiveType;
        }
    }

    public SmiPrimitiveType getPrimitiveType() {
        return this.m_primitiveType;
    }
}
