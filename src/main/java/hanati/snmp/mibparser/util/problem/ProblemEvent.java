package hanati.snmp.mibparser.util.problem;

import java.util.Formatter;

import hanati.snmp.mibparser.util.location.Location;
import hanati.snmp.mibparser.util.problem.annotations.ProblemSeverity;

public class ProblemEvent {
    private Location location;
    private ProblemSeverity severity;
    private String messageKey;
    private String defaultMessage;
    private Object[] arguments;

    public ProblemEvent(Location location, ProblemSeverity severity, String messageKey, String defaultMessage, Object[] arguments) {
        this.location = location;
        this.severity = severity;
        this.messageKey = messageKey;
        this.defaultMessage = defaultMessage;
        this.arguments = arguments;
    }

    public Location getLocation() {
        return this.location;
    }

    public ProblemSeverity getSeverity() {
        return this.severity;
    }

    public String getMessageKey() {
        return this.messageKey;
    }

    public Object[] getArguments() {
        return this.arguments;
    }

    public String getLocalizedMessage() {
        Formatter f = new Formatter();
        f.format(this.defaultMessage, this.arguments);
        return f.toString();
    }
}
