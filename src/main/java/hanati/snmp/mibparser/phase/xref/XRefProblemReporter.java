package hanati.snmp.mibparser.phase.xref;

import hanati.snmp.mibparser.smi.SmiSymbol;
import hanati.snmp.mibparser.util.location.Location;
import hanati.snmp.mibparser.util.problem.annotations.ProblemMethod;
import hanati.snmp.mibparser.util.problem.annotations.ProblemSeverity;
import hanati.snmp.mibparser.util.token.IdToken;
import hanati.snmp.mibparser.util.token.Token;

public interface XRefProblemReporter {
    @ProblemMethod(
            message = "Cannot find module %s"
    )
    void reportCannotFindModule(IdToken var1);

    @ProblemMethod(
            message = "Cannot find imported symbol %s in module %s"
    )
    void reportCannotFindImportedSymbol(IdToken var1, IdToken var2);

    @ProblemMethod(
            message = "Cannot find symbol %s"
    )
    void reportCannotFindSymbol(IdToken var1);

    @ProblemMethod(
            message = "Found symbol %s but expected a %s instead of %s"
    )
    void reportFoundSymbolButWrongType(IdToken var1, Class<? extends SmiSymbol> var2, Class<? extends SmiSymbol> var3);

    @ProblemMethod(
            message = "DEFVAL with bit values but variable doesn't have a BITS type."
    )
    void reportBitsValueWithoutBitsType(Location var1);

    @ProblemMethod(
            message = "Cannot find bit field %s"
    )
    void reportCannotFindBitField(IdToken var1);

    @ProblemMethod(
            message = "Cannot find enum constant %s"
    )
    void reportCannotFindEnumConstant(IdToken var1);

    @ProblemMethod(
            message = "Invalid default value %s",
            severity = ProblemSeverity.WARNING
    )
    void reportInvalidDefaultValue(IdToken var1);

    @ProblemMethod(
            message = "An object identifier default value must be expressed as a single ASN.1 identifier, and not as a collection of sub-identifiers: %s",
            ref = "RFC1902, 7.9"
    )
    void reportOidDefaultValueMustBeSingleIdentifier(Token var1);

    @ProblemMethod(
            message = "%s is not a valid value for the ACCESS(SMIv1) field. You are probably mixing v1/v2 constructions.",
            severity = ProblemSeverity.WARNING
    )
    void reportInvalidAccess(IdToken var1);

    @ProblemMethod(
            message = "%s is not a valid value for the MAX-ACCESS(SMIv2) field. You are probably mixing v1/v2 constructions.",
            severity = ProblemSeverity.WARNING
    )
    void reportInvalidMaxAccess(IdToken var1);
}
