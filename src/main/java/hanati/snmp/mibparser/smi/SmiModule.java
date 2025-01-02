package hanati.snmp.mibparser.smi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import hanati.snmp.mibparser.phase.xref.XRefProblemReporter;
import hanati.snmp.mibparser.util.token.IdToken;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class SmiModule {
    private static final Logger LOG = LogManager.getLogger(SmiModule.class);
    private SmiMib mib;
    private IdToken idToken;
    private List<SmiImports> imports = new ArrayList();
    private List<SmiSymbol> symbols = new ArrayList();
    Map<String, SmiType> typeMap = new LinkedHashMap();
    Map<String, SmiTextualConvention> textualConventionMap = new LinkedHashMap();
    Map<String, SmiSymbol> symbolMap = new LinkedHashMap();
    Map<String, SmiVariable> variableMap = new LinkedHashMap();
    Map<String, SmiVariable> scalarMap = new LinkedHashMap();
    Map<String, SmiTable> tableMap = new LinkedHashMap();
    Map<String, SmiRow> rowMap = new LinkedHashMap();
    Map<String, SmiVariable> columnMap = new LinkedHashMap();
    Map<String, SmiOidValue> oidValueMap = new LinkedHashMap();
    Map<String, SmiObjectType> objectTypeMap = new LinkedHashMap();
    private int v1Features = 0;
    private int v2Features = 0;
    private SmiVersion version;
    private boolean isSmiDefinitionModule;

    public SmiModule(SmiMib mib, IdToken idToken) {
        this.mib = mib;
        if (idToken == null) {
            throw new IllegalArgumentException();
        } else {
            this.setIdToken(idToken);
            this.isSmiDefinitionModule = SmiConstants.SMI_DEFINITION_MODULE_NAMES.contains(idToken.getId());
        }
    }

    public int getV1Features() {
        return this.v1Features;
    }

    public void incV1Features() {
        ++this.v1Features;
    }

    public int getV2Features() {
        return this.v2Features;
    }

    public void incV2Features() {
        ++this.v2Features;
    }

    public SmiVersion getVersion() {
        if (this.version == null && (this.v1Features != 0 || this.v2Features != 0)) {
            this.version = this.determineVersion();
        }

        return this.version;
    }

    private SmiVersion determineVersion() {
        if (this.v1Features > this.v2Features) {
            return SmiVersion.V1;
        } else if (this.v1Features < this.v2Features) {
            return SmiVersion.V2;
        } else {
            LOG.info("interesting mib with equal amount of V1 and V2 features: " + this.v1Features + ": " + this.getIdToken());
            return null;
        }
    }

    public SmiType findType(String id) {
        return (SmiType)this.typeMap.get(id);
    }

    public Collection<SmiType> getTypes() {
        return this.typeMap.values();
    }

    public SmiTextualConvention findTextualConvention(String id) {
        return (SmiTextualConvention)this.textualConventionMap.get(id);
    }

    public Collection<SmiTextualConvention> getTextualConventions() {
        return this.textualConventionMap.values();
    }

    public Collection<SmiSymbol> getSymbols() {
        return (Collection)(this.symbols != null ? this.symbols : this.symbolMap.values());
    }

    public SmiSymbol findSymbol(String id) {
        return (SmiSymbol)this.symbolMap.get(id);
    }

    public SmiVariable findVariable(String id) {
        return (SmiVariable)this.variableMap.get(id);
    }

    public Collection<SmiVariable> getVariables() {
        return this.variableMap.values();
    }

    public SmiVariable findScalar(String id) {
        return (SmiVariable)this.scalarMap.get(id);
    }

    public Collection<SmiVariable> getScalars() {
        return this.scalarMap.values();
    }

    public SmiTable findTable(String id) {
        return (SmiTable)this.tableMap.get(id);
    }

    public Collection<SmiTable> getTables() {
        return this.tableMap.values();
    }

    public SmiRow findRow(String id) {
        return (SmiRow)this.rowMap.get(id);
    }

    public Collection<SmiRow> getRows() {
        return this.rowMap.values();
    }

    public SmiVariable findColumn(String id) {
        return (SmiVariable)this.columnMap.get(id);
    }

    public Collection<SmiVariable> getColumns() {
        return this.columnMap.values();
    }

    public SmiOidValue findOidValue(String id) {
        return (SmiOidValue)this.oidValueMap.get(id);
    }

    public Collection<SmiOidValue> getOidValues() {
        return this.oidValueMap.values();
    }

    public SmiObjectType findObjectType(String id) {
        return (SmiObjectType)this.objectTypeMap.get(id);
    }

    public Collection<SmiObjectType> getObjectTypes() {
        return this.objectTypeMap.values();
    }

    public void setIdToken(IdToken id) {
        assert this.idToken == null;

        this.idToken = id;
        this.mib.addModule(id.getId(), this);
    }

    public IdToken getIdToken() {
        return this.idToken;
    }

    public String getId() {
        return this.idToken.getId();
    }

    public SmiMib getMib() {
        return this.mib;
    }

    public SmiType createType(IdToken idToken) {
        SmiType type = new SmiType(idToken, this);
        this.typeMap.put(idToken.getId(), type);
        return type;
    }

    public String getCodeId() {
        return this.getMib().getCodeNamingStrategy().getModuleId(this);
    }

    public String getFullCodeId() {
        return this.getMib().getCodeNamingStrategy().getFullModuleId(this);
    }

    public SmiTable createTable(IdToken idToken) {
        SmiTable table = new SmiTable(idToken, this);
        this.tableMap.put(idToken.getId(), table);
        return table;
    }

    public SmiRow createRow(IdToken idToken) {
        SmiRow row = new SmiRow(idToken, this);
        this.rowMap.put(idToken.getId(), row);
        return row;
    }

    public String getFullVariableOidClassId() {
        return this.getMib().getCodeNamingStrategy().getFullVariableOidClassId(this);
    }

    public String getVariableOidClassId() {
        return this.getMib().getCodeNamingStrategy().getVariableOidClassId(this);
    }

    public boolean isSmiDefinitionModule() {
        return this.isSmiDefinitionModule;
    }

    public List<SmiImports> getImports() {
        return this.imports;
    }

    public Set<SmiModule> getImportedModules() {
        Set<SmiModule> result = new HashSet();
        Iterator i$ = this.imports.iterator();

        while(i$.hasNext()) {
            SmiImports anImport = (SmiImports)i$.next();
            result.add(anImport.getModule());
        }

        return result;
    }

    public void fillTables() {
        Iterator i$ = this.symbols.iterator();

        while(i$.hasNext()) {
            SmiSymbol symbol = (SmiSymbol)i$.next();
            this.put(this.tableMap, SmiTable.class, symbol);
            this.put(this.variableMap, SmiVariable.class, symbol);
            this.put(this.typeMap, SmiType.class, symbol);
            this.put(this.textualConventionMap, SmiTextualConvention.class, symbol);
            this.put(this.rowMap, SmiRow.class, symbol);
            this.put(this.oidValueMap, SmiOidValue.class, symbol);
            this.put(this.objectTypeMap, SmiObjectType.class, symbol);
        }

    }

    public void fillExtraTables() {
        Iterator i$ = this.variableMap.values().iterator();

        while(i$.hasNext()) {
            SmiVariable variable = (SmiVariable)i$.next();
            if (variable.isColumn()) {
                this.columnMap.put(variable.getId(), variable);
            } else {
                this.scalarMap.put(variable.getId(), variable);
            }
        }

    }

    private <T extends SmiSymbol> void put(Map<String, T> map, Class<T> clazz, SmiSymbol symbol) {
        if (clazz.isInstance(symbol)) {
            map.put(symbol.getId(), clazz.cast(symbol));
        }

    }

    public void addSymbol(SmiSymbol symbol) {
        this.symbols.add(symbol);
        this.symbolMap.put(symbol.getId(), symbol);
    }

    public SmiSymbol resolveReference(IdToken idToken, XRefProblemReporter reporter) {
        SmiSymbol result = this.findSymbol(idToken.getId());
        if (result == null) {
            result = this.findImportedSymbol(idToken.getId());
        }

        if (result == null) {
            List<SmiSymbol> symbols = this.getMib().getSymbols().findAll(idToken.getId());
            if (symbols.size() == 1) {
                result = (SmiSymbol)symbols.get(0);
            } else if (symbols.size() > 0) {
                result = this.determineBestMatch(idToken, symbols);
            }
        }

        if (result == null && reporter != null) {
            reporter.reportCannotFindSymbol(idToken);
        }

        return result;
    }

    public <T extends SmiSymbol> T resolveReference(IdToken idToken, Class<T> expectedClass, XRefProblemReporter reporter) {
        SmiSymbol result = this.resolveReference(idToken, reporter);
        if (result != null) {
            if (expectedClass.isInstance(result)) {
                return (T) expectedClass.cast(result);
            }

            reporter.reportFoundSymbolButWrongType(idToken, expectedClass, result.getClass());
        }

        return null;
    }

    private SmiSymbol determineBestMatch(IdToken idToken, List<SmiSymbol> symbols) {
        SmiSymbol result = this.determineBestMatchBasedOnSnmpVersion(symbols);
        if (result != null) {
            return result;
        } else {
            result = this.determineBestMatchBasedOnOtherImports(idToken, symbols);
            if (result != null) {
                return result;
            } else {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Couldn't choose between " + symbols.size() + " choices for resolving: " + idToken + ":");
                    Iterator i$ = symbols.iterator();

                    while(i$.hasNext()) {
                        SmiSymbol symbol = (SmiSymbol)i$.next();
                        LOG.debug(symbol);
                    }
                }

                return null;
            }
        }
    }

    private SmiSymbol determineBestMatchBasedOnOtherImports(IdToken idToken, List<SmiSymbol> symbols) {
        Iterator i$ = symbols.iterator();

        while(i$.hasNext()) {
            SmiSymbol symbol = (SmiSymbol)i$.next();
            i$ = this.imports.iterator();

            while(i$.hasNext()) {
                SmiImports smiImport = (SmiImports)i$.next();
                if (smiImport.getModule() == symbol.getModule()) {
                    LOG.debug("Determined best match for " + idToken + " based on other imports from " + symbol.getModule().getId());
                    return symbol;
                }
            }
        }

        return null;
    }

    private SmiSymbol determineBestMatchBasedOnSnmpVersion(List<SmiSymbol> symbols) {
        if (symbols.size() == 2) {
            SmiSymbol s0 = (SmiSymbol)symbols.get(0);
            SmiSymbol s1 = (SmiSymbol)symbols.get(1);
            SmiVersion version0 = s0.getModule().getVersion();
            SmiVersion version1 = s1.getModule().getVersion();
            if (version0 != null && version1 != null && version0 != version1) {
                if (this.getVersion() == version0) {
                    return s0;
                }

                if (this.getVersion() == version1) {
                    return s1;
                }
            }
        }

        return null;
    }

    private SmiSymbol findImportedSymbol(String id) {
        Iterator i$ = this.imports.iterator();

        SmiSymbol symbol;
        do {
            if (!i$.hasNext()) {
                return null;
            }

            SmiImports smiImport = (SmiImports)i$.next();
            symbol = smiImport.find(id);
        } while(symbol == null);

        return symbol;
    }

    public void resolveImports(XRefProblemReporter reporter) {
        Iterator i$ = this.imports.iterator();

        while(i$.hasNext()) {
            SmiImports smiImport = (SmiImports)i$.next();
            smiImport.resolveImports(reporter);
        }

    }

    public List<String> checkImports() {
        Iterator i$ = this.imports.iterator();
        List<String> list = new ArrayList<>();

        while(i$.hasNext()) {
            SmiImports smiImport = (SmiImports)i$.next();
            boolean check = smiImport.checkImports();
            if(!check) {
                list.add(smiImport.getModuleToken().getId());
            }

        }
        return list;
    }

    public String toString() {
        return this.idToken.toString();
    }

    public Map<String, SmiOidValue> getOidValueMap() {
        return oidValueMap;
    }
}
