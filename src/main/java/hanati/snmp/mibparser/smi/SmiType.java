package hanati.snmp.mibparser.smi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hanati.snmp.mibparser.phase.xref.XRefProblemReporter;
import hanati.snmp.mibparser.util.token.IdToken;

public class SmiType extends SmiSymbol {
    public static final SmiPrimitiveType[] APPLICATION_TYPES;
    private SmiType baseType;
    private final SmiPrimitiveType primitiveType;
    private List<SmiNamedNumber> enumValues;
    private List<SmiNamedNumber> bitFields;
    private List<SmiRange> rangeConstraints;
    private List<SmiRange> sizeConstraints;
    private SmiSelect selectContraints;
    private List<SmiField> fields;
    private IdToken elementTypeToken;
    private SmiType elementType;

    public SmiType(IdToken idToken, SmiModule module, SmiPrimitiveType primitiveType) {
        super(idToken, module);
        this.primitiveType = primitiveType;
    }

    public SmiType(IdToken idToken, SmiModule module, int applicationTag) {
        super(idToken, module);
        if (applicationTag >= 0) {
            if (applicationTag >= APPLICATION_TYPES.length) {
                throw new IllegalArgumentException("Application tag " + applicationTag + " is invalid at: " + idToken);
            }

            if (APPLICATION_TYPES[applicationTag] == SmiPrimitiveType.GAUGE_32 && "Unsigned32".equals(this.getId())) {
                this.primitiveType = SmiPrimitiveType.UNSIGNED_32;
            } else {
                this.primitiveType = APPLICATION_TYPES[applicationTag];
            }
        } else {
            this.primitiveType = null;
        }

    }

    public SmiType(IdToken idToken, SmiModule module) {
        super(idToken, module);
        this.primitiveType = null;
    }

    public SmiType getBaseType() {
        return this.baseType;
    }

    public void setBaseType(SmiType baseType) {
        this.baseType = baseType;
    }

    public SmiPrimitiveType getPrimitiveType() {
        if (this.enumValues != null) {
            return SmiPrimitiveType.ENUM;
        } else if (this.bitFields != null) {
            return SmiPrimitiveType.BITS;
        } else if ("Integer32".equals(this.getId()) && "SNMPv2-SMI".equals(this.getModule().getId())) {
            return SmiPrimitiveType.INTEGER_32;
        } else {
            return this.primitiveType == null && this.baseType != null ? this.baseType.getPrimitiveType() : this.primitiveType;
        }
    }

    public SmiVarBindField getVarBindField() {
        return this.getPrimitiveType().getVarBindField();
    }

    public List<SmiNamedNumber> getEnumValues() {
        return this.enumValues;
    }

    public void setEnumValues(List<SmiNamedNumber> enumValues) {
        if (enumValues != null) {
            this.setType(enumValues);
        }

        this.enumValues = enumValues;
    }

    private void setType(List<SmiNamedNumber> enumValues) {
        Iterator i$ = enumValues.iterator();

        while(i$.hasNext()) {
            SmiNamedNumber namedNumber = (SmiNamedNumber)i$.next();
            namedNumber.setType(this);
        }

    }

    public List<SmiNamedNumber> getBitFields() {
        return this.bitFields;
    }

    public void setBitFields(List<SmiNamedNumber> bitFields) {
        if (bitFields != null) {
            this.setType(bitFields);
        }

        this.bitFields = bitFields;
    }

    public String getCodeId() {
        return this.getModule().getMib().getCodeNamingStrategy().getTypeId(this);
    }

    public SmiNamedNumber getBiggestEnumValue() {
        int currentBiggest = Integer.MIN_VALUE;
        SmiNamedNumber result = null;
        Iterator i$ = this.enumValues.iterator();

        while(i$.hasNext()) {
            SmiNamedNumber ev = (SmiNamedNumber)i$.next();
            if (ev.getValue().intValue() > currentBiggest) {
                currentBiggest = ev.getValue().intValue();
                result = ev;
            }
        }

        return result;
    }

    public SmiNamedNumber getSmallestEnumValue() {
        int currentSmallest = Integer.MAX_VALUE;
        SmiNamedNumber result = null;
        Iterator i$ = this.enumValues.iterator();

        while(i$.hasNext()) {
            SmiNamedNumber ev = (SmiNamedNumber)i$.next();
            if (ev.getValue().intValue() < currentSmallest) {
                currentSmallest = ev.getValue().intValue();
                result = ev;
            }
        }

        return result;
    }

    public SmiNamedNumber findEnumValue(int i) {
        Iterator i$ = this.enumValues.iterator();

        SmiNamedNumber ev;
        do {
            if (!i$.hasNext()) {
                return null;
            }

            ev = (SmiNamedNumber)i$.next();
        } while(ev.getValue().intValue() != i);

        return ev;
    }

    public SmiNamedNumber findEnumValue(String id) {
        Iterator i$ = this.enumValues.iterator();

        SmiNamedNumber ev;
        do {
            if (!i$.hasNext()) {
                return null;
            }

            ev = (SmiNamedNumber)i$.next();
        } while(!ev.getId().equals(id));

        return ev;
    }

    public List<SmiRange> getRangeConstraints() {
        return this.rangeConstraints;
    }

    public void setRangeConstraints(List<SmiRange> rangeConstraints) {
        this.rangeConstraints = rangeConstraints;
    }

    public List<SmiRange> getSizeConstraints() {
        return this.sizeConstraints;
    }

    public void setSizeConstraints(List<SmiRange> sizeConstraints) {
        this.sizeConstraints = sizeConstraints;
    }

    public SmiSelect getSelectContraints() {
        return this.selectContraints;
    }

    public void setSelectContraints(SmiSelect selectContraints) {
        this.selectContraints = selectContraints;
    }
    public void addField(IdToken col, SmiType fieldType) {
        if (this.fields == null) {
            this.fields = new ArrayList();
        }

        this.fields.add(new SmiField(this, col, fieldType));
    }

    public List<SmiField> getFields() {
        return this.fields;
    }

    public IdToken getElementTypeToken() {
        return this.elementTypeToken;
    }

    public void setElementTypeToken(IdToken elementTypeToken) {
        this.elementTypeToken = elementTypeToken;
    }

    public SmiType getElementType() {
        return this.elementType;
    }

    public void setElementType(SmiType elementType) {
        this.elementType = elementType;
    }

    public SmiType resolveThis(XRefProblemReporter reporter, SmiType ignored) {
        if (this.baseType != null) {
            this.baseType = this.baseType.resolveThis(reporter, this);
        }

        return this;
    }

    public void resolveReferences(XRefProblemReporter reporter) {
        assert this.getIdToken() != null;

        assert !(this instanceof SmiReferencedType);

        if (this.baseType != null) {
            this.baseType = this.baseType.resolveThis(reporter, this);
        }

        if (this.elementTypeToken != null) {
            this.elementType = (SmiType)this.getModule().resolveReference(this.elementTypeToken, SmiType.class, reporter);
        }

        if (this.fields != null) {
            Iterator i$ = this.fields.iterator();

            while(i$.hasNext()) {
                SmiField field = (SmiField)i$.next();
                field.resolveReferences(reporter);
            }
        }

    }

    static {
        APPLICATION_TYPES = new SmiPrimitiveType[]{SmiPrimitiveType.IP_ADDRESS, SmiPrimitiveType.COUNTER_32, SmiPrimitiveType.GAUGE_32, SmiPrimitiveType.TIME_TICKS, SmiPrimitiveType.OPAQUE, null, SmiPrimitiveType.COUNTER_64};
    }
}
