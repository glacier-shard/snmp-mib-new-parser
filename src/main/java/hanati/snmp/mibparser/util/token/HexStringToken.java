package hanati.snmp.mibparser.util.token;

import java.math.BigInteger;

import hanati.snmp.mibparser.util.location.Location;

public class HexStringToken extends StringToken {
    private final char radixChar;

    public HexStringToken(Location location, String value) {
        super(location, value.substring(1, value.length() - 2));
        this.radixChar = value.charAt(value.length() - 1);
    }

    public BigInteger getIntegerValue() {
        return new BigInteger((String)this.getValue(), 16);
    }

    public String toString() {
        return "'" + (String)this.getValue() + "'" + this.radixChar;
    }

    public char getRadixChar() {
        return this.radixChar;
    }
}
