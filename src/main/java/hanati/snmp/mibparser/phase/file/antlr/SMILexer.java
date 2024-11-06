package hanati.snmp.mibparser.phase.file.antlr;

import antlr.ANTLRHashString;
import antlr.ByteBuffer;
import antlr.CharBuffer;
import antlr.CharScanner;
import antlr.CharStreamException;
import antlr.CharStreamIOException;
import antlr.InputBuffer;
import antlr.LexerSharedInputState;
import antlr.NoViableAltForCharException;
import antlr.RecognitionException;
import antlr.Token;
import antlr.TokenStream;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.TokenStreamRecognitionException;
import antlr.collections.impl.BitSet;
import java.io.InputStream;
import java.io.Reader;
import java.util.Hashtable;

public class SMILexer extends CharScanner implements SMITokenTypes, TokenStream {
    public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
    public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
    public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
    public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
    public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
    public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
    public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
    public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());

    public SMILexer(InputStream in) {
        this((InputBuffer)(new ByteBuffer(in)));
    }

    public SMILexer(Reader in) {
        this((InputBuffer)(new CharBuffer(in)));
    }

    public SMILexer(InputBuffer ib) {
        this(new LexerSharedInputState(ib));
    }

    public SMILexer(LexerSharedInputState state) {
        super(state);
        this.caseSensitiveLiterals = true;
        this.setCaseSensitive(true);
        this.literals = new Hashtable();
        this.literals.put(new ANTLRHashString("IDENTIFIER", this), Integer.valueOf(42));
        this.literals.put(new ANTLRHashString("PRESENT", this), Integer.valueOf(67));
        this.literals.put(new ANTLRHashString("MACRO", this), Integer.valueOf(136));
        this.literals.put(new ANTLRHashString("CHOICE", this), Integer.valueOf(18));
        this.literals.put(new ANTLRHashString("ACCESS", this), Integer.valueOf(140));
        this.literals.put(new ANTLRHashString("VARIABLES", this), Integer.valueOf(165));
        this.literals.put(new ANTLRHashString("END", this), Integer.valueOf(27));
        this.literals.put(new ANTLRHashString("DEFVAL", this), Integer.valueOf(147));
        this.literals.put(new ANTLRHashString("NOTIFICATIONS", this), Integer.valueOf(154));
        this.literals.put(new ANTLRHashString("PDV", this), Integer.valueOf(65));
        this.literals.put(new ANTLRHashString("INTERSECTION", this), Integer.valueOf(49));
        this.literals.put(new ANTLRHashString("OBJECT-IDENTITY", this), Integer.valueOf(128));
        this.literals.put(new ANTLRHashString("COMPONENT", this), Integer.valueOf(21));
        this.literals.put(new ANTLRHashString("STRING", this), Integer.valueOf(76));
        this.literals.put(new ANTLRHashString("NOTIFICATION-TYPE", this), Integer.valueOf(129));
        this.literals.put(new ANTLRHashString("PrintableString", this), Integer.valueOf(68));
        this.literals.put(new ANTLRHashString("CLASS", this), Integer.valueOf(19));
        this.literals.put(new ANTLRHashString("ARGUMENT", this), Integer.valueOf(8));
        this.literals.put(new ANTLRHashString("IA5String", this), Integer.valueOf(41));
        this.literals.put(new ANTLRHashString("EMBEDDED", this), Integer.valueOf(26));
        this.literals.put(new ANTLRHashString("SYNTAX", this), Integer.valueOf(138));
        this.literals.put(new ANTLRHashString("INSTANCE", this), Integer.valueOf(47));
        this.literals.put(new ANTLRHashString("ENUMERATED", this), Integer.valueOf(28));
        this.literals.put(new ANTLRHashString("NOTIFICATION-GROUP", this), Integer.valueOf(132));
        this.literals.put(new ANTLRHashString("NumericString", this), Integer.valueOf(56));
        this.literals.put(new ANTLRHashString("PLUSINFINITY", this), Integer.valueOf(66));
        this.literals.put(new ANTLRHashString("ABSTRACT-SYNTAX", this), Integer.valueOf(5));
        this.literals.put(new ANTLRHashString("TRAP-TYPE", this), Integer.valueOf(135));
        this.literals.put(new ANTLRHashString("TAGS", this), Integer.valueOf(77));
        this.literals.put(new ANTLRHashString("ERRORS", this), Integer.valueOf(30));
        this.literals.put(new ANTLRHashString("ENTERPRISE", this), Integer.valueOf(164));
        this.literals.put(new ANTLRHashString("AUGMENTS", this), Integer.valueOf(146));
        this.literals.put(new ANTLRHashString("MANDATORY-GROUPS", this), Integer.valueOf(156));
        this.literals.put(new ANTLRHashString("UTF8String", this), Integer.valueOf(86));
        this.literals.put(new ANTLRHashString("FROM", this), Integer.valueOf(37));
        this.literals.put(new ANTLRHashString("NULL", this), Integer.valueOf(55));
        this.literals.put(new ANTLRHashString("REAL", this), Integer.valueOf(70));
        this.literals.put(new ANTLRHashString("GraphicString", this), Integer.valueOf(40));
        this.literals.put(new ANTLRHashString("UTCTime", this), Integer.valueOf(85));
        this.literals.put(new ANTLRHashString("MODULE-IDENTITY", this), Integer.valueOf(127));
        this.literals.put(new ANTLRHashString("UNION", this), Integer.valueOf(81));
        this.literals.put(new ANTLRHashString("AUTOMATIC", this), Integer.valueOf(10));
        this.literals.put(new ANTLRHashString("OPTIONAL", this), Integer.valueOf(63));
        this.literals.put(new ANTLRHashString("UNITS", this), Integer.valueOf(139));
        this.literals.put(new ANTLRHashString("SET", this), Integer.valueOf(74));
        this.literals.put(new ANTLRHashString("ObjectDescriptor", this), Integer.valueOf(57));
        this.literals.put(new ANTLRHashString("WITH", this), Integer.valueOf(89));
        this.literals.put(new ANTLRHashString("OF", this), Integer.valueOf(61));
        this.literals.put(new ANTLRHashString("LAST-UPDATED", this), Integer.valueOf(148));
        this.literals.put(new ANTLRHashString("INDEX", this), Integer.valueOf(145));
        this.literals.put(new ANTLRHashString("BITS", this), Integer.valueOf(137));
        this.literals.put(new ANTLRHashString("EXPORTS", this), Integer.valueOf(33));
        this.literals.put(new ANTLRHashString("FALSE", this), Integer.valueOf(36));
        this.literals.put(new ANTLRHashString("GeneralizedTime", this), Integer.valueOf(38));
        this.literals.put(new ANTLRHashString("MIN-ACCESS", this), Integer.valueOf(159));
        this.literals.put(new ANTLRHashString("UNIQUE", this), Integer.valueOf(82));
        this.literals.put(new ANTLRHashString("VideotexString", this), Integer.valueOf(87));
        this.literals.put(new ANTLRHashString("BY", this), Integer.valueOf(16));
        this.literals.put(new ANTLRHashString("BASEDNUM", this), Integer.valueOf(11));
        this.literals.put(new ANTLRHashString("TYPE-IDENTIFIER", this), Integer.valueOf(80));
        this.literals.put(new ANTLRHashString("PRIVATE", this), Integer.valueOf(69));
        this.literals.put(new ANTLRHashString("DISPLAY-HINT", this), Integer.valueOf(153));
        this.literals.put(new ANTLRHashString("ANY", this), Integer.valueOf(7));
        this.literals.put(new ANTLRHashString("DEFAULT", this), Integer.valueOf(23));
        this.literals.put(new ANTLRHashString("REVISION", this), Integer.valueOf(151));
        this.literals.put(new ANTLRHashString("OBJECT", this), Integer.valueOf(58));
        this.literals.put(new ANTLRHashString("BMPString", this), Integer.valueOf(14));
        this.literals.put(new ANTLRHashString("MIN", this), Integer.valueOf(54));
        this.literals.put(new ANTLRHashString("INCLUDES", this), Integer.valueOf(46));
        this.literals.put(new ANTLRHashString("REFERENCE", this), Integer.valueOf(144));
        this.literals.put(new ANTLRHashString("RELATIVE", this), Integer.valueOf(71));
        this.literals.put(new ANTLRHashString("BOOLEAN", this), Integer.valueOf(15));
        this.literals.put(new ANTLRHashString("ALL", this), Integer.valueOf(6));
        this.literals.put(new ANTLRHashString("DEFINED", this), Integer.valueOf(24));
        this.literals.put(new ANTLRHashString("CONSTRAINED", this), Integer.valueOf(22));
        this.literals.put(new ANTLRHashString("WRITE-SYNTAX", this), Integer.valueOf(158));
        this.literals.put(new ANTLRHashString("IMPLIED", this), Integer.valueOf(44));
        this.literals.put(new ANTLRHashString("RESULT", this), Integer.valueOf(72));
        this.literals.put(new ANTLRHashString("AGENT-CAPABILITIES", this), Integer.valueOf(134));
        this.literals.put(new ANTLRHashString("VisibleString", this), Integer.valueOf(88));
        this.literals.put(new ANTLRHashString("VARIATION", this), Integer.valueOf(162));
        this.literals.put(new ANTLRHashString("CHARACTER", this), Integer.valueOf(17));
        this.literals.put(new ANTLRHashString("BEGIN", this), Integer.valueOf(12));
        this.literals.put(new ANTLRHashString("GROUP", this), Integer.valueOf(157));
        this.literals.put(new ANTLRHashString("BIT", this), Integer.valueOf(13));
        this.literals.put(new ANTLRHashString("ISO646String", this), Integer.valueOf(50));
        this.literals.put(new ANTLRHashString("ERROR", this), Integer.valueOf(29));
        this.literals.put(new ANTLRHashString("SUPPORTS", this), Integer.valueOf(161));
        this.literals.put(new ANTLRHashString("TEXTUAL-CONVENTION", this), Integer.valueOf(130));
        this.literals.put(new ANTLRHashString("SIZE", this), Integer.valueOf(75));
        this.literals.put(new ANTLRHashString("EXTERNAL", this), Integer.valueOf(35));
        this.literals.put(new ANTLRHashString("PRODUCT-RELEASE", this), Integer.valueOf(160));
        this.literals.put(new ANTLRHashString("ABSENT", this), Integer.valueOf(4));
        this.literals.put(new ANTLRHashString("CREATION-REQUIRES", this), Integer.valueOf(163));
        this.literals.put(new ANTLRHashString("OBJECT-TYPE", this), Integer.valueOf(126));
        this.literals.put(new ANTLRHashString("TeletexString", this), Integer.valueOf(78));
        this.literals.put(new ANTLRHashString("PARAMETER", this), Integer.valueOf(64));
        this.literals.put(new ANTLRHashString("MODULE", this), Integer.valueOf(155));
        this.literals.put(new ANTLRHashString("OPERATION", this), Integer.valueOf(60));
        this.literals.put(new ANTLRHashString("ORGANIZATION", this), Integer.valueOf(149));
        this.literals.put(new ANTLRHashString("STATUS", this), Integer.valueOf(142));
        this.literals.put(new ANTLRHashString("OID", this), Integer.valueOf(62));
        this.literals.put(new ANTLRHashString("EXCEPT", this), Integer.valueOf(31));
        this.literals.put(new ANTLRHashString("APPLICATION", this), Integer.valueOf(9));
        this.literals.put(new ANTLRHashString("MAX", this), Integer.valueOf(52));
        this.literals.put(new ANTLRHashString("MINUSINFINITY", this), Integer.valueOf(53));
        this.literals.put(new ANTLRHashString("GeneralString", this), Integer.valueOf(39));
        this.literals.put(new ANTLRHashString("LINKED", this), Integer.valueOf(51));
        this.literals.put(new ANTLRHashString("IMPORTS", this), Integer.valueOf(45));
        this.literals.put(new ANTLRHashString("UNIVERSAL", this), Integer.valueOf(83));
        this.literals.put(new ANTLRHashString("OCTET", this), Integer.valueOf(59));
        this.literals.put(new ANTLRHashString("COMPONENTS", this), Integer.valueOf(20));
        this.literals.put(new ANTLRHashString("DEFINITIONS", this), Integer.valueOf(25));
        this.literals.put(new ANTLRHashString("DESCRIPTION", this), Integer.valueOf(143));
        this.literals.put(new ANTLRHashString("TRUE", this), Integer.valueOf(79));
        this.literals.put(new ANTLRHashString("SEQUENCE", this), Integer.valueOf(73));
        this.literals.put(new ANTLRHashString("UniversalString", this), Integer.valueOf(84));
        this.literals.put(new ANTLRHashString("OBJECTS", this), Integer.valueOf(152));
        this.literals.put(new ANTLRHashString("MAX-ACCESS", this), Integer.valueOf(141));
        this.literals.put(new ANTLRHashString("IMPLICIT", this), Integer.valueOf(43));
        this.literals.put(new ANTLRHashString("OBJECT-GROUP", this), Integer.valueOf(131));
        this.literals.put(new ANTLRHashString("INTEGER", this), Integer.valueOf(48));
        this.literals.put(new ANTLRHashString("EXTENSIBILITY", this), Integer.valueOf(34));
        this.literals.put(new ANTLRHashString("MODULE-COMPLIANCE", this), Integer.valueOf(133));
        this.literals.put(new ANTLRHashString("EXPLICIT", this), Integer.valueOf(32));
        this.literals.put(new ANTLRHashString("CONTACT-INFO", this), Integer.valueOf(150));
    }

    public Token nextToken() throws TokenStreamException {
        Token theRetToken = null;

        while(true) {
            Token _token = null;
//            int _ttype = false;
            this.resetText();

            try {
                try {
                    switch (this.LA(1)) {
                        case '\t':
                        case '\n':
                        case '\f':
                        case '\r':
                        case ' ':
                            this.mWS(true);
                            theRetToken = this._returnToken;
                            break;
                        case '\u000b':
                        case '\u000e':
                        case '\u000f':
                        case '\u0010':
                        case '\u0011':
                        case '\u0012':
                        case '\u0013':
                        case '\u0014':
                        case '\u0015':
                        case '\u0016':
                        case '\u0017':
                        case '\u0018':
                        case '\u0019':
                        case '\u001a':
                        case '\u001b':
                        case '\u001c':
                        case '\u001d':
                        case '\u001e':
                        case '\u001f':
                        case '$':
                        case '%':
                        case '&':
                        case '\'':
                        case '*':
                        case '-':
                        case '.':
                        case '/':
                        case ':':
                        case '=':
                        case '>':
                        case '?':
                        case '@':
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                        case 'G':
                        case 'H':
                        case 'I':
                        case 'J':
                        case 'K':
                        case 'L':
                        case 'M':
                        case 'N':
                        case 'O':
                        case 'P':
                        case 'Q':
                        case 'R':
                        case 'S':
                        case 'T':
                        case 'U':
                        case 'V':
                        case 'W':
                        case 'X':
                        case 'Y':
                        case 'Z':
                        case '\\':
                        case '_':
                        case '`':
                        default:
                            if (this.LA(1) == '.' && this.LA(2) == '.' && this.LA(3) == '.') {
                                this.mELLIPSIS(true);
                                theRetToken = this._returnToken;
                            } else if (this.LA(1) == 'S' && this.LA(2) == 'M' && this.LA(3) == 'I') {
                                this.mSMIC_DIRECTIVE(true);
                                theRetToken = this._returnToken;
                            } else if (this.LA(1) == '-' && this.LA(2) == '-' && this.LA(3) >= 3 && this.LA(3) <= 255) {
                                this.mSL_COMMENT(true);
                                theRetToken = this._returnToken;
                            } else if (this.LA(1) == '\'' && _tokenSet_0.member(this.LA(2)) && _tokenSet_1.member(this.LA(3))) {
                                this.mB_OR_H_STRING(true);
                                theRetToken = this._returnToken;
                            } else if (this.LA(1) == ':' && this.LA(2) == ':') {
                                this.mASSIGN_OP(true);
                                theRetToken = this._returnToken;
                            } else if (this.LA(1) == '-' && this.LA(2) == '-') {
                                this.mCOMMENT(true);
                                theRetToken = this._returnToken;
                            } else if (this.LA(1) == '.' && this.LA(2) == '.') {
                                this.mDOTDOT(true);
                                theRetToken = this._returnToken;
                            } else if (this.LA(1) == '\'' && this.LA(2) == 'B') {
                                this.mCHARB(true);
                                theRetToken = this._returnToken;
                            } else if (this.LA(1) == '\'' && this.LA(2) == 'H') {
                                this.mCHARH(true);
                                theRetToken = this._returnToken;
                            } else if (this.LA(1) == ':') {
                                this.mCOLON(true);
                                theRetToken = this._returnToken;
                            } else if (this.LA(1) == '.') {
                                this.mDOT(true);
                                theRetToken = this._returnToken;
                            } else if (this.LA(1) == '-') {
                                this.mMINUS(true);
                                theRetToken = this._returnToken;
                            } else if (this.LA(1) == '\'') {
                                this.mSINGLE_QUOTE(true);
                                theRetToken = this._returnToken;
                            } else if (this.LA(1) >= 'A' && this.LA(1) <= 'Z') {
                                this.mUPPER(true);
                                theRetToken = this._returnToken;
                            } else if (this.LA(1)=='/' && this.LA(2)=='*') {
                                this.mL_COMMENT2(true);
                                theRetToken = this._returnToken;

                            } else if (this.LA(1)=='*' && this.LA(2)=='/') {
                                this.mR_COMMENT2(true);
                                theRetToken = this._returnToken;

                            } else {
                                if (this.LA(1) != '\uffff') {
                                    throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                                }

                                this.uponEOF();
                                this._returnToken = this.makeToken(1);
                            }
                            break;
                        case '!':
                            this.mEXCLAMATION(true);
                            theRetToken = this._returnToken;
                            break;
                        case '"':
                            this.mC_STRING(true);
                            theRetToken = this._returnToken;
                            break;
                        case '#':
                            this.mINCLUDE(true);
                            theRetToken = this._returnToken;
                            break;
                        case '(':
                            this.mL_PAREN(true);
                            theRetToken = this._returnToken;
                            break;
                        case ')':
                            this.mR_PAREN(true);
                            theRetToken = this._returnToken;
                            break;
                        case '+':
                            this.mPLUS(true);
                            theRetToken = this._returnToken;
                            break;
                        case ',':
                            this.mCOMMA(true);
                            theRetToken = this._returnToken;
                            break;
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            int i = checkCustomSyntax();
                            if( i != 0 ) {
                                this.mComplex(true, i);
                                theRetToken = this._returnToken;
                                break;
                            } else {
                                this.mNUMBER(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                        case ';':
                            this.mSEMI(true);
                            theRetToken = this._returnToken;
                            break;
                        case '<':
                            this.mLESS(true);
                            theRetToken = this._returnToken;
                            break;
                        case '[':
                            this.mL_BRACKET(true);
                            theRetToken = this._returnToken;
                            break;
                        case ']':
                            this.mR_BRACKET(true);
                            theRetToken = this._returnToken;
                            break;
                        case '^':
                            this.mINTERSECTION(true);
                            theRetToken = this._returnToken;
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                        case 'g':
                        case 'h':
                        case 'i':
                        case 'j':
                        case 'k':
                        case 'l':
                        case 'm':
                        case 'n':
                        case 'o':
                        case 'p':
                        case 'q':
                        case 'r':
                        case 's':
                        case 't':
                        case 'u':
                        case 'v':
                        case 'w':
                        case 'x':
                        case 'y':
                        case 'z':
                            this.mLOWER(true);
                            theRetToken = this._returnToken;
                            break;
                        case '{':
                            this.mL_BRACE(true);
                            theRetToken = this._returnToken;
                            break;
                        case '|':
                            this.mBAR(true);
                            theRetToken = this._returnToken;
                            break;
                        case '}':
                            this.mR_BRACE(true);
                            theRetToken = this._returnToken;
                    }

                    if (this._returnToken != null) {
                        int _ttype = this._returnToken.getType();
                        _ttype = this.testLiteralsTable(_ttype);
                        this._returnToken.setType(_ttype);
                        return this._returnToken;
                    }
                } catch (RecognitionException var5) {
                    throw new TokenStreamRecognitionException(var5);
                }
            } catch (CharStreamException var6) {
                if (var6 instanceof CharStreamIOException) {
                    throw new TokenStreamIOException(((CharStreamIOException)var6).io);
                }

                throw new TokenStreamException(var6.getMessage());
            }
        }
    }

    public final void mASSIGN_OP(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 90;
        this.match("::=");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mBAR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 91;
        this.match('|');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mCOLON(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 92;
        this.match(':');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mCOMMA(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 93;
        this.match(',');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mCOMMENT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 94;
        this.match("--");

        if(this.LA(0)==45 && this.LA(1)==65535 && this.LA(2)==65535) {
            return;
        }

        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mL_COMMENT2(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 200;
        this.match("/*");

        while(this.LA(1)=='*' && this.LA(2)=='/') {
            this.consume();
        }

        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mR_COMMENT2(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 201;
        this.match("*/");

        while(this.LA(1)=='*' && this.LA(2)=='/') {
            this.consume();
        }

        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mDOT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 95;
        this.match('.');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mDOTDOT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 96;
        this.match("..");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mELLIPSIS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 97;
        this.match("...");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mEXCLAMATION(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 98;
        this.match('!');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mINTERSECTION(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 99;
        this.match('^');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mLESS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 100;
        this.match('<');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mL_BRACE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 101;
        this.match('{');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mL_BRACKET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 102;
        this.match('[');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mL_PAREN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 103;
        this.match('(');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mMINUS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 104;
        this.match('-');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mPLUS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 105;
        this.match('+');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mR_BRACE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 106;
        this.match('}');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mR_BRACKET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 107;
        this.match(']');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mR_PAREN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 108;
        this.match(')');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mSEMI(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 109;
        this.match(';');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mSINGLE_QUOTE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 110;
        this.match("'");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mCHARB(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 111;
        this.match("'B");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mCHARH(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 112;
        this.match("'H");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mWS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 113;
        int _cnt27 = 0;

        while(true) {
            switch (this.LA(1)) {
                case '\t':
                    this.match('\t');
                    break;
                case '\n':
                case '\r':
                    if (this.LA(1) == '\r' && this.LA(2) == '\n') {
                        this.match("\r\n");
                        if (this.inputState.guessing == 0) {
                            this.newline();
                        }
                    } else if (this.LA(1) == '\r') {
                        this.match('\r');
                        if (this.inputState.guessing == 0) {
                            this.newline();
                        }
                    } else {
                        if (this.LA(1) != '\n') {
                            throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                        }

                        this.match('\n');
                        if (this.inputState.guessing == 0) {
                            this.newline();
                        }
                    }
                    break;
                case '\f':
                    this.match('\f');
                    break;
                case ' ':
                    this.match(' ');
                    break;
                default:
                    if (_cnt27 >= 1) {
                        if (this.inputState.guessing == 0) {
                            _ttype = -1;
                        }

                        if (_createToken && _token == null && _ttype != -1) {
                            _token = this.makeToken(_ttype);
                            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
                        }

                        this._returnToken = _token;
                        return;
                    } else {
                        throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                    }
            }

            ++_cnt27;
        }
    }

    public final void mSMIC_DIRECTIVE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 114;
        this.match("SMI");
        this.mWS(false);
        switch (this.LA(1)) {
            case 'O':
                this.match("OBJECT-TYPE");
                break;
            case 'T':
                this.match("TRAP-TYPE");
                break;
            default:
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
        }

        if (this.inputState.guessing == 0) {
            _ttype = -1;
        }

        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mINCLUDE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 115;
        this.match("#include");

        while(_tokenSet_2.member(this.LA(1))) {
            this.match(_tokenSet_2);
        }

        switch (this.LA(1)) {
            case '\r':
                this.match('\r');
            case '\n':
                this.match('\n');
                if (this.inputState.guessing == 0) {
                    this.newline();
                }

                if (this.inputState.guessing == 0) {
                    _ttype = -1;
                }

                if (_createToken && _token == null && _ttype != -1) {
                    _token = this.makeToken(_ttype);
                    _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
                }

                this._returnToken = _token;
                return;
            default:
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
        }
    }

    public final void mSL_COMMENT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 116;
        this.mCOMMENT(false);

        while(_tokenSet_3.member(this.LA(1))) {
            this.match(_tokenSet_3);
        }

        if(this.LA(0)==42 && this.LA(1)==65535 && this.LA(2)==65535) {
            return;
        }

        switch (this.LA(1)) {
            case '\r':
                this.match('\r');
            case '\n':
                this.match('\n');
                if (this.inputState.guessing == 0) {
                    this.newline();
                }

                if (this.inputState.guessing == 0) {
                    _ttype = -1;
                }

                if (_createToken && _token == null && _ttype != -1) {
                    _token = this.makeToken(_ttype);
                    _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
                }

                this._returnToken = _token;
                return;
            default:
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
        }
    }

    public final void mNUMBER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 117;

        int _cnt47;
        for(_cnt47 = 0; this.LA(1) >= '0' && this.LA(1) <= '9'; ++_cnt47) {
            this.matchRange('0', '9');
        }

        if (_cnt47 >= 1) {
            if (_createToken && _token == null && _ttype != -1) {
                _token = this.makeToken(_ttype);
                _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
            }

            this._returnToken = _token;
        } else {
            throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
        }
    }

    public final void mUPPER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 118;
        this.matchRange('A', 'Z');

        while(_tokenSet_4.member(this.LA(1))) {
            switch (this.LA(1)) {
                case '-':
                    this.match('-');
                    break;
                case '.':
                case '/':
                case ':':
                case ';':
                case '<':
                case '=':
                case '>':
                case '?':
                case '@':
                case '[':
                case '\\':
                case ']':
                case '^':
                case '`':
                default:
                    throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    this.matchRange('0', '9');
                    break;
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    this.matchRange('A', 'Z');
                    break;
                case '_':
                    this.match('_');
                    break;
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    this.matchRange('a', 'z');
            }
        }

        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mLOWER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 119;
        this.matchRange('a', 'z');

        while(_tokenSet_4.member(this.LA(1))) {
            switch (this.LA(1)) {
                case '-':
                    this.match('-');
                    break;
                case '.':
                case '/':
                case ':':
                case ';':
                case '<':
                case '=':
                case '>':
                case '?':
                case '@':
                case '[':
                case '\\':
                case ']':
                case '^':
                case '`':
                default:
                    throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    this.matchRange('0', '9');
                    break;
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    this.matchRange('A', 'Z');
                    break;
                case '_':
                    this.match('_');
                    break;
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    this.matchRange('a', 'z');
            }
        }

        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    protected final void mBDIG(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 120;
        switch (this.LA(1)) {
            case '0':
                this.match('0');
                break;
            case '1':
                this.match('1');
                break;
            default:
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
        }

        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    protected final void mHDIG(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 121;
        switch (this.LA(1)) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                this.matchRange('0', '9');
                break;
            case ':':
            case ';':
            case '<':
            case '=':
            case '>':
            case '?':
            case '@':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            default:
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
                this.matchRange('A', 'F');
                break;
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
                this.matchRange('a', 'f');
        }

        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mB_OR_H_STRING(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 122;
        boolean synPredMatched68 = false;
        if (this.LA(1) == '\'' && _tokenSet_5.member(this.LA(2)) && _tokenSet_6.member(this.LA(3))) {
            int _m68 = this.mark();
            synPredMatched68 = true;
            ++this.inputState.guessing;

            try {
                this.mB_STRING(false);
            } catch (RecognitionException var9) {
                synPredMatched68 = false;
//                var9.printStackTrace();
            }

            this.rewind(_m68);
            --this.inputState.guessing;
        }

        if (synPredMatched68) {
            this.mB_STRING(false);
            if (this.inputState.guessing == 0) {
                _ttype = 123;
            }
        } else {
            if (this.LA(1) != '\'' || !_tokenSet_0.member(this.LA(2)) || !_tokenSet_1.member(this.LA(3))) {
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            }

            this.mH_STRING(false);
            if (this.inputState.guessing == 0) {
                _ttype = 124;
            }
        }

        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    protected final void mB_STRING(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 123;
        this.mSINGLE_QUOTE(false);

        while(this.LA(1) == '0' || this.LA(1) == '1') {
            this.mBDIG(false);
        }

        this.mSINGLE_QUOTE(false);
        switch (this.LA(1)) {
            case 'B':
                this.match('B');
                break;
            case 'b':
                this.match('b');
                break;
            default:
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
        }

        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    protected final void mH_STRING(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 124;
        this.mSINGLE_QUOTE(false);

        while(_tokenSet_7.member(this.LA(1))) {
            this.mHDIG(false);
        }

        this.mSINGLE_QUOTE(false);
        switch (this.LA(1)) {
            case 'H':
                this.match('H');
                break;
            case 'h':
                this.match('h');
                break;
            default:
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
        }

        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    public final void mC_STRING(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 125;
        this.match('"');

        while(this.LA(1) != '"') {
            if (this.LA(1) == '\r' && this.LA(2) == '\n' && this.LA(3) >= 3 && this.LA(3) <= 255) {
                this.match("\r\n");
                if (this.inputState.guessing == 0) {
                    this.newline();
                }
            } else if (this.LA(1) == '\r' && this.LA(2) >= 3 && this.LA(2) <= 255) {
                this.match('\r');
                if (this.inputState.guessing == 0) {
                    this.newline();
                }
            } else if (_tokenSet_3.member(this.LA(1)) && this.LA(2) >= 3 && this.LA(2) <= 255) {
                this.match(_tokenSet_3);
            } else {
                if (this.LA(1) != '\n') {
                    break;
                }

                this.match('\n');
                if (this.inputState.guessing == 0) {
                    this.newline();
                }
            }
        }

        this.match('"');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }

        this._returnToken = _token;
    }

    private static final long[] mk_tokenSet_0() {
        long[] data = new long[]{287949450930814976L, 541165879422L, 0L, 0L, 0L};
        return data;
    }

    private static final long[] mk_tokenSet_1() {
        long[] data = new long[]{287949450930814976L, 1640677507454L, 0L, 0L, 0L};
        return data;
    }

    private static final long[] mk_tokenSet_2() {
        long[] data = new long[8];
        data[0] = -35184372098056L;

        for(int i = 1; i <= 3; ++i) {
            data[i] = -1L;
        }

        return data;
    }

    private static final long[] mk_tokenSet_3() {
        long[] data = new long[8];
        data[0] = -9224L;

        for(int i = 1; i <= 3; ++i) {
            data[i] = -1L;
        }

        return data;
    }

    private static final long[] mk_tokenSet_4() {
        long[] data = new long[]{287984085547089920L, 576460745995190270L, 0L, 0L, 0L};
        return data;
    }

    private static final long[] mk_tokenSet_5() {
        long[] data = new long[]{844974685945856L, 0L, 0L, 0L, 0L};
        return data;
    }

    private static final long[] mk_tokenSet_6() {
        long[] data = new long[]{844974685945856L, 17179869188L, 0L, 0L, 0L};
        return data;
    }

    private static final long[] mk_tokenSet_7() {
        long[] data = new long[]{287948901175001088L, 541165879422L, 0L, 0L, 0L};
        return data;
    }

    protected final void mComplex(boolean _createToken, int count) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        int _begin = this.text.length();
        int _ttype = 199;

        StringBuffer sb = new StringBuffer();
        for(int i=1; i<count; i++) {
            sb.append(this.LA(i));
        }

        for(int j=1; j<=count; j++) {
            this.inputState.getInput().consume();
        }

        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
//            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
            _token.setText(sb.toString());
        }

        this._returnToken = _token;
    }

    protected final int checkCustomSyntax() throws CharStreamException {

        int i=1;
        char c = this.LA(i);
        String arr = " (){},.\r\n|";

        while( !arr.contains(String.valueOf(this.LA(i))) ) {
            i++;
        }

        for(int j=1; j<=i; j++) {
            if(Character.isAlphabetic(this.LA(j))) {
                return i;
            }
        }

        return 0;
    }
}
