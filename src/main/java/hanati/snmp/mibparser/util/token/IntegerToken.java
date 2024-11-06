package hanati.snmp.mibparser.util.token;

import hanati.snmp.mibparser.util.location.Location;

public class IntegerToken extends AbstractToken {
    private int value;

    public IntegerToken(Location location, int value) {
        super(location);
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public Integer getObject() {
        return this.value;
    }

    public String toString() {
        return this.getLocation().toString() + ':' + this.value;
    }
}
