package hanati.snmp.mibparser.smi;

import java.math.BigInteger;

import hanati.snmp.mibparser.util.location.Location;
import hanati.snmp.mibparser.util.token.BigIntegerToken;
import hanati.snmp.mibparser.util.token.HexStringToken;
import hanati.snmp.mibparser.util.token.IdToken;

public class SmiNamedNumber {
    private SmiType type;
    private IdToken idToken;
    private BigIntegerToken valueToken;
    private HexStringToken hValueToken;

    public SmiNamedNumber(IdToken id, BigIntegerToken value) {
        this.idToken = id;
        this.valueToken = value;
    }

    public SmiNamedNumber(IdToken id, HexStringToken value) {
        this.idToken = id;
        this.hValueToken = value;
    }

    public Location getLocation() {
        return this.idToken.getLocation();
    }

    public String getId() {
        return this.idToken.getId();
    }

    public BigInteger getValue() {
        return (BigInteger)this.valueToken.getValue();
    }

    public String getCodeId() {
        return this.type.getModule().getMib().getCodeNamingStrategy().getEnumValueId(this);
    }

    public SmiType getType() {
        return this.type;
    }

    public void setType(SmiType type) {
        this.type = type;
    }

    public IdToken getIdToken() {
        return this.idToken;
    }

    public BigIntegerToken getValueToken() {
        return this.valueToken;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.idToken != null) {
            sb.append(this.idToken.getId());
        }

        if (this.valueToken != null) {
            sb.append('(').append(this.valueToken.getValue()).append(')');
        }

        return sb.toString();
    }
}
