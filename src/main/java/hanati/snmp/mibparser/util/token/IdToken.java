package hanati.snmp.mibparser.util.token;

import hanati.snmp.mibparser.util.location.Location;

public class IdToken extends StringToken {
    public IdToken(Location location, String value) {
        super(location, value);
    }

    public String getId() {
        return (String)this.getValue();
    }
}
