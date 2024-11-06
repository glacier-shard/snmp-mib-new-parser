package hanati.snmp.mibparser.util.token;

import java.math.BigInteger;

import hanati.snmp.mibparser.util.location.Location;

public class BigIntegerToken extends GenericToken<BigInteger> {
    public BigIntegerToken(Location location, boolean negate, String value) {
        super(location, negate ? (new BigInteger(value)).negate() : new BigInteger(value));
    }

    public BigIntegerToken(int value) {
        super(new Location("hardcoded", 0), BigInteger.valueOf((long)value));
    }
}
