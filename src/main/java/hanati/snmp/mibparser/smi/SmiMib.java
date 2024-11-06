package hanati.snmp.mibparser.smi;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import hanati.snmp.mibparser.phase.xref.XRefProblemReporter;
import hanati.snmp.mibparser.util.location.Location;
import hanati.snmp.mibparser.util.token.IdToken;

public class SmiMib {
    private Map<String, SmiModule> moduleMap = new LinkedHashMap();
    private final SmiOptions options;
    private SmiCodeNamingStrategy codeNamingStrategy;
    private SmiOidNode rootNode;
    SmiSymbolMapImpl<SmiType> typeMap;
    SmiSymbolMapImpl<SmiTextualConvention> textualConventionMap;
    SmiSymbolMapImpl<SmiSymbol> symbolMap;
    SmiSymbolMapImpl<SmiVariable> variableMap;
    SmiSymbolMapImpl<SmiTable> tableMap;
    SmiSymbolMapImpl<SmiRow> rowMap;
    SmiSymbolMapImpl<SmiVariable> columnMap;
    SmiSymbolMapImpl<SmiVariable> scalarMap;
    SmiSymbolMapImpl<SmiOidValue> oidValueMap;
    SmiSymbolMapImpl<SmiObjectType> objectTypesMap;
    int dummyOidNodesCount;
    private SmiModule internalModule;

    public SmiMib(SmiOptions options, SmiCodeNamingStrategy codeNamingStrategy) {
        this.typeMap = new SmiSymbolMapImpl(SmiType.class, this.moduleMap);
        this.textualConventionMap = new SmiSymbolMapImpl(SmiTextualConvention.class, this.moduleMap);
        this.symbolMap = new SmiSymbolMapImpl(SmiSymbol.class, this.moduleMap);
        this.variableMap = new SmiSymbolMapImpl(SmiVariable.class, this.moduleMap);
        this.tableMap = new SmiSymbolMapImpl(SmiTable.class, this.moduleMap);
        this.rowMap = new SmiSymbolMapImpl(SmiRow.class, this.moduleMap);
        this.columnMap = new SmiSymbolMapImpl(SmiVariable.class, this.moduleMap);
        this.scalarMap = new SmiSymbolMapImpl(SmiVariable.class, this.moduleMap);
        this.oidValueMap = new SmiSymbolMapImpl(SmiOidValue.class, this.moduleMap);
        this.objectTypesMap = new SmiSymbolMapImpl(SmiObjectType.class, this.moduleMap);
        this.options = options;
        this.codeNamingStrategy = codeNamingStrategy;
        SmiModule internalMib = this.buildInternalMib();
        this.moduleMap.put(internalMib.getId(), internalMib);
    }

    private SmiModule buildInternalMib() {
        Location location = new Location("JSMI_INTERNAL_MIB");
        this.internalModule = new SmiModule(this, new IdToken(location, "JSMI_INTERNAL_MIB"));
        this.rootNode = SmiOidNode.createRootNode();
        return this.internalModule;
    }

    public SmiOidNode getRootNode() {
        return this.rootNode;
    }

    public SmiModule getInternalModule() {
        return this.internalModule;
    }

    public SmiModule findModule(String id) {
        return (SmiModule)this.moduleMap.get(id);
    }

    public Collection<SmiModule> getModules() {
        return this.moduleMap.values();
    }

    public SmiOptions getOptions() {
        return this.options;
    }

    public SmiCodeNamingStrategy getCodeNamingStrategy() {
        return this.codeNamingStrategy;
    }

    public void setCodeNamingStrategy(SmiCodeNamingStrategy codeNamingStrategy) {
        this.codeNamingStrategy = codeNamingStrategy;
    }

    public SmiModule createModule(IdToken idToken) {
        SmiModule oldModule = (SmiModule)this.moduleMap.get(idToken.getId());
        if (oldModule != null) {
//            throw new IllegalStateException("Duplicate module: " + oldModule.getIdToken() + " is already defined when adding: " + idToken);
            this.moduleMap.remove(idToken.getId());
        }
        SmiModule module = new SmiModule(this, idToken);
        this.moduleMap.put(module.getId(), module);
        return module;
    }

    public void determineInheritanceRelations() {
        Iterator i$ = this.rowMap.values().iterator();

        while(i$.hasNext()) {
            SmiRow row = (SmiRow)i$.next();
            if (row.getAugments() != null) {
                row.addParentRow(row.getAugments());
            } else {
                SmiIndex lastIndex;
                SmiRow lastIndexRow;
                if (row.getIndexes().size() == 1) {
                    lastIndex = (SmiIndex)row.getIndexes().get(0);
                    lastIndexRow = lastIndex.getColumn().getRow();
                    if (row != lastIndexRow) {
                        row.addParentRow(lastIndexRow);
                    }
                } else if (row.getIndexes().size() > 1) {
                    lastIndex = (SmiIndex)row.getIndexes().get(row.getIndexes().size() - 1);
                    lastIndexRow = lastIndex.getColumn().getRow();
                    if (row != lastIndexRow && row.hasSameIndexes(lastIndexRow)) {
                        row.addParentRow(lastIndexRow);
                    }
                }
            }
        }

    }

    void addModule(String id, SmiModule module) {
        SmiModule oldModule = (SmiModule)this.moduleMap.get(id);
        if (oldModule != null) {
            throw new IllegalArgumentException("Mib already contains a module: " + oldModule);
        } else {
            this.moduleMap.put(id, module);
        }
    }

    public void fillTables() {
        Iterator i$ = this.moduleMap.values().iterator();

        while(i$.hasNext()) {
            SmiModule module = (SmiModule)i$.next();
            module.fillTables();
            this.typeMap.putAll(module.typeMap);
            this.textualConventionMap.putAll(module.textualConventionMap);
            this.variableMap.putAll(module.variableMap);
            this.rowMap.putAll(module.rowMap);
            this.tableMap.putAll(module.tableMap);
            this.symbolMap.putAll(module.symbolMap);
            this.oidValueMap.putAll(module.oidValueMap);
            this.objectTypesMap.putAll(module.objectTypeMap);
        }

    }

    public void fillExtraTables() {
        Iterator i$ = this.moduleMap.values().iterator();

        while(i$.hasNext()) {
            SmiModule module = (SmiModule)i$.next();
            module.fillExtraTables();
            this.scalarMap.putAll(module.scalarMap);
            this.columnMap.putAll(module.columnMap);
        }

    }

    public int getDummyOidNodesCount() {
        return this.dummyOidNodesCount;
    }

    public SmiSymbolMap<SmiType> getTypes() {
        return this.typeMap;
    }

    public SmiSymbolMap<SmiTextualConvention> getTextualConventions() {
        return this.textualConventionMap;
    }

    public SmiSymbolMap<SmiSymbol> getSymbols() {
        return this.symbolMap;
    }

    public SmiSymbolMap<SmiVariable> getVariables() {
        return this.variableMap;
    }

    public SmiSymbolMap<SmiTable> getTables() {
        return this.tableMap;
    }

    public SmiSymbolMap<SmiRow> getRows() {
        return this.rowMap;
    }

    public SmiSymbolMap<SmiVariable> getColumns() {
        return this.columnMap;
    }

    public SmiSymbolMap<SmiVariable> getScalars() {
        return this.scalarMap;
    }

    public SmiSymbolMap<SmiOidValue> getOidValues() {
        return this.oidValueMap;
    }

    public SmiSymbolMap<SmiObjectType> getObjectTypes() {
        return this.objectTypesMap;
    }

    public SmiOidNode findByOid(int... oid) {
        SmiOidNode child = null;
        SmiOidNode parent = this.getRootNode();
        int[] arr$ = oid;
        int len$ = oid.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            int oidPart = arr$[i$];
            child = parent.findChild(oidPart);
            if (child == null) {
                return null;
            }

            parent = child;
        }

        return child;
    }

    public SmiOidNode findByOidPrefix(int... oid) {
        SmiOidNode parent = this.getRootNode();
        int[] arr$ = oid;
        int len$ = oid.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            int subId = arr$[i$];
            SmiOidNode result = parent.findChild(subId);
            if (result == null) {
                return parent;
            }

            parent = result;
        }

        return null;
    }

    public Set<SmiModule> findModules(SmiVersion version) {
        Set<SmiModule> result = new HashSet();
        Iterator i$ = this.moduleMap.values().iterator();

        while(true) {
            SmiModule module;
            do {
                if (!i$.hasNext()) {
                    return result;
                }

                module = (SmiModule)i$.next();
            } while(module.getVersion() != null && module.getVersion() != version);

            result.add(module);
        }
    }

    public void defineMissingStandardOids() {
        Location location = this.internalModule.getIdToken().getLocation();
        SmiOidNode isoNode;
        SmiOidValue iso;
        if (this.symbolMap.findAll("itu").isEmpty()) {
            isoNode = new SmiOidNode(this.rootNode, 0);
            iso = new SmiOidValue(new IdToken(location, "itu"), this.internalModule, isoNode);
            this.internalModule.addSymbol(iso);
            this.internalModule.symbolMap.put(iso.getId(), iso);
            this.symbolMap.put(iso.getId(), iso);
            this.oidValueMap.put(iso.getId(), iso);
        }

        if (this.symbolMap.findAll("iso").isEmpty()) {
            isoNode = new SmiOidNode(this.rootNode, 1);
            iso = new SmiOidValue(new IdToken(location, "iso"), this.internalModule, isoNode);
            this.internalModule.addSymbol(iso);
            this.internalModule.symbolMap.put(iso.getId(), iso);
            this.symbolMap.put(iso.getId(), iso);
            this.oidValueMap.put(iso.getId(), iso);
        }

    }

    public SmiModule resolveModule(IdToken moduleToken, XRefProblemReporter reporter) {
        SmiModule result = (SmiModule)this.moduleMap.get(moduleToken.getId());
        if (result == null) {
            reporter.reportCannotFindModule(moduleToken);
        }

        return result;
    }
}
