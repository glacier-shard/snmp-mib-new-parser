package hanati.snmp.mibparser.util.check;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import hanati.snmp.mibparser.phase.file.antlr.SMILexer;
import hanati.snmp.mibparser.phase.file.antlr.SMIParser;
import hanati.snmp.mibparser.smi.SmiJavaCodeNamingStrategy;
import hanati.snmp.mibparser.smi.SmiMib;
import hanati.snmp.mibparser.smi.SmiModule;
import hanati.snmp.mibparser.smi.SmiOptions;
import hanati.snmp.mibparser.util.pair.CommonResult;

import java.io.*;
import java.net.URL;
import java.util.*;

public class MibValidator {

    public CommonResult isMibFile(File file) {
        CommonResult result = new CommonResult(CommonResult.INVALID_MIB_FILE_ERROR, "Not a valid MIB file");

        if (!file.canRead() || !file.isFile()) {
            return result;
        }
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            while (true) {
                String str = in.readLine();
                if (str == null) {
                    break;
                }
                str = str.trim();
                if (!str.isEmpty() && !str.startsWith("--")) {
                    if (str.contains("DEFINITIONS")) {
                        result = new CommonResult(CommonResult.SUCCESS);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            result = new CommonResult(CommonResult.UNKNOWN_ERROR, e.getMessage());
        }
        return result;
    }

    public CommonResult syntaxValidator(File file) {
        InputStream is = null;
        SmiMib mib = new SmiMib(new SmiOptions(), new SmiJavaCodeNamingStrategy("hanati.snmp.mibparser.mib"));
        CommonResult result = null;

        try {
            URL url = file.toURL();
            is = url.openStream();
            is = new BufferedInputStream(is);
            SMILexer lexer = new SMILexer(is);
            SMIParser parser = new SMIParser(lexer);
            parser.init(mib, url.toString());

            parser.grammar_check();
            result = new CommonResult(CommonResult.SUCCESS);

        } catch (TokenStreamException te) {
            String msg = "[Lex error] " + te.getMessage();
            result = new CommonResult(CommonResult.SYNTAX_ERROR, msg);
        } catch (RecognitionException re) {
            String msg = "[Parse error] " + exceptionHandler(re.getMessage(), re.getLine(), re.getColumn());
            result = new CommonResult(CommonResult.SYNTAX_ERROR, msg);
        } catch (IOException ie) {
            String msg = "[I/O error] " + ie.getMessage();
            result = new CommonResult(CommonResult.SYNTAX_ERROR, msg);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ioe) {
                    String msg = "[I/O error-2] " + ioe.getMessage();
                    result = new CommonResult(CommonResult.SYNTAX_ERROR, msg);
                }
            }
        }
        return result;
    }

    public CommonResult importValidator(File targetMib, SmiMib mib) {
        Set<String> list = new HashSet<>();
        CommonResult result;

        for (SmiModule module : mib.getModules()) {
            List<String> check = module.checkImports();
            if (!check.isEmpty()) {
                list.addAll(check);
            }
        }

        if (!list.isEmpty()) {
            result = new CommonResult(CommonResult.IMPORT_ERROR, list);
        } else {
            result = new CommonResult(CommonResult.SUCCESS);
        }

        return result;
    }

    private String exceptionHandler(String msg, int line, int column) {
        StringBuilder sb = new StringBuilder();
        sb.append(msg);
        sb.append(" (");
        sb.append("line ");
        sb.append(line);
        sb.append(":");
        sb.append(column);
        sb.append(")");

        return sb.toString();
    }
}
