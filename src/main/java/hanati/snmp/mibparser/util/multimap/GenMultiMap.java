package hanati.snmp.mibparser.util.multimap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;

public class GenMultiMap<K, V> implements Serializable {
    static final long serialVersionUID = 1L;
    protected MultiMap multiMap;

    public static <K, V> GenMultiMap<K, V> hashMap() {
        return new GenMultiMap(MultiValueMap.decorate(new HashMap(), ArrayList.class));
    }

    public static <K, V> GenMultiMap<K, V> treeMap() {
        return new GenMultiMap(MultiValueMap.decorate(new TreeMap(), ArrayList.class));
    }

    public GenMultiMap(MultiMap impl) {
        this.multiMap = impl;
    }

    public int size() {
        return this.multiMap.values().size();
    }

    public boolean isEmpty() {
        return this.multiMap.isEmpty();
    }

    public boolean containsKey(K key) {
        return this.multiMap.containsKey(key);
    }

    public boolean containsValue(V value) {
        return this.multiMap.containsValue(value);
    }

    public List<V> getAll(K key) {
        List<V> result = (List)this.multiMap.get(key);
        if (result == null) {
            result = Collections.emptyList();
        }

        return result;
    }

    public V getOne(K key) throws IllegalArgumentException {
        List<V> all = (List)this.multiMap.get(key);
        if (all == null) {
            return null;
        } else if (all.size() > 1) {
            throw new IllegalArgumentException("More than one element was found for key: " + key);
        } else {
            return all.get(0);
        }
    }

    public List<V> put(K key, Collection<V> value) {
        List<V> result = new ArrayList();
        Iterator i$ = value.iterator();

        while(i$.hasNext()) {
            V v = (V) i$.next();
            Object insertedObject = this.multiMap.put(key, v);
            if (insertedObject != null) {
                result.add((V) insertedObject);
            }
        }

        return result;
    }

    public V put(K key, V value) {
        return (V) this.multiMap.put(key, value);
    }

    public Collection<V> remove(K key) {
        return (Collection)this.multiMap.remove(key);
    }

    public void putAllMultiMap(Map<? extends K, ? extends Collection<V>> t) {
        this.multiMap.putAll(t);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        Iterator i$ = map.entrySet().iterator();

        while(i$.hasNext()) {
            Map.Entry<? extends K, ? extends V> entry = (Map.Entry)i$.next();
            this.put(entry.getKey(), entry.getValue());
        }

    }

    public void clear() {
        this.multiMap.clear();
    }

    public Set<K> keySet() {
        return this.multiMap.keySet();
    }

    public Collection<V> values() {
        return this.multiMap.values();
    }

    public Set<Map.Entry<K, List<V>>> entrySet() {
        return this.multiMap.entrySet();
    }
}
