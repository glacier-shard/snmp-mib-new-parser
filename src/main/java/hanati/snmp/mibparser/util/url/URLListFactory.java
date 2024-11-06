package hanati.snmp.mibparser.util.url;

import java.net.URL;
import java.util.List;

public interface URLListFactory {
    List<URL> create() throws Exception;
}
