package hanati.snmp.mibparser.util.url;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileURLListFactory extends AbstractURLListFactory {
    public FileURLListFactory() {
    }

    public FileURLListFactory(File rootDir) {
        this(rootDir.getAbsolutePath());
    }

    public FileURLListFactory(String rootPath) {
        super(rootPath);
    }

    public FileURLListFactory(File rootDir, List<String> children) {
        super(rootDir.getAbsolutePath(), children);
    }

    public FileURLListFactory(String rootPath, List<String> children) {
        super(rootPath, children);
    }

    public List<URL> create() throws Exception {
        List<URL> result = new ArrayList();
        File dir = new File(this.rootPath);
        Iterator i$ = this.children.iterator();

        while(i$.hasNext()) {
            String child = (String)i$.next();
            File file = new File(dir, child);
            if (!file.exists()) {
                throw new IllegalStateException("File doesn't exist: " + file);
            }

            result.add(file.toURL());
        }

        return result;
    }
}
