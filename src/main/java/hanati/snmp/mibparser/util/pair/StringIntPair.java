package hanati.snmp.mibparser.util.pair;

public class StringIntPair extends Pair<String, Integer> {
    public StringIntPair(String first, Integer second) {
        super(first, second);
    }

    public StringIntPair(String first) {
        super(first, (Integer) null);
    }

    public StringIntPair(Integer second) {
        super((String) null, second);
    }

    public String getString() {
        return (String)this.getFirst();
    }

    public void setString(String str) {
        this.setFirst(str);
    }

    public Integer getInt() {
        return (Integer)this.getSecond();
    }

    public void setInt(Integer i) {
        this.setSecond(i);
    }
}
