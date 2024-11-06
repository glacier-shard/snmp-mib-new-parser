package hanati.snmp.mibparser.util.token;

import hanati.snmp.mibparser.util.location.Location;

public interface Token {
    Location getLocation();

    Object getObject();
}
