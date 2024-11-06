package hanati.snmp.mibparser.smi;

import java.util.Collection;
import java.util.List;

public interface SmiSymbolMap<T extends SmiSymbol> extends Iterable<T> {
    T find(String var1) throws IllegalArgumentException;

    T find(String var1, String var2) throws IllegalArgumentException;

    List<T> findAll(String var1);

    Collection<T> getAll();

    int size();

    boolean isEmpty();
}
