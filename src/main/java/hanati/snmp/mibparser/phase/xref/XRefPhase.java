package hanati.snmp.mibparser.phase.xref;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import hanati.snmp.mibparser.exception.SmiException;
import hanati.snmp.mibparser.phase.Phase;
import hanati.snmp.mibparser.smi.SmiDefaultValue;
import hanati.snmp.mibparser.smi.SmiMib;
import hanati.snmp.mibparser.smi.SmiModule;
import hanati.snmp.mibparser.smi.SmiOidNode;
import hanati.snmp.mibparser.smi.SmiOidValue;
import hanati.snmp.mibparser.smi.SmiSymbol;
import hanati.snmp.mibparser.smi.SmiVariable;
import hanati.snmp.mibparser.util.location.Location;
import hanati.snmp.mibparser.util.problem.DefaultProblemReporterFactory;
import hanati.snmp.mibparser.util.problem.ProblemEventHandler;
import hanati.snmp.mibparser.util.problem.ProblemReporterFactory;
import hanati.snmp.mibparser.util.token.IdToken;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class XRefPhase implements Phase {
    private static final Logger m_log = LogManager.getLogger(XRefPhase.class);
    private XRefProblemReporter m_reporter;
    private Map<String, SymbolDefiner> m_symbolDefinerMap = new LinkedHashMap();

    public XRefPhase(XRefProblemReporter reporter) {
        this.m_reporter = reporter;
    }

    public XRefPhase(ProblemReporterFactory reporterFactory) {
        this.m_reporter = (XRefProblemReporter)reporterFactory.create(XRefProblemReporter.class);
    }

    public XRefPhase(ProblemEventHandler eventHandler) {
        DefaultProblemReporterFactory reporterFactory = new DefaultProblemReporterFactory(eventHandler);
        this.m_reporter = (XRefProblemReporter)reporterFactory.create(XRefProblemReporter.class);
    }

    public Object getOptions() {
        return null;
    }

    public Map<String, SymbolDefiner> getSymbolDefinerMap() {
        return this.m_symbolDefinerMap;
    }

    public void setSymbolDefinerMap(Map<String, SymbolDefiner> symbolDefinerMap) {
        this.m_symbolDefinerMap = symbolDefinerMap;
    }

    public SmiMib process(SmiMib mib) throws SmiException {
        this.defineMissingSymbols(mib);
        mib.fillTables();
        mib.defineMissingStandardOids();
        Iterator i$ = mib.getModules().iterator();

        while(i$.hasNext()) {
            SmiModule module = (SmiModule)i$.next();
            module.resolveImports(this.m_reporter);
        }

        Collection<SmiModule> modules = mib.getModules();
        this.resolveReferences(modules);
        this.resolveOids(modules);
        mib.fillExtraTables();
        this.resolveDefaultValues(mib);
        return mib;
    }

    protected void defineMissingSymbols(SmiMib mib) {
        Map.Entry entry;
        SmiModule module;
        for(Iterator i$ = this.m_symbolDefinerMap.entrySet().iterator(); i$.hasNext(); ((SymbolDefiner)entry.getValue()).defineSymbols(module)) {
            entry = (Map.Entry)i$.next();
            module = mib.findModule((String)entry.getKey());
            if (module == null) {
                module = mib.createModule(new IdToken((Location)null, (String)entry.getKey()));
            }
        }

    }

    protected void resolveReferences(Collection<SmiModule> modules) {
        Iterator i$ = modules.iterator();

        while(i$.hasNext()) {
            SmiModule module = (SmiModule)i$.next();
            Iterator ii = module.getSymbols().iterator();

            while(ii.hasNext()) {
                SmiSymbol symbol = (SmiSymbol)ii.next();
                symbol.resolveReferences(this.m_reporter);
            }
        }

    }

    protected void resolveOids(Collection<SmiModule> modules) {
        Iterator i$ = modules.iterator();

        SmiModule module;
        Iterator ii;
        SmiOidValue oidValue;
        while(i$.hasNext()) {
            module = (SmiModule)i$.next();
            m_log.debug("Resolving oids in module: " + module.getId() + " hash=" + module.getId().hashCode());

            ii = module.getOidValues().iterator();

            while(ii.hasNext()) {
                oidValue = (SmiOidValue)ii.next();
                oidValue.resolveOid(this.m_reporter);
            }
        }

        i$ = modules.iterator();

        while(i$.hasNext()) {
            module = (SmiModule)i$.next();
            ii = module.getOidValues().iterator();

            while(ii.hasNext()) {
                oidValue = (SmiOidValue)ii.next();
                SmiOidNode node = oidValue.getNode();
                if (node != null) {
                    node.determineFullOid();
                }
            }
        }

    }

    protected void resolveDefaultValues(SmiMib mib) {
        Iterator i$ = mib.getVariables().iterator();

        while(i$.hasNext()) {
            SmiVariable variable = (SmiVariable)i$.next();
            SmiDefaultValue defaultValue = variable.getDefaultValue();
            if (defaultValue != null) {
                defaultValue.resolveReferences(this.m_reporter);
            }
        }

    }
}

