package hanati.snmp.mibparser.smi;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hanati.snmp.mibparser.phase.xref.XRefProblemReporter;
import hanati.snmp.mibparser.util.token.BigIntegerToken;
import hanati.snmp.mibparser.util.token.BinaryStringToken;
import hanati.snmp.mibparser.util.token.HexStringToken;
import hanati.snmp.mibparser.util.token.IdToken;
import hanati.snmp.mibparser.util.token.QuotedStringToken;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class SmiDefaultValue {
    private static final Logger LOG = LogManager.getLogger(SmiDefaultValue.class);
    private final SmiModule module;
    private final BigIntegerToken bigIntegerToken;
    private final List<IdToken> bitsIdTokenList;
    private final OidComponent lastOidComponent;
    private final BinaryStringToken binaryStringToken;
    private final HexStringToken hexStringToken;
    private final QuotedStringToken quotedStringToken;
    private final ScopedId scopedId;
    private final boolean isNullValue;
    private final Boolean logicalValue;
    SmiVariable variable;
    private ArrayList<SmiNamedNumber> bitsValue;
    private SmiNamedNumber enumValue;
    private SmiOidValue oidValue;
    private SmiOidNode oidNode;
    private SmiSymbol symbolValue;

    public SmiDefaultValue(SmiModule module, BigIntegerToken bigIntegerToken, List<IdToken> bitsIdTokenList, OidComponent lastOidComponent, BinaryStringToken binaryStringToken, HexStringToken hexStringToken, QuotedStringToken quotedStringToken, ScopedId scopedId, boolean nullValue, Boolean logicalValue) {
        this.module = module;
        this.bigIntegerToken = bigIntegerToken;
        this.bitsIdTokenList = bitsIdTokenList;
        this.lastOidComponent = lastOidComponent;
        this.binaryStringToken = binaryStringToken;
        this.hexStringToken = hexStringToken;
        this.quotedStringToken = quotedStringToken;
        this.scopedId = scopedId;
        this.isNullValue = nullValue;
        this.logicalValue = logicalValue;
    }

    public SmiVariable getVariable() {
        return this.variable;
    }

    public BigInteger getIntegerValue() {
        return this.bigIntegerToken != null ? (BigInteger)this.bigIntegerToken.getValue() : null;
    }

    public List<SmiNamedNumber> getBitsValue() {
        return this.bitsValue;
    }

    public SmiNamedNumber getEnumValue() {
        return this.enumValue;
    }

    public SmiOidValue getOidValue() {
        return this.oidValue;
    }

    public SmiOidNode getOidNode() {
        if (this.oidNode != null) {
            return this.oidNode;
        } else {
            return this.oidValue != null ? this.oidValue.getNode() : null;
        }
    }

    public String getCStringValue() {
        return this.quotedStringToken != null ? (String)this.quotedStringToken.getValue() : null;
    }

    public String getBinaryStringValue() {
        return this.binaryStringToken != null ? (String)this.binaryStringToken.getValue() : null;
    }

    public String getHexStringValue() {
        return this.hexStringToken != null ? (String)this.hexStringToken.getValue() : null;
    }

    public SmiSymbol getSymbolValue() {
        return this.symbolValue;
    }

    public BigIntegerToken getBigIntegerToken() {
        return this.bigIntegerToken;
    }

    public List<IdToken> getBitsIdTokenList() {
        return this.bitsIdTokenList;
    }

    public OidComponent getLastOidComponents() {
        return this.lastOidComponent;
    }

    public BinaryStringToken getBinaryStringToken() {
        return this.binaryStringToken;
    }

    public HexStringToken getHexStringToken() {
        return this.hexStringToken;
    }

    public QuotedStringToken getQuotedStringToken() {
        return this.quotedStringToken;
    }

    public ScopedId getScopedId() {
        return this.scopedId;
    }

    public boolean isNullValue() {
        return this.isNullValue;
    }

    public void resolveReferences(XRefProblemReporter reporter) {
        if (this.bitsIdTokenList != null) {
            this.resolveBits(reporter);
        } else if (this.lastOidComponent != null) {
            this.oidNode = this.resolveOids(reporter);
        } else if (this.scopedId != null) {
            if (this.scopedId.getModuleToken() != null) {
                LOG.debug("Not yet implemented: " + this.scopedId.getModuleToken());
            } else if (this.variable.getEnumValues() != null) {
                this.enumValue = this.variable.resolveEnumConstant(this.scopedId.getSymbolToken(), reporter);
            } else {
                SmiSymbol symbol = this.variable.getModule().resolveReference(this.scopedId.getSymbolToken(), reporter);
                if (symbol != null) {
                    if (symbol instanceof SmiOidValue && this.variable.getPrimitiveType() == SmiPrimitiveType.OBJECT_IDENTIFIER) {
                        this.oidValue = (SmiOidValue)symbol;
                        this.oidNode = this.oidValue.getNode();
                    } else {
                        this.symbolValue = symbol;
                        reporter.reportInvalidDefaultValue(this.scopedId.getSymbolToken());
                    }
                }
            }
        }

    }

    private SmiOidNode resolveOids(XRefProblemReporter reporter) {
        return this.lastOidComponent.resolveNode(this.module, reporter);
    }

    private void resolveBits(XRefProblemReporter reporter) {
        if (this.variable.getBitFields() != null) {
            this.bitsValue = new ArrayList();
            Iterator i$ = this.bitsIdTokenList.iterator();

            while(i$.hasNext()) {
                IdToken idToken = (IdToken)i$.next();
                SmiNamedNumber nn = this.variable.resolveBitField(idToken, reporter);
                this.bitsValue.add(nn);
            }
        } else {
            reporter.reportBitsValueWithoutBitsType(((IdToken)this.bitsIdTokenList.get(0)).getLocation());
        }

    }
}
