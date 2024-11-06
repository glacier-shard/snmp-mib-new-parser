package hanati.snmp.mibparser.util.location;

public class Location {
    public static final char SEPARATOR = ':';
    public static final int INVALID_LINE = -1;
    public static final int INVALID_COLUMN = -1;
    private String source;
    private int line = -1;
    private int column = -1;

    public Location(String file, int line, int column) {
        this.source = file;
        this.line = line;
        this.column = column;
    }

    public Location(String source, int line) {
        this.source = source;
        this.line = line;
    }

    public Location(String source) {
        this.source = source;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getLine() {
        return this.line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        if (this.source != null) {
            result.append(this.source);
        }

        result.append(':');
        if (this.line > -1) {
            result.append(this.line);
        }

        result.append(':');
        if (this.column > -1) {
            result.append(this.column);
        }

        return result.toString();
    }
}
