package hanati.snmp.mibparser.util.token;

import hanati.snmp.mibparser.util.location.Location;

public class QuotedStringToken extends StringToken {
    private char quoteChar;

    public QuotedStringToken(Location location, String value, char quoteChar) {
        super(location, strip(value, quoteChar));
        this.quoteChar = quoteChar;
    }

    private static String strip(String str, char quoteChar) {
        if (str.charAt(0) == quoteChar && str.charAt(str.length() - 1) == quoteChar) {
            return str.substring(1, str.length() - 1);
        } else {
            throw new IllegalArgumentException(str + " is not a valid " + quoteChar + "-quoted string ");
        }
    }

    public char getQuoteChar() {
        return this.quoteChar;
    }

    public String toString() {
        return this.quoteChar + (String)this.value + this.quoteChar;
    }
}
