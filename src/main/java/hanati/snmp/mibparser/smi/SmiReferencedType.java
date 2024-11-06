package hanati.snmp.mibparser.smi;

import java.util.List;

import hanati.snmp.mibparser.phase.xref.XRefProblemReporter;
import hanati.snmp.mibparser.util.token.IdToken;

public class SmiReferencedType extends SmiType {
    private IdToken referencedModuleToken;
    private List<SmiNamedNumber> namedNumbers;

    public SmiReferencedType(IdToken idToken, SmiModule module) {
        super(idToken, module);
    }

    public IdToken getReferencedModuleToken() {
        return this.referencedModuleToken;
    }

    public void setReferencedModuleToken(IdToken referencedModuleToken) {
        this.referencedModuleToken = referencedModuleToken;
    }

    public List<SmiNamedNumber> getNamedNumbers() {
        return this.namedNumbers;
    }

    public void setNamedNumbers(List<SmiNamedNumber> namedNumbers) {
        this.namedNumbers = namedNumbers;
    }

    public SmiType resolveThis(XRefProblemReporter reporter, SmiType parentType) {
        SmiType result = this;
        SmiType type = (SmiType)this.getModule().resolveReference(this.getIdToken(), SmiType.class, reporter);
        if (type != null) {
            if (this.getEnumValues() == null && this.getBitFields() == null && this.getRangeConstraints() == null && this.getSizeConstraints() == null && this.getSelectContraints() == null) {
                result = type;
            } else if (parentType != null) {
                parentType.setEnumValues(this.getEnumValues());
                parentType.setBitFields(this.getBitFields());
                parentType.setRangeConstraints(this.getRangeConstraints());
                parentType.setSizeConstraints(this.getSizeConstraints());
                parentType.setSelectContraints(this.getSelectContraints());
                result = type;
            } else {
                result = new SmiType((IdToken)null, this.getModule());
                ((SmiType)result).setEnumValues(this.getEnumValues());
                ((SmiType)result).setBitFields(this.getBitFields());
                ((SmiType)result).setRangeConstraints(this.getRangeConstraints());
                ((SmiType)result).setSizeConstraints(this.getSizeConstraints());
                ((SmiType)result).setSelectContraints(this.getSelectContraints());
                ((SmiType)result).setBaseType(type);
            }
        }

        return (SmiType)result;
    }

    public String toString() {
        return "WARNING REFERENCED TYPE: " + super.toString();
    }
}
