package hanati.snmp.mibparser.util.token;

import hanati.snmp.mibparser.util.location.Location;

public class GenericToken<Value> extends AbstractToken {
    protected Value value;

    public GenericToken(Location location, Value value) {
        super(location);
        this.value = value;
    }

    public Value getValue() {
        return this.value;
    }

    public Value getObject() {
        return this.value;
    }
}
