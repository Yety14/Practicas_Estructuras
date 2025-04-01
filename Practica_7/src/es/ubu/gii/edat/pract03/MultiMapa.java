package es.ubu.gii.edat.pract03;

import java.util.*;

public class MultiMapa<K, V> extends AbstractMap<K, V> {
    
    private final Map<K, List<V>> mapa;
    private int totalSize = 0;
    public final Map<K, Integer> lastAccessedIndex; // Nuevo campo para rastrear el último índice accedido

    public MultiMapa() {
        this.mapa = new HashMap<>();
        this.lastAccessedIndex = new HashMap<>();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entries = new LinkedHashSet<>();
        for (Map.Entry<K, List<V>> entry : mapa.entrySet()) {
            for (V value : entry.getValue()) {
                entries.add(new AbstractMap.SimpleEntry<>(entry.getKey(), value));
            }
        }
        return entries;
    }

    public V put(K key, V value) {
        mapa.putIfAbsent(key, new ArrayList<>());
        mapa.get(key).add(value);
        totalSize++;
        return value;
    }

    public void putAllMappings(K key, List<V> values) {
        mapa.putIfAbsent(key, new ArrayList<>());
        mapa.get(key).addAll(values);
        totalSize += values.size();
    }

    public List<V> getAllMappings(Object key) {
        return mapa.getOrDefault(key, new ArrayList<>());
    }

    public List<V> removeAllMappings(Object key) {
        List<V> removed = mapa.remove(key);
        if (removed != null) {
            totalSize -= removed.size();
        }
        return removed != null ? removed : new ArrayList<>();
    }
    
    @Override
    public void clear() {
        this.mapa.clear();
        totalSize = 0;
    }
    
    public V get(Object key) {
        List<V> values = mapa.get(key);
        if (values == null || values.isEmpty()) {
            return null;
        }
        
        int index = lastAccessedIndex.getOrDefault(key, 0);
        if (index >= values.size()) {
            index = 0;
        }
        
        // Solo actualizamos el índice si realmente vamos a devolver el valor
        lastAccessedIndex.put((K) key, index+1);
        return values.get(index);
    }

    public V remove(Object key) {
        List<V> values = mapa.get(key);
        if (values == null || values.isEmpty()) {
            return null;
        }

        // Siempre eliminamos el primer elemento (FIFO)
        V removedValue = values.remove(0);
        totalSize--;

        if (values.isEmpty()) {
            mapa.remove(key);
            lastAccessedIndex.remove(key);
        } else {
            lastAccessedIndex.put((K) key, 0); // Reiniciamos el índice
        }

        return removedValue;
    }

    
    @Override
    public boolean containsValue(Object value) {
        for (List<V> values : mapa.values()) {
            if (values.contains(value)) {
                return true;
            }
        }
        return false;
    }
   
    @Override
    public boolean containsKey(Object key) {
        return mapa.containsKey(key); 
    }
    
    @Override
    public Collection<V> values() {
        List<V> allValues = new ArrayList<>();
        for (List<V> values : mapa.values()) {
            allValues.addAll(values);
        }
        return allValues;
    }
    
    @Override
    public int size() {
        return totalSize;
    }
    
    @Override
    public boolean isEmpty() {
        return totalSize == 0;
    }
    
}