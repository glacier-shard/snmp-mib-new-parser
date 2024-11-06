package hanati.snmp.mibparser.util.url;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CompositeURLListFactory implements URLListFactory {
    private List<URLListFactory> children;

    public CompositeURLListFactory() {
        this((List)(new ArrayList()));
    }

    public CompositeURLListFactory(List<URLListFactory> children) {
        this.children = children;
    }

    public CompositeURLListFactory(URLListFactory... urlListFactories) {
        this(Arrays.asList(urlListFactories));
    }

    public List<URLListFactory> getChildren() {
        return this.children;
    }

    public void setChildren(List<URLListFactory> children) {
        this.children = children;
    }

    public List<URL> create() throws Exception {
        List<URL> result = new ArrayList();
        Iterator i$ = this.children.iterator();

        while(i$.hasNext()) {
            URLListFactory child = (URLListFactory)i$.next();
            result.addAll(child.create());
        }

        return result;
    }
}
