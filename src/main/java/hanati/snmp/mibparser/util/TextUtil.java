package hanati.snmp.mibparser.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TextUtil {
    private static final Logger LOG = LogManager.getLogger(TextUtil.class);
    private static final String JAVA_KEYWORDS_PATH = "/hanati/snmp/mibparser/util/JavaKeywords.txt";
    static final String KEYWORD_PREFIX = "_";
    static Map<String, String> keywords = makeKeyWordMap();

    public TextUtil() {
    }

    public static String makeCodeId(String id) {
        return makeCodeId(id, false);
    }

    public static String makeCodeId(String id, boolean isTypeName) {
        if (id != null && id.length() != 0) {
            StringBuilder buf = null;

            for(int i = 0; i < id.length(); ++i) {
                if (!Character.isJavaIdentifierPart(id.charAt(i))) {
                    if (buf == null) {
                        buf = new StringBuilder(id);
                    }

                    buf.setCharAt(i, '_');
                }
            }

            if (buf != null) {
                if (isTypeName && Character.isLowerCase(buf.charAt(0))) {
                    buf.setCharAt(0, Character.toUpperCase(buf.charAt(0)));
                }

                id = buf.toString();
            } else if (isTypeName && Character.isLowerCase(id.charAt(0))) {
                id = Character.toUpperCase(id.charAt(0)) + id.substring(1);
            }

            if (!Character.isJavaIdentifierStart(id.charAt(0))) {
                id = "_" + id;
            }
        } else {
            id = String.valueOf("_");
        }

        Object o = keywords.get(id);
        if (o != null) {
            id = (String)o;
        }

        return id;
    }

    public static String makeTypeName(String str) {
        return ucFirst(makeCodeId(str, true));
    }

    private static void addKeyWord(Map<String, String> m, String kw) {
        m.put(kw, "_" + kw);
    }

    private static Map<String, String> makeKeyWordMap() {
        BufferedReader reader = null;

        try {
            Map<String, String> m = new HashMap();
            reader = new BufferedReader(new InputStreamReader(TextUtil.class.getResourceAsStream("/hanati/snmp/mibparser/util/JavaKeywords.txt")));

            for(String keyword = reader.readLine(); keyword != null; keyword = reader.readLine()) {
                addKeyWord(m, keyword);
            }

            HashMap var3 = (HashMap) m;
            return var3;
        } catch (Throwable var12) {
            LOG.error(var12.getMessage(), var12);
            throw new RuntimeException(var12);
        } finally {
            try {
                reader.close();
            } catch (IOException var11) {
                LOG.error(var11.getMessage(), var11);
            }

        }
    }

    public static String ucFirst(String str) {
        String result = str;
        if (str.length() > 0 && Character.isLowerCase(str.charAt(0))) {
            StringBuilder b = new StringBuilder(str);
            b.setCharAt(0, Character.toUpperCase(str.charAt(0)));
            result = b.toString();
        }

        return result;
    }

    public static String lcFirst(String str) {
        String result = str;
        if (str.length() > 0 && Character.isUpperCase(str.charAt(0))) {
            StringBuilder b = new StringBuilder(str);
            b.setCharAt(0, Character.toLowerCase(str.charAt(0)));
            result = b.toString();
        }

        return result;
    }

    public static String getPath(Package pkg) {
        LOG.debug("package: " + pkg);
        LOG.debug("getPath() for: " + pkg.getName());
        return "/" + pkg.getName().replace('.', '/');
    }

    public static String deleteChar(String str, char c) {
        if (str.indexOf(c) < 0) {
            return str;
        } else {
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < str.length(); ++i) {
                if (str.charAt(i) != c) {
                    sb.append(str.charAt(i));
                }
            }

            return sb.toString();
        }
    }
}
