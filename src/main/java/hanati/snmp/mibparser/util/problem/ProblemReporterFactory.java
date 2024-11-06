package hanati.snmp.mibparser.util.problem;

public interface ProblemReporterFactory {
    <T> T create(Class<T> var1);

    ProblemEventHandler getProblemEventHandler();
}
