package hanati.snmp.mibparser.smi;

class Util {
    Util() {
    }

    public static <T> T find(Class<T> cl, String keyword, boolean mandatory) {
        Object[] arr$ = cl.getEnumConstants();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            T enumConstant = (T) arr$[i$];
            if (enumConstant.toString().equals(keyword)) {
                return enumConstant;
            }
        }

        if (mandatory) {
            throw new IllegalArgumentException(keyword + " is not a valid " + cl.getSimpleName());
        } else {
            return null;
        }
    }
}
