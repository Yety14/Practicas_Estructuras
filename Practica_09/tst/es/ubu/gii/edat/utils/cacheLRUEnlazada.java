package es.ubu.gii.edat.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class cacheLRUEnlazada<K,V> extends LinkedHashMap<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2399759402416810177L;

	private int MAX_ENTRIES;

	public cacheLRUEnlazada(int maxSize){
		super(maxSize,1f,true);
		this.MAX_ENTRIES = maxSize;
	}
	
	@Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
       return size() > MAX_ENTRIES;
    }
	
//	@Override
//	public V put(K key, V value){
//		if(containsKey(key))
//			remove(key);
//		return super.put(key,value);
//	}
	
}
