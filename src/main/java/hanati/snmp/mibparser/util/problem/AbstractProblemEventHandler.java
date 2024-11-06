package hanati.snmp.mibparser.util.problem;

import hanati.snmp.mibparser.util.problem.annotations.ProblemSeverity;

public abstract class AbstractProblemEventHandler implements ProblemEventHandler {
    private int[] severityCounters = new int[ProblemSeverity.values().length];
    private int totalCounter;

    public AbstractProblemEventHandler() {
    }

    public void handle(ProblemEvent event) {
        int var10002 = this.severityCounters[event.getSeverity().ordinal()]++;
        ++this.totalCounter;
    }

    public boolean isOk() {
        for(int i = 0; i < this.severityCounters.length; ++i) {
            if (i >= ProblemSeverity.ERROR.ordinal()) {
                int severityCounter = this.severityCounters[i];
                if (severityCounter > 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isNotOk() {
        return !this.isOk();
    }

    public int getSeverityCount(ProblemSeverity severity) {
        return this.severityCounters[severity.ordinal()];
    }

    public int getTotalCount() {
        return this.totalCounter;
    }
}
