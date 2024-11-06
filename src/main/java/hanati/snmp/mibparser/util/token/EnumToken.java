package hanati.snmp.mibparser.util.token;

import hanati.snmp.mibparser.util.location.Location;

public class EnumToken<E extends Enum> extends GenericToken<E> {
    public EnumToken(Location location, Class<E> enumClass, String value) {
        super(location, determineValue(enumClass.getEnumConstants(), value));
    }

    public EnumToken(Location location, E value) {
        super(location, value);
    }

    private static <E> E determineValue(E[] enumConstants, String value) {
        Object[] arr$ = enumConstants;
        int len$ = enumConstants.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            E e = (E) arr$[i$];
            if (e.toString().equals(value)) {
                return e;
            }
        }

        throw new IllegalArgumentException("No enum found for " + value);
    }
}
