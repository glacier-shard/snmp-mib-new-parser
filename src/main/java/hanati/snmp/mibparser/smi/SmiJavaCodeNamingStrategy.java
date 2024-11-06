package hanati.snmp.mibparser.smi;

public class SmiJavaCodeNamingStrategy implements SmiCodeNamingStrategy {
    public static final String ATTR_OIDS = "AttrOids";
    private String packagePrefix_;

    public SmiJavaCodeNamingStrategy(String packagePrefix) {
        this.packagePrefix_ = packagePrefix;
    }

    public String getModuleId(SmiModule module) {
        StringBuilder result = new StringBuilder();
        String[] parts = module.getId().split("[^_0-9A-Za-z]");
        String[] arr$ = parts;
        int len$ = parts.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String str = arr$[i$];
            result.append(SmiUtil.ucFirst(str.toLowerCase()));
        }

        return result.toString();
    }

    public String getFullModuleId(SmiModule module) {
        return this.packagePrefix_ + "." + this.getModuleId(module).toLowerCase();
    }

    public String getCodeConstantId(SmiVariable variable) {
        return this.makeConstant(variable.getId());
    }

    public String getFullCodeConstantId(SmiVariable variable) {
        return variable.getModule().getFullVariableOidClassId() + "." + variable.getCodeConstantId();
    }

    public String makeConstant(String str) {
        StringBuilder result = new StringBuilder();
        int i = 0;

        String prev;
        Category cat;
        String current;
        for(prev = null; i < str.length(); prev = this.processPrevious(result, prev, cat, current)) {
            cat = this.getCategory(str.charAt(i));

            int j;
            for(j = i + 1; j < str.length() && this.getCategory(str.charAt(j)) == cat; ++j) {
            }

            current = str.substring(i, j);
            i = j;
        }

        this.append(result, prev, (String)null);
        return result.toString();
    }

    private String processPrevious(StringBuilder result, String prev, Category cat, String current) {
        String newPrev = null;
        if (prev == null) {
            newPrev = current;
        } else {
            switch (this.getCategory(prev.charAt(0))) {
                case DIGIT:
                    newPrev = this.append(result, prev, current);
                    break;
                case UPPER:
                    if (cat == SmiJavaCodeNamingStrategy.Category.LOWER) {
                        if (prev.length() == 1) {
                            newPrev = prev + current;
                        } else {
                            current = prev.charAt(prev.length() - 1) + current;
                            prev = prev.substring(0, prev.length() - 1);
                            newPrev = this.append(result, prev, current);
                        }
                    } else {
                        newPrev = this.append(result, prev, current);
                    }
                    break;
                case LOWER:
                    newPrev = this.append(result, prev, current);
                    break;
                case OTHER:
                    newPrev = current;
            }
        }

        return newPrev;
    }

    private String append(StringBuilder result, String prev, String current) {
        if (result.length() != 0) {
            result.append('_');
        }

        result.append(prev.toUpperCase());
        return current;
    }

    private Category getCategory(char ch) {
        if (Character.isUpperCase(ch)) {
            return SmiJavaCodeNamingStrategy.Category.UPPER;
        } else if (Character.isLowerCase(ch)) {
            return SmiJavaCodeNamingStrategy.Category.LOWER;
        } else {
            return Character.isDigit(ch) ? SmiJavaCodeNamingStrategy.Category.DIGIT : SmiJavaCodeNamingStrategy.Category.OTHER;
        }
    }

    public String getTypeId(SmiType type) {
        return type.getId();
    }

    public String getSingleVariableEnumId(SmiVariable attr) {
        return SmiUtil.ucFirst(attr.getCodeId());
    }

    public String getVariableId(SmiVariable variable) {
        return variable.getId();
    }

    public String getEnumValueId(SmiNamedNumber ev) {
        return this.makeConstant(ev.getId());
    }

    public String getRequestMethodId(SmiVariable attr) {
        return "req" + SmiUtil.ucFirst(attr.getCodeId());
    }

    public String getGetterMethodId(SmiVariable attr) {
        return "get" + SmiUtil.ucFirst(attr.getCodeId());
    }

    public String getSetterMethodId(SmiVariable attr) {
        return "set" + SmiUtil.ucFirst(attr.getCodeId());
    }

    public String getFullCodeId(SmiSymbol symbol) {
        return symbol.getModule().getFullCodeId() + "." + symbol.getCodeId();
    }

    public String getTableId(SmiTable table) {
        return table.getId();
    }

    public String getFullVariableOidClassId(SmiModule module) {
        return module.getFullCodeId() + "." + module.getVariableOidClassId();
    }

    public String getVariableOidClassId(SmiModule module) {
        return module.getCodeId().toUpperCase() + "AttrOids";
    }

    private static enum Category {
        UPPER,
        LOWER,
        DIGIT,
        OTHER;

        private Category() {
        }
    }
}
