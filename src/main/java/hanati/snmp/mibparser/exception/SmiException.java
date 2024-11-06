package hanati.snmp.mibparser.exception;

public class SmiException extends RuntimeException {
    public SmiException() {
    }

    public SmiException(String message) {
        super(message);
    }

    public SmiException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmiException(Throwable cause) {
        super(cause);
    }
}
