package hanati.snmp.mibparser.util.url;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClassPathURLListFactory extends AbstractURLListFactory {
    protected ClassLoader classLoader;

    public ClassPathURLListFactory() {
    }

    public ClassPathURLListFactory(String rootPath) {
        super(rootPath);
    }

    public ClassPathURLListFactory(String rootPath, List<String> children) {
        super(rootPath, children);
    }

    public ClassLoader getClassLoader() {
        return this.classLoader != null ? this.classLoader : Thread.currentThread().getContextClassLoader();
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public List<URL> create() {
        List<URL> result = new ArrayList();
        Iterator i$ = this.children.iterator();

        while(i$.hasNext()) {
            String child = (String)i$.next();
            String path = this.getRootPath() + child;
            URL url = this.getClassLoader().getResource(path);
            if (url == null) {
                throw new IllegalStateException("Classpath resource doesn't exist (perhaps you are missing a slash or have one too much at the beginning?): " + path);
            }

            result.add(url);
        }

        return result;
    }
}
