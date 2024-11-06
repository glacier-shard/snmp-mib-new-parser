package hanati.snmp.mibparser.smi;

import java.math.BigInteger;

import hanati.snmp.mibparser.util.location.Location;
import hanati.snmp.mibparser.util.token.BigIntegerToken;
import hanati.snmp.mibparser.util.token.BinaryStringToken;
import hanati.snmp.mibparser.util.token.HexStringToken;
import hanati.snmp.mibparser.util.token.Token;

public class SmiRange {
    private Token beginToken;
    private Token endToken;

    public SmiRange(Token beginToken, Token endToken) {
        this.beginToken = beginToken;
        this.endToken = endToken;
    }

    public SmiRange(Token singleToken) {
        this.beginToken = singleToken;
        this.endToken = singleToken;
    }

    public Token getBeginToken() {
        return this.beginToken;
    }

    public Token getEndToken() {
        return this.endToken;
    }

    public boolean isSingle() {
        return this.beginToken == this.endToken;
    }

    public Location getLocation() {
        return this.beginToken.getLocation();
    }

    public BigInteger getMinValue() {
        return getValue(this.beginToken);
    }

    public BigInteger getMaxValue() {
        return getValue(this.endToken);
    }

    private static BigInteger getValue(Token token) {
        if (token instanceof BigIntegerToken) {
            return (BigInteger)((BigIntegerToken)token).getValue();
        } else if (token instanceof HexStringToken) {
            return ((HexStringToken)token).getIntegerValue();
        } else {
            return token instanceof BinaryStringToken ? ((BinaryStringToken)token).getIntegerValue() : null;
        }
    }

    public String toString() {
        if (this.beginToken == this.endToken) {
            return this.beginToken.getObject().toString();
        } else {
            StringBuilder result = new StringBuilder("(");
            result.append(this.beginToken.getObject());
            result.append("..");
            result.append(this.endToken);
            result.append(")");
            return result.toString();
        }
    }
}
