import hanati.snmp.mibparser.printer.MibPrinter;
import hanati.snmp.mibparser.util.pair.CommonResult;

import java.io.File;
import java.net.MalformedURLException;

public class MainTester {
    public static void main(String[] args) {
        final String UPLOAD_DIR_NAME = "uploads";
        final String BASE_DIR_NAME = "base";
        final String FILE_NAME = "CISCO-MIB";

        MibPrinter parser = new MibPrinter();
        File uploadDir = new File(UPLOAD_DIR_NAME);
        File standardDir = new File(BASE_DIR_NAME);

        CommonResult loadResult = parser.loadMibs(uploadDir, null, standardDir);
        CommonResult parseResult = parser.getMibMap(new File(UPLOAD_DIR_NAME + "/" + FILE_NAME + ".txt"), false);
        System.out.println();
    }
}
