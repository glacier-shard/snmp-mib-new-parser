package hanati.snmp.mibparser.phase.xref;

public abstract class AbstractSMIv1SymbolDefiner extends AbstractSymbolDefiner {
    protected AbstractSMIv1SymbolDefiner(String moduleId) {
        super(moduleId);
    }

    public void defineSymbols() {
        super.defineSymbols();
        this.addInternetOid();
        this.addDirectoryOid();
        this.addMgmtOid();
        this.addExperimentalOid();
        this.addPrivateOid();
        this.addEnterprisesOid();
        this.addObjectTypeMacro();
        this.addObjectNameType();
        this.addObjectSyntaxType();
        this.addSimpleSyntaxType();
        this.addApplicationSyntaxType();
        this.addNetworkAddressType();
        this.addIpAddressType();
        this.addCounterType();
        this.addGaugeType();
        this.addTimeTicksType();
        this.addOpaqueType();
    }
}
