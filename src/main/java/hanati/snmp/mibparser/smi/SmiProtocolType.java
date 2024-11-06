package hanati.snmp.mibparser.smi;

import hanati.snmp.mibparser.util.token.IdToken;

public class SmiProtocolType extends SmiType {
    public SmiProtocolType(IdToken idToken, SmiModule module) {
        super(idToken, module);
    }

    public static SmiType createChoiceType(IdToken idToken, SmiModule module) {
        if (idToken.getId().equals("NetworkAddress")) {
            SmiType result = new SmiType(idToken, module);
            result.setBaseType(new SmiReferencedType(new IdToken(idToken.getLocation(), "IpAddress"), module));
            return result;
        } else {
            return new SmiProtocolType(idToken, module);
        }
    }
}
