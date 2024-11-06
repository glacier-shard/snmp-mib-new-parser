package hanati.snmp.mibparser.parser;

import hanati.snmp.mibparser.exception.SmiException;
import hanati.snmp.mibparser.phase.Phase;
import hanati.snmp.mibparser.phase.check.ErrorCheckPhase;
import hanati.snmp.mibparser.phase.file.FileParserPhase;
import hanati.snmp.mibparser.phase.file.FileParserProblemReporter;
import hanati.snmp.mibparser.phase.xref.XRefPhase;
import hanati.snmp.mibparser.phase.xref.XRefProblemReporter;
import hanati.snmp.mibparser.smi.SmiJavaCodeNamingStrategy;
import hanati.snmp.mibparser.smi.SmiMib;
import hanati.snmp.mibparser.smi.SmiOptions;
import hanati.snmp.mibparser.util.problem.DefaultProblemEventHandler;
import hanati.snmp.mibparser.util.problem.DefaultProblemReporterFactory;
import hanati.snmp.mibparser.util.problem.ProblemEventHandler;
import hanati.snmp.mibparser.util.problem.ProblemReporterFactory;

public class SmiDefaultParser implements SmiParser {
    protected boolean m_failOnError;
    protected ProblemReporterFactory m_problemReporterFactory;
    protected FileParserPhase m_fileParserPhase;
    protected XRefPhase m_xRefPhase;
    protected ErrorCheckPhase m_errorCheckPhase;
    protected SmiOptions options;

    public SmiDefaultParser() {
        this((ProblemEventHandler)(new DefaultProblemEventHandler()));
    }

    public SmiDefaultParser(ProblemEventHandler problemEventHandler) {
        this((ProblemReporterFactory)(new DefaultProblemReporterFactory(problemEventHandler)));
    }

    public SmiDefaultParser(ProblemReporterFactory problemReporterFactory) {
        this.m_failOnError = false;
        this.options = new SmiOptions();
        this.m_problemReporterFactory = problemReporterFactory;
    }

    public SmiMib parse() throws SmiException {
        SmiMib mib = new SmiMib(this.options, new SmiJavaCodeNamingStrategy("hanati.snmp.mibparser.mib"));
        Phase[] phases = new Phase[]{this.getFileParserPhase(), this.getXRefPhase(), this.getErrorCheckPhase()};
        Phase[] arr$ = phases;
        int len$ = phases.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Phase phase = arr$[i$];
            phase.process(mib);
        }

        if (this.m_failOnError && this.getProblemReporterFactory().getProblemEventHandler().isNotOk()) {
            throw new SmiException();
        } else {
            return mib;
        }
    }

    public SmiOptions getOptions() {
        return this.options;
    }

    public void setOptions(SmiOptions options) {
        this.options = options;
    }

    protected FileParserPhase createFileParserPhase() {
        return new FileParserPhase((FileParserProblemReporter)this.getProblemReporterFactory().create(FileParserProblemReporter.class));
    }

    protected XRefPhase createXRefPhase() {
        return new XRefPhase((XRefProblemReporter)this.getProblemReporterFactory().create(XRefProblemReporter.class));
    }

    private ErrorCheckPhase createErrorCheckPhase() {
        return new ErrorCheckPhase();
    }

    public ProblemEventHandler getProblemEventHandler() {
        return this.m_problemReporterFactory.getProblemEventHandler();
    }

    public ProblemReporterFactory getProblemReporterFactory() {
        return this.m_problemReporterFactory;
    }

    public void setProblemReporterFactory(ProblemReporterFactory problemReporterFactory) {
        this.m_problemReporterFactory = problemReporterFactory;
    }

    public FileParserPhase getFileParserPhase() {
        if (this.m_fileParserPhase == null) {
            this.m_fileParserPhase = this.createFileParserPhase();
        }

        return this.m_fileParserPhase;
    }

    public void setFileParserPhase(FileParserPhase fileParserPhase) {
        this.m_fileParserPhase = fileParserPhase;
    }

    public XRefPhase getXRefPhase() {
        if (this.m_xRefPhase == null) {
            this.m_xRefPhase = this.createXRefPhase();
        }

        return this.m_xRefPhase;
    }

    public void setXRefPhase(XRefPhase xrefPhase) {
        this.m_xRefPhase = xrefPhase;
    }

    public ErrorCheckPhase getErrorCheckPhase() {
        if (this.m_errorCheckPhase == null) {
            this.m_errorCheckPhase = this.createErrorCheckPhase();
        }

        return this.m_errorCheckPhase;
    }

    public void setErrorCheckPhase(ErrorCheckPhase errorCheckPhase) {
        this.m_errorCheckPhase = errorCheckPhase;
    }

    public boolean isFailOnError() {
        return this.m_failOnError;
    }

    public void setFailOnError(boolean failOnError) {
        this.m_failOnError = failOnError;
    }
}
