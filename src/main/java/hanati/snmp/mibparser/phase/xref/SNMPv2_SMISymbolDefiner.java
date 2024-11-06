package hanati.snmp.mibparser.phase.xref;

import java.util.ArrayList;
import java.util.List;
import hanati.snmp.mibparser.smi.SmiConstants;
import hanati.snmp.mibparser.smi.SmiRange;
import hanati.snmp.mibparser.smi.SmiType;
import hanati.snmp.mibparser.util.pair.StringIntPair;
import hanati.snmp.mibparser.util.token.BigIntegerToken;

public class SNMPv2_SMISymbolDefiner extends AbstractSymbolDefiner {
    protected SNMPv2_SMISymbolDefiner() {
        super("SNMPv2-SMI");
    }

    protected void defineSymbols() {
        super.defineSymbols();
        this.addOrgOid();
        this.addDodOid();
        this.addInternetOid();
        this.addDirectoryOid();
        this.addMgmtOid();
        this.addMib2Oid();
        this.addTransmissionOid();
        this.addExperimentalOid();
        this.addPrivateOid();
        this.addEnterprisesOid();
        this.addSnmpV2Oid();
        this.addSnmpDomainsOid();
        this.addSnmpProxysOid();
        this.addSnmpModulesOid();
        this.addExtUTCTimeType();
        this.addModuleIdentityMacro();
        this.addObjectIdentityMacro();
        this.addObjectNameType();
        this.addNotificationNameType();
        this.addObjectSyntaxType();
        this.addSimpleSyntaxType();
        this.addInteger32Type();
        this.addApplicationSyntaxType();
        this.addNetworkAddressType();
        this.addIpAddressType();
        this.addCounter32Type();
        this.addGauge32Type();
        this.addTimeTicksType();
        this.addOpaqueType();
        this.addCounter64Type();
        this.addObjectTypeMacro();
        this.addNotificationTypeMacro();
        this.addZeroDotZeroObjectIdentity();
    }

    private void addSnmpV2Oid() {
        this.addOid("snmpV2", new StringIntPair[]{new StringIntPair("internet"), new StringIntPair(6)});
    }

    private void addSnmpDomainsOid() {
        this.addOid("snmpDomains", new StringIntPair[]{new StringIntPair("snmpV2"), new StringIntPair(1)});
    }

    private void addSnmpProxysOid() {
        this.addOid("snmpProxys", new StringIntPair[]{new StringIntPair("snmpV2"), new StringIntPair(2)});
    }

    private void addSnmpModulesOid() {
        this.addOid("snmpModules", new StringIntPair[]{new StringIntPair("snmpV2"), new StringIntPair(3)});
    }

    private void addExtUTCTimeType() {
        if (this.isMissing("ExtUTCTime")) {
            SmiType type = new SmiType(this.idt("ExtUTCTime"), this.m_module);
            type.setBaseType(SmiConstants.OCTET_STRING_TYPE);
            List<SmiRange> constraints = new ArrayList();
            constraints.add(new SmiRange(new BigIntegerToken(11)));
            constraints.add(new SmiRange(new BigIntegerToken(13)));
            type.setSizeConstraints(constraints);
            this.m_module.addSymbol(type);
        }

    }

    private void addModuleIdentityMacro() {
        this.addMacro("MODULE-IDENTITY");
    }

    private void addObjectIdentityMacro() {
        this.addMacro("OBJECT-IDENTITY");
    }

    private void addNotificationTypeMacro() {
        this.addMacro("NOTIFICATION-TYPE");
    }

    private void addZeroDotZeroObjectIdentity() {
        this.addOid("zeroDotZero", new StringIntPair[]{new StringIntPair(0), new StringIntPair(0)});
    }
}
