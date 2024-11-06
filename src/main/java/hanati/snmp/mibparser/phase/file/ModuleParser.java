package hanati.snmp.mibparser.phase.file;

import antlr.Token;
import java.util.ArrayList;
import java.util.List;

import hanati.snmp.mibparser.smi.*;
import hanati.snmp.mibparser.util.token.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import hanati.snmp.mibparser.util.location.Location;

public class ModuleParser {
    private static final Logger m_log = LogManager.getLogger(ModuleParser.class);
    private final SmiModule m_module;

    public ModuleParser(SmiModule module) {
        this.m_module = module;
    }

    public SmiModule getModule() {
        return this.m_module;
    }

    private Location makeLocation(Token token) {
        String source = this.m_module.getIdToken().getLocation().getSource();
        return new Location(source, token.getLine(), token.getColumn());
    }

    public IdToken idt(Token idToken) {
        return new IdToken(this.makeLocation(idToken), idToken.getText());
    }

    public IdToken idt(Token... tokens) {
        Token[] arr$ = tokens;
        int len$ = tokens.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Token token = arr$[i$];
            if (token != null) {
                return this.idt(token);
            }
        }

        return null;
    }

    public IntKeywordToken intkt(Token idToken, SmiPrimitiveType primitiveType, SmiVersion version) {
        if (version != null) {
            if (version == SmiVersion.V1) {
                this.m_module.incV1Features();
            } else {
                this.m_module.incV2Features();
            }
        }

        return new IntKeywordToken(this.makeLocation(idToken), idToken.getText(), primitiveType);
    }

    public String getCStr(Token t) {
        String text = t.getText();
        if (text.startsWith("\"") && text.endsWith("\"")) {
            return text.substring(1, text.length() - 1);
        } else {
            throw new IllegalArgumentException(t.getText());
        }
    }

    public String getOptCStr(Token t) {
        return t != null ? this.getCStr(t) : null;
    }

    public IntegerToken intt(Token t) {
        int value = Integer.parseInt(t.getText());
        return new IntegerToken(this.makeLocation(t), value);
    }

    public BigIntegerToken bintt(Token t) {
        return new BigIntegerToken(this.makeLocation(t), false, t.getText());
    }

    public BigIntegerToken bintt(Token minusToken, Token t) {
        return new BigIntegerToken(this.makeLocation(t), minusToken != null, t.getText());
    }

    public List<IdToken> makeIdTokenList() {
        return new ArrayList();
    }

    public void addImports(IdToken moduleToken, List<IdToken> importedTokenList) {
        SmiImports result = new SmiImports(this.m_module, moduleToken, importedTokenList);
        this.m_module.getImports().add(result);
    }

    public OidComponent createOidComponent(OidComponent parent, Token id, Token value) {
        IdToken idToken = id != null ? this.idt(id) : null;
//        IntegerToken valueToken = value != null ? this.intt(value) : null;
        IntegerToken valueToken = null;
        if(value != null) {
            try {
                valueToken = this.intt(value);
            } catch (NumberFormatException ne) {
//                valueToken = this.intt(value, 32);
                valueToken = null;
            }
        }
        return new OidComponent(parent, idToken, valueToken);
    }

    public SmiOidValue createOidValue(IdToken idToken, OidComponent lastOidComponent) {
        SmiOidValue result = new SmiOidValue(idToken, this.m_module);
        result.setLastOidComponent(lastOidComponent);
        return result;
    }

    public SmiMacro createMacro(IdToken idToken) {
        return new SmiMacro(idToken, this.m_module);
    }

    public SmiOidMacro createOidMacro(IdToken idToken) {
        return new SmiOidMacro(idToken, this.m_module);
    }

    public SmiVariable createVariable(IdToken idToken, SmiType t, Token units, SmiDefaultValue defaultValue) {
        String methodWithParams = "createVariable(" + idToken.getId() + ")";
        m_log.debug(methodWithParams);
        QuotedStringToken unitsToken = null;
        if (units != null) {
            unitsToken = new QuotedStringToken(this.makeLocation(units), units.getText(), '"');
        }

        return new SmiVariable(idToken, this.m_module, t, unitsToken, defaultValue);
    }

    public SmiRow createRow(IdToken idToken, SmiType t) {
        String methodWithParams = "createRow(" + idToken.getId() + ")";
        m_log.debug(methodWithParams);
        SmiRow result = new SmiRow(idToken, this.m_module);
        result.setType(t);
        return result;
    }

    public SmiTable createTable(IdToken idToken, SmiType t) {
        String methodWithParams = "createTable(" + idToken.getId() + ")";
        m_log.debug(methodWithParams);
        SmiTable result = new SmiTable(idToken, this.m_module);
        result.setType(t);
        return result;
    }

    public SmiTextualConvention createTextualConvention(IdToken idToken, Token displayHint, StatusV2 status, Token description, Token reference, SmiType type) {
        SmiTextualConvention result = new SmiTextualConvention(idToken, this.m_module, this.getOptCStr(displayHint), status, this.getCStr(description), this.getOptCStr(reference));
        if (type.getBaseType() == null) {
            result.setBaseType(type);
        } else {
            result.setBaseType(type.getBaseType());
        }

        result.setEnumValues(type.getEnumValues());
        result.setBitFields(type.getBitFields());
        result.setRangeConstraints(type.getRangeConstraints());
        result.setSizeConstraints(type.getSizeConstraints());
        return result;
    }

    public SmiType createSequenceType(IdToken idToken) {
        return new SmiType(idToken, this.m_module);
    }

    public SmiType createType(IdToken idToken, SmiType baseType) {
        if (baseType == null) {
            throw new IllegalArgumentException();
        } else {
            SmiType result;
            if (idToken == null) {
                result = baseType;
            } else {
                result = new SmiType(idToken, this.m_module);
                result.setBaseType(baseType);
            }

            return result;
        }
    }

    public SmiType createIntegerType(IdToken idToken, IntKeywordToken intToken, Token applicationTagToken, List<SmiNamedNumber> namedNumbers, List<SmiRange> rangeConstraints) {
        if (idToken == null && intToken.getPrimitiveType() == SmiPrimitiveType.INTEGER && namedNumbers == null && rangeConstraints == null) {
            return SmiConstants.INTEGER_TYPE;
        } else if (idToken == null && namedNumbers == null && rangeConstraints == null) {
            return new SmiReferencedType(intToken, this.m_module);
        } else {
            SmiType type = this.createPotentiallyTaggedType(idToken, applicationTagToken);
            if (intToken.getPrimitiveType() == SmiPrimitiveType.INTEGER) {
                type.setBaseType(SmiConstants.INTEGER_TYPE);
            } else {
                type.setBaseType(new SmiReferencedType(intToken, this.m_module));
            }

            type.setEnumValues(namedNumbers);
            type.setRangeConstraints(rangeConstraints);
            return type;
        }
    }

    private SmiType createPotentiallyTaggedType(IdToken idToken, Token applicationTagToken) {
        SmiType type;
        if (applicationTagToken != null) {
            int tag = Integer.parseInt(applicationTagToken.getText());
            type = new SmiType(idToken, this.m_module, tag);
        } else {
            type = new SmiType(idToken, this.m_module);
        }

        return type;
    }

    public SmiType createBitsType(IdToken idToken, List<SmiNamedNumber> namedNumbers) {
        this.m_module.incV2Features();
        if (idToken == null && namedNumbers == null) {
            return SmiConstants.BITS_TYPE;
        } else {
            SmiType type = new SmiType(idToken, this.m_module);
            type.setBaseType(SmiConstants.BITS_TYPE);
            type.setBitFields(namedNumbers);
            return type;
        }
    }

    public SmiType createOctetStringType(IdToken idToken, Token applicationTagToken, List<SmiRange> sizeConstraints) {
        if (idToken == null && sizeConstraints == null) {
            return SmiConstants.OCTET_STRING_TYPE;
        } else {
            SmiType type = this.createPotentiallyTaggedType(idToken, applicationTagToken);
            type.setBaseType(SmiConstants.OCTET_STRING_TYPE);
            type.setSizeConstraints(sizeConstraints);
            return type;
        }
    }

    public SmiType createBitStringType(IdToken idToken, Token applicationTagToken, List<SmiNamedNumber> namedNumbers) {
        if (idToken == null && namedNumbers == null) {
            return SmiConstants.BIT_STRING_TYPE;
        } else {
            SmiType type = this.createPotentiallyTaggedType(idToken, applicationTagToken);
            type.setBaseType(SmiConstants.BIT_STRING_TYPE);
            type.setEnumValues(namedNumbers);
            return type;
        }
    }

    public SmiType createDefinedType(IdToken idToken, Token moduleToken, Token referencedIdToken, List<SmiNamedNumber> namedNumbers, List<SmiRange> sizeConstraints, List<SmiRange> rangeConstraints, SmiSelect selectConstraints) {
        SmiReferencedType referencedType = new SmiReferencedType(this.idt(referencedIdToken), this.m_module);
        if (moduleToken != null) {
            referencedType.setReferencedModuleToken(this.idt(moduleToken));
        }

        referencedType.setNamedNumbers(namedNumbers);
        referencedType.setSizeConstraints(sizeConstraints);
        referencedType.setRangeConstraints(rangeConstraints);
        referencedType.setSelectContraints(selectConstraints);
        Object result;
        if (idToken != null) {
            result = new SmiType(idToken, this.m_module);
            ((SmiType)result).setBaseType(referencedType);
        } else {
            result = referencedType;
        }

        return (SmiType)result;
    }

    public SmiType createChoiceType(IdToken idToken) {
        return SmiProtocolType.createChoiceType(idToken, this.m_module);
    }

    public void addField(SmiType sequenceType, Token col, SmiType fieldType) {
        sequenceType.addField(this.idt(col), fieldType);
    }

    public void addReference(SmiOidMacro oidMacro, Token token) {
        oidMacro.addReference(token.getText());
    }

    public SmiType createSequenceOfType(Token elementTypeNameToken) {
        SmiType sequenceOfType = new SmiType((IdToken)null, this.m_module);
        sequenceOfType.setElementTypeToken(this.idt(elementTypeNameToken));
        return sequenceOfType;
    }

    public void addRange(List<SmiRange> rc, hanati.snmp.mibparser.util.token.Token rv1, hanati.snmp.mibparser.util.token.Token rv2) {
        SmiRange range;
        if (rv2 != null) {
            range = new SmiRange(rv1, rv2);
        } else {
            range = new SmiRange(rv1);
        }

        rc.add(range);
    }

    public BinaryStringToken bst(Token t) {
        return new BinaryStringToken(this.makeLocation(t), t.getText());
    }

    public HexStringToken hst(Token t) {
        return new HexStringToken(this.makeLocation(t), t.getText());
    }

    public QuotedStringToken dqst(Token t) {
        return new QuotedStringToken(this.makeLocation(t), t.getText(), '"');
    }

    public StringToken st(Token t) {
        return new StringToken(this.makeLocation(t), t.getText());
    }

    public void addSymbol(SmiSymbol symbol) {
        if (symbol != null) {
            this.m_module.addSymbol(symbol);
        }

    }

    public StatusV2 findStatusV2(String text) {
        this.m_module.incV2Features();
        return StatusV2.find(text, true);
    }

    public ScopedId makeScopedId(Token moduleToken, Token symbolToken) {
        return new ScopedId(this.m_module, moduleToken != null ? this.idt(moduleToken) : null, this.idt(symbolToken));
    }
}
