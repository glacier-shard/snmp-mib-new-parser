package hanati.snmp.mibparser.util.token;

import hanati.snmp.mibparser.util.location.Location;

public abstract class AbstractToken implements Token {
    private Location location;

    protected AbstractToken(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        if (this.getLocation() != null) {
            result.append(this.getLocation().toString());
        } else {
            result.append("<hardcoded>");
        }

        result.append(':');
        result.append(this.getObject().toString());
        return result.toString();
    }
}
