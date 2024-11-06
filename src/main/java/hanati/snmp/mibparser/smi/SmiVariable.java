package hanati.snmp.mibparser.smi;

import java.util.Iterator;
import java.util.List;

import hanati.snmp.mibparser.phase.xref.XRefProblemReporter;
import hanati.snmp.mibparser.util.token.IdToken;
import hanati.snmp.mibparser.util.token.QuotedStringToken;

public class SmiVariable extends SmiObjectType {
    private final QuotedStringToken unitsToken;
    private final SmiDefaultValue defaultValue;

    public SmiVariable(IdToken idToken, SmiModule module, SmiType type, QuotedStringToken unitsToken, SmiDefaultValue defaultValue) {
        super(idToken, module);
        this.setType(type);
        this.unitsToken = unitsToken;
        this.defaultValue = defaultValue;
        if (this.defaultValue != null) {
            this.defaultValue.variable = this;
        }

    }

    public String getCodeConstantId() {
        return this.getModule().getMib().getCodeNamingStrategy().getCodeConstantId(this);
    }

    public String getFullCodeConstantId() {
        return this.getModule().getMib().getCodeNamingStrategy().getFullCodeConstantId(this);
    }

    public String getCodeOid() {
        return this.getOidStr();
    }

    public String getCodeId() {
        return this.getModule().getMib().getCodeNamingStrategy().getVariableId(this);
    }

    public String getRequestMethodId() {
        return this.getModule().getMib().getCodeNamingStrategy().getRequestMethodId(this);
    }

    public String getGetterMethodId() {
        return this.getModule().getMib().getCodeNamingStrategy().getGetterMethodId(this);
    }

    public String getSetterMethodId() {
        return this.getModule().getMib().getCodeNamingStrategy().getSetterMethodId(this);
    }

    public SmiRow getRow() {
        if (this.getNode() != null && this.getNode().getParent() != null) {
            SmiOidValue oidValue = this.getNode().getParent().getSingleValue(SmiOidValue.class, this.getModule());
            if (oidValue instanceof SmiRow) {
                return (SmiRow)oidValue;
            }
        }

        return null;
    }

    public SmiTable getTable() {
        SmiRow row = this.getRow();
        return row != null ? row.getTable() : null;
    }

    public boolean isColumn() {
        return this.getRow() != null;
    }

    public boolean isScalar() {
        return this.getRow() == null;
    }

    public String getUnits() {
        return this.unitsToken != null ? (String)this.unitsToken.getValue() : null;
    }

    public QuotedStringToken getUnitsToken() {
        return this.unitsToken;
    }

    public SmiTextualConvention getTextualConvention() {
        for(SmiType smiType = this.type; smiType != null; smiType = smiType.getBaseType()) {
            if (smiType instanceof SmiTextualConvention) {
                return (SmiTextualConvention)smiType;
            }
        }

        return null;
    }

    public SmiPrimitiveType getPrimitiveType() {
        return this.type.getPrimitiveType();
    }

    public List<SmiNamedNumber> getEnumValues() {
        for(SmiType smiType = this.type; smiType != null; smiType = smiType.getBaseType()) {
            if (smiType.getEnumValues() != null) {
                return smiType.getEnumValues();
            }
        }

        return null;
    }

    public List<SmiNamedNumber> getBitFields() {
        for(SmiType smiType = this.type; smiType != null; smiType = smiType.getBaseType()) {
            if (smiType.getBitFields() != null) {
                return smiType.getBitFields();
            }
        }

        return null;
    }

    public List<SmiRange> getRangeConstraints() {
        for(SmiType smiType = this.type; smiType != null; smiType = smiType.getBaseType()) {
            if (smiType.getRangeConstraints() != null) {
                return smiType.getRangeConstraints();
            }
        }

        return null;
    }

    public List<SmiRange> getSizeConstraints() {
        for(SmiType smiType = this.type; smiType != null; smiType = smiType.getBaseType()) {
            if (smiType.getSizeConstraints() != null) {
                return smiType.getSizeConstraints();
            }
        }

        return null;
    }

    public SmiDefaultValue getDefaultValue() {
        return this.defaultValue;
    }

    public SmiNamedNumber resolveBitField(IdToken idToken, XRefProblemReporter reporter) {
        Iterator i$ = this.getBitFields().iterator();

        SmiNamedNumber nn;
        do {
            if (!i$.hasNext()) {
                reporter.reportCannotFindBitField(idToken);
                return null;
            }

            nn = (SmiNamedNumber)i$.next();
        } while(!nn.getId().equals(idToken.getId()));

        return nn;
    }

    public SmiNamedNumber resolveEnumConstant(IdToken idToken, XRefProblemReporter reporter) {
        Iterator i$ = this.getEnumValues().iterator();

        SmiNamedNumber nn;
        do {
            if (!i$.hasNext()) {
                reporter.reportCannotFindEnumConstant(idToken);
                return null;
            }

            nn = (SmiNamedNumber)i$.next();
        } while(!nn.getId().equals(idToken.getId()));

        return nn;
    }
}
