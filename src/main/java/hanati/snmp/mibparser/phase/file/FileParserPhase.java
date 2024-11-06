package hanati.snmp.mibparser.phase.file;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import hanati.snmp.mibparser.exception.SmiException;
import hanati.snmp.mibparser.phase.file.antlr.SMILexer;
import hanati.snmp.mibparser.phase.file.antlr.SMIParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import hanati.snmp.mibparser.phase.Phase;
import hanati.snmp.mibparser.smi.SmiMib;
import hanati.snmp.mibparser.smi.SmiModule;
import hanati.snmp.mibparser.smi.SmiVersion;
import hanati.snmp.mibparser.util.location.Location;
import hanati.snmp.mibparser.util.problem.DefaultProblemReporterFactory;
import hanati.snmp.mibparser.util.problem.ProblemEventHandler;
import hanati.snmp.mibparser.util.problem.ProblemReporterFactory;

public class FileParserPhase implements Phase {
    private static final Logger m_log = LogManager.getLogger(FileParserPhase.class);
    private FileParserProblemReporter m_reporter;
    private List<URL> m_inputUrls;

    public FileParserPhase(FileParserProblemReporter reporter) {
        this.m_reporter = reporter;
    }

    public FileParserPhase(ProblemReporterFactory reporterFactory) {
        this.m_reporter = (FileParserProblemReporter)reporterFactory.create(FileParserProblemReporter.class);
    }

    public FileParserPhase(ProblemEventHandler eventHandler) {
        DefaultProblemReporterFactory reporterFactory = new DefaultProblemReporterFactory(eventHandler);
        this.m_reporter = (FileParserProblemReporter)reporterFactory.create(FileParserProblemReporter.class);
    }

    public FileParserProblemReporter getFileParserProblemReporter() {
        return this.m_reporter;
    }

    public List<URL> getInputUrls() {
        return this.m_inputUrls;
    }

    public void setInputUrls(List<URL> inputUrls) {
        this.m_inputUrls = inputUrls;
    }

    public SmiMib process(SmiMib mib) throws SmiException {
        Iterator i$ = this.getInputUrls().iterator();

        while(i$.hasNext()) {
            URL url = (URL)i$.next();
            this.parse(mib, url, this.determineResourceLocation(url));
        }

        if (m_log.isDebugEnabled()) {
            this.logParseResults(mib);
        }

        return mib;
    }

    private String determineResourceLocation(URL url) {
        return "file".equals(url.getProtocol()) ? "file://" + url.getPath() : url.toString();
    }

    public void parse(SmiMib mib, URL url, String resourceLocation) {
        InputStream is = null;

        try {
            m_log.debug("Parsing :" + url);
            is = url.openStream();
            is = new BufferedInputStream(is);
            SMILexer lexer = new SMILexer(is);
            SMIParser parser = new SMIParser(lexer);
            parser.init(mib, resourceLocation);

            for(SmiModule module = parser.module_definition(); module != null; module = parser.module_definition()) {
            }
        } catch (TokenStreamException var20) {
            m_log.debug(var20.getMessage(), var20);
            this.m_reporter.reportTokenStreamError(resourceLocation, var20.getMessage());
        } catch (RecognitionException var21) {
            m_log.debug(var21.getMessage(), var21);
            this.m_reporter.reportParseError(new Location(resourceLocation, var21.getLine(), var21.getColumn()), var21.getMessage());
        } catch (IOException var22) {
            m_log.debug(var22.getMessage(), var22);
            this.m_reporter.reportIoException(new Location(resourceLocation, 0, 0), var22.getMessage());
        } finally {
            m_log.debug("Finished parsing :" + resourceLocation);
            if (is != null) {
                try {
                    is.close();
                } catch (IOException var19) {
                    m_log.warn(var19.getMessage(), var19);
                }
            }

        }

    }

    private void logParseResults(SmiMib mib) {
        Set<SmiModule> v1modules = mib.findModules(SmiVersion.V1);
        Set<SmiModule> v2modules = mib.findModules(SmiVersion.V2);
        m_log.debug("#SMIv1 modules=" + v1modules.size() + " #SMIv2 modules=" + v2modules.size());
        if (v1modules.size() > v2modules.size()) {
            m_log.debug("V2 modules:");
            this.logMibs(v2modules);
        } else if (v1modules.size() < v2modules.size()) {
            m_log.debug("V1 modules:");
            this.logMibs(v1modules);
        }

    }

    private void logMibs(Set<SmiModule> modules) {
        Iterator i$ = modules.iterator();

        while(i$.hasNext()) {
            SmiModule module = (SmiModule)i$.next();
            m_log.debug(module + ": #V1 features=" + module.getV1Features() + " #V2 features=" + module.getV2Features());
        }

    }
}
