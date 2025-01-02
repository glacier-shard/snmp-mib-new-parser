import com.fasterxml.jackson.databind.ObjectMapper;
import hanati.snmp.mibparser.printer.MibPrinter;
import hanati.snmp.mibparser.util.pair.CommonResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        int[] oid = {1, 3, 6, 1, 1}; // ?
        MibPrinter parser = new MibPrinter();

//        try {
////            File f = new File("mibs");
//            File f = new File("mibs2");
////            File f = new File("sample");
//
//            parser.loadMibs(f);
////            String name = parser.getObjectNameByOID(oid);
////            System.out.println(name);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            File baseDir = new File("mibs");
            File newDir = new File("mibs2");

//            parser.loadMibs(baseDir, newDir);
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean useIndent = true;

        CommonResult result = null;
//        String type = "all";
//        String type = "priv";
        String type = "map";
//        String type = "valid";
//        String type = "import";

        String s = "";
        switch (type) {
            case "all":
                s = parser.getMibTreeAll("JUNIPER-ANALYZER-MIB", useIndent);
                System.out.println(s);
                break;
            case "priv":
                s = parser.getMibTreePrivate("IPTIME-ROUTER-MIB", useIndent);
                System.out.println(s);
                break;
            case "map":
//                Map<Object, Object> map = parser.getMibMap("mib-jnx-analyzer.txt");
//                Map<Object, Object> map = parser.getMibMap(new File("C:\\project\\code\\mibparser2\\mibs2\\mib-jnx-analyzer.txt"));
//                Map<Object, Object> map = parser.getMibMap(new File("/Users/ihyeseong/IdeaProjects/mib-parser2/newMibs/IPTIME-ROUTER-MIB.txt"));
//                String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(map);

//                System.out.printf(json);


                String filename = "/Users/ihyeseong/IdeaProjects/mib-parser2/newMibs/test.txt";
                try {
                    File file = new File(filename);
                    FileWriter fw = new FileWriter(file, true);

//                    fw.write(json);
                    fw.flush();
                    fw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
//            case "valid":
//                MibValidator test = new MibValidator();
//                File f = new File("C:/project/code/mibparser2/mibs2/IPTIME-ROUTER-MIB.txt");
////                File f = new File("C:/project/code/mibparser2/sample/EVNTAGENT-MIB.mib");
//                result = test.syntaxValidator(f);
//
//                System.out.println("Syntax Validation >> " + result.getResultCode());
//                System.out.println("Message >> " + Optional.ofNullable(result.getMsg()).orElse(""));
//                break;
//            case "import":
//                test = new MibValidator();
//                File baseDir = new File("C:/project/code/mibparser2/mibs2");
//
////                File newDir = new File("C:/project/code/mibparser2/mibs/IPTIME-ROUTER-MIB.txt");
//                File newDir = new File("C:/project/code/mibparser2/mibs2");
////                File newDir = new File("C:/project/code/mibparser2/sample/EVNTAGENT-MIB.mib");
//                result = test.importValidator(baseDir, newDir);
//
//                switch (result.getResultCode()) {
//                    case CommonResult.SUCCESS:
//                        System.out.println("Success");
//                        break;
//                    case CommonResult.FAIL:
//                        Set<String> data = (Set<String>) result.getData();
//                        System.out.println("Import Fail");
//                        for(String str : data) {
//                            System.out.println(str);
//                        }
//                        break;
//                    case CommonResult.ERROR:
//                        System.out.println(result.getMsg());
//                        break;
//                }
//                break;
        }

    }
//
//    public static void main(String[] args) {
//
//        MibValidator validator = new MibValidator();
//        CommonResult result = null;
//
//        try {
//            File file = new File("mibs/IPTIME-ROUTER-MIB.txt");
//            result = validator.syntaxValidator(file);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        switch(result.getResultCode()) {
//            case CommonResult.SUCCESS:
//                System.out.println("success");
//                break;
//            case CommonResult.FAIL:
//                String msg = result.getMsg();
//                System.out.println(msg);
//                break;
//        }
//    }
}