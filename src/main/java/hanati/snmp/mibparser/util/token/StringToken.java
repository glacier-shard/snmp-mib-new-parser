package hanati.snmp.mibparser.util.token;

import hanati.snmp.mibparser.util.location.Location;

public class StringToken extends GenericToken<String> {
    public StringToken(Location location, String value) {
        super(location, value);
    }
}
