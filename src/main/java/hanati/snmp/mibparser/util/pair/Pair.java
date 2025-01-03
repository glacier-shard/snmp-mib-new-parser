package hanati.snmp.mibparser.util.pair;

public class Pair<T1, T2> {
    private T1 first;
    private T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return this.first;
    }

    public void setFirst(T1 first) {
        this.first = first;
    }

    public T2 getSecond() {
        return this.second;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Pair pair = (Pair)o;
            if (this.first != null) {
                if (!this.first.equals(pair.first)) {
                    return false;
                }
            } else if (pair.first != null) {
                return false;
            }

            if (this.second != null) {
                if (this.second.equals(pair.second)) {
                    return true;
                }
            } else if (pair.second == null) {
                return true;
            }

            return false;
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.first != null ? this.first.hashCode() : 0;
        result = 31 * result + (this.second != null ? this.second.hashCode() : 0);
        return result;
    }
}
