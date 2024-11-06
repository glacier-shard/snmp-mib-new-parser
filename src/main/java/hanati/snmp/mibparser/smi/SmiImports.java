package hanati.snmp.mibparser.smi;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import hanati.snmp.mibparser.phase.xref.XRefProblemReporter;
import hanati.snmp.mibparser.util.location.Location;
import hanati.snmp.mibparser.util.pair.Pair;
import hanati.snmp.mibparser.util.token.IdToken;

public class SmiImports {
    static final String[] V1_V2_MAP = new String[]{"RFC1155-SMI", "internet", "SNMPv2-SMI", "internet", "RFC1155-SMI", "directory", "SNMPv2-SMI", "directory", "RFC1155-SMI", "mgmt", "SNMPv2-SMI", "mgmt", "RFC1155-SMI", "experimental", "SNMPv2-SMI", "experimental", "RFC1155-SMI", "private", "SNMPv2-SMI", "private", "RFC1155-SMI", "enterprises", "SNMPv2-SMI", "enterprises", "RFC1155-SMI", "IpAddress", "SNMPv2-SMI", "IpAddress", "RFC1155-SMI", "Counter", "SNMPv2-SMI", "Counter32", "RFC1155-SMI", "Gauge", "SNMPv2-SMI", "Gauge32", "RFC1155-SMI", "TimeTicks", "SNMPv2-SMI", "TimeTicks", "RFC1155-SMI", "Opaque", "SNMPv2-SMI", "Opaque", "RFC1065-SMI", "internet", "SNMPv2-SMI", "internet", "RFC1065-SMI", "directory", "SNMPv2-SMI", "directory", "RFC1065-SMI", "mgmt", "SNMPv2-SMI", "mgmt", "RFC1065-SMI", "experimental", "SNMPv2-SMI", "experimental", "RFC1065-SMI", "private", "SNMPv2-SMI", "private", "RFC1065-SMI", "enterprises", "SNMPv2-SMI", "enterprises", "RFC1065-SMI", "IpAddress", "SNMPv2-SMI", "IpAddress", "RFC1065-SMI", "Counter", "SNMPv2-SMI", "Counter32", "RFC1065-SMI", "Gauge", "SNMPv2-SMI", "Gauge32", "RFC1065-SMI", "TimeTicks", "SNMPv2-SMI", "TimeTicks", "RFC1065-SMI", "Opaque", "SNMPv2-SMI", "Opaque", "RFC1213-MIB", "mib-2", "SNMPv2-SMI", "mib-2", "RFC1213-MIB", "DisplayString", "SNMPv2-TC", "DisplayString", "RFC-1212", "OBJECT-TYPE", "SNMPv2-SMI", "OBJECT-TYPE"};
    private final SmiModule importerModule;
    private final IdToken moduleToken;
    private final List<IdToken> symbolTokens;
    private SmiModule module;
    private LinkedHashMap<String, SmiSymbol> symbolMap = new LinkedHashMap();

    public SmiImports(SmiModule importerModule, IdToken moduleToken, List<IdToken> symbolTokens) {
        assert importerModule != null;

        assert moduleToken != null;

        assert symbolTokens != null;

        this.importerModule = importerModule;
        this.moduleToken = moduleToken;
        this.symbolTokens = Collections.unmodifiableList(symbolTokens);
    }

    public SmiModule getModule() {
        return this.module;
    }

    public Collection<SmiSymbol> getSymbols() {
        return this.symbolMap.values();
    }

    public IdToken getModuleToken() {
        return this.moduleToken;
    }

    public List<IdToken> getSymbolTokens() {
        return this.symbolTokens;
    }

    public Location getLocation() {
        return this.moduleToken.getLocation();
    }

    public SmiSymbol find(String id) {
        return (SmiSymbol)this.symbolMap.get(id);
    }

    public void resolveImports(XRefProblemReporter reporter) {
        this.module = this.importerModule.getMib().findModule(this.moduleToken.getId());
        if (this.module != null) {
            Iterator i$ = this.getSymbolTokens().iterator();

            while(i$.hasNext()) {
                IdToken idToken = (IdToken)i$.next();
                SmiSymbol symbol = this.getModule().findSymbol(idToken.getId());
                if (symbol != null) {
                    this.symbolMap.put(idToken.getId(), symbol);
                } else {
                    reporter.reportCannotFindImportedSymbol(idToken, this.moduleToken);
                }
            }
        } else if (this.importerModule.getMib().getOptions().isConvertV1ImportsToV2()) {
            this.resolveV1Imports(reporter);
        } else {
            reporter.reportCannotFindModule(this.moduleToken);
        }

    }

    public boolean checkImports() {
        this.module = this.importerModule.getMib().findModule(this.moduleToken.getId());
        if (this.module != null) {
            // module exist
            return true;
        } else if (this.importerModule.getMib().getOptions().isConvertV1ImportsToV2()) {
            return this.checkV1Imports();
        } else {
//            reporter.reportCannotFindModule(this.moduleToken);
            return false;
        }
    }

    private void resolveV1Imports(XRefProblemReporter reporter) {
        Iterator i$ = this.getSymbolTokens().iterator();

        while(i$.hasNext()) {
            IdToken idToken = (IdToken)i$.next();
            Pair<String, String> v2Definition = this.findV2Definition(idToken.getId());
            if (v2Definition != null) {
                SmiModule module = this.importerModule.getMib().findModule((String)v2Definition.getFirst());
                if (module != null) {
                    SmiSymbol symbol = module.findSymbol((String)v2Definition.getSecond());
                    if (symbol != null) {
                        this.symbolMap.put(idToken.getId(), symbol);
                    } else {
                        reporter.reportCannotFindImportedSymbol(idToken, this.moduleToken);
                    }
                } else {
                    reporter.reportCannotFindModule(this.moduleToken);
                }
            } else {
                reporter.reportCannotFindImportedSymbol(idToken, this.moduleToken);
            }
        }

    }

    private boolean checkV1Imports() {
        Iterator i$ = this.getSymbolTokens().iterator();

        while(i$.hasNext()) {
            IdToken idToken = (IdToken)i$.next();
            Pair<String, String> v2Definition = this.findV2Definition(idToken.getId());
            if (v2Definition != null) {
                SmiModule module = this.importerModule.getMib().findModule((String)v2Definition.getFirst());
                if (module != null) {
                    return true;
                } else {
//                    reporter.reportCannotFindModule(this.moduleToken);
                    return false;
                }
            } else {
//                reporter.reportCannotFindImportedSymbol(idToken, this.moduleToken);
                return false;
            }
        }
        return false;
    }

    public Pair<String, String> findV2Definition(String id) {
        for(int i = 0; i < V1_V2_MAP.length; i += 4) {
            String oldMib = V1_V2_MAP[i];
            String oldId = V1_V2_MAP[i + 1];
            if (oldMib.equals(this.moduleToken.getId()) && oldId.equals(id)) {
                return new Pair(V1_V2_MAP[i + 2], V1_V2_MAP[i + 3]);
            }
        }

        return null;
    }
}
