package hanati.snmp.mibparser.smi;

import hanati.snmp.mibparser.util.location.Location;
import hanati.snmp.mibparser.util.token.Token;

public class SmiSelect {
    private Token first;
    private Token second;
    private Token third;
    private Token fourth;
    private int count;

    public SmiSelect(Token first) {
        this.first = first;
        this.second = null;
        this.third = null;
        this.fourth = null;
        this.count = 1;
    }

    public SmiSelect(Token first, Token second) {
        this.first = first;
        this.second = second;
        this.third = null;
        this.fourth = null;
        this.count = 2;
    }

    public SmiSelect(Token first, Token second, Token third) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = null;
        this.count = 3;
    }

    public SmiSelect(Token first, Token second, Token third, Token fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.count = 4;
    }

    public Token getFirst() {
        return this.first;
    }

    public Token getSecond() {
        return this.second;
    }

    public Token getThird() {
        return this.third;
    }

    public Token getFourth() {
        return this.fourth;
    }

    public int getCount() { return this.count; }

    public Location getLocation() {
        return this.first.getLocation();
    }

    public String toString() {
        StringBuilder result = new StringBuilder("(");
        switch(this.count) {
            case 1:
                result.append(this.first);
                result.append(")");
            case 2:
                result.append(this.first);
                result.append(" | ");
                result.append(this.second);
                result.append(")");
            case 3:
                result.append(this.first);
                result.append(" | ");
                result.append(this.second);
                result.append(" | ");
                result.append(this.third);
                result.append(")");
            case 4:
                result.append(this.first);
                result.append(" | ");
                result.append(this.second);
                result.append(" | ");
                result.append(this.third);
                result.append(" | ");
                result.append(this.fourth);
                result.append(")");
        }
        return result.toString();
    }
}
