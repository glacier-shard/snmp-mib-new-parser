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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MibValidator {

    public boolean isMibFile(File file) {
        if (!file.canRead() || !file.isFile()) {
            return false;
        }
        try (
                BufferedReader in = new BufferedReader(new FileReader(file))
        ) {
            while (true) {
                String str = in.readLine();
                if (str == null) {
                    break;
                }
                str = str.trim();
                if (!str.isEmpty() && !str.startsWith("--")) {
                    return str.contains("DEFINITIONS");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
            result = new CommonResult(CommonResult.FAIL, msg);
        } catch (RecognitionException re) {
            String msg = "[Parse error] " + exceptionHandler(re.getMessage(), re.getLine(), re.getColumn());
            result = new CommonResult(CommonResult.FAIL, msg);
        } catch (IOException ie) {
            String msg = "[I/O error] " + ie.getMessage();
            result = new CommonResult(CommonResult.FAIL, msg);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ioe) {
                    String msg = "[I/O error-2] " + ioe.getMessage();
                    result = new CommonResult(CommonResult.FAIL, msg);
                }
            }
        }
        return result;
    }

    public CommonResult importValidator(File baseDir, File newDir) {
        InputStream is = null;
        SmiMib mib = new SmiMib(new SmiOptions(), new SmiJavaCodeNamingStrategy("hanati.snmp.mibparser.mib"));
        Set<String> list = new HashSet<>();
        CommonResult result = null;

        try {
            Set<File> mibFiles = new HashSet<>();
            mibFiles.addAll(List.of(baseDir.listFiles()));
            mibFiles.addAll(List.of(newDir.listFiles()));

            for (File f : mibFiles) {
                URL url = f.toURL();
                is = url.openStream();
                is = new BufferedInputStream(is);
                SMILexer lexer = new SMILexer(is);
                SMIParser parser = new SMIParser(lexer);
                parser.init(mib, url.toString());

                parser.external_module_check();
            }

//            this.defineMissingSymbols(mib);
            mib.fillTables();
            mib.defineMissingStandardOids();

            Iterator i$ = mib.getModules().iterator();
            while(i$.hasNext()) {
                SmiModule module = (SmiModule)i$.next();
                List<String> check = module.checkImports();
                if(!check.isEmpty()) {
                    list.addAll(check);
                }
            }

            if(list.size()!=0) {
                result = new CommonResult(CommonResult.FAIL, list);
            } else {
                result = new CommonResult(CommonResult.SUCCESS);
            }

        } catch (TokenStreamException te) {
            String msg = "[Lex error] " + te.getMessage();
            result = new CommonResult(CommonResult.ERROR, msg);
        } catch (RecognitionException re) {
            String msg = "[Parse error] " + exceptionHandler(re.getMessage(), re.getLine(), re.getColumn());
            result = new CommonResult(CommonResult.ERROR, msg);
        } catch (IOException ie) {
            String msg = "[I/O error] " + ie.getMessage();
            result = new CommonResult(CommonResult.ERROR, msg);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ioe) {
                    String msg = "[I/O error-2] " + ioe.getMessage();
                    result = new CommonResult(CommonResult.ERROR, msg);
                }
            }
        }
//        return list;
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
