package hanati.snmp.mibparser.printer;

import hanati.snmp.mibparser.parser.SmiDefaultParser;
import hanati.snmp.mibparser.smi.*;
import hanati.snmp.mibparser.util.check.MibValidator;
import hanati.snmp.mibparser.util.pair.CommonResult;
import hanati.snmp.mibparser.util.pair.MibDTO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MibPrinter {
    private static SmiMib mibs;

    public CommonResult loadMibs (File uploadDir, File enterpriseDir, File standardDir) {
        try {
            Set<File> mibFiles = new HashSet<>();
            Set<File> uploadFiles = new HashSet<>();
            Optional.ofNullable(uploadDir).map(File::listFiles).ifPresent(files -> {
                mibFiles.addAll(List.of(files));
                uploadFiles.addAll(List.of(files));
            });
            Optional.ofNullable(enterpriseDir).map(File::listFiles).ifPresent(files -> mibFiles.addAll(List.of(files)));
            Optional.ofNullable(standardDir).map(File::listFiles).ifPresent(files -> mibFiles.addAll(List.of(files)));

            MibValidator validator = new MibValidator();
            for (File mib : uploadFiles) {
                CommonResult mibFileResult = validator.isMibFile(mib);
                if (mibFileResult.getResultCode() != CommonResult.SUCCESS) {
                    return mibFileResult;
                }
                CommonResult syntaxResult = validator.syntaxValidator(mib);
                if (syntaxResult.getResultCode() != CommonResult.SUCCESS) {
                    return syntaxResult;
                }
            }

            List<URL> mibUrls = new ArrayList<URL>();
            for(File mib : mibFiles){
                mibUrls.add(mib.toURI().toURL());
            }

            SmiDefaultParser parser = new SmiDefaultParser();
            parser.getFileParserPhase().setInputUrls(mibUrls);
            mibs = parser.parse();

        } catch (Exception e) {
            return new CommonResult(CommonResult.UNKNOWN_ERROR, e.getMessage());
        }

        return new CommonResult(CommonResult.SUCCESS);
    }

    public String getObjectNameByOID(int... oid) {
        String name = null;
        SmiOidNode node = mibs.findByOid(oid);

        if (node != null) {
            try {
                name = node.getValues().get(0).getId();
            } catch (AssertionError ae) {
                ae.printStackTrace();
            }
        }

        return name;
    }

    public String getMibTreeAll (String mibName, boolean useIndent) {
        SmiModule module = mibs.findModule(mibName);
        SmiMib mib = module.getMib();
        SmiOidNode node = mib.getRootNode();

        StringBuilder result = new StringBuilder();
        printTreeAll(result, mibName, node, 0, useIndent);

        return result.toString();
    }

    public void printTreeAll (StringBuilder result, String mibName, SmiOidNode node, int depth, boolean useIndent) {
        Collection<? extends SmiOidNode> childs = node.getChildren();
//        boolean printDesc = false;
        boolean printDesc = true;
        String format = "%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s";

        for (SmiOidNode child : childs) {
            List <SmiOidValue> values = child.getValues();
//            for (SmiOidValue var : values) {
            if (values.size()!=0) {
                SmiOidValue var = values.get(0);

                String name = var.getId();
                String oid = var.getOidStr();

                String line = "";
                if (var instanceof SmiTable) {

                    SmiTable st = (SmiTable) var;

                    String type = "TABLE";
                    String syntax = "SEQUENCE OF " + st.getType().getElementTypeToken().getValue();
                    String access = st.getAccessToken()!=null? st.getAccessToken().getId() : st.getMaxAccessToken().getId();
                    String mib = st.getModule().getId();
                    String status = st.getStatus().toString();
                    String defval = "";
                    String indexes = "";
                    String description = printDescription(st.getDescription(), printDesc);
                    line = String.format(format, depth, type, name, oid, mib, syntax, access, status, defval, indexes, description, "\n");

                } else if (var instanceof SmiRow) {

                    SmiRow sr = (SmiRow)var;

                    String type =  "ENTRY";

                    String syntax = sr.getType().getIdToken().getValue();
                    String access = sr.getAccessToken()!=null? sr.getAccessToken().getId() : sr.getMaxAccessToken().getId();
                    String mib = sr.getModule().getId();
                    String status = sr.getStatus().toString();
                    String defval = "";
                    String indexes = "";
                    String description = printDescription(sr.getDescription(), printDesc);

                    if(sr.getIndexes()!=null) {
                        List <SmiIndex> idx = sr.getIndexes();
                        StringBuilder sb = new StringBuilder();
                        for (SmiIndex index : idx) {
                            sb.append(", " + index.getColumn().getId());
                        }
                        indexes += sb.toString().replaceFirst(", ", "");
                    }

                    line = String.format(format, depth, type, name, oid, mib, syntax, access, status, defval, indexes, description, "\n");

                } else if (var instanceof SmiVariable) {
                    SmiVariable sv = (SmiVariable) var;

                    String type = name.contains("Index")? "INDEX":"LEAF";
                    String syntax = "" ;

                    if(sv.getType().getBaseType()!=null) {
                        syntax = sv.getType().getBaseType().getId();
                    } else if(sv.getType().getPrimitiveType()!=null){
                        syntax= sv.getType().getPrimitiveType().toString();
                    } else {
                        syntax = sv.getType().getIdToken().getValue();
                    }


                    // Define Syntax Range
//                    if( sv.getType().getSizeConstraints() != null ) {
//                        syntax = syntax + " (SIZE (" + sv.getType().getSizeConstraints().get(0).getMinValue() + ".." + sv.getType().getSizeConstraints().get(0).getMaxValue() + "))";
//                    }

                    String access = sv.getAccessToken()!=null? sv.getAccessToken().getId() : sv.getMaxAccessToken().getId();
                    String mib = sv.getModule().getId();
                    String status = sv.getStatus().toString();
                    String defval = "";
                    String indexes = "";
                    String description = printDescription(sv.getDescription(), printDesc);

                    if(sv.getDefaultValue() !=null) {
                        if(sv.getDefaultValue().getBigIntegerToken()!=null) {
                            defval = sv.getDefaultValue().getBigIntegerToken().getValue().toString();
                        } else if(sv.getDefaultValue().getBinaryStringToken()!=null) {
                            defval = sv.getDefaultValue().getBinaryStringValue();
                        } else if(sv.getDefaultValue().getHexStringToken()!=null) {
                            defval = sv.getDefaultValue().getHexStringToken().toString();
                        } else if(sv.getDefaultValue().getQuotedStringToken()!=null) {
                            defval = sv.getDefaultValue().getQuotedStringToken().getValue();
                        } else if(sv.getDefaultValue().getEnumValue()!=null) {
                            defval = sv.getDefaultValue().getEnumValue().getId();
                        }
                    }

                    line = String.format(format, depth, type, name, oid, mib, syntax, access, status, defval, indexes, description, "\n");

                } else if (var instanceof SmiOidValue) {
                    int num_child = child.getTotalChildCount();
                    String type = "DIRECTORY";
                    if (num_child > 0) {
                    } else {
                        type = "LEAF";
                    }

                    if ( var.getUserData()!=null && "NOTIFICATION-TYPE".equals(var.getUserData().get("objectType")) ) {
                        type = "TRAP";
                    }

                    String mib = var.getModule().getId();
                    line = String.format(format, depth, type, name, oid, mib, "", "", "", "", "", "", "\n");
                } else {
                    line = var.getClass().getName();
                    line = "?" + line +"\n";
                }
//                printMibObject(line, depth);
                if(useIndent) {
                    line = indent(line, depth);
                }
                result.append(line);
            }
            printTreeAll(result, mibName, child, depth + 1, useIndent);
        }
    }

    public void printMibObject (String str, int depth) {
        boolean useIndent = true;
//        boolean useIndent = false;
        if(str==null || str.equals("")) {
            return;
        }
        if(useIndent) {
            int indent = str.length() + (depth-1) * 4;
            String format = "%" + indent + "s";
            System.out.printf(format, str);
        } else {
            System.out.printf(str);
        }
    }

    public String printDescription(String desc, boolean check) {

        String result = "";
        if(check && desc != null) {
            result = desc.replaceAll("(\r\n|\r|\n|\n\r)", " ").replaceAll("\\s+", " ");
        }
        if(result.length()>100) {
            result = result.substring(0,100) + "...";
        }
        return result;
    }

    public String getMibTreePrivate (String mibName, boolean useIndent) {
        SmiModule module = mibs.findModule(mibName);
        SmiMib mib = module.getMib();
        SmiOidNode node = mib.getRootNode();

        StringBuilder result = new StringBuilder();
        printTreePrivate(result, mibName, node, 0, useIndent);

        return result.toString();
    }

    public void printTreePrivate (StringBuilder result, String mibName, SmiOidNode node, int depth, boolean useIndent) {
        Collection<? extends SmiOidNode> childs = node.getChildren();
//        boolean printDesc = false;
        boolean printDesc = true;
        boolean isPrivate = false;
        String format = "%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s";

        for (SmiOidNode child : childs) {
            List <SmiOidValue> values = child.getValues();
//            for (SmiOidValue var : values) {
            if (values.size()!=0) {
                SmiOidValue var = values.get(0);

                String name = var.getId();
                String oid = var.getOidStr();

                String line = "";
                if (var instanceof SmiTable) {

                    SmiTable st = (SmiTable) var;

                    String type = "TABLE";
                    String syntax = "SEQUENCE OF " + st.getType().getElementTypeToken().getValue();
                    String access = st.getAccessToken()!=null? st.getAccessToken().getId() : st.getMaxAccessToken().getId();
                    String mib = st.getModule().getId();
                    String status = st.getStatus().toString();
                    String defval = "";
                    String indexes = "";
                    String description = printDescription(st.getDescription(), printDesc);
                    line = String.format(format, depth, type, name, oid, mib, syntax, access, status, defval, indexes, description, "\n");

                } else if (var instanceof SmiRow) {

                    SmiRow sr = (SmiRow)var;

                    String type =  "ENTRY";

                    String syntax = sr.getType().getIdToken().getValue();
                    String access = sr.getAccessToken()!=null? sr.getAccessToken().getId() : sr.getMaxAccessToken().getId();
                    String mib = sr.getModule().getId();
                    String status = sr.getStatus().toString();
                    String defval = "";
                    String indexes = "";
                    String description = printDescription(sr.getDescription(), printDesc);

                    if(sr.getIndexes()!=null) {
                        List <SmiIndex> idx = sr.getIndexes();
                        StringBuilder sb = new StringBuilder();
                        for (SmiIndex index : idx) {
                            sb.append(", " + index.getColumn().getId());
                        }
                        indexes += sb.toString().replaceFirst(", ", "");
                    }

                    line = String.format(format, depth, type, name, oid, mib, syntax, access, status, defval, indexes, description, "\n");

                } else if (var instanceof SmiVariable) {
                    SmiVariable sv = (SmiVariable) var;

                    String type = name.contains("Index")? "INDEX":"LEAF";
                    String syntax = "" ;

                    if(sv.getType().getBaseType()!=null) {
                        syntax = sv.getType().getBaseType().getId();
                    } else if(sv.getType().getPrimitiveType()!=null){
                        syntax= sv.getType().getPrimitiveType().toString();
                    } else {
                        syntax = sv.getType().getIdToken().getValue();
                    }


                    // Define Syntax Range
//                    if( sv.getType().getSizeConstraints() != null ) {
//                        syntax = syntax + " (SIZE (" + sv.getType().getSizeConstraints().get(0).getMinValue() + ".." + sv.getType().getSizeConstraints().get(0).getMaxValue() + "))";
//                    }

                    String access = sv.getAccessToken()!=null? sv.getAccessToken().getId() : sv.getMaxAccessToken().getId();
                    String mib = sv.getModule().getId();
                    String status = sv.getStatus().toString();
                    String defval = "";
                    String indexes = "";
                    String description = printDescription(sv.getDescription(), printDesc);

                    if(sv.getDefaultValue() !=null) {
                        if(sv.getDefaultValue().getBigIntegerToken()!=null) {
                            defval = sv.getDefaultValue().getBigIntegerToken().getValue().toString();
                        } else if(sv.getDefaultValue().getBinaryStringToken()!=null) {
                            defval = sv.getDefaultValue().getBinaryStringValue();
                        } else if(sv.getDefaultValue().getHexStringToken()!=null) {
                            defval = sv.getDefaultValue().getHexStringToken().toString();
                        } else if(sv.getDefaultValue().getQuotedStringToken()!=null) {
                            defval = sv.getDefaultValue().getQuotedStringToken().getValue();
                        } else if(sv.getDefaultValue().getEnumValue()!=null) {
                            defval = sv.getDefaultValue().getEnumValue().getId();
                        }
                    }

                    line = String.format(format, depth, type, name, oid, mib, syntax, access, status, defval, indexes, description, "\n");

                } else if (var instanceof SmiOidValue) {
                    int num_child = child.getTotalChildCount();
//                    boolean check = false;
                    String type = "DIRECTORY";
                    if (num_child > 0) {
                    } else {
                        type = "LEAF";
                    }

                    if ( "private".equals(name) ) {
                        isPrivate = true;
                    }

                    if( isPrivate || (!isPrivate && findParent(child, "private")) ) {
                        if(!separatePrivate(child, mibName)) {
                            continue;
                        }
                    }

                    if ( var.getUserData()!=null && "NOTIFICATION-TYPE".equals(var.getUserData().get("objectType")) ) {
                        type = "TRAP";
                    }

                    String mib = var.getModule().getId();

                    line = String.format(format, depth, type, name, oid, mib, "", "", "", "", "", "", "\n");

                } else {
                    line = var.getClass().getName();
                    line = "?" + line +"\n";
                }
//                printMibObject(line, depth);
                if(useIndent) {
                    line = indent(line, depth);
                }
                result.append(line);
            }
            printTreePrivate(result, mibName, child, depth + 1, useIndent);
        }
    }

    public boolean separatePrivate (SmiOidNode node, String mib) {
        boolean check = false;
        Collection<? extends SmiOidNode> childs = node.getChildren();

        if(childs.size()!=0 && childs != null) {
            for(SmiOidNode child : childs) {
                List<SmiOidValue> values = child.getValues();

                if(values.size()!=0) {
                    check = checkModule(child, mib);
                    if(check) {
                        return true;
                    }
                }
                check = separatePrivate(child, mib);
            }
        } else {
            List<SmiOidValue> values = node.getValues();
            if(values.size()!=0) {
                check = checkModule(node, mib);
                if(check) {
                    return true;
                }
            }
        }

        return check;
    }

    public boolean checkModule(SmiOidNode node, String mib) {
        for(SmiValue sv : node.getValues()) {
            if(mib.equals(sv.getModule().getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean findParent(SmiOidNode node, String str) {
        SmiOidNode sn = node.getParent();
        do{
            if(sn.getValues().size()==0) {
                return false;
            } else {
                SmiOidValue sv = sn.getValues().get(0);
                if( str.equals(sv.getId()) ) {
                    return true;
                }
            }
            sn = sn.getParent();
        } while(sn != null);
        return false;
    }

    public String indent(String str, int depth) {
        int indent = str.length() + (depth-1) * 4;
        String format = "%" + indent + "s";
        return String.format(format, str);
    }

    public CommonResult getMibMap (File targetFile, boolean isForAll) {
        MibValidator validator = new MibValidator();
        CommonResult importResult = validator.importValidator(targetFile, mibs);
        if (importResult.getResultCode() != CommonResult.SUCCESS) {
            StringBuilder msg = new StringBuilder();
            Object dataObj = importResult.getData();
            if (dataObj instanceof Set<?> dataSet) {
                String joinedData = dataSet.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));
                msg.append(joinedData);
            }
            return new CommonResult(CommonResult.IMPORT_ERROR, msg.toString());
        }

        String str = "";
        String strPrev = "";
        String mibName = "";

        try ( BufferedReader in = new BufferedReader(new FileReader(targetFile)) ) {
            do {
                String line = in.readLine();
                strPrev = str;
                if (!Objects.equals(line, "")) {
                    str = line;
                }
                str = str.trim();
            } while (!str.contains("DEFINITIONS"));

            StringTokenizer stPrev = new StringTokenizer(strPrev, " ");
            StringTokenizer st = new StringTokenizer(str, " ");
            mibName = st.nextToken();
            if ("DEFINITIONS".equals(mibName)) {
                mibName = stPrev.nextToken();
            }
        }
        catch (Exception e) {
            return new CommonResult(CommonResult.UNKNOWN_ERROR, e.getMessage());
        }

        SmiModule module = mibs.findModule(mibName);
        List<MibDTO> ast = new ArrayList<>();
        Set<String> oidSet = new HashSet<>();

        if (isForAll) {
            SmiMib mib = module.getMib();
            SmiOidNode node = mib.getRootNode();
            MibDTO mibDTO = mibAstToMap(node, 0, module);
            ast.add(mibDTO);
        }
        else {
            Map<String, SmiOidValue> oidValueMap = module.getOidValueMap();
            oidValueMap.forEach((key, value) -> {
                SmiOidNode node = value.getNode();
                checkIfVisited(node, 0, oidSet);
            });

            oidValueMap.forEach((key, value) -> {
                SmiOidNode node = value.getNode();
                if (!oidSet.contains(node.getOidStr())) {
                    MibDTO mibDTO = mibAstToMap(node, 0, module);
                    ast.add(mibDTO);
                }
            });
        }

        return new CommonResult(CommonResult.SUCCESS, ast);
    }

    public void checkIfVisited(SmiOidNode node, int depth, Set<String> oidSet) {
        node.getChildren().forEach(child -> {
            String oid = child.getOidStr();
            oidSet.add(oid);
            checkIfVisited(child, depth + 1, oidSet);
        });
    }

    public MibDTO mibAstToMap(SmiOidNode node, int depth, SmiModule module) {
        Collection<? extends  SmiOidNode> children = node.getChildren();
        List<MibDTO> list = new ArrayList<>();

        for (SmiOidNode child : children) {
            MibDTO childTree = mibAstToMap(child, depth + 1, module);
            if (childTree != null) {
                list.add(childTree);
            }
        }

        List<SmiOidValue> values = node.getValues();
        String name = "";
        String oid = "";
        Boolean isScalar = null;
        String type = "";
        String syntax = "";
        String access = "";
        String mib = "";
        String status = "";
        String defval = "";
        String indexes = "";
        String description = "";

        if (!values.isEmpty()) {
            SmiOidValue var = values.get(0);

            name = var.getId();
            oid = var.getOidStr();
            isScalar = module.findScalar(name) != null;
            boolean printDesc = true;

            if (var instanceof SmiTable) {
                SmiTable st = (SmiTable) var;

                type = "TABLE";
                syntax = "SEQUENCE OF " + st.getType().getElementTypeToken().getValue();
                access = st.getAccessToken()!=null? st.getAccessToken().getId() : st.getMaxAccessToken().getId();
                mib = st.getModule().getId();
                status = st.getStatus().toString();
                description = printDescription(st.getDescription(), printDesc);
            }
            else if (var instanceof SmiRow) {
                SmiRow sr = (SmiRow) var;

                type =  "ENTRY";
                syntax = sr.getType().getIdToken().getValue();
                access = sr.getAccessToken()!=null? sr.getAccessToken().getId() : sr.getMaxAccessToken().getId();
                mib = sr.getModule().getId();
                status = sr.getStatus().toString();
                description = printDescription(sr.getDescription(), printDesc);

                if(sr.getIndexes()!=null) {
                    List <SmiIndex> idx = sr.getIndexes();
                    StringBuilder sb = new StringBuilder();
                    for (SmiIndex index : idx) {
                        sb.append(", ").append(index.getColumn().getId());
                    }
                    indexes += sb.toString().replaceFirst(", ", "");
                }
            }
            else if (var instanceof SmiVariable) {
                SmiVariable sv = (SmiVariable) var;

                type = name.contains("Index")? "INDEX":"LEAF";
                if(sv.getType().getBaseType()!=null) {
                    syntax = sv.getType().getBaseType().getId();
                } else if(sv.getType().getPrimitiveType()!=null){
                    syntax= sv.getType().getPrimitiveType().toString();
                } else {
                    syntax = sv.getType().getIdToken().getValue();
                }
                access = sv.getAccessToken()!=null? sv.getAccessToken().getId() : sv.getMaxAccessToken().getId();
                mib = sv.getModule().getId();
                status = sv.getStatus().toString();
                description = printDescription(sv.getDescription(), printDesc);
                if(sv.getDefaultValue() !=null) {
                    if(sv.getDefaultValue().getBigIntegerToken()!=null) {
                        defval = sv.getDefaultValue().getBigIntegerToken().getValue().toString();
                    } else if(sv.getDefaultValue().getBinaryStringToken()!=null) {
                        defval = sv.getDefaultValue().getBinaryStringValue();
                    } else if(sv.getDefaultValue().getHexStringToken()!=null) {
                        defval = sv.getDefaultValue().getHexStringToken().toString();
                    } else if(sv.getDefaultValue().getQuotedStringToken()!=null) {
                        defval = sv.getDefaultValue().getQuotedStringToken().getValue();
                    } else if(sv.getDefaultValue().getEnumValue()!=null) {
                        defval = sv.getDefaultValue().getEnumValue().getId();
                    }
                }
            }
            else if (var instanceof SmiOidValue) {
                int num_child = node.getTotalChildCount();
                type = "DIRECTORY";
                if (num_child > 0) {
                } else {
                    type = "LEAF";
                }
                if ( var.getUserData()!=null && "NOTIFICATION-TYPE".equals(var.getUserData().get("objectType")) ) {
                    type = "TRAP";
                }
                mib = var.getModule().getId();
            }
        }
        return new MibDTO(oid, name, type, syntax, access, mib, status, defval, indexes, description, isScalar, list);
    }
}
