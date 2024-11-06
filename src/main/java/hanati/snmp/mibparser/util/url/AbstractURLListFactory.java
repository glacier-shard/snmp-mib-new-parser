package hanati.snmp.mibparser.util.url;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractURLListFactory implements URLListFactory {
    protected String rootPath;
    protected List<String> children;

    public AbstractURLListFactory() {
        this("");
    }

    public AbstractURLListFactory(String rootPath) {
        this(rootPath, new ArrayList());
    }

    public AbstractURLListFactory(String rootPath, List<String> children) {
        this.rootPath = rootPath;
        this.children = children;
    }

    public String getRootPath() {
        return this.rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public List<String> getChildren() {
        return this.children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    public void add(String child) {
        this.getChildren().add(child);
    }
}
