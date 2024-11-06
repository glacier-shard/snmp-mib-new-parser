package hanati.snmp.mibparser.smi;

import hanati.snmp.mibparser.util.token.IdToken;

public abstract class SmiValue extends SmiSymbol {
    private static final long serialVersionUID = 7551610624414592921L;

    public SmiValue(IdToken idToken, SmiModule module) {
        super(idToken, module);
    }
}
