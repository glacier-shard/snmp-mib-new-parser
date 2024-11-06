package hanati.snmp.mibparser.smi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import hanati.snmp.mibparser.util.multimap.GenMultiMap;
import org.apache.commons.collections.map.MultiValueMap;

class SmiSymbolMapImpl<T extends SmiSymbol> extends GenMultiMap<String, T> implements SmiSymbolMap<T> {
    private final Class<T> symbolClass;
    private final Map<String, SmiModule> moduleMap;

    public SmiSymbolMapImpl(Class<T> symbolClass, Map<String, SmiModule> moduleMap) {
        super(MultiValueMap.decorate(new HashMap(), ArrayList.class));
        this.symbolClass = symbolClass;
        this.moduleMap = moduleMap;
    }

    public T find(String symbolId) throws IllegalArgumentException {
        return (T) this.getOne(symbolId);
    }

    public T find(String moduleId, String symbolId) throws IllegalArgumentException {
        if (moduleId != null) {
            SmiModule module = (SmiModule)this.moduleMap.get(moduleId);
            if (module == null) {
                throw new IllegalArgumentException("Module " + moduleId + " could not be found.");
            } else {
                SmiSymbol symbol = module.findSymbol(symbolId);
                if (symbol == null) {
                    return null;
                } else {
                    return this.symbolClass.isAssignableFrom(symbol.getClass()) ? (T) this.symbolClass.cast(symbol) : null;
                }
            }
        } else {
            return (T) this.getOne(symbolId);
        }
    }

    public List<T> findAll(String symbolId) {
        return this.getAll(symbolId);
    }

    public Collection<T> getAll() {
        return this.multiMap.values();
    }

    public Iterator<T> iterator() {
        return this.multiMap.values().iterator();
    }
}
