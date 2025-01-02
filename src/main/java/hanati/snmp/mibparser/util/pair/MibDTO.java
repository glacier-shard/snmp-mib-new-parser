package hanati.snmp.mibparser.util.pair;

import java.util.List;

public class MibDTO {
    String oid;
    String name;
    String type;
    String syntax;
    String access;
    String mib;
    String status;
    String defval;
    String indexes;
    String description;
    Boolean isScalar;
    List<MibDTO> childList;

    public MibDTO(
            String oid,
            String name,
            String type,
            String syntax,
            String access,
            String mib,
            String status,
            String defval,
            String indexes,
            String description,
            Boolean isScalar,
            List<MibDTO> childList
    ) {
        this.oid = oid;
        this.name = name;
        this.type = type;
        this.syntax = syntax;
        this.access = access;
        this.mib = mib;
        this.status = status;
        this.defval = defval;
        this.indexes = indexes;
        this.description = description;
        this.isScalar = isScalar;
        this.childList = childList;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSyntax() {
        return syntax;
    }

    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getMib() {
        return mib;
    }

    public void setMib(String mib) {
        this.mib = mib;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDefval() {
        return defval;
    }

    public void setDefval(String defval) {
        this.defval = defval;
    }

    public String getIndexes() {
        return indexes;
    }

    public void setIndexes(String indexes) {
        this.indexes = indexes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsScalar() {
        return isScalar;
    }

    public void setIsScalar(Boolean scalar) {
        isScalar = scalar;
    }

    public List<MibDTO> getChildList() {
        return childList;
    }

    public void setChildList(List<MibDTO> childList) {
        this.childList = childList;
    }
}
