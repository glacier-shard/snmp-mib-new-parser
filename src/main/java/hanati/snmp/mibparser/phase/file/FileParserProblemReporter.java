package hanati.snmp.mibparser.phase.file;

import java.io.File;
import hanati.snmp.mibparser.util.location.Location;
import hanati.snmp.mibparser.util.problem.annotations.ProblemMethod;

public interface FileParserProblemReporter {
    @ProblemMethod(
            message = "Lex error at: %s : %s"
    )
    void reportTokenStreamError(String var1, String var2);

    @ProblemMethod(
            message = "Parse error: %s"
    )
    void reportParseError(Location var1, String var2);

    @ProblemMethod(
            message = "File not found: %s"
    )
    void reportFileNotFound(File var1);

    @ProblemMethod(
            message = "IO error: %s"
    )
    void reportIoException(Location var1, String var2);
}
