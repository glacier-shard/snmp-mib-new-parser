package hanati.snmp.mibparser.util.problem;

import java.io.PrintStream;

import hanati.snmp.mibparser.util.location.Location;

public class DefaultProblemEventHandler extends AbstractProblemEventHandler {
    private PrintStream out;
    private PrintStream err;

    public DefaultProblemEventHandler() {
        this.out = System.out;
        this.err = System.err;
    }

    public DefaultProblemEventHandler(PrintStream out, PrintStream err) {
        this.out = out;
        this.err = err;
    }

    public void handle(ProblemEvent event) {
        super.handle(event);
        switch (event.getSeverity()) {
            case ERROR:
                this.error(event.getLocation(), event.getLocalizedMessage());
                break;
            case FATAL:
                this.error(event.getLocation(), event.getLocalizedMessage());
                break;
            case WARNING:
                this.warning(event.getLocation(), event.getLocalizedMessage());
        }

    }

    private void error(Location location, String localizedMessage) {
        this.print(this.err, "Error", location, localizedMessage);
    }

    private void warning(Location location, String localizedMessage) {
        this.print(this.out, "Warning", location, localizedMessage);
    }

    private void print(PrintStream stream, String sev, Location location, String localizedMessage) {
        String loc = location != null ? location.toString() : null;
        stream.println(sev + ": file://" + loc + " :" + localizedMessage);
    }
}
