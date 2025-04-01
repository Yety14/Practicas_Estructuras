package es.ubu.gii.edat.pract03;

import java.util.*;

public class MultiMapa<K, V> extends AbstractMap<K, V> {
	
    private final Map<K, V> mapa;  

    public MultiMapa() {
        this.mapa = new HashMap<>();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entries = new HashSet<>();
        for (Map.Entry<K, V> entry : mapa.entrySet()) {
            for (V value : entry.getValue()) {
                entries.add(new AbstractMap.SimpleEntry<>(entry.getKey(), value));
            }
        }
        return entries;
    }

    @Override
    public V put(K key, V value) {
        mapa.putIfAbsent(key, new ArrayList<>());
        mapa.get(key).add(value);
        return value;
    }

    public void putAllMappings(K key, List<V> values) {
        mapa.putIfAbsent(key, new ArrayList<>());
        mapa.get(key).addAll(values);
    }

    public List<V> getAllMappings(Object key) {
        return mapa.getOrDefault(key, new ArrayList<>());
    }

    public List<V> removeAllMappings(Object key) {
        return mapa.remove(key);
    }
    
    @Override
    public void clear() {
        this.mapa.clear();
    }
    
    @Override
    public V remove(Object key) {
        V valor = mapa.remove(key);
        return valor; 
    }

//	@Override
//	public V remove(Object key) {
//	    V values = mapa.remove(key);
//	    return values != null && !values.isEmpty() ? values.get(0) : null;
//	}

    @Override
    public boolean containsValue(Object value) {
        for (List<V> values : mapa.values()) {
            if (values.contains(value)) {
                return true;
            }
        }
        return false;
    }
   
    public V getFirstMapping(K key) {
        List<V> values = mapa.get(key);
        if (values != null && !values.isEmpty()) {
            return values.get(0); // Retorna el primer valor de la lista
        }
        return null; // Retorna null si no se encuentra la clave o la lista está vacía
    }

}
