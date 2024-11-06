package hanati.snmp.mibparser.smi;

import hanati.snmp.mibparser.phase.xref.XRefProblemReporter;
import hanati.snmp.mibparser.util.token.IdToken;

public class ScopedId {
    private final SmiModule localModule;
    private final IdToken moduleToken;
    private final IdToken symbolToken;
    private SmiModule module;
    private SmiSymbol symbol;

    public ScopedId(SmiModule localModule, IdToken moduleToken, IdToken symbolToken) {
        this.localModule = localModule;
        this.moduleToken = moduleToken;
        this.symbolToken = symbolToken;
    }

    public SmiModule getLocalModule() {
        return this.localModule;
    }

    public IdToken getModuleToken() {
        return this.moduleToken;
    }

    public IdToken getSymbolToken() {
        return this.symbolToken;
    }

    public SmiModule getModule() {
        return this.module;
    }

    public SmiSymbol getSymbol() {
        return this.symbol;
    }

    public void resolveReferences(XRefProblemReporter reporter) {
        if (this.moduleToken != null) {
            this.module = this.localModule.getMib().resolveModule(this.moduleToken, reporter);
        }

        if (this.module != null) {
            this.symbol = this.module.resolveReference(this.symbolToken, reporter);
        } else {
            this.symbol = this.localModule.resolveReference(this.symbolToken, reporter);
        }

    }
}
