package es.ubu.gii.edat.pract03;

import java.util.*;

public class MultiMapa<K, V> extends AbstractMap<K, Collection<V>> {
    private final Map<K, Collection<V>> mapa;

    public MultiMapa() {
        this.mapa = new HashMap<>();
    }

    @Override
    public Set<Entry<K, Collection<V>>> entrySet() {
        return mapa.entrySet();
    }

    @Override
    public Collection<V> put(K key, Collection<V> values) {
        return mapa.put(key, new ArrayList<>(values));
    }

    public void putAllMappings(K key, Collection<V> values) {
        mapa.putIfAbsent(key, new ArrayList<>());
        mapa.get(key).addAll(values);
    }

    public Collection<V> getAllMappings(Object key) {
        return mapa.getOrDefault(key, Collections.emptyList());
    }

    public Collection<V> removeAllMappings(Object key) {
        Collection<V> valores = mapa.remove(key);
        return valores != null ? valores : Collections.emptyList();
    }

    
    // Método extra: Verifica si un valor específico está en el MultiMapa
	@Override
	public boolean containsValue(Object value) {
	    for (Collection<V> valores : mapa.values()) {
	        if (valores.contains(value)) {
	            return true;
	        }
	    }
	    return false;
	}

    
    // Nuevo método extra: Devuelve el número total de asociaciones clave-valor
    public int totalMappings() {
        int count = 0;
        for (Collection<V> valores : mapa.values()) {
            count += valores.size();
        }
        return count;
    }
}