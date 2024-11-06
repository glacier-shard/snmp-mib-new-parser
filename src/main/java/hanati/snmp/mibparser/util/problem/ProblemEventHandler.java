package hanati.snmp.mibparser.util.problem;

import hanati.snmp.mibparser.util.problem.annotations.ProblemSeverity;

public interface ProblemEventHandler {
    void handle(ProblemEvent var1);

    boolean isOk();

    boolean isNotOk();

    int getSeverityCount(ProblemSeverity var1);

    int getTotalCount();
}
