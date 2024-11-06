package hanati.snmp.mibparser.phase.file.antlr;

import antlr.LLkParser;
import antlr.NoViableAltException;
import antlr.ParserSharedInputState;
import antlr.RecognitionException;
import antlr.Token;
import antlr.TokenBuffer;
import antlr.TokenStream;
import antlr.TokenStreamException;
import antlr.collections.impl.BitSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import hanati.snmp.mibparser.phase.file.IntKeywordToken;
import hanati.snmp.mibparser.phase.file.ModuleParser;
import hanati.snmp.mibparser.smi.*;
import hanati.snmp.mibparser.util.location.Location;
import hanati.snmp.mibparser.util.token.*;

public class SMIParser extends LLkParser implements SMITokenTypes {
    private SmiMib m_mib;
    private String m_locationPath;
    private ModuleParser m_mp;
    public static final String[] _tokenNames = new String[]{"<0>", "EOF", "<2>", "NULL_TREE_LOOKAHEAD", "\"ABSENT\"", "\"ABSTRACT-SYNTAX\"", "\"ALL\"", "\"ANY\"", "\"ARGUMENT\"", "\"APPLICATION\"", "\"AUTOMATIC\"", "\"BASEDNUM\"", "\"BEGIN\"", "\"BIT\"", "\"BMPString\"", "\"BOOLEAN\"", "\"BY\"", "\"CHARACTER\"", "\"CHOICE\"", "\"CLASS\"", "\"COMPONENTS\"", "\"COMPONENT\"", "\"CONSTRAINED\"", "\"DEFAULT\"", "\"DEFINED\"", "\"DEFINITIONS\"", "\"EMBEDDED\"", "\"END\"", "\"ENUMERATED\"", "\"ERROR\"", "\"ERRORS\"", "\"EXCEPT\"", "\"EXPLICIT\"", "\"EXPORTS\"", "\"EXTENSIBILITY\"", "\"EXTERNAL\"", "\"FALSE\"", "\"FROM\"", "\"GeneralizedTime\"", "\"GeneralString\"", "\"GraphicString\"", "\"IA5String\"", "\"IDENTIFIER\"", "\"IMPLICIT\"", "\"IMPLIED\"", "\"IMPORTS\"", "\"INCLUDES\"", "\"INSTANCE\"", "\"INTEGER\"", "\"INTERSECTION\"", "\"ISO646String\"", "\"LINKED\"", "\"MAX\"", "\"MINUSINFINITY\"", "\"MIN\"", "\"NULL\"", "\"NumericString\"", "\"ObjectDescriptor\"", "\"OBJECT\"", "\"OCTET\"", "\"OPERATION\"", "\"OF\"", "\"OID\"", "\"OPTIONAL\"", "\"PARAMETER\"", "\"PDV\"", "\"PLUSINFINITY\"", "\"PRESENT\"", "\"PrintableString\"", "\"PRIVATE\"", "\"REAL\"", "\"RELATIVE\"", "\"RESULT\"", "\"SEQUENCE\"", "\"SET\"", "\"SIZE\"", "\"STRING\"", "\"TAGS\"", "\"TeletexString\"", "\"TRUE\"", "\"TYPE-IDENTIFIER\"", "\"UNION\"", "\"UNIQUE\"", "\"UNIVERSAL\"", "\"UniversalString\"", "\"UTCTime\"", "\"UTF8String\"", "\"VideotexString\"", "\"VisibleString\"", "\"WITH\"", "ASSIGN_OP", "BAR", "COLON", "COMMA", "COMMENT", "DOT", "DOTDOT", "ELLIPSIS", "EXCLAMATION", "INTERSECTION", "LESS", "L_BRACE", "L_BRACKET", "L_PAREN", "MINUS", "PLUS", "R_BRACE", "R_BRACKET", "R_PAREN", "SEMI", "SINGLE_QUOTE", "CHARB", "CHARH", "WS", "SMIC_DIRECTIVE", "INCLUDE", "SL_COMMENT", "NUMBER", "UPPER", "LOWER", "BDIG", "HDIG", "B_OR_H_STRING", "B_STRING", "H_STRING", "C_STRING", "\"OBJECT-TYPE\"", "\"MODULE-IDENTITY\"", "\"OBJECT-IDENTITY\"", "\"NOTIFICATION-TYPE\"", "\"TEXTUAL-CONVENTION\"", "\"OBJECT-GROUP\"", "\"NOTIFICATION-GROUP\"", "\"MODULE-COMPLIANCE\"", "\"AGENT-CAPABILITIES\"", "\"TRAP-TYPE\"", "\"MACRO\"", "\"BITS\"", "\"SYNTAX\"", "\"UNITS\"", "\"ACCESS\"", "\"MAX-ACCESS\"", "\"STATUS\"", "\"DESCRIPTION\"", "\"REFERENCE\"", "\"INDEX\"", "\"AUGMENTS\"", "\"DEFVAL\"", "\"LAST-UPDATED\"", "\"ORGANIZATION\"", "\"CONTACT-INFO\"", "\"REVISION\"", "\"OBJECTS\"", "\"DISPLAY-HINT\"", "\"NOTIFICATIONS\"", "\"MODULE\"", "\"MANDATORY-GROUPS\"", "\"GROUP\"", "\"WRITE-SYNTAX\"", "\"MIN-ACCESS\"", "\"PRODUCT-RELEASE\"", "\"SUPPORTS\"", "\"VARIATION\"", "\"CREATION-REQUIRES\"", "\"ENTERPRISE\"", "\"VARIABLES\""};
    public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
    public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
    public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
    public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
    public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
    public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());

    public void init(SmiMib mib, String locationPath) {
        this.m_mib = mib;
        this.m_locationPath = locationPath;
    }

    SmiModule beginModule(Token idToken) {
        if (this.m_mp != null) {
            throw new IllegalStateException("Module " + this.m_mp.getModule().getIdToken() + " is still being parsed when trying to create new module " + idToken);
        } else {
            SmiModule module = this.m_mib.createModule(this.idt(idToken));
            this.m_mp = new ModuleParser(module);
            return module;
        }
    }

    private void endModule() {
        if (this.m_mp == null) {
            throw new IllegalStateException("No module is being parsed");
        } else {
            this.m_mp = null;
        }
    }

    private Location makeLocation(Token token) {
        return new Location(this.m_locationPath, token.getLine(), token.getColumn());
    }

    private IdToken idt(Token idToken) {
        return new IdToken(this.makeLocation(idToken), idToken.getText());
    }

    protected SMIParser(TokenBuffer tokenBuf, int k) {
        super(tokenBuf, k);
        this.tokenNames = _tokenNames;
    }

    public SMIParser(TokenBuffer tokenBuf) {
        this((TokenBuffer)tokenBuf, 3);
    }

    protected SMIParser(TokenStream lexer, int k) {
        super(lexer, k);
        this.tokenNames = _tokenNames;
    }

    public SMIParser(TokenStream lexer) {
        this((TokenStream)lexer, 3);
    }

    public SMIParser(ParserSharedInputState state) {
        super(state, 3);
        this.tokenNames = _tokenNames;
    }

    public final SmiModule module_definition() throws RecognitionException, TokenStreamException {
        SmiModule result = null;

        switch (this.LA(1)) {
            case 1:
                this.match(1);
                break;
            case 118:
            case 119:
                result = this.module_identifier();

                if(this.LA(1)==101){
                    this.consumeUntil(106);
                    this.consume();
                }

                this.match(25); // DEFINITIONS

                if(this.LA(1) == 43) { // IMPLICIT
                    this.consumeUntil(90); // TAGS
                }

                this.match(90); // ::=
                this.match(12); // BEGIN

                if(this.LA(1)==200) {
                    this.consumeUntil(201);
                }

                this.module_body();
                this.match(27);
                if (this.inputState.guessing == 0) {
                    this.endModule();
                }
                break;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }

        return result;
    }

    public final SmiModule module_identifier() throws RecognitionException, TokenStreamException {
        SmiModule result = null;
        Token u = null;
        Token l = null;
        switch (this.LA(1)) {
            case 118:
                u = this.LT(1);
                this.match(118);
                break;
            case 119:
                l = this.LT(1);
                this.match(119);
                break;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }

        if (this.inputState.guessing == 0) {
            result = this.beginModule(u != null ? u : l);
        }

        return result;
    }

    public final void module_body() throws RecognitionException, TokenStreamException {
        label18:
        switch (this.LA(1)) {
            case 33:
                this.exports();
            case 27:
            case 45:
            case 118:
            case 119:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
                switch (this.LA(1)) {
                    case 27:
                    case 118:
                    case 119:
                    case 126:
                    case 127:
                    case 128:
                    case 129:
                    case 130:
                    case 131:
                    case 132:
                    case 133:
                    case 134:
                    case 135:
                        break label18;
                    case 45:
                        this.imports();
                        break label18;
                    default:
                        throw new NoViableAltException(this.LT(1), this.getFilename());
                }
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }

        while(_tokenSet_0.member(this.LA(1)) || this.LA(1)==85 || this.LA(1)==86) {
            this.assignment();
        }

    }

    public final void exports() throws RecognitionException, TokenStreamException {
        this.match(33);
        this.symbol_list();
        this.match(109);
    }

    public final void imports() throws RecognitionException, TokenStreamException {
        this.match(45);

        while(_tokenSet_0.member(this.LA(1)) || this.LA(1)==86 ) {
            this.symbols_from_module();
        }

        this.match(109);
    }

    public final SmiSymbol assignment() throws RecognitionException, TokenStreamException {
        SmiSymbol s = null;
        Token u = null;
        Token l = null;
        IdToken intToken = null;
        SmiType type = null;
        IdToken mn = null;

        Token cv = null;

        switch (this.LA(1)) {
            case 85:
                u = this.LT(1);
                this.match(85);
                this.match(90);
                s = this.type_assignment(this.m_mp.idt(u));
                break;
            case 86:
                u = this.LT(1);
                this.match(86);
                this.match(90);
                s = this.type_assignment(this.m_mp.idt(u));
                break;
            case 118:
                u = this.LT(1);
                this.match(118);

                if(u.getType()==118 && this.LA(1)!=90) {
                    this.match(126);
                    s = this.value_assignment(this.m_mp.idt(u));
                    break;
                }

                this.match(90);
                s = this.type_assignment(this.m_mp.idt(u));
                break;
            case 119:
                l = this.LT(1);
                this.match(119);
                s = this.value_assignment(this.m_mp.idt(l));
                break;
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
                mn = this.macroName();
                this.match(136);
                this.match(90);
                this.match(12);

                while(_tokenSet_1.member(this.LA(1))) {
                    this.match(_tokenSet_1);
                }

                this.match(27);
                if (this.inputState.guessing == 0) {
                    s = this.m_mp.createMacro(mn);
                }
            case 199:
                cv = this.LT(1);

        }

        if (this.inputState.guessing == 0) {
            this.m_mp.addSymbol((SmiSymbol)s);
        }

        return (SmiSymbol)s;
    }

    public final List<IdToken> symbol_list() throws RecognitionException, TokenStreamException {
        List<IdToken> result = this.m_mp.makeIdTokenList();
        IdToken s1 = null;
        IdToken s2 = null;
        s1 = this.symbol();
        if (this.inputState.guessing == 0) {
            result.add(s1);
        }

        while(this.LA(1) == 93) {
            this.match(93);
            s2 = this.symbol();
            if (this.inputState.guessing == 0) {
                result.add(s2);
            }
        }

        return result;
    }

    public final void symbols_from_module() throws RecognitionException, TokenStreamException {
        List<IdToken> idTokenList = null;
        IdToken m = null;
        idTokenList = this.symbol_list();
        this.match(37);
        m = this.upper();
        if (this.inputState.guessing == 0) {
            this.m_mp.addImports(m, idTokenList);
        }

    }

    public final IdToken upper() throws RecognitionException, TokenStreamException {
        IdToken result = null;
        Token u = null;
        u = this.LT(1);

        if(u.getType()==86) {
            this.match(86);
        } else {
            this.match(118);
        }

        if (this.inputState.guessing == 0) {
            result = this.m_mp.idt(u);
        }

        return result;
    }

    public final IdToken symbol() throws RecognitionException, TokenStreamException {
        IdToken result = null;
        int i = this.LA(1);
        switch (i) {
            case 86:
            case 118:
                result = this.upper();
                break;
            case 119:
                result = this.lower();
                break;
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
                result = this.macroName();
        }

        return result;
    }

    public final IdToken lower() throws RecognitionException, TokenStreamException {
        IdToken result = null;
        Token l = null;

        l = this.LT(1);

        if(l.getType()==199) {
            this.match(199);
            if (this.inputState.guessing == 0) {
                result = this.m_mp.idt(l);
            }
        } else {
            this.match(119);
            if (this.inputState.guessing == 0) {
                result = this.m_mp.idt(l);
            }
        }

        return result;
    }

    public final IdToken macroName() throws RecognitionException, TokenStreamException {
        IdToken result = null;
        Token ot = null;
        Token mi = null;
        Token oi = null;
        Token nt = null;
        Token tc = null;
        Token og = null;
        Token ng = null;
        Token mc = null;
        Token ac = null;
        Token tt = null;
        switch (this.LA(1)) {
            case 126:
                ot = this.LT(1);
                this.match(126);
                break;
            case 127:
                mi = this.LT(1);
                this.match(127);
                if (this.inputState.guessing == 0) {
                    this.m_mp.getModule().incV2Features();
                }
                break;
            case 128:
                oi = this.LT(1);
                this.match(128);
                if (this.inputState.guessing == 0) {
                    this.m_mp.getModule().incV2Features();
                }
                break;
            case 129:
                nt = this.LT(1);
                this.match(129);
                if (this.inputState.guessing == 0) {
                    this.m_mp.getModule().incV2Features();
                }
                break;
            case 130:
                tc = this.LT(1);
                this.match(130);
                if (this.inputState.guessing == 0) {
                    this.m_mp.getModule().incV2Features();
                }
                break;
            case 131:
                og = this.LT(1);
                this.match(131);
                if (this.inputState.guessing == 0) {
                    this.m_mp.getModule().incV2Features();
                }
                break;
            case 132:
                ng = this.LT(1);
                this.match(132);
                if (this.inputState.guessing == 0) {
                    this.m_mp.getModule().incV2Features();
                }
                break;
            case 133:
                mc = this.LT(1);
                this.match(133);
                if (this.inputState.guessing == 0) {
                    this.m_mp.getModule().incV2Features();
                }
                break;
            case 134:
                ac = this.LT(1);
                this.match(134);
                if (this.inputState.guessing == 0) {
                    this.m_mp.getModule().incV2Features();
                }
                break;
            case 135:
                tt = this.LT(1);
                this.match(135);
                if (this.inputState.guessing == 0) {
                    this.m_mp.getModule().incV1Features();
                }
                break;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }

        if (this.inputState.guessing == 0) {
            result = this.m_mp.idt(new Token[]{ot, mi, oi, nt, tc, og, ng, mc, ac, tt});
        }

        return result;
    }

    public final SmiType type_assignment(IdToken idToken) throws RecognitionException, TokenStreamException {
        SmiType t = null;
        switch (this.LA(1)) {
            case 18:
                t = this.choice_type(idToken);
                break;
            case 48:
            case 58:
            case 59:
            case 102:
            case 118:
            case 137:
                t = this.leaf_type(idToken);
                break;
            case 73:
                t = this.sequence_type(idToken);
                break;
            case 130:
                t = this.textualconvention_macro(idToken);
                break;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }

        return (SmiType)t;
    }

    public final SmiValue value_assignment(IdToken idToken) throws RecognitionException, TokenStreamException {
        SmiValue v = null;

        switch (this.LA(1)) {
            case 58:
                v = this.oid_value_assignment(idToken);
                break;
            case 126:
            case 127:
            case 128:
            case 129:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 138:
                v = this.macro_value_assignment(idToken);
                break;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }

        return (SmiValue)v;
    }

    public final SmiTextualConvention textualconvention_macro(IdToken idToken) throws RecognitionException, TokenStreamException {
        SmiTextualConvention tc = null;
        Token displayHint = null;    Token description = null;
        Token reference = null;
        this.match(130);
        switch (this.LA(1)) {
            case 153:
                this.match(153);
                displayHint = this.LT(1);
                this.match(125);
            case 142:
                this.match(142);
                StatusV2 status = this.status_v2();
                this.match(143);
                description = this.LT(1);
                this.match(125);
                switch (this.LA(1)) {
                    case 144:
                        this.match(144);
                        reference = this.LT(1);
                        this.match(125);
                    case 138:
                        this.match(138);
                        SmiType type = this.leaf_type((IdToken)null);
                        if (this.inputState.guessing == 0) {
                            tc = this.m_mp.createTextualConvention(idToken, displayHint, status, description, reference, type);
                        }

                        return tc;
                    default:
                        throw new NoViableAltException(this.LT(1), this.getFilename());
                }
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final SmiType leaf_type(IdToken idToken) throws RecognitionException, TokenStreamException {
        SmiType t = null;
        Token n = null;
        switch (this.LA(1)) {
            case 102:
                this.match(102);
                this.match(9);
                n = this.LT(1);
                this.match(117);
                this.match(107);
                this.match(43);
            case 13:
            case 48:
            case 58:
            case 59:
            case 86:
            case 118:
            case 137:
                int i = this.LA(1);
                switch (i) {
                    case 13:
                        t = this.bit_string_type(idToken, n);
                        break;
                    case 48:
                        t = this.integer_type(idToken, n);
                        break;
                    case 58:
                        t = this.oid_type(idToken);
                        break;
                    case 59:
                        t = this.octet_string_type(idToken, n);
                        break;
                    case 86:
                    case 118:
                        t = this.defined_type(idToken);
                        break;
                    case 137:
                        t = this.bits_type(idToken);
                        break;
                    default:
                        throw new NoViableAltException(this.LT(1), this.getFilename());
                }

                return t;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final SmiType sequence_type(IdToken idToken) throws RecognitionException, TokenStreamException {
        SmiType t = null;
        this.match(73);
        if (this.inputState.guessing == 0) {
            t = this.m_mp.createSequenceType(idToken);
        }

        this.match(101);
        this.sequence_field(t);

        while(this.LA(1) == 93) {
            this.match(93);
            this.sequence_field(t);
        }

        this.match(106);
        return t;
    }

    public final SmiType choice_type(IdToken idToken) throws RecognitionException, TokenStreamException {
        SmiType t = null;
        this.match(18);
        this.match(101);

        while(_tokenSet_2.member(this.LA(1))) {
            this.match(_tokenSet_2);
        }

        this.match(106);
        return (SmiType)(this.inputState.guessing == 0 ? this.m_mp.createChoiceType(idToken) : t);
    }

    public final SmiType integer_type(IdToken idToken, Token applicationTagToken) throws RecognitionException, TokenStreamException {
        SmiType t = null;
        List<SmiNamedNumber> namedNumbers = null;
        List<SmiRange> rangeConstraints = null;
        IntKeywordToken intToken = this.integer_type_kw(idToken);

        switch (this.LA(1)) {
            case 27:
            case 93:
            case 106:
            case 118:
            case 119:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 147:
            case 158:
            case 159:
            case 163:
                break;
            case 101:
                namedNumbers = this.named_number_list();
                break;
            case 103:
                rangeConstraints = this.range_constraint();
                break;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }

        if (this.inputState.guessing == 0) {
            t = this.m_mp.createIntegerType(idToken, intToken, applicationTagToken, namedNumbers, rangeConstraints);
        }

        return t;
    }

    public final SmiType oid_type(IdToken idToken) throws RecognitionException, TokenStreamException {
        SmiType t = null;
        this.match(58);
        this.match(42);
        if (this.inputState.guessing == 0) {
            t = this.m_mp.createType(idToken, SmiConstants.OBJECT_IDENTIFIER_TYPE);
        }

        return t;
    }

    public final SmiType octet_string_type(IdToken idToken, Token applicationTagToken) throws RecognitionException, TokenStreamException {
        SmiType type = null;
        List<SmiRange> sizeConstraints = null;
        this.match(59);
        this.match(76);
        switch (this.LA(1)) {
            case 103:
                sizeConstraints = this.size_constraint();
            case 27:
            case 93:
            case 106:
            case 118:
            case 119:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 147:
            case 158:
            case 159:
            case 163:
                if (this.inputState.guessing == 0) {
                    type = this.m_mp.createOctetStringType(idToken, applicationTagToken, sizeConstraints);
                }

                return type;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final SmiType bit_string_type(IdToken idToken, Token applicationTagToken) throws RecognitionException, TokenStreamException {
        SmiType type = null;
        List<SmiNamedNumber> namedNumbers = null;
        this.match(13);
        this.match(76);
        switch (this.LA(1)) {
            case 101:
                namedNumbers = this.named_number_list();
            case 27:
            case 93:
            case 106:
            case 118:
            case 119:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 147:
            case 158:
            case 159:
            case 163:
                if (this.inputState.guessing == 0) {
                    type = this.m_mp.createBitStringType(idToken, applicationTagToken, namedNumbers);
                }

                return type;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final SmiType bits_type(IdToken idToken) throws RecognitionException, TokenStreamException {
        SmiType type = null;
        List<SmiNamedNumber> namedNumbers = null;
        this.match(137);
        switch (this.LA(1)) {
            case 101:
                namedNumbers = this.named_number_list();
            case 27:
            case 93:
            case 106:
            case 118:
            case 119:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 147:
            case 158:
            case 159:
            case 163:
                if (this.inputState.guessing == 0) {
                    type = this.m_mp.createBitsType(idToken, namedNumbers);
                }

                return type;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final SmiType defined_type(IdToken idToken) throws RecognitionException, TokenStreamException {
        SmiType type = null;
        Token mt = null;
        Token tt = null;
        List<SmiNamedNumber> namedNumbers = null;
        List<SmiRange> sizeConstraints = null;
        List<SmiRange> rangeConstraints = null;
        SmiSelect selectConstraints = null;
        if (this.LA(1) == 118 && this.LA(2) == 95) {
            mt = this.LT(1);
            this.match(118);
            this.match(95);
        } else if ( (this.LA(1) != 118 || !_tokenSet_3.member(this.LA(2))) && this.LA(1)!=86 ) {
            throw new NoViableAltException(this.LT(1), this.getFilename());
        }

        tt = this.LT(1);
        if(this.LA(1)==86) {
            this.match(86);
        } else {
            this.match(118);
        }
        switch (this.LA(1)) {
            case 27:
            case 93:
            case 106:
            case 118:
            case 119:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 147:
            case 158:
            case 159:
            case 163:
                break;
            case 101:
                namedNumbers = this.named_number_list();
                break;
            default:
                if (this.LA(1) == 103 && this.LA(2) == 75) {
                    sizeConstraints = this.size_constraint();
                } else if (this.LA(1)==103 && this.LA(2)==119 && this.LA(3)==91) {
                    selectConstraints = this.select_constraint();
                } else {
                    if (this.LA(1) != 103 || !_tokenSet_4.member(this.LA(2))) {
                        throw new NoViableAltException(this.LT(1), this.getFilename());
                    }

                    rangeConstraints = this.range_constraint();
                }
        }

        if (this.inputState.guessing == 0) {
            type = this.m_mp.createDefinedType(idToken, mt, tt, namedNumbers, sizeConstraints, rangeConstraints, selectConstraints);
        }

        return type;
    }

    public final IntKeywordToken integer_type_kw(IdToken idToken) throws RecognitionException, TokenStreamException {
        IntKeywordToken t = null;
        Token i = null;
        i = this.LT(1);
        this.match(48);
        if (this.inputState.guessing == 0) {
            t = this.m_mp.intkt(i, SmiPrimitiveType.INTEGER, (SmiVersion)null);
        }

        return t;
    }

    public final List<SmiNamedNumber> named_number_list() throws RecognitionException, TokenStreamException {
        List<SmiNamedNumber> l = new ArrayList();
        this.match(101);
        this.named_number(l);

        while(this.LA(1) == 93) {
            this.match(93);
            this.named_number(l);
        }

        this.match(106);
        return l;
    }

    public final List<SmiRange> range_constraint() throws RecognitionException, TokenStreamException {
        List<SmiRange> rc = null;
        this.match(103);
        if (this.inputState.guessing == 0) {
            rc = new ArrayList();
        }

        this.range(rc);

        while(this.LA(1) == 91) {
            this.match(91);
            this.range(rc);
        }

        this.match(108);
        return rc;
    }

    public final List<SmiRange> size_constraint() throws RecognitionException, TokenStreamException {
        List<SmiRange> rc = null;

        if(this.LA(2)==75) {
            this.match(103);
            this.match(75);
        }
        rc = this.range_constraint();

        if(this.LA(1)==108) {
            this.match(108);
        }

        return rc;
    }

    public final SmiSelect select_constraint() throws RecognitionException, TokenStreamException {
        SmiSelect ss = null;
        this.match(103); // '('
        ss = select_process();
        this.match(108);
        return ss;
    }

    public final SmiSelect select_process() throws TokenStreamException, RecognitionException {
        SmiSelect ss = null;
        hanati.snmp.mibparser.util.token.Token first = null;
        hanati.snmp.mibparser.util.token.Token second = null;
        hanati.snmp.mibparser.util.token.Token third = null;
        hanati.snmp.mibparser.util.token.Token fourth = null;

        first = this.range_value();
        switch (this.LA(1)) {
            case 91: // '|'
                this.match(91);
                second = this.range_value();
                switch (this.LA(1)) {
                    case 91:
                        this.match(91);
                        third = this.range_value();
                        switch (this.LA(1)) {
                            case 91:
                                this.match(91);
                                fourth = this.range_value();
                                switch (this.LA(1)) {
                                    case 108:
                                        if(this.inputState.guessing==0) {
                                            ss = new SmiSelect(first, second, third, fourth);
                                        }
                                        return ss;
                                    default:
                                        throw new NoViableAltException(this.LT(1), this.getFilename());
                                }
                            case 108:
                                if(this.inputState.guessing==0) {
                                    ss = new SmiSelect(first, second, third);
                                }
                                return ss;
                            default:
                                throw new NoViableAltException(this.LT(1), this.getFilename());
                        }
                    case 108:
                        if(this.inputState.guessing==0) {
                            ss = new SmiSelect(first, second);
                        }
                        return ss;
                    default:
                        throw new NoViableAltException(this.LT(1), this.getFilename());
                }
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final void sequence_field(SmiType sequenceType) throws RecognitionException, TokenStreamException {
        Token l = null;
        SmiType fieldType = null;
        l = this.LT(1);
        this.match(119);
        fieldType = this.leaf_type((IdToken)null);
        if (this.inputState.guessing == 0) {
            this.m_mp.addField(sequenceType, l, fieldType);
        }

    }

    public final SmiType sequenceof_type() throws RecognitionException, TokenStreamException {
        SmiType t = null;
        Token u = null;
        this.match(73);
        this.match(61);
        u = this.LT(1);

        if(u.getType() == 59){ // OCTET
            t = this.octet_string_type((IdToken)null, (Token)null);
        } else {
            this.match(118);
            if (this.inputState.guessing == 0) {
                t = this.m_mp.createSequenceOfType(u);
            }
        }


        return t;
    }

    public final void range(List<SmiRange> rc) throws RecognitionException, TokenStreamException {
        hanati.snmp.mibparser.util.token.Token rv1 = null;
        hanati.snmp.mibparser.util.token.Token rv2 = null;
        rv1 = this.range_value();
        switch (this.LA(1)) {
            case 96:
                this.match(96);
                rv2 = this.range_value();
            case 91:
            case 108:
                if (this.inputState.guessing == 0) {
                    this.m_mp.addRange(rc, rv1, rv2);
                }

                return;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final hanati.snmp.mibparser.util.token.Token range_value() throws RecognitionException, TokenStreamException {
        hanati.snmp.mibparser.util.token.Token t = null;
        switch (this.LA(1)) {
            case 52:
            case 118:
            case 119:
                t= this.max_value_token();
                break;
            case 104:
            case 117:
                t = this.big_integer_token();
                break;
            case 123:
                t = this.binary_string_token();
                break;
            case 124:
                t = this.hex_string_token();
                break;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }

        return (hanati.snmp.mibparser.util.token.Token)t;
    }

    public final BigIntegerToken big_integer_token() throws RecognitionException, TokenStreamException {
        BigIntegerToken bit = null;
        Token mt = null;
        Token nt = null;
        switch (this.LA(1)) {
            case 104:
                mt = this.LT(1);
                this.match(104);
            case 117:
                nt = this.LT(1);
                this.match(117);
                if (this.inputState.guessing == 0) {
                    bit = this.m_mp.bintt(mt, nt);
                }

                return bit;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final BinaryStringToken binary_string_token() throws RecognitionException, TokenStreamException {
        BinaryStringToken t = null;
        Token bt = null;
        bt = this.LT(1);
        this.match(123);
        if (this.inputState.guessing == 0) {
            t = this.m_mp.bst(bt);
        }

        return t;
    }

    public final HexStringToken hex_string_token() throws RecognitionException, TokenStreamException {
        HexStringToken t = null;
        Token ht = null;
        ht = this.LT(1);
        this.match(124);
        if (this.inputState.guessing == 0) {
            t = this.m_mp.hst(ht);
        }

        return t;
    }

    public final StringToken max_value_token() throws RecognitionException, TokenStreamException {
        StringToken t = null;
        Token st = null;
        st = this.LT(1);
        switch(this.LA(1)) {
            case 52:
                this.match(52);
                break;
            case 118:
                this.match(118);
                break;
            case 119:
                this.match(119);
                break;
        }
        if (this.inputState.guessing == 0) {
            t = this.m_mp.st(st);
        }

        return t;
    }

    public final SmiValue macro_value_assignment(IdToken idToken) throws RecognitionException, TokenStreamException {
        SmiValue v = null;
        switch (this.LA(1)) {
            case 126:
            case 127:
            case 128:
            case 129:
            case 131:
            case 132:
            case 133:
            case 134:
            case 138:
                v = this.oid_macro_value_assignment(idToken);
                break;
            case 130:
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
            case 135:
                this.int_macro_value_assignment();
        }

        return v;
    }

    public final SmiOidValue oid_value_assignment(IdToken idToken) throws RecognitionException, TokenStreamException {
        SmiOidValue v = null;
        OidComponent last = null;
        this.match(58);
        this.match(42);
        this.match(90);
        last = this.oid_sequence(idToken);
        if (this.inputState.guessing == 0) {
            v = this.m_mp.createOidValue(idToken, last);
        }

        return v;
    }

    public final OidComponent oid_sequence(IdToken idToken) throws RecognitionException, TokenStreamException {
        OidComponent last = null;
        this.match(101);

        int _cnt149;
        for(_cnt149 = 0; this.LA(1) == 117 || this.LA(1) == 119; ++_cnt149) {
            last = this.oid_component(last);
        }

        if (_cnt149 >= 1) {
            this.match(106);
            return last;
        } else {
            throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final SmiOidMacro oid_macro_value_assignment(IdToken idToken) throws RecognitionException, TokenStreamException {
        SmiOidMacro v = null;
        OidComponent lastOidComponent = null;

        switch (this.LA(1)) {
            case 138:
            case 126:
                v = this.objecttype_macro(idToken);
                break;
            case 127:
                this.moduleidentity_macro();
                break;
            case 128:
                this.objectidentity_macro();
                break;
            case 129:
                v = this.notificationtype_macro(idToken);
                break;
            case 130:
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
            case 131:
                this.objectgroup_macro();
                break;
            case 132:
                this.notificationgroup_macro();
                break;
            case 133:
                this.modulecompliance_macro();
                break;
            case 134:
                this.agentcapabilities_macro();
        }

        this.match(90);
        lastOidComponent = this.oid_sequence(idToken);
        if (this.inputState.guessing == 0) {
            if (v == null) {
                v = this.m_mp.createOidMacro(idToken);
            }

            ((SmiOidMacro)v).setLastOidComponent(lastOidComponent);
        }

        return (SmiOidMacro)v;
    }

    public final void int_macro_value_assignment() throws RecognitionException, TokenStreamException {
        this.traptype_macro();
        this.match(90);
        this.match(117);
    }

    public final SmiObjectType objecttype_macro(IdToken idToken) throws RecognitionException, TokenStreamException {
        SmiObjectType ot = null;
        Token units = null;
        Token access = null;
        Token maxAccess = null;
        Token desc = null;
        SmiType sequenceOfType = null;
        SmiType type = null;
        SmiVariable var = null;
        SmiRow row = null;
        SmiTable table = null;
        StatusAll status = null;
        SmiDefaultValue defaultValue = null;

        if(this.LA(0)!=126) {
            this.match(126);
        }

        this.match(138);

        switch (this.LA(1)) {
            case 13:
            case 48:
            case 58:
            case 59:
            case 86:
            case 102:
            case 118:
            case 137:
                type = this.leaf_type((IdToken)null);
                break;
            case 73:
                sequenceOfType = this.sequenceof_type();
                break;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }

        switch (this.LA(1)) {
            case 139:
                this.match(139);
                units = this.LT(1);
                this.match(125);
            case 140:
            case 141:
            case 142:
                switch (this.LA(1)) {
                    case 140:
                        this.match(140);
                        access = this.LT(1);
                        this.match(119);
                        break;
                    case 141:
                        this.match(141);
                        maxAccess = this.LT(1);
                        this.match(119);
                    case 142:
                        break;
                    default:
                        throw new NoViableAltException(this.LT(1), this.getFilename());
                }

                this.match(142);
                status = this.status_all();
                switch (this.LA(1)) {
                    case 143:
                        this.match(143);
                        desc = this.LT(1);
                        this.match(125);
                    case 90:
                    case 144:
                    case 145:
                    case 146:
                    case 147:
                        switch (this.LA(1)) {
                            case 144:
                                this.match(144);
                                this.match(125);
                            case 90:
                            case 145:
                            case 146:
                            case 147:
                                switch (this.LA(1)) {
                                    case 145:
                                    case 146:
                                        switch (this.LA(1)) {
                                            case 145:
                                                this.match(145);
                                                row = this.objecttype_macro_index(idToken, type);
                                                break;
                                            case 146:
                                                this.match(146);
                                                row = this.objecttype_macro_augments(idToken, type);
                                                break;
                                            default:
                                                throw new NoViableAltException(this.LT(1), this.getFilename());
                                        }
                                    case 90:
                                    case 147:
                                        switch (this.LA(1)) {
                                            case 147:
                                                this.match(147);
                                                this.match(101);
                                                defaultValue = this.leaf_value();
                                                this.match(106);
                                            case 90:
                                                if (this.inputState.guessing == 0) {
                                                    if (sequenceOfType != null) {
                                                        ot = this.m_mp.createTable(idToken, sequenceOfType);
                                                    } else if (row != null) {
                                                        ot = row;
                                                    } else {
                                                        ot = this.m_mp.createVariable(idToken, type, units, defaultValue);
                                                    }

                                                    if (access != null) {
                                                        ((SmiObjectType)ot).setAccessToken(this.m_mp.idt(access));
                                                    } else {
                                                        ((SmiObjectType)ot).setMaxAccessToken(this.m_mp.idt(maxAccess));
                                                    }

                                                    ((SmiObjectType)ot).setStatus(status);
                                                    ((SmiObjectType)ot).setDescription(this.m_mp.getOptCStr(desc));
                                                }

                                                return (SmiObjectType)ot;
                                            default:
                                                throw new NoViableAltException(this.LT(1), this.getFilename());
                                        }
                                    default:
                                        throw new NoViableAltException(this.LT(1), this.getFilename());
                                }
                            default:
                                throw new NoViableAltException(this.LT(1), this.getFilename());
                        }
                    default:
                        throw new NoViableAltException(this.LT(1), this.getFilename());
                }
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final void moduleidentity_macro() throws RecognitionException, TokenStreamException {
        this.match(127);
        this.match(148);
        this.match(125);
        this.match(149);
        this.match(125);
        this.match(150);
        this.match(125);
        this.match(143);
        this.match(125);

        while(this.LA(1) == 151) {
            this.moduleidentity_macro_revision();
        }

    }

    public final void objectidentity_macro() throws RecognitionException, TokenStreamException {
        this.match(128);
        this.match(142);
        this.status_v2();
        this.match(143);
        this.match(125);
        switch (this.LA(1)) {
            case 144:
                this.match(144);
                this.match(125);
            case 90:
                return;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final SmiOidMacro notificationtype_macro(IdToken idToken) throws RecognitionException, TokenStreamException {
        SmiOidMacro v = null;
        v = this.m_mp.createOidMacro(idToken);
        this.match(129);
        switch (this.LA(1)) {
            case 152:
                this.match(152);
                this.match(101);
                this.notification_reference(v);

                while(this.LA(1) == 93) {
                    this.match(93);
                    this.notification_reference(v);
                }

                this.match(106);
            case 142:
                this.match(142);
                this.status_v2();
                this.match(143);
                this.match(125);
                switch (this.LA(1)) {
                    case 144:
                        this.match(144);
                        this.match(125);
                    case 90:
                        if (this.inputState.guessing == 0) {
                            v.addUserData("objectType", "NOTIFICATION-TYPE");
                        }

                        return v;
                    default:
                        throw new NoViableAltException(this.LT(1), this.getFilename());
                }
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final void objectgroup_macro() throws RecognitionException, TokenStreamException {
        this.match(131);
        this.match(152);
        this.match(101);
        this.match(119);

        while(this.LA(1) == 93) {
            this.match(93);
            this.match(119);
        }

        this.match(106);
        this.match(142);
        this.status_v2();
        this.match(143);
        this.match(125);
        switch (this.LA(1)) {
            case 144:
                this.match(144);
                this.match(125);
            case 90:
                return;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final void notificationgroup_macro() throws RecognitionException, TokenStreamException {
        this.match(132);
        this.match(154);
        this.match(101);
        this.match(119);

        while(this.LA(1) == 93) {
            this.match(93);
            this.match(119);
        }

        this.match(106);
        this.match(142);
        this.status_v2();
        this.match(143);
        this.match(125);
        switch (this.LA(1)) {
            case 144:
                this.match(144);
                this.match(125);
            case 90:
                return;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final void modulecompliance_macro() throws RecognitionException, TokenStreamException {
        this.match(133);
        this.match(142);
        this.status_v2();
        this.match(143);
        this.match(125);
        switch (this.LA(1)) {
            case 144:
                this.match(144);
                this.match(125);
            case 155:
                int _cnt204;
                for(_cnt204 = 0; this.LA(1) == 155; ++_cnt204) {
                    this.modulecompliance_macro_module();
                }

                if (_cnt204 >= 1) {
                    return;
                } else {
                    throw new NoViableAltException(this.LT(1), this.getFilename());
                }
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final void agentcapabilities_macro() throws RecognitionException, TokenStreamException {
        this.match(134);
        this.match(160);
        this.match(125);
        this.match(142);
        this.agentcapabilities_status();
        this.match(143);
        this.match(125);
        switch (this.LA(1)) {
            case 90:
            case 161:
                break;
            case 144:
                this.match(144);
                this.match(125);
                break;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }

        while(this.LA(1) == 161) {
            this.agentcapabilities_macro_module();
        }

    }

    public final void traptype_macro() throws RecognitionException, TokenStreamException {
        this.match(135);
        this.match(164);

        // enterprise { 처리
        if(this.LA(1)==101) {
            this.consume();
        }

        this.match(119);

        // enterprise ... } 처리
        if(this.LA(1)==106) {
            this.consume();
        }

        switch (this.LA(1)) {
            case 165:
                this.match(165);
                this.match(101);
                this.match(119);

                while(this.LA(1) == 93) {
                    this.match(93);
                    this.match(119);

                    if(this.LA(1)==200) {
                        this.consumeUntil(201);
                        this.match(201);
                    }
                }

                this.match(106);
            case 90:
            case 143:
            case 144:
                switch (this.LA(1)) {
                    case 143:
                        this.match(143);
                        this.match(125);
                    case 90:
                    case 144:
                        switch (this.LA(1)) {
                            case 144:
                                this.match(144);
                                this.match(125);
                            case 90:
                                return;
                            default:
                                throw new NoViableAltException(this.LT(1), this.getFilename());
                        }
                    default:
                        throw new NoViableAltException(this.LT(1), this.getFilename());
                }
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final SmiDefaultValue leaf_value() throws RecognitionException, TokenStreamException {
        SmiDefaultValue result;
        BigIntegerToken bit;
        List bitsIdTokenList;
        OidComponent lastOidComponent;
        BinaryStringToken bst;
        HexStringToken hst;
        QuotedStringToken qst;
        ScopedId scopedId;
        boolean isNullValue;
        Boolean logicalValue;
        label68: {
            result = null;
            bit = null;
            bitsIdTokenList = null;
            lastOidComponent = null;
            bst = null;
            hst = null;
            qst = null;
            scopedId = null;
            isNullValue = false;
            logicalValue = null;

            switch (this.LA(1)) {
                case 36:
                    this.match(36);
                    if (this.inputState.guessing == 0) {
                        logicalValue = false;
                    }
                    break label68;
                case 79:
                    this.match(79);
                    if (this.inputState.guessing == 0) {
                        logicalValue = true;
                    }
                    break label68;
                case 55:
                    this.match(55);
                    if (this.inputState.guessing == 0) {
                        isNullValue = true;
                    }
                    break label68;
                case 104:
                case 117:
                    bit = this.big_integer_token();
                    break label68;
                case 118:
                case 119:
                    scopedId = this.defined_value();
                    break label68;
                case 123:
                    bst = this.binary_string_token();
                    break label68;
                case 124:
                    hst = this.hex_string_token();
                    break label68;
                case 125:
                    qst = this.double_quoted_string_token();
                    break label68;
            }

            boolean synPredMatched146 = false;
            if (this.LA(1) == 101 && (this.LA(2) == 106 || this.LA(2) == 119) && (this.LA(3) == 93 || this.LA(3) == 106)) {
                int _m146 = this.mark();
                synPredMatched146 = true;
                ++this.inputState.guessing;

                try {
                    this.bits_value();
                } catch (RecognitionException var13) {
                    synPredMatched146 = false;
                }

                this.rewind(_m146);
                --this.inputState.guessing;
            }

            if (synPredMatched146) {
                bitsIdTokenList = this.bits_value();
            } else {
                if (this.LA(1) != 101 || this.LA(2) != 117 && this.LA(2) != 119 || !_tokenSet_5.member(this.LA(3))) {
                    throw new NoViableAltException(this.LT(1), this.getFilename());
                }

                lastOidComponent = this.oid_sequence((IdToken)null);
            }
        }

        if (this.inputState.guessing == 0) {
            result = new SmiDefaultValue(this.m_mp.getModule(), bit, bitsIdTokenList, lastOidComponent, bst, hst, qst, scopedId, isNullValue, logicalValue);
        }

        return result;
    }

    public final List<IdToken> bits_value() throws RecognitionException, TokenStreamException {
        List<IdToken> result = new ArrayList();
        Token l1 = null;
        Token l2 = null;
        this.match(101);
        switch (this.LA(1)) {
            case 119:
                l1 = this.LT(1);
                this.match(119);
                if (this.inputState.guessing == 0) {
                    result.add(this.idt(l1));
                }

                while(this.LA(1) == 93) {
                    this.match(93);
                    l2 = this.LT(1);
                    this.match(119);
                    if (this.inputState.guessing == 0) {
                        result.add(this.idt(l2));
                    }
                }
            case 106:
                this.match(106);
                return result;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final QuotedStringToken double_quoted_string_token() throws RecognitionException, TokenStreamException {
        QuotedStringToken t = null;
        Token ct = null;
        ct = this.LT(1);
        this.match(125);
        if (this.inputState.guessing == 0) {
            t = this.m_mp.dqst(ct);
        }

        return t;
    }

    public final ScopedId defined_value() throws RecognitionException, TokenStreamException {
        ScopedId id = null;
        Token u = null;
        Token l = null;
        Token c = null;

        switch (this.LA(1)) {
            case 118: // UPPER
                u = this.LT(1);
                this.match(118);
                this.match(95);
            case 119: // LOWER
                l = this.LT(1);
                this.match(119);
                if (this.inputState.guessing == 0) {
                    id = this.m_mp.makeScopedId(u, l);
                }

                return id;
            case 125: // C_STRING
                c = this.LT(1);
                String s = this.m_mp.getCStr(c);
                c.setText(s);

                this.match(125);
                if (this.inputState.guessing == 0) {
                    id = this.m_mp.makeScopedId(null, c);
                }

                return id;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final OidComponent oid_component(OidComponent parent) throws RecognitionException, TokenStreamException {
        OidComponent oc = null;
        Token nt1 = null;
        Token lt = null;
        Token nt2 = null;
        switch (this.LA(1)) {
            case 117:
                nt1 = this.LT(1);
                this.match(117);
                if (this.inputState.guessing == 0) {
                    oc = this.m_mp.createOidComponent(parent, (Token)null, nt1);
                }
                break;
            case 119:
                lt = this.LT(1);
                this.match(119);
                switch (this.LA(1)) {
                    case 103:
                        this.match(103);
                        nt2 = this.LT(1);
                        this.match(117);
                        this.match(108);
                    case 106:
                    case 117:
                    case 119:
                        if (this.inputState.guessing == 0) {
                            oc = this.m_mp.createOidComponent(parent, lt, nt2);
                        }

                        return oc;
                    default:
                        throw new NoViableAltException(this.LT(1), this.getFilename());
                }
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }

        return oc;
    }

    public final StatusAll status_all() throws RecognitionException, TokenStreamException {
        StatusAll status = null;
        Token l = null;
        l = this.LT(1);
        this.match(119);
        if (this.inputState.guessing == 0) {
            status = StatusAll.find(l.getText(), true);
        }

        return status;
    }

    public final SmiRow objecttype_macro_index(IdToken idToken, SmiType type) throws RecognitionException, TokenStreamException {
        SmiRow row = this.m_mp.createRow(idToken, type);
        this.match(101);
        this.objecttype_macro_indextype(row);

        while(this.LA(1) == 93) {
            this.match(93);
            this.objecttype_macro_indextype(row);
        }

        this.match(106);
        return row;
    }

    public final SmiRow objecttype_macro_augments(IdToken idToken, SmiType type) throws RecognitionException, TokenStreamException {
        SmiRow row = this.m_mp.createRow(idToken, type);
        this.match(101);
        ScopedId id = this.defined_value();
        this.match(106);
        if (this.inputState.guessing == 0) {
            row.setAugmentsId(id);
        }

        return row;
    }

    public final StatusV2 status_v2() throws RecognitionException, TokenStreamException {
        StatusV2 status = null;
        Token l = null;
        l = this.LT(1);
        this.match(119);
        if (this.inputState.guessing == 0) {
            status = this.m_mp.findStatusV2(l.getText());
        }

        return status;
    }

    public final void objecttype_macro_indextype(SmiRow row) throws RecognitionException, TokenStreamException {
        boolean implied = false;
        switch (this.LA(1)) {
            case 44: // IMPLIED
                this.match(44);
                if (this.inputState.guessing == 0) {
                    implied = true;
                }
            case 118: // UPPER
            case 119: // LOWER
            case 125: // C_STRING
                ScopedId id = this.defined_value();
                if (this.inputState.guessing == 0) {
                    row.addIndex(id, implied);
                }

                return;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final void moduleidentity_macro_revision() throws RecognitionException, TokenStreamException {
        this.match(151);
        this.match(125);
        this.match(143);
        this.match(125);
    }

    public final void notification_reference(SmiOidMacro sequenceType) throws RecognitionException, TokenStreamException {
        Token l = null;
        l = this.LT(1);
        this.match(119);
        if (this.inputState.guessing == 0) {
            this.m_mp.addReference(sequenceType, l);
        }


    }

    public final void modulecompliance_macro_module() throws RecognitionException, TokenStreamException {
        this.match(155);
        switch (this.LA(1)) {
            case 58:
            case 90:
            case 155:
            case 156:
            case 157:
                break;
            case 118:
                this.match(118);
                break;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }

        switch (this.LA(1)) {
            case 156:
                this.match(156);
                this.match(101);
                this.match(119);

                while(this.LA(1) == 93) {
                    this.match(93);
                    this.match(119);
                }

                this.match(106);
            case 58:
            case 90:
            case 155:
            case 157:
                while(this.LA(1) == 58 || this.LA(1) == 157) {
                    this.modulecompliance_macro_compliance();
                }

                return;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final void modulecompliance_macro_compliance() throws RecognitionException, TokenStreamException {
        switch (this.LA(1)) {
            case 58:
                this.modulecompliance_macro_compliance_object();
                break;
            case 157:
                this.modulecompliance_macro_compliance_group();
                break;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }

    }

    public final void modulecompliance_macro_compliance_group() throws RecognitionException, TokenStreamException {
        this.match(157);
        this.match(119);
        this.match(143);
        this.match(125);
    }

    public final void modulecompliance_macro_compliance_object() throws RecognitionException, TokenStreamException {
        this.match(58);
        this.match(119);
        switch (this.LA(1)) {
            case 138:
                this.match(138);
                this.leaf_type((IdToken)null);
            case 143:
            case 158:
            case 159:
                switch (this.LA(1)) {
                    case 158:
                        this.match(158);
                        this.leaf_type((IdToken)null);
                    case 143:
                    case 159:
                        switch (this.LA(1)) {
                            case 159:
                                this.match(159);
                                this.modulecompliance_access();
                            case 143:
                                this.match(143);
                                this.match(125);
                                return;
                            default:
                                throw new NoViableAltException(this.LT(1), this.getFilename());
                        }
                    default:
                        throw new NoViableAltException(this.LT(1), this.getFilename());
                }
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final void modulecompliance_access() throws RecognitionException, TokenStreamException {
        Token l = null;
        l = this.LT(1);
        this.match(119);
        if (this.inputState.guessing == 0) {
            ModuleComplianceAccess.find(l.getText(), true);
        }

    }

    public final void agentcapabilities_status() throws RecognitionException, TokenStreamException {
        Token l = null;
        l = this.LT(1);
        this.match(119);
        if (this.inputState.guessing == 0) {
            AgentCapabilitiesStatus.find(l.getText(), true);
        }

    }

    public final void agentcapabilities_macro_module() throws RecognitionException, TokenStreamException {
        this.match(161);
        this.match(118);
        this.match(46);
        this.match(101);
        this.match(119);

        while(this.LA(1) == 93) {
            this.match(93);
            this.match(119);
        }

        this.match(106);

        while(this.LA(1) == 162) {
            this.agentcapabilities_macro_variation();
        }

    }

    public final void agentcapabilities_macro_variation() throws RecognitionException, TokenStreamException {
        this.match(162);
        this.match(119);
        switch (this.LA(1)) {
            case 138:
                this.match(138);
                this.leaf_type((IdToken)null);
            case 140:
            case 143:
            case 147:
            case 158:
            case 163:
                break;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }

        switch (this.LA(1)) {
            case 158:
                this.match(158);
                this.leaf_type((IdToken)null);
            case 140:
            case 143:
            case 147:
            case 163:
                switch (this.LA(1)) {
                    case 140:
                        this.match(140);
                        this.agentcapabilities_access();
                    case 143:
                    case 147:
                    case 163:
                        break;
                    default:
                        throw new NoViableAltException(this.LT(1), this.getFilename());
                }

                switch (this.LA(1)) {
                    case 163:
                        this.match(163);
                        this.match(101);
                        this.match(119);

                        while(this.LA(1) == 93) {
                            this.match(93);
                            this.match(119);
                        }

                        this.match(106);
                    case 143:
                    case 147:
                        switch (this.LA(1)) {
                            case 147:
                                this.match(147);
                                this.match(101);
                                this.leaf_value();
                                this.match(106);
                            case 143:
                                this.match(143);
                                this.match(125);
                                return;
                            default:
                                throw new NoViableAltException(this.LT(1), this.getFilename());
                        }
                    default:
                        throw new NoViableAltException(this.LT(1), this.getFilename());
                }
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }
    }

    public final void agentcapabilities_access() throws RecognitionException, TokenStreamException {
        Token l = null;
        l = this.LT(1);
        this.match(119);
        if (this.inputState.guessing == 0) {
            AgentCapabilitiesAccess.find(l.getText(), true);
        }

    }

    public final void named_number(List<SmiNamedNumber> l) throws RecognitionException, TokenStreamException {
        IdToken it = null;
        BigIntegerToken bit = null;
        HexStringToken hst = null;

        it = this.lower();
        this.match(103);
        if(this.LA(1)==124) {
            hst = this.hex_string_token();
        } else {
            bit = this.big_integer_token();
        }

        this.match(108);
        if (this.inputState.guessing == 0) {
            if(bit!=null) {
                l.add(new SmiNamedNumber(it, bit));
            } else {
                l.add(new SmiNamedNumber(it, hst));
            }

        }

    }

    private static final long[] mk_tokenSet_0() {
        long[] data = new long[]{0L, -4557642822898941952L, 255L, 0L, 0L, 0L};
        return data;
    }

    private static final long[] mk_tokenSet_1() {
        long[] data = new long[]{-134217744L, -1L, 274877906943L, 0L, 0L, 0L};
        return data;
    }

    private static final long[] mk_tokenSet_2() {
        long[] data = new long[8];
        data[0] = -16L;
        data[1] = -4398046511105L;
        data[2] = 274877906943L;
        return data;
    }

    private static final long[] mk_tokenSet_3() {
        long[] data = new long[]{134217728L, -4557637737120792576L, 37581551871L, 0L, 0L, 0L};
        return data;
    }

    private static final long[] mk_tokenSet_4() {
        long[] data = new long[]{0L, 1738390555676639232L, 0L, 0L};
        return data;
    }

    private static final long[] mk_tokenSet_5() {
        long[] data = new long[]{0L, 45040944076029952L, 0L, 0L};
        return data;
    }

    public final boolean grammar_check() throws RecognitionException, TokenStreamException {

        SmiModule result = null;
        boolean check = false;

        switch (this.LA(1)) {
            case 118: // UPPER
            case 119: // LOWER
                result = this.module_identifier();

//                this.consumeUntil(25);
                this.match(25); // DEFINITION

                this.match(90); // ASSIGN_OP '::='

                this.match(12); // BEGIN

//                this.consumeUntil(27);

                label18:
                switch (this.LA(1)) {
                    case 33:
                        this.exports();
                    case 27:
                    case 45:
                    case 118:
                    case 119:
                    case 126:
                    case 127:
                    case 128:
                    case 129:
                    case 130:
                    case 131:
                    case 132:
                    case 133:
                    case 134:
                    case 135:
                        switch (this.LA(1)) {
                            case 27:
                            case 118:
                            case 119:
                            case 126:
                            case 127:
                            case 128:
                            case 129:
                            case 130:
                            case 131:
                            case 132:
                            case 133:
                            case 134:
                            case 135:
                                break label18;
                            case 45:
                                this.imports();
                                break label18;
                            default:
                                throw new NoViableAltException(this.LT(1), this.getFilename());
                        }
                    default:
                        throw new NoViableAltException(this.LT(1), this.getFilename());
                }

                this.consumeUntil(27);
                this.match(27); // END

                check = true;
                break;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }

        return check;
    }

    public final boolean external_module_check() throws RecognitionException, TokenStreamException {

        SmiModule result = null;
        boolean check = false;

        switch (this.LA(1)) {
            case 118: // UPPER
            case 119: // LOWER
                result = this.module_identifier();

//                this.consumeUntil(25);
                this.match(25); // DEFINITION

                this.match(90); // ASSIGN_OP '::='

                this.match(12); // BEGIN

                label18:
                switch (this.LA(1)) {
                    case 33:
                        this.exports();
                    case 27:
                    case 45:
                    case 118:
                    case 119:
                    case 126:
                    case 127:
                    case 128:
                    case 129:
                    case 130:
                    case 131:
                    case 132:
                    case 133:
                    case 134:
                    case 135:
                        switch (this.LA(1)) {
                            case 27:
                            case 118:
                            case 119:
                            case 126:
                            case 127:
                            case 128:
                            case 129:
                            case 130:
                            case 131:
                            case 132:
                            case 133:
                            case 134:
                            case 135:
                                break label18;
                            case 45:
                                this.imports();
                                break label18;
                            default:
                                throw new NoViableAltException(this.LT(1), this.getFilename());
                        }
                    default:
                        throw new NoViableAltException(this.LT(1), this.getFilename());
                }

                check = true;
                break;
            default:
                throw new NoViableAltException(this.LT(1), this.getFilename());
        }

        return check;
    }
}
