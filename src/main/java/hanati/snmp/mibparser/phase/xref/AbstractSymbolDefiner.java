package hanati.snmp.mibparser.phase.xref;

import java.util.Collections;
import java.util.Iterator;
import hanati.snmp.mibparser.smi.OidComponent;
import hanati.snmp.mibparser.smi.SmiConstants;
import hanati.snmp.mibparser.smi.SmiMacro;
import hanati.snmp.mibparser.smi.SmiModule;
import hanati.snmp.mibparser.smi.SmiOidValue;
import hanati.snmp.mibparser.smi.SmiProtocolType;
import hanati.snmp.mibparser.smi.SmiRange;
import hanati.snmp.mibparser.smi.SmiSymbol;
import hanati.snmp.mibparser.smi.SmiType;
import hanati.snmp.mibparser.util.location.Location;
import hanati.snmp.mibparser.util.pair.StringIntPair;
import hanati.snmp.mibparser.util.token.BigIntegerToken;
import hanati.snmp.mibparser.util.token.IdToken;
import hanati.snmp.mibparser.util.token.IntegerToken;

public abstract class AbstractSymbolDefiner implements SymbolDefiner {
    protected String m_moduleId;
    protected SmiModule m_module;
    protected boolean m_defineItu;
    protected boolean m_defineIso;

    protected AbstractSymbolDefiner(String moduleId) {
        this.m_moduleId = moduleId;
    }

    public String getModuleId() {
        return this.m_moduleId;
    }

    public void setModuleId(String moduleId) {
        this.m_moduleId = moduleId;
    }

    public void defineSymbols(SmiModule module) {
        this.m_module = module;
        this.defineSymbols();
        this.m_module = null;
    }

    protected void defineSymbols() {
        if (this.m_defineIso) {
            this.addIsoOid();
        }

    }

    public void addItuOid() {
        this.addOid("itu", new StringIntPair(0));
    }

    public void addIsoOid() {
        this.addOid("iso", new StringIntPair(1));
    }

    public void addOrgOid() {
        this.addOid("org", new StringIntPair("iso", 3));
    }

    public void addDodOid() {
        this.addOid("dod", new StringIntPair("org", 1));
    }

    public void addInternetOid() {
        this.addOid("internet", new StringIntPair("iso"), new StringIntPair("org", 3), new StringIntPair("dod", 6), new StringIntPair(1));
    }

    public void addDirectoryOid() {
        this.addOid("directory", new StringIntPair("internet"), new StringIntPair(1));
    }

    public void addMgmtOid() {
        this.addOid("mgmt", new StringIntPair("internet"), new StringIntPair(2));
    }

    public void addMib2Oid() {
        this.addOid("mib-2", new StringIntPair("mgmt"), new StringIntPair(1));
    }

    public void addTransmissionOid() {
        this.addOid("transmission", new StringIntPair("mib-2"), new StringIntPair(10));
    }

    public void addExperimentalOid() {
        this.addOid("experimental", new StringIntPair("internet"), new StringIntPair(3));
    }

    public void addPrivateOid() {
        this.addOid("private", new StringIntPair("internet"), new StringIntPair(4));
    }

    public void addEnterprisesOid() {
        this.addOid("enterprises", new StringIntPair("private"), new StringIntPair(1));
    }

    public void addOid(String id, StringIntPair... oidComponents) {
        if (this.isMissing(id)) {
            SmiOidValue oidValue = new SmiOidValue(this.idt(id), this.m_module);
            OidComponent oc = null;
            StringIntPair[] arr$ = oidComponents;
            int len$ = oidComponents.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                StringIntPair oidComponent = arr$[i$];
                IdToken idToken = oidComponent.getString() != null ? this.idt(oidComponent.getString()) : null;
                IntegerToken intToken = oidComponent.getInt() != null ? this.intt(oidComponent.getInt()) : null;
                oc = new OidComponent(oc, idToken, intToken);
            }

            oidValue.setLastOidComponent(oc);
            this.m_module.addSymbol(oidValue);
        }

    }

    public boolean isMissing(String id) {
        Iterator i$ = this.m_module.getSymbols().iterator();

        SmiSymbol symbol;
        do {
            if (!i$.hasNext()) {
                return true;
            }

            symbol = (SmiSymbol)i$.next();
        } while(!id.equals(symbol.getId()));

        return false;
    }

    public IdToken idt(String id) {
        return new IdToken(this.location(), id);
    }

    public IntegerToken intt(int value) {
        return new IntegerToken(this.location(), value);
    }

    public Location location() {
        return null;
    }

    public void addObjectTypeMacro() {
        this.addMacro("OBJECT-TYPE");
    }

    public void addTrapTypeMacro() {
        this.addMacro("TRAP-TYPE");
    }

    public void addMacro(String id) {
        if (this.isMissing(id)) {
            SmiMacro macro = new SmiMacro(this.idt(id), this.m_module);
            this.m_module.addSymbol(macro);
        }

    }

    public void addObjectSyntaxType() {
        this.addChoiceType("ObjectSyntax");
    }

    public void addSimpleSyntaxType() {
        this.addChoiceType("SimpleSyntax");
    }

    public void addApplicationSyntaxType() {
        this.addChoiceType("ApplicationSyntax");
    }

    public void addIndexSyntaxType() {
        this.addChoiceType("IndexSyntax");
    }

    public void addNetworkAddressType() {
        this.addChoiceType("NetworkAddress");
    }

    public void addChoiceType(String id) {
        if (this.isMissing(id)) {
            SmiType type = SmiProtocolType.createChoiceType(this.idt(id), this.m_module);
            this.m_module.addSymbol(type);
        }

    }

    public void addObjectNameType() {
        this.addObjectIdentifierType("ObjectName");
    }

    public void addNotificationNameType() {
        this.addObjectIdentifierType("NotificationName");
    }

    public void addObjectIdentifierType(String id) {
        if (this.isMissing(id)) {
            SmiType type = new SmiType(this.idt(id), this.m_module);
            type.setBaseType(SmiConstants.OBJECT_IDENTIFIER_TYPE);
        }

    }

    public void addInteger32Type() {
        if (this.isMissing("Integer32")) {
            SmiType type = new SmiType(this.idt("Integer32"), this.m_module);
            type.setBaseType(SmiConstants.INTEGER_TYPE);
            SmiRange range = new SmiRange(new BigIntegerToken(Integer.MIN_VALUE), new BigIntegerToken(Integer.MAX_VALUE));
            type.setRangeConstraints(Collections.singletonList(range));
            this.m_module.addSymbol(type);
        }

    }

    public void addIpAddressType() {
        this.addApplicationType("IpAddress", 0);
    }

    public void addCounterType() {
        this.addApplicationType("Counter", 1);
    }

    public void addCounter32Type() {
        this.addApplicationType("Counter32", 1);
    }

    public void addGaugeType() {
        this.addApplicationType("Gauge", 2);
    }

    public void addGauge32Type() {
        this.addApplicationType("Gauge32", 2);
    }

    public void addUnsigned32Type() {
        this.addApplicationType("Unsigned32", 2);
    }

    public void addTimeTicksType() {
        this.addApplicationType("TimeTicks", 3);
    }

    public void addOpaqueType() {
        this.addApplicationType("Opaque", 4);
    }

    public void addCounter64Type() {
        this.addApplicationType("Counter64", 6);
    }

    public void addApplicationType(String id, int tag) {
        if (this.isMissing(id)) {
            SmiType type = new SmiType(this.idt(id), this.m_module, tag);
            this.m_module.addSymbol(type);
        }

    }

    public boolean isDefineItu() {
        return this.m_defineItu;
    }

    public void setDefineItu(boolean defineItu) {
        this.m_defineItu = defineItu;
    }

    public boolean isDefineIso() {
        return this.m_defineIso;
    }

    public void setDefineIso(boolean defineIso) {
        this.m_defineIso = defineIso;
    }
}
