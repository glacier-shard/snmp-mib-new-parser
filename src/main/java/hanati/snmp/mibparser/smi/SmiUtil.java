package hanati.snmp.mibparser.smi;

import hanati.snmp.mibparser.util.token.IdToken;

public class SmiUtil {
    public SmiUtil() {
    }

    public static String ucFirst(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static String stripLastOidPart(String oid) {
        int index = oid.lastIndexOf(46);
        return oid.substring(0, index);
    }

    public static IdToken ucFirst(IdToken idToken) {
        return new IdToken(idToken.getLocation(), ucFirst(idToken.getId()));
    }
}
